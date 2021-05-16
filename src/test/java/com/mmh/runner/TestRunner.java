package com.mmh.runner;

import io.cucumber.testng.*;
import org.testng.annotations.*;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "com/mmh/stepDefinations",
        tags = "@config",
        monochrome = true,
        plugin = {
                "json:target/cucumber-report/cucumber.json"
        }
)
public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
    private String scenarioName;

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod()
    public void beforeMethod(Object[] params) {
        PickleWrapper pickleWrapper = (PickleWrapper) params[0];
        scenarioName = pickleWrapper.getPickle().getName();
    }

    @Test(description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runScenario(pickle.getPickle());
    }

    @DataProvider()
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner != null) {
            testNGCucumberRunner.finish();
        }
    }

}
