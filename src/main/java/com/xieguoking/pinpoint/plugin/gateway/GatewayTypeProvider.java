package com.xieguoking.pinpoint.plugin.gateway;

import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.AnnotationKeyFactory;
import com.navercorp.pinpoint.common.trace.AnnotationKeyMatchers;
import com.navercorp.pinpoint.common.trace.TraceMetadataProvider;
import com.navercorp.pinpoint.common.trace.TraceMetadataSetupContext;

/**
 * @author xieguoking
 * @author (2023 / 2 / 4 add by xieguoking
 * @version 1.0
 * @since 1.0
 */
public class GatewayTypeProvider implements TraceMetadataProvider {

    public static final AnnotationKey GATEWAY_IO = AnnotationKeyFactory.of(888, "gateway.io");

    @Override
    public void setup(TraceMetadataSetupContext context) {
        context.addServiceType(_.GATEWAY, AnnotationKeyMatchers.exact(GATEWAY_IO));
    }
}
