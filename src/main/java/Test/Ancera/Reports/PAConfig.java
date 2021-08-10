package Test.Ancera.Reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
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

import Models.PAModel;
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

public class PAConfig {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/PA_Configuration"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("P/A Configuration Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@SuppressWarnings("unchecked")
	@Test (enabled= true, priority = 1) 
	public void PAConfiguration() throws InterruptedException, IOException {

		int z = 0;
		Test_Variables.lstPASalmonella = PAModel.FillData();
		for (PAModel objModel : Test_Variables.lstPASalmonella) { 
			try{
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				Test_Variables.steps.createNode("1. Create new configuration and get its sample matrix id from database");

				if (objModel.runIngestion) {					
					Helper.driver.get(Constants.url_piperConfiguration);			
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (Helper.driver.findElements(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).size() != 0) {
							if (Helper.driver.findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals(objModel.SampleMatrixConfig)) {
								Thread.sleep(1000);
								break;
							}
						}
					}

					////////////////////////////////////////////////////////////////////////////////////////

					Test_Variables.steps.createNode("2. Ingest with runmode 3 and sample matrix id");
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
					System.out.println(token);	

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
					json4.put("RunId", objModel.sampleID);
					json4.put("PathogenName", objModel.pathogen);				

					request_startAssay.body(json4.toString());
					Response response3 = request_startAssay.post(Constants.api_StartAssay);

					String data4 = response3.asString();
					System.out.println(data4);				

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
					json3.put("RunMode", "3");
					json3.put("Pathogen", objModel.pathogen);

					System.out.println(z);
					z++;

					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_FileUpload);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(180000);

					/////////////////////////////////////////////////////////////////////////////////////////////

					Helper.driver.get(Constants.url_SalmonellaLog);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("sampleId_show-filter")));
					Thread.sleep(1000);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);

					Test_Variables.steps.createNode("3. Navigate to report and search for Ingested sample id");
					//ClickElement.clickById(Helper.driver, "sampleId_show-filter");
					Helper.driver.findElement(By.id("sampleId_show-filter")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);		
					ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
					//Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);

					Test_Variables.steps.createNode("4. Compare the result column with w2 cell count; Threshold value is "+Test_Variables.PA_Threshold);
					Helper.driver.findElement(By.id("sampleId_apply")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);	
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("PA Config", Constants.PAConfigReportPath));

					for (int x=0; x<12; x++) {
						String getResult = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slResultCol)).getText();
						String getCount = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slW2CellCountCol)).getText();

						String regex = "(?<=[\\d])(,)(?=[\\d])";
						Pattern p = Pattern.compile(regex);
						String str = getCount;
						Matcher m = p.matcher(str);
						str = m.replaceAll("");

						if (Integer.parseInt(str) <= Integer.parseInt(Test_Variables.PA_Threshold)) {
							//System.out.println("W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold); 
							if (getResult.equals("Missing Sample Metadata") || getResult.equals("Invalid Result, Please Retest all Dilutions")) {
								System.out.println("Missing Sample Metadata");
							}
							else {
								Assert.assertEquals(getResult, "Negative", "W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold);
							}
						}

						if (Integer.parseInt(str) > Integer.parseInt(Test_Variables.PA_Threshold)) {
							//System.out.println("W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold); 
							if (getResult.equals("Missing Sample Metadata") || getResult.equals("Invalid Result, Please Retest all Dilutions")) {
								System.out.println("Missing Sample Metadata");
							}
							else {
								Assert.assertEquals(getResult, "Positive", "W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold);
							}
						}

						WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
						Actions builder = new Actions(Helper.driver);
						builder.moveToElement(hover).build().perform();
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
						Helper.driver.findElement(By.id("audit-trial-"+x)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("PA Config", Constants.PAConfigReportPath));
						String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditRunTypeCol+".text-dark")).getText();
						Assert.assertEquals(getAuditRunType, "P/A");

						String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteIDCol+".text-dark")).getText();
						Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

						String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteNameCol+".text-dark")).getText();
						Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

						String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
						Assert.assertEquals(getAuditAction, "Modified");

						String getAuditCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW2CellCountCol+".text-dark")).getText();
						String getAuditResult = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditResultCol+".text-dark")).getText();
						String str1 = getAuditCount;
						Matcher m1 = p.matcher(str1);
						str1 = m1.replaceAll("");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("PA Config", Constants.PAConfigReportPath));

						if (Integer.parseInt(str1) <= Integer.parseInt(Test_Variables.PA_Threshold)) {
							//System.out.println("W2CellCount: "+str1+" | Threshold: "+Test_Variables.PA_Threshold); 
							if (getAuditResult.equals("Missing Sample Metadata") || getAuditResult.equals("Invalid Result, Please Retest all Dilutions")) {
								System.out.println("Missing Sample Metadata");
							}
							else {
								Assert.assertEquals(getAuditResult, "Negative", "W2CellCount: "+str1+" | Threshold: "+Test_Variables.PA_Threshold);
							}
						}

						if (Integer.parseInt(str1) > Integer.parseInt(Test_Variables.PA_Threshold)) {
							//System.out.println("W2CellCount: "+str1+" | Threshold: "+Test_Variables.PA_Threshold); 
							if (getAuditResult.equals("Missing Sample Metadata") || getAuditResult.equals("Invalid Result, Please Retest all Dilutions")) {
								System.out.println("Missing Sample Metadata");
							}
							else {
								Assert.assertEquals(getAuditResult, "Positive", "W2CellCount: "+str1+" | Threshold: "+Test_Variables.PA_Threshold);
							}
						}					

						Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
						Thread.sleep(800);
					}
					
					Test_Variables.test.pass("Result column dislayed Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold successfully after ingestion");
					Test_Variables.results.createNode("Result column dislayed Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold successfully after ingestion");
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.PAConfigReportPath, null);	
				}
								
				
				FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.PA_fileName));
				@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook(fsIP);
				XSSFSheet worksheet = wb.getSheetAt(0);
				Cell cell = null;

				for (int i=0; i<12; i++) {

					//	String getResult = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-3")).getText();
					//	Assert.assertEquals(getResult, "Missing Sample Metadata");	

					String getResultDate = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slDateCol)).getText();
					cell=worksheet.getRow(i+1).createCell(0); 
					cell.setCellValue(getResultDate);  

					String getLane = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slLaneCol)).getText();
					cell=worksheet.getRow(i+1).createCell(1); 
					cell.setCellValue(getLane);  

					cell=worksheet.getRow(i+1).createCell(2); 
					cell.setCellValue(Test_Variables.CartridgeID);

					String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+Test_Elements.slResultIDCol)).getText();
					cell=worksheet.getRow(i+1).createCell(3); 
					cell.setCellValue(getResultID);

					cell=worksheet.getRow(i+1).createCell(5); 
					cell.setCellValue(objModel.SampleMatrix); 

					cell=worksheet.getRow(i+1).createCell(17); 
					cell.setCellValue(objModel.sampleID);  
					fsIP.close();
				}

				FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+Test_Variables.PA_fileName));
				wb.write(output_file);
				output_file.close();  


				Test_Variables.steps.createNode("5. Upload sample matrix");
				Helper.driver.get(Constants.url_dataUpload);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("OrgnTypeID")).click();
				Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
				Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("DataFormatId")).click();
				Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
				Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

				Helper.driver.findElement(By.id("file-input")).sendKeys(Test_Variables.fileAbsolutePath+"Excel\\"+Test_Variables.PA_fileName);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
				Thread.sleep(2000);
				Helper.driver.findElement(By.cssSelector(".fa-save")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
				Thread.sleep(1000);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("PA Config", Constants.PAConfigReportPath));
				
				Helper.driver.get(Constants.url_SalmonellaLog);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(3000);

				Test_Variables.steps.createNode("6. Navigate to report and search for Ingested sample id");
				Helper.driver.findElement(By.id("sampleId_show-filter")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("sampleId_search-input")).clear();
				Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(2000);						
				//Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID)).click();
				ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);

				Test_Variables.steps.createNode("5. Compare the result column with w2 cell count; Threshold value is "+objModel.ThresholdValue);
				Helper.driver.findElement(By.id("sampleId_apply")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);			
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("PA Config", Constants.PAConfigReportPath));
				
				for (int x=0; x<12; x++) {

					String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slRunTypeCol+" label")).getText();
					Assert.assertEquals(getRunType, "P/A", "Run Type is not displayed in table");

					String getSampleMatrix = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slSampleMatrixCol+" label")).getText();
					Assert.assertEquals(getSampleMatrix, objModel.SampleMatrix);

					String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slTestSiteIDCol+" label")).getText();
					Assert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");

					String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slTestSiteNameCol+" label")).getText();
					Assert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");

					String getResult = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slResultCol)).getText();
					String getCount = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slW2CellCountCol)).getText();

					String regex = "(?<=[\\d])(,)(?=[\\d])";
					Pattern p = Pattern.compile(regex);
					String str = getCount;
					Matcher m = p.matcher(str);
					str = m.replaceAll("");

					if (Integer.parseInt(str) <= Integer.parseInt(objModel.ThresholdValue)) {
						System.out.println("W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue); 
						Assert.assertEquals(getResult, "Negative", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
					}

					if (Integer.parseInt(str) > Integer.parseInt(objModel.ThresholdValue)) {
						System.out.println("W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue); 
						Assert.assertEquals(getResult, "Positive", "W2CellCount: "+str+" | Threshold: "+objModel.ThresholdValue);
					}

					WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
					Actions builder = new Actions(Helper.driver);
					builder.moveToElement(hover).build().perform();
					Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
					Helper.driver.findElement(By.id("audit-trial-"+x)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);

					String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditRunTypeCol+".text-dark")).getText();
					Assert.assertEquals(getAuditRunType, "P/A");

					String getAuditSampleMatrix = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditSampleMatrixCol+".text-dark")).getText();
					Assert.assertEquals(getAuditSampleMatrix, objModel.SampleMatrix);

					String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteIDCol+".text-dark")).getText();
					Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

					String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteNameCol+".text-dark")).getText();
					Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

					String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
					Assert.assertEquals(getAuditAction, "Modified");

					String getAuditCount = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditW2CellCountCol+".text-dark")).getText();
					String getAuditResult = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditResultCol+".text-dark")).getText();
					String str1 = getAuditCount;
					Matcher m1 = p.matcher(str1);
					str1 = m1.replaceAll("");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("PA Config", Constants.PAConfigReportPath));


					if (Integer.parseInt(str1) <= Integer.parseInt(objModel.ThresholdValue)) {
						System.out.println("W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue); 
						Assert.assertEquals(getAuditResult, "Negative", "W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue);
					}

					if (Integer.parseInt(str1) > Integer.parseInt(objModel.ThresholdValue)) {
						System.out.println("W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue); 
						Assert.assertEquals(getAuditResult, "Positive", "W2CellCount: "+str1+" | Threshold: "+objModel.ThresholdValue);
					}

					Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
					Thread.sleep(800);
				}

				Test_Variables.test.pass("Result column dislayed Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold after uploading sample matrix successfully");
				Test_Variables.results.createNode("Result column dislayed Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold successfully");
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.PAConfigReportPath, null);
				Thread.sleep(1000);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				Test_Variables.results.createNode("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.PAConfigReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				Test_Variables.results.createNode("Result column failed to dislay Positive for w2 cell count greater than threshold and Negative for w2 cell count less than threshold");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.PAConfigReportPath, ex);
			}
		}
	}
	
	
	
	

	@SuppressWarnings("unchecked")
	@Test (enabled= false, priority = 1) 
	public void PAConfiguratfgion() throws InterruptedException, IOException {
	
	
	
	
	Helper.driver.get(Constants.url_SalmonellaLog);
	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("sampleId_show-filter")));
	Thread.sleep(1000);
	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	Thread.sleep(1000);
	//Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//ClickElement.clickById(Helper.driver, "sampleId_show-filter");
	Helper.driver.findElement(By.id("sampleId_show-filter")).click();
	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	Thread.sleep(1000);
	Helper.driver.findElement(By.id("sampleId_search-input")).clear();
	Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys("20210805-TestAut-PA-14907");
	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	Thread.sleep(2000);		
	ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_20210805-TestAut-PA-14907");
	//Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID)).click();
	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	Thread.sleep(1500);

	Test_Variables.steps.createNode("4. Compare the result column with w2 cell count; Threshold value is "+Test_Variables.PA_Threshold);
	Helper.driver.findElement(By.id("sampleId_apply")).click();
	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	Thread.sleep(1500);	
	Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("PA Config", Constants.PAConfigReportPath));

	
	
	String getResult1 = Helper.driver.findElement(By.cssSelector("#row-0 #col-"+Test_Elements.slResultCol)).getText();
	System.out.println(getResult1);
	if (getResult1.equals("Missing Sample Metadata") || getResult1.equals("Invalid Result, Please Retest all Dilutions")) {
		System.out.println("hi");
	}
	
	else {
		System.out.println("hello");
	}
	
	
	
	
	
	
	
	
	
	/*
	
	for (int x=0; x<12; x++) {
		String getResult = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slResultCol)).getText();
		String getCount = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slW2CellCountCol)).getText();

		String regex = "(?<=[\\d])(,)(?=[\\d])";
		Pattern p = Pattern.compile(regex);
		String str = getCount;
		Matcher m = p.matcher(str);
		str = m.replaceAll("");	
		
		
		if (Integer.parseInt(str) <= Integer.parseInt(Test_Variables.PA_Threshold)) {
			System.out.println("W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold); 
			if (getResult.equals("Missing Sample Metadata") == false || getResult.equals("Invalid Result, Please Retest all Dilutions") == false) {
				System.out.println("hello");
			//	Assert.assertEquals(getResult, "Negative", "W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold);
			}
			else {
				System.out.println("hi");
			}
		}

		if (Integer.parseInt(str) > Integer.parseInt(Test_Variables.PA_Threshold)) {
			System.out.println("W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold); 
			if (getResult.equals("Missing Sample Metadata") == false || getResult.equals("Invalid Result, Please Retest all Dilutions") == false) {
				System.out.println("hello1");
			//	Assert.assertEquals(getResult, "Positive", "W2CellCount: "+str+" | Threshold: "+Test_Variables.PA_Threshold);
			}
			else {
				System.out.println("hi1");
			}
		}		
		}      */
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}
