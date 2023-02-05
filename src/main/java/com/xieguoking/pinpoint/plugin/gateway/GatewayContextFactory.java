package com.xieguoking.pinpoint.plugin.gateway;

import com.navercorp.pinpoint.bootstrap.interceptor.scope.AttachmentFactory;

/**
 * @author xieguoking
 * @author (2023 / 2 / 5 add by xieguoking
 * @version 1.0
 * @since 1.0
 */
public class GatewayContextFactory implements AttachmentFactory {

    public static final GatewayContextFactory INSTANCE = new GatewayContextFactory();

    @Override
    public Object createAttachment() {
        return new GatewayContext();
    }
}
