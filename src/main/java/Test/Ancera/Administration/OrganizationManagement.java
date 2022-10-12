package Test.Ancera.Administration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.OrgModel;
import Models.ReportFilters;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

public class OrganizationManagement{

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Management"+date+".html");	//	spark.config().setReportName("Organization Management Test Report"); 
		spark.config().setReportName("Organization Management Test Report"); 
		config();
		ConfigureLogin.login();
	}
	
	@Test(priority = 1, enabled = true)
	public void Navigate() throws InterruptedException, IOException {
	NavigateToScreen(url_organization, "Organization Management", OrgManagementReportPath, orgTitle);
	}

	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Lock(orgManagementTable, "Organization Management", OrgManagementReportPath, 0);
	}

	@Test (priority = 3, enabled = true) 
	public void WildcardOrg() throws InterruptedException, IOException {
		driver.get(url_organization);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard1(orgManagementTable, "Organization Management", OrgManagementReportPath, 0);
	}

	@Test(priority= 4, enabled = true)
	public void sorting() throws InterruptedException, IOException {
		Sorting1(orgManagementTable, "Organization Management", OrgManagementReportPath, 0);
	}
	
	
	@Test (enabled= true, priority= 6) 
	public void OpenClosePopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-02: Verify user can open and close Create New User Popup");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar; Click on Administration and select Organization Management");	
			steps.createNode("1. Click on Create New button");

			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(driver.findElement(By.cssSelector(".pop-head")).getText(), "Create Organization"); 
			click(popupCloseButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(driver.findElements(popupNextButton).size(), 0); 
			test.pass("Organization popup window closed successfully");
			results.createNode("Create New Organization popup closed successfully");
			test.addScreenCaptureFromPath(getScreenshot("User Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Organization popup window did not open or closed successfully");
			results.createNode("Organization popup window did not open or closed successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization popup window did not open or closed successfully");  
			results.createNode("Organization popup window did not open or closed successfully"); 
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}
	

	@Test (enabled= true, priority= 7, dependsOnMethods = {"OpenClosePopup"}) 
	public void ResetButton() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-17: Verify user can send reset fields");
			results = test.createNode(Scenario.class, Results);

			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			type(orgNameInput, "Test Org");
			click(popupResetButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(driver.findElement(orgNameInput).getAttribute("value"), "");
			test.pass("Fields reset successfully");
			results.createNode("Fields reset successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}		
		catch(AssertionError er) {
			test.fail("Fields failed to reset");  
			results.createNode("Fields failed to reset"); 
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}
		catch(Exception ex){
			test.fail("Fields failed to reset");  
			results.createNode("Fields failed to reset"); 
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 8, dependsOnMethods = {"ResetButton"}) 
	public void testInvalidEmail() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-19: Verify user cannot create Organization with invalid email", "This test case will verify that user cannot create new organization with invalid email");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			
			steps.createNode("1. Enter invalid email");
			steps.createNode("2. Click on next button; should display validation message");

			driver.findElement(orgEmailInput).sendKeys("invalid@email");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			driver.findElement(popupNextButton).click();

			Assert.assertEquals(driver.findElements(orgEmailError).size(), 1); 
			test.pass("User cannot create organization with invalid email; displays validation message 'Invalid email'");
			results.createNode("User cannot create organization with invalid email; displays validation message 'Invalid email'");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("User was able to create organization with invalid email");
			results.createNode("User was able to create organization with invalid email");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("User was able to create organization with invalid email");
			results.createNode("User was able to create organization with invalid email");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
		click(popupCloseButton);
		Thread.sleep(1000);
	}
	

	@Test (description="Test Case: Create New Organization",enabled= true, priority= 9) 
	public static void CreateOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify user can create New Organizationn", "This test case will verify that user can create new organization");
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button; popup appears");
			steps.createNode("1. Enter valid data in all fields");
			steps.createNode("2. Click on save button");
			
			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);			
			
			String recordsCountBefore = driver.findElement(By.id(ResultsCount)).getText();
			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgTypeDropDownExpand);
			Thread.sleep(750);
			driver.findElement(orgTypeInput).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			clear(orgNameInput);
			type(orgNameInput, lstOrganizationCreate.get(0));
			click(popupNextButton);
			Thread.sleep(700);
			type(orgMaxUsersInput, "10");
			click(orgSystemRolesExpand);
			Thread.sleep(700);
			driver.findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgSystemRolesExpand);
			
			click(orgReportRolesExpand);
			Thread.sleep(700);
			driver.findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgReportRolesExpand);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(0)); 
			String recordsCountAfter = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountAfter, recordsCountBefore);	
			softAssert.assertAll();
			test.pass("New Organization created successfully");
			results.createNode("New Organization created successfully; displayed alert message 'New organization created.'");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("New Organization creation failed");
			results.createNode("New Organization creation failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization creation failed");
			results.createNode("New Organization creation failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}


	@Test (description="Test Case: Update New Organization ",enabled= true, priority= 10) 
	public void UpdateOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-22: Verify user can update Created Organization", "This test case will verify that user can update created organization");	
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new organization");
			steps.createNode("1. Click on update button next to created user; Update organization popup appears");
			steps.createNode("2. Make any change and click on Save button");
			test.createNode("Search for the created organization and click on edit button");  
			
			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			openEditOrgPopup();
			
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(driver.findElement(By.id("nmeOrgID")).getAttribute("value"), "", "Organization ID should not be empty in edit organization popup");
			Thread.sleep(1000);
			type(orgEmailInput, "testorg@anc.com");
			Thread.sleep(500);
			click(popupNextButton);
			Thread.sleep(500);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);  
			Thread.sleep(1000);

			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(1)); 
			softAssert.assertAll();
			test.pass("Organization updated successfully");
			results.createNode("Organization updated successfully; an alert message appears 'Organization details updated.'");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Failed to update the Organization");
			results.createNode("Failed to update the Organization");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Failed to update the Organization");
			results.createNode("Failed to update the Organization");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify Updated Organization ",enabled= true, priority= 11) 
	public void VerifyUpdateOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-23: Verify Organization remained updated after updating it");	
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Update the Organization and click on Save button");
			steps.createNode("2. Reopen the updated popup to verify that changes made were save or not");
			steps.createNode("3. Click on update button");
			
			openEditOrgPopup();

			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(orgEmailInput).getAttribute("value"), "testorg@anc.com"); 
			click(popupCloseButton);
			test.pass("Organization updation verified successfully");
			results.createNode("Organization was updated successfully; changes remained saved");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Organization updation failed");
			results.createNode("Organization updation failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization was not updated successfully; changes did not remained saved");
			results.createNode("Organization was not updated successfully; changes did not remained saved");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Organization Site Check",enabled= true, priority= 12) 
	public void OrganizationSitesHierarchyCheck() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
			steps.createNode("2. Verify Site Type is Organization");

			SoftAssert softAssert = new SoftAssert();
			
			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
			//		if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals("q")) {
					driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
	
			steps.createNode("3. Click on + icon to create new site and verify Site Type is Region");
			click(orgAddSite1);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgSiteTypeInput);
			String regionType = driver.findElement(orgSiteTypeDropDownValue).getText();	
			softAssert.assertEquals(regionType, "Region");
			driver.findElement(orgSiteNameInput).sendKeys("Test Region");
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			click(orgAddSite2);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgSiteTypeInput);
			String subregionType = driver.findElement(orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			driver.findElement(orgSiteNameInput).sendKeys("Test Sub Region");
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			driver.findElement(orgAddSite3).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			String ele1 = driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
			String ele2 = driver.findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
			String ele3 = driver.findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();

			softAssert.assertEquals(ele1, "Complex");
			softAssert.assertEquals(ele2, "Processing Plant");			
			softAssert.assertEquals(ele3, "Testing Lab");

			driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			driver.findElement(orgSiteNameInput).sendKeys("Test Complex Site");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			driver.findElement(orgAddSite4).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInputChild).click();
			String farmType = driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			driver.findElement(orgSiteNameInput).sendKeys("Test Farm");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("11. Click on + icon to create new site and verify Site Type as House");
			driver.findElement(orgAddSite5).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteTypeInputChild).click();
			String HouseType = driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			driver.findElement(orgSiteNameInput).sendKeys("Test House");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			driver.findElement(orgAddSite3).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInputChild).click();
			driver.findElement(By.cssSelector("div .ng-option:nth-child(2)")).click();
			driver.findElement(orgSiteNameInput).sendKeys("Test Processing Plant Site");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1500);
			steps.createNode("13. Create Processing PLant Site");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("14. Click on + icon to create new site and verify Site Type as Rehang, BIrd Wash, Bird Rinse, Chiller, Wing Dip");
			driver.findElement(orgAddSite6).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			String elem1 = driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
			String elem2 = driver.findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
			String elem3 = driver.findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();
			String elem4 = driver.findElement(By.cssSelector("div .ng-option:nth-child(4)")).getText();
			String elem5 = driver.findElement(By.cssSelector("div .ng-option:nth-child(5)")).getText();

			softAssert.assertEquals(elem1, "Rehang");
			softAssert.assertEquals(elem2, "Bird Wash");			
			softAssert.assertEquals(elem3, "Bird Rinse");
			softAssert.assertEquals(elem4, "Chiller");			
			softAssert.assertEquals(elem5, "Wing Dip");	

			driver.findElement(By.cssSelector("div .ng-option:nth-child(4)")).click();
			driver.findElement(orgSiteNameInput).sendKeys("Test Chiller Site");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("15. Verify Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			driver.findElement(orgAddSite3).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteTypeInputChild).click();
			driver.findElement(By.cssSelector("div .ng-option:nth-child(3)")).click();
			driver.findElement(orgSiteNameInput).sendKeys("Test Testing Lab Site");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("16. Create Testing Lab Site");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("17. Click on + icon to create new site and verify Site Type as Lab-Sub Division");
			driver.findElement(orgAddSite7).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteTypeInputChild).click();
			String subDivisionType = driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(subDivisionType, "Lab-Sub Division");
			driver.findElement(orgSiteNameInput).sendKeys("Test Sub Division Site");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1500);
			steps.createNode("18. Verify Lab Sub Division Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");
			
			driver.findElement(orgDeleteSite1).click();
			Thread.sleep(1000);
			driver.findElement(confirmationYes).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);	
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Site details deleted successfully.");
			softAssert.assertAll();
			test.pass("Site heirarchy verified successfully");
			results.createNode("Site heirarchy verified successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);	
		}catch(AssertionError er){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}	

	
	@Test (description="Test Case: Organization Site Mandatory Check",enabled= true, priority= 13) 
	public void OrganizationSiteMandatoryCheck() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-39: Verify Organization Site mandatory checks", "This test case will verify mandotory checks on organization sites");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
			steps.createNode("2. Click on + icon to open create new site window");
			steps.createNode("3. Enter valid data in fields");
			steps.createNode("4. Verify valid data is populated on entering valid street address");
			steps.createNode("5. Click on save button");	

			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
					driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			
			driver.findElement(orgAddSite1).click();  
			waitElementInvisible(loading_cursor);
			driver.findElement(popupSaveButton).click(); 
			Thread.sleep(500);    

			Assert.assertEquals(driver.findElements(orgSiteNameError).size(), 1); 
			test.pass("Site Mandatory fields check verified successfully");
			results.createNode("User was not able to create site without filling all mandatory fields");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Site Mandatory fields check verification failed");
			results.createNode("Site Mandatory fields check verification failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Site Mandatory fields check verification failed");
			results.createNode("Site Mandatory fields check verification failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Site Reset fields", enabled= true, priority= 14) 
	public void SiteResetButton() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-40: Verify Organization Site Reset fields check", "This test case will verify that user can reset fields of create organization site");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
			steps.createNode("2. Click on + icon to open create new site window");
			steps.createNode("3. Enter data in fields");
			steps.createNode("4. Click on reset button");

			driver.findElement(orgSiteNameInput).sendKeys("Lab");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			driver.findElement(popupResetButton).click();
			Thread.sleep(1000);
		
			String orgSiteNameReset = driver.findElement(orgSiteNameInput).getAttribute("value");
			Assert.assertEquals(orgSiteNameReset, "");
			test.pass("Fields reset successfully");   
			results.createNode("Fields reset successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}
		catch(AssertionError er){
			test.fail("Fields reset failed");
			results.createNode("Fields reset failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Fields reset failed");
			results.createNode("Fields reset failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Create Organization Site",enabled= true, priority= 15) 
	public void CreateOrganizationSite() throws InterruptedException, IOException {			
		try {
			test = extent.createTest("AN-OM-41: Verify Organization Site can be created", "This test case will verify that user can cretae new site");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
			steps.createNode("2. Click on + icon to open create new site window");
			steps.createNode("3. Enter valid data in fields");
			steps.createNode("4. Click on save button");
			
			driver.findElement(orgSiteTypeInputChild).click();   
			Thread.sleep(500);	
			driver.findElement(orgSiteTypeDropDownValue).click();  
			driver.findElement(orgSiteNameInput).sendKeys("Region");    
			Thread.sleep(500);
			SoftAssert softAssert = new SoftAssert();
			WebElement stAddress = driver.findElement(orgSiteAddressInput);
			stAddress.clear(); 
			stAddress.sendKeys("Lahore Pakistan"); 
			Thread.sleep(2000);
			stAddress.sendKeys(Keys.DOWN); stAddress.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			softAssert.assertEquals(driver.findElement(By.cssSelector(".confirmation-message label")).getText(), "The specified address is outside USA. Please enter a valid US address.");
			driver.findElement(popupOKButton).click();
			Thread.sleep(1000);
			
			driver.findElement(orgSiteLongitudeInput).sendKeys("-65.32");
			softAssert.assertEquals(driver.findElement(orgSiteLongitudeInput).getAttribute("value"),"-65.32", "Longitude did not filled");
			driver.findElement(orgSiteLatitudeInput).sendKeys("-70.32");
			softAssert.assertEquals(driver.findElement(orgSiteLatitudeInput).getAttribute("value"),"-70.32", "Latitude did not filled");
			
			stAddress.clear();
			stAddress.sendKeys("new york"); 
			Thread.sleep(1000);
			stAddress.sendKeys(Keys.DOWN); stAddress.sendKeys(Keys.ENTER);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(6000);
			driver.findElement(orgSiteNameInput).click();  
			Thread.sleep(2000);
			softAssert.assertEquals(driver.findElement(orgSiteCountryInput).getText(), "Country\nUnited States");
			softAssert.assertEquals(driver.findElement(orgSiteStateInput).getText(), "State\nNew York");
			softAssert.assertEquals(driver.findElement(orgSiteCityInput).getText(), "City\nNew York");
	//		softAssert.assertEquals(driver.findElement(orgSiteZipCodeInput).getText(), "28092");
			softAssert.assertEquals(driver.findElement(orgSiteLatitudeInput).getAttribute("value"),"40.7127753", "Latitude did not autofilled");
			softAssert.assertEquals(driver.findElement(orgSiteLongitudeInput).getAttribute("value"),"-74.0059728", "Longitude did not autofilled");

			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			driver.findElement(popupSaveButton).click(); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);
			String actual = driver.findElement(alertMessage).getText();
			String expected = lstOrgAlertMessages.get(2) ;
			driver.findElement(alertMessageClose).click();

			softAssert.assertEquals(actual, expected);
			softAssert.assertAll();
			test.pass("New Organization site created successfully");
			results.createNode("New Organization site created successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("New Organization site failed to create");
			results.createNode("New Organization site failed to create");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization site failed to create");
			results.createNode("New Organization site failed to create");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Update Organization Sites ",enabled= true, priority= 16) 
	public void UpdateOrganizationSites() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-42: Verify Organization Site can be updated", "This test case will verify that organization site can be updated");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			preconditions.createNode("5. Click on site button next to created Organization; Organization Site popup appears");
			preconditions.createNode("6. Click on + icon to open create new site window");
			preconditions.createNode("7. Create a site");
			steps.createNode("1. Click on created site to reopen it");
			steps.createNode("2. Make changes and click on save button");
			SoftAssert softAssert = new SoftAssert();
			test.createNode("Click on update button next to created site");
			Thread.sleep(2000);
			driver.findElement(orgSite1Click).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(8000);
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			driver.findElement(orgSiteNameInput).clear();
			driver.findElement(orgSiteNameInput).sendKeys("Region Updated");  
			softAssert.assertEquals(driver.findElement(orgSiteCountryInput).getText(), "Country\nUnited States");
			softAssert.assertEquals(driver.findElement(orgSiteStateInput).getText(), "State\nNew York");
			softAssert.assertEquals(driver.findElement(orgSiteCityInput).getText(), "City\nNew York");
	//		softAssert.assertEquals(driver.findElement(orgSiteZipCodeInput).getText(), "28092");
			softAssert.assertEquals(driver.findElement(orgSiteLatitudeInput).getAttribute("value"),"41", "Latitude did not autofilled");
			softAssert.assertEquals(driver.findElement(orgSiteLongitudeInput).getAttribute("value"),"-74", "Longitude did not autofilled");
			driver.findElement(popupSaveButton).click(); 
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(3)); 
			softAssert.assertAll();
			test.pass("Organization site updated successfully");
			results.createNode("Organization site updated successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Organization site failed to update");
			results.createNode("Organization site failed to update");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization site failed to update");
			results.createNode("Organization site failed to update");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
		driver.findElement(popupCloseButton).click(); 
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Verify Update Organization Sites ",enabled= true, priority= 17) 
	public void VerifyUpdateOrganizationSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-43: Verify Organization Site remains updated on reopening", "This test case will verify that updated organization site remain saved");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			preconditions.createNode("5. Click on site button next to created Organization; Organization Site popup appears");
			preconditions.createNode("6. Click on + icon to open create new site window");
			preconditions.createNode("7. Create a site");
			steps.createNode("1. Click on created site to reopen it");
			steps.createNode("2. Make changes and click on save button");
			steps.createNode("3. Reopen ithe updated site to verify the cahnges were saved or not");

			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
					driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			Thread.sleep(3000);
			driver.findElement(orgSite1Click).click(); 
			waitElementInvisible(loading_cursor);
			Thread.sleep(8000);
			Assert.assertEquals(driver.findElement(orgSiteNameInput).getAttribute("value"), "Region Updated"); 
			test.pass("New Organization site updation verified successfully");
			results.createNode("New Organization site updation verified successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("New Organization site updation verification failed");
			results.createNode("New Organization site updation verification failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization site updation verification failed");
			results.createNode("New Organization site updation verification failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Delete Organization Sites ",enabled= true, priority= 18) 
	public void DeleteOrganizationSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-44: Verify Organization Site can be deleted", "This test case will verify that organization site can be deleted");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			preconditions.createNode("5. Click on site button next to created Organization; Organization Site popup appears");
			preconditions.createNode("6. Click on + icon to open create new site window");
			preconditions.createNode("7. Create a site");
			steps.createNode("1. Click on delete icon next to created site");
			steps.createNode("2. Confirmation popup appears");
			steps.createNode("3. Click on yes button");

			driver.findElement(orgSite1Delete).click(); 
			Thread.sleep(750);
			driver.findElement(confirmationYes).click();  
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);

			Assert.assertEquals(driver.findElement(By.id("message")).getText(), lstOrgAlertMessages.get(4)); 
			test.pass("New Organization site deleted successfully");
			results.createNode("New Organization site deleted successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("New Organization site failed to delete");
			results.createNode("New Organization site failed to delete");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization site failed to delete");
			results.createNode("New Organization site failed to delete");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
		driver.findElement(popupCloseButton).click();   
	}
	
	
	@Test (enabled= true, priority = 19) 
	public void BulkUploadEmpty() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-45: Verify user is not able to upload empty bulk site file", "This testcase will verify user is not able to upload empty bulk site file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Navigate to Organization Management screen");
			preconditions.createNode("4. Create a new Organization");
			steps.createNode("1. Click on edit sites icon and upload empty bulk file");

			driver.get(url_organization);;
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
					driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}

			driver.findElement(By.id("file-input")).sendKeys(System.getProperty("user.dir")+"/BulkSiteUpload/"+OrgModel.BulkSitefileNameEmpty);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			Assert.assertEquals(driver.findElement(alertMessage).getText(), "Uploaded file contains no rows."); 
			test.pass("User was not able to upload empty file successfully");
			results.createNode("User was not able to upload empty file successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("User was able to upload empty file");
			results.createNode("User was able to upload empty file");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User was able to upload empty file");
			results.createNode("User was able to upload empty file");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}
	

	@Test (enabled= true, priority = 20) 
	public void BulkUpload() throws InterruptedException, IOException {

		lstOrgBulkSiteUpload = OrgModel.BulkSiteFillData();

		for (OrgModel objModel : lstOrgBulkSiteUpload) { 
			try {
				test = extent.createTest(objModel.testCaseTitle, objModel.testCaseDesc);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Navigate to Organization Management screen");
				preconditions.createNode("4. Create a new Organization");
				steps.createNode("1. Click on edit sites icon and upload bulk file");
				
				driver.get(url_organization);;
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				SoftAssert softAssert = new SoftAssert();

				for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				//	if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals("Test Organization2233")) {
					if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
						driver.findElement(By.id("edit-orgn-sites-"+i)).click();
						waitElementInvisible(loading_cursor);
						break;
					}
				}
				
				driver.findElement(By.cssSelector("li:nth-child(1) div span")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				driver.findElement(orgSiteIDField).click();
				Thread.sleep(4000);
				String SiteID = driver.findElement(orgSiteIDField).getAttribute("value");
				
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

						driver.findElement(By.id("file-input")).sendKeys(System.getProperty("user.dir")+"/BulkSiteUpload/"+OrgModel.BulkSitefileName);
						waitElementInvisible(loading_cursor);
						wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)); 

						softAssert.assertEquals(driver.findElement(alertMessage).getText(), objModel.AlertMessage); 
						
						if (objModel.ErrorCase) {
						driver.findElement(By.id("ErrorBtn")).click();
						Thread.sleep(2000);
							
						WebElement ele = driver.findElement(By.cssSelector(".tooltipBlock"));
						Actions action = new Actions(driver);
						action.moveToElement(ele).perform();
						Thread.sleep(1000);			
						String tooltipText = driver.findElement(By.cssSelector(".tooltip-inner")).getText();
						softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}
						
						if (!objModel.ErrorCase) {
							steps.createNode("2. Verify new tag next to sites on uploading valid bulk file");
							Thread.sleep(3000);
							softAssert.assertNotEquals(driver.findElements(By.cssSelector(".new-site-design")).size(), 0, "Sites uploaded through bulk are not showing up in list with new tag");
						}
							
						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
						saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Assign Agreement to Organization",enabled= false, priority= 21) 
	public void AssignAgreement() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-58: Verify Organization can be assigned Agreement", "This test case will verify that organization can be assigned Agreement");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select Organization Management");
			preconditions.createNode("4. Click on create new button and create a new Organization");
			steps.createNode("1. Click on update icon next to created organization");
			steps.createNode("2. Assign an Agreement");
			steps.createNode("3. Click on save button");

			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);

			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
					driver.findElement(By.id("edit-orgn-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			
			Thread.sleep(2000);
			driver.findElement(popupNextButton).click();
			Thread.sleep(1000);
			driver.findElement(orgAgreementDropDownExpand).click();
			Thread.sleep(1000);
			driver.findElement(orgAgreementDropDownSelect).click();
			driver.findElement(popupSaveButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)); 
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(1)); 
			test.pass("Organization was assigned agreeement successfully");
			results.createNode("Organization was assigned agreeement successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Organization failed to assigned agreeement");
			results.createNode("Organization failed to assigned agreeement");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to assigned agreeement");
			results.createNode("Organization failed to assigned agreeement");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}

	
	@Test (description="Test Case: InActive Organization",enabled= true, priority= 22) 
	public void InActiveOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-59: Verify Organization Site can be made inactive", "This test case will verify that organization can be made inactive");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on update icon next to created organization");
			steps.createNode("2. Click on Inactive toggle button");
			steps.createNode("3. Click on save button");

			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			openEditOrgPopup();
			
			Thread.sleep(1000);
			click(popupNextButton);
			Thread.sleep(1000);

			ClickElement.clickById(driver, "yes_radio");
			click(popupSaveButton);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(1)); 
			test.pass("Organization inactivated successfully");
			results.createNode("Organization inactivated successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Organization failed to inactivated");
			results.createNode("Organization failed to inactivated");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to inactivated");
			results.createNode("Organization failed to inactivated");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Verify InActive Organization",enabled= true, priority= 23) 
	public void VerifyInActiveOrganization() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-60: Verify inactive Organization Site is not displayed in create user popup");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on update icon next to created organization");
			steps.createNode("2. Click on Inactive toggle button and save the organization");
			steps.createNode("3. Go to User Management and open new user popup");
			steps.createNode("4. Search for the inactivated organization in Organization dropdown; should not appear");

			searchOrg();
			Assert.assertTrue(driver.findElement(By.cssSelector(".ng-option-disabled")).isDisplayed()); 
			test.pass("Inactivated Organization was not found in dropdown successfully");
			results.createNode("Inactivated Organization was not found in dropdown successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Inactivated Organization was found in dropdown");
			results.createNode("Inactivated Organization was found in dropdown");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Inactivated Organization was found in dropdown");
			results.createNode("Inactivated Organization was found in dropdown");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}

	
	@Test (description="Test Case: Delete Organization",enabled= true, priority= 24) 
	public void DeleteOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-61: Verify Organization can be deleted and verify it from table and user dropdown as well");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on delete icon next to created organization");
			steps.createNode("2. Click on yes from confirmation box");
			steps.createNode("3. Verify the organization is deleted from table");
			steps.createNode("4. Go to user screen and verify it is deleted from user dropdown");

			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(orgTitle));
			Thread.sleep(4000);

			String recordsCountBefore = driver.findElement(By.id(ResultsCount)).getText();
			
			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
					driver.findElement(By.id("delete-orgn-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			
			Thread.sleep(1000);
			driver.findElement(confirmationYes).click();  
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(5)); 
			Thread.sleep(3500);
			String recordsCountAfter = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter);
			
			Test_Functions.searchOrg();
			softAssert.assertTrue(driver.findElement(By.cssSelector(".ng-option-disabled")).isDisplayed()); 
			softAssert.assertAll();
			test.pass("Organization deleted and verified successfully");
			results.createNode("Organization deleted and verified successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Organization failed to delete");
			results.createNode("Organization failed to delete");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to delete");
			results.createNode("Organization failed to delete");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}
	
	
	@Test (enabled= true, priority= 25) 
	public static void CreateAlliedPartnerOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify user can create New Allied Partner Organizationn");
			steps.createNode("1. Enter valid data in all fields");
			steps.createNode("2. Click on save button");
			
			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);			
			
			String recordsCountBefore = driver.findElement(By.id(ResultsCount)).getText();
			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgTypeDropDownExpand);
			Thread.sleep(750);
			driver.findElement(orgTypeInput).sendKeys("Allied Partner");
			driver.findElement(orgTypeInput).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			clear(orgNameInput);
			type(orgNameInput, lstOrganizationCreate.get(5));
			click(popupNextButton);
			Thread.sleep(700);
			type(orgMaxUsersInput, "10");
			click(orgSystemRolesExpand);
			Thread.sleep(700);
			driver.findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgSystemRolesExpand);
			
			click(orgReportRolesExpand);
			Thread.sleep(700);
			driver.findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgReportRolesExpand);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(0)); 
			String recordsCountAfter = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountAfter, recordsCountBefore);	
			softAssert.assertAll();
			test.pass("New Allied Organization created successfully");
			results.createNode("New Allied Organization created successfully; displayed alert message 'New organization created.'");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("New AlliedOrganization creation failed");
			results.createNode("New Allied Organization creation failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Allied Organization creation failed");
			results.createNode("New Allied Organization creation failed");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 26) 
	public static void AddProductsAlliedPartnerOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify user can add/remove product for Allied Partner Organization");
			SoftAssert softAssert = new SoftAssert();
			
			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(5))) {
					driver.findElement(By.xpath("(//*[@title = 'Manage Products'])["+i+"]")).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}		
			
			Thread.sleep(800);
			type(orgUploadProductImage, projectPath+"/Image/ancera_logo.jpg");
			softAssert.assertEquals(size(orgUploadProductImage), 0, "Product not added successfully");
			
			click(orgRemoveUploadedProduct);
			softAssert.assertEquals(size(orgUploadProductImage), 1, "Product not removed successfully");
			
			type(orgUploadProductImage, projectPath+"/Image/ancera_logo.jpg");
			type(orgAddProductName, "Allied Product 1");
			type(orgAddProductDescription, "Product for Allied Organization");
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(6)); 
			softAssert.assertAll();
			test.pass("Product added successfully");
			results.createNode("Product added successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Product failed to add successfully");
			results.createNode("Product failed to add successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to add successfully");
			results.createNode("Product failed to add successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 27) 
	public static void VerifyProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify product added from Organization Management screen is reflected on Company Products screen");
			SoftAssert softAssert = new SoftAssert();
			
			driver.get(url_companyProducts);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
					
			for (int i=1;i<driver.findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(lstOrganizationCreate.get(5))) {
					softAssert.assertFalse(driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(""), "Product name is empty");
					softAssert.assertFalse(driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5) label")).getText().equals(""), "Product description is empty");
					break;
				}
			}	
			softAssert.assertAll();
			test.pass("Product reflected successfully");
			results.createNode("Product reflected successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);

		}catch(AssertionError er){
			test.fail("Product did not reflected successfully");
			results.createNode("Product failed to add successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to add successfully");
			results.createNode("Product failed to add successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 28) 
	public static void AddProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify user can add product in Company Products screen");
			SoftAssert softAssert = new SoftAssert();
			
			click(CompanyProductCreateNewButton);
			waitElementInvisible(loading_cursor);
			
			type(orgUploadProductImage, projectPath+"/Image/ancera_logo.jpg");
			softAssert.assertEquals(size(orgUploadProductImage), 0, "Product not added successfully");
			
			click(orgRemoveUploadedProduct);
			softAssert.assertEquals(size(orgUploadProductImage), 1, "Product not removed successfully");
			
			type(orgUploadProductImage, projectPath+"/Image/ancera_logo.jpg");
			type(CompanyProductNameInput, lstOrganizationCreate.get(5));
			driver.findElement(CompanyProductNameInput).sendKeys(Keys.ENTER);
			type(orgAddProductName, "Allied Product 2");
			type(orgAddProductDescription, "Product for Allied Organization");
			click(popupSaveButtonXpath);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(6)); 
			softAssert.assertAll();
			test.pass("Product added successfully");
			results.createNode("Product added successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Product failed to create");
			results.createNode("Product failed to create");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to create");
			results.createNode("Product failed to create");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 29, dependsOnMethods = {"AddProductInCompanyProductsScreen"}) 
	public static void VerifyProductInOrganizationScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify product added from Company Products is reflected on Organization Management screen screen");
			SoftAssert softAssert = new SoftAssert();
			
			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
					
			for (int i=1;i<driver.findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(5))) {
					driver.findElement(By.xpath("(//*[@title = 'Manage Products'])["+i+"]")).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			
			softAssert.assertEquals(size(orgGetTotalCreatedProducts), 2, "Product created from Company Product screen is not reflected on Organization Screen");
			softAssert.assertAll();
			test.pass("Product reflected successfully");
			results.createNode("Product reflected successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);

		}catch(AssertionError er){
			test.fail("Product did not reflected successfully");
			results.createNode("Product failed to add successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to add successfully");
			results.createNode("Product failed to add successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 30) 
	public static void DeleteProductsAlliedPartnerOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify user can delete product for Allied Partner Organization");
			SoftAssert softAssert = new SoftAssert();
			
			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(5))) {
					try {
					driver.findElement(By.xpath("(//*[@title = 'Manage Products'])["+i+"]")).click();
					}
					catch (ElementNotInteractableException ex){
					}
					break;
				}
			}		
			
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			scroll(orgDeleteUploadedProduct);
			Thread.sleep(800);
			click(orgDeleteUploadedProduct);
			waitElementVisible(popupYesButton);
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(6)); 
			test.pass("Product deleted successfully");
			results.createNode("Product deleted successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Product failed to deleted successfully");
			results.createNode("Product failed to deleted successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to deleted successfully");
			results.createNode("Product failed to deleted successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 31) 
	public static void EditProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify product can be edited in Company Product Screen");
			SoftAssert softAssert = new SoftAssert();
			
			driver.get(url_companyProducts);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
					
			for (int i=1;i<driver.findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(lstOrganizationCreate.get(5))) {
					click(By.id(CompanyNameProductEditButton+""+i));
					waitElementInvisible(loading_cursor);
					break;
				}
			}	
			
			type(orgAddProductDescription, "Product for Allied Organization Description");
			click(popupSaveButtonXpath);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(8)); 
			
			softAssert.assertAll();
			test.pass("Product updated successfully");
			results.createNode("Product updated successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);

		}catch(AssertionError er){
			test.fail("Product failed update successfully");
			results.createNode("Product failed update successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed update successfully");
			results.createNode("Product failed update successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 32) 
	public static void DeleteProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-20: Verify product can be deleted in Company Product Screen");
			SoftAssert softAssert = new SoftAssert();
					
			for (int i=1;i<driver.findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(lstOrganizationCreate.get(5))) {
					click(By.id(CompanyNameProductDeleteButton+""+i));
					waitElementInvisible(loading_cursor);
					break;
				}
			}	
			
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(7)); 
			
			softAssert.assertAll();
			test.pass("Product deleted successfully");
			results.createNode("Product deleted successfully");	
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);

		}catch(AssertionError er){
			test.fail("Product did not deleted successfully");
			results.createNode("Product did not deleted successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product did not deleted successfully");
			results.createNode("Product did not deleted successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 33) 
	public void DeleteAlliedOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-61: Verify Organization can be deleted and verify it from table and user dropdown as well");
			
			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			waitElementVisible(orgTitle);
			Thread.sleep(2000);

			String recordsCountBefore = driver.findElement(By.id(ResultsCount)).getText();
			
			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(5))) {
					driver.findElement(By.id("delete-orgn-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			
			Thread.sleep(1000);
			click(confirmationYes);
			waitElementVisible(alertMessage);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), lstOrgAlertMessages.get(5)); 
			Thread.sleep(3500);
			String recordsCountAfter = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter);
			
			softAssert.assertAll();
			test.pass("Organization deleted and verified successfully");
			results.createNode("Organization deleted and verified successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Organization failed to delete");
			results.createNode("Organization failed to delete");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to delete");
			results.createNode("Organization failed to delete");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}
	
	
	@Test (enabled= true, priority= 33) 
	public void TestFilterCompanyProducts() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-61: Verify Allied Company and Product Name filter functionality");
			
			driver.get(url_companyProducts);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			
			SoftAssert softAssert = new SoftAssert();
			
			String recordsCountBefore = driver.findElement(By.id(ResultsCount)).getText();
			
			click(By.id(productAlliedCompany+""+ShowFilter));
			waitElementInvisible(loading_cursor);
			click(By.cssSelector("#ul-"+productAlliedCompany+" li:nth-child(2) label"));
			click(By.id(productAlliedCompany+""+ApplyFilter));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			String recordsCountAfter = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter, "Allied Company filter failed to apply");
			click(By.id(productAlliedCompany+""+ClearFilter));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			
			click(By.id(productName+""+ShowFilter));
			waitElementInvisible(loading_cursor);
			click(By.cssSelector("#ul-"+productName+" li:nth-child(2) label"));
			click(By.id(productName+""+ApplyFilter));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			String recordsCountAfter2 = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter2, "Product Name filter failed to apply");
			click(By.id(productName+""+ClearFilter));
			
			softAssert.assertAll();
			test.pass("Filters functionality verified successfully");
			results.createNode("Filters functionality verified successfully");
		
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Filters functionality failed to verify successfully");
			results.createNode("Filters functionality failed to verify successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Filters functionality failed to verify successfully");
			results.createNode("Filters functionality failed to verify successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}
	
	
		
	

	@Test (priority = 34, enabled = true) 
	public void WildcardCP() throws InterruptedException, IOException {
		driver.get(url_companyProducts);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard1(CompanyProductsTableName, "Company Products", OrgManagementReportPath, 1);
	}


	@Test(priority= 35, enabled = true)
	public void sortingCP() throws InterruptedException, IOException {
		Sorting1(CompanyProductsTableName, "Company Products", OrgManagementReportPath, 1);
	}
	
	
	@Test(priority= 36, enabled = true)
	public void PaginationCP() throws InterruptedException, IOException {
		Pagination(CompanyProductsTableName, "Company Products", OrgManagementReportPath);
	}
	
	
	@Test(priority= 37, enabled = true)
	public void RowsPerPageCP() throws InterruptedException, IOException {
		RowsPerPage1();
	}
	
	
	@Test(priority= 38)
	public void ExportCSV() throws InterruptedException, IOException {
		driver.get(url_organization);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);	
		CSVExport("Organization Management", OrgManagementReportPath, Test_Elements.orgCSVFileName, orgManagementTable, 1);
	}



	@AfterTest
	public static void endreport() {
		extent.flush();
		//	driver.close();
	}
}
