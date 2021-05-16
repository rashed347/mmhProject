package com.mmh.drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public abstract class DriverManager {

    protected abstract WebDriver initDriver();

    protected abstract WebDriver initDriver(String platform) throws MalformedURLException;

//    public WebDriver getDriver() {
//        initDriver();
//        return driver;
//    }
//
//    public void quitDriver() {
//        driver.quit();
//    }
//
//    public void closeDriver() {
//        driver.close();
//    }
//
//    public void navigateToDriver(String url) {
//        driver.get(url);
//    }
//
//    public void maximizeBrowser() {
//        driver.manage().window().maximize();
//    }

    public WebDriver getRemoteDriver(MutableCapabilities driverOptions) throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://192.168.0.105:4444/wd/hub"), driverOptions);
    }

    public DesiredCapabilities setBrowserType(String browserName, Platform platform) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        //capabilities.setVersion(browserVersion);
        capabilities.setPlatform(platform);
        return capabilities;
    }

    public Platform getPlatform(String platformName) {
        Platform platform;
        switch (platformName.toLowerCase()) {
            case "mac":
                platform = Platform.MAC;
                break;
            case "windows":
            default:
                platform = Platform.WINDOWS;
                break;
        }
        return platform;
    }
//    public void setImplicitWaitTimeOut(int timeInMiliSeconds) {
//        driver.manage().timeouts().implicitlyWait(timeInMiliSeconds, TimeUnit.MILLISECONDS);
//    }
}
