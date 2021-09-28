package Test.Ancera.Administration;

import java.io.IOException;

import java.util.ArrayList;


import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.AlertManagementModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AlertManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Alert_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Agreement Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Alert Management Screen",enabled= true, priority = 1) 
	public void NavigateAlertManagement() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-01: Verify user can navigate to Alert Management screen", "This test case will verify that user can navigate to Alert Management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Alert Management", Constants.AlertManagementReportPath));
			Helper.driver.get(Constants.url_alert);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Alert Management")));
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Administration and select Alert Management");

			Assert.assertEquals(Helper.driver.findElement(By.id("Alert Management")).getText(), "Alert Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Alert Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AlertManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AlertManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Alert Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Alert Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Login To Email",enabled= true, priority = 2) 
	public void EmailLogin() throws InterruptedException, IOException {
		try {

			((JavascriptExecutor)Helper.driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(Helper.driver.getWindowHandles());
			Helper.driver.switchTo().window(tabs.get(1));

			Helper.driver.get(Constants.url_GmailSignin);   
			Thread.sleep(1500);

			if (Helper.driver.findElements(By.id("identifierId")).size() != 0) {
				Helper.driver.findElement(By.id("identifierId")).sendKeys(Test_Variables.forgotPassword_email);    
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);	
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.name("password")).sendKeys(Test_Variables.forgotPassword_password);
			Helper.driver.findElement(By.name("password")).sendKeys(Keys.ENTER);	
			Thread.sleep(6000);
			System.out.println(Helper.driver.findElements(By.id(("headingText"))).size());
			
			if (Helper.driver.findElements(By.id(("headingText"))).size() !=  0) {
				Helper.driver.findElement(By.cssSelector((".vxx8jf"))).click();
				Thread.sleep(2000);
				Helper.driver.findElement(By.id("knowledge-preregistered-email-response")).sendKeys(Test_Variables.forgotPasswordSecurityEmail);
				Helper.driver.findElement(By.id("knowledge-preregistered-email-response")).sendKeys(Keys.ENTER);
			}

			Thread.sleep(2000);
			ArrayList<String> tabs2 = new ArrayList<String> (Helper.driver.getWindowHandles());
			Helper.driver.switchTo().window(tabs2.get(0));
			Thread.sleep(2000);	
		}
		catch(Exception ex) {

		}

	}


	@SuppressWarnings({ "unused", "unchecked" })
	@Test (enabled= false, priority = 2) 
	public void CreateAlert() throws InterruptedException, IOException {

		Test_Variables.lstAlertCreate = AlertManagementModel.FillData();
		for (AlertManagementModel objModel : Test_Variables.lstAlertCreate) { 
			try{
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Navigate to Alert Management screen");
				Test_Variables.steps.createNode("1. Click on create new button");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {

						for (int i=2; i <=Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
							if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).size() != 0) {
								if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).getText().equals(objModel.AlertDesc)) {
									Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).click();
									Thread.sleep(1500);
									WebElement filter_scroll = Helper.driver.findElement(By.id("duplicate-alert"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
									Thread.sleep(1000);
									if (Helper.driver.findElements(By.id("activate-alert")).size() == 1) {
										Helper.driver.findElement(By.id("activate-alert")).click();
									}
									else {
										Helper.driver.findElement(By.id("close-duplicate-modal")).click();
									}
								}
							}
						}

						List<WebElement> rows = Helper.driver.findElements(By.cssSelector("td:nth-child(6)"));
						List<String> value = new ArrayList<String>();

						for (int j=0; j<rows.size(); j++){
							//System.out.println(rows.get(j).getText());
							value.add(rows.get(j).getText());
						}
						if (value.contains(objModel.AlertDesc)){
							System.out.println("Value found");
						}
						else{
							//System.out.println("Not Found");
							Helper.driver.findElement(By.id("create-alert")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys("Ancera");
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("div[class='form-control form-control-inv']")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector(".d-inline-block ")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("list-title_apply")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("alert-info-input")).sendKeys(objModel.AlertInfo);
							Helper.driver.findElement(By.id("alert-desc-input")).sendKeys(objModel.AlertDesc);
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("btn-next")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(objModel.DataSource);
							Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(Keys.ENTER);

							if (Helper.driver.findElements(By.cssSelector("#reportId input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(objModel.Report);
								Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(Keys.ENTER);
							}

							if (Helper.driver.findElements(By.cssSelector("#fieldId input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(objModel.AlertVariable);
								Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(Keys.ENTER);
							}

							if (Helper.driver.findElements(By.cssSelector("#alertModesId input")).size() != 0 ) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(objModel.AlertMode);
								Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(Keys.ENTER);	  //threshold
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.cssSelector("#thresholdConditionId input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(objModel.Condition);
								Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("minId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("minId")).sendKeys(objModel.minValue);
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("maxId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("maxId")).sendKeys(objModel.maxValue);
							}

							Thread.sleep(1000);
							System.out.println("adasd");
							if (Helper.driver.findElements(By.id("aggregateModeId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#aggregateModeId input")).sendKeys(objModel.AggregateMode);
								Helper.driver.findElement(By.cssSelector("#aggregateModeId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("aggregeateConditionId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(objModel.Condition);
								Helper.driver.findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("alert-aggregate-days-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-aggregate-days-input")).sendKeys(objModel.Text);
							}

							if (Helper.driver.findElements(By.id("absenceDelayFactorId-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("absenceDelayFactorId-input")).sendKeys(objModel.Deviation);
							}

							if (Helper.driver.findElements(By.id("alert-deviation-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-deviation-input")).sendKeys(objModel.Deviation);	
							}

							if (Helper.driver.findElements(By.id("notifyEveryId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#notifyEveryId input")).sendKeys(objModel.Notify);
								Helper.driver.findElement(By.cssSelector("#notifyEveryId input")).sendKeys(Keys.ENTER);
							}

							if (Helper.driver.findElements(By.id("alert-custom-days-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-custom-days-input")).sendKeys(objModel.customDays);
							}

							if (Helper.driver.findElements(By.id("alert-duration-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-duration-input")).sendKeys(objModel.Text);
							}					

							if (Helper.driver.findElements(By.id("absenceDelayFactorId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#absenceDelayFactorId input")).sendKeys(objModel.Days);
								Helper.driver.findElement(By.cssSelector("#absenceDelayFactorId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							Helper.driver.findElement(By.id("btn-next")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("btn-finish")).click();
							Thread.sleep(1000);
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
							Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "New alert configuration created.");
							Thread.sleep(1000);
						}

						Test_Variables.test.pass("User was able to create alert successfully");
						Test_Variables.results.createNode("User was able to create alert successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.AlertManagementReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.AlertManagementReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("User failed to create alert");
						Test_Variables.results.createNode("User failed to create alert");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("User failed to create alert");
						Test_Variables.results.createNode("User failed to create alert");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, ex);
					}	
				}

				if (objModel.isGenerate) {
				RequestSpecification request = RestAssured.given();
				request.header("Content-Type", "application/json");
				JSONObject json = new JSONObject();   
				json.put("piperid", Test_Variables.piperId);
				json.put("password", Test_Variables.piperPassword);
				json.put("DISAPIVersion", "14.13");
				request.body(json.toString());
				Response response = request.post(Constants.api_login);
				int code = response.getStatusCode();

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
				Thread.sleep(1000);
				}

				ArrayList<String> tabs2 = new ArrayList<String> (Helper.driver.getWindowHandles());
				Helper.driver.switchTo().window(tabs2.get(1));
				Thread.sleep(2000);	

				Helper.driver.navigate().refresh();
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
				Thread.sleep(2000);
				List<WebElement> a = Helper.driver.findElements(By.xpath("//*[@class='yW']/span"));
				for(int i=0;i<a.size();i++){
					if(a.get(i).getText().equals("Ancera Alert Center")){  
						Assert.assertTrue(a.get(i).getText().equals("Ancera Alert Center"));
						a.get(i).click();
					}
				}

				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();

				Helper.driver.switchTo().window(tabs2.get(0));
				Thread.sleep(2000);	
			}	
			catch(Exception ex) {
			}
		}
	}



	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}
