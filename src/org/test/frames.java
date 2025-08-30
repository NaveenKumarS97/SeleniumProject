package org.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class frames {

	  public static void main(String[] args) {
	        ChromeDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://ui.vision/demo/webtest/frames/");
			//SWITCH TO FRAME USING INDEX
			driver.switchTo().frame(0);
	        WebElement frame1 = driver.findElement(By.name("mytext1"));
	        frame1.sendKeys("Naveen");
	        //COMING OUT THE FRAME --- TO DOM
	        driver.switchTo().defaultContent();
	        //Creating OBJECT FOR FRAME 2 
	        WebElement framebyname = driver.findElement(By.xpath("//frame[@src='frame_2.html']"));
	        //SWITCH TO FRAME USING NAME
	        driver.switchTo().frame(framebyname);
	        WebElement frame2 = driver.findElement(By.name("mytext2"));	        
	        frame2.sendKeys("Kumar");
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame(2);
	        WebElement frame3 = driver.findElement(By.name("mytext3"));
	        frame3.sendKeys("Kevin");
	        driver.switchTo().frame(0); 
	        WebElement Btnclick1 = driver.findElement(By.xpath("(//div[@class='AB7Lab Id5V1'])[3]"));
	        Btnclick1.click();
	        WebElement others = driver.findElement(By.xpath("//input[@type='text']"));
	        others.sendKeys("YES");
	        WebElement Btnclick2 = driver.findElement(By.xpath("(//div[@class='uHMk6b fsHoPb'])[2]"));
	        Btnclick2.click();	       
	        WebElement Btnclick3 = driver.findElement(By.xpath("(//div[@class='uHMk6b fsHoPb'])[3]"));
	        Btnclick3.click();	
	        driver.switchTo().parentFrame();
	        WebElement frame2NEW = driver.findElement(By.name("mytext2"));	        
	        frame2NEW.sendKeys("Kevin");

	        
	  }
}
