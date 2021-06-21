package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class InfifniteScrollPage {
	
	private WebDriver driver;
	By textBlocks = By.className("iscroll-added");
	
	public InfifniteScrollPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Scrolls until paragraph with index specified is in view
	 * @param index 1-based
	 */
	public void scrollToParagraph(int index) {
		String script = "window.scrollTo(0, document.body.scrollHeight)";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		while (getNumberOfParagraphs() < index) {
			jsExecutor.executeScript(script);
		}
		
	}
	
	public int getNumberOfParagraphs() {
		return driver.findElements(textBlocks).size();
		
	}

}
