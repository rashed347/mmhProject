package com.mmh.pageObects;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BaseLogin {

    public LoginPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
        this.userNameField = By.id("Email");
        this.passwordField = By.id("Password");
        this.loginButton = By.id("login-btn");
    }

    public void navigateToLoginPage(String url) {
        elementManager.navigateToPage(url);
    }

}
