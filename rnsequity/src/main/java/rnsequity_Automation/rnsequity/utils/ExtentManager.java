package rnsequity_Automation.rnsequity.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	  static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	        }
	        return extent;
	    }
	}