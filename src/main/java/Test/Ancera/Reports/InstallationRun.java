package Test.Ancera.Reports;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
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

import Models.InstallationRunModel;
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

public class InstallationRun {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Installation_Run"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Installation Run Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	
	@SuppressWarnings("unchecked")
	@Test (enabled= false, priority = 2) 
	public void installationRunConfigSalmonella() throws InterruptedException, IOException {

		int z = 0;
		Test_Variables.lstInstallationRunCreate = InstallationRunModel.FillData();
		for (InstallationRunModel objModel : Test_Variables.lstInstallationRunCreate) { 
			try{
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
				
				for (ReportFilters objFilter : objModel.lstFilters) {
					
					Helper.driver.get(Constants.url_piperConfiguration);			
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (Helper.driver.findElements(By.cssSelector("#installation-"+i+" td:nth-child(2)")).size() != 0) {
							if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals("4.0.8.2")) {
								Thread.sleep(1000);
								WebElement filter_scroll = Helper.driver.findElement(By.id("edit-installation-"+i));
								((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
								Helper.driver.findElement(By.id("delete-installation-"+i)).click();
								Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
								Helper.driver.findElement(By.id("btn-yes")).click();

								Helper.driver.navigate().refresh();	
								Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-installation-run")));
								break;
							}
						}
					}

					Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");
					Helper.driver.findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys("Salmonella");
					Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
					Helper.driver.findElement(By.id("create-installation-run")).click();
					Thread.sleep(2000);
					Helper.driver.findElement(By.cssSelector("#ImprocName .ng-arrow-wrapper")).click();
					Helper.driver.findElement(By.cssSelector("#ImprocName input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#ImprocVersion .ng-arrow-wrapper")).click();
					Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys("4.0.8.2");
					Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Keys.ENTER);	
					Thread.sleep(1000);

					Test_Variables.steps.createNode("3. "+objModel.steps);
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

					///////////////////////////////////////////////////////////////////////////
					
					RequestSpecification request_startAssay = RestAssured.given();

					request_startAssay.header("Content-Type", "application/json");
					request_startAssay.header("Authorization", "bearer " +token);

					HttpGet postRequest3 = new HttpGet(Constants.api_StartAssay);
					postRequest3.addHeader("Content-Type", "application/json");
					postRequest3.addHeader("Authorization", "Bearer "+token);

					json4.put("DateTime", Test_Variables.lstStartAssay.get(0).DateTime);
					json4.put("InstrumentId", Test_Variables.lstStartAssay.get(0).InstrumentID);
					json4.put("UserId", Test_Variables.lstStartAssay.get(0).UserID);
					json4.put("CartridgeId", Test_Variables.lstStartAssay.get(0).CartridgeID);
					json4.put("RunId", Test_Variables.lstStartAssay.get(0).RunID);
					json4.put("PathogenName", Test_Variables.lstStartAssay.get(0).PathogenName);				

					request_startAssay.body(json4.toString());
					Response response3 = request_startAssay.post(Constants.api_StartAssay);

					String data4 = response3.asString();
					System.out.println(data4);
							
					///////////////////////////////////////////////////////////////////////	
					
					Thread.sleep(2000);
					RequestSpecification request_fileupload = RestAssured.given();

					request_fileupload.header("Content-Type", "application/json");
					request_fileupload.header("Authorization", "bearer " +token);

					HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);

					json3.put("runId", Test_Variables.lstInstallationRunIngest.get(z).runId);
					json3.put("checksum", Test_Variables.lstInstallationRunIngest.get(z).checksum);
					json3.put("fileName", Test_Variables.lstInstallationRunIngest.get(z).fileName);
					json3.put("fileType", Test_Variables.lstInstallationRunIngest.get(z).fileType);
					json3.put("file", Test_Variables.lstInstallationRunIngest.get(z).file);
					json3.put("fileJson", Test_Variables.lstInstallationRunIngest.get(z).fileJson);				
					json3.put("Improc", Test_Variables.lstInstallationRunIngest.get(z).improc);
					json3.put("RunMode", Test_Variables.lstInstallationRunIngest.get(z).runMode);
					System.out.println(z);
					z++;
					
					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_FileUpload);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(1000);

					Helper.driver.get(Constants.url_SalmonellaLog);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
					Thread.sleep(3000);

					Test_Variables.steps.createNode("4. Navigate to report and search for Ingested sample id");
					ClickElement.clickByCss(Helper.driver, "#sort-sampleId .log-header__filter-icon");
					Thread.sleep(2000);

					Helper.driver.findElement(By.xpath("//*[@id=\"sort-sampleId\"]/div[1]/app-custom-filter-popup/div/div/div[3]/span[2]")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);
					
					Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).clear();
					Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).sendKeys("Test"+Test_Variables.lstSampleID.get(0));
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);						

	
					Helper.driver.findElement(By.id("Test"+Test_Variables.lstSampleID.get(0))).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);
					
					Test_Variables.steps.createNode("5. Verify the status in QC Code column");
					Helper.driver.findElement(By.cssSelector("#sort-sampleId .filter-popup__action--apply")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

					for (int x=0; x<12; x++) {
						String getData = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-2")).getText();
						System.out.println(getData);
						Assert.assertEquals(getData, objModel.dataLogOutcome);
						
						if(objModel.checkAudit) {
							WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
							Actions builder = new Actions(Helper.driver);
							builder.moveToElement(hover).build().perform();
							Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
							Helper.driver.findElement(By.id("audit-trial-"+x)).click();
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-runId")));
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Installation Run", Constants.InstallationRunReportPath));
							Thread.sleep(1000);
							Assert.assertTrue(Helper.driver.findElement(By.id("audit-action-7")).isDisplayed());
							Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
							Thread.sleep(800);
						}
					}
		
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Installation Run", Constants.InstallationRunReportPath));
					Test_Variables.test.pass(objModel.passStep);
					Test_Variables.results.createNode(objModel.passStep);
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.InstallationRunReportPath, null);
					Thread.sleep(1000);		
				}
			}catch(AssertionError er) {
				Test_Variables.test.fail(objModel.failStep);
				Test_Variables.results.createNode(objModel.failStep);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.InstallationRunReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail(objModel.failStep);
				Test_Variables.results.createNode(objModel.failStep);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.InstallationRunReportPath, ex);
			}
		}
	}
	
	
	@Test (enabled= true, priority = 2) 
	public void installationRunConfigPractice() throws InterruptedException, IOException {
	
		Helper.driver.get(Constants.url_SalmonellaLog);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
		Thread.sleep(3000);

		Test_Variables.steps.createNode("4. Navigate to report and search for Ingested sample id");
		ClickElement.clickByCss(Helper.driver, "#sort-sampleId .log-header__filter-icon");
		Thread.sleep(2000);

		Helper.driver.findElement(By.xpath("//*[@id=\"sort-sampleId\"]/div[1]/app-custom-filter-popup/div/div/div[3]/span[2]")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(3000);
		
		Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).clear();
		Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).sendKeys("TestAutomation12749");
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(3000);						


		Helper.driver.findElement(By.id("TestAutomation12749")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);
		
		Test_Variables.steps.createNode("5. Verify the status in QC Code column");
		Helper.driver.findElement(By.cssSelector("#sort-sampleId .filter-popup__action--apply")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		
		for (int x=0; x<12; x++) {
			String getData = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-2")).getText();
			System.out.println(getData);
			Assert.assertEquals(getData, "PASS");
			
			
				WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
				Actions builder = new Actions(Helper.driver);
				builder.moveToElement(hover).build().perform();
				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
				Helper.driver.findElement(By.id("audit-trial-"+x)).click();
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-runId")));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Installation Run", Constants.InstallationRunReportPath));
				Thread.sleep(1000);
				Assert.assertTrue(Helper.driver.findElement(By.id("audit-action-7")).isDisplayed());
				Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
				
			
		}
		
	}
	
	
	
	

	
	
	@SuppressWarnings("unchecked")
	@Test (enabled= false, priority = 3) 
	public void installationRunConfigCoccidia() throws InterruptedException, IOException {

		int z = 0;
		Test_Variables.lstInstallationRunCreateCoccidia = InstallationRunModel.FillDataCoccidia();
		for (InstallationRunModel objModel : Test_Variables.lstInstallationRunCreateCoccidia) { 
			try{

				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
				
				for (ReportFilters objFilter : objModel.lstFilters) {
					
					Helper.driver.get(Constants.url_piperConfiguration);			
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);

					for (int i = 1; i<=100; i++) {
						if (Helper.driver.findElements(By.cssSelector("#installation-"+i+" td:nth-child(2)")).size() != 0) {
							if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals("6.2.1.0")) {
								Thread.sleep(1000);
								WebElement filter_scroll = Helper.driver.findElement(By.id("edit-installation-"+i));
								((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
								Helper.driver.findElement(By.id("delete-installation-"+i)).click();
								Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
								Helper.driver.findElement(By.id("btn-yes")).click();

								Helper.driver.navigate().refresh();	
								Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-installation-run")));
								break;
							}
						}
					}

					Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");
					Helper.driver.findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys("Coccidia");
					Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
					Helper.driver.findElement(By.id("create-installation-run")).click();
					Thread.sleep(2000);
					Helper.driver.findElement(By.cssSelector("#ImprocName .ng-arrow-wrapper")).click();
					Helper.driver.findElement(By.cssSelector("#ImprocName input")).sendKeys("ImprocCocc01");
					Helper.driver.findElement(By.cssSelector("#ImprocName input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#ImprocVersion .ng-arrow-wrapper")).click();
					Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys("6.2.1.0");
					Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Keys.ENTER);	
					Thread.sleep(1000);

					Test_Variables.steps.createNode("3. "+objModel.steps);
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

					///////////////////////////////////////////////////////////////////////////
					
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
							
					///////////////////////////////////////////////////////////////////////	
					
					Thread.sleep(2000);
					RequestSpecification request_fileupload = RestAssured.given();

					request_fileupload.header("Content-Type", "application/json");
					request_fileupload.header("Authorization", "bearer " +token);

					HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);

					json3.put("runId", Test_Variables.lstInstallationRunIngestCoccidia.get(z).runId);
					json3.put("checksum", Test_Variables.lstInstallationRunIngestCoccidia.get(z).checksum);
					json3.put("fileName", Test_Variables.lstInstallationRunIngestCoccidia.get(z).fileName);
					json3.put("fileType", Test_Variables.lstInstallationRunIngestCoccidia.get(z).fileType);
					json3.put("file", Test_Variables.lstInstallationRunIngestCoccidia.get(z).file);
					json3.put("fileJson", Test_Variables.lstInstallationRunIngestCoccidia.get(z).fileJson);				
					json3.put("Improc", Test_Variables.lstInstallationRunIngestCoccidia.get(z).improc);
					json3.put("RunMode", Test_Variables.lstInstallationRunIngestCoccidia.get(z).runMode);
					System.out.println(z);
					z++;
					
					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_FileUpload);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(1000);

					Helper.driver.get(Constants.url_CoccidiaLog);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
					Thread.sleep(3000);

					Test_Variables.steps.createNode("4. Navigate to report and search for Ingested sample id");
					ClickElement.clickByCss(Helper.driver, "#sort-sampleId .log-header__filter-icon");
					Thread.sleep(2000);

					Helper.driver.findElement(By.xpath("//*[@id=\"sort-sampleId\"]/div[1]/app-custom-filter-popup/div/div/div[3]/span[2]")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);
					
					Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).clear();
					Helper.driver.findElement(By.cssSelector("#sort-sampleId .form-control")).sendKeys("Test"+Test_Variables.lstSampleID.get(0));
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);						

	
					Helper.driver.findElement(By.id("Test"+Test_Variables.lstSampleID.get(0))).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);
					
					Test_Variables.steps.createNode("5. Verify the status in QC Code column");
					Helper.driver.findElement(By.cssSelector("#sort-sampleId .filter-popup__action--apply")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

					for (int x=0; x<12; x++) {
						String getData = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-2")).getText();
						System.out.println(getData);
						Assert.assertEquals(getData, objModel.dataLogOutcome);
						
						if(objModel.checkAudit) {
							WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
							Actions builder = new Actions(Helper.driver);
							builder.moveToElement(hover).build().perform();
							Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
							Helper.driver.findElement(By.id("audit-trial-"+x)).click();
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-runId")));
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Installation Run", Constants.InstallationRunReportPath));
							Thread.sleep(1000);
							Assert.assertTrue(Helper.driver.findElement(By.id("audit-action-7")).isDisplayed());
							Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
							Thread.sleep(800);
						}
					}
		
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Installation Run", Constants.InstallationRunReportPath));
					Test_Variables.test.pass(objModel.passStep);
					Test_Variables.results.createNode(objModel.passStep);
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.InstallationRunReportPath, null);
					Thread.sleep(1000);		
				}
			}catch(AssertionError er) {
				Test_Variables.test.fail(objModel.failStep);
				Test_Variables.results.createNode(objModel.failStep);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.InstallationRunReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail(objModel.failStep);
				Test_Variables.results.createNode(objModel.failStep);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.InstallationRunReportPath, ex);
			}
		}
	}
	
	

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}


}
