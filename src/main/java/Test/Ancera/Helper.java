package Test.Ancera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Helper {

	public static WebDriver driver; // to declare globally
	public static String projectPath;
	
	@BeforeSuite
	public static void config() {

	  	projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/CDriver/chromedriver.exe");
		
	
		
		driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions())); 
		driver.manage().window().maximize();
		driver.get(Constants.url_login);
		
	
		Test_Variables.spark.config().setDocumentTitle("Ancera Test Report");
		Test_Variables.spark.config().setTheme(Theme.DARK);
		Test_Variables.extent = new ExtentReports();
		Test_Variables.extent.attachReporter(Test_Variables.spark);
		
			
//		String downloadFilepath = "D:\\a";
//		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", downloadFilepath);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", chromePrefs);
//		DesiredCapabilities cap = DesiredCapabilities.chrome();
//		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		cap.setCapability(ChromeOptions.CAPABILITY, options);
	//	@SuppressWarnings("deprecation")
	//	WebDriver driver = new ChromeDriver(cap);
		
	}
	

	
	
	public static void saveResult(ITestResult result, String reportPath) throws IOException {
		System.out.println(Reporter.getCurrentTestResult());
		if (result.getStatus() == ITestResult.FAILURE) {
			Test_Variables.test.log(Status.FAIL, "Test Case Failed is " + result.getName());
			Test_Variables.test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
			Test_Variables.test.addScreenCaptureFromPath(getScreenshot(result.getName(), reportPath));
		} 
		else if (result.getStatus() == ITestResult.SKIP) {
			Test_Variables.test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			Test_Variables.test.log(Status.PASS, "Test Case Passed");
		}
	}
	
	public static void saveResultNew(int testResult, String reportPath, Exception e) throws IOException {
		ITestResult objResult = Reporter.getCurrentTestResult();
		if (testResult == ITestResult.SUCCESS) {
			objResult.setStatus(ITestResult.SUCCESS);
			Test_Variables.test.log(Status.PASS, "Test Case Passed");
			//Test_Variables.test.addScreenCaptureFromPath(getScreenshot(result.getName(), reportPath));// adding screen shot
		}
		else if (testResult == ITestResult.FAILURE) {
			objResult.setStatus(ITestResult.FAILURE);
			objResult.setThrowable(e);
			Test_Variables.test.log(Status.FAIL, "Test Case Failed"); // to add name in extent report
			Test_Variables.test.log(Status.FAIL, "Issue -> " + e); // to add error/exception in extent report
	//		String screenshotPath = getScreenshot(result.getName(), reportPath);
			Test_Variables.test.addScreenCaptureFromPath(getScreenshot(objResult.getName(), reportPath));// adding screen shot
		} else if (testResult == ITestResult.SKIP) {
			Test_Variables.test.log(Status.SKIP, "Test Case SKIPPED IS " + objResult.getName());
		}
	}

	public static String getScreenshot(String screenshotName, String reportPath) throws IOException {
		String dateName = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) Helper.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
	//	String destination = System.getProperty("user.dir") + reportPath +dateName+".png";
		String destination = Constants.ReportFilePath + reportPath +dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return "." + reportPath + dateName+".png";

	}
	
	
	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.setHeadless(false);
		return options;
	}
	

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}


	@AfterSuite
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
