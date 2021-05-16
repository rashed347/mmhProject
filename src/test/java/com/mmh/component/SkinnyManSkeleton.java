package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkinnyManSkeleton extends BaseComponent {

    private By rotateBodyButton = By.xpath("//a[input[@id='BodyDisplay']]");
    private By bulletPoint = By.cssSelector("a.bulletPoint");
    private By bodySideElement = By.cssSelector("li[style*='inline']>h1:nth-child(3)");

    public SkinnyManSkeleton(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public void rotateBody() {
        elementManager.applyActionClick(rotateBodyButton);
    }

    public List<WebElement> getAllBulletPoints() {
        return elementManager.findElements(bulletPoint);
    }

    private String getBulletPointName(WebElement element) {
        String bulletPointName;
        String onclickAttribute = element.getAttribute("onclick");
        Matcher match = elementManager.matchPattern(
                onclickAttribute, ".*true,'(\\w+)',\\d\\)");
        if (match.find()) {
            bulletPointName = match.group(1);
        } else {
            throw new NotFoundException("No match found of given pattern");
        }

        return bulletPointName;
    }

    private void presentTheCorrectBodySide(WebElement element) {
        String bodyPartClass = element.getAttribute("class").split(" ")[1].trim();
        List<WebElement> bodySide = elementManager.findElements(bodySideElement);
        WebElement visibleSide = bodySide.size() > 0 ? bodySide.get(0)
                : elementManager.findElement(By.cssSelector("h1.FrontFrontImg"));
        String presentBodySide = visibleSide.getText().trim().toLowerCase();
        if (!bodyPartClass.startsWith(presentBodySide)) {
            rotateBody();
        }
    }

    public void selectBulletPoint(String pointName) {
        WebElement element = elementManager.findElement(By
                .cssSelector("a.bulletPoint[onclick*=\"'" + pointName + "'\"]"));

        presentTheCorrectBodySide(element);
        //elementManager.scrollToElement(element, "down");
        elementManager.applyActionClick(element);
        elementManager.waitForJSandJQueryToLoad();
    }

    public String selectBulletPoint(WebElement element) {
        presentTheCorrectBodySide(element);
        element.click();
        return getBulletPointName(element);
    }

}
