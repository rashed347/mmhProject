package com.mmh.pageObects;

import com.mmh.component.Form;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationInformationPage extends IntakeForm {
    private By registrationInformationPageLocator = By.cssSelector(".active#tab1");
    private By continueButtonLocator = By.cssSelector("#continue-btn");

    public RegistrationInformationPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    public Form getRegistrationInformationForm() {
        return new Form(elementManager, elementManager.findElement(intakeFormLocator)
                .findElement(registrationInformationPageLocator));
    }

    public void clickContinueButton() {
        elementManager.findElement(continueButtonLocator).click();
    }

}
