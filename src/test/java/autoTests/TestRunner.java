package autoTests;

import org.testng.annotations.Test;

public class TestRunner extends SetupAndTeardown
{

	TestSuite testSuite = new TestSuite();

	@Test(enabled = true, groups = {"transport", "t1"}, priority = 1)
	public void testDefaultFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Verify defaults filters",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyDefaultFilters(driver);
	}

	@Test(enabled = true, groups = {"transport", "t2"}, priority = 2)
	public void testVerifyPresentSomeMarks() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Check list of marks that current mark is current",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyPresentSomeMarks(driver, "Jeep");
	}

	@Test(enabled = true, groups = {"transport", "t3"}, priority = 3)
	public void testPriceFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Check field price(entering letters)",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.enterPriceFrom(driver, "qwr", "");
		testSuite.enterPriceTo(driver, "qwr#$", "");
	}

	@Test(enabled = true, groups = {"transport", "t3"}, priority = 4)
	public void testDistanceFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Filtering by distance enter values",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyDistanceValue(driver,"50000","150000");
	}



}
