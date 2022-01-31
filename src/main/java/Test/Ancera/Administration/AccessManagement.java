package Test.Ancera.Administration;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


	@Test (description="Test Case: Navigate to Access Management Screen",enabled= true, priority = 1) 
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
			String actual = Helper.driver.findElement(By.id("Access Management")).getText();
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


	@Test (description="Test Case: Access Management",enabled= true, priority= 2) 
	public void CreateAccess() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
	
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



	@Test (description="Test Case: Update Access ",enabled= true, priority= 3) 
	public void UpdateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-06: Verify user can update Role", "This test case will verify that user can update a role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			//	Helper.driver.get(Constants.url_access);
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
		Helper.driver.findElement(By.cssSelector("button.close span")).click();
	}


	@Test (description="Test Case: Verify Update Access",enabled= true, priority= 4) 
	public void VerifyUpdateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-07: Verify role remains updated on reopening the Role", "This test case will verify that changes made in role remains saved on reopening the role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Thread.sleep(1000);		
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



	@Test (description="Test Case: InActivate Role",enabled= true, priority= 5) 
	public void InActivateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-08: Verify user can InActivate Role", "This test case will verify that role can be made inactive");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
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
		Helper.driver.findElement(By.cssSelector("button.close span")).click();
	}


	@Test (description="Test Case: Edit Rights screen",enabled= true, priority= 6) 
	public void EditRightsScreen() throws InterruptedException, IOException
	{
		try{
			Helper.driver.get(Constants.url_access);
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



	@Test (description="Test Case: Role Assign screen",enabled= true, priority= 7) 
	public void RoleAssignScreen() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-11: Verify user can open Role Assign screen", "This test case will verify that user can open Role Assign screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Helper.driver.get(Constants.url_access);
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



	@Test (description="Test Case: Role Assign screen",enabled= true, priority= 7) 
	public void RoleAssignScfreen() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-11: Verify user can open Role Assign screen", "This test case will verify that user can open Role Assign screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Helper.driver.get(Constants.url_access);
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




	@Test (description="Test Case: User Management Access",enabled= false, priority = 8) 
	public void UserManagementAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-12/13/14: Verify create, update and view role of user", "This test case will verify that create, update and view role of user");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create radio button next to User Management");
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

			Test_Variables.steps.createNode("3. Go to User Management screen and verify that user is not able to create a new user");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.id("create-user")).size(), 0);

			////////////////////

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect Edit radio button");
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

			Test_Variables.steps.createNode("5. Go to User Management screen and verify that user is not able to edit any user");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("edit-user-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-untouched#firstNameId")).size(), 1);
			////////////////////////////////////

			Test_Variables.steps.createNode("6. Go to Access Management screen and unselect View radio button next to User Management");
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

			Test_Variables.steps.createNode("7. Verify that User Management is not visible in side menu bar");
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
			Test_Variables.test.pass("Access Rights passed for User Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for User Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for User Management Screen");
			Test_Variables.results.createNode("Access Rights failed for User Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for User Management Screen");
			Test_Variables.results.createNode("Access Rights failed for User Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Organization Management Access",enabled= false, priority = 9) 
	public void OrganizationManagementAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-15/16/17: Verify create, update and view role of organization", "This test case will verify that create, update and view role of organization");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.login();
			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();


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

			Test_Variables.steps.createNode("2. Unselect Create radio button next to Organization Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Organization Management screen and verify that user is not able to create a new organization");
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.id("create-organization")).size(), 0);

			////////////////////

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect Edit radio button");
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

			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.id("isUpdateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("5. Go to Organization Management screen and verify that user is not able to edit any organization");
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("orgnType-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-orgn-1")));
			Thread.sleep(2000);

			Helper.driver.findElement(By.id("edit-orgn-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-untouched#nameId")).size(), 1);
			////////////////////////////////////

			Test_Variables.steps.createNode("6. Go to Access Management screen and unselect View radio button next to Organization");
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
			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.id("isUpdateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.id("isViewOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) div:nth-child(1)")).click();
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

			Test_Variables.steps.createNode("7. Verify that Organization Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();			
			Assert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageOrganMenu")).size(), 0);

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
			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.id("isUpdateOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.id("isViewOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Organization Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Organization Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Organization Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Organization Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Organization Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Organization Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Organization Sites Management Access",enabled= false, priority = 10) 
	public void OrganizationSitesManagementAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-18/19/20: Verify create, update and view role of organization sites", "This test case will verify that create, update and view role of organization sites");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();


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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Organization Sites");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Organization Management screen and verify that user is not able to create or update a site");
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("orgnType-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-orgn-1")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("edit-orgn-sites-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[2]/div/div[1]/div/ul/div/li/ul/li/div/div[4]/div[1]/img")).size(), 0);
			Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[2]/div/div[1]/div/ul/div/li/ul/li/div/div[4]/div[1]/img")).click();
			Thread.sleep(2000);
			Assert.assertEquals(Helper.driver.findElements(By.id("btn-save")).size(), 0);

			////////////////////////////////////

			Test_Variables.steps.createNode("6. Go to Access Management screen and unselect View radio button next to Organization Sites");
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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();


			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("orgnType-1")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-orgn-1")));
			Thread.sleep(2000);
			Assert.assertEquals(Helper.driver.findElements(By.id("edit-orgn-sites-1")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewOrganization Sites']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Organization Sites successfully");
			Test_Variables.results.createNode("Access Rights passed for Organization Sites successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Organization Sites");
			Test_Variables.results.createNode("Access Rights failed for Organization Sites");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Organization Sites");
			Test_Variables.results.createNode("Access Rights failed for Organization Sites");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Alert Configuration Access",enabled= false, priority = 11) 
	public void AlertConfigurationAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-24/25/26: Verify create, update and view role of alert configuration", "This test case will verify that create, update and view role of alert configuration");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();


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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Alert Configuration");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Alert Management screen and verify that user is not able to create or update any alert");
			Helper.driver.get(Constants.url_alert);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.id("create-role")).size(), 0);
			Assert.assertEquals(Helper.driver.findElements(By.id("duplicate-active-alert-1")).size(), 0);

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Alert Configuration");
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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(4) div:nth-child(1)")).click();
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

			Test_Variables.steps.createNode("7. Verify that Alert Configuration is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();			
			Assert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageAlertMenu")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAlert Configurations']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Alert Configuration Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Alert Configuration Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Alert Configuration Screen");
			Test_Variables.results.createNode("Access Rights failed for Alert Configuration Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Alert Configuration Screen");
			Test_Variables.results.createNode("Access Rights failed for Alert Configuration Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Report Role Access",enabled= false, priority = 12) 
	public void ReportRoleAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-27/28/29/30/31/32: Verify create, update and view role of report role and report group", "This test case will verify that create, update and view role of report role and report group");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			///////////////////////////////
			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Report Role and Report Group");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReport Groups']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReport Groups']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(3) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Report Management screen and verify that user is not able to create or update any report role or group");
			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.id("create-report-role")).size(), 0);

			Helper.driver.findElement(By.id("edit-report-role-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#nameId.ng-touched")).size(), 0);

			Helper.driver.findElement(By.id("close-popup-modal")).click();
			//////////////////////////////////////////
			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Alert Configuration");
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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(4) div:nth-child(1)")).click();
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

			Test_Variables.steps.createNode("7. Verify that Alert Configuration is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();			
			Assert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageReportRole")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReporting Roles']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(4) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReport Groups']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReport Groups']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(3) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Alert Configuration Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Alert Configuration Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Alert Configuration Screen");
			Test_Variables.results.createNode("Access Rights failed for Alert Configuration Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Alert Configuration Screen");
			Test_Variables.results.createNode("Access Rights failed for Alert Configuration Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Agreement Role Access",enabled= false, priority = 13) 
	public void AgreeemntRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-33/34/35: Verify create, update and view role of Agreemeent Management", "This test case will verify that create, update and view role of Agreeement Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Agreement Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAgreement Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAgreement Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(3) div:nth-child(1)")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Agreement Management screen and verify that user is not able to create or update any agreement");
			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("file-license")).size(), 0);
			softAssert.assertEquals(Helper.driver.findElements(By.id("no_radio")).size(), 0);

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Agreement Management");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAgreement Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Agreement Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageUserAgreement")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAgreement Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAgreement Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAgreement Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(4) div:nth-child(1)")).click();
			}


			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Agreement Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Agreement Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Agreement Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Agreement Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Agreement Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Agreement Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Barcode Role Access",enabled= false, priority = 14) 
	public void BarcodeRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-36/37/38: Verify create, update and view role of Barcode Management", "This test case will verify that create, update and view role of Barcode Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Barcode Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateBarcode Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateBarcode Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(3) div:nth-child(1)")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Barcode Management screen and verify that user is not able to create or update any barcode");
			Helper.driver.get(Constants.url_barcodeManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("fa-print")).size(), 0);

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Barcode Management");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewBarcode Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Barcode Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageBarcode")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateBarcode Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateBarcode Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewBarcode Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Barcode Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Barcode Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Barcode Management Screen");
			Test_Variables.results.createNode("Access Rights failed for AgBarcodereement Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Barcode Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Barcode Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Piper Management Role Access",enabled= false, priority = 15) 
	public void PiperManagementRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-39/40/41: Verify create, update and view role of Piper Management", "This test case will verify that create, update and view role of Piper Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Piper Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(3) div:nth-child(1)")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Piper Management screen and verify that user is not able to create or update any piper");
			Helper.driver.get(Constants.url_piperManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("button:nth-child(1)")).size(), 0);

			Helper.driver.findElement(By.id("piper-1")).click();
			Thread.sleep(1500);
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-touched")).size(), 0);

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Piper Management");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Agreement Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-piper")).click();	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManagePiperScreen")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Piper Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Piper Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Piper Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Piper Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Piper Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Piper Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Piper Software Management Role Access",enabled= false, priority = 16) 
	public void PiperSoftwareManagementRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-42/43/44: Verify create, update and view role of Piper Software Management", "This test case will verify that create, update and view role of Piper Software Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Piper Software Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);                                                  
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Software Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Software Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(3) div:nth-child(1)")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Piper Software Management screen and verify that user is not able to create or update any piper");
			Helper.driver.get(Constants.url_piperSoftware);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("file-userapp")).size(), 0);
			softAssert.assertEquals(Helper.driver.findElements(By.id("upd-improc-userapp-ic-0")).size(), 0);

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Piper Software Management");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Software Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Piper Software Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-piper")).click();	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTPiperSoftware")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Software Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Software Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Software Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Piper Software Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Piper Software Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Piper Software Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Piper Software Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Piper Software Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Piper Software Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Piper Config Management Role Access",enabled= false, priority = 17) 
	public void PiperConfigManagementRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-45/46/47: Verify create, update and view role of Piper Configuration Management", "This test case will verify that create, update and view role of Piper Configuration Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Piper Configuration Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(3) div:nth-child(1)")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Piper Configuration Management screen and verify that user is not able to create or update any coonfiguration");
			Helper.driver.get(Constants.url_piperConfiguration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("create-installation-run")).size(), 0, "User is still able to create config");
			softAssert.assertEquals(Helper.driver.findElements(By.id("create-mpn")).size(), 0, "User is still able to edit config");
			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Piper Configuration");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPIPER Configuration Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Piper Configuration Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-piper")).click();	
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManagePiperConfig")).size(), 0, "Piper Configuration is still displayed in side menu");

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePIPER Configuration Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPIPER Configuration Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Piper Configuration Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Piper Configuration Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Piper Configuration Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Piper Configuration Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Piper Configuration Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Piper Configuration Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Data Template Management Role Access",enabled= false, priority = 18) 
	public void DataTemplateAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-48/49/50: Verify create, update and view role of Data Template Management", "This test case will verify that create, update and view role of Data Template Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Data Template Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Template']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateData Template']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(3) div:nth-child(1)")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Data Template Management screen and verify that user is not able to create or update any data template");
			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));

			softAssert.assertEquals(Helper.driver.findElements(By.id("create-data-format")).size(), 0);
			softAssert.assertEquals(Helper.driver.findElements(By.id("delete-data-format")).size(), 0);

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Data Management");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewData Template']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Data Template Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-metadata")).click();	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageDataTemplateMenu")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Template']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateData Template']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewData Template']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Data Template Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Data Template Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Data Template Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Data Template Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Data Template Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Data Template Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Data Upload Role Access",enabled= false, priority = 19) 
	public void DataUploadAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-51: Verify create of Data Upload Management", "This test case will verify that create, update and view role of Data Template Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create  radio button next to Data Upload");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Upload']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(14) td:nth-child(2) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Data Template Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-metadata")).click();	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTDataUpload")).size(), 0);

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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Upload']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(14) td:nth-child(2) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Data Upload Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Data Upload Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Data Upload Screen");
			Test_Variables.results.createNode("Access Rights failed for Data Upload Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Data Upload Screen");
			Test_Variables.results.createNode("Access Rights failed for Data Upload Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Poultry Management Role Access",enabled= false, priority = 20) 
	public void PoultryManagementAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-48/49/50: Verify create, update and view role of Poultry Management", "This test case will verify that create, update and view role of Poultry Management");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Poultry Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePoultry Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePoultry Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(3) div:nth-child(1)")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Poultry Management screen and verify that user is not able to create or update poultry");
			Helper.driver.get(Constants.url_poultryManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));

			softAssert.assertEquals(Helper.driver.findElements(By.id("create-treatment")).size(), 0);
			softAssert.assertEquals(Helper.driver.findElements(By.id("delete-treatment-1")).size(), 0);

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Poultry Management");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPoultry Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("5. Verify that Poultry Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-metadata")).click();	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManagePoultry")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePoultry Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(2) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePoultry Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(3) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPoultry Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Poultry Management Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Poultry Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Poultry Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Poultry Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Poultry Management Screen");
			Test_Variables.results.createNode("Access Rights failed for Poultry Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Report, Help Contact Us Role Access",enabled= false, priority = 21) 
	public void ReportHelpContactUsAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-55/56/57/58: Verify view role of Reports, Dashboard, Contact Us and Help Page", "This test case will verify the view role of Reports, Dashboard, Contact Us and Help Page");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Access Management");
			Test_Variables.steps.createNode("1. Click on edit rights icon next to assigned role");

			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

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


			Test_Variables.steps.createNode("2. Go to Access Management screen and unselect View radio button next to Reports, Dashboard, Contact Us and Help");
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

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewDashboard']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(16) td:nth-child(4) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReports']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(17) td:nth-child(4) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewContact us']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(18) td:nth-child(4) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewHelp']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(19) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Reports, Dashboard, Contact Us and Help is not visible in side menu bar");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTAnalysisDashboard")).size(), 0);
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTReport")).size(), 0);
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageContactUs")).size(), 0);
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageHelp")).size(), 0);

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
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewDashboard']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(16) td:nth-child(4) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReports']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(17) td:nth-child(4) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewContact us']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(18) td:nth-child(4) div:nth-child(1)")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewHelp']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(19) td:nth-child(4) div:nth-child(1)")).click();
			}

			Helper.driver.findElement(By.cssSelector(".btn-ok")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details updated.");
			Test_Variables.test.pass("Access Rights passed for Reports, Dashboard, Contact Us and Help Screen successfully");
			Test_Variables.results.createNode("Access Rights passed for Reports, Dashboard, Contact Us and Help Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			Test_Variables.results.createNode("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			Test_Variables.results.createNode("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.quit();
	}
}