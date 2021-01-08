package Test.Ancera.Administration;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.OrgModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;

public class OrganizationManagement{
	
	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Management"+Test_Variables.date+".html");	//	Test_Variables.spark.config().setReportName("Organization Management Test Report"); 
		Test_Variables.spark.config().setReportName("Organization Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}

	
	@Test (description="Test Case: Navigate to Organization Management Screen",enabled=true, priority = 2) 
	public void NavigateOM() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-01: Navigate to Organization Management Screen", "This test case will navigate to Organization Managment Screen");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
		Test_Variables.steps.createNode("2. Click on Adminstration and select Organization Management");
		
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.get(Constants.url_organization);
		Thread.sleep(2000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText();
		String expected = "Organization Management";

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Organization Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));

		}catch(AssertionError e){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User failed to navigate to Organization Management Screen");
		}	
	}



	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 3) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {

		String orgNameError;
		String orgPhoneNoError;
		String orgEmailError;
		String orgMaxUserError;

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);

		for (OrgModel objModel : Test_Variables.lstOrgMandatoryCheck) {
			orgNameError = "";
			orgPhoneNoError = "";
			orgEmailError = "";
			orgMaxUserError = "";
			
			if (objModel.isOpenPopUp)
			{
				Test_Variables.test = Test_Variables.extent.createTest("AN-OM-02: Open Create Organization Popup", "This test case will verify that user is able to open create organization popup");
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar; Click on Administration and select Organization Management");	
				Test_Variables.steps.createNode("1. Click on Create New button");
			
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
				Helper.driver.findElement(By.xpath(Test_Elements.orgCreateButton)).click();
				Thread.sleep(2000);
	
				try{
					Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.orgPopupGetTitle)).getText(), "Create Organization"); 
					Test_Variables.test.pass("Organization popup window opened successfully");
					Test_Variables.results.createNode("Create New Organization popup opens successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
				}catch(AssertionError e){
					Test_Variables.test.fail("Organization popup window did not open successfully");
					Test_Variables.results.createNode("Create New Organization popup failed to opened");
					break;
					}	
			}
			
			Test_Variables.test = Test_Variables.extent.createTest(objModel.testCaseTitle, objModel.testCaseDesc);
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar; Click on Administration and select Organization Management");	
			Test_Variables.preconditions.createNode("4. Click on Create New button; Popup appears");
			Test_Variables.steps.createNode("1. "+objModel.step1);
			Test_Variables.steps.createNode("2. "+objModel.step2);
			
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.orgPopupResetButton)).click();
			
			
			if (objModel.orgType)
			{
			Helper.driver.findElement(By.xpath(Test_Elements.orgType)).click();
			Thread.sleep(1500);
			Helper.driver.findElement(By.xpath(Test_Elements.orgTypeSelect)).click();
			Helper.driver.findElement(By.xpath(Test_Elements.orgTypeId));
			}
			
			if (objModel.orgPhoneCode)
			{
			Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneCode)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneCodeSelect)).click(); 
			
			}
			
			Thread.sleep(1500);
			Helper.driver.findElement(By.xpath(Test_Elements.orgName)).clear();
			Helper.driver.findElement(By.xpath(Test_Elements.orgName)).sendKeys(objModel.orgName);
			Thread.sleep(1500);
			Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneNo)).clear();
			Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneNo)).sendKeys(objModel.orgPhoneNo);	
			Thread.sleep(1500);
			Helper.driver.findElement(By.xpath(Test_Elements.orgEmail)).clear();
			Helper.driver.findElement(By.xpath(Test_Elements.orgEmail)).sendKeys(objModel.orgEmail);
			Thread.sleep(1500);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgPopupNextButton)));
			Helper.driver.findElement(By.xpath(Test_Elements.orgPopupNextButton)).click(); 

			Thread.sleep(2000);
			
			try {
			
			if (objModel.chkMandatoryFieldsS1)
			{
				
				if ( objModel.orgName.isEmpty())
				{
					if(Helper.driver.findElements(By.xpath(Test_Elements.orgNameValidation)).size() != 0) {
						orgNameError = Helper.driver.findElement(By.xpath(Test_Elements.orgNameValidation)).getText();
					}
					Assert.assertEquals(orgNameError, Test_Elements.orgNameexpected); 
				}				
				
				if ( objModel.orgPhoneNo.isEmpty())
				{
					if(Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneNumberValidation)).isDisplayed()) {
						orgPhoneNoError = Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneNumberValidation)).getText();
					}
					Assert.assertEquals(orgPhoneNoError, Test_Elements.orgPhonenoexpected); 
				}
				
				if ( objModel.orgEmail.isEmpty())
				{
					if(Helper.driver.findElement(By.xpath(Test_Elements.orgEmailValidation)).isDisplayed()) {
						orgEmailError = Helper.driver.findElement(By.xpath(Test_Elements.orgEmailValidation)).getText();
					}
					Assert.assertEquals(orgEmailError, Test_Elements.orgEmailexpected); 
				}
				
				Test_Variables.test.pass(objModel.passScenario);
				Test_Variables.results.createNode(objModel.passScenario);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
				
				
				continue;
			}
			}	catch(AssertionError e){
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);	
			}
			
			if (Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUser)).isDisplayed()) {
				Assert.assertTrue(true);
				
				
				Thread.sleep(2000);
				
				Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUser)).clear();
				Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUser)).sendKeys(objModel.maxUsers);
				
				if (objModel.roles)
				{
				Helper.driver.findElement(By.xpath(Test_Elements.orgRoles)).click();
				Thread.sleep(1500);
				Helper.driver.findElement(By.xpath(Test_Elements.orgRolesIdSelect)).click();
				Helper.driver.findElement(By.xpath("/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[2]/div/div/ng-select/div/span[2]")).click();
				}

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgSaveButton)));
				Thread.sleep(1500);
				Helper.driver.findElement(By.xpath(Test_Elements.orgSaveButton)).click(); 
							
				try {
				if (objModel.chkMandatoryFieldsS2)
				{
				if ( objModel.maxUsers.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUsersValidation)).isDisplayed()) {
							orgMaxUserError = Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUsersValidation)).getText();
						}
						Assert.assertEquals(orgMaxUserError, Test_Elements.orgMaxUsersexpected); 
					}
				
				Test_Variables.test.pass(objModel.passScenario);
				Test_Variables.results.createNode(objModel.passScenario);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
				
				
				}}catch(AssertionError e){
					Test_Variables.test.fail(objModel.failScenario);
					Test_Variables.results.createNode(objModel.failScenario);	
				}
			}
			else
			{
				Assert.assertFalse(true);
			}
		} 		
	}

	
	@Test (description="Exceptional Flow: Close Popup", enabled= true, priority= 4) 
	public void ClosePopup() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-16: Verify user can close create Organization popup", "This test case will verify that user can close create organization popup");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
		Test_Variables.preconditions.createNode("4. Click on create new button; popup opens");
		Test_Variables.steps.createNode("1. Click on cross button on top right of popup");
		
		Helper.driver.get(Constants.url_organization);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgCreateButton)));
		Thread.sleep(2000);
	    Helper.driver.findElement(By.xpath(Test_Elements.orgCreateButton)).click();
	    Thread.sleep(2000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
	    Thread.sleep(1000);
	    Helper.driver.findElement(By.xpath(Test_Elements.orgCloseButton)).click();
	    
		try{
			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText(), "Organization Management"); 
			Test_Variables.test.pass("Organization popup window closed successfully");
			Test_Variables.results.createNode("Organization popup window closed successfully");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Organization popup window failed to close");
			Test_Variables.results.createNode("Organization popup window failed to close");	
		}	
	}
	

	@Test (description="Exceptional Flow: Reset fields", enabled= true, priority= 5) 
	public void ResetButton() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-17: Verify user can send reset fields", "This test case will verify that user can reset fields");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button");
		Test_Variables.steps.createNode("1. Enter data in all fields");
		Test_Variables.steps.createNode("2. Click on reset button");
			
		Helper.driver.get(Constants.url_organization);
		Thread.sleep(2000);
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Helper.driver.findElement(By.xpath(Test_Elements.orgCreateButton)).click();
		Thread.sleep(1500);

		WebElement orgName = Helper.driver.findElement(By.xpath(Test_Elements.orgName));
		String orgNameReset = orgName.getAttribute("value");
		orgName.sendKeys(Test_Variables.lstUserCreate.get(0));

		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgPopupResetButton)).click();
		Thread.sleep(1000);

		if(orgNameReset.isEmpty() )     {
			Test_Variables.test.pass("Fields reset successfully");
			Test_Variables.results.createNode("Fields reset successfully");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		}
		
		else {
			Test_Variables.test.fail("Fields failed to reset");  
			Test_Variables.results.createNode("Fields failed to reset"); 		
		}	
		Helper.driver.findElement(By.xpath(Test_Elements.orgPopupCloseButton)).click();
	}



	@Test (description="Test Case: Create New Organization",enabled= true, priority= 6) 
	public void CreateOrganization() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_organization);
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(Helper.driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgCreateButton)));

		Helper.driver.findElement(By.xpath(Test_Elements.orgCreateButton)).click();  
		Thread.sleep(2000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgType)).click();
		Helper.driver.findElement(By.xpath(Test_Elements.orgTypeSelect)).click();
		Helper.driver.findElement(By.xpath(Test_Elements.orgTypeId));
		Thread.sleep(1000);

		Helper.driver.findElement(By.xpath(Test_Elements.orgName)).sendKeys(Test_Variables.lstOrganizationCreate.get(0));
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneCode)).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneCodeSelect)).click(); 
		Helper.driver.findElement(By.xpath(Test_Elements.orgPhoneNo)).sendKeys(Test_Variables.lstOrganizationCreate.get(1));
		Thread.sleep(1500);

		Test_Variables.test.createNode("Enter invalid email");
		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-19: Verify user cannot create Organization with invalid email", "This test case will verify that user cannot create new organization with invalid email");
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button; popup appears");
		Test_Variables.steps.createNode("1. Enter invalid email");
		Test_Variables.steps.createNode("2. Click on next button; should display validation message");

		Helper.driver.findElement(By.xpath(Test_Elements.orgEmail)).sendKeys(Test_Variables.lstOrganizationCreate.get(2));
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgPopupNextButton)).click();
		String orgEmailactual = Helper.driver.findElement(By.xpath(Test_Elements.orgEmailValidation)).getText();

		try{
			Assert.assertEquals(orgEmailactual, Test_Elements.orgInvalidEmailexpected); 
			Test_Variables.test.pass("User cannot create organization with invalid email; displays validation message 'Invalid email'");
			Test_Variables.results.createNode("User cannot create organization with invalid email; displays validation message 'Invalid email'");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("User was able to create organization with invalid email");
			Test_Variables.results.createNode("User was able to create organization with invalid email");	
		}

		Test_Variables.test.createNode("Result: Should display invalid email message");
		Thread.sleep(1500);

		//				Test_Variables.test = Test_Variables.extent.createTest("AN-OM-20: Upload Organization logo", "This test case will verify that user can upload organization logo");
		//				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		//				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		//				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		//				Test_Variables.preconditions.createNode("4. Click on create new button; popup appears");
		//				Test_Variables.steps.createNode("1. Enter invalid email");
		//				Test_Variables.steps.createNode("2. Click on next button; should display validation message");
		//				
		//				Test_Variables.test.createNode("Click on logo icon");
		//
		//				Test_Variables.test.createNode("Browse for a picture");
		//				Thread.sleep(2500);
		//				
		//				String imagePath = System.getProperty("user.dir") + "/Image/ancera_logo.jpg";
		//				System.out.print(imagePath);
		//				WebElement chooseFile3 = Helper.driver.findElement(By.xpath(Test_Elements.orgUploadPhoto));
		//				Thread.sleep(2500);
		//				chooseFile3.sendKeys(imagePath);
		//				System.out.print(imagePath);
		//				
		//				chooseFile.sendKeys("C:/Users/Muhammad Jawad Rauf/eclipse-workspace/IETestingCode/Image/ancera_logo.jpg");
		//				try{
		//					//chooseFile.sendKeys(Helper.projectPath+"/Image/ancera_logo.jpg");
		//					Test_Variables.test.pass("Organization logo uploaded successfully");
		//				}catch(AssertionError e){
		//					Test_Variables.test.fail("Organization logo upload failed");
		//				}
		//				Test_Variables.test.createNode("Resut: Should be able to upload org logo");

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-21: Verify user can create New Organizationn", "This test case will verify that user can create new organization");
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button; popup appears");
		Test_Variables.steps.createNode("1. Enter valid data in all fields");
		Test_Variables.steps.createNode("2. Click on save button");

		Helper.driver.findElement(By.xpath(Test_Elements.orgEmail)).clear();
		Helper.driver.findElement(By.xpath(Test_Elements.orgEmail)).sendKeys(Test_Variables.lstOrganizationCreate.get(3));


		Helper.driver.findElement(By.xpath(Test_Elements.orgPopupNextButton)).click();
		Thread.sleep(1500);

		Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUser)).sendKeys(Test_Variables.lstOrganizationCreate.get(4));;
		Thread.sleep(1000);

		WebElement roles = Helper.driver.findElement(By.xpath(Test_Elements.orgRolesId));
		roles.click();
		Helper.driver.findElement(By.xpath(Test_Elements.orgRolesIdSelect)).click();
		Helper.driver.findElement(By.xpath(Test_Elements.orgClickOut));
		Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUser)).click();
		Thread.sleep(1000);
		
		Helper.driver.findElement(By.xpath(Test_Elements.orgUserAgreement)).click();
		Helper.driver.findElement(By.xpath(Test_Elements.orgUserAgreementSelect)).click();
		Helper.driver.findElement(By.xpath(Test_Elements.orgMaxUser)).click();

		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgSaveButton)).click();;
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = Test_Variables.lstOrgAlertMessages.get(0) ;
		Thread.sleep(1000);
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("New Organization created successfully");
			Test_Variables.results.createNode("New Organization created successfully; displayed alert message 'New organization created.'");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("New Organization creation failed");
			Test_Variables.results.createNode("New Organization failed to create");	
		}		
	}


	@Test (description="Test Case: Search Created Organization ",enabled= true, priority= 7) 
	public void SearchOrganization() throws InterruptedException, IOException {
	
		Helper.driver.get(Constants.url_organization);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgSearch)));
		for(OrgModel objModel : Test_Variables.lstOrgSearch) {
			Test_Variables.test = Test_Variables.extent.createTest(objModel.orgSearchTestCaseTitle, objModel.orgSearchTestCaseDesc);	
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new user");
			Test_Variables.steps.createNode("1. "+objModel.orgSearchStep);

			Thread.sleep(1500);
			WebElement search = Helper.driver.findElement(By.xpath(Test_Elements.orgSearch));
			search.clear();
			search.sendKeys(objModel.orgSearchName);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			search.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			try{
				Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.orgSearchResult)).getText(), objModel.orgSearchResult); 
				Test_Variables.test.pass(objModel.orgSearchPassScenario);
				Test_Variables.results.createNode(objModel.orgSearchPassScenario);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			}catch(AssertionError e){
				Test_Variables.test.fail(objModel.orgSearchFailScenario);
				Test_Variables.results.createNode(objModel.orgSearchFailScenario);
			}	
		}
		
		Thread.sleep(1500);
		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-23: Verify user can close search results", "This test case will verify that user can close search results");	
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.steps.createNode("1. Search for a organization using search box");
		Test_Variables.steps.createNode("2. Click on cross icon");
		
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgCloseSearch)).click();
		if(Helper.driver.findElements(By.xpath(Test_Elements.orgSearchResult)).size() == 0)
			{
			Test_Variables.test.pass("Searched results closed successfully");
			Test_Variables.results.createNode("Search results closed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		}
		else {
			Test_Variables.test.fail("Search results failed to close");
			Test_Variables.results.createNode("Search results failed to close");
		}
	}

	
	@Test (description="Test Case: Update New Organization ",enabled= true, priority= 8) 
	public void UpdateOrganization() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-24: Verify user can update Created Organization", "This test case will verify that user can update created organization");	
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new organization");
		Test_Variables.steps.createNode("1. Click on update button next to created user; Update organization popup appears");
		Test_Variables.steps.createNode("2. Make any change and click on Save button");
		
		Helper.driver.get(Constants.url_organization);
		Test_Functions.OrgSearch();
		Test_Variables.test.createNode("Search for the created organization");
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgUpdateButton)));                      
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateButton)).click();
		Thread.sleep(1000);
		Test_Variables.test.createNode("Click on update button");
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgUpdatePhoneNo)));   
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdatePhoneNo)).clear();
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdatePhoneNo)).sendKeys("(111) 111-1111"); 
		Thread.sleep(1000);

		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateNextButton)).click(); 
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));

		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateSaveButton)).click();  
		Thread.sleep(1000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));   
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = Test_Variables.lstOrgAlertMessages.get(1) ;
		Thread.sleep(1000);
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Organization updated successfully");
			Test_Variables.results.createNode("Organization updated successfully; an alert message appears 'Organization details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Failed to update the Organization");
			Test_Variables.results.createNode("Failed to update the Organization");
		}
		Thread.sleep(1500);

	}


	@Test (description="Test Case: Verify Updated Organization ",enabled= true, priority= 9) 
	public void SearchUpdateOrganization() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-25: Verify Organization remained updated after updating it", "This test case will verify that updated changes are saved by reopening updated organization");	
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.preconditions.createNode("5. Click on update button next to created Organization; Update Organization popup appears");
		Test_Variables.steps.createNode("1. Update the Organization and click on Save button");
		Test_Variables.steps.createNode("2. Reopen the updated popup to verify that changes made were save or not");
		
		
		Helper.driver.get(Constants.url_organization);
		Test_Functions.OrgSearch();
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgUpdateButton)));                      
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateButton)).click();
		Thread.sleep(2000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgUpdatePhoneNo))); 
