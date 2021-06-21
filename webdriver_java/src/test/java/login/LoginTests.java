package login;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.SecureAreaPage;

public class LoginTests extends BaseTests {
	
	@Test
	public void testSuccessfulLogin() {
	LoginPage loginPage = homePage.clickFormAuthentication();
	loginPage.setUsername("tomsmith");
	loginPage.setPassword("SuperSecretPassword!");
	SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
	assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"), "Message is correct");
	}
	
	
	
	
}

