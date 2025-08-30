/* Task 1:

Web form basics — fill & submit
Link:  https://www.selenium.dev/selenium/web/web-form.html
Steps: fill text, textarea, select from dropdown, check a checkbox, choose a radio, submit, assert success banner appears.*/

package org.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebFormBasics1 {
    // ---------- DROP-IN HELPER ----------
    // Works with the Bootstrap-style calendar on the Selenium demo page.
    static void pickDate(ChromeDriver driver, By inputLocator, LocalDate target) throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // open the picker
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.click();

        // visible calendar container
        WebElement cal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".datepicker[style*='display: block']")));

        By headerSwitch = By.cssSelector("th.datepicker-switch");
        By prevBtn = By.cssSelector("th.prev");
        By nextBtn = By.cssSelector("th.next");

        YearMonth want = YearMonth.of(target.getYear(), target.getMonth());
        int guard = 0;

        while (true) {
            String header = cal.findElement(headerSwitch).getText().trim(); // e.g. "July 2025"
            YearMonth seen = YearMonth.parse(header, DateTimeFormatter.ofPattern("MMMM uuuu"));
            if (seen.equals(want)) break;

            if (seen.isAfter(want)) cal.findElement(prevBtn).click();
            else cal.findElement(nextBtn).click();

            // wait until header changes
            wait.until(d -> {
                String h2 = cal.findElement(headerSwitch).getText().trim();
                return !h2.equals(header);
            });

            if (++guard > 48) throw new TimeoutException("Month navigation took too long");
        }

        // click the day (ignore greyed old/new days)
        String dayText = Integer.toString(target.getDayOfMonth());
        WebElement dayCell = wait.until(ExpectedConditions.elementToBeClickable(
                cal.findElement(By.xpath(".//td[contains(@class,'day') and " +
                        "not(contains(@class,'old')) and not(contains(@class,'new')) and text()='" + dayText + "']"))));
        dayCell.click();
    }
    // ---------- END HELPER ----------

 
public static void main(String[] args) throws TimeoutException {
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://www.selenium.dev/selenium/web/web-form.html");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().window().maximize();
	
	WebElement TextInput = driver.findElement(By.id("my-text-id"));
	TextInput.sendKeys("Selenium demo");
	WebElement Password = driver.findElement(By.name("my-password"));
	Password.sendKeys("Secret123!");
	WebElement TextArea = driver.findElement(By.name("my-textarea"));
	TextArea.sendKeys("This is a test message\nentered by Selenium.\n");
	WebElement SelectDropdown = driver.findElement(By.xpath("//select[@class='form-select']"));
    Select dropdown = new Select(SelectDropdown);
    dropdown.selectByVisibleText("Two");   
    try { Thread.sleep(2000); } catch (InterruptedException e) {}
    
    WebElement datalistInput = driver.findElement(By.name("my-datalist"));
    datalistInput.clear();
    datalistInput.sendKeys("Selenium");
    WebElement datalist = driver.findElement(By.id("my-options"));
    
    List<WebElement> opts = datalist.findElements(By.tagName("option"));
    for (WebElement opt : opts) {
        String val = opt.getAttribute("value");
        if (opt.getAttribute("value").equalsIgnoreCase("Selenium")) {
            datalistInput.clear();
            datalistInput.sendKeys(val);
            break;
            }
        }
    
    WebElement DefaultCheckbox = driver.findElement(By.id("my-check-2"));   //    xpath -----	(//input[@id='my-check-2'])
    WebElement Defaultradio = driver.findElement(By.id("my-radio-2"));      //    xpath -----	(//input[@id='my-check-2'])
    DefaultCheckbox.click();
    Defaultradio.click();
    	
    WebElement colorInput = driver.findElement(By.name("my-colors"));
/*      *********************Another method using CSS SELECTOR***********
        WebElement colorInput = driver.findElement(
	    By.cssSelector("input[type='color'][name='my-colors']")
	    ); 
	    *****************************************************************     */
    colorInput.sendKeys("#00ff00");   // set to lime green
    String v = colorInput.getAttribute("value"); // e.g. "#00ff00"
    System.out.println("New color: " + v);       // (optional) Checking and printing in console if the value is changed...
    	
    DefaultCheckbox.click();
/*  *********************Another method using CSS SELECTOR***************
    WebElement color = driver.findElement(By.cssSelector("input[name='my-colors']"));
    color.sendKeys("#fffb00");        
    System.out.println(color);      
    String C = color.getAttribute("value");
    System.out.println(C);
	*********************************************************************     */
    	
  WebElement DatePicker = driver.findElement(By.xpath("//input[@name='my-date']"));
  DatePicker.click();
  DatePicker.clear();
  DatePicker.sendKeys("07/07/2025");
    	
//  ---- Use the helper to pick JULY 12, 2025 ----
//    pickDate(driver, By.name("my-date"), LocalDate.of(2025, 7, 12));
        
    WebElement Submit = driver.findElement(By.xpath("//button[@type='submit']"));
    Submit.click();
    }
}	