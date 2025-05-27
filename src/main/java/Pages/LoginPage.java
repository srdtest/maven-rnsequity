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
 * LoginPage represents the RNS Equity login page.
 * It contains WebElements and methods specific to interactions on the login page.
 */
public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(LoginPage.class.getName());

    // Locators for elements on the Login Page
    // NOTE: These locators are examples. You will need to inspect rnsequity.com
    // and update them with actual, robust locators.
    private By usernameField = By.id("username"); // Example username field locator
    private By passwordField = By.id("password"); // Example password field locator
    private By loginButton = By.xpath("//button[contains(text(),'Login')]"); // Example login button locator
    private By errorMessage = By.cssSelector(".error-message"); // Example error message locator

    /**
     * Constructor for LoginPage.
     * Initializes WebDriver and WebDriverWait, and PageFactory elements.
     *
     * @param driver The WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Wait for up to 15 seconds
        PageFactory.initElements(driver, this); // Initialize WebElements
        log.info("LoginPage initialized.");
    }

    /**
     * Enters the username into the username field.
     *
     * @param username The username to enter.
     */
    public void enterUsername(String username) {
        log.info("Entering username: " + username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    /**
     * Enters the password into the password field.
     *
     * @param password The password to enter.
     */
    public void enterPassword(String password) {
        log.info("Entering password.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        log.info("Clicking login button.");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Performs a login action with the given username and password.
     *
     * @param username The username.
     * @param password The password.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        log.info("Attempted login with username: " + username);
    }

    /**
     * Retrieves the error message displayed on the login page.
     *
     * @return The text of the error message, or null if not found.
     */
    public String getErrorMessage() {
        try {
            log.info("Attempting to get error message.");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            log.warn("No error message found or element not visible: " + e.getMessage());
            return null;
        }
    }
}
