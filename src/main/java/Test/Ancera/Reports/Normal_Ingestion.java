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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.NormalIngestionModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Constants.*;

public class Normal_Ingestion {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Normal Ingestion"+date+".html");
		spark.config().setReportName("Normal Ingestion Test Report"); 
		config();
		ConfigureLogin.login();
	}
	
	
	@SuppressWarnings("unchecked")
	@Test (description="Test Case: Normal Ingestion for Salmonella/Listeria", enabled= true, priority= 1) 
	public void NormalIngestionSalmonella() throws InterruptedException, IOException	{
		lstNormalIngestion = NormalIngestionModel.FillData();
		for (NormalIngestionModel objModel : lstNormalIngestion) { 
			try{

				test = extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				steps.createNode("1. Enter valid piperid ("+piperId+")");
				steps.createNode("2. Enter valid password (********)");
				steps.createNode("3. Run the API");

				for (ReportFilters objFilter : objModel.lstFilters) {

					DateFormat dateFormat = new SimpleDateFormat("mm.ss");
					Date date1 = new Date();
					dateFormat.format(date1);

					RequestSpecification request = RestAssured.given();
					request.header("Content-Type", "application/json");
					JSONObject json = new JSONObject();
					json.put("piperid", piperId);
					json.put("password", piperPassword);
					json.put("DISAPIVersion", "14.13");
					request.body(json.toString());
					Response response = request.post(api_login);
					int code = response.getStatusCode();
					Assert.assertEquals(code, 200);

					String data = response.asString();
					System.out.println(data);

					JsonPath jsonPathEvaluator = response.jsonPath();
					String token = jsonPathEvaluator.get("token");		
					String statusCode = jsonPathEvaluator.get("statusCode");
					//System.out.println(token);

					try{
						Assert.assertEquals(statusCode, "114"); 
						test.pass("Login Api ran successfully");
						results.createNode("Login API ran successfully; token was generated successfully");
						saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
					}
					catch(AssertionError er) {
						test.fail("Login Api failed");
						results.createNode("Login API failed to run; token was not generated");
						saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail("Login Api failed");
						results.createNode("Login API failed to run; token was not generated");
						saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
					}

					test = extent.createTest("AN-API-Anncmnt: Verify File Announcement API", "This test case will run file announcement api");	
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Run login API to generate token");
					steps.createNode("1. Add token in Authorization");
					steps.createNode("2. Add a unique RUN ID");
					steps.createNode("3. Run the API");

					Thread.sleep(2000);
					RequestSpecification request_announcement = RestAssured.given();

					request_announcement.header("Content-Type", "application/json");
					request_announcement.header("Authorization", "bearer " +token);

					HttpGet postRequest = new HttpGet(api_announcement);
					postRequest.addHeader("Content-Type", "application/json");
					postRequest.addHeader("Authorization", "Bearer "+token);

					JSONObject json1 = new JSONObject();
					JSONObject json2 = new JSONObject();
					JSONObject json3 = new JSONObject();
					JSONObject json4 = new JSONObject();
					JSONArray list = new JSONArray();

					json1.put("runId", lstApiAnnouncement.get(0));
					json1.put("dateTime", lstApiAnnouncement.get(1));
					json1.put("Piperid",  lstApiAnnouncement.get(2));
					json1.put("MPNCalculationType", lstApiAnnouncement.get(3));

					json2.put("fileName", lstApiAnnouncement.get(4));
					json2.put("checksum", lstApiAnnouncement.get(5));

					list.add(json2);
					json1.put("files", list);

					request_announcement.body(json1.toString());
					Response response1 = request_announcement.post(api_announcement);

					String data1 = response1.asString();
					System.out.println(data1);

					String statusCodeAnnouncement = jsonPathEvaluator.get("statusCode");

					try{
						Assert.assertEquals(statusCodeAnnouncement, "114"); 
						test.pass("File Announcement API ran successfully");
						results.createNode("File Announcement API ran successfully");
						saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
					}
					catch(AssertionError er) {
						test.fail("File Announcement API failed to run");
						results.createNode("File Announcement API failed to run");
						saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail("File Announcement API failed to run");
						results.createNode("File Announcement API failed to run");
						saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
					}


					///////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////
					SoftAssert softAssert = new SoftAssert();

					if (objModel.runStartAssay ) {
						try {
							test = extent.createTest("AN-StrtAssay-01: Verify "+objModel.pathogen+" Start Assay API", "This test case will run Salmonella Start Assay API");	
							preconditions = test.createNode(Scenario.class, PreConditions);
							steps = test.createNode(Scenario.class, Steps);
							results = test.createNode(Scenario.class, Results);

							preconditions.createNode("1. Run login API to generate token");
							steps.createNode("1. Add token generated on running Login API in Authorization");
							steps.createNode("2. Write 'Salmonella' in Pathogen Name");
							steps.createNode("3. Run the API");

							RequestSpecification request_startAssay = RestAssured.given();

							request_startAssay.header("Content-Type", "application/json");
							request_startAssay.header("Authorization", "bearer " +token);

							HttpGet postRequest3 = new HttpGet(api_StartAssay);
							postRequest3.addHeader("Content-Type", "application/json");
							postRequest3.addHeader("Authorization", "Bearer "+token);

							json4.put("DateTime", lstStartAssaySalmonella.get(0).DateTime);
							json4.put("InstrumentId", lstStartAssaySalmonella.get(0).InstrumentID);
							json4.put("UserId", lstStartAssaySalmonella.get(0).UserID);
							json4.put("CartridgeId", objModel.cartridgeID);
							json4.put("RunId", objFilter.LstSampleID.get(0));
							json4.put("PathogenName", objModel.pathogen);				

							request_startAssay.body(json4.toString());
							Response response3 = request_startAssay.post(api_StartAssay);

							String data4 = response3.asString();
							System.out.println(data4);
							Thread.sleep(60000);

							steps.createNode("4. Verify 12 lanes are ingested in Salmonella Report");
							driver.get(url_SalmonellaLog);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							WebElement filter_scroll = driver.findElement(By.id("instrumentId_show-filter"));
							((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);

							driver.findElement(By.id("cartridgeId_show-filter")).click();	
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);

							driver.findElement(By.id("cartridgeId_search-input")).clear();
							driver.findElement(By.id("cartridgeId_search-input")).sendKeys(objModel.cartridgeID);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);			

							if (driver.findElements(By.cssSelector("#cartridgeId_cust-cb-lst-txt_"+objModel.cartridgeID)).size() == 0) {
								Thread.sleep(45000);
								driver.navigate().refresh();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
								Thread.sleep(1000);
								WebElement filter_scroll1 = driver.findElement(By.id("instrumentId_show-filter"));
								((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll1);

								driver.findElement(By.id("cartridgeId_show-filter")).click();	
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);
								driver.findElement(By.id("cartridgeId_search-input")).clear();
								driver.findElement(By.id("cartridgeId_search-input")).sendKeys(objModel.cartridgeID);
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);	
							}

							ClickElement.clickByCss(driver, "#cartridgeId_cust-cb-lst-txt_"+objModel.cartridgeID);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							driver.findElement(By.id("cartridgeId_apply")).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);
							String records = driver.findElement(By.id("results-found-count")).getText();
							softAssert.assertEquals(records, "12"); 

							for(int i = 0; i<12;i++) {
								int lane = i+1;
								steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
								String getResultStatus = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slResultStatusCol+" label")).getText();
								softAssert.assertEquals(getResultStatus, "Pending", "Result Status not displayed as Pending in table");

								steps.createNode("Verify Date is displayed same as that written in API body for lane "+lane);
								String getDate = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slDateCol+" label")).getText();
								softAssert.assertEquals(getDate, dateMMDDYYYY1, "Date not displayed in table");

								steps.createNode("Verify Time is displayed same as that written in API body for lane "+lane);
								String getTime = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slTimeCol+" label")).getText();
								softAssert.assertEquals(getTime.isEmpty(), false, "Time displayed blank");				
												
								steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
								String getCartridgeID = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slCartridgeIDCol+" label")).getText();
								softAssert.assertEquals(getCartridgeID, objModel.cartridgeID, "Cartridge ID not displayed in table");

								steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
								String getInstrumentID = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slInstrumentIDCol+" label")).getText();
								softAssert.assertEquals(getInstrumentID, InstrumentID, "Instrument ID not displayed in table");

								steps.createNode("Verify Test Site ID is written  for lane "+lane);
								String getTestSiteID = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slTestSiteIDCol+" label")).getText();
								softAssert.assertEquals(getTestSiteID.isEmpty(), false, "Test Site ID is not displaying in table");

								steps.createNode("Verify Test Site Name is written for lane "+lane);
								String getTestSiteName = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slTestSiteNameCol+" label")).getText();
								softAssert.assertEquals(getTestSiteName.isEmpty(), false, "Test Site Name is not displaying in table");

								steps.createNode("Open Audit Trial popup for lane "+lane);
								WebElement scroll = driver.findElement(By.id("select-runId-0"));
								((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 

								Actions builder = new Actions(driver); 
								WebElement hover = driver.findElement(By.cssSelector("#audit-trial-"+i+" img"));
								builder.moveToElement(hover).build().perform();	
								Thread.sleep(1500);
								driver.findElement(By.id("audit-trial-"+i)).click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1500);

								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("audit-changed-date-0")));
								steps.createNode("Verify Date is same as that written in API body for lane "+lane);
								String getAuditDate = driver.findElement(By.id("audit-changed-date-0")).getText();
								softAssert.assertEquals(getAuditDate, dateMMDDYYYY1);

								steps.createNode("Verify Action as 'Created' for lane "+lane);
								String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
								softAssert.assertEquals(getAuditAction, "Created", "Action not displayed as 'Created 'in Audit Log");

								steps.createNode("Verify Time is same as in Log");
								String getAuditTime = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTimeCol+".text-dark")).getText(); 
								softAssert.assertEquals(getAuditTime, getTime, "Action not displayed as 'Created 'in Audit Log");
								softAssert.assertEquals(getAuditTime.isBlank(), false, "Time displayed blank in Audit Log");
						
								steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
								String getAuditResultStatus = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditResultStatusCol+".text-dark")).getText(); 
								softAssert.assertEquals(getAuditResultStatus, "Pending", "Result Status not displayed as Pending in Audit Log");

								steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
								String getAuditCartridgeId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditCartridgeIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID, "Cartridge ID not displayed in Audit Log");

								steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
								String getAuditInstrumentId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditInstrumentIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditInstrumentId, InstrumentID, "Instrument ID not displayed in Audit Log");

								steps.createNode("Verify Test Site ID for lane "+lane);
								String getAuditTestSiteId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTestSiteId.isEmpty(), false, "Test Site ID is not displaying in Audit Log");

								steps.createNode("Verify Test Site Name for lane "+lane);
								String getAuditTestSiteName = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteNameCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTestSiteName.isEmpty(), false, "Test Site Name is not displaying in Audit Log");

								driver.findElement(By.cssSelector(closeAudit)).click();
								softAssert.assertAll();	
							}	
							test.pass("Start Assay all scenarios passed successfully");
							results.createNode("Start Assay all scenarios passed successfully");
							test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", NormalIngestionReportPath));
							saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
						}
						catch(AssertionError er) {
							test.fail("Start Assay API failed");
							results.createNode("Start Assay API failed");
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
						}catch(Exception ex){
							test.fail("Start Assay API failed");
							results.createNode("Start Assay API failed");
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
						}
					}
					/////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////	


					/////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////

					for(int i=0; i<lstSalmonellaIngest.size(); i++)	{
						try{
							test = extent.createTest("AN-"+objModel.pathogen+"-01: Ingest "+objModel.pathogen+" run", "This test case will verify "+objModel.pathogen+" run");	
							preconditions = test.createNode(Scenario.class, PreConditions);
							steps = test.createNode(Scenario.class, Steps);
							results = test.createNode(Scenario.class, Results);

							preconditions.createNode("Run login API to generate token");
							preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
							steps.createNode(lstSalmonellaIngest.get(i).step);

							Thread.sleep(2000);
							RequestSpecification request_fileupload = RestAssured.given();
							request_fileupload.header("Content-Type", "application/json");
							request_fileupload.header("Authorization", "bearer " +token);

							HttpGet postRequest1 = new HttpGet(api_FileUpload);
							postRequest1.addHeader("Content-Type", "application/json");
							postRequest1.addHeader("Authorization", "Bearer "+token);

							json3.put("runId", lstSalmonellaIngest.get(i).runId);
							json3.put("checksum", lstSalmonellaIngest.get(i).checksum);
							json3.put("fileName", lstSalmonellaIngest.get(i).fileName);
							json3.put("fileType", lstSalmonellaIngest.get(i).fileType);
							json3.put("file", lstSalmonellaIngest.get(i).file);
							json3.put("fileJson", objModel.fileJson);				
							json3.put("Improc", lstSalmonellaIngest.get(i).improc);
							json3.put("RunMode", "1");
							json3.put("Pathogen", objModel.pathogen);

							request_fileupload.body(json3.toString());
							Response response2 = request_fileupload.post(api_FileUpload);
							String data3 = response2.asString();
							System.out.println(data3);

							JsonPath jsonPathEvaluator1 = response.jsonPath();
							jsonPathEvaluator1.get("statusCode");

							test.pass("File Upload API ran successfully");
							results.createNode(lstSalmonellaIngest.get(i).passScenario);
							saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
						}
						catch(AssertionError er) {
							test.fail("File Upload API failed to run");
							results.createNode(lstSalmonellaIngest.get(i).failScenario);
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
						}catch(Exception ex){
							test.fail("File Upload API failed to run");
							results.createNode(lstSalmonellaIngest.get(i).failScenario);
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
						}

						try{
							test = extent.createTest("AN-"+objModel.pathogen+"-02: Verify the ingestion and relevant records from report", "This test case will verify the ingestion and relevant records from report");	
							preconditions = test.createNode(Scenario.class, PreConditions);
							steps = test.createNode(Scenario.class, Steps);
							results = test.createNode(Scenario.class, Results);

							preconditions.createNode("1. Go to url " +url_login);
							preconditions.createNode("2. Login with valid credentials; user navigates to home page");
							preconditions.createNode("3. Hover to sidebar to expand the menu");
							preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
							preconditions.createNode("5. Click on Salmonella Log");

							Thread.sleep(150000);
							driver.get(url_SalmonellaLog);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
							Thread.sleep(1000);

							steps.createNode("1. Click on Sample ID to expand the filter");
							ClickElement.clickById(driver, "sampleId_show-filter");			
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							steps.createNode("2. Search for the Sample ID's against which the data is ingested");

							driver.findElement(By.id("sampleId_search-input")).clear();
							driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2500);	
							try {
								driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);	
							}
							catch (Exception ex) {
								ClickElement.clickByCss(driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0));
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);	
							}

							steps.createNode("3. Click on Apply filter button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							driver.findElement(By.id("sampleId_apply")).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);	
							test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", NormalIngestionReportPath));
							String records = driver.findElement(By.id("results-found-count")).getText();

							softAssert.assertEquals(records, "12"); 

							for(int j = 0; j<12; j++) {
								int lane = j+1;
								steps.createNode("Verify Result Status is displayed as 'Completed' in table for lane" +lane);
								String getResultStatus = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slResultStatusCol+" label")).getText();
								softAssert.assertEquals(getResultStatus, "Completed", "Result Status is not displayed as Completed in table");

								String getSampleId = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slSampleIDCol+" label")).getText();
								softAssert.assertEquals(getSampleId, objFilter.LstSampleID.get(0));

								steps.createNode("Verify Pathogen is displayed as 'Coccidia' in table for lane" +lane);
								String getPathogen = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slAssayCol+" label")).getText();
								softAssert.assertTrue(objModel.pathogen.equalsIgnoreCase(getPathogen));

								steps.createNode("Verify Cartridge ID is same as that written in API body for lane" +lane);
								String getCartridgeID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slCartridgeIDCol+" label")).getText();
								softAssert.assertEquals(getCartridgeID, objModel.cartridgeID);

								steps.createNode("Verify Instrument ID is same as that written in API body for lane" +lane);
								String getInstrumentID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slInstrumentIDCol+" label")).getText();
								softAssert.assertEquals(getInstrumentID, InstrumentID);
								
								steps.createNode("Verify Time is displayed same as that written in API body for lane "+lane);
								String getTime = driver.findElement(By.cssSelector("#row-"+i+" #col-"+slTimeCol+" label")).getText();
								softAssert.assertEquals(getTime.isBlank(), false, "Time displayed blank");
									
								steps.createNode("Verify Collection Site ID is same as that written in API body for lane" +lane);
								String getCSiteID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slSiteIDCol+" label")).getText();
								softAssert.assertEquals(getCSiteID, slCSiteID);

								steps.createNode("Verify Piper User is same as that written in API body for lane" +lane);
								String getPiperUser = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slPiperUserCol+" label")).getText();
								Assert.assertEquals(getPiperUser.isBlank(), false, "Piper User is blank");

								steps.createNode("Verify Run Type as "+RunType+" in API body for lane" +lane);
								String getRunType = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slRunTypeCol+" label")).getText();
								softAssert.assertEquals(getRunType, RunType, "Run Type is not displayed in table");

								steps.createNode("Verify Improc Version as "+Test_Variables.slImprocVersion+" in API body for lane" +lane);
								String getImprocID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slImprocIDCol+" label")).getText();
								softAssert.assertEquals(getImprocID, Test_Variables.slImprocVersion);

								steps.createNode("Verify Test Site ID is displayed in table for lane" +lane);
								String getTestSiteID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slTestSiteIDCol+" label")).getText();
								softAssert.assertEquals(getTestSiteID.isEmpty(), false, "Test Site ID is not dislayed in table");

								steps.createNode("Verify Test Site Name is displayed in table for lane" +lane);
								String getTestSiteName = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slTestSiteNameCol+" label")).getText();
								softAssert.assertEquals(getTestSiteName.isEmpty(), false, "Test Site Name is not dislayed in table");

								steps.createNode("Verify W1 PC Count is displayed in table for lane" +lane);
								String getW1PCCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slW1PCCountCol+" label")).getText();
								softAssert.assertEquals(getW1PCCount.isEmpty(), false);

								steps.createNode("Verify W1 Cell Count is displayed in table for lane" +lane);
								String getW1CellCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slW1CellCountCol+" label")).getText();
								softAssert.assertEquals(getW1CellCount.isEmpty(), false);

								steps.createNode("Verify W2 PC Count is displayed in table for lane" +lane);
								String getW2PCCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slW2CPCCountCol+" label")).getText();
								softAssert.assertEquals(getW2PCCount.isEmpty(), false);

								steps.createNode("Verify W2 Cell Count is displayed in table for lane" +lane);
								String getW2CellCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slW2CellCountCol+" label")).getText();
								softAssert.assertEquals(getW2CellCount.isEmpty(), false);

								String getSampleID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+slSampleIDCol+" label")).getText();

								steps.createNode("Open Audit trail popup for lane" +lane);
								driver.findElement(By.id("audit-trial-"+j)).click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1500);			
								test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", NormalIngestionReportPath));
								steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
								String getAuditSampleID = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditSampleIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditSampleID, getSampleID);

								if (objModel.runStartAssay) {
									steps.createNode("Verify Action as 'Modified' in Audit log for lane" +lane);
									String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
									softAssert.assertEquals(getAuditAction, "Modified");
									softAssert.assertAll();	
								}
								else {
									steps.createNode("Verify Action as 'Created' in Audit log for lane" +lane);
									String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
									softAssert.assertEquals(getAuditAction, "Created");
									softAssert.assertAll();	
								}

								steps.createNode("Verify Time is same as in Log");
								String getAuditTime = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTimeCol+".text-dark")).getText(); 
								softAssert.assertEquals(getAuditTime, getTime);
								
								steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
								String getAuditResultStatus = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditResultStatusCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditResultStatus, "Completed");

								steps.createNode("Verify Cartridge ID is displayed in Audit log for lane" +lane);
								String getAuditCartridgeId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditCartridgeIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

								steps.createNode("Verify Instrument ID is displayed in Audit log for lane" +lane);
								String getAuditInstrumentId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditInstrumentIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditInstrumentId, InstrumentID);

								steps.createNode("Verify Piper User is displayed in Audit log for lane" +lane);
								String getAuditPiperUser = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditPiperUserCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditPiperUser.isBlank(), false, "Piper User is blank");

								steps.createNode("Verify Run Type is displayed in Audit log for lane" +lane);
								String getAuditRunType = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditRunTypeCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditRunType, RunType);

								steps.createNode("Verify Improc Version is displayed in Audit log for lane" +lane);
								String getAuditImprocVersion = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditImprocIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditImprocVersion, Test_Variables.slImprocVersion);

								steps.createNode("Verify W1 PC Count Count is displayed in Audit log for lane" +lane);
								String getAuditW1PCCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditW1PCCountCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditW1PCCount.isEmpty() == false);

								steps.createNode("Verify W1 Cell Count is displayed in Audit log for lane" +lane);
								String getAuditW1CellCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditW1CellCountCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditW1CellCount.isEmpty() == false);

								steps.createNode("Verify W2 PC Count is displayed in Audit log for lane" +lane);
								String getAuditW2PCCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditW2CPCCountCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditW2PCCount.isEmpty() == false);

								steps.createNode("Verify W2 Cell Count is displayed in Audit log for lane" +lane);
								String getAuditW2CellCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditW2CellCountCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditW2CellCount.isEmpty() == false);

								steps.createNode("Verify Test Site ID is displayed in Audit log for lane" +lane);
								String getAuditTestSiteId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteIDCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false);

								steps.createNode("Verify Test Site Name is displayed in Audit log for lane" +lane);
								String getAuditTestSiteName = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteNameCol+".text-dark")).getText();
								softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false);

								driver.findElement(By.cssSelector(closeAudit)).click();   
								softAssert.assertAll();
							}

							test.pass("Ingested Successfully");
							results.createNode("Data ingestion verified successfully");
							saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
						}catch(AssertionError er) {
							test.fail("Ingestion failed");
							results.createNode("Data ingestion verification failed");
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
						}catch(Exception ex){
							test.fail("Ingestion failed");
							results.createNode("Data ingestion verification failed");
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
						}

						////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

						try {	
							test = extent.createTest("AN-"+objModel.pathogen+"-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
							preconditions = test.createNode(Scenario.class, PreConditions);
							steps = test.createNode(Scenario.class, Steps);
							results = test.createNode(Scenario.class, Results);

							preconditions.createNode("1. Go to url " +url_login);
							preconditions.createNode("2. Run Start Assaay and file Upload API");
							steps.createNode("1. Upload Sample MetaData Template against the ingested run");
							steps.createNode("2. Verify the data in Report");

							FileInputStream fsIP= new FileInputStream(new File("./Excel/"+fileName));
							@SuppressWarnings("resource")
							XSSFWorkbook wb = new XSSFWorkbook(fsIP);
							XSSFSheet worksheet = wb.getSheetAt(0);
							Cell cell = null;

							if (driver.findElement(By.id("results-found-count")).getText().equals("12")) {

								for (int z=0; z<12; z++) {

									String getSampleID = driver.findElement(By.cssSelector("#row-"+z+" #col-"+slSampleIDCol)).getText();
									cell=worksheet.getRow(z+1).createCell(4); 
									cell.setCellValue(getSampleID+"Updt");  

									String getResultID = driver.findElement(By.cssSelector("#row-"+z+" #col-"+slResultIDCol)).getText();
									cell=worksheet.getRow(z+1).createCell(0); 
									cell.setCellValue(getResultID);  

									cell=worksheet.getRow(z+1).createCell(19); 
									cell.setCellValue(objModel.cartridgeID); 

									cell=worksheet.getRow(z+1).createCell(3); 
									cell.setCellValue(SampleMatrix); 

									cell=worksheet.getRow(z+1).createCell(9); 
									cell.setCellValue(FlockID); 

									cell=worksheet.getRow(z+1).createCell(8); 
									cell.setCellValue(RequestedAssay); 

									cell=worksheet.getRow(z+1).createCell(2); 
									cell.setCellValue(KitLot); 

									cell=worksheet.getRow(z+1).createCell(23); 
									cell.setCellValue(CollectionDate); 
									
									cell=worksheet.getRow(z+1).createCell(6); 
									cell.setCellValue(CustomerSampleID); 

									cell=worksheet.getRow(z+1).createCell(1); 
									cell.setCellValue(SiteID); 

									String getLane = driver.findElement(By.cssSelector("#row-"+z+" #col-"+slLaneCol)).getText();
									cell=worksheet.getRow(z+1).createCell(16); 
									cell.setCellValue(getLane);  

									fsIP.close();
								}

								FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+fileName));
								wb.write(output_file);
								output_file.close();  

								driver.get(url_dataUpload);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
								Thread.sleep(1000);
								driver.findElement(By.id("OrgnTypeID")).click();
								driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
								driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								driver.findElement(By.id("DataFormatId")).click();
								driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
								driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								driver.findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+fileName);
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
								Thread.sleep(4000);
								driver.findElement(By.cssSelector(".fa-save")).click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
								Thread.sleep(2000);

								driver.get(url_SalmonellaLog);
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
								Thread.sleep(1000);

								driver.findElement(By.id("sampleId_show-filter")).click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);
								//		driver.findElement(By.id("sampleId_view-all")).click();
								//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								//		Thread.sleep(2000);

								driver.findElement(By.id("sampleId_search-input")).clear();
								driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0)+"Updt");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);
								try {
									ClickElement.clickByCss(driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0)+"Updt");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(2000);
								}
								catch (Exception ex) {
									driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0)+"Updt")).click();
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(2000);
								}


								driver.findElement(By.id("sampleId_apply")).click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(3000);	
								test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", NormalIngestionReportPath));

								for (int k=0; k<12;k++) {
								//	int lane = k+1;
								//	String laneGetText = driver.findElement(By.cssSelector("tr:nth-child("+lane+") td:nth-child(3) label")).getText();
								//	int laneNumber = Integer.parseInt(laneGetText);

									String getSampleId = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slSampleIDCol+" label")).getText();
									softAssert.assertEquals(getSampleId, objFilter.LstSampleID.get(0)+"Updt");
									
									String getRequestedAssay = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slRequestedAssayCol+" label")).getText();
									softAssert.assertEquals(getRequestedAssay, RequestedAssay);

									String getFlockID = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slFlockIDCol+" label")).getText();
									softAssert.assertEquals(getFlockID, FlockID);

									String getCSampleID = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slCSampleIDCol+" label")).getText();
									softAssert.assertEquals(getCSampleID, CustomerSampleID);

									String getSampleMatrix = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slSampleMatrixCol+" label")).getText();
									softAssert.assertEquals(getSampleMatrix, SampleMatrix);

									String getKitLot = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slKitLotCol+" label")).getText();
									softAssert.assertEquals(getKitLot, KitLot);
									
									String getCollectionDate = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slCollectionDateCol+" label")).getText();
									softAssert.assertEquals(getCollectionDate, CollectionDate);

									String getSampleID = driver.findElement(By.cssSelector("#row-"+k+" #col-"+slSampleIDCol+" label")).getText();

									driver.findElement(By.id("audit-trial-"+k)).click();
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1500);		

									test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", NormalIngestionReportPath));
									
									String getAuditSampleID = driver.findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
									softAssert.assertEquals(getAuditSampleID, getSampleID);

									String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
									softAssert.assertEquals(getAuditAction, "Modified");

									String getAuditResultStatus = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditResultStatusCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditResultStatus, "Completed");

									String getAuditCartridgeId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditCartridgeIDCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

									String getAuditInstrumentId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditInstrumentIDCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditInstrumentId, InstrumentID);

									String getAuditImprocVersion = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditImprocIDCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditImprocVersion, Test_Variables.slImprocVersion);
									
									String getAuditCollectionDate = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditCollectionDateCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditCollectionDate, CollectionDate);

									String getAuditTestSiteId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteIDCol+".text-dark")).getText();
									softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false);

									String getAuditTestSiteName = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditTestSiteNameCol+".text-dark")).getText();
									softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false);

									driver.findElement(By.cssSelector(closeAudit)).click(); 
									softAssert.assertAll();
								}
								test.pass("Data Verified successfully on uploading Sample Metadata Template");
								results.createNode("Data Verified successfully on uploading Sample Metadata Template");
								saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);

							}
							else {
								results.createNode("12 records not displaying in table hence file upload method not executed");
								System.out.println("12 records not displaying in table hence file upload method not executed");
							}
						}
						catch(AssertionError er) {
							test.fail("File Upload Failed");
							results.createNode("File Upload Failed");
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
						}catch(Exception ex){
							test.fail("File Upload Failed");
							results.createNode("File Upload Failed");
							saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
						}
						Thread.sleep(2000);	
					}
				}
			}
			catch(Exception ex){
			}
		}		
	}


	@SuppressWarnings({ "unchecked", "unused" })
	@Test (description="Test Case: Run Ingestion for Coccidia", enabled= true, priority= 2) 
	public void NormalIngestionCoccidia() throws InterruptedException, IOException	{
		lstNormalIngestion = NormalIngestionModel.FillDataCocci();
		for (NormalIngestionModel objModel : lstNormalIngestion) { 
			test = extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Enter valid piperid ("+piperId+")");
			steps.createNode("2. Enter valid password (********)");
			steps.createNode("3. Run the API");

			SoftAssert softAssert = new SoftAssert();

			for (ReportFilters objFilter : objModel.lstFilters) {

				DateFormat dateFormat = new SimpleDateFormat("mm.ss");
				Date date1 = new Date();
				dateFormat.format(date1);

				RequestSpecification request = RestAssured.given();
				request.header("Content-Type", "application/json");
				JSONObject json = new JSONObject();
				json.put("piperid", piperId);
				json.put("password", piperPassword);
				json.put("DISAPIVersion", "14.13");
				request.body(json.toString());
				Response response = request.post(api_login);
				int code = response.getStatusCode();
				Assert.assertEquals(code, 200);

				String data = response.asString();
				System.out.println(data);

				JsonPath jsonPathEvaluator = response.jsonPath();
				String token = jsonPathEvaluator.get("token");		
				String statusCode = jsonPathEvaluator.get("statusCode");
				//System.out.println(token);

				try{
					Assert.assertEquals(statusCode, "114"); 
					test.pass("Login Api ran successfully");
					results.createNode("Login API ran successfully; token was generated successfully");
					saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
				}
				catch(AssertionError er) {
					test.fail("Login Api failed");
					results.createNode("Login API failed to run; token was not generated");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					test.fail("Login Api failed");
					results.createNode("Login API failed to run; token was not generated");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
				}

				test = extent.createTest("AN-API-Anncmnt: Verify Coccidia File Announcement API", "This test case will run Coccidia file announcement api");	
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Run login API to generate token");
				steps.createNode("1. Add token in Authorization");
				steps.createNode("2. Add a unique RUN ID");
				steps.createNode("3. Run the API");

				Thread.sleep(2000);
				RequestSpecification request_announcement = RestAssured.given();

				request_announcement.header("Content-Type", "application/json");
				request_announcement.header("Authorization", "bearer " +token);

				HttpGet postRequest = new HttpGet(api_announcement);
				postRequest.addHeader("Content-Type", "application/json");
				postRequest.addHeader("Authorization", "Bearer "+token);

				JSONObject json1 = new JSONObject();
				JSONObject json2 = new JSONObject();
				JSONObject json3 = new JSONObject();
				JSONObject json4 = new JSONObject();
				JSONArray list = new JSONArray();

				json1.put("runId", lstApiAnnouncement.get(0));
				json1.put("dateTime", lstApiAnnouncement.get(1));
				json1.put("Piperid",  lstApiAnnouncement.get(2));
				json1.put("MPNCalculationType", lstApiAnnouncement.get(3));
				json2.put("fileName", lstApiAnnouncement.get(4));
				json2.put("checksum", lstApiAnnouncement.get(5));

				list.add(json2);
				json1.put("files", list);

				request_announcement.body(json1.toString());
				Response response1 = request_announcement.post(api_announcement);

				String data1 = response1.asString();
				System.out.println(data1);

				String statusCodeAnnouncement = jsonPathEvaluator.get("statusCode");

				try{
					Assert.assertEquals(statusCodeAnnouncement, "114"); 
					test.pass("File Announcement API ran successfully");
					results.createNode("File Announcement API ran successfully");
					saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
				}
				catch(AssertionError er) {
					test.fail("File Announcement API failed to run");
					results.createNode("File Announcement API failed to run");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					test.fail("File Announcement API failed to run");
					results.createNode("File Announcement API failed to run");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
				}


				///////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////
				if (objModel.runStartAssay) {
					try {
						test = extent.createTest("AN-StrtAssay-01: Verify Coccidia Start Assay API", "This test case will run Coccidia Start Assay API");	
						preconditions = test.createNode(Scenario.class, PreConditions);
						steps = test.createNode(Scenario.class, Steps);
						results = test.createNode(Scenario.class, Results);

						preconditions.createNode("1. Run login API to generate token");
						steps.createNode("1. Add token generated on running Login API in Authorization");
						steps.createNode("2. Write 'Coccidia' in Pathogen Name");
						steps.createNode("3. Run the API");


						RequestSpecification request_startAssay = RestAssured.given();

						request_startAssay.header("Content-Type", "application/json");
						request_startAssay.header("Authorization", "bearer " +token);

						HttpGet postRequest3 = new HttpGet(api_StartAssay);
						postRequest3.addHeader("Content-Type", "application/json");
						postRequest3.addHeader("Authorization", "Bearer "+token);

						json4.put("DateTime", lstStartAssayCoccidia.get(0).DateTime);
						json4.put("InstrumentId", lstStartAssayCoccidia.get(0).InstrumentID);
						json4.put("UserId", lstStartAssayCoccidia.get(0).UserID);
						json4.put("CartridgeId", objModel.cartridgeID);
						json4.put("RunId", objFilter.LstSampleID.get(0));
						json4.put("PathogenName", objModel.pathogen);				

						request_startAssay.body(json4.toString());
						Response response3 = request_startAssay.post(api_StartAssay);

						String data4 = response3.asString();
						System.out.println(data4);
						Thread.sleep(60000);

						steps.createNode("4. Verify 12 lanes are ingested in Coccidia Report");
						driver.get(url_CoccidiaLog);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
						Thread.sleep(1000);
						WebElement filter_scroll = driver.findElement(By.id("instrumentId_show-filter"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);

						driver.findElement(By.id("cartridgeId_show-filter")).click();	
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						driver.findElement(By.id("cartridgeId_view-all")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						driver.findElement(By.id("cartridgeId_search-input")).clear();
						driver.findElement(By.id("cartridgeId_search-input")).sendKeys(objModel.cartridgeID);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);				
						driver.findElement(By.cssSelector("#cartridgeId_cust-cb-lst-txt_"+objModel.cartridgeID)).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(800);

						driver.findElement(By.id("cartridgeId_apply")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(4000);
						String records = driver.findElement(By.id("results-found-count")).getText();
						softAssert.assertEquals(records, "12"); 

						for(int i = 0; i<12;i++) {
							int lane = i+1;
							steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
							String getResultStatus = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clResultStatusCol+" label")).getText();
							softAssert.assertEquals(getResultStatus, "Pending", "Result Status not displayed as Pending in table");

							steps.createNode("Verify Date is displayed same as that written in API body for lane "+lane);
							String getDate = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clDateCol+" label")).getText();
							softAssert.assertEquals(getDate, dateMMDDYYYY1, "Date not displayed in table");

							steps.createNode("Verify Pathogen Name as 'Coccidia' for lane "+lane);
							String getPathogen = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clAssayCol+" label")).getText();
							softAssert.assertEquals(getPathogen, objModel.pathogen, "Pathogen Name not displayed as Coccidia in table");

							steps.createNode("Verify Time for lane "+lane);
							String getTime = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clTimeCol+" label")).getText();
							softAssert.assertEquals(getTime.isBlank(), false, "Time displayed blank");
							
							steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
							String getCartridgeID = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clCatridgeIDCol+" label")).getText();
							softAssert.assertEquals(getCartridgeID, objModel.cartridgeID, "Cartridge ID not displayed in table");

							steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
							String getInstrumentID = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clInstrumentIDCol+" label")).getText();
							softAssert.assertEquals(getInstrumentID, InstrumentID, "Instrument ID not displayed in table");

							steps.createNode("Verify Piper User is same as that written in API body for lane "+lane);
							String getPiperUser = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clPiperUserCol+" label")).getText();
							softAssert.assertEquals(getPiperUser.isBlank(), false, "Piper User not displayed in table");

							steps.createNode("Verify Test Site ID is written  for lane "+lane);
							String getTestSiteID = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clTestSiteIDCol+" label")).getText();
							softAssert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not displaying in table");

							steps.createNode("Verify Test Site Name is written for lane "+lane);
							String getTestSiteName = driver.findElement(By.cssSelector("#row-"+i+" #col-"+clTestSiteNameCol+" label")).getText();
							softAssert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not displaying in table");

							steps.createNode("Open Audit Trial popup for lane "+lane);			
							WebElement scroll = driver.findElement(By.id("select-runId-0"));
							((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 

							Actions builder = new Actions(driver); 
							WebElement hover = driver.findElement(By.cssSelector("#audit-trial-"+i+" img"));
							builder.moveToElement(hover).build().perform();	
							Thread.sleep(1500);
							driver.findElement(By.id("audit-trial-"+i)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1500);

							steps.createNode("Verify Date is same as that written in API body for lane "+lane);
							String getAuditDate = driver.findElement(By.id("audit-changed-date-0")).getText();
							softAssert.assertEquals(getAuditDate, dateMMDDYYYY1);

							steps.createNode("Verify Action as 'Created' for lane "+lane);
							String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Created", "Action not displayed as 'Created 'in Audit Log");

							steps.createNode("Verify Changed by is same as that written in API body for lane "+lane);
							//String getAuditUser = driver.findElement(By.id("audit-changed-by-0")).getText();
							//softAssert.assertEquals(getAuditUser, PiperUser, "Changed By not displayed in Audit Log");

							steps.createNode("Verify Time is same as in Log");
							String getAuditTime = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTimeCol+".text-dark")).getText(); 
							softAssert.assertEquals(getAuditTime, getTime);
							
							steps.createNode("Verify Result Status as 'Pending' for lane "+lane);
							String getAuditResultStatus = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditResultStatusCol+".text-dark")).getText(); 
							softAssert.assertEquals(getAuditResultStatus, "Pending", "Result Status not displayed as Pending in Audit Log");

							steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
							String getAuditCartridgeId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditCartridgeIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID, "Cartridge ID not displayed in Audit Log");

							steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
							String getAuditInstrumentId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditInstrumentIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditInstrumentId, InstrumentID, "Instrument ID not displayed in Audit Log");

							steps.createNode("Verify Piper User is same as that written in API body for lane "+lane);
							String getAuditPiperUser = driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
							softAssert.assertEquals(getAuditPiperUser.isBlank(), false, "Piper User not displayed in Audit Log");

							steps.createNode("Verify Test Site ID for lane "+lane);
							String getAuditTestSiteId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTestSiteIDCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false, "Test Site ID is not displaying in Audit Log");

							steps.createNode("Verify Test Site Name for lane "+lane);
							String getAuditTestSiteName = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTestSiteNameCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false, "Test Site Name is not displaying in Audit Log");

							driver.findElement(By.cssSelector(closeAudit)).click();
					
						}	
						softAssert.assertAll();
						test.pass("Start Assay all scenarios passed successfully");
						results.createNode("Start Assay all scenarios passed successfully");
						test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));
						saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
					}
					catch(AssertionError er) {
						test.fail("Start Assay API failed");
						results.createNode("Start Assay API failed");
						saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail("Start Assay API failed");
						results.createNode("Start Assay API failed");
						saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
					}
				}

				/////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////	


				/////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////

				try{
					test = extent.createTest("AN-Coccidia-01: Ingest Coccidia run", "This test case will run and verify  ingestion");	
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("Run login API to generate token");
					preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
					steps.createNode(lstCoccidiaIngest.get(0).step);

					Thread.sleep(2000);
					RequestSpecification request_fileupload = RestAssured.given();
					request_fileupload.header("Content-Type", "application/json");
					request_fileupload.header("Authorization", "bearer " +token);

					HttpGet postRequest1 = new HttpGet(api_FileUpload);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);

					json3.put("runId", lstCoccidiaIngest.get(0).runId);
					json3.put("checksum", lstCoccidiaIngest.get(0).checksum);
					json3.put("fileName", lstCoccidiaIngest.get(0).fileName);
					json3.put("fileType", lstCoccidiaIngest.get(0).fileType);
					json3.put("file", lstCoccidiaIngest.get(0).file);
					json3.put("fileJson", objModel.fileJson);				
					json3.put("Improc", lstCoccidiaIngest.get(0).improc);
					json3.put("RunMode", "1");
					json3.put("Pathogen", objModel.pathogen);

					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(api_FileUpload);
					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");

					test.pass("File Upload API ran successfully");
					results.createNode("File Upload API ran successfully");
					saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
				}
				catch(AssertionError er) {
					test.fail("File Upload API failed to run");
					results.createNode("File Upload API failed to run");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					test.fail("File Upload API failed to run");
					results.createNode("File Upload API failed to run");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
				}

				try{
					test = extent.createTest("AN-Coccidia-02: Verify the ingestion and relevant records from report", "This test case will verify the ingestion and relevant records from report");	
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Go to url " +url_login);
					preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					preconditions.createNode("3. Hover to sidebar to expand the menu");
					preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
					preconditions.createNode("5. Click on Coccidia Log");

					Thread.sleep(150000);
					driver.get(url_CoccidiaLog);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
					Thread.sleep(1000);

					steps.createNode("1. Click on Sample ID to expand the filter");
					ClickElement.clickById(driver, "sampleId_show-filter");			
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					driver.findElement(By.id("sampleId_view-all")).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					steps.createNode("2. Search for the Sample ID's against which the data is ingested");


					driver.findElement(By.id("sampleId_search-input")).clear();
					driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);	
					try {
						driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
					}
					catch(Exception ex) {
						Thread.sleep(1000);
						driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
					}
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(800);


					steps.createNode("3. Click on Apply filter button");
					driver.findElement(By.id("sampleId_apply")).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(4000);
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));
					String records = driver.findElement(By.id("results-found-count")).getText();

					softAssert.assertEquals(records, "12"); 

					for(int j = 0; j<12; j++) {
						int lane = j+1;
						steps.createNode("Verify Result Status is displayed as 'Completed' in table for lane" +lane);
						String getResultStatus = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clResultStatusCol+" label")).getText();
						Assert.assertEquals(getResultStatus, "Completed", "Result Status is not displayed as Completed in table");

						String laneGetText = driver.findElement(By.cssSelector("tr:nth-child("+lane+") td:nth-child(3) label")).getText();
						int laneNumber = Integer.parseInt(laneGetText);

						String getSampleId = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clSampleIDCol+" label")).getText();
						softAssert.assertEquals(getSampleId, objFilter.LstSampleID.get(0));

						steps.createNode("Verify Pathogen is displayed as 'Coccidia' in table for lane" +lane);
						String getPathogen = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clAssayCol+" label")).getText();
						softAssert.assertEquals(getPathogen, objModel.pathogen);

						steps.createNode("Verify Cartridge ID is same as that written in API body for lane" +lane);
						String getCartridgeID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clCatridgeIDCol+" label")).getText();
						softAssert.assertEquals(getCartridgeID, objModel.cartridgeID);

						steps.createNode("Verify Instrument ID is same as that written in API body for lane" +lane);
						String getInstrumentID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clInstrumentIDCol+" label")).getText();
						softAssert.assertEquals(getInstrumentID, InstrumentID);
						
						steps.createNode("Verify Time for lane "+lane);
						String getTime = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clTimeCol+" label")).getText();
						softAssert.assertEquals(getTime.isBlank(), false, "Time displayed blank");
						
						steps.createNode("Verify Piper User is same as that written in API body for lane" +lane);
						String getPiperUser = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clPiperUserCol+" label")).getText();
						Assert.assertEquals(getPiperUser.isBlank(), false, "Piper User is Empty");

						steps.createNode("Verify Run Type as "+RunType+" in API body for lane" +lane);
						String getRunType = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clRunTypeCol+" label")).getText();
						softAssert.assertEquals(getRunType, RunType, "Run Type is not displayed in table");

						steps.createNode("Verify Improc Version as "+ImprocVersion+" in API body for lane" +lane);
						String getImprocID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clImprocIDCol+" label")).getText();
						softAssert.assertEquals(getImprocID, ImprocVersion);

						steps.createNode("Verify Test Site ID is displayed in table for lane" +lane);
						String getTestSiteID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clTestSiteIDCol+" label")).getText();
						softAssert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");

						steps.createNode("Verify Test Site Name is displayed in table for lane" +lane);
						String getTestSiteName = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clTestSiteNameCol+" label")).getText();
						softAssert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");

						steps.createNode("Verify Total Count is displayed in table for lane" +lane);
						String getTotalCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clTotalCountCol+" label")).getText();
						softAssert.assertTrue(getTotalCount.isEmpty() == false);

						steps.createNode("Verify Small Count is displayed in table for lane" +lane);
						String getSmallCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clSmallCountCol+" label")).getText();
						softAssert.assertTrue(getSmallCount.isEmpty() == false);

						steps.createNode("Verify Medium Count is displayed in table for lane" +lane);
						String getMediumCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clMediumCountCol+" label")).getText();
						softAssert.assertTrue(getMediumCount.isEmpty() == false);

						steps.createNode("Verify Large Count is displayed in table for lane" +lane);
						String getLargeCount = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clLargeCountCol+" label")).getText();
						softAssert.assertTrue(getLargeCount.isEmpty() == false);

						String getSampleID = driver.findElement(By.cssSelector("#row-"+j+" #col-"+clSampleIDCol+" label")).getText();

						steps.createNode("Open Audit trail popup for lane" +lane);
						driver.findElement(By.id("audit-trial-"+j)).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1500);			

						test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));
						
						steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
						String getAuditSampleID = driver.findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
						softAssert.assertEquals(getAuditSampleID, getSampleID);

						if (objModel.runStartAssay) {
							steps.createNode("Verify Action as 'Modified' in Audit log for lane" +lane);
							String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");
							softAssert.assertAll();
						}
						else {
							steps.createNode("Verify Action as 'Created' in Audit log for lane" +lane);
							String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Created");
							softAssert.assertAll();
						}

						steps.createNode("Verify Changed by in Audit log for lane" +lane);
					//	String getAuditUser = driver.findElement(By.id("audit-changed-by-0")).getText();
					//	softAssert.assertEquals(getAuditUser, PiperUser);

						steps.createNode("Verify Result Status as 'Completed' for lane "+lane);
						String getAuditResultStatus = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditResultStatusCol+".text-dark")).getText(); 
						softAssert.assertEquals(getAuditResultStatus, "Completed", "Result Status not displayed as Completed in Audit Log");

						steps.createNode("Verify Cartridge ID is same as that written in API body for lane "+lane);
						String getAuditCartridgeId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditCartridgeIDCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID, "Cartridge ID not displayed in Audit Log");

						steps.createNode("Verify Time is same as in Log");
						String getAuditTime = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTimeCol+".text-dark")).getText(); 
						softAssert.assertEquals(getAuditTime, getTime);
						
						steps.createNode("Verify Instrument ID is same as that written in API body for lane "+lane);
						String getAuditInstrumentId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditInstrumentIDCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditInstrumentId, InstrumentID, "Instrument ID not displayed in Audit Log");

						steps.createNode("Verify Piper User is displayed in Audit log for lane" +lane);
						String getAuditPiperUser = driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
						Assert.assertEquals(getAuditPiperUser.isEmpty(), false, "Piper User is Empty");

						steps.createNode("Verify Run Type is displayed in Audit log for lane" +lane);
						String getAuditRunType = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditRunTypeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditRunType, RunType);

						steps.createNode("Verify Improc Version is displayed in Audit log for lane" +lane);
						String getAuditImprocVersion = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditImprocIDCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditImprocVersion, ImprocVersion);

						steps.createNode("Verify Total Count is displayed in Audit log for lane" +lane);
						String getAuditTotalCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTotalCountCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditTotalCount.isEmpty() == false);

						steps.createNode("Verify Small Count is displayed in Audit log for lane" +lane);
						String getAuditSmallCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditSmallCountCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditSmallCount.isEmpty() == false);

						steps.createNode("Verify Medium Count is displayed in Audit log for lane" +lane);
						String getAuditMediumCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditMediumCountCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditMediumCount.isEmpty() == false);

						steps.createNode("Verify Large Count is displayed in Audit log for lane" +lane);
						String getAuditLargeCount = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditLargeCountCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditLargeCount.isEmpty() == false);

						steps.createNode("Verify Test Site ID for lane "+lane);
						String getAuditTestSiteId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTestSiteIDCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false, "Test Site ID is not displaying in Audit Log");

						steps.createNode("Verify Test Site Name for lane "+lane);
						String getAuditTestSiteName = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTestSiteNameCol+".text-dark")).getText();
						softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false, "Test Site Name is not displaying in Audit Log");

						driver.findElement(By.cssSelector(closeAudit)).click();   
						softAssert.assertAll();
					}

					test.pass("Ingested data verified successfully in log");
					results.createNode("Ingested data verified successfully in log");
					saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
				}catch(AssertionError er) {
					test.fail("Data ingestion verification failed");
					results.createNode("Data ingestion verification failed");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					test.fail("Data ingestion verification failed");
					results.createNode("Data ingestion verification failed");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);
				}

				////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

				try {	
					test = extent.createTest("AN-Coccidia-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Go to url " +url_login);
					preconditions.createNode("2. Run Start Assaay and file Upload API");
					steps.createNode("1. Upload Sample MetaData Template against the ingested run");
					steps.createNode("2. Verify the data in Report");

					FileInputStream fsIP= new FileInputStream(new File("./Excel/"+fileName));
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fsIP);
					XSSFSheet worksheet = wb.getSheetAt(0);
					Cell cell = null;

					if (driver.findElement(By.id("results-found-count")).getText().equals("12")) {

						for (int z=0; z<12; z++) {

							String getSampleID = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clSampleIDCol)).getText();
							cell=worksheet.getRow(z+1).createCell(4); 
							cell.setCellValue(getSampleID+"Updt");  

							String getResultID = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clResultIDCol)).getText();
							cell=worksheet.getRow(z+1).createCell(0); 
							cell.setCellValue(getResultID);  

							cell=worksheet.getRow(z+1).createCell(19); 
							cell.setCellValue(CartridgeID); 

							cell=worksheet.getRow(z+1).createCell(3); 
							cell.setCellValue(SampleMatrix); 

							cell=worksheet.getRow(z+1).createCell(9); 
							cell.setCellValue(FlockID); 

							cell=worksheet.getRow(z+1).createCell(8); 
							cell.setCellValue(RequestedAssay); 

							cell=worksheet.getRow(z+1).createCell(2); 
							cell.setCellValue(KitLot); 

							cell=worksheet.getRow(z+1).createCell(6); 
							cell.setCellValue(CustomerSampleID); 
							
							cell=worksheet.getRow(z+1).createCell(1); 
							cell.setCellValue(SiteID); 

							String getLane = driver.findElement(By.cssSelector("#row-"+z+" #col-0")).getText();
							cell=worksheet.getRow(z+1).createCell(16); 
							cell.setCellValue(getLane);  

							fsIP.close();
						}

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+fileName));
						wb.write(output_file);
						output_file.close();  

						driver.get(url_dataUpload);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
						Thread.sleep(1000);
						driver.findElement(By.id("OrgnTypeID")).click();
						driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
						driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						driver.findElement(By.id("DataFormatId")).click();
						driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
						driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						driver.findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+fileName);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(4000);
						driver.findElement(By.cssSelector(".fa-save")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(2000);

						driver.get(url_CoccidiaLog);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
						Thread.sleep(1000);

						driver.findElement(By.id("sampleId_show-filter")).click();	
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(800);
						driver.findElement(By.id("sampleId_view-all")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						driver.findElement(By.id("sampleId_search-input")).clear();
						driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0)+"Updt");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);				

						try {
							driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0)+"Updt")).click();
						}
						catch (Exception ex) {
							ClickElement.clickByCss(driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0)+"Updt");
						}

						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);	
						driver.findElement(By.id("sampleId_apply")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(3000);
						test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));		

						for (int k=0; k<12;k++) {
							int lane = k+1;
							String laneGetText = driver.findElement(By.cssSelector("tr:nth-child("+lane+") td:nth-child(3) label")).getText();
							int laneNumber = Integer.parseInt(laneGetText);

							String getSampleId = driver.findElement(By.cssSelector("#row-"+k+" #col-"+clSampleIDCol+" label")).getText();
							softAssert.assertEquals(getSampleId, objFilter.LstSampleID.get(0)+"Updt");
							
							String getRequestedAssay = driver.findElement(By.cssSelector("#row-"+k+" #col-"+clRequestedAssayCol+" label")).getText();
							softAssert.assertEquals(getRequestedAssay, RequestedAssay);

							String getFlockID = driver.findElement(By.cssSelector("#row-"+k+" #col-"+clFlockIDCol+" label")).getText();
							softAssert.assertEquals(getFlockID, FlockID);

							String getCSampleID = driver.findElement(By.cssSelector("#row-"+k+" #col-"+clCSampleIDCol+" label")).getText();
							softAssert.assertEquals(getCSampleID, CustomerSampleID);

							String getSampleMatrix = driver.findElement(By.cssSelector("#row-"+k+" #col-"+clSampleMatrixCol+" label")).getText();
							softAssert.assertEquals(getSampleMatrix, SampleMatrix);

							String getKitLot = driver.findElement(By.cssSelector("#row-"+k+" #col-"+clKitLotCol+" label")).getText();
							softAssert.assertEquals(getKitLot, KitLot);

							String getSampleID = driver.findElement(By.cssSelector("#row-"+k+" #col-"+clSampleIDCol+" label")).getText();

							driver.findElement(By.id("audit-trial-"+k)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);		

							test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));
							
							String getAuditSampleID = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditSampleIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditSampleID, getSampleID);

							String getAuditAction = driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");

							String getAuditResultStatus = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditResultStatusCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditResultStatus, "Completed");

							String getAuditCartridgeId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditCartridgeIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

							String getAuditInstrumentId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditInstrumentIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditInstrumentId, InstrumentID);

							String getAuditPiperUser = driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
							Assert.assertEquals(getAuditPiperUser.isBlank(), false, "Piper User is empty");

							String getAuditImprocVersion = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditImprocIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditImprocVersion, ImprocVersion);

							String getAuditTestSiteId = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTestSiteIDCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false);

							String getAuditTestSiteName = driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+clAuditTestSiteNameCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false);

							driver.findElement(By.cssSelector(closeAudit)).click(); 
							softAssert.assertAll();
						}
						test.pass("Data Verified successfully on uploading Sample Metadata Template");
						results.createNode("Data Verified successfully on uploading Sample Metadata Template");
						saveResultNew(ITestResult.SUCCESS, NormalIngestionReportPath, null);
					}
					else {
						results.createNode("12 records not displaying in table hence file upload method not executed");
						System.out.println("12 records not displaying in table hence file upload method not executed");
					}
				}
				catch(AssertionError er) {
					test.fail("Data failed to verify on uploading Sample Metadata Template");
					results.createNode("Data failed to verify on uploading Sample Metadata Template");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					test.fail("Data failed to verify on uploading Sample Metadata Template");
					results.createNode("Data failed to verify on uploading Sample Metadata Template");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);	
				}
				Thread.sleep(2000);	
			}
		}
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}
