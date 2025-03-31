package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Navigated to expected Url
        driver.navigate().to("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        wait.until(ExpectedConditions.urlToBe("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform"));

        String currentUrl = Wrappers.getCurrentUrl(driver);

        if(currentUrl.contains("forms")){
            Assert.assertTrue(true);
            System.out.println("Navigated to the google form");
        }else {
            Assert.assertFalse(false);
            System.out.println("Unable to navigate to google form");
        }

        //Code to perform Name in field
        WebElement name_field = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='i1']/following::input[1]")));
        Thread.sleep(2000);
        name_field.sendKeys("Crio Learner");

        System.out.println("Typed name into input box");


        //Code to perform answer in input box
        Wrappers.shiftToNextTab(driver);
        long epochLong = System.currentTimeMillis()/1000;
        String epoch = String.valueOf(epochLong);
        String actual = "I want to be the best QA Engineer! "+epoch;
        Wrappers.sendKeys(driver, actual);
        String expected = driver.findElement(By.tagName("textarea")).getAttribute("data-initial-value");
        Assert.assertEquals(actual,expected);

        System.out.println("Typed phrase into input box");

        //code to perform actions on radio button
        Wrappers.shiftToNextTab(driver);
        WebElement zero_to_two = driver.findElement(By.xpath("//*[@id='i16']/div[3]"));
        zero_to_two.click();
        System.out.println("Clicked on Experience box");

        //Code to perform actions on checkBox
        Wrappers.shiftToNextTab(driver);
        WebElement javaCheck = driver.findElement(By.xpath("//label[@for='i34']"));
        javaCheck.click();

        WebElement seleniumCheck = driver.findElement(By.xpath("//label[@for='i37']"));
        seleniumCheck.click();


        WebElement testNGCheck = driver.findElement(By.xpath("//label[@for='i43']"));
        testNGCheck.click();
        System.out.println("Chose any of the Tools you are experienced in");

        //Code to perform actions on dropdown
        WebElement dropDown = driver.findElement(By.xpath("//div[@jsname='wQNmvb'][2]"));
        Wrappers.click(driver, dropDown);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mG61Hd']/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[2]")));
        Wrappers.downArrow(driver);
        Wrappers.enter(driver);
        Thread.sleep(2000);
        System.out.println("Chose how to be addressed option");

        //Code to perform action in simple calendar
        Wrappers.shiftToNextTab(driver);
        Wrappers.sendKeys(driver, "24032025");
        System.out.println("Entered 7 days before date");

        //Code to perform actions on time input field
        Wrappers.shiftToNextTab(driver);
        Wrappers.shiftToNextTab(driver);
        Wrappers.sendKeys(driver, "07");
        Wrappers.shiftToNextTab(driver);
        Wrappers.sendKeys(driver, "30");
        Thread.sleep(2000);
        System.out.println("Entered correct time into time input");
        // Code to perform submit form
        WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']"));
        Wrappers.click(driver, submitButton);

        //Code to get Success Message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']")));
        String successMessage = driver.findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']")).getText();
        Assert.assertEquals(successMessage, "Thanks for your response, Automation Wizard!");
        System.out.println("Read the text at the end of submission: "+successMessage);

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}