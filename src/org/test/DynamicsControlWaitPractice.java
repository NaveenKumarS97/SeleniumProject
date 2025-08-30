package org.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class DynamicsControlWaitPractice {

    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // Remove → wait until checkbox disappears → assert message
        WebElement toggleCheckboxBtn = driver.findElement(By.cssSelector("#checkbox-example button"));
        toggleCheckboxBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        String msg1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkbox-example #message"))
        ).getText().trim();
        if (!"It's gone!".equals(msg1)) {
            throw new AssertionError("Expected \"It's gone!\" but got: " + msg1);
        }
        WebElement toggleCheckboxBtn1 = driver.findElement(By.cssSelector("#checkbox-example button"));  
        toggleCheckboxBtn1.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
        String msg2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText().trim();  // trim used to remove extra leading or trailing spaces.
        System.out.println(msg2);
        //the below step is to check if there are any other values pop out than expected
        if (!"It's back!".equals(msg2)) {
            throw new AssertionError("Expected \"It's back!\" but got: " + msg2);
        }
        
        WebElement EnableBtn = driver.findElement(By.cssSelector("#input-example > button"));
        EnableBtn.click();
        
        String msg3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText().trim();  // trim used to remove extra leading or trailing spaces.
        System.out.println(msg3);
        //the below step is to check if there are any other values pop out than expected
        if (!"It's enabled!".equals(msg3)) {
            throw new AssertionError("Expected \"It's enabled!\" but got: " + msg3);
        }
        WebElement Textvalue = driver.findElement(By.cssSelector("#input-example > input[type=text]"));
        Textvalue.sendKeys("Pavithra samuthirapandi");
        
        WebElement disableBtn = driver.findElement(By.cssSelector("#input-example > button"));
        disableBtn.click();
     // **********using lamda to see if the text field is enabled - we need disabled so using !***************        
//      wait.until(driver1 -> !driver1.findElement(By.cssSelector("#input-example > input[type=text]")).isEnabled());
/*      wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver1) {
                WebElement input = driver1.findElement(By.cssSelector("#input-example input[type=text]"));
                return !input.isEnabled(); // true when input is disabled
            }
        });                                                                   */
//       wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.cssSelector("#input-example > input[type=text]"))));
         wait.until(ExpectedConditions.attributeToBe(By.cssSelector("#input-example > input[type=text]"), "disabled", "true"));
//       String msg4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText().trim();  // trim used to remove extra leading or trailing spaces.
//       System.out.println(msg4);
//       //the below step is to check if there are any other values pop out than expected
//       if (!"It's disabled!".equals(msg4)) {
//            throw new AssertionError("Expected \"It's enabled!\" but got: " + msg4);
         driver.quit();         
//        }  
       }
}
/*Mini step-by-step (manual test flow)
Open page → click Remove
Wait until #checkbox is invisible/not present
Expect message: “It’s gone!”

Click Add
Wait until #checkbox is visible again
Expect message: “It’s back!”

Click Enable
Wait until #input-example input isEnabled()==true
Type text
Expect message: “It’s enabled!”

Click Disable
Wait until input isEnabled()==false
Expect message: “It’s disabled!”
*/



/*  Practice code
 
  package org.test;
 

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Teee {
	
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");
		WebElement ToogleBtn = driver.findElement(By.cssSelector("#checkbox-example > button"));
		ToogleBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
		String msg1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText().trim();
		if (!"It's gone!".equals(msg1)) {
			throw new AssertionError ("Expected error is It's gone! but error : " + msg1);
		}
		WebElement ToogleBtn1 = driver.findElement(By.cssSelector("#checkbox-example > button"));
		ToogleBtn1.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
		String msg2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText().trim();
		if (!"It's back!".equals(msg2)) {
			throw new AssertionError ("Expected error = It's back! but error now is : " + msg2);
		}
		WebElement enablebtn = driver.findElement(By.cssSelector("#input-example > button"));
		enablebtn.click();
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#input-example > input[type=text]")));
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("#input-example > input[type=text]"), "disabled", "true"));
		String msg3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText().trim();
		if ("!It's enabled!".equals(msg3)) {
			throw new AssertionError ("Expected error = It's enabled! but error now is : " + msg3);
		}
		WebElement textfill = driver.findElement(By.cssSelector("#input-example > input[type=text]"));
		textfill.sendKeys("TRY HARDER");
		WebElement disablebtn = driver.findElement(By.cssSelector("#input-example > button"));
		disablebtn.click();
		driver.quit();
	}
}
*/