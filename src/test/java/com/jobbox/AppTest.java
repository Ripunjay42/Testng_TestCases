package com.jobbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testWebsiteLoading() {
        driver.get("https://jobbox-gules.vercel.app/");
        
        // Basic assertions
        Assert.assertNotNull(driver.getTitle(), "Website title is missing");
        Assert.assertTrue(driver.getCurrentUrl().contains("jobbox"), "Incorrect URL");
    }

    @Test
    public void testWebsiteAccessibility() {
        driver.get("https://jobbox-gules.vercel.app/");
        
        // Check if page is fully loaded
        Assert.assertTrue(driver.getPageSource().length() > 0, "Page source is empty");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}