//		String actual = Helper.driver.findElement(By.xpath(Test_Elements.orgUpdatePhoneNo)).getAttribute("value");
		Thread.sleep(2000);
//		String expected = "(111) 111-1111";

		try{
	//		Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Organization updation verified successfully");
			Test_Variables.results.createNode("Organization was updated successfully; changes remained saved");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Organization updation failed");
			Test_Variables.results.createNode("Organization was not updated successfully; changes did not remained saved");
		}

		Helper.driver.findElement(By.xpath(Test_Elements.orgPopupCloseButton)).click();
		Thread.sleep(1500);
	}
	
	
	
	@Test (description="Test Case: Organization Site Mandatory Check",enabled= true, priority= 10) 
	public void OrganizationSiteMandatoryCheck() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-26: Verify Organization Site Mandatory check", "This test case will verify that user cannot create organization site with out filling all mandatory fields");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
		Test_Variables.steps.createNode("2. Click on + icon to open create new site window");
		Test_Variables.steps.createNode("3. Leave all fields empty and click on save button");
		
		Helper.driver.get(Constants.url_organization);
		Test_Functions.OrgSearch();

		Helper.driver.findElement(By.cssSelector("svg")).click();    
		Thread.sleep(2000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgNewSiteButton)).click();  
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgSiteSaveButton))); 
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteSaveButton)).click(); 
		Thread.sleep(2000);
		String orgSiteTypeactual = Helper.driver.findElement(By.xpath(Test_Elements.orgSiteTypeValidation)).getText();     
		String orgSiteNameactual = Helper.driver.findElement(By.xpath(Test_Elements.orgSiteNameValidation)).getText();

		try{
			Assert.assertEquals(orgSiteTypeactual, Test_Elements.orgSiteTypeexpected); 
			Assert.assertEquals(orgSiteNameactual, Test_Elements.orgSiteNameexpected); 
			Test_Variables.test.pass("Site Mandatory fields check verified successfully");
			Test_Variables.results.createNode("User was not able to create site without filling all mandatory fields");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			
		}catch(AssertionError e){
			Test_Variables.test.fail("Site Mandatory fields check verification failed");
			Test_Variables.results.createNode("User was able to create site without filling all mandatory fields");
		}
		
	}
	
	
	@Test (description="Exceptional Flow: Site Reset fields", enabled= true, priority= 11) 
	public void SiteResetButton() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-27: Verify Organization Site Reset fields check", "This test case will verify that user can reset fields of create organization site");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
		Test_Variables.steps.createNode("2. Click on + icon to open create new site window");
		Test_Variables.steps.createNode("3. Enter data in fields");
		Test_Variables.steps.createNode("4. Click on reset button");
		
		Thread.sleep(2000);
		Helper.driver.findElement(By.id("SiteNameId")).sendKeys("Lab");
		String orgSiteNameReset = Helper.driver.findElement(By.id("SiteNameId")).getText();
		
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteResetButton)).click();
		Thread.sleep(1500);


		if(orgSiteNameReset.isEmpty() )     {
			Test_Variables.test.pass("Fields reset successfully");   
			Test_Variables.results.createNode("Fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			}

		else {
			Test_Variables.test.fail("Fields reset failed");   
			Test_Variables.results.createNode("Fields failed to reset");
			}
	}
	
	
	@Test (description="Test Case: Create Organization Site",enabled= true, priority= 12) 
	public void CreateOrganizationSite() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-28: Verify Organization Site can be created", "This test case will verify that organization site can be created");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
		Test_Variables.steps.createNode("2. Click on + icon to open create new site window");
		Test_Variables.steps.createNode("3. Enter valid data in fields");
		Test_Variables.steps.createNode("4. Click on save button");
		
		Helper.driver.get(Constants.url_organization);
		Test_Functions.OrgSearch();

		Helper.driver.findElement(By.cssSelector("svg")).click();    
		Thread.sleep(2000);

		Helper.driver.findElement(By.xpath(Test_Elements.orgNewSiteButton)).click();  
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgSiteType))); 
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteType)).click();   
		Thread.sleep(1000);	
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteTypeSelect)).click(); 
		Thread.sleep(1000);	
		Helper.driver.findElement(By.id("SiteNameId")).sendKeys("Lab");    
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteCountry)).click();   
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteCountrySelect)).click();  
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteSaveButton)).click(); 
		Thread.sleep(1000);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = Test_Variables.lstOrgAlertMessages.get(2) ;
		Thread.sleep(1000);
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("New Organization site created successfully");
			Test_Variables.results.createNode("New Organization site created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("New Organization site failed to create");
			Test_Variables.results.createNode("New Organization site failed to create");
		}	
		Thread.sleep(1500);
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteClose)).click();  
		Thread.sleep(3500);
	}


	@Test (description="Test Case: Update Organization Sites ",enabled= true, priority= 13) 
	public void UpdateOrganizationSites() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-29: Verify Organization Site can be updated", "This test case will verify that organization site can be updated");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.preconditions.createNode("5. Click on site button next to created Organization; Organization Site popup appears");
		Test_Variables.preconditions.createNode("6. Click on + icon to open create new site window");
		Test_Variables.preconditions.createNode("7. Create a site");
		Test_Variables.steps.createNode("1. Click on created site to reopen it");
		Test_Variables.steps.createNode("2. Make changes and click on save button");
		
		Thread.sleep(1500); 
		Test_Variables.test.createNode("Click on update button next to created site");
		Helper.driver.findElement(By.cssSelector("svg")).click();   
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgCreatedSite)));
		Helper.driver.findElement(By.xpath(Test_Elements.orgCreatedSite)).click();   
		Thread.sleep(4000);

		Helper.driver.findElement(By.id("streetAddressId")).clear();
		Helper.driver.findElement(By.id("streetAddressId")).sendKeys("26-B");  
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteSaveButton)).click(); 
		Thread.sleep(1500);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = Test_Variables.lstOrgAlertMessages.get(3) ;
		Thread.sleep(1000);
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Organization site updated successfully");
			Test_Variables.results.createNode("Organization site updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Organization site failed to update");
			Test_Variables.results.createNode("Organization site failed to update");
		}	

		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteClose)).click(); 
		Thread.sleep(1500);
	}


	@Test (description="Test Case: Verify Update Organization Sites ",enabled= true, priority= 14) 
	public void VerifyUpdateOrganizationSites() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-30: Verify Organization Site remains updated on reopening", "This test case will verify that updated organization site remain saved");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.preconditions.createNode("5. Click on site button next to created Organization; Organization Site popup appears");
		Test_Variables.preconditions.createNode("6. Click on + icon to open create new site window");
		Test_Variables.preconditions.createNode("7. Create a site");
		Test_Variables.steps.createNode("1. Click on created site to reopen it");
		Test_Variables.steps.createNode("2. Make changes and click on save button");
		Test_Variables.steps.createNode("3. Reopen ithe updated site to verify the cahnges were saved or not");
		
		Thread.sleep(1500); 
		Helper.driver.findElement(By.cssSelector("svg")).click();  
		Thread.sleep(1000);

		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgCreatedSite)).click(); 
		Thread.sleep(1500);

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgStreetAddress)));
		Thread.sleep(1000);
		String addressActual = Helper.driver.findElement(By.xpath(Test_Elements.orgStreetAddress)).getAttribute("value");
		String addressExpected = "26-B";

		try{
			Assert.assertEquals(addressActual, addressExpected); 
			Test_Variables.test.pass("New Organization site updation verified successfully");
			Test_Variables.results.createNode("New Organization site updation verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));

		}catch(AssertionError e){
			Test_Variables.test.fail("New Organization site updation verification failed");
			Test_Variables.results.createNode("New Organization site updation verification failed");
		}	
	}


	@Test (description="Test Case: Delete Organization Sites ",enabled= true, priority= 15) 
	public void DeleteOrganizationSites() throws InterruptedException, IOException {
		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-31: Verify Organization Site can be deleted", "This test case will verify that organization site can be deleted");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.preconditions.createNode("5. Click on site button next to created Organization; Organization Site popup appears");
		Test_Variables.preconditions.createNode("6. Click on + icon to open create new site window");
		Test_Variables.preconditions.createNode("7. Create a site");
		Test_Variables.steps.createNode("1. Click on delete icon next to created site");
		Test_Variables.steps.createNode("2. Confirmation popup appears");
		Test_Variables.steps.createNode("3. Click on yes button");
		
		Thread.sleep(1500); 
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteDeleteButton)).click(); 
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgSiteDeleteConfirm)));
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteDeleteConfirm)).click();  

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = Test_Variables.lstOrgAlertMessages.get(4) ;
		Thread.sleep(1000);
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("New Organization site deleted successfully");
			Test_Variables.results.createNode("New Organization site deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("New Organization site failed to delete");
			Test_Variables.results.createNode("New Organization site failed to delete");
		}	

		Helper.driver.findElement(By.xpath(Test_Elements.orgSiteClose)).click();   
		Thread.sleep(1000);
	}
	
	
	@Test (description="Test Case: InActive Organization",enabled= true, priority= 16) 
	public void InActiveOrganization() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-32: Verify Organization Site can be made inactive", "This test case will verify that organization can be made inactive");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.steps.createNode("1. Click on update icon next to created organization");
		Test_Variables.steps.createNode("2. Click on Inactive toggle button");
		Test_Variables.steps.createNode("3. Click on save button");
		
		Helper.driver.get(Constants.url_organization);
		Test_Functions.OrgSearch();
		Thread.sleep(2000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgUpdateButton)));                      
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateButton)).click();
		Thread.sleep(1000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgUpdateNextButton)));  
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateNextButton)).click();
		Thread.sleep(2000);

		Helper.driver.findElement(By.xpath(Test_Elements.orgInActiveButton)).click();
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.orgUpdateSaveButton)).click();
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox))); 
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = Test_Variables.lstOrgAlertMessages.get(1) ;
		Thread.sleep(1000);
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Organization inactivated successfully");
			Test_Variables.results.createNode("Organization inactivated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Organization failed to inactivated");
			Test_Variables.results.createNode("Organization failed to inactivated");
		}
		Thread.sleep(1500);
	}
	
	
	@Test (description="Test Case: Verify InActive Organization",enabled= true, priority= 17) 
	public void VerifyInActiveOrganization() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-33: Verify inactive Organization Site is not displayed in create user popup", "This test case will verify that inactive organization is not displayed in create new user popup");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.steps.createNode("1. Click on update icon next to created organization");
		Test_Variables.steps.createNode("2. Click on Inactive toggle button and save the organization");
		Test_Variables.steps.createNode("3. Go to User Management and open new user popup");
		Test_Variables.steps.createNode("4. Search for the inactivated organization in Organization dropdown; should not appear");
		
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userCreateButton)));
		Helper.driver.findElement(By.xpath(Test_Elements.userCreateButton)).click();

		
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userFirstName)));
		Helper.driver.findElement(By.xpath(Test_Elements.userFirstName)).sendKeys(Test_Variables.lstUserCreate.get(0));    
		Helper.driver.findElement(By.xpath(Test_Elements.userLastName)).sendKeys(Test_Variables.lstUserCreate.get(1));  
		Helper.driver.findElement(By.xpath(Test_Elements.userPhoneCode)).click();      
		Helper.driver.findElement(By.xpath(Test_Elements.userPhoneCodeSelect)).click();
        Thread.sleep(1000);

		Helper.driver.findElement(By.xpath(Test_Elements.userPhoneNo)).sendKeys(Test_Variables.lstUserCreate.get(2));
		Helper.driver.findElement(By.xpath(Test_Elements.userPopupNextButton1)).click();    
		Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationType)).click();  
		Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationTypeSelect)).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.userOrganization)).click(); 
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationInput)).sendKeys(Test_Variables.lstOrganizationCreate.get(0));

		String actual = Helper.driver.findElement(By.xpath(Test_Elements.userOrganizationInActiveText)).getText();
		String expected = "No items found"; 
        
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Inactivated Organization was not found in dropdown successfully");
			Test_Variables.results.createNode("Inactivated Organization was not found in dropdown successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Inactivated Organization was found in dropdown");
			Test_Variables.results.createNode("Inactivated Organization was found in dropdown");
		}
        Thread.sleep(1000);
		
	}


	@Test (description="Test Case: Delete Organization",enabled= true, priority= 18) 
	public void DeleteOrganization() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-OM-34: Verify Organization can be deleted", "This test case will verify that organization can be deleted");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
		Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
		Test_Variables.steps.createNode("1. Click on delete icon next to created organization");
		Test_Variables.steps.createNode("2. Click on yes from confirmation box");
		
		Helper.driver.get(Constants.url_organization);
		Test_Functions.OrgSearch();
		Test_Variables.test.createNode("Search created org");
		Thread.sleep(1500);
		List<WebElement> x = Helper.driver.findElements(By.xpath(Test_Elements.orgDeleteButton));

		if (x.size() > 0)
		{
		    x.get(0).click();
		}
				
		Thread.sleep(1500);
		Helper.driver.findElement(By.xpath(Test_Elements.orgDeleteConfirm)).click();  

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText();
		String expected = Test_Variables.lstOrgAlertMessages.get(5) ;
		Thread.sleep(1000);
		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("Organization deleted successfully");
			Test_Variables.results.createNode("Organization deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Organization failed to delete");
			Test_Variables.results.createNode("Organization failed to delete");
		}	
	}
	
	@AfterMethod
	public void saveResult(ITestResult result) throws IOException {
		Helper.saveResult(result, Constants.OrgManagementReportPath);
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}

}
