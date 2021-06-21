package uploadfile;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTests;
import pages.FileUploadPage;

public class FileUploadTests extends BaseTests{
	
	@Test
	private void testSuccessfulFileUpload() {
		FileUploadPage fileUploadPage = homePage.clickFileUpload();
		fileUploadPage.uploadFile("C:\\chromedriver.exe");
		Assert.assertEquals(fileUploadPage.getUploadedFiles(), "chromedriver.exe", "Uploaded file");
	}

}
