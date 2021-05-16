package com.mmh.utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MmhHtmlElementManager {
    private WebDriver driver;
    private Scenario scenario;

    public MmhHtmlElementManager(WebDriver driver) {
        this.driver = driver;
    }

    private final int TIMEOUTSECS = 120;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public WebElement findElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    private void applyJsOnElement(WebElement element, String js) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("(function (e) {" + js + "})(arguments[0])", element);
    }

    public void clickElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    public WebElement waitForToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void applyActionClick(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).click().perform();
    }

    public void applyActionClick(By by) {
        WebElement element = findElement(by);
        applyActionClick(element);
    }

    public void navigateToPage(String url) {
        driver.get(url);
    }

    public void verifyThePageTitle(String expectedPageTitle) {
        String pageTitle = getPageTitle();
        scenario.log("Got the page title, which is \"" + pageTitle + "\"");
        Assert.assertEquals(pageTitle, expectedPageTitle);
        scenario.log("Verified the page title");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getText(By by) {
        return findElement(by).getText();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void fillTextField(By by, String value) {
        WebElement element = findElement(by);
        element.sendKeys(value);
    }

    public void fillTextField(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void applyActionSendKeys(WebElement element, String value) {
        Actions act = new Actions(driver);
        act.moveToElement(element).click().sendKeys(value).perform();
    }

    public void scrollToElement(WebElement element, String elementPosition) {
        String isDown = elementPosition != "up" ? "true" : "false";
        applyJsOnElement(element, "e.scrollIntoView(" + isDown + ")");
    }

    public void scrollToElement(By by, String elementPosition) {
        scrollToElement(findElement(by), elementPosition);
    }

    public Boolean validateElementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public Boolean isElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException timeoutException) {
            return false;
        }
    }

    public Boolean isElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException timeoutException) {
            return false;
        }
    }

    public Boolean isAttributePresenceInElement(WebElement element, String attribute) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        return wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    public void waitForTheElementToBeInvisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForTheSpinnerToBeInvisible() {
        List<WebElement> elements = driver.findElements(By.cssSelector(".blockUI.blockElement"));

        if (elements.size() != 0) {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        }
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUTSECS);
        List<WebElement> loading = findElements(By.cssSelector("div#loading"));
        if (loading.size() > 0)
            wait.until(ExpectedConditions.attributeToBeNotEmpty(loading.get(0), "style"));
    }

    private By findParentMenu(String title) {
        return By.xpath(
                "//ul[@class='page-sidebar-menu']//span[contains(text(),'" + title + "')]/ancestor::li"
        );
    }

    private By findChildMenu(String title) {
        return By.xpath(".//span[contains(text(),'" + title + "')]/parent::a/..");
    }

    public void waitForAnimation() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnMainMenu(String path) {
        String[] pagePath = path.split(">");
        WebElement parentMenu = findElement(findParentMenu(pagePath[0].trim()));
        if (pagePath.length > 1) {
            for (int i = 0; i < pagePath.length; i++) {
                if (!parentMenu.getAttribute("class").contains("open")) {
                    parentMenu.click();
                    waitForAnimation();
                    if (i != pagePath.length - 1) {
                        parentMenu = parentMenu.findElement(findChildMenu(pagePath[i + 1].trim()));
                    }

                }
            }

        } else {
            parentMenu.click();
        }

        waitForPageLoad();
    }

    public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public Matcher matchPattern(String anyString, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        return pattern.matcher(anyString);
    }

    public void applyJavaScriptClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void highLightElement(WebElement webElement) {
        //WebElement webElement = driver.findElement(elementBy);
        String originalStyle = webElement.getAttribute("style");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]", webElement, "style", originalStyle + "border: 2px solid red;");

        //Do something e.g. make a screenshot

        //Reset style
        // js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", webElement, "style", originalStyle);
    }

    public byte[] takeScreenShot() {
        TakesScreenshot shot = (TakesScreenshot) driver;
//        logger.info("Screen Shot taken for full driver. ");
        return shot.getScreenshotAs(OutputType.BYTES);
    }

    public byte[] takeScreenShot(By by) {
        TakesScreenshot shot = (TakesScreenshot) driver.findElement(by);
//        logger.info("Screen Shot taken for element: " + by.toString() );
        return shot.getScreenshotAs(OutputType.BYTES);
    }

    public File takeScreenShotAsFile() {
        TakesScreenshot shot = (TakesScreenshot) driver;
//        logger.info("Screen Shot taken for full driver and returned as a file.");
        return shot.getScreenshotAs(OutputType.FILE);
    }

    public File takeScreenShotAsFile(By by) {
        TakesScreenshot shot = (TakesScreenshot) driver.findElement(by);
//        logger.info("Screen Shot taken for element and returned as a file. By descp: " + by.toString());
        return shot.getScreenshotAs(OutputType.FILE);
    }

    public void takeScreenShotAndAttachInReport() {
        scenario.attach(takeScreenShot(), "image/png", scenario.getName().replace(" ", "_"));
    }
}
