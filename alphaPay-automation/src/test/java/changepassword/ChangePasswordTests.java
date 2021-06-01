package changepassword;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import base.BaseTests;
import base.UtilityTests;
import pages.DashboardPage;
import pages.ProfilePage;
import pages.SecurityPage;

public class ChangePasswordTests extends BaseTests{
	
	@Test
	private void testSucessfulChangePassword() throws IOException, ParseException, InterruptedException {
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		path = new File(classpathRoot, "resources/data.config.json");
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("ChangePassword");
		
		String pw = (String) envs.get("pw");
		
		// Generate new password 
		String new_pw = (String) envs.get("new_pw");
		new_pw = new_pw + UtilityTests.generateRandomNumbers();
		
		DashboardPage dashboardPage = new DashboardPage(driver);
		ProfilePage profilePage = dashboardPage.clickProfileIcon();
		SecurityPage securityPage = profilePage.clickSecurityButton();
		securityPage.setOldPassword(pw);
		securityPage.setNewPassword(new_pw);
		securityPage.clickChangePasswordButton();
		Assert.assertEquals(securityPage.getSuccessText(), "Password updated successfully");
		
		//automatically update JSON with new password
		envs.put("pw", new_pw);
		config.put("ChangePassword", envs);
		@SuppressWarnings("resource")
		FileWriter file = new FileWriter("resources/data.config.json");
		Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
		String newJson = gson1.toJson(config);
		file.write(newJson);
		file.flush();
	}
}
