package com.mmh.pageObects.AssessmentPage;

import com.mmh.utils.MmhHtmlElementManager;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DemographicsInformationTab extends Tab {
    private By searchForPatientField = By.cssSelector("input#PatientAuto");
    private By patientList = By.cssSelector("ul.ui-autocomplete[style*=block] .table");
    private By chiefComplaintTab = By.cssSelector(".nav a[href*='tab_chief']");


    public DemographicsInformationTab(MmhHtmlElementManager elementManager, Scenario scenario) {
        super(elementManager, scenario);
        this.tabID = "tab1";
    }

    public WebElement getAssessmentTypeRadioButton(String buttonName) {
        return elementManager.findElement(By.cssSelector("label[for='AssessmentType" + buttonName + "']"));
    }

    public void selectAssessmentTypeRadioButton(String buttonName) {
        getAssessmentTypeRadioButton(buttonName).click();
    }

    public void searchAndSelectPatient(String patientEmail) {
        elementManager.findElement(searchForPatientField).sendKeys(patientEmail);
        elementManager.waitForTheElementToBeInvisible(By.cssSelector(".ui-autocomplete-loading"));
        elementManager.findElement(patientList).findElement(By.xpath("//td[contains(text(),'" + patientEmail + "')]")).click();
        elementManager.waitForAnimation();
        elementManager.waitForJSandJQueryToLoad();
    }

    public void navigateToChiefComplaintsPage() {
        elementManager.scrollToElement(chiefComplaintTab, "up");
        elementManager.findElement(chiefComplaintTab).click();
        elementManager.waitForJSandJQueryToLoad();
        elementManager.waitForAnimation();
    }
}
