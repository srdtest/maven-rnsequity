package rnsequity_Automation.rnsequity.rnsequity.tests;

import rnsequity_Automation.rnsequity.base.BaseTest;
import rnsequity_Automation.rnsequity.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class LoginTest extends BaseTest {
	ExtentTest test;

	
	@Test
	public void actitle() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		// Assuming you have a method to log in
		test = extent.createTest("Login Page Test");
		String actitle = loginPage.acqtitle();
		System.out.println(actitle);
		test.pass("Login Page Test Passed");

	}

	
	
	@Test(description = "verifying the title of the blog page")
	public void blogtitle() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		// Assuming you have a method to log in
		test = extent.createTest("Login Page Test");
		String blogtitle = loginPage.blogtitle();
		System.out.println(blogtitle);
		test.pass("blog titel Test Passed");

	}
	
	@Test
	public void hometitle() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		// Assuming you have a method to log in
		test = extent.createTest("Login Page Test");
		String hometitle = loginPage.hometitle();
		System.out.println(hometitle);
		test.pass("Homepage title Test Passed");

	}

	@Test
	public void invtitle() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		// Assuming you have a method to log in
		test = extent.createTest("Login Page Test");
		String investortitle = loginPage.investortitle();
		System.out.println(investortitle);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(investortitle, "Investor", "Investor title is not matching");
		
		test.pass("Inverstor ttilte Test Passed");

	}

}