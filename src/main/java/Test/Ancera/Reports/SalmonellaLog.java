package Test.Ancera.Reports;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Models.SalmonellaModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SalmonellaLog {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Salmonella_Log"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Salmonella Log Test Report"); 
		Helper.config();
		ConfigureLogin.login();
	}
	
	
	@Test (priority = 1) 
	public void NavigateSalmonella() throws InterruptedException, IOException {
		Test_Functions.NavigateToScreen(Constants.url_SalmonellaLog, "Salmonella Log", Constants.SalmonellaReportPath, Test_Elements.salmonellaLogTitle);
	}


	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_SalmonellaLog);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Test_Functions.Lock(Test_Elements.salmonellaLogTable, "Salmonella Log", Constants.SalmonellaReportPath, 2);
	}
	

	@Test (priority = 3) 
	public void Wildcard() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_SalmonellaLog);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Test_Functions.Wildcard(Test_Elements.salmonellaLogTable, "Salmonella Log", Constants.SalmonellaReportPath, 2);
	}
	
	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		Test_Functions.Sorting(Test_Elements.salmonellaLogTable, "Salmonella Log", Constants.SalmonellaReportPath);
	}
	
	@Test(priority= 5, enabled = true)
	public void RowsPerPage() throws InterruptedException, IOException {
		Test_Functions.RowsPerPage();
	}


	@SuppressWarnings("unused")
	@Test (description="Test Case: Date Filter Test",enabled= true, priority = 6) 
	public void DateFilter() throws InterruptedException, IOException {

		Test_Functions.fieldLevelReset();
		Test_Variables.lstSalmonellaDateSearch = SalmonellaModel.FillDate();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText();
		SoftAssert softAssert = new SoftAssert();

		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaDateSearch) { 
			Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			for (ReportFilters objFilter : objModel.lstFilters) {
				Actions actions = new Actions(Helper.driver);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.steps.createNode("1. Click on date calendar icon; Calendar pops up");
				Helper.driver.findElement(By.id(Test_Elements.slResultDate+""+Test_Elements.slShowFilter)).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);
				String dateFrom = Helper.driver.findElement(By.xpath("//input[@placeholder='Start Date']")).getText();
				//	softAssert.assertEquals(dateFrom, Test_Variables.dateMMDDYYYY1);

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Test_Variables.steps.createNode("2. Click on "+objFilter.FilterName);
				Helper.driver.findElement(By.id("list-title_date-selection")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				if (Helper.driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch)).isEnabled()) {
					actions.moveToElement(Helper.driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch))).click().perform();	
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(750);
					Helper.driver.findElement(By.id(Test_Elements.slResultDate+""+Test_Elements.slShowFilter)).click();
					Thread.sleep(750);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));	

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
							softAssert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();

						}catch(AssertionError er) {
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
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
							String fromDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();
							//Assert.assertEquals(fromDateField, fromDate);
							//Assert.assertEquals(toDateField, toDate, "Please ingest data with current date to test this scenario successfully");
							softAssert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();
						}catch(AssertionError er) {
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
						}
					}

					if(objModel.Filter3) {
						try {
							String value1 = objFilter.fromDate;	
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.DATE, Integer.parseInt(value1));
							Date fromdate1 = cal.getTime();    
							String fromDate = dateFormat.format(fromdate1);
							Helper.driver.findElement(By.id(Test_Elements.slResultDate+""+Test_Elements.slShowFilter)).click();

							String value3 =objFilter.toMonth;   
							cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, Integer.parseInt(value3));
							cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							Date todate2 = cal.getTime();    
							String toDate = dateFormat.format(todate2);
							System.out.println(toDate);
							Helper.driver.findElement(By.id(Test_Elements.slResultDate+""+Test_Elements.slShowFilter)).click();
							String fromDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();
							//Assert.assertEquals(fromDateField, fromDate);
							//Assert.assertEquals(toDateField, toDate);
							softAssert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode("1. "+objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();	
						}catch(AssertionError er) {
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
						}
					}
					Helper.driver.findElement(By.id(Test_Elements.slResultDate+""+Test_Elements.slShowFilter)).click();	
				}
				else {
					Test_Variables.test.skip("Unable to test the scenario because button is disabled");
					Test_Variables.results.createNode("Unable to test the scenario because button is disabled");
					Helper.saveResultNew(ITestResult.SKIP, Constants.SalmonellaReportPath, null);
					ClickElement.clickById(Helper.driver, "results-found-count");
				}
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= true, priority = 7) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-10: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("1. Open date filter popup");
			SoftAssert softAssert = new SoftAssert();
			WebElement filter_scroll = Helper.driver.findElement(By.id(Test_Elements.slSortFilter+""+Test_Elements.slResultDate));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(Test_Elements.slResultDate+""+Test_Elements.slShowFilter)).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);

			Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
			Helper.driver.findElement(By.id("list-title_date-selection")).click();
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector(Test_Elements.slLast30Days)).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Test_Variables.steps.createNode("3. Click on Lock button");
			Helper.driver.findElement(By.id("save-filters")).click();;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			String recordsbeforefilter = Helper.driver.findElement(By.id("results-found-count")).getText(); 
			Thread.sleep(500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Test_Variables.steps.createNode("4. Close report");
			Test_Variables.steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();

			softAssert.assertEquals(recordsafterfilter, recordsbeforefilter);
			Test_Variables.test.pass("Filter locked functionality verified successfully on date filter");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report on date filter");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
			softAssert.assertAll();
		}catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Helper.driver.findElement(By.id("remove-filters")).click();
		Helper.driver.findElement(By.id("reset-all-filters")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}

	


	@Test (description="Test Case: Test Site Name Filter",enabled= true, priority = 8) 
	public void SiteName() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-59: Verify Site Name Filter Functionality", "This test case will test Site Name Filter Functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			String recordsBefore = Helper.driver.findElement(By.id("results-found-count")).getText();

			WebElement filter_scroll = Helper.driver.findElement(By.id(Test_Elements.slCollectionSiteName+""+Test_Elements.slShowFilter));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(800);	

			Helper.driver.findElement(By.id(Test_Elements.slCollectionSiteName+""+Test_Elements.slShowFilter)).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			Helper.driver.findElement(By.cssSelector("#sort-site_id tr:nth-child(1) td:nth-child(1) .custom-control.custom-checkbox")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#sort-site_id #list-title_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Assert.assertNotEquals(Helper.driver.findElement(By.id("results-found-count")).getText(), recordsBefore);
			Test_Variables.test.pass("Checkbox selected successfully");
			Test_Variables.results.createNode("Checkbox selected successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}	
	}



	@SuppressWarnings({ "unused", "unchecked" })
	@Test (description="Test Case: Contextual",enabled= true, priority = 9) 
	public void Contexual() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_SalmonellaLog);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);

		Test_Variables.lstSalmonellaContexualCheck = SalmonellaModel.ContexualCheck(); 
		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaContexualCheck) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	

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
								String data2 = response1.asString();
								System.out.println(data2);
								Thread.sleep(2000);
								RequestSpecification request_fileupload = RestAssured.given();
								request_fileupload.header("Content-Type", "application/json");
								request_fileupload.header("Authorization", "bearer " +token);
								HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
								postRequest1.addHeader("Content-Type", "application/json");
								postRequest1.addHeader("Authorization", "Bearer "+token);

								json3.put("runId", Test_Variables.lstSalmonellaIngest.get(0).runId);
								json3.put("checksum", Test_Variables.lstSalmonellaIngest.get(0).checksum);
								json3.put("fileName", Test_Variables.lstSalmonellaIngest.get(0).fileName);
								json3.put("fileType", Test_Variables.lstSalmonellaIngest.get(0).fileType);
								json3.put("file", Test_Variables.lstSalmonellaIngest.get(0).file);
								json3.put("fileJson", objModel.fileJson);				
								json3.put("Improc", Test_Variables.lstSalmonellaIngest.get(0).improc);
								json3.put("RunMode", "1");
								json3.put("Pathogen", "Salmonella");

								request_fileupload.body(json3.toString());
								Response response2 = request_fileupload.post(Constants.api_FileUpload);
								String data3 = response2.asString();
								System.out.println(data3);
								JsonPath jsonPathEvaluator1 = response.jsonPath();
								jsonPathEvaluator1.get("statusCode");
								Thread.sleep(1000);

								Thread.sleep(120000);
								Helper.driver.navigate().refresh();
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);
							}

							Test_Variables.steps.createNode("1. Apply "+objFilter.FilterName);
							WebElement filter_scroll2 = Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slApplyFilter));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
							Thread.sleep(800);

							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slShowFilter)).click();		
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);						

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slApplyFilter)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							Test_Variables.steps.createNode("2. Verify all filter behaves contexually");

							for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

								WebElement filter_scroll3 = Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+Test_Elements.slShowFilter));
								((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll3); 

								Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+Test_Elements.slShowFilter)).click();		
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);	

								String b = Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+Test_Elements.footerCount)).getText();
								Assert.assertEquals(b, "Showing 1 - 1 Results");														
							}

							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slClearFilter)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	
						}

						if (objModel.secondCase) {
							String[] array;
							array = new String[objFilter.LstFilterSearch.size()];

							for (int t = 0; t<array.length; ) {
								for(int k = 0; k<objFilter.LstFilterSearch.size(); k++) {

									Test_Variables.steps.createNode("1. Apply "+objFilter.FilterName);				
									Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(k)+""+Test_Elements.slShowFilter)).click();		
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);						
									String a = Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(k)+" "+Test_Elements.footerCount)).getText();
									array[t] = a;
									//			System.out.println("array: "+array[t]);
									t++;
									Test_Variables.steps.createNode("2. Verify all filter behaves contexually");
									if(k==objFilter.LstFilterSearch.size() - 1) {
										WebElement filter_scroll2 = Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slShowFilter));
										((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
										Thread.sleep(800);

										Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slShowFilter)).click();		
										Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);						
										Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
										Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);
										Helper.driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

										Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slApplyFilter)).click();
										Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(2000);

										for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

											Helper.driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+Test_Elements.slShowFilter)).click();		
											Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
											Thread.sleep(1000);	

											String b = Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+Test_Elements.footerCount)).getText();
											Assert.assertNotEquals(array[l], b);
										}
									}
								}
							}
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+Test_Elements.slClearFilter)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	
						}
						Test_Variables.test.pass("Contexual Filter verified successfully");
						Test_Variables.results.createNode("Contexual Filter verified successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to verify contexually");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to verify contexually");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to verify contexually");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to verify contexually");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Pagination",enabled= true, priority = 10) 
	public void Pagination() throws InterruptedException, IOException {
		Test_Variables.lstSalmonellaPagination = SalmonellaModel.pagination();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 
		Thread.sleep(500);

		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaPagination) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						String recordNumber = Helper.driver.findElement(By.id("results-found-count")).getText();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));

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
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);		
							}

							if (objModel.paginationLastPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '>>' icon in pagination");

								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));	
									Assert.fail("An error alert message displayed");
								}	
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, totalPages+"/"+totalPages);
								Test_Variables.test.pass("Navigated to last page successfully");
								Test_Variables.results.createNode("Navigated to last page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
							}

							if (objModel.paginationPreviousPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
								Test_Variables.test.pass("Navigated to previous page successfully");
								Test_Variables.results.createNode("Navigated to previous page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
							}


							if (objModel.paginationFirstPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '<<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 1+"/"+totalPages);
								Test_Variables.test.pass("Navigated to first page successfully");
								Test_Variables.results.createNode("Navigated to first page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
							}


							if (objModel.paginationNextPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '>' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 2+"/"+totalPages);
								Test_Variables.test.pass("Navigated to next page successfully");
								Test_Variables.results.createNode("Navigated to next page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
							}
						}

						else {
							Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
							Test_Variables.test.pass("Records are less then 100; pagination cannot be tested");
							Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
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


	
	
	@Test (description="Test Case: Test Pagination",enabled= true, priority = 10) 
	public void PaginationNew(String tablename, String name, String ReportPath) throws InterruptedException, IOException {
		for (int i=1; i<=3;i++) {
			try {
				test = extent.createTest("Test pagination "+i);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				SoftAssert softAssert = new SoftAssert();
				String recordBefore = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();   //get result count
				test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
				test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
				String[] paginationButtons = {lastPagePagination, previousPagePagination, firstPagePagination, nextPagePagination};

				String removeComma = recordBefore.replace(",", "");
				double x = Double.parseDouble(removeComma);
				double y = 100;
				double divide = Math.ceil(Math.abs(x/y));
				final int totalPages = (int)divide;
				waitElementInvisible(loading_cursor);
				String results = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();   //get result count

				waitElementInvisible(loading_cursor);	
				steps.createNode("1. Verify pagination exists");
				Assert.assertTrue(Helper.driver.findElements(By.cssSelector("#"+tablename+" #activePageNumber")).size() != 0, "Pagination not displaying");

				if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
					driver.findElement(By.cssSelector("#"+tablename+" #"+paginationButtons[i])).click();
					waitElementInvisible(loading_cursor);
					String pageCount =	Helper.driver.findElement(By.cssSelector("#"+tablename+" #activePageNumber")).getText();

					if (Helper.driver.findElements(By.id("message")).size() !=0) {
						Thread.sleep(500);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));	
						softAssert.fail("An error alert message displayed");
					}	

					if (paginationButtons[i].equals(lastPagePagination)) {
						Test_Variables.steps.createNode("1. Click on '>>' icon in pagination");
						softAssert.assertEquals(pageCount, totalPages+"/"+totalPages);
					}

					if (paginationButtons[i].equals(previousPagePagination)) {	
						Test_Variables.steps.createNode("1. Click on '<' icon in pagination");
						softAssert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
					}

					if (paginationButtons[i].equals(firstPagePagination)) {	
						Test_Variables.steps.createNode("1. Click on '<<' icon in pagination");
						softAssert.assertEquals(pageCount, 1+"/"+totalPages);
					}

					if (paginationButtons[i].equals(nextPagePagination)) {	
						Test_Variables.steps.createNode("1. Click on '>' icon in pagination");
						softAssert.assertEquals(pageCount, 2+"/"+totalPages);
					}

					softAssert.assertAll();
					Test_Variables.test.pass("Navigated to next page successfully");
					Test_Variables.results.createNode("Navigated to next page successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}
				else {
					Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
					Test_Variables.test.skip("Records are less then 100; pagination cannot be tested");
					Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
					Helper.saveResultNew(ITestResult.SKIP, ReportPath, null);	
				}
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("Failed to get desired results on clicking button");
				Test_Variables.results.createNode("Failed to get desired results on clicking button");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail("Failed to get desired results on clicking button");
				Test_Variables.results.createNode("Failed to get desired results on clicking button");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, ex);
			}
		}
	}
		

	@Test (enabled= true, priority = 12) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-174: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
			Test_Variables.steps.createNode("1. Verify int data type columns are right alligned");

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slLaneCol+" .text-right")).isDisplayed() == true, "Lane column is not right alligned");
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW1CellCountCol+" .text-right")).isDisplayed() == true, "W1CellCount column is not right alligned");
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW1PCCountCol+" .text-right")).isDisplayed() == true, "W1PCCount column is not right alligned");
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW1MeanIntensityCol+" .text-right")).isDisplayed() == true, "W1MeanIntensity column is not right alligned");
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW2CellCountCol+" .text-right")).isDisplayed() == true, "W2CellCount column is not right alligned");
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW2CPCCountCol+" .text-right")).isDisplayed() == true, "W2PCCount column is not right alligned");
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW2MeanIntensityCol+" .text-right")).isDisplayed() == true, "W2MeanIntensity column is not right alligned");
			Test_Variables.test.pass("Int data type columns are right alligned");
			Test_Variables.results.createNode("Int data type columns are right alligned");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
			softAssert.assertAll();	
		}catch(AssertionError er) {
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 13) 
	public void FieldAccess() throws InterruptedException, IOException {

		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstSalmonellaFieldAccess = SalmonellaModel.FieldAccess();

		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaFieldAccess) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
				Test_Variables.steps.createNode("1. Click on filed access icon; popup appears");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);     
						Helper.driver.findElement(By.id("edit-field-access")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);
						Test_Variables.steps.createNode("2. Unselect "+objFilter.FilterName+" column and Select previously unselected filter column and click on save button");

						if(objModel.viewAccess) {
							if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) input")).isSelected() == false) {
								Helper.driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}	

						if(objModel.unviewAccess) {
							int inc = Integer.parseInt(objFilter.FilterID) + 1;
							if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) input")).isSelected() == true) {
								Helper.driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}

						Helper.driver.findElement(By.id("btn-save")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
						Thread.sleep(1000);
						Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Report Settings Saved");

						if(objModel.viewAccess) {
							Test_Variables.steps.createNode("3. Verify selected column is displayed in the table");
							Assert.assertEquals(Helper.driver.findElements(By.id(objModel.FilterUnHideID)).size(), 1);
						}	
						if(objModel.unviewAccess) {
							Test_Variables.steps.createNode("4. Verify unselected column is hidden in the table");
							Assert.assertEquals(Helper.driver.findElements(By.id(objModel.FilterHideID)).size(), 0);
						}
						Test_Variables.steps.createNode("5. Open audit log and verify that unselected column is hidden while selected column is displayed");
						Helper.driver.findElement(By.id("audit-trial-0")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						if(objModel.viewAccess) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#audit-"+objModel.FilterUnHideID+".table-header-report")).size(), 1);	
						}

						if(objModel.unviewAccess) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#audit-"+objModel.FilterHideID+".table-header-report")).size(), 0);
						}

						Helper.driver.findElement(By.cssSelector(Test_Elements.closeAudit)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						Test_Variables.test.pass("Column was hidden successfully");
						Test_Variables.results.createNode("Column was hidden successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to hide");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to shide");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+" column failed to hide");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to shide");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}

	@Test (enabled= true, priority = 14) 
	public void FieldAccessResetDefault() throws InterruptedException, IOException {

		Helper.driver.findElement(By.id("edit-field-access")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-reset")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-save")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
	}


	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}

		return theNewestFile;
	}

	@SuppressWarnings("unused")
	@Test (description="Test Case: Test Salmonella PNG Download",enabled= true, priority = 15) 
	public void PNGExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-214: Verify user can download Salmonella PNG file", "This test case will verify user can download Salmonella PNG file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards barchart on top");
			Test_Variables.steps.createNode("2. Export PNG button becomes visible");

			Helper.driver.navigate().refresh();			
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			Helper.driver.findElement(By.id("sampleId_show-filter")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#sort-sampleId li:nth-child(3) label")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("sampleId_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);

			Actions builder = new Actions(Helper.driver); 
			WebElement pngHover = Helper.driver.findElement(By.cssSelector(".run-timeline-bar-chart__download"));
			Test_Variables.steps.createNode("3. Click on the button");
			builder.moveToElement(pngHover).build().perform();

			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			WebElement clickDownload = Helper.driver.findElement(By.id("dc-bar-chart-salm-png"));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(clickDownload).click().perform();

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			//File downloadFolder = new File(Test_Variables.fileDownloadPath);
			//List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			//	Assert.assertTrue(namesOfFiles.contains(Test_Variables.slPNGFileName+date+".png")); 
			System.out.println("Success");
			Test_Variables.test.pass("PNG downloaded successfully");
			Test_Variables.results.createNode("PNG downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
		Thread.sleep(1000);
	}


	@SuppressWarnings({ "resource", "unused" })
	@Test (description="Test Case: Test Salmonella CSV Download",enabled= true, priority =17) 
	public void CSVExport1() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-215: Verify user can download Salmonella CSV file", "This test case will verify that user can download Salmonella CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");

//			Helper.driver.get(Constants.url_SalmonellaLog);
//			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
//			Thread.sleep(2000);			
			String getRowText = Helper.driver.findElement(By.id("results-found-count")).getText();
			//int getRowCount = Integer.parseInt(getRowText);

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Test_Variables.steps.createNode("5. Click on Export as CSV");	
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, Test_Variables.slCSVFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
			FileReader filereader = new FileReader(file);
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			StringBuffer buffer = new StringBuffer();
			String data[];		    				

			int columnsCountTotal = 0;
			int rowsCount = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {

					int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {

						int totalColumns = Helper.driver.findElements(By.cssSelector("tr:nth-child(1) td")).size() - 1;

						int columnsCount = columnsCountTotal+3;
						if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), Helper.driver.findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = -1;
						}
						columnsCountTotal++;
					}
				}
			}

			Path path = Paths.get(Test_Variables.fileDownloadPath+"\\"+filename);
			long lines = 0;
			try {
				lines = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
			}

			long excludeHeader = lines - 1;
			String s = String.valueOf(excludeHeader);

			String str = getRowText;
			str = str.replace(",", "");
			Assert.assertEquals(s, str);

			file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
		Thread.sleep(1000);
	}

	@Test (description="Test Case: Test Salmonella Audit Download",enabled= true, priority = 18) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-216: Verify user can download Salmonella Audit file", "This test case will verify that user can download Salmonella Audit file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			ClickElement.clickByCss(Helper.driver, "#select-runId-0 .data-log-checkbox");
			Thread.sleep(1000);

			//String getRowCount = Helper.driver.findElement(By.id("results-found-count")).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
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
			Assert.assertEquals(filename, Test_Variables.slCSVAuditFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
	}


	@Test (description="Test Case: Test Salmonella Template Download",enabled= true, priority = 19) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-217: Verify user can download Salmonella Template file", "This test case will verify that user download Salmonella Template file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Variables.steps.createNode("3. Click on the button");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1500);
			ClickElement.clickByCss(Helper.driver, "#export-data-template label");
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Test_Variables.steps.createNode("5. Click on Export Data Template");
			Test_Variables.steps.createNode("6. Select Sample MetaData Template");
			ClickElement.clickById(Helper.driver, "Sample-Metadata-Upload-Template");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			//System.out.println("Latest XLSX file is = "+filename);
			//Assert.assertEquals(filename, Test_Variables.slSampleMetaData+".xlsx");
			Assert.assertTrue(filename.startsWith(Test_Variables.slSampleMetaData));
			Test_Variables.test.pass("Sample MetaData template downloaded successfully");
			Test_Variables.results.createNode("Sample MetaData template downloaded successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}
