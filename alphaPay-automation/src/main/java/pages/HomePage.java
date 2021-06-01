package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	private WebDriver driver;
	private By loginButton = By.cssSelector("button.btn.button.btn_colored");
	private By signUpLink = By.linkText("Sign Up");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage clickLoginButton() {
		driver.findElement(loginButton).click();
		return new LoginPage(driver);
	}
	
	public SignUpPage clickSignUpLink() {
		driver.findElement(signUpLink).click();
		return new SignUpPage(driver);
	}
}
