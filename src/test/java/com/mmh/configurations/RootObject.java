package com.mmh.configurations;

public class RootObject {

    private String MMHTargetEnvironment;
    private TargetEnvironment[] TargetEnvironments;
    private Driver Driver;
    private MmhUser[] Users;

    public String getMMHTargetEnvironment() {
        return MMHTargetEnvironment;
    }

    public void setMMHTargetEnvironment(String mMHTargetEnvironment) {
        MMHTargetEnvironment = mMHTargetEnvironment;
    }

    public TargetEnvironment[] getTargetEnvironments() {
        return TargetEnvironments;
    }

    public void setTargetEnvironments(TargetEnvironment[] targetEnvironments) {
        TargetEnvironments = targetEnvironments;
    }

    public Driver getDriver() {
        return Driver;
    }

    public void setDriver(Driver driver) {
        Driver = driver;
    }

    public MmhUser[] getUsers() {
        return Users;
    }

    public void setUsers(MmhUser[] users) {
        Users = users;
    }

}
