package com.selenium.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShoppingCartTest extends BaseTest {
    @Test
    public void testShoppingCart() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Navigate to Women > Tops > Jackets
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tops"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Jackets"))).click();

        // Add all displayed items to the shopping cart
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(".action.tocart"));
        for (WebElement button : addToCartButtons) {
            button.click();
            Assert.assertTrue(driver.findElement(By.cssSelector(".message-success")).isDisplayed());
        }

        // View cart
        driver.findElement(By.cssSelector(".action.viewcart")).click();

        // Verify navigation to shopping cart page
        Assert.assertTrue(driver.getTitle().contains("Shopping Cart"));

        // Verify prices sum
        double totalPrice = 0.0;
        List<WebElement> prices = driver.findElements(By.cssSelector(".cart-price"));
        for (WebElement price : prices) {
            totalPrice += Double.parseDouble(price.getText().replace("$", ""));
        }

        double orderTotal = Double.parseDouble(driver.findElement(By.cssSelector(".order-total .price")).getText().replace("$", ""));
        Assert.assertEquals(totalPrice, orderTotal);
    }
}