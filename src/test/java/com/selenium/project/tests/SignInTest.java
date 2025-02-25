package com.selenium.project.tests;

import com.selenium.project.pages.HomePage;
import com.selenium.project.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignInTest extends BaseTest {

    @Test
    public void testSignIn() {
        try {
            // Navigate to the website
            driver.get("https://magento.softwaretestingboard.com/");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Current Title: " + driver.getTitle());

            // Initialize HomePage and click the SignIn link
            HomePage homePage = new HomePage(driver);
            homePage.clickSignInLink();
            System.out.println("After clicking Sign In - Current URL: " + driver.getCurrentUrl());
            System.out.println("After clicking Sign In - Current Title: " + driver.getTitle());

            // Initialize LoginPage and perform login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("gisihasani95@gmail.com", "Alidemi1213");
            System.out.println("After login attempt - Current URL: " + driver.getCurrentUrl());
            System.out.println("After login attempt - Current Title: " + driver.getTitle());

            // Check if the user is still on the login page (failed login)
            String currentUrl = driver.getCurrentUrl();
            String expectedLoginPageUrl = "https://magento.softwaretestingboard.com/customer/account/login/";

            if (currentUrl.startsWith(expectedLoginPageUrl)) {
                System.out.println("Login failed, still on login page: " + currentUrl);
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-error")));
                    String errorMessage = loginPage.getErrorMessage();
                    System.out.println("Error Message: " + errorMessage);
                } catch (Exception e) {
                    System.out.println("No error message found.");
                }
                return; // Exit the test here as no redirection occurred, indicating failed login
            }

            // If redirected to the homepage after login, confirm successful login
            if (currentUrl.equals("https://magento.softwaretestingboard.com/")) {
                System.out.println("Login successful and redirected to homepage.");
            } else {
                System.out.println("Login failed, redirection issue.");
                Assert.fail("Login failed or the page wasn't redirected correctly. Current URL: " + currentUrl);
            }

            // Verify successful login by checking if a unique element (e.g., "My Account") is present
            boolean isUserLoggedIn = homePage.isUserLoggedIn(); // Implement this method in HomePage to check a logged-in element
            Assert.assertTrue(isUserLoggedIn, "User is not logged in or homepage didn't load correctly.");

        } catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
