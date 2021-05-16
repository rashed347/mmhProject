package com.mmh.stepDefinations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmh.context.TestContext;
import com.mmh.models.chiefComplaints.ChiefComplaints;
import com.mmh.models.generalForm.GeneralForm;
import com.mmh.pageObects.AllAssessmentsPage;
import com.mmh.pageObects.AssessmentPage.AssessmentPage;
import com.mmh.pageObects.AssessmentPage.Tab;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class AssessmentSteps extends TestBase {

    public AssessmentSteps(TestContext testContext) throws IOException, URISyntaxException {
        super(testContext);
    }

    @When("I select the {string} radio button")
    public void iSelectTheRadioButton(String assessmentType) {
        testContext.getAssessmentPage().getDemographicsInformationTab().selectAssessmentTypeRadioButton(assessmentType);
    }

    @And("I fillup the {string} form in assessment with the following information")
    public void iFillupTheFormInAssessmentWithTheFollowingInformation(String formName, DataTable table) throws Exception {
        Tab tab = null;
        AssessmentPage assessmentPage = testContext.getAssessmentPage();
        switch (formName) {
            case "Demographics":
                tab = assessmentPage.getDemographicsInformationTab();
                break;
            case "ADL":
                tab = assessmentPage.getAdlTab();
                break;
            case "Medical History":
                tab = assessmentPage.getMedicalHistoryTab();
                break;
            default:
                throw new Exception(formName + " is an invalid tab. Please enter the correct form name");
        }
        assert tab != null;
        tab.click();
        tab.fillAllForms(table);

    }


    @Given("I fillup the intake registration information form with the following information")
    public void iFillupTheIntakeRegistrationInformationFormWithTheFollowingInformation(DataTable table) {
        testContext.getRegistrationInformationPage().getRegistrationInformationForm().fillupFormFieldsByUsingDataTable(table);
        testContext.getRegistrationInformationPage().clickContinueButton();
    }

    @Given("I fillup the intake information form with the following information")
    public void iFillupTheIntakeInformationFormWithTheFollowingInformation(DataTable table) {
        testContext.getIntakeInformationPage().getIntakeInformationForm().fillupFormFieldsByUsingDataTable(table);
    }

    @Then("I can generate question list for the chief complaints")
    public void iCanGenerateQuestionListForTheChiefComplaints() throws IOException {
        ChiefComplaints chiefComplaints = testContext.getAssessmentPage().getChiefComplaintsTab().getChiefComplaints();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("chiefComplaints.json"), chiefComplaints);
    }

    @And("I move to the next tab")
    public void iMoveToTheNextTab() {

    }

    @Then("I can generate question list for the following tabs")
    public void iCanGenerateQuestionListForTheFollowingTabs(List<String> tabNames) throws Exception {
        Tab tab = null;
        AssessmentPage assessmentPage = testContext.getAssessmentPage();
        for (int i = 1; i < tabNames.size(); i++) {
            String tabName = tabNames.get(i);
            switch (tabName) {
                case "Biometric":
                    tab = assessmentPage.getBiometricTab();
                    break;
                case "Demographics":
                    tab = assessmentPage.getDemographicsInformationTab();
                    break;
                case "ADL":
                    tab = assessmentPage.getAdlTab();
                    break;
                case "Medical History":
                    tab = assessmentPage.getMedicalHistoryTab();
                    break;
                default:
                    throw new Exception(tabName + " is an invalid tab. Please enter the correct form name");
            }
            if (tab != null) {
                List<GeneralForm> forms = tab.getAllQuestions();
                ObjectMapper mapper = new ObjectMapper();
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(
                        "src//test//resources//" + tabName.trim().replace(" ", "_") + ".json"), forms);
            }
        }

    }

    @And("I search for the {string} in assessment list")
    public void iSearchForTheInAssessmentList(String assessmentId) {
        testContext.getAllAssessmentsPage().searchForAssessment(assessmentId);
    }

    @And("I view the assessment details")
    public void iViewTheAssessmentDetails() {
        testContext.getAllAssessmentsPage().openAssessmentEditView(0);
    }
}
