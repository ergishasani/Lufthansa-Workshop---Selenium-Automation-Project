package com.selenium.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WishListTest extends BaseTest {

    @Test
    public void testWishList() {

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

        // Initialize Actions class for hover actions
        Actions actions = new Actions(driver);

        // Hover and add the first item to wishlist
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item-info:nth-of-type(1)")));
        actions.moveToElement(firstProduct).perform(); // Hover over the first product
        WebElement addToWishListButton1 = wait.until(ExpectedConditions.elementToBeClickable(firstProduct.findElement(By.cssSelector(".action.towishlist"))));
        addToWishListButton1.click();

        // Click on the logo to return to the homepage
        driver.findElement(By.cssSelector(".logo img")).click();

        // Navigate to Women > Tops > Jackets again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Jackets"))).click();

        // Hover and add the second item (product-item) to wishlist
        WebElement secondProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item:nth-of-type(2) .product-item-info")));
        actions.moveToElement(secondProduct).perform(); // Hover over the second product
        WebElement addToWishListButton2 = wait.until(ExpectedConditions.elementToBeClickable(secondProduct.findElement(By.cssSelector(".action.towishlist"))));
        addToWishListButton2.click();

        // Click on the logo to return to the homepage again
        driver.findElement(By.cssSelector(".logo img")).click();

        // Pass the test if all actions were completed without errors
        Assert.assertTrue(true);
    }
}
