package rnsequity_Automation.rnsequity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rnsequity_Automation.rnsequity.base.BaseTest;
import rnsequity_Automation.rnsequity.utils.ScreeshotUtil;

public class LoginPage extends BaseTest {
    private WebDriver driver;

    @FindBy(xpath = "//*[text()='Home']")
    private WebElement Home;
    
    @FindBy(xpath = "//*[text()='About Us']")
    private WebElement Aboutus;
    
    @FindBy(xpath = "//*[text()='Blog']")
    private WebElement Blog;
    
    @FindBy(xpath = "//*[text()='Acquisitions']")
    private WebElement Acquisitions;
    
    @FindBy(xpath = "//*[text()='Become a Investor']")
    private WebElement becomeinvestor;
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "loginBtn")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }
    public String gettitle() {
    	return driver.getTitle();
    }
    
    public String hometitle() throws Exception {
    	Home.click();
    	ScreeshotUtil.captureScreenshot(driver, "HomePage");
    	return gettitle();
    }
    
    public String blogtitle() throws Exception {
    	waitForClickability(Blog);
    	Blog.click();
    	ScreeshotUtil.captureScreenshot(driver, "BlogPage");
    	return gettitle();
    	
    }
    public String acqtitle() throws Exception {
    	waitForClickability(Acquisitions);
    	Acquisitions.click();
    	ScreeshotUtil.captureScreenshot(driver, "acquisistionsPage");
    	return gettitle();
    }
    
    public String investortitle() throws Exception {
    	waitForClickability(becomeinvestor);
    	becomeinvestor.click();
    	ScreeshotUtil.captureScreenshot(driver, "Become investors Page");
    	return gettitle();
    }
}