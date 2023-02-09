package Test.Ancera.Ingestions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.ClickElement;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import Models.InstallationRunModel;
import Models.RawImageModel;
import Models.ReportFilters;
import PageObjects.BasePage;
import PageObjects.CoccidiaLogPage;
import Test.Ancera.Login.LoginTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static MiscFunctions.Methods.*;
import static Models.IngestionsModel.*;

public class RawImageCoccidia extends BaseTest{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/Raw Image Ingestion"+DateUtil.date+".html");
		ExtentVariables.spark.config().setReportName("Raw Image Ingestion Test Report"); 
	}

	@Test
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}

	@SuppressWarnings({"unused", "resource" })
	@Test (description="Test Case: Raw Image Ingestion for Coccidia", enabled= true, priority= 1) 
	public void RawImageCocci() throws InterruptedException, IOException	{
		RawImageModel.lstRawImageCoccidia = RawImageModel.FillData();
		for (RawImageModel objModel : RawImageModel.lstRawImageCoccidia) { 
			try{
				for (ReportFilters objFilter : objModel.lstFilters) {

					if (objModel.isInstallationRunConfigure) {
						getDriver().get(Constants.url_piperConfiguration);		
						waitElementInvisible(BasePage.loading_cursor);
						Thread.sleep(1000);
						for (int i = 1; i<=100; i++) {
							if (getDriver().findElements(By.cssSelector("#installation-"+i+" td:nth-child(2)")).size() != 0) {
								if (getDriver().findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals(InstallationRunModel.installationImprocVersionCocci)) {
									Thread.sleep(1000);
									getDriver().findElement(By.id("edit-installation-"+i)).click();
									waitElementInvisible(BasePage.loading_cursor);
									Thread.sleep(1500);
									break;
								}
							}
						}

						getDriver().findElement(By.id("MinMeanVal")).clear();
						getDriver().findElement(By.id("MinMeanVal")).sendKeys("1");
						getDriver().findElement(By.id("MaxMeanVal")).clear();
						getDriver().findElement(By.id("MaxMeanVal")).sendKeys("2000");
						getDriver().findElement(By.id("MinStdVal")).clear();
						getDriver().findElement(By.id("MinStdVal")).sendKeys("1");
						getDriver().findElement(By.id("MaxStdVal")).clear();
						getDriver().findElement(By.id("MaxStdVal")).sendKeys("2000");
						getDriver().findElement(By.id("MinStdVal")).click();
						getDriver().findElement(By.id("btn-save")).click();
						Thread.sleep(2000);
						getScreenshot();
					}


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
					Response response = request.post(Constants.api_login);
					int code = response.getStatusCode();
					Assert.assertEquals(code, 200);

					String data = response.asString();
					System.out.println(data);

					JsonPath jsonPathEvaluator = response.jsonPath();
					String token = jsonPathEvaluator.get("token");		
					String statusCode = jsonPathEvaluator.get("statusCode");

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


					///////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////

					if (objModel.runStartAssay ) {
						try {
							ExtentVariables.test = ExtentVariables.extent.createTest("AN-StrtAssay-01: Verify Coccidia Start Assay API", "This test case will run Coccidia Start Assay API");	
							ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
							ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
							ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

							ExtentVariables.preconditions.createNode("1. Run login API to generate token");
							ExtentVariables.steps.createNode("1. Add token generated on running Login API in Authorization");
							ExtentVariables.steps.createNode("2. Write 'Coccidia' in Pathogen Name");
							ExtentVariables.steps.createNode("3. Run the API");

							RequestSpecification request_startAssay = RestAssured.given();

							request_startAssay.header("Content-Type", "application/json");
							request_startAssay.header("Authorization", "bearer " +token);

							HttpGet postRequest3 = new HttpGet(Constants.api_StartAssay);
							postRequest3.addHeader("Content-Type", "application/json");
							postRequest3.addHeader("Authorization", "Bearer "+token);

							System.out.println("Cartridge-ID: "+objModel.cartridgeID);
							System.out.println("Run-ID: "+RunID_Cocci);
							json4.put("DateTime", lstStartAssayCoccidia.get(0).DateTime);
							json4.put("InstrumentId", objModel.InstrumentID);
							json4.put("UserId", lstStartAssayCoccidia.get(0).UserID);
							json4.put("CartridgeId", objModel.cartridgeID);
							json4.put("RunId", RunID_Cocci);
							json4.put("PathogenName", objModel.pathogen);				

							request_startAssay.body(json4.toString());
							Response response3 = request_startAssay.post(Constants.api_StartAssay);

							String data4 = response3.asString();
							System.out.println(data4);
							Thread.sleep(10000);
						}
						catch(AssertionError er) {
							ExtentVariables.test.fail("Start Assay API failed");
							ExtentVariables.results.createNode("Start Assay API failed");
							saveResult(ITestResult.FAILURE, new Exception(er));
						}catch(Exception ex){
							ExtentVariables.test.fail("Start Assay API failed");
							ExtentVariables.results.createNode("Start Assay API failed");
							saveResult(ITestResult.FAILURE, ex);
						}
					}
					/////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////	


					/////////////////////////////////////////////////////////Raw Image API////////////////////////////////////////////////////////////////////////////////

					DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
					Date dateR = new Date();
					String date = dateFormat1.format(dateR);

					DateFormat dateFormat3 = new SimpleDateFormat("hh:mm a");
					Date dateRI = new Date();
					String dateRIT = dateFormat3.format(dateRI);

					try{
						ExtentVariables.test = ExtentVariables.extent.createTest("AN-RawImage-"+objModel.lane+": Ingest Coccidia Raw Image run for lane "+objModel.lane, "This test case will verify "+objModel.pathogen+" Raw Image");	
						ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
						ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
						ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

						ExtentVariables.preconditions.createNode("Run login API to generate token");
						ExtentVariables.preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
						ExtentVariables.steps.createNode("Run Raw Image API");

						Thread.sleep(1000);
						RequestSpecification request_fileupload = RestAssured.given();
						request_fileupload.header("Content-Type", "application/json");
						request_fileupload.header("Authorization", "bearer " +token);

						HttpGet postRequest1 = new HttpGet(Constants.api_RawImage);
						postRequest1.addHeader("Content-Type", "application/json");
						postRequest1.addHeader("Authorization", "Bearer "+token);

						System.out.println("Cartridge-ID Raw Image: "+objModel.cartridgeID);
						System.out.println("Run-ID Raw Image: "+objModel.run_id);
						json3.put("cartridgeId", objModel.cartridgeID);
						json3.put("lane", objModel.lane);
						json3.put("dateTime", DateUtil.dateRIY+"T"+date+".000Z");
						json3.put("piperId", objModel.InstrumentID);
						json3.put("runType", objModel.runMode);
						json3.put("runId", objModel.run_id);
						json3.put("Pathogen", objModel.pathogen);
						json3.put("sampleid", RunID_Cocci);
						json3.put("instrumentid", objModel.InstrumentID);
						json3.put("userid", UserID);
						json3.put("checksum", objModel.checksum);
						json3.put("fileName", objModel.fileName);
						json3.put("fileType", objModel.fileType);
						json3.put("ieSampleMatrixID", "57");


						
						if (!objModel.isErrorCode) {
							String TestFile = objModel.base64fileName;
							FileReader FR = new FileReader(TestFile);
							BufferedReader BR = new BufferedReader(FR);
							String Content = "";

							while((Content = BR.readLine())!= null) {
								json3.put("file", Content);					
							}
						}

						if (objModel.isErrorCode) {
							json3.put("file", "");		
						}

						json3.put("Improc", "ImprocCocc01");
						json3.put("countOutCome", objModel.countOutcome);
						json3.put("statusCode", "");
						json3.put("IE_COLLECTION_SITE_ID", "1");
						json3.put("runMode", objModel.runMode);						

						request_fileupload.body(json3.toString());
						Response response2 = request_fileupload.post(Constants.api_RawImage);
						String data3 = response2.asString();
						System.out.println(data3);

						ExtentVariables.test.pass("Raw Image API ran successfully");
						ExtentVariables.results.createNode("Raw Image API ran successfully");
						saveResult(ITestResult.SUCCESS, null);
						Thread.sleep(5000);
					}
					catch(AssertionError er) {
						ExtentVariables.test.fail("Raw Image API failed to run");
						ExtentVariables.results.createNode("Raw Image API ran successfully");
						saveResult(ITestResult.FAILURE, new Exception(er));
					}catch(Exception ex){
						ExtentVariables.test.fail("Raw Image API failed to run");
						ExtentVariables.results.createNode("Raw Image API ran successfully");
						saveResult(ITestResult.FAILURE, ex);
					}

					if(objModel.checkLog) {
						try{
							ExtentVariables.test = ExtentVariables.extent.createTest("AN-"+objModel.pathogen+"-01 Verify relevant data appears in log after running Raw Image API", "This test case will verify that relevant data appears in log after running Raw Image API");	
							ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
							ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
							ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

							ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
							ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
							ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
							ExtentVariables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
							ExtentVariables.preconditions.createNode("5. Click on Coccidia Log");

							Thread.sleep(150000);
							CoccidiaLogPage.openCoccidiaLogPage();
							waitElementInvisible(BasePage.loading_cursor);
							waitElementVisible(By.id("sort-sampleId"));
							Thread.sleep(1000);

							SoftAssert softAssert = new SoftAssert();
							ExtentVariables.steps.createNode("1. Click on Sample ID to expand the filter");
							ClickElement.clickById(getDriver(), "sampleId_show-filter");			
							waitElementInvisible(BasePage.loading_cursor);
							Thread.sleep(1000);
							ExtentVariables.steps.createNode("2. Search for the Sample ID's against which the data is ingested");

							getDriver().findElement(By.id("sampleId_search-input")).sendKeys(RunID_Cocci);
							waitElementInvisible(BasePage.loading_cursor);
							Thread.sleep(2000);	
							ClickElement.clickByCss(getDriver(), "#sampleId_cust-cb-lst-txt_"+RunID_Cocci);
							waitElementInvisible(BasePage.loading_cursor);
							Thread.sleep(2000);	

							ExtentVariables.steps.createNode("3. Click on Apply filter button");
							getDriver().findElement(By.id("sampleId_apply")).click();
							waitElementInvisible(BasePage.loading_cursor);
							getScreenshot();
							String records = getDriver().findElement(By.id("results-found-count")).getText();
							int results = Integer.parseInt(records); 

							softAssert.assertEquals(results, objModel.lane); 
/*
							for(int j = 0; j<objModel.lane; j++) {
								int lane = j+1;
								Test_Variables.steps.createNode("Verify Result Status is displayed as 'Completed' in table for lane" +lane);
								String getResultStatus = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clResultStatusCol+" label")).getText();
								softAssert.assertEquals(getResultStatus, "Completed", "Result Status is not displayed as Completed in table");

								Test_Variables.steps.createNode("Verify SampleID is displayed as 'Completed' in table for lane" +lane);
								String getSampleId = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clSampleIDCol+" label")).getText();
								softAssert.assertEquals(getSampleId, objFilter.LstSampleID.get(0), "Sample ID is displayed blank in table");

								Test_Variables.steps.createNode("Verify Pathogen is displayed as 'Coccidia' in table for lane" +lane);
								String getPathogen = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clAssayCol+" label")).getText();
								softAssert.assertEquals(getPathogen, objModel.pathogen);

								Test_Variables.steps.createNode("Verify Time is updated in table for lane" +lane);
								String getTime = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clTimeCol+" label")).getText();
								//	softAssert.assertEquals(getTime, dateRIT);

								if (!objModel.isInstallationRunConfigure) {
									Test_Variables.steps.createNode("Verify QCCode is displayed in table for lane" +lane);
									String getQCCode = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clQCCodeCol+" label")).getText();
									softAssert.assertEquals(getQCCode, objModel.countOutcome);
								}

								if (objModel.isInstallationRunConfigure) {
									Test_Variables.steps.createNode("Verify QCCode is displayed in table for lane" +lane);
									String getQCCode = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clQCCodeCol+" label")).getText();
									softAssert.assertEquals(getQCCode, "PASS");
								}

								if (objModel.isErrorCode) {
									Test_Variables.steps.createNode("Verify Result is displayed in table for lane" +lane);
									String getResult = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clResultCol+" label")).getText();
									softAssert.assertEquals(getResult, "QCFail");
								}

								Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane" +lane);
								String getCartridgeID = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clCatridgeIDCol+" label")).getText();
								softAssert.assertEquals(getCartridgeID, objModel.cartridgeID);

								Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane" +lane);
								String getInstrumentID = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clInstrumentIDCol+" label")).getText();
								softAssert.assertEquals(getInstrumentID, objModel.InstrumentID);

								Test_Variables.steps.createNode("Verify Run Type as "+Test_Variables.RunType+" in API body for lane" +lane);
								String getRunType = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clRunTypeCol+" label")).getText();
								softAssert.assertEquals(getRunType, objModel.runType, "Run Type is not displayed in table");

								Test_Variables.steps.createNode("Verify Test Site ID is displayed in table for lane" +lane);
								String getTestSiteID = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clTestSiteIDCol+" label")).getText();
								softAssert.assertEquals(getTestSiteID.isEmpty(),false, "Test Site ID is not dislayed in table");

								Test_Variables.steps.createNode("Verify Test Site Name is displayed in table for lane" +lane);
								String getTestSiteName = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clTestSiteNameCol+" label")).getText();
								softAssert.assertEquals(getTestSiteName.isEmpty(), false, "Test Site Name is not dislayed in table");

								if (!objModel.isErrorCode) {
									Test_Variables.steps.createNode("Verify Total Count is displayed in table for lane" +lane);
									String getTotalCount = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clTotalCountCol+" label")).getText();
									softAssert.assertEquals(getTotalCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Small Count is displayed in table for lane" +lane);
									String getSmallCount = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clSmallCountCol+" label")).getText();
									softAssert.assertEquals(getSmallCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Medium Count is displayed in table for lane" +lane);
									String getMediumCount = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clMediumCountCol+" label")).getText();
									softAssert.assertEquals(getMediumCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Large Count is displayed in table for lane" +lane);
									String getLargeCount = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clLargeCountCol+" label")).getText();
									softAssert.assertEquals(getLargeCount.isEmpty(), false);
								}
								String getSampleID = getDriver().findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slSampleIDCol+" label")).getText();

								Test_Variables.steps.createNode("Open Audit trail popup for lane" +lane);
								getDriver().findElement(By.id("audit-trial-"+j)).click();
								waitElementInvisible(BasePage.loading_cursor);
								Thread.sleep(1500);			
								Test_Variables.test.addScreenCaptureFromPath(getScreenshot("Coccidia Log"));
								Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
								String getAuditSampleID = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
								softAssert.assertEquals(getAuditSampleID, getSampleID);

								Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
								String getAuditResultStatus = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditResultStatusCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditResultStatus, "Completed");

								Test_Variables.steps.createNode("Verify Time is displayed in Audit log for lane" +lane);
								String getAuditTime = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditTimeCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTime, getTime);

								if (!objModel.isInstallationRunConfigure) {
									Test_Variables.steps.createNode("Verify QCCode is displayed in Audit log for lane" +lane);
									String getAuditQCCode = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditQCCodeCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditQCCode, objModel.countOutcome);
								}

								if (objModel.isInstallationRunConfigure) {
									Test_Variables.steps.createNode("Verify QCCode is displayed in Audit log for lane" +lane);
									String getAuditQCCode = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditQCCodeCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditQCCode, "PASS");
								}

								Test_Variables.steps.createNode("Verify Cartridge ID is displayed in Audit log for lane" +lane);
								String getAuditCartridgeId = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditCartridgeIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

								Test_Variables.steps.createNode("Verify Instrument ID is displayed in Audit log for lane" +lane);
								String getAuditInstrumentId = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditInstrumentIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditInstrumentId, objModel.InstrumentID);

								Test_Variables.steps.createNode("Verify Run Type is displayed in Audit log for lane" +lane);
								String getAuditRunType = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditRunTypeCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditRunType, objModel.runType);

								if (!objModel.isErrorCode) {
									Test_Variables.steps.createNode("Verify Total Count is displayed in Audit log for lane" +lane);
									String getAuditTotalCount = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditTotalCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditTotalCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Small Count is displayed in Audit log for lane" +lane);
									String getAuditSmallCount = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditSmallCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditSmallCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Medium Count is displayed in Audit log for lane" +lane);
									String getAuditMediumCount = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditMediumCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditMediumCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Large Count is displayed in Audit log for lane" +lane);
									String getAuditLargeCount = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditLargeCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditLargeCount.isEmpty(), false);
								}
								Test_Variables.steps.createNode("Verify Test Site ID is displayed in Audit log for lane" +lane);
								String getAuditTestSiteId = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditTestSiteIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTestSiteId.isEmpty(), false);

								Test_Variables.steps.createNode("Verify Test Site Name is displayed in Audit log for lane" +lane);
								String getAuditTestSiteName = getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditTestSiteNameCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTestSiteName.isEmpty(), false);
								getDriver().findElement(By.cssSelector(Test_Elements.closeAudit)).click();   	
								
							}*/
							softAssert.assertAll();	
							ExtentVariables.test.pass("Ingested Successfully");
							ExtentVariables.results.createNode("Data ingestion verified successfully");
							saveResult(ITestResult.SUCCESS, null);
						}catch(AssertionError er) {
							ExtentVariables.test.fail("Ingestion failed");
							ExtentVariables.results.createNode("Data ingestion verification failed");
							saveResult(ITestResult.FAILURE, new Exception(er));
						}catch(Exception ex){
							ExtentVariables.test.fail("Ingestion failed");
							ExtentVariables.results.createNode("Data ingestion verification failed");
							saveResult(ITestResult.FAILURE, ex);
						}
					}
					Thread.sleep(2000);	
				}
			}
			catch(Exception ex){
			}
		}		
	}


	@AfterTest
	public static void endreport() {
		ExtentVariables.extent.flush();
	}


}
