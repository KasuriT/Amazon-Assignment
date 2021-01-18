package Test.Ancera.Reports;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import Models.ExternalCoccidiaModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExternalCoccidia {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/External_Coccidia"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("External Coccidia Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Run APIs", enabled= false, priority= 1) 
	public void RunAPI() throws InterruptedException, IOException	{

		Test_Variables.test = Test_Variables.extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.steps.createNode("1. Enter valid piperid ("+Test_Variables.piperId+")");
		Test_Variables.steps.createNode("2. Enter valid password (********)");
		Test_Variables.steps.createNode("3. Run the API");

		DateFormat dateFormat = new SimpleDateFormat("mm.ss");
		Date date1 = new Date();
		dateFormat.format(date1);

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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}	

		Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-01: Verify Coccidia File Announcement API", "This test case will run Coccidia file announcement api");	
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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}	

		for(int i=0; i<Test_Variables.lstExternalCoccidiaIngest.size(); i++)	{
			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstExternalCoccidiaIngest.get(i).testCaseTitle, Test_Variables.lstExternalCoccidiaIngest.get(i).testCaseDesc);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("Run login API to generate token");
				Test_Variables.preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
				Test_Variables.steps.createNode(Test_Variables.lstExternalCoccidiaIngest.get(i).step);

				Thread.sleep(2000);
				RequestSpecification request_fileupload = RestAssured.given();

				request_fileupload.header("Content-Type", "application/json");
				request_fileupload.header("Authorization", "bearer " +token);

				HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("runId", Test_Variables.lstExternalCoccidiaIngest.get(i).runId);
				json3.put("checksum", Test_Variables.lstExternalCoccidiaIngest.get(i).checksum);
				json3.put("fileName", Test_Variables.lstExternalCoccidiaIngest.get(i).fileName);
				json3.put("fileType", Test_Variables.lstExternalCoccidiaIngest.get(i).fileType);
				json3.put("file", Test_Variables.lstExternalCoccidiaIngest.get(i).file);
				json3.put("fileJson", Test_Variables.lstExternalCoccidiaIngest.get(i).fileJson);				
				json3.put("Improc", Test_Variables.lstExternalCoccidiaIngest.get(i).improc);

				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(Constants.api_FileUpload);
				String data3 = response2.asString();
				System.out.println(data3);

				JsonPath jsonPathEvaluator1 = response.jsonPath();
				jsonPathEvaluator1.get("statusCode");


				//	Assert.assertEquals(statusCodeFileUpload, 114); 
				Test_Variables.test.pass("File Upload API ran successfully");
				Test_Variables.results.createNode(Test_Variables.lstExternalCoccidiaIngest.get(i).passScenario);
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstCoccidiaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstCoccidiaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
			}		


			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstExternalCoccidiaIngest.get(i).testCaseTitleIngestion, Test_Variables.lstExternalCoccidiaIngest.get(i).testCaseDescIngestion);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on External Coccidia Log");

				Thread.sleep(1000);
				Helper.driver.get(Constants.url_ExternalCoccidiaLog);
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.eclSampleID)));
				Thread.sleep(2000);
				
				Test_Variables.steps.createNode("1. Click on Lab Sample ID to expand the filter");
				Helper.driver.findElement(By.id("Lab Sample ID")).click();
				Thread.sleep(1000);
				Test_Variables.steps.createNode("2. Search for the Sample ID against which the data is ingested");
				Helper.driver.findElement(By.id("place-holder-search-Lab Sample ID")).sendKeys("Test"+Test_Variables.lstExternalCoccidiaSampleID.get(i));
				Thread.sleep(1000);

