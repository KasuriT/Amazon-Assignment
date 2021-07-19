package Test.Ancera.Administration;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.ReportFilters;
import Models.UserModel;
import Test.Ancera.ClickElement;
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


	@Test (description="Test Case: Navigate to User Management Screen",enabled= true, priority = 1) 
	public void NavigateUM() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-01: Verify user can navigate to User Management screen", "This test case will verify that user can navigate to User Management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-user")));
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


	@Test (description="Exceptional Flow: Mandatory field check", enabled= false, priority= 2) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);

		String userFirstNameError;
		String userLastNameError;
		String userPhoneNoError;
		String userEmailError;

		for (UserModel objModel : Test_Variables.lstUserMandatoryCheck) {
			userFirstNameError = "";
			userLastNameError = "";
			userPhoneNoError = "";
			userEmailError = "";

			if (objModel.isOpenPopUp)
			{
				try{
					Test_Variables.test = Test_Variables.extent.createTest("AN-UM-02: Verify user can open Create New User Popup", "This test case will verify that user is able to open create new user popup");
					Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
					Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
					Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

					Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
					Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					Test_Variables.preconditions.createNode("3. Hover to sidebar; Click on Administration and select User Management");	
					Test_Variables.steps.createNode("1. Click on Create New button");

					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.driver.findElement(By.id("create-user")).click();
					Thread.sleep(1000);


					Assert.assertTrue(Helper.driver.findElement(By.id("firstNameId")).isDisplayed()); 
					Test_Variables.test.pass("User popup window opened successfully");
					Test_Variables.results.createNode("Create New User popup opens successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
				}catch(AssertionError er){
					Test_Variables.test.fail("User popup window did not open successfully");
					Test_Variables.results.createNode("User popup window did not open successfully");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
				}	
				catch(Exception ex){
					Test_Variables.test.fail("User popup window did not open successfully");
					Test_Variables.results.createNode("User popup window did not open successfully");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
				}
			}

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

				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
				Helper.driver.findElement(By.id("btn-reset")).click();
				Helper.driver.findElement(By.id("firstNameId")).clear();
				Helper.driver.findElement(By.id("firstNameId")).sendKeys(objModel.userFirstName);
				Helper.driver.findElement(By.id("lastNameId")).clear();
				Helper.driver.findElement(By.id("lastNameId")).sendKeys(objModel.userLastName);	
				Thread.sleep(1000);

				if (objModel.userPhoneCode)
				{
					Helper.driver.findElement(By.id("phoneCodeId")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.cssSelector("#phoneCodeId div.ng-star-inserted")).click();
				}

				Helper.driver.findElement(By.id("PhoneNumberId")).clear();
				Helper.driver.findElement(By.id("PhoneNumberId")).sendKeys(objModel.userPhoneNo);	
				Thread.sleep(500);

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-next")));
				Helper.driver.findElement(By.id("btn-next")).click(); 
				Thread.sleep(1000);

				if (objModel.chkMandatoryFieldsS1)
				{

					if ( objModel.userFirstName.isEmpty())
					{
						if(Helper.driver.findElements(By.xpath(Test_Elements.firstNameValidation)).size() != 0) {
							userFirstNameError = Helper.driver.findElement(By.xpath(Test_Elements.firstNameValidation)).getText();
						}
						Assert.assertEquals(userFirstNameError, Test_Elements.firstnameexpected); 
					}


					if ( objModel.userLastName.isEmpty())
					{
						if(Helper.driver.findElements(By.xpath(Test_Elements.lastNameValidation)).size() != 0) {
							userLastNameError = Helper.driver.findElement(By.xpath(Test_Elements.lastNameValidation)).getText();
						}
						Assert.assertEquals(userLastNameError, Test_Elements.lastnameexpected); 
					}			

					if ( objModel.userPhoneNo.isEmpty())
					{
						if(Helper.driver.findElements(By.xpath(Test_Elements.phoneNumberValidation)).size() != 0) {
							userPhoneNoError = Helper.driver.findElement(By.xpath(Test_Elements.phoneNumberValidation)).getText();
						}
						Assert.assertEquals(userPhoneNoError, Test_Elements.phonenoexpected); 
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


			if (Helper.driver.findElement(By.xpath(Test_Elements.userEmail)).isDisplayed()) {
				Assert.assertTrue(true);
				Thread.sleep(1000);

				Helper.driver.findElement(By.xpath(Test_Elements.userResetButton2)).click();
				Helper.driver.findElement(By.xpath(Test_Elements.userEmail)).clear();
				Helper.driver.findElement(By.xpath(Test_Elements.userEmail)).sendKeys(objModel.userEmail);

				if (objModel.userOrganizationType)
				{
					Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationType)).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationTypeSelect)).click();
				}

				if (objModel.userOrganization)
				{
					Helper.driver.findElement(By.xpath(Test_Elements.userOrganization)).click();
					Thread.sleep(500);
					Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationSelect)).click();
				}


				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userPopupNextButton2)));
				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath(Test_Elements.userPopupNextButton2)).click(); 


				if (objModel.chkMandatoryFieldsS2)
				{
					if ( objModel.userEmail.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.emailValidation)).isDisplayed()) {
							userEmailError = Helper.driver.findElement(By.xpath(Test_Elements.emailValidation)).getText();
						}
						Assert.assertEquals(userEmailError, Test_Elements.emailexpected); 
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
					}
				}
			}
			else
			{
				Assert.assertFalse(true);
			}
		}

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-07 :Close Create User Popup", "This test case will verify that user is able to close create organization popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button; popup opens");
			Test_Variables.steps.createNode("1. Click on cross button on top right of popup");
			Thread.sleep(1000);

			if (Helper.driver.findElements(By.id("close-popup-modal")).size() != 0) {
				Helper.driver.findElement(By.id("close-popup-modal")).click();
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-user")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-user")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.driver.findElement(By.id("close-popup-modal")).click();	


			Assert.assertEquals(Helper.driver.findElement(By.id("User Management")).getText(), "User Management"); 
			Test_Variables.test.pass("User popup window closed successfully");
			Test_Variables.results.createNode("User popup window closed successfully");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("User popup window failed to close");
			Test_Variables.results.createNode("User popup window failed to close");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User popup window failed to close");
			Test_Variables.results.createNode("User popup window failed to close");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}



	@Test (description="Exceptional Flow: Reset fields", enabled= false, priority= 3) 
	public void ResetButton() throws InterruptedException, IOException {

		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-08: Verify user can reset fields", "This test case will verify that user can reset fields");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button");
			Test_Variables.steps.createNode("1. Enter data in all fields");
			Test_Variables.steps.createNode("2. Click on reset button");

			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("create-user")));
			Helper.driver.findElement(By.id("create-user")).click();
			Thread.sleep(1000);

			WebElement usrFirstName = Helper.driver.findElement(By.id("firstNameId"));
			String firstNameReset = usrFirstName.getAttribute("value");
			usrFirstName.sendKeys(Test_Variables.lstUserCreate.get(0));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("btn-reset")).click();
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



	@Test (enabled= true, priority= 4) 
	public void CreateUser() throws InterruptedException, IOException {
		try{
			Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(500);
			WebElement search = Helper.driver.findElement(By.xpath(Test_Elements.userSearch));
			search.sendKeys(Test_Variables.lstUserCreate.get(0));
			ClickElement.clickById(Helper.driver, "tag-0");
			search.sendKeys(Test_Variables.lstUserCreate.get(1));
			ClickElement.clickById(Helper.driver, "tag-1");

			Thread.sleep(1000);
			if (Helper.driver.findElement(By.cssSelector("#user-grid>tbody>tr:first-child>td:first-child")).isDisplayed()) {
				String user_firstname = Helper.driver.findElement(By.cssSelector("#user-grid>tbody>tr:first-child>td:first-child")).getText();
				System.out.print(user_firstname);
				if (user_firstname.equals(Test_Variables.lstUserCreate.get(0))) {
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-user-1")));
					Helper.driver.findElement(By.id("delete-user-1")).click();
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
					Helper.driver.findElement(By.id("btn-yes")).click();	
				}
			}
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-user")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("create-user")).click();     

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstNameId")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("firstNameId")).sendKeys(Test_Variables.lstUserCreate.get(0));    
			Helper.driver.findElement(By.id("lastNameId")).sendKeys(Test_Variables.lstUserCreate.get(1));  
			Helper.driver.findElement(By.id("phoneCodeId")).click();      
			Helper.driver.findElement(By.cssSelector("#phoneCodeId div.ng-star-inserted")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("PhoneNumberId")).sendKeys(Test_Variables.lstUserCreate.get(2));
			Helper.driver.findElement(By.id("btn-next")).click();


			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-09: Verify user cannot create a user with invalid email", "This test case will create a user with invalid email");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button");
			Test_Variables.steps.createNode("1. Enter invalid email");
			Test_Variables.steps.createNode("2. Click on next button; should display validation message");

			Helper.driver.findElement(By.id("emailId")).sendKeys(Test_Variables.lstUserEmails.get(0));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(1000);
			String userEmailactual = Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-user-v2/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[1]/div")).getText();

			Assert.assertEquals(userEmailactual, "Invalid email"); 
			Test_Variables.test.pass("User was not created with invalid email");
			Test_Variables.results.createNode("Did not save user, displayed validaton message underneath email field 'Invalid Email'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("User was created with invalid email");
			Test_Variables.results.createNode("User was created with invalid email");
		}


		Thread.sleep(1500);

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
			Thread.sleep(500);

			Helper.driver.findElement(By.id("emailId")).clear();   
			Helper.driver.findElement(By.id("emailId")).sendKeys(Test_Variables.createUserEmail);  
			ClickElement.clickById(Helper.driver, "orgTypeId");
			Helper.driver.findElement(By.xpath("//*[text()='Ancera']")).click();  
			Thread.sleep(1000);
			ClickElement.clickById(Helper.driver, "organizationId");
			Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationSelect)).click();
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("btn-next")).click();   
			Thread.sleep(1000);
			ClickElement.clickById(Helper.driver, "rolesId");  
			Thread.sleep(1000);
			ClickElement.clickById(Helper.driver, "rolesId-1");  
			ClickElement.clickByCss(Helper.driver, "#rolesId span.ng-arrow-wrapper"); 

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.driver.findElement(By.id("btn-save")).click();    

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(500);

			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(0)); 
			Test_Variables.test.pass("New User created successfully");
			Test_Variables.results.createNode("New User created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Failed to create a new user");
			Test_Variables.results.createNode("Failed to create a new user");
		}

		Thread.sleep(500);
	}


	@Test (enabled= false, priority= 5, retryAnalyzer = RetryFailedCases.class) 
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
		Helper.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
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

		//	Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.gmailEmail)));
		//	Helper.driver.findElement(By.xpath(Test_Elements.gmailEmail)).sendKeys(Test_Variables.createUserEmail);
		//	Helper.driver.findElement(By.xpath(Test_Elements.gmailEmail)).sendKeys(Keys.ENTER);

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


	@Test (enabled= false, priority= 6) 
	public void ResetPassword() throws InterruptedException, IOException {

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

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordId")));
		Helper.driver.findElement(By.id("passwordId")).sendKeys(Test_Variables.createUserPassword);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rePassordId")));

		Helper.driver.findElement(By.id("rePassordId")).sendKeys(Test_Variables.createUserPassword);
		Helper.driver.findElement(By.cssSelector("button.apl-btn")).click();
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		Helper.driver.findElement(By.id("email")).clear(); 
		Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.createUserEmail);     
		Helper.driver.findElement(By.id("pwd")).clear();
		Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.createUserPassword);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
		Helper.driver.findElement(By.id("btn-sign-in")).click();

		if (Helper.driver.findElements(By.xpath("/html/body/app-root/div/app-sign-in/div[2]/app-general-modal/div/div/div/div[2]/app-view-user-license/div[2]/button[1]")).size() != 0 ) {
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-sign-in/div[2]/app-general-modal/div/div/div/div[2]/app-view-user-license/div[2]/button[1]")).click();
		}


		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ancera Intelligence Engine")));

		try{
			Assert.assertTrue(Helper.driver.findElement(By.id("Ancera Intelligence Engine")).isDisplayed());
			Test_Variables.test.pass("Password was reset; user logged in successfully");
			Test_Variables.results.createNode("Password was reset; user logged in successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("New User log in failed");
			Test_Variables.results.createNode("User failed to login");
		}	
	}


	@Test (description="Test Case: Search Created User",enabled= false, priority= 7) 
	public void SearchUser() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userSearch)));
		for(UserModel objModel : Test_Variables.lstUserSearch) {
			try{
				Test_Variables.test = Test_Variables.extent.createTest(objModel.userSearchTestCaseTitle, objModel.userSearchTestCaseDesc);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
				Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
				Test_Variables.steps.createNode("1. "+objModel.userSearchStep);

				Thread.sleep(1000);
				WebElement search = Helper.driver.findElement(By.xpath(Test_Elements.userSearch));
				search.clear();
				search.sendKeys(objModel.userSearchName);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
				Helper.driver.findElement(By.id("tag-0")).click();
				Thread.sleep(1000);
				int rowCount=Helper.driver.findElements(By.xpath("//table[@id='user-grid']/tbody/tr")).size();

				Assert.assertTrue(rowCount == Integer.parseInt(objModel.userSearchResult));
				Test_Variables.test.pass(objModel.userSearchPassScenario);
				Test_Variables.results.createNode(objModel.userSearchPassScenario);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail(objModel.userSearchFailScenario);
				Test_Variables.results.createNode(objModel.userSearchFailScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				Test_Variables.test.fail(objModel.userSearchFailScenario);
				Test_Variables.results.createNode(objModel.userSearchFailScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
			}		
		}
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-15: Verify user can close search results", "This test case will verify that user can close search results");	
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.steps.createNode("1. Search for a user using search box");
			Test_Variables.steps.createNode("2. Click on cross icon");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.driver.findElement(By.cssSelector("i.fa-times")).click();
			Thread.sleep(2000);

			int rowCount=Helper.driver.findElements(By.xpath("//table[@id='user-grid']/tbody/tr")).size();
			System.out.println(rowCount);
			Assert.assertTrue(rowCount > 5);
			Test_Variables.test.pass("Searched results closed successfully");
			Test_Variables.results.createNode("Search results closed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Search results failed to close");
			Test_Variables.results.createNode("Search results failed to close");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Search results failed to close");
			Test_Variables.results.createNode("Search results failed to close");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}

	@Test (description="Test Case: Update User", enabled = false, priority= 8) 
	public void UpdateUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-16: Verify user can update a user", "This test case will verify that user can update a user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Click on update button next to created user; Update user popup appears");
			Test_Variables.steps.createNode("2. Make any change and click on Save button");
			Thread.sleep(1000);
			Test_Functions.userSearch();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-user-1")));
			Helper.driver.findElement(By.id("edit-user-1")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastNameId")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("lastNameId")).clear();
			Helper.driver.findElement(By.id("lastNameId")).sendKeys(Test_Variables.lstUserUpdate.get(0));
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
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


	@Test (description="Test Case: Verify Update User", enabled = false, priority= 9) 
	public void VerifyUpdateUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-17: Verify user is actually updated", "This test case will verify that the user is actually updated by reopening the popup after updation");
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

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-user-1")));
			Helper.driver.findElement(By.id("edit-user-1")).click();
			Thread.sleep(1500);

			String a = Helper.driver.findElement(By.id("lastNameId")).getAttribute("value");
			System.out.println(a);
			Assert.assertEquals(a, Test_Variables.lstUserUpdate.get(0)); 
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
		Helper.driver.findElement(By.cssSelector(".close")).click();
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Delete User", enabled= false, priority= 10) 
	public void DeleteUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-18: Verify user can be deleted", "This test case will verify that user can delete a user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
Helper.driver.navigate().refresh();
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. Click on delete butotn next to created user; confirmation box appears");
			Test_Variables.steps.createNode("2. Click on yes button");
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
			Helper.driver.findElement(By.id("logout")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.login_email);
			Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.login_password);
			Helper.driver.findElement(By.id("btn-sign-in")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ancera Intelligence Engine")));
			Helper.driver.get(Constants.url_user);
			Test_Functions.userSearch(); 
			Thread.sleep(1000);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-user-1")));
			Helper.driver.findElement(By.id("delete-user-1")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-yes")));
			Helper.driver.findElement(By.id("btn-yes")).click();

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstUserAlertMessages.get(2)); 
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


	@Test (description="Test Case: Verify Delete User", enabled= false, priority= 11) 
	public void VerifyDeleteUser() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-19: Search for deleted user to verify user is actually deleted", "This test case will search for deleted user to verify user is actually deleted");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.preconditions.createNode("5. Click on delete butotn next to created user; confirmation box appears");
			Test_Variables.preconditions.createNode("6. Click on yes button");
			Test_Variables.steps.createNode("1. Search for the deleted user from search box");

			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));

			String result = Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-user-v2/div/div[2]/div/div/table/tbody/tr/td")).getText();

			Test_Variables.steps.createNode("2. Check that search results displays 0");
			Thread.sleep(1500);

			Assert.assertEquals(result, "No results found"); 
			Test_Variables.test.pass("User deleted successfully");
			Test_Variables.results.createNode("User deleted successfully; search result displayed 0");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);	
		} catch(AssertionError er) {
			Test_Variables.test.fail("User was not deleted");
			Test_Variables.results.createNode("User was not deleted");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User was not deleted");
			Test_Variables.results.createNode("User was not deleted");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}	
	}
	
	
	////////////////////////////
	
	
	@Test (enabled= false, priority = 12) 
	public void Sorting() throws InterruptedException, IOException {
		
		Test_Variables.lstUserSorting = UserModel.FillData();
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-first-name"))); 
		
		for (UserModel objModel : Test_Variables.lstUserSorting) { 
			try {
				Thread.sleep(2000);
				Test_Variables.test = Test_Variables.extent.createTest(objModel.testCaseTitle, objModel.testCaseDesc);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");

				for (@SuppressWarnings("unused") ReportFilters objFilter : objModel.lstFilters) {	
					try {						
						Test_Variables.steps.createNode("1. "+objModel.step1);

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						
						if(objModel.search) {
							Helper.driver.findElement(By.id("search-bar")).sendKeys("a");
							Helper.driver.findElement(By.id("tag-0")).click();
							Thread.sleep(500);
						}
								
						Helper.driver.findElement(By.id(objModel.userType)).click();
						Helper.driver.findElement(By.id(objModel.sortType)).click();
						Thread.sleep(2000);
						Assert.assertTrue(Helper.driver.findElement(By.cssSelector(".sorting_desc")).isDisplayed()); 
						if (Helper.driver.findElements(By.id("message")).size() != 0) {
							Assert.assertTrue(false);
						}
						Test_Variables.test.pass(objModel.passScenario);
						Test_Variables.results.createNode(objModel.passScenario);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Upload", Constants.DataUploadReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataUploadReportPath, null);
						Helper.driver.findElement(By.id(objModel.sortType)).click();			
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objModel.failScenario);
						Test_Variables.results.createNode(objModel.failScenario);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objModel.failScenario);
						Test_Variables.results.createNode(objModel.failScenario);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}