package com.selenium.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPage extends BasePage {
    @FindBy(css = ".action.towishlist")
    private WebElement addToWishListButton;

    @FindBy(css = ".message-success")
    private WebElement successMessage;

    @FindBy(css = ".counter-number")
    private WebElement wishListCounter;

    public WishListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addItemToWishList() {
        addToWishListButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public String getWishListCounterText() {
        return wishListCounter.getText();
    }
}