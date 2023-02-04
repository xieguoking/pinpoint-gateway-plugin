package com.xieguoking.pinpoint.plugin.gateway;

import com.navercorp.pinpoint.bootstrap.config.ProfilerConfig;

import java.util.Arrays;
import java.util.List;

/**
 * @author xieguoking
 * @author (2023 / 2 / 4 add by xieguoking
 * @version 1.0
 * @since 1.0
 */
public class GatewayPluginConfig {

    private final boolean enable;
    private final boolean io;

    public GatewayPluginConfig(ProfilerConfig src) {
        this.enable = readBoolean(src, Arrays.asList("profiler.gateway.enable"), true);
        this.io = readBoolean(src, Arrays.asList("profiler.gateway.io"), true);
    }

    private boolean readBoolean(final ProfilerConfig src, final List<String> nameList, final boolean defaultValue) {
        for (String name : nameList) {
            final String value = src.readString(name, null);
            if (value != null) {
                return src.readBoolean(name, defaultValue);
            }
        }
        return defaultValue;
    }

    public boolean isEnable() {
        return enable;
    }

    public boolean isIo() {
        return io;
    }
}
