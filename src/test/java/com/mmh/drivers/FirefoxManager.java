package com.mmh.drivers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class FirefoxManager extends DriverManager {

    private final String BROWSER_NAME = "firefox";

    @Override
    protected WebDriver initDriver() {
        FirefoxOptions options = setOption();
        return new FirefoxDriver(options);
    }

    protected WebDriver initDriver(String platformName) throws MalformedURLException {
        FirefoxOptions options = setOption();
        Platform platform = getPlatform(platformName);
        DesiredCapabilities capabilities = setBrowserType(BROWSER_NAME, platform);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);

        return getRemoteDriver(options);
    }

    private FirefoxOptions setOption() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return firefoxOptions;
    }
}
