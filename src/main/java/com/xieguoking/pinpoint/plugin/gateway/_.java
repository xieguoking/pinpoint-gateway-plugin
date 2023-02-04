package com.xieguoking.pinpoint.plugin.gateway;

import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeFactory;

import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.RECORD_STATISTICS;
import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.TERMINAL;

/**
 * @author xieguoking
 * @author (2023 / 2 / 4 add by xieguoking
 * @version 1.0
 * @since 1.0
 */
public class _ {

    public static final ServiceType GATEWAY = ServiceTypeFactory.of(8200, "GATEWAY", TERMINAL, RECORD_STATISTICS);
    public static final String GATEWAY_SCOPE = "gatewayScope";
}
