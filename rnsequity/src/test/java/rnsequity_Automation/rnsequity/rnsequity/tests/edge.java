package rnsequity_Automation.rnsequity.rnsequity.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import Base.BaseTest;
import rnsequity_Automation.rnsequity.utils.ExtentManager;

public class edge extends BaseTest {
	public static ExtentManager extent;
	public static WebDriver driver;
	// public static WebDriverWait wait;
	// public ExtentReports extent;
	// ExtentTest test;


	@Test
	  public void setUp() {
	        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	         driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        
	        driver.manage().window().maximize();
	        driver.get("https://rnsequity.com");

}
}

