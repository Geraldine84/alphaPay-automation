package javascript;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.LargeAndDeepDomPage;

public class JavaScriptTests extends BaseTests{
	
	@Test
	public void testScrollToTable() {
		LargeAndDeepDomPage largedeepPage  = homePage.clickLargeAndDeepDom();
		largedeepPage.scrollTotable();
	}
	
	@Test
	public void testScrollToFifthParagraph() {
		homePage.clickInfiniteScroll().scrollToParagraph(5);
		
	}

}
