package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    @Test(description = "Automate Google Form", enabled = true)
    public void testCase01() throws InterruptedException {

        //Create Object for Wrapper Class to use it
        Wrappers actions = new Wrappers(driver);
        System.out.println("Start Test Case");

        //Navigate to Url
        actions.navigateToUrl("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        System.out.println("Test Step: Navigated to Url Successfully");

        //Find WebElement to Name Field
        WebElement name_field = actions.getElement(By.xpath("//div[contains(@class,'snByac')]//preceding-sibling::input"));

        //Type in Name Field
        actions.type(name_field, "Crio Learner");
        System.out.println("Test Step: Successfully entered text in the field");

        //Find WebElement to Why are you practicing Automation Field

        WebElement nextField = actions.getElement(By.xpath("//div[contains(@class,'snByac')]//following::textarea"));

        //Type in Next Field

        actions.type(nextField, "I want to be the best QA Engineer! "+actions.getEpochTime());
        System.out.println("Test Step: Successfully entered text in the field");

        //Find List of Radio Buttons
        List<WebElement> radioButtons = actions.getElements(By.xpath("//div[contains(@class,'nWQGrd')]"));

        //Click on radio button according to the visible text
        actions.clickRadioButtonByVisibleText(radioButtons, "0 - 2");
        System.out.println("Test Step: Successfully Clicked on Radio Button");

        //Find List of Check Boxes
        List<WebElement> checkBoxes = actions.getElements(By.xpath("//div[@class='eBFwI']"));

        //Click check Boxes according to visible text
        actions.clickCheckBoxByVisibleText(checkBoxes, "Java", "Selenium", "TestNG");
        System.out.println("Test Step: Successfully Clicked On Check Boxes");

        //Find WebElement for Drop Down
        WebElement dropDown = actions.getElement(By.xpath("//div[contains(@class,'LMgvRb ')]"));

        //Click on to open DropDown List
        actions.clickElement(dropDown);
        System.out.println("Test Step: Successfully Clicked on DropDown ");

        //Find List of WebElement of Drop Down List
        List<WebElement> dropDownList = actions.getElements(By.xpath("//div[contains(@class,'ncFHed')]//child::span[not(contains(text(),'Choose'))]"));

        //Click DropDown By Visible Text
        actions.clickDropDownListByVisibleText(dropDownList, "Mr");
        System.out.println("Test Step: Successfully Clicked on DropDown List");

        //Find WebElement of Date Field
        WebElement dateField = actions.getElement(By.xpath("//input[@type='date']"));

        //Provide Current Date Minus 7 Days in the field
        actions.type(dateField, actions.getCurrentDateInFormat());
        System.out.println("Test Step: Successfully Provided CurrentDate Minus 7 days in the date field");

        //Find WebElement for Hour Filed
        WebElement hourField = actions.getElement(By.xpath("//input[@aria-label='Hour']"));

        //Type in the hour Field
        actions.type(hourField, "07");
        System.out.println("Test Step: Successfully Provided hour in the hour Field");

        //Find WebElement for Minute Field
        WebElement minuteField = actions.getElement(By.xpath("//input[@aria-label='Minute']"));

        //Type in Minute Field
        actions.type(minuteField, "30");
        System.out.println("Test Step: Successfully provided Minute in the minute Field");

        //Find WebElement of Submit Button
        WebElement submitButton = actions.getElement(By.xpath("//span[text()='Submit']"));

        //Click on submit element
        actions.clickElement(submitButton);
        System.out.println("Test Step: Successfully Clicked on the submit button");

        //Find WebElement of Success Message
        WebElement successMessageElement = actions.getElement(By.xpath("//div[contains(text(),'Thanks')]"));

        //Compare Expected Text With actual Text
        String ExpectedText = "Thanks for your response, Automation Wizard!";
        if(actions.getSuccessMessage(successMessageElement, ExpectedText)){
            System.out.println("Test Case Success");
        }else {
            System.out.println("Test Case Failed");
        }

        actions.waitFor(5000);
    }

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}