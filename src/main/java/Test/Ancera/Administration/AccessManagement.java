package Test.Ancera.Administration;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;

public class AccessManagement{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Access_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Access Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Access Management Screen",enabled= false, priority = 1) 
	public void NavigateAM() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-01: Verify user can navigate to access management screen", "This test case will verify that user can navigate to access management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Adminstration and select Access Management");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.driver.get(Constants.url_access);
			Thread.sleep(2000);
			String actual = Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText();
			String expected = "Access Management";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully to Access Management screen");
			Test_Variables.results.createNode("User navigates to Access Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("User did not navigated to Access Management screen");
			Test_Variables.results.createNode("User did not navigate to Access Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User did not navigated to Access Management screen");
			Test_Variables.results.createNode("User did not navigate to Access Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}

	
	@Test (description="Test Case: User Management Access",enabled= true, priority = 2) 
	public void UserManagementAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("aa", "aa");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
			Test_Variables.steps.createNode("1. Click on filed access icon; popup appears");

			///////////////////////////////

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4)  label")).getText().equals(Test_Variables.login_email)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();
			System.out.println(getSystemRole);

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.id("create-user")).size(), 0);

			////////////////////

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			
			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("edit-user-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-untouched#firstNameId")).size(), 1);
			////////////////////////////////////

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isViewUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Helper.driver.findElement(By.id("menu-administration")).click();			
			Assert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageUsersMenu")).size(), 0);

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isViewUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Create Access popup failed to open");
			Test_Variables.results.createNode("Create Access popup failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Create Access popup failed to open");
			Test_Variables.results.createNode("Create Access popup failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Access Management",enabled= false, priority= 2) 
	public void CreateAccess() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-02: Verify user can open Create New Access Popup", "This test case will verify that user can open create new access popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.accessCreateButton)).click();
			Thread.sleep(2000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.steps.createNode("1. Click on Create New button");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.accessGetPopupTitle)));
			String getTitleActual = Helper.driver.findElement(By.xpath(Test_Elements.accessGetPopupTitle)).getText();
			String getTitleExpected = "Create Role";

			Assert.assertEquals(getTitleActual, getTitleExpected); 
			Test_Variables.test.pass("Access Popup opened successfully");
			Test_Variables.results.createNode("Create Access popup opens");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Create Access popup failed to open");
			Test_Variables.results.createNode("Create Access popup failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Create Access popup failed to open");
			Test_Variables.results.createNode("Create Access popup failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
		Thread.sleep(1000);

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-03: Verify Mandatory fields check", "This test case will verify madatory field check in create access popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.accessSaveButton)).click();
			Thread.sleep(1000);
			String nameValidationActual = Helper.driver.findElement(By.xpath(Test_Elements.accessNameValidation)).getText();
			String nameValidationExpected = Test_Elements.accessNameValidationExpected;
			String descValidationActual = Helper.driver.findElement(By.xpath(Test_Elements.accessDescValidation)).getText();
			String descValidationExpected = Test_Elements.accessDescValidationExpected;
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on Create New button");				
			Test_Variables.steps.createNode("1. Leave name field empty");
			Test_Variables.steps.createNode("2. Leave description field empty");
			Test_Variables.steps.createNode("3. Click on save button");

			Assert.assertEquals(nameValidationActual, nameValidationExpected); 
			Assert.assertEquals(descValidationActual, descValidationExpected); 
			Test_Variables.test.pass("Mandatory Field check verified successfully");
			Test_Variables.results.createNode("Does not save Role; displays validation messages underneath name and description fields");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Mandatory Field check verification failed");
			Test_Variables.results.createNode("Saved Role; displayed validation messages underneath name and description fields");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Mandatory Field check verification failed");
			Test_Variables.results.createNode("Saved Role; displayed validation messages underneath name and description fields");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}	

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-04: Verify reset fields check", "This test case will verify field reset check in create new access popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Thread.sleep(1000);
			WebElement accessName = Helper.driver.findElement(By.xpath(Test_Elements.accessName));
			accessName.sendKeys(Test_Variables.lstAccessCreate.get(0));

			WebElement accessDesc = Helper.driver.findElement(By.xpath(Test_Elements.accessDesc));
			accessDesc.sendKeys(Test_Variables.lstAccessCreate.get(1));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.accessResetButton)).click();
			Thread.sleep(3000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on Create New button");
			Test_Variables.steps.createNode("1. Enter data in name field");
			Test_Variables.steps.createNode("2. Enter data in description field");
			Test_Variables.steps.createNode("3. Click on reset button");

			String nameActual = accessName.getAttribute("value");
			String descActual = accessDesc.getAttribute("value");
			String expected = "";

			Assert.assertEquals(nameActual, expected); 
			Assert.assertEquals(descActual, expected); 
			Test_Variables.test.pass("Access Reset successfully");
			Test_Variables.results.createNode("Fields reset successfully");
			//Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Fields failed to reset");
			Test_Variables.results.createNode("Fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Fields failed to reset");
			Test_Variables.results.createNode("Fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}	

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-05: Verify user can create Role", "This test case will verify that user can create a new role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Helper.driver.findElement(By.id("nameId")).sendKeys(Test_Variables.lstAccessCreate.get(0));
			Helper.driver.findElement(By.id("DescId")).sendKeys(Test_Variables.lstAccessCreate.get(1));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.driver.findElement(By.id("btn-save")).click();
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on Create New button");
			Test_Variables.steps.createNode("1. Enter valid data in name field");
			Test_Variables.steps.createNode("2. Enter valid data in description field");
			Test_Variables.steps.createNode("3. Click on save button");
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstAccessAlertMessages.get(0)); 
			Test_Variables.test.pass("Role created successfully");
			Test_Variables.results.createNode("New role created; displays alert message 'New role created.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			ClickElement.clickByCss(Helper.driver, ".close");
		}catch(AssertionError er){
			Test_Variables.test.fail("New role failed to create");
			Test_Variables.results.createNode("New role failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("New role failed to create");
			Test_Variables.results.createNode("New role failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}	
	}



	@Test (description="Test Case: Update Access ",enabled= false, priority= 3) 
	public void UpdateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-06: Verify user can update Role", "This test case will verify that user can update a role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			//	Helper.driver.get(Constants.url_access);
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Test_Functions.AccessFind();
			Thread.sleep(2000);

			WebElement desc = Helper.driver.findElement(By.id("DescId"));
			desc.clear();
			desc.sendKeys("Role Updated");
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.driver.findElement(By.id("btn-save")).click();
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");

			Test_Variables.steps.createNode("1. Click on update button next to created role");
			Test_Variables.steps.createNode("2. Update name and description of role");
			Test_Variables.steps.createNode("3. Click on save button");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = Test_Variables.lstAccessAlertMessages.get(1) ;

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role updated successfully");
			Test_Variables.results.createNode("Role updated; user receives an alert message that 'Role details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			Test_Variables.results.createNode("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			Test_Variables.results.createNode("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}			
	}


	@Test (description="Test Case: Verify Update Access",enabled= false, priority= 4) 
	public void VerifyUpdateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-07: Verify role remains updated on reopening the Role", "This test case will verify that changes made in role remains saved on reopening the role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1000);		
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Test_Functions.AccessFind();
			Thread.sleep(1500);  

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.preconditions.createNode("5. Update created role");

			Test_Variables.steps.createNode("1. Reopen updated role by clicking on update button");
			Test_Variables.steps.createNode("2. Verify the updation made in fields are saved");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DescId")));
			Thread.sleep(1500);
			String actual = Helper.driver.findElement(By.id("DescId")).getAttribute("value");
			String expected = "Role Updated";
			Thread.sleep(1000);

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role updation verified successfully");
			Test_Variables.results.createNode("Changes remained saved");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Role updation verification failed");
			Test_Variables.results.createNode("Role updation verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Role updation verification failed");
			Test_Variables.results.createNode("Role updation verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: InActivate Role",enabled= false, priority= 5) 
	public void InActivateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-08: Verify user can InActivate Role", "This test case will verify that role can be made inactive");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);

			Helper.driver.findElement(By.id("role-status")).click();
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.steps.createNode("1. Reopen created role");
			Test_Variables.steps.createNode("2. Click on InActive toggle button");
			Test_Variables.steps.createNode("3. Click on save button");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));

			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = Test_Variables.lstAccessAlertMessages.get(1) ;

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Role inactivated successfully");
			Test_Variables.results.createNode("User receives an alert message that 'Role details updated'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Thread.sleep(1000);
			ClickElement.clickByCss(Helper.driver, ".close");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("User does not receives an alert message that 'Role details updated'");
			Test_Variables.results.createNode("User does not receives an alert message that 'Role details updated'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User does not receives an alert message that 'Role details updated'");
			Test_Variables.results.createNode("User does not receives an alert message that 'Role details updated'");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}		
	}


	@Test (description="Test Case: Edit Rights screen",enabled= false, priority= 6) 
	public void EditRightsScreen() throws InterruptedException, IOException
	{
		try{
			Helper.driver.get(Constants.url_access);
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);

			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-09: Verify user can open Edit Rights screen", "This test case will verify that user can open Edit Rights screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.steps.createNode("1. Click on edit rights button next to created role");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));

			Thread.sleep(2000);		

			for (int i=2; i<=80; i++) {
				int j = i-1;
				String actualXpath = Test_Elements.accessBeforeXpath+j+Test_Elements.accessAfterXpath;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
				Thread.sleep(1000);
				if (element.getText().equals(Test_Variables.lstAccessCreate.get(0))) {
					Helper.driver.findElement(By.xpath(Test_Elements.accessBeforeXpath+j+Test_Elements.accessAfterXpath2)).click(); 
					break;
				}}


			Thread.sleep(2000);
			String getTitleActual = Helper.driver.findElement(By.xpath(Test_Elements.accessGetPopupTitle)).getText();
			String getTitleExpected = "Edit Rights";

			Assert.assertEquals(getTitleActual, getTitleExpected); 
			Test_Variables.test.pass("Edit Rights Popup opened successfully");
			Test_Variables.results.createNode("Edits Rights popup opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Edit Rights Popup opened failed");
			Test_Variables.results.createNode("Edit Rights Popup opened failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Edit Rights Popup opened failed");
			Test_Variables.results.createNode("Edit Rights Popup opened failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}	
		Thread.sleep(1000);	

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-10: Verify user can update Rights", "This test case will verify user can update Rights");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.accessEditRights)));

			Helper.driver.findElement(By.xpath(Test_Elements.accessEditRights)).click();
			Thread.sleep(1000);	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));

			Helper.driver.findElement(By.xpath(Test_Elements.accessEditRightsSaveButton)).click();

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.preconditions.createNode("5. Click on edit rights button next to created role; edit rights popup opens");		

			Test_Variables.steps.createNode("1. Select rights from list");
			Test_Variables.steps.createNode("2. Click on save button");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			String actual = Helper.driver.findElement(By.id("message")).getText();
			String expected = "Rights details updated." ;

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Rights updated successfully");
			Test_Variables.results.createNode("Rights updated; an alert message displays 'Rights details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Thread.sleep(1000);
			ClickElement.clickByCss(Helper.driver, ".close");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Rights failed to update");
			Test_Variables.results.createNode("Rights failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Rights failed to update");
			Test_Variables.results.createNode("Rights failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}				
	}



	@Test (description="Test Case: Role Assign screen",enabled= false, priority= 7) 
	public void RoleAssignScreen() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-11: Verify user can open Role Assign screen", "This test case will verify that user can open Role Assign screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Helper.driver.get(Constants.url_access);
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.steps.createNode("1. Click on role button next to created role");	

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));

			for (int i=2; i<=80; i++) {
				int j = i-1;
				String actualXpath = Test_Elements.accessBeforeXpath+j+Test_Elements.accessAfterXpath;
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
				Thread.sleep(1000);
				if (element.getText().equals(Test_Variables.lstAccessCreate.get(0))) {
					Helper.driver.findElement(By.xpath(Test_Elements.accessBeforeXpath+j+Test_Elements.accessAfterXpath3)).click(); 
					break;
				}}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.accessGetAssignRoleTitle)));
			String getTitleActual = Helper.driver.findElement(By.xpath(Test_Elements.accessGetAssignRoleTitle)).getText();
			String getTitleExpected = "Users";


			Assert.assertEquals(getTitleActual, getTitleExpected); 
			Test_Variables.test.pass("Role Assign screen opened successfully");
			Test_Variables.results.createNode("Role Assign screen opens successfully");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Role Assign screen failed to open");
			Test_Variables.results.createNode("Role Assign screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Role Assign screen failed to open");
			Test_Variables.results.createNode("Role Assign screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
		Thread.sleep(1000);	
		Helper.driver.findElement(By.xpath(Test_Elements.accessCloseAssignRole)).click();
	}
	
	
	

	@Test (description="Test Case: Role Assign screen",enabled= false, priority= 7) 
	public void RoleAssignScfreen() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-11: Verify user can open Role Assign screen", "This test case will verify that user can open Role Assign screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Helper.driver.get(Constants.url_access);
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.steps.createNode("1. Click on role button next to created role");	
			
			
			
		}catch(AssertionError er){
			Test_Variables.test.fail("Role Assign screen failed to open");
			Test_Variables.results.createNode("Role Assign screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Role Assign screen failed to open");
			Test_Variables.results.createNode("Role Assign screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
		Thread.sleep(1000);	
		Helper.driver.findElement(By.xpath(Test_Elements.accessCloseAssignRole)).click();
	}
	
			
	
	
	
	
	
	
	
	
	

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}

}



