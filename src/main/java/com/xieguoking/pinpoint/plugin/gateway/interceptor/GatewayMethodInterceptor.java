package com.xieguoking.pinpoint.plugin.gateway.interceptor;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.SpanEventSimpleAroundInterceptorForPlugin;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.InterceptorScope;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.InterceptorScopeInvocation;
import com.xieguoking.pinpoint.plugin.gateway.GatewayContextFactory;
import com.xieguoking.pinpoint.plugin.gateway._;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

/**
 * @author xieguoking
 * @author (2023 / 2 / 5 add by xieguoking
 * @version 1.0
 * @since 1.0
 */
public class GatewayMethodInterceptor extends SpanEventSimpleAroundInterceptorForPlugin {

    private InterceptorScope interceptorScope;

    public GatewayMethodInterceptor(TraceContext traceContext, MethodDescriptor methodDescriptor, InterceptorScope interceptorScope) {
        super(traceContext, methodDescriptor);
        this.interceptorScope = interceptorScope;
    }

    @Override
    protected void doInBeforeTrace(SpanEventRecorder recorder, Object o, Object[] objects) throws Exception {
        final InterceptorScopeInvocation invocation = interceptorScope.getCurrentInvocation();
        if (invocation != null) {
            invocation.getOrCreateAttachment(GatewayContextFactory.INSTANCE);
        }
    }

    @Override
    public void doInAfterTrace(SpanEventRecorder recorder, Object target, Object[] args, Object result, Throwable throwable) {

        if (args[0] instanceof ServerWebExchange) {
            ServerWebExchange exchange = (ServerWebExchange) args[0];
            URI routeUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
            String endPoint = String.format("%s:%d", routeUri.getHost(), routeUri.getPort());

            recorder.recordEndPoint(endPoint != null ? endPoint : "Unknown");
            recorder.recordDestinationId(_.GATEWAY.getName());
            recorder.recordServiceType(_.GATEWAY);
            recorder.recordException(throwable);
        }

    }
}
