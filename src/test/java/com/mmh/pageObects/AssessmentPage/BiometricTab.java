package com.mmh.pageObects.AssessmentPage;

import com.mmh.utils.MmhHtmlElementManager;
import io.cucumber.java.Scenario;

public class BiometricTab extends Tab {
    public BiometricTab(MmhHtmlElementManager elementManager, Scenario scenario) {
        super(elementManager, scenario);
        this.tabID = "tab_bio";
    }
}
