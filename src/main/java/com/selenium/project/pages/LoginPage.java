package com.selenium.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "pass")
    private WebElement passwordField;

    @FindBy(id = "send2")
    private WebElement loginButton;

    @FindBy(css = ".message-error") // Locator for error messages
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    // Method to get error message text
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}