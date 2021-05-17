package com.mmh.pageObects.patientPortalPages;

import com.mmh.pageObects.BaseLogin;
import com.mmh.pageObects.BasePage;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientLoginPage extends BaseLogin {

    public PatientLoginPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
        this.userNameField = By.name("Email");
        this.passwordField = By.name("Password");
        this.loginButton = By.cssSelector("button[type='submit']");
    }
}
