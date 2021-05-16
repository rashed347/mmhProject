package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends BaseComponent {

    private WebElement parentElement;

    public Dropdown(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public Dropdown setParentElement(WebElement parentElement) {
        this.parentElement = parentElement;
        return this;
    }

    public void selectFromDropdown(String value) {
        Select dropdown = new Select(parentElement.findElement(By.cssSelector("select")));
        dropdown.selectByVisibleText(value);
    }
}
