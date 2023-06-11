package testNgpack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utils.Utility;
import browsersSetup.Base;
import page.ForgotPasswordPage;
import page.LoginPage;

public class VerifyForgotPasswordPage extends Base{

	WebDriver driver ;
	LoginPage loginPage  ;
	ForgotPasswordPage forgotPasswordPage ;
	String testID ;
	
	static ExtentTest test;
	static ExtentHtmlReporter reporter;
	
	@Parameters ("browser")
	@BeforeTest

	public void open1234Browser(String browserName) 

	public void openBrowser(String browserName) {
		
		reporter = new ExtentHtmlReporter("test-output/ExtendReport/Extent.html");
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
		
		System.out.println("Before Test");
		
		if(browserName.equals("Chrome"))
		{
			driver = openChromeBrowser();
		}
		
		if(browserName.equals("Firefox"))
		{
			driver = openFirefoxBrowser();
		}
		
		if(browserName.equals("Opera"))
		{
			driver = openOperaBrowser();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS) ;	
	}
	
	@BeforeClass
	public void creatPOMObject() {
		loginPage = new LoginPage(driver) ;
		forgotPasswordPage= new ForgotPasswordPage(driver);
		
		//String data = Utility.getExcelData("TestData", 2, 0);
		loginPage.sendUserName(Utility.getExcelData("TestData", 2, 0));
		
		//data = Utility.getExcelData("TestData", 2, 1);
		loginPage.sendPassword(Utility.getExcelData("TestData", 2, 1));
	}
	
	
	@BeforeMethod
	public void goToForgotPasswordPage() {
		System.out.println("Before Method");
		driver.get("https://www.facebook.com/");
		loginPage.clickForgotPassword();
	}
	
	@Test
	public void verifyMessageOnForgotPasswordPage() {
		testID = "T101" ;
		System.out.println("Test 1");
		//ForgotPasswordPage forgotPasswordPage= new ForgotPasswordPage(driver);
		String actualMsg = forgotPasswordPage.getTextMessage();
		String expectedMsg = "Please enter your email address or mobile number to search for your account.";
	}
	
	@Test
	public void verifyCancelButtonOnForgotPasswordPage() {
		testID = "T201" ;
		System.out.println("Test 2");
		//ForgotPasswordPage forgotPasswordPage= new ForgotPasswordPage(driver);
		forgotPasswordPage.clickOnCancelButton();
		String expectedURL = "https://www.facebook.com/login.php";
		String expectedTitle = "Log in to Facebook";
		String atualURL = driver.getCurrentUrl();
		String actualTitle = driver.getTitle();
		if(expectedURL.equals(atualURL)  &&  expectedTitle.equals(actualTitle))
		{
			System.out.println("PASSED");
		}
		else
		{
			System.out.println("FAILED");
		}
	}
	
	@Test
	public void verifySearchFunctionOnForgotPasswordPage() {
		testID = "T301" ;
		//ForgotPasswordPage forgotPasswordPage= new ForgotPasswordPage(driver);
		forgotPasswordPage.sendEmailOrPhone();
		forgotPasswordPage.clickOnSearchButton();
		String expectedErrorTitle = "sdgfgdfhbg";
		String expectedErrorMessage = "sdvfgdfhgrh";
		String atualErrorTitle = forgotPasswordPage.getErrorTitle();
		String actualErrorMessge = forgotPasswordPage.getErrorMessage();
		Assert.assertEquals(atualErrorTitle, expectedErrorTitle);
	}
	
	// TestNG Listeners
	// Used to maintain the statuses of test methods 
	// 1. IReporter
	// 2. ITestResult
	// 3. ITestListener

	@AfterMethod
	public void logOutFromApplication(ITestResult result) {
		if(ITestResult.FAILURE == result.getStatus())
		{
			Utility.captureScrren(driver, testID);
		}
	
		
		System.out.println("After Method");
		// logout
	}
	
	@AfterClass
	public void clearObjects() {
		loginPage = null ;
		forgotPasswordPage= null ;
	}
	
	@AfterTest
	public void closedBrowser() {
		System.out.println("After Class");
		driver.quit();
		driver = null ;
		System.gc(); // Garbeg collection
	}
	
}
