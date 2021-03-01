package Test.Ancera.Administration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

		Test_Variables.spark = new 		ExtentSparkReporter("target/Reports/Administration_Reports_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Reports Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	

	@Test (description="Test Case: Navigate to Reports Management Screen",enabled=true, priority = 2) 
	public void NavigateRM() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-RM-01: Verify user can navigate to Reports Management Screen", "This test case will verify user can navigate to Reports Management Screen");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
		Helper.driver.get(Constants.url_reportsManagement);
		Thread.sleep(2000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText();
		String expected = "Reports Management";
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
		Test_Variables.steps.createNode("2. Click on Administration and select Report Management");
		
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Report Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Report Management Screen");
		}	
	}

	
	@Test (description="Open Create Role Popup", enabled= true, priority= 3) 
	public void OpenPopup() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-RM-02: Verify user can open Create Role Popup", "This test case will verify that user is able to open create role popup");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
		Test_Variables.steps.createNode("1. Click on Create New button");
		
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
		Thread.sleep(2000);
		Helper.driver.findElement(By.xpath(Test_Elements.rmCreateButton)).click();
		Thread.sleep(1500);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.rmPopupGetTitle)).getText();
		String expected = "Create Role";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Create Role popup window opened successfully");
			Test_Variables.results.createNode("Create New Role popup opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Create Role popup window did not open successfully");
			Test_Variables.results.createNode("Create New Role popup failed to open");
		}
	}


	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 4) 
	public void ResetFieldCheck() throws InterruptedException, IOException {
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
		
		Thread.sleep(1000);
		Helper.driver.findElement(By.id(Test_Elements.rmName)).sendKeys("Test");
		Thread.sleep(1000);
		Helper.driver.findElement(By.id(Test_Elements.rmDesc)).sendKeys("Description");
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.rmResetButton)).click();
		Thread.sleep(1000);		
		
		String nameActual = Helper.driver.findElement(By.id(Test_Elements.rmName)).getText();
		String descActual = Helper.driver.findElement(By.id(Test_Elements.rmDesc)).getText();	
		try{
			Assert.assertEquals(nameActual, ""); 
			Assert.assertEquals(descActual, "");
			Test_Variables.test.pass("Create Role popup fields reset successfully");
			Test_Variables.results.createNode("Fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Create Role popup window did not reset successfully");
			Test_Variables.results.createNode("Fields failed to reset");
		}

	}


	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 5) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {
		String rmNameError;
		String rmDescError;

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);

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
			Helper.driver.findElement(By.xpath(Test_Elements.rmSaveButton)).click();

			
			try {
				if(objModel.step)	
				{
					Thread.sleep(2000);
					if ( objModel.rmName.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.rmNameValidation)).isDisplayed()) {
							rmNameError = Helper.driver.findElement(By.xpath(Test_Elements.rmNameValidation)).getText();
						}
						Assert.assertEquals(rmNameError, "Role name is required"); 
					}

					if ( objModel.rmDesc.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.rmDescValidation)).isDisplayed()) {
							rmDescError = Helper.driver.findElement(By.xpath(Test_Elements.rmDescValidation)).getText();
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
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
					Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText(), "New role created."); 
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
				}

				Test_Variables.test.pass(objModel.passScenario);
				Test_Variables.results.createNode(objModel.passScenario);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
				
			}
			catch(AssertionError e){
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);	
			}	
		}
	}



	@Test (description="Test Case: Update Role",enabled= true, priority= 6) 
	public void UpdateRole() throws InterruptedException, IOException {

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
		Thread.sleep(3500);

		for (int i=1; i<=35; i++) {
			String actualXpath = Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

			Thread.sleep(1500);

			if (element.getText().equals(Test_Variables.RoleName)) {
				Helper.driver.findElement(By.xpath(Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath1)).click();
				break;
			}
		}
		
		
		
		
		Thread.sleep(1500);
		Helper.driver.findElement(By.id(Test_Elements.rmDesc)).clear();
		Helper.driver.findElement(By.id(Test_Elements.rmDesc)).sendKeys(Test_Variables.lstRMUpdation.get(1));
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmUpdateButton)).click();

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		Thread.sleep(1000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = "Role details updated.";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role updated successfully");
			Test_Variables.results.createNode("User receives an alert message that 'Role details updated'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Role updation failed");
			Test_Variables.results.createNode("User did not received an alert message that 'Role details updated'");
		}
	}


	@Test (description="Test Case: Verify Update Role",enabled= true, priority= 7) 
	public void VerifyUpdateRole() throws InterruptedException, IOException {
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
		for (int i=1; i<=35; i++) {
			String actualXpath = Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
			Thread.sleep(1500);

			if (element.getText().equals(Test_Variables.RoleName)) {
				Helper.driver.findElement(By.xpath(Test_Elements.rmbeforeXpath+i+Test_Elements.rmafterXpath1)).click();
				break;
			}
		}
		Thread.sleep(1500);
		String actual = Helper.driver.findElement(By.id(Test_Elements.rmDesc)).getAttribute("value");
		String expected = Test_Variables.lstRMUpdation.get(1);

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role updation verified successfully");
			Test_Variables.results.createNode("Changes made remained saved successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("Role updation verification failed");
			Test_Variables.results.createNode("Changes failed to remain save");
		}
	}


	
	@Test (description="Test Case: InActivate Role",enabled= true, priority= 8) 
	public void InActiveRole() throws InterruptedException, IOException {
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
		Thread.sleep(1500);

		Helper.driver.findElement(By.xpath(Test_Elements.rmToggleInactive)).click();
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.rmUpdateButton)).click();
		Thread.sleep(1000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		Thread.sleep(1000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = "Role details updated.";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role inactivated successfully");
			Test_Variables.results.createNode("User recieves an alert message that 'Role details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Role inactivation failed");
			Test_Variables.results.createNode("User did not recieved an alert message that 'Role details updated.'");
		}
	}


	@Test (description="Test Case: Verify InActive Role on User Screen",enabled= true, priority= 9) 
	public void VerifyInActiveRoleUsers() throws InterruptedException, IOException {
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
		Thread.sleep(1500);
		Helper.driver.get(Constants.url_user);
		Thread.sleep(1500);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userExpandAnceraTab)));
		Helper.driver.findElement(By.xpath(Test_Elements.userExpandAnceraTab)).click();
		Thread.sleep(1500);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userExpandAnceraSite)));
		Helper.driver.findElement(By.xpath(Test_Elements.userExpandAnceraSite)).click();
		Thread.sleep(1500);
		Helper.driver.findElement(By.xpath(Test_Elements.userEditIcon)).click();
		Thread.sleep(1500);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-next")));
		Helper.driver.findElement(By.id("btn-next")).click();
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-next")));
		Thread.sleep(500);
		Helper.driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmRoleFind)).sendKeys(Test_Variables.RoleName);
		Thread.sleep(1000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.userUpdateRoleResult)).getText();

		try{
			Assert.assertEquals(actual, "No items found"); 
			Test_Variables.test.pass("InActivated Role was not found in User Management screen");
			Test_Variables.results.createNode("User was not able to see InActivated Role in dropdown list");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("InActivated Role was found in User Management screen");
			Test_Variables.results.createNode("User was able to see InActivated Role in dropdown list");
		}
	}


	@Test (description="Test Case: Verify InActive Role on Organization Screen",enabled= true, priority= 10) 
	public void VerifyInActiveRoleOrg() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-RM-12: Verify Role is InActivated on Organization Management Screen", "This test case will verify that user cannot view inactive role on organization create or update screen");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
       
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Adminstration and select Report Management");
		Test_Variables.preconditions.createNode("4. Click on Create New button; popup appears");
		Test_Variables.steps.createNode("1. Create a Role; Click on InActivate toggle button and click on save button");
		Test_Variables.steps.createNode("2. Go to Organization Management");
		Test_Variables.steps.createNode("3. Click on Create New button and go to step 2 of create organization popup");
		Test_Variables.steps.createNode("4. Open Report Roles dropdown");
		Test_Variables.steps.createNode("5. Find the inactivated role in dropdown list");
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);
		Helper.driver.get(Constants.url_organization);
		Thread.sleep(1500);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgExpandAnceraTab)));
		Helper.driver.findElement(By.xpath(Test_Elements.orgExpandAnceraTab)).click();
		Thread.sleep(2000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateButton)).click();
		Thread.sleep(1500);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.rmorgButton1)));
		Helper.driver.findElement(By.xpath(Test_Elements.rmorgButton1)).click();
		Thread.sleep(1500);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmOrgUpdateRole)).sendKeys(Test_Variables.RoleName);

		Thread.sleep(2000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.rmOrgUpdateRoleSelect)).getText();
		Thread.sleep(2000);
		try{
			Assert.assertEquals(actual, "No items found"); 
			Test_Variables.test.pass("InActivated Role was not found in Organization Management screen");
			Test_Variables.results.createNode("User was not able to see InActivated Role in dropdown list");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("InActivated Role was found in Organization Management screen");
			Test_Variables.results.createNode("User was able to see InActivated Role in dropdown list");
		}

	}


	@Test (description="Open Create Report Groups Popup", enabled= true, priority= 11) 
	public void OpenReportGroupsPopup() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-RM-13: Verify user can open Create Report Group Popup", "This test case will verify that user is able to open create report group popup");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
       
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
		Test_Variables.steps.createNode("1. Click on Report Group button");

		Helper.driver.get(Constants.url_reportsManagement);
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.rmReportGroupsCreateButton)));
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsCreateButton)).click();

		Thread.sleep(1500);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsPopupGetTitle)).getText();
		String expected = "Report Groups";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report Groups popup window opened successfully");
			Test_Variables.results.createNode("Report Groups popup window opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("Report Groups popup window did not open successfully");
			Test_Variables.results.createNode("Failed to open Report Groups popup window");
		}
	}


	@Test (description="Open Report Details Popup", enabled= true, priority= 12) 
	public void OpenReportDetails() throws InterruptedException, IOException {

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
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsAddbutton)).click();
		Thread.sleep(1000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsDetailsGetTitle)).getText();
		String expected = "Group Details";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Create Report Group details screen opened successfully");
			Test_Variables.results.createNode("Report Group screen opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("Create Report Group details screen failed to open");
			Test_Variables.results.createNode("Report Group screen failed to open");
		}
	}




	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 13) 
	public void ResetGroupReportsFieldCheck() throws InterruptedException, IOException {

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
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpName)).sendKeys("Test");
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpDesc)).sendKeys("Description");
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsReports)).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsReportsSelect)).click();
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsReportsClose)).click();
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsResetButton)).click();
		Thread.sleep(1000);

		String nameActual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpName)).getAttribute("value");
		String descActual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpDesc)).getAttribute("value");
		String reportActual = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsReportsInput)).getAttribute("value");

		try{
			Assert.assertEquals(nameActual, ""); 
			Assert.assertEquals(descActual, "");
			Assert.assertEquals(reportActual, "");
			Test_Variables.test.pass("Report Group Details fields reset successfully");
			Test_Variables.results.createNode("Report Group Details fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("Report Group Details fields failed to reset");
			Test_Variables.results.createNode("Report Group Details fields failed to reset");
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

			Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpName)).clear();
			Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpName)).sendKeys(objModel.rgName);
			Thread.sleep(2000);
			Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpDesc)).clear();
			Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpDesc)).sendKeys(objModel.rgDesc);
			Thread.sleep(2000);

			if(objModel.rgReport) {
				Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsReports)).click();
				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsReportsSelect)).click();
				Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsReportsClose)).click();
			}

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsSaveButton)).click();
			try{
				if(objModel.rgstep)	
				{
					Thread.sleep(2000);
					if ( objModel.rgName.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpNameValidation)).isDisplayed()) {
							rmNameError = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpNameValidation)).getText();
						}
						Assert.assertEquals(rmNameError, "Group name is required"); 

					}

					if ( objModel.rgDesc.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpDescValidation)).isDisplayed()) {
							rmDescError = Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsGrpDescValidation)).getText();
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
//				Test_Variables.test = Test_Variables.extent.createTest("AN-RM-20: Verify user can save Report Details: Verify that user can save report details on filling all mandatory fields");
//				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
//				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
//				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
//
//				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
//				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
//				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Report Management");
//				Test_Variables.preconditions.createNode("4. Click on Report Groups button; Report Group popup opens");
//				Test_Variables.preconditions.createNode("5. Click on '+' icon; Report Group Details screen shows up");
//				Test_Variables.steps.createNode("1. Enter valid data in all fields");
//				Test_Variables.steps.createNode("2. Click on save button");

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
				Thread.sleep(1000);
				String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
				String expected = "New report group created.";

	//			try{
					Assert.assertEquals(actual, expected); 
	//				Test_Variables.test.pass("Report Group details saved successfully");
	//				Test_Variables.results.createNode("Report Group details saved successfully");
	//				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
	//			}catch(AssertionError e){
	//				Test_Variables.test.fail("Report Group details failed to save");
		//			Test_Variables.results.createNode("Report Group details failed to save");
	//			}	
			}
			
			Test_Variables.test.pass(objModel.rgpassScenario);
			Test_Variables.results.createNode(objModel.rgpassScenario);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
			}
			catch(AssertionError e){
				Test_Variables.test.fail(objModel.rgfailScenario);
				Test_Variables.results.createNode(objModel.rgfailScenario);
			}
		}
	}


	@Test (description="Test Case: Update Report Details", enabled= true, priority= 15) 
	public void UpdateReportDetails() throws InterruptedException, IOException {

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
			Thread.sleep(1500);

			if (element.getText().equals(Test_Variables.ReportName)) {
				Helper.driver.findElement(By.xpath(Test_Elements.rgbeforeXpath+i+Test_Elements.rgafterXpath)).click();
				break;
			}
		}

		Thread.sleep(1500);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.rmReportGroupsUpdateDesc)));
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsUpdateDesc)).clear();
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsUpdateDesc)).sendKeys("Automated Report Details Update Test ");
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmReportGroupsUpdateButton)).click();
		Thread.sleep(1000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		Thread.sleep(1000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = "Report Group details updated.";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report details updated successfully");
			Test_Variables.results.createNode("Report Group details updated successfully; an alert message was generated 'Report Group details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Report details failed to update");
			Test_Variables.results.createNode("Report Group details failed to update");
		}
	}



	@Test (description="Test Case: Open Edit Rights Popup",enabled= true, priority= 16) 
	public void OpenEditRightsPopup() throws InterruptedException, IOException {

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
		Thread.sleep(3500);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		Helper.driver.findElement(By.xpath(Test_Elements.rmEditRightsButton)).click();
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.rmEditRightsGetTitle)));
		Thread.sleep(1500);
		String actual =	Helper.driver.findElement(By.xpath(Test_Elements.rmEditRightsGetTitle)).getText();
		String expected = "Edit Rights";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Edit Rights popup window opened successfully");
			Test_Variables.results.createNode("Edit Rights popup window opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
		}catch(AssertionError e){
			Test_Variables.test.fail("Edit Rights popup window opening failed");
			Test_Variables.results.createNode("Edit Rights popup window failed to open");
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
	
	
	@Test (description="Test Case: Delete Report Group",enabled= true, priority= 21) 
	public void DeleteReportGroup() throws InterruptedException, IOException {
		try{
		Test_Variables.test = Test_Variables.extent.createTest("AM-RM-28: Verify user can delete Report Group", "This test case will verify that user can delete Report Group");
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
		Helper.driver.get(Constants.url_reportsManagement);
	
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
		String actual = Helper.driver.findElement(By.xpath("message")).getText();
		String expected = "Report Group details deleted.";

	
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Report Configuration details deleted successfully");
			Test_Variables.results.createNode("User should receive an alert message that 'Report Group details deleted.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Report Management", Constants.ReportManagementReportPath));	
			Thread.sleep(1000);
		}catch(AssertionError e){
			Test_Variables.test.fail("Report Configuration details deletion failed");
			Test_Variables.results.createNode("User did not receive an alert message that 'Report Group details deleted.'");
		}
	}


	@AfterMethod
	public void saveResult(ITestResult result) throws IOException {
		Helper.saveResult(result, Constants.ReportManagementReportPath);
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}

}


