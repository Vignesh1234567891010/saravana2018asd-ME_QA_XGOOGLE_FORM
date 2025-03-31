package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public static void shiftToNextTab(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
    }

    public static void sendKeys(WebDriver driver, String message){
        Actions actions = new Actions(driver);
        actions.sendKeys(message).perform();
    }

    public static void click(WebDriver driver, WebElement element){

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public static void enter(WebDriver driver){

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ENTER).keyDown(Keys.ENTER).perform();
    }

    public static void downArrow(WebDriver driver){

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_UP).perform();
    }


}
