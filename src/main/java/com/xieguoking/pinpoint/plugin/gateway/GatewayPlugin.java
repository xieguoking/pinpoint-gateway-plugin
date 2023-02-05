package com.xieguoking.pinpoint.plugin.gateway;

import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentMethod;
import com.navercorp.pinpoint.bootstrap.instrument.Instrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;

import java.security.ProtectionDomain;

/**
 * @author xieguoking
 * @author (2023 / 2 / 4 add by xieguoking
 * @version 1.0
 * @since 1.0
 */
public class GatewayPlugin implements ProfilerPlugin, TransformTemplateAware {

    private final PLogger logger = PLoggerFactory.getLogger(this.getClass());
    private TransformTemplate transformTemplate;

    @Override
    public void setTransformTemplate(TransformTemplate template) {
        this.transformTemplate = transformTemplate;
    }

    @Override
    public void setup(ProfilerPluginSetupContext context) {
        final GatewayPluginConfig config = new GatewayPluginConfig(context.getConfig());

        if (!config.isEnable()) {
            if (logger.isInfoEnabled()) {
                logger.info("Disable {}. config={}", getClass().getName(), config);
            }
            return;
        }
        logger.info("{} config:{}", this.getClass().getName(), config);

        addGatewayExtends("org.springframework.cloud.gateway.filter.NettyRoutingFilter", GatewayExtendsTransform.class);
    }


    private void addGatewayExtends(final String targetClassName, Class<? extends TransformCallback> transformCallback){
        transformTemplate.transform(targetClassName, transformCallback);
    }

    public static final class GatewayExtendsTransform implements TransformCallback {
        @Override
        public byte[] doInTransform(Instrumentor instrumentor, ClassLoader loader, String className, Class<?> aClass, ProtectionDomain domain, byte[] classfileBuffer) throws InstrumentException {

            final InstrumentClass target = instrumentor.getInstrumentClass(loader, className, classfileBuffer);

            final InstrumentMethod filterMethod = target.getDeclaredMethod("filter", "org.springframework.web.server.ServerWebExchange", "org.springframework.cloud.gateway.filter.GatewayFilterChain");
            if (filterMethod != null) {
                filterMethod.addScopedInterceptor(null, _.GATEWAY_SCOPE);
            }

            return classfileBuffer;
        }
    }

}
