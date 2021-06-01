package signup;

import static org.testng.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTests;
import base.UtilityTests;
import pages.DashboardPage;
import pages.SignUpPage;

public class SignUpTests extends BaseTests{
	
	private String email = "test" + UtilityTests.generateRandomNumbers() + "@yopmail.com";
	private String phoneNumber = UtilityTests.generatePhoneNumber();
	private String username = "test" + UtilityTests.uniqueString(3);
	
	@Test
	public void testSuccessfulSignUp() throws FileNotFoundException, IOException, ParseException {
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		path = new File(classpathRoot, "resources/data.config.json");
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Login");

		String valid_fullname = (String) envs.get("valid_fullname");
		String valid_pw = (String) envs.get("valid_pw");
		String valid_confirm_pass = (String) envs.get("valid_pw");
		
		UtilityTests.testTitle("Create Account with email: " + email + " and valid password: " + valid_pw);
		SignUpPage signUpPage = homePage.clickSignUpLink();
		signUpPage.setFullname(valid_fullname);
		signUpPage.setUsername(username);
		signUpPage.setPhoneNumber(phoneNumber);
		signUpPage.setEmail(email);
		signUpPage.setPassword(valid_pw);
		signUpPage.setConfirmPassword(valid_confirm_pass);
		DashboardPage dashboardPage = signUpPage.clickCreateAccountButton();
		assertTrue(dashboardPage.getSuccessText().contains("Signup Successful"), "Message is incorrect");
		testInfo.get().log(Status.INFO, dashboardPage.getSuccessText() + " found");
	}
	

}
