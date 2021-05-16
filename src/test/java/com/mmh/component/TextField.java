package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextField extends BaseComponent {
    private WebElement parentElement;

    public TextField(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public TextField setParentElement(WebElement parentElement) {
        this.parentElement = parentElement;
        return this;
    }

    private WebElement getTextField() {
        return parentElement.findElement(By.cssSelector("input"));
    }

    public TextField clearField() {
        getTextField().clear();
        return this;
    }

    public void setData(String data) {
        getTextField().sendKeys(data);
    }
}
