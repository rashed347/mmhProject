package com.mmh.pageObects.AssessmentPage;

import com.mmh.component.FormSubmitModal;
import com.mmh.component.Form;
import com.mmh.component.SkinnyManSkeleton;
import com.mmh.models.chiefComplaints.BodyPart;
import com.mmh.models.chiefComplaints.ChiefComplaints;
import com.mmh.models.Question;
import com.mmh.models.chiefComplaints.Section;
import com.mmh.utils.MmhHtmlElementManager;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChiefComplaintsTab extends Tab {
    SkinnyManSkeleton skinnyManSkeleton;
    FormSubmitModal formSubmitModal;
    Form treatmentTypeForm;
    Form symptomsDescriptionForm;
    Form symptomsFrequencyForm;
    Form painDescriptionForm;
    Form painFrequencyForm;
    Form painLevelForm;

    private final String TREATMENT_TYPE_SECTION_TITLE = "TYPE OF TREATMENT";
    private final String SYMPTOMS_DESCRIPTION_TITLE = "DESCRIBE YOUR SYMPTOMS - DESCRIPTION";
    private final String SYMPTOMS_FREQUENCY_TITLE = "DESCRIBE YOUR SYMPTOMS -FREQUENCY";
    private final String PAIN_DESCRIPTION_TITLE = "DESCRIBE YOUR PAIN AT ITS WORST - DESCRIPTION";
    private final String PAIN_FREQUENCY_TITLE = "DESCRIBE YOUR PAIN AT ITS WORST- FREQUENCY";
    private final String PAIN_LEVEL = "RATE YOUR LEVEL OF PAIN";

    public ChiefComplaintsTab(MmhHtmlElementManager elementManager, Scenario scenario) {
        super(elementManager, scenario);
        this.tabID = "tab_chief";
        this.skinnyManSkeleton = new SkinnyManSkeleton(elementManager);
        this.formSubmitModal = new FormSubmitModal(elementManager, ".swal2-modal");
    }

    public Form getTreatmentTypeForm() {
        return treatmentTypeForm;
    }

    public Form getSymptomsDescriptionForm() {
        return symptomsDescriptionForm;
    }

    public Form getSymptomsFrequencyForm() {
        return symptomsFrequencyForm;
    }

    public Form getPainDescriptionForm() {
        return painDescriptionForm;
    }

    public Form getPainFrequencyForm() {
        return painFrequencyForm;
    }

    public Form getPainLevelForm() {
        return painLevelForm;
    }

    public FormSubmitModal getFormSubmitModal() {
        return formSubmitModal;
    }

    private List<WebElement> getAllFormsSection(String bodyPart) {
        return elementManager.findElements(By.xpath(
                "//div[@id='" + bodyPart + "']//div[@class='padding-15-lr']"
        ));
    }

    private List<WebElement> getAllFormsSectionHeader(String bodyPart) {
        return elementManager.findElements(By.xpath("//div[@id='" + bodyPart + "']// h4"));
    }

    public void fillAllForms(String bodyPart, Map<String, String> table) {
        List<WebElement> allFormsSection = getAllFormsSection(bodyPart);
        List<WebElement> allFormsSectionHeader = getAllFormsSectionHeader(bodyPart);

        Assert.assertEquals(allFormsSection.size(), allFormsSectionHeader.size());

        for (int i = 0; i < allFormsSection.size(); i++) {
            String sectionTitle = allFormsSectionHeader.get(i).getText().trim();
            String value = table.get(sectionTitle);
            switch (sectionTitle) {
                case TREATMENT_TYPE_SECTION_TITLE:
                    this.treatmentTypeForm = new Form(elementManager, allFormsSection.get(i));
                    this.treatmentTypeForm.fillupFormRadioButtonsWithSameValue(value);
                    break;
                case SYMPTOMS_DESCRIPTION_TITLE:
                    this.symptomsDescriptionForm = new Form(elementManager, allFormsSection.get(i));
                    this.symptomsDescriptionForm.fillupFormRadioButtonsWithSameValue(value);
                    break;
                case SYMPTOMS_FREQUENCY_TITLE:
                    this.symptomsFrequencyForm = new Form(elementManager, allFormsSection.get(i));
                    this.symptomsFrequencyForm.fillupFormRadioButtonsWithSameValue(value);
                    break;
                case PAIN_DESCRIPTION_TITLE:
                    this.painDescriptionForm = new Form(elementManager, allFormsSection.get(i));
                    this.painDescriptionForm.fillupFormRadioButtonsWithSameValue(value);
                    break;
                case PAIN_FREQUENCY_TITLE:
                    this.painFrequencyForm = new Form(elementManager, allFormsSection.get(i));
                    this.painFrequencyForm.fillupFormRadioButtonsWithSameValue(value);
                    break;
                case PAIN_LEVEL:
                    this.painLevelForm = new Form(elementManager, allFormsSection.get(i));
                    this.painLevelForm.fillupFormRadioButtonsWithSameValue(value);
                    break;
            }
        }
    }

    public SkinnyManSkeleton getSkinnyManSkeleton() {
        return skinnyManSkeleton;
    }

    private List<WebElement> getAllBodyPartsElement() {
        return elementManager.findElements(By.cssSelector("div.hideActivityOfDailyLiving"));
    }

    public List<String> getBodyPartIds() {
        List<String> bodyPartIds = new ArrayList<>();
        List<WebElement> bodyPartsElement = getAllBodyPartsElement();
        for (WebElement element :
                bodyPartsElement) {
            bodyPartIds.add(element.getAttribute("id"));
        }
        return bodyPartIds;
    }

    public ChiefComplaints getChiefComplaints() {
        List<BodyPart> bodyParts = new ArrayList<>();
        List<String> bodyPartIds = getBodyPartIds();

        for (int i = 0; i < bodyPartIds.size(); i++) {
            List<Section> sections = new ArrayList<>();
            String bodyPartId = bodyPartIds.get(i);
            List<WebElement> formSectionHeaders = getAllFormsSectionHeader(bodyPartId);
            List<WebElement> formSections = getAllFormsSection(bodyPartId);

            for (int j = 0; j < formSectionHeaders.size(); j++) {
                List<Question> questions = new ArrayList<>();
                String formSectionHeader = formSectionHeaders.get(j).getAttribute("innerText").trim();
                Form formSection = new Form(elementManager, formSections.get(j));
                for (WebElement element :
                        formSection.getAllQuestionsFromForm()) {
                    String questionTitle = formSection.getQuestionTitle(element);
                    String questionId = formSection.getQuestionId(element);
                    String fieldType = formSection.getFieldType(element);
                    String answer = "";
                    questions.add(new Question(questionTitle, questionId, fieldType, answer));
                }
                sections.add(new Section(formSectionHeader, questions));
            }
            bodyParts.add(new BodyPart(bodyPartId, sections));
        }
        return new ChiefComplaints(bodyParts);
    }
}
