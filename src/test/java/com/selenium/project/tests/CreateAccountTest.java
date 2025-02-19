package com.selenium.project.tests;

import com.selenium.project.pages.AccountCreationPage;
import com.selenium.project.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTest extends BaseTest {
    @Test
    public void testCreateAccount() {
        driver.get("https://magento.softwaretestingboard.com/");

        HomePage homePage = new HomePage(driver);
        homePage.clickCreateAccountLink();

        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

        accountCreationPage.fillForm("John", "Doe", "john.doe@example.com", "Password123!");
        accountCreationPage.clickCreateAccountButton();

        Assert.assertTrue(accountCreationPage.isSuccessMessageDisplayed());
        accountCreationPage.signOut();
    }
}