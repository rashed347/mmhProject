package com.mmh.stepDefinations;

import com.mmh.configurations.Driver;
import com.mmh.context.TestContext;
import com.mmh.drivers.DriverFactory;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class Hooks extends TestBase {


    public Hooks(TestContext testContext) throws IOException, URISyntaxException {
        super(testContext);
    }

    @Before
    public void beforeTest(Scenario scenario) throws IOException, URISyntaxException {
        testContext.setScenario(scenario);
    }

    @After
    public void afterTest() {
        Scenario scenario = testContext.getScenario();
        if (scenario.isFailed()) {
            TakesScreenshot shot = (TakesScreenshot) testContext.getDriver();
            byte[] data = shot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", scenario.getName().replace(" ", "_"));
        }

        if (providerDriver != null)
            providerDriver.quit();
        if (patientDriver != null)
            patientDriver.quit();
    }

    @BeforeStep
    public void beforeStep() {

    }

    @AfterStep
    public void afterStep() {

    }


}
