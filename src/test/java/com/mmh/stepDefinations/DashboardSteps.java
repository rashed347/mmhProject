package com.mmh.stepDefinations;

import com.mmh.context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardSteps extends TestBase {
    String bodyPartName;
    List<String> leftPartScore = new ArrayList<>();
    List<String> rightPartScore = new ArrayList<>();

    public DashboardSteps(TestContext testContext) throws IOException, URISyntaxException {
        super(testContext);
    }

    @When("I present the {string} page")
    public void iPresentThePage(String pagePath) {
        testContext.getDashboardPage().getElementManager().clickOnMainMenu(pagePath);
    }

    @When("I select an existing patient with the email id {string} for the follow up")
    public void iSelectAnExistingPatientWithTheEmailIdForTheFollowUp(String patientEmail) {
        testContext.getAssessmentPage().getDemographicsInformationTab().searchAndSelectPatient(patientEmail);
    }

    @And("I click chief complaints")
    public void iClickChiefComplaints() {
        testContext.getAssessmentPage().getDemographicsInformationTab().navigateToChiefComplaintsPage();
    }

    @And("I select {string} part from the skeleton")
    public void iSelectPartFromTheSkeleton(String bulletPointName) {
        testContext.getAssessmentPage().getChiefComplaintsTab().getSkinnyManSkeleton().selectBulletPoint(bulletPointName);
        bodyPartName = bulletPointName;
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        testContext.getAssessmentPage().getChiefComplaintsTab().getFormSubmitModal().submitTheModal();
    }

    @And("I get the fysical score for the {string} part")
    public void iGetTheFysicalScoreForThePart(String partName) {
        switch (partName) {
            case "left":
                leftPartScore = testContext.getRecommendationReportPage().getAllBarsValue();
                break;
            case "right":
                rightPartScore = testContext.getRecommendationReportPage().getAllBarsValue();
                break;
        }

    }

    @Then("I compare the both parts")
    public void iCompareTheBothParts() {
        Assert.assertEquals(leftPartScore, rightPartScore);
    }

    @And("I fillup the form for the selected part with following information")
    public void iFillupTheFormForTheSelectedPartWithFollowingInformation(Map<String, String> table) {
        testContext.getAssessmentPage().getChiefComplaintsTab().fillAllForms(bodyPartName, table);
    }

    @When("I initiate {string} from patient dashboard")
    public void iInitiateFromPatientDashboard(String patientDashboardItemName) {
        testContext.getPatientDashboardPage().initiateDshboardItem(patientDashboardItemName);
    }
}
