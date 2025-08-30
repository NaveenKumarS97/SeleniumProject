package org.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class DragandHold {
	
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
//		driver.get("https://greenstech.in/selenium-course-content.html");
//		
//		WebElement mveCourse = driver.findElement(By.xpath("//div[@title='Courses']"));
//		mveCourse.click();
//		
		Actions a = new Actions(driver);
//		a.moveToElement(mveCourse).perform();
//		
//		WebElement mveOracle = driver.findElement(By.xpath("//div[@title='Oracle']"));
//		a.moveToElement(mveOracle).perform();
//
//		
//		WebElement mveSQLlink = driver.findElement(By.xpath("//span[text()='SQL Certification Training']"));
//		a.click(mveSQLlink).perform();

			
		driver.get("https://demo.automationtesting.in/Dynamic.html");
		WebElement src = driver.findElement(By.xpath("//img[@id='angular']"));
		WebElement src2 = driver.findElement(By.xpath("//img[@id='mongo']"));
		WebElement src3 = driver.findElement(By.xpath("//img[@id='node']"));
		WebElement des = driver.findElement(By.xpath("//div[@id='droparea']"));
		
		a.dragAndDrop(src, des).perform();
		a.clickAndHold(src2).moveToElement(des).release().perform();
		a.clickAndHold(src3).release(des).perform();
	}	
}
