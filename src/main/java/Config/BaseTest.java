package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import MiscFunctions.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseTest {
	protected BaseTest() {
		
	}
	
//	@BeforeSuite
//	public void beforeSuite() {
//		ExtentReport.initReports(this.getClass().getName());
//	}
//	
//	@AfterSuite
//	public void afterSuite() {
//		ExtentReport.flushReports();
//	}
//	
	@BeforeMethod 
	public void setup() {
		System.out.println(1);
		Driver.initDriver();
	}
	
	
	
	
	@AfterMethod
	protected void tearDown() {
		Driver.quitDriver();
	}
}
