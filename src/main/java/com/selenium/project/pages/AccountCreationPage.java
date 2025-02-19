package com.selenium.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationPage extends BasePage {
    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "email_address")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordField;

    @FindBy(css = "button[title='Create an Account']")
    private WebElement createAccountButton;

    public AccountCreationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String firstName, String lastName, String email, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        // Add logic to check for success message
        return true;
    }

    public void signOut() {
        // Add logic to sign out
    }
}