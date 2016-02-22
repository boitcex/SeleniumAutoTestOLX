package autoTests;

import org.testng.annotations.Test;

public class TestRunner extends SetupAndTeardown
{

	TestSuite testSuite = new TestSuite();


	@Test(enabled = true, groups = {"transport", "t1"}, priority = 1)
	public void testDefaultFiltersValues() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Verify that default values in filters are present",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyDefaultFiltersValues(driver);
	}

	@Test(enabled = true, groups = {"transport", "t2"}, priority = 2)
	public void testThatPresentSomeMarks() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Check list of marks that current mark is current",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyPresentSomeMarks(driver, "Jeep");
	}

	@Test(enabled = true, groups = {"transport", "t3"}, priority = 3)
	public void negativeTestPriceFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Check field price(entering not numbers)",
				Thread.currentThread().getStackTrace()[1].toString()
		);
			testSuite.verifyPriceFilter(driver,"@#DFG","$%SDFd");
	}

	@Test(enabled = true, groups = {"transport", "t4"}, priority = 4)
	public void testPriceFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Test price filters",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyPriceFiltersWorking(driver,"100000","500000");
	}

	@Test(enabled = true, groups = {"transport", "t5"}, priority = 5)
	public void testDistanceFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Filtering by distance enter values",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyDistanceFilter(driver,"50000","150000");
	}

	@Test(enabled = true, groups = {"transport", "t6"}, priority = 6)
	public void testCheckBox_TransmissionFilters() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Testig transmissionCheckBox",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.verifyTransmissionBox(driver);
	}





}
