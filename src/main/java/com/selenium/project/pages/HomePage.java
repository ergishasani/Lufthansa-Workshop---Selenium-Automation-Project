package com.selenium.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(linkText = "Create an Account")
    private WebElement createAccountLink;

    @FindBy(linkText = "Sign In")
    private WebElement signInLink;

    @FindBy(xpath = "//span[text()='Welcome, Ergis Hasani!']") // Locate by text
    private WebElement welcomeMessage;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickCreateAccountLink() {
        createAccountLink.click();
    }

    public void clickSignInLink() {
        signInLink.click();
    }

    public boolean isUserLoggedIn() {
        return welcomeMessage.isDisplayed(); // Checks if the welcome message is displayed
    }
}
