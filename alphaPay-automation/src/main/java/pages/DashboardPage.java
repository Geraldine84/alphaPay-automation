package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	
	private WebDriver driver;
	private By statusAlert = By.xpath("//div/div[2]/div/div[2]");
	private By profileIcon = By.xpath("//div/a/img[@alt='profile_image']");
	private By welcomeAlert = By.xpath("//div/div/div/div[2]");

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getSuccessText() {
		return driver.findElement(statusAlert).getText();
	}
	
	public ProfilePage clickProfileIcon() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(welcomeAlert));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
		driver.findElement(profileIcon).click();
		return new ProfilePage(driver);
	}
}
