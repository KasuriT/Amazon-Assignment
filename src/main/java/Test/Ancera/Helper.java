package Test.Ancera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.get(Constants.url_login);
		
	
		Test_Variables.spark.config().setDocumentTitle("Ancera Test Report"); // Tile of report
		Test_Variables.spark.config().setTheme(Theme.DARK);
		Test_Variables.extent = new ExtentReports();
		Test_Variables.extent.attachReporter(Test_Variables.spark);

	}	
	public static void saveResult(ITestResult result, String reportPath) throws IOException {
		System.out.println(Reporter.getCurrentTestResult());
		if (result.getStatus() == ITestResult.FAILURE) {
			Test_Variables.test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			Test_Variables.test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
	//		String screenshotPath = getScreenshot(result.getName(), reportPath);
			Test_Variables.test.addScreenCaptureFromPath(getScreenshot(result.getName(), reportPath));// adding screen shot
		} 
		else if (result.getStatus() == ITestResult.SKIP) {
			Test_Variables.test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			Test_Variables.test.log(Status.PASS, "Test Case Passed");
			//Test_Variables.test.addScreenCaptureFromPath(getScreenshot(result.getName(), reportPath));// adding screen shot
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
			Test_Variables.test.log(Status.FAIL, "TEST CASE Failed"); // to add name in extent report
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
