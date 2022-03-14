package Test.Ancera.Administration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

import Models.DataSecurityModel;
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

public class DataSecurity {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Data_Security"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Data Security Test Report"); 
		Helper.config();
		ConfigureLogin.login();
	}


	@Test (enabled= true, priority = 1) 
	public void turnonDataSecurity() throws InterruptedException, IOException {
		try {
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for (int j=1;j<Helper.driver.findElements(By.cssSelector("tr")).size(); j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					String ReportRole = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userReportingCol+" label")).getText();
					Helper.driver.get(Constants.url_reportsManagement);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
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

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			if(Helper.driver.findElements(By.cssSelector("#status-data-security .wrapper-true")).size() == 0) {
				Helper.driver.findElement(By.cssSelector("#status-data-security .row")).click();
			}
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Role has been updated successfully."); 	
		}
		catch(AssertionError er) {
		}
		catch(Exception ex) {
		}
	}


	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority = 2) 
	public void positive() throws InterruptedException, IOException {
		Test_Variables.lstDataSecurity = DataSecurityModel.FillData();
		for (DataSecurityModel objModel : Test_Variables.lstDataSecurity) { 
			try{
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
				SoftAssert softAssert = new SoftAssert();

				for (ReportFilters objFilter : objModel.lstFilters) {
					if (objModel.runIngestion) {
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
						json3.put("RunMode", "1");
						json3.put("Pathogen", objModel.pathogen);

						request_fileupload.body(json3.toString());
						Response response2 = request_fileupload.post(Constants.api_FileUpload);
						String data3 = response2.asString();
						System.out.println(data3);

						JsonPath jsonPathEvaluator1 = response.jsonPath();
						jsonPathEvaluator1.get("statusCode");

						Thread.sleep(45000);
						Helper.driver.get(Constants.url_SalmonellaLog);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
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
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						Helper.driver.findElement(By.id("sampleId_search-input")).clear();
						Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2500);	

						if (objModel.validCase) {
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).size(), 1);
							try {
								Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
							}
							catch (Exception ex) {
								ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0));
							}

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);	
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);	
							String records = Helper.driver.findElement(By.id("results-found-count")).getText();
							softAssert.assertEquals(records, "12"); 
						}

						if (!objModel.validCase) {
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).size(), 0, "Run displayed in report even when data security was on");
							turnoffDataSecurity();
							Helper.driver.get(Constants.url_SalmonellaLog);
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);
							Helper.driver.findElement(By.id("sampleId_show-filter")).click();		
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);	
							Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
							Thread.sleep(2000);
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1500);	
							String records = Helper.driver.findElement(By.id("results-found-count")).getText();
							softAssert.assertEquals(records, "12"); 
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.DataSecurityReportPath));
						}	

						FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.templateFileNameDS));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;

						for (int z=0; z<12; z++) {

							String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.slSampleIDCol)).getText();
							cell=worksheet.getRow(z+1).createCell(4); 
							cell.setCellValue(getSampleID);  

							String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.slResultIDCol)).getText();
							cell=worksheet.getRow(z+1).createCell(0); 
							cell.setCellValue(getResultID);  

							cell=worksheet.getRow(z+1).createCell(19); 
							cell.setCellValue(objModel.cartridgeID); 

							cell=worksheet.getRow(z+1).createCell(3); 
							cell.setCellValue(Test_Variables.SampleMatrix); 

							cell=worksheet.getRow(z+1).createCell(9); 
							cell.setCellValue(Test_Variables.FlockID); 

							cell=worksheet.getRow(z+1).createCell(8); 
							cell.setCellValue(Test_Variables.RequestedAssay); 

							cell=worksheet.getRow(z+1).createCell(2); 
							cell.setCellValue(Test_Variables.KitLot); 

							cell=worksheet.getRow(z+1).createCell(23); 
							cell.setCellValue(Test_Variables.CollectionDate); 

							cell=worksheet.getRow(z+1).createCell(6); 
							cell.setCellValue(Test_Variables.CustomerSampleID); 

							cell=worksheet.getRow(z+1).createCell(1); 
							cell.setCellValue(objModel.collectionSiteID); 

							String getLane = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.slLaneCol)).getText();
							cell=worksheet.getRow(z+1).createCell(16); 
							cell.setCellValue(getLane);  

							fsIP.close();
						}
						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+Test_Variables.templateFileNameDS));
						wb.write(output_file);
						output_file.close();  
					}

					if (!objModel.validCase) {
						turnonDataSecurity();
					}	

					if (objModel.uploadTemplate) {
						FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.templateFileNameDS));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;

						for (int z=0; z<12; z++) {
							cell=worksheet.getRow(z+1).createCell(1); 
							cell.setCellValue(objModel.collectionSiteID); 
							fsIP.close();
						}

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+Test_Variables.templateFileNameDS));
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
						Helper.driver.findElement(By.id("file-input")).sendKeys(Test_Variables.fileAbsolutePath+"Excel\\"+Test_Variables.templateFileNameDS);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(4000);		

						if (!objModel.validCase) {
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage)); 
							softAssert.assertTrue(Helper.driver.findElement(Test_Elements.alertMessage).getText().startsWith("Errors found in"), "Error message did not appear");
						}

						if (objModel.validCase) {
							Helper.driver.findElement(By.cssSelector(".fa-save")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.DataSecurityReportPath));
							softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.templateFileNameDS+" saved successfully.", "Error message appeared");
							Helper.driver.get(Constants.url_SalmonellaLog);
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
							Thread.sleep(2000);

							Helper.driver.findElement(By.id("sampleId_show-filter")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);

							Helper.driver.findElement(By.id("sampleId_search-input")).clear();
							Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);

							try {
								ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0));
							}
							catch (Exception ex) {
								Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
							}

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							Helper.driver.findElement(By.id("sampleId_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(3000);	

							Helper.driver.findElement(By.id("audit-trial-0")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1500);		

							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.DataSecurityReportPath));

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

					Test_Variables.test.pass("Scenario verified successfully");
					Test_Variables.results.createNode("Scenario verified successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.DataSecurityReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataSecurityReportPath, null);
				}
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.DataSecurityReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.DataSecurityReportPath, ex);
			}
		}
	}


	//@Test (enabled= true, priority = 3) 
	public void turnoffDataSecurity() throws InterruptedException, IOException {
		try {
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for (int j=1;j<Helper.driver.findElements(By.cssSelector("tr")).size(); j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					String ReportRole = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userReportingCol+" label")).getText();
					Helper.driver.get(Constants.url_reportsManagement);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
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

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			if(Helper.driver.findElements(By.cssSelector("#status-data-security .wrapper-true")).size() == 1) {
				Helper.driver.findElement(By.cssSelector("#status-data-security .toggle")).click();
			}
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Role has been updated successfully."); 
		}
		catch(AssertionError er) {
		}
		catch(Exception ex) {
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//Helper.driver.close();
	}
}
