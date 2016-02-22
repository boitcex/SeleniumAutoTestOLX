package autoTests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;


public class CustomMethods {

    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();


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

    //Проверяем что элемент присутствует и виден
    public void CheckElementPresent(WebElement element) throws InterruptedException {
        Assert.assertEquals(true, element.isDisplayed());
        Assert.assertEquals(true, element.isEnabled());
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
