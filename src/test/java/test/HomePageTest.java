package test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import base.BaseTest;

/**
 * HomePageTest contains test cases for the RNS Equity homepage.
 * It extends BaseTest to inherit WebDriver setup/teardown and other utilities.
 */
public class HomePageTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(HomePageTest.class.getName());

    /**
     * Test case to verify if the RNS Equity homepage loads correctly and its logo is displayed.
     */
    @Test(priority = 1, description = "Verify RNS Equity homepage loads and logo is displayed")
    public void verifyHomePageLoadAndLogo() {
        log.info("Starting verifyHomePageLoadAndLogo test.");
        HomePage homePage = new HomePage(getDriver()); // Get the WebDriver instance from BaseTest
        Assert.assertTrue(homePage.isLogoDisplayed(), "RNS Equity logo should be displayed on the homepage.");
        Assert.assertTrue(homePage.verifyPageTitle("RNS Equity"), "Page title should contain 'RNS Equity'.");
        log.info("verifyHomePageLoadAndLogo test completed successfully.");
    }

    /**
     * Test case to verify navigation to the login page from the homepage.
     */
    @Test(priority = 2, description = "Verify navigation to Login page from Homepage")
    public void navigateToLoginPage() {
        log.info("Starting navigateToLoginPage test.");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginLink(); // This will navigate to the login page
        // You can add an assertion here to verify if the URL changed or a login element is present
        Assert.assertTrue(getDriver().getCurrentUrl().contains("login"), "URL should contain 'login' after clicking login link.");
        log.info("navigateToLoginPage test completed successfully.");
    }

    /**
     * Test case to verify the search functionality on the homepage.
     * NOTE: This assumes a search box exists and performs a basic search.
     * You might need to add assertions for search results.
     */
    @Test(priority = 3, description = "Verify search functionality on Homepage")
    public void verifySearchFunctionality() {
        log.info("Starting verifySearchFunctionality test.");
        HomePage homePage = new HomePage(getDriver());
        String searchTerm = "investments";
        homePage.enterSearchText(searchTerm);
        homePage.clickSearchButton();
        // Add assertions here to verify search results, e.g.,
        // Assert.assertTrue(getDriver().getCurrentUrl().contains("search=" + searchTerm), "Search URL should contain the search term.");
        // Or check for presence of search results elements
        log.info("verifySearchFunctionality test completed.");
    }
}
