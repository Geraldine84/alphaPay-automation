package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	private WebDriver driver;
	//private By formAuthenticationLink = By.linkText("Form Authentication");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage clickFormAuthentication() {
	//	driver.findElement(formAuthenticationLink).click();
		clickLink("Form Authentication");
		return new LoginPage(driver);
	}

	public DropdownPage clickDropdown() {
		clickLink("Dropdown");
		return new DropdownPage(driver);
	}
	
	public void clickLink(String lintText) {
		driver.findElement(By.linkText(lintText)).click();
	}

	public HoversPage clickHovers() {
		clickLink("Hovers");
		return new HoversPage(driver);
	}

	public KeyPressesPage clickKeyPresses() {
		clickLink("Key Presses");
		return new KeyPressesPage(driver);
	}

	public AlertsPage clickJavaScriptAlerts() {
		clickLink("JavaScript Alerts");
		return new AlertsPage(driver);
	}
	
	public FileUploadPage clickFileUpload() {
		clickLink("File Upload");
		return new FileUploadPage(driver);
	}
	
	public WysiwygEditorPage clickWysiwygEditor() {
		clickLink("WYSIWYG Editor");
		return new WysiwygEditorPage(driver);
	}
	
	public DynamicLoadingPage clickDynamicLoading() {
		clickLink("Dynamic Loading");
		return new DynamicLoadingPage(driver);
	}
	
	public LargeAndDeepDomPage clickLargeAndDeepDom() {
		clickLink("Large & Deep DOM");
		return new LargeAndDeepDomPage(driver);
	}
	
	public InfifniteScrollPage clickInfiniteScroll() {
		clickLink("Infifnite Scroll");
		return new InfifniteScrollPage(driver);
	}
	
	public MultipleWindowsPage clickMultipleWindows() {
		clickLink("Multiple Windows");
		return new MultipleWindowsPage(driver);
	}
}
