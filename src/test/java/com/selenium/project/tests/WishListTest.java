package com.selenium.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WishListTest extends BaseTest {
    @Test
    public void testWishList() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Navigate to Women > Tops > Jackets
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tops"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Jackets"))).click();

        // Remove price filter
        driver.findElement(By.cssSelector("a[title='Remove filter']")).click();

        // Add first two items to wishlist
        List<WebElement> addToWishListButtons = driver.findElements(By.cssSelector(".action.towishlist"));
        for (int i = 0; i < 2; i++) {
            addToWishListButtons.get(i).click();
            Assert.assertTrue(driver.findElement(By.cssSelector(".message-success")).isDisplayed());
        }

        // Verify wishlist counter
        String wishListCounterText = driver.findElement(By.cssSelector(".counter-number")).getText();
        Assert.assertEquals(wishListCounterText, "2");
    }
}