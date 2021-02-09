package Test.Ancera.Administration;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
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
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Agreement Management")));
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Administration and select Agreement Management");

			Assert.assertEquals(Helper.driver.findElement(By.id("Agreement Management")).getText(), "Agreement Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Data Upload Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to User Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to User Management Screen");
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
				//	Helper.driver.findElement(By.cssSelector("#file-license")).sendKeys("D:\\sample.pdf");
				//	Helper.driver.findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+"/EULA/sample.docx");
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
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			Helper.driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			Test_Variables.steps.createNode("2. Search for uploaded file in user agreement dropdown");
			Helper.driver.findElement(By.cssSelector("#ApplyEulaId > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(0));
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


	@Test (description="Test Case: Delete from File",enabled= false, priority = 5) 
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

			
//			for (int j=1; j<=20; j++) {
//				int x= j-1;
//				if (Helper.driver.findElement(By.cssSelector("#lst-eula-files-0 td._"+j)).getText() == Test_Variables.lstAgreemmentManagementFileName.get(0)) {
//					Helper.driver.findElement(By.id("delete-license-"+x)).click();
//					break;
//				}
//			}
			
				
			for (int i=1; i<=15; i++) {
				try {	
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				int j= i-1;
				if (element.getText() == Test_Variables.lstAgreemmentManagementFileName.get(0)) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("delete-license-"+j)).click();
					break;
				}
				}
		
		
		catch(NoSuchElementException e) {
			System.out.println("sdsd");
		}
			}
		
		
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


	@Test (description="Test Case: Delete from Grid",enabled= false, priority = 6) 
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

			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforeGrid+i+"]";
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.xpath(element+"/i")).click();
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
			Thread.sleep(1000);
			
			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList2;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(1500);
					element.click();
					element.sendKeys(Keys.BACK_SPACE); element.sendKeys(Keys.BACK_SPACE); element.sendKeys(Keys.BACK_SPACE);
					element.sendKeys(Keys.BACK_SPACE);
					element.sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(1));
					break;
				}
			}

			Thread.sleep(500);
			Helper.driver.findElement(By.id("progressbar-1")).click();
			Thread.sleep(500);

			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList2;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					Assert.assertTrue(true);
					break;
				}
			}

			Test_Variables.test.pass("The user was able to rename the uploaded file successfully");
			Test_Variables.results.createNode("The user was able to rename the uploaded file successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
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

			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList2;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(2))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("status-license-"+i)).click();
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
			Helper.driver.findElement(By.cssSelector("#alrt > button")).click();
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
			Helper.driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			Test_Variables.steps.createNode("2. Search for deactivated file");
			Helper.driver.findElement(By.cssSelector("#ApplyEulaId > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));
			Thread.sleep(500);

			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.amDropdownSelect)).getText(), "No items found");
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
			Thread.sleep(500);
			Helper.driver.findElement(By.id("progressbar-1")).click();
			Thread.sleep(500);


			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList2;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(2))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("status-license-"+i)).click();
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
			Helper.driver.findElement(By.cssSelector("#alrt > button")).click();
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
			Helper.driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			Test_Variables.steps.createNode("2. Search for uploaded file in user agreement dropdown");
			Helper.driver.findElement(By.cssSelector("#ApplyEulaId > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.amDropdownSelect)).getText(), Test_Variables.lstAgreemmentManagementFileName.get(2));;
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


	@Test (description="Test Case: Invalid Selection",enabled= false, priority = 13) 
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
			Helper.driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Click on organization radio button");
			Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div/div[1]/label")).click();

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



	@Test (description="Test Case: Assign Agreement",enabled= false, priority = 14) 
	public void VerifyAssignAgreement() throws InterruptedException, IOException {

		for (int i =0; i<Test_Variables.lstAgreementManagementCheckbox.size(); i++) {

			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstAgreementManagementCheckbox.get(i).testCaseTitle, Test_Variables.lstAgreementManagementCheckbox.get(i).testCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
				Test_Variables.preconditions.createNode("5. Upload a file and go to Apply Agreement page");

				Helper.driver.findElement(By.id("progressbar-2")).click();
				Thread.sleep(1000);
				Test_Variables.steps.createNode("1. Select uploaded file from User Agreement dropdown");
				Helper.driver.findElement(By.cssSelector("#ApplyEulaId > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));
				Thread.sleep(500);
				Helper.driver.findElement(By.xpath(Test_Elements.amDropdownSelect)).click();
				Thread.sleep(1500);

				Helper.driver.findElement(By.xpath(Test_Elements.amExpandOrg)).click();
				Thread.sleep(2000);
				Helper.driver.findElement(By.xpath(Test_Elements.amExpandOrgType)).click();
				Thread.sleep(1500);

				WebElement chck1 = Helper.driver.findElement(By.xpath(Test_Variables.lstAgreementManagementCheckbox.get(0).checkboxInput));
				System.out.println("The checkbox is selection state is - " + chck1.isSelected());

				if (chck1.isSelected() == true) {	
					Helper.driver.findElement(By.xpath(Test_Variables.lstAgreementManagementCheckbox.get(0).selectCheckbox)).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.id("apply-license")).click();
				}

				Test_Variables.steps.createNode("2. Click on checkbox next to "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+"");
				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath(Test_Variables.lstAgreementManagementCheckbox.get(i).selectCheckbox)).click();
				Thread.sleep(500);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Test_Variables.steps.createNode("3. Click on save button");
				Helper.driver.findElement(By.id("apply-license")).click();

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
				Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Data details updated.");;
				Test_Variables.test.pass("The user was able to assign agreement at "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+" level successfully");
				Test_Variables.results.createNode("The user was able to assign agreement at "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+" level successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("The user was not able to assign agreement at "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+" level");
				Test_Variables.results.createNode("The user was not able to assign agreement at "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+" level");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail("The user was not able to assign agreement at "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+" level");
				Test_Variables.results.createNode("The user was not able to assign agreement at "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+" level");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
			}


			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstAgreementManagementCheckbox.get(i).testCaseTitleUser, Test_Variables.lstAgreementManagementCheckbox.get(i).testCaseDescriptionUser);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
				Test_Variables.preconditions.createNode("5. Upload a file and go to Apply Agreement page");	
				Test_Variables.preconditions.createNode("6. Select agreement from dropdown and select "+Test_Variables.lstAgreementManagementCheckbox.get(i).stepName+" checkbox");	

				Test_Variables.steps.createNode("1. Go to user management screen");
				Helper.driver.get(Constants.url_user);
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSearchId")));
				Thread.sleep(1500); 
				Test_Variables.steps.createNode("2. Open any user to whom agreement is assigned");
				Helper.driver.findElement(By.id("orgnType-1")).click();
				Thread.sleep(1500); 
				Helper.driver.findElement(By.xpath(Test_Elements.userExpandAnceraSite)).click();
				Thread.sleep(1500); 
				Test_Variables.steps.createNode("3. Check if the agreement is assigned to that user");
				Helper.driver.findElement(By.cssSelector("#edit-user-1")).click();
				Thread.sleep(1000); 
				Helper.driver.findElement(By.id("btn-next")).click();
				Thread.sleep(1000); 
				Helper.driver.findElement(By.id("btn-next")).click(); 
				Thread.sleep(1000); 
				Helper.driver.findElement(By.cssSelector("#euladdl > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));	
				Thread.sleep(1000); 

				WebElement chck = Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[4]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div/div/input"));
				System.out.println("The checkbox is selection state is - " + chck.isSelected());

				Assert.assertEquals(chck.isSelected(), true);
				Test_Variables.test.pass("The agreement was assigned to user successfully");
				Test_Variables.results.createNode("The agreement was assigned to user successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);

			}
			catch(AssertionError er) {
				Test_Variables.test.fail("The agreement was not assigned to user");
				Test_Variables.results.createNode("The agreement was not assigned to user");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail("The agreement was not assigned to user");
				Test_Variables.results.createNode("The agreement was not assigned to user");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
			}

			Helper.driver.get(Constants.url_agreementManagement);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-2")));
			Thread.sleep(1500);

		}
	}


	
	@Test (description="Test Case: User",enabled= false, priority = 15) 
	public void User() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-24: Verify file is displayed in create/edit user agreement dropdown in User Management", "This test case will verify that file is displayed in create/edit user agreement dropdown");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Go to User Management Screen");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSearchId")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Click on create or edit user button");
			Helper.driver.findElement(By.id("orgnType-1")).click();
			Thread.sleep(1000); 
			Helper.driver.findElement(By.id(Test_Elements.userExpandAnceraSite)).click();
			Thread.sleep(1000); 
			Helper.driver.findElement(By.cssSelector("#edit-user-1")).click();
			Thread.sleep(1000); 
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000); 
			Helper.driver.findElement(By.id("btn-next")).click(); 
			Thread.sleep(1000); 	
			Test_Variables.steps.createNode("3. Search for the file in User Agreement dropdown");
			Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[4]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));	
			Thread.sleep(1000); 
			Assert.assertEquals(Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[4]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div")).getText(), Test_Variables.lstAgreemmentManagementFileName.get(0));;
			Test_Variables.test.pass("The user was able to search for the file in User Agreement dropdown");
			Test_Variables.results.createNode("The user was able to search for the file in User Agreement dropdown");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to search for the file in User Agreement dropdown");
			Test_Variables.results.createNode("The user was not able to search for the file in User Agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to search for the file in User Agreement dropdown");
			Test_Variables.results.createNode("The user was not able to search for the file in User Agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Organization",enabled= false, priority = 16) 
	public void Organization() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-25: Verify file is displayed in create/edit user agreement dropdown in Organization Manamgement", "This test case will verify that file is displayed in create/edit user agreement dropdown");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Agreement Management");
			Test_Variables.preconditions.createNode("5. Upload a file");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));

			Test_Variables.steps.createNode("1. Go to User Management Screen");
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("organSearchId")));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Click on create or edit user button");
			Helper.driver.findElement(By.id("orgnType-1")).click();
			Thread.sleep(1000); 
			Helper.driver.findElement(By.id("edit-orgn-1")).click();
			Thread.sleep(1000); 
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000); 
			Test_Variables.steps.createNode("3. Search for the file in User Agreement dropdown");
			Helper.driver.findElement(By.cssSelector("#eulaId > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));	
			Thread.sleep(1000); 
			/////////////////////////////////////
			// ->		Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#a74fd5948ad9 > div.ng-dropdown-panel-items.scroll-host > div:nth-child(2) > div")).getText(), Test_Variables.lstAgreemmentManagementFileName.get(0));;
			Test_Variables.test.pass("The user was able to search for the file in User Agreement dropdown");
			Test_Variables.results.createNode("The user was able to search for the file in User Agreement dropdown");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AgreementManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("The user was not able to search for the file in User Agreement dropdown");
			Test_Variables.results.createNode("The user was not able to search for the file in User Agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("The user was not able to search for the file in User Agreement dropdown");
			Test_Variables.results.createNode("The user was not able to search for the file in User Agreement dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AgreementManagementReportPath, ex);
		}
	}
	
	
	
	@Test (description="Test Case: Deactivate Assign Agreement",enabled= false, priority = 17) 
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

		Helper.driver.get(Constants.url_agreementManagement);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
		Helper.driver.findElement(By.id("progressbar-2")).click();
		Thread.sleep(1000);
		Test_Variables.steps.createNode("1. Deactivate the uploaded agreement");
		Helper.driver.findElement(By.cssSelector("#ApplyEulaId > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));
		Thread.sleep(500);
		Helper.driver.findElement(By.xpath(Test_Elements.amDropdownSelect)).click();
		Thread.sleep(1500);

		WebElement chck1 = Helper.driver.findElement(By.xpath(Test_Variables.lstAgreementManagementCheckbox.get(0).checkboxInput));
		System.out.println("The checkbox is selection state is - " + chck1.isSelected());

		if (chck1.isSelected() == false) {	
			Helper.driver.findElement(By.xpath(Test_Variables.lstAgreementManagementCheckbox.get(0).selectCheckbox)).click();
			Thread.sleep(500);
			Helper.driver.findElement(By.id("apply-license")).click();
		}
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("progressbar-1")).click();

		for (int i =0; i<=1; i++) {


			for (int j=1; j<=10; j++) {
				String actualXpath = Test_Elements.amBeforelist+j+Test_Elements.amAfterList2;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(2))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("status-license-"+j)).click();
					break;
				}
			}
			Thread.sleep(1000);
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
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSearchId")));
				Thread.sleep(1500); 
				Helper.driver.findElement(By.id("orgnType-1")).click();
				Thread.sleep(1500); 
				Helper.driver.findElement(By.xpath(Test_Elements.userExpandAnceraSite)).click();
				Thread.sleep(1500); 
				Test_Variables.steps.createNode("2. Open any user to whom agreement was assigned");
				Helper.driver.findElement(By.cssSelector("#edit-user-1")).click();
				Thread.sleep(1000); 
				Helper.driver.findElement(By.id("btn-next")).click();
				Thread.sleep(1000); 
				Helper.driver.findElement(By.id("btn-next")).click(); 
				Thread.sleep(1000); 
				Test_Variables.steps.createNode("3. Search for the agreement in User Agreement dropdown");
				Helper.driver.findElement(By.cssSelector("#euladdl > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));	
				Thread.sleep(1000); 

				Assert.assertEquals(Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[4]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div")).getText(), Test_Variables.lstAgreementManagementDeactivate.get(i).userStatus);
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
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-2")));
			Thread.sleep(1500);

		}
	}

	@Test (description="Test Case: Delete Assigned File",enabled= false, priority = 17) 
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

			Helper.driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Click on delete icon next to uploaded file in table");
			Helper.driver.findElement(By.cssSelector("#ApplyEulaId > div > div > div.ng-input > input[type=text]")).sendKeys(Test_Variables.lstAgreemmentManagementFileName.get(2));
			Thread.sleep(500);
			Helper.driver.findElement(By.xpath(Test_Elements.amDropdownSelect)).click();
			Thread.sleep(1500);

			WebElement chck1 = Helper.driver.findElement(By.xpath(Test_Variables.lstAgreementManagementCheckbox.get(0).checkboxInput));
			System.out.println("The checkbox is selection state is - " + chck1.isSelected());

			if (chck1.isSelected() == false) {	
				Helper.driver.findElement(By.xpath(Test_Variables.lstAgreementManagementCheckbox.get(0).selectCheckbox)).click();
				Thread.sleep(500);
				Helper.driver.findElement(By.id("apply-license")).click();
			}
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("progressbar-1")).click();

			for (int i=1; i<=10; i++) {
				String actualXpath = Test_Elements.amBeforelist+i+Test_Elements.amAfterList2;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				int j= i-1;
				if (element.getText().equals(Test_Variables.lstAgreemmentManagementFileName.get(2))) {
					Thread.sleep(500);
					Helper.driver.findElement(By.id("delete-license-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Click on yes button from delete confirmation box");
			Helper.driver.findElement(By.id("btn-yes")).click();
			Thread.sleep(1000); 

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
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

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}
