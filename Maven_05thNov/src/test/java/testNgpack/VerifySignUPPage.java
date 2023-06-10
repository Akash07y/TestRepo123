package testNgpack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersSetup.Base;
import page.ForgotPasswordPage;
import page.LoginPage;

public class VerifySignUPPage extends Base  {
	
	WebDriver driver ;
	LoginPage loginPage  ;
	ForgotPasswordPage forgotPasswordPage ;
	
	@Parameters ("browser")
	@BeforeTest
	public void openBrowser(String browserName) {
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
		
		if(browserName.equals("Safari"))
		{
			driver = openOperaBrowser();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS) ;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS) ;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS) ;
	}
	
	@BeforeClass
	public void creatPOMObject() {
		loginPage = new LoginPage(driver) ;
		forgotPasswordPage= new ForgotPasswordPage(driver);
	}
	
	
	@BeforeMethod
	public void goToForgotPasswordPage() {
		System.out.println("Before Method");
		driver.get("https://www.facebook.com/");
		loginPage.clickForgotPassword();
	}
	
	@Test
	public void verifySignUpFunctonality() {
		System.out.println("Test 1");
		//ForgotPasswordPage forgotPasswordPage= new ForgotPasswordPage(driver);
		String actualMsg = forgotPasswordPage.getTextMessage();
		String expectedMsg = "Please enter your email address or mobile number to search for your account.";
		if(actualMsg.equals(expectedMsg))
		{
			System.out.println("PASSED");
		}
		else
		{
			System.out.println("FAILED");
		}
	}
	
	@Test
	public void verifyErrorMessageONSignUoPage() {
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
	public void verifySignupHiddenElement() {
		//ForgotPasswordPage forgotPasswordPage= new ForgotPasswordPage(driver);
		forgotPasswordPage.sendEmailOrPhone();
		forgotPasswordPage.clickOnSearchButton();
		String expectedErrorTitle = "sdgfgdfhbg";
		String expectedErrorMessage = "sdvfgdfhgrh";
		String atualErrorTitle = forgotPasswordPage.getErrorTitle();
		String actualErrorMessge = forgotPasswordPage.getErrorMessage();
		
		
		Assert.assertEquals(atualErrorTitle, expectedErrorTitle);
		
	}
	
	@AfterMethod
	public void logOutFromApplication() {
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
