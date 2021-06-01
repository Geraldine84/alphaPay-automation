package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
	
	private WebDriver driver;
	private By createAccountButton = By.cssSelector("button.button.btn_colored");

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void elementLocator(String name, String value) {
		driver.findElement(By.name(name)).clear();
		driver.findElement(By.name(name)).sendKeys(value);
	}
	
	public void setFullname(String fullname) {
		elementLocator("full_name", fullname);
	}

	public void setUsername(String username) {
		elementLocator("username", username);
	}
	
	public void setPhoneNumber(String phoneNum) {
		elementLocator("phone_number", phoneNum);
	}
	
	public void setEmail(String email) {
		elementLocator("email", email);
	}
	
	public void setPassword(String password) {
		elementLocator("password", password);
	}
	
	public void setConfirmPassword(String confirmPassword) {
		elementLocator("confirmPassword", confirmPassword);
	}
	
	public DashboardPage clickCreateAccountButton() {
		driver.findElement(createAccountButton).click();
		return new DashboardPage(driver);
	}
}
