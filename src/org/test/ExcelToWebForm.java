
/* package org.test;
public class ExcelToWebForm {

/* import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



    public static void main(String[] args) throws Exception {
        // ====== EDIT THIS PATH ======
        String excelPath = "C:\\Users\\spavi\\OneDrive\\Documents\\webform.xlsx";   // change to your .xlsx path

        String sheetName = "Sheet1";                   // change if needed

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook wb = WorkbookFactory.create(fis)) {

            Sheet sh = wb.getSheet(sheetName);
            if (sh == null) throw new RuntimeException("Sheet not found: " + sheetName);

            // Optional: assume the first row is headers -> start from row 1
            int firstDataRow = 1;

            for (int r = firstDataRow; r <= sh.getLastRowNum(); r++) {
                Row row = sh.getRow(r);
                if (row == null) continue; // skip blank rows

                String txtValue   = cellAsString(row.getCell(0));  // Column A -> Text input
                String pwdValue   = cellAsString(row.getCell(1));  // Column B -> Password
                String areaValue  = cellAsString(row.getCell(2));  // Column C -> Textarea
                String dropValue  = cellAsString(row.getCell(3));  // Column D -> Dropdown: "One" or "Two"

                // Open the form for each submission (page changes after submit)
                driver.get("https://www.selenium.dev/selenium/web/web-form.html");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // --- Fill fields ---
                WebElement textInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-text-id")));
                textInput.clear();
                textInput.sendKeys(txtValue);

                WebElement password = driver.findElement(By.name("my-password"));
                password.clear();
                password.sendKeys(pwdValue);

                WebElement textarea = driver.findElement(By.name("my-textarea"));
                textarea.clear();
                textarea.sendKeys(areaValue);

                Select dropdown = new Select(driver.findElement(By.cssSelector("select.form-select")));
                //if (dropValue != null && !dropValue.isBlank()) {
                    dropdown.selectByVisibleText(dropValue.trim()); // e.g., "One" or "Two"
                }

                // --- Submit ---
                driver.findElement(By.cssSelector("button[type='submit']")).click();

                // --- Basic verification ---
                // Page shows a result — wait for an <h1> heading or a title change
                wait.until(ExpectedConditions.or(
                        ExpectedConditions.presenceOfElementLocated(By.tagName("h1")),
                        ExpectedConditions.titleContains("Form")
                ));

                System.out.printf("Row %d submitted: text='%s', dropdown='%s'%n", r, txtValue, dropValue);
            }
        } finally {
            driver.quit();
        }
    }

    // Converts any Excel cell to a readable String (keeps numbers/dates formatted)
    private static String cellAsString(Cell cell) {
        if (cell == null) return "";
        DataFormatter fmt = new DataFormatter();
        switch (cell.getCellType()) {
            case STRING:  return cell.getStringCellValue();
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                } else {
                    // Use DataFormatter to avoid scientific notation, etc.
                    return fmt.formatCellValue(cell);
                }
            case FORMULA: return fmt.formatCellValue(cell);
            case BLANK:   return "";
            default:      return fmt.formatCellValue(cell);
        }
    
    }

*/