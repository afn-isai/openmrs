/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;

import static org.junit.Assert.assertTrue;


/**
 * Created by nata on 20.07.15.
 */
public class XSSOnPhoneNumberFieldTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private ManageFormsPage manageForm;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;
    private RegistrationPage registrationPage;

    @Before
    public void setUp() throws Exception {
        
        homePage = new HomePage(page);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
        manageForm = new ManageFormsPage(driver);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        registrationPage = new RegistrationPage(driver);
        homePage.goToActiveVisitPatient();
    }

    @Test
    @Category(org.openmrs.reference.groups.BuildTests.class)
    public void  XSSOnPhoneNumberFieldTest() throws Exception {
        patientDashboardPage.clickOnShowContact();
        patientDashboardPage.clickOnEditContact();
        registrationPage.clickOnPhoneNumberEdit();
        registrationPage.clearPhoneNumber();
        registrationPage.enterPhoneNumber("<script>alert(0)</script>");
        registrationPage.clickOnConfirmEdit();
        assertTrue(driver.getPageSource().contains("Must be a valid phone number (with +, -, numbers or parentheses)"));
        registrationPage.clearPhoneNumber();
        registrationPage.enterPhoneNumber("111111111");
        registrationPage.clickOnConfirmEdit();
        registrationPage.confirmPatient();
        patientDashboardPage.waitForVisitLinkHidden();
        assertTrue(driver.getPageSource().contains("Saved changes in contact info for"));

    }
    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }
}

