package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Form extends BaseComponent {
    WebElement parentElement;
    DatePicker datePicker;
    TextField textField;
    HeightPicker heightPicker;
    RadioButton radioButton;
    Checkbox checkbox;
    Dropdown dropdown;

    public Form(MmhHtmlElementManager elementManager, WebElement parentElement) {
        super(elementManager);
        this.parentElement = parentElement;

        this.heightPicker = new HeightPicker(elementManager);
        this.datePicker = new DatePicker(elementManager);
        this.radioButton = new RadioButton(elementManager);
        this.checkbox = new Checkbox(elementManager);
        this.textField = new TextField(elementManager);
        this.dropdown = new Dropdown(elementManager);
    }

    public List<WebElement> getAllQuestionsFromForm() {
        return parentElement.findElements(By.cssSelector("div.form-group:not([style='display: none;']):not([style*='display:none'])"));
    }

    public void fillupFormFieldsByUsingDataTable(DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        List<WebElement> allFields = getAllQuestionsFromForm();
        int numberOfDataRow = data.size();
        int numberOfFields = allFields.size();
        Assert.assertEquals(numberOfDataRow, numberOfFields,
                "data and fields mismatch. Number of dataRow: " + numberOfDataRow + ", Number of fields: " + numberOfFields + "");

        for (int i = 0; i < data.size(); i++) {
            String fieldType = data.get(i).get("Field Type");
            String fieldValue = data.get(i).get("Field Value");
            WebElement element = allFields.get(i);

            if (fieldValue.equals(""))
                switch (fieldType) {
                    case "TextField":
                        textField.setParentElement(element).clearField().setData(fieldValue);
                        break;
                    case "HeightPicker":
                        heightPicker.setParentElement(element).setHeightValue(fieldValue);
                        break;
                    case "DatePicker":
                        datePicker.setParentElement(element).clickDateField().setDate(fieldValue);
                        break;
                    case "Dropdown":
                        dropdown.setParentElement(element).selectFromDropdown(fieldValue);
                        break;
                    case "RadioButton":
                        radioButton.setParentElement(element).selectOption(fieldValue);
                        break;
                    case "Checkbox":
                        checkbox.setParentElement(element).selectOption(fieldValue);
                        break;
                    case "ReadOnly":
                        continue;
                }
            elementManager.clickElement(By.xpath("//body"));
        }
    }

    //Following method is created to test chief complaints
    public void fillupFormRadioButtonsWithSameValue(String value) {
        List<WebElement> allQuestions = getAllQuestionsFromForm();
        for (WebElement question :
                allQuestions) {
            radioButton.setParentElement(question).selectOption(value);
        }
    }

    public String getQuestionTitle(WebElement questionElement) {
        return questionElement.findElements(By.cssSelector("label")).get(0).getAttribute("innerText").trim();
    }

    public String getQuestionId(WebElement questionElement) {
        return questionElement.getAttribute("id");
    }

    public String getFieldType(WebElement questionElement) {
        String fieldType = null;
        List<WebElement> elements = questionElement.findElements(By.cssSelector("div[class*=col-md]>*:nth-child(1)"));
        WebElement element = elements.get(elements.size() - 1);
        String tagName = element.getTagName();
        switch (tagName) {
            case "select":
                fieldType = "Dropdown";
                break;
            case "div":
                List<WebElement> radioFields = questionElement.findElements(
                        By.cssSelector("div[class*=col-md] .md-radio-inline, div[class*=col-md] .md-radio-list"));
                if (radioFields.isEmpty()) {
                    fieldType = "Checkbox";
                } else {
                    fieldType = "RadioButton";
                }
//                String classAttribute = element.getAttribute("class");
//                if (classAttribute.equals("md-radio-inline") || classAttribute.equals("md-radio-list")) {
//                    fieldType = "RadioButton";
//                } else if (classAttribute.equals("md-checkbox-inline")) {
//                    fieldType = "Checkbox";
//                }
                break;
            case "input":
                if (element.getAttribute("readonly") != null) {
                    fieldType = element.getAttribute("id") == "Height" ? "HeightPicker" : "ReadOnly";
                } else if (element.getAttribute("class").contains("mask_date")) {
                    fieldType = "DatePicker";
                } else {
                    fieldType = "TextField";
                }
                break;
            default:
                throw new NotFoundException("Unknown field type");
        }
        return fieldType;
    }
}
