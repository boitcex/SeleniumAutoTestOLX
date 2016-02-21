package autoTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRunner extends SetupAndTeardown
{

	TestSuite testSuite = new TestSuite();

	@Test(enabled = true, groups = {"transport", "car"}, priority = 1)
	public void testVerifyPresentSomeMarks() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Check list of marks that current mark is current",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyPresentSomeMarks(driver, "Jeep");
	}

	@Test(enabled = true, groups = {"transport", "bus"}, priority = 2)
	public void testPriceFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Check field price(entering letters)",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.enterPriceFrom(driver, "qwr", "");
		testSuite.enterPriceTo(driver, "qwr#$", "");
	}


}
