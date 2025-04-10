package demo.wrappers;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    private WebDriver driver;
    private WebDriverWait wait;

    public Wrappers(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    public void navigateToUrl(String Url){
        try {
            driver.get(Url);
            wait.until(ExpectedConditions.urlContains("/forms"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public WebElement getElement(By locator){
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<WebElement> getElements(By locator){
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void type(WebElement element, String text){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void clickElement(WebElement element){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getEpochTime(){
        try {
            long epoch = System.currentTimeMillis()/1000;
            return String.valueOf(epoch);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void clickRadioButtonByVisibleText(List<WebElement> elements, String text){
        try {
            for (WebElement element : elements){
                String elementText = element.findElement(By.xpath(".//child::span")).getText();
                if(elementText.equals(text)){
                    clickElement(element);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void clickCheckBoxByVisibleText(List<WebElement> elements, String... textsToSelect){
        try {
            for (WebElement element : elements){
                String label = element.findElement(By.xpath(".//child::span")).getText().trim();
                for (String text : textsToSelect){
                    if (label.equalsIgnoreCase(text.trim())){
                        if (!element.isSelected()){
                            element.click();
                        }
                        break;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void clickDropDownListByVisibleText(List<WebElement> elements, String text){
        try {
            for (WebElement element : elements){
                String elementText = element.getText();
                if(elementText.equals(text)){
                    clickElement(element);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getCurrentDateInFormat() {
        try {
            LocalDate dateMinus7 = LocalDate.now().minusDays(7);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return dateMinus7.format(formatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean getSuccessMessage(WebElement element, String expectedText){
        try {
            return element.getText().equals(expectedText);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void waitFor(int millis){
        try {
            Thread.sleep(millis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
