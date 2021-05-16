package com.mmh.pageObects;

import com.mmh.component.Form;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IntakeInformationPage extends IntakeForm {
    private By intakeInformationPageLocator = By.cssSelector(".active#tab2");

    public IntakeInformationPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    public Form getIntakeInformationForm() {
        return new Form(elementManager, elementManager.findElement(intakeFormLocator)
                .findElement(intakeInformationPageLocator));
    }
}
