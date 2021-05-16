package com.mmh.configurations;

public class TargetEnvironment {
    private String Name;
    private String PatientPortal;
    private String ProviderPortal;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPatientPortal() {
        return PatientPortal;
    }

    public void setPatientPortal(String url) {
        PatientPortal = url;
    }

    public String getProviderPortal() {
        return ProviderPortal;
    }

    public void setProviderPortal(String url) {
        ProviderPortal = url;
    }
}
