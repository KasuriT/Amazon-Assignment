package Test.Ancera.MetaData;

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

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class PoultryManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Poultry_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Poultry Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	
	@Test (description="Test Case: Navigate to Poultry Management Screen",enabled= true, priority = 1) 
	public void NavigatePoultryManagement() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-01: Verify user can navigate to Poultry Management screen", "This test case will verify that user can navigate to Poultry Management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.driver.get(Constants.url_poultryManagement);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on MetaData and select Poultry Management");

			Assert.assertEquals(Helper.driver.findElement(By.id("Logging and Management")).getText(), "Logging and Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Poultry Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Poultry Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Poultry Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}
	
	
	@Test (description="Test Case: Verify table Content",enabled= true, priority = 2) 
	public void VerifyTableContent() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-02: Verify table contents", "This test case will verify table contents");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.preconditions.createNode("1. Verify table headings");
					
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#user-grid th:nth-child(2) label")).getText(), "Management Type"); 
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#user-grid th:nth-child(3) label")).getText(), "Start Date"); 
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#user-grid th:nth-child(4) label")).getText(), "End Date"); 
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#user-grid th:nth-child(5) label")).getText(), "Notes"); 
			Test_Variables.test.pass("Table contents verified successfully");
			Test_Variables.results.createNode("Table contents verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Table contents failed to verify");
			Test_Variables.results.createNode("Table contents failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Table contents failed to verify");
			Test_Variables.results.createNode("Table contents failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}
	
	@Test (description="Test Case: Verify Filter",enabled= true, priority = 3) 
	public void VerifyFilter() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-03: Verify table contents", "This test case will verify table contents");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.preconditions.createNode("1. Verify table headings");

			Helper.driver.findElement(By.cssSelector("#user-grid th:nth-child(1)")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".ulModalList li:nth-child(3)")).click();
			
		 int a = Helper.driver.findElements(By.cssSelector("td:nth-child(2) label")).size();
			
			for (int i=1; i<a; i++) {
				
				Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#treatment-"+i+" td:nth-child(2) label")).getText(),  "Feed");
			}

			Test_Variables.test.pass("Table contents verified successfully");
			Test_Variables.results.createNode("Table contents verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Table contents failed to verify");
			Test_Variables.results.createNode("Table contents failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Table contents failed to verify");
			Test_Variables.results.createNode("Table contents failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}

	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//		Helper.driver.close();
	}
}
