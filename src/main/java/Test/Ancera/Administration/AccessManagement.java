package Test.Ancera.Administration;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


	@Test (enabled= true, priority= 2) 
	public void OpenPopup() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-02: Verify user can open Create New Access Popup", "This test case will verify that user can open create new access popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.steps.createNode("1. Click on Create New button");

			Helper.driver.findElement(Test_Elements.accessCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector(".popup-header")).getText(), "Create Role", "Create popup failed to open"); 
			Test_Variables.test.pass("Popup opened successfully");
			Test_Variables.results.createNode("Popup opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Popup failed to open");
			Test_Variables.results.createNode("Popup failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Popup failed to open");
			Test_Variables.results.createNode("Popup failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 3) 
	public void MandatoryCheck() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-03: Verify Mandatory fields check", "This test case will verify madatory field check in create access popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on Create New button");				
			Test_Variables.steps.createNode("1. Leave name field empty");
			Test_Variables.steps.createNode("2. Leave description field empty");
			Test_Variables.steps.createNode("3. Click on save button");

			Helper.driver.findElement(Test_Elements.accessName).click();
			Helper.driver.findElement(Test_Elements.accessDesc).click();
			Helper.driver.findElement(Test_Elements.accessName).click();
			Assert.assertEquals(Helper.driver.findElements(Test_Elements.accessNameDescValidation).size(), 4); 
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
	}


	@Test (enabled= true, priority= 4) 
	public void ResetAccess() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-04: Verify reset fields check", "This test case will verify field reset check in create new access popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on Create New button");
			Test_Variables.steps.createNode("1. Enter data in name field");
			Test_Variables.steps.createNode("2. Enter data in description field");
			Test_Variables.steps.createNode("3. Click on reset button");

			WebElement accessName = Helper.driver.findElement(Test_Elements.accessName);
			accessName.sendKeys(Test_Variables.lstAccessCreate.get(0));

			WebElement accessDesc = Helper.driver.findElement(Test_Elements.accessDesc);
			accessDesc.sendKeys(Test_Variables.lstAccessCreate.get(1));

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Thread.sleep(1000);

			String nameActual = accessName.getAttribute("value");
			String descActual = accessDesc.getAttribute("value");
			String expected = "";

			Assert.assertEquals(nameActual, expected); 
			Assert.assertEquals(descActual, expected); 
			Test_Variables.test.pass("Access Reset successfully");
			Test_Variables.results.createNode("Fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
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
	}


	@Test (description="Test Case: Create Access",enabled= true, priority= 5) 
	public void CreateAccess() throws InterruptedException, IOException {	
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-05: Verify user can create Role", "This test case will verify that user can create a new role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on Create New button");
			Test_Variables.steps.createNode("1. Enter valid data in name field");
			Test_Variables.steps.createNode("2. Enter valid data in description field");
			Test_Variables.steps.createNode("3. Click on save button");

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			SoftAssert softAssert = new SoftAssert();
			int rows = Helper.driver.findElements(By.cssSelector("tr")).size()-1;
			String resultsFound = Helper.driver.findElement(By.id("results-found-count")).getText();
			softAssert.assertEquals(rows, Integer.parseInt(resultsFound), "Results count not showing correct number of access in table");

			Helper.driver.findElement(Test_Elements.accessCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.accessName).sendKeys(Test_Variables.lstAccessCreate.get(0));
			Helper.driver.findElement(Test_Elements.accessDesc).sendKeys(Test_Variables.lstAccessCreate.get(1));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstAccessAlertMessages.get(0)); 
			softAssert.assertEquals(rows+1, Integer.parseInt(resultsFound)+1);
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


	@Test (description="Test Case: Update Access ",enabled= true, priority= 6) 
	public void UpdateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-06: Verify user can update Role", "This test case will verify that user can update a role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.steps.createNode("1. Click on update button next to created role");
			Test_Variables.steps.createNode("2. Update name and description of role");
			Test_Variables.steps.createNode("3. Click on save button");

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();

			for (int i=1; i<rows;i++) {
					if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.lstAccessCreate.get(0)) ) {
			//	if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals("Administrator5244") ) {
					i = i-1;
					Helper.driver.findElement(By.id("edit-role-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					break;
				}
			}
			
			WebElement desc = Helper.driver.findElement(Test_Elements.accessDesc);
			desc.clear();
			desc.sendKeys("Role created by automation script");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstAccessAlertMessages.get(1)); 
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
		Helper.driver.findElement(Test_Elements.alertMessageClose).click();
	}


	@Test (description="Test Case: Verify Update Access",enabled= true, priority= 7) 
	public void VerifyUpdateRole() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-07: Verify role remains updated on reopening the Role", "This test case will verify that changes made in role remains saved on reopening the role");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.preconditions.createNode("5. Update created role");
			Test_Variables.steps.createNode("1. Reopen updated role by clicking on update button");
			Test_Variables.steps.createNode("2. Verify the updation made in fields are saved");
			
			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.lstAccessCreate.get(0)) ) {
					i=i-1;
					Helper.driver.findElement(By.id("edit-role-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(3000);
					break;
				}
			}

			Assert.assertEquals(Helper.driver.findElement(By.id("descId")).getAttribute("value"), "Role created by automation script"); 
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


	@Test (description="Test Case: InActivate Role",enabled= true, priority= 8) 
	public void InActivateRole() throws InterruptedException, IOException
	{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-08: Verify user can InActivate Role", "This test case will verify that role can be made inactive");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.steps.createNode("1. Reopen created role");
			Test_Variables.steps.createNode("2. Click on InActive toggle button");
			Test_Variables.steps.createNode("3. Click on save button");

			Helper.driver.findElement(By.cssSelector("#role-status .toggle")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
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





	@Test (description="Test Case: Role Assign screen",enabled= true, priority= 9) 
	public void RoleAssignScreen() throws InterruptedException, IOException{
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-AM-11: Verify Role Assign screen contains names of user to which the role is assigned", "This test case will verify that Role Assign screen contains names of user to which the role is assigned");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new role");
			Test_Variables.steps.createNode("1. Go to user screen");	
			Test_Variables.steps.createNode("2. Check role assigned to your user");	
			Test_Variables.steps.createNode("3. Navigate back to access screen and verify that in system roles that user appears in list");	
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					WebElement scroll = Helper.driver.findElement(By.id("edit-user-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000); 
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}	

			Thread.sleep(5000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			List<WebElement> systemRoles = Helper.driver.findElements(By.cssSelector("#rolesId .ng-value-label"));
			String systemRoleName = systemRoles.get(0).getText(); 

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(systemRoleName) ) {
					i=i-1;
					Helper.driver.findElement(By.id("view-role-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					break;
				}
			}

			for (int j=1;j<rows;j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(5) label")).getText().equals(Test_Variables.login_email)) {
					Assert.assertTrue(true, "System Role assigned to user does not contains the name of user");
					Test_Variables.test.pass("Role Assign screen contains names of user to which the role is assigned successfully");
					Test_Variables.results.createNode("Role Assign screen contains names of user to which the role is assigned successfully");	
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Access Management", Constants.AccessManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.AccessManagementReportPath, null);
				}				
			}
		}catch(AssertionError er){
			Test_Variables.test.fail("Role Assign screen did not contains names of user to which the role is assigned");
			Test_Variables.results.createNode("Role Assign screen did not contains names of user to which the role is assigned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Role Assign screen did not contains names of user to which the role is assigned");
			Test_Variables.results.createNode("Role Assign screen did not contains names of user to which the role is assigned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AccessManagementReportPath, ex);
		}
		Thread.sleep(1000);	
		Helper.driver.findElement(Test_Elements.popupCloseButton).click();
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
			SoftAssert softAssert = new SoftAssert();
			Test_Functions.getUserAccess();
			List<WebElement> systemRoles = Helper.driver.findElements(By.cssSelector("#rolesId .ng-value-label"));
			String getSystemRole = systemRoles.get(0).getText();

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=500; i++) {
				System.out.println("0");
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create radio button next to User Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to User Management screen and verify that user is not able to create a new user");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElements(By.id("create-user")).size(), 0, "User is able to create user");

			////////////////////

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect Edit radio button");
			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			for(int i=1; i<=500; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			Thread.sleep(1000);
			if (Helper.driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();
			Test_Variables.steps.createNode("5. Go to User Management screen and verify that user is not able to edit any user");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("edit-user-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-untouched#firstNameId")).size(), 1, "User is able to edit user");
			////////////////////////////////////

			Test_Variables.steps.createNode("6. Go to Access Management screen and unselect View radio button next to User Management");
			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=500; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isViewUsers")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that User Management is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();			
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageUsersMenu")).size(), 0, "User Management is visible in sidebar");

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.cssSelector("#isViewUsers")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
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
			List<WebElement> systemRoles = Helper.driver.findElements(By.cssSelector("#rolesId .ng-value-label"));
			String getSystemRole = systemRoles.get(0).getText(); 

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
	
			
			for(int i=1; i<=500; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create radio button next to Organization Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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

			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			for(int i=1; i<=500; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.id("isUpdateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();

			Test_Variables.steps.createNode("5. Go to Organization Management screen and verify that user is not able to edit any organization");
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("edit-orgn-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-untouched#nameId")).size(), 1);
			////////////////////////////////////

			Test_Variables.steps.createNode("6. Go to Access Management screen and unselect View radio button next to Organization");
			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.id("isUpdateOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.id("isViewOrganizations")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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

			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.id("isCreateOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.id("isUpdateOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.id("isViewOrganizations")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			SoftAssert  softAssert = new SoftAssert();
			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();
			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Organization Sites");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Organization Management screen and verify that user is not able to create or update a site");
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("edit-orgn-sites-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElements(Test_Elements.orgAddSite1).size(), 0, "Add Org site button is not hidden");
			Helper.driver.findElement(Test_Elements.orgSite1Click).click();
			Thread.sleep(2000);
			softAssert.assertEquals(Helper.driver.findElements(By.id("btn-save")).size(), 0, "Edit Org Site button is not hidden");

			////////////////////////////////////

			Test_Variables.steps.createNode("6. Go to Access Management screen and unselect View radio button next to Organization Sites");
			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewOrganization Sites']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();


			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			softAssert.assertEquals(Helper.driver.findElements(By.id("edit-orgn-sites-1")).size(), 0, "Edit org site button is viewable");

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewOrganization Sites']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
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
			SoftAssert  softAssert = new SoftAssert();
			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Alert Configuration");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Alert Management screen and verify that user is not able to create or update any alert");
			Helper.driver.get(Constants.url_alert);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElements(By.id("create-role")).size(), 0, "User is able to create alert");
			softAssert.assertEquals(Helper.driver.findElements(By.id("duplicate-active-alert-1")).size(), 0, "User is able to edit alert");

			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Alert Configuration");
			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAlert Configurations']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();
			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Alert Configuration is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();			
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageAlertMenu")).size(), 0, "User is able to view Alert");

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAlert Configurations']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
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
			SoftAssert  softAssert = new SoftAssert();
			///////////////////////////////
			Test_Functions.getUserAccess();
			String getSystemRole = Helper.driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Report Role and Report Group");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReport Groups']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReport Groups']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();

			Test_Variables.steps.createNode("3. Go to Report Management screen and verify that user is not able to create or update any report role or group");
			Helper.driver.get(Constants.url_reportsManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElements(By.id("create-report-role")).size(), 0, "Create button is visible");

			Helper.driver.findElement(By.id("edit-report-role-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#nameId.ng-touched")).size(), 0, "User is able to edit report role");

			Helper.driver.findElement(By.id("close-popup-modal")).click();
			//////////////////////////////////////////
			Test_Variables.steps.createNode("4. Go to Access Management screen and unselect View radio button next to Alert Configuration");
			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReporting Roles']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			Test_Functions.login();

			Actions builder = new Actions(Helper.driver); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = Helper.driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			Test_Variables.steps.createNode("7. Verify that Alert Configuration is not visible in side menu bar");
			Helper.driver.findElement(By.id("menu-administration")).click();			
			softAssert.assertEquals(Helper.driver.findElements(By.id("roleMGMTManageReportRole")).size(), 0, "User can view report managemenet");

			Helper.driver.get(Constants.url_access);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReporting Roles']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(4) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReport Groups']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReport Groups']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(3) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Agreement Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAgreement Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAgreement Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(3) .custom-checkbox")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAgreement Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAgreement Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAgreement Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAgreement Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(4) .custom-checkbox")).click();
			}


			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Barcode Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateBarcode Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateBarcode Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(3) .custom-checkbox")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewBarcode Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateBarcode Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateBarcode Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewBarcode Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Piper Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(3) .custom-checkbox")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Piper Software Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);                                                  
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Software Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Software Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(3) .custom-checkbox")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Software Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Software Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Software Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Software Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Piper Configuration Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(3) .custom-checkbox")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;	
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPIPER Configuration Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePIPER Configuration Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPIPER Configuration Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Data Template Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Template']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateData Template']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(3) .custom-checkbox")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewData Template']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Template']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateData Template']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewData Template']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create  radio button next to Data Upload");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Upload']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(14) td:nth-child(2) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Upload']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(14) td:nth-child(2) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Variables.steps.createNode("2. Unselect Create and Update radio button next to Poultry Management");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePoultry Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePoultry Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(3) .custom-checkbox")).click();
			}	

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPoultry Management']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePoultry Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(2) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePoultry Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(3) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPoultry Management']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
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
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewDashboard']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(16) td:nth-child(4) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReports']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(17) td:nth-child(4) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewContact us']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(18) td:nth-child(4) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewHelp']")).isSelected() == true) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(19) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

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
			Helper.driver.findElement(By.id("userRoleName_sort")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = Helper.driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Helper.driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewDashboard']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(16) td:nth-child(4) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReports']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(17) td:nth-child(4) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewContact us']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(18) td:nth-child(4) .custom-checkbox")).click();
			}

			if (Helper.driver.findElement(By.xpath("//input[normalize-space(@id)='isViewHelp']")).isSelected() == false) {	
				Helper.driver.findElement(By.cssSelector("tr:nth-child(19) td:nth-child(4) .custom-checkbox")).click();
			}

			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
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
	//	Helper.driver.quit();
	}
}