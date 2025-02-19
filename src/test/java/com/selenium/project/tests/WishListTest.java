package com.selenium.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Jackets"))).click();

        // Remove price filter
        driver.findElement(By.cssSelector("a[title='Remove filter']")).click();

        // Initialize Actions class for hover actions
        Actions actions = new Actions(driver);

        // Add first two items to wishlist
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item-info"));
        for (int i = 0; i < 2; i++) {
            WebElement product = products.get(i);

            // Hover over the product to reveal the wishlist button
            actions.moveToElement(product).perform();

            // Find and click the "Add to Wishlist" button
            WebElement addToWishListButton = product.findElement(By.cssSelector(".action.towishlist"));
            addToWishListButton.click();

            // Verify success message appears after adding to wishlist
            Assert.assertTrue(driver.findElement(By.cssSelector(".message-success")).isDisplayed());
        }

        // Verify wishlist counter
        String wishListCounterText = driver.findElement(By.cssSelector(".counter-number")).getText();
        Assert.assertEquals(wishListCounterText, "2");

        // Navigate to Profile and check the correct number of items is displayed (My Wish List (2 items))
        driver.findElement(By.cssSelector(".header .account")).click();  // Click user profile link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Wish List"))).click();

        // Verify the correct number of items in the wishlist
        WebElement wishlistItemCount = driver.findElement(By.cssSelector(".wishlist-items-count"));
        Assert.assertTrue(wishlistItemCount.getText().contains("2 items"));
    }
}
