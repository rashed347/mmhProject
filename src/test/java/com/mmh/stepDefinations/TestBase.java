package com.mmh.stepDefinations;

import com.mmh.configurations.Config;
import com.mmh.configurations.Driver;
import com.mmh.context.TestContext;
import com.mmh.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase {
    TestContext testContext;
    Config config;
    WebDriver providerDriver;
    WebDriver patientDriver;

    public TestBase(TestContext testContext) throws IOException, URISyntaxException {
        this.testContext = testContext;
        this.config = Config.getInstance();
    }

    public String getRandomString() {
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return simpleDateFormat.format(currentDate);
    }

    public WebDriver createDrive(String portalName) throws Exception {
        Driver getDriver = config.getConfigDriver();
        WebDriver driver = DriverFactory.createDriver(portalName);
        driver.manage().timeouts().pageLoadTimeout(getDriver.getTimeOutSecs(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
