package com.selenium.project.tests;

import com.selenium.project.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.List;

public class PageFiltersTest extends BaseTest {
    @Test
    public void testPageFilters() {
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

        // Apply color filter
        driver.findElement(By.cssSelector("div[option-label='Blue']")).click();

        // Verify all displayed products have the selected color
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));
        for (WebElement product : products) {
            Assert.assertTrue(product.findElement(By.cssSelector(".swatch-option.color")).getAttribute("option-label").equals("Blue"));
        }

        // Apply price filter
        driver.findElement(By.cssSelector("div[option-label='$50.00 - $59.99']")).click();

        // Verify only two products are displayed
        products = driver.findElements(By.cssSelector(".product-item"));
        Assert.assertEquals(products.size(), 2);

        // Verify price of each product
        for (WebElement product : products) {
            String priceText = product.findElement(By.cssSelector(".price")).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            Assert.assertTrue(price >= 50.00 && price <= 59.99);
        }
    }
}