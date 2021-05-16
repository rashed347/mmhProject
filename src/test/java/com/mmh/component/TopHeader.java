package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopHeader extends BaseComponent {
    private final By topHeaderElement = By.cssSelector(".navbar-fixed-top");
    private final By logoElement = By.cssSelector("img.logo-default");
    private final By sidebarTogglerElement = By.cssSelector(".sidebar-toggler");
    private final By calendarIconElement = By.cssSelector(".icon-calendar");
    private final By failedExercisesElement = By.cssSelector("a[href*='failedexercises']");
    private final By userElement = By.cssSelector(".dropdown-user");

    public TopHeader(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public boolean hasLogo() {
        return elementManager.findElement(topHeaderElement)
                .findElements(logoElement).size() > 0;
    }

    public WebElement getLogo() {
        return elementManager.findElement(topHeaderElement)
                .findElement(logoElement);
    }

    public boolean hasSidebarToggler() {
        return elementManager.findElement(topHeaderElement)
                .findElements(sidebarTogglerElement).size() > 0;
    }

    public WebElement getSidebarToggler() {
        return elementManager.findElement(topHeaderElement)
                .findElement(sidebarTogglerElement);
    }

    public boolean hasCalendarIcon() {
        return elementManager.findElement(topHeaderElement)
                .findElements(calendarIconElement).size() > 0;
    }

    public WebElement getCalendarIcon() {
        return elementManager.findElement(topHeaderElement)
                .findElement(sidebarTogglerElement);
    }

}
