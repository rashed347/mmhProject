package com.mmh.pageObects;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    private final By userNameField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By loginButton = By.id("login-btn");
    private final By sideBar = By.cssSelector(".page-sidebar");

    public void navigateToLoginPage(String url) {
        elementManager.navigateToPage(url);
    }

    public void clickLoginButton() {
        elementManager.clickElement(loginButton);
        scenario.log("Clicked register button");
        elementManager.waitForPageLoad();
        Assert.assertTrue(elementManager.isElementVisible(sideBar));
    }

    public void fillupLoginForm(String username, String password) {
        elementManager.fillTextField(userNameField, username);
        scenario.log("Value entered in username field: " + username);

        elementManager.fillTextField(passwordField, password);
        scenario.log("Value entered in password field: " + password);
    }
}
