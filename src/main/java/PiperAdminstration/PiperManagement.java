package PiperAdminstration;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.PiperManagementModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class PiperManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new 	ExtentSparkReporter("target/Reports/Piper_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Piper Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}

	
	@Test (description="Test Case: Navigate to Piper Management Screen",enabled= true, priority = 1) 
	public void NavigatePM() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-PM-01: Verify user can navigate to Piper Management Screen", "This test case will verify that user can navigate to Piper Managament screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, "Pre_Conditions");
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, "Steps");
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, "Result");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));

			Helper.driver.get(Constants.url_piperManagement);
			Thread.sleep(2000);
			String actual = Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText();
			String expected = "PIPER Management";
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login+ "and login with valid credentials");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand menu");
			Test_Variables.steps.createNode("2. Expand Administration and click on Piper Managment");

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully to Piper Management screen");
			Test_Variables.results.createNode("User navigates to Piper Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("User did not navigated to Piper Management screen");
			Test_Variables.results.createNode("User did not navigate to Piper Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User did not navigated to Piper Management screen");
			Test_Variables.results.createNode("User did not navigate to Piper Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, ex);
		}	
	}
	

	@Test (description="Test Case: All basic scenarios",enabled= true, priority = 2) 
	public void BasicScenarios() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-PM-02: Verify user can view sites", "This test case will verify that user can view sites");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, "Pre_Conditions");
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, "Steps");
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, "Result");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login+ "and login with valid credentials");
			Test_Variables.preconditions.createNode("2. Hover to sidebar to expand menu");
			Test_Variables.preconditions.createNode("3. Expand Administration and click on Piper Managment");
			Test_Variables.steps.createNode("1. Open configure piper popop");
			Test_Variables.steps.createNode("2. Verify sites hierarcy");
			Test_Variables.steps.createNode("3. Verify oranization appears in dropdown");
			Test_Variables.steps.createNode("4. Verify user can save the settings");
			
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("piper-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.findElement(By.cssSelector(".b-md:nth-child(1)")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.findElement(By.id("btn-show-tree")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector(".popup-heading")).isDisplayed());
			
			Helper.driver.findElement(By.cssSelector("#orgTypeId .ng-arrow-wrapper")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			if (Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1)")).getText() == "Ancera" && Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(2)")).getText() == "Client" && Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(3)")).getText() == "Partner" && Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(4)")).getText() == "Consumer") {
				Assert.assertTrue(true);
			}
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.findElement(By.cssSelector(".p-point-625 #btn-ok-sites")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".btn-ok#btn-ok-sites")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Testing sites details updated successfully.");
			
			Test_Variables.test.pass("Sites and dropdowns verified successfully");
			Test_Variables.results.createNode("Sites and dropdowns verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Sites and dropdowns failed to verify");
			Test_Variables.results.createNode("Sites and dropdowns failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Sites and dropdowns failed to verify");
			Test_Variables.results.createNode("Sites and dropdowns failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority = 2) 
	public void AlertSetting() throws InterruptedException, IOException {

		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Test_Variables.lstPiperManagementCreate = PiperManagementModel.FillData();
		for (PiperManagementModel objModel : Test_Variables.lstPiperManagementCreate) { 
			try{
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, "Pre_Conditions");
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, "Steps");
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, "Result");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login+ "and login with valid credentials");
				Test_Variables.preconditions.createNode("2. Hover to sidebar to expand menu");
				Test_Variables.preconditions.createNode("3. Expand Administration and click on Piper Managment");
				Test_Variables.steps.createNode("1. Open Alert Setting popup");
				Test_Variables.steps.createNode(objModel.passStep);

				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(2000);
				Helper.driver.findElement(By.xpath("//button[contains(text(),'Alert Setting')]")).click();
		
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Thread.sleep(2000);
						Helper.driver.findElement(By.id("emailsListId")).clear();
						Helper.driver.findElement(By.id("emailsListId")).sendKeys(objModel.emailList);
						Helper.driver.findElement(By.id("ThresholdTimeId")).clear();
						Helper.driver.findElement(By.id("ThresholdTimeId")).sendKeys(objModel.hoursList);
						Thread.sleep(2000);
						Helper.driver.findElement(By.id("btn-save")).click();
						if (objModel.negativeScenario) {
						for (int i =0; i<objFilter.LstFilterValues.size(); i++) {
							
							Assert.assertEquals(Helper.driver.findElements(By.xpath(objFilter.LstFilterValues.get(i))).size(), 1);	
						}

						Helper.driver.findElement(By.id("close-popup-modal")).click();
						Thread.sleep(2000);
						}
						
						if (objModel.positiveScenario) {
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
							Thread.sleep(1000);
							Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Alert Settings details updated.");
						}

						Test_Variables.test.pass(objModel.passStep);
						Test_Variables.results.createNode(objModel.passStep);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperManagementReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail(objModel.failStep);
						Test_Variables.results.createNode(objModel.failStep);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objModel.failStep);
						Test_Variables.results.createNode(objModel.failStep);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, ex);
					}
				}
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
