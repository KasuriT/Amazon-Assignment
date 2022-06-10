package Test.Ancera.Administration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
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
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Models.OrgModel;
import Models.ReportFilters;
import Models.UserModel;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;
import Test.Ancera.Reports.SalmonellaLog;

public class OrganizationManagement{

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Management"+Test_Variables.date+".html");	//	Test_Variables.spark.config().setReportName("Organization Management Test Report"); 
		Test_Variables.spark.config().setReportName("Organization Management Test Report"); 
		Helper.config();
		ConfigureLogin.login();
	}
	
	@Test
	public void Navigate() throws InterruptedException, IOException {
	Test_Functions.NavigateToScreen(Constants.url_organization, "Organization Management", Constants.OrgManagementReportPath, Test_Elements.orgTitle);
	
	Thread.sleep(5000);
	
	Helper.driver.findElement(By.id("orgnName_show-filter")).click();
	System.out.println("hello");
	Thread.sleep(3000);
	Helper.driver.findElement(By.cssSelector("#table-orgn-log th:nth-child(1) li:nth-child(3) label")).click();
	Helper.driver.findElement(By.cssSelector("#table-orgn-log th:nth-child(1) .filter-popup__footer--view-all")).click();
	Thread.sleep(3000);
	
	
	System.out.println("1. Checkbox: "+Helper.driver.findElement(By.cssSelector("#table-orgn-log th:nth-child(1) li:nth-child(3) .ng-touched")).isSelected());
	System.out.println("2. Checkbox: "+Helper.driver.findElement(By.cssSelector("#table-orgn-log th:nth-child(1) li:nth-child(4) .ng-touched")).isSelected());

	
	
	}


	@Test (enabled= true, priority= 2) 
	public void OpenClosePopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-02: Verify user can open and close Create New User Popup", "This test case will verify that user is able to open and close create new user popup");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar; Click on Administration and select Organization Management");	
			Test_Variables.steps.createNode("1. Click on Create New button");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(Test_Elements.orgCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.cssSelector(".pop-head")).getText(), "Create Organization"); 
			Test_Variables.test.pass("Organization popup window opened successfully");
			Test_Variables.results.createNode("Organization New User popup opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);

			Helper.driver.findElement(Test_Elements.popupCloseButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

			Assert.assertEquals(Helper.driver.findElements(Test_Elements.popupNextButton).size(), 0); 
			Test_Variables.test.pass("Organization popup window closed successfully");
			Test_Variables.results.createNode("Create New Organization popup closed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);

		}catch(AssertionError er){
			Test_Variables.test.fail("Organization popup window did not open or closed successfully");
			Test_Variables.results.createNode("Organization popup window did not open or closed successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Organization popup window did not open or closed successfully");
			Test_Variables.results.createNode("Organization popup window did not open or closed successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}

	
	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 3) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_organization);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

		Helper.driver.findElement(Test_Elements.orgCreateButton).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);

		for (OrgModel objModel : Test_Variables.lstOrgMandatoryCheck) {

			try {
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
				Helper.driver.findElement(Test_Elements.popupResetButton).click();
				SoftAssert softAssert = new SoftAssert();
				
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				Helper.driver.findElement(Test_Elements.orgNameInput).clear();
				Helper.driver.findElement(Test_Elements.orgNameInput).sendKeys(objModel.orgName);
				Thread.sleep(1000);
	
				Helper.driver.findElement(Test_Elements.orgEmailInput).clear();
				Helper.driver.findElement(Test_Elements.orgEmailInput).sendKeys(objModel.orgEmail);
				Thread.sleep(1000);

				if (objModel.orgType)
				{
					Helper.driver.findElement(Test_Elements.orgTypeDropDownExpand).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.orgTypeInput).sendKeys(Keys.ENTER);
				}

				if (objModel.orgPhoneCode)
				{
				//	ClickElement.clickById(Helper.driver, "phoneCodeId");
				//	Helper.driver.findElement(Test_Elements.orgPhoneCodeInput).click();
				//	Thread.sleep(1000);
				//	Helper.driver.findElement(Test_Elements.orgPhoneCodeInput).sendKeys("+92");
				//	Helper.driver.findElement(Test_Elements.orgPhoneCodeInput).sendKeys(Keys.ENTER);
				//	Helper.driver.findElement(Test_Elements.orgPhoneNumberInput).clear();
				//	Helper.driver.findElement(Test_Elements.orgPhoneNumberInput).sendKeys(objModel.orgPhoneNo);	
				}
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-next")));
				Helper.driver.findElement(Test_Elements.popupNextButton).click(); 
				Thread.sleep(1000);

				if ( objModel.orgType == false && objModel.chkMandatoryFieldsS1)
				{
					softAssert.assertEquals(Helper.driver.findElements(Test_Elements.orgTypeError).size(), 1); 
				}	

				if ( objModel.orgName.equals("") && objModel.chkMandatoryFieldsS1)
				{
					softAssert.assertEquals(Helper.driver.findElements(Test_Elements.orgNameError).size(), 1); 
				}				

				if ( objModel.orgPhoneNo.equals("") && objModel.orgPhoneCode == true && objModel.chkMandatoryFieldsS1)
				{
					softAssert.assertEquals(Helper.driver.findElements(Test_Elements.orgPhoneNumberError).size(), 1); 
				}

				if (Helper.driver.findElement(Test_Elements.orgMaxUsersInput).isDisplayed()) {

					Helper.driver.findElement(Test_Elements.orgMaxUsersInput).clear();
					Helper.driver.findElement(Test_Elements.orgMaxUsersInput).sendKeys(objModel.maxUsers);

					Helper.driver.findElement(Test_Elements.popupSaveButton).click(); 


					if ( objModel.maxUsers.equals("") && objModel.chkMandatoryFieldsS2)
					{
						softAssert.assertEquals(Helper.driver.findElements(Test_Elements.orgMaxUsersError).size(), 1); 
					}

					Test_Variables.test.pass(objModel.passScenario);
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
				}
				continue;
			}catch(AssertionError er){
				Test_Variables.test.fail("User navigation failed");
				Test_Variables.results.createNode("User did not navigate to User Management Screen");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
			}	
			catch(Exception ex){
				Test_Variables.test.fail("User navigation failed");
				Test_Variables.results.createNode("User did not navigate to User Management Screen");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
			}
		}
	}

	

	@Test (description="Exceptional Flow: Reset fields", enabled= true, priority= 4, dependsOnMethods = {"MandatoryFieldCheck"}) 
	public void ResetButton() throws InterruptedException, IOException {
		try {
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

			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

			WebElement usrFirstName = Helper.driver.findElement(Test_Elements.orgNameInput);
			usrFirstName.sendKeys("Test Org");
			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			String firstNameReset = usrFirstName.getAttribute("value");

			Assert.assertEquals(firstNameReset, "");
			Test_Variables.test.pass("Fields reset successfully");
			Test_Variables.results.createNode("Fields reset successfully");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}		
		catch(AssertionError er) {
			Test_Variables.test.fail("Fields failed to reset");  
			Test_Variables.results.createNode("Fields failed to reset"); 
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}
		catch(Exception ex){
			Test_Variables.test.fail("Fields failed to reset");  
			Test_Variables.results.createNode("Fields failed to reset"); 
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
		Helper.driver.findElement(Test_Elements.popupCloseButton).click();
	}



	@Test (description="Test Case: Test Invalid Email",enabled= true, priority= 5) 
	public void testInvalidEmail() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-19: Verify user cannot create Organization with invalid email", "This test case will verify that user cannot create new organization with invalid email");
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			Test_Variables.preconditions.createNode("4. Click on create new button; popup appears");
			Test_Variables.steps.createNode("1. Enter invalid email");
			Test_Variables.steps.createNode("2. Click on next button; should display validation message");

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgCreateButton).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

			Helper.driver.findElement(Test_Elements.orgEmailInput).sendKeys("invalid@email");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupNextButton).click();

			Assert.assertEquals(Helper.driver.findElements(Test_Elements.orgEmailError).size(), 1); 
			Test_Variables.test.pass("User cannot create organization with invalid email; displays validation message 'Invalid email'");
			Test_Variables.results.createNode("User cannot create organization with invalid email; displays validation message 'Invalid email'");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("User was able to create organization with invalid email");
			Test_Variables.results.createNode("User was able to create organization with invalid email");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User was able to create organization with invalid email");
			Test_Variables.results.createNode("User was able to create organization with invalid email");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}	
		Helper.driver.findElement(Test_Elements.popupCloseButton).click();
		Thread.sleep(1000);
	}
	

	@Test (description="Test Case: Create New Organization",enabled= true, priority= 6, dependsOnMethods = {"testInvalidEmail"}) 
	public void CreateOrganization() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-20: Verify user can create New Organizationn", "This test case will verify that user can create new organization");
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			Test_Variables.preconditions.createNode("4. Click on create new button; popup appears");
			Test_Variables.steps.createNode("1. Enter valid data in all fields");
			Test_Variables.steps.createNode("2. Click on save button");
			
			String recordsCountBefore = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
			Helper.driver.findElement(Test_Elements.orgCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgTypeDropDownExpand).click();
			Thread.sleep(750);
			Helper.driver.findElement(Test_Elements.orgTypeInput).sendKeys(Keys.ENTER);
			Helper.driver.findElement(Test_Elements.orgNameInput).clear();
			Helper.driver.findElement(Test_Elements.orgNameInput).sendKeys(Test_Variables.lstOrganizationCreate.get(0));
			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgMaxUsersInput).sendKeys("10");
			
			Helper.driver.findElement(Test_Elements.orgSystemRolesExpand).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[2]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div[1]/div/label")).click();
			Helper.driver.findElement(Test_Elements.orgSystemRolesExpand).click();
			
			Helper.driver.findElement(Test_Elements.orgReportRolesExpand).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[3]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div[1]/div/label")).click();
			Helper.driver.findElement(Test_Elements.orgReportRolesExpand).click();
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(0)); 
			String recordsCountAfter = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountAfter, recordsCountBefore);	
			softAssert.assertAll();
			Test_Variables.test.pass("New Organization created successfully");
			Test_Variables.results.createNode("New Organization created successfully; displayed alert message 'New organization created.'");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("New Organization creation failed");
			Test_Variables.results.createNode("New Organization creation failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("New Organization creation failed");
			Test_Variables.results.createNode("New Organization creation failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}	
	}


	@Test (description="Test Case: Create New Organization",enabled= true, priority= 7) 
	public void VerifyCreatedOrganization() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-21: Verify created Organization from create/edit user popup", "This test case will verify created Organization from create/edit user popup");
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			Test_Variables.preconditions.createNode("4. Click on create new button; popup appears");
			Test_Variables.preconditions.createNode("5. Enter valid data in all fields");
			Test_Variables.preconditions.createNode("6. Click on save button");
			Test_Variables.steps.createNode("1. Go to user screen and verify created organization is displayed in dropdown");
			
			Test_Functions.searchOrg();
			Assert.assertEquals(Helper.driver.findElements(By.xpath("//*[contains(text(),'"+Test_Variables.lstOrganizationCreate.get(0)+"')]")).size(), 1);
			Test_Variables.test.pass("Organization displayed in user dropdown successfully");
			Test_Variables.results.createNode("Organization displayed in user dropdown successfully");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Organization did not displayed in user dropdown");
			Test_Variables.results.createNode("Organization did not displayed in user dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Organization did not displayed in user dropdown");
			Test_Variables.results.createNode("Organization did not displayed in user dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}	
	}

	@Test (description="Test Case: Update New Organization ",enabled= true, priority= 8) 
	public void UpdateOrganization() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-22: Verify user can update Created Organization", "This test case will verify that user can update created organization");	
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
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Test_Variables.test.createNode("Search for the created organization");  
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("edit-orgn-"+i)).click();
					Test_Variables.test.createNode("Click on update button");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}	
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(Helper.driver.findElement(By.id("nmeOrgID")).getAttribute("value"), "", "Organization ID should not be empty in edit organization popup");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgEmailInput).sendKeys("testorg@anc.com");	
			Thread.sleep(500);
			Helper.driver.findElement(Test_Elements.popupNextButton).click(); 
			Thread.sleep(500);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();  
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));  
			Thread.sleep(1000);

			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(1)); 
			softAssert.assertAll();;
			Test_Variables.test.pass("Organization updated successfully");
			Test_Variables.results.createNode("Organization updated successfully; an alert message appears 'Organization details updated.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Failed to update the Organization");
			Test_Variables.results.createNode("Failed to update the Organization");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Failed to update the Organization");
			Test_Variables.results.createNode("Failed to update the Organization");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Updated Organization ",enabled= true, priority= 9) 
	public void VerifyUpdateOrganization() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-23: Verify Organization remained updated after updating it", "This test case will verify that updated changes are saved by reopening updated organization");	
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
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
						Helper.driver.findElement(By.id("edit-orgn-"+i)).click();
					Test_Variables.test.createNode("Click on update button");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}

			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.orgEmailInput).getAttribute("value"), "testorg@anc.com"); 
			Helper.driver.findElement(Test_Elements.popupCloseButton).click();
			Test_Variables.test.pass("Organization updation verified successfully");
			Test_Variables.results.createNode("Organization was updated successfully; changes remained saved");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Organization updation failed");
			Test_Variables.results.createNode("Organization updation failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Organization was not updated successfully; changes did not remained saved");
			Test_Variables.results.createNode("Organization was not updated successfully; changes did not remained saved");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Organization Site Check",enabled= true, priority= 10) 
	public void OrganizationSitesHierarchyCheck() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
			Test_Variables.steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
			Test_Variables.steps.createNode("2. Verify Site Type is Organization");

			SoftAssert softAssert = new SoftAssert();
			
			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
			//	if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals("TestOrg-765")) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}

			Thread.sleep(1000);	
			Helper.driver.findElement(Test_Elements.orgParentSiteClick).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInput).click();
			String orgType = Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(orgType, "Organization");
			
			Test_Variables.steps.createNode("3. Click on + icon to create new site and verify Site Type is Region");
			Helper.driver.findElement(Test_Elements.orgAddSite1).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInput).click();
			String regionType = Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).getText();	
			softAssert.assertEquals(regionType, "Region");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Region");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			Helper.driver.findElement(Test_Elements.orgAddSite2).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInput).click();
			String subregionType = Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Sub Region");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			Helper.driver.findElement(Test_Elements.orgAddSite3).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			String ele1 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
			String ele2 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
			String ele3 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();

			softAssert.assertEquals(ele1, "Complex");
			softAssert.assertEquals(ele2, "Processing Plant");			
			softAssert.assertEquals(ele3, "Testing Lab");

			Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Complex Site");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			Helper.driver.findElement(Test_Elements.orgAddSite4).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String farmType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Farm");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("11. Click on + icon to create new site and verify Site Type as House");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String HouseType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test House");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Helper.driver.findElement(Test_Elements.orgAddSite3).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(2)")).click();
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Processing Plant Site");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1500);
			Test_Variables.steps.createNode("13. Create Processing PLant Site");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("14. Click on + icon to create new site and verify Site Type as Rehang, BIrd Wash, Bird Rinse, Chiller, Wing Dip");
			Helper.driver.findElement(Test_Elements.orgAddSite6).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			String elem1 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
			String elem2 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
			String elem3 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();
			String elem4 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(4)")).getText();
			String elem5 = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(5)")).getText();

			softAssert.assertEquals(elem1, "Rehang");
			softAssert.assertEquals(elem2, "Bird Wash");			
			softAssert.assertEquals(elem3, "Bird Rinse");
			softAssert.assertEquals(elem4, "Chiller");			
			softAssert.assertEquals(elem5, "Wing Dip");	

			Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(4)")).click();
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Chiller Site");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("15. Verify Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Helper.driver.findElement(Test_Elements.orgAddSite3).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(3)")).click();
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Testing Lab Site");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("16. Create Testing Lab Site");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("17. Click on + icon to create new site and verify Site Type as Lab-Sub Division");
			Helper.driver.findElement(Test_Elements.orgAddSite7).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String subDivisionType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(subDivisionType, "Lab-Sub Division");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Sub Division Site");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1500);
			Test_Variables.steps.createNode("18. Verify Lab Sub Division Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");
			
			Helper.driver.findElement(Test_Elements.orgDeleteSite1).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.confirmationYes).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);	
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Site details deleted successfully.");
			softAssert.assertAll();
			Test_Variables.test.pass("Site heirarchy verified successfully");
			Test_Variables.results.createNode("Site heirarchy verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);	
		}catch(AssertionError er){
			Test_Variables.test.fail("Site heirarchy failed to verify successfully");
			Test_Variables.results.createNode("Site heirarchy failed to verify successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Site heirarchy failed to verify successfully");
			Test_Variables.results.createNode("Site heirarchy failed to verify successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}	

	@Test (description="Test Case: Organization Site Mandatory Check",enabled= true, priority= 11) 
	public void OrganizationSiteMandatoryCheck() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-39: Verify Organization Site mandatory checks", "This test case will verify mandotory checks on organization sites");
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
			Test_Variables.steps.createNode("4. Verify valid data is populated on entering valid street address");
			Test_Variables.steps.createNode("5. Click on save button");	

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}
			
			Helper.driver.findElement(Test_Elements.orgAddSite1).click();  
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Helper.driver.findElement(Test_Elements.popupSaveButton).click(); 
			Thread.sleep(500);    

			Assert.assertEquals(Helper.driver.findElements(Test_Elements.orgSiteNameError).size(), 1); 
			Test_Variables.test.pass("Site Mandatory fields check verified successfully");
			Test_Variables.results.createNode("User was not able to create site without filling all mandatory fields");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Site Mandatory fields check verification failed");
			Test_Variables.results.createNode("Site Mandatory fields check verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Site Mandatory fields check verification failed");
			Test_Variables.results.createNode("Site Mandatory fields check verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Site Reset fields", enabled= true, priority= 12) 
	public void SiteResetButton() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-40: Verify Organization Site Reset fields check", "This test case will verify that user can reset fields of create organization site");
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

			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Lab");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Thread.sleep(1000);
		
			String orgSiteNameReset = Helper.driver.findElement(Test_Elements.orgSiteNameInput).getAttribute("value");
			Assert.assertEquals(orgSiteNameReset, "");
			Test_Variables.test.pass("Fields reset successfully");   
			Test_Variables.results.createNode("Fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}
		catch(AssertionError er){
			Test_Variables.test.fail("Fields reset failed");
			Test_Variables.results.createNode("Fields reset failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Fields reset failed");
			Test_Variables.results.createNode("Fields reset failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Create Organization Site",enabled= true, priority= 13) 
	public void CreateOrganizationSite() throws InterruptedException, IOException {			
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-41: Verify Organization Site can be created", "This test case will verify that user can cretae new site");
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
			
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();   
			Thread.sleep(500);	
			Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).click();  
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Region");    
			Thread.sleep(500);
			SoftAssert softAssert = new SoftAssert();
			WebElement stAddress = Helper.driver.findElement(Test_Elements.orgSiteAddressInput);
			stAddress.clear(); 
			stAddress.sendKeys("Lahore Pakistan"); 
			Thread.sleep(2000);
			stAddress.sendKeys(Keys.DOWN); stAddress.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(".confirmation-message label")).getText(), "The specified address is outside USA. Please enter a valid US address.");
			Helper.driver.findElement(Test_Elements.popupOKButton).click();
			Thread.sleep(1000);
			
			Helper.driver.findElement(Test_Elements.orgSiteLongitudeInput).sendKeys("-65.32");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteLongitudeInput).getAttribute("value"),"-65.32", "Longitude did not filled");
			Helper.driver.findElement(Test_Elements.orgSiteLatitudeInput).sendKeys("-70.32");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteLatitudeInput).getAttribute("value"),"-70.32", "Latitude did not filled");
			
			stAddress.clear();
			stAddress.sendKeys("new york"); 
			Thread.sleep(1000);
			stAddress.sendKeys(Keys.DOWN); stAddress.sendKeys(Keys.ENTER);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(6000);
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).click();  
			Thread.sleep(2000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteCountryInput).getText(), "Country\nUnited States");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteStateInput).getText(), "State\nNew York");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteCityInput).getText(), "City\nNew York");
	//		softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteZipCodeInput).getText(), "28092");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteLatitudeInput).getAttribute("value"),"40.7127753", "Latitude did not autofilled");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteLongitudeInput).getAttribute("value"),"-74.0059728", "Longitude did not autofilled");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupSaveButton).click(); 
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(Test_Elements.alertMessage).getText();
			String expected = Test_Variables.lstOrgAlertMessages.get(2) ;
			Helper.driver.findElement(Test_Elements.alertMessageClose).click();

			softAssert.assertEquals(actual, expected);
			softAssert.assertAll();
			Test_Variables.test.pass("New Organization site created successfully");
			Test_Variables.results.createNode("New Organization site created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("New Organization site failed to create");
			Test_Variables.results.createNode("New Organization site failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("New Organization site failed to create");
			Test_Variables.results.createNode("New Organization site failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Update Organization Sites ",enabled= true, priority= 14) 
	public void UpdateOrganizationSites() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-42: Verify Organization Site can be updated", "This test case will verify that organization site can be updated");
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
			SoftAssert softAssert = new SoftAssert();
			Test_Variables.test.createNode("Click on update button next to created site");
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSite1Click).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(8000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).clear();
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Region Updated");  
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteCountryInput).getText(), "Country\nUnited States");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteStateInput).getText(), "State\nNew York");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteCityInput).getText(), "City\nNew York");
	//		softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteZipCodeInput).getText(), "28092");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteLatitudeInput).getAttribute("value"),"40.712775", "Latitude did not autofilled");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteLongitudeInput).getAttribute("value"),"-74.005973", "Longitude did not autofilled");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(3)); 
			softAssert.assertAll();
			Test_Variables.test.pass("Organization site updated successfully");
			Test_Variables.results.createNode("Organization site updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Organization site failed to update");
			Test_Variables.results.createNode("Organization site failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Organization site failed to update");
			Test_Variables.results.createNode("Organization site failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
		Helper.driver.findElement(Test_Elements.popupCloseButton).click(); 
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Verify Update Organization Sites ",enabled= true, priority= 15) 
	public void VerifyUpdateOrganizationSites() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-43: Verify Organization Site remains updated on reopening", "This test case will verify that updated organization site remain saved");
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

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}
			Thread.sleep(3000);
			Helper.driver.findElement(Test_Elements.orgSite1Click).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(8000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.orgSiteNameInput).getAttribute("value"), "Region Updated"); 
			Test_Variables.test.pass("New Organization site updation verified successfully");
			Test_Variables.results.createNode("New Organization site updation verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("New Organization site updation verification failed");
			Test_Variables.results.createNode("New Organization site updation verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("New Organization site updation verification failed");
			Test_Variables.results.createNode("New Organization site updation verification failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Delete Organization Sites ",enabled= true, priority= 16) 
	public void DeleteOrganizationSites() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-44: Verify Organization Site can be deleted", "This test case will verify that organization site can be deleted");
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

			Helper.driver.findElement(Test_Elements.orgSite1Delete).click(); 
			Thread.sleep(750);
			Helper.driver.findElement(Test_Elements.confirmationYes).click();  
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstOrgAlertMessages.get(4)); 
			Test_Variables.test.pass("New Organization site deleted successfully");
			Test_Variables.results.createNode("New Organization site deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("New Organization site failed to delete");
			Test_Variables.results.createNode("New Organization site failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("New Organization site failed to delete");
			Test_Variables.results.createNode("New Organization site failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
		Helper.driver.findElement(Test_Elements.popupCloseButton).click();   
	}
	
	
	@Test (enabled= true, priority = 17) 
	public void BulkUploadEmpty() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-45: Verify user is not able to upload empty bulk site file", "This testcase will verify user is not able to upload empty bulk site file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Navigate to Organization Management screen");
			Test_Variables.preconditions.createNode("4. Create a new Organization");
			Test_Variables.steps.createNode("1. Click on edit sites icon and upload empty bulk file");

			Helper.driver.get(Constants.url_organization);;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}

			Helper.driver.findElement(By.id("file-input")).sendKeys(System.getProperty("user.dir")+"/BulkSiteUpload/"+OrgModel.BulkSitefileNameEmpty);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Uploaded file contains no rows."); 
			Test_Variables.test.pass("User was not able to upload empty file successfully");
			Test_Variables.results.createNode("User was not able to upload empty file successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User was able to upload empty file");
			Test_Variables.results.createNode("User was able to upload empty file");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User was able to upload empty file");
			Test_Variables.results.createNode("User was able to upload empty file");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}
	

	@Test (enabled= true, priority = 18) 
	public void BulkUpload() throws InterruptedException, IOException {

		Test_Variables.lstOrgBulkSiteUpload = OrgModel.BulkSiteFillData();

		for (OrgModel objModel : Test_Variables.lstOrgBulkSiteUpload) { 
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.testCaseTitle, objModel.testCaseDesc);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Navigate to Organization Management screen");
				Test_Variables.preconditions.createNode("4. Create a new Organization");
				Test_Variables.steps.createNode("1. Click on edit sites icon and upload bulk file");
				
				Helper.driver.get(Constants.url_organization);;
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				SoftAssert softAssert = new SoftAssert();

				for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				//	if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals("Test Organization2233")) {
					if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
						Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
						break;
					}
				}
				
				Helper.driver.findElement(By.cssSelector("li:nth-child(1) div span")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				Helper.driver.findElement(Test_Elements.orgSiteIDField).click();
				Thread.sleep(4000);
				String SiteID = Helper.driver.findElement(Test_Elements.orgSiteIDField).getAttribute("value");
				
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						@SuppressWarnings("unused")
						int chkCounter = 1;

						Row row;
						Cell cell;
						
						FileInputStream fsIP= new FileInputStream(new File("./BulkSiteUpload/"+OrgModel.BulkSitefileName));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						for (int i = 0; i < objFilter.LstColumnID.size() && i < 100; i++) {

							if (objFilter.LstRowID.get(i) == 1 && objModel.getParentSiteID == true) {
								row = worksheet.getRow(1);
								cell = row.createCell(0);
								cell.setCellValue(SiteID);
							}

							if (objFilter.LstRowID.get(i) == 2 && objModel.getParentSiteID == true) {
								row = worksheet.getRow(2);
								cell = row.createCell(0);
								cell.setCellValue(SiteID);
							}
							
							if (objFilter.LstRowID.get(i) == 3 && objModel.getParentSiteID == true) {
								row = worksheet.getRow(3);
								cell = row.createCell(0);
								cell.setCellValue(SiteID);
							}
							
							row = worksheet.getRow(objFilter.LstRowID.get(i));
							cell = row.createCell(objFilter.LstColumnID.get(i));
							cell.setCellValue(objFilter.LstColumnValues.get(i));
							
							chkCounter++;
						}

						FileOutputStream output_file =new FileOutputStream(new File("./BulkSiteUpload/"+OrgModel.BulkSitefileName));
						wb.write(output_file);
						output_file.close(); 

						Helper.driver.findElement(By.id("file-input")).sendKeys(System.getProperty("user.dir")+"/BulkSiteUpload/"+OrgModel.BulkSitefileName);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage)); 

						softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), objModel.AlertMessage); 
						
						if (objModel.ErrorCase) {
						Helper.driver.findElement(By.id("ErrorBtn")).click();
						Thread.sleep(2000);
							
						WebElement ele = Helper.driver.findElement(By.cssSelector(".tooltipBlock"));
						Actions action = new Actions(Helper.driver);
						action.moveToElement(ele).perform();
						Thread.sleep(1000);			
						String tooltipText = Helper.driver.findElement(By.cssSelector(".tooltip-inner")).getText();
						softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}
						
						if (!objModel.ErrorCase) {
							Test_Variables.steps.createNode("2. Verify new tag next to sites on uploading valid bulk file");
							Thread.sleep(3000);
							softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector(".new-site-design")).size(), 0, "Sites uploaded through bulk are not showing up in list with new tag");
						}
							
						softAssert.assertAll();
						Test_Variables.test.pass(objModel.passStep);
						Test_Variables.results.createNode(objModel.passStep);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objModel.failStep);
						Test_Variables.results.createNode(objModel.failStep);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objModel.failStep);
						Test_Variables.results.createNode(objModel.failStep);
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Assign Agreement to Organization",enabled= true, priority= 19) 
	public void AssignAgreement() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-58: Verify Organization can be assigned Agreement", "This test case will verify that organization can be assigned Agreement");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
			Test_Variables.steps.createNode("1. Click on update icon next to created organization");
			Test_Variables.steps.createNode("2. Assign an Agreement");
			Test_Variables.steps.createNode("3. Click on save button");

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("edit-orgn-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}
			
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgAgreementDropDownExpand).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgAgreementDropDownSelect).click();
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage)); 
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(1)); 
			Test_Variables.test.pass("Organization was assigned agreeement successfully");
			Test_Variables.results.createNode("Organization was assigned agreeement successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Organization failed to assigned agreeement");
			Test_Variables.results.createNode("Organization failed to assigned agreeement");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Organization failed to assigned agreeement");
			Test_Variables.results.createNode("Organization failed to assigned agreeement");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}

	@Test (description="Test Case: InActive Organization",enabled= true, priority= 20) 
	public void InActiveOrganization() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-59: Verify Organization Site can be made inactive", "This test case will verify that organization can be made inactive");
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
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("edit-orgn-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}
			
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Thread.sleep(1000);

			ClickElement.clickById(Helper.driver, "yes_radio");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage)); 
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(1)); 
			Test_Variables.test.pass("Organization inactivated successfully");
			Test_Variables.results.createNode("Organization inactivated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Organization failed to inactivated");
			Test_Variables.results.createNode("Organization failed to inactivated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Organization failed to inactivated");
			Test_Variables.results.createNode("Organization failed to inactivated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify InActive Organization",enabled= true, priority= 21) 
	public void VerifyInActiveOrganization() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-60: Verify inactive Organization Site is not displayed in create user popup", "This test case will verify that inactive organization is not displayed in create new user popup");
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

			Test_Functions.searchOrg();
			Assert.assertTrue(Helper.driver.findElement(By.cssSelector(".ng-option-disabled")).isDisplayed()); 
			Test_Variables.test.pass("Inactivated Organization was not found in dropdown successfully");
			Test_Variables.results.createNode("Inactivated Organization was not found in dropdown successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Inactivated Organization was found in dropdown");
			Test_Variables.results.createNode("Inactivated Organization was found in dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Inactivated Organization was found in dropdown");
			Test_Variables.results.createNode("Inactivated Organization was found in dropdown");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}

	
	@Test (description="Test Case: Delete Organization",enabled= true, priority= 22) 
	public void DeleteOrganization() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-61: Verify Organization can be deleted and verify it from table and user dropdown as well", "This test case will verify that organization can be deleted");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			Test_Variables.preconditions.createNode("4. Click on create new button and create a new Organization");
			Test_Variables.steps.createNode("1. Click on delete icon next to created organization");
			Test_Variables.steps.createNode("2. Click on yes from confirmation box");
			Test_Variables.steps.createNode("3. Verify the organization is deleted from table");
			Test_Variables.steps.createNode("4. Go to user screen and verify it is deleted from user dropdown");

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			String recordsCountBefore = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(Test_Variables.lstOrganizationCreate.get(0))) {
					Helper.driver.findElement(By.id("delete-orgn-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}
			
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.confirmationYes).click();  
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(5)); 
			Thread.sleep(3500);
			String recordsCountAfter = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter);
			
			Test_Functions.searchOrg();
			softAssert.assertTrue(Helper.driver.findElement(By.cssSelector(".ng-option-disabled")).isDisplayed()); 
			softAssert.assertAll();
			Test_Variables.test.pass("Organization deleted and verified successfully");
			Test_Variables.results.createNode("Organization deleted and verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Organization failed to delete");
			Test_Variables.results.createNode("Organization failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Organization failed to delete");
			Test_Variables.results.createNode("Organization failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}

	
	
	
	
	@Test (description="Test Case: Filter Test",enabled= false, priority = 23) 
	public void TestFilterPractice(ArrayList<OrgModel> a1, String b1, String c1, String d1, ArrayList<OrgModel> e1) throws InterruptedException, IOException {
		
	

	
	//	Test_Variables.lstOrgSearch;
		Helper.driver.get(d1);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

		a1 = e1;
		String recordBefore = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(); 
		

		for (OrgModel objModel : Test_Variables.lstOrgSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Organization Management; Organization Management screen opens");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Thread.sleep(500);
							WebElement filter_scroll = Helper.driver.findElement(By.id(Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
							Thread.sleep(800);	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

							Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");				
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Thread.sleep(800);						
							if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
								Assert.assertTrue(true, "No records available to test filter");
								Test_Variables.test.skip("No records available to test filter");
								Helper.saveResultNew(ITestResult.SKIP, Constants.OrgManagementReportPath, null);
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
							}

							else {
								for (int j = 0; j < objFilter.LstFilterValues.size(); j++) {
									Test_Variables.steps.createNode("2. Select the checkbox");
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
									WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 		
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label")).click();
								}

								Test_Variables.steps.createNode("3. Click on apply filter button");	
								ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+""+Test_Elements.ApplyFilter);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
								Thread.sleep(800);
								String recordAfter = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();		

								Assert.assertNotEquals(recordBefore, recordAfter);
								Test_Variables.test.pass("Filter applied successfully");
								Test_Variables.results.createNode("Filter applied successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(c1, Constants.OrgManagementReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, b1, null);
							}
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Organization Management; Organization Management reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify filter is applied and relevant results are displayed in table");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

							if (Helper.driver.findElements(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" .active-filter")).size() != 0) {			
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
								Thread.sleep(500);
								if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
									Test_Variables.test.skip("No records available to test filter");
									Helper.saveResultNew(ITestResult.SKIP, Constants.OrgManagementReportPath, null);
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.ShowFilter)).click();
								}
								else {	
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" .divider")).size(), 1);
									Test_Variables.test.pass("Applied checkbox bubbled to top successfully");
									Test_Variables.results.createNode("Applied checkbox bubbled to top successfully");
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
									Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ClearFilter)).click();
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
									Thread.sleep(1000);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.ShowFilter)).click();
								}
							}
							else {
								Test_Variables.results.createNode("Test case skipped because filter was not applied");
								Test_Variables.test.skip("Test case skipped because filter was not applied");
								Helper.saveResultNew(ITestResult.SKIP, Constants.OrgManagementReportPath, null);
							}			
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}

	
	@Test (description="Test Case: Filter Test",enabled= false, priority = 23) 
	public void TestFilter() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_organization);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

		Test_Variables.lstOrgSearch = OrgModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(); 
		for (OrgModel objModel : Test_Variables.lstOrgSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Organization Management; Organization Management screen opens");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Thread.sleep(500);
							WebElement filter_scroll = Helper.driver.findElement(By.id(Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
							Thread.sleep(800);	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

							Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");				
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Thread.sleep(800);						
							if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
								Assert.assertTrue(true, "No records available to test filter");
								Test_Variables.test.skip("No records available to test filter");
								Helper.saveResultNew(ITestResult.SKIP, Constants.OrgManagementReportPath, null);
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
							}

							else {
								for (int j = 0; j < objFilter.LstFilterValues.size(); j++) {
									Test_Variables.steps.createNode("2. Select the checkbox");
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
									WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 		
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label")).click();
								}

								Test_Variables.steps.createNode("3. Click on apply filter button");	
								ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+""+Test_Elements.ApplyFilter);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
								Thread.sleep(800);
								String recordAfter = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();		

								Assert.assertNotEquals(recordBefore, recordAfter);
								Test_Variables.test.pass("Filter applied successfully");
								Test_Variables.results.createNode("Filter applied successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
							}
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Organization Management; Organization Management reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify filter is applied and relevant results are displayed in table");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

							if (Helper.driver.findElements(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" .active-filter")).size() != 0) {			
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ShowFilter)).click();	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
								Thread.sleep(500);
								if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
									Test_Variables.test.skip("No records available to test filter");
									Helper.saveResultNew(ITestResult.SKIP, Constants.OrgManagementReportPath, null);
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.ShowFilter)).click();
								}
								else {	
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.SortFilter+""+objFilter.LstFilterXpath.get(i)+" .divider")).size(), 1);
									Test_Variables.test.pass("Applied checkbox bubbled to top successfully");
									Test_Variables.results.createNode("Applied checkbox bubbled to top successfully");
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
									Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.ClearFilter)).click();
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
									Thread.sleep(1000);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.ShowFilter)).click();
								}
							}
							else {
								Test_Variables.results.createNode("Test case skipped because filter was not applied");
								Test_Variables.test.skip("Test case skipped because filter was not applied");
								Helper.saveResultNew(ITestResult.SKIP, Constants.OrgManagementReportPath, null);
							}			
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Wildcard",enabled= false, priority = 24) 
	public void Wildcard() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_organization);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);

		Test_Variables.lstOrgWildcardSearch = OrgModel.Wildcard(); 
		for (OrgModel objModel : Test_Variables.lstOrgWildcardSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Organization Management; Organization Management page opens");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ShowFilter));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 	
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ShowFilter)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

						if (Helper.driver.findElements(By.cssSelector("#sort-"+objFilter.FilterID+" .data-log-radio")).size() == 0) {
							Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.FilterID+" .filter-popup__action--wildcard")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
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
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ApplyFilter)).click();

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
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
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
					}
					Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ClearFilter)).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Lock Filter Functionality",enabled= false, priority = 25) 
	public void Lock() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_organization);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);

		if (Helper.driver.findElements(By.cssSelector("#remove-filters.d-none")).size() == 0) {
			Helper.driver.findElement(By.id(Test_Elements.UnlockFilter)).click();
			Thread.sleep(1000);
		}

		Test_Variables.lstOrgLock = OrgModel.Lock(); 
		for (OrgModel objModel : Test_Variables.lstOrgLock) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Organization Management; Organization Management open");
				SoftAssert softAssert = new SoftAssert();

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
						Thread.sleep(1000);	
						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID+"_show-filter"));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
						Helper.driver.findElement(By.id(objFilter.FilterID+"_show-filter")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
						Thread.sleep(1000);
						if (Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.FilterID+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
							Test_Variables.test.skip("Values not enough to test lock filter functionality");
							Test_Variables.results.createNode("Values not enough to test lock filter functionality");
							Helper.saveResultNew(ITestResult.SKIP, Constants.OrgManagementReportPath, null);
							Helper.driver.findElement(By.id(objFilter.FilterID+"_show-filter")).click();
						}
						else {
							Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.FilterID+" li:nth-child(3) label")).click();
							Thread.sleep(500);
							Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
							Helper.driver.findElement(By.id(objFilter.FilterID+"_apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Test_Variables.steps.createNode("2. Click on lock button");	
							Helper.driver.findElement(By.id(Test_Elements.LockFilter)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
							Thread.sleep(1000);
							String recordsafterfilter = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
							Test_Variables.steps.createNode("3. Close Organization Management Report");
							Test_Variables.steps.createNode("4. Reopen Organization Management Report");
							Helper.driver.navigate().refresh();

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.steps.createNode("5. Verify lock filter remains applied");
							softAssert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText());
							Test_Variables.test.pass(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
							Thread.sleep(1000);
							Helper.driver.findElement(By.id(Test_Elements.UnlockFilter)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Helper.driver.findElement(By.id(Test_Elements.ResetFilters)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Thread.sleep(1000);
							softAssert.assertNotEquals(Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(), recordsafterfilter);
							softAssert.assertAll();
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= false, priority =26) 
	public void Sorting() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_organization);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Test_Variables.lstUserSorting = UserModel.sorting();

		for (OrgModel objModel : Test_Variables.lstOrgSorting) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.testCaseTitle, objModel.testCaseDesc);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Organization Management; Organization Management open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.ColumnID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" column header");
						Helper.driver.findElement(By.id("objFilter.ColumnID")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));						
						Thread.sleep(500);

						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).size(), 1, "Did not sorted in descending order");

						Assert.assertEquals(Helper.driver.findElements(Test_Elements.alertMessage).size(), 0, "Exception message occured");

						Test_Variables.test.pass(objFilter.FilterName+" column sorted descending successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted descending successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));						
						Thread.sleep(1000);

						Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_asc")).size(), 1, "Did not sorted in ascending order");

						Assert.assertEquals(Helper.driver.findElements(Test_Elements.alertMessage).size(), 0, "Exception message occured");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.test.pass(objFilter.FilterName+" column sorted ascending successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted ascending successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to sort");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to sort");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
					}
					catch(StaleElementReferenceException s) {
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Test CSV Download",enabled= false, priority =27) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM103: Verify user can download User CSV file and verify the records", "This test case will verify that user can download User CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Organization Management; Organization Management reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	

			Helper.driver.findElement(By.id(Test_Elements.userOrgType+""+Test_Elements.ShowFilter)).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(800);						
			ClickElement.clickByCss(Helper.driver, "#"+Test_Elements.SortFilter+""+Test_Elements.userOrgType+" li:nth-child(2) label");

			ClickElement.clickById(Helper.driver, Test_Elements.orgOrganzationType+""+Test_Elements.ApplyFilter);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(800);

			String getRowText = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Test_Variables.steps.createNode("5. Click on Export as CSV");	
			Test_Variables.steps.createNode("6. Verify the columns are same in table and CSV");

			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, Test_Variables.orgCSVFileName+date+".csv");
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
						int totalColumns = Helper.driver.findElements(By.cssSelector("tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+1;
						if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+rowsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), Helper.driver.findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = 0;
						}
						columnsCountTotal++;
					}
				}
				//System.out.println(" ");
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
			Test_Variables.test.pass("Column data exported successfully");
			Test_Variables.results.createNode("Column data exported successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
		Thread.sleep(1000);
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}
