package Test.Ancera.Reports;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.CoccidiaTimelineModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class CoccidiaTimeline {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/CoccidiaTimeline"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Coccidia Timeline Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	
	
	@Test (description="Test Case: Navigate to Coccidia Log Screen",enabled= true, priority = 1) 
	public void NavigateCoccidia() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-01: Verify user can navigate to Coccidia Timeline Screen", "This test case will verify user can navigate to Coccidia Timeline Screen");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
		Test_Variables.steps.createNode("1. Click on Coccidia Timeline");
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));

		Helper.driver.get(Constants.url_CoccidiaTimeline);
		Thread.sleep(2000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText();
		String expected = "Coccidia Timeline Internal";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("Coccidia Timeline report opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Coccidia Timeline report failed to open");
			
		}	
	}
	
	
	@Test (description="Test Case: Filter Test",enabled= true, priority = 2) 
	public void SearchFilter() throws InterruptedException, IOException {
		
		Test_Variables.lstCoccidiaTimelineSearch = CoccidiaTimelineModel.FillData();

		for (CoccidiaTimelineModel objModel : Test_Variables.lstCoccidiaTimelineSearch) { 
			Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			
	        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");
							
			for (ReportFilters objFilter : objModel.lstFilters) {
				Thread.sleep(1500);
				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objFilter.FilterXPath)));
				
				WebElement expandFilter = Helper.driver.findElement(By.xpath(objFilter.FilterXPath));
				Actions actions = new Actions(Helper.driver);
				actions.moveToElement(expandFilter).click().perform();

				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSearch)).sendKeys(objFilter.SearchVlaue);  
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
				Thread.sleep(1500);
				int chkCounter = 0;
				for (int i = 1; chkCounter < objFilter.LstFilterValues.size() && i < 5000; i++) {

					String actualXpathx = objFilter.FilterListXPathPrefix + i + objFilter.FilterListXPathSuffix;
					WebElement element = Helper.driver.findElement(By.xpath(actualXpathx));

					if (objFilter.LstFilterValues.contains(element.getText())) {
						Thread.sleep(1500);

						    boolean result = false;
						    int attempts = 0;
						    while(attempts < 5) {
						        try {
						            Helper.driver.findElement(By.xpath(objFilter.FilterListXPathPrefix + i + objFilter.FilterListXPathChkSuffix)).click();
						            result = true;
						            break;
						        } catch(StaleElementReferenceException e) {
						        } 
						        attempts++;
						    }					   
						    chkCounter++;
					}
				}
				
				Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");
				Test_Variables.steps.createNode("2. Enter value to search ("+objFilter.SearchVlaue+")");
				Test_Variables.steps.createNode("3. Select the checkbox");
				Test_Variables.steps.createNode("4. Click on apply filter button");
				
				if(chkCounter == objFilter.LstFilterValues.size()) {
					Test_Variables.test.pass(objFilter.FilterName + " applied successfully");
					Test_Variables.results.createNode(objFilter.FilterName + " applied successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
				}
				else {
					Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
					Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
				}
					
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameClearInput, objModel.TestCaseDescClearInput);

				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
				
		        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");
				Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
				Test_Variables.preconditions.createNode("7. Click on apply filter button");
				
				Test_Variables.steps.createNode("1. Click on cross icon next to entered text in search field");

				WebElement clearInput = Helper.driver.findElement(By.xpath(objFilter.ClearInput));
				JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
				jse.executeScript("arguments[0].click()", clearInput);
				Thread.sleep(1000);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
				WebElement closeSearch = Helper.driver.findElement(By.xpath(objFilter.FilterXPath));
				actions.moveToElement(closeSearch).click().perform();
				Thread.sleep(1000);

				if (objFilter.FilterListXPathSearch.contains("")) {
					Test_Variables.test.pass("Search field cleared successfully");
					Test_Variables.results.createNode("1. Search field cleared successfully on clicking cross icon");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
				}

				else {
					Test_Variables.test.fail("Search field failed to clear");
					Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
				}

				Thread.sleep(1000);
			 
			 
			 if(objModel.ApplyFilter) {
				 Helper.driver.get(Constants.url_CoccidiaTimeline);
				 Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.ctlResetButton)));
				 Thread.sleep(3000);
			 } 
			 Thread.sleep(1000);
			}
		}
	}
	
	
	@Test (description="Test Case: Test Coccidia Lock Filter Functionality",enabled= true, priority = 2) 
	public void CoccidiaLock() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-25: Verify Coccidia Timeline Lock Filter Functionality", "This test case will test Coccidia Timeline Lock Filter Functionality");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
		Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");
		
		Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
		Test_Variables.steps.createNode("2. Click on lock button");
		Test_Variables.steps.createNode("3. Close Coccidia Timeline Report");
		Test_Variables.steps.createNode("4. Reopen Coccidia Timeline Report");
		Test_Variables.steps.createNode("5. Verify lock filter remains applied");
		
		Helper.driver.get(Constants.url_CoccidiaTimeline);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.ctlSampleID)));
		Thread.sleep(2000);
		Helper.driver.findElement(By.xpath(Test_Elements.ctlSampleID)).click();
		Thread.sleep(1500);
		Helper.driver.findElement(By.xpath(Test_Elements.ctlSampleIDFirstRecord)).click();
		Thread.sleep(2000);
		
		WebElement applyFilter = Helper.driver.findElement(By.xpath(Test_Elements.ctlApplyFilter));
		Actions actions = new Actions(Helper.driver);
		actions.moveToElement(applyFilter).click().perform();
		Thread.sleep(2000);
			
		WebElement lockButton = Helper.driver.findElement(By.xpath(Test_Elements.ctlLockButton));
		actions.moveToElement(lockButton).click().perform();
		Thread.sleep(2000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
		String recordsafterfilter = Helper.driver.findElement(By.xpath(Test_Elements.ctlRecordNumber)).getText();  //records after applying filter
		Thread.sleep(1000);

		Helper.driver.get(Constants.url_reports);
		Thread.sleep(2000);
		Helper.driver.get(Constants.url_CoccidiaTimeline);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.ctlRecordNumber)));

		try{
			Assert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.xpath(Test_Elements.ctlRecordNumber)).getText());
			Test_Variables.test.pass("Filter locked functionality verified successfully");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
		}	
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.ctlUnLockButton)));
		Helper.driver.findElement(By.xpath(Test_Elements.ctlUnLockButton)).click();
		Thread.sleep(2000);
	}



	@Test (description="Test Case: Test Coccidia PNG Download",enabled= true, priority = 3) 
	public void PNGExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-26: Verify user can download Coccidia Timeline PNG file", "This test case will verify user can download Coccidia Timeline PNG file");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
		Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");
		
		Test_Variables.steps.createNode("1. Hover mouse towards barchart on top");
		Test_Variables.steps.createNode("2. Export PNG button becomes visible");
		Test_Variables.steps.createNode("3. Click on the button");
		
		Thread.sleep(2000);
		
		Actions builder = new Actions(Helper.driver);  
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.ctlTimelinePngHover)));
		WebElement pngHover = Helper.driver.findElement(By.xpath(Test_Elements.ctlTimelinePngHover));
		builder.moveToElement(pngHover).build().perform();

		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
		WebElement clickDownload = Helper.driver.findElement(By.xpath(Test_Elements.ctlTimelinePng));
		Actions actions = new Actions(Helper.driver);
		actions.moveToElement(clickDownload).click().perform();

		Thread.sleep(8000);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date date1 = new Date();
		String date= dateFormat.format(date1);
		Thread.sleep(1000);

		File downloadFolder = new File(Test_Variables.fileDownloadPath);
		List namesOfFiles = Arrays.asList(downloadFolder.list());
		for(int i = 0; i<=2; i++) {
		if(namesOfFiles.contains(Test_Variables.ctlTimelineFileName+date+".png")) {	
			System.out.println("Success");
			Test_Variables.test.pass("Timeline PNG downloaded successfully");
			Test_Variables.results.createNode("Timeline PNG downloads successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			break;
		}
		}
		
		if(namesOfFiles.contains(Test_Variables.ctlTimelineFileName+date+".png") == false) {	
			System.out.println("Failure");
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
		} 
		Thread.sleep(2000);
	}



	@Test (description="Test Case: Test Coccidia Oocysts Count Download",enabled= true, priority = 4) 
	public void OocystsCountExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-27: Verify user can download Oocysts Count PNG file", "This test case will verify that user can download Oocysts Count PNG file");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
		Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");
		
		Test_Variables.steps.createNode("1. Hover mouse towards Ocysts Count table");
		Test_Variables.steps.createNode("2. PNG file button becomes visible");
		Test_Variables.steps.createNode("3. Click on the button");
		
		Thread.sleep(2000);
		
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.ctlOCountPngHover)));
		Actions builder = new Actions(Helper.driver);
		builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.ctlOCountPngHover))).build().perform();

		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
		Thread.sleep(1000);
		builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.ctlOCountPng))).click().perform();
		Thread.sleep(6000);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date date1 = new Date();
		String date= dateFormat.format(date1);

		File downloadFolder = new File(Test_Variables.fileDownloadPath);
		List namesOfFiles = Arrays.asList(downloadFolder.list());
		for(int i = 0; i<=2; i++) {
		if(namesOfFiles.contains(Test_Variables.ctlOCountFileName+date+".png")) {	
			System.out.println("Success");
			Test_Variables.test.pass("Oocysts Counts PNG file downloaded successfully");
			Test_Variables.results.createNode("Oocysts Counts PNG downloads successfully");
			break;
		}
		}
		if(namesOfFiles.contains(Test_Variables.ctlOCountFileName+date+".png") == false) {	
			System.out.println("Failure");
			Test_Variables.test.fail("Oocysts Counts PNG failed to download");
			Test_Variables.results.createNode("Oocysts Counts PNG failed to download");
		} 
		Thread.sleep(2000);
	}


	@Test (description="Test Case: Test Coccidia Last 10 Coccidia Test Download",enabled= true, priority = 5) 
	public void CoccidiaTestExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-28: Verify user can download Last 10 Coccidia Test PNG file", "This test case will verify that user can download last 10 Coccidia Test PNG file");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
		Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");
		
		Test_Variables.steps.createNode("1. Hover mouse towards Last 10 Coccidia Test table");
		Test_Variables.steps.createNode("2. PNG file button becomes visible");
		Test_Variables.steps.createNode("3. Click on the button");
		
		Thread.sleep(2000);
		
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.ctlLast10PngHover)));
		Actions builder = new Actions(Helper.driver);
		builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.ctlLast10PngHover))).build().perform();

		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
		Thread.sleep(1000);
		builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.ctlLast10Png))).click().perform();
		Thread.sleep(6000);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date date1 = new Date();
		String date= dateFormat.format(date1);

		File downloadFolder = new File(Test_Variables.fileDownloadPath);
		List namesOfFiles = Arrays.asList(downloadFolder.list());
		for(int i = 0; i<=2; i++) {
		if(namesOfFiles.contains(Test_Variables.ctlLast10FileName+date+".png")) {	
			System.out.println("Success");
			Test_Variables.test.pass("Last 10 Coccidia Test file downloaded successfully");
			Test_Variables.results.createNode("Last 10 Coccidia Test PNG file downloads successfully");
			break;
		}
		}
		if(namesOfFiles.contains(Test_Variables.ctlLast10FileName+date+".png") == false) {	
			System.out.println("Failure");
			Test_Variables.test.fail("Last 10 Coccidia Test PNG failed to download");
			Test_Variables.results.createNode("Last 10 Coccidia Test PNG failed to download");
		} 
		Thread.sleep(2000);
	}


	@Test (description="Test Case: Test Coccidia Count Over Time Download",enabled= true, priority = 6) 
	public void CoccidiaCountOverTimeExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-29: Verify user can download Coccidia Count Over Time PNG file", "This test case will verify that user can download Coccidia Count Over Time PNG file");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
		Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");
		
		Test_Variables.steps.createNode("1. Hover mouse towards Coccidia Count Over Time table");
		Test_Variables.steps.createNode("2. PNG file button becomes visible");
		Test_Variables.steps.createNode("3. Click on the button");
		
		Thread.sleep(2000);
		
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.ctlOverTimePngHover)));
		Actions builder = new Actions(Helper.driver);
		builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.ctlOverTimePngHover))).build().perform();

		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
		Thread.sleep(1000);
		builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.ctlOverTimePng))).click().perform();
		Thread.sleep(6000);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date date1 = new Date();
		String date= dateFormat.format(date1);

		File downloadFolder = new File(Test_Variables.fileDownloadPath);
		List namesOfFiles = Arrays.asList(downloadFolder.list());
		for(int i = 0; i<=2; i++) {
		if(namesOfFiles.contains(Test_Variables.ctlOverTimeFileName+date+".png")) {	
			System.out.println("Success");
			Test_Variables.test.pass("Coccidia Count Over Time file downloaded successfully");
			Test_Variables.results.createNode("Coccidia Count Over Time PNG file downloads successfully");
			break;
		}
		}
		if(namesOfFiles.contains(Test_Variables.ctlOverTimeFileName+date+".png") == false) {	
			System.out.println("Failure");
			Test_Variables.test.fail("Coccidia Count Over Time PNG failed to download");
			Test_Variables.results.createNode("Coccidia Count Over Time PNG failed to download");
		} 
		Thread.sleep(2000);
	}
	
	
	@AfterMethod
	public void saveResult(ITestResult result) throws IOException {
		Helper.saveResult(result, Constants.CoccidiaTimelineReportPath);
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
	
	
	
}
