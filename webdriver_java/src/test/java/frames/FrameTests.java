package frames;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTests;
import pages.WysiwygEditorPage;

public class FrameTests extends BaseTests{
	
	@Test
	public void testWysiwyg() {
		WysiwygEditorPage wysiwygEditorPage = homePage.clickWysiwygEditor();
		wysiwygEditorPage.clearTextArea();
		
		 String text1 = "hello ";
		 String text2 = "world";
		 
		wysiwygEditorPage.setTextArea(text1);
		wysiwygEditorPage.decreaseIndention();
		wysiwygEditorPage.setTextArea(text2);
		Assert.assertEquals(wysiwygEditorPage.getTextFromEditor(), text1 + text2, "Text from editor is incorrect");
	}

}
