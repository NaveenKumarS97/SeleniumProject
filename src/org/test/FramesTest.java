package org.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FramesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // If using Maven + WebDriverManager, you can do:
        // WebDriverManager.chromedriver().setup();
        // Otherwise ensure chromedriver is on PATH or set it explicitly:
        // System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Keep implicit small; rely on explicit waits for frames/elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://ui.vision/demo/webtest/frames/");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void snapOnFailure(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                // create screenshots/ if not present
                Path dir = Paths.get("screenshots");
                if (Files.notExists(dir)) {
                    Files.createDirectories(dir);
                }

                // take screenshot
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // destination path (Java 8 style)
                Path dest = dir.resolve(result.getName() + "_" + System.currentTimeMillis() + ".png");

                Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Saved screenshot: " + dest.toAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ---------- Helpers ----------

    /** Reset to top-level DOM. */
    private void toTop() {
        driver.switchTo().defaultContent();
    }

    /** Wait and switch to frame by index from current context. */
    private void switchToFrame(int index) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    /** Wait and switch to a specific <frame>/<iframe> element by locator from current context. */
    private void switchToFrame(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /** Get element (waits for visibility). */
    private WebElement el(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ---------- Tests ----------

    @Test(priority = 1)
    public void frame1_enterName() {
        // Enter frame #0 and type into mytext1
        toTop();
        switchToFrame(0);
        el(By.name("mytext1")).sendKeys("Naveen");

        // Assert value actually went in
        String val = el(By.name("mytext1")).getAttribute("value");
        Assert.assertEquals(val, "Naveen", "Frame1 input value mismatch");
    }

    @Test(priority = 2)
    public void frame2_enterName_byElement() {
        // Switch by locating the actual <frame> element (more stable than index when layout changes)
        toTop();
        switchToFrame(By.cssSelector("frame[src*='frame_2.html']"));
        el(By.name("mytext2")).sendKeys("Kumar");

        String val = el(By.name("mytext2")).getAttribute("value");
        Assert.assertEquals(val, "Kumar", "Frame2 input value mismatch");
    }

    @Test(priority = 3)
    public void frame3_and_innerFrame_interactions() {
        // Enter frame #2 and type into mytext3
        toTop();
        switchToFrame(2);
        el(By.name("mytext3")).sendKeys("Kevin");
        Assert.assertEquals(el(By.name("mytext3")).getAttribute("value"), "Kevin");

        // Now there is a nested INNER frame (Google Form) inside frame #3; index 0 refers to first CHILD frame
        switchToFrame(0);

        // Type "YES" into the text input inside the Google Form
        WebElement txt = el(By.xpath("//input[@type='text']"));
        txt.clear();
        txt.sendKeys("YES");
        Assert.assertEquals(txt.getAttribute("value"), "YES", "Inner-frame text not set");

        // Go back up one level (to frame #3)
        driver.switchTo().parentFrame();
        // (Optional) Verify we can still see the frame3 input
        Assert.assertTrue(driver.findElements(By.name("mytext3")).size() > 0, "Back in wrong context after inner frame");
    }

    @Test(priority = 4, dependsOnMethods = {"frame2_enterName_byElement", "frame3_and_innerFrame_interactions"})
    public void revisit_frame2_and_append() {
        // From any context, go to top, re-enter frame #1 and append text again
        toTop();
        switchToFrame(1);
        WebElement f2 = el(By.name("mytext2"));
        f2.sendKeys(" Kevin (again)");

        String val = f2.getAttribute("value");
        Assert.assertTrue(val.contains("Kumar") && val.contains("Kevin"),
                "Frame2 value did not contain expected appended text: " + val);
    }
}
