package browsersSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Base {
	
	public static WebDriver openChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/browserFile/chromedriver.exe");
		WebDriver driver = new ChromeDriver() ;
		return driver ;
	}
	
	public static WebDriver openFirefoxBrowser() {
		System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\browserFile\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver() ;
		WebDriver driver = new FirefoxDriver() ;
		WebDriver driver = new FirefoxDriver() ;
		WebDriver driver = new FirefoxDriver() ;
		return driver ;
	}
	
	public static WebDriver openOperaBrowser() {
		System.setProperty("webdriver.opera.driver", "src\\test\\resources\\browserFile\\operadriver.exe");
		System.setProperty("webdriver.opera.driver", "src\\test\\resources\\browserFile\\operadriver.exe");
		System.setProperty("webdriver.opera.driver", "src\\test\\resources\\browserFile\\operadriver.exe");
		WebDriver driver = new OperaDriver() ;
		return driver ;
	}
	
	public static WebDriver openOperaBrowser() {
		System.setProperty("webdriver.opera.driver", "src\\test\\resources\\browserFile\\operadriver.exe");
		WebDriver driver = new OperaDriver() ;
		return driver ;
	}

}
