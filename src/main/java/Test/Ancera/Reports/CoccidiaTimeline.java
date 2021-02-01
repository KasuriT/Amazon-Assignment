package Test.Ancera.Reports;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
import Models.ExternalCoccidiaModel;
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
	
	
	@Test (description="Test Case: Navigate to Coccidia Timeline Screen",enabled= true, priority = 1) 
	public void NavigateCoccidia() throws InterruptedException, IOException {

		try {
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
			String actual = Helper.driver.findElement(By.id("Coccidia Timeline Internal")).getText();
			String expected = "Coccidia Timeline Internal";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("Coccidia Timeline report opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Coccidia Timeline report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Coccidia Timeline report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
		}
	}
	

	@Test (description="Test Case: Date Filter Test",enabled= false, priority = 2) 
	public void DateFilter() throws InterruptedException, IOException {

		Test_Variables.lstCoccidiaTimelineDateSearch = CoccidiaTimelineModel.FillDate();

		for (CoccidiaTimelineModel objModel : Test_Variables.lstCoccidiaTimelineDateSearch) { 
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
				Actions actions = new Actions(Helper.driver);

				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("calendarIcon"))); 
				Test_Variables.steps.createNode("1. Click on date calendar icon; Calendar pops up");
				actions.moveToElement(Helper.driver.findElement(By.id("calendarIcon"))).click().perform();	
				Thread.sleep(1000);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

				Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
				actions.moveToElement(Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSearch))).click().perform();	
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));

				if(objModel.Filter1) {
					try{
						String value1 = objFilter.fromDate;	
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, Integer.parseInt(value1));
						Date fromdate1 = cal.getTime();    
						String fromDate = dateFormat.format(fromdate1);

						String value = objFilter.toDate;
						cal = Calendar.getInstance();
						cal.add(Calendar.DATE, Integer.parseInt(value));
						Date todate2 = cal.getTime();    
						String toDate = dateFormat.format(todate2);

						String fromDateField = Helper.driver.findElement(By.id("filterDateFrom")).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.id("filterDateTo")).getAttribute("value");

						Thread.sleep(2000);
						Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 

						System.out.println(fromDate);
						System.out.println(fromDateField);
						System.out.println(toDate);
						System.out.println(toDateField);

						Assert.assertEquals(fromDateField, fromDate);
						Assert.assertEquals(toDateField, toDate);
						Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
						Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
					}
				}
				


				if(objModel.Filter2) {
					try{
						String value2 =objFilter.fromMonth;
						String value1 = objFilter.fromDate;	
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, Integer.parseInt(value2));
						cal.set(Calendar.DATE, Integer.parseInt(value1));
						Date fromdate1 = cal.getTime();    
						String fromDate = dateFormat.format(fromdate1);

						String value3 =objFilter.toMonth;   
						cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, Integer.parseInt(value3));
						cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						Date todate2 = cal.getTime();    
						String toDate = dateFormat.format(todate2);

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
						String fromDateField = Helper.driver.findElement(By.id("filterDateFrom")).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.id("filterDateTo")).getAttribute("value");

						Thread.sleep(2000);
						Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 

						System.out.println(fromDate);
						System.out.println(fromDateField);
						System.out.println(toDate);
						System.out.println(toDateField);

						Assert.assertEquals(fromDateField, fromDate);
						Assert.assertEquals(toDateField, toDate);
						Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
						Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
					}
				}

				if(objModel.Filter3) {
					try {
						String value1 = objFilter.fromDate;	
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DATE, Integer.parseInt(value1));
						Date fromdate1 = cal.getTime();    
						String fromDate = dateFormat.format(fromdate1);

						String value = objFilter.toDate;
						cal = Calendar.getInstance();
						cal.add(Calendar.DATE, Integer.parseInt(value));
						Date todate2 = cal.getTime();    
						String toDate = dateFormat.format(todate2);

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
						String fromDateField = Helper.driver.findElement(By.id("filterDateFrom")).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.id("filterDateTo")).getAttribute("value");

						Thread.sleep(2000);
						Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 

						System.out.println(fromDate);
						System.out.println(fromDateField);
						System.out.println(toDate);
						System.out.println(toDateField);

						Assert.assertEquals(fromDateField, fromDate);
						Assert.assertEquals(toDateField, toDate);
						Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
						Test_Variables.results.createNode("1. "+objFilter.FilterName+ " values verified successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
					}
				}
			}
		}
	}


	@Test (description="Test Case: Date Enter",enabled= false, priority = 3) 
	public void EnterDate() throws InterruptedException, IOException {

		Test_Variables.lstCoccidiaTimelineDateEnter = CoccidiaTimelineModel.EnterDate();

		for (CoccidiaTimelineModel objModel : Test_Variables.lstCoccidiaTimelineDateEnter) { 

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

				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filterDateFrom")));
				Test_Variables.steps.createNode("1. "+objFilter.FilterName);
				Thread.sleep(1000);

				Helper.driver.findElement(By.id("filterDateFrom")).clear();
				Helper.driver.findElement(By.id("filterDateFrom")).sendKeys(objFilter.fromDate);
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("filterDateTo")).clear();
				Helper.driver.findElement(By.id("filterDateTo")).sendKeys(objFilter.toDate);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));	

				Test_Variables.steps.createNode("2. Click on Apply filter button");
				Helper.driver.findElement(By.id("filter-icon")).click();

				if(objModel.Filter1) {
					try {
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
						String actual = Helper.driver.findElement(By.id("message")).getText();
						String expected = objFilter.alertMessage;

						Assert.assertEquals(actual, expected); 
						Test_Variables.test.pass("Filter was not applied successfully");
						Test_Variables.results.createNode("1. Filter was not applied successfully");	
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));	
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Filter was applied with invalid date or did not receive an alert message");
						Test_Variables.results.createNode("1. Filter was applied with invalid date or did not receive an alert message");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Filter was applied with invalid date or did not receive an alert message");
						Test_Variables.results.createNode("1. Filter was applied with invalid date or did not receive an alert message");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
					}	
				}				
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= false, priority = 4) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-CT-12: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");

			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-icon")));
			Test_Variables.steps.createNode("1. Enter valid date in from and 2 fields");
			Helper.driver.findElement(By.xpath("filterDateFrom")).clear();
			Helper.driver.findElement(By.xpath("filterDateFrom")).sendKeys("12/01/2020");
			Helper.driver.findElement(By.xpath("filterDateTo")).clear();
			Helper.driver.findElement(By.xpath("filterDateTo")).sendKeys("12/15/2020");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			Test_Variables.steps.createNode("2. Click on Apply filter button");
			Helper.driver.findElement(By.id("filter-icon")).click();
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));	
			Thread.sleep(3000);
			Test_Variables.steps.createNode("3. Click on Lock button");
			Helper.driver.findElement(By.id("save-icon")).click();;
			Thread.sleep(1000);
			String recordsbeforefilter = Helper.driver.findElement(By.id("filterDateTo")).getText(); 
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			Helper.driver.navigate().refresh();
			Test_Variables.steps.createNode("4. Reopen report and verify that records are still the same as before closing the report");
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-icon")));
			Thread.sleep(2000);
			String recordsafterfilter = Helper.driver.findElement(By.id("filterDateTo")).getText();

			Assert.assertEquals(recordsafterfilter, recordsbeforefilter);
			Test_Variables.test.pass("Filter locked functionality verified successfully on date filter");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report on date filter");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
		}
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("un-save-icon")).click();
	}
	
	
	
	@Test (description="Test Case: Reset Test",enabled= false, priority = 5) 
	public void ResetFilter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-CT-13: Verify filter can be reset", "This testcase will verify that filter can be reset");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");

			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-icon")));
			Test_Variables.steps.createNode("1. Select any filter");
			Helper.driver.findElement(By.xpath("filterDateFrom")).clear();
			Helper.driver.findElement(By.xpath("filterDateFrom")).sendKeys("12/01/2020");
			Helper.driver.findElement(By.xpath("filterDateTo")).clear();
			Helper.driver.findElement(By.xpath("filterDateTo")).sendKeys("12/15/2020");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			Test_Variables.steps.createNode("2. Click on Apply filter button");
			Helper.driver.findElement(By.id("filter-icon")).click();
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));	
			Thread.sleep(3000);
			String recordsbeforefilter = Helper.driver.findElement(By.id("filterDateTo")).getText(); 
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			Test_Variables.steps.createNode("3. Click on reset button");
			Helper.driver.findElement(By.id("reset-icon")).click();;
			Thread.sleep(3000);
			String recordsafterfilter = Helper.driver.findElement(By.id("filterDateTo")).getText();

			Assert.assertNotEquals(recordsafterfilter, recordsbeforefilter);
			Test_Variables.test.pass("Filter reset successfully");
			Test_Variables.results.createNode("Filter reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Filter failed to reset");
			Test_Variables.results.createNode("Filter failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Filter failed to reset");
			Test_Variables.results.createNode("Filter failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
		}
		Thread.sleep(1000);
	}
	
	
	
	
	@Test (description="Test Case: Filter Test",enabled= true, priority = 6) 
	public void SearchFilter() throws InterruptedException, IOException {

		Test_Variables.lstCoccidiaTimelineSearch = CoccidiaTimelineModel.FillData();

		for (CoccidiaTimelineModel objModel : Test_Variables.lstCoccidiaTimelineSearch) { 
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Timeline; Coccidia Timeline reports open");

				Actions actions = new Actions(Helper.driver);
				for (ReportFilters objFilter : objModel.lstFilters) {
					try {	
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(500);
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");
						WebElement expandFilter = Helper.driver.findElement(By.id(objFilter.FilterXPath));
						actions.moveToElement(expandFilter).click().perform();
						Test_Variables.steps.createNode("2. Enter value to search ("+objFilter.SearchVlaue+")");
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterListXPathSearch)).sendKeys(objFilter.SearchVlaue);  
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
						Thread.sleep(500);
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterValues.size() && i < 3500; i++) {

							Test_Variables.steps.createNode("3. Select the checkbox");
							int attempts = 0;
							while(attempts < 3) {
								try {
									WebElement a =  Helper.driver.findElement(By.id(objFilter.LstFilterValues.get(i)));		
									actions.moveToElement(a).click().perform();
									break;
								} catch(StaleElementReferenceException e) {
								} 
								attempts++;
							}					   
							chkCounter++;
						}

						Assert.assertTrue(chkCounter == objFilter.LstFilterValues.size()); 
						Test_Variables.test.pass("Checkbox selected successfully");
						Test_Variables.results.createNode("Checkbox selected successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline Log", Constants.CoccidiaTimelineReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
					}		
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
					}

					try {	
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

						WebElement clearInput = Helper.driver.findElement(By.id(objFilter.ClearInput));
						JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
						jse.executeScript("arguments[0].click()", clearInput);
						Thread.sleep(1000);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
						WebElement closeSearch = Helper.driver.findElement(By.id(objFilter.FilterXPath));
						actions.moveToElement(closeSearch).click().perform();
						Thread.sleep(1000);

						Assert.assertTrue(objFilter.FilterListXPathSearch.contains(""));
						Test_Variables.test.pass("Input search field cleared successfully");
						Test_Variables.results.createNode("1. Search field cleared successfully on clicking cross icon");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
					}

					if(objModel.ResetFilter) {
						Helper.driver.findElement(By.id("reset-icon")).click();
						Thread.sleep(2000);
					}

					if(objModel.ApplyFilter) {
						Helper.driver.get(Constants.url_CoccidiaTimeline);
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
						Thread.sleep(3000);
					}
					Thread.sleep(1000);
				}
			}
			catch(Exception ex) {
			}
		}
	}
	
	
	@Test (description="Test Case: Test Coccidia Lock Filter Functionality",enabled = false, priority = 7) 
	public void CoccidiaLock() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-CT-49: Verify Coccidia Timeline Lock Filter Functionality", "This test case will test Coccidia Timeline Lock Filter Functionality");
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
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset-icon")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("Sample ID")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("place-holder-search-Sample ID")).sendKeys("1");
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("1")).click();
			Thread.sleep(2000);

			WebElement applyFilter = Helper.driver.findElement(By.id("apply-icon"));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(applyFilter).click().perform();
			Thread.sleep(2000);

			WebElement lockButton = Helper.driver.findElement(By.id("save-icon"));
			actions.moveToElement(lockButton).click().perform();
			Thread.sleep(2000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));

			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset-icon")));
			Thread.sleep(1000);

			if (Helper.driver.findElements(By.xpath("//*[@id=\"group-head\"]/span")).size() != 0) {
				Test_Variables.test.pass("Filter locked functionality verified successfully");
				Test_Variables.results.createNode("Filter lock remained applied on reopening the report");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Timeline", Constants.CoccidiaTimelineReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaTimelineReportPath, null);
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaTimelineReportPath, ex);
		}	
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.ctlUnLockButton)));
		Helper.driver.findElement(By.xpath(Test_Elements.ctlUnLockButton)).click();
		Thread.sleep(2000);
	}



	@Test (description="Test Case: Test Coccidia PNG Download",enabled= false, priority = 8) 
	public void PNGExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-50: Verify user can download Coccidia Timeline PNG file", "This test case will verify user can download Coccidia Timeline PNG file");
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



	@Test (description="Test Case: Test Coccidia Oocysts Count Download",enabled= false, priority = 9) 
	public void OocystsCountExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-51: Verify user can download Oocysts Count PNG file", "This test case will verify that user can download Oocysts Count PNG file");
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


	@Test (description="Test Case: Test Coccidia Last 10 Coccidia Test Download",enabled= false, priority = 10) 
	public void CoccidiaTestExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-52: Verify user can download Last 10 Coccidia Test PNG file", "This test case will verify that user can download last 10 Coccidia Test PNG file");
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


	@Test (description="Test Case: Test Coccidia Count Over Time Download",enabled= false, priority = 11) 
	public void CoccidiaCountOverTimeExport() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-CT-53: Verify user can download Coccidia Count Over Time PNG file", "This test case will verify that user can download Coccidia Count Over Time PNG file");
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
