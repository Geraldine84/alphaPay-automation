package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	private By emailField = By.name("email");
	private By passwordField = By.name("password");
	private By loginButton = By.cssSelector("button.button.btn_colored");
	private By loginStatusAlert = By.xpath("//div/div[2]/div/div[2]");
	private By welcomeText = By.xpath("//p");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setEmail(String email) {
		driver.findElement(emailField).clear();
		driver.findElement(emailField).sendKeys(email);
	}

	public void setPassword(String password) {
		driver.findElement(passwordField).clear();
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public DashboardPage clickLoginButton() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.findElement(loginButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginStatusAlert));
		return new DashboardPage(driver);
	}
	
	public String getWelcomeText() {
		return driver.findElement(welcomeText).getText();
		
	}
	
}
