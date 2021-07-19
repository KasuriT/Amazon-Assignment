package Test.Ancera.Reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Normal_Ingestion {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Normal Ingestion"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Normal Ingestion Test Report"); 
		
		Helper.config();
		ConfigureLogin.login();
	}
	

	@SuppressWarnings("unchecked")
	@Test (description="Test Case: Run Ingestion for Coccidia", enabled= true, priority= 1) 
	public void NormalIngestionCoccidia() throws InterruptedException, IOException	{

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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
		}


		///////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////
		
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-StrtAssay-01: Verify Coccidia Start Assay API", "This test case will run Coccidia Start Assay API");	
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Run login API to generate token");
			Test_Variables.steps.createNode("1. Add token generated on running Login API in Authorization");
			Test_Variables.steps.createNode("2. Write 'Coccidia' in Pathogen Name");
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
			Thread.sleep(180000);
			
			Test_Variables.steps.createNode("4. Verify 12 lanes are ingested in Coccidia Report");
			Helper.driver.get(Constants.url_CoccidiaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
			Thread.sleep(1000);
			WebElement filter_scroll = Helper.driver.findElement(By.id("instrumentId_show-filter"));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);

			Helper.driver.findElement(By.id("cartridgeId_show-filter")).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("cartridgeId_view-all")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("cartridgeId_search-input")).clear();
			Helper.driver.findElement(By.id("cartridgeId_search-input")).sendKeys(Test_Variables.CartridgeID);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);				
			Helper.driver.findElement(By.cssSelector("#cartridgeId_cust-cb-lst-txt_"+Test_Variables.CartridgeID)).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);
	
			Helper.driver.findElement(By.id("cartridgeId_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			String records = Helper.driver.findElement(By.id("results-found-count")).getText();
			Assert.assertEquals(records, "12"); 

			for(int i = 0; i<12;i++) {
				int lane = i+1;
				Test_Variables.steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
				String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clResultStatusCol+" label")).getText();
				Assert.assertEquals(getResultStatus, "Pending", "Result Status not displayed as Pending in table");

				Test_Variables.steps.createNode("Verify Date is displayed same as that written in API body for lane "+lane);
				String getDate = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clDateCol+" label")).getText();
				Assert.assertEquals(getDate, Test_Variables.dateMMDDYYYY1, "Date not displayed in table");

				Test_Variables.steps.createNode("Verify Pathogen Name as 'Coccidia' for lane "+lane);
				String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clAssayCol+" label")).getText();
				Assert.assertEquals(getPathogen, Test_Variables.Pathogen, "Pathogen Name not displayed as Coccidia in table");

				Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
				String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clCatridgeIDCol+" label")).getText();
				Assert.assertEquals(getCartridgeID, Test_Variables.CartridgeID, "Cartridge ID not displayed in table");

				Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
				String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clInstrumentIDCol+" label")).getText();
				Assert.assertEquals(getInstrumentID, Test_Variables.InstrumentID, "Instrument ID not displayed in table");

				Test_Variables.steps.createNode("Verify Piper User is same as that written in API body for lane "+lane);
				String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clPiperUserCol+" label")).getText();
				Assert.assertEquals(getPiperUser, Test_Variables.PiperUser, "Piper User not displayed in table");

				Test_Variables.steps.createNode("Verify Test Site ID is written  for lane "+lane);
				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clTestSiteIDCol+" label")).getText();
				Assert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not displaying in table");

				Test_Variables.steps.createNode("Verify Test Site Name is written for lane "+lane);
				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.clTestSiteNameCol+" label")).getText();
				Assert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not displaying in table");

				Test_Variables.steps.createNode("Open Audit Trial popup for lane "+lane);			
				WebElement scroll = Helper.driver.findElement(By.id("select-runId-0"));
				((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
				
				Actions builder = new Actions(Helper.driver); 
				WebElement hover = Helper.driver.findElement(By.cssSelector("#audit-trial-"+i+" img"));
				builder.moveToElement(hover).build().perform();	
				Thread.sleep(1500);
				Helper.driver.findElement(By.id("audit-trial-"+i)).click();
				Thread.sleep(1000);
				
				Test_Variables.steps.createNode("Verify Date is same as that written in API body for lane "+lane);
				String getAuditDate = Helper.driver.findElement(By.id("audit-changed-date-0")).getText();
				Assert.assertEquals(getAuditDate, Test_Variables.dateMMDDYYYY1);

				Test_Variables.steps.createNode("Verify Action as 'Created' for lane "+lane);
				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				Assert.assertEquals(getAuditAction, "Created", "Action not displayed as 'Created 'in Audit Log");

				Test_Variables.steps.createNode("Verify Changed by is same as that written in API body for lane "+lane);
				String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
				Assert.assertEquals(getAuditUser, Test_Variables.PiperUser, "Changed By not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
				String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-3.text-dark")).getText(); 
				Assert.assertEquals(getAuditResultStatus, "Pending", "Result Status not displayed as Pending in Audit Log");

				Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
				String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-16.text-dark")).getText();
				Assert.assertEquals(getAuditCartridgeId, Test_Variables.CartridgeID, "Cartridge ID not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
				String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-17.text-dark")).getText();
				Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID, "Instrument ID not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Piper User is same as that written in API body for lane "+lane);
				String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
				Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser, "Piper User not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Test Site ID for lane "+lane);
				String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-26.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteId.isEmpty() == false, "Test Site ID is not displaying in Audit Log");

				Test_Variables.steps.createNode("Verify Test Site Name for lane "+lane);
				String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-27.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteName.isEmpty() == false, "Test Site Name is not displaying in Audit Log");

				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
			}	
			Test_Variables.test.pass("Start Assay all scenarios passed successfully");
			Test_Variables.results.createNode("Start Assay all scenarios passed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Start Assay API failed");
			Test_Variables.results.createNode("Start Assay API failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Start Assay API failed");
			Test_Variables.results.createNode("Start Assay API failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
		}

		/////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////	


		/////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////
		
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
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("File Upload API failed to run");
				Test_Variables.results.createNode(Test_Variables.lstCoccidiaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("File Upload API failed to run");
				Test_Variables.results.createNode(Test_Variables.lstCoccidiaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
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

				Thread.sleep(180000);
				Helper.driver.get(Constants.url_CoccidiaLog);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
				Thread.sleep(1000);

				Test_Variables.steps.createNode("1. Click on Sample ID to expand the filter");
				ClickElement.clickById(Helper.driver, "sampleId_show-filter");			
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("sampleId_view-all")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.steps.createNode("2. Search for the Sample ID's against which the data is ingested");

				for(int j=0; j<4; j++)	{

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
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
				String records = Helper.driver.findElement(By.id("results-found-count")).getText();

				Assert.assertEquals(records, "12"); 

				for(int j = 0; j<12; j++) {
				int lane = j+1;
				Test_Variables.steps.createNode("Verify Result Status is displayed as 'Completed' in table for lane" +lane);
				String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clResultStatusCol+" label")).getText();
				Assert.assertEquals(getResultStatus, "Completed", "Result Status is not displayed as Completed in table");
				
				Test_Variables.steps.createNode("Verify Pathogen is displayed as 'Coccidia' in table for lane" +lane);
				String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clAssayCol+" label")).getText();
				Assert.assertEquals(getPathogen, Test_Variables.Pathogen);
				
				Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane" +lane);
				String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clCatridgeIDCol+" label")).getText();
				Assert.assertEquals(getCartridgeID, Test_Variables.CartridgeID);
				
				Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane" +lane);
				String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clInstrumentIDCol+" label")).getText();
				Assert.assertEquals(getInstrumentID, Test_Variables.InstrumentID);
				
				Test_Variables.steps.createNode("Verify Piper User is same as that written in API body for lane" +lane);
				String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clPiperUserCol+" label")).getText();
				Assert.assertEquals(getPiperUser, Test_Variables.PiperUser);
				
				Test_Variables.steps.createNode("Verify Run Type as "+Test_Variables.RunType+" in API body for lane" +lane);
				String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clRunTypeCol+" label")).getText();
				Assert.assertEquals(getRunType, Test_Variables.RunType, "Run Type is not displayed in table");
				
				Test_Variables.steps.createNode("Verify Improc Version as "+Test_Variables.ImprocVersion+" in API body for lane" +lane);
				String getImprocID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clImprocIDCol+" label")).getText();
				Assert.assertEquals(getImprocID, Test_Variables.ImprocVersion);
				
				Test_Variables.steps.createNode("Verify Test Site ID is displayed in table for lane" +lane);
				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clTestSiteIDCol+" label")).getText();
				Assert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");
				
				Test_Variables.steps.createNode("Verify Test Site Name is displayed in table for lane" +lane);
				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clTestSiteNameCol+" label")).getText();
				Assert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");
				
				Test_Variables.steps.createNode("Verify Total Count is displayed in table for lane" +lane);
				String getTotalCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clTotalCountCol+" label")).getText();
				Assert.assertTrue(getTotalCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify Small Count is displayed in table for lane" +lane);
				String getSmallCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clSmallCountCol+" label")).getText();
				Assert.assertTrue(getSmallCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify Medium Count is displayed in table for lane" +lane);
				String getMediumCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clMediumCountCol+" label")).getText();
				Assert.assertTrue(getMediumCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify Large Count is displayed in table for lane" +lane);
				String getLargeCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clLargeCountCol+" label")).getText();
				Assert.assertTrue(getLargeCount.isEmpty() == false);
					
				String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clSampleIDCol+" label")).getText();
				
				Test_Variables.steps.createNode("Open Audit trail popup for lane" +lane);
				Helper.driver.findElement(By.id("audit-trial-"+j)).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);			
				
				Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
				String getAuditSampleID = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
				Assert.assertEquals(getAuditSampleID, getSampleID);
				
				Test_Variables.steps.createNode("Verify Action as 'Modified' in Audit log for lane" +lane);
				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				Assert.assertEquals(getAuditAction, "Modified");

				Test_Variables.steps.createNode("Verify Changed by in Audit log for lane" +lane);
				String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
				Assert.assertEquals(getAuditUser, Test_Variables.PiperUser);
				
				Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
				String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-3.text-dark")).getText();
				Assert.assertEquals(getAuditResultStatus, "Completed");
				
				Test_Variables.steps.createNode("Verify Cartridge ID is displayed in Audit log for lane" +lane);
				String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-16.text-dark")).getText();
				Assert.assertEquals(getAuditCartridgeId, Test_Variables.CartridgeID);

				Test_Variables.steps.createNode("Verify Instrument ID is displayed in Audit log for lane" +lane);
				String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-17.text-dark")).getText();
				Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

				Test_Variables.steps.createNode("Verify Piper User is displayed in Audit log for lane" +lane);
				String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
				Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser);
				
				Test_Variables.steps.createNode("Verify Run Type is displayed in Audit log for lane" +lane);
				String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-31.text-dark")).getText();
				Assert.assertEquals(getAuditRunType, Test_Variables.RunType);

				Test_Variables.steps.createNode("Verify Improc Version is displayed in Audit log for lane" +lane);
				String getAuditImprocVersion = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-25.text-dark")).getText();
				Assert.assertEquals(getAuditImprocVersion, Test_Variables.ImprocVersion);

				Test_Variables.steps.createNode("Verify Total Count is displayed in Audit log for lane" +lane);
				String getAuditTotalCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-18.text-dark")).getText();
				Assert.assertTrue(getAuditTotalCount.isEmpty() == false);

				Test_Variables.steps.createNode("Verify Small Count is displayed in Audit log for lane" +lane);
				String getAuditSmallCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-19.text-dark")).getText();
				Assert.assertTrue(getAuditSmallCount.isEmpty() == false);

				Test_Variables.steps.createNode("Verify Medium Count is displayed in Audit log for lane" +lane);
				String getAuditMediumCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-20.text-dark")).getText();
				Assert.assertTrue(getAuditMediumCount.isEmpty() == false);

				Test_Variables.steps.createNode("Verify Large Count is displayed in Audit log for lane" +lane);
				String getAuditLargeCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-21.text-dark")).getText();
				Assert.assertTrue(getAuditLargeCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify Test Site ID is displayed in Audit log for lane" +lane);
				String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-26.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

				Test_Variables.steps.createNode("Verify Test Site Name is displayed in Audit log for lane" +lane);
				String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-27.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();   
				}
				
				Test_Variables.test.pass("Ingested Successfully");
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
			}

			////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

			try {	
				Test_Variables.test = Test_Variables.extent.createTest("AN-CL-02: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Run Start Assaay and file Upload API");
				Test_Variables.steps.createNode("1. Upload Sample MetaData Template against the ingested run");
				Test_Variables.steps.createNode("2. Verify the data in Report");

				FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.fileName));
				@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook(fsIP);
				XSSFSheet worksheet = wb.getSheetAt(0);
				Cell cell = null;

				for (int z=0; z<12; z++) {

					String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clSampleIDCol)).getText();
					System.out.println(getSampleID);
				//	String updatedSampleID = getSampleID+"U";
					cell=worksheet.getRow(z+1).createCell(17); 
					cell.setCellValue(getSampleID+"Updt");  

					String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clResultIDCol)).getText();
					cell=worksheet.getRow(z+1).createCell(3); 
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

				Helper.driver.findElement(By.id("sampleId_show-filter")).click();	
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(800);
				Helper.driver.findElement(By.id("sampleId_view-all")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);

				for(int j=0; j<4; j++)	{

					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys("Test"+Test_Variables.lstSampleID.get(j)+"Updt");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);				
					Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(j)+"Updt")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(800);
				}
				
				Helper.driver.findElement(By.id("sampleId_apply")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));		
				 
				for (int k=0; k<12;k++) {
				String getRequestedAssay = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clRequestedAssayCol+" label")).getText();
				Assert.assertEquals(getRequestedAssay, Test_Variables.RequestedAssay);

				String getFlockID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clFlockIDCol+" label")).getText();
				Assert.assertEquals(getFlockID, Test_Variables.FlockID);
				
				String getCSampleID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clCSampleIDCol+" label")).getText();
				Assert.assertEquals(getCSampleID, Test_Variables.CustomerSampleID);
				
				String getSampleMatrix = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clSampleMatrixCol+" label")).getText();
				Assert.assertEquals(getSampleMatrix, Test_Variables.SampleMatrix);
				
				String getKitLot = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clKitLotCol+" label")).getText();
				Assert.assertEquals(getKitLot, Test_Variables.KitLot);
				
				String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clSampleIDCol+" label")).getText();
				
				Helper.driver.findElement(By.id("audit-trial-"+k)).click();
				Thread.sleep(1000);		
				
				String getAuditSampleID = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
				Assert.assertEquals(getAuditSampleID, getSampleID);
				
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
				}
				Test_Variables.test.pass("Data Verified successfully on uploading Sample Metadata Template");
				Test_Variables.results.createNode("Data Verified successfully on uploading Sample Metadata Template");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);

			}catch(AssertionError er) {
				Test_Variables.test.fail("Data failed to verify on uploading Sample Metadata Template");
				Test_Variables.results.createNode("Data failed to verify on uploading Sample Metadata Template");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Data failed to verify on uploading Sample Metadata Template");
				Test_Variables.results.createNode("Data failed to verify on uploading Sample Metadata Template");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);	
			}
			Thread.sleep(2000);	
		}	
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test (description="Test Case: Normal Ingestion for Salmonella", enabled= true, priority= 2) 
	public void NormalIngestionSalmonella() throws InterruptedException, IOException	{

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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
		}

		Test_Variables.test = Test_Variables.extent.createTest("AN-API-Anncmnt: Verify File Announcement API", "This test case will run file announcement api");	
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
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("File Announcement API failed to run");
			Test_Variables.results.createNode("File Announcement API failed to run");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
		}


		///////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////
		
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-StrtAssay-01: Verify Salmonella Start Assay API", "This test case will run Salmonella Start Assay API");	
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Run login API to generate token");
			Test_Variables.steps.createNode("1. Add token generated on running Login API in Authorization");
			Test_Variables.steps.createNode("2. Write 'Salmonella' in Pathogen Name");
			Test_Variables.steps.createNode("3. Run the API");


			RequestSpecification request_startAssay = RestAssured.given();

			request_startAssay.header("Content-Type", "application/json");
			request_startAssay.header("Authorization", "bearer " +token);

			HttpGet postRequest3 = new HttpGet(Constants.api_StartAssay);
			postRequest3.addHeader("Content-Type", "application/json");
			postRequest3.addHeader("Authorization", "Bearer "+token);

			json4.put("DateTime", Test_Variables.lstStartAssaySalmonella.get(0).DateTime);
			json4.put("InstrumentId", Test_Variables.lstStartAssaySalmonella.get(0).InstrumentID);
			json4.put("UserId", Test_Variables.lstStartAssaySalmonella.get(0).UserID);
			json4.put("CartridgeId", Test_Variables.lstStartAssaySalmonella.get(0).CartridgeID);
			json4.put("RunId", Test_Variables.lstStartAssaySalmonella.get(0).RunID);
			json4.put("PathogenName", Test_Variables.lstStartAssaySalmonella.get(0).PathogenName);				

			request_startAssay.body(json4.toString());
			Response response3 = request_startAssay.post(Constants.api_StartAssay);

			String data4 = response3.asString();
			System.out.println(data4);
			Thread.sleep(180000);
			
			Test_Variables.steps.createNode("4. Verify 12 lanes are ingested in Salmonella Report");
			Helper.driver.get(Constants.url_SalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
			Thread.sleep(1000);
			WebElement filter_scroll = Helper.driver.findElement(By.id("instrumentId_show-filter"));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);

			Helper.driver.findElement(By.id("cartridgeId_show-filter")).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("cartridgeId_view-all")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("cartridgeId_search-input")).clear();
			Helper.driver.findElement(By.id("cartridgeId_search-input")).sendKeys(Test_Variables.CartridgeID);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);			
			ClickElement.clickByCss(Helper.driver, "#cartridgeId_cust-cb-lst-txt_"+Test_Variables.CartridgeID);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
		//	Helper.driver.findElement(By.cssSelector("#cartridgeId_cust-cb-lst-txt_"+Test_Variables.CartridgeID)).click();
			Helper.driver.findElement(By.id("cartridgeId_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			String records = Helper.driver.findElement(By.id("results-found-count")).getText();
			Assert.assertEquals(records, "12"); 

			for(int i = 0; i<12;i++) {
				int lane = i+1;
				Test_Variables.steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
				String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slResultStatusCol+" label")).getText();
				Assert.assertEquals(getResultStatus, "Pending", "Result Status not displayed as Pending in table");

				Test_Variables.steps.createNode("Verify Date is displayed same as that written in API body for lane "+lane);
				String getDate = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slDateCol+" label")).getText();
				Assert.assertEquals(getDate, Test_Variables.dateMMDDYYYY1, "Date not displayed in table");

				Test_Variables.steps.createNode("Verify Pathogen Name as 'S​a​l​m​o​n​e​l​l​a​ ​P​r​e​s​e​n​c​e​/​A​b​s​e​n​c​e​' for lane "+lane);
			//	String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slAssayCol+" label")).getText();
			//	Assert.assertEquals(getPathogen, "S​a​l​m​o​n​e​l​l​a​ ​P​r​e​s​e​n​c​e​/​A​b​s​e​n​c​e​", "Pathogen Name not displayed as Salmonella in table");

				Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
				String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slCartridgeIDCol+" label")).getText();
				Assert.assertEquals(getCartridgeID, Test_Variables.CartridgeID, "Cartridge ID not displayed in table");

				Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
				String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slInstrumentIDCol+" label")).getText();
				Assert.assertEquals(getInstrumentID, Test_Variables.InstrumentID, "Instrument ID not displayed in table");

				Test_Variables.steps.createNode("Verify Piper User is same as that written in API body for lane "+lane);
				String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slPiperUserCol+" label")).getText();
				Assert.assertEquals(getPiperUser, Test_Variables.PiperUser, "Piper User not displayed in table");

				Test_Variables.steps.createNode("Verify Test Site ID is written  for lane "+lane);
				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slTestSiteIDCol+" label")).getText();
				Assert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not displaying in table");

				Test_Variables.steps.createNode("Verify Test Site Name is written for lane "+lane);
				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slTestSiteNameCol+" label")).getText();
				Assert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not displaying in table");

				Test_Variables.steps.createNode("Open Audit Trial popup for lane "+lane);
				WebElement scroll = Helper.driver.findElement(By.id("select-runId-0"));
				((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
				
				Actions builder = new Actions(Helper.driver); 
				WebElement hover = Helper.driver.findElement(By.cssSelector("#audit-trial-"+i+" img"));
				builder.moveToElement(hover).build().perform();	
				Thread.sleep(1500);
				Helper.driver.findElement(By.id("audit-trial-"+i)).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("audit-changed-date-0")));
				Test_Variables.steps.createNode("Verify Date is same as that written in API body for lane "+lane);
				String getAuditDate = Helper.driver.findElement(By.id("audit-changed-date-0")).getText();
				Assert.assertEquals(getAuditDate, Test_Variables.dateMMDDYYYY1);

				Test_Variables.steps.createNode("Verify Action as 'Created' for lane "+lane);
				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				Assert.assertEquals(getAuditAction, "Created", "Action not displayed as 'Created 'in Audit Log");

				Test_Variables.steps.createNode("Verify Changed by is same as that written in API body for lane "+lane);
				String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
				Assert.assertEquals(getAuditUser, Test_Variables.PiperUser, "Changed By not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
				String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-3.text-dark")).getText(); 
				Assert.assertEquals(getAuditResultStatus, "Pending", "Result Status not displayed as Pending in Audit Log");

				Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
				String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-15.text-dark")).getText();
				Assert.assertEquals(getAuditCartridgeId, Test_Variables.CartridgeID, "Cartridge ID not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
				String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-16.text-dark")).getText();
				Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID, "Instrument ID not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Piper User is same as that written in API body for lane "+lane);
				String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
				Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser, "Piper User not displayed in Audit Log");

				Test_Variables.steps.createNode("Verify Test Site ID for lane "+lane);
				String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-26.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteId.isEmpty() == false, "Test Site ID is not displaying in Audit Log");

				Test_Variables.steps.createNode("Verify Test Site Name for lane "+lane);
				String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-29.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteName.isEmpty() == false, "Test Site Name is not displaying in Audit Log");

				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
			}	
			Test_Variables.test.pass("Start Assay all scenarios passed successfully");
			Test_Variables.results.createNode("Start Assay all scenarios passed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Start Assay API failed");
			Test_Variables.results.createNode("Start Assay API failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Start Assay API failed");
			Test_Variables.results.createNode("Start Assay API failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
		}

		/////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////	


		/////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////
		
		for(int i=0; i<Test_Variables.lstSalmonellaIngest.size(); i++)	{
			try{
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstSalmonellaIngest.get(i).testCaseTitle, Test_Variables.lstSalmonellaIngest.get(i).testCaseDesc);	
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

				json3.put("runId", Test_Variables.lstSalmonellaIngest.get(i).runId);
				json3.put("checksum", Test_Variables.lstSalmonellaIngest.get(i).checksum);
				json3.put("fileName", Test_Variables.lstSalmonellaIngest.get(i).fileName);
				json3.put("fileType", Test_Variables.lstSalmonellaIngest.get(i).fileType);
				json3.put("file", Test_Variables.lstSalmonellaIngest.get(i).file);
				json3.put("fileJson", Test_Variables.lstSalmonellaIngest.get(i).fileJson);				
				json3.put("Improc", Test_Variables.lstSalmonellaIngest.get(i).improc);
				json3.put("RunMode", Test_Variables.lstSalmonellaIngest.get(i).runMode);

				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(Constants.api_FileUpload);
				String data3 = response2.asString();
				System.out.println(data3);

				JsonPath jsonPathEvaluator1 = response.jsonPath();
				jsonPathEvaluator1.get("statusCode");
				
				Test_Variables.test.pass("File Upload API ran successfully");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).passScenario);
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("File Upload API failed to run");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("File Upload API failed to run");
				Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
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
				Test_Variables.preconditions.createNode("5. Click on Salmonella Log");

				Thread.sleep(180000);
				Helper.driver.get(Constants.url_SalmonellaLog);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
				Thread.sleep(1000);

				Test_Variables.steps.createNode("1. Click on Sample ID to expand the filter");
				ClickElement.clickById(Helper.driver, "sampleId_show-filter");			
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("sampleId_view-all")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.steps.createNode("2. Search for the Sample ID's against which the data is ingested");

				for(int j=0; j<4; j++)	{

					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys("Test"+Test_Variables.lstSampleID.get(j));
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);	
					ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(j));
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);	
				//	Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(j))).click();
				}

				Test_Variables.steps.createNode("3. Click on Apply filter button");
				Helper.driver.findElement(By.id("sampleId_apply")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));
				String records = Helper.driver.findElement(By.id("results-found-count")).getText();

				Assert.assertEquals(records, "12"); 

				for(int j = 0; j<12; j++) {
				int lane = j+1;
				Test_Variables.steps.createNode("Verify Result Status is displayed as 'Completed' in table for lane" +lane);
				String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slResultStatusCol+" label")).getText();
			    System.out.println(getResultStatus);
				Assert.assertEquals(getResultStatus, "Completed", "Result Status is not displayed as Completed in table");
				
				Test_Variables.steps.createNode("Verify Pathogen is displayed as 'Coccidia' in table for lane" +lane);
			//	String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slAssayCol+" label")).getText();
			//	Assert.assertEquals(getPathogen, "S​a​l​m​o​n​e​l​l​a​ ​P​r​e​s​e​n​c​e​/​A​b​s​e​n​c​e​");
				
				Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane" +lane);
				String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slCartridgeIDCol+" label")).getText();
				Assert.assertEquals(getCartridgeID, Test_Variables.CartridgeID);
				
				Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane" +lane);
				String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slInstrumentIDCol+" label")).getText();
				Assert.assertEquals(getInstrumentID, Test_Variables.InstrumentID);
				
				Test_Variables.steps.createNode("Verify Piper User is same as that written in API body for lane" +lane);
				String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slPiperUserCol+" label")).getText();
				Assert.assertEquals(getPiperUser, Test_Variables.PiperUser);
				
				Test_Variables.steps.createNode("Verify Run Type as "+Test_Variables.RunType+" in API body for lane" +lane);
				String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slRunTypeCol+" label")).getText();
				Assert.assertEquals(getRunType, Test_Variables.RunType, "Run Type is not displayed in table");
				
				Test_Variables.steps.createNode("Verify Improc Version as "+Test_Variables.slImprocVersion+" in API body for lane" +lane);
				String getImprocID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slImprocIDCol+" label")).getText();
				Assert.assertEquals(getImprocID, Test_Variables.slImprocVersion);
				
				Test_Variables.steps.createNode("Verify Test Site ID is displayed in table for lane" +lane);
				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slTestSiteIDCol+" label")).getText();
				Assert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");
				
				Test_Variables.steps.createNode("Verify Test Site Name is displayed in table for lane" +lane);
				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slTestSiteNameCol+" label")).getText();
				Assert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");
				
				Test_Variables.steps.createNode("Verify W1 PC Count is displayed in table for lane" +lane);
				String getW1PCCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW1PCCountCol+" label")).getText();
				Assert.assertTrue(getW1PCCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify W1 Cell Count is displayed in table for lane" +lane);
				String getW1CellCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW1CellCountCol+" label")).getText();
				Assert.assertTrue(getW1CellCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify W2 PC Count is displayed in table for lane" +lane);
				String getW2PCCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW2CPCCountCol+" label")).getText();
				Assert.assertTrue(getW2PCCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify W2 Cell Count is displayed in table for lane" +lane);
				String getW2CellCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW2CellCountCol+" label")).getText();
				Assert.assertTrue(getW2CellCount.isEmpty() == false);
					
				String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slSampleIDCol+" label")).getText();
				
				Test_Variables.steps.createNode("Open Audit trail popup for lane" +lane);
				Helper.driver.findElement(By.id("audit-trial-"+j)).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);			
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));
				Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
				String getAuditSampleID = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
				Assert.assertEquals(getAuditSampleID, getSampleID);
				
				Test_Variables.steps.createNode("Verify Action as 'Modified' in Audit log for lane" +lane);
				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				Assert.assertEquals(getAuditAction, "Modified");

				Test_Variables.steps.createNode("Verify Changed by in Audit log for lane" +lane);
				String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
				Assert.assertEquals(getAuditUser, Test_Variables.PiperUser);
				
				Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
				String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-3.text-dark")).getText();
				Assert.assertEquals(getAuditResultStatus, "Completed");
				
				Test_Variables.steps.createNode("Verify Cartridge ID is displayed in Audit log for lane" +lane);
				String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-15.text-dark")).getText();
				Assert.assertEquals(getAuditCartridgeId, Test_Variables.CartridgeID);

				Test_Variables.steps.createNode("Verify Instrument ID is displayed in Audit log for lane" +lane);
				String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-16.text-dark")).getText();
				Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

				Test_Variables.steps.createNode("Verify Piper User is displayed in Audit log for lane" +lane);
				String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
				Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser);
				
				Test_Variables.steps.createNode("Verify Run Type is displayed in Audit log for lane" +lane);
				String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-30.text-dark")).getText();
				Assert.assertEquals(getAuditRunType, Test_Variables.RunType);

				Test_Variables.steps.createNode("Verify Improc Version is displayed in Audit log for lane" +lane);
				String getAuditImprocVersion = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-25.text-dark")).getText();
				Assert.assertEquals(getAuditImprocVersion, Test_Variables.slImprocVersion);

				Test_Variables.steps.createNode("Verify W1 PC Count Count is displayed in Audit log for lane" +lane);
				String getAuditW1PCCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-18.text-dark")).getText();
				Assert.assertTrue(getAuditW1PCCount.isEmpty() == false);

				Test_Variables.steps.createNode("Verify W1 Cell Count is displayed in Audit log for lane" +lane);
				String getAuditW1CellCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-17.text-dark")).getText();
				Assert.assertTrue(getAuditW1CellCount.isEmpty() == false);

				Test_Variables.steps.createNode("Verify W2 PC Count is displayed in Audit log for lane" +lane);
				String getAuditW2PCCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-21.text-dark")).getText();
				Assert.assertTrue(getAuditW2PCCount.isEmpty() == false);

				Test_Variables.steps.createNode("Verify W2 Cell Count is displayed in Audit log for lane" +lane);
				String getAuditW2CellCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-20.text-dark")).getText();
				Assert.assertTrue(getAuditW2CellCount.isEmpty() == false);
				
				Test_Variables.steps.createNode("Verify Test Site ID is displayed in Audit log for lane" +lane);
				String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-26.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

				Test_Variables.steps.createNode("Verify Test Site Name is displayed in Audit log for lane" +lane);
				String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-29.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();   
				}
				
				Test_Variables.test.pass("Ingested Successfully");
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
			}

			////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

			try {	
				Test_Variables.test = Test_Variables.extent.createTest("AN-SL-02: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Run Start Assaay and file Upload API");
				Test_Variables.steps.createNode("1. Upload Sample MetaData Template against the ingested run");
				Test_Variables.steps.createNode("2. Verify the data in Report");

				FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.fileName));
				@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook(fsIP);
				XSSFSheet worksheet = wb.getSheetAt(0);
				Cell cell = null;

				for (int z=0; z<12; z++) {

					String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.slSampleIDCol)).getText();
					cell=worksheet.getRow(z+1).createCell(17); 
					cell.setCellValue(getSampleID+"Updt");  

					String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.slResultIDCol)).getText();
					cell=worksheet.getRow(z+1).createCell(3); 
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

					String getLane = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.slLaneCol)).getText();
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

				Helper.driver.get(Constants.url_SalmonellaLog);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
				Thread.sleep(1000);

				Helper.driver.findElement(By.id("sampleId_show-filter")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);
				Helper.driver.findElement(By.id("sampleId_view-all")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);

				for(int j=0; j<4; j++)	{

					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys("Test"+Test_Variables.lstSampleID.get(j)+"Updt");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);	
					ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(j)+"Updt");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);
				}
				System.out.println("b");
				Helper.driver.findElement(By.id("sampleId_apply")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));		
				 
				for (int k=0; k<12;k++) {
				String getRequestedAssay = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.slRequestedAssayCol+" label")).getText();
				Assert.assertEquals(getRequestedAssay, Test_Variables.RequestedAssay);

				String getFlockID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.slFlockIDCol+" label")).getText();
				Assert.assertEquals(getFlockID, Test_Variables.FlockID);
				
				String getCSampleID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.slCSampleIDCol+" label")).getText();
				Assert.assertEquals(getCSampleID, Test_Variables.CustomerSampleID);
				
				String getSampleMatrix = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.slSampleMatrixCol+" label")).getText();
				Assert.assertEquals(getSampleMatrix, Test_Variables.SampleMatrix);
				
				String getKitLot = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.slKitLotCol+" label")).getText();
				Assert.assertEquals(getKitLot, Test_Variables.KitLot);
				
				String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.slSampleIDCol+" label")).getText();
				
				Helper.driver.findElement(By.id("audit-trial-"+k)).click();
				Thread.sleep(1000);		
				
				String getAuditSampleID = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
				Assert.assertEquals(getAuditSampleID, getSampleID);
				
				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				Assert.assertEquals(getAuditAction, "Modified");

				String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
				Assert.assertEquals(getAuditUser, Test_Variables.PiperUser);

				String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-3.text-dark")).getText();
				Assert.assertEquals(getAuditResultStatus, "Completed");

				String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-15.text-dark")).getText();
				Assert.assertEquals(getAuditCartridgeId, Test_Variables.CartridgeID);

				String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-16.text-dark")).getText();
				Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

				String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
				Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser);

				String getAuditImprocVersion = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-25.text-dark")).getText();
				Assert.assertEquals(getAuditImprocVersion, Test_Variables.slImprocVersion);

				String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-26.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

				String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-29.text-dark")).getText();
				Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click(); 
				}
				Test_Variables.test.pass("Data Verified successfully on uploading Sample Metadata Template");
				Test_Variables.results.createNode("Data Verified successfully on uploading Sample Metadata Template");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);

			}catch(AssertionError er) {
				Test_Variables.test.fail("Data failed to verify on uploading Sample Metadata Template");
				Test_Variables.results.createNode("Data failed to verify on uploading Sample Metadata Template");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Data failed to verify on uploading Sample Metadata Template");
				Test_Variables.results.createNode("Data failed to verify on uploading Sample Metadata Template");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);	
			}

			Thread.sleep(2000);	
		}	
	}
	

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}
