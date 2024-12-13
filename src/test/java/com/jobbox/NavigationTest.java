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

public class NavigationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set up explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testNavigationMenuItems() {
        // Navigate to the website
        driver.get("https://jobbox-gules.vercel.app/");

        // Wait for navigation to be present
        WebElement navigation = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".hidden.md\\:flex")));

        // Find all navigation items
        List<WebElement> navItems = navigation.findElements(By.cssSelector("a"));

        // Extract navigation item texts
        List<String> navItemTexts = navItems.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());

        // Print navigation items for logging
        System.out.println("Navigation Items: " + navItemTexts);

        // Verify minimum number of navigation items
        Assert.assertTrue(navItemTexts.size() >= 7,
                "Expected at least 7 navigation items, found: " + navItemTexts.size());

        // Expected navigation items based on the HTML
        List<String> expectedNavItems = List.of(
                "Home", "About", "Gov.Jobs", "Pvt.Jobs", "Links", "Books", "Contact");

        // Check if all expected items are present
        expectedNavItems.forEach(expectedItem -> Assert.assertTrue(
                navItemTexts.stream().anyMatch(item -> item.trim().equalsIgnoreCase(expectedItem.trim())),
                "Navigation item '" + expectedItem + "' not found"));
    }

    @Test
    public void testNavigationItemClickability() {
        // Navigate to the website
        driver.get("https://jobbox-gules.vercel.app/");

        // Wait for navigation to be present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav")));

        // Test each navigation item
        List<String> navItemTexts = List.of("About", "Gov.Jobs", "Pvt.Jobs", "Links", "Books", "Contact");

        for (String navItemText : navItemTexts) {
            try {
                // Relocate the navigation item before each click to avoid stale element
                WebElement navItem = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//nav//a[contains(text(), '" + navItemText + "')]")));

                // Store current URL
                String originalUrl = driver.getCurrentUrl();

                // Try standard click first
                try {
                    navItem.click();
                } catch (Exception standardClickException) {
                    // Fallback to JavaScript click
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navItem);
                }

                // Wait for page to load with more robust condition
                wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .equals("complete"));

                // Optional: Add small delay for dynamic content
                Thread.sleep(500);

                // Verify URL has changed
                Assert.assertNotEquals(
                        originalUrl,
                        driver.getCurrentUrl(),
                        "Navigation item '" + navItemText + "' did not navigate");

                // Navigate back to home
                driver.get("https://jobbox-gules.vercel.app/");
            } catch (Exception e) {
                Assert.fail("Failed to navigate with item: " + navItemText, e);
            }
        }
    }

    @Test
    public void testNavigationMenuItemsTwo() {
        // Navigate to the website
        driver.get("https://jobbox-gules.vercel.app/");

        // Wait for navigation to be present
        WebElement navigation = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".hidden.md\\:flex")));

        // Find all navigation items
        List<WebElement> navItems = navigation.findElements(By.cssSelector("a"));

        // Extract navigation item texts
        List<String> navItemTexts = navItems.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());

        // Print navigation items for logging
        System.out.println("Navigation Items: " + navItemTexts);

        // Verify minimum number of navigation items
        Assert.assertTrue(navItemTexts.size() >= 7,
                "Expected at least 7 navigation items, found: " + navItemTexts.size());

        // Expected navigation items based on the HTML
        List<String> expectedNavItems = List.of(
                "Home", "About", "Gov.Jobs", "Pvt.Jobs", "Links", "Books", "Contact", "navitem");

        // Check if all expected items are present
        expectedNavItems.forEach(expectedItem -> Assert.assertTrue(
                navItemTexts.stream().anyMatch(item -> item.trim().equalsIgnoreCase(expectedItem.trim())),
                "Navigation item '" + expectedItem + "' not found"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}