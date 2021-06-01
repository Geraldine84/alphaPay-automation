package base;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;

import base.SendMail;

public class BaseTests {

	protected static WebDriver driver;
	protected HomePage homePage;
	public static ExtentReports reports;
	public static ExtentSparkReporter  htmlReporter;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testInfo = new ThreadLocal<ExtentTest>();
	public static String toAddress;

	@BeforeSuite
    @Parameters("groupReport")
	public void setUp( String groupReport) throws Exception {

		htmlReporter = new ExtentSparkReporter (new File(System.getProperty("user.dir") + groupReport));
		reports = new ExtentReports();
		reports.setSystemInfo("TEST ENVIRONMENT", "https://alphapay.netlify.app/");
		reports.attachReporter(htmlReporter);
	}
	
	@BeforeClass
	public void setUp() {
		
		ExtentTest parent = reports.createTest(getClass().getName());
	    parentTest.set(parent);
		
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://alphapay.netlify.app/");
		
		homePage = new HomePage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@BeforeMethod(description = "fetch test cases name")
	public void register(Method method) throws InterruptedException {

		ExtentTest child = parentTest.get().createNode(method.getName());
		testInfo.set(child);
		testInfo.get().getModel().setDescription(UtilityTests.CheckBrowser());
		if (UtilityTests.isAlertPresents()) {
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
		}
	}
	
	@AfterMethod(description = "to display the result after each test method")
	public void captureStatus(ITestResult result) throws IOException {
		for (String group : result.getMethod().getGroups())
			testInfo.get().assignCategory(group);
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = UtilityTests.addScreenshot();
			 testInfo.get().addScreenCaptureFromBase64String(screenshotPath);
			testInfo.get().fail(result.getThrowable());
			driver.navigate().refresh();
		}			
        else if (result.getStatus() == ITestResult.SKIP)
        	testInfo.get().skip(result.getThrowable());
        else
        	testInfo.get().pass(result.getName() +" Test passed");

		reports.flush();
	}
	
	@Parameters({"toMails", "groupReport"})
    @AfterSuite(description = "clean up report after test suite")
    public void cleanup(String toMails, String groupReport) {

        toAddress = System.getProperty("email_list", toMails);
        SendMail.ComposeGmail("AlphaPay Report <gtbankuct.test.report@gmail.com>", toAddress, groupReport);

    }
	
	@Parameters("user")
	@Test
	public void login(String user) throws FileNotFoundException, IOException, ParseException {
		
		File path = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		path = new File(classpathRoot, "resources/data.config.json");
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(path));
		JSONObject envs = (JSONObject) config.get(user);

		String email = (String) envs.get("email");
		String pw = (String) envs.get("pw");
		
		UtilityTests.testTitle("Login with valid email: "+ email + " and valid password: " + pw);
		
		LoginPage loginPage = homePage.clickLoginButton();
		loginPage.setEmail(email);
		loginPage.setPassword(pw);
		DashboardPage dashboardPage = loginPage.clickLoginButton();
		assertTrue(dashboardPage.getSuccessText().contains("Login Successful"), "Message is incorrect");
		testInfo.get().log(Status.INFO, dashboardPage.getSuccessText() + " found");
	}
}