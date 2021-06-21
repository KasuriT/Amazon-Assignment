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

			Test_Variables.steps.createNode("1. Verify table headings");

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

		for (int j = 0; j<Test_Variables.lstPoultryfilter.size(); j++) {
			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstPoultryfilter.get(j).testCaseTitle, Test_Variables.lstPoultryfilter.get(j).testCaseDesc);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

				Test_Variables.steps.createNode("1. Apply filter");

				Helper.driver.findElement(By.cssSelector("#user-grid th:nth-child(1)")).click();
				Thread.sleep(1000);
				Helper.driver.findElement(By.cssSelector(".ulModalList li:nth-child("+Test_Variables.lstPoultryfilter.get(j).filter+")")).click();

				int a = Helper.driver.findElements(By.cssSelector("td:nth-child(2) label")).size();

				for (int i=1; i<a; i++) {
					Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#treatment-"+i+" td:nth-child(2) label")).getText(),  Test_Variables.lstPoultryfilter.get(j).text);
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
	}


	@Test (enabled= true, priority = 4) 
	public void CreateFeed() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-07: Verify user can create feed intervention", "This test case will verify that user can create feed intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.steps.createNode("1. Select site and click on create new button; select feed from radio button");
			Test_Variables.steps.createNode("1. Enter valid data in all mandatory fields and click on save button");
			Helper.driver.findElement(By.id("site-1")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-treatment")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));

			Helper.driver.findElement(By.id("feed")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("feedStartDatebtn")).click();
			Helper.driver.findElement(By.xpath(Test_Elements.poultryStartDateSelect)).click();

			Helper.driver.findElement(By.id("btn-save-feed")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Feed details saved successfully");
			Test_Variables.test.pass("Feed details saved successfully");
			Test_Variables.results.createNode("Feed details saved successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Feed details failed to save");
			Test_Variables.results.createNode("Feed details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Feed details failed to save");
			Test_Variables.results.createNode("Feed details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 5) 
	public void UpdateFeed() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-08: Verify user can update feed intervention", "This test case will verify that user can update feed intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create feed intervention");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.steps.createNode("1. Click on edit icon next to created feed and update it");

			Helper.driver.findElement(By.id("edit-treatment-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("WD1Day")).sendKeys("100");;
			Helper.driver.findElement(By.id("btn-save-feed")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Feed details saved successfully");
			Test_Variables.test.pass("Feed details updated successfully");
			Test_Variables.results.createNode("Feed details updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Feed details failed to update");
			Test_Variables.results.createNode("Feed details failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Feed details failed to update");
			Test_Variables.results.createNode("Feed details failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 6) 
	public void DeleteFeed() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-09: Verify user can delete feed intervention", "This test case will verify that user can update delete intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create feed intervention");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.preconditions.createNode("1. Click on delete icon next to created feed and delete it");

			Helper.driver.findElement(By.id("delete-treatment-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-yes")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Feed details deleted.");
			Test_Variables.test.pass("Feed details deleted successfully");
			Test_Variables.results.createNode("Feed details deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Feed details failed to delete");
			Test_Variables.results.createNode("Feed details failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Feed details failed to delete");
			Test_Variables.results.createNode("Feed details failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 7) 
	public void CreateTreatment() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-10: Verify user can create feed intervention", "This test case will verify that user can create feed intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.steps.createNode("1. Select site and click on create new button");
			Test_Variables.steps.createNode("2. Select treatment from radio button");
			Test_Variables.steps.createNode("3. Enter valid data in all mandatory fields and click on save button");
		//	Helper.driver.findElement(By.id("site-1")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-treatment")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));

			Helper.driver.findElement(By.id("startDatebtn")).click();
			Helper.driver.findElement(By.xpath(Test_Elements.poultryStartDateSelect)).click();
			
			Helper.driver.findElement(By.xpath(Test_Elements.poultryAdminMethod)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.poultryAdminMethodSelect)).click();
			Thread.sleep(500);
			Helper.driver.findElement(By.xpath(Test_Elements.poultryBrandName)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.poultryBrandNameSelect)).click();
			Thread.sleep(500);
			Helper.driver.findElement(By.id("btn-save")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Treatment details saved successfully");
			Test_Variables.test.pass("Treatment details saved successfully");
			Test_Variables.results.createNode("Treatment details saved successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Treatment details failed to save");
			Test_Variables.results.createNode("Treatment details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Treatment details failed to save");
			Test_Variables.results.createNode("Treatment details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 8) 
	public void UpdateTreatment() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-11: Verify user can update feed intervention", "This test case will verify that user can update feed intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create feed intervention");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.steps.createNode("1. Click on edit icon next to created Treatment and update it");

			Helper.driver.findElement(By.id("edit-treatment-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("Note")).sendKeys("Test treatment");;
			Helper.driver.findElement(By.id("btn-save")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Treatment details saved successfully");
			Test_Variables.test.pass("Treatment details updated successfully");
			Test_Variables.results.createNode("Treatment details updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Treatment details failed to update");
			Test_Variables.results.createNode("Treatment details failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Treatment details failed to update");
			Test_Variables.results.createNode("Treatment details failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 9) 
	public void DeleteTreatment() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-12: Verify user can delete Treatment intervention", "This test case will verify that user can update delete Treatment intervention");			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create Treatment intervention");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.preconditions.createNode("1. Click on delete icon next to created Treatment and delete it");

			Helper.driver.findElement(By.id("delete-treatment-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-yes")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Treatment details deleted.");
			Test_Variables.test.pass("Treatment details deleted successfully");
			Test_Variables.results.createNode("Treatment details deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Treatment details failed to delete");
			Test_Variables.results.createNode("Treatment details failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Treatment details failed to delete");
			Test_Variables.results.createNode("Treatment details failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//		Helper.driver.close();
	}
}