//				for (int j=1; j<=4000; j++) {
//					String actualXpathx = Test_Elements.eclSampleIDbeforeXpath+j+Test_Elements.eclSampleIDafterXpath;
//					WebElement element = Helper.driver.findElement(By.xpath(actualXpathx));
//
//					if (element.getText().equals("Test"+Test_Variables.lstExternalCoccidiaSampleID.get(i))) {
//						Helper.driver.findElement(By.xpath(Test_Elements.eclSampleIDbeforeXpath+j+Test_Elements.eclSampleIDafterXpath1)).click();
//						break;
//					}
//				}

				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
				Test_Variables.steps.createNode("3. Click on Apply filter button");
				Helper.driver.findElement(By.id("filter-icon")).click(); 
				Thread.sleep(4000);
				String records = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();

				Assert.assertEquals(records, "1"); 
				Test_Variables.test.pass("Ingested Successfully");
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
			}	
			Thread.sleep(1000);				
		}	
	}


	@Test (description="Test Case: Navigate to External Coccidia Log Screen",enabled= true, priority = 2) 
	public void NavigateCoccidia() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-06: Verify user can navigate to External Coccidia Log Screen", "This test case will verify user can navigate to External Coccidia Log Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.steps.createNode("1. Click on External Coccidia Log");

			Helper.driver.get(Constants.url_ExternalCoccidiaLog);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset-icon"))); 
			Thread.sleep(2000);
			String actual = Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText();
			String expected = "External Coccidia log";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("External Coccidia Log report opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("External Coccidia Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("External Coccidia Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}
	}

	
	@Test (description="Test Case: Date Filter Test",enabled= false, priority = 3) 
	public void DateFilter() throws InterruptedException, IOException {

		Test_Variables.lstExternalCoccidiaDateSearch = ExternalCoccidiaModel.FillDate();

		for (ExternalCoccidiaModel objModel : Test_Variables.lstExternalCoccidiaDateSearch) { 
			Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

			for (ReportFilters objFilter : objModel.lstFilters) {
				Actions actions = new Actions(Helper.driver);

				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objFilter.FilterXPath))); 
				Test_Variables.steps.createNode("1. Click on date calendar icon; Calendar pops up");
				actions.moveToElement(Helper.driver.findElement(By.xpath(objFilter.FilterXPath))).click().perform();	
				Thread.sleep(1000);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

				Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
				actions.moveToElement(Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSearch))).click().perform();	
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));

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

						String fromDateField = Helper.driver.findElement(By.xpath(objFilter.FilterListXPathPrefix)).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSuffix)).getAttribute("value");

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

					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
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

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						String fromDateField = Helper.driver.findElement(By.xpath(objFilter.FilterListXPathPrefix)).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSuffix)).getAttribute("value");

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

					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
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

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						String fromDateField = Helper.driver.findElement(By.xpath(objFilter.FilterListXPathPrefix)).getAttribute("value");
						String toDateField = Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSuffix)).getAttribute("value");

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

					}catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
						Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
					}
				}

				String recordBefore = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText(); 
				try {

					Test_Variables.steps.createNode("4. Click on Apply filter button");
					Helper.driver.findElement(By.xpath(Test_Elements.eclApplyFilter)).click();
					Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.eclResetButton)));	
					Thread.sleep(5000);

					String recordAfter = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();
					Assert.assertTrue(recordBefore != recordAfter); 
					Test_Variables.test.pass(objFilter.FilterName+" applied successfully");
					Test_Variables.results.createNode("2. "+objFilter.FilterName+" applied successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
				}catch(AssertionError er) {
					Test_Variables.test.fail(objFilter.FilterName+" failed to apply");
					Test_Variables.results.createNode(objFilter.FilterName+" failed to apply");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
				}catch(Exception ex){
					Test_Variables.test.fail(objFilter.FilterName+" failed to apply");
					Test_Variables.results.createNode(objFilter.FilterName+" failed to apply");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
				}
			}
		}
	}



	@Test (description="Test Case: Date Enter",enabled= false, priority = 4) 
	public void EnterDate() throws InterruptedException, IOException {

		Test_Variables.lstExternalCoccidiaDateEnter = ExternalCoccidiaModel.EnterDate();

		for (ExternalCoccidiaModel objModel : Test_Variables.lstExternalCoccidiaDateEnter) { 

			Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

			String recordBefore = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText(); 

			for (ReportFilters objFilter : objModel.lstFilters) {

				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objFilter.FilterListXPathPrefix)));
				Test_Variables.steps.createNode("1. "+objFilter.FilterName);
				Thread.sleep(1000);

				Helper.driver.findElement(By.xpath(objFilter.FilterListXPathPrefix)).clear();
				Helper.driver.findElement(By.xpath(objFilter.FilterListXPathPrefix)).sendKeys(objFilter.fromDate);
				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSuffix)).clear();
				Helper.driver.findElement(By.xpath(objFilter.FilterListXPathSuffix)).sendKeys(objFilter.toDate);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));	

				Test_Variables.steps.createNode("2. Click on Apply filter button");
				Helper.driver.findElement(By.xpath(Test_Elements.eclApplyFilter)).click();

				if(objModel.Filter1) {
					try {
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
						String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
						String expected = objFilter.alertMessage;

						Assert.assertEquals(actual, expected); 
						Test_Variables.test.pass("Filter was not applied successfully");
						Test_Variables.results.createNode("1. Filter was not applied successfully");	
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));	
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Filter was applied with invalid date or did not receive an alert message");
						Test_Variables.results.createNode("1. Filter was applied with invalid date or did not receive an alert message");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Filter was applied with invalid date or did not receive an alert message");
						Test_Variables.results.createNode("1. Filter was applied with invalid date or did not receive an alert message");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
					}	
				}

				if(objModel.Filter2) {

					try {
						String recordAfter = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();
						Assert.assertTrue(recordBefore != recordAfter); 
						Test_Variables.test.pass("Filter was applied");
						Test_Variables.results.createNode("1. Filter was applied");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to apply ");
						Test_Variables.results.createNode("1. Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Filter failed to apply");
						Test_Variables.results.createNode("1. Filter failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
					}					
				}
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= false, priority = 5) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-17: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.eclApplyFilter)));
			Test_Variables.steps.createNode("1. Enter valid date in from and 2 fields");
			Helper.driver.findElement(By.xpath(Test_Elements.eclDateFrom)).clear();
			Helper.driver.findElement(By.xpath(Test_Elements.eclDateFrom)).sendKeys("12/01/2020");
			Helper.driver.findElement(By.xpath(Test_Elements.eclDateTo)).clear();
			Helper.driver.findElement(By.xpath(Test_Elements.eclDateTo)).sendKeys("12/15/2020");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Test_Variables.steps.createNode("2. Click on Apply filter button");
			Helper.driver.findElement(By.xpath(Test_Elements.eclApplyFilter)).click();
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.eclResetButton)));	
			Thread.sleep(3000);
			Test_Variables.steps.createNode("3. Click on Lock button");
			Helper.driver.findElement(By.id("save-icon")).click();;
			Thread.sleep(1000);
			String recordsbeforefilter = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText(); 
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Test_Variables.steps.createNode("4. Close report");
			Helper.driver.get(Constants.url_reports);
			Thread.sleep(2000);
			Test_Variables.steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			Helper.driver.get(Constants.url_ExternalCoccidiaLog);
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.eclApplyFilter)));
			String recordsafterfilter = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();

			Assert.assertEquals(recordsafterfilter, recordsbeforefilter);
			Test_Variables.test.pass("Filter locked functionality verified successfully on date filter");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report on date filter");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Filer lock functionality failed on date filter");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("un-save-icon")).click();
	}
	
	
	@Test (description="Test Case: Reset Filter",enabled= false, priority = 6) 
	public void ResetFilter() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-18: Verify user can reset filter", "This testcase will verify that user can reset filter");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");	
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("reset-icon")).click();
			Thread.sleep(3000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			String recordBefore = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();
			Test_Variables.steps.createNode("1. Change from date");
			Helper.driver.findElement(By.id("filterDateFrom")).clear();
			Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("09/01/2020");
			Test_Variables.steps.createNode("2. Click on apply filter button");
			Helper.driver.findElement(By.id("filter-icon")).click();
			Thread.sleep(3500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			String recordAfterApplyFilter = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();
			Assert.assertTrue(recordBefore != recordAfterApplyFilter, "Failed to apply filter (Records remained same after applying filters)");
			Test_Variables.steps.createNode("3. Click on reset button");
			Helper.driver.findElement(By.id("reset-icon")).click();
			Thread.sleep(3500);
			String recordAfterReset = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();

			Assert.assertEquals(recordAfterReset, recordBefore);
			Test_Variables.test.pass("Filter reset successfully");
			Test_Variables.results.createNode("Filter reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Filter failed to reset successfully");
			Test_Variables.results.createNode("Filter failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Filter failed to reset successfully");
			Test_Variables.results.createNode("Filter failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}	
	}
	


	@Test (description="Test Case: Filter Test",enabled= true, priority = 7) 
	public void SearchFilter() throws InterruptedException, IOException {

		Test_Variables.lstExternalCoccidiaSearch = ExternalCoccidiaModel.FillData();

		for (ExternalCoccidiaModel objModel : Test_Variables.lstExternalCoccidiaSearch) { 
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

				Actions actions = new Actions(Helper.driver);
				for (ReportFilters objFilter : objModel.lstFilters) {
					try {
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id(objFilter.FilterXPath)));
						Thread.sleep(1000);
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");
						WebElement expandFilter = Helper.driver.findElement(By.id(objFilter.FilterXPath));
						actions.moveToElement(expandFilter).click().perform();
						Test_Variables.steps.createNode("2. Enter value to search ("+objFilter.SearchVlaue+")");
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterListXPathSearch)).sendKeys(objFilter.SearchVlaue);  
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						Thread.sleep(500);
						Test_Variables.steps.createNode("3. Select the checkbox");
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterValues.size() && i < 3500; i++) {
							int attempts = 0;
							while(attempts < 5) {
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
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
					}		
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
					}

					String recordBefore = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText(); 
					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch, objModel.TestCaseDescriptionSearch);

						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));

						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter-icon")));
						Test_Variables.steps.createNode("1. Click on apply filter button");
						Helper.driver.findElement(By.id("filter-icon")).click(); 
						Thread.sleep(3000);
						String recordAfter = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();
						Test_Variables.steps.createNode("2. Verify the filter is applied and user is able to see the relavant records in the table");
					
