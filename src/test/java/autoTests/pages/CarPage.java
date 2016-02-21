package autoTests.pages;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.By;
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

    //-------------------Buttons---------------------------//
    @FindBy(id = "search-submit")
    public WebElement button_found;

    //--------------------Elements--------------------//
    public CarPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//span[@data-default-label='Марка']")
    public WebElement selectMark;

    @FindBy(xpath = "//ul[@class='small suggestinput bgfff lheight20 br-3 abs subcategories binded']/li/a")
    public List<WebElement> marksOfCars;

    @FindBy(xpath = "//ul[@class='small suggestinput bgfff lheight20 br-3 abs subcategories binded']/li/a/span")
    public List<WebElement> marksOfCars1;

    @FindBy(xpath = "//li[@class='subcategory']/div/ul/li/a")
    public List<WebElement> marksPresent;
    //-------------------Field search and checkbox-----------//
    @FindBy(id = "search-text")
    public WebElement searchField;

    @FindBy(xpath = ".//*[@relname='search[photos]']")
    public WebElement checkbox_photoOnly;

    @FindBy(xpath = ".//*[@relname='search[description]']")
    public WebElement checkbox_searchDescription;
    //-------------------Price---------------------------//
    @FindBy(xpath = ".//*[@data-default-label='Цена от']")
    public WebElement click_field_priceFrom;

    @FindBy(xpath = ".//*[@data-default-label='Цена до']")
    public WebElement click_field_priceTo;

    @FindBy(xpath = ".//input[@defaultval='от...']")
    public WebElement enter_field_priceFrom;

    @FindBy(xpath = ".//input[@defaultval='до...']")
    public WebElement enter_field_priceTo;

    @FindBy(xpath = ".//p[@class='price']/strong")
    public List<WebElement> check_cost;

    @FindBy(xpath = ".//p[@class='price']/strong")
    public WebElement check_cost2;

    //-------------------Transmission box---------------------------//
    @FindBy(xpath = ".//span[@data-default-label='Коробка передач']")
    public WebElement trans_box_click;

    @FindBy(xpath = ".//input[@id='f-all-filter_enum_transmission_type_24']")
    public WebElement trans_box_check_selectAll;

    @FindBy(xpath = ".//*[@class='filter-item rel filter-item-transmission_type']//input")
    public List<WebElement> trans_box_click_select;

    //---------//
    public void getPage()
    {
        driver.navigate().to(configVariables.olxPage);
    }

    public enum TypeTransmissions {
        Механическая,
        Автоматическая,
        Другая
    }

    public CarPage selectTransmissionBox(Enum typeTransmissionBox) {

        new WebDriverWait(driver, 5, 1).until(ExpectedConditions.elementToBeClickable(trans_box_click));
        trans_box_click.click();
        driver.findElement(By.xpath(".//label[@class='select-only-this-opiton inlblk value c27 lheight18 active-filter']/span[text()='" + typeTransmissionBox + "']")).click();
        return this;
    }
   /* public CarPage checkTransmissionsBox(Enum typeTransmissionBox){
        WebElement assertCheckBox = driver.findElement(By.xpath(".//input[@data-text='"+typeTransmissionBox+"']")).getAttribute();
        return this;
    }*/


    //--------------------Metods--------------------//

    public String workWithRegex(String line, String regex) {
        String ans = "";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(line);
        if (m.find()) {
            ans = m.group(1);
        }
        return ans;
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

    public CarPage clickButtonFound() {
        button_found.click();
        return this;
    }

    public CarPage enterPriceFrom(String from) {
        new WebDriverWait(driver, 5, 1).until(ExpectedConditions.visibilityOf(click_field_priceFrom));
        click_field_priceFrom.click();
        enter_field_priceFrom.sendKeys(from);
        //enter_field_priceFrom.sendKeys(Keys.ENTER);
        return this;
    }

    public CarPage enterPriceTo(String to) {
        new WebDriverWait(driver, 5, 1).until(ExpectedConditions.visibilityOf(click_field_priceTo));
        click_field_priceTo.click();
        enter_field_priceTo.sendKeys(to);
        return this;
    }

    public CarPage verifyPriceFromField(String expected) {
        String actual = click_field_priceFrom.getText();
        assertEquals(actual, expected);
        return this;
    }

    public CarPage verifyPriceToField(String expected) {
        String actual = click_field_priceTo.getText();
        assertEquals(actual, expected);
        return this;
    }

    //-----------------Verify-------------------//
    public CarPage verifyPresentSomeMarks(String mark) throws Exception {
        String realValue = "nothing!!!!";
        selectMark.click();
        for (WebElement element : marksOfCars) {
            String newList = workWithRegex(element.getText(), "(\\w+)");
            if (mark.equals(newList)) {
                realValue = newList;
                break;
            }

        }
        assertEquals(realValue, mark);
        return this;
    }

    public CarPage verifyVisibilityOfAllMarks() {
        selectMark.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElements(this.marksPresent));
        return this;
    }

    public void verifyCheckedPrice(int min, int max) throws Exception {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//p[@class='price']/strong")));
        int costs;
        for (WebElement element : check_cost) {
            String str = element.getText();
            costs = convertStringToIntegerUsingRegex(str);
            //System.out.println(cost);
            if (costs < min && costs > max) {
                throw new Exception("ERROR!Price filter doesn`t work");
            }
        }

    }
}
