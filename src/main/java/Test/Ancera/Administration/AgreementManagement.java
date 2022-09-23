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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Test_Variables;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

public class AgreementManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Agreement_Management"+date+".html");
		spark.config().setReportName("Agreement Management Test Report"); 
		config();
		ConfigureLogin.login();
	}


	@Test (priority = 1) 
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen(url_agreementManagement, "Agreement Management", AgreementManagementReportPath, amTitle);
	}


	@Test (description="Test Case: Upload file",enabled= true, priority = 2) 
	public void LicenseUpload() throws InterruptedException, IOException {

		for (int i=0; i<=lstAgreementManagement.size(); i++) {
			try {
				test = extent.createTest(lstAgreementManagement.get(i).testCaseTitle, lstAgreementManagement.get(i).testCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Agreement Management");

				Thread.sleep(2000);
				steps.createNode("1. Click on dotted box; file explorer opens");
				steps.createNode("2. Upload "+lstAgreementManagement.get(i).fileType+"and verify the file is uploaded and visible in box");
				test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
				driver.findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+lstAgreementManagement.get(i).fileName);

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alrt")));
				Thread.sleep(1000);
				Assert.assertEquals(driver.findElement(By.id("message")).getText(), lstAgreementManagement.get(i).alertMessage);

				test.pass(lstAgreementManagement.get(i).passMessage);
				results.createNode(lstAgreementManagement.get(i).passMessage);
				test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
				driver.findElement(By.cssSelector("#alrt > button")).click();
			}
			catch(AssertionError er) {
				test.fail(lstAgreementManagement.get(i).failMessage);
				results.createNode(lstAgreementManagement.get(i).failMessage);
				saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(lstAgreementManagement.get(i).failMessage);
				results.createNode(lstAgreementManagement.get(i).failMessage);
				saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
			}
		}
	}



	@Test (description="Test Case: Verify uploaded file",enabled= true, priority = 3) 
	public void VerifyUploadFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-07: Verify uploaded file is displayed in User Agreement dropdown", "This test case will verify uploaded file on Apply User Agreement page");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			steps.createNode("2. Search for uploaded file in user agreement dropdown");
			driver.findElement(By.cssSelector("#ApplyEulaId input")).sendKeys(lstAgreemmentManagementFileName.get(0));
			Thread.sleep(1000);

			Assert.assertEquals(driver.findElement(By.xpath(amDropdownSelect)).getText(), lstAgreemmentManagementFileName.get(0));;
			test.pass("The user was able to see the uploaded file in user agreement dropdown successfully");
			results.createNode("The user was able to see the uploaded file in user agreement dropdown successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
			driver.findElement(By.id("progressbar-1")).click();
		}
		catch(AssertionError er) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: View file",enabled= true, priority = 4) 
	public void ViewFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-08: Verify user can view the uploaded file", "This test case will verify that user can view the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			steps.createNode("1. Click on eye icon next to uploaded file; uploaded file opens in popup");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			for (int i=1; i<=10; i++) {
				String actualXpath = amBeforelist+i+amAfterList;
				WebElement element = driver.findElement(By.xpath(actualXpath));

				int j= i-1;
				if (element.getText().equals(lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					driver.findElement(By.id("view-license-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("close-popup-modal")));
			Thread.sleep(1000);
			Assert.assertTrue(driver.findElement(By.id("close-popup-modal")).isDisplayed());
			test.pass("The user was able to view the uploaded file successfully");
			results.createNode("The user was able to view the uploaded file successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
			driver.findElement(By.id("close-popup-modal")).click();
		}
		catch(AssertionError er) {
			test.fail("The user was not able to view the uploaded file successfully");
			results.createNode("The user was not able to view the uploaded file successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to view the uploaded file successfully");
			results.createNode("The user was not able to view the uploaded file successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Delete from File",enabled= true, priority = 5) 
	public void DeleteFromFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-09: Verify user can delete the uploaded file from table list", "This test case will verify that user can delete the uploaded file from table list");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			steps.createNode("1. Click on delete icon next to uploaded file in table");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			Thread.sleep(1000);

			for (int i=1; i<=10; i++) {
				String actualXpath = amBeforelist+i+amAfterList2;
				WebElement element = driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(1500);
					int j= i-1;
					driver.findElement(By.id("delete-license-"+j)).click();
					break;
				}
			}

			Thread.sleep(2500);
			steps.createNode("2. Click on yes button from delete confirmation box");
			driver.findElement(By.id("btn-yes")).click();
			Thread.sleep(1000); 

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "User agreement details deleted.");;
			test.pass("The user was able to delete the uploaded file from the table below successfully");
			results.createNode("The user was able to delete the uploaded file from the table below successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Delete from Grid",enabled= true, priority = 6) 
	public void DeleteFromGrid() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-10: Verify user can delete the uploaded file from grid", "This test case will verify that user can delete the uploaded file from grid");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			steps.createNode("1. Click on delete icon next to uploaded file in table");
			Thread.sleep(1000);

			driver.findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+lstAgreementManagement.get(0).fileName);
			Thread.sleep(2000);


			for (int i=1; i<=15; i++) {
				String actualXpath = amBeforeGrid+i+"]/p[1]";
				WebElement element = driver.findElement(By.xpath(actualXpath));

				if (element.getText().equals(lstAgreemmentManagementFileName.get(0))) {
					Thread.sleep(500);
					int j = i-1;

					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element); 
					Thread.sleep(500);
					driver.findElement(By.id("del-license-ic-"+j)).click();
					break;
				}
			}

			Thread.sleep(1000);
			steps.createNode("2. Click on yes button from delete confirmation box");
			driver.findElement(By.id("btn-yes")).click();
			Thread.sleep(1000); 

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "User agreement details deleted.");;
			test.pass("The user was able to delete the uploaded file from the table below successfully");
			results.createNode("The user was able to delete the uploaded file from the table below successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to delete the uploaded file from the table below successfully");
			results.createNode("The user was not able to delete the uploaded file from the table below successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Rename file",enabled= true, priority = 7) 
	public void RenameFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-11: Verify user can rename the file", "This test case will verify that user can rename the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			steps.createNode("1. Click on filename in user agreement name column and rename the file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			driver.findElement(By.cssSelector("#file-license")).sendKeys(System.getProperty("user.dir")+lstAgreementManagement.get(0).fileName);
			Thread.sleep(3000);

			List<WebElement> rows = driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(0))) {

					ClickElement.clickByCss(driver, "tr:nth-child("+j+") td:nth-child(2)");
			//		driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).clear();
					Thread.sleep(3000);
System.out.println(1);
					
					for (int i=1; i <=13; i++) {
						driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					}
					//					driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					//					driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					//					driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					//					driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(Keys.BACK_SPACE);
					driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2) label")).sendKeys(lstAgreemmentManagementFileName.get(1));
					break;
				}
			}

			Thread.sleep(500);
			ClickElement.clickById(driver, "progressbar-1");
			Thread.sleep(1000);

			for(int j = 1; j<count; j++) {

				if (driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
					WebElement element = driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)"));

					Assert.assertEquals(element.getText(), lstAgreemmentManagementFileName.get(1));
					test.pass("The user was able to rename the uploaded file successfully");
					results.createNode("The user was able to rename the uploaded file successfully");
					test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
					saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);
				}
			}
		}
		catch(AssertionError er) {
			test.fail("The user was not able to rename the uploaded file successfully");
			results.createNode("The user was not able to rename the uploaded file successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to rename the uploaded file successfully");
			results.createNode("The user was not able to rename the uploaded file successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Deactivate file",enabled= true, priority = 8) 
	public void DeactivateFile() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-License-12: Verify user can deactivate the uploaded file", "This test case will verify that user can deactivate the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			steps.createNode("1. Click on deactivate toggle button in Actions column next to uploaded file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			List<WebElement> rows = driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
					Thread.sleep(500);
					driver.findElement(By.id("status-license-"+j)).click();
					break;
				}			
			}

			Thread.sleep(1000);
			driver.findElement(By.id("btn-yes")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "User agreement details updated.");;
			test.pass("The user was able to deactivate the uploaded file successfully");
			results.createNode("The user was able to deactivate the uploaded file successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to deactivate the uploaded file successfully");
			results.createNode("The user was not able to deactivate the uploaded file successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to deactivate the uploaded file successfully");
			results.createNode("The user was not able to dactivate the uploaded file successfully");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Deactivate file",enabled= true, priority = 9) 
	public void VerifyDeactivateFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-13: Verify deactivated file is not shown in User Agreement dropdown", "This test case will verify deactivated file is not shown in User Agreement dropdown");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			WebElement element = driver.findElement(By.id("progressbar-2"));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("progressbar-2")));

			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-100)");
			Thread.sleep(500);

			ClickElement.clickById(driver, "progressbar-2");
			Thread.sleep(500);
			steps.createNode("2. Search for deactivated file");
			driver.findElement(By.cssSelector(".ng-input input")).sendKeys(lstAgreemmentManagementFileName.get(1));
			Thread.sleep(500);

			Assert.assertEquals(driver.findElement(By.cssSelector(".ng-option")).getText(), "No items found");
			test.pass("The user was not able to see the deactivated file in User Agreement dropdown successfully");
			results.createNode("The user was not able to see the deactivated file in User Agreement dropdown successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Deactivated file showed in User Agreement dropdown");
			results.createNode("Deactivated file showed in User Agreement dropdown");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Deactivated file showed in User Agreement dropdown");
			results.createNode("Deactivated file showed in User Agreement dropdown");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
		driver.findElement(By.id("progressbar-1")).click();
	}


	@Test (description="Test Case: Reactivate file",enabled= true, priority = 10) 
	public void ReactivateFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-14: Verify user can reactivate deactivated file", "This test case will verify that user can reactivate deactivated file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			steps.createNode("1. Click on Activate toggle button again");

			List<WebElement> rows = driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
			int count = rows.size();

			for(int j = 1; j<count; j++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
					Thread.sleep(500);
					driver.findElement(By.id("status-license-"+j)).click();
					break;
				}			
			}

			Thread.sleep(1000);
			driver.findElement(By.id("btn-yes")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "User agreement details updated.");;
			test.pass("The user was able to reactivate the deactivated file successfully");
			results.createNode("The user was able to deactivate the uploaded file successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("The user was not able to reactivate the deactivated file");
			results.createNode("The user was not able to reactivate the deactivated file");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to reactivate the deactivated file");
			results.createNode("The user was not able to reactivate the deactivated file");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Reactivate file",enabled= true, priority = 11) 
	public void VerifyReactivateFile() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-15: Verify file is displayed in User Agreement dropdown on reactivating", "This test case will verify file is displayed in User Agreement dropdown on reactivating");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			preconditions.createNode("6. Click on deactivate toggle button in Actions column next to uploaded file");
			preconditions.createNode("7. Again activate the deactivated file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			WebElement element = driver.findElement(By.id("progressbar-2"));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("progressbar-2")));

			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-100)");
			Thread.sleep(500);

			ClickElement.clickById(driver, "progressbar-2");
			Thread.sleep(500);
			steps.createNode("2. Search for uploaded file in user agreement dropdown");
			driver.findElement(By.cssSelector(".ng-input input")).sendKeys(lstAgreemmentManagementFileName.get(1));
			Thread.sleep(1000);

			Assert.assertEquals(driver.findElement(By.cssSelector(".ng-option")).getText(), lstAgreemmentManagementFileName.get(1));
			test.pass("The user was able to see the uploaded file in user agreement dropdown successfully on reactivating the file");
			results.createNode("The user was able to see the uploaded file in user agreement dropdown successfully on reactivating the file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	

		}
		catch(AssertionError er) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			results.createNode("The user was not able to see the uploaded file in user agreement dropdown on reactivating the file");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
		driver.findElement(By.id("progressbar-1")).click();
	}



	@Test (description="Test Case: Search User",enabled= true, priority = 12) 
	public void SearchUser() throws InterruptedException, IOException {

		try {
			test = extent.createTest("AN-License-16: Verify search bar is functional", "This test case will verify that user is able to search for a user from search bar");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			steps.createNode("1. Go to Apply User Agreement page");
			Thread.sleep(500);
			driver.findElement(By.id("progressbar-2")).click();
			Thread.sleep(500);
			steps.createNode("2. Search for user is search bar");
			driver.findElement(By.id("userSearchId")).sendKeys(Test_Variables.userFirstName);
			Thread.sleep(500);
			driver.findElement(By.id("userSearchId")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			if (Integer.parseInt(driver.findElement(By.xpath(amSearchNo)).getText()) >=1) {
				test.pass("The user was able to search for the user in User Agreement page");
				results.createNode("The user was able to search for the user in User Agreement page");
				test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
			}

		}
		catch(AssertionError er) {
			test.fail("The user was not able to search for the user in User Agreement page");
			results.createNode("The user was not able to search for the user in User Agreement page");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was not able to search for the user in User Agreement page");
			results.createNode("The user was not able to search for the user in User Agreement page");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
		driver.findElement(By.id("progressbar-1")).click();
	}


	@Test (description="Test Case: Invalid Selection",enabled= true, priority = 13) 
	public void InvalidSelection() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-License-17: Verify user cannot select organization without selecting User Agreement", "This test case will verify that user cannot select organization without selecting User Agreement");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			Thread.sleep(1000);
			steps.createNode("1. Go to apply agreement page");
			ClickElement.clickById(driver, "progressbar-2");
			Thread.sleep(3000);
			steps.createNode("2. Click on organization radio button");

			driver.findElement(By.id("ic-orgnType-1")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Please select user agreement.");;
			test.pass("The user was not able to select radio button without selecting agreement from dropdown");
			results.createNode("The user was not able to select radio button without selecting agreement from dropdown");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("The user was able to select radio button without selecting agreement from dropdown");
			results.createNode("The user was able to select radio button without selecting agreement from dropdown");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was able to select radio button without selecting agreement from dropdown");
			results.createNode("The user was able to select radio button without selecting agreement from dropdown");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
		driver.findElement(By.id("progressbar-1")).click();
	}	


	@Test (description="Test Case: Deactivate Assign Agreement",enabled= true, priority = 17) 
	public void DeactivateAssignAgreement() throws InterruptedException, IOException {

		test = extent.createTest("AN-License-31: Verify user can deactivate assigned Agreement", "This test case will verify that user can deactivate assigned agreement");
		preconditions = test.createNode(Scenario.class, PreConditions);
		steps = test.createNode(Scenario.class, Steps);
		results = test.createNode(Scenario.class, Results);

		preconditions.createNode("1. Go to url " +url_login);
		preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		preconditions.createNode("3. Hover to sidebar to expand the menu");
		preconditions.createNode("4. Click on Administration and select Agreement Management");
		preconditions.createNode("5. Upload a file");
		preconditions.createNode("6. Go to Apply Agreement page and assign user with an agreement");

		String filename = driver.findElement(By.id("eula-name-1")).getText();

		for (int i=0; i<2;i++) {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			driver.findElement(By.id("progressbar-1")).click();
			Thread.sleep(1000);
			steps.createNode("1. Deactivate the assigned agreement");
			if (driver.findElements(By.cssSelector("#status-license-1 .wrapper-true")).size() == 1) {
				driver.findElement(By.id("status-license-1")).click();
			}
			Thread.sleep(2000);
			driver.findElement(By.id("btn-yes")).click();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
				Assert.assertEquals(driver.findElement(By.id("message")).getText(), "User agreement details updated.");;
				test.pass("The user was able to deactivate assigned agreement successfully");
				results.createNode("The user was able to deactivate assigned agreement successfully");
				test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("The user was not able to deactivate assigned agreement");
				results.createNode("The user was not able to deactivate assigned agreement");
				saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("The user was not able to deactivate assigned agreement");
				results.createNode("The user was not able to deactivate assigned agreement");
				saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
			}


			try {
				test = extent.createTest(lstAgreementManagementDeactivate.get(i).testCaseTitle, lstAgreementManagementDeactivate.get(i).testCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Agreement Management");
				preconditions.createNode("5. Upload a file");
				preconditions.createNode("6. Go to Apply Agreement page and assign user with an agreement");
				preconditions.createNode("7. Deactivated the uploaded agreement");
				steps.createNode("1. Go to user management page");
				driver.get(url_user);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
				Thread.sleep(2500); 

				openEditUserPopup(login_email);
				click(popupNextButton);
				Thread.sleep(500);	
				click(popupNextButton);
				Thread.sleep(500);	
				
				steps.createNode("3. Search for the agreement in User Agreement dropdown");
				ClickElement.clickById(driver, "euladdl");
				Thread.sleep(1000);
				SoftAssert softAssert = new SoftAssert();
				driver.findElement(By.cssSelector("#euladdl > div > div > div.ng-input > input[type=text]")).sendKeys(filename);
				Thread.sleep(1000); 

				if (i==0) {
					softAssert.assertEquals(driver.findElements(By.cssSelector(".ng-option-disabled")).size(), "1", "'No items found' did not displayed on deactivating the agreement");
				}
				if (i==1) {
					softAssert.assertEquals(driver.findElements(By.xpath("//*[contains(text(),'"+filename+"')]")).size(), 1, "Agreement did not displayed on deactivating the agreement");
				}
				test.pass(lstAgreementManagementDeactivate.get(i).passMessage);
				results.createNode(lstAgreementManagementDeactivate.get(i).passMessage);
				test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail(lstAgreementManagementDeactivate.get(i).failMessage);
				results.createNode(lstAgreementManagementDeactivate.get(i).failMessage);
				saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(lstAgreementManagementDeactivate.get(i).failMessage);
				results.createNode(lstAgreementManagementDeactivate.get(i).failMessage);
				saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
			}

			driver.get(url_agreementManagement);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			Thread.sleep(1500);
		}
	}


	@Test (description="Test Case: Delete Assigned File",enabled= true, priority = 17) 
	public void DeleteAssignFile() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-License-40: Verify user cannot delete the uploaded file", "This test case will verify that user can delete the uploaded file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Agreement Management");
			preconditions.createNode("5. Upload a file");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));

			steps.createNode("1. Assign the agreement to some user or organization");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("progressbar-1")));
			Thread.sleep(1000);
			driver.findElement(By.id("progressbar-1")).click();
			steps.createNode("2. Click on delete icon next to uploaded file in table");
			Thread.sleep(1000);
			driver.findElement(By.id("delete-license-0")).click();

			steps.createNode("2. Click on yes button from delete confirmation box");
			driver.findElement(By.id("btn-yes")).click();		
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000); 
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "This user agreement is already assigned. It cannot be deleted.");;
			test.pass("The user was not able to delete the assigned agreement successfully");
			results.createNode("The user was not able to delete the assigned agreement successfully");
			test.addScreenCaptureFromPath(getScreenshot("Agreement Management", AgreementManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AgreementManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("The user was able to delete the assigned agreement");
			results.createNode("The user was able to delete the assigned agreement");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("The user was able to delete the assigned agreement");
			results.createNode("The user was able to delete the assigned agreement");
			saveResultNew(ITestResult.FAILURE, AgreementManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 18) 
	public void DeleteFile() throws InterruptedException, IOException {

		List<WebElement> rows = driver.findElements(By.cssSelector("[class='ng-tns-c4-0'] tr"));
		int count = rows.size();
		for(int j = 1; j<count; j++) {
			if (driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(2)")).getText().equals(lstAgreemmentManagementFileName.get(1))) {
				int x = j-1;
				driver.findElement(By.id("delete-license-"+x)).click();
				break;
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.id("btn-yes")).click();	
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
		//driver.close();
	}
}
