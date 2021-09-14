package Test.Ancera.Administration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.RMModel;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class ReportsManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new 		ExtentSparkReporter("target/Reports/Administration_Reports_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Reports Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Reports Management Screen",enabled=true, priority = 2) 
	public void NavigateRM() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-01: Verify user can navigate to Reports Management Screen", "This test case will verify user can navigate to Reports Management Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-report-role")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("Reports Management")).getText();
			String expected = "Reports Management";

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Administration and select Report Management");

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Report Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));		
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("User did not navigate to Report Management Screen");
			Test_Variables.results.createNode("User did not navigate to Report Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User did not navigate to Report Management Screen");
			Test_Variables.results.createNode("User did not navigate to Report Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Open Create Role Popup", enabled= true, priority= 3) 
	public void OpenPopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-02: Verify user can open Create Role Popup", "This test case will verify that user is able to open create role popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.steps.createNode("1. Click on Create New button");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-report-role")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameId")));
			Thread.sleep(1000);

			Assert.assertTrue(Helper.driver.findElement(By.id("nameId")).isDisplayed()); 
			Test_Variables.test.pass("Create Role popup window opened successfully");
			Test_Variables.results.createNode("Create New Role popup opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Create Role popup window did not open successfully");
			Test_Variables.results.createNode("Create Role popup window did not open successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Create Role popup window did not open successfully");
			Test_Variables.results.createNode("Create Role popup window did not open successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 4) 
	public void ResetFieldCheck() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-03: Verify Reset fields check", "This test case will verify that user is able to reset create role fields");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.steps.createNode("1. Enter data in name and description fields");
			Test_Variables.steps.createNode("2. Click on Reset button");

			Helper.driver.findElement(By.id("nameId")).sendKeys("Test");
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("DescId")).sendKeys("Description");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.driver.findElement(By.id("btn-reset")).click();
			Thread.sleep(1000);		

			//	String nameActual = Helper.driver.findElement(By.id("nameId")).getText();
			//	String descActual = Helper.driver.findElement(By.id("DescId")).get;	
			String nameActual = Helper.driver.findElement(By.id("nameId")).getAttribute("value");
			String descActual = Helper.driver.findElement(By.id("DescId")).getAttribute("value");

			//	Assert.assertEquals(nameActual, ""); 
			//	Assert.assertEquals(descActual, "");
			Assert.assertTrue(nameActual.isEmpty());
			Assert.assertTrue(descActual.isEmpty());
			Test_Variables.test.pass("Create Role popup fields reset successfully");
			Test_Variables.results.createNode("Fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Create Role popup window did not reset successfully");
			Test_Variables.results.createNode("Create Role popup window did not reset successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Create Role popup window did not reset successfully");
			Test_Variables.results.createNode("Create Role popup window did not reset successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 5) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {
		String rmNameError;
		String rmDescError;

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);

		for (RMModel objModel : Test_Variables.lstRMMandatoryCheck) {
			rmNameError = "";
			rmDescError = "";

			Test_Variables.test = Test_Variables.extent.createTest(objModel.testcaseTitle, objModel.testcaseDesc);
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.steps.createNode(objModel.step1);
			Test_Variables.steps.createNode("2. Click on save button");

			Helper.driver.findElement(By.id(Test_Elements.rmName)).clear();
			Helper.driver.findElement(By.id(Test_Elements.rmName)).sendKeys(objModel.rmName);
			Thread.sleep(2000);

			Helper.driver.findElement(By.id(Test_Elements.rmDesc)).clear();
			Helper.driver.findElement(By.id(Test_Elements.rmDesc)).sendKeys(objModel.rmDesc);
			Thread.sleep(2000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save")).click();


			try {
				if(objModel.step)	
				{
					Thread.sleep(2000);
					if ( objModel.rmName.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath("//div[contains(text(), 'Role name is required')]")).isDisplayed()) {
							rmNameError = Helper.driver.findElement(By.xpath("//div[contains(text(), 'Role name is required')]")).getText();
						}
						Assert.assertEquals(rmNameError, "Role name is required"); 
					}

					if ( objModel.rmDesc.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath("//div[contains(text(), 'Role description is required' )]")).isDisplayed()) {
							rmDescError = Helper.driver.findElement(By.xpath("//div[contains(text(), 'Role description is required' )]")).getText();
						}
						Assert.assertEquals(rmDescError, "Role description is required"); 
					}

					Test_Variables.test.pass(objModel.passScenario);
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
					continue;
				}



				if (objModel.save)
				{
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
					Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "New Reporting role created."); 
					Thread.sleep(1000);
				}

				Test_Variables.test.pass(objModel.passScenario);
				Test_Variables.results.createNode(objModel.passScenario);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
				ClickElement.clickByCss(Helper.driver, "#alrt .close");
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
			}		
			catch(AssertionError er) {
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
			}
		}
	}



	@Test (description="Test Case: Update Role",enabled= true, priority= 6) 
	public void UpdateRole() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-08: Verify user can update Role", "This test case will verify that user can update a Role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Role");
			Test_Variables.steps.createNode("1. Click on update icon next to created role; popup appears");
			Test_Variables.steps.createNode("2. Update name and description");
			Test_Variables.steps.createNode("3. Click on save button");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1000);

			for (int i=1; i<=100; i++) {
				String actualXpath = Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				//Thread.sleep(1500);

				if (element.getText().equals(Test_Variables.RoleName)) {
					WebElement filter_scroll = Helper.driver.findElement(By.xpath(Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath1));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.xpath(Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath1)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameId")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(Test_Elements.rmDesc)).clear();
			Helper.driver.findElement(By.id(Test_Elements.rmDesc)).sendKeys(Test_Variables.lstRMUpdation.get(1));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.id("btn-save")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = "Reporting role details updated successfully.";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role updated successfully");

			//Helper.driver.findElement(By.cssSelector("#alrt .close")).click();
			Test_Variables.results.createNode("User receives an alert message that 'Role details updated'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			ClickElement.clickByCss(Helper.driver, "#alrt .close");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("User did not received an alert message that 'Role details updated'");
			Test_Variables.results.createNode("User did not received an alert message that 'Role details updated'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User did not received an alert message that 'Role details updated'");
			Test_Variables.results.createNode("User did not received an alert message that 'Role details updated'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Update Role",enabled= true, priority= 7) 
	public void VerifyUpdateRole() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-09: Verify changes remains save on updating the Role", "This test case will verify that changes remains save on updating the Role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Role");
			Test_Variables.steps.createNode("1. Click on update icon next to created role; popup appears");
			Test_Variables.steps.createNode("2. Update name and description and click on save button");
			Test_Variables.steps.createNode("3. Reopen the updated role");
			Test_Variables.steps.createNode("4. Verify the changes made");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1500);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			for (int i=1; i<=100; i++) {
				String actualXpath = Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
				//	Thread.sleep(1500);

				if (element.getText().equals(Test_Variables.RoleName)) {
					WebElement filter_scroll = Helper.driver.findElement(By.xpath(Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath1));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.xpath(Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath1)).click();
					break;
				}
			}
			Thread.sleep(1500);
			String actual = Helper.driver.findElement(By.id(Test_Elements.rmDesc)).getAttribute("value");
			String expected = Test_Variables.lstRMUpdation.get(1);

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role updation verified successfully");
			Test_Variables.results.createNode("Changes made remained saved successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Role updation verification failed");
			Test_Variables.results.createNode("Role updation verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Role updation verification failed");
			Test_Variables.results.createNode("Role updation verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: InActivate Role",enabled= true, priority= 8) 
	public void InActiveRole() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-10: Verify InActivate Role functionality", "This test case will verify user can inactivate the role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Role");
			Test_Variables.steps.createNode("1. Click on update icon next to created role; popup appears");
			Test_Variables.steps.createNode("2. Click on InActivate toggle button and click on save button");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameId")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector("#status-report-role div .toggle")).click();
			Thread.sleep(500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.id("btn-save")).click();
			Thread.sleep(1000);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = "Reporting role details updated successfully.";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role inactivated successfully");
			Test_Variables.results.createNode("User recieves an alert message that 'Role details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Role inactivation failed");
			Test_Variables.results.createNode("User did not recieved an alert message that 'Role details updated.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Role inactivation failed");
			Test_Variables.results.createNode("User did not recieved an alert message that 'Role details updated.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify InActive Role on User Screen",enabled= true, priority= 9) 
	public void VerifyInActiveRoleUsers() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-11: Verify Role is inactivated in User Management screen", "This test case will verify user cannot view inactive role on user create or update screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.steps.createNode("1. Create a Role; Click on InActivate toggle button and click on save button");
			Test_Variables.steps.createNode("2. Go to User Management");
			Test_Variables.steps.createNode("3. Click on Create New button and go to step 3 of create user popup");
			Test_Variables.steps.createNode("4. Open Report Roles dropdown");
			Test_Variables.steps.createNode("5. Find the inactivated role in dropdown list");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-user")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("edit-user-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-next")));
			Thread.sleep(3000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-next")));
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.cssSelector("#reportRoleId input")).sendKeys(Test_Variables.RoleName);
			Thread.sleep(1000);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector("div .ng-option-disabled")).isDisplayed());
			Test_Variables.test.pass("InActivated Role was not found in User Management screen");
			Test_Variables.results.createNode("User was not able to see InActivated Role in dropdown list");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("User was able to see InActivated Role in dropdown list");
			Test_Variables.results.createNode("User was able to see InActivated Role in dropdown list");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User was able to see InActivated Role in dropdown list");
			Test_Variables.results.createNode("User was able to see InActivated Role in dropdown list");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Open Create Report Groups Popup", enabled= true, priority= 11) 
	public void OpenReportGroupsPopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-13: Verify user can open Create Report Group Popup", "This test case will verify that user is able to open create report group popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.steps.createNode("1. Click on Report Group button");

			Helper.driver.get(Constants.url_reportsManagement);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-report-role")));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.id("manage-report-group")).click();

			Thread.sleep(1500);
			String actual = Helper.driver.findElement(By.cssSelector(".popup-header")).getText();
			String expected = "Report Groups";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report Groups popup window opened successfully");
			Test_Variables.results.createNode("Report Groups popup window opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Report Groups popup window did not open successfully");
			Test_Variables.results.createNode("Failed to open Report Groups popup window");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Report Groups popup window did not open successfully");
			Test_Variables.results.createNode("Failed to open Report Groups popup window");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Open Report Details Popup", enabled= true, priority= 12) 
	public void OpenReportDetails() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-14: Verify user can open Create Report Details section", "This test case will verify that user is able to open report details section by clicking on '+' icon");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.steps.createNode("1. Click on Report Groups button; Report Group popup opens");
			Test_Variables.steps.createNode("2. Click on '+' icon");

			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.id("add-group")).click();
			Thread.sleep(1000);
			Assert.assertTrue(Helper.driver.findElement(By.id("groupNameId")).isDisplayed());
			//	String actual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsDetailsGetTitle)).getText();
			//	String expected = "Group Details";
			//	Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Create Report Group details screen opened successfully");
			Test_Variables.results.createNode("Report Group screen opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Create Report Group details screen failed to open");
			Test_Variables.results.createNode("Create Report Group details screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Create Report Group details screen failed to open");
			Test_Variables.results.createNode("Create Report Group details screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 13) 
	public void ResetGroupReportsFieldCheck() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-15: Verify user can reset Group Details fields", "This test case will verify that user is able to reset Report Details fields");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Report Groups button; Report Group popup opens");
			Test_Variables.preconditions.createNode("5. Click on '+' icon; Report Group Details screen shows up");
			Test_Variables.steps.createNode("1. Enter valid data in all fields");
			Test_Variables.steps.createNode("2. Click on reset fields button");

			Thread.sleep(1000);
			Helper.driver.findElement(By.id("groupNameId")).sendKeys("Test");
			Thread.sleep(500);
			Helper.driver.findElement(By.id("groupDescId")).sendKeys("Description");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.id("btn-reset-2")).click();
			Thread.sleep(1000);

			String nameActual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpName)).getAttribute("value");
			String descActual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpDesc)).getAttribute("value");

			Assert.assertEquals(nameActual, ""); 
			Assert.assertEquals(descActual, "");
			Test_Variables.test.pass("Report Group Details fields reset successfully");
			Test_Variables.results.createNode("Report Group Details fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Report Group Details fields failed to reset");
			Test_Variables.results.createNode("Report Group Details fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Report Group Details fields failed to reset");
			Test_Variables.results.createNode("Report Group Details fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 14) 
	public void MandatoryReportGroupFieldCheck() throws InterruptedException, IOException {
		String rmNameError;
		String rmDescError;

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);

		for (RMModel objModel : Test_Variables.lstRGMandatoryCheck) {
			rmNameError = "";
			rmDescError = "";
			Test_Variables.test = Test_Variables.extent.createTest(objModel.rgtestcaseTitle, objModel.rgtestcaseDesc);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar; Click on Administration and select Report Management");	
			Test_Variables.preconditions.createNode("4. Click on Create New button; Popup appears");
			Test_Variables.steps.createNode("1. "+objModel.rgstep1);
			Test_Variables.steps.createNode("2. Click on save button");

			Helper.driver.findElement(By.id("groupNameId")).clear();
			Helper.driver.findElement(By.id("groupNameId")).sendKeys(objModel.rgName);
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("groupDescId")).clear();
			Helper.driver.findElement(By.id("groupDescId")).sendKeys(objModel.rgDesc);
			Thread.sleep(2000);

			if(objModel.rgReport) {
				Helper.driver.findElement(By.id("groupRprtId")).click();
				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[3]/div/ng-select/ng-dropdown-panel/div[1]/div/label")).click();
				Helper.driver.findElement(By.id("groupDescId")).click();
			}

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.driver.findElement(By.id("btn-save-2")).click();
			try{
				if(objModel.rgstep)	
				{
					Thread.sleep(2000);
					if ( objModel.rgName.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath("//div[contains(text(), ' Group name is required ')]")).isDisplayed()) {
							rmNameError = Helper.driver.findElement(By.xpath("//div[contains(text(), ' Group name is required ')]")).getText();
						}
						Assert.assertEquals(rmNameError, "Group name is required"); 
					}

					if ( objModel.rgDesc.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath("//div[contains(text(), ' Group description is required ')]")).isDisplayed()) {
							rmDescError = Helper.driver.findElement(By.xpath("//div[contains(text(), ' Group description is required ')]")).getText();
						}
						Assert.assertEquals(rmDescError, "Group description is required"); 
					}

					Test_Variables.test.pass(objModel.rgpassScenario);
					Test_Variables.results.createNode(objModel.rgpassScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
					continue;
				}


				if (objModel.rgsave)
				{
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
					Thread.sleep(1000);
					String actual = Helper.driver.findElement(By.id("message")).getText();
					String expected = "New report group created.";
					Assert.assertEquals(actual, expected); 
				}

				Test_Variables.test.pass(objModel.rgpassScenario);
				Test_Variables.results.createNode(objModel.rgpassScenario);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
			}		
			catch(AssertionError er) {
				Test_Variables.test.fail(objModel.rgfailScenario);
				Test_Variables.results.createNode(objModel.rgfailScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(objModel.rgfailScenario);
				Test_Variables.results.createNode(objModel.rgfailScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
			}
		}
	}


	@Test (description="Test Case: Update Report Details", enabled= true, priority= 15) 
	public void UpdateReportDetails() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-20: Verify user can update Report Details", "This test case will verify that user can update Report Details");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Report Groups button; Report Group popup opens");
			Test_Variables.preconditions.createNode("5. Click on '+' icon and create a Report Group");
			Test_Variables.steps.createNode("1. Click on created popup; report details screen shows up");
			Test_Variables.steps.createNode("2. Update data and click on save button");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1500);

			for (int i=1; i<=Test_Variables.ReportGroupLength; i++) {
				String actualXpath = Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpath;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
				Thread.sleep(500);

				if (element.getText().equals(Test_Variables.ReportName)) {
					Helper.driver.findElement(By.xpath(Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpath)).click();
					//	Helper.driver.findElement(By.id("edit-report-role-"+i)).click();
					//	ClickElement.clickById(Helper.driver, "edit-report-role-"+i);
					break;
				}
			}

			Thread.sleep(1500);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("groupNameId")));
			Helper.driver.findElement(By.id("groupDescId")).clear();
			Helper.driver.findElement(By.id("groupDescId")).sendKeys("Automated Report Details Update Test ");
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));		
			Helper.driver.findElement(By.id("btn-save-2")).click();
			Thread.sleep(1000);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = "Report Group details updated successfully.";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report details updated successfully");
			Test_Variables.results.createNode("Report Group details updated successfully; an alert message was generated 'Report Group details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			ClickElement.clickByCss(Helper.driver, "#alrt .close");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Report Group details failed to update");
			Test_Variables.results.createNode("Report Group details failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Report Group details failed to update");
			Test_Variables.results.createNode("Report Group details failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Open Edit Rights Popup",enabled= true, priority= 16) 
	public void OpenEditRightsPopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-21: Verify user can Open Edit Rights Popup", "This test case will verify that user can open edit rights popup window");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Role");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to created role");

			Thread.sleep(1500);
			Helper.driver.get(Constants.url_reportsManagement);
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-report-role")));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.id("edit-role-rights-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".popup-header")));
			Thread.sleep(1500);
			String actual =	Helper.driver.findElement(By.cssSelector(".popup-header")).getText();
			String expected = "Edit Rights";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Edit Rights popup window opened successfully");
			Test_Variables.results.createNode("Edit Rights popup window opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Edit Rights popup window opening failed");
			Test_Variables.results.createNode("Edit Rights popup window opening failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Edit Rights popup window opening failed");
			Test_Variables.results.createNode("Edit Rights popup window opening failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Report Groups are visible on Edit Rights screen",enabled= true, priority= 17) 
	public void VerifyReportOnEditRights() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AM-RM-22: Verify Report Groups are visible on Edit Rights popup", "This test case will verify that added report group is being displayed on Edit Rights popup");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
		Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
		Test_Variables.preconditions.createNode("5. Create a Role");
		Test_Variables.steps.createNode("1. Click on edit rights icon next to created role; popup appears");

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		for (int i=1; i<=Test_Variables.ReportGroupLength; i++) {
			String actualXpath = Test_Elements.rmEditRightsbeforeXpath+i+Test_Elements.rmEditRightsafterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

			Thread.sleep(2500);

			if (element.getText().equals(Test_Variables.ReportName)) {
				Test_Variables.test.pass("Added report is displayed in Edit Rights page successfully");
				Test_Variables.results.createNode("User is able to view Report Groups in Edit Rights popup successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
				break;
			}
			//		else {
			//			Test_Variables.test.fail("Added report displayed in Edit Rights page failed");
			//			Test_Variables.results.createNode("User is not able to view Report Groups in Edit Rights popup successfully");
			//		}
		}
	}



	@Test (description="Test Case: Verify Report Details",enabled= true, priority= 18) 
	public void VerifyReportDetails() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AM-RM-23: Verify Reports Details are displayed in Edit Rights screen", "This test case will verify that report details are displayed on Edit Rights popup on clicking Report Group");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
		Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
		Test_Variables.preconditions.createNode("5. Create a Role");
		Test_Variables.steps.createNode("1. Click on edit rights icon next to created role; popup appears");
		Test_Variables.steps.createNode("2. Click on report group");

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);

		for (int i=1; i<=30; i++) {
			String actualXpath = Test_Elements.rmEditRightsbeforeXpath+i+Test_Elements.rmEditRightsafterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

			Thread.sleep(1500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			if (element.getText().equals(Test_Variables.ReportName)) {
				Helper.driver.findElement(By.xpath(Test_Elements.rmEditRightsbeforeXpath+i+Test_Elements.rmEditRightsafterXpath1)).click();
				break;
			}

		}
		Thread.sleep(1500);
		String actual =	Helper.driver.findElement(By.xpath(Test_Elements.rmEditRightsReportName)).getText();
		String expected = "Salmonella Log";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Reports in report groups displayed successfully");
			Test_Variables.results.createNode("Displays list of reports that are present in that Report Group");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("Reports in report groups did not displayed successfully");
			Test_Variables.results.createNode("Did not displayed list of reports that are present in that Report Group");
		}
	}




	@Test (description="Test Case: Save Report Access rights",enabled= true, priority= 19) 
	public void SaveAccessRights() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AM-RM-24: Verify user can save Report Access Rights", "This test case will verify that user can save Report Access rights");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
		Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
		Test_Variables.preconditions.createNode("5. Create a Report Group and add a new report into it");
		Test_Variables.steps.createNode("1. Click on edit rights icon next to created role; popup appears");
		Test_Variables.steps.createNode("2. Click on Save button");


		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);

		Helper.driver.findElement(By.xpath(Test_Elements.rmEditRightsView)).click();	
		Thread.sleep(1500);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmEditRightsSaveButton)).click();
		Thread.sleep(1000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		String actual =	Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = "Report access rights saved successfully.";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Edit Rights saved successfully");
			Test_Variables.results.createNode("Edit Rights saved successfully; displays an alert message 'Report access rights saved successfully'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Edit Rights failed to save");
			Test_Variables.results.createNode("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
		}
	}


	@Test (description="Test Case: Delete Report Group Configurations",enabled= false, priority= 20) 
	public void DeleteReportGroupConfigurations() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AM-RM-27: Verify user can delete Report Group Configurations", "This test case will verify that user can delete added reports from created Report Group");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
		Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
		Test_Variables.preconditions.createNode("5. Create a Report Group and add a new report into it");
		Test_Variables.steps.createNode("1. Click on delete button next to added report; confirmaton message appears");
		Test_Variables.steps.createNode("2. Click on Yes");

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Helper.driver.get(Constants.url_reportsManagement);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.rmReportGroupsCreateButton)));
		Thread.sleep(1500);
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsCreateButton)).click();
		Thread.sleep(1500);
		for (int i=1; i<=Test_Variables.ReportGroupConfigLength; i++) {
			String actualXpath = Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
			Thread.sleep(1500);

			if (element.getText().equals(Test_Variables.ReportName)) {
				Helper.driver.findElement(By.xpath(Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpathExpand)).click();
				Thread.sleep(1000);
				for(int j =1; j<=40; j++) {
					String xx = Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpathExpandDelete+j+Test_Elements.rgafterXpathExpandDelete2;
					WebElement e = Helper.driver.findElement(By.xpath(xx));
					if (e.getText().equals("Salmonella Log")) {
						Helper.driver.findElement(By.xpath("")).click();
					}
				}
				//	Helper.driver.findElement(By.xpath(Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpathExpandDelete)).click();
				break;
			}
		}

		Thread.sleep(1500);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsConfigDeleteCnfrm)).click();
		Thread.sleep(1000);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = "Report Configuration details deleted.";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report Configuration details deleted successfully");
			Test_Variables.results.createNode("User should receive an alert message that 'Report Configuration details deleted.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Report Configuration details deletion failed");
			Test_Variables.results.createNode("User did not receive an alert message that 'Report Confguration details deleted.'");
		}
	}


	@Test (description="Test Case: Inactivate Report Group",enabled= false, priority= 21) 
	public void InActivateReportGroup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-28: Verify user can inactivate Report Group", "This test case will verify that user can inactivate Report Group");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Report Group");
			Test_Variables.steps.createNode("1. Click on inactivate toggle button");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			
			Helper.driver.findElement(By.id("edit-role-rights-1")).click();
			Thread.sleep(2000);
			
			for (int i=1; i<=Test_Variables.ReportGroupLength; i++) {
				String actualXpath = Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpath;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
				Thread.sleep(500);

			//	if (element.getText().equals(Test_Variables.ReportName)) {
					if (element.getText().equals("Test Report Group1248")) {			
					Helper.driver.findElement(By.xpath(Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpath)).click();
					break;
				}
			}

			Thread.sleep(1500);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("groupNameId")));
			Helper.driver.findElement(By.cssSelector(".toggle")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));		
			Helper.driver.findElement(By.id("btn-save-2")).click();
			Thread.sleep(1000);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = "Report Group details updated successfully.";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report Group inactivated successfully");
			Test_Variables.results.createNode("Report Group inactivated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Report Group failed to inactivate");
			Test_Variables.results.createNode("Report Group failed to inactivate");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Report Group failed to inactivate");
			Test_Variables.results.createNode("Report Group failed to inactivate");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify inactivated report group in edit rights screen",enabled= false, priority= 22) 
	public void VerifyInactivatedReportGroup() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AM-RM-29: Verify inactivated Report Group is not visible on Edit Rights popup", "This test case will verify that inactivated report group is not being displayed on Edit Rights popup");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
		Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
		Test_Variables.preconditions.createNode("5. Create a Report Group and make it inactivated");
		Test_Variables.steps.createNode("1. Click on edit rights icon to check if report group appears in list or not");

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);
		Helper.driver.findElement(By.id("edit-role-rights-1")).click();
		Thread.sleep(1500);
		for (int i=1; i<=Test_Variables.ReportGroupLength; i++) {
			String actualXpath = Test_Elements.rmEditRightsbeforeXpath+i+Test_Elements.rmEditRightsafterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

			Thread.sleep(2500);

			if (element.getText().equals("Test Report Group1248")) {
				Test_Variables.test.fail("Inactivated report group is displayed in Edit Rights page");
				Test_Variables.results.createNode("Inactivated report group is displayed in Edit Rights page");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
				break;
			}
			else {
				Test_Variables.test.pass("Inactivated report group is not displayed in Edit Rights page successfully");
				Test_Variables.results.createNode("Inactivated report group is not displayed in Edit Rights page successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
			}
			Helper.driver.findElement(By.cssSelector(".close-text")).click();
		}
	}



	@Test (description="Test Case: Hide Column",enabled= false, priority= 23) 
	public void HideColumn() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-29: Verify Report Groups are visible on Edit Rights popup", "This test case will verify that added report group is being displayed on Edit Rights popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Role");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to created role; popup appears");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1000);
		
			for (int i=1; i<=100; i++) {
				WebElement element = Helper.driver.findElement(By.cssSelector("tbody tr:nth-child("+i+") td:nth-child(1) label:nth-child(1)"));
				Thread.sleep(1000);

				if (element.getText().equals("PIPER Output")) {
					Helper.driver.findElement(By.id("edit-role-rights-"+i)).click();
					Thread.sleep(2000);	
					break;
				}
			}

			for (int j =2; j<=100; j++) {
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul:nth-child(2) li:nth-child(1) label:nth-child(1)")));
				WebElement element_group = Helper.driver.findElement(By.cssSelector("ul:nth-child("+j+") li:nth-child(1) label:nth-child(1)"));
				Thread.sleep(1000);	
				if (element_group.getText().equals("all reports")) {
					Helper.driver.findElement(By.cssSelector("ul:nth-child("+j+") li:nth-child(1) label:nth-child(1)")).click();
					Thread.sleep(1000);	
					Helper.driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(6) span:nth-child(1) i:nth-child(1)")).click();
					Thread.sleep(1000);	
					if (Helper.driver.findElements(By.cssSelector("#field-0.T")).size() != 0) {
					Helper.driver.findElement(By.id("field-0")).click();
					}
					Helper.driver.findElement(By.id("btn-save")).click();  
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
					break;
				}
			}	

			Helper.driver.get(Constants.url_SalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			int column = Helper.driver.findElements(By.id("sort-laneNum")).size();

			
			
			Helper.driver.get(Constants.url_reportsManagement);
			for (int i=1; i<=Test_Variables.ReportGroupLength; i++) {
				WebElement element = Helper.driver.findElement(By.cssSelector("tbody tr:nth-child("+i+") td:nth-child(1) label:nth-child(1)"));
				Thread.sleep(1000);

				if (element.getText().equals("PIPER Output")) {
					Helper.driver.findElement(By.id("edit-role-rights-"+i)).click();
					Thread.sleep(1000);	
					break;
				}
			}

			for (int j =2; j<=100; j++) {
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul:nth-child(2) li:nth-child(1) label:nth-child(1)")));
				WebElement element_group = Helper.driver.findElement(By.cssSelector("ul:nth-child("+j+") li:nth-child(1) label:nth-child(1)"));
				Thread.sleep(1000);	
				if (element_group.getText().equals("all reports")) {
					Helper.driver.findElement(By.cssSelector("ul:nth-child("+j+") li:nth-child(1) label:nth-child(1)")).click();
					Thread.sleep(1000);	
					Helper.driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(6) span:nth-child(1) i:nth-child(1)")).click();
					Thread.sleep(1000);	
					if (Helper.driver.findElements(By.cssSelector("#field-0.T")).size() == 0) {
					Helper.driver.findElement(By.id("field-0")).click();
					}
					Helper.driver.findElement(By.id("btn-save")).click();
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
					break;
				}
			}
				
			Assert.assertEquals(column, 0);
			Test_Variables.test.pass("Inactivated report group is not displayed in Edit Rights page successfully");
			Test_Variables.results.createNode("Inactivated report group is not displayed in Edit Rights page successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
			
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("User did not receive an alert message that 'Report Group details deleted.'");
			Test_Variables.results.createNode("User did not receive an alert message that 'Report Group details deleted.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User did not receive an alert message that 'Report Group details deleted.'");
			Test_Variables.results.createNode("User did not receive an alert message that 'Report Group details deleted.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}









	@Test (description="Test Case: Delete Report Group",enabled= false, priority= 22) 
	public void DeleteReportGroup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-30: Verify user can delete Report Group", "This test case will verify that user can delete Report Group");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Report Group");
			Test_Variables.steps.createNode("1. Click on delete button next to created report group; confirmaton message appears");
			Test_Variables.steps.createNode("2. Click on Yes");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//	Helper.driver.get(Constants.url_reportsManagement);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.rmReportGroupsCreateButton)));
			Thread.sleep(1500);
			Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsCreateButton)).click();
			Thread.sleep(1500);
			for (int i=1; i<=Test_Variables.ReportGroupLength; i++) {
				String actualXpath = Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpath;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
				Thread.sleep(1500);

				if (element.getText().equals(Test_Variables.ReportName)) {
					Helper.driver.findElement(By.xpath(Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpathDelete)).click();
					break;
				}
			}

			Thread.sleep(1500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(By.id("btn-yes")).click();
			Thread.sleep(1000);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = "Report Group details deleted.";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report Group deleted successfully");
			Test_Variables.results.createNode("Report Group deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("User did not receive an alert message that 'Report Group details deleted.'");
			Test_Variables.results.createNode("User did not receive an alert message that 'Report Group details deleted.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User did not receive an alert message that 'Report Group details deleted.'");
			Test_Variables.results.createNode("User did not receive an alert message that 'Report Group details deleted.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();
	}

}


