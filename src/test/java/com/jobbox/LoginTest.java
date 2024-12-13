package com.jobbox;

import org.openqa.selenium.By;
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

public class LoginTest {

        private WebDriver driver;
        private WebDriverWait wait;

        @BeforeMethod
        public void setUp() {
                // Setup WebDriver for Chrome
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();

                // Set up WebDriverWait with a 10-second timeout
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        @Test
        public void aatestLoginFromHomePage() {
                // Step 1: Navigate to the Home Page
                driver.get("https://jobbox-gules.vercel.app/");

                // Step 2: Wait for the 'Admin' button to load and click it
                WebElement adminButton = wait.until(ExpectedConditions
                                .elementToBeClickable(By.cssSelector("button.bg-white.dark\\:bg-gray-700")));
                adminButton.click();

                // Step 3: Wait for the login page to load (waiting for form element)
                WebElement loginForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

                // Step 4: Locate the 'username' and 'password' input fields
                WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
                WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));

                // Step 5: Locate the login button (submit button)
                WebElement loginButton = wait.until(
                                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

                // Step 6: Enter valid login credentials (replace with actual test credentials)
                usernameField.sendKeys("dhiraj12"); // Replace with your username for testing
                passwordField.sendKeys("Dhiraj@12"); // Replace with your password for testing

                // Step 7: Click the login button to submit the form
                loginButton.click();

                // Step 8: Wait for the dashboard element to appear (indicating a successful
                // login)
                WebElement dashboardElement = wait.until(ExpectedConditions
                                .presenceOfElementLocated(By.cssSelector("button.px-3.py-1.text-sm.rounded-md")));

                // Step 9: Assert that the dashboard element is displayed (indicating successful
                // login)
                Assert.assertTrue(dashboardElement.isDisplayed(), "Login failed: Dashboard element not visible!");

                // Step 10: Verify that the user was redirected to the dashboard URL after login
                Assert.assertEquals(driver.getCurrentUrl(), "https://jobbox-gules.vercel.app/login",
                                "User did not navigate to the expected dashboard URL after login.");
        }

        @Test
        public void atestInvalidoneLoginFromHomePage() {
                // Step 1: Navigate to the Home Page
                driver.get("https://jobbox-gules.vercel.app/");

                // Step 2: Wait for the 'Admin' button to load and click it
                WebElement adminButton = wait.until(ExpectedConditions
                                .elementToBeClickable(By.cssSelector("button.bg-white.dark\\:bg-gray-700")));
                adminButton.click();

                // Step 3: Wait for the login page to load (waiting for form element)
                WebElement loginForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

                // Step 4: Locate the 'username' and 'password' input fields
                WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
                WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));

                // Step 5: Locate the login button (submit button)
                WebElement loginButton = wait.until(
                                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

                // Step 6: Enter invalid login credentials (for testing failure)
                usernameField.sendKeys("invaliduser"); // Use invalid username
                passwordField.sendKeys("invalidpass"); // Use invalid password

                // Step 7: Click the login button to submit the form
                loginButton.click();

                // Step 8: Wait for the error message to appear (assuming the app shows an error
                // for invalid login)
                WebElement errorMessage = wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                                By.cssSelector("p.text-red-600.dark\\:text-red-400.text-xs")));

                // Step 9: Assert that the error message is displayed (indicating login failure)
                Assert.assertTrue(errorMessage.isDisplayed(),
                                "Login failed: Error message not displayed for invalid credentials.");
        }

        @Test
        public void btestInvalidtwoLoginFromHomePage() {
                // Step 1: Navigate to the Home Page
                driver.get("https://jobbox-gules.vercel.app/");

                // Step 2: Wait for the 'Admin' button to load and click it
                WebElement adminButton = wait.until(ExpectedConditions
                                .elementToBeClickable(By.cssSelector("button.bg-white.dark\\:bg-gray-700")));
                adminButton.click();

                // Step 3: Wait for the login page to load (waiting for form element)
                WebElement loginForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

                // Step 4: Locate the 'username' and 'password' input fields
                WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
                WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));

                // Step 5: Locate the login button (submit button)
                WebElement loginButton = wait.until(
                                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

                // Step 6: Enter invalid login credentials (for testing failure)
                usernameField.sendKeys("invaliduser"); // Use invalid username
                passwordField.sendKeys("Dhiraj@12"); // Use invalid password

                // Step 7: Click the login button to submit the form
                loginButton.click();

                // Step 8: Wait for the error message to appear (assuming the app shows an error
                // for invalid login)
                WebElement errorMessage = wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                                By.cssSelector("p.text-red-600.dark\\:text-red-400.text-xs")));

                // Step 9: Assert that the error message is displayed (indicating login failure)
                Assert.assertTrue(errorMessage.isDisplayed(),
                                "Login failed: Error message not displayed for invalid credentials.");
        }

        @Test
        public void ctestInvalidLoginFromHomePage() {
                // Step 1: Navigate to the Home Page
                driver.get("https://jobbox-gules.vercel.app/");

                // Step 2: Wait for the 'Admin' button to load and click it
                WebElement adminButton = wait.until(ExpectedConditions
                                .elementToBeClickable(By.cssSelector("button.bg-white.dark\\:bg-gray-700")));
                adminButton.click();

                // Step 3: Wait for the login page to load (waiting for form element)
                WebElement loginForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

                // Step 4: Locate the 'username' and 'password' input fields
                WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
                WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));

                // Step 5: Locate the login button (submit button)
                WebElement loginButton = wait.until(
                                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

                // Step 6: Enter invalid login credentials (for testing failure)
                usernameField.sendKeys("dhiraj12"); // Use invalid username
                passwordField.sendKeys("invalidpass"); // Use invalid password

                // Step 7: Click the login button to submit the form
                loginButton.click();

                // Step 8: Wait for the error message to appear (assuming the app shows an error
                // for invalid login)
                WebElement errorMessage = wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                                By.cssSelector("p.text-red-600.dark\\:text-red-400.text-xs")));

                // Step 9: Assert that the error message is displayed (indicating login failure)
                Assert.assertTrue(errorMessage.isDisplayed(),
                                "Login failed: Error message not displayed for invalid credentials.");
        }

        @AfterMethod
        public void tearDown() {
                // Step 10: Close the browser after each test method
                if (driver != null) {
                        driver.quit();
                }
        }
}
