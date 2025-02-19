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
    public void testSignInAndAddOneProductToCart() {
        driver.get("https://magento.softwaretestingboard.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Sign in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign In"))).click();
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement passwordField = driver.findElement(By.id("pass"));
        WebElement signInButton = driver.findElement(By.id("send2"));

        emailField.sendKeys("gisihasani95@gmail.com");
        passwordField.sendKeys("Alidemi1213");
        signInButton.click();

        // Navigate to Women > Tops > Jackets
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Jackets"))).click();

        // Get all products
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item-info"));

        // Add only the first product to the cart
        if (!products.isEmpty()) {
            WebElement product = products.get(0);

            // Select size using specific id selector
            WebElement sizeOption = product.findElement(By.id("option-label-size-143-item-169"));
            if (sizeOption != null) {
                sizeOption.click(); // Select the size
            }

            // Select the first color option (if available)
            List<WebElement> colorOptions = product.findElements(By.cssSelector(".swatch-option.color"));
            if (!colorOptions.isEmpty()) {
                colorOptions.get(0).click(); // Select first available color
            }

            // Add to cart
            WebElement addToCartButton = product.findElement(By.cssSelector(".tocart"));
            addToCartButton.click(); // Click 'Add to Cart'
        }

        // Immediately go to the cart page
        WebDriverWait waitForCart = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForCart.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".showcart"))).click();
        waitForCart.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".viewcart"))).click();

        // Wait for the cart page to load
        waitForCart.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-summary")));

        // Get the price excluding tax from the price-excluding-tax class
        WebElement priceExcludingTaxElement = driver.findElement(By.cssSelector(".price-excluding-tax"));
        double priceExcludingTax = parsePrice(priceExcludingTaxElement.getText());

        // Get the total price from the td element (usually grand total)
        WebElement totalPriceElement = driver.findElement(By.cssSelector("td .price"));
        double tdPrice = parsePrice(totalPriceElement.getText());

        // Compare the two prices
        Assert.assertEquals(priceExcludingTax, tdPrice, "The price excluding tax does not match the total price in the td element.");
    }

    // Utility function to parse the price text (handling currency symbols)
    private double parsePrice(String priceText) {
        // Remove any non-numeric characters (e.g., $, spaces)
        return Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
    }
}
