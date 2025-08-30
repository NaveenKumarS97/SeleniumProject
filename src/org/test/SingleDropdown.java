package org.test;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SingleDropdown {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		try {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		
		WebElement BTN = driver.findElement(By.xpath("(//a[@role='button'])[2]"));
		BTN.click();		
		WebElement MonthBtn = driver.findElement(By.id("month"));
		Select s1 = new Select(MonthBtn);
		s1.selectByIndex(1);	
		System.out.println("getting the value of the selected dropdown (Index 1): " + s1.getFirstSelectedOption().getText());
		s1.selectByValue("5");
		System.out.println("getting the value of the selected dropdown (Value 5): " + s1.getFirstSelectedOption().getText());
		s1.selectByVisibleText("Feb");		
		System.out.println("getting the value of the selected dropdown (Text Feb): " + s1.getFirstSelectedOption().getText());

		
		boolean b = s1.isMultiple();
		System.out.println("Is it Multiple Dropdown ? : " + b);
		
		
		List<WebElement> optList =  s1.getOptions();
		
		WebElement P = optList.get(1);
		System.out.println("Getting the value through getOptions method (from List)  - Value for Index 1:" + P.getText());
		
		System.out.println("Getting all the options available in the dropdown for  - MONTH");
		for (WebElement x : optList) {
			System.out.println(x.getText());
		}
		MonthBtn.sendKeys("Jan");
		
		
		
		driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
		WebElement btn = driver.findElement(By.name("multipleselect[]"));
		
		//SELECT:
		Select s11 = new Select (btn);
		//SELECTBY
		s11.selectByIndex(0);
		s11.selectByValue("msagile");
		s11.selectByVisibleText("Selenium");

		//IS MULTIPLE:
		Boolean Multiple = s11.isMultiple();
		System.out.println("Is it Multiple Dropdown:----- " + Multiple);
		
		System.out.println("Get All Options present in dropdown ------ ");
		List<WebElement> op = s11.getOptions();
		for (WebElement x : op) {
			System.out.println(x.getText());
			}

		System.out.println("Selected Options from dropdown ------ ");			
		List<WebElement> SelectedOptions = s11.getAllSelectedOptions();	
		for (WebElement y : SelectedOptions) {
			System.out.println(y.getText());
			}
		
		System.out.println("First Selected Option from dropdown ------ ");			
		WebElement First = s11.getFirstSelectedOption();
		System.out.println(First.getText());
	
		System.out.println("****************************************");
		System.out.println("select all options in dropdown by index");
		for (int i=0; i<op.size(); i++){
		s11.selectByIndex(i);
		}
		
		System.out.println("****************************************");
		System.out.println("select all options in dropdown by value");
		for (WebElement x : op) {
		String VAL = x.getAttribute("value");
		s11.selectByValue(VAL);
		}
		
		System.out.println("****************************************");
		System.out.println("select all options in dropdown by visibletext");
		for (WebElement x : op) {
		String TXT = x.getText();
		s11.selectByVisibleText(TXT);
		}
		
		Thread.sleep(5000);
		s11.deselectAll();	
	    }
	     finally {
		   driver.quit();
	       }		
}
}
	
