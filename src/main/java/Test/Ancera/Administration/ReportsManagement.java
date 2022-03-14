package Test.Ancera.Administration;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import Models.RMModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class ReportsManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Reports_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Reports Management Test Report"); 
		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Reports Management Screen",enabled=true, priority = 1) 
	public void NavigateRM() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-01: Verify user can navigate to Reports Management Screen", "This test case will verify user can navigate to Reports Management Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Administration and select Report Management");

			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("Reports Management")).getText();
			String expected = "Reports Management";

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


	@Test (description="Open Create Role Popup", enabled= true, priority= 2) 
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
			Helper.driver.findElement(Test_Elements.rmCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Assert.assertTrue(Helper.driver.findElement(Test_Elements.rmName).isDisplayed(), "Popup failed to open"); 
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


	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 3) 
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

			Helper.driver.findElement(Test_Elements.rmName).sendKeys("Test");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmDesc).sendKeys("Description");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupResetButton ).click();
			Thread.sleep(1000);		
			Assert.assertEquals(Helper.driver.findElements(Test_Elements.alertMessage).size(), 0);
			String nameActual = Helper.driver.findElement(By.id("nameId")).getAttribute("value");
			String descActual = Helper.driver.findElement(By.id("DescId")).getAttribute("value");
			Assert.assertTrue(nameActual.isEmpty(), "Name field is not empty");
			Assert.assertTrue(descActual.isEmpty(), "Desc field not empty");
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


	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 4) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-04: Verify mandatory field check", "This testcase will verify mandatory field check");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.steps.createNode("1. Leave both fields empty");
			Test_Variables.steps.createNode("2. Click on save button");

			Helper.driver.findElement(Test_Elements.rmName).click();
			Helper.driver.findElement(Test_Elements.rmDesc).click();
			Helper.driver.findElement(Test_Elements.rmName).click();
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Assert.assertEquals(Helper.driver.findElements(Test_Elements.alertMessage).size(), 0);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".has-error")).size(), 4);
			Test_Variables.test.pass("Mandatory field check verified successfully");
			Test_Variables.results.createNode("Mandatory field check verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Mandatory field verification failed");
			Test_Variables.results.createNode("Mandatory field verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Mandatory field verification failed");
			Test_Variables.results.createNode("Mandatory field verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Create Role", enabled= true, priority= 5) 
	public void CreateRole() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-05: Verify user can create role", "This testcase will verify that user can create role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.steps.createNode("1. Leave both fields empty");
			Test_Variables.steps.createNode("2. Click on save button");

			Helper.driver.findElement(Test_Elements.rmName).sendKeys(Test_Variables.RoleName);
			Helper.driver.findElement(Test_Elements.rmDesc).sendKeys("Role created by automation script");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Role has been created successfully.");
			Test_Variables.test.pass("Role created successfully");
			Test_Variables.results.createNode("Role created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Role failed to create successfully");
			Test_Variables.results.createNode("Role failed to create successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Role failed to create successfully");
			Test_Variables.results.createNode("Role failed to create successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Update Role",enabled= true, priority= 6) 
	public void UpdateRole() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-06: Verify user can update Role", "This test case will verify that user can update a Role");
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

			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.RoleName) ) {
					Helper.driver.findElement(By.id("edit-report-role-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					break;
				}
			}

			Helper.driver.findElement(Test_Elements.rmDesc).clear();
			Helper.driver.findElement(Test_Elements.rmDesc).sendKeys("Role created and updated by automation script");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Role has been updated successfully."); 
			Test_Variables.test.pass("Role updated successfully");
			Test_Variables.results.createNode("User receives an alert message that 'Role details updated'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
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
		Helper.driver.findElement(Test_Elements.alertMessageClose).click();
	}


	@Test (description="Test Case: Verify Update Role",enabled= true, priority= 7) 
	public void VerifyUpdateRole() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-07: Verify changes remains save on updating the Role", "This test case will verify that changes remains save on updating the Role");
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

			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.RoleName) ) {
					Helper.driver.findElement(By.id("edit-report-role-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					break;
				}
			}

			Assert.assertEquals(Helper.driver.findElement(Test_Elements.rmDesc).getAttribute("value"), "Role created and updated by automation script"); 
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


	@Test (description="Test Case: Audit Trail",enabled= true, priority= 8) 
	public void AuditTrail() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-08: Verify Audit trail functionality", "This test case will verify audit trail functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Role");
			Test_Variables.steps.createNode("1. Click on update icon next to created role; popup appears");
			Test_Variables.steps.createNode("2. Click on Audit Trail toggle button and click on save button");
			Test_Variables.steps.createNode("3. Verify Audit trail button is shown in reports");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			for (int j=1;j<Helper.driver.findElements(By.cssSelector("tr")).size(); j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					String ReportRole = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userReportingCol+" label")).getText();
					Helper.driver.get(Constants.url_reportsManagement);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
						if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ReportRole)) {
							Helper.driver.findElement(By.id("edit-report-role-"+i)).click();
							break;
						}	
					}
					break;
				}	
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			if (!Helper.driver.findElement(By.cssSelector("#status-audit-trail .toggle")).isEnabled()) {
				Helper.driver.findElement(By.cssSelector("#status-audit-trail .toggle")).click();
				Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			}

			Helper.driver.get(Constants.url_SalmonellaLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.id("audit-trial-0")).size(), 1);
			Test_Variables.test.pass("Audit trail icon displayed in reports successfully");
			Test_Variables.results.createNode("Audit trail icon displayed in reports successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Audit trail icon did not displayed in reports");
			Test_Variables.results.createNode("Audit trail icon did not displayed in reports");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Audit trail icon did not displayed in reports");
			Test_Variables.results.createNode("Audit trail icon did not displayed in reports");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: InActivate Role",enabled= true, priority= 9) 
	public void InActiveRole() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-09: Verify InActivate Role functionality", "This test case will verify user can inactivate the role");
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

			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.RoleName) ) {
					Helper.driver.findElement(By.id("edit-report-role-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					break;
				}
			}
			Helper.driver.findElement(By.cssSelector("#status-report-role .toggle")).click();
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Role has been updated successfully."); 
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


	@Test (description="Test Case: Verify InActive Role on User Screen",enabled= true, priority= 10) 
	public void VerifyInActiveRoleUsers() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-10: Verify Role is inactivated in User Management screen", "This test case will verify user cannot view inactive role on user create or update screen");
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

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("edit-user-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(4000);
			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Thread.sleep(1500);
			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Thread.sleep(1500);
			Helper.driver.findElement(By.cssSelector("#reportRoleId input")).clear();
			Helper.driver.findElement(By.cssSelector("#reportRoleId input")).sendKeys(Test_Variables.RoleName);
			Thread.sleep(1000);
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector(".ng-option-disabled")).isDisplayed());
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


	@Test ( enabled= true, priority= 11) 
	public void Filter() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-11: Verify Filter functionality", "This test case will verify filter functionality");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.steps.createNode("1. Open Role Filter and select a value; click on Apply filter button");
			Test_Variables.steps.createNode("2. Verify filter is applied");
			Test_Variables.steps.createNode("3. Apply lock and reload the page to verify lock remianed apply");
			Test_Variables.steps.createNode("4. Reset the filter to verify reset is working");
			Test_Variables.steps.createNode("5. Apply wildcard and verify that only wildcard results are shown");

			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();	
			String recordsBefore = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

			Helper.driver.findElement(Test_Elements.rmRoleFilterShow).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			List<WebElement> list = Helper.driver.findElements(By.cssSelector("li"));
			Helper.driver.findElement(Test_Elements.rmRoleFilterSearch).sendKeys(list.get(15).getText());
			Helper.driver.findElement(By.id("reportRoleName_cust-cb-lst-txt_"+list.get(15).getText())).click();


			Helper.driver.findElement(Test_Elements.rmRoleFilterApply).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			String recordsAfter = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

			softAssert.assertNotEquals(recordsBefore, recordsAfter, "Filter failed to apply");

			Helper.driver.findElement(By.id(Test_Elements.LockFilter)).click();
			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			String recordsAfterLock = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
			softAssert.assertEquals(recordsAfterLock, recordsAfter, "Lock filter failed to apply");

			Helper.driver.findElement(By.id(Test_Elements.UnlockFilter)).click();;
			Helper.driver.findElement(By.id(Test_Elements.ResetFilters)).click();;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			softAssert.assertEquals(Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(), recordsBefore, "Reset filter failed to apply");

			Helper.driver.findElement(Test_Elements.rmRoleFilterShow).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmRoleFilterWildcardToggle).click();
			Helper.driver.findElement(Test_Elements.rmRoleFilterSearch).sendKeys("Te");
			Helper.driver.findElement(Test_Elements.rmRoleFilterApply).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);

			List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] td:nth-child(1) label"));
			int count = rows.size();
			for (int i = 1; i<count; i++) {
				String str = Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText();
				softAssert.assertTrue(str.toUpperCase().startsWith("T"), "Wildcard failed to apply");
			}

			softAssert.assertAll();
			Test_Variables.test.pass("Filter functionality verified successfully");
			Test_Variables.results.createNode("Filter functionality verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Filter functionality failed");
			Test_Variables.results.createNode("Filter functionality failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Filter functionality failed");
			Test_Variables.results.createNode("Filter functionality failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Open Create Report Groups Popup", enabled= true, priority= 12) 
	public void OpenReportGroupsPopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-12: Verify user can open Create Report Group Popup", "This test case will verify that user is able to open create report group popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
			Test_Variables.steps.createNode("1. Click on Report Group button");

			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmReportGroupPopupOpen).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
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



	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 13) 
	public void ResetGroupReportsFieldCheck() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-RM-13: Verify user can reset Group Details fields", "This test case will verify that user is able to reset Report Details fields");
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

			Helper.driver.findElement(Test_Elements.rmReportGroupsCreateButton).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmReportGroupsName).sendKeys("Test");
			Thread.sleep(500);
			Helper.driver.findElement(Test_Elements.rmReportGroupsDesc).sendKeys("Description");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.driver.findElement(Test_Elements.rmReportGroupsresetButton).click();
			Thread.sleep(1000);

			String nameActual = Helper.driver.findElement(Test_Elements.rmReportGroupsName).getAttribute("value");
			String descActual = Helper.driver.findElement(Test_Elements.rmReportGroupsDesc).getAttribute("value");

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

		for (RMModel objModel : Test_Variables.lstRGMandatoryCheck) {
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

			Helper.driver.findElement(Test_Elements.rmReportGroupsName).clear();
			Helper.driver.findElement(Test_Elements.rmReportGroupsName).sendKeys(objModel.rgName);
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmReportGroupsDesc).clear();
			Helper.driver.findElement(Test_Elements.rmReportGroupsDesc).sendKeys(objModel.rgDesc);
			Thread.sleep(1000);

			if(objModel.rgReport) {
				Helper.driver.findElement(By.id("groupRprtId")).click();
				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath("//label[contains(text(),'Select All')]")).click();
				Helper.driver.findElement(Test_Elements.rmReportGroupsDesc).click();
			}

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.driver.findElement(By.id("btn-save-2")).click();
			try{
				if(objModel.rgstep)	
				{
					if ( objModel.rgName.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#groupNameId-error-container .hide")).size(), 0); 
					}

					if ( objModel.rgDesc.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("groupDescId-error-container .hide")).size(), 0); 
					}

					if ( objModel.rgDesc.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".floating-error.hide")).size(), 0); 
					}

					Test_Variables.test.pass(objModel.rgpassScenario);
					Test_Variables.results.createNode(objModel.rgpassScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
					continue;
				}

				if (objModel.rgsave)
				{
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					String actual = Helper.driver.findElement(Test_Elements.alertMessage).getText();
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
	public void UpdateReportGroupDetails() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-17: Verify user can update Report Group", "This test case will verify that user can update Report Details");
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

			List<WebElement> op = Helper.driver.findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(Test_Variables.ReportGroupName)) {
					Helper.driver.findElement(By.xpath("//label[contains(text(),'"+Test_Variables.ReportGroupName+"')]")).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmReportGroupsDesc).clear();
			Helper.driver.findElement(Test_Elements.rmReportGroupsDesc).sendKeys("Group created and updated by automation script");
			Helper.driver.findElement(By.id("btn-save-2")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = "Report Group details updated successfully.";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report details updated successfully");
			Test_Variables.results.createNode("Report Group details updated successfully; an alert message was generated 'Report Group details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
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


	@Test (enabled= true, priority= 16) 
	public void VerifyReportGroupOnEditRights() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-18: Verify Report Groups are visible on Edit Rights popup", "This test case will verify that added report group is being displayed on Edit Rights popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Role");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to created role; popup appears");

			Helper.driver.get(Constants.url_reportsManagement);			
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.RoleName) ) {
					Helper.driver.findElement(By.id("edit-role-rights-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					break;
				}
			}

			List<WebElement> op = Helper.driver.findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(Test_Variables.ReportGroupName)) {
					Assert.assertTrue(true, "Report Group not visible"); 
					Test_Variables.test.pass("Created Report group was visible on edit rights successfully");
					Test_Variables.results.createNode("Created Report group was visible on edit rights successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
					break;
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Created Report group was not visible on edit rights");
			Test_Variables.results.createNode("Created Report group was not visible on edit rights");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Created Report group was not visible on edit rights");
			Test_Variables.results.createNode("Created Report group was not visible on edit rights");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Report Details",enabled= true, priority= 17) 
	public void VerifyReportDetails() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-19: Verify Reports Details are displayed in Edit Rights screen", "This test case will verify that report details are displayed on Edit Rights popup on clicking Report Group");
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
			Test_Variables.steps.createNode("3. Verify all reports are visible in report group that are assigned to it");

			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmReportGroupPopupOpen).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			List<WebElement> op = Helper.driver.findElements(By.cssSelector(".popup-content ul label"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(Test_Variables.ReportGroupName)) {
					Helper.driver.findElement(By.xpath("//label[contains(text(),'"+Test_Variables.ReportGroupName+"')]")).click();  //open report group
					Thread.sleep(1500);
					break;
				}
			}

			Helper.driver.findElement(By.id("groupRprtId")).click();
			Thread.sleep(2000);
			List<WebElement> groupReports = Helper.driver.findElements(By.cssSelector("#groupRprtId .custom-control-label")); //get list of all reports assigned to report group

			Helper.driver.findElement(Test_Elements.popupCloseButton).click();

			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.RoleName) ) {

					Helper.driver.findElement(By.id("edit-role-rights-"+i)).click();  //open edit rights popup
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);
					break;
				}
			}


			List<WebElement> reportGroups = Helper.driver.findElements(By.cssSelector("label.group-card-text"));
			for (int i=0; i<reportGroups.size(); i++) {
				if (reportGroups.get(i).getText().equals(Test_Variables.ReportGroupName)) {
					int j=i+1;
					Helper.driver.findElement(By.cssSelector("#status-report-right-"+j+" .row")).click();
					break;
				}
			}
			Thread.sleep(2000);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".popup-content tr")).size(), groupReports.size()); //verify all reports assigned to report group are vivsible in edit rights popup

			Helper.driver.findElement(By.cssSelector(".fa-caret-down")).click();
			Helper.driver.findElement(By.xpath("//label[contains(text(),'Select all')]")).click();

			List<WebElement> viewCheckbox = Helper.driver.findElements(By.cssSelector(".custom-checkbox label"));
			for (int j =0;j<viewCheckbox.size();j++) {
				Assert.assertTrue(viewCheckbox.get(j).isEnabled());
			}
			Test_Variables.test.pass("Reports in report groups displayed successfully");
			Test_Variables.results.createNode("Displays list of reports that are present in that Report Group");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Did not displayed list of reports that are present in that Report Group");
			Test_Variables.results.createNode("Did not displayed list of reports that are present in that Report Group");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Did not displayed list of reports that are present in that Report Group");
			Test_Variables.results.createNode("Did not displayed list of reports that are present in that Report Group");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Save Report Access rights",enabled= true, priority= 18) 
	public void SaveAccessRights() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-20: Verify user can save Report Access Rights", "This test case will verify that user can save Report Access rights");
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
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Thread.sleep(1000);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			String actual =	Helper.driver.findElement(By.id("message")).getText();
			String expected = "Report access rights saved successfully.";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Edit Rights saved successfully");
			Test_Variables.results.createNode("Edit Rights saved successfully; displays an alert message 'Report access rights saved successfully'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			Test_Variables.results.createNode("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			Test_Variables.results.createNode("Edit Rights failed to saved;did not displayed an alert message 'Report access rights saved successfully'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Inactivate Report Group",enabled= true, priority= 19)
	public void InActivateReportGroup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-21: Verify user can inactivate Report Group", "This test case will verify that user can inactivate Report Group");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Report Group");
			Test_Variables.steps.createNode("1. Click on inactivate toggle button");

			Helper.driver.findElement(Test_Elements.rmReportGroupPopupOpen).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			List<WebElement> op = Helper.driver.findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(Test_Variables.ReportGroupName)) {
					Helper.driver.findElement(By.xpath("//label[contains(text(),'"+Test_Variables.ReportGroupName+"')]")).click();
					break;
				}
			}
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#status-report-role .row")).click();	
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save-2")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
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
		Helper.driver.findElement(Test_Elements.popupCloseButton);
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Verify inactivated report group in edit rights screen",enabled= true, priority= 20) 
	public void VerifyInactivatedReportGroup() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-22: Verify inactivated Report Group is not visible on Edit Rights popup", "This test case will verify that inactivated report group is not being displayed on Edit Rights popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
			Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
			Test_Variables.preconditions.createNode("5. Create a Report Group and make it inactivated");
			Test_Variables.steps.createNode("1. Click on edit rights icon to check if report group appears in list or not");

			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("edit-role-rights-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			List<WebElement> op = Helper.driver.findElements(By.cssSelector(".popup-content ul"));
			for (int i=0; i<op.size(); i++) {
				if (!op.get(i).getText().equals(Test_Variables.ReportGroupName)) {
					Assert.assertTrue(true, "Report Group not visible"); 
					Test_Variables.test.pass("Inactivated report group is not displayed in Edit Rights page successfully");
					Test_Variables.results.createNode("Inactivated report group is not displayed in Edit Rights page successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ReportManagementReportPath, null);
					break;
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Inactivated report group is displayed in Edit Rights page");
			Test_Variables.results.createNode("Inactivated report group is displayed in Edit Rights page");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Inactivated report group is displayed in Edit Rights page");
			Test_Variables.results.createNode("Inactivated report group is displayed in Edit Rights page");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ReportManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Delete Report Group",enabled= true, priority= 21) 
	public void DeleteReportGroup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AM-RM-23: Verify user can delete Report Group", "This test case will verify that user can delete Report Group");
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

			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.rmReportGroupPopupOpen).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			List<WebElement> op = Helper.driver.findElements(By.cssSelector(".popup-content ul label"));
			for (int i=0; i<op.size(); i++) {
				if (op.get(i).getText().equals(Test_Variables.ReportGroupName)) {
					int j=i+1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("ul:nth-child("+j+") > li > div > div:nth-child(2) > span.group-card-option.m-l-0px > img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("ul:nth-child("+j+") > li > div > div:nth-child(2) > span.group-card-option.m-l-0px > img")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("delete-group-"+j)).click();
					break;
				}
			}

			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupYesButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(Test_Elements.alertMessage).getText();
			String expected = "Report Group details has been deleted.";

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
		//	Helper.driver.close();
	}
}