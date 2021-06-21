package dropdown;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.testng.annotations.Test;

import base.BaseTests;
import pages.DropdownPage;

public class DropdownTests extends BaseTests{

	@Test
	private void testSelectOption() {
		DropdownPage dropDownPage = homePage.clickDropdown();
		String option = "Option 1";
		dropDownPage.selectDropdown(option);
		List<String> selectedOptions = dropDownPage.getSelectedOptions();
		assertEquals(selectedOptions.size(), 1, "Incorrect number of options");
		assertTrue(selectedOptions.contains(option), "Option not selected");
	}
	
}
