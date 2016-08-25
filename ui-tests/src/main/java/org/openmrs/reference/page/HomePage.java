package org.openmrs.reference.page;

import org.openmrs.uitestframework.page.Page;
import org.openmrs.uitestframework.test.TestData;
import org.openqa.selenium.By;

public class HomePage extends Page {

    private static final String FIND_PATIENT_APP_ID = "coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension";
    private static final String REGISTER_PATIENT_APP_ID = "referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension";
    private static final String ACTIVE_VISITS_APP_ID = "org-openmrs-module-coreapps-activeVisitsHomepageLink-org-openmrs-module-coreapps-activeVisitsHomepageLink-extension";
    private static final String APPOINTMENT_SCHEDULING_APP_ID = "appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension";
    private static final String STYLE_GUIDE_APP_ID = "referenceapplication-styleGuide-referenceapplication-styleGuide-extension";
    private static final String SYSTEM_ADMIN_APP_ID = "coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension";
    private static final String CONFIGURE_METADATA_APP_ID = "org-openmrs-module-adminui-configuremetadata-homepageLink-org-openmrs-module-adminui-configuremetadata-homepageLink-extension";
    private static final String DISPENSING_MEDICATION_APP_ID = "dispensing-app-homepageLink-dispensing-app-homepageLink-extension";
    private static final String CAPTURE_VITALS_APP_ID = "referenceapplication-vitals-referenceapplication-vitals-extension";
    private static final String DATA_MANAGAMENT_APP_ID = "coreapps-datamanagement-homepageLink-coreapps-datamanagement-homepageLink-extension";
    private static final By MANAGE_FORM = By.id("formentryapp-forms-homepageLink-formentryapp-forms-homepageLink-extension");
    private static final By SYSTEM_ADMINISTRATION = By.id("coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension");
    private static final By FIND_PATIENT_RECORD = By.cssSelector("#coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension");
    private static final By DATA_MANAGAMENT = By.id("coreapps-datamanagement-homepageLink-coreapps-datamanagement-homepageLink-extension");

    public HomePage(Page page) {
        super(page);
    }

    private boolean isAppButtonPresent(String appId) {
        try {
            return driver.findElement(By.id(appId)) != null;
        }
        catch (Exception ex) {
            return false;
        }
    }

    private void openApp(String appIdentifier) throws InterruptedException {
        driver.get(properties.getWebAppUrl());
        clickOn(By.id(appIdentifier));
    }

    public void captureVitals() {
        clickOn(By.id(CAPTURE_VITALS_APP_ID));
    }

    public int numberOfAppsPresent() {
        return driver.findElements(By.cssSelector("#apps .app")).size();
    }

    public boolean isFindAPatientAppPresent() {
        return isAppButtonPresent(FIND_PATIENT_APP_ID);
    }

    public Boolean isRegisterPatientCustomizedForRefAppPresent() {
        return isAppButtonPresent(REGISTER_PATIENT_APP_ID);
    }

    public RegistrationPage goToRegisterPatientApp() throws InterruptedException {
        clickOn(By.id(REGISTER_PATIENT_APP_ID));
        return new RegistrationPage(this);
    }

    public void openLegacyAdministrationApp()  throws InterruptedException{
        openApp(SYSTEM_ADMIN_APP_ID);
    }

    public Boolean isActiveVisitsAppPresent() {
        return isAppButtonPresent(ACTIVE_VISITS_APP_ID);
    }

    public Boolean isAppointmentSchedulingAppPresent() {
        return isAppButtonPresent(APPOINTMENT_SCHEDULING_APP_ID);
    }

    public Boolean isStyleGuideAppPresent() {
        return isAppButtonPresent(STYLE_GUIDE_APP_ID);
    }

    public Boolean isSystemAdministrationAppPresent() {
        return isAppButtonPresent(SYSTEM_ADMIN_APP_ID);
    }

    public Boolean isConfigureMetadataAppPresent() {
        return isAppButtonPresent(CONFIGURE_METADATA_APP_ID);
    }

    public Boolean isDispensingMedicationAppPresent() {
        return isAppButtonPresent(DISPENSING_MEDICATION_APP_ID);
    }

    public boolean isCaptureVitalsAppPresent() {
        return isAppButtonPresent(CAPTURE_VITALS_APP_ID);
    }

    public boolean isDataManagementAppPresent() {
        return isAppButtonPresent(DATA_MANAGAMENT_APP_ID);
    }

    public ClinicianFacingPatientDashboardPage goToActiveVisitPatient(){
        return goToActiveVisitsSearch().goToPatientDashboardOfLastActiveVisit();
    }

    public ActiveVisitsPage goToActiveVisitsSearch() {
        clickOn(By.id(ACTIVE_VISITS_APP_ID));
        return new ActiveVisitsPage(this);
    }

    public void goToManageForm() {
        clickOn(By.id(CONFIGURE_METADATA_APP_ID));
        clickOn(MANAGE_FORM);
    }

    public AdministrationPage goToAdministration() {
        clickOn(SYSTEM_ADMINISTRATION);
        return new SystemAdministrationPage(this).goToAdvancedAdministration();
    }

    public FindPatientPage clickOnFindPatientRecord(){
    	clickOn(FIND_PATIENT_RECORD);
    	return new FindPatientPage(driver);
    }
    public void goToDataMagament(){ clickOn(DATA_MANAGAMENT);}


    @Override
    public String getPageUrl() {
        return "/referenceapplication/home.page";
    }

    @Override
    public String getPageAliasUrl() {
    	return "/index.htm";
    }

}
