package autoTests;

import autoTests.pages.*;
//import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class TestSuite extends CustomMethods
{
	ConfigurationVariables configVariables = ConfigurationVariables.getInstance();


	public void verifyPresentSomeMarks(WebDriver driver, String mark) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();
		String realValue = "nothing!!!!";
		carPage.selectMark.click();
		for (WebElement element : carPage.marksOfCars) {
			String newList = carPage.workWithRegex(element.getText(), "(\\w+)");
			if (mark.equals(newList)) {
				realValue = newList;
				break;
			}

		}
		assertEquals(realValue, mark);
	}

	public void enterPriceFrom(WebDriver driver, String from, String expected) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(carPage.span_priceFrom));
		carPage.span_priceFrom.click();
		carPage.enter_field_priceFrom.sendKeys(from);
		String actual = carPage.span_priceFrom.getText();
		assertEquals(actual, expected);

	}

	public void enterPriceTo(WebDriver driver, String to, String expected) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(carPage.span_priceTo));
		carPage.span_priceTo.click();
		carPage.enter_field_priceTo.sendKeys(to);
		String actual = carPage.span_priceTo.getText();
		assertEquals(actual, expected);

	}

	public void verifyDefaultFilters(WebDriver driver) throws InterruptedException {
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

	public void verifyDistanceValue(WebDriver driver,String distFrom, String distTo) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();

		carPage.enterDistanceFrom(driver,distFrom);
		carPage.enterDistanceTo(driver,distTo);

		carPage.checkDistance(driver,carPage.span_distanceFrom,"от "+distFrom+" км");
		carPage.checkDistance(driver,carPage.span_distanceTo,"до "+distTo+" км");
	}

	public void testPriceFiltersNormalWorking(WebDriver driver,String from,String to) throws Exception {

		int prFrom = Integer.parseInt(from);
		int prTo= Integer.parseInt(to);

		CarPage carPage = new CarPage(driver);
		carPage.getPage();

		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(carPage.span_priceFrom));
		carPage.span_priceFrom.click();
		carPage.enter_field_priceFrom.sendKeys(from);

		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(carPage.span_priceTo));
		carPage.span_priceTo.click();
		carPage.enter_field_priceTo.sendKeys(to);

		carPage.verifyCheckedPrice(prFrom,prTo);

	}

}
