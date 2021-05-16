package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FormSubmitModal extends BaseComponent {
    private String modalLocator;

    public FormSubmitModal(MmhHtmlElementManager elementManager, String modalLocator) {
        super(elementManager);
        this.modalLocator = modalLocator;
    }

    private WebElement getModal() {
        return elementManager.findElement(By.cssSelector(modalLocator));
    }

    public void submitTheModal() {
        getModal().findElement(By.cssSelector("button[class*='confirm']")).click();
        elementManager.waitForJSandJQueryToLoad();
    }

    public void cancelTheModal() {
        getModal().findElement(By.cssSelector("button[class*='cancel']")).click();
    }

}
