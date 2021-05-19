package com.mmh.pageObects.patientPortalPages;

import com.mmh.pageObects.BasePage;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PatientDashboardPage extends BasePage {

    private final String PAIN_RECORDER_BOT_HEADER = "Pain Recorder Virtual Assistant";
    private final String MSK_BOT_HEADER = "Fysical Score™ Screening";
    private final String APPOINTMENT_BOT_HEADER = "Appointment Virtual Assistant";
    private final String PRECALL_TEST_BOT_HEADER = "Precall Test Virtual Assistant";
    private final String PAIN_DASHBOARD_HEADER = "Your Personal Pain Dashboard";
    private final String APPOINTMENT_SCHEDULER_HEADER = "Your Appointment Scheduler";
    private final String GAIT_HEADER = "Gait Analysis";
    private final String POSTURE_HEADER = "Posture Analysis";

    public PatientDashboardPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    private WebElement getDashboardItemElement(String dashboardItemHeader) {
        return elementManager.findElements(By.xpath("//div[div[h3='dashboardItemHeader']]//a")).get(0);
    }

    public void initiateDshboardItem(String dashboardItemName) {
        String dashboardItemNameInLowerCase = dashboardItemName.toLowerCase();

        String dashboardItemHeader = dashboardItemNameInLowerCase.equals("pain recorder") ? PAIN_RECORDER_BOT_HEADER
                : dashboardItemNameInLowerCase.equals("msk bot") ? MSK_BOT_HEADER
                : dashboardItemNameInLowerCase.equals("appointment bot") ? APPOINTMENT_BOT_HEADER
                : dashboardItemNameInLowerCase.equals("precall test bot") ? PRECALL_TEST_BOT_HEADER
                : dashboardItemNameInLowerCase.equals("pain dashboard") ? PAIN_DASHBOARD_HEADER
                : dashboardItemNameInLowerCase.equals("appointment scheduler") ? APPOINTMENT_SCHEDULER_HEADER
                : dashboardItemNameInLowerCase.equals("gait") ? GAIT_HEADER
                : dashboardItemNameInLowerCase.equals("posture") ? POSTURE_HEADER
                : "";

        Assert.assertTrue(dashboardItemHeader.isEmpty(), "Entered invalid name for patient dashboard item");
        getDashboardItemElement(dashboardItemHeader).click();
    }
}
