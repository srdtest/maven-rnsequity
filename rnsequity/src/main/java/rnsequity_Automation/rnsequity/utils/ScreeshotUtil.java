package rnsequity_Automation.rnsequity.utils;

import java.io.File;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreeshotUtil {
	 public static  void captureScreenshot(WebDriver driver, String name) throws Exception {
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        Files.copy(src.toPath(), new File("screenshots/" + name + "_" + timestamp +".png").toPath());
	    }
	}