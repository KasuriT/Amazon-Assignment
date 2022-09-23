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

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

public class AccessManagement{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Access_Management"+date+".html");
		spark.config().setReportName("Access Management Test Report"); 
		config();
		ConfigureLogin.login();
	}


	@Test (priority = 1) 
	public void NavigateAm() throws InterruptedException, IOException {
		NavigateToScreen(url_access, "Access Management", AccessManagementReportPath, accessTitle);
	}


	@Test (enabled= true, priority= 2) 
	public void OpenPopup() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-AM-02: Verify user can open Create New Access Popup", "This test case will verify that user can open create new access popup");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			steps.createNode("1. Click on Create New button");

			driver.findElement(accessCreateButton).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(By.cssSelector(".popup-header")).getText(), "Create Role", "Create popup failed to open"); 
			test.pass("Popup opened successfully");
			results.createNode("Popup opened successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Popup failed to open");
			results.createNode("Popup failed to open");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Popup failed to open");
			results.createNode("Popup failed to open");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 3) 
	public void MandatoryCheck() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-AM-03: Verify Mandatory fields check", "This test case will verify madatory field check in create access popup");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Leave name field empty");
			steps.createNode("2. Leave description field empty");
			steps.createNode("3. Click on save button");

			driver.findElement(accessName).click();
			driver.findElement(accessDesc).click();
			driver.findElement(accessName).click();
			Assert.assertEquals(driver.findElements(accessNameDescValidation).size(), 4); 
			test.pass("Mandatory Field check verified successfully");
			results.createNode("Does not save Role; displays validation messages underneath name and description fields");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Mandatory Field check verification failed");
			results.createNode("Saved Role; displayed validation messages underneath name and description fields");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Mandatory Field check verification failed");
			results.createNode("Saved Role; displayed validation messages underneath name and description fields");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}	
	}


	@Test (enabled= true, priority= 4) 
	public void ResetAccess() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-AM-04: Verify reset fields check", "This test case will verify field reset check in create new access popup");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Enter data in name field");
			steps.createNode("2. Enter data in description field");
			steps.createNode("3. Click on reset button");

			type(accessName, lstAccessCreate.get(0));
			type(accessDesc, lstAccessCreate.get(1));

			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			driver.findElement(popupResetButton).click();
			Thread.sleep(1000);

			Assert.assertEquals(driver.findElement(accessName).getAttribute("value"), ""); 
			Assert.assertEquals(driver.findElement(accessDesc).getAttribute("value"), ""); 

			test.pass("Access Reset successfully");
			results.createNode("Fields reset successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Fields failed to reset");
			results.createNode("Fields failed to reset");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Fields failed to reset");
			results.createNode("Fields failed to reset");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}	
	}


	@Test (description="Test Case: Create Access",enabled= true, priority= 5) 
	public void CreateAccess() throws InterruptedException, IOException {	
		try{
			test = extent.createTest("AN-AM-05: Verify user can create Role", "This test case will verify that user can create a new role");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Enter valid data in name field");
			steps.createNode("2. Enter valid data in description field");
			steps.createNode("3. Click on save button");

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(2000);

			SoftAssert softAssert = new SoftAssert();
			int rows = driver.findElements(By.cssSelector("tr")).size()-1;
			String resultsFound = driver.findElement(By.id("results-found-count")).getText();
			softAssert.assertEquals(rows, Integer.parseInt(resultsFound), "Results count not showing correct number of access in table");

			driver.findElement(accessCreateButton).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000);

			driver.findElement(accessName).sendKeys(lstAccessCreate.get(0));
			driver.findElement(accessDesc).sendKeys(lstAccessCreate.get(1));
			Thread.sleep(1000);
			driver.findElement(popupSaveButton).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstAccessAlertMessages.get(0)); 
			softAssert.assertEquals(rows+1, Integer.parseInt(resultsFound)+1);
			test.pass("Role created successfully");
			results.createNode("New role created; displays alert message 'New role created.'");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			ClickElement.clickByCss(driver, ".close");
		}catch(AssertionError er){
			test.fail("New role failed to create");
			results.createNode("New role failed to create");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New role failed to create");
			results.createNode("New role failed to create");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}	
	}


	@Test (description="Test Case: Update Access ",enabled= true, priority= 6) 
	public void UpdateRole() throws InterruptedException, IOException
	{
		try{
			test = extent.createTest("AN-AM-06: Verify user can update Role", "This test case will verify that user can update a role");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			preconditions.createNode("4. Click on create new button and create a new role");
			steps.createNode("1. Click on update button next to created role");
			steps.createNode("2. Update name and description of role");
			steps.createNode("3. Click on save button");

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(2000);

			int rows = driver.findElements(By.cssSelector("tr")).size();

			for (int i=1; i<rows;i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(lstAccessCreate.get(0)) ) {
					//	if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals("Administrator5244") ) {
					i = i-1;
					driver.findElement(By.id("edit-role-"+i)).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
					Thread.sleep(1000);
					break;
				}
			}

			WebElement desc = driver.findElement(accessDesc);
			desc.clear();
			desc.sendKeys("Role created by automation script");
			Thread.sleep(1000);
			driver.findElement(popupSaveButton).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), lstAccessAlertMessages.get(1)); 
			test.pass("Role updated successfully");
			results.createNode("Role updated; user receives an alert message that 'Role details updated.'");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			results.createNode("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			results.createNode("Role fails to update; user does not receive an alert message that 'Role details updated.'");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}	
		driver.findElement(alertMessageClose).click();
	}


	@Test (description="Test Case: Verify Update Access",enabled= true, priority= 7) 
	public void VerifyUpdateRole() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-AM-07: Verify role remains updated on reopening the Role", "This test case will verify that changes made in role remains saved on reopening the role");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar and click on Adminstration and select Access Management; Access Management screen opens");
			preconditions.createNode("4. Click on create new button and create a new role");
			preconditions.createNode("5. Update created role");
			steps.createNode("1. Reopen updated role by clicking on update button");
			steps.createNode("2. Verify the updation made in fields are saved");

			int rows = driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<rows;i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(lstAccessCreate.get(0)) ) {
					i=i-1;
					driver.findElement(By.id("edit-role-"+i)).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
					Thread.sleep(3000);
					break;
				}
			}

			Assert.assertEquals(driver.findElement(By.id("descId")).getAttribute("value"), "Role created by automation script"); 
			test.pass("Role updation verified successfully");
			results.createNode("Changes remained saved");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Role updation verification failed");
			results.createNode("Role updation verification failed");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Role updation verification failed");
			results.createNode("Role updation verification failed");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: InActivate Role",enabled= true, priority= 8) 
	public void InActivateRole() throws InterruptedException, IOException
	{
		try{
			test = extent.createTest("AN-AM-08: Verify user can InActivate Role", "This test case will verify that role can be made inactive");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Reopen created role");
			steps.createNode("2. Click on InActive toggle button");
			steps.createNode("3. Click on save button");

			driver.findElement(By.cssSelector("#role-status .toggle")).click();
			Thread.sleep(1000);
			driver.findElement(popupSaveButton).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			String actual = driver.findElement(By.id("message")).getText();
			String expected = lstAccessAlertMessages.get(1) ;

			Assert.assertEquals(actual, expected); 
			test.pass("Role inactivated successfully");
			results.createNode("User receives an alert message that 'Role details updated'");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			Thread.sleep(1000);
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("User does not receives an alert message that 'Role details updated'");
			results.createNode("User does not receives an alert message that 'Role details updated'");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("User does not receives an alert message that 'Role details updated'");
			results.createNode("User does not receives an alert message that 'Role details updated'");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}	
		driver.findElement(By.cssSelector("button.close span")).click();
	}


	@Test (description="Test Case: Role Assign screen",enabled= true, priority= 9) 
	public void RoleAssignScreen() throws InterruptedException, IOException{
		try{
			test = extent.createTest("AN-AM-11: Verify Role Assign screen contains names of user to which the role is assigned");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Go to user screen");	
			steps.createNode("2. Check role assigned to your user");	
			steps.createNode("3. Navigate back to access screen and verify that in system roles that user appears in list");	
		
			driver.get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);
			openEditUserPopup(login_email);
			click(popupCloseButton);
			Thread.sleep(1000);
			
			List<WebElement> systemRoles = driver.findElements(By.cssSelector("tr:nth-child(1) #col-6 label"));
			String systemRoleName = systemRoles.get(0).getText(); 
			//System.out.println(systemRoleName);

			driver.get(url_access);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			
			int rows = driver.findElements(By.cssSelector("tr")).size();
			System.err.println(rows);
			
			for (int j=1; j<rows;j++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+j+") td:nth-child(1) label")).getText().equals(systemRoleName) ) {
					int l=j-1;
					driver.findElement(By.id("view-role-user-"+l)).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					//	break;


				//	int rows1 = driver.findElements(By.cssSelector("td:nth-child(5) label")).size();
					for (int k=1;k<rows;k++) {
						if (driver.findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child(5) label")).getText().equals(login_email)) {
							Assert.assertTrue(true, "System Role assigned to user does not contains the name of user");
							test.pass("Role Assign screen contains names of user to which the role is assigned successfully");
							results.createNode("Role Assign screen contains names of user to which the role is assigned successfully");	
							test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
							saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
							break;
						}				
					}
				}

			}



		}catch(AssertionError er){
			test.fail("Role Assign screen did not contains names of user to which the role is assigned");
			results.createNode("Role Assign screen did not contains names of user to which the role is assigned");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Role Assign screen did not contains names of user to which the role is assigned");
			results.createNode("Role Assign screen did not contains names of user to which the role is assigned");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
		Thread.sleep(1000);	
		//	driver.findElement(popupCloseButton).click();
	}



	@Test (description="Test Case: User Management Access",enabled= false, priority = 8) 
	public void UserManagementAccess() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-AM-12/13/14: Verify create, update and view role of user", "This test case will verify that create, update and view role of user");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");
			SoftAssert softAssert = new SoftAssert();
			getUserAccess();
			List<WebElement> systemRoles = driver.findElements(By.cssSelector("#rolesId .ng-value-label"));
			String getSystemRole = systemRoles.get(0).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=500; i++) {
				System.out.println("0");
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create radio button next to User Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to User Management screen and verify that user is not able to create a new user");
			driver.get(url_user);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElements(By.id("create-user")).size(), 0, "User is able to create user");

			////////////////////

			steps.createNode("4. Go to Access Management screen and unselect Edit radio button");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			for(int i=1; i<=500; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			Thread.sleep(1000);
			if (driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();
			steps.createNode("5. Go to User Management screen and verify that user is not able to edit any user");
			driver.get(url_user);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			driver.findElement(By.id("edit-user-1")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			softAssert.assertEquals(driver.findElements(By.cssSelector(".ng-untouched#firstNameId")).size(), 1, "User is able to edit user");
			////////////////////////////////////

			steps.createNode("6. Go to Access Management screen and unselect View radio button next to User Management");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=500; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.cssSelector("#isViewUsers")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that User Management is not visible in side menu bar");
			driver.findElement(By.id("menu-administration")).click();			
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageUsersMenu")).size(), 0, "User Management is visible in sidebar");

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.cssSelector("#isCreateUsers")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.cssSelector("#isUpdateUsers")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.cssSelector("#isViewUsers")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
			test.pass("Access Rights passed for User Management Screen successfully");
			results.createNode("Access Rights passed for User Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for User Management Screen");
			results.createNode("Access Rights failed for User Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for User Management Screen");
			results.createNode("Access Rights failed for User Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Organization Management Access",enabled= false, priority = 9) 
	public void OrganizationManagementAccess() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-AM-15/16/17: Verify create, update and view role of organization", "This test case will verify that create, update and view role of organization");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			login();
			getUserAccess();
			List<WebElement> systemRoles = driver.findElements(By.cssSelector("#rolesId .ng-value-label"));
			String getSystemRole = systemRoles.get(0).getText(); 

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);


			for(int i=1; i<=500; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create radio button next to Organization Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.id("isCreateOrganizations")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Organization Management screen and verify that user is not able to create a new organization");
			driver.get(url_organization);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElements(By.id("create-organization")).size(), 0);

			////////////////////

			steps.createNode("4. Go to Access Management screen and unselect Edit radio button");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for(int i=1; i<=500; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.id("isCreateOrganizations")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.id("isUpdateOrganizations")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("5. Go to Organization Management screen and verify that user is not able to edit any organization");
			driver.get(url_organization);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			driver.findElement(By.id("edit-orgn-1")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(driver.findElements(By.cssSelector(".ng-untouched#nameId")).size(), 1);
			////////////////////////////////////

			steps.createNode("6. Go to Access Management screen and unselect View radio button next to Organization");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.id("isCreateOrganizations")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.id("isUpdateOrganizations")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.id("isViewOrganizations")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Organization Management is not visible in side menu bar");
			driver.findElement(By.id("menu-administration")).click();			
			Assert.assertEquals(driver.findElements(By.id("roleMGMTManageOrganMenu")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img")).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.id("isCreateOrganizations")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.id("isUpdateOrganizations")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.id("isViewOrganizations")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Organization Management Screen successfully");
			results.createNode("Access Rights passed for Organization Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Organization Management Screen");
			results.createNode("Access Rights failed for Organization Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Organization Management Screen");
			results.createNode("Access Rights failed for Organization Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Organization Sites Management Access",enabled= false, priority = 10) 
	public void OrganizationSitesManagementAccess() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-AM-18/19/20: Verify create, update and view role of organization sites", "This test case will verify that create, update and view role of organization sites");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");
			SoftAssert  softAssert = new SoftAssert();
			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Organization Sites");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Organization Management screen and verify that user is not able to create or update a site");
			driver.get(url_organization);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			driver.findElement(By.id("edit-orgn-sites-1")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElements(orgAddSite1).size(), 0, "Add Org site button is not hidden");
			driver.findElement(orgSite1Click).click();
			Thread.sleep(2000);
			softAssert.assertEquals(driver.findElements(By.id("btn-save")).size(), 0, "Edit Org Site button is not hidden");

			////////////////////////////////////

			steps.createNode("6. Go to Access Management screen and unselect View radio button next to Organization Sites");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewOrganization Sites']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();


			driver.get(url_organization);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			softAssert.assertEquals(driver.findElements(By.id("edit-orgn-sites-1")).size(), 0, "Edit org site button is viewable");

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateOrganization Sites']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateOrganization Sites']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewOrganization Sites']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
			test.pass("Access Rights passed for Organization Sites successfully");
			results.createNode("Access Rights passed for Organization Sites successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Organization Sites");
			results.createNode("Access Rights failed for Organization Sites");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Organization Sites");
			results.createNode("Access Rights failed for Organization Sites");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Alert Configuration Access",enabled= false, priority = 11) 
	public void AlertConfigurationAccess() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-AM-24/25/26: Verify create, update and view role of alert configuration", "This test case will verify that create, update and view role of alert configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");
			SoftAssert  softAssert = new SoftAssert();
			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Alert Configuration");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Alert Management screen and verify that user is not able to create or update any alert");
			driver.get(url_alert);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElements(By.id("create-role")).size(), 0, "User is able to create alert");
			softAssert.assertEquals(driver.findElements(By.id("duplicate-active-alert-1")).size(), 0, "User is able to edit alert");

			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Alert Configuration");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAlert Configurations']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();
			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Alert Configuration is not visible in side menu bar");
			driver.findElement(By.id("menu-administration")).click();			
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageAlertMenu")).size(), 0, "User is able to view Alert");

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAlert Configurations']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAlert Configurations']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAlert Configurations']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
			test.pass("Access Rights passed for Alert Configuration Screen successfully");
			results.createNode("Access Rights passed for Alert Configuration Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Alert Configuration Screen");
			results.createNode("Access Rights failed for Alert Configuration Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Alert Configuration Screen");
			results.createNode("Access Rights failed for Alert Configuration Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Report Role Access",enabled= false, priority = 12) 
	public void ReportRoleAccess() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-AM-27/28/29/30/31/32: Verify create, update and view role of report role and report group", "This test case will verify that create, update and view role of report role and report group");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");
			SoftAssert  softAssert = new SoftAssert();
			///////////////////////////////
			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Report Role and Report Group");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReport Groups']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReport Groups']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Report Management screen and verify that user is not able to create or update any report role or group");
			driver.get(url_reportsManagement);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElements(By.id("create-report-role")).size(), 0, "Create button is visible");

			driver.findElement(By.id("edit-report-role-1")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElements(By.cssSelector("#nameId.ng-touched")).size(), 0, "User is able to edit report role");

			driver.findElement(By.id("close-popup-modal")).click();
			//////////////////////////////////////////
			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Alert Configuration");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReporting Roles']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Alert Configuration is not visible in side menu bar");
			driver.findElement(By.id("menu-administration")).click();			
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageReportRole")).size(), 0, "User can view report managemenet");

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReporting Roles']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReporting Roles']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReporting Roles']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(6) td:nth-child(4) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateReport Groups']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateReport Groups']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(7) td:nth-child(3) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			softAssert.assertAll();
			test.pass("Access Rights passed for Alert Configuration Screen successfully");
			results.createNode("Access Rights passed for Alert Configuration Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Alert Configuration Screen");
			results.createNode("Access Rights failed for Alert Configuration Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Alert Configuration Screen");
			results.createNode("Access Rights failed for Alert Configuration Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Agreement Role Access",enabled= false, priority = 13) 
	public void AgreeemntRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-33/34/35: Verify create, update and view role of Agreemeent Management", "This test case will verify that create, update and view role of Agreeement Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Agreement Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAgreement Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAgreement Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(3) .custom-checkbox")).click();
			}	

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Agreement Management screen and verify that user is not able to create or update any agreement");
			driver.get(url_reportsManagement);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("file-license")).size(), 0);
			softAssert.assertEquals(driver.findElements(By.id("no_radio")).size(), 0);

			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Agreement Management");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAgreement Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Agreement Management is not visible in side menu bar");
			driver.findElement(By.id("menu-administration")).click();	
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageUserAgreement")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateAgreement Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateAgreement Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewAgreement Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(4) .custom-checkbox")).click();
			}


			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Agreement Management Screen successfully");
			results.createNode("Access Rights passed for Agreement Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Agreement Management Screen");
			results.createNode("Access Rights failed for Agreement Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Agreement Management Screen");
			results.createNode("Access Rights failed for Agreement Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Barcode Role Access",enabled= false, priority = 14) 
	public void BarcodeRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-36/37/38: Verify create, update and view role of Barcode Management", "This test case will verify that create, update and view role of Barcode Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Barcode Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateBarcode Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateBarcode Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(3) .custom-checkbox")).click();
			}	

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Barcode Management screen and verify that user is not able to create or update any barcode");
			driver.get(url_barcodeManagement);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("fa-print")).size(), 0);

			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Barcode Management");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewBarcode Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Barcode Management is not visible in side menu bar");
			driver.findElement(By.id("menu-administration")).click();	
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageBarcode")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateBarcode Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateBarcode Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewBarcode Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Barcode Management Screen successfully");
			results.createNode("Access Rights passed for Barcode Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Barcode Management Screen");
			results.createNode("Access Rights failed for AgBarcodereement Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Barcode Management Screen");
			results.createNode("Access Rights failed for Barcode Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Piper Management Role Access",enabled= false, priority = 15) 
	public void PiperManagementRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-39/40/41: Verify create, update and view role of Piper Management", "This test case will verify that create, update and view role of Piper Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Piper Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(9) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(3) .custom-checkbox")).click();
			}	

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Piper Management screen and verify that user is not able to create or update any piper");
			driver.get(url_piperManagement);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.cssSelector("button:nth-child(1)")).size(), 0);

			driver.findElement(By.id("piper-1")).click();
			Thread.sleep(1500);
			softAssert.assertEquals(driver.findElements(By.cssSelector(".ng-touched")).size(), 0);

			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Piper Management");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Agreement Management is not visible in side menu bar");
			driver.findElement(By.id("menu-piper")).click();	
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManagePiperScreen")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(10) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Piper Management Screen successfully");
			results.createNode("Access Rights passed for Piper Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Piper Management Screen");
			results.createNode("Access Rights failed for Piper Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Piper Management Screen");
			results.createNode("Access Rights failed for Piper Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Piper Software Management Role Access",enabled= false, priority = 16) 
	public void PiperSoftwareManagementRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-42/43/44: Verify create, update and view role of Piper Software Management", "This test case will verify that create, update and view role of Piper Software Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Piper Software Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);                                                  
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Software Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Software Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(3) .custom-checkbox")).click();
			}	

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Piper Software Management screen and verify that user is not able to create or update any piper");
			driver.get(url_piperSoftware);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("file-userapp")).size(), 0);
			softAssert.assertEquals(driver.findElements(By.id("upd-improc-userapp-ic-0")).size(), 0);

			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Piper Software Management");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Software Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Piper Software Management is not visible in side menu bar");
			driver.findElement(By.id("menu-piper")).click();	
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTPiperSoftware")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePiper Software Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePiper Software Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPiper Software Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(11) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Piper Software Management Screen successfully");
			results.createNode("Access Rights passed for Piper Software Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Piper Software Management Screen");
			results.createNode("Access Rights failed for Piper Software Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Piper Software Management Screen");
			results.createNode("Access Rights failed for Piper Software Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Piper Config Management Role Access",enabled= false, priority = 17) 
	public void PiperConfigManagementRoleAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-45/46/47: Verify create, update and view role of Piper Configuration Management", "This test case will verify that create, update and view role of Piper Configuration Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Piper Configuration Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(3) .custom-checkbox")).click();
			}	

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Piper Configuration Management screen and verify that user is not able to create or update any coonfiguration");
			driver.get(url_piperConfiguration);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("create-installation-run")).size(), 0, "User is still able to create config");
			softAssert.assertEquals(driver.findElements(By.id("create-mpn")).size(), 0, "User is still able to edit config");
			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Piper Configuration");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;	
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPIPER Configuration Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Piper Configuration Management is not visible in side menu bar");
			driver.findElement(By.id("menu-piper")).click();	
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManagePiperConfig")).size(), 0, "Piper Configuration is still displayed in side menu");

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePIPER Configuration Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePIPER Configuration Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPIPER Configuration Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(12) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Piper Configuration Management Screen successfully");
			results.createNode("Access Rights passed for Piper Configuration Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Piper Configuration Management Screen");
			results.createNode("Access Rights failed for Piper Configuration Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Piper Configuration Management Screen");
			results.createNode("Access Rights failed for Piper Configuration Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Data Template Management Role Access",enabled= false, priority = 18) 
	public void DataTemplateAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-48/49/50: Verify create, update and view role of Data Template Management", "This test case will verify that create, update and view role of Data Template Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Data Template Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Template']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateData Template']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(3) .custom-checkbox")).click();
			}	

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Data Template Management screen and verify that user is not able to create or update any data template");
			driver.get(url_dataTemplate);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));

			softAssert.assertEquals(driver.findElements(By.id("create-data-format")).size(), 0);
			softAssert.assertEquals(driver.findElements(By.id("delete-data-format")).size(), 0);

			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Data Management");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewData Template']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Data Template Management is not visible in side menu bar");
			driver.findElement(By.id("menu-metadata")).click();	
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageDataTemplateMenu")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Template']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateData Template']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewData Template']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(13) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Data Template Management Screen successfully");
			results.createNode("Access Rights passed for Data Template Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Data Template Management Screen");
			results.createNode("Access Rights failed for Data Template Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Data Template Management Screen");
			results.createNode("Access Rights failed for Data Template Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Data Upload Role Access",enabled= false, priority = 19) 
	public void DataUploadAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-51: Verify create of Data Upload Management", "This test case will verify that create, update and view role of Data Template Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create  radio button next to Data Upload");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Upload']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(14) td:nth-child(2) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Data Template Management is not visible in side menu bar");
			driver.findElement(By.id("menu-metadata")).click();	
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTDataUpload")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateData Upload']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(14) td:nth-child(2) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Data Upload Screen successfully");
			results.createNode("Access Rights passed for Data Upload Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Data Upload Screen");
			results.createNode("Access Rights failed for Data Upload Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Data Upload Screen");
			results.createNode("Access Rights failed for Data Upload Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Poultry Management Role Access",enabled= false, priority = 20) 
	public void PoultryManagementAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-48/49/50: Verify create, update and view role of Poultry Management", "This test case will verify that create, update and view role of Poultry Management");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			steps.createNode("2. Unselect Create and Update radio button next to Poultry Management");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePoultry Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePoultry Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(3) .custom-checkbox")).click();
			}	

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			steps.createNode("3. Go to Poultry Management screen and verify that user is not able to create or update poultry");
			driver.get(url_poultryManagement);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));

			softAssert.assertEquals(driver.findElements(By.id("create-treatment")).size(), 0);
			softAssert.assertEquals(driver.findElements(By.id("delete-treatment-1")).size(), 0);

			steps.createNode("4. Go to Access Management screen and unselect View radio button next to Poultry Management");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPoultry Management']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("5. Verify that Poultry Management is not visible in side menu bar");
			driver.findElement(By.id("menu-metadata")).click();	
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManagePoultry")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreatePoultry Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(2) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdatePoultry Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(3) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewPoultry Management']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(15) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Poultry Management Screen successfully");
			results.createNode("Access Rights passed for Poultry Management Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Poultry Management Screen");
			results.createNode("Access Rights failed for Poultry Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Poultry Management Screen");
			results.createNode("Access Rights failed for Poultry Management Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Report, Help Contact Us Role Access",enabled= false, priority = 21) 
	public void ReportHelpContactUsAccess() throws InterruptedException, IOException {
		try{
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("AN-AM-55/56/57/58: Verify view role of Reports, Dashboard, Contact Us and Help Page", "This test case will verify the view role of Reports, Dashboard, Contact Us and Help Page");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Access Management");
			steps.createNode("1. Click on edit rights icon next to assigned role");

			getUserAccess();
			String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}


			steps.createNode("2. Go to Access Management screen and unselect View radio button next to Reports, Dashboard, Contact Us and Help");
			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);

			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewDashboard']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(16) td:nth-child(4) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReports']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(17) td:nth-child(4) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewContact us']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(18) td:nth-child(4) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewHelp']")).isSelected() == true) {	
				driver.findElement(By.cssSelector("tr:nth-child(19) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

			login();

			Actions builder = new Actions(driver); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-administration")));
			WebElement pngHover = driver.findElement(By.id("menu-administration"));
			builder.moveToElement(pngHover).build().perform();

			steps.createNode("7. Verify that Reports, Dashboard, Contact Us and Help is not visible in side menu bar");

			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTAnalysisDashboard")).size(), 0);
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTReport")).size(), 0);
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageContactUs")).size(), 0);
			softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageHelp")).size(), 0);

			driver.get(url_access);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(750);
			driver.findElement(By.id("userRoleName_sort")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			for(int i=1; i<=200; i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
					int j = i-1;
					WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-"+j+" img"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					driver.findElement(By.id("edit-role-rights-"+j)).click();
					break;
				}
			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewDashboard']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(16) td:nth-child(4) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewReports']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(17) td:nth-child(4) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewContact us']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(18) td:nth-child(4) .custom-checkbox")).click();
			}

			if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewHelp']")).isSelected() == false) {	
				driver.findElement(By.cssSelector("tr:nth-child(19) td:nth-child(4) .custom-checkbox")).click();
			}

			driver.findElement(By.cssSelector(".fa-save")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
			test.pass("Access Rights passed for Reports, Dashboard, Contact Us and Help Screen successfully");
			results.createNode("Access Rights passed for Reports, Dashboard, Contact Us and Help Screen successfully");
			test.addScreenCaptureFromPath(getScreenshot("Access Management", AccessManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, AccessManagementReportPath, null);
			softAssert.assertAll();
		}
		catch(AssertionError er){
			test.fail("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			results.createNode("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			results.createNode("Access Rights failed for Reports, Dashboard, Contact Us and Help Screen");
			saveResultNew(ITestResult.FAILURE, AccessManagementReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
		//	driver.quit();
	}
}