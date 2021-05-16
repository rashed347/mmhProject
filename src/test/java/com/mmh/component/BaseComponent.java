package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.WebDriver;

public class BaseComponent {
MmhHtmlElementManager elementManager;

    public BaseComponent(MmhHtmlElementManager elementManager) {
        this.elementManager = elementManager;
    }
}
