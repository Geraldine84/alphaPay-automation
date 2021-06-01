package login;

import static org.testng.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTests;
import base.UtilityTests;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProfilePage;

public class LoginTests extends BaseTests {
	
	@Test
	public void testSuccessfulLogin() throws FileNotFoundException, IOException, ParseException {
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		path = new File(classpathRoot, "resources/data.config.json");
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Login");

		String valid_email = (String) envs.get("valid_email");
		String valid_pw = (String) envs.get("valid_pw");
		
		UtilityTests.testTitle("Login with valid email: "+ valid_email + " and valid password: " + valid_pw);
		
		LoginPage loginPage = homePage.clickLoginButton();
		loginPage.setEmail(valid_email);
		loginPage.setPassword(valid_pw);
		DashboardPage dashboardPage = loginPage.clickLoginButton();
		assertTrue(dashboardPage.getSuccessText().contains("Login Successful"), "Message is incorrect");
		testInfo.get().log(Status.INFO, dashboardPage.getSuccessText() + " found");
	}
	
	@Test
	public void testLogout() throws InterruptedException {
		DashboardPage dashboardPage = new DashboardPage(driver);
		ProfilePage profilePage = dashboardPage.clickProfileIcon();
		profilePage.clickLogoutButton();
		
		UtilityTests.testTitle("Logout User");
		
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getWelcomeText(), "Welcome back, Please log in to your account to access your dashboard");
		testInfo.get().log(Status.INFO, loginPage.getWelcomeText() + " found");
	}
	
}

