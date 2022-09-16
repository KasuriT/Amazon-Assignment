package Test.Ancera.PiperAdministration;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.PiperManagementModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;

public class PiperManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		Test_Variables.spark = new 	ExtentSparkReporter("target/Reports/Piper_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Piper Management Test Report"); 
		Helper.config();
		ConfigureLogin.login();
	}

	
	@Test(priority= 1)
	public void Navigate() throws InterruptedException, IOException {
		Test_Functions.NavigateToScreen(Constants.url_piperManagement, "PIPER Management", Constants.PiperManagementReportPath, Test_Elements.piperManagementTitle);
	}
	
	
	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Test_Functions.Lock(Test_Elements.piperManagementTable, "PIPER Management", Constants.PiperManagementReportPath, 0);
	}
	
	
	@Test (priority = 3, enabled = true) 
	public void Wildcard() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_piperManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Test_Functions.Wildcard(Test_Elements.piperManagementTable, "PIPER Management", Constants.PiperManagementReportPath, 0);
	}

	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		Test_Functions.Sorting(Test_Elements.piperManagementTable, "PIPER Management", Constants.PiperManagementReportPath);
	}
	
	
	@Test (enabled= true, priority = 5) 
	public void TestSiteFilter() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-PM-02: Verify Test Site Filter Functionality", "This test case will verify test site filter functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, "Pre_Conditions");
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, "Steps");
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, "Result");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login+ "and login with valid credentials");
			Test_Variables.preconditions.createNode("2. Hover to sidebar to expand menu");
			Test_Variables.preconditions.createNode("3. Expand Administration and click on Piper Managment");
			Test_Variables.steps.createNode("1. Verify Test Site Filter Functionality");

			SoftAssert softAssert = new SoftAssert();
			String recordBefore = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(); 
			Helper.driver.findElement(By.id("testSite_show-filter")).click();
			Thread.sleep(2000);
			List<WebElement> a = Helper.driver.findElements(By.cssSelector("th:nth-child(2) label"));
			a.get(7).click();
			Helper.driver.findElement(By.cssSelector("th:nth-child(2) .filter-popup__footer--apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Test_Variables.steps.createNode("2. Click on lock button");	
			Helper.driver.findElement(By.id(Test_Elements.LockFilter)).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);
			String recordsafterfilter = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
			softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");
			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			softAssert.assertEquals(Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(), recordsafterfilter, "Lock functionality failed");
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(Test_Elements.UnlockFilter)).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Helper.driver.findElement(By.cssSelector("th:nth-child(2) .log-header__clear-filter span")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			softAssert.assertAll();
			Test_Variables.test.pass("Test Site Filter verified successfully");
			Test_Variables.results.createNode("Test Site Filter verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Test Site filter failed to verify");
			Test_Variables.results.createNode("Test Site filter failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Test Site filter failed to verify");
			Test_Variables.results.createNode("Test Site filter failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperManagementReportPath, ex);
		}	
	}
	
	

	@Test (description="Test Case: All basic scenarios",enabled= true, priority = 6) 
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
			SoftAssert softAssert = new SoftAssert();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			String piperName = Helper.driver.findElement(By.cssSelector("#"+Test_Elements.piperManagementTable+" #row-0 #col-0 label")).getText();
			
			WebElement scroll = Helper.driver.findElement(By.id("#edit-piper-1 img"));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#edit-piper-1 img")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			softAssert.assertEquals(Helper.driver.findElement(By.id("descId")).getText(), piperName, "Piper Name does not appear in text field");
			Helper.driver.findElement(By.cssSelector(".b-md:nth-child(1)")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Helper.driver.findElement(By.id("btn-show-tree")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector(".popup-heading")).isDisplayed());
			
			Helper.driver.findElement(By.cssSelector("#orgTypeId .ng-arrow-wrapper")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			if (Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1)")).getText() == "Ancera" && Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(2)")).getText() == "Client" && Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(3)")).getText() == "Partner" && Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(4)")).getText() == "Consumer") {
				Assert.assertTrue(true, "Organization types not displaying in dropdown");
			}
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Helper.driver.findElement(By.id("btn-cancel-sites")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			Helper.driver.findElement(By.cssSelector("//*[text() = ' Submit ']")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Testing sites details updated successfully.");
			softAssert.assertAll();
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
	
	
	@Test (enabled= true, priority = 7) 
	public void AlertSetting() throws InterruptedException, IOException {

		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
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
				SoftAssert softAssert = new SoftAssert();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(2000);
				Helper.driver.findElement(By.xpath("//button[contains(text(),'Alert Setting')]")).click();
		
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Thread.sleep(2000);
						Helper.driver.findElement(By.id("programNameId")).clear();
						Helper.driver.findElement(By.id("programNameId")).sendKeys(objModel.emailList);
						Helper.driver.findElement(By.id("num-descId")).clear();
						Helper.driver.findElement(By.id("num-descId")).sendKeys(objModel.hoursList);
						Thread.sleep(2000);
						Helper.driver.findElement(By.id("btn-save")).click();
						if (objModel.negativeScenario) {
						for (int i =0; i<objFilter.LstFilterValues.size(); i++) {
							
							softAssert.assertEquals(Helper.driver.findElements(By.xpath(objFilter.LstFilterValues.get(i))).size(), 1);	
						}

						Helper.driver.findElement(By.id("close-popup-modal")).click();
						Thread.sleep(2000);
						}
						
						if (objModel.positiveScenario) {
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
							Thread.sleep(1000);
							softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Alert Settings details updated.");
						}

						softAssert.assertAll();
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
		Helper.driver.close();
	}
}
