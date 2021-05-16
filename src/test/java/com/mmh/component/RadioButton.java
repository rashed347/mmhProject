package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RadioButton extends SelectionField {

    public RadioButton(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public RadioButton setParentElement(WebElement parentElement) {
        this.parentElement = parentElement.findElement(By.cssSelector("div.md-radio-inline"));
        return this;
    }
}
