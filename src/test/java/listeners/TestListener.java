package listeners;

import com.aventstack.extentreports.ExtentTest;

import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("test");
        String screenshotPath = captureScreenshot(getDriver(), result.getName());
        test.fail(result.getThrowable());
        try {
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}