package com.mmh.stepDefinations;

import com.mmh.context.TestContext;
import com.mmh.pageObects.BasePage;
import io.cucumber.java.Scenario;

public class TopNavigationBarSteps {
    TestContext testContext;
    Scenario scenario;
    BasePage basePage;

    public TopNavigationBarSteps(TestContext testContext) {
        this.testContext = testContext;
        this.scenario = testContext.getScenario();
        this.basePage = testContext.getTopNavigationBar();
    }

}
