package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterNG implements ITestListener {

    // ExtentReports object to manage the reporting
    private ExtentReports extent;
    // ThreadLocal to manage ExtentTest instances for parallel execution
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * Called before the suite starts. Initializes ExtentReports.
     * Creates a new report file with a timestamp.
     *
     * @param context The TestNG context.
     */
    @Override
    public void onStart(ITestContext context) {
        // Define the report file path with a timestamp
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportFileName = "Test-Report-" + timestamp + ".html";
        String path = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReports" + File.separator + reportFileName;

        // Create the directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReports"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize ExtentSparkReporter for HTML report generation
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("RNS Equity Automation Results");
        reporter.config().setDocumentTitle("RNS Equity Test Automation Report");
        reporter.config().setTheme(Theme.DARK); // Set report theme to Dark

        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Your Name"); // Add system info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    /**
     * Called after the suite finishes. Flushes the ExtentReports.
     *
     * @param context The TestNG context.
     */
    @Override
    public void onFinish(ITestContext context) {
        // Flush the report to write all data to the HTML file
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * Called when a test method starts. Creates a new test entry in the report.
     *
     * @param result The TestNG result object for the test method.
     */
    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report for each test method
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Store the test instance in ThreadLocal
    }

    /**
     * Called when a test method passes. Logs the success status.
     *
     * @param result The TestNG result object for the test method.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test status as PASS
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    /**
     * Called when a test method fails. Logs the failure status and attaches a screenshot.
     *
     * @param result The TestNG result object for the test method.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        // Log test status as FAIL and the exception details
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());

        // Get the WebDriver instance from the BaseTest
        Object currentClass = result.getInstance();
        if (currentClass instanceof base.BaseTest) {
            base.BaseTest baseTest = (BaseTest) currentClass;
            try {
                // Capture screenshot and attach to the report
                String screenshotPath = baseTest.getScreenshotPath(result.getMethod().getMethodName());
                extentTest.get().addScreenCaptureFromPath(screenshotPath, "Screenshot of Failed Test");
            } catch (IOException e) {
                e.printStackTrace();
                extentTest.get().fail("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    /**
     * Called when a test method is skipped. Logs the skipped status.
     *
     * @param result The TestNG result object for the test method.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test status as SKIP
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Not implemented
    }
}
