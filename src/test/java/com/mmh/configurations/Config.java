package com.mmh.configurations;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

public class Config {
    private TargetEnvironment configEnvironment;
    private Driver configDriver;
    private MmhUser[] configUsers;

    //Because this is static it will be executed at the start of any test run.
    //Reads the keys from the config and assigns them to the properties declared above.
    protected Config() throws IOException, URISyntaxException {
        String fileName = "config.json";

        fileName = getFullPath(fileName);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        RootObject rootObject = mapper.readValue(new File(fileName), RootObject.class);

        this.configEnvironment = getTargetEnvironment(rootObject);
        this.configDriver = rootObject.getDriver();
        this.configUsers = rootObject.getUsers();

        applyTargettedBrowser();
    }

    private static Config _instance;

    public static Config getInstance() throws IOException, URISyntaxException {
        if (_instance == null)
            _instance = new Config();
        return _instance;
    }

    private TargetEnvironment getTargetEnvironment(RootObject root) {
        String environmentParameter = System.getenv("EBCTargetEnvironment");

        if (environmentParameter == null || environmentParameter.isEmpty())
            environmentParameter = root.getMMHTargetEnvironment();

        if (environmentParameter == null || environmentParameter.isEmpty())
            environmentParameter = "DEFAULT";

        String finalEnvironmentParameter = environmentParameter;
        TargetEnvironment environment = Arrays.stream(root.getTargetEnvironments())
                .filter(x -> x.getName().equals(finalEnvironmentParameter))
                .findFirst().orElse(null);

        return environment;
    }

    private void applyTargettedBrowser() {
        String environmentRemoteParameter = System.getenv("Remote");

        if (environmentRemoteParameter == "true") {
            configDriver.setRemote(true);
            configDriver.setProviderBrowser(System.getenv("ProviderBrowser"));
            configDriver.setPatientBrowser(System.getenv("PatientBrowser"));
        }

    }

    public Driver getConfigDriver() {

        return configDriver;
    }

    public TargetEnvironment getConfigEnvironement() {
        return configEnvironment;
    }

    public MmhUser[] getConfigUsers() {
        return configUsers;
    }

    public String getFullPath(String fileName) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        File file = Paths.get(resource.toURI()).toFile();

        return file.getAbsolutePath();
    }

}
