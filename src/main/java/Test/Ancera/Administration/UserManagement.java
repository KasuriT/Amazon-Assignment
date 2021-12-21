package Test.Ancera.Administration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
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
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Models.ReportFilters;
import Models.UserModel;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.RetryFailedCases;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import Test.Ancera.Reports.SalmonellaLog;

public class UserManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_User_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("User Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to User Management Screen",enabled= true, priority = 1) 
	public void NavigateUM() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-01: Verify user can navigate to User Management screen", "This test case will verify that user can navigate to User Management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Adminstration and select User Management");

			Assert.assertEquals(Helper.driver.findElement(By.id("User Management")).getText(), "User Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to User Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to User Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to User Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}



	@Test (enabled= true, priority= 2) 
	public void OpenPopup() throws InterruptedException, IOException {
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
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
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
				Helper.driver.findElement(By.cssSelector("#firstNameId input:nth-child(1)")).clear();
				Helper.driver.findElement(By.cssSelector("#firstNameId input:nth-child(1)")).sendKeys(objModel.userFirstName);
				Helper.driver.findElement(By.cssSelector("#lastNameId input:nth-child(1)")).clear();
				Helper.driver.findElement(By.cssSelector("#lastNameId input:nth-child(1)")).sendKeys(objModel.userLastName);	
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
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#firstNameId .anc-form-floating__error")).size(), 1); 
					}


					if ( objModel.userLastName.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#lastNameId .anc-form-floating__error")).size(), 1); 
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
					Helper.driver.findElement(By.id("#orgTypeId .ng-arrow-wrapper")).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys("Ancera");
					Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
				}

				if (objModel.userOrganization)
				{
					Helper.driver.findElement(By.id("#organizationId .ng-arrow-wrapper")).click();
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


	@Test (description="Exceptional Flow: Reset fields", enabled= false, priority= 4, dependsOnMethods = {"MandatoryFieldCheck"}) 
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

			WebElement usrFirstName = Helper.driver.findElement(By.id("firstNameId"));
			String firstNameReset = usrFirstName.getAttribute("value");
			usrFirstName.sendKeys(Test_Variables.lstUserCreate.get(0));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-reset")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Assert.assertTrue(firstNameReset.isEmpty());
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
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#firstNameId input:nth-child(1)")).sendKeys(Test_Variables.lstUserCreate.get(0));    
			Helper.driver.findElement(By.cssSelector("#lastNameId input:nth-child(1)")).sendKeys(Test_Variables.lstUserCreate.get(1));  
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(750);
			Helper.driver.findElement(By.cssSelector("#emailId input:nth-child(1)")).sendKeys(Test_Variables.createUserEmail);  
			Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys("Ancera");
			Helper.driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys("Ancera");
			Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.id("btn-next")).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#rolesId .ng-arrow-wrapper")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//*[@id=\"rolesId\"]//div[2]/input")).sendKeys("Admin");
			Helper.driver.findElement(By.xpath("//*[@id=\"rolesId\"]//div[2]/input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.id("btn-save")).click();    
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(500);
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

		Thread.sleep(1500);
		Helper.driver.findElement(By.xpath(Test_Elements.gmailPassword)).sendKeys(Test_Variables.createUserPassword);
		Helper.driver.findElement(By.xpath(Test_Elements.gmailPassword)).sendKeys(Keys.ENTER);

		if (Helper.driver.findElements(By.xpath(Test_Elements.gmailSecurityCheck)).size() != 0) { 

			Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityCheck)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityEmail)).sendKeys(Test_Variables.createUserSecurityEmail);
			Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityEmail)).sendKeys(Keys.ENTER);

		}

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));

		List<WebElement> a = Helper.driver.findElements(By.xpath("//*[@class='yW']/span"));
		for(int i=0;i<a.size();i++){
			if(a.get(i).getText().equals("ancera.org") || a.get(i).getText().equals("support")){  
				a.get(i).click();
			}
		}

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create Password")));

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


	@Test (enabled= true, priority= 7) 
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
			Thread.sleep(4000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ancera Intelligence Engine")));

			Assert.assertTrue(Helper.driver.findElement(By.id("Ancera Intelligence Engine")).isDisplayed());
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


	@Test (enabled= true, priority= 8) 
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
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				//if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals("ancera@email.com")) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Helper.driver.findElement(By.id("btn-next")).click();
			Helper.driver.findElement(By.id("btn-next")).click();
			Helper.driver.findElement(By.cssSelector("#reportRoleId .ng-arrow-wrapper")).click();
			Helper.driver.findElement(By.xpath("//*[@id=\"reportRoleId\"]//input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");
			Helper.driver.get(Constants.url_reports);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			Assert.assertNotEquals(Helper.driver.findElements(By.cssSelector(".report-img")).size(), 0);
			Test_Variables.test.pass("Assigned reports were visible to the user successfully");
			Test_Variables.results.createNode("Assigned reports were visible to the user successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
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


	@Test (enabled= true, priority= 9) 
	public void VerifySitesAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning sites to the user", "This test case will verify Sites column displays Active after assigning sites to the user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Assign sites to new user");
			Test_Variables.steps.createNode("2. Verify Active in Sites column");
			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(9) label")).getText(), "Inactive");
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
			Helper.driver.findElement(By.id("select-testing-sites")).click();
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


	@Test (enabled= true, priority= 10) 
	public void VerifyAgreementAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-15: Verify user can view assigned agreement", "This test case will verify that user can view assigned agreement");
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
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Helper.driver.findElement(By.id("btn-next")).click();
			Helper.driver.findElement(By.id("btn-next")).click();
			Helper.driver.findElement(By.cssSelector("#euladdl .ng-arrow-wrapper")).click();
			Helper.driver.findElement(By.xpath("//*[@id=\"euladdl\"]//div[2]/input")).sendKeys(Keys.ENTER);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");

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


	@Test (description="Test Case: Update User", enabled = true, priority= 11) 
	public void UpdateUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-16: Verify user can update a user and convert user to piper user", "This test case will verify that user can update a user");
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

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				//	if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals("ancera@email.com")) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#lastNameId input:nth-child(1)")).clear();
			Helper.driver.findElement(By.cssSelector("#lastNameId input:nth-child(1)")).sendKeys(Test_Variables.lstUserUpdate.get(0));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector("#piper-user .wrapper-v2")).click();
			Thread.sleep(500);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(1)); 
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


	@Test (description="Test Case: Verify Update User", enabled = true, priority= 12) 
	public void VerifyUpdateUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-17: Verify user is actually updated and user can reset pin for piper user", "This test case will verify that the user is actually updated by reopening the popup after updation");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.preconditions.createNode("5. Click on update button next to created user; Update user popup appears");
			Test_Variables.steps.createNode("1. Update the user and click on  Save button");
			Test_Variables.steps.createNode("2. Reopen the updated popup to verify that changes made were save or not");

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#lastNameId input:nth-child(1)")).getAttribute("value"), Test_Variables.lstUserUpdate.get(0)); 

			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.id("btn-reset-pin")).size(), 1); 

			Test_Variables.test.pass("User updation verified successfully");
			Test_Variables.results.createNode("User was updated successfully; changes remained saved");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User updation failed");
			Test_Variables.results.createNode("User updation failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("User updation failed");
			Test_Variables.results.createNode("User updation failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
		Helper.driver.findElement(By.id("close-popup-modal")).click();
	}


	@Test (description="Test Case: Delete User", enabled= true, priority= 13) 
	public void DeleteUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-18: Verify user can be deleted", "This test case will verify that user can delete a user");
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
			Helper.driver.findElement(By.id("logout")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.login_email);
			Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.login_password);
			Helper.driver.findElement(By.id("btn-sign-in")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ancera Intelligence Engine")));
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

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
			Thread.sleep(1000);
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


	@Test (description="Test Case: Filter Test",enabled= true, priority = 14) 
	public void TestFilter() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstUserSearch = UserModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
		for (UserModel objModel : Test_Variables.lstUserSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on User Management; User Management screen opens");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							WebElement filter_scroll = Helper.driver.findElement(By.id(Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
							Thread.sleep(800);	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");				
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);						
							if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
								Assert.assertTrue(true, "No records available to test filter");
								Test_Variables.test.skip("No records available to test filter");
								Helper.saveResultNew(ITestResult.SKIP, Constants.UserManagementReportPath, null);
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
							}

							else {
								for (int j = 0; j < objFilter.LstFilterValues.size(); j++) {
									Test_Variables.steps.createNode("2. Select the checkbox");
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 		
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label")).click();
								}

								Test_Variables.steps.createNode("3. Click on apply filter button");	
								ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+""+Test_Elements.ApplyFilter);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(800);
								String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();		

								Assert.assertNotEquals(recordBefore, recordAfter);
								Test_Variables.test.pass("Filter applied successfully");
								Test_Variables.results.createNode("Filter applied successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
							}
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch, objModel.TestCaseDescriptionSearch);
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on User Management; User Management reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify filter is applied and relevant results are displayed in table");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							if (Helper.driver.findElements(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" .active-filter")).size() != 0) {			
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Thread.sleep(500);
								if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
									Test_Variables.test.skip("No records available to test filter");
									Helper.saveResultNew(ITestResult.SKIP, Constants.UserManagementReportPath, null);
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.ShowFilter)).click();
								}
								else {	
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" .divider")).size(), 1);
									Test_Variables.test.pass("Applied checkbox bubbled to top successfully");
									Test_Variables.results.createNode("Applied checkbox bubbled to top successfully");
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
									Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ClearFilter)).click();
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.ShowFilter)).click();
								}
							}
							else {
								Test_Variables.results.createNode("Test case skipped because filter was not applied");
								Test_Variables.test.skip("Test case skipped because filter was not applied");
								Helper.saveResultNew(ITestResult.SKIP, Constants.UserManagementReportPath, null);
							}			
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Wildcard",enabled= true, priority = 15) 
	public void Wildcard() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);

		Test_Variables.lstUserWildcardSearch = UserModel.Wildcard(); 
		for (UserModel objModel : Test_Variables.lstUserWildcardSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on User Management; User Management page opens");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ShowFilter));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 	
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ShowFilter)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						if (Helper.driver.findElements(By.cssSelector("#sort-"+objFilter.FilterID+" .data-log-radio")).size() == 0) {
							Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.FilterID+" .filter-popup__action--wildcard")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						}

						if(objModel.startWith) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterID+"_wildcard-option1")).click();
						}

						if(objModel.endsWith) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterID+"_wildcard-option3")).click();
						}

						if(objModel.contains) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterID+"_wildcard-option2")).click();
						}

						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.SearchInput)).clear();
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.SearchInput)).sendKeys(objModel.input);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ApplyFilter)).click();

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] td:nth-child(4) label"));
						int count = rows.size();
						Thread.sleep(1000);

						for (int i = 0; i<count; i++) {
							if(objModel.startWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								Assert.assertTrue(str.startsWith(objFilter.LstFilterValues.get(0)) || str.startsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.endsWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();				
								Assert.assertTrue(str.endsWith(objFilter.LstFilterValues.get(0)) || str.endsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.contains) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								Assert.assertTrue(str.contains(objFilter.LstFilterValues.get(0)) || str.contains(objFilter.LstFilterValues.get(1)));
							}
						}

						Thread.sleep(1000);
						Test_Variables.test.pass("Wildcards tested successfully");
						Test_Variables.results.createNode("Wildcards tested successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.UserManagementReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
					}
					Helper.driver.findElement(By.id(objFilter.FilterXPath+""+Test_Elements.ClearFilter)).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Lock Filter Functionality",enabled= true, priority = 16) 
	public void Lock() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);

		if (Helper.driver.findElements(By.cssSelector("#remove-filters.d-none")).size() == 0) {
			Helper.driver.findElement(By.id("remove-filters")).click();
			Thread.sleep(1000);
		}

		Test_Variables.lstUserLock = UserModel.Lock(); 
		for (UserModel objModel : Test_Variables.lstUserLock) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on User Management; User Management open");
				SoftAssert softAssert = new SoftAssert();

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);	
						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID+"_show-filter"));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
						Helper.driver.findElement(By.id(objFilter.FilterID+"_show-filter")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);
						if (Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.FilterID+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
							Test_Variables.test.skip("Values not enough to test lock filter functionality");
							Test_Variables.results.createNode("Values not enough to test lock filter functionality");
							Helper.saveResultNew(ITestResult.SKIP, Constants.UserManagementReportPath, null);
							Helper.driver.findElement(By.id(objFilter.FilterID+"_show-filter")).click();
						}
						else {
							Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.FilterID+" li:nth-child(2) label")).click();
							Thread.sleep(500);
							Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
							Helper.driver.findElement(By.id(objFilter.FilterID+"_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Test_Variables.steps.createNode("2. Click on lock button");	
							Helper.driver.findElement(By.id("save-filters")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
							Thread.sleep(1000);
							String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();
							Test_Variables.steps.createNode("3. Close User Management Report");
							Test_Variables.steps.createNode("4. Reopen User Management Report");
							Helper.driver.navigate().refresh();

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.steps.createNode("5. Verify lock filter remains applied");
							softAssert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.id("results-found-count")).getText());
							Test_Variables.test.pass(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("remove-filters")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Helper.driver.findElement(By.id("reset-all-filters")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(1000);
							softAssert.assertNotEquals(Helper.driver.findElement(By.id("results-found-count")).getText(), recordsafterfilter);
							softAssert.assertAll();
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority =17) 
	public void Sorting() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Test_Variables.lstUserSorting = UserModel.sorting();

		for (UserModel objModel : Test_Variables.lstUserSorting) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.testCaseTitle, objModel.testCaseDesc);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on User Management; User Management open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.ColumnID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" column header");
						Helper.driver.findElement(By.id("objFilter.ColumnID")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(500);

						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).size(), 1, "Did not sorted in descending order");

						Assert.assertEquals(Helper.driver.findElements(By.id("message")).size(), 0, "Exception message occured");

						Test_Variables.test.pass(objFilter.FilterName+" column sorted descending successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted descending successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(1000);

						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_asc")).size(), 1, "Did not sorted in ascending order");

						Assert.assertEquals(Helper.driver.findElements(By.id("message")).size(), 0, "Exception message occured");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.test.pass(objFilter.FilterName+" column sorted ascending successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted ascending successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to sort");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to sort");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
					}
					catch(StaleElementReferenceException s) {
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority =18) 
	public void AccessRoleCount() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-63: Verify count of assign role in popup is same as that in table", "This test case will verify that count of assign role in popup is same as that in table");
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


	@Test (enabled= true, priority= 19) 
	public void EditAssignRolePopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-64: Verify user can be edited from assign roles and right popup", "This test case will verify that user can be edited from roles and right popup");
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
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				//if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals("ancera@email.com")) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userRoleCol+" img")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Helper.driver.findElement(By.cssSelector("#reportRoleId .ng-arrow-wrapper")).click();
			Helper.driver.findElement(By.xpath("//*[@id=\"reportRoleId\"]//input")).sendKeys(Keys.ARROW_DOWN);
			Helper.driver.findElement(By.xpath("//*[@id=\"reportRoleId\"]//input")).sendKeys(Keys.ENTER);
			String Reporting = Helper.driver.findElement(By.xpath("//*[@id=\"reportRoleId\"]//input")).getText();
			Helper.driver.findElement(By.id("btn-save")).click();
			Thread.sleep(750);
			Helper.driver.findElement(By.cssSelector(".anc-btn-solid")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Roles and Rights has been updated successfully");
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.createUserEmail)) {
					Assert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userReportingCol+" img")).getText(), Reporting);
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


	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Test User CSV Download",enabled= true, priority =20) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-65: Verify user can download User CSV file and verify the records", "This test case will verify that user can download User CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on User Management; User Management reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Helper.driver.findElement(By.id(Test_Elements.userOrgType+""+Test_Elements.ShowFilter)).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);						
			ClickElement.clickByCss(Helper.driver, "#"+Test_Elements.SortFilter+""+Test_Elements.userOrgType+" li:nth-child(2) label");

			ClickElement.clickById(Helper.driver, Test_Elements.userOrgType+""+Test_Elements.ApplyFilter);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);

			String getRowText = Helper.driver.findElement(By.id("results-found-count")).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Test_Variables.steps.createNode("5. Click on Export as CSV");	
			Test_Variables.steps.createNode("6. Verify the columns are same in table and CSV");

			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, Test_Variables.userCSVFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
			FileReader filereader = new FileReader(file);
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			StringBuffer buffer = new StringBuffer();
			String data[];		    				

			int columnsCountTotal = 0;
			int rowsCount = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {

					int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {

						int columnsCount = columnsCountTotal+1;
						if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+rowsCount+")")).size() != 0 && columnsCount<=9) {
							softAssert.assertEquals(data[i], Helper.driver.findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText());
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = 0;
						}
						columnsCountTotal++;
					}
				}
				System.out.println(" ");
			}

			Path path = Paths.get(Test_Variables.fileDownloadPath+"\\"+filename);
			long lines = 0;
			try {
				lines = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
			}

			long excludeHeader = lines - 1;
			String s = String.valueOf(excludeHeader);

			String str = getRowText;
			str = str.replace(",", "");
			Assert.assertEquals(s, str);

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
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