package com.xieguoking.pinpoint.plugin.gateway;

import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;

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


    }
}
