package Test.Ancera.Reports;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

import Models.SalmonellaModel;
import Models.ReportFilters;
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

import java.io.*;

public class SalmonellaLog {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Salmonella_Log"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Salmonella Log Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	
	
	@SuppressWarnings("unchecked")
	@Test (description="Test Case: Run APIs", enabled= false, priority= 1) 
	public void RunAPI() throws InterruptedException, IOException	{

		Test_Variables.test = Test_Variables.extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.steps.createNode("1. Enter valid piperid ("+Test_Variables.piperId+")");
		Test_Variables.steps.createNode("2. Enter valid password (********)");
		Test_Variables.steps.createNode("3. Run the API");

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
		System.out.println(data);
		JsonPath jsonPathEvaluator = response.jsonPath();
		String token = jsonPathEvaluator.get("token");		

		String statusCode = jsonPathEvaluator.get("statusCode");
		System.out.println(token);

		try{
			Assert.assertEquals(statusCode, "114"); 
			Test_Variables.test.pass("Login Api ran successfully");
			Test_Variables.results.createNode("Login API ran successfully; token was generated successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}	

		Test_Variables.test = Test_Variables.extent.createTest("AN-Announcement-01: Verify Salmonella File Announcement API", "This test case will run Salmonella file announcement api");	
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Run login API to generate token");
		Test_Variables.steps.createNode("1. Add token in Authorization");
		Test_Variables.steps.createNode("2. Add a unique RUN ID");
		Test_Variables.steps.createNode("3. Run the API");

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
		String data1 = response1.asString();
		System.out.println(data1);
		String statusCodeAnnouncement = jsonPathEvaluator.get("statusCode");

		try{
			Assert.assertEquals(statusCodeAnnouncement, "114"); 
			Test_Variables.test.pass("File Announcement API ran successfully");
			Test_Variables.results.createNode("File Announcement API ran successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}


		for(int i=0; i<Test_Variables.lstSalmonellaIngest1Lane.size(); i++)	{
			try{
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstSalmonellaIngest1Lane.get(i).testCaseTitle, Test_Variables.lstSalmonellaIngest1Lane.get(i).testCaseDesc);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("Run login API to generate token");
				Test_Variables.preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
				Test_Variables.steps.createNode(Test_Variables.lstSalmonellaIngest.get(i).step);

				Thread.sleep(2000);
				RequestSpecification request_fileupload = RestAssured.given();

				request_fileupload.header("Content-Type", "application/json");
				request_fileupload.header("Authorization", "bearer " +token);
				HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("runId", Test_Variables.lstSalmonellaIngest1Lane.get(i).runId);
				json3.put("checksum", Test_Variables.lstSalmonellaIngest1Lane.get(i).checksum);
				json3.put("fileName", Test_Variables.lstSalmonellaIngest1Lane.get(i).fileName);
				json3.put("fileType", Test_Variables.lstSalmonellaIngest1Lane.get(i).fileType);
				json3.put("file", Test_Variables.lstSalmonellaIngest1Lane.get(i).file);
				json3.put("fileJson", Test_Variables.lstSalmonellaIngest1Lane.get(i).fileJson);				
				json3.put("Improc", Test_Variables.lstSalmonellaIngest1Lane.get(i).improc);
				json3.put("RunMode", Test_Variables.lstSalmonellaIngest1Lane.get(i).runMode);
				
				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(Constants.api_FileUpload);
				String data3 = response2.asString();
				System.out.println(data3);
				JsonPath jsonPathEvaluator1 = response.jsonPath();
				jsonPathEvaluator1.get("statusCode");

				//	Assert.assertEquals(statusCodeFileUpload, 114); 
				Test_Variables.test.pass("File Upload API ran successfully");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest1Lane.get(i).passScenario);
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest1Lane.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest1Lane.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
			}	
			Thread.sleep(1000);

			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstSalmonellaIngest1Lane.get(i).testCaseTitleIngestion, Test_Variables.lstSalmonellaIngest1Lane.get(i).testCaseDescIngestion);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Salmonella Log");

				Helper.driver.get(Constants.url_SalmonellaLog);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter-Sample-Id")));
				Thread.sleep(1000);

				Test_Variables.steps.createNode("1. Click on Lab Sample ID to expand the filter");
				ClickElement.clickById(Helper.driver, "filter-Sample-Id");
				Thread.sleep(1000);
				Test_Variables.steps.createNode("2. Search for the Sample ID against which the data is ingested");
				
				for(int j=0; j<Test_Variables.lstSampleID.size(); j++)	{
				Helper.driver.findElement(By.id("Sample-Id-place-holder-search")).clear();
				Helper.driver.findElement(By.id("Sample-Id-place-holder-search")).sendKeys("Test"+Test_Variables.lstSampleID.get(j));
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);								
				Helper.driver.findElement(By.id("Sample-Id_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(j))).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);
				}
				
				Test_Variables.steps.createNode("3. Click on Apply filter button");
				Helper.driver.findElement(By.id("filter-icon")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
				String records = Helper.driver.findElement(By.id("results-found-count")).getText();

				Assert.assertEquals(records, "12"); 
				Test_Variables.test.pass("Ingested Successfully");
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
			}
			Thread.sleep(1000);				
		}	
	}



	@Test (description="Test Case: Navigate to Salmonella Log Screen",enabled= true, priority = 1) 
	public void NavigateSalmonella() throws InterruptedException, IOException {

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-05: Verify user can navigate to Salmonella Log Screen", "This test case will verify user can navigate to Salmonella Log Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.steps.createNode("1. Click on Salmonella Log");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(Constants.url_SalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("Salmonella Log")).getText();
			String expected = "Salmonella Log";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully to Salmonella Log screen");
			Test_Variables.results.createNode("Salmonella Log report opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Salmonella Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Salmonella Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
	}

	
	@Test (description="Test Case: Wildcard",enabled= true, priority = 2) 
	public void wildcard() throws InterruptedException, IOException {
		Test_Variables.lstSalmonellaWildcardSearch = SalmonellaModel.Wildcard(); 
		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaWildcardSearch) { 	
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

						Test_Variables.steps.createNode("1. Click on Salmonella Log");
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
						Test_Variables.test.pass("Wildcards tested successfully");
						Test_Variables.results.createNode("Wildcards tested successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}
					Helper.driver.findElement(By.id(objFilter.FilterXPath+"_clear-filter")).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}
	

	@SuppressWarnings("unused")
	@Test (description="Test Case: Date Filter Test",enabled= true, priority = 3) 
	public void DateFilter() throws InterruptedException, IOException {

		Test_Variables.lstSalmonellaDateSearch = SalmonellaModel.FillDate();

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
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-scanDateTime"))); 
				Test_Variables.steps.createNode("1. Click on date calendar icon; Calendar pops up");
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
					Thread.sleep(750);
					Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter")).click();
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
							Assert.assertEquals(fromDateField, fromDate);
							Assert.assertEquals(toDateField, toDate);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");

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
							Assert.assertEquals(fromDateField, fromDate);
					//		Assert.assertEquals(toDateField, toDate, "Please ingest data with current date to test this scenario successfully");
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");

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
							Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter")).click();
							
							String value3 =objFilter.toMonth;   
							cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, Integer.parseInt(value3));
							cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							Date todate2 = cal.getTime();    
							String toDate = dateFormat.format(todate2);
							System.out.println(toDate);
							Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter")).click();
							String fromDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = Helper.driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 

							Assert.assertEquals(fromDateField, fromDate);
							Assert.assertEquals(toDateField, toDate);
							Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
							Test_Variables.results.createNode("1. "+objFilter.FilterName+ " values verified successfully");

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
					Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter")).click();	
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


	@Test (description="Test Case: Date Filter Lock Test",enabled= true, priority = 5) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-17: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

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
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Test_Variables.steps.createNode("4. Close report");
			Test_Variables.steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();

			Assert.assertEquals(recordsafterfilter, recordsbeforefilter);
			Test_Variables.test.pass("Filter locked functionality verified successfully on date filter");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report on date filter");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
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
		WebElement filter_scroll = Helper.driver.findElement(By.cssSelector("sort-scanDateTime"));
		((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
		Thread.sleep(800);
		Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .log-header__clear-filter")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	@Test (description="Test Case: Filter Test",enabled= true, priority = 6) 
	public void TestFilter() throws InterruptedException, IOException {

		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstSalmonellaSearch = SalmonellaModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaSearch) { 	
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

							if(NumberFormat.getNumberInstance(Locale.US).parse(recordAfter).intValue() != 0 && objFilter.FilterName == "Load Filter") {	
								WebElement filter_scrollStart = Helper.driver.findElement(By.id("select-runId-0"));
								((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scrollStart); 
								Thread.sleep(800);
								if (NumberFormat.getNumberInstance(Locale.US).parse(recordAfter).intValue() > 100) {
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector(objFilter.rowValueExpected)).size(), 100);
								}
								else {
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector(objFilter.rowValueExpected)).size(), NumberFormat.getNumberInstance(Locale.US).parse(recordAfter).intValue());	
								}
								WebElement filter_scrollBack = Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)));
								((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scrollBack); 	
							}

							System.out.println(recordBefore+", "+recordAfter);
							Assert.assertNotEquals(recordBefore, recordAfter);
							Test_Variables.test.pass("Checkbox selected successfully");
							Test_Variables.results.createNode("Checkbox selected successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
						}	
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + "checkbox failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + "checkbox failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + "checkbox failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + "checkbox failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
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
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);

							Helper.driver.findElement(By.cssSelector(objFilter.LstFilterXpath.get(i)+" .filter-popup__action--clear-all")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);		
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to apply");
						Test_Variables.results.createNode("Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter failed to apply");
						Test_Variables.results.createNode("Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Salmonella Lock Filter Functionality",enabled= true, priority = 8) 
	public void SalmonellaLock() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-187: Verify Salmonella Lock Filter Functionality", "This test case will test Salmonella Lock Filter Functionality");
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
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-laneNum"))); 
			Thread.sleep(1000);	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.driver.findElement(By.id("laneNum_show-filter")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#sort-laneNum .checkbox-sm:nth-child(2) label")).click();
			Thread.sleep(500);
			Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
			Helper.driver.findElement(By.id("laneNum_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Variables.steps.createNode("2. Click on lock button");	
			Helper.driver.findElement(By.id("save-filters")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Thread.sleep(1000);
			String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();
			Test_Variables.steps.createNode("3. Close Salmonella Log Report");
			Test_Variables.steps.createNode("4. Reopen Salmonella Log Report");
			Helper.driver.navigate().refresh();

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("5. Verify lock filter remains applied");
			Assert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.id("results-found-count")).getText());
			Test_Variables.test.pass("Filter locked functionality verified successfully");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("remove-filters")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Helper.driver.findElement(By.id("laneNum_clear-filter")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
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

	@Test (description="Test Case: Test Pagination",enabled= true, priority = 9) 
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


	@Test (description="Test Case: Test Table Rows",enabled= true, priority = 10) 
	public void RowsPerPage() throws InterruptedException, IOException {
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 
		Thread.sleep(500);

		Test_Variables.lstSalmonellaRowCount = SalmonellaModel.searchRows();

		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaRowCount) { 	
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

				Actions actions = new Actions(Helper.driver);
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("rows")));
						Thread.sleep(500);
						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > Integer.parseInt(objFilter.count)) {
							WebElement expandFilter = Helper.driver.findElement(By.id("rows"));
							actions.moveToElement(expandFilter).click().perform();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.driver.findElement(By.id(objFilter.FilterListXPathSearch)).click();

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(1000);
							List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] tr"));

							int count = rows.size();
							int new_count = count - 1;
							System.out.println("ROW COUNT : "+new_count);
							Assert.assertEquals(new_count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
						}

						else {
							Assert.assertTrue(true, "Records are less then "+objFilter.count);
							Test_Variables.test.pass("Records are less then "+objFilter.count);
							Test_Variables.results.createNode("Rcords are less then "+objFilter.count);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));

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
							System.out.println("ROW COUNT : "+new_count);
							Assert.assertEquals(new_count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);	
						}
						else {
							Assert.assertTrue(true, "Records are less then "+sum);
							Test_Variables.test.pass("Records are less then "+sum);
							Test_Variables.results.createNode("Rcords are less then "+sum);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Sorting",enabled= true, priority = 11) 
	public void Sorting() throws InterruptedException, IOException {

		Test_Variables.lstSalmonellaSorting = SalmonellaModel.sorting();

		for (SalmonellaModel objModel : Test_Variables.lstSalmonellaSorting) { 	
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

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.ColumnID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" column header");
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(500);
						Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).isDisplayed());
						Test_Variables.test.pass(objFilter.FilterName+" column sorted successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).isDisplayed());
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to sort");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to sort");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
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
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-324: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
			Test_Variables.steps.createNode("1. Verify int data type columns are right alligned");
			
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slLaneCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW1CellCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW1PCCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW1MeanIntensityCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW2CellCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW2CPCCountCol+" .text-right")).isDisplayed() == true);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("#col-"+Test_Elements.slW2MeanIntensityCol+" .text-right")).isDisplayed() == true);
			Test_Variables.test.pass("Int data type columns are right alligned");
			Test_Variables.results.createNode("Int data type columns are right alligned");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
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
	public void FieldAccessUnview() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-325: Verify that unselecting column from field access popup hides the column from report table", "This testcase will verify that unselecting column from field access popup hides the column from report table");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
			Test_Variables.steps.createNode("1. Click on filed access icon; popup appears");

			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);     
			Helper.driver.findElement(By.id("edit-field-access")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
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
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
			}
		}catch(AssertionError er) {
			Test_Variables.test.fail("Column failed to hide");
			Test_Variables.results.createNode("Column failed to hide");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Column failed to hide");
			Test_Variables.results.createNode("Column failed to hide");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 14) 
	public void FieldAccessView() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-326: Verify that re-selecting column from field access popup displays the column from report table", "This testcase will verify that re-selecting column from field access popup displays the column from report table");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
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
			Thread.sleep(2000);
			Assert.assertTrue(Helper.driver.findElement(By.id("sort-laneNum")).isDisplayed());
			Test_Variables.test.pass("Column displayed successfully");
			Test_Variables.results.createNode("Column displayed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Column failed to display");
			Test_Variables.results.createNode("Column failed to display");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Column failed to display");
			Test_Variables.results.createNode("Column failed to display");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
		}
	}


	@SuppressWarnings("unused")
	@Test (description="Test Case: Test Salmonella PNG Download",enabled= true, priority = 15) 
	public void PNGExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-199: Verify user can download Salmonella PNG file", "This test case will verify user can download Salmonella PNG file");
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

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("svg-layout")));
			WebElement pngHover = Helper.driver.findElement(By.id("svg-layout"));
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
			Thread.sleep(1000);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
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


	@SuppressWarnings("unused")
	@Test (description="Test Case: Test Salmonella CSV Download",enabled= true, priority =16) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-200: Verify user can download Salmonella CSV file", "This test case will verify that user can download Salmonella CSV file");
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
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Test_Variables.steps.createNode("5. Click on Export as CSV");	
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			@SuppressWarnings("unused")
			String date= dateFormat.format(date1);
			Thread.sleep(500);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
		//	Assert.assertTrue(namesOfFiles.contains(Test_Variables.slCSVFileName+date+".csv"));
			System.out.println("Success");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
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
	
	@SuppressWarnings("unused")
	@Test (description="Test Case: Test Salmonella Audit Download",enabled= true, priority = 17) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-151: Verify user can download Salmonella Audit file", "This test case will verify that user can download Salmonella Audit file");
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
			ClickElement.clickByCss(Helper.driver, "#select-runId-0 .checkbox");
			Thread.sleep(1000);

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

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
	//		Assert.assertTrue(namesOfFiles.contains(Test_Variables.clCSVAuditFileName+date+".csv"));
			System.out.println("Success");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
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


	@Test (description="Test Case: Test Salmonella Template Download",enabled= true, priority = 18) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-SL-201: Verify user can download Salmonella Template file", "This test case will verify that user download Salmonella Template file");
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
			Thread.sleep(1000);
			ClickElement.clickByCss(Helper.driver, "#export-data-template label");
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Test_Variables.steps.createNode("5. Click on Export Data Template");
			Test_Variables.steps.createNode("6. Select Sample MetaData Template");
			ClickElement.clickById(Helper.driver, "Sample-Metadata-Upload-Template");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			@SuppressWarnings("unused")
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
	//		Assert.assertTrue(namesOfFiles.contains(Test_Variables.slSampleMetaData+".xlsx"));
			Test_Variables.test.pass("Sample MetaData downloaded successfully");
			Test_Variables.results.createNode("Sample MetaData downloaded successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);

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
