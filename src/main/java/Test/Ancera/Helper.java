package Test.Ancera;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Helper {

	public static WebDriver driver; 
	public static String projectPath;

	@BeforeSuite
	public static void config() throws MalformedURLException {

		projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/CDriver/chromedriver.exe");		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
		prefs.put("download.prompt_for_download", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-infobars");
		options.addArguments("disable-popup-blocking");
//		options.addArguments("--headless");
//		options.addArguments("window-size=1920,1080"); 
	
		//	DesiredCapabilities cap = new DesiredCapabilities();
		//	cap.setBrowserName(BrowserType.CHROME);
		//	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		
		driver = new ChromeDriver(options); 
		driver.manage().window().maximize();
		driver.get(Constants.url_login);

		Test_Variables.spark.config().setDocumentTitle("Ancera Test Report");
		Test_Variables.spark.config().setTheme(Theme.DARK);
		Test_Variables.extent = new ExtentReports();
		Test_Variables.extent.attachReporter(Test_Variables.spark);		
	}
		

	public static void saveResultNew(int testResult, String reportPath, Exception e) throws IOException {
		ITestResult objResult = Reporter.getCurrentTestResult();
		if (testResult == ITestResult.SUCCESS) {
			objResult.setStatus(ITestResult.SUCCESS);
			Test_Variables.test.log(Status.PASS, "Test Case Passed");
		}
		else if (testResult == ITestResult.FAILURE) {
			objResult.setStatus(ITestResult.FAILURE);
			objResult.setThrowable(e);
			Test_Variables.test.log(Status.FAIL, "Test Case Failed"); 
			Test_Variables.test.log(Status.FAIL, "Issue -> " + e); 
			Test_Variables.test.addScreenCaptureFromPath(getScreenshot(objResult.getName(), reportPath));
		} else if (testResult == ITestResult.SKIP) {
			Test_Variables.test.log(Status.SKIP, "Test Case Skipped ");
			Test_Variables.test.addScreenCaptureFromPath(getScreenshot(objResult.getName(), reportPath));
		}
	}
	
	
	public static String getScreenshot(String screenshotName, String reportPath) throws IOException {
		String dateName = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) Helper.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = Constants.ReportFilePath + reportPath +dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return "." + reportPath + dateName+".png";
	}
}
