package com.mmh.pageObects;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Homepage extends BasePage {

    public Homepage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
    }

    private final By inviteAPatientLink = By.cssSelector("#tli-home a#topRefer");
    private final By inviteAPatientModal = By.cssSelector("#ReferAPatientFunc[style^='display: block;'] .modal-content");
    private final By inviteAPatientModalTitle = By.xpath("//h4[@class='modal-title'] /*[text()='Invite a Patient']");
    private final By sendButton = By.cssSelector("button#sendrefermodalbtn");
    private final By confirmationModal = By.cssSelector(".swal2-modal");
    private final By createNewRoomModal = By.xpath("//h4[text()='Create New Virtual Room']/ancestor::div[@class='modal-content']");
    private final By roomHeading = By.cssSelector("#name-heading.room-name");

    public void clickInviteAPatientLink() {
        elementManager.clickElement(inviteAPatientLink);
        Assert.assertTrue(elementManager.isElementPresent(inviteAPatientModalTitle));
    }

    public void selectInvitationType(String type) {
        WebElement typeRadioButton = elementManager.findElement(By.xpath(
                ".//label[text()[contains(.,'" + type + "')]]/preceding-sibling::input[@name='referAssessmentType']"
        ));
        elementManager.applyActionClick(typeRadioButton);
    }

    public void selectBodyRegion(String region) {
        WebElement regionRadioButton = elementManager.findElement(inviteAPatientModal)
                .findElement(By.xpath(
                        ".//label[text()[contains(.,'" + region + "')]]/preceding-sibling::input[@name='regionradio']"
                ));
        elementManager.applyActionClick(regionRadioButton);
    }

    public void enterPatientEmailAddress(String email) {
        WebElement patientEmailAddressField = elementManager.findElement(inviteAPatientModal)
                .findElement(By.cssSelector("textarea#emails"));
        elementManager.fillTextField(patientEmailAddressField, email);
    }

    public void clickSendButton() {
        elementManager.clickElement(sendButton);
        Assert.assertTrue(elementManager.isElementVisible(confirmationModal), "Confirmation modal is not showing");
    }

    public void invitePatientForTelemedicineFromConfirmationModal() {
        WebElement button = elementManager.findElement(confirmationModal)
                .findElement(By.cssSelector("button[aria-label='Do you want to schedule a telemedicine?'"));
        elementManager.clickElement(button);
    }

    public String createRoom(String roomName) {
        WebElement roomNameField = elementManager.findElement(createNewRoomModal)
                .findElement(By.id("room-name"));

        WebElement createButton = elementManager.findElement(createNewRoomModal)
                .findElement(By.id("room-create-btn"));

        elementManager.fillTextField(roomNameField, roomName);
        elementManager.clickElement(createButton);

        elementManager.waitForTheSpinnerToBeInvisible();

        return roomNameField.getAttribute("value");
    }

    public void goToRoomFromCreateNewRoomModal(String roomName) {
        WebElement meetNowButton = elementManager.findElement(createNewRoomModal)
                .findElement(By.id("gtRoom"));

        elementManager.clickElement(meetNowButton);
        elementManager.waitForPageLoad();

        String roomHeader = elementManager.findElement(roomHeading).getText();
        elementManager.getScenario().log(roomHeader + " separator " + roomName);
        Assert.assertTrue(roomHeader==roomName);
    }

    public void sideBarMenuIsDisplayed() {

    }

}
