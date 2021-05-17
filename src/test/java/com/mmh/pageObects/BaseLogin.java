package com.mmh.pageObects;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BaseLogin extends BasePage {
    protected By userNameField;
    protected By passwordField;
    protected By loginButton;

    public BaseLogin(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    public void clickLoginButton() {
        elementManager.clickElement(loginButton);
        scenario.log("Clicked login button");
        elementManager.waitForPageLoad();
    }

    public void fillupLoginForm(String username, String password) {
        elementManager.fillTextField(userNameField, username);
        scenario.log("Value entered in username field: " + username);

        elementManager.fillTextField(passwordField, password);
        scenario.log("Value entered in password field: " + password);
    }
}
