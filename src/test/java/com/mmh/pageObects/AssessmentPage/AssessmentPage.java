package com.mmh.pageObects.AssessmentPage;

import com.mmh.component.Form;
import com.mmh.pageObects.BasePage;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AssessmentPage extends BasePage {
    String tabID;
    DemographicsInformationTab demographicsInformationTab;
    ADLTab adlTab;
    MedicalHistoryTab medicalHistoryTab;
    BiometricTab biometricTab;
    ChiefComplaintsTab chiefComplaintsTab;

    public AssessmentPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
        this.demographicsInformationTab = new DemographicsInformationTab(elementManager, scenario);
        this.adlTab = new ADLTab(elementManager, scenario);
        this.medicalHistoryTab = new MedicalHistoryTab(elementManager, scenario);
        this.biometricTab = new BiometricTab(elementManager, scenario);
        this.chiefComplaintsTab = new ChiefComplaintsTab(elementManager, scenario);
    }

    public List<WebElement> getActiveTabs() {
        return elementManager.findElements(By.cssSelector(tabID));
    }

    public WebElement isActiveTab() {
        List<WebElement> activeTabs = getActiveTabs();
        Assert.assertTrue(activeTabs.size() > 0);
        return activeTabs.get(0);
    }

    public Form getForm() {
        WebElement activeTab = isActiveTab();
        return new Form(elementManager, activeTab);
    }

    WebElement getTab(String tabName) {
        List<WebElement> allTabs = getAllTabs();
        WebElement tabElement = null;
        for (WebElement tab : allTabs
        ) {
            if (tab.getAttribute("innerText").contains(tabName)) {
                tabElement = tab;
                break;
            }
        }
        return tabElement;
    }

    List<WebElement> getAllTabs() {
        return elementManager.findElements(By.cssSelector(".steps>li:not([style])>a"));
    }

    public void clickTab(String tabName) {
        WebElement element = getTab(tabName);
        element.click();
        elementManager.waitForAnimation();
    }

    public ADLTab getAdlTab() {
        return adlTab;
    }

    public MedicalHistoryTab getMedicalHistoryTab() {
        return medicalHistoryTab;
    }

    public BiometricTab getBiometricTab() {
        return biometricTab;
    }

    public DemographicsInformationTab getDemographicsInformationTab() {
        return demographicsInformationTab;
    }

    public ChiefComplaintsTab getChiefComplaintsTab() {
        return chiefComplaintsTab;
    }
}
