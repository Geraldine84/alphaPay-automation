package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

	private  WebDriver driver;
	private By updateDetailsButton = By.cssSelector("form > button.button.btn_colored");
	private By updateAlert = By.xpath("//div/div/div[2]");
	private By securityButton = By.linkText("Security");
	private By fullnameField = By.name("full_name");
	private By usernameField = By.name("username");
	private By logoutButton = By.linkText("Log Out");
	
	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void updateDetails(String fullname, String username) throws InterruptedException {
		setFullname(fullname);
		setUsername(username);
		clickUpdateDetailsButton();
	}
	
	public void setFullname(String fullname) {
		driver.findElement(fullnameField).clear();
		driver.findElement(fullnameField).sendKeys(fullname);
	}

	public void setUsername(String username) {
		driver.findElement(usernameField).clear();
		driver.findElement(usernameField).sendKeys(username);
	}
	
	public void clickUpdateDetailsButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		scrollToElement(updateDetailsButton);
		driver.findElement(updateDetailsButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(updateAlert));
	}
	
	public SecurityPage clickSecurityButton() {
		driver.findElement(securityButton).click();
		return new SecurityPage(driver);
	}
	
	public LoginPage clickLogoutButton() {
		driver.findElement(logoutButton).click();
		return new LoginPage(driver);
	}
	
	public String getSuccessText() {
		return driver.findElement(updateAlert).getText();
	}
	
	public  void scrollToElement(By element) throws InterruptedException {
		WebElement webElement = driver.findElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		Thread.sleep(500); 
	}
}
