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



    /******************************************************** Animation ************************************************/

    /*************************************************** Drop down list ********************************************/
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

    @FindBy(xpath = ".//input[@name='search[filter_float_motor_mileage:from]']")
    public WebElement enter_field_priceFrom;

    @FindBy(xpath = ".//input[@name='search[filter_float_motor_mileage:to]']")
    public WebElement enter_field_priceTo;

    @FindBy(xpath = ".//p[@class='price']/strong")
    public List<WebElement> check_cost;

    @FindBy(xpath = ".//p[@class='price']/strong")
    public WebElement check_cost2;

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
    /******************************************************* Information ***********************************************/

    /****************************************************** Images ***********************************************/

    /********************************************************* Buttons *************************************************/

    /********************************************************** Fields **************************************************/

    /******************************************************* RadioButton **********************************************/

    /************************************************** Error message *******************************************/

    /******************************************************** Links **************************************************/

    /******************************************************* Check-box*************************************************/

    /******************************************************** Methods **************************************************/
    public void getPage()
    {
        driver.navigate().to(configVariables.olxPage);
    }

    public void enterDistanceFrom(WebDriver driver,String distFrom) {

        try {
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(span_distanceFrom));
            span_distanceFrom.click();
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(input_distanceFrom));
            input_distanceFrom.sendKeys(distFrom);
            input_distanceFrom.sendKeys(Keys.ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enterDistanceTo(WebDriver driver,String distTo){
        try {
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(span_distanceTo));
            span_distanceTo.click();
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(input_distanceTo));
            input_distanceTo.sendKeys(distTo);
            input_distanceTo.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    public void checkDistance(WebDriver driver,WebElement actualWebElement,String expectedValue){
        try {
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(actualWebElement));
            String actualDistanceFrom = actualWebElement.getText();
            assertEquals(expectedValue,actualDistanceFrom);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CarPage verifyPresentSomeMarks(String mark) throws Exception {
        String realValue = "nothing!!!!";
        selectMark.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElements(this.marksPresent));
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

    public String workWithRegex(String line, String regex) {
        String ans = "";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(line);
        if (m.find()) {
            ans = m.group(1);
        }
        return ans;
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


/*


    //-------------------Buttons---------------------------//
    @FindBy(id = "search-submit")
    public WebElement button_found;

    //--------------------Elements--------------------//
    public CarPage() {
        PageFactory.initElements(driver, this);
    }







    //-------------------Field search and checkbox-----------//
    @FindBy(id = "search-text")
    public WebElement searchField;

    @FindBy(xpath = "./*/
/*[@relname='search[photos]']")
    public WebElement checkbox_photoOnly;

    @FindBy(xpath = "./*/
/*[@relname='search[description]']")
    public WebElement checkbox_searchDescription;


    //-------------------Transmission box---------------------------//
    @FindBy(xpath = ".//span[@data-default-label='Коробка передач']")
    public WebElement trans_box_click;

    @FindBy(xpath = ".//input[@id='f-all-filter_enum_transmission_type_24']")
    public WebElement trans_box_check_selectAll;

    @FindBy(xpath = "./*/
/*[@class='filter-item rel filter-item-transmission_type']//input")
    public List<WebElement> trans_box_click_select;

    //---------//
*/


   /* public enum TypeTransmissions {
        Механическая,
        Автоматическая,
        Другая
    }

    public CarPage selectTransmissionBox(Enum typeTransmissionBox) {

        new WebDriverWait(driver, 5, 1).until(ExpectedConditions.elementToBeClickable(trans_box_click));
        trans_box_click.click();
        driver.findElement(By.xpath(".//label[@class='select-only-this-opiton inlblk value c27 lheight18 active-filter']/span[text()='" + typeTransmissionBox + "']")).click();
        return this;
    }*/
   /* public CarPage checkTransmissionsBox(Enum typeTransmissionBox){
        WebElement assertCheckBox = driver.findElement(By.xpath(".//input[@data-text='"+typeTransmissionBox+"']")).getAttribute();
        return this;
    }*/


    //--------------------Metods--------------------//



    public static int convertStringToIntegerUsingRegex(String line) {
        String ans = "";
        Pattern r = Pattern.compile("[0-9]+");
        Matcher m = r.matcher(line);
        while (m.find()) {
            ans += m.group(0);
        }
        return Integer.parseInt(ans);
    }


}
