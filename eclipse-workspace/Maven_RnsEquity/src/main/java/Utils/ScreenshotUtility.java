package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = screenshotName + "_" + dateTime + ".png";
        String path = "test-output/screenshots/" + fileName;
        File destFile = new File(path);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile.getAbsolutePath();
    }
}