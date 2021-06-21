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
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-installation-run")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("PIPER Configuration Management")).getText();
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
	public void CreatePiperConfig() throws InterruptedException, IOException {

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
		Thread.sleep(2000);
		Helper.driver.findElement(By.cssSelector("#ImprocName .ng-arrow-wrapper")).click();
		Helper.driver.findElement(By.cssSelector("#ImprocName input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		Helper.driver.findElement(By.cssSelector("#ImprocVersion .ng-arrow-wrapper")).click();
		Helper.driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(Keys.ENTER);	
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
						Thread.sleep(1000);
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
							String message = Helper.driver.findElement(By.id("message")).getText();
							Assert.assertEquals(message, "Installation Run Configuration saved successfully");
						}

						Thread.sleep(1000);
						Test_Variables.test.pass(objModel.passStep);
						Test_Variables.results.createNode(objModel.passStep);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
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
	public void UpdatePiperConfig() throws InterruptedException, IOException {


		try{
			Test_Variables.test = Test_Variables.extent.createTest("a", "a");
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
				//	String a =	Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText();
				//	System.out.println(a +" 1"+Test_Variables.date0);
				if (Helper.driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText().equals("1"+Test_Variables.date0)) {
					Thread.sleep(1000);

					WebElement filter_scroll = Helper.driver.findElement(By.id("edit-installation-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.id("edit-installation-"+i)).click();
					break;
				}
			}


			Assert.assertTrue(Helper.driver.findElements(By.cssSelector(".disable-cursor-NgSelect ")).size() == 2);
			Test_Variables.test.pass("a");
			Test_Variables.results.createNode("a");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);

		}catch(AssertionError er) {
			Test_Variables.test.fail("a");
			Test_Variables.results.createNode("a");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("a");
			Test_Variables.results.createNode("a");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}


		try{
			Test_Variables.test = Test_Variables.extent.createTest("aa", "a");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			Test_Variables.steps.createNode("1. Click on create new button next to Installation Run Config");
			Test_Variables.steps.createNode("2. Select improc name and improc version from dropdown");

			Helper.driver.findElement(By.id("MinStdVal")).clear();
			Helper.driver.findElement(By.id("MinStdVal")).sendKeys("500");
			Helper.driver.findElement(By.id("btn-save")).click();
		}catch(AssertionError er) {
			Test_Variables.test.fail("a");
			Test_Variables.results.createNode("a");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("a");
			Test_Variables.results.createNode("a");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}
	}




	@Test (enabled= true, priority = 4) 
	public void DeletePiperConfig() throws InterruptedException, IOException {

		try{
			Test_Variables.test = Test_Variables.extent.createTest("a", "a");
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
					WebElement filter_scroll = Helper.driver.findElement(By.id("edit-installation-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.id("delete-installation-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Helper.driver.findElement(By.id("btn-yes")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			String message = Helper.driver.findElement(By.id("message")).getText();
			Assert.assertEquals(message, "Installation Run Configuration details deleted.");

			Test_Variables.test.pass("a");
			Test_Variables.results.createNode("a");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.PiperConfigurationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperConfigurationReportPath, null);

		}catch(AssertionError er) {
			Test_Variables.test.fail("a");
			Test_Variables.results.createNode("a");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("a");
			Test_Variables.results.createNode("a");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperConfigurationReportPath, ex);
		}

	}











	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}
