package update;

import static org.testng.Assert.assertTrue;

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
import pages.ProfilePage;

public class UpdateDetailsTests extends BaseTests {
	
	private String username = "test" + UtilityTests.uniqueString(3);
	
	@Test
	public void testUpdateDetails() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		path = new File(classpathRoot, "resources/data.config.json");
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get("Login");
		String valid_fullname = (String) envs.get("valid_fullname");
		
		UtilityTests.testTitle("Update Details with valid fullname: "+ valid_fullname + " and valid username: " + username);
		
		DashboardPage dashboardPage = new DashboardPage(driver);
		ProfilePage profilePage = dashboardPage.clickProfileIcon();
		profilePage.updateDetails(valid_fullname, username);
		assertTrue(profilePage.getSuccessText().contains("Update Successful"), "Message is incorrect");
		testInfo.get().log(Status.INFO, profilePage.getSuccessText() + " found");
	}

}
