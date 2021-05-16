package com.mmh.pageObects;

import com.mmh.utils.MmhHtmlElementManager;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public MmhHtmlElementManager elementManager;
    Scenario scenario;

    public BasePage(WebDriver driver, Scenario scenario) {

        this.elementManager = new MmhHtmlElementManager(driver);
        this.scenario = scenario;
    }

    public MmhHtmlElementManager getElementManager() {
        return elementManager;
    }

}
