package Test.Ancera.Administration;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import Models.UserModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.RetryFailedCases;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;


public class UserManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_User_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("User Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}

	
	@Test(priority= 1, enabled = true)
	public void Navigate() throws InterruptedException, IOException {
		Test_Functions.NavigateToScreen(Constants.url_user, "User Management", Constants.UserManagementReportPath, Test_Elements.userTitle);
	}


	@Test (enabled= true, priority= 2) 
	public void OpenClosePopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-02: Verify user can open and close Create New User Popup", "This test case will verify that user is able to open and close create new user popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar; Click on Administration and select User Management");	
			Test_Variables.steps.createNode("1. Click on Create New button");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.driver.findElement(By.id("create-user")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.cssSelector(".pop-head")).getText(), "Create User"); 
			Test_Variables.test.pass("User popup window opened successfully");
			Test_Variables.results.createNode("Create New User popup opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);

			Helper.driver.findElement(By.cssSelector("#close-popup-modal img")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Assert.assertEquals(Helper.driver.findElements(By.id("btn-next")).size(), 0); 
			Test_Variables.test.pass("User popup window closed successfully");
			Test_Variables.results.createNode("Create New User popup closed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);

		}catch(AssertionError er){
			Test_Variables.test.fail("User popup window did not open or closed successfully");
			Test_Variables.results.createNode("User popup window did not open or closed successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User popup window did not open or closed successfully");
			Test_Variables.results.createNode("User popup window did not open or closed successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 3) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {
		//Helper.driver.get(Constants.url_user);
		//Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);

		Helper.driver.findElement(By.id("create-user")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);


		for (UserModel objModel : Test_Variables.lstUserMandatoryCheck) {

			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.testCaseTitle, objModel.testCaseDesc);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar; Click on Administration and select User Management");	
				Test_Variables.preconditions.createNode("4. Click on Create New button; Popup appears");
				Test_Variables.steps.createNode("1. "+objModel.step1);
				Helper.driver.findElement(By.id("btn-reset")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				Helper.driver.findElement(By.cssSelector("#firstNameId")).clear();
				Helper.driver.findElement(By.cssSelector("#firstNameId")).sendKeys(objModel.userFirstName);
				Helper.driver.findElement(By.cssSelector("#lastNameId")).clear();
				Helper.driver.findElement(By.cssSelector("#lastNameId")).sendKeys(objModel.userLastName);	
				Thread.sleep(1000);

				if (objModel.userPhoneCode)
				{
					Helper.driver.findElement(By.id("cellCodeId")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#cellCodeId input")).sendKeys("+92");
					Helper.driver.findElement(By.cssSelector("#cellCodeId input")).sendKeys(Keys.ENTER);

					Helper.driver.findElement(By.cssSelector("#cellNumberId input")).clear();
					Helper.driver.findElement(By.cssSelector("#cellNumberId input")).sendKeys(objModel.userPhoneNo);	
					Thread.sleep(1000);
				}

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-next")));
				Helper.driver.findElement(By.id("btn-next")).click(); 
				Thread.sleep(1000);

				if (objModel.chkMandatoryFieldsS1)
				{

					if ( objModel.userFirstName.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#firstNameId .hide")).size(), 0); 
					}


					if ( objModel.userLastName.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#lastNameId .hide")).size(), 0); 
					}			

					Test_Variables.test.pass(objModel.passScenario);
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);

					continue;
				}
			}	
			catch(AssertionError er){
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
			}	
			catch(Exception ex){
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
			}


			if (Helper.driver.findElement(By.id("emailId")).isDisplayed()) {
				//	Assert.assertTrue(true);
				Thread.sleep(1000);

				//		Helper.driver.findElement(By.id("btn-reset")).click();
				Helper.driver.findElement(By.id("emailId")).clear();
				Helper.driver.findElement(By.id("emailId")).sendKeys(objModel.userEmail);

				if (objModel.userOrganizationType)
				{
					Helper.driver.findElement(By.cssSelector("#orgTypeId .ng-arrow-wrapper")).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys("Ancera");
					Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
				}

				if (objModel.userOrganization)
				{
					Helper.driver.findElement(By.cssSelector("#organizationId .ng-arrow-wrapper")).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys("Ancera");
					Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
				}

				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Helper.driver.findElement(By.id("btn-next")).click(); 

				if (objModel.chkMandatoryFieldsS2)	{
					if ( objModel.userOrganizationType == false)
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#orgTypeId .anc-form-floating__error")).size(), 1); 
					}
					else {
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#orgTypeId .anc-form-floating__error")).size(), 0); 
					}

					if ( objModel.userOrganization == false)
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#organizationId .anc-form-floating__error")).size(), 1); 
					}
					else {
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#organizationId .anc-form-floating__error")).size(), 0); 
					}
				}
			}
			else
			{
				Assert.assertFalse(true);
			}

			if (Helper.driver.findElement(By.id("rolesId")).isDisplayed()) {
				Assert.assertTrue(true);
				Thread.sleep(1000);

				if (objModel.userAsssignedRole) {
					Helper.driver.findElement(By.cssSelector("rolesId input")).sendKeys("Admin");
					Helper.driver.findElement(By.cssSelector("rolesId input")).sendKeys(Keys.ENTER);
				}		

				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Helper.driver.findElement(By.id("btn-next")).click(); 

				if (objModel.chkMandatoryFieldsS3)	{
					if ( objModel.userAsssignedRole == false)
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#rolesId .anc-form-floating__error")).size(), 1); 
					}
				}
			}
			else
			{
				Assert.assertFalse(true);
			}
		}
	}


	@Test (description="Exceptional Flow: Reset fields", enabled= true, priority= 4, dependsOnMethods = {"MandatoryFieldCheck"}) 
	public void ResetButton() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-09: Verify user can reset fields", "This test case will verify that user can reset fields");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button");
			Test_Variables.steps.createNode("1. Enter data in all fields");
			Test_Variables.steps.createNode("2. Click on reset button");

			Helper.driver.findElement(By.id("btn-reset")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			WebElement usrFirstName = Helper.driver.findElement(By.cssSelector("#firstNameId"));
			String firstNameReset = usrFirstName.getAttribute("value");
			usrFirstName.sendKeys("Ancera Test");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-reset")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Assert.assertEquals(firstNameReset, "");
			Test_Variables.test.pass("Fields reset successfully");
			Test_Variables.results.createNode("Fields reset successfully");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Fields failed to reset");  
			Test_Variables.results.createNode("Fields failed to reset"); 
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex){
			Test_Variables.test.fail("Fields failed to reset");  
			Test_Variables.results.createNode("Fields failed to reset"); 
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
		Helper.driver.findElement(By.id("close-popup-modal")).click();
	}


	@Test (enabled= true, priority= 5) 
	public void CreateUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-10: Verify user can create a user", "This test case will verify create new ancera user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button");
			Test_Variables.steps.createNode("1. Enter valid data in all fields and click on Save button");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("create-user")));
			Thread.sleep(3000);
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("delete-user-"+i)).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector(".confirmation-overlay .anc-btn-solid-2")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}			

			Helper.driver.findElement(By.id("create-user")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			Helper.driver.findElement(By.cssSelector("#firstNameId")).sendKeys("Ancera Test");    
			Helper.driver.findElement(By.cssSelector("#lastNameId")).sendKeys("User");  
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#emailId")).sendKeys(Test_Variables.createUserEmail);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys("Client");
			Thread.sleep(2500);
			Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#organizationId input")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			Assert.assertEquals(Helper.driver.findElements(By.id("site-administrator")).size(), 1, "Site Administrator button is not displayed");
			Helper.driver.findElement(By.cssSelector("#site-administrator .row")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-next")).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#rolesId .ng-arrow-wrapper")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//*[@id=\"rolesId\"]//div[2]/input")).sendKeys("Admin");
			Helper.driver.findElement(By.xpath("//*[@id=\"rolesId\"]//div[2]/input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.id("btn-save")).click();    
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(0)); 
			Test_Variables.test.pass("New User created successfully");
			Test_Variables.results.createNode("New User created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Failed to create a new user");
			Test_Variables.results.createNode("Failed to create a new user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Failed to create a new user");
			Test_Variables.results.createNode("Failed to create a new user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 6, retryAnalyzer = RetryFailedCases.class, dependsOnMethods = {"CreateUser"}) 
	public void VerifyEmail() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-UM-11: Verify user receives an email to reset password", "This test case will verify that user will receive an email with reset password link");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
		Test_Variables.steps.createNode("1. Go to email account against which the user is created");
		Test_Variables.steps.createNode("2. Check that mail to reset password is received or not");

		Helper.driver.get(Constants.url_GmailSignin);
		Thread.sleep(12000);

		if (Helper.driver.findElements(By.xpath(Test_Elements.gmailEmail)).size() != 0) {
			Helper.driver.findElement(By.xpath(Test_Elements.gmailEmail)).sendKeys(Test_Variables.createUserEmail);    
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.gmailEmail)).sendKeys(Keys.ENTER);	
		}

		else if(Helper.driver.findElements(By.id("Email")).size() != 0) {

			Helper.driver.findElement(By.id("Email")).sendKeys(Test_Variables.createUserEmail);    
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("Email")).sendKeys(Keys.ENTER);			
		}

		Thread.sleep(6500);
		Helper.driver.findElement(By.xpath(Test_Elements.gmailPassword)).sendKeys(Test_Variables.createUserPassword);
		Helper.driver.findElement(By.xpath(Test_Elements.gmailPassword)).sendKeys(Keys.ENTER);

		if (Helper.driver.findElements(By.xpath(Test_Elements.gmailSecurityCheck)).size() != 0) { 

			Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityCheck)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityEmail)).sendKeys(Test_Variables.createUserSecurityEmail);
			Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityEmail)).sendKeys(Keys.ENTER);

		}

