package Test.Ancera.Reports;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

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

public class RawImageSalmonella {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Raw Image"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Salmonella Raw Image Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@SuppressWarnings({"unused", "resource" })
	@Test (description="Test Case: Raw Image Ingestion for Salmonella", enabled= true, priority= 1) 
	public void RawImageSalm() throws InterruptedException, IOException	{
		Test_Variables.lstRawImageSalmonella = RawImageModel.FillDataSalm();
		for (RawImageModel objModel : Test_Variables.lstRawImageSalmonella) { 
			try{
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

							System.out.println("Cartridge-ID: "+objModel.cartridgeID);
							System.out.println("Run-ID: "+objFilter.LstSampleID.get(0));
							json4.put("DateTime", Test_Variables.lstStartAssaySalmonella.get(0).DateTime);
							json4.put("InstrumentId", objModel.InstrumentID);
							json4.put("UserId", Test_Variables.lstStartAssaySalmonella.get(0).UserID);
							json4.put("CartridgeId", objModel.cartridgeID);
							json4.put("RunId", objFilter.LstSampleID.get(0));
							json4.put("PathogenName", objModel.pathogen);				

							request_startAssay.body(json4.toString());
							Response response3 = request_startAssay.post(Constants.api_StartAssay);

							String data4 = response3.asString();
							System.out.println(data4);
							Thread.sleep(150000);
						}
						catch(AssertionError er) {
							Test_Variables.test.fail("Start Assay API failed");
							Test_Variables.results.createNode("Start Assay API failed");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.RawImageReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail("Start Assay API failed");
							Test_Variables.results.createNode("Start Assay API failed");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.RawImageReportPath, ex);
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
						Test_Variables.test = Test_Variables.extent.createTest("AN-RawImage-"+objModel.lane+": Ingest Salmonella Raw Image run for lane "+objModel.lane, "This test case will verify "+objModel.pathogen+" Raw Image");	
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("Run login API to generate token");
						Test_Variables.preconditions.createNode("Add token in Authorization and run file announcement API with unique RUN ID");
						Test_Variables.steps.createNode("Run Raw Image API");

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
						json3.put("dateTime", Test_Variables.dateRIY+"T"+date+".000Z");
						json3.put("piperId", objModel.InstrumentID);
						json3.put("runType", objModel.runMode);
						json3.put("runId", objModel.run_id);
						json3.put("Pathogen", objModel.pathogen);
						json3.put("sampleid", objFilter.LstSampleID.get(0));
						json3.put("instrumentid", objModel.InstrumentID);
						json3.put("userid", Test_Variables.UserID);
						json3.put("checksum", objModel.checksum);
						json3.put("fileName", objModel.fileName);
						json3.put("fileType", objModel.fileType);

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

						json3.put("Improc", "ImprocSalm01");
						json3.put("countOutCome", objModel.countOutcome);
						json3.put("statusCode", "");
						json3.put("IE_COLLECTION_SITE_ID", "1");
						json3.put("runMode", objModel.runMode);						

						request_fileupload.body(json3.toString());
						Response response2 = request_fileupload.post(Constants.api_RawImage);
						String data3 = response2.asString();
						System.out.println(data3);
						
						Test_Variables.test.pass("Raw Image API ran successfully");
						Test_Variables.results.createNode("Raw Image API ran successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.RawImageReportPath, null);
						Thread.sleep(5000);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Raw Image API failed to run");
						Test_Variables.results.createNode("Raw Image API ran successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.RawImageReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Raw Image API failed to run");
						Test_Variables.results.createNode("Raw Image API ran successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.RawImageReportPath, ex);
					}

					if(objModel.checkLog) {
						try{
							Test_Variables.test = Test_Variables.extent.createTest("AN-"+objModel.pathogen+"-01 Verify relevant data appears in log after running Raw Image API", "This test case will verify that relevant data appears in log after running Raw Image API");	
							Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
							Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
							Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

							Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
							Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
							Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
							Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
							Test_Variables.preconditions.createNode("5. Click on Salmonella Log");

							Thread.sleep(45000);
							Helper.driver.get(Constants.url_SalmonellaLog);
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
							Thread.sleep(1000);

							SoftAssert softAssert = new SoftAssert();
							Test_Variables.steps.createNode("1. Click on Sample ID to expand the filter");
							ClickElement.clickById(Helper.driver, "sampleId_show-filter");			
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("sampleId_view-all")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.steps.createNode("2. Search for the Sample ID's against which the data is ingested");

							Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);	
							ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0));
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);	

							Test_Variables.steps.createNode("3. Click on Apply filter button");
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.RawImageReportPath));
							String records = Helper.driver.findElement(By.id("results-found-count")).getText();
							int results = Integer.parseInt(records); 

							softAssert.assertEquals(results, objModel.lane); 

							for(int j = 0; j<objModel.lane; j++) {
								int lane = j+1;
								Test_Variables.steps.createNode("Verify Result Status is displayed as 'Completed' in table for lane" +lane);
								String getResultStatus = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slResultStatusCol+" label")).getText();
								softAssert.assertEquals(getResultStatus, "Completed", "Result Status is not displayed as Completed in table");

								Test_Variables.steps.createNode("Verify SampleID is displayed as 'Completed' in table for lane" +lane);
								String getSampleId = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slSampleIDCol+" label")).getText();
								softAssert.assertEquals(getSampleId, objFilter.LstSampleID.get(0), "Sample ID is displayed blank in table");

								Test_Variables.steps.createNode("Verify Pathogen is displayed as 'Salmonella' in table for lane" +lane);
								String getPathogen = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slAssayCol+" label")).getText();
								softAssert.assertEquals(getPathogen, objModel.pathogen);
								
								Test_Variables.steps.createNode("Verify Time is updated in table for lane" +lane);
								String getTime = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slTimeCol+" label")).getText();
							//	softAssert.assertEquals(getTime, dateRIT);

								Test_Variables.steps.createNode("Verify QCCode is displayed in table for lane" +lane);
								String getQCCode = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slQCCodeCol+" label")).getText();
								softAssert.assertEquals(getQCCode, objModel.countOutcome);

								if (objModel.isErrorCode) {
									Test_Variables.steps.createNode("Verify Result is displayed in table for lane" +lane);
									String getResult = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slResultCol+" label")).getText();
									softAssert.assertEquals(getResult, "QCFail");
								}

								Test_Variables.steps.createNode("Verify Cartridge ID is same as that written in API body for lane" +lane);
								String getCartridgeID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slCartridgeIDCol+" label")).getText();
								softAssert.assertEquals(getCartridgeID, objModel.cartridgeID);

								Test_Variables.steps.createNode("Verify Instrument ID is same as that written in API body for lane" +lane);
								String getInstrumentID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slInstrumentIDCol+" label")).getText();
								softAssert.assertEquals(getInstrumentID, objModel.InstrumentID);

								Test_Variables.steps.createNode("Verify Run Type as "+Test_Variables.RunType+" in API body for lane" +lane);
								String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.clRunTypeCol+" label")).getText();
								softAssert.assertEquals(getRunType, objModel.runType, "Run Type is not displayed in table");

								Test_Variables.steps.createNode("Verify Test Site ID is displayed in table for lane" +lane);
								String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slTestSiteIDCol+" label")).getText();
								softAssert.assertEquals(getTestSiteID.isEmpty(),false, "Test Site ID is not dislayed in table");

								Test_Variables.steps.createNode("Verify Test Site Name is displayed in table for lane" +lane);
								String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slTestSiteNameCol+" label")).getText();
								softAssert.assertEquals(getTestSiteName.isEmpty(), false, "Test Site Name is not dislayed in table");

								if (!objModel.isErrorCode) {
									Test_Variables.steps.createNode("Verify Total Count is displayed in table for lane" +lane);
									String getTotalCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW1CellCountCol+" label")).getText();
									softAssert.assertEquals(getTotalCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Small Count is displayed in table for lane" +lane);
									String getSmallCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW1PCCountCol+" label")).getText();
									softAssert.assertEquals(getSmallCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Medium Count is displayed in table for lane" +lane);
									String getMediumCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW2CellCountCol+" label")).getText();
									softAssert.assertEquals(getMediumCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Large Count is displayed in table for lane" +lane);
									String getLargeCount = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slW2PCCount+" label")).getText();
									softAssert.assertEquals(getLargeCount.isEmpty(), false);
								}
								String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-"+Test_Elements.slSampleIDCol+" label")).getText();

								Test_Variables.steps.createNode("Open Audit trail popup for lane" +lane);
								Helper.driver.findElement(By.id("audit-trial-"+j)).click();
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1500);			
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.RawImageReportPath));
								Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
								String getAuditSampleID = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-1.text-dark")).getText();
								softAssert.assertEquals(getAuditSampleID, getSampleID);

								Test_Variables.steps.createNode("Verify Sample ID is displayed in Audit log for lane" +lane);
								String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditResultStatusCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditResultStatus, "Completed");

								Test_Variables.steps.createNode("Verify Time is displayed in Audit log for lane" +lane);
								String getAuditTime = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTimeCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTime, getTime);
								
								Test_Variables.steps.createNode("Verify QCCode is displayed in Audit log for lane" +lane);
								String getAuditQCCode = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditQCCodeCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditQCCode, objModel.countOutcome);

								Test_Variables.steps.createNode("Verify Cartridge ID is displayed in Audit log for lane" +lane);
								String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditCartridgeIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

								Test_Variables.steps.createNode("Verify Instrument ID is displayed in Audit log for lane" +lane);
								String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditInstrumentIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditInstrumentId, objModel.InstrumentID);

								Test_Variables.steps.createNode("Verify Run Type is displayed in Audit log for lane" +lane);
								String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditRunTypeCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditRunType, objModel.runType);

								if (!objModel.isErrorCode) {
									Test_Variables.steps.createNode("Verify Total Count is displayed in Audit log for lane" +lane);
									String getAuditTotalCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW1CellCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditTotalCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Small Count is displayed in Audit log for lane" +lane);
									String getAuditSmallCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW1PCCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditSmallCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Medium Count is displayed in Audit log for lane" +lane);
									String getAuditMediumCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW2CellCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditMediumCount.isEmpty(), false);

									Test_Variables.steps.createNode("Verify Large Count is displayed in Audit log for lane" +lane);
									String getAuditLargeCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW2CPCCountCol+".text-dark")).getText();
									softAssert.assertEquals(getAuditLargeCount.isEmpty(), false);
								}
								Test_Variables.steps.createNode("Verify Test Site ID is displayed in Audit log for lane" +lane);
								String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteIDCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTestSiteId.isEmpty(), false);

								Test_Variables.steps.createNode("Verify Test Site Name is displayed in Audit log for lane" +lane);
								String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteNameCol+".text-dark")).getText();
								softAssert.assertEquals(getAuditTestSiteName.isEmpty(), false);
								Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();   			
							}
							softAssert.assertAll();	
							Test_Variables.test.pass("Ingested Successfully");
							Test_Variables.results.createNode("Data ingestion verified successfully");
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.RawImageReportPath, null);
						}catch(AssertionError er) {
							Test_Variables.test.fail("Ingestion failed");
							Test_Variables.results.createNode("Data ingestion verification failed");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.RawImageReportPath, new Exception(er));
						}catch(Exception ex){
							Test_Variables.test.fail("Ingestion failed");
							Test_Variables.results.createNode("Data ingestion verification failed");
							Helper.saveResultNew(ITestResult.FAILURE, Constants.RawImageReportPath, ex);
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
		Test_Variables.extent.flush();
	}


}
