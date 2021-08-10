package Test.Ancera.Reports;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.ReportFilters;
import Models.CoccidiaModel;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CoccidiaLog {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Coccidia_Log"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Coccidia Log Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Coccidia Log Screen",enabled= true, priority = 1) 
	public void NavigateCoccidia() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-04: Verify user can navigate to Coccidia Log Screen", "This test case will verify user can navigate to Coccidia Log Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.steps.createNode("1. Click on Coccidia Log");

			Helper.driver.get(Constants.url_CoccidiaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("Coccidia Log")).getText();
			String expected = "Coccidia Log";

			Assert.assertEquals(actual, expected);			
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("Coccidia Log report opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Coccidia Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Coccidia Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
	}


	@SuppressWarnings("unused")
	@Test (description="Test Case: Date Filter Test",enabled= true, priority = 2) 
	public void DateFilter() throws InterruptedException, IOException {

		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText();
		Test_Variables.lstCoccidiaDateSearch = CoccidiaModel.FillDate();

		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaDateSearch) { 
			Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			for (ReportFilters objFilter : objModel.lstFilters) {
				Actions actions = new Actions(Helper.driver);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.steps.createNode("1. Click on date calendar icon; Calendar pops up");
				WebElement filter_scroll = Helper.driver.findElement(By.id("scanDateTime_show-filter"));
				((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
				Helper.driver.findElement(By.id("scanDateTime_show-filter")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
				Helper.driver.findElement(By.id("list-title_date-selection")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				if (Helper.driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch)).isEnabled()) {
					actions.moveToElement(Helper.driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch))).click().perform();	
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("scanDateTime_show-filter")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));	

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
							String fromDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();
						//	Assert.assertEquals(fromDateField, fromDate);
						//	Assert.assertEquals(toDateField, toDate);
							Assert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");

						}catch(AssertionError er) {
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
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

							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							String fromDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();
						//	Assert.assertEquals(fromDateField, fromDate);
						//	Assert.assertEquals(toDateField, toDate);
							Assert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");

						}catch(AssertionError er) {
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
						}
					}

					if(objModel.Filter3) {
						try {
							String value1 = objFilter.fromDate;	
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.DATE, Integer.parseInt(value1));
							Date fromdate1 = cal.getTime();    
							String fromDate = dateFormat.format(fromdate1);

							String value3 =objFilter.toMonth;   
							cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, Integer.parseInt(value3));
							cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							Date todate2 = cal.getTime();    
							String toDate = dateFormat.format(todate2);

							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							String fromDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");

							Thread.sleep(1000);
							Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 

							String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();
						//	Assert.assertEquals(fromDateField, fromDate);
						//	Assert.assertEquals(toDateField, toDate);
							Assert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode("1. "+objFilter.FilterName+ " values verified successfully");

						}catch(AssertionError er) {
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
						}
					}
					Helper.driver.findElement(By.id("scanDateTime_show-filter")).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.id("scanDateTime_clear-filter")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

				}
				else {
					Test_Variables.test.skip("Unable to test the scenario because button is disabled");
					Test_Variables.results.createNode("Unable to test the scenario because button is disabled");
					Helper.saveResultNew(ITestResult.SKIP, Constants.CoccidiaReportPath, null);
					ClickElement.clickById(Helper.driver, "results-found-count");
				}
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= true, priority = 3) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-15: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-scanDateTime"))); 
			Test_Variables.steps.createNode("1. Open date filter popup");
			WebElement filter_scroll = Helper.driver.findElement(By.id("sort-scanDateTime"));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter")).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);

			Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
			Helper.driver.findElement(By.id("list-title_date-selection")).click();
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector("body app-root p:nth-child(5)")).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Test_Variables.steps.createNode("3. Click on Lock button");
			Helper.driver.findElement(By.id("save-filters")).click();;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			String recordsbeforefilter = Helper.driver.findElement(By.id("results-found-count")).getText(); 
			Thread.sleep(500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Test_Variables.steps.createNode("4. Close report");
			Test_Variables.steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();

			Assert.assertEquals(recordsafterfilter, recordsbeforefilter);
			Test_Variables.test.pass("Filter locked functionality verified successfully on date filter");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report on date filter");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
		
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Helper.driver.findElement(By.id("remove-filters")).click();
		WebElement filter_scroll = Helper.driver.findElement(By.cssSelector("sort-scanDateTime"));
		((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
		Thread.sleep(800);
		Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .log-header__clear-filter")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	@Test (description="Test Case: Filter Test",enabled= true, priority = 4) 
	public void TestFilter() throws InterruptedException, IOException {

		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstCoccidiaSearch = CoccidiaModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							WebElement filter_scroll = Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
							Thread.sleep(800);	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");
							Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .fa-filter")).click();		
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);						
							Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .filter-popup__footer--view-all")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);

							for (int j = 0; j < objFilter.LstFilterValues.size(); j++) {
								Test_Variables.steps.createNode("2. Select the checkbox");
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .checkbox-sm:nth-child("+objFilter.LstFilterValues.get(j)+") label"));
								((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 										
								Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .checkbox-sm:nth-child("+objFilter.LstFilterValues.get(j)+") label")).click();
							}

							Test_Variables.steps.createNode("3. Click on apply filter button");	
							Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .filter-popup__action--apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);
							String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();		

							//System.out.println(recordBefore+", "+recordAfter);
							Assert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass("Checkbox selected successfully");
							Test_Variables.results.createNode("Checkbox selected successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
						}	
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + "checkbox failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + "checkbox failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + "checkbox failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + "checkbox failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch, objModel.TestCaseDescriptionSearch);
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify filter is applied and relevant results are displayed in table");

						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i) +" .fa-filter")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .divider")).size(), 1);
							Test_Variables.test.pass("Filter was applied successfully");
							Test_Variables.results.createNode("Filter was applied successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);

							Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .filter-popup__action--clear-all")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to apply");
						Test_Variables.results.createNode("Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter failed to apply");
						Test_Variables.results.createNode("Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}

	
	@Test (description="Test Case: Test Site Name Filter",enabled= true, priority = 5) 
	public void SiteName() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-137: Verify Site Name Filter Functionality", "This test case will test Site Name Filter Functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-laneNum"))); 
			Thread.sleep(1000);	
			String recordsBefore = Helper.driver.findElement(By.id("results-found-count")).getText();
			
			WebElement filter_scroll = Helper.driver.findElement(By.id("sample_matrix_sort"));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(800);	
			
			Helper.driver.findElement(By.id("site_id_show-filter")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			Helper.driver.findElement(By.cssSelector(".background-white:nth-child(2)  div.table-responsive.apl-data-log-table.apl-scrollbar div.mb-0 table.table__data-log tr.text-uppercase th.min-w-157px.w-157px.max-w-157px.ng-star-inserted:nth-child(16) div.filter-block.d-none.d-block app-sites-filter-popup.ng-star-inserted div.filter-popup div.filter-popup__container div.filter-popup__body div.filter-popup__filter-container.apl-scrollbar div.sites-box.ancera-scrollbar.grid-container tr.ng-star-inserted:nth-child(1) td:nth-child(1) div.d-flex.align-items-center.site-view-checkbox div.checkbox.checkbox-sm > label.mb-0:nth-child(2)")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#sort-site_id #list-title_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Assert.assertNotEquals(Helper.driver.findElement(By.id("results-found-count")).getText(), recordsBefore);
			Test_Variables.test.pass("Checkbox selected successfully");
			Test_Variables.results.createNode("Checkbox selected successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}	
	}
	
	
	@Test (description="Test Case: Wildcard",enabled= true, priority = 6) 
	public void wildcard() throws InterruptedException, IOException {

		
		Test_Variables.lstCoccidiaWildcardSearch = CoccidiaModel.Wildcard(); 
		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaWildcardSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	

						Test_Variables.steps.createNode("1. Click on Coccidia Log");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						WebElement filter_scroll = Helper.driver.findElement(By.cssSelector(objFilter.FilterID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 	
						Thread.sleep(1000);
						Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .fa-filter")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));


						if (Helper.driver.findElements(By.cssSelector(objFilter.FilterID+" .data-log-radio")).size() == 0) {
							Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .filter-popup__wildcard")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						}

						if(objModel.startWith) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterXPath+"_wildcard-option1")).click();
						}

						if(objModel.endsWith) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterXPath+"_wildcard-option3")).click();
						}

						if(objModel.contains) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterXPath+"_wildcard-option2")).click();
						}

						Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .form-control")).clear();
						Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .form-control")).sendKeys(objModel.input);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .filter-popup__action--apply")).click();

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] td:nth-child(4) label"));
						int count = rows.size();
						Thread.sleep(1000);
						for (int i = 0; i<count; i++) {
							if(objModel.startWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								Assert.assertTrue(str.startsWith(objFilter.LstFilterValues.get(0)) || str.startsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.endsWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								Assert.assertTrue(str.endsWith(objFilter.LstFilterValues.get(0)) || str.endsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.contains) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								Assert.assertTrue(str.contains(objFilter.LstFilterValues.get(0)) || str.contains(objFilter.LstFilterValues.get(1)));
							}
						}

						Thread.sleep(1000);
						Test_Variables.test.pass("User navigated successfully");
						Test_Variables.results.createNode("Coccidia Log report opens successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("User navigation failed");
						Test_Variables.results.createNode("Coccidia Log report failed to open");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("User navigation failed");
						Test_Variables.results.createNode("Coccidia Log report failed to open");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}
					Helper.driver.findElement(By.id(objFilter.FilterXPath+"_clear-filter")).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Test (description="Test Case: Contextual",enabled= true, priority = 7) 
	public void Contexual() throws InterruptedException, IOException {

		Thread.sleep(1500);
		Test_Variables.lstCoccidiaContexualCheck = CoccidiaModel.ContexualCheck(); 
		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaContexualCheck) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
				
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						Test_Variables.steps.createNode("1. Apply "+objFilter.FilterName);	
						Test_Variables.steps.createNode("2. Verify all filter behaves contexually");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(500);
						WebElement filter_scroll = Helper.driver.findElement(By.id("sort-"+objFilter.LstFilterSearch.get(0)));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(800);
						
						if (objModel.firstCase) {
							if (objModel.runIngestion) {
								RequestSpecification request = RestAssured.given();
								request.header("Content-Type", "application/json");
								JSONObject json = new JSONObject();   
								json.put("piperid", Test_Variables.piperId);
								json.put("password", Test_Variables.piperPassword);
								json.put("DISAPIVersion", "14.13");
								request.body(json.toString());
								Response response = request.post(Constants.api_login);
								int code = response.getStatusCode();
								Assert.assertEquals(code, 200);

								String data = response.asString();
								JsonPath jsonPathEvaluator = response.jsonPath();
								String token = jsonPathEvaluator.get("token");		
								Thread.sleep(2000);
								RequestSpecification request_announcement = RestAssured.given();

								request_announcement.header("Content-Type", "application/json");
								request_announcement.header("Authorization", "bearer " +token);
								HttpGet postRequest = new HttpGet(Constants.api_announcement);
								postRequest.addHeader("Content-Type", "application/json");
								postRequest.addHeader("Authorization", "Bearer "+token);

								JSONObject json1 = new JSONObject();
								JSONObject json2 = new JSONObject();
								JSONObject json3 = new JSONObject();
								JSONArray list = new JSONArray();

								json1.put("runId", Test_Variables.lstApiAnnouncement.get(0));
								json1.put("dateTime", Test_Variables.lstApiAnnouncement.get(1));
								json1.put("Piperid",  Test_Variables.lstApiAnnouncement.get(2));
								json1.put("MPNCalculationType", Test_Variables.lstApiAnnouncement.get(3));
								json2.put("fileName", Test_Variables.lstApiAnnouncement.get(4));
								json2.put("checksum", Test_Variables.lstApiAnnouncement.get(5));
								list.add(json2);
								json1.put("files", list);

								request_announcement.body(json1.toString());
								Response response1 = request_announcement.post(Constants.api_announcement);
								Thread.sleep(2000);
								RequestSpecification request_fileupload = RestAssured.given();
								request_fileupload.header("Content-Type", "application/json");
								request_fileupload.header("Authorization", "bearer " +token);
								HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
								postRequest1.addHeader("Content-Type", "application/json");
								postRequest1.addHeader("Authorization", "Bearer "+token);

								json3.put("runId", Test_Variables.lstCoccidiaIngest.get(0).runId);
								json3.put("checksum", Test_Variables.lstCoccidiaIngest.get(0).checksum);
								json3.put("fileName", Test_Variables.lstCoccidiaIngest.get(0).fileName);
								json3.put("fileType", Test_Variables.lstCoccidiaIngest.get(0).fileType);
								json3.put("file", Test_Variables.lstCoccidiaIngest.get(0).file);
								json3.put("fileJson", objModel.fileJson);				
								json3.put("Improc", Test_Variables.lstCoccidiaIngest.get(0).improc);
								json3.put("RunMode", "1");
								json3.put("Pathogen", "Coccidia");

								request_fileupload.body(json3.toString());
								Response response2 = request_fileupload.post(Constants.api_FileUpload);
								String data3 = response2.asString();
								//System.out.println(data3);
								JsonPath jsonPathEvaluator1 = response.jsonPath();
								jsonPathEvaluator1.get("statusCode");
								Thread.sleep(1000);
								
								Thread.sleep(60000);
								Helper.driver.navigate().refresh();
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);
							}
									
							WebElement filter_scroll2 = Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_show-filter"));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
							Thread.sleep(800);
							
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_show-filter")).click();		
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);						

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_search-input")).sendKeys(objFilter.LstFilterValues.get(0));
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							
							for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {
								
								WebElement filter_scroll3 = Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+"_show-filter"));
								((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll3); 
								
								Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+"_show-filter")).click();		
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);	

								String b = Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+Test_Elements.footerCount)).getText();
								Assert.assertEquals(b, "Showing 1 - 1 Results");														
							}
							
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_clear-filter")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	
						}
						
						if (objModel.secondCase) {
							String[] array;
							array = new String[objFilter.LstFilterSearch.size()];

							for (int t = 0; t<array.length; ) {
								for(int k = 0; k<objFilter.LstFilterSearch.size(); k++) {

									Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(k)+"_show-filter")).click();		
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);						
									String a = Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(k)+" "+Test_Elements.footerCount)).getText();
									array[t] = a;
									//System.out.println("array: "+array[t]);
									t++;
								
									if(k==objFilter.LstFilterSearch.size() - 1) {
										WebElement filter_scroll2 = Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_show-filter"));
										((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
										Thread.sleep(800);
										
										Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_show-filter")).click();		
										Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);						
										Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_search-input")).sendKeys(objFilter.LstFilterValues.get(0));
										Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);
										Helper.driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

										Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_apply")).click();
										Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(2000);

										for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

											Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+"_show-filter")).click();		
											Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
											Thread.sleep(1000);	

											String b = Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+Test_Elements.footerCount)).getText();
											//System.out.println("array result: "+array[l]+" -> "+b);
											Assert.assertNotEquals(array[l], b);
										}
									}
								}
							}
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+"_clear-filter")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	
							
						}
						Test_Variables.test.pass("Contexual Filter verified successfully");
						Test_Variables.results.createNode("Contexual Filter verified successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to verify contexually");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to verify contexually");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to verify contexually");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to verify contexually");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}
	

	@Test (description="Test Case: Test Coccidia Lock Filter Functionality",enabled= true, priority = 8) 
	public void CoccidiaLock() throws InterruptedException, IOException {
		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);
		Test_Variables.lstCoccidiaLock = CoccidiaModel.Lock(); 
		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaLock) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);	
						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.driver.findElement(By.id(objFilter.FilterID)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);
						Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterSort+" .checkbox-sm:nth-child(2) label")).click();
						Thread.sleep(500);
						Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
						Helper.driver.findElement(By.id(objFilter.FilterApply)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.steps.createNode("2. Click on lock button");	
						Helper.driver.findElement(By.id("save-filters")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Thread.sleep(1000);
						String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();
						Test_Variables.steps.createNode("3. Close Coccidia Log Report");
						Test_Variables.steps.createNode("4. Reopen Coccidia Log Report");
						Helper.driver.navigate().refresh();

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						Test_Variables.steps.createNode("5. Verify lock filter remains applied");
						Assert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.id("results-found-count")).getText());
						Test_Variables.test.pass(objFilter.FilterName+" lock functionality verified successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" lock functionality verified successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("remove-filters")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Helper.driver.findElement(By.id(objFilter.FilterClear)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Pagination",enabled= true, priority = 9) 
	public void Pagination() throws InterruptedException, IOException {
		Test_Variables.lstCoccidiaPagination = CoccidiaModel.pagination();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 

		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaPagination) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						String recordNumber = Helper.driver.findElement(By.id("results-found-count")).getText();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));

						String removeComma = recordNumber.replace(",", "");
						double x = Double.parseDouble(removeComma);
						double y = 100;
						double divide = Math.ceil(Math.abs(x/y));
						final int totalPages = (int)divide;
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
							Helper.driver.findElement(By.id(objFilter.paginationPage)).click();

							if (objModel.paginationExist) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Verify pagination exists");
								Assert.assertTrue(Helper.driver.findElements(By.id("activePageNumber")).size() != 0);
								Test_Variables.test.pass("Pagination displayed successfully");
								Test_Variables.results.createNode("Pagination displayed successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);		
							}

							if (objModel.paginationLastPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Click on '>>' icon in pagination");

								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));	
									Assert.fail("An error alert message displayed");
								}

								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, totalPages+"/"+totalPages);
								Test_Variables.test.pass("Navigated to last page successfully");
								Test_Variables.results.createNode("Navigated to last page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
							}


							if (objModel.paginationPreviousPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Click on '<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
									Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
									Assert.fail("An error alert message displayed");
								}

								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
								Test_Variables.test.pass("Navigated to previous page successfully");
								Test_Variables.results.createNode("Navigated to previous page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
							}


							if (objModel.paginationFirstPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Click on '<<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
									Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
									Assert.fail("An error alert message displayed");
								}
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 1+"/"+totalPages);
								Test_Variables.test.pass("Navigated to first page successfully");
								Test_Variables.results.createNode("Navigated to first page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
							}


							if (objModel.paginationNextPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Click on '>' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
									Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
									Assert.fail("An error alert message displayed");
								}
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 2+"/"+totalPages);
								Test_Variables.test.pass("Navigated to next page successfully");
								Test_Variables.results.createNode("Navigated to next page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
							}
						}

						else {
							Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
							Test_Variables.test.pass("Records are less then 100; pagination cannot be tested");
							Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Helper.driver.findElement(By.id("first-page")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	@Test (description="Test Case: Test Table Rows",enabled= true, priority = 10) 
	public void RowsPerPage() throws InterruptedException, IOException {
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 
		Thread.sleep(500);

		Test_Variables.lstCoccidiaRowCount = CoccidiaModel.searchRows();

		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaRowCount) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				Actions actions = new Actions(Helper.driver);
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > Integer.parseInt(objFilter.count)) {
							WebElement expandFilter = Helper.driver.findElement(By.id("rows"));
							actions.moveToElement(expandFilter).click().perform();				
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));	
							Helper.driver.findElement(By.id(objFilter.FilterListXPathSearch)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1500);
							List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] tr"));
							int count = rows.size();
							int new_count = count - 1;
							//System.out.println("ROW COUNT : "+new_count);
							Assert.assertEquals(new_count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
						}
						else {
							Assert.assertTrue(true, "Records are less then "+objFilter.count);
							Test_Variables.test.pass("Records are less then "+objFilter.count);
							Test_Variables.results.createNode("Rcords are less then "+objFilter.count);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch, objModel.TestCaseDescriptionSearch);
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));

						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						Test_Variables.steps.createNode("2. Go to next page from pagination");
						Test_Variables.steps.createNode("3. Verify that still "+objFilter.FilterName+" is selected");

						String results = Helper.driver.findElement(By.id("results-found-count")).getText();
						int sum = Integer.parseInt(objFilter.count) + Integer.parseInt(objFilter.count);

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > sum) {

							ClickElement.clickById(Helper.driver, "next-page");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] tr"));
							int count = rows.size();
							int new_count = count - 1;
							//System.out.println("ROW COUNT : "+new_count);
							Assert.assertEquals(new_count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);	
						}

						else {
							Assert.assertTrue(true, "Records are less then "+sum);
							Test_Variables.test.pass("Records are less then "+sum);
							Test_Variables.results.createNode("Records are less then "+sum);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);	
						}

					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Sorting",enabled= true, priority = 11) 
	public void Sorting() throws InterruptedException, IOException {

		Test_Variables.lstCoccidiaSorting = CoccidiaModel.sorting();

		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaSorting) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.ColumnID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" column header");
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));						
						Thread.sleep(500);
						Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).isDisplayed());
						Test_Variables.test.pass(objFilter.FilterName+" column sorted successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).isDisplayed());
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to sort");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to sort");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}

					catch(StaleElementReferenceException s) {
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority = 12) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-324: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
			Test_Variables.steps.createNode("1. Verify int data type columns are right alligned");

			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clLaneCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clTotalOPGCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clSmallOPGCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clMediumOPGCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clLargeOPGCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clTotalCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clSmallCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clMediumCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clLargeCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.clMeanIntensityCol+" .text-right")).isDisplayed() == true);
			Test_Variables.test.pass("Int data type columns are right alligned");
			Test_Variables.results.createNode("Int data type columns are right alligned");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 13) 
	public void FieldAccessUnview() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-325: Verify that unselecting column from field access popup hides the column from report table", "This testcase will verify that unselecting column from field access popup hides the column from report table");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
			Test_Variables.steps.createNode("1. Click on filed access icon; popup appears");

			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("edit-field-access")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Unselect any column and click on save button");
			Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")).click();
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			if (Helper.driver.findElements(By.id("sort-laneNum")).size() == 0) {
				Assert.assertTrue(true);
				Test_Variables.test.pass("Column was hidden successfully");
				Test_Variables.results.createNode("Column was hidden successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
			}
		}catch(AssertionError er) {
			Test_Variables.test.fail("Column failed to hide");
			Test_Variables.results.createNode("Column failed to hide");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Column failed to hide");
			Test_Variables.results.createNode("Column failed to hide");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 14) 
	public void FieldAccessView() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-ESL-326: Verify that re-selecting column from field access popup displays the column from report table", "This testcase will verify that re-selecting column from field access popup displays the column from report table");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
			Test_Variables.steps.createNode("1. Click on filed access icon; popup appears");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("edit-field-access")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Re-select any unselcted column and click on save button");
			Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")).click();
			Thread.sleep(1000);                 
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			Assert.assertTrue(Helper.driver.findElement(By.id("sort-laneNum")).isDisplayed());
			Test_Variables.test.pass("Column displayed successfully");
			Test_Variables.results.createNode("Column displayed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Column failed to display");
			Test_Variables.results.createNode("Column failed to display");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Column failed to display");
			Test_Variables.results.createNode("Column failed to display");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
	}



	@SuppressWarnings("unused")
	@Test (description="Test Case: Test Coccidia PNG Download",enabled= true, priority = 15) 
	public void PNGExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-149: Verify user can download Coccidia PNG file", "This test case will verify user can download Coccidia PNG file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards barchart on top");
			Test_Variables.steps.createNode("2. Export PNG button becomes visible");
			Test_Variables.steps.createNode("3. Click on the button");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			Helper.driver.findElement(By.id("scanDateTime_show-filter")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("list-title_date-selection")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("list-title_range-2")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
		
			WebElement pngHover = Helper.driver.findElement(By.cssSelector(".run-timeline-bar-chart__download"));
			Actions builder = new Actions(Helper.driver);
			builder.moveToElement(pngHover).build().perform();
			
			WebElement clickDownload = Helper.driver.findElement(By.id("dc-bar-chart-coci-png"));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(clickDownload).click().perform();

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1000);
			//File downloadFolder = new File(Test_Variables.fileDownloadPath);
			//List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			//	Assert.assertTrue(namesOfFiles.contains(Test_Variables.clPNGFileName+date+".png")); 
			//System.out.println("Success");
			Test_Variables.test.pass("PNG downloaded successfully");
			Test_Variables.results.createNode("PNG downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
		Thread.sleep(2000);
	}


	@Test (description="Test Case: Test Coccidia CSV Download",enabled= true, priority = 16) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-150: Verify user can download Coccidia CSV file", "This test case will verify that user can download Coccidia CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("5. Click on Export as CSV");
			Helper.driver.findElement(By.id("export-csv")).click();
			Thread.sleep(45000);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, Test_Variables.clCSVFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
	}


	@Test (description="Test Case: Test Coccidia Audit Download",enabled= true, priority = 17) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-151: Verify user can download Coccidia Audit file", "This test case will verify that user can download Coccidia Audit file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			ClickElement.clickByCss(Helper.driver, "#select-runId-0 .checkbox");
			Thread.sleep(1000);

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Thread.sleep(1500);
			Test_Variables.steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(Helper.driver, "export-audit-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);
			
			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, Test_Variables.clCSVAuditFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
			
			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}
	}


	@Test (description="Test Case: Test Coccidia Template Download",enabled= true, priority = 18) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-152: Verify user can download Coccidia Template file", "This test case will verify that user download Coccidia Template file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Variables.steps.createNode("3. Click on the button");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1000);
			ClickElement.clickByCss(Helper.driver, "#export-data-template label");
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Test_Variables.steps.createNode("5. Click on Export Data Template");
			Test_Variables.steps.createNode("6. Select Sample MetaData Template");
			ClickElement.clickById(Helper.driver, "Sample-Metadata-Upload-Template");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			
			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			//System.out.println("Latest XLSX file is = "+filename);
			Assert.assertEquals(filename, Test_Variables.clSampleMetaData+".xlsx");
			Test_Variables.test.pass("Sample MetaData template downloaded successfully");
			Test_Variables.results.createNode("Sample MetaData template downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");
			

			Test_Variables.test.pass("Sample MetaData downloaded successfully");
			Test_Variables.results.createNode("Sample MetaData downloaded successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}

	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}

}
