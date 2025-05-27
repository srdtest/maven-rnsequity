package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

/**
 * BaseTest class provides the foundational setup and teardown for all test classes.
 * It handles WebDriver initialization, configuration loading, screenshot capture,
 * and integrates with ExtentReports.
 */
public class BaseTest {

    // ThreadLocal to manage WebDriver instances for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    // Logger for logging test execution details
    public static final Logger log = LogManager.getLogger(BaseTest.class.getName());
    // Properties object to load configuration from data.properties
    public Properties prop;
    // ExtentReports instance
    public ExtentReports extent;
    // ExtentTest instance for the current test method
    public ExtentTest test;

    /**
     * Constructor to load properties file.
     */
    public BaseTest() {
        prop = new Properties();
        try {
            // Load data.properties from src/main/resources
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
            prop.load(fis);
        } catch (IOException e) {
            log.error("Error loading data.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initializes the WebDriver based on the browser specified in data.properties.
     * Uses WebDriverManager to handle driver binaries automatically.
     *
     * @param browser The browser name (e.g., "chrome", "firefox", "edge").
     * @throws IOException If there's an issue with file operations.
     */
    public WebDriver initializeDriver(String browser) throws IOException {
        log.info("Initializing WebDriver for browser: " + browser);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		} else {
			log.error("Unsupported browser: " + browser);
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
        getDriver().manage().window().maximize(); // Maximize browser window
        log.info("WebDriver initialized successfully.");
        return getDriver();
    }

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Captures a screenshot and saves it to the specified path.
     *
     * @param testName The name of the test for naming the screenshot file.
     * @return The absolute path of the saved screenshot.
     * @throws IOException If there's an issue saving the screenshot.
     */
    public String getScreenshotPath(String testName) throws IOException {
        log.info("Capturing screenshot for test: " + testName);
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        log.info("Screenshot saved to: " + destinationFile);
        return destinationFile;
    }

    /**
     * Runs before each test method.
     * Initializes the WebDriver and navigates to the base URL.
     *
     * @param method The test method being executed.
     * @throws IOException If an I/O error occurs.
     */
    @BeforeMethod
    @Parameters("browser") // Expecting browser parameter from testng.xml
    public void setup(@Optional("chrome") String browser, Method method) throws IOException {
        log.info("Setting up test: " + method.getName());
        initializeDriver(browser);
        getDriver().get(prop.getProperty("baseUrl"));
        log.info("Navigated to URL: " + prop.getProperty("baseUrl"));
    }

    /**
     * Runs after each test method.
     * Quits the WebDriver and logs test status to ExtentReports.
     *
     * @param result The TestNG result object for the test method.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        log.info("Tearing down after test: " + result.getMethod().getMethodName());
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // Remove driver from ThreadLocal
            log.info("WebDriver quit successfully.");
        }
    }
}
	