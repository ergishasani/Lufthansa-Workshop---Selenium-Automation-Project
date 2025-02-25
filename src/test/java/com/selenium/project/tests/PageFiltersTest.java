package com.selenium.project.tests;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Jackets"))).click(); // Click on Jackets option

        // Apply color filter
        WebElement colorFilterTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'filter-options-title') and text()='Color']")));
        colorFilterTitle.click(); // Open color filter dropdown

        // Select a specific color (e.g., Red)
        List<WebElement> colorOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".filter-options-content .swatch-option.color")));
        if (!colorOptions.isEmpty()) {
            colorOptions.get(0).click(); // Click on the first available color
        } else {
            Assert.fail("Color filter options are missing!");
        }

        // Wait for page to update after filter application
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-item")));

        // Verify that all displayed products have the selected color bordered in red
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));
        for (WebElement product : products) {
            WebElement colorSwatch = product.findElement(By.cssSelector(".swatch-option.color.selected"));
            Assert.assertTrue(colorSwatch.isDisplayed(), "Color swatch is not displayed for product");
        }

        // Apply price filter
        WebElement priceFilterTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'filter-options-title') and text()='Price']")));
        priceFilterTitle.click(); // Open price filter dropdown

        // Select the third price range
        List<WebElement> priceOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".filter-options-content .item")));
        if (priceOptions.size() > 2) {
            priceOptions.get(2).click(); // Click the third price option
        } else {
            Assert.fail("Price filter options are missing!");
        }

        // Wait for the page to reload
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-item")));

        // Verify that each product's price falls within the selected price range
        for (WebElement product : driver.findElements(By.cssSelector(".product-item"))) {
            String priceText = product.findElement(By.cssSelector(".price")).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            Assert.assertTrue(price >= 50.00 && price <= 59.99, "Product price is outside the selected range: " + price);
        }

        // Pass the test if all assertions pass
        Assert.assertTrue(true);
    }
}
