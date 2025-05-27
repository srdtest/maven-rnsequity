package Pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * HomePage represents the RNS Equity homepage.
 * It contains WebElements and methods specific to interactions on the homepage.
 */
public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(HomePage.class.getName());

    // Locators for elements on the Home Page
    // NOTE: These locators are examples. You will need to inspect rnsequity.com
    // and update them with actual, robust locators (e.g., By.id, By.cssSelector, By.xpath)
    private By logo = By.cssSelector("img[alt='RNS Equity Logo']"); // Example logo locator
    private By loginLink = By.xpath("//a[contains(text(),'Login')]"); // Example login link locator
    private By searchInput = By.id("search-box"); // Example search input
    private By searchButton = By.xpath("//button[contains(text(),'Search')]"); // Example search button

    /**
     * Constructor for HomePage.
     * Initializes WebDriver and WebDriverWait, and PageFactory elements.
     *
     * @param driver The WebDriver instance.
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Wait for up to 15 seconds
        PageFactory.initElements(driver, this); // Initialize WebElements
        log.info("HomePage initialized.");
    }

    /**
     * Checks if the RNS Equity logo is displayed on the homepage.
     *
     * @return true if the logo is displayed, false otherwise.
     */
    public boolean isLogoDisplayed() {
        try {
            log.info("Checking if logo is displayed.");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
        } catch (Exception e) {
            log.error("Logo not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Clicks on the Login link.
     *
     * @return A new LoginPage object.
     */
    public LoginPage clickLoginLink() {
        log.info("Clicking on Login link.");
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }

    /**
     * Enters text into the search input field.
     *
     * @param searchText The text to enter.
     */
    public void enterSearchText(String searchText) {
        log.info("Entering search text: " + searchText);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys(searchText);
    }

    /**
     * Clicks the search button.
     */
    public void clickSearchButton() {
        log.info("Clicking search button.");
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    /**
     * Verifies if the current page title contains a specific text.
     *
     * @param titlePart The part of the title to check for.
     * @return true if the title contains the text, false otherwise.
     */
    public boolean verifyPageTitle(String titlePart) {
        log.info("Verifying page title contains: " + titlePart);
        return driver.getTitle().contains(titlePart);
    }
}
