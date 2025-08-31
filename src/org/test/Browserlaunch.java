package org.test;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;




public class Browserlaunch {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
//		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.saucedemo.com/");
		
		
//		WebElement name = driver.findElement(By.xpath("//input[@id='email']"));
//		name.sendKeys("Naveen");
//		
//		WebElement password = driver.findElement(By.xpath("//input[@id='pass']"));
//		password.sendKeys("123456");
//		
//		WebElement button = driver.findElement(By.xpath("//button[@value='1']"));
//		button.click();
//		
//      WebElement Createbutton = driver.findElement(By.xpath("(//a[@role='button'])[2]"));
//   	Createbutton.click();
//		
//		WebElement Firstname = driver.findElement(By.name("firstname"));
//		Firstname.sendKeys("dhoni");
//				
//		WebElement Surname = driver.findElement(By.name("lastname"));
//		Surname.sendKeys("Naveen");
//		
//		WebElement email = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
//		email.sendKeys("naveesm@amazon.com");
//		
//		String title = driver.getTitle();
//		System.out.println(title);
//		
//		String curURL = driver.getCurrentUrl();
//		System.out.println(curURL);
//				
//      WebElement crc = driver.findElement(By.className("_8esh"));
//		crc.click();
//	
//		WebElement fullText = driver.findElement(By.xpath("//a[text()='Create a Page']"));
//		fullText.click();
//		
//		// Output: Create a Page for a celebrity, brand or business.
//		System.out.println(curURL);
		

// exercise:		
		
		WebElement userName = driver.findElement(By.id("user-name"));
		userName.sendKeys("standard_user");

		WebElement Passwd = driver.findElement(By.id("password"));
		Passwd.sendKeys("secret_sauce");
		
		WebElement LoginBtn = driver.findElement(By.id("login-button"));
		LoginBtn.click();

        
//Using Class name:
//		WebElement firstProd = driver.findElement(By.className("inventory_item_name"));
//   	firstProd.click();
		
//Using Index method:
		WebElement firstProd = driver.findElement(By.xpath("(//div[@class='inventory_item_name '])[1]"));
    	firstProd.click();
    	
    	String title = driver.getTitle();
		System.out.println(title);
    	
    	WebElement noOneProd = driver.findElement(By.xpath("(//div[text()='Sauce Labs Backpack'])"));
    	String text = noOneProd.getText();
    	System.out.println(text);
    	
//Using List method:
//		List <WebElement> firstProduct = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
//		WebElement firstProdClick = firstProduct.get(0);
//		firstProdClick.click();
    	
    	WebElement AddBtn = driver.findElement(By.id("add-to-cart"));
    	AddBtn.click();
    	
    	WebElement CartBtn = driver.findElement(By.className("shopping_cart_link"));
    	CartBtn.click();
    	
    	Actions a = new Actions(driver);
    	
    	WebElement MenuBtn = driver.findElement(By.id("react-burger-menu-btn"));
    	a.moveToElement(MenuBtn).perform();
    	
    	
    	WebElement LogOutBtn = driver.findElement(By.id("logout_sidebar_link"));
    	a.click(LogOutBtn).perform();
    	
    	WebElement LogOutBtn1 = driver.findElement(By.id("logout_sidebar_link"));
    	a.click(LogOutBtn).perform();
    	
}
}
