package Test.Ancera.MetaData;

import java.io.IOException;

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
import org.testng.asserts.SoftAssert;

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
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));
			Thread.sleep(2000);

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
			SoftAssert softAssert = new SoftAssert();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.steps.createNode("1. Verify table headings");

			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#poultry-grid th:nth-child(1) .d-flex label")).getText(), "Management Type"); 
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#poultry-grid th:nth-child(2) label")).getText(), "Start Date"); 
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#poultry-grid th:nth-child(3) label")).getText(), "End Date"); 
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#poultry-grid th:nth-child(4) label")).getText(), "Sites"); 
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#poultry-grid th:nth-child(5) label")).getText(), "Note"); 
			softAssert.assertTrue(Helper.driver.findElements(By.cssSelector("tr:nth-child(1) td:nth-child(4) label")).size() >=1, "Sites not displayed"); 
			Test_Variables.test.pass("Table contents verified successfully");
			Test_Variables.results.createNode("Table contents verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
			softAssert.assertAll();
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


	@Test (enabled= true, priority = 3) 
	public void CreateFeed() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-03: Verify user can create feed intervention", "This test case will verify that user can create feed intervention");
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
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".d-inline-block")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-treatment")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
			Thread.sleep(3000);
			Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[1]/div[2]/div/div/label")).click();
			Thread.sleep(2000);

			Helper.driver.findElement(By.id("feedStartDatebtn")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.poultryStartDateSelect)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("FeedNote")).sendKeys("Feed Notes - "+Test_Variables.date0);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save-feed")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Feed details saved successfully");
			Thread.sleep(3500);
			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Feed Notes - "+Test_Variables.date0)) {
					Assert.assertFalse(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4)")).getText().isEmpty(), "Sites displayed empty");
					break;
				}
			}
	
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


	@Test (enabled= true, priority = 4) 
	public void HeartbeatFeed() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-04: Verify user can update feed intervention", "This test case will verify heartbeat icon next to active feed");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create feed intervention");

			Test_Variables.steps.createNode("1. Verify active heartbeat icon next to created feed");
			
			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Feed Notes - "+Test_Variables.date0)) {
					int j=i-1;
					Assert.assertEquals(Helper.driver.findElements(By.id("live-icon-"+j)).size(), 1);	
					Test_Variables.test.pass("Heartbeat icon displayed next to active feed successfully");
					Test_Variables.results.createNode("Heartbeat icon displayed next to active feed successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
					break;
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Heartbeat icon failed to display next to active feed");
			Test_Variables.results.createNode("Heartbeat icon failed to display next to active feed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Heartbeat icon failed to display next to active feed");
			Test_Variables.results.createNode("Heartbeat icon failed to display next to active feed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 5) 
	public void EndIconFeed() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-05: Verify End Icon and current date is generated on clicking on End Date", "This test case will verify End Icon and current date is generated on clicking on End Date");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create feed intervention without adding End icon");

			Test_Variables.steps.createNode("1. Verify End icon appears when end date is not given");
			
			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Feed Notes - "+Test_Variables.date0)) {
					Assert.assertEquals(Helper.driver.findElements(By.cssSelector("tr:nth-child("+i+") td:nth-child(3) img")).size(), 1);	
					break;
				}
			}
			
			Test_Variables.test.pass("End Icon displayed successfully");
			Test_Variables.results.createNode("End Icon displayed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("End Icon failed to display");
			Test_Variables.results.createNode("End Icon failed to display");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("End Icon failed to display");
			Test_Variables.results.createNode("End Icon failed to display");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}

	@Test (enabled= true, priority = 6) 
	public void UpdateFeed() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-06: Verify user can update feed intervention", "This test case will verify that user can update feed intervention");
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

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Feed Notes - "+Test_Variables.date0)) {
					Helper.driver.findElement(By.id("edit-treatment-"+i)).click();				
					break;
				}
			}
			
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("WD1Day")).sendKeys("100");;
			Helper.driver.findElement(By.id("btn-save-feed")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
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


	@Test (enabled= true, priority = 7) 
	public void CreateTreatment() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-07: Verify user can create treatment intervention", "This test case will verify that user can create feed intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");


			Helper.driver.get(Constants.url_poultryManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector(".d-inline-block")).click();
			Thread.sleep(1000);

			Test_Variables.steps.createNode("1. Select site and click on create new button");
			Test_Variables.steps.createNode("2. Select treatment from radio button");
			Test_Variables.steps.createNode("3. Enter valid data in all mandatory fields and click on save button");
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-treatment")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("startDatebtn")).click();
			Helper.driver.findElement(By.xpath(Test_Elements.poultryStartDateSelect1)).click();

			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Method']")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Method']")).sendKeys("a");
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Method']")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Method']")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Brand Name']")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Brand Name']")).sendKeys("a");
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Brand Name']")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Brand Name']")).sendKeys(Keys.ENTER);		
			Thread.sleep(1000);
			
			Helper.driver.findElement(By.id("Note")).sendKeys("Treatment Notes - "+Test_Variables.date0);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Treatment details saved successfully");
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			Thread.sleep(3500);
		
			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Treatment Notes - "+Test_Variables.date0)) {
					Assert.assertFalse(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4)")).getText().isEmpty(), "Sites displayed empty");
					break;
				}
			}
			
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
	public void HeartbeatTreatment() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-08: Verify heartbeat icon appears next to created treatment", "This test case will verify heartbeat icon next to active treatment");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create treatment intervention");

			Test_Variables.steps.createNode("1. Verify active heartbeat icon next to created treatment");
			
			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Feed Notes - "+Test_Variables.date0)) {
					int j=i-1;
					Assert.assertEquals(Helper.driver.findElements(By.id("live-icon-"+j)).size(), 1);
					Test_Variables.test.pass("Heartbeat icon displayed next to active treatment successfully");
					Test_Variables.results.createNode("Heartbeat icon displayed next to active treatment successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
					break;
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Heartbeat icon failed to display next to active treatment");
			Test_Variables.results.createNode("Heartbeat icon failed to display next to active treatment");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Heartbeat icon failed to display next to active treatment");
			Test_Variables.results.createNode("Heartbeat icon failed to display next to active treatment");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}

	@Test (enabled= true, priority = 9) 
	public void UpdateTreatment() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-09: Verify user can update treatment intervention", "This test case will verify that user can update treatment intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create treatment intervention");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));
			Test_Variables.steps.createNode("1. Click on edit icon next to created Treatment and update it");
			
			if (Helper.driver.findElements(By.cssSelector("button.close span")).size() != 0 ) {
				Helper.driver.findElement(By.cssSelector("button.close span")).click();
			}
			Thread.sleep(1000);
			
			int rows = Helper.driver.findElements(By.cssSelector("td:nth-child(4)")).size();
			
			for (int i = 1; i<=rows; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Treatment Notes - "+Test_Variables.date0)) {
					int j = i+2;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(5)"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("edit-treatment-"+i)).click();
					break;
				}
			}
			
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("Note")).clear();
			Helper.driver.findElement(By.id("Note")).sendKeys("Treatment Notes Updated - "+Test_Variables.date0);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
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


	@Test (description="Test Case: Verify Filter",enabled= true, priority = 10) 
	public void VerifyFilter() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-10: Verify Management Type Filter", "This test case will verify management type filter functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.steps.createNode("5. Apply filter on Management Type");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));
			Helper.driver.findElement(By.id("interventionName_show-filter")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("interventionName_search-input")).sendKeys("Feed");

			Helper.driver.findElement(By.id("interventionName_cust-cb-lst-txt_Feed")).click();
			Helper.driver.findElement(By.id("interventionName_apply")).click();

			for (int i = 1; i< Helper.driver.findElements(By.id("#poultry-grid tr")).size(); i++) {
				Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#poultry-grid tr:nth-child("+i+") td:nth-child(1) label")).getText(), "feed");
			}
			Helper.driver.findElement(By.id("interventionName}_clear-filter")).click();

			Test_Variables.test.pass("Filter applied successfully");
			Test_Variables.results.createNode("Filter applied successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Poultry Management", Constants.PoultryManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PoultryManagementReportPath, null);	
		}

		catch(AssertionError er) {
			Test_Variables.test.fail("Filter failed to apply");
			Test_Variables.results.createNode("Filter failed to apply");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Filter failed to apply");
			Test_Variables.results.createNode("Filter failed to apply");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PoultryManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 11) 
	public void DeleteTreatment() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-11: Verify user can delete Treatment intervention", "This test case will verify that user can update delete Treatment intervention");			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create Treatment intervention");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.preconditions.createNode("1. Click on delete icon next to created Treatment and delete it");

			int rows = Helper.driver.findElements(By.cssSelector("td:nth-child(4)")).size();
			
			for (int i = 1; i<=rows; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Treatment Notes Updated - "+Test_Variables.date0)) {
					int j = i+2;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(5)"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("delete-treatment-"+i)).click();
					break;
				}
			}
			
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
	
	@Test (enabled= true, priority = 12) 
	public void DeleteFeed() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-12: Verify user can delete feed intervention", "This test case will verify that user can update delete intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			Test_Variables.preconditions.createNode("5. Create feed intervention");

			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-treatment")));

			Test_Variables.preconditions.createNode("1. Click on delete icon next to created feed and delete it");

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Feed Notes - "+Test_Variables.date0)) {
					Helper.driver.findElement(By.id("delete-treatment-"+i)).click();	
					break;}
			}
				
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
	
	
	@Test (enabled= false, priority = 3) 
	public void CreateVaccine() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Poultry-03: Verify user can create vaccine intervention", "This test case will verify that user can create vaccine intervention");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Poultry Management");
			
			Helper.driver.get(Constants.url_poultryManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("1. Select site and click on create new button; select feed from radio button");
			Test_Variables.steps.createNode("1. Enter valid data in all mandatory fields and click on save button");
			Helper.driver.findElement(By.cssSelector(".d-inline-block")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-cocci-intervention")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			Helper.driver.findElement(By.id("startDatebtnVaccine")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.poultryStartDateVaccine)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//input[@placeholder='Vaccine']")).sendKeys(Test_Variables.dateYYYYMMDD+"_Vaccine_"+Test_Variables.date0);
			Thread.sleep(4000);
			Helper.driver.findElement(By.id("btnAddNew-VaccineId")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("btn-save-vaccine")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Feed details saved successfully");
			Thread.sleep(3500);
			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5)")).getText().equals("Feed Notes - "+Test_Variables.date0)) {
					Assert.assertFalse(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4)")).getText().isEmpty(), "Sites displayed empty");
					break;
				}
			}
	
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

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();
	}
}
