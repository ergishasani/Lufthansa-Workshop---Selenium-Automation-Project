package com.selenium.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import java.time.Duration;

public class EmptyCartTest extends BaseTest {
    @Test
    public void testEmptyCart() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Navigate to shopping cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action.viewcart"))).click();

        // Delete items one by one
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".action.delete"));
        for (WebElement button : deleteButtons) {
            button.click();
            wait.until(ExpectedConditions.invisibilityOf(button));
        }

        // Verify cart is empty
        Assert.assertTrue(driver.findElement(By.cssSelector(".empty-cart-message")).isDisplayed());
    }
}