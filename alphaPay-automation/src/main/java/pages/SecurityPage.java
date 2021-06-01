package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurityPage {

	private WebDriver driver;
	private By oldPasswordField = By.name("password");
	private By newPasswordField = By.name("new_password");
	private By changePasswordButton = By.cssSelector("button.button.btn_colored");
	private By successAlert = By.xpath("//div/div/div[2]");

	public SecurityPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setOldPassword(String oldPassword) {
		driver.findElement(oldPasswordField).clear();
		driver.findElement(oldPasswordField).sendKeys(oldPassword);
	}

	public void setNewPassword(String newPassword) {
		driver.findElement(newPasswordField).clear();
		driver.findElement(newPasswordField).sendKeys(newPassword);
	}
	
	public void clickChangePasswordButton() {
		driver.findElement(changePasswordButton).click();
	}
	
	public String getSuccessText() {
		return driver.findElement(successAlert).getText();
	}
}
