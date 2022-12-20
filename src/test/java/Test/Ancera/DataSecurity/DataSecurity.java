package Test.Ancera.DataSecurity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.ReadPropertyFile;
import Constants.FrameworkConstants;
import MiscFunctions.ClickElement;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import MiscFunctions.Helper;
import Models.DataSecurityModel;
import Models.ReportFilters;
import PageObjects.SalmonellaLogPage;
import PageObjects.UserManagementPage;
import Test.Ancera.Login.LoginTest;

import static PageObjects.SalmonellaLogPage.*;
import static PageObjects.BasePage.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static MiscFunctions.Helper.getScreenshot;
import static Models.IngestionsModel.*;

public class DataSecurity {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/Data_Security"+DateUtil.date+".html");
		ExtentVariables.spark.config().setReportName("Data Security Test Report"); 
		Helper.config();
		LoginTest.login();
	}


	@Test (enabled= true, priority = 1) 
	public void turnonDataSecurity() throws InterruptedException, IOException {
		try {
			Helper.driver.get(Constants.url_user);
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			for (int j=1;j<Helper.driver.findElements(By.cssSelector("tr")).size(); j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+UserManagementPage.userEmailCol+" label")).getText().equals(config.ie_username())) {
					String ReportRole = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+UserManagementPage.userReportingCol+" label")).getText();
					Helper.driver.get(Constants.url_reportsManagement);
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
						if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ReportRole)) {
							Helper.driver.findElement(By.id("edit-report-role-"+i)).click();
							break;
						}	
					}
					break;
				}	
			}

			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			if(Helper.driver.findElements(By.cssSelector("#status-data-security .wrapper-true")).size() == 0) {
				Helper.driver.findElement(By.cssSelector("#status-data-security .row")).click();
			}
			Helper.driver.findElement(popupSaveButton).click();
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(alertMessage).getText(), "Role has been updated successfully."); 	
		}
		catch(AssertionError er) {
		}
		catch(Exception ex) {
		}
	}


	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority = 2) 
	public void positive() throws InterruptedException, IOException {
		DataSecurityModel.lstDataSecurity = DataSecurityModel.FillData();
		for (DataSecurityModel objModel : DataSecurityModel.lstDataSecurity) { 
			try{
				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);
				SoftAssert softAssert = new SoftAssert();

				for (ReportFilters objFilter : objModel.lstFilters) {
					if (objModel.runIngestion) {
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

						json1.put("runId", lstApiAnnouncement.get(0));
						json1.put("dateTime", lstApiAnnouncement.get(1));
						json1.put("Piperid",  lstApiAnnouncement.get(2));
						json1.put("MPNCalculationType", lstApiAnnouncement.get(3));
						json2.put("fileName", lstApiAnnouncement.get(4));
						json2.put("checksum", lstApiAnnouncement.get(5));

						list.add(json2);
						json1.put("files", list);

						request_announcement.body(json1.toString());
						Response response1 = request_announcement.post(Constants.api_announcement);
						String data1 = response1.asString();
						System.out.println(data1);
						String statusCodeAnnouncement = jsonPathEvaluator.get("statusCode");
						RequestSpecification request_fileupload = RestAssured.given();
						request_fileupload.header("Content-Type", "application/json");
						request_fileupload.header("Authorization", "bearer " +token);

						HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
						postRequest1.addHeader("Content-Type", "application/json");
						postRequest1.addHeader("Authorization", "Bearer "+token);

						json3.put("runId", lstSalmonellaIngest.get(0).runId);
						json3.put("checksum", lstSalmonellaIngest.get(0).checksum);
						json3.put("fileName", lstSalmonellaIngest.get(0).fileName);
						json3.put("fileType", lstSalmonellaIngest.get(0).fileType);
						json3.put("file", lstSalmonellaIngest.get(0).file);
						json3.put("fileJson", objModel.fileJson);				
						json3.put("Improc", lstSalmonellaIngest.get(0).improc);
						json3.put("RunMode", "1");
						json3.put("Pathogen", objModel.pathogen);

						request_fileupload.body(json3.toString());
						Response response2 = request_fileupload.post(Constants.api_FileUpload);
						String data3 = response2.asString();
						System.out.println(data3);

						JsonPath jsonPathEvaluator1 = response.jsonPath();
						jsonPathEvaluator1.get("statusCode");

						Thread.sleep(45000);
						SalmonellaLogPage.openSalmonellaLogPage();
						Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Constants.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
						Thread.sleep(2000);
						System.out.println("1");
					//	ClickElement.clickById(Helper.driver, "sampleId_show-filter");		
						try {
							Helper.driver.findElement(By.id("sampleId_show-filter")).click();
						}
						catch (Exception ex) {
							ClickElement.clickById(Helper.driver, "sampleId_show-filter");		
						}
						System.out.println("2");
						Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						Helper.driver.findElement(By.id("sampleId_search-input")).clear();
						Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(SampleID_Salm);
						Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2500);	

						if (objModel.validCase) {
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#sampleId_cust-cb-lst-txt_"+SampleID_Salm)).size(), 1);
							try {
								Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+SampleID_Salm)).click();
							}
							catch (Exception ex) {
								ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+SampleID_Salm);
							}

							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);	
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);	
							String records = Helper.driver.findElement(By.id("results-found-count")).getText();
							softAssert.assertEquals(records, "12"); 
						}

						if (!objModel.validCase) {
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#sampleId_cust-cb-lst-txt_"+SampleID_Salm)).size(), 0, "Run displayed in report even when data security was on");
							turnoffDataSecurity();
							SalmonellaLogPage.openSalmonellaLogPage();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);
							Helper.driver.findElement(By.id("sampleId_show-filter")).click();		
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(SampleID_Salm);
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);	
							Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+SampleID_Salm)).click();
							Thread.sleep(2000);
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1500);	
							String records = Helper.driver.findElement(By.id("results-found-count")).getText();
							softAssert.assertEquals(records, "12"); 
							Helper.getScreenshot();
						}	

						FileInputStream fsIP= new FileInputStream(new File(FrameworkConstants.DataSecurityTemplateUpload));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;

						for (int z=0; z<12; z++) {

							String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+slSampleIDCol)).getText();
							cell=worksheet.getRow(z+1).createCell(4); 
							cell.setCellValue(getSampleID);  

							String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+slResultIDCol)).getText();
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
							cell.setCellValue(objModel.collectionSiteID); 

							String getLane = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+slLaneCol)).getText();
							cell=worksheet.getRow(z+1).createCell(16); 
							cell.setCellValue(getLane);  

							fsIP.close();
						}
						FileOutputStream output_file =new FileOutputStream(new File(FrameworkConstants.DataSecurityTemplateUpload));
						wb.write(output_file);
						output_file.close();  
					}

					if (!objModel.validCase) {
						turnonDataSecurity();
					}	

					if (objModel.uploadTemplate) {
						FileInputStream fsIP= new FileInputStream(new File(FrameworkConstants.DataSecurityTemplateUpload));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;

						for (int z=0; z<12; z++) {
							cell=worksheet.getRow(z+1).createCell(1); 
							cell.setCellValue(objModel.collectionSiteID); 
							fsIP.close();
						}

						FileOutputStream output_file =new FileOutputStream(new File(FrameworkConstants.DataSecurityTemplateUpload));
						wb.write(output_file);
						output_file.close();  

						Helper.driver.get(Constants.url_dataUpload);
						Constants.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("OrgnTypeID")).click();
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("DataFormatId")).click();
						Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
						Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("file-input")).sendKeys(FrameworkConstants.DataSecurityTemplateUpload);
						Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(4000);		

						if (!objModel.validCase) {
							Constants.wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)); 
							softAssert.assertTrue(Helper.driver.findElement(alertMessage).getText().startsWith("Errors found in"), "Error message did not appear");
						}

						if (objModel.validCase) {
							Helper.driver.findElement(By.cssSelector(".fa-save")).click();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							getScreenshot();
							softAssert.assertEquals(Helper.driver.findElement(alertMessage).getText(), "MetaData DataSecurity.xlsx saved successfully.", "Error message appeared");
							SalmonellaLogPage.openSalmonellaLogPage();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Constants.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
							Thread.sleep(2000);

							Helper.driver.findElement(By.id("sampleId_show-filter")).click();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);

							Helper.driver.findElement(By.id("sampleId_search-input")).clear();
							Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(SampleID_Salm);
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);

							try {
								ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+SampleID_Salm);
							}
							catch (Exception ex) {
								Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+SampleID_Salm)).click();
							}

							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);	

							Helper.driver.findElement(By.id("audit-trial-0")).click();
							Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1500);		
							getScreenshot();

							String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");

							int getAuditRows = Helper.driver.findElements(By.cssSelector(".audit-v2 tr")).size();

							if (objModel.TestCaseName.startsWith("AN-DS-03:")) {
								System.out.println("hello3");
								softAssert.assertEquals(getAuditRows, 3);
							}

							if (objModel.TestCaseName.startsWith("AN-DS-05:")) {
								System.out.println("hello4");
								softAssert.assertEquals(getAuditRows, 4);
							}
						}
					}
					softAssert.assertAll();

					ExtentVariables.test.pass("Scenario verified successfully");
					ExtentVariables.results.createNode("Scenario verified successfully");
					getScreenshot();
					Helper.saveResult(ITestResult.SUCCESS, null);
				}
			}catch(AssertionError er) {
				ExtentVariables.test.fail("Ingestion failed");
				ExtentVariables.results.createNode("Data ingestion verification failed");
				Helper.saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex){
				ExtentVariables.test.fail("Ingestion failed");
				ExtentVariables.results.createNode("Data ingestion verification failed");
				Helper.saveResult(ITestResult.FAILURE, ex);
			}
		}
	}


	//@Test (enabled= true, priority = 3) 
	public void turnoffDataSecurity() throws InterruptedException, IOException {
		try {
			Helper.driver.get(Constants.url_user);
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			for (int j=1;j<Helper.driver.findElements(By.cssSelector("tr")).size(); j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+UserManagementPage.userEmailCol+" label")).getText().equals(config.ie_username())) {
					String ReportRole = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+UserManagementPage.userReportingCol+" label")).getText();
					Helper.driver.get(Constants.url_reportsManagement);
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
						if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ReportRole)) {
							Helper.driver.findElement(By.id("edit-report-role-"+i)).click();
							break;
						}	
					}
					break;
				}	
			}

			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			if(Helper.driver.findElements(By.cssSelector("#status-data-security .wrapper-true")).size() == 1) {
				Helper.driver.findElement(By.cssSelector("#status-data-security .toggle")).click();
			}
			Helper.driver.findElement(popupSaveButton).click();

			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(alertMessage).getText(), "Role has been updated successfully."); 
		}
		catch(AssertionError er) {
		}
		catch(Exception ex) {
		}
	}


	@AfterTest
	public static void endreport() {
		ExtentVariables.extent.flush();
		//Helper.driver.close();
	}
}
