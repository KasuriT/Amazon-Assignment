package Test.Ancera.Administration;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class AgreementManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Agreement_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Agreement Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Agreement Management Screen",enabled= true, priority = 1) 
	public void NavigateAgreementManagement() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-01: Verify user can navigate to Agreement Management screen", "This test case will verify that user can navigate to Agreement Management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.driver.get(Constants.url_agreementManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Administration and select Agreement Management");
			Assert.assertEquals(Helper.driver.findElement(By.id("Agreement Management")).getText(), "Agreement Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Agreement MAnagement Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Agreement Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Agreement Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Upload file",enabled= true, priority = 2) 
	public void LicenseUpload() throws InterruptedException, IOException {

		for (int i=0; i<=Test_Variables.lstAgreementManagement.size(); i++) {
			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstAgreementManagement.get(i).testCaseTitle, Test_Variables.lstAgreementManagement.get(i).testCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");

				Thread.sleep(2000);
				Test_Variables.steps.createNode("1. Click on dotted box; file explorer opens");
				Test_Variables.steps.createNode("2. Upload "+Test_Variables.lstAgreementManagement.get(i).fileType+"and verify the file is uploaded and visible in box");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Helper.driver.findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+Test_Variables.lstAgreementManagement.get(i).fileName);

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alrt")));
				Thread.sleep(1000);
				Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstAgreementManagement.get(i).alertMessage);

				Test_Variables.test.pass(Test_Variables.lstAgreementManagement.get(i).passMessage);
				Test_Variables.results.createNode(Test_Variables.lstAgreementManagement.get(i).passMessage);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
				Helper.driver.findElement(By.cssSelector("#alrt > button")).click();
			}
			catch(AssertionError er) {
				Test_Variables.test.fail(Test_Variables.lstAgreementManagement.get(i).failMessage);
				Test_Variables.results.createNode(Test_Variables.lstAgreementManagement.get(i).failMessage);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(Test_Variables.lstAgreementManagement.get(i).failMessage);
				Test_Variables.results.createNode(Test_Variables.lstAgreementManagement.get(i).failMessage);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
			}
		}
	}



	@Test (description="Test Case: Verify uploaded file",enabled= true, priority = 3) 
	public void VerifyUploadFile() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-07: Verify uploaded file is displayed in User Agreement dropdown", "This test case will verify uploaded file on Apply User Agreement page");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");

			Test_Variables.steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			Helper.driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			Test_Variables.steps.createNode("2. Search for uploaded file in user agreement dropdown");
			Helper.driver.findElement(By.cssSelector("#ApplyEulaId input")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(0));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.amDropdownSelect)).getText(), Test_Variables.lstAgreemmentManagementFileName.get(0));;
			Test_Variables.test.pass("The user was able to see the uploaded file in user agreement dropdown successfully");
			Test_Variables.results.createNode("The user was able to see the uploaded file in user agreement dropdown successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
			Helper.driver.findElement(By.id("progressbar-1")).click();
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to see the uploaded file in user agreement dropdown");
			Test_Variables.results.createNode("The user was not able to see the uploaded file in user agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to see the uploaded file in user agreement dropdown");
			Test_Variables.results.createNode("The user was not able to see the uploaded file in user agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: View file",enabled= true, priority = 4) 
	public void ViewFile() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-08: Verify user can view the uploaded file", "This test case will verify that user can view the uploaded file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.steps.createNode("1. Click on eye icon next to uploaded file; uploaded file opens in popup");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				int j= i-1;
				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("view-license-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("close-popup-modal")));
			Thread.sleep(1000);
			Assert.assertTrue(Helper.driver.findElement(By.id("close-popup-modal")).isDisplayed());
			Test_Variables.test.pass("The user was able to view the uploaded file successfully");
			Test_Variables.results.createNode("The user was able to view the uploaded file successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
			Helper.driver.findElement(By.id("close-popup-modal")).click();
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to view the uploaded file successfully");
			Test_Variables.results.createNode("The user was not able to view the uploaded file successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to view the uploaded file successfully");
			Test_Variables.results.createNode("The user was not able to view the uploaded file successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Delete from File",enabled= true, priority = 5) 
	public void DeleteFromFile() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-09: Verify user can delete the uploaded file from table list", "This test case will verify that user can delete the uploaded file from table list");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Click on delete icon next to uploaded file in table");
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			Thread.sleep(1000);

			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList2;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(1500);
					int j= i-1;
					Helper.driver.findElement(By.id("delete-license-"+j)).click();
					break;
				}
			}

			Thread.sleep(2500);
			Test_Variables.steps.createNode("2. Click on yes button from delete confirmation box");
			Helper.driver.findElement(By.id("btn-yes")).click();
			Thread.sleep(1000); 

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User agreement details deleted.");;
			Test_Variables.test.pass("The user was able to delete the uploaded file from the table below successfully");
			Test_Variables.results.createNode("The user was able to delete the uploaded file from the table below successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to delete the uploaded file from the table below successfully");
			Test_Variables.results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to delete the uploaded file from the table below successfully");
			Test_Variables.results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Delete from Grid",enabled= true, priority = 6) 
	public void DeleteFromGrid() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-10: Verify user can delete the uploaded file from grid", "This test case will verify that user can delete the uploaded file from grid");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Click on delete icon next to uploaded file in table");
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+Test_Variables.lstAgreementManagement.get(0).fileName);
			Thread.sleep(2000);


			for (int i=1; i<=15; i++) {
				String actualXpath = Test_Elements.amBeforeGrid+i+"]/p[1]";
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					int j = i-1;

					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", element); 
					Thread.sleep(500);
					Helper.driver.findElement(By.id("del-license-ic-"+j)).click();
					break;
				}
			}

			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Click on yes button from delete confirmation box");
			Helper.driver.findElement(By.id("btn-yes")).click();
			Thread.sleep(1000); 

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User agreement details deleted.");;
			Test_Variables.test.pass("The user was able to delete the uploaded file from the table below successfully");
			Test_Variables.results.createNode("The user was able to delete the uploaded file from the table below successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to delete the uploaded file from the table below successfully");
			Test_Variables.results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to delete the uploaded file from the table below successfully");
			Test_Variables.results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Rename file",enabled= true, priority = 7) 
	public void RenameFile() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-11: Verify user can rename the file", "This test case will verify that user can rename the uploaded file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.steps.createNode("1. Click on filename in user agreement name column and rename the file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Helper.driver.findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+Test_Variables.lstAgreementManagement.get(0).fileName);
			Thread.sleep(3000);

			List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(1)")).getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {

					ClickElement.clickByCss(Helper.driver, "tr:nth-child("+j+") td:nth-child(2)");
					Thread.sleep(3000);
					Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(1));
					break;
				}
			}

			Thread.sleep(500);
			ClickElement.clickById(Helper.driver, "progressbar-1");
			Thread.sleep(1000);

			for(int j = 1; j<count; j++) {

				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(1)")).getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					WebElement element = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)"));

					Assert.assertEquals(element.getText(), Test_Variables.lstAgreemmentManagementFileName.get(2));
					Test_Variables.test.pass("The user was able to rename the uploaded file successfully");
					Test_Variables.results.createNode("The user was able to rename the uploaded file successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to rename the uploaded file successfully");
			Test_Variables.results.createNode("The user was not able to rename the uploaded file successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to rename the uploaded file successfully");
			Test_Variables.results.createNode("The user was not able to rename the uploaded file successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Deactivate file",enabled= true, priority = 8) 
	public void DeactivateFile() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-12: Verify user can deactivate the uploaded file", "This test case will verify that user can deactivate the uploaded file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.steps.createNode("1. Click on deactivate toggle button in Actions column next to uploaded file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(1)")).getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("status-license-"+j)).click();
					break;
				}			
			}

			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-yes")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User agreement details updated.");;
			Test_Variables.test.pass("The user was able to deactivate the uploaded file successfully");
			Test_Variables.results.createNode("The user was able to deactivate the uploaded file successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to deactivate the uploaded file successfully");
			Test_Variables.results.createNode("The user was not able to deactivate the uploaded file successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to deactivate the uploaded file successfully");
			Test_Variables.results.createNode("The user was not able to dactivate the uploaded file successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Deactivate file",enabled= true, priority = 9) 
	public void VerifyDeactivateFile() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-13: Verify deactivated file is not shown in User Agreement dropdown", "This test case will verify deactivated file is not shown in User Agreement dropdown");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			WebElement element = Helper.driver.findElement(By.id("progressbar-2"));
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("progressbar-2")));

			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor js = (JavascriptExecutor) Helper.driver;
			js.executeScript("window.scrollBy(0,-100)");
			Thread.sleep(500);

			ClickElement.clickById(Helper.driver, "progressbar-2");
			Thread.sleep(500);
			Test_Variables.steps.createNode("2. Search for deactivated file");
			Helper.driver.findElement(By.cssSelector(".ng-input input")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));
			Thread.sleep(500);

			Assert.assertEquals(Helper.driver.findElement(By.cssSelector(".ng-option")).getText(), "No items found");
			Test_Variables.test.pass("The user was not able to see the deactivated file in User Agreement dropdown successfully");
			Test_Variables.results.createNode("The user was not able to see the deactivated file in User Agreement dropdown successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Deactivated file showed in User Agreement dropdown");
			Test_Variables.results.createNode("Deactivated file showed in User Agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Deactivated file showed in User Agreement dropdown");
			Test_Variables.results.createNode("Deactivated file showed in User Agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
		Helper.driver.findElement(By.id("progressbar-1")).click();
	}


	@Test (description="Test Case: Reactivate file",enabled= true, priority = 10) 
	public void ReactivateFile() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-14: Verify user can reactivate deactivated file", "This test case will verify that user can reactivate deactivated file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Click on Activate toggle button again");

			List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(1)")).getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("status-license-"+j)).click();
					break;
				}			
			}

			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-yes")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User agreement details updated.");;
			Test_Variables.test.pass("The user was able to reactivate the deactivated file successfully");
			Test_Variables.results.createNode("The user was able to deactivate the uploaded file successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to reactivate the deactivated file");
			Test_Variables.results.createNode("The user was not able to reactivate the deactivated file");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to reactivate the deactivated file");
			Test_Variables.results.createNode("The user was not able to reactivate the deactivated file");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Reactivate file",enabled= true, priority = 11) 
	public void VerifyReactivateFile() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-15: Verify file is displayed in User Agreement dropdown on reactivating", "This test case will verify file is displayed in User Agreement dropdown on reactivating");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			Test_Variables.preconditions.createNode("7. Again activate the deactivated file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			WebElement element = Helper.driver.findElement(By.id("progressbar-2"));
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("progressbar-2")));

			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor js = (JavascriptExecutor) Helper.driver;
			js.executeScript("window.scrollBy(0,-100)");
			Thread.sleep(500);

			ClickElement.clickById(Helper.driver, "progressbar-2");
			Thread.sleep(500);
			Test_Variables.steps.createNode("2. Search for uploaded file in user agreement dropdown");
			Helper.driver.findElement(By.cssSelector(".ng-input input")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.cssSelector(".ng-option")).getText(), Test_Variables.lstAgreemmentManagementFileName.get(2));
			Test_Variables.test.pass("The user was able to see the uploaded file in user agreement dropdown successfully on reactivating the file");
			Test_Variables.results.createNode("The user was able to see the uploaded file in user agreement dropdown successfully on reactivating the file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	

		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			Test_Variables.results.createNode("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			Test_Variables.results.createNode("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
		Helper.driver.findElement(By.id("progressbar-1")).click();
	}



	@Test (description="Test Case: Search User",enabled= true, priority = 12) 
	public void SearchUser() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-16: Verify search bar is functional", "This test case will verify that user is able to search for a user from search bar");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			Helper.driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			Test_Variables.steps.createNode("2. Search for user is search bar");
			Helper.driver.findElement(By.id("userSearchId")).sendKeys(Test_Variables.userFirstName);
			Thread.sleep(500);
			Helper.driver.findElement(By.id("userSearchId")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			if (Integer.parseInt(Helper.driver.findElement(By.xpath(Test_Elements.amSearchNo)).getText()) >=1) {
				Test_Variables.test.pass("The user was able to search for the user in User Agreement page");
				Test_Variables.results.createNode("The user was able to search for the user in User Agreement page");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
			}

		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to search for the user in User Agreement page");
			Test_Variables.results.createNode("The user was not able to search for the user in User Agreement page");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to search for the user in User Agreement page");
			Test_Variables.results.createNode("The user was not able to search for the user in User Agreement page");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
		Helper.driver.findElement(By.id("progressbar-1")).click();
	}


	@Test (description="Test Case: Invalid Selection",enabled= true, priority = 13) 
	public void InvalidSelection() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-17: Verify user cannot select organization without selecting User Agreement", "This test case will verify that user cannot select organization without selecting User Agreement");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("1. Go to apply agreement page");
			ClickElement.clickById(Helper.driver, "progressbar-2");
			Thread.sleep(3000);
			Test_Variables.steps.createNode("2. Click on organization radio button");

			Helper.driver.findElement(By.id("ic-orgnType-1")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Please select user agreement.");;
			Test_Variables.test.pass("The user was not able to select radio button without selecting agreement from dropdown");
			Test_Variables.results.createNode("The user was not able to select radio button without selecting agreement from dropdown");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was able to select radio button without selecting agreement from dropdown");
			Test_Variables.results.createNode("The user was able to select radio button without selecting agreement from dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was able to select radio button without selecting agreement from dropdown");
			Test_Variables.results.createNode("The user was able to select radio button without selecting agreement from dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
		Helper.driver.findElement(By.id("progressbar-1")).click();
	}	


	@Test (description="Test Case: Deactivate Assign Agreement",enabled= true, priority = 17) 
	public void DeactivateAssignAgreement() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-License-31: Verify user can deactivate assigned Agreement", "This test case will verify that user can deactivate assigned agreement");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
		Test_Variables.preconditions.createNode("5. Upload a file");
		Test_Variables.preconditions.createNode("6. Go to Apply Agreement page and assign user with an agreement");

		String filename = Helper.driver.findElement(By.id("eula-name-1")).getText();

		for (int i=0; i<2;i++) {

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			Helper.driver.findElement(By.id("progressbar-1")).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("1. Deactivate the assigned agreement");
			if (Helper.driver.findElements(By.cssSelector("#status-license-1 .wrapper-true")).size() == 1) {
				Helper.driver.findElement(By.id("status-license-1")).click();
			}
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("btn-yes")).click();
			try {
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
				Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User agreement details updated.");;
				Test_Variables.test.pass("The user was able to deactivate assigned agreement successfully");
				Test_Variables.results.createNode("The user was able to deactivate assigned agreement successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("The user was not able to deactivate assigned agreement");
				Test_Variables.results.createNode("The user was not able to deactivate assigned agreement");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail("The user was not able to deactivate assigned agreement");
				Test_Variables.results.createNode("The user was not able to deactivate assigned agreement");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
			}


			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstAgreementManagementDeactivate.get(i).testCaseTitle, Test_Variables.lstAgreementManagementDeactivate.get(i).testCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
				Test_Variables.preconditions.createNode("5. Upload a file");
				Test_Variables.preconditions.createNode("6. Go to Apply Agreement page and assign user with an agreement");
				Test_Variables.preconditions.createNode("7. Deactivated the uploaded agreement");
				Test_Variables.steps.createNode("1. Go to user management page");
				Helper.driver.get(Constants.url_user);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
				Thread.sleep(2500); 

				for (int j=1;j<Helper.driver.findElements(By.cssSelector("tr")).size(); j++) {
					if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
						WebElement scroll = Helper.driver.findElement(By.id("edit-user-"+j));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
						Thread.sleep(1000); 
						Helper.driver.findElement(By.id("edit-user-"+j)).click();
						break;
					}	
				}
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(8000); 
				Helper.driver.findElement(By.id("btn-next")).click();
				Thread.sleep(2000); 
				Helper.driver.findElement(By.id("btn-next")).click(); 
				Thread.sleep(2000); 
				Test_Variables.steps.createNode("3. Search for the agreement in User Agreement dropdown");

				ClickElement.clickById(Helper.driver, "euladdl");
				Thread.sleep(1000);
				SoftAssert softAssert = new SoftAssert();
				Helper.driver.findElement(By.cssSelector("#euladdl > div > div > div.ng-input > input[type=text]")).sendKeys(filename);
				Thread.sleep(1000); 

				if (i==0) {
					softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option-disabled")).size(), "1", "'No items found' did not displayed on deactivating the agreement");
				}
				if (i==1) {
					softAssert.assertEquals(Helper.driver.findElements(By.xpath("//*[contains(text(),'"+filename+"')]")).size(), 1, "Agreement did not displayed on deactivating the agreement");
				}
				Test_Variables.test.pass(Test_Variables.lstAgreementManagementDeactivate.get(i).passMessage);
				Test_Variables.results.createNode(Test_Variables.lstAgreementManagementDeactivate.get(i).passMessage);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail(Test_Variables.lstAgreementManagementDeactivate.get(i).failMessage);
				Test_Variables.results.createNode(Test_Variables.lstAgreementManagementDeactivate.get(i).failMessage);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(Test_Variables.lstAgreementManagementDeactivate.get(i).failMessage);
				Test_Variables.results.createNode(Test_Variables.lstAgreementManagementDeactivate.get(i).failMessage);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
			}

			Helper.driver.get(Constants.url_agreementManagement);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			Thread.sleep(1500);
		}
	}


	@Test (description="Test Case: Delete Assigned File",enabled= true, priority = 17) 
	public void DeleteAssignFile() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-40: Verify user cannot delete the uploaded file", "This test case will verify that user can delete the uploaded file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Assign the agreement to some user or organization");
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("progressbar-1")).click();
			Test_Variables.steps.createNode("2. Click on delete icon next to uploaded file in table");
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("delete-license-0")).click();

			Test_Variables.steps.createNode("2. Click on yes button from delete confirmation box");
			Helper.driver.findElement(By.id("btn-yes")).click();		
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000); 
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "This user agreement is already assigned. It cannot be deleted.");;
			Test_Variables.test.pass("The user was not able to delete the assigned agreement successfully");
			Test_Variables.results.createNode("The user was not able to delete the assigned agreement successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was able to delete the assigned agreement");
			Test_Variables.results.createNode("The user was able to delete the assigned agreement");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was able to delete the assigned agreement");
			Test_Variables.results.createNode("The user was able to delete the assigned agreement");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 18) 
	public void DeleteFile() throws InterruptedException, IOException {

		List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
		int count = rows.size();
		for(int j = 1; j<count; j++) {
			if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(1)")).getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
				int x = j-1;
				Helper.driver.findElement(By.id("delete-license-"+x)).click();
				break;
			}
		}
		Thread.sleep(2000);
		Helper.driver.findElement(By.id("btn-yes")).click();	
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//Helper.driver.close();
	}
}
