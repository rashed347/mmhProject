package com.mmh.pageObects;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IntakeForm extends BasePage{
    By intakeFormLocator = By.cssSelector(".info-intake.form");

    public IntakeForm(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }
}
