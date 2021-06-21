package navigation;

import org.testng.annotations.Test;

import base.BaseTests;

public class NavigationTests extends BaseTests{
	
	@Test
	public void testNavigator() {
		homePage.clickDynamicLoading().clickExample1();
		getWindowManager().goback();
		getWindowManager().refreshPage();
		getWindowManager().goForward();
		getWindowManager().goTo("https://google.com");
	}
	
	@Test
	public void testSwitchTab() {
		homePage.clickMultipleWindows().clickHere();
		getWindowManager().switchToTab("New Window");
	}

}
