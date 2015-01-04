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
package org.openmrs.reference.page;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractBasePage {
	
	static final String FIND_PATIENT_APP_ID = "coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension";
	static final String REGISTER_PATIENT_APP_ID = "referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension";
	static final String ACTIVE_VISITS_APP_ID = "org-openmrs-module-coreapps-activeVisitsHomepageLink-org-openmrs-module-coreapps-activeVisitsHomepageLink-extension";
	static final String STYLE_GUIDE_APP_ID = "referenceapplication-styleGuide-referenceapplication-styleGuide-extension";
	static final String SYSTEM_ADMIN_APP_ID = "coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension";
    static final String CONFIGURE_METADATA_APP_ID = "coreapps-configuremetadata-homepageLink-coreapps-configuremetadata-homepageLink-extension";
	static final String DISPENSING_MEDICATION_APP_ID = "dispensing-app-homepageLink-dispensing-app-homepageLink-extension";
	static final String CAPTURE_VITALS_APP_ID = "referenceapplication-vitals-referenceapplication-vitals-extension";
	static final String HOME_CONTAINER = "home-container";
	static final String CANT_LOGIN = "cant-login";
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	private boolean isAppButtonPresent(String appId) {
		try {
			return driver.findElement(By.id(appId)) != null;
		}
		catch (Exception ex) {
			return false;
		}
	}

	private void openApp(String appIdentifier) {
		driver.get(properties.getWebAppUrl());
		clickOn(By.id(appIdentifier));
        waitForJsVariable("Navigator.isReady");
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
	
	public void openRegisterAPatientApp() {
		openApp(REGISTER_PATIENT_APP_ID);
	}
	
	public void openLegacyAdministrationApp() {
		openApp(SYSTEM_ADMIN_APP_ID);
	}
	
	public Boolean isActiveVisitsAppPresent() {
		return isAppButtonPresent(ACTIVE_VISITS_APP_ID);
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
	
	public String getLoggedAsString() {
		WebElement container = driver.findElement(By.id(HOME_CONTAINER));
		WebElement text = container.findElement(By.xpath("id('home-container')/h4"));
		return text.getText();
	}
	
	public void pressCantLogin() {
		WebElement button = driver.findElement(By.id(CANT_LOGIN));
		button.click();
	}

	@Override
	public String expectedUrlPath() {
		return URL_ROOT + "/referenceapplication/home.page";
	}

}
