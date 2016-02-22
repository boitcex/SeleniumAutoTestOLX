package autoTests;

import autoTests.pages.CarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestSuite extends CustomMethods
{
	ConfigurationVariables configVariables = ConfigurationVariables.getInstance();


	public void verifyPresentSomeMarks(WebDriver driver, String mark) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();
		carPage.verifyMarksFilterBySomeMark(mark);

	}

	public void verifyPriceFilter(WebDriver driver,String from,String to){
		CarPage carPage = new CarPage(driver);
		carPage.getPage();
		try {
			carPage.enterValueToPriceFilterFrom(driver,from);
			carPage.verifyPriceFilter(driver,carPage.span_priceFrom,"");

			carPage.enterValueToPriceFilterTo(driver,to);
			carPage.verifyPriceFilter(driver,carPage.span_priceTo,"");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void verifyDefaultFiltersValues(WebDriver driver) throws InterruptedException {
		CustomMethods metods = new CustomMethods();
		CarPage carPage = new CarPage(driver);
		carPage.getPage();

		metods.CheckElementPresent(carPage.span_mark);
		metods.CheckElementPresent(carPage.span_model);
		metods.CheckElementPresent(carPage.span_yearFrom);
		metods.CheckElementPresent(carPage.span_yearTo);
		metods.CheckElementPresent(carPage.span_priceFrom);
		metods.CheckElementPresent(carPage.span_priceTo);
		metods.CheckElementPresent(carPage.span_engineVolumeFrom);
		metods.CheckElementPresent(carPage.span_engineVolumeTo);
		metods.CheckElementPresent(carPage.span_distanceFrom);
		metods.CheckElementPresent(carPage.span_distanceTo);
		metods.CheckElementPresent(carPage.span_bodyType);
		metods.CheckElementPresent(carPage.span_petrolType);
		metods.CheckElementPresent(carPage.span_transBoxType);
		metods.CheckElementPresent(carPage.span_colorType);
	}

	public void verifyDistanceFilter(WebDriver driver, String distFrom, String distTo) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();

		carPage.enterToDistanceFromFilter(driver,distFrom);
		carPage.enterToDistanceToFilter(driver,distTo);

		carPage.verifyDistanceFilter(driver,carPage.span_distanceFrom,"от "+distFrom+" км");
		carPage.verifyDistanceFilter(driver,carPage.span_distanceTo,"до "+distTo+" км");
	}

	public void verifyPriceFiltersWorking(WebDriver driver, String from, String to) throws Exception {

		int prFrom = Integer.parseInt(from);
		int prTo= Integer.parseInt(to);

		CarPage carPage = new CarPage(driver);
		carPage.getPage();

		carPage.enterValueToPriceFilterFrom(driver,from);
		carPage.enterValueToPriceFilterTo(driver,to);

		carPage.verifyCostOfCars(prFrom,prTo);

	}

	public void verifyTransmissionBox(WebDriver driver) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();

		carPage.selectTransBox(driver,carPage.checkBox_clickMechanic);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAll,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedMechanic,"true");
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAutomation,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedOther,null);

		carPage.selectTransBox(driver,carPage.checkBox_clickAutomation);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAll,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedMechanic,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAutomation,"true");
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedOther,null);

		carPage.selectTransBox(driver,carPage.checkBox_clickOther);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAll,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedMechanic,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAutomation,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedOther,"true");

		carPage.selectTransBox(driver,carPage.checkBox_clickAll);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAll,"true");
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedMechanic,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedAutomation,null);
		carPage.verify_atrChecked(driver,carPage.checkBox_checkedOther,null);

	}

}
