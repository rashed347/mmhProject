package com.mmh.pageObects.patientPortalPages;

import com.mmh.pageObects.BasePage;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientTypeSelectionPage extends BasePage {

    private final By createMyAccountButton = By.cssSelector("a[href$=sign-up]");
    private final By signInButton = By.cssSelector("a[href$=sign-in]");

    public PatientTypeSelectionPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    public void navigateToPatientTypeSelectionPage(String url) {
        elementManager.navigateToPage(url);
    }

    public void clickCreateMyAccountButton() {
        elementManager.clickElement(createMyAccountButton);
    }

    public void clickSignInButton() {
        elementManager.clickElement(signInButton);
    }
}
