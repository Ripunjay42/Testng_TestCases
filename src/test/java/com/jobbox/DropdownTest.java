package com.jobbox;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class DropdownTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Increased wait time to 15 seconds for more visibility
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void atestDropdownMenuVisibility() {
        // Navigate to the website
        driver.get("https://jobbox-gules.vercel.app/");

        // Wait for and locate the dropdown button using the specific CSS selector
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".relative.group > button")));

        // Add a pause to make the process more visible
        try {
            Thread.sleep(2000); // 2 seconds pause
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify dropdown button is present
        Assert.assertTrue(dropdownButton.isDisplayed(), "Dropdown button is not visible");
        Assert.assertTrue(dropdownButton.getText().contains("Others"), "Dropdown button text is incorrect");
    }

    @Test
    public void btestDropdownMenuItems() {
        // Navigate to the website
        driver.get("https://jobbox-gules.vercel.app/");

        // Wait for and locate the dropdown button
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".relative.group > button")));

        // Add a pause before clicking
        try {
            Thread.sleep(1500); // 1.5 seconds pause
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Open dropdown by clicking the button
        dropdownButton.click();

        // Wait for dropdown menu to be visible using the specific CSS selector
        WebElement dropdownMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".absolute.left-0.mt-2.w-48")));

        // Add a pause to show the dropdown menu
        try {
            Thread.sleep(2000); // 2 seconds pause
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Find all dropdown menu items using the specific role attribute selector
        List<WebElement> dropdownItems = dropdownMenu.findElements(By.cssSelector("a[role='menuitem']"));

        // Extract dropdown item texts
        List<String> dropdownItemTexts = dropdownItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Print dropdown items for logging
        System.out.println("Dropdown Items: " + dropdownItemTexts);

        // Verify number of dropdown items
        Assert.assertEquals(dropdownItemTexts.size(), 3,
                "Expected 3 dropdown items, found: " + dropdownItemTexts.size());

        // Expected dropdown items
        List<String> expectedDropdownItems = List.of("Employer", "Internship", "Courses");

        // Check if all expected items are present
        expectedDropdownItems.forEach(expectedItem -> Assert.assertTrue(
                dropdownItemTexts.stream().anyMatch(item -> item.trim().equalsIgnoreCase(expectedItem.trim())),
                "Dropdown item '" + expectedItem + "' not found"));
    }

    @Test
    public void ctestDropdownMenuItemClickability() {
        // Navigate to the website
        driver.get("https://jobbox-gules.vercel.app/");

        // Wait for the dropdown button to be clickable
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".relative.group > button")));

        // List of items to check in the dropdown menu
        List<String> dropdownItemTexts = List.of("Internship", "Courses", "Employer");

        for (String itemText : dropdownItemTexts) {
            try {
                // Open the dropdown menu (ensure it's open before interacting)
                dropdownButton.click();

                // Wait for dropdown menu to be visible and interactive
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".absolute.left-0.mt-2.w-48")));

                // Relocate the dropdown item using the role and text-based selector
                WebElement dropdownItem = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space(text())='" + itemText + "']")));

                // Store the current URL to verify navigation
                String originalUrl = driver.getCurrentUrl();

                // Try standard click first
                try {
                    dropdownItem.click();
                } catch (Exception standardClickException) {
                    // Fallback to JavaScript click if standard click fails
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownItem);
                }

                // Wait for the page to load with a more robust condition
                wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .equals("complete"));

                // Optional: Add a small delay to allow dynamic content to settle
                Thread.sleep(6000); // 1.5 seconds pause for stability

                // Verify that the URL has changed after navigation
                Assert.assertNotEquals(
                        originalUrl,
                        driver.getCurrentUrl(),
                        "Dropdown item '" + itemText + "' did not navigate");

                // Navigate back to home page
                driver.get("https://jobbox-gules.vercel.app/");
            } catch (Exception e) {
                Assert.fail("Failed to navigate with dropdown item: " + itemText, e);
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}