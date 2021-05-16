package com.mmh.pageObects.AssessmentPage;

import com.mmh.utils.MmhHtmlElementManager;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class MedicalHistoryTab extends Tab {
    public MedicalHistoryTab(MmhHtmlElementManager elementManager, Scenario scenario) {
        super(elementManager, scenario);
        this.tabID = "tab_med";
    }
}
