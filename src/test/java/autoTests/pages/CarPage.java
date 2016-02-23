package autoTests.pages;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;


public class CarPage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();

    public CarPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    /******************************************************* Information *********************************************/

    /****************************************************** Images ***************************************************/

    /********************************************************* Buttons ***********************************************/

    /********************************************************** Fields ***********************************************/

    /******************************************************* RadioButton *********************************************/

    /************************************************** Error message ************************************************/

    /******************************************************** Links **************************************************/

    /****************************************************
     * Drop down list
     ********************************************/
    @FindBy(xpath = ".//input[@id='search-submit']")
    public WebElement searchButton;
    //-------------------Default Filters-----------------//
    @FindBy(xpath = ".//span[@data-default-label='Марка']")
    public WebElement span_mark;

    @FindBy(xpath = ".//span[@data-default-label='Модель']")
    public WebElement span_model;

    @FindBy(xpath = ".//span[@data-default-label='Год выпуска от']")
    public WebElement span_yearFrom;

    @FindBy(xpath = ".//span[@data-default-label='Год выпуска до']")
    public WebElement span_yearTo;

    @FindBy(xpath = ".//span[@data-default-label='Цена от']")
    public WebElement span_priceFrom;

    @FindBy(xpath = ".//span[@data-default-label='Цена до']")
    public WebElement span_priceTo;

    @FindBy(xpath = ".//span[@data-default-label='Объем двигателя от']")
    public WebElement span_engineVolumeFrom;

    @FindBy(xpath = ".//span[@data-default-label='Объем двигателя до']")
    public WebElement span_engineVolumeTo;

    @FindBy(xpath = ".//span[@data-default-label='Пробег от']")
    public WebElement span_distanceFrom;

    @FindBy(xpath = ".//span[@data-default-label='Пробег до']")
    public WebElement span_distanceTo;

    @FindBy(xpath = ".//span[@data-default-label='Тип кузова']")
    public WebElement span_bodyType;

    @FindBy(xpath = ".//span[@data-default-label='Вид топлива']")
    public WebElement span_petrolType;

    @FindBy(xpath = ".//span[@data-default-label='Коробка передач']")
    public WebElement span_transBoxType;

    @FindBy(xpath = ".//span[@data-default-label='Цвет']")
    public WebElement span_colorType;

    //-------------------Price---------------------------//

    @FindBy(xpath = "(//input[@value=''])[7]")
    public WebElement enter_field_priceFrom;

    @FindBy(xpath = "(//input[@value=''])[8]")
    public WebElement enter_field_priceTo;

    @FindBy(xpath = ".//p[@class='price']/strong")
    public List<WebElement> check_cost;


    //-------------------Distance---------------------------//
    @FindBy(xpath = "(//input[@value=''])[11]")
    public WebElement input_distanceFrom;

    @FindBy(xpath = "(//input[@value=''])[12]")
    public WebElement input_distanceTo;

    @FindBy(xpath = ".//*[@class='filter-item filter-item-from rel numeric-item filterActive']/ul/li/a")
    public List<WebElement> select_distanceFrom;

    @FindBy(xpath = ".//*[@class='filter-item filter-item-to rel numeric-item']/ul/li/a")
    public List<WebElement> select_distanceTo;

    @FindBy(xpath = ".//span[@data-default-label='Марка']")
    public WebElement selectMark;

    @FindBy(xpath = "//ul[@class='small suggestinput bgfff lheight20 br-3 abs subcategories binded']/li/a")
    public List<WebElement> marksOfCars;

    @FindBy(xpath = "//li[@class='subcategory']/div/ul/li/a")
    public List<WebElement> marksPresent;

    /********************************************************
     * Check-box
     ***********************************************/
    @FindBy(xpath = ".//span[@data-default-label='Коробка передач']")
    public WebElement span_transBox;

    @FindBy(xpath = ".//label[@data-text='Механическая']/span")
    public WebElement checkBox_clickMechanic;

    @FindBy(xpath = ".//label[@data-text='Автоматическая']/span")
    public WebElement checkBox_clickAutomation;

    @FindBy(xpath = ".//label[@data-text='Другая']/span")
    public WebElement checkBox_clickOther;

    @FindBy(xpath = ".//input[@id='f-all-filter_enum_transmission_type_24']")
    public WebElement checkBox_clickAll;

    @FindBy(xpath = ".//input[@data-text='Механическая']")
    public WebElement checkBox_checkedMechanic;

    @FindBy(xpath = ".//input[@data-text='Автоматическая']")
    public WebElement checkBox_checkedAutomation;

    @FindBy(xpath = ".//input[@data-text='Другая']")
    public WebElement checkBox_checkedOther;

    @FindBy(xpath = ".//label[@for='f-all-filter_enum_transmission_type_24']/input")
    public WebElement checkBox_checkedAll;


    @FindBy(xpath = ".//ul[@class='small suggestinput bgfff lheight20 br-3 abs select binded']/li/label/input")
    public List<WebElement> checkBox_all;

    /*********************************************************
     * Methods
     ************************************************/
    public void getPage() {
        driver.navigate().to(configVariables.olxPage);
    }

    public void enterToDistanceFromFilter(WebDriver driver, String distanceFrom) {

        try {
            new WebDriverWait(driver, configVariables.waitElement)
                    .until(ExpectedConditions
                            .elementToBeClickable(span_distanceFrom));
            span_distanceFrom.click();
            new WebDriverWait(driver, configVariables.waitElement)
                    .until(ExpectedConditions.elementToBeClickable(input_distanceFrom));
            input_distanceFrom.sendKeys(distanceFrom);
            input_distanceFrom.sendKeys(Keys.ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterToDistanceToFilter(WebDriver driver, String distanceTo) {
        try {
            new WebDriverWait(driver, configVariables.waitElement)
                    .until(ExpectedConditions.elementToBeClickable(span_distanceTo));
            span_distanceTo.click();
            new WebDriverWait(driver, configVariables.waitElement)
                    .until(ExpectedConditions.elementToBeClickable(input_distanceTo));
            input_distanceTo.sendKeys(distanceTo);
            input_distanceTo.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyDistanceFilter(WebDriver driver, WebElement actualWebElement, String expectedValue) {
        try {
            new WebDriverWait(driver, configVariables.waitElement).until(ExpectedConditions.visibilityOf(actualWebElement));
            String actualDistanceFrom = actualWebElement.getText();
            assertEquals(expectedValue, actualDistanceFrom);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CarPage verifyMarksFilterBySomeMark(String mark) {
        String realValue = "nothing!!!!";
        selectMark.click();
        new WebDriverWait(driver, configVariables.waitElement)
                .until(ExpectedConditions.visibilityOfAllElements(this.marksPresent));
        for (WebElement element : marksOfCars) {
            String newList = workWithMarksUsingRegex(element.getText(), "(\\w+)");
            if (mark.equals(newList)) {
                realValue = newList;
                break;
            }

        }
        assertEquals(realValue, mark);
        return this;
    }

    public String workWithMarksUsingRegex(String line, String regex) {
        String ans = "";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(line);
        if (m.find()) {
            ans = m.group(1);
        }
        return ans;
    }

    public void verifyCostOfCars(int minCost, int maxCost) throws Exception {
        int costs;
        new WebDriverWait(driver, configVariables.waitElement)
                //.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//p[@class='price']/strong")));
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//p[@class='price']/strong")));
        for (WebElement element : check_cost) {
            String str = element.getText();
            costs = convertStringToIntegerUsingRegex(str);
            if (costs < minCost && costs > maxCost) {
                throw new Exception("ERROR!Price filter doesn`t work");
            }
        }

    }

    public static int convertStringToIntegerUsingRegex(String line) {
        String ans = "";
        Pattern r = Pattern.compile("[0-9]+");
        Matcher m = r.matcher(line);
        while (m.find()) {
            ans += m.group(0);
        }
        return Integer.parseInt(ans);
    }

    public void enterValueToPriceFilterFrom(WebDriver driver, String value){
        new WebDriverWait(driver, configVariables.waitElement)
                .until(ExpectedConditions.visibilityOf(span_priceFrom));
        span_priceFrom.click();
        enter_field_priceFrom.sendKeys(value);
        searchButton.click();
    }

    public void enterValueToPriceFilterTo(WebDriver driver, String value){
        new WebDriverWait(driver, configVariables.waitElement)
                .until(ExpectedConditions.visibilityOf(span_priceTo));
        span_priceTo.click();
        new WebDriverWait(driver, configVariables.waitElement)
                .until(ExpectedConditions.visibilityOf(enter_field_priceTo));
        enter_field_priceTo.sendKeys(value);
        searchButton.click();
    }

    public void verifyPriceFilter(WebDriver driver,WebElement elementForVerify, String expected){
        new WebDriverWait(driver, configVariables.waitElement)
                .until(ExpectedConditions.visibilityOf(elementForVerify));
        elementForVerify.click();
        String actual = elementForVerify.getText();
        assertEquals(actual, expected);
    }

    public void selectTransBox(WebDriver driver, WebElement checkboxElement) {
        new WebDriverWait(driver, configVariables.waitElement).until(ExpectedConditions.visibilityOf(span_transBox));
        span_transBox.click();
        new WebDriverWait(driver, configVariables.waitElement).until(ExpectedConditions.visibilityOf(checkboxElement));
        checkboxElement.click();
    }

    public void verify_atrChecked(WebDriver driver, WebElement elementForAssert, String expectedResult) {
        new WebDriverWait(driver, configVariables.waitElement).until(ExpectedConditions.visibilityOf(span_transBox));
        span_transBox.click();
        assertEquals(elementForAssert.getAttribute("checked"), expectedResult);
    }

}
