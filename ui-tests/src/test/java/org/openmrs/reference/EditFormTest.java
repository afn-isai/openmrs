package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.ManageFormsPage;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.uitestframework.test.TestBase;
import org.openqa.selenium.By;
import static org.junit.Assert.assertNotNull;


/**
 * Created by nata on 24.06.15.
 */
public class EditFormTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private ManageFormsPage manageForm;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;

    @Before
    public void setUp() throws Exception {
       
        homePage = new HomePage(page);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
        manageForm = new ManageFormsPage(driver);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
    }

    @Ignore//ignore due to moving forms functionality
    @Test
    public void EditFormTest() throws Exception {
        homePage.goToManageForm();
        if(!manageForm.addPresent()) {
            manageForm.delete();
        }
        manageForm.add();
        manageForm.addLabel("Eye Report");
        manageForm.addIcon("icon-align-justify");
        manageForm.formIdFromUrl();
        manageForm.save();
        headerPage.clickOnHomeIcon();
        homePage.goToManageForm();
        manageForm.editPath();
        manageForm.addLabel("Eye Test");
        manageForm.save();
        headerPage.clickOnHomeIcon();
        homePage.goToActiveVisitPatient();
        assertNotNull("Eye Test", patientDashboardPage.FORM_EXIST);
//        Delete Form
        headerPage.clickOnHomeIcon();
        homePage.goToManageForm();
        manageForm.deletePath();
        assertNotNull("Add", manageForm.ADD);

    }
    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }
}

