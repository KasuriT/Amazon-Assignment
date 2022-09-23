package Test.Ancera.PiperAdministration;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

public class Piper_Configuration {


	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Piper_Configuration"+date+".html");
		spark.config().setReportName("Piper Configuration Test Report"); 
		config();
		ConfigureLogin.login();
	}


	@Test (priority = 1) 
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen(url_piperConfiguration, "Piper Configuration", PiperConfigurationReportPath, piperConfigurationTitle);
	}


	@Test (enabled= true, priority = 2) 
	public void CreatePiperConfigSalm() throws InterruptedException, IOException {
				
		String minMeanError = null;
		String maxMeanError = null;
		String minStdError = null;
		String maxStdError = null;
		
		lstPiperConfigurationCreate = PiperConfigurationModel.FillData();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#PathogenName input")).sendKeys("Salmonella");
		driver.findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("create-installation-run")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#ImprocName img")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#ImprocName ul")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001);
		driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(alertMessage).getText(), "Invalid Version. Please enter a valid 4-digit version i.e. 11.22.33.44");
		driver.findElement(alertMessageClose).click();
		driver.findElement(By.cssSelector("#ImprocVersion input")).clear();
		driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
		
		if (driver.findElements(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).size() == 1) {
			driver.findElement(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).click();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'Add New +')]")).size() == 1) {
			driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		}	

	//	driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
		Thread.sleep(1000);

		for (PiperConfigurationModel objModel : lstPiperConfigurationCreate) { 
			try{
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Navigate to Piper Configuration Management screen");
				steps.createNode("1. Click on create new button next to Installation Run Config");
				steps.createNode("2. Select improc name and improc version from dropdown");
				SoftAssert softAssert = new SoftAssert();
				
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Thread.sleep(2000);
						driver.findElement(By.id("MinMeanVal")).clear();
						driver.findElement(By.id("MinMeanVal")).sendKeys(objFilter.MinMean);
						driver.findElement(By.id("MaxMeanVal")).clear();
						driver.findElement(By.id("MaxMeanVal")).sendKeys(objFilter.MaxMean);
						driver.findElement(By.id("MinStdVal")).clear();
						driver.findElement(By.id("MinStdVal")).sendKeys(objFilter.MinStd);
						driver.findElement(By.id("MaxStdVal")).clear();
						driver.findElement(By.id("MaxStdVal")).sendKeys(objFilter.MaxStd);
						driver.findElement(By.id("MinStdVal")).click();
						test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
	
						steps.createNode("3. "+objModel.steps);
						Thread.sleep(1000);
						if (driver.findElement(By.id("MinMeanVal")).getAttribute("value").isEmpty())
						{
							minMeanError = driver.findElement(By.xpath("//div[contains(text(), ' Min. mean is required ')]")).getText();
							softAssert.assertEquals(minMeanError, " Min. mean is required "); 
						}

						if (driver.findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty())
						{
							maxMeanError = driver.findElement(By.xpath("//div[contains(text(), ' Max. mean is required ')]")).getText();
							softAssert.assertEquals(maxMeanError, " Max. mean is required "); 
						}

						if (driver.findElement(By.id("MinStdVal")).getAttribute("value").isEmpty())
						{
							minStdError = driver.findElement(By.xpath("//div[contains(text(), ' Min. Std. deviation is required ')]")).getText();
							softAssert.assertEquals(minStdError, " Min. Std. deviation is required "); 
						}

						if (driver.findElement(By.id("MaxStdVal")).getAttribute("value").isEmpty())
						{
							maxStdError = driver.findElement(By.xpath("//div[contains(text(), ' Max. Std. deviation is required ')]")).getText();
							softAssert.assertEquals(maxStdError, " Max. Std. deviation is required ");
						}

						if(objModel.GreaterLesserCheck) {
							maxMeanError = driver.findElement(By.xpath("//div[contains(text(), 'Max. mean must be greater than Min. mean')]")).getText();
							softAssert.assertEquals(maxMeanError, " Max. mean must be greater than Min. mean "); 

							maxStdError = driver.findElement(By.xpath("//div[contains(text(), 'Max. Std. deviation must be greater than Min. Std. deviation')]")).getText();
							softAssert.assertEquals(maxStdError, " Max. Std. deviation must be greater than Min. Std. deviation ");
						}

						else if (!driver.findElement(By.id("MinMeanVal")).getAttribute("value").isEmpty() && 
								!driver.findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty() && 
								!driver.findElement(By.id("MinStdVal")).getAttribute("value").isEmpty() && 
								!driver.findElement(By.id("MaxMeanVal")).getAttribute("value").isEmpty())
						{
							driver.findElement(By.id("btn-save")).click();
							test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
							Thread.sleep(1500);
							String message = driver.findElement(alertMessage).getText();
							test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
							driver.findElement(alertMessageClose).click();				
							softAssert.assertEquals(message, "Installation Run Configuration saved successfully");							
						}
						softAssert.assertAll();
						Thread.sleep(1000);
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);					
						saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);				
					}catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
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
			test = extent.createTest("AN-PCM-06: Verify user can create Coccidia configuration", "This test case will verify that user can create Coccidia Configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown and click on save button");
			driver.get(url_piperConfiguration);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("#PathogenName  .ng-arrow-wrapper")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#PathogenName input")).sendKeys("Coccidia");
			driver.findElement(By.cssSelector("#PathogenName input")).sendKeys(Keys.ENTER);
			driver.findElement(By.id("create-installation-run")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#ImprocName img")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#ImprocName ul")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#ImprocVersion input")).clear();
			driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001);
			driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(alertMessage).getText(), "Invalid Version. Please enter a valid 4-digit version i.e. 11.22.33.44");
			driver.findElement(By.cssSelector("#ImprocVersion input")).clear();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#ImprocVersion input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
			
			if (driver.findElements(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).size() == 1) {
				driver.findElement(By.xpath("//b[contains(text(),'"+date1001+"."+date1001+"."+date1001+"."+date1001+"')]")).click();
			}
			if (driver.findElements(By.xpath("//*[contains(text(),'Add New +')]")).size() == 1) {
				driver.findElement(By.xpath("//*[contains(text(),'Add New +')]")).click();
			}	
			
			Thread.sleep(1000);
			driver.findElement(By.id("MinMeanVal")).clear();
			driver.findElement(By.id("MinMeanVal")).sendKeys("1");
			driver.findElement(By.id("MaxMeanVal")).clear();
			driver.findElement(By.id("MaxMeanVal")).sendKeys("1600");
			driver.findElement(By.id("MinStdVal")).clear();
			driver.findElement(By.id("MinStdVal")).sendKeys("1");
			driver.findElement(By.id("MaxStdVal")).clear();
			driver.findElement(By.id("MaxStdVal")).sendKeys("1"+date0);
			Thread.sleep(1000);
			driver.findElement(By.id("MinStdVal")).click();
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			driver.findElement(By.id("btn-save")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1500);
			String message = driver.findElement(alertMessage).getText();
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			driver.findElement(alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration saved successfully");
			test.pass("Coccidia configuration created successfully");
			results.createNode("Coccidia configuration created successfully");
		
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			test.fail("Coccidia configuration failed");
			results.createNode("Coccidia configuration failed");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Coccidia configuration failed");
			results.createNode("Coccidia configuration failed");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 4) 
	public void UpdatePiperConfig() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-PCM-07: Verify dropdowns are disabled in update mode", "This testcase will verify that dropdowns are disabled in update mode");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");

			driver.get(url_piperConfiguration);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for (int i = 1; i<=1000; i++) {
				Thread.sleep(1000);
				if (driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText().equals("1"+date0)) {
					int j = i-2;
					if (driver.findElements(By.id("edit-installation-"+j)).size() != 0) {
					WebElement scroll = driver.findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Thread.sleep(1000);
					}
					driver.findElement(By.id("edit-installation-"+i)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertTrue(driver.findElements(By.cssSelector("#ImprocName.cursor-not-allowed")).size() == 1);
			Assert.assertTrue(driver.findElements(By.cssSelector("#ImprocVersion.cursor-not-allowed")).size() == 1);
			test.pass("Dropdowns were disabled in update mode");
			results.createNode("Dropdowns were disabled in update mode");
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			test.fail("Dropdowns were not disabled in update mode");
			results.createNode("Dropdowns were not disabled in update mode");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Dropdowns were not disabled in update mode");
			results.createNode("Dropdowns were not disabled in update mode");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}

		try{
			test = extent.createTest("AN-PCM-08: Verify piper configuration can be updated", "This testcase will verify that piper configuration can be updated");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");
			Thread.sleep(1500);
			driver.findElement(By.id("MinStdVal")).clear();
			driver.findElement(By.id("MinStdVal")).sendKeys("500");
			driver.findElement(By.id("btn-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000);	
			String message = driver.findElement(By.id("message")).getText();
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			driver.findElement(alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration saved successfully");
			test.pass("Installation Run Configuration updated successfully");
			results.createNode("Installation Run Configuration updated successfully");
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			test.fail("Installation Run Configuration failed to update");
			results.createNode("Installation Run Configuration failed to update");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Installation Run Configuration failed to update");
			results.createNode("Installation Run Configuration failed to update");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 5) 
	public void DeletePiperConfigSalm() throws InterruptedException, IOException {

		try{
			test = extent.createTest("AN-PCM-09: Verify user can delete Salmonella piper configuration", "This testcase will verify that user can delete piper configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");

			
			for (int i = 1; i<=1000; i++) {
				if (driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(3)")).getText().equals("1"+date0)) {
					Thread.sleep(1000);
					int j= i-2;
					if (driver.findElements(By.id("edit-installation-"+j)).size() != 0) {
					WebElement scroll = driver.findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					}
					driver.findElement(By.id("delete-installation-"+i)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			driver.findElement(By.id("btn-yes")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1500);
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals(message, "Installation Run Configuration details deleted.");
			driver.findElement(alertMessageClose).click();
			test.pass("Piper configuration deleted successfully");
			results.createNode("Piper configuration deleted successfully");
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 6) 
	public void DeletePiperConfigCocci() throws InterruptedException, IOException {
		try{
			
			driver.get(url_piperConfiguration);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test = extent.createTest("AN-PCM-10: Verify user can delete Coccidia piper configuration", "This testcase will verify that user can delete piper configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Select improc name and improc version from dropdown");
			Thread.sleep(2000);
			for (int i = 1; i<=1000; i++) {
				if (driver.findElement(By.cssSelector("#installation-"+i+" td:nth-child(6)")).getText().equals("1"+date0)) {
					int j= i-2;
					Thread.sleep(1000);
					WebElement scroll = driver.findElement(By.id("edit-installation-"+j));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("delete-installation-"+i)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			driver.findElement(By.id("btn-yes")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1500);	
			String message = driver.findElement(By.id("message")).getText();
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			driver.findElement(alertMessageClose).click();
			Assert.assertEquals(message, "Installation Run Configuration details deleted.");	
			test.pass("Piper configuration deleted successfully");
			results.createNode("Piper configuration deleted successfully");
			
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Piper configuration failed to delete");
			results.createNode("Piper configuration failed to delete");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 7) 
	public void PAConnfigImprocCheck() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-PCM-11: Verify created imrpoc is displayed in P/A Configurations", "This testcase will verifycreated imrpoc is displayed in P/A Configurations");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on create new button next to Installation Run Config");
			steps.createNode("2. Create new Improc");
			steps.createNode("3. Verify created mrpoc in P/A configurations");

//			driver.get(url_piperConfiguration);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
//			Thread.sleep(1000);
			
		//	for (int i=1; i<=2;i++) {

		//		if (i==1) {
					driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Salmonella");
					driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
					driver.findElement(By.id("create-mpn")).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#sampleMatrix3LId input")).click();
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#ImprocVersion3LId input")).click();
					driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
					//driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys("59.59.59.59");
					Thread.sleep(1500);
					test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
					Assert.assertEquals(driver.findElements(By.cssSelector(".ng-option-disabled")).size(), 0);
					driver.findElement(By.cssSelector("#close-popup-modal p")).click();
		//		}
		//		if (i==2) {
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#PathogenNameConfig input")).clear();
					driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Listeria");
					driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					driver.findElement(By.id("create-mpn")).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
					Thread.sleep(1500);
					driver.findElement(By.cssSelector("#sampleMatrixId input")).click();
					Thread.sleep(1500);
					driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(date1001+"."+date1001+"."+date1001+"."+date1001);
					//driver.findElement(By.cssSelector("#ImprocVersionId input")).sendKeys("59.59.59.59");
					Thread.sleep(2000);
					test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
					Assert.assertEquals(driver.findElements(By.cssSelector(".ng-option-disabled")).size(), 0);
		//		}
		//	}

			test.pass("Created improc displayed in P/A Configurations successfully");
			results.createNode("Created improc displayed in P/A Configurations successfully");
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}catch(AssertionError er) {
			test.fail("Created improc failed to display in P/A Configurations");
			results.createNode("Created improc failed to display in P/A Configurations");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Created improc failed to display in P/A Configurations");
			results.createNode("Created improc failed to display in P/A Configurations");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 8) 
	public void CreatePAConfigSalm() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-12: Verify user can create P/A MPN Configuration for Salmonella", "This test case will verify that user can create P/A MPN Configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Click on create new button next to MPN P/A Config");
			steps.createNode("2. Select improc name and improc version from dropdown and click on save button");

			driver.get(url_piperConfiguration);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Salmonella");
			driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.id("create-mpn")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys("AT_SMatrix_Salm_"+date0);
			Thread.sleep(1000);
			
			if (driver.findElements(By.cssSelector(".fa-trash")).size() == 1) {
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".fa-trash")).click();
			Thread.sleep(1000);
			driver.findElement(popupYesButton).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(alertMessage).getText(), "Sample Matrix details deleted.");
			}
			
			driver.findElement(By.id("dilution-factor-var")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("newSampleMatrix3LId")).sendKeys("AT_SMatrix_Salm_"+date0);
			
			
			driver.findElement(By.cssSelector(".m-l-5px#btn-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys("AT_SMatrix_Salm_"+date0);
			driver.findElement(By.cssSelector("#sampleMatrix3LId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
		//	driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(PA_ImprocVersionNew);
			driver.findElement(By.cssSelector("#ImprocVersion3LId input")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#ImprocVersion3LId input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			driver.findElement(By.id("ThresholdPAId")).sendKeys("1000");
			driver.findElement(By.id("EAIUnit3LId")).sendKeys("100");

			driver.findElement(By.cssSelector(".ml-1")).click();
			Thread.sleep(750);
			driver.findElement(By.id("constIncolEq1Id")).sendKeys("10");
			driver.findElement(By.cssSelector("#MicrobialItemsId1LCCV input")).sendKeys("Piper Count");
			Thread.sleep(750);
			driver.findElement(By.cssSelector("#MicrobialItemsId1LCCV input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			driver.findElement(By.id("constMicrobialEq1Id")).sendKeys("10");
			driver.findElement(By.cssSelector("#MicrobialItemsId1LMLCV input")).sendKeys("Piper Count");
			Thread.sleep(750);
			driver.findElement(By.cssSelector("#MicrobialItemsId1LMLCV input")).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			driver.findElement(By.id("enrichVol1LId")).sendKeys("10");
			driver.findElement(By.id("enrichDiluFactor1LId")).sendKeys("10");
			driver.findElement(By.id("rinsateVol1LId")).sendKeys("10");
			Thread.sleep(750);

			driver.findElement(By.cssSelector(".m-l-10px#btn-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "MPN & P/A Configuration saved successfully.");
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			driver.findElement(alertMessageClose).click();
			softAssert.assertAll();
			
			test.pass("P/A MPN configuration created successfully");
			results.createNode("P/A MPN configuration created successfully");
		
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResultNew(ITestResult.FAILURE,
					PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}	 


	@Test (enabled= true, priority = 9) 
	public void DeletePAConfigSalm() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-13: Verify user can delete P/A MPN Configuration for Salmonella", "This test case will verify that user can create P/A MPN Configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Create P/A config");
			steps.createNode("2. Delete that config");
			
			for (int i = 1; i<=1000; i++) {
				if (driver.findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals("AT_SMatrix_Salm_"+date0)) {
					Thread.sleep(1000);
					int j = i-2;
					WebElement filter_scroll = driver.findElement(By.id("delete-mpn-"+j));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					driver.findElement(By.id("delete-mpn-"+i)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			driver.findElement(By.id("btn-yes")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1500);
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals(message, "MPN & P/A Configuration details deleted.");
			driver.findElement(alertMessageClose).click();
			test.pass("P/A MPN configuration deleted successfully");
			results.createNode("P/A MPN configuration deleted successfully");
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to delete");
			results.createNode("P/A MPN configuration failed to delete");
			saveResultNew(ITestResult.FAILURE,
					PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to delete");
			results.createNode("P/A MPN configuration failed to delete");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}	 

	
	@Test (enabled= true, priority = 10) 
	public void CreatePAConfigList() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-14: Verify user can create P/A MPN Configuration for Listeria", "This test case will verify that user can create P/A MPN Configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Click on create new button next to MPN P/A Config");
			steps.createNode("2. Select improc name and improc version from dropdown and click on save button");

			driver.get(url_piperConfiguration);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys("Listeria");
			driver.findElement(By.cssSelector("#PathogenNameConfig input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.id("create-mpn")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys("AT_SMatrix_List_"+date0);
			Thread.sleep(1500);
			SoftAssert softAssert = new SoftAssert();
			if (driver.findElements(By.cssSelector(".fa-trash")).size() == 1) {
			driver.findElement(By.cssSelector(".fa-trash")).click();
			Thread.sleep(1000);
			driver.findElement(popupYesButton).click();	
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Sample Matrix details deleted.");
			}
			
			driver.findElement(By.id("dilution-factor-var")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("newSampleMatrixId")).sendKeys("AT_SMatrix_List_"+date0);
			driver.findElement(By.cssSelector(".m-l-5px#btn-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New Sample Matrix created.");
			driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys("AT_SMatrix_List_"+date0);
			Thread.sleep(750);
			driver.findElement(By.cssSelector("#sampleMatrixId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			//driver.findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(PA_ImprocVersionNew);
			driver.findElement(By.cssSelector("#ImprocVersionId input")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#ImprocVersionId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.id("ThresholdId")).sendKeys("1000");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".m-l-10px#btn-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Listeria Configuration saved successfully");
			driver.findElement(alertMessageClose).click();
			softAssert.assertAll();
			test.pass("P/A MPN configuration created successfully");
			results.createNode("P/A MPN configuration created successfully");
		
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResultNew(ITestResult.FAILURE,
					PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to create");
			results.createNode("P/A MPN configuration failed to create");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}	 


	@Test (enabled= true, priority = 11) 
	public void DeletePAConfigList() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-PCM-15: Verify user can delete P/A MPN Configuration for Listeria", "This test case will verify that user can create P/A MPN Configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Navigate to Piper Configuration Management screen");
			steps.createNode("1. Create P/A config");
			steps.createNode("2. Delete that config");
			
			 for(int i=0; i<2; i++){
				   driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
				  }
			 
			 Robot robot = new Robot();
			 for (int i = 0; i < 4; i++) {
				   robot.keyPress(KeyEvent.VK_CONTROL);
				   robot.keyPress(KeyEvent.VK_SUBTRACT);
				   robot.keyRelease(KeyEvent.VK_SUBTRACT);
				   robot.keyRelease(KeyEvent.VK_CONTROL);
				  }
			 Thread.sleep(3000);			 
			
			for (int i = 1; i<=1000; i++) {
				if (driver.findElement(By.cssSelector("#mpn-"+i+" td:nth-child(4) label")).getText().equals("AT_SMatrix_List_"+date0)) {
					Thread.sleep(1000);
					int j = i-2;
					WebElement scroll = driver.findElement(By.id("delete-mpn-"+j));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Thread.sleep(2000);
					driver.findElement(By.id("delete-mpn-"+i)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			driver.findElement(By.id("btn-yes")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1500);
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals(message, "Listeria Configuration details deleted");
			driver.findElement(alertMessageClose).click();
			test.pass("P/A MPN configuration deleted successfully");
			results.createNode("P/A MPN configuration deleted successfully");
			test.addScreenCaptureFromPath(getScreenshot("Piper Configuration", PiperConfigurationReportPath));
			saveResultNew(ITestResult.SUCCESS, PiperConfigurationReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("P/A MPN configuration failed to delete");
			results.createNode("P/A MPN configuration failed to delete");
			saveResultNew(ITestResult.FAILURE,
					PiperConfigurationReportPath, new Exception(er)); }
		catch(Exception ex) {
			test.fail("P/A MPN configuration failed to delete");
			results.createNode("P/A MPN configuration failed to delete");
			saveResultNew(ITestResult.FAILURE, PiperConfigurationReportPath, ex);
		}
	}	 

	
	@AfterTest
	public static void endreport() {
		extent.flush();
		driver.close();
	}
}
