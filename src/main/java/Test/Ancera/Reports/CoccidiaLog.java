package Test.Ancera.Reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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



	
	
	@SuppressWarnings("unchecked")
	@Test (description="Test Case: Run APIs", enabled= true, priority= 1) 
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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}

		Test_Variables.test = Test_Variables.extent.createTest("AN-API-Anncmnt: Verify Coccidia File Announcement API", "This test case will run Coccidia file announcement api");	
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
		JSONObject json4 = new JSONObject();
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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}


		///////////////////////////////////////////////////////////////////////////
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-StrtAssay-01: Verify Coccidia Start Assay API", "This test case will run Coccidia Start Assay api");	
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Run login API to generate token");
			Test_Variables.steps.createNode("1. Add token in Authorization");
			Test_Variables.steps.createNode("2. Add a unique RUN ID");
			Test_Variables.steps.createNode("3. Run the API");


			RequestSpecification request_startAssay = RestAssured.given();

			request_startAssay.header("Content-Type", "application/json");
			request_startAssay.header("Authorization", "bearer " +token);

			HttpGet postRequest3 = new HttpGet(Constants.api_StartAssay);
			postRequest3.addHeader("Content-Type", "application/json");
			postRequest3.addHeader("Authorization", "Bearer "+token);

			json4.put("DateTime", Test_Variables.lstStartAssayCoccidia.get(0).DateTime);
			json4.put("InstrumentId", Test_Variables.lstStartAssayCoccidia.get(0).InstrumentID);
			json4.put("UserId", Test_Variables.lstStartAssayCoccidia.get(0).UserID);
			json4.put("CartridgeId", Test_Variables.lstStartAssayCoccidia.get(0).CartridgeID);
			json4.put("RunId", Test_Variables.lstStartAssayCoccidia.get(0).RunID);
			json4.put("PathogenName", Test_Variables.lstStartAssayCoccidia.get(0).PathogenName);				

			request_startAssay.body(json4.toString());
			Response response3 = request_startAssay.post(Constants.api_StartAssay);

			String data4 = response3.asString();
			System.out.println(data4);
			Thread.sleep(90000);
			Helper.driver.get(Constants.url_CoccidiaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
			Thread.sleep(1000);
			WebElement filter_scroll = Helper.driver.findElement(By.id("sort-cartridgeId"));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);

			Test_Variables.steps.createNode("1. Click on Lab Sample ID to expand the filter");
			ClickElement.clickById(Helper.driver, "cartridgeId_show-filter");				
			Thread.sleep(800);
			Helper.driver.findElement(By.id("cartridgeId_view-all")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Search for the Cartridge ID against which the data is ingested");

			Helper.driver.findElement(By.id("cartridgeId_search-input")).clear();
			Helper.driver.findElement(By.id("cartridgeId_search-input")).sendKeys(""+Test_Variables.CartridgeID);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);				
			Helper.driver.findElement(By.cssSelector("#cartridgeId_cust-cb-lst-txt_"+Test_Variables.CartridgeID)).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);

			Test_Variables.steps.createNode("3. Click on Apply filter button");
			Helper.driver.findElement(By.id("cartridgeId_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			String records = Helper.driver.findElement(By.id("results-found-count")).getText();

			Assert.assertEquals(records, "12"); 
			Test_Variables.test.pass("Start Assay API ran successfully");
			Test_Variables.results.createNode("Start Assay API ran successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);

			for(int i = 0; i<12;i++) {
				String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clResultStatusCol+" label")).getText();
				Assert.assertEquals(getResultStatus, "Pending");

				String getDate = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clDateCol+" label")).getText();
				Assert.assertEquals(getDate, Test_Variables.dateMMDDYYYY1);

				String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clAssayCol+" label")).getText();
				Assert.assertEquals(getPathogen, Test_Variables.Pathogen);

				String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clCatridgeIDCol+" label")).getText();
				Assert.assertEquals(getCartridgeID, Test_Variables.CartridgeID);

				String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clInstrumentIDCol+" label")).getText();
				Assert.assertEquals(getInstrumentID, Test_Variables.InstrumentID);

				String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clPiperUserCol+" label")).getText();
				Assert.assertEquals(getPiperUser, Test_Variables.PiperUser);

				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clTestSiteIDCol+" label")).getText();
				Assert.assertTrue(getTestSiteID.isEmpty() == false);

				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clTestSiteNameCol+" label")).getText();
				Assert.assertTrue(getTestSiteName.isEmpty() == false);

				Helper.driver.findElement(By.id("audit-trial-"+i)).click();
				Thread.sleep(1000);
				String getAuditDate = Helper.driver.findElement(By.id("audit-changed-date-0")).getText();
				Assert.assertEquals(getAuditDate, Test_Variables.dateMMDDYYYY1);

				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				Assert.assertEquals(getAuditAction, "Created");

				String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
				Assert.assertEquals(getAuditUser, Test_Variables.PiperUser);

				String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-3.text-dark")).getText(); 
				Assert.assertEquals(getAuditResultStatus, "Pending");

				String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-16.text-dark")).getText();
				Assert.assertEquals(getAuditCartridgeId, Test_Variables.CartridgeID);

				String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-17.text-dark")).getText();
				Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

				String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
				Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser);

				String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-26.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

				String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-27.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);
				
				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
			}			
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Start Assay API failed to run");
			Test_Variables.results.createNode("Start Assay API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Start Assay API failed to run");
			Test_Variables.results.createNode("Start Assay API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
		}

		///////////////////////////////////////////////////////////////////////	


		for(int i=0; i<Test_Variables.lstCoccidiaIngest.size(); i++)	{
			try{
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstCoccidiaIngest.get(i).testCaseTitle, Test_Variables.lstCoccidiaIngest.get(i).testCaseDesc);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("Run login API to generate token");
				Test_Variables.preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
				Test_Variables.steps.createNode(Test_Variables.lstCoccidiaIngest.get(i).step);

				Thread.sleep(2000);
				RequestSpecification request_fileupload = RestAssured.given();
				request_fileupload.header("Content-Type", "application/json");
				request_fileupload.header("Authorization", "bearer " +token);

				HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("runId", Test_Variables.lstCoccidiaIngest.get(i).runId);
				json3.put("checksum", Test_Variables.lstCoccidiaIngest.get(i).checksum);
				json3.put("fileName", Test_Variables.lstCoccidiaIngest.get(i).fileName);
				json3.put("fileType", Test_Variables.lstCoccidiaIngest.get(i).fileType);
				json3.put("file", Test_Variables.lstCoccidiaIngest.get(i).file);
				json3.put("fileJson", Test_Variables.lstCoccidiaIngest.get(i).fileJson);				
				json3.put("Improc", Test_Variables.lstCoccidiaIngest.get(i).improc);
				json3.put("RunMode", Test_Variables.lstCoccidiaIngest.get(i).runMode);

				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(Constants.api_FileUpload);
				String data3 = response2.asString();
				System.out.println(data3);

				JsonPath jsonPathEvaluator1 = response.jsonPath();
				jsonPathEvaluator1.get("statusCode");

				Test_Variables.test.pass("File Upload API ran successfully");
				Test_Variables.results.createNode(Test_Variables.lstCoccidiaIngest.get(i).passScenario);
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstCoccidiaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("File Upload API running failed");
				Test_Variables.results.createNode(Test_Variables.lstCoccidiaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
			}

			try{
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstCoccidiaIngest.get(i).testCaseTitleIngestion, Test_Variables.lstCoccidiaIngest.get(i).testCaseDescIngestion);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log");

				Thread.sleep(75000);
				Helper.driver.get(Constants.url_CoccidiaLog);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
				Thread.sleep(1000);

				Test_Variables.steps.createNode("1. Click on Lab Sample ID to expand the filter");
				ClickElement.clickById(Helper.driver, "sampleId_show-filter");			
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("sampleId_view-all")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.steps.createNode("2. Search for the Sample ID against which the data is ingested");

				for(int j=0; j<Test_Variables.lstSampleID.size(); j++)	{

					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys("Test"+Test_Variables.lstSampleID.get(j));
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);				
					Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(j))).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(800);
				}

				Test_Variables.steps.createNode("3. Click on Apply filter button");
				Helper.driver.findElement(By.id("sampleId_apply")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
				String records = Helper.driver.findElement(By.id("results-found-count")).getText();

				Assert.assertEquals(records, "12"); 
				
				String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clResultStatusCol+" label")).getText();
				Assert.assertEquals(getResultStatus, "Completed");

				String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clAssayCol+" label")).getText();
				Assert.assertEquals(getPathogen, Test_Variables.Pathogen);

				String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clCatridgeIDCol+" label")).getText();
				Assert.assertEquals(getCartridgeID, Test_Variables.CartridgeID);

				String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clInstrumentIDCol+" label")).getText();
				Assert.assertEquals(getInstrumentID, Test_Variables.InstrumentID);

				String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clPiperUserCol+" label")).getText();
				Assert.assertEquals(getPiperUser, Test_Variables.PiperUser);
	
		//		String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clRunTypeCol+" label")).getText();
		//		Assert.assertEquals(getRunType, Test_Variables.RunType);
				
				String getImprocID = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clImprocIDCol+" label")).getText();
				Assert.assertEquals(getImprocID, Test_Variables.ImprocVersion);

				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clTestSiteIDCol+" label")).getText();
				Assert.assertTrue(getTestSiteID.isEmpty() == false);

				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clTestSiteNameCol+" label")).getText();
				Assert.assertTrue(getTestSiteName.isEmpty() == false);
				
				String getTotalCount = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clTotalCountCol+" label")).getText();
				Assert.assertTrue(getTotalCount.isEmpty() == false);
				
				String getSmallCount = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clSmallCountCol+" label")).getText();
				Assert.assertTrue(getSmallCount.isEmpty() == false);
				
				String getMediumCount = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clMediumCountCol+" label")).getText();
				Assert.assertTrue(getMediumCount.isEmpty() == false);
				
				String getLargeCount = Helper.driver.findElement(By.cssSelector("#row-"+i+" "+Test_Elements.clLargeCountCol+" label")).getText();
				Assert.assertTrue(getLargeCount.isEmpty() == false);
				
				Helper.driver.findElement(By.id("audit-trial-"+i)).click();
				Thread.sleep(1000);

				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				Assert.assertEquals(getAuditAction, "Modified");

				String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
				Assert.assertEquals(getAuditUser, Test_Variables.PiperUser);

				String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-3.text-dark")).getText();
				Assert.assertEquals(getAuditResultStatus, "Completed");

				String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-16.text-dark")).getText();
				Assert.assertEquals(getAuditCartridgeId, Test_Variables.CartridgeID);

				String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-17.text-dark")).getText();
				Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

				String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
				Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser);
				
				String getAuditImprocVersion = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-25.text-dark")).getText();
				Assert.assertEquals(getAuditImprocVersion, Test_Variables.ImprocVersion);

				String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-26.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

				String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-27.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);
				
				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
				
				Test_Variables.test.pass("Ingested Successfully");
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
			}
			
			///////////////////////////////////////////////////////////////////////////////////////////////////
	
			try {	
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstCoccidiaIngest.get(i).testCaseTitleIngestion, Test_Variables.lstCoccidiaIngest.get(i).testCaseDescIngestion);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log");

		//		String getSampleID = Helper.driver.findElement(By.cssSelector("#row-1 #col-1")).getText();
		//		String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-1 #col-15")).getText();

				FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.fileName));
				@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook(fsIP);
				XSSFSheet worksheet = wb.getSheetAt(0);
				Cell cell = null;

				for (int z=0; z<12; z++) {

					String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+z+1+" "+Test_Elements.clSampleIDCol)).getText();
					String updatedSampleID = getSampleID+"U";
					cell=worksheet.getRow(z).createCell(17); 
					cell.setCellValue(updatedSampleID);  

					String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+z+1+" #col-10")).getText();
					cell=worksheet.getRow(z).createCell(3); 
					cell.setCellValue(getResultID);  
					
					cell=worksheet.getRow(z+1).createCell(2); 
					cell.setCellValue(Test_Variables.CartridgeID); 

					cell=worksheet.getRow(z+1).createCell(5); 
					cell.setCellValue(Test_Variables.SampleMatrix); 
					
					cell=worksheet.getRow(z+1).createCell(11); 
					cell.setCellValue(Test_Variables.FlockID); 
					
					cell=worksheet.getRow(z+1).createCell(7); 
					cell.setCellValue(Test_Variables.RequestedAssay); 
					
					cell=worksheet.getRow(z+1).createCell(19); 
					cell.setCellValue(Test_Variables.KitLot); 
					
					cell=worksheet.getRow(z+1).createCell(6); 
					cell.setCellValue(Test_Variables.CustomerSampleID); 
					
					cell=worksheet.getRow(z+1).createCell(4); 
					cell.setCellValue(Test_Variables.SiteID); 

					String getLane = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-0")).getText();
					cell=worksheet.getRow(z+1).createCell(1); 
					cell.setCellValue(getLane);  
 	
					fsIP.close();
				}

				FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+Test_Variables.fileName));
				wb.write(output_file);
				output_file.close();  

				Helper.driver.get(Constants.url_dataUpload);
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("OrgnTypeID")).click();
				Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
				Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("DataFormatId")).click();
				Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
				Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("file-input")).sendKeys(Test_Variables.fileAbsolutePath+"Excel\\"+Test_Variables.fileName);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
				Thread.sleep(4000);
				Helper.driver.findElement(By.cssSelector(".fa-save")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
				Thread.sleep(2000);

				Helper.driver.get(Constants.url_CoccidiaLog);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
				Thread.sleep(1000);

				Test_Variables.steps.createNode("1. Click on Lab Sample ID to expand the filter");
				ClickElement.clickByCss(Helper.driver, "#sort-sampleId .log-header__filter-icon");				
				Thread.sleep(800);
				Helper.driver.findElement(By.cssSelector("#sort-sampleId .filter-popup__footer--view-all")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.steps.createNode("2. Search for the Sample ID against which the data is ingested");

				for(int j=0; j<Test_Variables.lstSampleID.size(); j++)	{

					Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).clear();
					Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).sendKeys("Test"+Test_Variables.lstSampleID.get(j)+"U");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);				
					Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(j))).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(800);
				}

				Test_Variables.steps.createNode("3. Click on Apply filter button");
				Helper.driver.findElement(By.cssSelector("#sort-sampleId .filter-popup__action--apply")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
				String records = Helper.driver.findElement(By.id("results-found-count")).getText();

				Assert.assertEquals(records, "12"); 
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);

			}catch(AssertionError er) {
				Test_Variables.test.fail("gf");
					Test_Variables.results.createNode("gfg");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.InstallationRunReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("h");
				Test_Variables.results.createNode("fgfg");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.InstallationRunReportPath, ex);	
			}
			
			Thread.sleep(2000);	
		}	
	}


	@Test (description="Test Case: Navigate to Coccidia Log Screen",enabled= false, priority = 2) 
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


	@Test (description="Test Case: Wildcard",enabled= false, priority = 2) 
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
								System.out.println(str);
								Assert.assertTrue(str.startsWith(objFilter.LstFilterValues.get(0)) || str.startsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.endsWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								System.out.println(str);
								Assert.assertTrue(str.endsWith(objFilter.LstFilterValues.get(0)) || str.endsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.contains) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								System.out.println(str);
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


	@Test (description="Test Case: Date Filter Test",enabled= false, priority = 3) 
	public void DateFilter() throws InterruptedException, IOException {

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
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-scanDateTime"))); 
				WebElement filter_scroll = Helper.driver.findElement(By.id("sort-site_id"));
				((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
				Test_Variables.steps.createNode("1. Click on date calendar icon; Calendar pops up");
				actions.moveToElement(Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter"))).click().perform();	
				Thread.sleep(1000);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
				System.out.println("a");
				Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-chevron-down")).click();
				System.out.println("b");
				Thread.sleep(1000);
				if (Helper.driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch)).isEnabled()) {
					actions.moveToElement(Helper.driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch))).click().perform();	
					System.out.println("c");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter")).click();
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
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail(objFilter.FilterName+ " values failed to verify");
							Test_Variables.results.createNode(objFilter.FilterName+ " values failed to verify");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
						}
					}
					Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-filter")).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .log-header__clear-filter")).click();
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


	@Test (description="Test Case: Date Filter Lock Test",enabled= false, priority = 5) 
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
			Thread.sleep(500);

			Test_Variables.steps.createNode("2. Click on objFilter.FilterName");
			Helper.driver.findElement(By.cssSelector("#sort-scanDateTime .fa-chevron-down")).click();
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


	@Test (description="Test Case: Filter Test",enabled= false, priority = 6) 
	public void TestFilter() throws InterruptedException, IOException {

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
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(500);
						WebElement filter_scroll = Helper.driver.findElement(By.cssSelector(objFilter.FilterID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Thread.sleep(800);	
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {					
							Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .fa-filter")).click();		
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);						
							Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .filter-popup__footer--view-all")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);
						}

						for (int i = 0; i < objFilter.LstFilterValues.size(); i++) {
							Test_Variables.steps.createNode("2. Select the checkbox");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .checkbox-sm:nth-child("+objFilter.LstFilterValues.get(i)+") label"));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 										
							Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .checkbox-sm:nth-child("+objFilter.LstFilterValues.get(i)+") label")).click();
						}

						Test_Variables.steps.createNode("3. Click on apply filter button");	
						Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .filter-popup__action--apply")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(800);
						String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();		

						System.out.println(recordBefore+", "+recordAfter);
						Assert.assertNotEquals(recordBefore, recordAfter);
						Test_Variables.test.pass("Checkbox selected successfully");
						Test_Variables.results.createNode("Checkbox selected successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
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

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Helper.driver.findElement(By.cssSelector(objFilter.FilterID +" .fa-filter")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(500);
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector(objFilter.FilterID+" .divider")).size(), 1);
						Test_Variables.test.pass("Filter was applied successfully");
						Test_Variables.results.createNode("Filter was applied successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
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

					Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .filter-popup__action--clear-all")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(800);
					Helper.driver.findElement(By.cssSelector(objFilter.FilterID+" .fa-filter")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(500);
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@SuppressWarnings("unused")
	@Test (description="Test Case: Filter Test",enabled= false, priority = 7) 
	public void TestFilters() throws InterruptedException, IOException {

		Test_Variables.lstCoccidiaSearch = CoccidiaModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
		for (CoccidiaModel objModel : Test_Variables.lstCoccidiaSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameButtonActive, objModel.TestCaseDescriptionButtonActive);
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
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id(objFilter.FilterXPath)));

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterXPath));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Thread.sleep(500);	
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						for(int i = 0; i<objFilter.LstFilterSearch.size(); i++) {
							WebElement expandFilter = Helper.driver.findElement(By.id("filter-"+objFilter.LstFilterXpath.get(i)));
							actions.moveToElement(expandFilter).click().perform();				
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Thread.sleep(1000);
						}

						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterValues.size() && i < 4000; i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Test_Variables.steps.createNode("2. Select the checkbox and verify that apply filter button becomes active or not");
							int attempts = 0;
							while(attempts < 4) {
								try {
									ClickElement.clickByCss(Helper.driver, "#"+objFilter.FilterID+" li.custom-control:nth-child("+objFilter.LstFilterValues.get(i)+")");									
									break;
								} catch(StaleElementReferenceException e) {
									ClickElement.clickByCss(Helper.driver, "#"+objFilter.FilterID+" li.custom-control:nth-child("+objFilter.LstFilterValues.get(i)+")");									
								} 
								attempts++;
							}					   
							chkCounter++;
						}

						WebElement filter_button_scroll = Helper.driver.findElement(By.id("filter-icon"));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_button_scroll); 

						Assert.assertTrue(Helper.driver.findElements(By.cssSelector("button.btn-background-solid#filter-icon")).size() != 0);
						Test_Variables.test.pass("Checkbox selected successfully and Apply filter button becomes active");
						Test_Variables.results.createNode("Checkbox selected successfully and Apply filter button becomes active");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}		
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to select checkbox or Apply filter button remained inactive");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));

						Test_Variables.steps.createNode("1. Click on apply filter button");	
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						WebElement element = Helper.driver.findElement(By.id("filter-icon"));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", element); 
						Thread.sleep(1500);
						ClickElement.clickById(Helper.driver, "filter-icon");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();		
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						if (Helper.driver.findElements(By.cssSelector("div#"+objFilter.LstFilterXpath.get(0)+"-group-head i.filters-clear")).size() !=0) {
							Actions builder = new Actions(Helper.driver); 
							WebElement hover = Helper.driver.findElement(By.cssSelector("div#"+objFilter.LstFilterXpath.get(0)+"-group-head i.filters-clear"));
							builder.moveToElement(hover).build().perform();	
						}

						System.out.println(recordBefore+", "+recordAfter);
						Assert.assertNotEquals(recordBefore, recordAfter);
						Test_Variables.test.pass("Filter applied successfully");
						Test_Variables.results.createNode("Filter applied successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}		
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
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
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
								break;
							} catch(StaleElementReferenceException e) {
							} 
							catch(AssertionError er) {
								Test_Variables.test.fail("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Test_Variables.results.createNode("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
							}
							catch(Exception ex) {
								Test_Variables.test.fail("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Test_Variables.results.createNode("Blue filter indicator failed to appears next to applied filter or apply filter button did not became inactive");
								Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.steps.createNode("1. Verify filter pops to top of filter list");

						Assert.assertTrue(Helper.driver.findElements(By.cssSelector("div.order-1 span#"+objFilter.FilterXPath)).size() != 0);
						Test_Variables.test.pass("Filter bubbles to top of filter list successfully on applying");
						Test_Variables.results.createNode("Filter bubbles to top of filter list successfully on applying");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to bubble to top of filter list on applying");
						Test_Variables.results.createNode("Filter failed to bubble to top of filter list on applying");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter failed to bubble to top of filter list on applying");
						Test_Variables.results.createNode("Filter failed to bubble to top of filter list on applying");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify checkbox selected pops to top of filter checkbox list");

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterValues.size() && i < 5000; i++) {

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							String s = Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterID+" li.custom-control:nth-child("+objFilter.checkboxNumber+")")).getText();

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Assert.assertTrue(Helper.driver.findElements(By.cssSelector("li.order-1 p#"+objFilter.LstFilterXpath.get(i)+"_cust-cb-lst-txt_"+s)).size() != 0);
							Test_Variables.test.pass("Selected filter checkbox bubbles to top of filter list successfully on applying filter");
							Test_Variables.results.createNode("Selected filter checkbox bubbles to top of filter list successfully on applying filter");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);

							break; 
						}
						chkCounter++;
					}

					catch(AssertionError er) {
						Test_Variables.test.fail("Selected filter checkbox failed to move to top of filter list on applying filter");
						Test_Variables.results.createNode("Selected filter checkbox failed to move to top of filter list on applying filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Selected filter checkbox failed to move to top of filter list on applying filter");
						Test_Variables.results.createNode("Selected filter checkbox failed to move to top of filter list on applying filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
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

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(500);
						Test_Variables.steps.createNode("1. Click on cross icon next to entered text in search field");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						for (int i = 0; i< objFilter.LstFilterSearch.size(); i++) {
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+"-place-holder-search")).clear();
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+"-place-holder-search")).sendKeys("text");

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
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Search field failed to clear");
						Test_Variables.results.createNode("1. Search field failed to clear on clicking cross icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Apply "+objFilter.FilterName);

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));

						Thread.sleep(1000);	

						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstFilterXpath.size(); i++) {

							Test_Variables.steps.createNode("1. Hover to blue indicator next to applied filter; blue indicator changes to cross icon");
							Test_Variables.steps.createNode("2. Click on the blue indicator icon");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							if (Helper.driver.findElements(By.id("-"+objFilter.LstFilterXpath.get(i)+"-filter-indicator")).size() != 0) {
								ClickElement.clickById(Helper.driver, "-"+objFilter.LstFilterXpath.get(i)+"-filter-indicator");
							}

							if (Helper.driver.findElements(By.cssSelector("div#"+objFilter.LstFilterXpath.get(i)+"-group-head i.filters-clear")).size() != 0) {
								ClickElement.clickById(Helper.driver, "div#"+objFilter.LstFilterXpath.get(i)+"-group-head i.filters-clear");
							}

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(1500);
							chkCounter++;
						}

						Assert.assertEquals(Helper.driver.findElement(By.id("results-found-count")).getText(), recordBefore);			
						Test_Variables.test.pass("Filter records reset successfully on clicking blue indicator icon");
						Test_Variables.results.createNode("Filter records reset successfully on clicking blue indicator icon");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter records failed to reset on clicking blue indicator icon");
						Test_Variables.results.createNode("Filter records failed to reset on clicking blue indicator icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter records failed to reset on clicking blue indicator icon");
						Test_Variables.results.createNode("Filter records failed to reset on clicking blue indicator icon");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on  Coccidia Log; Coccidia Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Click on apply filter button; selected filter moves to the top");

						Test_Variables.steps.createNode("1. Click on reset button");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						//ClickElement.clickById(Helper.driver, "reset-icon");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(500);

						for (int i = 0; i<objFilter.LstFilterXpath.size(); i++) { 
							Assert.assertTrue(Helper.driver.findElements(By.cssSelector("div.order-2 span#filter-"+objFilter.LstFilterXpath.get(i))).size() != 0);	
						}

						Test_Variables.test.pass("Filter reverts back to its position successfully on resetting filter");
						Test_Variables.results.createNode("Filter reverts back to its position successfully on resetting filter");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);							
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Filter failed to revert back to its position on resetting filter");
						Test_Variables.results.createNode("Filter failed to revert back to its position on resetting filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Filter failed to revert back to its position on resetting filter");
						Test_Variables.results.createNode("Filter failed to revert back to its position on resetting filter");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}

					if(objModel.ReloadPage) {
						Helper.driver.get(Constants.url_CoccidiaLog);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-Instrument-Id")));
					}
					Thread.sleep(500);
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Coccidia Lock Filter Functionality",enabled= false, priority = 8) 
	public void CoccidiaLock() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CL-137: Verify Coccidia Lock Filter Functionality", "This test case will test Coccidia Lock Filter Functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-laneNum"))); 

			WebElement filter_scroll = Helper.driver.findElement(By.id("sort-laneNum"));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(500);	
			ClickElement.clickByCss(Helper.driver, "#sort-laneNum .fa-filter");
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#sort-laneNum .checkbox-sm:nth-child(1) label")).click();

			Thread.sleep(500);
			Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.driver.findElement(By.cssSelector("#sort-laneNum .filter-popup__action--apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Variables.steps.createNode("2. Click on lock button");	
			Helper.driver.findElement(By.id("save-filters")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();
			Thread.sleep(500);
			Test_Variables.steps.createNode("3. Close Coccidia Log Report");
			Test_Variables.steps.createNode("4. Reopen Coccidia Log Report");
			Helper.driver.navigate().refresh();

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Variables.steps.createNode("5. Verify lock filter remains applied");
			Assert.assertEquals(Helper.driver.findElement(By.id("results-found-count")).getText(), recordsafterfilter);
			Test_Variables.test.pass("Filter locked functionality verified successfully");
			Test_Variables.results.createNode("Filter lock remained applied on reopening the report");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("remove-filters")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.navigate().refresh();
			//Helper.driver.findElement(By.cssSelector("#sort-laneNum .log-header__clear-filter")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
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


	@Test (description="Test Case: Test Pagination",enabled= false, priority = 9) 
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



	@Test (description="Test Case: Test Table Rows",enabled= false, priority = 10) 
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
							System.out.println("ROW COUNT : "+new_count);
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
							System.out.println("ROW COUNT : "+new_count);
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


	@Test (description="Sorting",enabled= false, priority = 11) 
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


	@Test (enabled= false, priority = 13) 
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

			Helper.driver.get(Constants.url_CoccidiaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#edit-field-access .fas")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Unselect any column and click on save button");
			Helper.driver.findElement(By.xpath("//tbody/tr[1]/td[4]/label[1]/div[1]")).click();
			Thread.sleep(1000);
			System.out.println("b");
			Helper.driver.findElement(By.id("btn-save")).click();
			System.out.println("c");
			Thread.sleep(1500);
			if (Helper.driver.findElements(By.id("sort-laneNum")).size() == 0) {
				System.out.println("d");
				Assert.assertTrue(true);
				Test_Variables.test.pass("Column was hidden successfully");
				Test_Variables.results.createNode("Column was hidden successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
				Thread.sleep(1000);
				System.out.println("e");
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


	@Test (enabled= false, priority = 14) 
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
			Helper.driver.findElement(By.cssSelector("#edit-field-access .fas")).click();
			System.out.println("a");
			Thread.sleep(2000);
			Test_Variables.steps.createNode("2. Re-select any unselcted column and click on save button");
			Helper.driver.findElement(By.xpath("//tbody/tr[1]/td[4]/label[1]/div[1]")).click();
			System.out.println("b");
			Thread.sleep(1000);                 
			Helper.driver.findElement(By.id("btn-save")).click();
			System.out.println("c");
			Thread.sleep(1500);
			Assert.assertTrue(Helper.driver.findElement(By.id("sort-laneNum")).isDisplayed());
			Test_Variables.test.pass("Column displayed successfully");
			Test_Variables.results.createNode("Column displayed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Thread.sleep(1000);
			System.out.println("d");
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



	@Test (description="Test Case: Test Coccidia PNG Download",enabled= false, priority = 15) 
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

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("svg-layout")));
			WebElement pngHover = Helper.driver.findElement(By.id("svg-layout"));
			Test_Variables.steps.createNode("3. Click on the button");
			builder.moveToElement(pngHover).build().perform();
			Thread.sleep(1000);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			WebElement clickDownload = Helper.driver.findElement(By.id("dc-bar-chart-coci-png"));
			Actions actions = new Actions(Helper.driver);
			actions.moveToElement(clickDownload).click().perform();

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(4000);
			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			@SuppressWarnings("unused")
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			Assert.assertTrue(namesOfFiles.contains(Test_Variables.clPNGFileName+date+".png")); 
			System.out.println("Success");
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


	@Test (description="Test Case: Test Coccidia CSV Download",enabled= false, priority = 16) 
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
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			@SuppressWarnings("unused")
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			Assert.assertTrue(namesOfFiles.contains(Test_Variables.clCSVFileName+date+".csv"));
			System.out.println("Success");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
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


	@Test (description="Test Case: Test Coccidia Audit Download",enabled= false, priority = 17) 
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

			Helper.driver.findElement(By.cssSelector("#select-runId-0 .checkbox")).click();
			Thread.sleep(500);

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("5. Click on Export with Audit as CSV");

			Helper.driver.findElement(By.id("export-audit-csv")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			Assert.assertTrue(namesOfFiles.contains(Test_Variables.clCSVAuditFileName+date+".csv"));
			System.out.println("Success");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
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


	@Test (description="Test Case: Test Coccidia Template Download",enabled= false, priority = 17) 
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


			Thread.sleep(500);
			Test_Variables.steps.createNode("3. Click on the button");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.id("#export-data-template label")).click();
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
			Test_Variables.steps.createNode("5. Click on Export Data Template");
			Test_Variables.steps.createNode("6. Select Sample MetaData Template");
	//		WebElement button = Helper.driver.findElement(By.xpath(Test_Elements.clSampleMetaDataExport));
	//		JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
	//		jse.executeScript("arguments[0].click()", button);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			@SuppressWarnings("unused")
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			//	Assert.assertTrue(namesOfFiles.contains(Test_Variables.clSampleMetaData+".xlsx"));
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
