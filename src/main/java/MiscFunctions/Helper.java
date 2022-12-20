package MiscFunctions;

import static MiscFunctions.ExtentVariables.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;

import PageObjects.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Helper {

	public static WebDriver driver; 
	public static String projectPath;

	@BeforeSuite
	public static void config() throws MalformedURLException {

		projectPath = System.getProperty("user.dir");	
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
		//	options.addArguments("--headless");
		//	options.addArguments("window-size=1920,1080"); 


		/*
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		 */

		driver = new ChromeDriver(options); 
		driver.manage().window().maximize();
		driver.get(Constants.url_login);
		//	driver.get(Constants.url);

		ExtentVariables.spark.config().setDocumentTitle("Ancera Test Report");
		ExtentVariables.spark.config().setTheme(Theme.DARK);
		ExtentVariables.extent = new ExtentReports();
		ExtentVariables.extent.attachReporter(ExtentVariables.spark);		
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
		TakesScreenshot ts = (TakesScreenshot) Helper.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = Constants.ReportFilePath + Constants.ReportScreenshotPath +dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return "." + Constants.ReportScreenshotPath + dateName+".png";
	}
	


	public static void click(By locator) {
		waitElementClickable(locator);
		driver.findElement(locator).click();
		waitElementInvisible(BasePage.loading_cursor);
		ExtentVariables.test.log(Status.INFO, "Clicking on "+locator);
	}

	public static void type(By locator, String text) {
		waitElementClickable(locator);
		driver.findElement(locator).sendKeys(text);
		waitElementInvisible(BasePage.loading_cursor);
		ExtentVariables.test.log(Status.INFO, "Typing "+text+" in "+locator);
	}

	public static void enterKey(By locator) {
		waitElementClickable(locator);
		driver.findElement(locator).sendKeys(Keys.ENTER);
		ExtentVariables.test.log(Status.INFO, "Press enter key in "+locator);
	}

	public static String getText(By locator) {
		waitElementVisible(locator);
		return driver.findElement(locator).getText();
	}

	public static String getAttribute(By locator) {
		return driver.findElement(locator).getAttribute("value");
	}

	public static void clear(By locator) {
		waitElementClickable(locator);
		driver.findElement(locator).clear();
	}

	public static int size(By locator) {
		int elementSize = driver.findElements(locator).size();
		return elementSize;
	}

	public static void waitElementVisible(By locator) {
		Constants.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitElementInvisible(String locator) {
		Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
	}

	public static void waitElementClickable(By locator) {
		Constants.wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void scroll(By locator) {
		WebElement scroll = driver.findElement(locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
	}
	
	public static void hover(By locator) {
		WebElement hover = driver.findElement(locator);
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
	}
	
	public static void getScreenshot() throws IOException {
		test.addScreenCaptureFromPath(get_Screenshot());
	}

}
