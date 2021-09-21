package Test.Ancera.Reports;

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

public class InstallationRun_Listeria {

	
	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Installation_Run"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Installation Run Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}

	
	@SuppressWarnings("unchecked")
	@Test (enabled= true, priority = 1) 
	public void installationRunConfigListeria() throws InterruptedException, IOException {

		int z = 0;
		Test_Variables.lstInstallationRunCreate = InstallationRunModel.FillDataListeria();
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
							if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(2) label")).getText().equals(Test_Variables.installationImprocVersionListeria)) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("edit-installation-"+i)).click();
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);
								break;
							}
						}
					}
					
					Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");
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

					json1.put("runId", Test_Variables.AnnouncementRunID);
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
					if (objModel.runStartAssay) {
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
						json4.put("PathogenName", "Listeria");				

						request_startAssay.body(json4.toString());
						Response response3 = request_startAssay.post(Constants.api_StartAssay);

						String data4 = response3.asString();
						System.out.println(data4);		
						Thread.sleep(60000);
					}
					///////////////////////////////////////////////////////////////////////	

					
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
					json3.put("RunMode", "2");
					json3.put("Pathogen", "Listeria");


					request_fileupload.body(json3.toString());
					Response response2 = request_fileupload.post(Constants.api_FileUpload);

					String data3 = response2.asString();
					System.out.println(data3);

					JsonPath jsonPathEvaluator1 = response.jsonPath();
					jsonPathEvaluator1.get("statusCode");
					Thread.sleep(45000);

					Helper.driver.get(Constants.url_SalmonellaLog);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);

					Test_Variables.steps.createNode("4. Navigate to report and search for Ingested sample id");
					Helper.driver.findElement(By.id("sampleId_show-filter")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);

					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objModel.sampleID);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);	
					ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objModel.sampleID);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);
					System.out.println(z);
					z++;

					Test_Variables.steps.createNode("5. Verify the status in QC Code column");
					Helper.driver.findElement(By.id("sampleId_apply")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);					

					for (int x=0; x<12; x++) {
						String getData = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slQCCodeCol)).getText();
						System.out.println(getData);
						Assert.assertEquals(getData, objModel.dataLogOutcome);

						String getRunType = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slRunTypeCol+" label")).getText();
						Assert.assertEquals(getRunType, "Installation", "Run Type is not displayed in table");

		//				String getTestSiteID = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slTestSiteIDCol+" label")).getText();
		//				Assert.assertTrue(getTestSiteID.isEmpty() == false, "Test Site ID is not dislayed in table");

		//				String getTestSiteName = Helper.driver.findElement(By.cssSelector("#row-"+x+" #col-"+Test_Elements.slTestSiteNameCol+" label")).getText();
		//				Assert.assertTrue(getTestSiteName.isEmpty() == false, "Test Site Name is not dislayed in table");	

						WebElement hover = Helper.driver.findElement(By.id("audit-trial-"+x));
						Actions builder = new Actions(Helper.driver);
						builder.moveToElement(hover).build().perform();
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("audit-trial-"+x)));
						Helper.driver.findElement(By.id("audit-trial-"+x)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".u-report-modal-close-icon")));
						Thread.sleep(1500);

			//			String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteIDCol+".text-dark")).getText();
			//			Assert.assertTrue(getAuditTestSiteId.isEmpty() == false);

			//			String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditTestSiteNameCol+".text-dark")).getText();
			//			Assert.assertTrue(getAuditTestSiteName.isEmpty() == false);

						String getAuditQCCode = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditQCCodeCol+".text-dark")).getText();
						Assert.assertEquals(getAuditQCCode, objModel.dataLogOutcome);
						
						String getAuditRunType = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.slAuditRunTypeCol+".text-dark")).getText();
						Assert.assertEquals(getAuditRunType, "Installation");
						
		//				String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
		//				Assert.assertEquals(getAuditAction, "Created");
						
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Installation Run", Constants.InstallationRunReportPath));
						Helper.driver.findElement(By.cssSelector(".u-report-modal-close-icon")).click();
						Thread.sleep(800);
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