//		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
//		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
//
//		List<WebElement> a = Helper.driver.findElements(By.xpath("//*[@class='yW']/span"));
//		for(int i=0;i<a.size();i++){
//			if(a.get(i).getText().equals("ancera.org") || a.get(i).getText().equals("support")){  
//				a.get(i).click();
//			}
//		}
//
//		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create Password")));
//		Thread.sleep(1000);
//		if (Helper.driver.findElement(By.linkText("Create Password")).getText().equals("Create Password")) {
//
//			Test_Variables.test.pass("Reset password email received successfully");
//			Test_Variables.results.createNode("Email to reset password received successfully");
//			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));	
//		}
//
//		else {
//			Test_Variables.test.fail("Did not receive an email");
//			Test_Variables.results.createNode("Email to reset password did not received");
//		}
//
//		Helper.driver.findElement(By.linkText("Create Password")).click();
//		Thread.sleep(1000);
//		Helper.driver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();
//
//		String currentTabHandle = Helper.driver.getWindowHandle();
//		String newTabHandle = Helper.driver.getWindowHandles()
//				.stream()
//				.filter(handle -> !handle.equals(currentTabHandle ))
//				.findFirst()
//				.get();
//		Helper.driver.switchTo().window(newTabHandle);

	}
	
	
	@Test (enabled= true, priority= 7, retryAnalyzer = RetryFailedCases.class) 
	public void ClickEmail() throws InterruptedException, IOException {

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));

		List<WebElement> a = Helper.driver.findElements(By.xpath("//*[@class='yW']/span"));
		for(int i=0;i<a.size();i++){
			if(a.get(i).getText().equals("ancera.org") || a.get(i).getText().equals("support")){  
				a.get(i).click();
			}
		}

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create Password")));
		Thread.sleep(1000);
		if (Helper.driver.findElement(By.linkText("Create Password")).getText().equals("Create Password")) {

			Test_Variables.test.pass("Reset password email received successfully");
			Test_Variables.results.createNode("Email to reset password received successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));	
		}

		else {
			Test_Variables.test.fail("Did not receive an email");
			Test_Variables.results.createNode("Email to reset password did not received");
		}

		Helper.driver.findElement(By.linkText("Create Password")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();

		String currentTabHandle = Helper.driver.getWindowHandle();
		String newTabHandle = Helper.driver.getWindowHandles()
				.stream()
				.filter(handle -> !handle.equals(currentTabHandle ))
				.findFirst()
				.get();
		Helper.driver.switchTo().window(newTabHandle);
	}


	@Test (enabled= true, priority= 8) 
	public void ResetPassword() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-12: Verify user can set password and log in", "This test case will verify that user can set password and log into his account");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.preconditions.createNode("5. Go to email account against which the user is created and check that mail to reset password is received or not");
			Test_Variables.steps.createNode("1. Click on the reset password link;  user redirects to application to set new password");
			Test_Variables.steps.createNode("2. Enter email and newly set password; user should be logged in");

			Thread.sleep(2000);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordId")));
			Helper.driver.findElement(By.id("passwordId")).sendKeys(Test_Variables.createUserPassword);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rePassordId")));

			Helper.driver.findElement(By.id("rePassordId")).sendKeys(Test_Variables.createUserPassword);
			Helper.driver.findElement(By.cssSelector("button.apl-btn")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);

			Helper.driver.findElement(By.id("logout")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.createUserEmail);
			Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.createUserPassword);
			Helper.driver.findElement(By.id("btn-sign-in")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Assert.assertTrue(Helper.driver.findElement(By.id("Ancera Intelligence Engine")).isDisplayed(), "New user wa not able to login into application");
			Test_Variables.test.pass("Password was reset; user logged in successfully");
			Test_Variables.results.createNode("Password was reset; user logged in successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User failed to login");
			Test_Variables.results.createNode("User failed to login");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User failed to login");
			Test_Variables.results.createNode("User failed to login");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 9) 
	public void VerifyReportRole() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-13: Verify user can view assigned reports", "This test case will verify that user can view assigned reports");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Assign report role to new user");
			Test_Variables.steps.createNode("2. Go to reports and verify the reports are visible to the user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Helper.driver.findElement(By.id("btn-next")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("btn-next")).click();
					Helper.driver.findElement(By.cssSelector("#reportRoleId .ng-arrow-wrapper")).click();
					Helper.driver.findElement(By.xpath("//*[@id=\"reportRoleId\"]//input")).sendKeys(Keys.ENTER);
					Helper.driver.findElement(By.id("btn-save")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);
					Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");
					Helper.driver.get(Constants.url_reports);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);

					Assert.assertNotEquals(Helper.driver.findElements(By.cssSelector(".report-img")).size(), 0);
					Test_Variables.test.pass("Assigned reports were visible to the user successfully");
					Test_Variables.results.createNode("Assigned reports were visible to the user successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
					break;
				}
			}		
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Assigned reports were not visible to the user");
			Test_Variables.results.createNode("Assigned reports were not visible to the user");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Assigned reports were not visible to the user");
			Test_Variables.results.createNode("Assigned reports were not visible to the user");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 10) 
	public void VerifyTestingSitesAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning All Testing Sites to the user", "This test case will verify Sites column displays Active after assigning sites to the user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Assign All Collection Sites to the new user");
			Test_Variables.steps.createNode("2. Verify user was able to assign All Testing Sites to the user");
			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(9) label")).getText(), "Inactive");
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(6000);
					
					Helper.driver.findElement(By.id("btn-next")).click();
					Helper.driver.findElement(By.id("btn-next")).click();
					Thread.sleep(750);
					Helper.driver.findElement(By.cssSelector(".btn-sites")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("select-testing-sites")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);
					Helper.driver.findElement(By.id("btn-ok-sites")).click();
					Thread.sleep(1000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.driver.findElement(By.id("btn-save")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);
					softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");
					Thread.sleep(6000);
					break;
				}
			}	

			

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(Test_Variables.createUserEmail)) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userSiteAccessCol+" label")).getText(), "Active");
					Test_Variables.test.pass("Sites column displayed Active after assigning Sites to the user");
					Test_Variables.results.createNode("Sites column displayed Active after assigning Sites to the user");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
					break;
				}
			}
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sites column failed to display Active after assigning Sites to the user");
			Test_Variables.results.createNode("Sites column failed to display Active after assigning Sites to the user");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Sites column failed to display Active after assigning Sites to the user");
			Test_Variables.results.createNode("Sites column failed to display Active after assigning Sites to the user");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}

	///////////////////////////////////////////////
	@Test (enabled= false, priority= 11) 
	public void VerifyCollectionSitesAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-15: Verify Sites column displays Active after assigning All Collection Sites to the user", "This test case will verify Sites column displays Active after assigning sites to the user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Assign sites to new user");
			Test_Variables.steps.createNode("2. Verify user was able to assign All Collection Sites to the user");
			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Helper.driver.findElement(By.id("btn-next")).click();
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(750);
			Helper.driver.findElement(By.cssSelector(".btn-sites")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.findElement(By.id("select-collection-sites")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("btn-ok-sites")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");
			Thread.sleep(3000);

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(Test_Variables.createUserEmail)) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userSiteAccessCol+" label")).getText(), "Active");
					Test_Variables.test.pass("Sites column displayed Active after assigning Sites to the user");
					Test_Variables.results.createNode("Sites column displayed Active after assigning Sites to the user");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
					break;
				}
			}
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sites column failed to display Active after assigning Sites to the user");
			Test_Variables.results.createNode("Sites column failed to display Active after assigning Sites to the user");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Sites column failed to display Active after assigning Sites to the user");
			Test_Variables.results.createNode("Sites column failed to display Active after assigning Sites to the user");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}



	@Test (enabled= true, priority= 12) 
	public void VerifyAgreementAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-16: Verify user can view assigned agreement", "This test case will verify that user can view assigned agreement");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Assign agreement to the user");
			Test_Variables.steps.createNode("2. Verify agreement displays in popup on clicking eye icon next to that user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(3000);
					Helper.driver.findElement(By.id("btn-next")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("btn-next")).click();
					Helper.driver.findElement(By.cssSelector("#euladdl .ng-arrow-wrapper")).click();
					Helper.driver.findElement(By.xpath("//*[@id=\"euladdl\"]//div[2]/input")).sendKeys(Keys.ENTER);
					Helper.driver.findElement(By.id("btn-save")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1500);
					Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");
					break;
				}
			}	

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Thread.sleep(2000);
					Helper.driver.findElement(By.id("view-agreements-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Assert.assertEquals(Helper.driver.findElements(By.xpath("//*[@id=\"manage-user\"]//app-user-license-log//tbody/tr[1]")).size(), 1);
					Helper.driver.findElement(By.cssSelector("#close-popup-modal img")).click();
					Test_Variables.test.pass("Assigned agreement displayed in popup successfully");
					Test_Variables.results.createNode("Assigned agreement displayed in popup successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
					break;
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Assigned agreement did not displayed in popup");
			Test_Variables.results.createNode("Assigned agreement did not displayed in popup");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Assigned agreement did not displayed in popup");
			Test_Variables.results.createNode("Assigned agreement did not displayed in popup");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Update User", enabled = true, priority= 13) 
	public void UpdateUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-17: Verify user can update a user and convert user to piper user", "This test case will verify that user can update a user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Click on update button next to created user; Update user popup appears");
			Test_Variables.steps.createNode("2. Make any change and click on Save button");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(4000);
					Helper.driver.findElement(By.cssSelector("#lastNameId")).clear();
					Helper.driver.findElement(By.cssSelector("#lastNameId")).sendKeys("User Updated");
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("btn-next")).click();
					Thread.sleep(1000);
					Assert.assertEquals(Helper.driver.findElements(By.id("site-administrator")).size(), 1, "Site Administrator button is not displayed");	
					Helper.driver.findElement(By.cssSelector("#site-administrator .toggle")).click();
					Thread.sleep(1000);
					
					Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys("Client");
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#organizationId input")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					break;
				}
			}	

			String organizationType = Helper.driver.findElement(By.cssSelector("#organizationId .ng-value-label")).getText();
			
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);

			//	Helper.driver.findElement(By.cssSelector("#piper-user .wrapper-v2")).click();
			//	Thread.sleep(500);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(1)); 
		
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Assert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userLastNameCol+" label")).getText(), "User Updated", "Last name in popup not same as in table"); 
					Assert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userOrgCol+" label")).getText(), organizationType, "Org type in popup not same as in table"); 
					break;
				}
			}
	
			Test_Variables.test.pass("User updated successfully");
			Test_Variables.results.createNode("User updated successfully; an alert message appears 'User details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
			
		}catch(AssertionError er) {
			Test_Variables.test.fail("Failed to update the user");
			Test_Variables.results.createNode("Failed to update the user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Failed to update the user");
			Test_Variables.results.createNode("Failed to update the user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 14) 
	public void EditAssignRolePopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-18: Verify user can be edited from assign roles and right popup", "This test case will verify that user can be edited from roles and right popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Click on assign roles and report button next to user");
			Test_Variables.steps.createNode("2. Verify user is able to edit user from there");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					int j = i-2;
					if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userRoleCol+" img")).size() != 0) {
					WebElement scroll = Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userRoleCol+" img"));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000); 
					}
					Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userRoleCol+" img")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(4000);
					Helper.driver.findElement(By.cssSelector("#reportRoleId .ng-arrow-wrapper")).click();
					Helper.driver.findElement(By.xpath("//*[@id=\"reportRoleId\"]//input")).sendKeys(Keys.ARROW_DOWN);
					Helper.driver.findElement(By.xpath("//*[@id=\"reportRoleId\"]//input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);

					break;
				}
			}	
			
			String Reporting = Helper.driver.findElement(By.cssSelector("#reportRoleId .ng-value-label")).getText();
			Helper.driver.findElement(By.id("btn-save")).click();
			Thread.sleep(750);
			Helper.driver.findElement(Test_Elements.popupYesButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Roles and Rights has been updated successfully");
			Thread.sleep(1000);
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Assert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userReportingCol+" label")).getText(), Reporting);
					break;
				}
			}	

			Test_Variables.test.pass("User was able to edit from assign roles and reports successfully");
			Test_Variables.results.createNode("User was able to edit from assign roles and reports successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User was not able to edit from assign roles and reports");
			Test_Variables.results.createNode("User was not able to edit from assign roles and reports");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User was not able to edit from assign roles and reports");
			Test_Variables.results.createNode("User was not able to edit from assign roles and reports");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}

	
	@Test (description="Test Case: Site Admin New User Create", enabled= true, priority= 16) 
	public void SiteAdminNewUserCreate() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-19: Verify site admin is able to create new user", "This test case will verify that is able to create new user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.steps.createNode("1. From Site Admin user create a new user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);

			Helper.driver.findElement(By.id("create-user")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#firstNameId")).sendKeys("Ancera Test");    
			Helper.driver.findElement(By.cssSelector("#lastNameId")).sendKeys("User");  
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#emailId")).sendKeys("siteadminuser@anc.com");
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#orgTypeId input")).click();
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option")).size(), 1);
			Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#organizationId input")).click();
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option")).size(), 1);
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			Assert.assertEquals(Helper.driver.findElements(By.id("site-administrator")).size(), 1, "Site Administrator button is not displayed");		
			Helper.driver.findElement(By.id("btn-next")).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#rolesId .ng-arrow-wrapper")).click();
			Thread.sleep(1000);
		//	Helper.driver.findElement(By.xpath("//*[@id=\"rolesId\"]//div[2]/input")).sendKeys("Admin");
			Helper.driver.findElement(By.xpath("//*[@id=\"rolesId\"]//div[2]/input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.id("btn-save")).click();    
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(0)); 
			Test_Variables.test.pass("New User created successfully");
			Test_Variables.results.createNode("New User created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals("siteadminuser@anc.com")) {
					Helper.driver.findElement(By.id("delete-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Thread.sleep(1500);
			Helper.driver.findElement(By.xpath("//app-confirmation-v3//button[1]")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(5000);
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(2)); 
			softAssert.assertAll();		
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
		}
			catch(AssertionError er) {
			Test_Variables.test.fail("User not created");
			Test_Variables.results.createNode("User not created");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("User not created");
			Test_Variables.results.createNode("User not created");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	@Test (description="Test Case: Site Admin Reports Assigned", enabled= true, priority= 17) 
	public void SiteAdminReportAssigned() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-20: Verify user can see reports that are assigned to him", "This test case will verify that user can see reports that are assigned to him");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Assign report role and verify user can view reports");

			Helper.driver.get(Constants.url_reports);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector(".report-img")).size(), 0, "Reports assigned not displayed in Reports Module");
			softAssert.assertAll();
			Test_Variables.test.pass("User was able to view reports successfully");
			Test_Variables.results.createNode("User was able to view reports successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User was not able to view reports");
			Test_Variables.results.createNode("User was not able to view reports");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("User was not able to view reports");
			Test_Variables.results.createNode("User was not able to view reports");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	@Test (description="Test Case: Site Admin Sites Assigned", enabled= true, priority= 18) 
	public void SiteAdminSitesAssigned() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-21: Verify user can only see sites that are assigned to him", "This test case will verify that user can only see sites that are assigned to him");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");

			
			SoftAssert softAssert = new SoftAssert();
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			Helper.driver.findElement(By.id("edit-orgn-sites-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			
			int siteSizeOrg = Helper.driver.findElements(By.cssSelector(".tree-list-toggle")).size();
			
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);
			Helper.driver.findElement(By.id("edit-user-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".btn-sites")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			int siteSizeUser = Helper.driver.findElements(By.cssSelector(".form-check-label")).size();
			
			int sites = (siteSizeOrg/2)+1;
			softAssert.assertNotEquals(sites, siteSizeUser, "Site Admin is not able to see only the sites that are assigned to him");
			
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			
			Helper.driver.findElement(By.id("orgnTypeName_show-filter")).click();		
			Thread.sleep(1000);
			softAssert.assertNotEquals(Helper.driver.findElement(By.cssSelector("#sort-orgnTypeName .filter-popup__footer--count")).getText(), "Showing 1 - 1 Results", "Site Admin is not able to see only his organization in org type filter");
			Helper.driver.findElement(By.id("orgnName_show-filter")).click();
			Thread.sleep(1000);
			softAssert.assertNotEquals(Helper.driver.findElement(By.cssSelector("#sort-orgnName .filter-popup__footer--count")).getText(), "Showing 1 - 1 Results", "Site Admin is not able to see only his organization in org filter");

			
			softAssert.assertAll();
			Test_Variables.test.pass("Sites showed only those that are assigned to user successfully");
			Test_Variables.results.createNode("Sites showed only those that are assigned to user successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sites not showed only those that are assigned to user");
			Test_Variables.results.createNode("Sites not showed only those that are assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Sites not showed only those that are assigned to user");
			Test_Variables.results.createNode("Sites not showed only those that are assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	
	@Test (enabled= true, priority= 19) 
	public void OrgTypeColumn() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-22: Verify user can only see organization that is assigned to him", "This test case will verify that user can only see organization that is assigned to him");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");

			
			SoftAssert softAssert = new SoftAssert();
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			
			softAssert.assertEquals(Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(), "1", "Only assigned org is not displayed");
			
			String orgName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(1) label")).getText();
			
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);
			
			String totalRows = Helper.driver.findElement(By.id("results-found-count")).getText();
			
			for (int i=1; i<Integer.parseInt(totalRows);i++) {
			List <WebElement> a = Helper.driver.findElements(By.cssSelector("tr:nth-child("+i+") td:nth-child(5) label"));
			softAssert.assertEquals(a.get(i).getText(), orgName);
			}
						
			softAssert.assertAll();
			Test_Variables.test.pass("Created user deleted successfully");
			Test_Variables.results.createNode("User deleted successfully; an alert message appears 'User details deleted.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Created user failed to delete");
			Test_Variables.results.createNode("Created user failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Created user failed to delete");
			Test_Variables.results.createNode("Created user failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	
	@Test (enabled= true, priority= 21) 
	public void ClientMappingSiteAdmin() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-23: Verify user can only see organization that is assigned to him in client mapping", "This test case will verify that user can only see organization that is assigned to him in client mapping");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with Site Admin; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on MetaData and select Data Template Managemnt");
			Test_Variables.steps.createNode("1. Open client mapping popup and verify only 1 org dislays assigned to Site Admin");

			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			Helper.driver.findElement(By.id("create-client-mapping")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("ClientId")).click();
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option")).size(), 1);					
			Test_Variables.test.pass("Client mapping only showed site admin org");
			Test_Variables.results.createNode("Client mapping only showed site admin org");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Client mapping not nly showed site admin org");
			Test_Variables.results.createNode("Client mapping not nly showed site admin org");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Client mapping not nly showed site admin org");
			Test_Variables.results.createNode("Client mapping not nly showed site admin org");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 22) 
	public void DataUploadSiteAdmin() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-24: Verify user can only see organization that is assigned to him in data upload screen", "This test case will verify that user can only see organization that is assigned to him in client mapping");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with Site Admin; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on MetaData and select Data Template Managemnt");
			Test_Variables.steps.createNode("1. Open client mapping popup and verify only 1 org dislays assigned to Site Admin");

			Helper.driver.get(Constants.url_dataUpload);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("ClientId")).click();
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option")).size(), 1);					
			Test_Variables.test.pass("Client mapping only showed site admin org");
			Test_Variables.results.createNode("Client mapping only showed site admin org");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Client mapping not nly showed site admin org");
			Test_Variables.results.createNode("Client mapping not nly showed site admin org");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Client mapping not nly showed site admin org");
			Test_Variables.results.createNode("Client mapping not nly showed site admin org");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 23) 
	public void SiteAdminEditSites() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-25: Verify user can edit sites of his organization", "This test case will verify that user can only see organization that is assigned to him in client mapping");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with Site Admin; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on MetaData and select Data Template Managemnt");
			Test_Variables.steps.createNode("1. Open client mapping popup and verify only 1 org dislays assigned to Site Admin");

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.orgCreateButton));
			Thread.sleep(3000);

			SoftAssert softAssert = new SoftAssert();

			Helper.driver.findElement(By.id("edit-orgn-sites-1")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);	

			Helper.driver.findElement(Test_Elements.orgAddSite1).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();   
			Thread.sleep(500);	
			Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).click();  
		
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Region");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(3000);
			Test_Variables.steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");
		
			Set<String> deleteIcons = new HashSet<String>();
			Helper.driver.findElements(By.cssSelector("li .text-ellipsis"))
			.stream()
			.forEach(product -> deleteIcons.add(product.getText()));
			System.out.println("Total delete icon : "+deleteIcons.size());
			
			List<WebElement> a = Helper.driver.findElements(By.cssSelector(".delete")) ;
			
			int b = deleteIcons.size() - 2;
			System.out.println("1");
			 a.get(b).click();
				System.out.println("2");
			 Thread.sleep(2000);
			 
			Helper.driver.findElement(Test_Elements.popupYesButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(2000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Site details deleted successfully.");
			softAssert.assertAll();
			Test_Variables.test.pass("Client mapping only showed site admin org");
			Test_Variables.results.createNode("Client mapping only showed site admin org");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Client mapping not nly showed site admin org");
			Test_Variables.results.createNode("Client mapping not nly showed site admin org");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Client mapping not nly showed site admin org");
			Test_Variables.results.createNode("Client mapping not nly showed site admin org");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	@Test (description="Test Case: Delete User", enabled= true, priority= 24) 
	public void DeleteUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-26: Verify user can be deleted", "This test case will verify that user can delete a user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Click on delete butotn next to created user; confirmation box appears");
			Test_Variables.steps.createNode("2. Click on yes button");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
			Helper.driver.navigate().refresh();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			Helper.driver.findElement(By.id("logout")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.login_email);
			Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.login_password);
			Helper.driver.findElement(By.id("btn-sign-in")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ancera Intelligence Engine")));
			
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);

			String preRecords = Helper.driver.findElement(By.id("results-found-count")).getText();

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("delete-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Thread.sleep(1500);
			Helper.driver.findElement(By.xpath("//app-confirmation-v3//button[1]")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(5000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(2)); 
			String postRecords = Helper.driver.findElement(By.id("results-found-count")).getText();
			softAssert.assertNotEquals(preRecords, postRecords);
			softAssert.assertAll();
			Test_Variables.test.pass("Created user deleted successfully");
			Test_Variables.results.createNode("User deleted successfully; an alert message appears 'User details deleted.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Created user failed to delete");
			Test_Variables.results.createNode("Created user failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Created user failed to delete");
			Test_Variables.results.createNode("Created user failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	
	@Test (priority = 26) 
	public void LockFilter() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
		Thread.sleep(3000);
		Test_Functions.Lock(Test_Elements.userManagementTable, "User Management", Constants.UserManagementReportPath);
	}
	
	@Test (priority = 27) 
	public void WildcardUser() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
		Thread.sleep(3000);
		Test_Functions.Wildcard(Test_Elements.userManagementTable, "User Management", Constants.UserManagementReportPath);
	}

	
	@Test(priority= 28)
	public void sorting() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
		Thread.sleep(3000);
		Test_Functions.Sorting(Test_Elements.userManagementTable, "User Management", Constants.UserManagementReportPath);
	}
	
	@Test(priority= 31)
	public void ExportCSV() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
		Thread.sleep(5000);

		Test_Functions.CSVExport("User Management", Constants.UserManagementReportPath, Test_Elements.userCSVFileName, Test_Elements.userManagementTable);
	}


	@Test (enabled= true, priority =30) 
	public void AccessRoleCount() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-65: Verify count of assign role in popup is same as that in table", "This test case will verify that count of assign role in popup is same as that in table");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on User Management; User Management open");
			Test_Variables.steps.createNode("5. Click on assign roles and rights popup next to user and check the assign roles");
			Test_Variables.steps.createNode("6. Verify the assign roles in table next to that user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);
			Helper.driver.findElement(By.cssSelector("tr:nth-child(3) #col-"+Test_Elements.userRoleCol+" img")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Helper.driver.findElement(By.cssSelector("#rolesId .ng-arrow-wrapper")).click();

			int roles = Helper.driver.findElements(By.cssSelector("#rolesId .ng-option-selected")).size();

			Helper.driver.findElement(By.cssSelector("#close-popup-modal img")).click();
			Thread.sleep(1000);

			String s = Helper.driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(7) label")).getText();
			int commas = 0;
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)==',') 
				{
					commas++;
				}
			}

			Assert.assertEquals(roles-1, commas);
			Test_Variables.test.pass("Assigned roles verified successfully");
			Test_Variables.results.createNode("Assigned roles verified successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Assigned roles were not same in popup and table");
			Test_Variables.results.createNode("Assigned roles were not same in popup and table");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("Assigned roles were not same in popup and table");
			Test_Variables.results.createNode("Assigned roles were not same in popup and table");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
		Thread.sleep(1000);
	}



	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//Helper.driver.close();
	}
}