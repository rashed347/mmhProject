package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;

public class HeightPicker extends BaseComponent {

    private final By feetField = By.cssSelector("input#demoFeet");
    private final By inchField = By.cssSelector("input#demoInches");
    private final By heightValueField = By.cssSelector("input#Height");
    private WebElement parentElement;
    private String feet;
    private String inch;

    public HeightPicker(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public HeightPicker setParentElement(WebElement parentElement) {
        this.parentElement = parentElement;
        return this;
    }

    public WebElement getFeetField() {
        return parentElement.findElement(feetField);
    }

    public WebElement getInchField() {
        return parentElement.findElement(inchField);
    }

    public WebElement getHeightValueField() {
        return parentElement.findElement(heightValueField);
    }

    public void setHeightValue(String heightValue) {
        Matcher matcher = elementManager.matchPattern(heightValue, "(\\d)'\\s(\\d+)");
        if (matcher.find()) {
            this.feet = matcher.group(1);
            this.inch = matcher.group(2);
        }
        getFeetField().clear();
        getFeetField().sendKeys(feet);
        getInchField().clear();
        getInchField().sendKeys(inch);
    }

}
