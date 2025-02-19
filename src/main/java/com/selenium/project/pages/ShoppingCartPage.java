package com.selenium.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage extends BasePage {
    @FindBy(css = ".action.tocart")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".message-success")
    private WebElement successMessage;

    @FindBy(css = ".action.viewcart")
    private WebElement viewCartLink;

    @FindBy(css = ".cart.item")
    private List<WebElement> cartItems;

    @FindBy(css = ".action.delete")
    private WebElement deleteItemButton;

    @FindBy(css = ".empty-cart-message")
    private WebElement emptyCartMessage;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addItemToCart(int index) {
        addToCartButtons.get(index).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public void viewCart() {
        viewCartLink.click();
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void deleteItem() {
        deleteItemButton.click();
    }

    public boolean isCartEmpty() {
        return emptyCartMessage.isDisplayed();
    }
}