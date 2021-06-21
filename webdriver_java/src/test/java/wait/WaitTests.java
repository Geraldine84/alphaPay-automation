package wait;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTests;
import pages.DynamicLoadingExample1Page;

public class WaitTests extends BaseTests{
	
	@Test
	public void testWaitUntilHidden() {
		DynamicLoadingExample1Page loadingPage = homePage.clickDynamicLoading().clickExample1();
		loadingPage.clickStart();
		Assert.assertEquals(loadingPage.getLoadedtext(), "Hello World!", "Loaded text is incorrect");
	}

}
