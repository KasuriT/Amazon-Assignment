package PiperAdminstration;

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

import Models.PiperConfigurationModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class Piper_Configuration {


	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Piper_Configuration"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Piper Configuration Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (enabled= true, priority = 1) 
	public void NavigatePiperConfig() throws InterruptedException, IOException {

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PC-01: Verify user can navigate to Piper Configuration Screen", "This test case will verify user can navigate to Piper Configuration Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("1. Click on Piper Administration and select Piper Configuration");
			Test_Variables.steps.createNode("2. Verify Piper Configuration page opens");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.cssSelector("#screen-header p")).getText();
			String expected = "PIPER Configuration Management";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("Piper Configuration screen opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Piper Configuration screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Piper Configuration screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}



	@Test (enabled= true, priority = 2) 
	public void CreatePiperConfigSalm() throws InterruptedException, IOException {

		String minMeanError = null;
		String maxMeanError = null;
		String minStdError = null;
		String maxStdError = null;

		Test_Variables.lstPiperConfigurationCreate = PiperConfigurationModel.FillData();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys("Salmonella");
		Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
		Helper.driver.findElement(By.id("create-installation-run")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.cssSelector("#ImprocName img")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.cssSelector("#ImprocName ul")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001);
		Helper.driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		Thread.sleep(1000);
		Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Invalid Version. Please enter a valid 4-digit version i.e. 11.22.33.44");
		Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).clear();
		Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001);
		
		if (Helper.driver.findElements(By.xpath("//b[contains(text(),'"+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"')]")).size() == 1) {
			Helper.driver.findElement(By.xpath("//b[contains(text(),'"+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"')]")).click();
		}
		if (Helper.driver.findElements(By.xpath("//*[contains(text(),'Add New +')]")).size() == 1) {
			Helper.driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		}	

	//	Helper.driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		Thread.sleep(1000);

		for (PiperConfigurationModel objModel : Test_Variables.lstPiperConfigurationCreate) { 
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
				Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Thread.sleep(2000);
						Helper.driver.findElement(By.id("MinMeanVal")).clear();
						Helper.driver.findElement(By.id("MinMeanVal")).sendKeys(objFilter.MinMean);
						Helper.driver.findElement(By.id("MaxMeanVal")).clear();
						Helper.driver.findElement(By.id("MaxMeanVal")).sendKeys(objFilter.MaxMean);
						Helper.driver.findElement(By.id("MinStdVal")).clear();
						Helper.driver.findElement(By.id("MinStdVal")).sendKeys(objFilter.MinStd);
						Helper.driver.findElement(By.id("MaxStdVal")).clear();
						Helper.driver.findElement(By.id("MaxStdVal")).sendKeys(objFilter.MaxStd);
						Helper.driver.findElement(By.id("MinStdVal")).click();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
						Helper.driver.findElement(By.id("btn-save")).click();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));

						Test_Variables.steps.createNode("3. "+objModel.steps);

						if (Helper.driver.findElement(By.id("MinMeanVal")).getAttribute("value").isEmpty())
						{
							minMeanError = Helper.driver.findElement(By.xpath("//div[contains(text(), ' Min. mean is required ')]")).getText();
							Assert.assertEquals(minMeanError, " Min. mean is required "); 
						}

						if (Helper.driver.findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty())
						{
							maxMeanError = Helper.driver.findElement(By.xpath("//div[contains(text(), ' Max. mean is required ')]")).getText();
							Assert.assertEquals(maxMeanError, " Max. mean is required "); 
						}

						if (Helper.driver.findElement(By.id("MinStdVal")).getAttribute("value").isEmpty())
						{
							minStdError = Helper.driver.findElement(By.xpath("//div[contains(text(), ' Min. Std. deviation is required ')]")).getText();
							Assert.assertEquals(minStdError, " Min. Std. deviation is required "); 
						}

						if (Helper.driver.findElement(By.id("MaxStdVal")).getAttribute("value").isEmpty())
						{
							maxStdError = Helper.driver.findElement(By.xpath("//div[contains(text(), ' Max. Std. deviation is required ')]")).getText();
							Assert.assertEquals(maxStdError, " Max. Std. deviation is required ");
						}

						if(objModel.GreaterLesserCheck) {
							maxMeanError = Helper.driver.findElement(By.xpath("//div[contains(text(), 'Max. mean must be greater than Min. mean')]")).getText();
							Assert.assertEquals(maxMeanError, " Max. mean must be greater than Min. mean "); 

							maxStdError = Helper.driver.findElement(By.xpath("//div[contains(text(), 'Max. Std. deviation must be greater than Min. Std. deviation')]")).getText();
							Assert.assertEquals(maxStdError, " Max. Std. deviation must be greater than Min. Std. deviation ");
						}

						else if (!Helper.driver.findElement(By.id("MinMeanVal")).getAttribute("value").isEmpty() && 
								!Helper.driver.findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty() && 
								!Helper.driver.findElement(By.id("MinStdVal")).getAttribute("value").isEmpty() && 
								!Helper.driver.findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty())
						{
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
							Thread.sleep(1500);
							String message = Helper.driver.findElement(Test_Elements.alertMessage).getText();
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
							Helper.driver.findElement(Test_Elements.alertMessageClose).click();				
							Assert.assertEquals(message, "Installation Run Configuration saved successfully");							
						}
						Thread.sleep(1000);
						Test_Variables.test.pass(objModel.passStep);
						Test_Variables.results.createNode(objModel.passStep);					
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);				
					}catch(AssertionError er) {
						Test_Variables.test.fail(objModel.failStep);
						Test_Variables.results.createNode(objModel.failStep);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objModel.failStep);
						Test_Variables.results.createNode(objModel.failStep);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority = 3) 
	public void CreatePiperConfigCocci() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-06: Verify user can create Coccidia configuration", "This test case will verify that user can create Coccidia Configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown and click on save button");
			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			Helper.driver.findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys("Coccidia");
			Helper.driver.findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.id("create-installation-run")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#ImprocName img")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#ImprocName ul")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).clear();
			Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001);
			Helper.driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Invalid Version. Please enter a valid 4-digit version i.e. 11.22.33.44");
			Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).clear();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001);
			
			if (Helper.driver.findElements(By.xpath("//b[contains(text(),'"+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"')]")).size() == 1) {
				Helper.driver.findElement(By.xpath("//b[contains(text(),'"+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"')]")).click();
			}
			if (Helper.driver.findElements(By.xpath("//*[contains(text(),'Add New +')]")).size() == 1) {
				Helper.driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
			}	
			
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("MinMeanVal")).clear();
			Helper.driver.findElement(By.id("MinMeanVal")).sendKeys("1");
			Helper.driver.findElement(By.id("MaxMeanVal")).clear();
			Helper.driver.findElement(By.id("MaxMeanVal")).sendKeys("1600");
			Helper.driver.findElement(By.id("MinStdVal")).clear();
			Helper.driver.findElement(By.id("MinStdVal")).sendKeys("1");
			Helper.driver.findElement(By.id("MaxStdVal")).clear();
			Helper.driver.findElement(By.id("MaxStdVal")).sendKeys("1"+Test_Variables.date0);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("MinStdVal")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.driver.findElement(By.id("btn-save")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1500);
			String message = Helper.driver.findElement(Test_Elements.alertMessage).getText();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration saved successfully");
			Test_Variables.test.pass("Coccidia configuration created successfully");
			Test_Variables.results.createNode("Coccidia configuration created successfully");
		
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Coccidia configuration failed");
			Test_Variables.results.createNode("Coccidia configuration failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Coccidia configuration failed");
			Test_Variables.results.createNode("Coccidia configuration failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 4) 
	public void UpdatePiperConfig() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-07: Verify dropdowns are disabled in update mode", "This testcase will verify that dropdowns are disabled in update mode");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");

			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for (int i = 1; i<=1000; i++) {
				Thread.sleep(1000);
				if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText().equals("1"+Test_Variables.date0)) {
					int j = i-2;
					if (Helper.driver.findElements(By.id("edit-installation-"+j)).size() != 0) {
					WebElement scroll = Helper.driver.findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Thread.sleep(1000);
					}
					Helper.driver.findElement(By.id("edit-installation-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertTrue(Helper.driver.findElements(By.cssSelector("#ImprocName.cursor-not-allowed")).size() == 1);
			Assert.assertTrue(Helper.driver.findElements(By.cssSelector("#ImprocVersion.cursor-not-allowed")).size() == 1);
			Test_Variables.test.pass("Dropdowns were disabled in update mode");
			Test_Variables.results.createNode("Dropdowns were disabled in update mode");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Dropdowns were not disabled in update mode");
			Test_Variables.results.createNode("Dropdowns were not disabled in update mode");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Dropdowns were not disabled in update mode");
			Test_Variables.results.createNode("Dropdowns were not disabled in update mode");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-08: Verify piper configuration can be updated", "This testcase will verify that piper configuration can be updated");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("MinStdVal")).clear();
			Helper.driver.findElement(By.id("MinStdVal")).sendKeys("500");
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);	
			String message = Helper.driver.findElement(By.id("message")).getText();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration saved successfully");
			Test_Variables.test.pass("Installation Run Configuration updated successfully");
			Test_Variables.results.createNode("Installation Run Configuration updated successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Installation Run Configuration failed to update");
			Test_Variables.results.createNode("Installation Run Configuration failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Installation Run Configuration failed to update");
			Test_Variables.results.createNode("Installation Run Configuration failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 5) 
	public void DeletePiperConfig() throws InterruptedException, IOException {

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-09: Verify user can delete Salmonella piper configuration", "This testcase will verify that user can delete piper configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");

			
			for (int i = 1; i<=1000; i++) {
				if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText().equals("1"+Test_Variables.date0)) {
					Thread.sleep(1000);
					int j= i-2;
					if (Helper.driver.findElements(By.id("edit-installation-"+j)).size() != 0) {
					WebElement scroll = Helper.driver.findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					}
					Helper.driver.findElement(By.id("delete-installation-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Helper.driver.findElement(By.id("btn-yes")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1500);
			String message = Helper.driver.findElement(By.id("message")).getText();
			Assert.assertEquals(message, "Installation Run Configuration details deleted.");
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			Test_Variables.test.pass("Piper configuration deleted successfully");
			Test_Variables.results.createNode("Piper configuration deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Piper configuration failed to delete");
			Test_Variables.results.createNode("Piper configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Piper configuration failed to delete");
			Test_Variables.results.createNode("Piper configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 6) 
	public void DeletePiperConfigCocci() throws InterruptedException, IOException {
		try{
			
			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-10: Verify user can delete Coccidia piper configuration", "This testcase will verify that user can delete piper configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");
			Thread.sleep(2000);
			for (int i = 1; i<=1000; i++) {
				if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(6)")).getText().equals("1"+Test_Variables.date0)) {
					int j= i-2;
					Thread.sleep(1000);
					WebElement scroll = Helper.driver.findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("delete-installation-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Helper.driver.findElement(By.id("btn-yes")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);	
			String message = Helper.driver.findElement(By.id("message")).getText();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration details deleted.");	
			Test_Variables.test.pass("Piper configuration deleted successfully");
			Test_Variables.results.createNode("Piper configuration deleted successfully");
			
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Piper configuration failed to delete");
			Test_Variables.results.createNode("Piper configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Piper configuration failed to delete");
			Test_Variables.results.createNode("Piper configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 7) 
	public void PAConnfigImprocCheck() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-11: Verify created imrpoc is displayed in P/A Configurations", "This testcase will verifycreated imrpoc is displayed in P/A Configurations");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
			Test_Variables.steps.createNode("2. Create new Improc");
			Test_Variables.steps.createNode("3. Verify created mrpoc in P/A configurations");

			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			for (int i=1; i<=2;i++) {

				if (i==1) {
					Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Salmonella");
					Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
					Helper.driver.findElement(By.id("create-mpn")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#sampleMatrix3LId input")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#ImprocVersion3LId input")).click();
					Helper.driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001);
				//	Helper.driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys("59.59.59.59");

					Thread.sleep(1000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
					Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option-disabled")).size(), 0);
					Helper.driver.findElement(Test_Elements.popupCloseButton).click();
				}
				if (i==2) {
					Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).clear();
					Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Listeria");
					Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("create-mpn")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);
					Helper.driver.findElement(By.cssSelector("#sampleMatrixId input")).click();
					Thread.sleep(1500);
					Helper.driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001+"."+Test_Variables.date1001);
				//	Helper.driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys("59.59.59.59");
					Thread.sleep(2000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
					Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option-disabled")).size(), 0);
				}
			}

			Test_Variables.test.pass("Created improc displayed in P/A Configurations successfully");
			Test_Variables.results.createNode("Created improc displayed in P/A Configurations successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Created improc failed to display in P/A Configurations");
			Test_Variables.results.createNode("Created improc failed to display in P/A Configurations");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Created improc failed to display in P/A Configurations");
			Test_Variables.results.createNode("Created improc failed to display in P/A Configurations");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 8) 
	public void CreatePAConfigSalm() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-12: Verify user can create P/A MPN Configuration for Salmonella", "This test case will verify that user can create P/A MPN Configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to MPN P/A Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown and click on save button");

			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Salmonella");
			Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-mpn")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			Helper.driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys("AT_SMatrix_0");
			Thread.sleep(1000);
			
			if (Helper.driver.findElements(By.cssSelector(".fa-trash")).size() == 1) {
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".fa-trash")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupYesButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Sample Matrix details deleted.");
			}
			
			Helper.driver.findElement(By.id("dilution-factor-var")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("newSampleMatrix3LId")).sendKeys("AT_SMatrix_0");
			
			
			Helper.driver.findElement(By.cssSelector(".m-l-5px#btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys("AT_SMatrix_0");
			Helper.driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
		//	Helper.driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(Test_Variables.PA_ImprocVersionNew);
			Helper.driver.findElement(By.cssSelector("#ImprocVersion3LId input")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			Helper.driver.findElement(By.id("ThresholdPAId")).sendKeys("1000");
			Helper.driver.findElement(By.id("EAIUnit3LId")).sendKeys("100");

			Helper.driver.findElement(By.cssSelector(".ml-1")).click();
			Thread.sleep(750);
			Helper.driver.findElement(By.id("constIncolEq1Id")).sendKeys("10");
			Helper.driver.findElement(By.cssSelector("#MicrobialItemsId1LCCV input")).sendKeys("Piper Count");
			Thread.sleep(750);
			Helper.driver.findElement(By.cssSelector("#MicrobialItemsId1LCCV input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			Helper.driver.findElement(By.id("constMicrobialEq1Id")).sendKeys("10");
			Helper.driver.findElement(By.cssSelector("#MicrobialItemsId1LMLCV input")).sendKeys("Piper Count");
			Thread.sleep(750);
			Helper.driver.findElement(By.cssSelector("#MicrobialItemsId1LMLCV input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			Helper.driver.findElement(By.id("enrichVol1LId")).sendKeys("10");
			Helper.driver.findElement(By.id("enrichDiluFactor1LId")).sendKeys("10");
			Helper.driver.findElement(By.id("rinsateVol1LId")).sendKeys("10");
			Thread.sleep(750);

			Helper.driver.findElement(By.cssSelector(".m-l-10px#btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "MPN & P/A Configuration saved successfully.");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			softAssert.assertAll();
			
			Test_Variables.test.pass("P/A MPN configuration created successfully");
			Test_Variables.results.createNode("P/A MPN configuration created successfully");
		
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("P/A MPN configuration failed to create");
			Test_Variables.results.createNode("P/A MPN configuration failed to create");
			Helper.saveResultNew(ITestResult.FAILURE,
					Constants.PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			Test_Variables.test.fail("P/A MPN configuration failed to create");
			Test_Variables.results.createNode("P/A MPN configuration failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}	 


	@Test (enabled= true, priority = 9) 
	public void DeletePAConfigSalm() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-13: Verify user can delete P/A MPN Configuration for Salmonella", "This test case will verify that user can create P/A MPN Configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Create P/A config");
			Test_Variables.steps.createNode("2. Delete that config");
			
			for (int i = 1; i<=1000; i++) {
				if (Helper.driver.findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals("AT_SMatrix_0")) {
					Thread.sleep(1000);
					int j = i-2;
					WebElement filter_scroll = Helper.driver.findElement(By.id("delete-mpn-"+j));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.id("delete-mpn-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Helper.driver.findElement(By.id("btn-yes")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);
			String message = Helper.driver.findElement(By.id("message")).getText();
			Assert.assertEquals(message, "MPN & P/A Configuration details deleted.");
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			Test_Variables.test.pass("P/A MPN configuration deleted successfully");
			Test_Variables.results.createNode("P/A MPN configuration deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("P/A MPN configuration failed to delete");
			Test_Variables.results.createNode("P/A MPN configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE,
					Constants.PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			Test_Variables.test.fail("P/A MPN configuration failed to delete");
			Test_Variables.results.createNode("P/A MPN configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}	 

	
	@Test (enabled= true, priority = 10) 
	public void CreatePAConfigList() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-14: Verify user can create P/A MPN Configuration for Listeria", "This test case will verify that user can create P/A MPN Configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to MPN P/A Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown and click on save button");

			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Listeria");
			Helper.driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-mpn")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			Helper.driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys("AT_SMatrix_0");
			Thread.sleep(1500);
			SoftAssert softAssert = new SoftAssert();
			if (Helper.driver.findElements(By.cssSelector(".fa-trash")).size() == 1) {
			Helper.driver.findElement(By.cssSelector(".fa-trash")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupYesButton).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Sample Matrix details deleted.");
			}
			
			Helper.driver.findElement(By.id("dilution-factor-var")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("newSampleMatrixId")).sendKeys("AT_SMatrix_0");
			Helper.driver.findElement(By.cssSelector(".m-l-5px#btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New Sample Matrix created.");
			Helper.driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys("AT_SMatrix_0");
			Thread.sleep(750);
			Helper.driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			//Helper.driver.findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(Test_Variables.PA_ImprocVersionNew);
			Helper.driver.findElement(By.cssSelector("#ImprocVersionId input")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("ThresholdId")).sendKeys("1000");
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".m-l-10px#btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Listeria Configuration saved successfully");
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			softAssert.assertAll();
			Test_Variables.test.pass("P/A MPN configuration created successfully");
			Test_Variables.results.createNode("P/A MPN configuration created successfully");
		
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("P/A MPN configuration failed to create");
			Test_Variables.results.createNode("P/A MPN configuration failed to create");
			Helper.saveResultNew(ITestResult.FAILURE,
					Constants.PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			Test_Variables.test.fail("P/A MPN configuration failed to create");
			Test_Variables.results.createNode("P/A MPN configuration failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}	 


	@Test (enabled= true, priority = 11) 
	public void DeletePAConfigList() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PCM-15: Verify user can delete P/A MPN Configuration for Listeria", "This test case will verify that user can create P/A MPN Configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Create P/A config");
			Test_Variables.steps.createNode("2. Delete that config");
			for (int i = 1; i<=1000; i++) {
				if (Helper.driver.findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals("AT_SMatrix_0")) {
					Thread.sleep(1000);
					int j = i-2;
					WebElement scroll = Helper.driver.findElement(By.id("delete-mpn-"+j));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Thread.sleep(2000);
					Helper.driver.findElement(By.id("delete-mpn-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Helper.driver.findElement(By.id("btn-yes")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);
			String message = Helper.driver.findElement(By.id("message")).getText();
			Assert.assertEquals(message, "Listeria Configuration details deleted");
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();
			Test_Variables.test.pass("P/A MPN configuration deleted successfully");
			Test_Variables.results.createNode("P/A MPN configuration deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("P/A MPN configuration failed to delete");
			Test_Variables.results.createNode("P/A MPN configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE,
					Constants.PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			Test_Variables.test.fail("P/A MPN configuration failed to delete");
			Test_Variables.results.createNode("P/A MPN configuration failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}	 

	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();
	}
}
