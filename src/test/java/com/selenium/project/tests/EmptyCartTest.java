package com.selenium.project.tests;

import com.selenium.project.pages.HomePage;
import com.selenium.project.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class EmptyCartTest extends BaseTest {

    @Test
    public void testLoginAndEmptyCart() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Initialize HomePage and click the SignIn link
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInLink();

        // Initialize LoginPage and perform login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("gisihasani95@gmail.com", "Alidemi1213");

        // Verify successful login
        Assert.assertTrue(homePage.isUserLoggedIn(), "Login failed or user is not logged in.");

        // Wait for and click the "Show Cart" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement showCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.showcart")));
        showCartButton.click();

        // Wait for the cart page to load
        WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.viewcart")));
        viewCartButton.click();

        // Delete items one by one using the ".action-delete" class
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".action.action-delete"));
        for (WebElement button : deleteButtons) {
            button.click();
            wait.until(ExpectedConditions.invisibilityOf(button));
        }

        // Check if the cart is empty by verifying the presence of the "cart-empty" class
        WebElement cartEmptyElement = driver.findElement(By.cssSelector(".cart-empty"));
        Assert.assertTrue(cartEmptyElement.isDisplayed(), "The cart is not empty.");
    }

}

