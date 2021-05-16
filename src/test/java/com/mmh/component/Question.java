package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Question extends BaseComponent {

    WebElement parentElement;

    private final String YES_OPTION = "Yes";
    private final String WEEKLY_OPTION = "Weekly";
    private final String BURNING_OPTION = "Burning";
    private final String PAIN_LEVEL_OPTION = "8";

    public Question(MmhHtmlElementManager elementManager, WebElement parentElement) {
        super(elementManager);
        this.parentElement = parentElement;
    }

    public List<WebElement> getAllQuestionElements() {
        return parentElement.findElements(By.cssSelector("label.control-label"));
    }

    public String getTheForAttributeValueOfQuestion(WebElement questionElement) {
        String forValue = questionElement.getAttribute("for");
        if (forValue.startsWith("Selected")) {
            forValue = forValue.split("Selected")[1];
        }

        return forValue;
    }

    public String getQuestionTitle(WebElement questionElement) {
        return questionElement.getText().trim();
    }

    public WebElement getQuestionElementByTitle(String questionTitle) {
        WebElement questionElement = null;
        List<WebElement> allQuestionElements = getAllQuestionElements();

        for (WebElement question : allQuestionElements) {
            if (question.getText().trim() == questionTitle) {
                questionElement = question;
                break;
            }
        }

        return questionElement;
    }

    public List<WebElement> getAllOptionElements(WebElement questionElement) {
        String forAttributeOfQuestion = getTheForAttributeValueOfQuestion(questionElement);

        return elementManager.findElements(By.cssSelector(
                ".md-radio label[for*='" + forAttributeOfQuestion + "']"));
    }

    public void selectOption(WebElement questionElement, String optionValue) {
        String forAttributeOfQuestion = getTheForAttributeValueOfQuestion(questionElement);
        elementManager.applyActionClick(By.cssSelector(
                ".md-radio label[for*='" + forAttributeOfQuestion + optionValue + "'] .box"));

    }

    public List<String> getAllQuestionTitles() {
        List<String> allQuestionTitles = null;

        List<WebElement> allQuestionElements = getAllQuestionElements();
        for (WebElement questionElement : allQuestionElements) {
            allQuestionTitles.add(getQuestionTitle(questionElement));
        }

        return allQuestionTitles;
    }

    public List<String> getAllOptionTitles(WebElement questionElement) {
        ArrayList<String> allOptionTitles = new ArrayList();

        List<WebElement> allOptionElements = getAllOptionElements(questionElement);
        for (WebElement optionElement : allOptionElements) {
            allOptionTitles.add(optionElement.getText().trim());
        }

        return allOptionTitles;
    }

    public Boolean hasQuestion(String questionTitle) {
        return getQuestionElementByTitle(questionTitle) != null;
    }

    public void waitForTheNextQuestionToBePlacingCorrectly(WebElement question) {
        WebDriverWait wait = new WebDriverWait(elementManager.getDriver(), 1);
        wait.until(ExpectedConditions.attributeContains(
                question.findElement(By.xpath("./..")), "style", "border: 1px"));
    }

    public void fillAllQuestions() {
        List<WebElement> allQuestions = getAllQuestionElements();

        for (WebElement question : allQuestions) {
            List<String> optionTitles = getAllOptionTitles(question);
            if (optionTitles.contains(YES_OPTION)) {
                selectOption(question, YES_OPTION);
            } else if (optionTitles.contains(WEEKLY_OPTION)) {
                selectOption(question, WEEKLY_OPTION);
            } else if (optionTitles.contains(BURNING_OPTION)) {
                selectOption(question, BURNING_OPTION);
            } else if (optionTitles.contains(PAIN_LEVEL_OPTION)) {
                selectOption(question, PAIN_LEVEL_OPTION);
            }
            elementManager.waitForJSandJQueryToLoad();
        }

    }
}
