package com.mmh.drivers;

import com.mmh.configurations.Config;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver createDriver(String portalName) throws Exception {

        return new DriverFactory().create(portalName);

    }

    private WebDriver create(String portalName) throws Exception {
        Config config = Config.getInstance();
        String browserName = portalName.equals("patientPortal") ? config.getConfigDriver().getPatientBrowser()
                : config.getConfigDriver().getProviderBrowser();
        String platFormName = portalName.equals("patientPortal") ? config.getConfigDriver().getPatientPlatform()
                : config.getConfigDriver().getProviderPlatform();

        DriverManager driverManager = getDriverManager(browserName);

        WebDriver driver = config.getConfigDriver().isRemote() ? driverManager.initDriver(platFormName)
                : driverManager.initDriver();

        //Return the driver instance to the calling class.
        return driver;
    }

    public DriverManager getDriverManager(String browserType) throws Exception {
        DriverManager driverManager;
        switch (browserType.toLowerCase()) {
            case "chrome":
                driverManager = new ChromeManager();
                break;
            case "firefox":
                driverManager = new FirefoxManager();
                break;
            default:
                throw new Exception("no such browser is present to be initialize. Browser name: " + browserType);
        }

        return driverManager;
    }
}