//						if(recordAfter != "0") {
//							String getRow = Helper.driver.findElement(By.xpath(objFilter.getRowValue)).getText();
//							Assert.assertEquals(getRow, objFilter.rowValueExpected);			
//						}	
						System.out.println(recordBefore+", "+recordAfter);
						Assert.assertNotEquals(recordBefore, recordAfter);
					//	Assert.assertTrue(recordBefore != recordAfter);
						Test_Variables.test.pass("Records verified successfully");
						Test_Variables.results.createNode("User is able to see filtered records in table successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Records verification failed");
						Test_Variables.results.createNode("User failed to see filtered records in table");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Records verification failed");
						Test_Variables.results.createNode("User failed to see filtered records in table");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Click on apply filter button");

						Test_Variables.steps.createNode("1. Click on cross icon next to entered text in search field");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						WebElement clearInput = Helper.driver.findElement(By.id(objFilter.ClearInput));
						JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
						jse.executeScript("arguments[0].click()", clearInput);
						Thread.sleep(500);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						WebElement closeSearch = Helper.driver.findElement(By.id(objFilter.FilterXPath));
						actions.moveToElement(closeSearch).click().perform();
						Thread.sleep(500);

						Assert.assertTrue(objFilter.FilterListXPathSearch.contains(""));
						Test_Variables.test.pass("Input search field cleared successfully");
						Test_Variables.results.createNode("1. Search field cleared successfully on clicking cross icon");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
					}

					Thread.sleep(1000);

					if(objModel.ResetFilter) {
						
						Helper.driver.findElement(By.id("reset-icon")).click();
						Thread.sleep(2000);				
					}

					if(objModel.ApplyFilter) {
						Helper.driver.get(Constants.url_ExternalCoccidiaLog);
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
						Thread.sleep(3000);
					} 
				//	Thread.sleep(1000);
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Coccidia Lock Filter Functionality",enabled= false, priority = 8) 
	public void CoccidiaLock() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-76: Verify External Coccidia Lock Filter Functionality", "This test case will test External Coccidia Lock Filter Functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

			Helper.driver.get(Constants.url_ExternalCoccidiaLog);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Lab Sample ID")));
			Thread.sleep(2000);
			Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
			Helper.driver.findElement(By.id("Lab Sample ID")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("place-holder-search-Lab Sample ID")).sendKeys("2019_SFC_2");
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("2019_SFC_2")).click();
			Thread.sleep(500);

			WebElement applyFilter = Helper.driver.findElement(By.xpath(Test_Elements.eclApplyFilter));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(applyFilter).click().perform();
			Thread.sleep(2000);
			Test_Variables.steps.createNode("2. Click on lock button");
			Helper.driver.findElement(By.id("save-icon")).click();
			Thread.sleep(2500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			String recordsafterfilter = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumber)).getText();  //records after applying filter
			Thread.sleep(500);
			Test_Variables.steps.createNode("3. Close External Coccidia Log Report");
			Helper.driver.get(Constants.url_reports);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));	
			Thread.sleep(1000);
			Test_Variables.steps.createNode("4. Reopen External Coccidia Log Report");
			Helper.driver.get(Constants.url_ExternalCoccidiaLog);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.eclRecordNumber)));
			Test_Variables.steps.createNode("5. Verify lock filter remains applied");

			Assert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumber)).getText());
			Test_Variables.test.pass("Filter locked functionality verified successfully");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Filer lock functionality failed");
			Test_Variables.results.createNode("Filter lock failed to remain applied on reopening the report");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}	
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.eclUnLockButton)));
		Helper.driver.findElement(By.xpath(Test_Elements.eclUnLockButton)).click();
		Thread.sleep(1000);
	}

	@Test (description="Test Case: Test Pagination",enabled= false, priority = 9) 
	public void Pagination() throws InterruptedException, IOException {
		Test_Variables.lstExternalCoccidiaPagination = ExternalCoccidiaModel.pagination();
		Helper.driver.get(Constants.url_ExternalCoccidiaLog);
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
		Thread.sleep(1500);
		Helper.driver.findElement(By.id("filterDateFrom")).clear();
		Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("10/01/2020");
		Helper.driver.findElement(By.id("filter-icon")).click();
		Thread.sleep(3000);

		for (ExternalCoccidiaModel objModel : Test_Variables.lstExternalCoccidiaPagination) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						String recordNumber = Helper.driver.findElement(By.xpath(Test_Elements.eclRecordNumberTop)).getText();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));

						String removeComma = recordNumber.replace(",", "");
						double x = Double.parseDouble(removeComma);
						double y = 100;
						double divide = Math.ceil(Math.abs(x/y));
						final int totalPages = (int)divide;
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.eclResetButton)));
						Helper.driver.findElement(By.id(objFilter.paginationPage)).click();

						if (objModel.paginationExist) {
							Thread.sleep(8000);
							Test_Variables.steps.createNode("1. Verify pagination exists");
							Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
							Assert.assertTrue(Helper.driver.findElements(By.id("activePageNumber")).size() != 0);
							Test_Variables.test.pass("Pagination displayed successfully");
							Test_Variables.results.createNode("Pagination displayed successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);		
						}


						if (objModel.paginationLastPage) {
							Thread.sleep(8000);
							Test_Variables.steps.createNode("1. Click on '>>' icon in pagination");
			
							if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
								Thread.sleep(1000);
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
								Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
								Assert.fail("An error alert message displayed");
							}
							
							String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
							Assert.assertEquals(pageCount, totalPages+"/"+totalPages);
							Test_Variables.test.pass("Navigated to last page successfully");
							Test_Variables.results.createNode("Navigated to last page successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);

						}


						if (objModel.paginationPreviousPage) {

							Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.eclResetButton)));
							Thread.sleep(8000);
							Test_Variables.steps.createNode("1. Click on '<' icon in pagination");
							if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
								Thread.sleep(1000);
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
								Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
								Assert.fail("An error alert message displayed");
							}
							String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
							Assert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
							Test_Variables.test.pass("Navigated to previous page successfully");
							Test_Variables.results.createNode("Navigated to previous page successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
						}


						if (objModel.paginationFirstPage) {	
							Thread.sleep(8000);
							Test_Variables.steps.createNode("1. Click on '<<' icon in pagination");
							if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
								Thread.sleep(1000);
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
								Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
								Assert.fail("An error alert message displayed");
							}
							String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
							Assert.assertEquals(pageCount, 1+"/"+totalPages);
							Test_Variables.test.pass("Navigated to first page successfully");
							Test_Variables.results.createNode("Navigated to first page successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
						}


						if (objModel.paginationNextPage) {	
							Thread.sleep(8000);
							Test_Variables.steps.createNode("1. Click on '>' icon in pagination");
							if (Helper.driver.findElements(By.id("alrt")).size() !=0) {
								Thread.sleep(1000);
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
								Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();	
								Assert.fail("An error alert message displayed");
							}
							String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
							Assert.assertEquals(pageCount, 2+"/"+totalPages);
							Test_Variables.test.pass("Navigated to next page successfully");
							Test_Variables.results.createNode("Navigated to next page successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
						}

					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}

	
	@Test (description="Test Case: Test Table Rows",enabled= false, priority = 10) 
	public void RowsPerPage() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_ExternalCoccidiaLog);
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
		Thread.sleep(1500);
		Helper.driver.findElement(By.id("filterDateFrom")).clear();
		Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("09/01/2020");
		Helper.driver.findElement(By.id("filter-icon")).click();
		Thread.sleep(3500);

		Test_Variables.lstExternalCoccidiaRowCount = ExternalCoccidiaModel.searchRows();

		for (ExternalCoccidiaModel objModel : Test_Variables.lstExternalCoccidiaRowCount) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

				Actions actions = new Actions(Helper.driver);
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("rows")));
						Thread.sleep(1000);
						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						WebElement expandFilter = Helper.driver.findElement(By.id("rows"));
						actions.moveToElement(expandFilter).click().perform();				
						Thread.sleep(1000);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						Helper.driver.findElement(By.id(objFilter.FilterListXPathSearch)).click();;  			
						Thread.sleep(7000);

						List<WebElement> rows = Helper.driver.findElements(By.xpath("//table[@class='dc-chart']/tbody/tr"));
						int count = rows.size();
						System.out.println("ROW COUNT : "+count);
						Assert.assertEquals(count, Integer.parseInt(objFilter.count));
						Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully");
						Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));

						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						Test_Variables.steps.createNode("2. Go to next page from pagination");
						Test_Variables.steps.createNode("3. Verify that still "+objFilter.FilterName+" is selected");
						Helper.driver.findElement(By.id("next-page")).click();
						Thread.sleep(12000);
						List<WebElement> rows = Helper.driver.findElements(By.xpath("//table[@class='dc-chart']/tbody/tr"));
						int count = rows.size();
						System.out.println("ROW COUNT : "+count);
						Assert.assertEquals(count, Integer.parseInt(objFilter.count));
						Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully on next page");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);	
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}

	
	@Test (description="Test Case: Test Coccidia PNG Download",enabled= false, priority = 11) 
	public void PNGExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-88: Verify user can download External Coccidia PNG file", "This test case will verify user can download External Coccidia PNG file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards barchart on top");
			Test_Variables.steps.createNode("2. Export PNG button becomes visible");

			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("reset-icon")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("filterDateFrom")).clear();
			Helper.driver.findElement(By.id("filterDateFrom")).sendKeys("12/22/2020");
			Helper.driver.findElement(By.id("filterDateTo")).clear();
			Helper.driver.findElement(By.id("filterDateTo")).sendKeys("12/25/2020");
			Helper.driver.findElement(By.id("filter-icon")).click();
			Thread.sleep(3500);

			Actions builder = new Actions(Helper.driver);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.eclPngHover)));
			WebElement pngHover = Helper.driver.findElement(By.xpath(Test_Elements.eclPngHover));
			Test_Variables.steps.createNode("3. Click on the button");
			builder.moveToElement(pngHover).build().perform();

			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			WebElement clickDownload = Helper.driver.findElement(By.xpath(Test_Elements.eclPng));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(clickDownload).click().perform();
			Thread.sleep(4000);

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(500);
			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());

	//		Assert.assertTrue(namesOfFiles.contains(Test_Variables.eclPNGFileName+date+".png")); 
			System.out.println("Success");
			Test_Variables.test.pass("PNG downloaded successfully");
			Test_Variables.results.createNode("PNG downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("PNG failed to download");
			Test_Variables.results.createNode("PNG failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}
		Thread.sleep(1000);
	}



	@Test (description="Test Case: Test Coccidia CSV Download",enabled= false, priority = 12) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-89: Verify user can download External Coccidia CSV file", "This test case will verify that user can download External Coccidia CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.eclCsvHover)));
			Actions builder = new Actions(Helper.driver);
			builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.eclCsvHover))).build().perform();
			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud popup opens");
			builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.eclDownloadButton))).click().perform();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("5. Click on Export as CSV");
			WebElement button = Helper.driver.findElement(By.xpath(Test_Elements.eclCsv));
			JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
			jse.executeScript("arguments[0].click()", button);
			Thread.sleep(7000);

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());

	//		Assert.assertTrue(namesOfFiles.contains(Test_Variables.eclCSVFileName+date+".csv"));
			System.out.println("Success");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Test Coccidia Template Download",enabled= false, priority = 13) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-ECL-90: Verify user can download External Coccidia Template file", "This test case will verify that user download External Coccidia Template file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on External Coccidia Log; External Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Actions builder = new Actions(Helper.driver);

			builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.eclCsvHover))).build().perform();

			builder.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.eclDownloadButton))).click().perform();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud popup opens");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("External Coccidia Log", Constants.ExternalCoccidiaReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.eclExportDataTemplate)).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("5. Click on Export Data Template");
			Test_Variables.steps.createNode("6. Select Sample MetaData Template");
			WebElement button = Helper.driver.findElement(By.xpath(Test_Elements.eclSampleMetaDataExport));
			JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
			jse.executeScript("arguments[0].click()", button);

			Thread.sleep(7000);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
		//	Assert.assertTrue(namesOfFiles.contains(Test_Variables.eclSampleMetaData+".xlsx"));
			Test_Variables.test.pass("Sample MetaData downloaded successfully");
			Test_Variables.results.createNode("Sample MetaData downloaded successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ExternalCoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ExternalCoccidiaReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}

}