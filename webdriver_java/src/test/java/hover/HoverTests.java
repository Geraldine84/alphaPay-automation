package hover;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.HoversPage;
import pages.HoversPage.FigureCaption;

public class HoverTests extends BaseTests{
	
	@Test
	private void testHoverUser1() {
		HoversPage hoverPage = homePage.clickHovers();
		FigureCaption caption = hoverPage.hoverOverFigure(1);
		assertTrue(caption.isCaptionDisplayed(), "Caption not displayed");
		assertEquals(caption.getTitle(), "name: user1", "Caption title incorrect");
		assertEquals(caption.getLinkText(), "View profile", "Caption link Text incorrect");
		assertTrue(caption.getLink().endsWith("/users/1"), "Link incorrect");
	}

}
