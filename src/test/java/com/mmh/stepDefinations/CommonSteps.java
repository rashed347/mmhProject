package com.mmh.stepDefinations;

import com.mmh.configurations.MmhUser;
import com.mmh.context.TestContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class CommonSteps extends TestBase {

    String completeRoomName;

    public CommonSteps(TestContext testContext) throws IOException, URISyntaxException {
        super(testContext);
    }

    @Given("I navigate to Login page")
    public void iNavigateToLoginPage() {

        testContext.getLoginPage().navigateToLoginPage(config.getConfigEnvironement().getProviderPortal());
    }

    @Given("I login as a {string}")
    public void iLoginAsA(String userType) {
        MmhUser user;
        try {
            user = Arrays.stream(config.getConfigUsers())
                    .filter(p -> p.getUserType().equals(userType)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(userType + " userType does not exist in config");
        }
        testContext.getLoginPage().fillupLoginForm(user.getUserName(), user.getPassword());
        testContext.getLoginPage().clickLoginButton();
    }

    @Given("I click on Invite a Patient link")
    public void iClickOnInviteAPatientLink() {
        testContext.getHomepage().clickInviteAPatientLink();
    }

    @Given("I select the invitation type {string}")
    public void iSelectTheInvitationType(String type) {
        testContext.getHomepage().selectInvitationType(type);
    }

    @Given("I select the body region {string}")
    public void iSelectTheBodyRegion(String region) {
        testContext.getHomepage().selectBodyRegion(region);
    }

    @Given("I enter the patient email {string}")
    public void iEnterThePatientEmail(String email) {
        testContext.getHomepage().enterPatientEmailAddress(email);
    }

    @When("I click on send button")
    public void iClickOnSendButton() {
        testContext.getHomepage().clickSendButton();
    }

    @When("I click on Invite for Telemedicine button from confirmation modal")
    public void iClickOnInviteForTelemedicineButtonFromConfirmationModal() {
        testContext.getHomepage().invitePatientForTelemedicineFromConfirmationModal();
    }

    @When("I create room with the name {string}")
    public void iCreateRoomWithTheName(String roomName) {

        completeRoomName = testContext.getHomepage().createRoom(roomName);
    }

    @And("I go to the room")
    public void iGoToTheRoom() {
        testContext.getHomepage().goToRoomFromCreateNewRoomModal(completeRoomName);
    }

    @Given("I am on the provider portal")
    public void iAmOnTheProviderPortal() throws Exception {
        if (providerDriver == null) {
            providerDriver = createDrive("providerPortal");
        }
        testContext.setDriver(providerDriver);
        testContext.initializePageObjectClasses();
    }

    @Given("I am on the patient portal")
    public void iAmOnThePatientPortal() throws Exception {
        if (patientDriver == null) {
            patientDriver = createDrive("patientPortal");
        }
        testContext.setDriver(patientDriver);
        testContext.initializePageObjectClasses();
    }
}
