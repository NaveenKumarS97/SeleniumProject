package org.test;                           // Declares the Java package (namespace) for this class.

import java.time.Duration;                  // Duration is used to define wait timeouts (e.g., 10 seconds).

import org.openqa.selenium.*;               // Core Selenium interfaces: WebDriver, WebElement, By, etc.
import org.openqa.selenium.chrome.ChromeDriver; // ChromeDriver implementation of WebDriver for Chrome.
import org.openqa.selenium.support.ui.ExpectedConditions; // Prebuilt wait conditions (visibility, clickability, URL, etc.).
import org.openqa.selenium.support.ui.WebDriverWait;      // Explicit wait utility.

public class SacsLab {                // Public class (file name must match class name).

    public static void main(String[] args) {   // Entry point for the Java program.
        WebDriver driver = new ChromeDriver();  // Launches a new Chrome browser session (opens a Chrome window).
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                                                // Explicit wait helper: polls DOM until a condition is true or timeout.
        try {                                   // try/finally to ensure the browser quits even if something fails.

            driver.manage().window().maximize(); // Maximizes the browser window (more stable element positions).
            driver.get("https://www.saucedemo.com/"); 
                                                 // Navigates to the URL (blocks until “document.readyState=complete” by default).

            // --- Login ---
            wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("user-name"))) // Wait until the username field exists and is visible.
                .sendKeys("standard_user");                      // Types text into the <input> (simulates user typing).

            driver.findElement(By.id("password")) // Locates the password field by its id attribute.
                  .sendKeys("secret_sauce");      // Types the password.

            driver.findElement(By.id("login-button")) // Locates the Login button by id.
                  .click();                          // Clicks it.

            // --- Confirm we’re on the inventory page ---
            wait.until(ExpectedConditions.urlContains("inventory"));
            // Waits until the URL contains the substring “inventory” (indicates login success & page loaded).

            // --- Open first product on the list ---
            WebElement firstProd = wait.until(ExpectedConditions
                .elementToBeClickable(
                    By.xpath("(//div[contains(@class,'inventory_item_name')])[1]")));
            // XPath explanation:
            //  ( ... )[1]          → take the first match
            //  //div[...]          → find <div> anywhere
            //  contains(@class,'inventory_item_name') → class attribute contains that token (robust to extra spaces/tokens)
            // ExpectedConditions.elementToBeClickable → visible + enabled (safe to click).

            String productName = firstProd.getText(); // Reads visible text inside that <div>.
            System.out.println("First product: " + productName); // Prints to console (good for quick assertions/debug).
            firstProd.click();                        // Clicks to open the product details page.

            // --- Add to cart on product details page ---
            
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add-to-cart']")));		
//          .elementToBeClickable(By.cssSelector("button[id^='add-to-cart-']")));
            // CSS selector explanation:
            //  button[...]        → a <button> element
            //  [id^='add-to-cart-'] → attribute starts with “add-to-cart-” (matches any product’s add button)
            // Using ^ (starts-with) makes this locator generic across products.

            addBtn.click();                           // Clicks the Add to cart button.

            // --- Go to cart ---
            wait.until(ExpectedConditions
                .elementToBeClickable(By.className("shopping_cart_link"))) // Finds the cart icon by class name
                .click();                                                  // and clicks it.

            // --- Verify product is in cart ---
            WebElement cartItemName = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".inventory_item_name")));
            // CSS: .inventory_item_name → element with class “inventory_item_name”.
            System.out.println("In cart: " + cartItemName.getText()); // Prints the product name found in the cart.

            // --- Logout ---
            wait.until(ExpectedConditions
                .elementToBeClickable(By.id("react-burger-menu-btn"))) // Menu (hamburger) button.
                .click();

            wait.until(ExpectedConditions
                .elementToBeClickable(By.id("logout_sidebar_link")))   // “Logout” link in the side menu.
                .click();

        } finally {
//            driver.quit(); // Always close the browser and end the WebDriver session (prevents zombie chromedrivers).
        }
    }
}
