package org.test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import java.awt.AWTException;
import java.time.Duration;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SacsMyCode {

	public static void main(String[] args) throws AWTException {
        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<>(); //import java.util.HashMap;  //import java.util.Map;
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        options.setExperimentalOption("prefs", prefs);

	ChromeDriver driver = new ChromeDriver(options);                                                           //import org.openqa.selenium.chrome.ChromeDriver;
	driver.get("https://www.saucedemo.com/");
	driver.manage().window().maximize();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	WebElement CredUser = driver.findElement(By.className("login_credentials")); //import org.openqa.selenium.WebElement;  & //import org.openqa.selenium.By;
	String[] lines = CredUser.getText().split("\\R");
	String testusername = lines[1].trim();
	
	WebElement CredPassword = driver.findElement(By.className("login_password"));
	String [] lines1 = CredPassword.getText().split("\\R");
	String testPassword = lines1[1].trim();

	WebElement userName = driver.findElement(By.id("user-name"));
	userName.sendKeys(testusername);
	WebElement Password = driver.findElement(By.id("password"));
	Password.sendKeys(testPassword);
	WebElement LoginButton = driver.findElement(By.id("login-button"));
	LoginButton.click();
	
//    WebElement Backpack = driver.findElement(By.xpath("//div[contains(text(),'Backpack')]"));
//	  Backpack.click();
//	
//	Alert sa =  driver.switchTo().alert();
//	sa.accept();

	
    List<WebElement> cards = driver.findElements(By.className("inventory_item"));

    for (int i = 0; i < cards.size(); i++) {
        WebElement card = cards.get(i);

        // Read the product name using the class you asked for
        WebElement nameEl = card.findElement(By.className("inventory_item_name"));
        String name = nameEl.getText().trim();

        // Find its Add to cart button inside the same card and click
        WebElement addBtn = card.findElement(By.cssSelector("button.btn_inventory"));
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();

        System.out.println("Added to cart: " + name);
    }

    // (Optional) verify the cart badge equals the number of products added
    WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
    System.out.println("Cart count: " + badge.getText()); // should be 6
	badge.click();	
	}
}


/*
 * ********************Practice Project Instructions — “SauceDemo Login & Product Actions”**************************************************


Follow each step, writing your own Selenium code for it.

Step 1 — Launch Browser & Go to Site

Open Chrome

Go to https://www.saucedemo.com/

Step 2 — Enter Username

Locator type: ID

Field ID: user-name

Value to enter: standard_user

Step 3 — Enter Password

Locator type: ID

Field ID: password

Value to enter: secret_sauce

Step 4 — Click Login Button

Locator type: XPath

XPath: //input[@id='login-button']

Step 5 — Click on First Product Name

Locator type: XPath

XPath: (//div[@class='inventory_item_name'])[1]

Step 6 — Get Product Name Text & Print It

Use getText() on the element

Step 7 — Add Product to Cart

Locator type: ID

Example: add-to-cart-sauce-labs-backpack (ID changes based on product)

Step 8 — Click Cart Icon

Locator type: XPath

XPath: //a[@class='shopping_cart_link']

Step 9 — Logout

Click menu button

ID: react-burger-menu-btn

Click "Logout"

ID: logout_sidebar_link   */