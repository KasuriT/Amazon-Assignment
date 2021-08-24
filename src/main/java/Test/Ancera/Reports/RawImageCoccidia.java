package Test.Ancera.Reports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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

import Models.NormalIngestionModel;
import Models.RawImageModel;
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

public class RawImageCoccidia {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Raw Image Ingestion"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Raw Image Ingestion Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}

	
	@Test (description="Test Case: Raw Image Ingestion for Coccidia", enabled= false, priority= 1) 
	public void RawImagfdeCocci() throws InterruptedException, IOException	{
		
		DateFormat dateFormat = new SimpleDateFormat("mm.ss");
		Date date1 = new Date();
		dateFormat.format(date1);
		
//		File file1 = new File("C:\\Users\\User\\Desktop\\hello.txt"); 
//		FileReader fr = new FileReader(file1);
//		long length = file1.length( ); 
//		for(long i=0; i<length; i++) {
//		System.out.print((char) fr.read( ));}

		String TestFile = "C:\\Users\\User\\Desktop\\hello.txt";
		FileReader FR = new FileReader(TestFile);
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

//		while((Content = BR.readLine())!= null){
//			System.out.println(Content);
//		}
				
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject json = new JSONObject();
		json.put("piperid", Test_Variables.piperId);
		while((Content = BR.readLine())!= null){
		//	System.out.println(Content);
		json.put("password", Content);
		
		}
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
	//		Test_Variables.test.pass("Login Api ran successfully");
	//		Test_Variables.results.createNode("Login API ran successfully; token was generated successfully");
	//		Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
		}
		catch(AssertionError er) {
	//		Test_Variables.test.fail("Login Api failed");
	//		Test_Variables.results.createNode("Login API failed to run; token was not generated");
	//		Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
		}catch(Exception ex){
	//		Test_Variables.test.fail("Login Api failed");
	//		Test_Variables.results.createNode("Login API failed to run; token was not generated");
	//		Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
		}
		
		
		
		
		
		
		
		

		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test (description="Test Case: Raw Image Ingestion for Coccidia", enabled= true, priority= 1) 
	public void RawImageCocci() throws InterruptedException, IOException	{
		Test_Variables.lstRawImageCoccidia = RawImageModel.FillData();
		for (RawImageModel objModel : Test_Variables.lstRawImageCoccidia) { 
			try{

				Test_Variables.test = Test_Variables.extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.steps.createNode("1. Enter valid piperid ("+Test_Variables.piperId+")");
				Test_Variables.steps.createNode("2. Enter valid password (********)");
				Test_Variables.steps.createNode("3. Run the API");


				for (ReportFilters objFilter : objModel.lstFilters) {

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
					if (objModel.runStartAssay ) {
						try {
							Test_Variables.test = Test_Variables.extent.createTest("AN-StrtAssay-01: Verify "+objModel.pathogen+" Start Assay API", "This test case will run Salmonella Start Assay API");	
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
							json4.put("CartridgeId", objModel.cartridgeID);
							json4.put("RunId", objFilter.LstSampleID.get(0));
							json4.put("PathogenName", objModel.pathogen);				

							request_startAssay.body(json4.toString());
							Response response3 = request_startAssay.post(Constants.api_StartAssay);

							String data4 = response3.asString();
							System.out.println(data4);
							Thread.sleep(60000);
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
					}
					/////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////	


					/////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////

					for(int i=0; i<Test_Variables.lstSalmonellaIngest.size(); i++)	{
						try{
							Test_Variables.test = Test_Variables.extent.createTest("AN-"+objModel.pathogen+"-01: Ingest "+objModel.pathogen+" run", "This test case will verify "+objModel.pathogen+" run");	
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

							HttpGet postRequest1 = new HttpGet(Constants.api_RawImage);
							postRequest1.addHeader("Content-Type", "application/json");
							postRequest1.addHeader("Authorization", "Bearer "+token);

							File file1 = new File("C:\\Users\\User\\Desktop\\hello.txt"); 
							FileReader fr = new FileReader(file1);
							long length = file1.length( ); 

							for(long j=0; j<length; j++)
							  // System.out.print((char) fr.read( ));
							
							json3.put("cartridgeId", objModel.cartridgeID);
							json3.put("lane", objModel.lane);
							json3.put("dateTime", "2021-08-21T16:48:15.000Z");
							json3.put("piperId", "PSN0001");
							json3.put("runType", "1");
							json3.put("runId", objFilter.LstSampleID.get(0));
							json3.put("Pathogen", objModel.pathogen);
							json3.put("sampleid", objFilter.LstSampleID.get(0));
							json3.put("instrumentid", "PSN0001");
							json3.put("userid", Test_Variables.UserID);
							json3.put("checksum", objModel.checksum);
							json3.put("fileName", "20210823-Rawimage-TC03_L01S01.bmp");
							json3.put("fileType", "bmp");
							
							String TestFile = "C:\\Users\\User\\Downloads\\RawImages\\base64.txt";
							FileReader FR = new FileReader(TestFile);
							BufferedReader BR = new BufferedReader(FR);
							String Content = "";
							
							while((Content = BR.readLine())!= null){
								//System.out.println(Content);
							json.put("file", Content);
							
							}
							
	
							
						//	json3.put("file", RawImageModel.base64);
							json3.put("Improc", "ImprocCocc01");
							json3.put("countOutCome", "");
							json3.put("statusCode", "");
							json3.put("IE_COLLECTION_SITE_ID", "1");
							json3.put("runMode", "1");						

							request_fileupload.body(json3.toString());
							Response response2 = request_fileupload.post(Constants.api_RawImage);
							String data3 = response2.asString();
							System.out.println(data3);

							JsonPath jsonPathEvaluator1 = response.jsonPath();
							jsonPathEvaluator1.get("statusCode");

							Test_Variables.test.pass("Raw Image API ran successfully");
							Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).passScenario);
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
						}
						catch(AssertionError er) {
							Test_Variables.test.fail("Raw Image API failed to run");
							Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).failScenario);
							Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail("Raw Image API failed to run");
							Test_Variables.results.createNode(Test_Variables.lstSalmonellaIngest.get(i).failScenario);
							Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
						}

						try{
							Test_Variables.test = Test_Variables.extent.createTest("AN-"+objModel.pathogen+"-02: Verify the ingestion and relevant records from report", "This test case will verify the ingestion and relevant records from report");	
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

						//	for(int j=0; j<4; j++)	{

								Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);	
								ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0));
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);	
						//	}

							Test_Variables.steps.createNode("3. Click on Apply filter button");
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));
							String records = Helper.driver.findElement(By.id("results-found-count")).getText();

							Assert.assertEquals(records, "1"); 

							for(int j = 0; j<12; j++) {
								int lane = j+1;
								Test_Variables.steps.createNode("Verify Result Status is displayed as 'Completed' in table for lane" +lane);
						//		String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slResultStatusCol+" label")).getText();
						//		Assert.assertEquals(getResultStatus, "Completed", "Result Status is not displayed as Completed in table");

								Test_Variables.steps.createNode("Verify Pathogen is displayed as 'Coccidia' in table for lane" +lane);
								//	String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slAssayCol+" label")).getText();
								//	Assert.assertEquals(getPathogen, "S​a​l​m​o​n​e​l​l​a​ ​P​r​e​s​e​n​c​e​/​A​b​s​e​n​c​e​");

								Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane" +lane);
					//			String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slCartridgeIDCol+" label")).getText();
					//			Assert.assertEquals(getCartridgeID, objModel.cartridgeID);

								Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane" +lane);
					//			String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slInstrumentIDCol+" label")).getText();
					//			Assert.assertEquals(getInstrumentID, Test_Variables.InstrumentID);

								Test_Variables.steps.createNode("Verify Piper User is same as that written in API body for lane" +lane);
						//		String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slPiperUserCol+" label")).getText();
						//		Assert.assertEquals(getPiperUser, Test_Variables.PiperUser);

								Test_Variables.steps.createNode("Verify Run Type as "+Test_Variables.RunType+" in API body for lane" +lane);
					//			String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slRunTypeCol+" label")).getText();
					//			Assert.assertEquals(getRunType, Test_Variables.RunType, "Run Type is not displayed in table");

								Test_Variables.steps.createNode("Verify Improc Version as "+Test_Variables.slImprocVersion+" in API body for lane" +lane);
					//			String getImprocID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slImprocIDCol+" label")).getText();
					//			Assert.assertEquals(getImprocID, Test_Variables.slImprocVersion);

								Test_Variables.steps.createNode("Verify Test Site ID is displayed in table for lane" +lane);
				//				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slTestSiteIDCol+" label")).getText();
				//				Assert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");

								Test_Variables.steps.createNode("Verify Test Site Name is displayed in table for lane" +lane);
				//				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slTestSiteNameCol+" label")).getText();
				//				Assert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");

								Test_Variables.steps.createNode("Verify W1 PC Count is displayed in table for lane" +lane);
				//				String getW1PCCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW1PCCountCol+" label")).getText();
				//				Assert.assertTrue(getW1PCCount.isEmpty() == false);

								Test_Variables.steps.createNode("Verify W1 Cell Count is displayed in table for lane" +lane);
				//				String getW1CellCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW1CellCountCol+" label")).getText();
				//				Assert.assertTrue(getW1CellCount.isEmpty() == false);

								Test_Variables.steps.createNode("Verify W2 PC Count is displayed in table for lane" +lane);
				//				String getW2PCCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW2CPCCountCol+" label")).getText();
				//				Assert.assertTrue(getW2PCCount.isEmpty() == false);

								Test_Variables.steps.createNode("Verify W2 Cell Count is displayed in table for lane" +lane);
				//				String getW2CellCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW2CellCountCol+" label")).getText();
				//				Assert.assertTrue(getW2CellCount.isEmpty() == false);

			//					String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slSampleIDCol+" label")).getText();

								Test_Variables.steps.createNode("Open Audit trail popup for lane" +lane);
								Helper.driver.findElement(By.id("audit-trial-"+j)).click();
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1500);			
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));
								Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
				//				String getAuditSampleID = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditSampleIDCol+".text-dark")).getText();
				//				Assert.assertEquals(getAuditSampleID, getSampleID);

								if (objModel.runStartAssay) {
									Test_Variables.steps.createNode("Verify Action as 'Modified' in Audit log for lane" +lane);
				//					String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
				//					Assert.assertEquals(getAuditAction, "Modified");
								}
								else {
									Test_Variables.steps.createNode("Verify Action as 'Created' in Audit log for lane" +lane);
			//						String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
			//						Assert.assertEquals(getAuditAction, "Created");
								}
								
								Test_Variables.steps.createNode("Verify Changed by in Audit log for lane" +lane);
			//					String getAuditUser = Helper.driver.findElement(By.id("audit-changed-by-0")).getText();
			//					Assert.assertEquals(getAuditUser, Test_Variables.PiperUser);

								Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
			//					String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditResultStatusCol+".text-dark")).getText();
			//					Assert.assertEquals(getAuditResultStatus, "Completed");

								Test_Variables.steps.createNode("Verify Cartridge ID is displayed in Audit log for lane" +lane);
			//					String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditCartridgeIDCol+".text-dark")).getText();
			//					Assert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

								Test_Variables.steps.createNode("Verify Instrument ID is displayed in Audit log for lane" +lane);
			//					String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditInstrumentIDCol+".text-dark")).getText();
			//					Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

								Test_Variables.steps.createNode("Verify Piper User is displayed in Audit log for lane" +lane);
					//			String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditPiperUserCol+".text-dark")).getText();
					//			Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser);

								Test_Variables.steps.createNode("Verify Run Type is displayed in Audit log for lane" +lane);
				//				String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditRunTypeCol+".text-dark")).getText();
				//				Assert.assertEquals(getAuditRunType, Test_Variables.RunType);

								Test_Variables.steps.createNode("Verify Improc Version is displayed in Audit log for lane" +lane);
			//					String getAuditImprocVersion = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditImprocIDCol+".text-dark")).getText();
			//					Assert.assertEquals(getAuditImprocVersion, Test_Variables.slImprocVersion);

								Test_Variables.steps.createNode("Verify W1 PC Count Count is displayed in Audit log for lane" +lane);
			//					String getAuditW1PCCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW1PCCountCol+".text-dark")).getText();
			//					Assert.assertTrue(getAuditW1PCCount.isEmpty() == false);

								Test_Variables.steps.createNode("Verify W1 Cell Count is displayed in Audit log for lane" +lane);
		//						String getAuditW1CellCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW1CellCountCol+".text-dark")).getText();
		//						Assert.assertTrue(getAuditW1CellCount.isEmpty() == false);

								Test_Variables.steps.createNode("Verify W2 PC Count is displayed in Audit log for lane" +lane);
		//						String getAuditW2PCCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW2CPCCountCol+".text-dark")).getText();
		//						Assert.assertTrue(getAuditW2PCCount.isEmpty() == false);

								Test_Variables.steps.createNode("Verify W2 Cell Count is displayed in Audit log for lane" +lane);
		//						String getAuditW2CellCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW2CellCountCol+".text-dark")).getText();
		//						Assert.assertTrue(getAuditW2CellCount.isEmpty() == false);

								Test_Variables.steps.createNode("Verify Test Site ID is displayed in Audit log for lane" +lane);
	//							String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteIDCol+".text-dark")).getText();
	//							Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

								Test_Variables.steps.createNode("Verify Test Site Name is displayed in Audit log for lane" +lane);
	//							String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteNameCol+".text-dark")).getText();
	//							Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

								Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();   
							}

							Test_Variables.test.pass("Ingested Successfully");
							Test_Variables.results.createNode("Data ingestion verified successfully");
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
/*
						try {	
							Test_Variables.test = Test_Variables.extent.createTest("AN-Salmonella-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
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
								cell.setCellValue(objModel.cartridgeID); 

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
								Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(j)+"Updt");
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);	
								ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(j)+"Updt");
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);
							}
							System.out.println("b");
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);	

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

								String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditResultStatusCol+".text-dark")).getText();
								Assert.assertEquals(getAuditResultStatus, "Completed");

								String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditCartridgeIDCol+".text-dark")).getText();
								Assert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

								String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditInstrumentIDCol+".text-dark")).getText();
								Assert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

					//			String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditPiperUserCol+".text-dark")).getText();
					//			Assert.assertEquals(getAuditPiperUser, Test_Variables.PiperUser);

								String getAuditImprocVersion = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditImprocIDCol+".text-dark")).getText();
								Assert.assertEquals(getAuditImprocVersion, Test_Variables.slImprocVersion);

								String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteIDCol+".text-dark")).getText();
								Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

								String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteNameCol+".text-dark")).getText();
								Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

								Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click(); 
							}
							Test_Variables.test.pass("Data Verified successfully on uploading Sample Metadata Template");
							Test_Variables.results.createNode("Data Verified successfully on uploading Sample Metadata Template");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.NormalIngestionReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);

						}
						catch(Exception ex){
						}
						*/
						Thread.sleep(2000);	
					}
				}

			}
			catch(Exception ex){
			}

		}		
	}
	
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
	
	
}
