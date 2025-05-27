package test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import base.BaseTest;

/**
 * LoginTest contains test cases for the RNS Equity login functionality.
 * It extends BaseTest to inherit WebDriver setup/teardown and other utilities.
 */
public class LoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTest.class.getName());

    /**
     * Test case for verifying invalid login credentials.
     * This test assumes that an error message is displayed for invalid credentials.
     */
    @Test(priority = 1, description = "Verify invalid login with incorrect credentials")
    public void verifyInvalidLogin() {
        log.info("Starting verifyInvalidLogin test.");
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginLink(); // Navigate to login page

        loginPage.login("invaliduser@example.com", "wrongpassword"); // Attempt login with invalid credentials

        // Assert that an error message is displayed
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message should be displayed for invalid login.");
        Assert.assertTrue(errorMessage.contains("Invalid credentials") || errorMessage.contains("incorrect"),
                "Error message should indicate invalid credentials.");
        log.info("verifyInvalidLogin test completed successfully.");
    }

    /**
     * Test case for verifying login with empty credentials.
     * This test assumes that an error message is displayed for empty credentials.
     */
    @Test(priority = 2, description = "Verify login with empty credentials")
    public void verifyLoginWithEmptyCredentials() {
        log.info("Starting verifyLoginWithEmptyCredentials test.");
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginLink(); // Navigate to login page

        loginPage.login("", ""); // Attempt login with empty credentials

        // Assert that an error message is displayed
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message should be displayed for empty credentials.");
        Assert.assertTrue(errorMessage.contains("required") || errorMessage.contains("empty"),
                "Error message should indicate missing credentials.");
        log.info("verifyLoginWithEmptyCredentials test completed successfully.");
    }

    // NOTE: For a successful login test, you would need valid credentials.
    // For security reasons, do not hardcode valid credentials.
    // Instead, load them from a secure source or use test data management.
    // Example of a successful login test (commented out):
    /*
    @Test(priority = 3, description = "Verify successful login (requires valid credentials)")
    public void verifySuccessfulLogin() {
        log.info("Starting verifySuccessfulLogin test.");
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginLink();

        // Replace with actual valid credentials from a secure source
        loginPage.login(prop.getProperty("validUsername"), prop.getProperty("validPassword"));

        // Assert successful login (e.g., dashboard element, URL change)
        // Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"), "Should navigate to dashboard after successful login.");
        // Assert.assertTrue(new DashboardPage(getDriver()).isDashboardDisplayed(), "Dashboard should be displayed.");
        log.info("verifySuccessfulLogin test completed successfully.");
    }
    */
}
