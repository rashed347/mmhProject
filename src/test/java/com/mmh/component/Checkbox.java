package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Checkbox extends SelectionField {
    public Checkbox(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public Checkbox setParentElement(WebElement parentElement) {
        this.parentElement = parentElement.findElement(By.cssSelector("div.md-radio-inline"));
        return this;
    }

    public void selectCheckbox(String commaSeparatedStringIfHasMultipleValue) {
        String[] checkboxTitles = commaSeparatedStringIfHasMultipleValue.split(",");
        for (String title : checkboxTitles
        ) {
            selectOption(title.trim());
        }
    }
}
