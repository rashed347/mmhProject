package com.mmh.drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class ChromeManager extends DriverManager {

    private final String BROWSER_NAME = "chrome";

    @Override
    protected WebDriver initDriver() {
        ChromeOptions options = setOption();
        return new ChromeDriver(options);
    }

    protected WebDriver initDriver(String platformName) throws MalformedURLException {
        ChromeOptions options = setOption();
        Platform platform = getPlatform(platformName);
        DesiredCapabilities capabilities = setBrowserType(BROWSER_NAME, platform);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);

        return getRemoteDriver(options);
    }

    private ChromeOptions setOption() {
        ChromeOptions chromeOptions = new ChromeOptions();
        return chromeOptions;
    }

}

