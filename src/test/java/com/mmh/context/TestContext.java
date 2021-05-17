package com.mmh.context;

import com.mmh.pageObects.*;
import com.mmh.pageObects.AssessmentPage.*;
import com.mmh.pageObects.patientPortalPages.PatientLoginPage;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class TestContext {

    public WebDriver driver;
    public Scenario scenario;
    private String userName;
    private BasePage basePage;
    private Homepage homepage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AssessmentPage assessmentPage;
    private AllAssessmentsPage allAssessmentsPage;
    private PatientLoginPage patientLoginPage;
    //    private ADLTab adlWorkingFrequencyPage;
//    private ADLWorkingNaturePage adlWorkingNaturePage;
//    private MHGeneralQuestions mhGeneralQuestions;
//    private MHPersonalHealthHistory mhPersonalHealthHistory;
//    private MHMedications mhMedications;
//    private MHSurgery mhSurgery;
//    private MHFamilyHistory mhFamilyHistory;
//    private MedicalHistoryTab mhExercise;
    private RecommendationReportPage recommendationReportPage;
    private IntakeInformationPage intakeInformationPage;
    private RegistrationInformationPage registrationInformationPage;

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

    public BasePage getTopNavigationBar() {
        return basePage;
    }

    public Homepage getHomepage() {
        return homepage;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        return dashboardPage;
    }

    public RecommendationReportPage getRecommendationReportPage() {
        return recommendationReportPage;
    }

    public IntakeInformationPage getIntakeInformationPage() {
        return intakeInformationPage;
    }

    public RegistrationInformationPage getRegistrationInformationPage() {
        return registrationInformationPage;
    }

    public AssessmentPage getAssessmentPage() {
        return assessmentPage;
    }

    public AllAssessmentsPage getAllAssessmentsPage() {
        return allAssessmentsPage;
    }

    public PatientLoginPage getPatientLoginPage() {
        return patientLoginPage;
    }

    //    public ADLTab getAdlWorkingFrequencyPage() {
//        return adlWorkingFrequencyPage;
//    }

//    public ADLWorkingNaturePage getAdlWorkingNaturePage() {
//        return adlWorkingNaturePage;
//    }
//
//    public MHGeneralQuestions getMhGeneralQuestions() {
//        return mhGeneralQuestions;
//    }
//
//    public MHPersonalHealthHistory getMhPersonalHealthHistory() {
//        return mhPersonalHealthHistory;
//    }
//
//    public MHMedications getMhMedications() {
//        return mhMedications;
//    }
//
//    public MHSurgery getMhSurgery() {
//        return mhSurgery;
//    }
//
//    public MHFamilyHistory getMhFamilyHistory() {
//        return mhFamilyHistory;
//    }

//    public MedicalHistoryTab getMhExercise() {
//        return mhExercise;
//    }

    public String getUserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public void initializePageObjectClasses() {
        driver = getDriver();
        basePage = new BasePage(driver, scenario);
        homepage = new Homepage(driver, scenario);
        loginPage = new LoginPage(driver, scenario);
        dashboardPage = new DashboardPage(driver, scenario);
        recommendationReportPage = new RecommendationReportPage(driver, scenario);
        intakeInformationPage = new IntakeInformationPage(driver, scenario);
        registrationInformationPage = new RegistrationInformationPage(driver, scenario);
        assessmentPage = new AssessmentPage(driver, scenario);
        allAssessmentsPage = new AllAssessmentsPage(driver, scenario);
        patientLoginPage = new PatientLoginPage(driver, scenario);
//        adlWorkingFrequencyPage = new ADLTab(driver, scenario);
//        adlWorkingNaturePage = new ADLWorkingNaturePage(driver, scenario);
//        mhGeneralQuestions = new MHGeneralQuestions(driver, scenario);
//        mhPersonalHealthHistory = new MHPersonalHealthHistory(driver, scenario);
//        mhMedications = new MHMedications(driver, scenario);
//        mhSurgery = new MHSurgery(driver, scenario);
//        mhFamilyHistory = new MHFamilyHistory(driver, scenario);
//        mhExercise = new MedicalHistoryTab(driver, scenario);

    }
}
