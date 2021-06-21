package alerts;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTests;
import pages.AlertsPage;

public class AlertTests extends BaseTests{
	
	@Test
	public void testAcceptAlert() {
		AlertsPage alertsPage = homePage.clickJavaScriptAlerts();
		alertsPage.triggerAlert();
		alertsPage.alert_clickToAccept();
		alertsPage.getResult();
		Assert.assertEquals(alertsPage.getResult(), "You successfully clicked an alert");
	}
	
	@Test
	public void testGetTextFromAlert() {
		AlertsPage alertsPage = homePage.clickJavaScriptAlerts();
		alertsPage.triggerConfirm();
		
		String text = alertsPage.alert_getText();
		alertsPage.alert_clickToDismiss();
		Assert.assertEquals(text, "I am a JS Confirm", "Alert text incorrect");
	}
	
	@Test
	public void testSetInputAlert() {
		AlertsPage alertsPage = homePage.clickJavaScriptAlerts();
		alertsPage.triggerPrompt();
		
		String text = "Geraldine rocks";
		alertsPage.alert_setInput(text);
		alertsPage.alert_clickToAccept();
		Assert.assertEquals(alertsPage.getResult(), "You entered: " + text, "Results text incorrect");
	}

}
