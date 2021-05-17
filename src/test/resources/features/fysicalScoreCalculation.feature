Feature: As a provider, I can calculate the fysical score after completing chief complaints

  @AutoTest @Test-002
  Scenario Outline: Verify that fysical score is same for the left and right part
    Given I am on the "provider" portal
    Given I navigate to Login page
    Given I login as a "Provider"
    When I present the "Assessment Type > Inclinic Assessment > Full Body" page
    When I select the "Follow Up" radio button
    When I select an existing patient with the email id "testmmh250101@yopmail.com" for the follow up
    And I click chief complaints
    And I select "<Left Body Part>" part from the skeleton
    And I fillup the form for the selected part with following information
      | sectionName                                   | value   |
      | TYPE OF TREATMENT                             | Yes     |
      | DESCRIBE YOUR SYMPTOMS - DESCRIPTION          | Yes     |
      | DESCRIBE YOUR SYMPTOMS -FREQUENCY             | Weekly  |
      | DESCRIBE YOUR PAIN AT ITS WORST - DESCRIPTION | Burning |
      | DESCRIBE YOUR PAIN AT ITS WORST- FREQUENCY    | Weekly  |
      | RATE YOUR LEVEL OF PAIN                       | 8       |
    And I submit the form
    And I get the fysical score for the "left" part
    When I present the "Assessment Type > Inclinic Assessment > Full Body" page
    When I select the "Follow Up" radio button
    When I select an existing patient with the email id "testmmh250101@yopmail.com" for the follow up
    And I click chief complaints
    And I select "<Right Body Part>" part from the skeleton
    And I fillup the form for the selected part with following information
      | sectionName                                   | value   |
      | TYPE OF TREATMENT                             | Yes     |
      | DESCRIBE YOUR SYMPTOMS - DESCRIPTION          | Yes     |
      | DESCRIBE YOUR SYMPTOMS -FREQUENCY             | Weekly  |
      | DESCRIBE YOUR PAIN AT ITS WORST - DESCRIPTION | Burning |
      | DESCRIBE YOUR PAIN AT ITS WORST- FREQUENCY    | Weekly  |
      | RATE YOUR LEVEL OF PAIN                       | 8       |
    And I submit the form
    And I get the fysical score for the "right" part
    Then I compare the both parts

    Examples:
      | Left Body Part      | Right Body Part          |
      | UpperTrapeziusFront | UpperTrapeziusFrontRight |
      | Shoulder1           | Shoulder1Right           |
      | Elbow               | ElbowRight               |
      | Wrist-L-R           | Wrist-L-RRight           |
      | Dorsum1Right        | Dorsum1                  |
      | Dorsum2Right        | Dorsum2                  |
      | Dorsum3Right        | Dorsum3                  |
      | Dorsum4Right        | Dorsum4                  |
      | Dorsum5Right        | Dorsum5                  |
      | Hip                 | HipRight                 |
      | Knee                | KneeRight                |
      | FrontofLeg          | FrontofLegRight          |
      | Ankle               | AnkleRight               |
      | Neck                | NeckRight                |
      | UpperTrapeziusBack  | UpperTrapeziusBackRight  |
      | Mid-Back-1          | Mid-Back-1Right          |
      | Mid-Back-2          | Mid-Back-2Right          |
      | Mid-Back-3          | Mid-Back-3Right          |
      | LowerBack           | LowerBackRight           |
      | Gluteal             | GlutealRight             |
      | Finger1             | Finger1Right             |
      | Finger2             | Finger2Right             |
      | Finger3             | Finger3Right             |
      | Finger4             | Finger4Right             |
      | Finger5             | Finger5Right             |
      | Thigh-Back          | Thigh-BackRight          |
      | Cal                 | CalRight                 |
      | Heel                | HeelRight                |
      | Sole                | SoleRight                |

  @config
  Scenario: Generate question configurations for chief complaints
    Given I am on the "provider" portal
    Given I navigate to Login page
    Given I login as a "Provider"
    When I present the "All Assessment" page
    And I search for the "TEST-14778" in assessment list
    And I view the assessment details
    #Then I can generate question list for the chief complaints
    Then I can generate question list for the following tabs
      | TabName         |
      | Demographics    |
      | ADL             |
      | Medical History |
      | Biometric       |