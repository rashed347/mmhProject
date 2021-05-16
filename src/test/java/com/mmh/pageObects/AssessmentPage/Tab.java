package com.mmh.pageObects.AssessmentPage;

import com.mmh.component.Form;
import com.mmh.models.Question;
import com.mmh.models.generalForm.GeneralForm;
import com.mmh.utils.MmhHtmlElementManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Tab {
    MmhHtmlElementManager elementManager;
    Scenario scenario;
    String tabID;

    public Tab(MmhHtmlElementManager elementManager, Scenario scenario) {
        this.elementManager = elementManager;
        this.scenario = scenario;
    }

    private WebElement getTab() {
        return elementManager.findElement(By
                .cssSelector("li:not([style='display: none'])>a[href^='#" + tabID + "']"));
    }

    public void click() {
        WebElement visibleTab = getTab();
        if (!visibleTab.findElement(By.xpath("/..")).getAttribute("class").contains("active")) {
            visibleTab.click();
        }
    }

    private List<WebElement> getAllTabs() {
        return elementManager.findElements(By.cssSelector("div[id^=" + tabID + "]"));
    }

    public void fillAllForms(DataTable data) {
        List<WebElement> tabs = getAllTabs();
        for (WebElement tab :
                tabs) {
            Form form = new Form(elementManager, tab);
            form.fillupFormFieldsByUsingDataTable(data);
            tabs.remove(tab);
            if (tabs.size() != 0) {
                elementManager.findElement(By.cssSelector(".button-next")).click();
                elementManager.waitForJSandJQueryToLoad();
            }
        }
    }

    public List<GeneralForm> getAllQuestions() {
        String tabName;
        List<GeneralForm> forms = new ArrayList<>();

        List<WebElement> tabs = getAllTabs();
        int tabIndex = 1;
        for (WebElement tab :
                tabs) {
            List<Question> questions = new ArrayList<>();
            Form form = new Form(elementManager, tab);
            List<WebElement> allQuestions = form.getAllQuestionsFromForm();
            for (WebElement element :
                    allQuestions) {
                String questionTitle = form.getQuestionTitle(element);
                String questionId = form.getQuestionId(element);
                String fieldType = form.getFieldType(element);
                String answer = "";
                questions.add(new Question(questionTitle, questionId, fieldType, answer));
            }
            if (tabs.size() == 1) {
                tabName = tabID;
            } else {
                tabName = tabID + tabIndex;
            }
            tabIndex++;
            forms.add(new GeneralForm(tabName, questions));
        }
        return forms;
    }


}
