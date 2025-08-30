package org.test;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertClass {
	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		try { 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://demo.automationtesting.in/Alerts.html");
		
		WebElement EnableBtn = driver.findElement(By.xpath("//button[@onclick='alertbox()']"));
		EnableBtn.click();
		
		Alert sa =  driver.switchTo().alert();
		
		String SIMPLE = sa.getText();
		System.out.println(SIMPLE);

		sa.accept();
		
		WebElement ConfirmAlertBtn = driver.findElement(By.xpath("//a[@href='#CancelTab']"));
		ConfirmAlertBtn.click();

		WebElement EnableBtn1 = driver.findElement(By.xpath("//button[@onclick='confirmbox()']"));
		EnableBtn1.click();
		
		Alert ca =  driver.switchTo().alert();		
		String CONFIRM = ca.getText();
		System.out.println(CONFIRM);
		ca.dismiss();
		
		WebElement PromptAlertBtn = driver.findElement(By.xpath("//a[@href='#Textbox']"));
		PromptAlertBtn.click();
		
		
		WebElement PromptBtn = driver.findElement(By.xpath("//button[@onclick='promptbox()']"));
		PromptBtn.click();
		Alert pa = driver.switchTo().alert();
		String Prompt = pa.getText();
		System.out.println(Prompt);
		pa.sendKeys("Naveen");
		pa.accept();
		}
		
		finally {		
			driver.quit();
		}
		
	}
}
