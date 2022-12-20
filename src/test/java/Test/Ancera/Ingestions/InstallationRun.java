package Test.Ancera.Ingestions;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
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

import MiscFunctions.ClickElement;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import MiscFunctions.Helper;
import Models.InstallationRunModel;
import Models.ReportFilters;
import PageObjects.CoccidiaLogPage;
import PageObjects.SalmonellaLogPage;
import Test.Ancera.Login.LoginTest;

import static Models.IngestionsModel.*;
import static PageObjects.SalmonellaLogPage.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InstallationRun {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/Installation_Run"+DateUtil.date+".html");
		ExtentVariables.spark.config().setReportName("Installation Run Test Report"); 

		Helper.config();
		LoginTest.login();
	}


	@SuppressWarnings("unchecked") 
	@Test (enabled= true, priority = 1) 
	public void installationRunConfigSalmList() throws InterruptedException, IOException {

		int z = 0;
		InstallationRunModel.lstInstallationRunCreate = InstallationRunModel.FillData();
		for (InstallationRunModel objModel : InstallationRunModel.lstInstallationRunCreate) { 
			try{
				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
				ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				ExtentVariables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				ExtentVariables.steps.createNode("1. Click on create new button next to Installation Run Config");
				SoftAssert softAssert = new SoftAssert();
				
				for (ReportFilters objFilter : objModel.lstFilters) {

					Helper.driver.get(Constants.url_piperConfiguration);			
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (Helper.driver.findElements(By.cssSelector("#installation-"+i+" td:nth-child(2)")).size() != 0) {
							if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals(InstallationRunModel.installationImprocVersionSalm)) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("edit-installation-"+i)).click();
								Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);
								break;
							}
//							else {
//								Helper.driver.findElement(By.id("PathogenName")).sendKeys("Salmonella");
//								Helper.driver.findElement(By.id("PathogenName")).sendKeys(Keys.ENTER);
//								Helper.driver.findElement(By.id("create-installation-run")).click();
//								Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
//								Thread.sleep(1000);
//								Helper.driver.findElement(By.cssSelector("#ImprocName img")).click();
//								Thread.sleep(800);
//								Helper.driver.findElement(By.xpath("//*[contains(text(),'ImprocSalm01')]")).click();
//								Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Test_Variables.installationImprocVersionSalm);
//								Helper.driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
//								break;
//							}
						}
					}

					ExtentVariables.steps.createNode("2. Select improc name and improc version from dropdown");
					ExtentVariables.steps.createNode("3. "+objModel.steps);
					Helper.driver.findElement(By.id("MinMeanVal")).clear();
					Helper.driver.findElement(By.id("MinMeanVal")).sendKeys(objFilter.MinMean);
					Helper.driver.findElement(By.id("MaxMeanVal")).clear();
					Helper.driver.findElement(By.id("MaxMeanVal")).sendKeys(objFilter.MaxMean);
					Helper.driver.findElement(By.id("MinStdVal")).clear();
					Helper.driver.findElement(By.id("MinStdVal")).sendKeys(objFilter.MinStd);
					Helper.driver.findElement(By.id("MaxStdVal")).clear();
					Helper.driver.findElement(By.id("MaxStdVal")).sendKeys(objFilter.MaxStd);
					Helper.driver.findElement(By.id("MinStdVal")).click();
					Helper.getScreenshot();
					Helper.driver.findElement(By.id("btn-save")).click();
					Thread.sleep(2000);
					Helper.getScreenshot();
					
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
					//System.out.println(token);	

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

					json1.put("runId", AnnouncementRunID);
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

					if (objModel.runStartAssay) {
						RequestSpecification request_startAssay = RestAssured.given();

						request_startAssay.header("Content-Type", "application/json");
						request_startAssay.header("Authorization", "bearer " +token);

						HttpGet postRequest3 = new HttpGet(Constants.api_StartAssay);
						postRequest3.addHeader("Content-Type", "application/json");
						postRequest3.addHeader("Authorization", "Bearer "+token);

						json4.put("DateTime", lstStartAssaySalmonella.get(0).DateTime);
						json4.put("InstrumentId", lstStartAssaySalmonella.get(0).InstrumentID);
						json4.put("UserId", lstStartAssaySalmonella.get(0).UserID);
						json4.put("CartridgeId", lstStartAssaySalmonella.get(0).CartridgeID);
						json4.put("RunId", objModel.sampleID);
						json4.put("PathogenName", objModel.pathogen);				

						request_startAssay.body(json4.toString());
						Response response3 = request_startAssay.post(Constants.api_StartAssay);

						String data4 = response3.asString();
						System.out.println(data4);	
						Thread.sleep(5000);
					}

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
					json3.put("RunMode", "2");
					json3.put("Pathogen", lstSalmonellaIngest.get(0).pathogen);

					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_FileUpload);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(75000);

					SalmonellaLogPage.openSalmonellaLogPage();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);

					ExtentVariables.steps.createNode("4. Navigate to report and search for Ingested sample id");
					Helper.driver.findElement(By.id("sampleId_show-filter")).click();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);

					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);	

					try {
						Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID+" b")).click();
					}
					catch (Exception ex) {
						ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
					}

					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);
					System.out.println(z);
					z++;

					ExtentVariables.steps.createNode("5. Verify the status in QC Code column");
					Helper.driver.findElement(By.id("sampleId_apply")).click();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);					

					for (int x=0; x<12; x++) {
						String getData = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+slQCCodeCol)).getText();
						System.out.println(getData);
						Assert.assertEquals(getData, objModel.dataLogOutcome);

						String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+slRunTypeCol+" label")).getText();
						Assert.assertEquals(getRunType, RunType_Installation, "Run Type is not displayed in table");

						WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
						Actions builder = new Actions(Helper.driver);
						builder.moveToElement(hover).build().perform();
						Constants.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
						Helper.driver.findElement(By.id("audit-trial-"+x)).click();
						Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Constants.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(closeAudit)));
						Thread.sleep(1500);

						String getAuditQCCode = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditQCCodeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditQCCode, objModel.dataLogOutcome);

						String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+slAuditRunTypeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditRunType, RunType_Installation);
						
						if (objModel.runStartAssay) {
							ExtentVariables.steps.createNode("Verify Action as 'Modified' in Audit log");
							String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".popup-body tr")).size(), 3, "Rows in audit should be 2");
						}

						if (!objModel.runStartAssay) {
							ExtentVariables.steps.createNode("Verify Action as 'Created' in Audit log");
							String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Created");	
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".popup-body tr")).size(), 2, "Rows in audit should be 1");
						}

						Helper.getScreenshot();
						Helper.driver.findElement(By.cssSelector(closeAudit)).click();
						Thread.sleep(800);
					}
			
					Helper.getScreenshot();
					softAssert.assertAll();	
					ExtentVariables.test.pass(objModel.passStep);
					ExtentVariables.results.createNode(objModel.passStep);
					Helper.saveResult(ITestResult.SUCCESS,  null);
					Thread.sleep(1000);		
				}
			}catch(AssertionError er) {
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				Helper.saveResult(ITestResult.FAILURE,  new Exception(er));
			}catch(Exception ex){
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				Helper.saveResult(ITestResult.FAILURE,  ex);
			}
		}
	}


	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority = 3) 
	public void installationRunConfigCoccidia() throws InterruptedException, IOException {

		int z = 0;
		InstallationRunModel.lstInstallationRunCreateCoccidia = InstallationRunModel.FillDataCoccidia();
		for (InstallationRunModel objModel : InstallationRunModel.lstInstallationRunCreateCoccidia) { 
			try{
				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
				ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				ExtentVariables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				ExtentVariables.steps.createNode("1. Click on create new button next to Installation Run Config");
				SoftAssert softAssert = new SoftAssert();
				
				for (ReportFilters objFilter : objModel.lstFilters) {

					Helper.driver.get(Constants.url_piperConfiguration);			
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (Helper.driver.findElements(By.cssSelector("#installation-"+i+" td:nth-child(2)")).size() != 0) {
							if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals(InstallationRunModel.installationImprocVersionCocci)) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("edit-installation-"+i)).click();
								Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1500);
								break;
							}
						}
					}

					ExtentVariables.steps.createNode("2. Select improc name and improc version from dropdown");
					ExtentVariables.steps.createNode("3. "+objModel.steps);
					Helper.driver.findElement(By.id("MinMeanVal")).clear();
					Helper.driver.findElement(By.id("MinMeanVal")).sendKeys(objFilter.MinMean);
					Helper.driver.findElement(By.id("MaxMeanVal")).clear();
					Helper.driver.findElement(By.id("MaxMeanVal")).sendKeys(objFilter.MaxMean);
					Helper.driver.findElement(By.id("MinStdVal")).clear();
					Helper.driver.findElement(By.id("MinStdVal")).sendKeys(objFilter.MinStd);
					Helper.driver.findElement(By.id("MaxStdVal")).clear();
					Helper.driver.findElement(By.id("MaxStdVal")).sendKeys(objFilter.MaxStd);
					Helper.driver.findElement(By.id("MinStdVal")).click();
					Helper.driver.findElement(By.id("btn-save")).click();
					Thread.sleep(2000);

					////////////////////////////////////////////////////////////////////////////////////////

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

					if (objModel.runStartAssay) {
						RequestSpecification request_startAssay = RestAssured.given();
						request_startAssay.header("Content-Type", "application/json");
						request_startAssay.header("Authorization", "bearer " +token);

						HttpGet postRequest3 = new HttpGet(Constants.api_StartAssay);
						postRequest3.addHeader("Content-Type", "application/json");
						postRequest3.addHeader("Authorization", "Bearer "+token);

						json4.put("DateTime", lstStartAssayCoccidia.get(0).DateTime);
						json4.put("InstrumentId", lstStartAssayCoccidia.get(0).InstrumentID);
						json4.put("UserId", lstStartAssayCoccidia.get(0).UserID);
						json4.put("CartridgeId", lstStartAssayCoccidia.get(0).CartridgeID);
						json4.put("RunId", objModel.sampleID);
						json4.put("PathogenName", lstStartAssayCoccidia.get(0).PathogenName);				

						request_startAssay.body(json4.toString());
						Response response3 = request_startAssay.post(Constants.api_StartAssay);

						String data4 = response3.asString();
						System.out.println(data4);
						Thread.sleep(10000);
					}

					RequestSpecification request_fileupload = RestAssured.given();
					request_fileupload.header("Content-Type", "application/json");
					request_fileupload.header("Authorization", "bearer " +token);

					HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);

					json3.put("runId", lstCoccidiaIngest.get(0).runId);
					json3.put("checksum", lstCoccidiaIngest.get(0).checksum);
					json3.put("fileName", lstCoccidiaIngest.get(0).fileName);
					json3.put("fileType", lstCoccidiaIngest.get(0).fileType);
					json3.put("file", lstCoccidiaIngest.get(0).file);
					json3.put("fileJson", objModel.fileJson);				
					json3.put("Improc", lstCoccidiaIngest.get(0).improc);
					json3.put("RunMode", "2");
					json3.put("Pathogen", lstCoccidiaIngest.get(0).pathogen);

					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_FileUpload);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(75000);

					CoccidiaLogPage.openCoccidiaLogPage();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);

					ExtentVariables.steps.createNode("4. Navigate to report and search for Ingested sample id");
					Helper.driver.findElement(By.id("sampleId_show-filter")).click();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);

					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);	

					try {
						Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.sampleID+" b")).click();
					}
					catch (Exception ex) {
						ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
					}

					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);
					System.out.println(z);
					z++;

					ExtentVariables.steps.createNode("5. Verify the status in QC Code column");
					Helper.driver.findElement(By.id("sampleId_apply")).click();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);				

					for (int x=0; x<12; x++) {
						String getData = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+CoccidiaLogPage.clQCCodeCol)).getText();
						System.out.println(getData);
						Assert.assertEquals(getData, objModel.dataLogOutcome);

						String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+CoccidiaLogPage.clRunTypeCol+" label")).getText();
						Assert.assertEquals(getRunType, RunType_Installation, "Run Type is not displayed in table");

						WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
						Actions builder = new Actions(Helper.driver);
						builder.moveToElement(hover).build().perform();
						Constants.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
						Helper.driver.findElement(By.id("audit-trial-"+x)).click();
						Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Constants.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(closeAudit)));
						Thread.sleep(1500);

						String getAuditQCCode = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+CoccidiaLogPage.clAuditQCCodeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditQCCode, objModel.dataLogOutcome);

						String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+CoccidiaLogPage.clAuditRunTypeCol+".text-dark")).getText();
						softAssert.assertEquals(getAuditRunType, RunType_Installation);

						if (objModel.runStartAssay) {
							softAssert.assertEquals(Helper.driver.findElements(By.id("audit-action-1")).size(), 1);
							softAssert.assertEquals(Helper.driver.findElements(By.id("audit-action-2")).size(), 0);
						}
						else {
							softAssert.assertEquals(Helper.driver.findElements(By.id("audit-action-0")).size(), 1);
							softAssert.assertEquals(Helper.driver.findElements(By.id("audit-action-1")).size(), 0);
						}
						
						if (objModel.runStartAssay) {
							ExtentVariables.steps.createNode("Verify Action as 'Modified' in Audit log");
							String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".popup-body tr")).size(), 3, "Rows in audit should be 2");
						}
						else {
							ExtentVariables.steps.createNode("Verify Action as 'Created' in Audit log");
							String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Created");	
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".popup-body tr")).size(), 2, "Rows in audit should be 1");
						}

						Helper.getScreenshot();
						Helper.driver.findElement(By.cssSelector(closeAudit)).click();
						Thread.sleep(800);
					}
					
					Helper.getScreenshot();
					softAssert.assertAll();
					ExtentVariables.test.pass(objModel.passStep);
					ExtentVariables.results.createNode(objModel.passStep);
					Helper.saveResult(ITestResult.SUCCESS,  null);
					Thread.sleep(1000);		
				}
			}catch(AssertionError er) {
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				Helper.saveResult(ITestResult.FAILURE,  new Exception(er));
			}catch(Exception ex){
				ExtentVariables.test.fail(objModel.failStep);
				ExtentVariables.results.createNode(objModel.failStep);
				Helper.saveResult(ITestResult.FAILURE,  ex);
			}
		}
	}


	@AfterTest
	public static void endreport() {
		ExtentVariables.extent.flush();
		//	Helper.driver.close();
	}
}
