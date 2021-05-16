package com.mmh.pageObects;

import com.mmh.component.MmhTable;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AllAssessmentsPage extends BasePage {
    private final By searchField = By.cssSelector("#SearchTable_filter input");
    private final By assessmentTable = By.cssSelector("table#SearchTable");

    private MmhTable allAssessmentList;

    public AllAssessmentsPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    public MmhTable getAllAssessmentList() {
        return allAssessmentList;
    }

    public void openAssessmentEditView(int rowIndex) {
        allAssessmentList.getCell(rowIndex, "View")
                .findElement(By.cssSelector(".btn")).click();
        elementManager.waitForAnimation();
    }

    public void searchForAssessment(String assessmentId) {
        elementManager.findElement(searchField).sendKeys(assessmentId);
        elementManager.waitForAnimation();
        elementManager.waitForTheElementToBeInvisible(By.cssSelector("#SearchTable_processing[style$='block;']"));
        this.allAssessmentList = new MmhTable(elementManager, assessmentTable);
    }
}
