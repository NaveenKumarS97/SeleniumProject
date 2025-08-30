package org.test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Teee {
	
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
//	try {
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
/*  ********EXERCISE 1:**********
 Goal: Click Start, wait for “Hello World!” to appear.
Manual steps: Start → spinner shows → wait until text visible → assert exact text.
Hints:
Start button: #start button
Result text: #finish h4
Wait: visibilityOfElementLocated(...)	

		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		WebElement Start = driver.findElement(By.cssSelector("#start > button"));
		Start.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish > h4")));
		String msg1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish > h4"))).getText().trim();
		System.out.println(msg1);
		if ("!Hello World!".equals(msg1)) {
			throw new AssertionError ("Expected !Hello World! but actual error shown : " +  msg1);
		}
		driver.quit();  */

/* *************Exercise 2:************************
4) Notification Message (retry until “Action successful”)

Link: https://the-internet.herokuapp.com/notification_message_rendered
Goal: Click Click here repeatedly until the flash message equals “Action successful”.
Manual steps: Click → wait for banner → read text → if not “Action successful”, click link again.
Hints:
Link: #content a
Flash: #flash (strip trailing “×” close button)
Wait: visibilityOfElementLocated(...) each iteration 
 

		driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
		String Lastmsg = "";
        int maxtries = 8;
		for (int i=0; i < maxtries; i++) {
			WebElement Clickhere = driver.findElement(By.cssSelector("#content a"));
			Clickhere.click();
			Lastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#flash"))).getText().replace("×", "").trim();
			System.out.println("attempt " + (i+1) + ": " + Lastmsg);
			if (Lastmsg.equals("Action successful")) {
//				success = true;
				break;
			}	 
		}	
		if (!Lastmsg.equals("Action successful")) {
			throw new AssertionError ("it is not success in 8 times");
		}
		}
		finally {
            driver.quit();
        }
        */

		
		

		            driver.get("https://the-internet.herokuapp.com/tables");

		            // ---- Focus Table 1 ----
		            WebElement table = driver.findElement(By.id("table1"));
		            List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
		            
		            
		            
		            
		            if (rows.isEmpty()) {
		                throw new RuntimeException("No rows found in #table1");
		            }

		            // ---- Part A: find row with highest Due ----
		            BigDecimal maxDue = new BigDecimal("-1");
		            WebElement maxRow = null;

		            for (WebElement row : rows) {
		                List<WebElement> tds = row.findElements(By.cssSelector("td"));
		                // 0=Last, 1=First, 2=Email, 3=Due, 4=WebSite, 5=Action
		                BigDecimal due = parseCurrency(tds.get(3).getText()); // "$51.00" -> 51.00
		                System.out.println(due);
		                if (due.compareTo(maxDue) > 0) {
		                    maxDue = due;
		                    maxRow = row;
		                }
		            }
		            if (maxRow == null) throw new RuntimeException("Could not determine max Due row.");

		            List<WebElement> maxTds = maxRow.findElements(By.cssSelector("td"));
		            System.out.printf(
		                "Highest Due = $%s | Row => Last=%s | First=%s | Email=%s | Due=%s | WebSite=%s%n",
		                maxDue.toPlainString(),
		                maxTds.get(0).getText().trim(),
		                maxTds.get(1).getText().trim(),
		                maxTds.get(2).getText().trim(),
		                maxTds.get(3).getText().trim(),
		                maxTds.get(4).getText().trim()
		            );

		            // ---- Part B: click Last Name header to sort ----
		            WebElement lastNameHeader = table.findElement(By.cssSelector("thead th:nth-child(1)"));
		            lastNameHeader.click();

		            // ---- Part C: verify ascending A→Z ----
		            List<WebElement> lastNameCells = table.findElements(By.cssSelector("tbody tr td:nth-child(1)"));
		            List<String> displayedLastNames = new ArrayList<>();
		            for (WebElement cell : lastNameCells) {
		                displayedLastNames.add(cell.getText().trim());
		            }
		            System.out.println("Last names after sort: " + displayedLastNames);

		            assertTrue(isAscendingIgnoreCase(displayedLastNames),
		                    "Last Name column is not in ascending alphabetical order (A→Z).");

		            String firstDisplayed = displayedLastNames.get(0);
		            String minByAlpha = Collections.min(displayedLastNames, String.CASE_INSENSITIVE_ORDER);
		            assertTrue(firstDisplayed.equalsIgnoreCase(minByAlpha),
		                    "First row's last name is not the alphabetically smallest.");

		            System.out.println("✅ Sort verified: Ascending A→Z by Last Name; first row is alphabetically smallest.");

		        } 
		    

		    // ---------- helpers ----------
		    private static BigDecimal parseCurrency(String text) {
		        // "$1,234.50" -> "1234.50"
		        String cleaned = text.replace("$", "").replace(",", "").trim();
		        return new BigDecimal(cleaned);
		    }

		    private static boolean isAscendingIgnoreCase(List<String> list) {
		        for (int i = 1; i < list.size(); i++) {
		            if (list.get(i - 1).compareToIgnoreCase(list.get(i)) > 0) return false;
		        }
		        return true;
		    }

		    private static void assertTrue(boolean condition, String messageIfFail) {
		        if (!condition) throw new AssertionError(messageIfFail);
		    }
		}






