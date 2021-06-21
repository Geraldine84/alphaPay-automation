package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WysiwygEditorPage {

	private WebDriver driver;
	private String editorIframeId = "mce_0_ifr";
	private By textArea = By.id("tinymce");
	private By decreaseIndentButton = By.cssSelector("#mceu_12 button");

	public WysiwygEditorPage(WebDriver driver) {
		this.driver = driver;
	}

	private void switchToEditArea() {
		driver.switchTo().frame(editorIframeId);
	}

	public void clearTextArea() {
		switchToEditArea();
		driver.findElement(textArea).clear();
		switchToMainArea();
	}

	public void setTextArea(String text) {
		switchToEditArea();
		driver.findElement(textArea).sendKeys(text);
		switchToMainArea();
	}

	private void switchToMainArea() {
		driver.switchTo().parentFrame();
	}

	public void decreaseIndention() {
		driver.findElement(decreaseIndentButton).click();
	}

	public String getTextFromEditor() {
		switchToEditArea();
		String text = driver.findElement(textArea).getText();
		switchToMainArea();
		return text;
	}
}
