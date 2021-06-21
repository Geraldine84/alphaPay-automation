package keys;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTests;
import pages.KeyPressesPage;

public class KeysTests extends BaseTests{

	@Test
	public void testBackspace() {
		KeyPressesPage keyPage = homePage.clickKeyPresses();
		keyPage.enterText("A" + Keys.BACK_SPACE);
		Assert.assertEquals(keyPage.getResult(), "You entered: BACK_SPACE");
	}
	
//	@Test
//	private void testPi() {
//		KeyPressesPage keyPage = homePage.clickKeyPresses();
//		keyPage.enterPi();
//	}
	
}
