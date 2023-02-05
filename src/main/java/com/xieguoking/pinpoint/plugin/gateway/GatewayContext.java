package com.xieguoking.pinpoint.plugin.gateway;

/**
 * @author xieguoking
 * @author (2023 / 2 / 5 add by xieguoking
 * @version 1.0
 * @since 1.0
 */
public class GatewayContext {

    private long readBeginTime;
    private long readEndTime;
    private boolean readFail;

    private String host;

    private Throwable cause;

    public long getReadBeginTime() {
        return readBeginTime;
    }

    public void setReadBeginTime(long readBeginTime) {
        this.readBeginTime = readBeginTime;
    }

    public long getReadEndTime() {
        return readEndTime;
    }

    public void setReadEndTime(long readEndTime) {
        this.readEndTime = readEndTime;
    }

    public boolean isReadFail() {
        return readFail;
    }

    public void setReadFail(boolean readFail) {
        this.readFail = readFail;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
