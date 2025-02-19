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

        // Apply color filter: Open the color filter dropdown by clicking on "Color"
        WebElement colorFilterTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='filter-options-item']//div[@data-role='title'][contains(text(), 'Color')]")));
        colorFilterTitle.click(); // Click on the "Color" title to open the color dropdown

        // After clicking on the color filter, navigate to the color-specific URL
        driver.get("https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html?color=49");

        // Verify that all displayed products have the selected color bordered in red (or any specific indication for color)
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));
        for (WebElement product : products) {
            WebElement colorSwatch = product.findElement(By.cssSelector(".swatch-option.color"));
            Assert.assertTrue(colorSwatch.isDisplayed(), "Color swatch is not displayed for product");
        }

        // Apply price filter: Click on the Price filter to open the dropdown
        WebElement priceFilterTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='filter-options-item']//div[@data-role='title'][contains(text(), 'Price')]")));
        priceFilterTitle.click(); // Click to open the Price filter dropdown

        // Click on the first price range ($50.00 - $59.99)
        WebElement priceRangeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html?color=49&amp;price=50-60']")));
        priceRangeOption.click(); // Click to apply the price range filter

        WebElement priceRangeOptions = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html?color=49&amp;price=50-60']")));
        priceRangeOptions.click(); // Click to apply the price range filter

        // Verify that each product's price falls within the selected price range
        for (WebElement product : products) {
            String priceText = product.findElement(By.cssSelector(".price")).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            Assert.assertTrue(price >= 50.00 && price <= 59.99, "Product price is outside the selected range: " + price);
        }

        // Pass the test if all assertions pass
        Assert.assertTrue(true);
    }
}
