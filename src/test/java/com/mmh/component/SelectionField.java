package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SelectionField extends BaseComponent {
    WebElement parentElement;

    public SelectionField(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public void selectOption(String value) {
        elementManager.applyActionClick(parentElement.findElement(By
                .xpath(".//label[text()[normalize-space(.)='" + value + "']]/span[@class='box']")));
        elementManager.waitForJSandJQueryToLoad();
    }

    public List<String> getAllOptionTitles(WebElement questionElement) {
        ArrayList<String> allOptionTitles = new ArrayList();

        List<WebElement> allOptionElements = parentElement.findElements(By.cssSelector("label"));
        for (WebElement optionElement : allOptionElements) {
            allOptionTitles.add(optionElement.getText().trim());
        }

        return allOptionTitles;
    }
}
