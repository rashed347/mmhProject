package com.mmh.configurations;

import java.security.Provider;

public class Driver {
    private boolean Remote;
    private String ProviderBrowser;
    private String PatientBrowser;
    private String ProviderPlatform;
    private String PatientPlatform;
    private int TimeOutSecs;

    public boolean isRemote() {
        return Remote;
    }

    public void setRemote(boolean remote) {
        Remote = remote;
    }

    public String getProviderBrowser() {
        return ProviderBrowser;
    }

    public void setProviderBrowser(String browser) {
        ProviderBrowser = browser;
    }

    public String getProviderPlatform() {
        return ProviderPlatform;
    }

    public void setProviderPlatform(String platform) {
        ProviderPlatform = platform;
    }

    public String getPatientBrowser() {
        return PatientBrowser;
    }

    public void setPatientBrowser(String browser) {
        PatientBrowser = browser;
    }

    public String getPatientPlatform() {
        return PatientPlatform;
    }

    public void setPatientPlatform(String platform) {
        PatientPlatform = platform;
    }

    public int getTimeOutSecs() {
        return TimeOutSecs;
    }

    public void setTimeOutSecs(int timeOutSecs) {
        TimeOutSecs = timeOutSecs;
    }
}
