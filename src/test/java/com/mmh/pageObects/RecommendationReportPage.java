package com.mmh.pageObects;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RecommendationReportPage extends BasePage {

    private final By neckPointsBar = By.cssSelector("div#NeckGraphPointsbar");
    private final By upperBar = By.cssSelector("div#Upperbar");
    private final By backGraphPointsBar = By.cssSelector("div#BackGraphPointsbar");
    private final By lowerGraphPointsBar = By.cssSelector("div#LowerGraphPointsbar");
    private final By barMarkerValue = By.cssSelector(".multi-bar-marker-value");

    public RecommendationReportPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    public List<String> getAllBarsValue() {
        List<String> barValues = new ArrayList<>();
        List<WebElement> barValueElements = elementManager.findElements(barMarkerValue);
        for (WebElement element : barValueElements
        ) {
            barValues.add(element.getText());
        }
        scenario.log("Neck: " + barValues.get(0) + " Upper: " + barValues.get(1)
                + " Back: " + barValues.get(2) + " Lower: " + barValues.get(3));
        return barValues;
    }
}
