package Test.Ancera.Reports;

import java.io.File;
import java.io.IOException;
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

import Models.ExternalSalmonellaModel;
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

public class ExternalSalmonella {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/External_Salmonella"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Reports Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}

	@SuppressWarnings("unchecked")
	@Test (description="Test Case: Run APIs", enabled= false, priority= 1) 
	public void RunAPI() throws InterruptedException, IOException	{

		Test_Variables.test = Test_Variables.extent.createTest("AN-API_Login: Verify Login API", "This test case will run login api and verify that token is generated or not");
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
		json.put("DISAPIVersion", "14.12.1");
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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
		}catch(AssertionError e){
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
		}	

		Test_Variables.test = Test_Variables.extent.createTest("AN-API_Announcement: Verify Salmonella File Announcement API", "This test case will run Salmonella file announcement api");	
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Run login API to generate token");
		Test_Variables.steps.createNode("1. Add token in Authorization");
		Test_Variables.steps.createNode("2. Add a unique RUN ID");
		Test_Variables.steps.createNode("3. Run the API");

		Thread.sleep(1000);
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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
		}catch(AssertionError e){
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
		}	

		for(int i=0; i<Test_Variables.lstExternalSalmonellaIngest.size(); i++)	{
			try{
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstExternalSalmonellaIngest.get(i).testCaseTitle, Test_Variables.lstExternalSalmonellaIngest.get(i).testCaseDesc);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("Run login API to generate token");
				Test_Variables.preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
				Test_Variables.steps.createNode(Test_Variables.lstExternalSalmonellaIngest.get(i).step);

				Thread.sleep(1000);
				RequestSpecification request_fileupload = RestAssured.given();

				request_fileupload.header("Content-Type", "application/json");
				request_fileupload.header("Authorization", "bearer " +token);

				HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("runId", Test_Variables.lstExternalSalmonellaIngest.get(i).runId);
				json3.put("checksum", Test_Variables.lstExternalSalmonellaIngest.get(i).checksum);
				json3.put("fileName", Test_Variables.lstExternalSalmonellaIngest.get(i).fileName);
				json3.put("fileType", Test_Variables.lstExternalSalmonellaIngest.get(i).fileType);
				json3.put("file", Test_Variables.lstExternalSalmonellaIngest.get(i).file);
				json3.put("fileJson", Test_Variables.lstExternalSalmonellaIngest.get(i).fileJson);				
				json3.put("Improc", Test_Variables.lstExternalSalmonellaIngest.get(i).improc);

				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(Constants.api_FileUpload);
				String data3 = response2.asString();
				System.out.println(data3);

				JsonPath jsonPathEvaluator1 = response.jsonPath();
				jsonPathEvaluator1.get("statusCode");

				//	Assert.assertEquals(statusCodeFileUpload, 114); 
				Test_Variables.test.pass("File Upload API ran successfully");
				Test_Variables.results.createNode(Test_Variables.lstExternalSalmonellaIngest.get(i).passScenario);
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
			}	

			Helper.driver.get(Constants.url_ExternalSalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset-icon")));
			Thread.sleep(500);
			try{
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstExternalSalmonellaIngest.get(i).testCaseTitleIngestion, Test_Variables.lstExternalSalmonellaIngest.get(i).testCaseDescIngestion);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log report opens");

				Test_Variables.steps.createNode("1. Click on Lab Sample ID to expand the filter");
				Helper.driver.findElement(By.id("filter-Lab-Sample-ID")).click();
				Thread.sleep(500);
				Test_Variables.steps.createNode("2. Search for the Sample ID against which the data is ingested");
				Helper.driver.findElement(By.id("Lab-Sample-ID-place-holder-search")).sendKeys("Test"+Test_Variables.lstSampleID.get(i));
				Thread.sleep(500);
				Helper.driver.findElement(By.id("Lab-Sample-ID_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(i))).click();;
				Thread.sleep(500);
				Test_Variables.steps.createNode("3. Click on Apply filter button");
				Helper.driver.findElement(By.id("filter-icon")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
				String records = Helper.driver.findElement(By.id("results-found-count")).getText();

				Assert.assertEquals(records, "1"); 
				Test_Variables.test.pass("Ingested Successfully");
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
			}					
		}	
	}


	@Test (description="Test Case: Navigate to External Salmonella Log Screen",enabled=true, priority = 2) 
	public void NavigateExternalSalmonella() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-ESL-04: Navigate to External Salmonella Log Screen", "This test case will navigate to Salmonella Log Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.steps.createNode("1. Click on External Salmonella Log");

			Helper.driver.get(Constants.url_ExternalSalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
			String actual = Helper.driver.findElement(By.id("External Salmonella log")).getText();
			String expected = "External Salmonella Log";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("External Salmonella Log report opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("External Salmonella Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("External Salmonella Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
		}	
	}


	@Test (description="Test Case: Date Filter Test",enabled= true, priority = 3) 
	public void DateFilter() throws InterruptedException, IOException {

		Test_Variables.lstExternalSalmonellaDateSearch = ExternalSalmonellaModel.FillDate();

		for (ExternalSalmonellaModel objModel : Test_Variables.lstExternalSalmonellaDateSearch) { 
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

				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("calendarIcon"))); 
				Test_Variables.steps.createNode("1. Click on date calendar icon; Calendar pops up");
				ClickElement.clickById(Helper.driver, "calendarIcon");	
				Thread.sleep(500);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

				Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
				actions.moveToElement(Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSearch))).click().perform();	
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));

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

						Thread.sleep(1000);
						Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 
						
						Assert.assertEquals(fromDateField, fromDate);
						Assert.assertEquals(toDateField, toDate);
						Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
						Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");

					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
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

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("ExternalSalmonella Log", Constants.ExternalSalmonellaReportPath));
						String fromDateField = Helper.driver.findElement(By.id("filterDateFrom")).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.id("filterDateTo")).getAttribute("value");

						Thread.sleep(1000);
						Test_Variables.steps.createNode("3. Verify the dates in To and From field");

						Assert.assertEquals(fromDateField, fromDate);
						Assert.assertEquals(toDateField, toDate);
						Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
						Test_Variables.results.createNode(objFilter.FilterName+ " values verified successfully");

					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
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

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						String fromDateField = Helper.driver.findElement(By.id("filterDateFrom")).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.id("filterDateTo")).getAttribute("value");

						Thread.sleep(1000);
						Test_Variables.steps.createNode("3. Verify the dates in To and From field"); 

						Assert.assertEquals(fromDateField, fromDate);
						Assert.assertEquals(toDateField, toDate);
						Test_Variables.test.pass(objFilter.FilterName+ " values verified successfully");
						Test_Variables.results.createNode("1. "+objFilter.FilterName+ " values verified successfully");

					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}
				}

				String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
				try {

					Test_Variables.steps.createNode("4. Click on Apply filter button");
					ClickElement.clickById(Helper.driver, "filter-icon");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

					String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();
					Assert.assertNotEquals(recordBefore, recordAfter);  
					Test_Variables.test.pass(objFilter.FilterName+" applied successfully");
					Test_Variables.results.createNode("2. "+objFilter.FilterName+" applied successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
				}catch(AssertionError er) {
					Test_Variables.test.fail(objFilter.FilterName+" failed to apply");
					Test_Variables.results.createNode(objFilter.FilterName+" failed to apply");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
				}catch(Exception ex){
					Test_Variables.test.fail(objFilter.FilterName+" failed to apply");
					Test_Variables.results.createNode(objFilter.FilterName+" failed to apply");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
				}
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				ClickElement.clickById(Helper.driver, "reset-icon");
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			}
		}
	}


	@Test (description="Test Case: Date Enter",enabled= true, priority = 4) 
	public void EnterDate() throws InterruptedException, IOException {

		Test_Variables.lstExternalSalmonellaDateEnter = ExternalSalmonellaModel.EnterDate();

		for (ExternalSalmonellaModel objModel : Test_Variables.lstExternalSalmonellaDateEnter) { 

			Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 

			for (ReportFilters objFilter : objModel.lstFilters) {

				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.steps.createNode("1. "+objFilter.FilterName);
				Helper.driver.findElement(By.id("filterDateFrom")).clear();
				Helper.driver.findElement(By.id("filterDateFrom")).sendKeys(objFilter.fromDate);
				Thread.sleep(500);
				Helper.driver.findElement(By.id("filterDateTo")).clear();
				Helper.driver.findElement(By.id("filterDateTo")).sendKeys(objFilter.toDate);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));	

				Thread.sleep(500);
				Test_Variables.steps.createNode("2. Click on Apply filter button");
				ClickElement.clickById(Helper.driver, "filter-icon");
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				
				if(objModel.Filter1) {
					try {
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
						String actual = Helper.driver.findElement(By.id("message")).getText();
						String expected = objFilter.alertMessage;
						Assert.assertEquals(actual, expected); 
						Test_Variables.test.pass("Filter was not applied successfully");
						Test_Variables.results.createNode("1. Filter was not applied successfully");	
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));	
						Thread.sleep(500);	
					}catch(AssertionError er) {
						Test_Variables.test.fail("Filter was applied with invalid date or did not receive an alert message");
						Test_Variables.results.createNode("1. Filter was applied with invalid date or did not receive an alert message");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Filter was applied with invalid date or did not receive an alert message");
						Test_Variables.results.createNode("1. Filter was applied with invalid date or did not receive an alert message");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}
				}


				if(objModel.Filter2) {
					try {
						String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();
						Assert.assertNotEquals(recordBefore, recordAfter); 
						Test_Variables.test.pass("Filter was applied successfully");
						Test_Variables.results.createNode("1. Filter was applied successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
						ClickElement.clickById(Helper.driver, "reset-icon");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					}catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to apply successfully");
						Test_Variables.results.createNode("1. Filter failed to apply successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Filter failed to apply successfully");
						Test_Variables.results.createNode("1. Filter failed to apply successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}			
				}	
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= true, priority = 5) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-ESL-15: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-icon")));
			Test_Variables.steps.createNode("1. Enter valid date in from and 2 fields");
			Helper.driver.findElement(By.id("filterDateFrom")).clear();
			Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("10/01/2020");
			Helper.driver.findElement(By.id("filterDateTo")).clear();
			Helper.driver.findElement(By.id("filterDateTo")).sendKeys("12/01/2020");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
			Test_Variables.steps.createNode("2. Click on Apply filter button");
			ClickElement.clickById(Helper.driver, "filter-icon");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Variables.steps.createNode("3. Click on Lock button");
			WebElement button = Helper.driver.findElement(By.id("save-icon"));
			JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
			jse.executeScript("arguments[0].click()", button);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			String recordsbeforefilter = Helper.driver.findElement(By.id("results-found-count")).getText(); 
			Thread.sleep(500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
			Test_Variables.steps.createNode("4. Close report");
			Test_Variables.steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			Helper.driver.get(Constants.url_ExternalSalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-icon")));
			String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();

			Assert.assertEquals(recordsafterfilter, recordsbeforefilter);
			Test_Variables.test.pass("Filter locked functionality verified successfully on date filter");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report on date filter");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
			ClickElement.clickById(Helper.driver, "un-save-icon");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			ClickElement.clickById(Helper.driver, "reset-icon");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
		}
	}

	
	
	@SuppressWarnings("unused")
	@Test (description="Test Case: Filter Test",enabled= true, priority = 6) 
	public void TestFilters111() throws InterruptedException, IOException {

		Test_Variables.lstExternalSalmonellaSearch = ExternalSalmonellaModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
		for (ExternalSalmonellaModel objModel : Test_Variables.lstExternalSalmonellaSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameButtonActive, objModel.TestCaseDescriptionButtonActive);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");

				Actions actions = new Actions(Helper.driver);
				for (ReportFilters objFilter : objModel.lstFilters) {			
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id(objFilter.FilterXPath)));

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterXPath));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Thread.sleep(500);	
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						
						for(int i = 0; i<objFilter.LstFilterSearch.size(); i++) {
							WebElement expandFilter = Helper.driver.findElement(By.id("filter-"+objFilter.LstFilterXpath.get(i)));
							actions.moveToElement(expandFilter).click().perform();				
							Thread.sleep(500);						
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Test_Variables.steps.createNode("2. Enter value to search ("+objFilter.LstFilterValues.get(i)+")");
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+"-place-holder-search")).clear();
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+"-place-holder-search")).sendKeys(objFilter.LstFilterSearch.get(i));  
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Thread.sleep(500);
						}

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						int chkCounter = 0;
	
						for (int i = 0; chkCounter < objFilter.LstFilterValues.size() && i < 4000; i++) {
							Test_Variables.steps.createNode("3. Select the checkbox and verify that apply filter button becomes active or not");
							int attempts = 0;
							while(attempts < 4) {
								try {
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
									ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(i));
									break;
								} catch(StaleElementReferenceException e) {
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
									ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(i));
								} 
								attempts++;
							}					   
							chkCounter++;
						}

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						WebElement filter_button_scroll = Helper.driver.findElement(By.id("filter-icon"));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_button_scroll); 

						Assert.assertTrue(Helper.driver.findElements(By.cssSelector("button.btn-background-solid#filter-icon")).size() != 0);
						Test_Variables.test.pass("Checkbox selected successfully and Apply filter button becomes active");
						Test_Variables.results.createNode("Checkbox selected successfully and Apply filter button becomes active");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
					}		
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);						
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));

						Test_Variables.steps.createNode("1. Click on apply filter button");	
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						WebElement element = Helper.driver.findElement(By.id("filter-icon"));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", element); 
						Thread.sleep(500);

						ClickElement.clickById(Helper.driver, "filter-icon");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1500);
						String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();		

						if(NumberFormat.getNumberInstance(Locale.US).parse(recordAfter).intValue() != 0 && objFilter.FilterName == "Load Filter") {
							String getRow = Helper.driver.findElement(By.xpath(objFilter.getRowValue)).getAttribute("class");
							Assert.assertEquals(getRow, objFilter.rowValueExpected);			
						}

						if (Helper.driver.findElements(By.cssSelector("div#"+objFilter.LstFilterXpath.get(0)+"-group-head i.filters-clear")).size() !=0) {
							Actions builder = new Actions(Helper.driver); 
							WebElement hover = Helper.driver.findElement(By.cssSelector("div#"+objFilter.LstFilterXpath.get(0)+"-group-head i.filters-clear"));
							builder.moveToElement(hover).build().perform();	
						}

						System.out.println(recordBefore+", "+recordAfter);
						Assert.assertNotEquals(recordBefore, recordAfter);
						Test_Variables.test.pass("Filter applied successfully");
						Test_Variables.results.createNode("Filter applied successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
					}		
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to apply");
						Test_Variables.results.createNode("Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter failed to apply");
						Test_Variables.results.createNode("Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and apply filter");

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.steps.createNode("1. Verify blue filter indicator next to applied filter/s");	
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterXpath.size() && i < 20; i++) {

							try {
								Assert.assertTrue(Helper.driver.findElements(By.id("-"+objFilter.LstFilterXpath.get(i)+"-filter-indicator")).size() != 0 || Helper.driver.findElements(By.cssSelector("div#"+objFilter.LstFilterXpath.get(0)+"-group-head i.filters-clear")).size() !=0);
								Assert.assertTrue(Helper.driver.findElements(By.cssSelector("button.btn-background-solid#filter-icon")).size() == 0);
								Test_Variables.test.pass("Blue filter indicator appears next to applied filter and apply filter button becomes inactive successfully");
								Test_Variables.results.createNode("Blue filter indicator appears next to applied filter and apply filter button becomes inactive successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
								break;
							} catch(StaleElementReferenceException e) {
								Assert.assertTrue(Helper.driver.findElements(By.id("-"+objFilter.LstFilterXpath.get(i)+"-filter-indicator")).size() != 0 || Helper.driver.findElements(By.cssSelector("div#"+objFilter.LstFilterXpath.get(0)+"-group-head i.filters-clear")).size() !=0);
								Assert.assertTrue(Helper.driver.findElements(By.cssSelector("button.btn-background-solid#filter-icon")).size() == 0);
							} 
							catch(AssertionError er) {
								Test_Variables.test.fail("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Test_Variables.results.createNode("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
							}
							catch(Exception ex) {
								Test_Variables.test.fail("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Test_Variables.results.createNode("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
							}					   
							chkCounter++;
						}
					}
					catch(Exception ex) {
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameBubbleFilterTop, objModel.TestCaseDescriptionBubbleFilterTop);

						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.steps.createNode("1. Verify filter pops to top of filter list");

						Assert.assertTrue(Helper.driver.findElements(By.cssSelector("div.order-1 span#"+objFilter.FilterXPath)).size() != 0);
						Test_Variables.test.pass("Filter bubbles to top of filter list successfully on applying");
						Test_Variables.results.createNode("Filter bubbles to top of filter list successfully on applying");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to bubble to top of filter list on applying");
						Test_Variables.results.createNode("Filter failed to bubble to top of filter list on applying");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter failed to bubble to top of filter list on applying");
						Test_Variables.results.createNode("Filter failed to bubble to top of filter list on applying");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameBubbleFilterCheckbox, objModel.TestCaseDescriptionBubbleFilterCheckbox);

						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify checkbox selected pops to top of filter checkbox list");

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterValues.size() && i < 5000; i++) {

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Assert.assertTrue(Helper.driver.findElements(By.cssSelector("li.order-1 p#"+objFilter.LstFilterXpath.get(i)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(i))).size() != 0);
							Test_Variables.test.pass("Selected filter checkbox bubbles to top of filter list successfully on applying filter");
							Test_Variables.results.createNode("Selected filter checkbox bubbles to top of filter list successfully on applying filter");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);

							break; 
						}
						chkCounter++;
					}

					catch(AssertionError er) {
						Test_Variables.test.fail("Selected filter checkbox failed to move to top of filter list on applying filter");
						Test_Variables.results.createNode("Selected filter checkbox failed to move to top of filter list on applying filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Selected filter checkbox failed to move to top of filter list on applying filter");
						Test_Variables.results.createNode("Selected filter checkbox failed to move to top of filter list on applying filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Click on apply filter button");

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(500);
						Test_Variables.steps.createNode("1. Click on cross icon next to entered text in search field");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));

						for (int i = 0; i< objFilter.LstFilterSearch.size(); i++) {
							ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+"-clear-input");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							String a = Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+"-place-holder-search")).getText();
							Assert.assertTrue(a.contains(""));

							WebElement closeSearch = Helper.driver.findElement(By.id("filter-"+objFilter.LstFilterXpath.get(i)));
							actions.moveToElement(closeSearch).click().perform();
							Thread.sleep(500);
						}

						Test_Variables.test.pass("Input search field cleared successfully");
						Test_Variables.results.createNode("1. Search field cleared successfully on clicking cross icon");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameHoverReset, objModel.TestCaseDescriptionHoverReset);

						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.preconditions.createNode("6. Apply "+objFilter.FilterName);

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
		
						Thread.sleep(500);	

						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterXpath.size(); i++) {

							Test_Variables.steps.createNode("1. Hover to blue indicator next to applied filter; blue indicator changes to cross icon");
							Test_Variables.steps.createNode("2. Click on the blue indicator icon");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							
							if (Helper.driver.findElements(By.id("-"+objFilter.LstFilterXpath.get(i)+"-filter-indicator")).size() != 0) {
								Helper.driver.findElement(By.id("-"+objFilter.LstFilterXpath.get(i)+"-filter-indicator")).click();
							}

							if (Helper.driver.findElements(By.cssSelector("div#"+objFilter.LstFilterXpath.get(i)+"-group-head i.filters-clear")).size() != 0) {
								Helper.driver.findElement(By.cssSelector("div#"+objFilter.LstFilterXpath.get(i)+"-group-head i.filters-clear")).click();
							}

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							chkCounter++;
							System.out.println(recordBefore);
						}

						Assert.assertEquals(Helper.driver.findElement(By.id("results-found-count")).getText(), recordBefore);			
						Test_Variables.test.pass("Filter records reset successfully on clicking blue indicator icon");
						Test_Variables.results.createNode("Filter records reset successfully on clicking blue indicator icon");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter records failed to reset on clicking blue indicator icon");
						Test_Variables.results.createNode("Filter records failed to reset on clicking blue indicator icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter records failed to reset on clicking blue indicator icon");
						Test_Variables.results.createNode("Filter records failed to reset on clicking blue indicator icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}					   

					
					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameRevertBack, objModel.TestCaseDescriptionRevertBack);

						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on  External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Click on apply filter button; selected filter moves to the top");

						Test_Variables.steps.createNode("1. Click on reset button");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
					//	ClickElement.clickById(Helper.driver, "reset-icon");
					//	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
					//	Thread.sleep(500);

						for (int i = 0; i<objFilter.LstFilterXpath.size(); i++) { 
							Assert.assertTrue(Helper.driver.findElements(By.cssSelector("div.order-2 span#filter-"+objFilter.LstFilterXpath.get(i))).size() != 0);	
						}

						Test_Variables.test.pass("Filter reverts back to its position successfully on resetting filter");
						Test_Variables.results.createNode("Filter reverts back to its position successfully on resetting filter");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);							
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to revert back to its position on resetting filter");
						Test_Variables.results.createNode("Filter failed to revert back to its position on resetting filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter failed to revert back to its position on resetting filter");
						Test_Variables.results.createNode("Filter failed to revert back to its position on resetting filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}

					if(objModel.ReloadPage) {
						Helper.driver.get(Constants.url_ExternalSalmonellaLog);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-Instrument-ID")));
					}
					if(objFilter.FilterName == "Load Filter") {
						Helper.driver.findElement(By.id("reset-icon")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					}
					Thread.sleep(500);
				}
			}
			catch(Exception ex) {
			}
		}
	}

	
	@Test (description="Test Case: Test External Salmonella Lock Filter Functionality",enabled= true, priority = 7) 
	public void SalmonellaLock() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ESL-201: Verify Salmonella Lock Filter Functionality", "This test case will test Salmonella Lock Filter Functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset-icon")));
			Helper.driver.findElement(By.id("filterDateFrom")).clear();
			Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("12/01/2020");
			Thread.sleep(500);
			Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
			WebElement applyFilter = Helper.driver.findElement(By.id("filter-icon"));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(applyFilter).click().perform();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Variables.steps.createNode("2. Click on lock button");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));	
			WebElement lockButton = Helper.driver.findElement(By.id("save-icon"));
			actions.moveToElement(lockButton).click().perform();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));

			String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();  
			Thread.sleep(500);
			Test_Variables.steps.createNode("3. Close External Salmonella Log Report");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
			Test_Variables.steps.createNode("4. Reopen External Salmonella Log Report");
			Helper.driver.get(Constants.url_ExternalSalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count")));
			Test_Variables.steps.createNode("5. Verify lock filter remains applied");

			Assert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.id("results-found-count")).getText());
			Test_Variables.test.pass("Filter locked functionality verified successfully");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
		}
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		ClickElement.clickById(Helper.driver, "un-save-icon");
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		ClickElement.clickById(Helper.driver, "reset-icon");
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	@Test (description="Test Case: Test Pagination",enabled= true, priority = 8) 
	public void Pagination() throws InterruptedException, IOException {
		Test_Variables.lstExternalSalmonellaPagination = ExternalSalmonellaModel.pagination();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
		Thread.sleep(500);
		Helper.driver.findElement(By.id("filterDateFrom")).clear();
		Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("09/01/2020");
		Helper.driver.findElement(By.id("filter-icon")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		for (ExternalSalmonellaModel objModel : Test_Variables.lstExternalSalmonellaPagination) { 	
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
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						String recordNumber = Helper.driver.findElement(By.id("results-found-count")).getText();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));

						String removeComma = recordNumber.replace(",", "");
						double x = Double.parseDouble(removeComma);
						double y = 100;
						double divide = Math.ceil(Math.abs(x/y));
						final int totalPages = (int)divide;
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
						//	Helper.driver.findElement(By.id(objFilter.paginationPage)).click();
							ClickElement.clickById(Helper.driver, objFilter.paginationPage);

							if (objModel.paginationExist) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Verify pagination exists");
								Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
								Assert.assertTrue(Helper.driver.findElements(By.id("activePageNumber")).size() != 0);
								Test_Variables.test.pass("Pagination displayed successfully");
								Test_Variables.results.createNode("Pagination displayed successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);		
							}

							if (objModel.paginationLastPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Click on '>>' icon in pagination");
								if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
									Thread.sleep(1000);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
									Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
									Assert.fail("An error alert message displayed");
								}
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, totalPages+"/"+totalPages);
								Test_Variables.test.pass("Navigated to last page successfully");
								Test_Variables.results.createNode("Navigated to last page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);

							}

							if (objModel.paginationPreviousPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Click on '<' icon in pagination");
								if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
									Thread.sleep(1000);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
									Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
									Assert.fail("An error alert message displayed");
								}
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
								Test_Variables.test.pass("Navigated to previous page successfully");
								Test_Variables.results.createNode("Navigated to previous page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
							}


							if (objModel.paginationFirstPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Variables.steps.createNode("1. Click on '<<' icon in pagination");
								if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
									Thread.sleep(1000);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
									Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
									Assert.fail("An error alert message displayed");
								}
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 1+"/"+totalPages);
								Test_Variables.test.pass("Navigated to first page successfully");
								Test_Variables.results.createNode("Navigated to first page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
							}


							if (objModel.paginationNextPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));						
								Test_Variables.steps.createNode("1. Click on '>' icon in pagination");
								if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
									Thread.sleep(1000);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
									Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
									Assert.fail("An error alert message displayed");
								}
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 2+"/"+totalPages);
								Test_Variables.test.pass("Navigated to next page successfully");
								Test_Variables.results.createNode("Navigated to next page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
							}
						}
						else {
							Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
							Test_Variables.test.pass("Records are less then 100; pagination cannot be tested");
							Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		ClickElement.clickById(Helper.driver, "first-page");
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	@Test (description="Test Case: Test Table Rows",enabled= true, priority = 9) 
	public void RowsPerPage() throws InterruptedException, IOException {

		Test_Variables.lstExternalSalmonellaRowCount = ExternalSalmonellaModel.searchRows();

		for (ExternalSalmonellaModel objModel : Test_Variables.lstExternalSalmonellaRowCount) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");

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
							Thread.sleep(500);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Helper.driver.findElement(By.id(objFilter.FilterListXPathSearch)).click();;  			
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1500);
							List<WebElement> rows = Helper.driver.findElements(By.xpath("//table[@class='dc-chart']/tbody/tr"));
							int count = rows.size();
							System.out.println("ROW COUNT : "+count);
							Assert.assertEquals(count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
						}

						else {
							Assert.assertTrue(true, "Records are less then "+objFilter.count);
							Test_Variables.test.pass("Records are less then "+objFilter.count);
							Test_Variables.results.createNode("Rcords are less then "+objFilter.count);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));

						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						Test_Variables.steps.createNode("2. Go to next page from pagination");
						Test_Variables.steps.createNode("3. Verify that still "+objFilter.FilterName+" is selected");	
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();
						int sum = Integer.parseInt(objFilter.count) + Integer.parseInt(objFilter.count);

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > sum) {

							ClickElement.clickById(Helper.driver, "next-page");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							List<WebElement> rows = Helper.driver.findElements(By.xpath("//table[@class='dc-chart']/tbody/tr"));
							int count = rows.size();
							System.out.println("ROW COUNT : "+count);
							Assert.assertEquals(count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);	
						}

						else {
							Assert.assertTrue(true, "Records are less then "+sum);
							Test_Variables.test.pass("Records are less then "+sum);
							Test_Variables.results.createNode("Rcords are less then "+sum);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test External Salmonella PNG Download",enabled= true, priority = 10) 
	public void PNGExport() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ESL-213: Verify user can download External Salmonella PNG file", "This test case will verify user can download External Salmonella PNG file");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
			Thread.sleep(500);
			Helper.driver.findElement(By.id("filterDateFrom")).clear();
			Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("12/01/2020");
			Helper.driver.findElement(By.id("filter-icon")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("svg-layout")));
			WebElement pngHover = Helper.driver.findElement(By.id("svg-layout"));
			Test_Variables.steps.createNode("3. Click on the button");
			builder.moveToElement(pngHover).build().perform();

			Thread.sleep(500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			WebElement clickDownload = Helper.driver.findElement(By.id("dc-bar-chart-e-salm-png"));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(clickDownload).click().perform();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(500);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());

			Assert.assertTrue(namesOfFiles.contains(Test_Variables.eslPNGFileName+date+".png")); 
			System.out.println("Success");
			Test_Variables.test.pass("PNG downloaded successfully");
			Test_Variables.results.createNode("PNG downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
		}
		Thread.sleep(500);
	}



	@Test (description="Test Case: Test External Salmonella CSV Download",enabled= true, priority = 11) 
	public void CSVExport() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ESL-214: Verify user can download Salmonella CSV file", "This test case will verify that user can download Salmonella CSV file");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			WebElement csvHover = Helper.driver.findElement(By.id("data-form-download"));
			Actions builder = new Actions(Helper.driver);
			builder.moveToElement(csvHover).build().perform();
			
			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Test_Variables.steps.createNode("5. Click on Export as CSV");	
			Thread.sleep(1000);
			builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.eslCsv))).click().perform();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(500);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());

			Assert.assertTrue(namesOfFiles.contains(Test_Variables.eslCSVFileName+date+".csv"));
			System.out.println("Success");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
		}
		Thread.sleep(500);
	}



	@Test (description="Test Case: Test External Salmonella Template Download",enabled= true, priority = 12) 
	public void TemplateExport() throws InterruptedException, IOException {

		try {	
			Test_Variables.test = Test_Variables.extent.createTest("AN-ESL-215: Verify user can download Salmonella Template file", "This test case will verify that user download Salmonella Template file");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Salmonella Log; External Salmonella Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			WebElement csvHover = Helper.driver.findElement(By.xpath(Test_Elements.eslCsvHover));
			Actions builder = new Actions(Helper.driver);
			builder.moveToElement(csvHover).build().perform();
			Thread.sleep(500);
			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.eslDownloadButton))).click().perform();
			Thread.sleep(500);
			Test_Variables.steps.createNode("5. Click on Export Data Template");
			builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.eslExportDataTemplate))).click().perform();

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Salmonella Log", Constants.ExternalSalmonellaReportPath));
			Test_Variables.steps.createNode("6. Select Sample MetaData Template");

			WebElement button = Helper.driver.findElement(By.xpath(Test_Elements.eslSampleMetaDataExport));
			JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
			jse.executeScript("arguments[0].click()", button);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());

			Assert.assertTrue(namesOfFiles.contains(Test_Variables.eslSampleMetaData+".xlsx"));
			Test_Variables.test.pass("Sample MetaData downloaded successfully");
			Test_Variables.results.createNode("Sample MetaData downloaded successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalSalmonellaReportPath, null);

		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalSalmonellaReportPath, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}
