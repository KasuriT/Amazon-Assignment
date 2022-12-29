package ParallelTest;

import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;

import MiscFunctions.Constants;
import MiscFunctions.DB_Config;
import MiscFunctions.ExtentVariables;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static ThreadLocal<ChromeDriver> driver = new ThreadLocal<>();
	
	
	@BeforeClass
	public static void config() {
		WebDriverManager.chromedriver().setup();
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
		prefs.put("download.prompt_for_download", false);
		prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-infobars");
		options.addArguments("disable-popup-blocking");

		driver.set(new ChromeDriver(options));
		getDriver().manage().window().maximize();
	//	getDriver().get(Constants.url_login);
		getDriver().get("https://www.espncricinfo.com/live-cricket-score");
		
		ExtentVariables.spark.config().setDocumentTitle("Ancera Test Report");
		ExtentVariables.spark.config().setTheme(Theme.DARK);
		ExtentVariables.extent = new ExtentReports();
		ExtentVariables.extent.attachReporter(ExtentVariables.spark);
		
	}
	
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	
	public static void saveResult(int testResult, Exception e) throws IOException {		
		ITestResult objResult = Reporter.getCurrentTestResult();
		if (testResult == ITestResult.SUCCESS) {
			objResult.setStatus(ITestResult.SUCCESS);
			ExtentVariables.test.log(Status.PASS, "Test Case Passed");
		}
		else if (testResult == ITestResult.FAILURE) {
			objResult.setStatus(ITestResult.FAILURE);
			objResult.setThrowable(e);
			ExtentVariables.test.log(Status.FAIL, "Test Case Failed"); 
			ExtentVariables.test.log(Status.FAIL, "Issue -> " + e); 
			ExtentVariables.test.addScreenCaptureFromPath(get_Screenshot());
		} else if (testResult == ITestResult.SKIP) {
			ExtentVariables.test.log(Status.SKIP, "Test Case Skipped ");
			Markup m = MarkupHelper.createLabel("Skipped", ExtentColor.YELLOW);
			ExtentVariables.test.skip(m);
			ExtentVariables.test.addScreenCaptureFromPath(get_Screenshot());
		}
	}
	

	public static String get_Screenshot() throws IOException {
		String dateName = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) BaseTest.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = links.ReportFilePath + links.ReportScreenshotPath +dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return "." + links.ReportScreenshotPath + dateName+".png";
	}
	

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
		extent.flush();
		DB_Config.tearDown();
	}
}