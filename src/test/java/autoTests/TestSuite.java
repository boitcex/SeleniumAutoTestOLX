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

	//<editor-fold desc="testVerifyPresentSomeMarks - Проверить список марок автомобилей на конкретную модель(позитивный сценарий).">
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
	//</editor-fold>

	//<editor-fold desc="enterPriceFrom - Проверяем фильтр 'Цена от (грн)'">
	public void enterPriceFrom(WebDriver driver, String from, String expected) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(carPage.click_field_priceFrom));
		carPage.click_field_priceFrom.click();
		carPage.enter_field_priceFrom.sendKeys(from);
		String actual = carPage.click_field_priceFrom.getText();
		assertEquals(actual, expected);

	}
	//</editor-fold>

	//<editor-fold desc="enterPriceTo - Проверяем фильтр 'Цена до (грн)'">
	public void enterPriceTo(WebDriver driver, String to, String expected) throws Exception {
		CarPage carPage = new CarPage(driver);
		carPage.getPage();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(carPage.click_field_priceTo));
		carPage.click_field_priceTo.click();
		carPage.enter_field_priceTo.sendKeys(to);
		String actual = carPage.click_field_priceTo.getText();
		assertEquals(actual, expected);

	}
	//</editor-fold>
}
