package autoTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class CustomMethods {

    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();

    public void waitForElementRemoved(WebDriver driver, By locator, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementRemoved(WebDriver driver, WebElement webElement, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        boolean flag = true;
        int counter = 0;
        while (flag) {
            if (counter > (int) (timeoutInSeconds * 1000 / pollingInterval)) {
                flag = false;
                throw new Exception("Ошибка во время выполнения теста. " +
                        "В метод waitForElementRemoved передан WebElement " +
                        webElement +
                        " который не удаляется"
                );
            }
            try {
                Thread.sleep(pollingInterval);
                counter++;
                if (!webElement.isDisplayed()) flag = false;
            } catch (Exception e) {
                flag = false;
            }
        }
        driver.manage().timeouts().implicitlyWait(configVariables.implicitTimeWait, TimeUnit.SECONDS);
    }

    public void waitForElementPresent(WebDriver driver, By locator, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

    public void waitForElementPresent(WebDriver driver, WebElement webElement, int timeoutInSeconds, int pollingInterval)
            throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public Calendar getCurrentCalendar() {

        String[] ids = TimeZone.getAvailableIDs(+2 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0) System.exit(0);
        // create a (CEST - Central Europe Summer Time Zone) UTC/GMT+2 time zone
        SimpleTimeZone GMT = new SimpleTimeZone(+2 * 60 * 60 * 1000, ids[0]);
        // create a GregorianCalendar with the current date and time
        Calendar calendar = new GregorianCalendar(GMT);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        return calendar;

    }

    public static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();

            } else {
                //list all the directory contents
                String files[] = file.list();
                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);
                    //recursive delete
                    delete(fileDelete);
                }
                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    //System.out.println("Directory is deleted : " + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
        }
    }

    public static void deleteFileOrDirectory(File directory) {
        //make sure directory exists
        if (!directory.exists()) {
            //System.out.println("Directory "+directory+" does not exist.");
        } else {
            try {
                delete(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //convert from UTF-8 -> internal Java String format
    public String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("Windows-1251"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    //convert from internal Java String format -> UTF-8
    public String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "Windows-1251");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    //Проверяем что элемент присутствует и видем
    public void CheckElementPresent(WebElement element) throws InterruptedException {
        Assert.assertEquals(true, element.isDisplayed());
        Assert.assertEquals(true, element.isEnabled());
    }

    public void choseElementFromListBox(WebElement listbox, String aim) {
        Select droplist = new Select(listbox);
        droplist.selectByValue(aim);
    }

    public void addStepToTheReport(String stepName) throws Exception {
        Reporter.log("<b>" + stepName + "</b><br>");
    }

    public static void addTestNameToTheReport(String testName, String methodPath) throws Exception {
        methodPath = methodPath.substring(0, methodPath.indexOf("("));

        //получим id теста
        String testId = methodPath.substring(methodPath.lastIndexOf(".") + 1, methodPath.length());

        //отделим имя теста от имени класса символом '#'
        StringBuilder tempPath = new StringBuilder(methodPath);
        methodPath =
                tempPath.substring(0, methodPath.lastIndexOf(".")) +
                        URLEncoder.encode("#", "UTF8") +
                        tempPath.substring(methodPath.lastIndexOf(".") + 1, methodPath.length());

        Reporter.log(
                "<form id = \"" + testId + "form\" action= \"\" method=\"post\">\n" +
                        "<font color=\"blue\" size=\"3\">" + testName + "</font>\n" +
                        "<input type=\"Submit\" value=\"Выполнить\">\n" +
                        "</form> \n" +
                        "<script type=\"text/javascript\">\n" +
                        "\tvar currentURL = document.URL;\n" +
                        "\tcurrentURL = currentURL.substring(0,currentURL.indexOf(\"/HTML_Report/\"));\n" +
                        "var jobNameStartIndex = currentURL.indexOf(\"AT.SELENIUM.\");\n" +
                        "while(currentURL.lastIndexOf(\"/\") > jobNameStartIndex)\n" +
                        "  currentURL = currentURL.substring(0,currentURL.lastIndexOf(\"/\")); " +
                        "\tdocument.getElementById('" + testId +
                        "form').action = currentURL + \"/buildWithParameters?suiteFile=testng.xml&test=" + methodPath + "\";\n" +
                        "</script>"
        );
    }

    public static void addErrorToTheReport(String testName) throws Exception {
        Reporter.log("<b><font color=\"red\" size=\"3\">" + testName + "</font></b><br>");
    }
}
