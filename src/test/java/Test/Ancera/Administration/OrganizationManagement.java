package Test.Ancera.Administration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.FrameworkConstants;
import MiscFunctions.ClickElement;
import MiscFunctions.DateUtil;
import MiscFunctions.NavigateToScreen;
import Models.OrganizationManagementModel;
import Models.ReportFilters;
import PageObjects.OrganizationManagementPage;
import PageObjects.UserManagementPage;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Test.Ancera.Login.LoginTest;

import static Models.OrganizationManagementModel.*;
import static PageObjects.OrganizationManagementPage.*;
import static PageObjects.CompanyProductsPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static LogViewFunctions.RowsPerPage.*;
import static MiscFunctions.Methods.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Constants.*;
import static LogViewFunctions.LogCSVExport.*;

public class OrganizationManagement extends BaseTest{

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Management"+DateUtil.date+".html");
		spark.config().setReportName("Organization Management Test Report"); 
	}
	
	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}

	@Test(priority = 1, enabled = true)
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen.navigate(url_organization, "Organization Management", orgTitle);
	}

	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		getDriver().get(url_organization);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Lock(orgManagementTable, "Organization Management", 0);
	}

	@Test (priority = 3, enabled = true) 
	public void WildcardOrg() throws InterruptedException, IOException {
		getDriver().get(url_organization);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard(orgManagementTable, "Organization Management", 0);
	}

	@Test(priority= 4, enabled = true)
	public void sorting() throws InterruptedException, IOException {
		Sorting(orgManagementTable, "Organization Management", 0);
	}
	
	
	@Test (enabled= true, priority= 6) 
	public void OpenClosePopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-02: Verify user can open and close Create New User Popup");

			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			getScreenshot();
			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(getDriver().findElement(By.cssSelector(".pop-head")).getText(), "Create Organization"); 
			click(popupCloseButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(getDriver().findElements(popupNextButton).size(), 0); 
			test.pass("Organization popup window closed successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Organization popup window did not open or closed successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization popup window did not open or closed successfully");  
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	

	@Test (enabled= true, priority= 7)
	public void ResetButton() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-03: Verify user can send reset fields");

			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			type(orgNameInput, "Test Org");
			click(popupResetButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(getDriver().findElement(orgNameInput).getAttribute("value"), "");
			test.pass("Fields reset successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}		
		catch(AssertionError er) {
			test.fail("Fields failed to reset");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex){
			test.fail("Fields failed to reset");  
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 8)
	public void testInvalidEmail() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-04: Verify user cannot create Organization with invalid email", "This test case will verify that user cannot create new organization with invalid email");
			
			getDriver().findElement(orgEmailInput).sendKeys("invalid@email");
			getScreenshot();
			getDriver().findElement(popupNextButton).click();

			Assert.assertEquals(getDriver().findElements(orgEmailError).size(), 1); 
			test.pass("User cannot create organization with invalid email; displays validation message 'Invalid email'");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("User was able to create organization with invalid email");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("User was able to create organization with invalid email");
			saveResult(ITestResult.FAILURE, ex);
		}	
		click(popupCloseButton);
		Thread.sleep(1000);
	}
	

	public void CreateOrganizationFunction(String orgName) throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-05: Verify user can create New Organizationn");
			
			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);			
			
			String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();
			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgTypeDropDownExpand);
			Thread.sleep(750);
			getDriver().findElement(orgTypeInput).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			clear(orgNameInput);
			type(orgNameInput, orgName);
			//	Allow Domains Start
			Thread.sleep(750);
			click(orgAllowDomainsExpand);
			Thread.sleep(1000);
			click(selectAllCheckBox);
			Thread.sleep(750);
			click(orgAllowDomainsExpand);
			Thread.sleep(750);
			// Allow Domains End
			click(popupNextButton);
			Thread.sleep(700);
			type(orgMaxUsersInput, "10");
			//	Role Category Start
			click(roleCategoryExpand);
			Thread.sleep(1000);
			click(selectAllCheckBox);
			Thread.sleep(750);
			click(roleCategoryExpand);
			Thread.sleep(750);
			//	Role Category End
			click(orgSystemRolesExpand);
			Thread.sleep(700);
			getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgSystemRolesExpand);
			
			click(orgReportRolesExpand);
			Thread.sleep(700);
			getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgReportRolesExpand);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(0)); 
			String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountAfter, recordsCountBefore);	
			softAssert.assertAll();
			test.pass("New Organization created successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("New Organization creation failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization creation failed");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (description="Test Case: Create New Organization",enabled= true, priority= 9) 
	public void CreateOrganizationCall() throws InterruptedException, IOException {
		CreateOrganizationFunction(OrganizationName);
	}
		
		

	@Test (description="Test Case: Update New Organization ",enabled= true, priority= 10) 
	public void UpdateOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-06: Verify user can update Created Organization");
			
			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			openEditOrgPopup(OrganizationName);
			
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(getDriver().findElement(By.id("nmeOrgID")).getAttribute("value"), "", "Organization ID should not be empty in edit organization popup");
			Thread.sleep(1000);
			type(orgEmailInput, "testorg@anc.com");
			Thread.sleep(500);
			click(popupNextButton);
			Thread.sleep(500);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);  
			Thread.sleep(1000);

			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(1)); 
			softAssert.assertAll();
			test.pass("Organization updated successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Failed to update the Organization");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Failed to update the Organization");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Verify Updated Organization ",enabled= true, priority= 11) 
	public void VerifyUpdateOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-07: Verify Organization remained updated after updating it");

			if (size(alertClose) != 0) {
				click(alertClose);
			}

			openEditOrgPopup(OrganizationName);

			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(orgEmailInput).getAttribute("value"), "testorg@anc.com"); 
			click(popupCloseButton);
			test.pass("Organization updation verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Organization updation failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization was not updated successfully; changes did not remained saved");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Organization Site Check",enabled= true, priority= 12) 
	public void OrganizationSitesHierarchyCheck() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-08-16: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on site button next to created Organization; Organization Site popup appears");
			steps.createNode("2. Verify Site Type is Organization");

			SoftAssert softAssert = new SoftAssert();
			
			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			openEditOrgSitesPopup(OrganizationName);
	
			steps.createNode("3. Click on + icon to create new site and verify Site Type is Region");
			waitElementClickable(orgAddSite1);
			click(orgAddSite1);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgSiteTypeInput);
			String regionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();	
			softAssert.assertEquals(regionType, "Region");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Region");
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			click(orgAddSite2);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgSiteTypeInput);
			String subregionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Region");
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			getDriver().findElement(orgAddSite3).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			String ele1 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
			String ele2 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
			String ele3 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();

			softAssert.assertEquals(ele1, "Complex");
			softAssert.assertEquals(ele2, "Processing Plant");			
			softAssert.assertEquals(ele3, "Testing Lab");

			getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Complex Site");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			getDriver().findElement(orgAddSite4).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			String farmType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Farm");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("11. Click on + icon to create new site and verify Site Type as House");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			String HouseType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test House");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			getDriver().findElement(orgAddSite3).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			getDriver().findElement(By.cssSelector("div .ng-option:nth-child(2)")).click();
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Processing Plant Site");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1500);
			steps.createNode("13. Create Processing PLant Site");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("14. Click on + icon to create new site and verify Site Type as Rehang, BIrd Wash, Bird Rinse, Chiller, Wing Dip");
			getDriver().findElement(orgAddSite6).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			String elem1 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();
			String elem2 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(2)")).getText();
			String elem3 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(3)")).getText();
			String elem4 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(4)")).getText();
			String elem5 = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(5)")).getText();

			softAssert.assertEquals(elem1, "Rehang");
			softAssert.assertEquals(elem2, "Bird Wash");			
			softAssert.assertEquals(elem3, "Bird Rinse");
			softAssert.assertEquals(elem4, "Chiller");			
			softAssert.assertEquals(elem5, "Wing Dip");	

			getDriver().findElement(By.cssSelector("div .ng-option:nth-child(4)")).click();
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Chiller Site");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("15. Verify Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			getDriver().findElement(orgAddSite3).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			getDriver().findElement(By.cssSelector("div .ng-option:nth-child(3)")).click();
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Testing Lab Site");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("16. Create Testing Lab Site");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("17. Click on + icon to create new site and verify Site Type as Lab-Sub Division");
			getDriver().findElement(orgAddSite7).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			String subDivisionType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(subDivisionType, "Lab-Sub Division");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Division Site");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1500);
			steps.createNode("18. Verify Lab Sub Division Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");
			
			getDriver().findElement(orgDeleteSite1).click();
			Thread.sleep(1000);
			getDriver().findElement(confirmationYes).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);	
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Site details deleted successfully.");
			softAssert.assertAll();
			test.pass("Site heirarchy verified successfully");
			results.createNode("Site heirarchy verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}catch(AssertionError er){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	

	
	@Test (description="Test Case: Organization Site Mandatory Check",enabled= true, priority= 13) 
	public void OrganizationSiteMandatoryCheck() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-17: Verify Organization Site mandatory checks");

			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			openEditOrgSitesPopup(OrganizationName);
			click(orgAddSite1);
			waitElementInvisible(loading_cursor);
			click(popupSaveButton);
			Thread.sleep(500);    

			Assert.assertEquals(getDriver().findElements(orgSiteNameError).size(), 1); 
			test.pass("Site Mandatory fields check verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Site Mandatory fields check verification failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Site Mandatory fields check verification failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Exceptional Flow: Site Reset fields", enabled= true, priority= 14)
	public void SiteResetButton() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-18: Verify Organization Site Reset fields check");

			type(orgSiteNameInput, "Lab");
			getScreenshot();
			click(popupResetButton);
			Thread.sleep(1000);
		
			String orgSiteNameReset = getDriver().findElement(orgSiteNameInput).getAttribute("value");
			Assert.assertEquals(orgSiteNameReset, "");
			test.pass("Fields reset successfully");   
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er){
			test.fail("Fields reset failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Fields reset failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Create Organization Site",enabled= true, priority= 15)
	public void CreateOrganizationSite() throws InterruptedException, IOException {			
		try {
			test = extent.createTest("AN-OM-19: Verify Organization Site can be created");
			click(orgSiteTypeInputChild);
			Thread.sleep(500);	
			click(orgSiteTypeDropDownValue);
			type(orgSiteNameInput, "Region");
			Thread.sleep(500);
			SoftAssert softAssert = new SoftAssert();
			WebElement stAddress = getDriver().findElement(orgSiteAddressInput);
			clear(orgSiteAddressInput);
			type(orgSiteAddressInput, "Lahore Pakistan");
			Thread.sleep(2000);
			stAddress.sendKeys(Keys.DOWN); stAddress.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			softAssert.assertEquals(getDriver().findElement(By.cssSelector(".confirmation-message label")).getText(), "The specified address is outside USA. Please enter a valid US address.");
			getDriver().findElement(popupOKButton).click();
			Thread.sleep(1000);
			
			getDriver().findElement(orgSiteLongitudeInput).sendKeys("-65.32");
			softAssert.assertEquals(getDriver().findElement(orgSiteLongitudeInput).getAttribute("value"),"-65.32", "Longitude did not filled");
			getDriver().findElement(orgSiteLatitudeInput).sendKeys("-70.32");
			softAssert.assertEquals(getDriver().findElement(orgSiteLatitudeInput).getAttribute("value"),"-70.32", "Latitude did not filled");
			
			stAddress.clear();
			stAddress.sendKeys("new york"); 
			Thread.sleep(1000);
			stAddress.sendKeys(Keys.DOWN); stAddress.sendKeys(Keys.ENTER);
			waitElementInvisible(loading_cursor);
			Thread.sleep(6000);
			getDriver().findElement(orgSiteNameInput).click();  
			Thread.sleep(2000);
			softAssert.assertEquals(getDriver().findElement(orgSiteCountryInput).getText(), "United States");
			softAssert.assertEquals(getDriver().findElement(orgSiteStateInput).getText(), "New York");
			softAssert.assertEquals(getDriver().findElement(orgSiteCityInput).getText(), "New York");
			softAssert.assertEquals(getDriver().findElement(orgSiteLatitudeInput).getAttribute("value"),"40.7127753", "Latitude did not autofilled");
			softAssert.assertEquals(getDriver().findElement(orgSiteLongitudeInput).getAttribute("value"),"-74.0059728", "Longitude did not autofilled");

			getScreenshot();
			getDriver().findElement(popupSaveButton).click(); 
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			String actual = getDriver().findElement(alertMessage).getText();
			String expected = lstOrgAlertMessages.get(2) ;
			getDriver().findElement(alertMessageClose).click();

			softAssert.assertEquals(actual, expected);
			softAssert.assertAll();
			test.pass("New Organization site created successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("New Organization site failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization site failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Update Organization Sites ",enabled= true, priority= 16) 
	public void UpdateOrganizationSites() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-20: Verify Organization Site can be updated");
			
			SoftAssert softAssert = new SoftAssert();
			Thread.sleep(2000);
			getDriver().findElement(orgSite1Click).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);
			getScreenshot();

			//#countryId .ng-placeholder
			//#countryId .ng-value-label

			getDriver().findElement(orgSiteNameInput).clear();
			getDriver().findElement(orgSiteNameInput).sendKeys("Region Updated");  
			softAssert.assertEquals(getDriver().findElement(orgSiteCountryPlaceholder).getText(), "Country");
			softAssert.assertEquals(getDriver().findElement(orgSiteCountryInput).getText(), "United States");
			softAssert.assertEquals(getDriver().findElement(orgSiteStatePlaceholder).getText(), "State");
			softAssert.assertEquals(getDriver().findElement(orgSiteStateInput).getText(), "New York");
			softAssert.assertEquals(getDriver().findElement(orgSiteCityPlaceholder).getText(), "City");
			softAssert.assertEquals(getDriver().findElement(orgSiteCityInput).getText(), "New York");
			softAssert.assertTrue(getDriver().findElement(orgSiteLatitudeInput).getAttribute("value").startsWith("40."), "Latitude did not autofilled");
			softAssert.assertTrue(getDriver().findElement(orgSiteLongitudeInput).getAttribute("value").startsWith("-74."), "Longitude did not autofilled");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(3)); 
			softAssert.assertAll();
			test.pass("Organization site updated successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Organization site failed to update");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization site failed to update");
			saveResult(ITestResult.FAILURE, ex);
		}
		getDriver().findElement(popupCloseButton).click(); 
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Verify Update Organization Sites ",enabled= true, priority= 17) 
	public void VerifyUpdateOrganizationSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-21: Verify Organization Site remains updated on reopening");

			openEditOrgSitesPopup(OrganizationName);
			
			Thread.sleep(3000);
			getDriver().findElement(orgSite1Click).click(); 
			waitElementInvisible(loading_cursor);
			Thread.sleep(8000);
			Assert.assertEquals(getDriver().findElement(orgSiteNameInput).getAttribute("value"), "Region Updated"); 
			test.pass("New Organization site updation verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("New Organization site updation verification failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization site updation verification failed");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Delete Organization Sites ",enabled= true, priority= 18) 
	public void DeleteOrganizationSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-22: Verify Organization Site can be deleted");


			getDriver().findElement(orgSite1Delete).click(); 
			Thread.sleep(750);
			getDriver().findElement(confirmationYes).click();  
			waitElementVisible(alertMessage);
			Thread.sleep(1000);

			Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), lstOrgAlertMessages.get(4)); 
			test.pass("New Organization site deleted successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("New Organization site failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Organization site failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
		getDriver().findElement(popupCloseButton).click();   
	}
	
	
	@Test (enabled= false, priority = 19)
	public void BulkUploadEmpty() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-23: Verify user is not able to upload empty bulk site file");

			getDriver().get(url_organization);;
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
	
			openEditOrgSitesPopup(OrganizationName);

			getDriver().findElement(By.cssSelector(".btn-lbl")).sendKeys(FrameworkConstants.OrganizationBulkSiteUploadInvalidFile);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Uploaded file contains no rows."); 
			test.pass("User was not able to upload empty file successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("User was able to upload empty file");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User was able to upload empty file");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	

	@Test (enabled= false, priority = 20)
	public void BulkUpload() throws InterruptedException, IOException {

		lstOrgBulkSiteUpload = OrganizationManagementModel.BulkSiteFillData();

		for (OrganizationManagementModel objModel : lstOrgBulkSiteUpload) { 
			try {
				test = extent.createTest(objModel.testCaseTitle);
				steps = test.createNode(Scenario.class, Steps);
				steps.createNode("1. Click on edit sites icon and upload bulk file");
				
				getDriver().get(url_organization);;
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				SoftAssert softAssert = new SoftAssert();

				openEditOrgSitesPopup(OrganizationName);
				
				getDriver().findElement(By.cssSelector("li:nth-child(1) div span")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				getDriver().findElement(orgSiteIDField).click();
				Thread.sleep(4000);
				String SiteID = getDriver().findElement(orgSiteIDField).getAttribute("value");
				
				System.out.println(SiteID);
				
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						@SuppressWarnings("unused")
						int chkCounter = 1;

						Row row;
						Cell cell;
						
						FileInputStream fsIP= new FileInputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadValidFile));
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

						FileOutputStream output_file =new FileOutputStream(new File(FrameworkConstants.OrganizationBulkSiteUploadValidFile));
						wb.write(output_file);
						output_file.close(); 

						getDriver().findElement(By.cssSelector(".btn-lbl")).sendKeys(FrameworkConstants.OrganizationBulkSiteUploadValidFile);
						waitElementInvisible(loading_cursor);
						waitElementVisible(alertMessage); 

						softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), objModel.AlertMessage); 
						
						if (objModel.ErrorCase) {
						getDriver().findElement(By.id("ErrorBtn")).click();
						Thread.sleep(2000);
							
						WebElement ele = getDriver().findElement(By.cssSelector(".tooltipBlock"));
						Actions action = new Actions(getDriver());
						action.moveToElement(ele).perform();
						Thread.sleep(1000);			
						String tooltipText = getDriver().findElement(By.cssSelector(".tooltip-inner")).getText();
						softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
						}
						
						if (!objModel.ErrorCase) {
							steps.createNode("2. Verify new tag next to sites on uploading valid bulk file");
							Thread.sleep(3000);
							softAssert.assertNotEquals(getDriver().findElements(By.cssSelector(".new-site-design")).size(), 0, "Sites uploaded through bulk are not showing up in list with new tag");
						}
							
						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						saveResult(ITestResult.FAILURE, ex);
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
			test = extent.createTest("AN-OM-36: Verify Organization can be assigned Agreement");

			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);

			openEditOrgPopup(OrganizationName);
			
			Thread.sleep(2000);
			getDriver().findElement(popupNextButton).click();
			Thread.sleep(1000);
			getDriver().findElement(orgAgreementDropDownExpand).click();
			Thread.sleep(1000);
			getDriver().findElement(orgAgreementDropDownSelect).click();
			getDriver().findElement(popupSaveButton).click();
			waitElementVisible(alertMessage); 
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(1)); 
			test.pass("Organization was assigned agreeement successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Organization failed to assigned agreeement");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to assigned agreeement");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	
	@Test (description="Test Case: InActive Organization",enabled= true, priority= 22) 
	public void InActiveOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-37: Verify Organization Site can be made inactive", "This test case will verify that organization can be made inactive");

			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			openEditOrgPopup(OrganizationName);
			
			Thread.sleep(1000);
			click(popupNextButton);
			Thread.sleep(1000);

			ClickElement.clickById(getDriver(), "yes_radio");
			click(popupSaveButton);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(1)); 
			test.pass("Organization inactivated successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Organization failed to inactivated");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to inactivated");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Verify InActive Organization",enabled= true, priority= 23) 
	public void VerifyInActiveOrganization() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-OM-38: Verify inactive Organization Site is not displayed in create user popup");

			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			UserManagementPage.openEditUserPopup(config.ie_username());
			getDriver().findElement(UserManagementPage.userOrgInput).sendKeys(OrganizationName);
			waitElementVisible(By.cssSelector(".ng-option-disabled"));
			Assert.assertTrue(getDriver().findElement(By.cssSelector(".ng-option-disabled")).isDisplayed()); 
			test.pass("Inactivated Organization was not found in dropdown successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Inactivated Organization was found in dropdown");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Inactivated Organization was found in dropdown");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	
	@Test (description="Test Case: Delete Organization",enabled= true, priority= 24) 
	public void DeleteOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-39: Verify Organization can be deleted and verify it from table and user dropdown as well");

			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			waitElementVisible(orgTitle);
			Thread.sleep(4000);

			String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();
			
			for (int i=1;i<getDriver().findElements(By.cssSelector("tr")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(OrganizationName)) {
					getDriver().findElement(By.id("delete-orgn-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			
			Thread.sleep(1000);
			getDriver().findElement(confirmationYes).click();  
			waitElementVisible(alertMessage);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(5)); 
			Thread.sleep(3500);
			String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter);
			softAssert.assertAll();
			test.pass("Organization deleted and verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Organization failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	
	@Test (enabled= true, priority= 25) 
	public void CreateAlliedPartnerOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-40: Verify user can create New Allied Partner Organizationn");

			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);			
			
			String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();
			click(orgCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(orgTypeDropDownExpand);
			Thread.sleep(750);
			getDriver().findElement(orgTypeInput).sendKeys("Allied Partner");
			getDriver().findElement(orgTypeInput).sendKeys(Keys.ENTER);
			Thread.sleep(750);
			clear(orgNameInput);
			type(orgNameInput, AlliedOrganizationName);
//			Allow Domains Start
			click(orgAllowDomainsExpand);
			Thread.sleep(750);
			click(selectAllCheckBox);
			Thread.sleep(750);
			click(orgAllowDomainsExpand);
			Thread.sleep(750);
			// Allow Domains End
			click(popupNextButton);
			Thread.sleep(700);
			type(orgMaxUsersInput, "10");
			//	Role Category Start
			click(roleCategoryExpand);
			Thread.sleep(1000);
			click(selectAllCheckBox);
			Thread.sleep(750);
			click(roleCategoryExpand);
			Thread.sleep(750);
			//	Role Category End
			Thread.sleep(700);
			type(orgMaxUsersInput, "10");
			click(orgSystemRolesExpand);
			Thread.sleep(700);
			getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgSystemRolesExpand);
			
			click(orgReportRolesExpand);
			Thread.sleep(700);
			getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
			click(orgReportRolesExpand);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(0)); 
			String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountAfter, recordsCountBefore);	
			softAssert.assertAll();
			test.pass("New Allied Organization created successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("New AlliedOrganization creation failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("New Allied Organization creation failed");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 26) 
	public void AddProductsAlliedPartnerOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-41: Verify user can add/remove product for Allied Partner Organization");
			SoftAssert softAssert = new SoftAssert();

			if (size(alertClose)!=0) {
				click(alertClose);
			}

			for (int i=1;i<getDriver().findElements(By.cssSelector("tr")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(AlliedOrganizationName)) {
			//		if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals("Test_Allied_Org_1458")) {
					getDriver().findElement(By.xpath("//*[@title = 'Manage Products'][@id = 'edit-orgn-"+i+"']")).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}

			getScreenshot();
			Thread.sleep(1500);
			getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.AnceraLogo);
			softAssert.assertEquals(size(orgUploadProductImageSize), 0, "Product not added successfully");
			
			click(orgRemoveUploadedProduct);
			softAssert.assertEquals(size(orgUploadProductImageSize), 1, "Product not removed successfully");
			getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.AnceraLogo);
			type(orgAddProductName, "Allied Product 1");
			type(orgAddProductDescription, "Product for Allied Organization");
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(6)); 
			softAssert.assertAll();
			test.pass("Product added successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Product failed to add successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to add successfully");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 27) 
	public void VerifyProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-42: Verify product added from Organization Management screen is reflected on Company Products screen");
			SoftAssert softAssert = new SoftAssert();
			
			getDriver().get(url_companyProducts);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
					
			for (int i=1;i<getDriver().findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(AlliedOrganizationName)) {
					softAssert.assertFalse(getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(""), "Product name is empty");
					softAssert.assertFalse(getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5) label")).getText().equals(""), "Product description is empty");
					break;
				}
			}	
			softAssert.assertAll();
			test.pass("Product reflected successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);

		}catch(AssertionError er){
			test.fail("Product did not reflected successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to add successfully");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}

	
	@Test (enabled= true, priority= 28) 
	public void AddProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-43: Verify user can add product in Company Products screen");
			SoftAssert softAssert = new SoftAssert();

			getDriver().get(url_companyProducts);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			click(CompanyProductCreateNewButton);
			waitElementInvisible(loading_cursor);

			getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.AnceraLogo);
			softAssert.assertEquals(size(orgUploadProductImage), 0, "Product not added successfully");
			
			click(orgRemoveUploadedProduct);
			softAssert.assertEquals(size(orgUploadProductImage), 1, "Product not removed successfully");

			getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.AnceraLogo);
			type(CompanyProductNameInput, AlliedOrganizationName);
			getDriver().findElement(CompanyProductNameInput).sendKeys(Keys.ENTER);
			type(orgAddProductName, AlliedOrganizationNameCompanyProduct);
			type(orgAddProductDescription, "Product for Allied Organization");
			click(popupSaveButtonXpath);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(6)); 
			softAssert.assertAll();
			test.pass("Product added successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Product failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 29) 
	public void VerifyProductInOrganizationScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-44: Verify product added from Company Products is reflected on Organization Management screen screen");
			SoftAssert softAssert = new SoftAssert();
			
			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
			
			for (int i=1;i<=getDriver().findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				System.out.println(getText(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")));
					if (getText(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).equals(AlliedOrganizationName)) {
						System.out.println("1gf");
						click(By.xpath("//*[@title = 'Manage Products'][@id = 'edit-orgn-"+i+"']"));
						System.out.println("12");
						waitElementInvisible(loading_cursor);
						break;
					}
				}	
			
			softAssert.assertEquals(size(orgGetTotalCreatedProducts), 2, "Product created from Company Product screen is not reflected on Organization Screen");
			softAssert.assertAll();
			test.pass("Product reflected successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);

		}catch(AssertionError er){
			test.fail("Product did not reflected successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to add successfully");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 30) 
	public void DeleteProductsAlliedPartnerOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-45: Verify user can delete product for Allied Partner Organization");
			SoftAssert softAssert = new SoftAssert();

			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			scroll(orgDeleteUploadedProduct);
			Thread.sleep(800);
			click(orgDeleteUploadedProduct);
			waitElementVisible(popupYesButton);
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(6)); 
			test.pass("Product deleted successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Product failed to deleted successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed to deleted successfully");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 31) 
	public void EditProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-46: Verify product can be edited in Company Product Screen");
			SoftAssert softAssert = new SoftAssert();
			
			getDriver().get(url_companyProducts);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
					
			for (int i=1;i<getDriver().findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(AlliedOrganizationName)) {
					int j = i-1;
					click(By.id(CompanyNameProductEditButton+""+j));
					waitElementInvisible(loading_cursor);
					break;
				}
			}	
			
			type(orgAddProductDescription, "Product for Allied Organization Description");
			waitElementClickable(popupSaveButtonXpath);
			click(popupSaveButtonXpath);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(8)); 
			
			softAssert.assertAll();
			test.pass("Product updated successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);

		}catch(AssertionError er){
			test.fail("Product failed update successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product failed update successfully");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (enabled= false, priority= 32)
	public void DeleteProductInCompanyProductsScreen() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-47: Verify product can be deleted in Company Product Screen");
			SoftAssert softAssert = new SoftAssert();

			if (size(alertClose) != 0) {
				click(alertClose);
			}

			for (int i=1;i<=getDriver().findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(AlliedOrganizationName)) {
					scroll(By.id(CompanyNameProductDeleteButton+""+i));
					Thread.sleep(1000);
					click(By.id(CompanyNameProductDeleteButton+""+i));
					waitElementInvisible(loading_cursor);
					break;
				}
			}	
			
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(7)); 
			
			softAssert.assertAll();
			test.pass("Product deleted successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);

		}catch(AssertionError er){
			test.fail("Product did not deleted successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Product did not deleted successfully");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}
	
	
	@Test (enabled= true, priority= 33) 
	public void DeleteAlliedOrganization() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-48: Verify Organization can be deleted and verify it from table and user dropdown as well");
			
			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			waitElementVisible(orgTitle);
			Thread.sleep(2000);

			String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();
			
			for (int i=1;i<getDriver().findElements(By.cssSelector("tr")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(AlliedOrganizationName)) {
					getDriver().findElement(By.id("delete-orgn-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}
			
			Thread.sleep(1000);
			click(confirmationYes);
			waitElementVisible(alertMessage);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(5)); 
			Thread.sleep(3500);
			String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter);
			
			softAssert.assertAll();
			test.pass("Organization deleted and verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Organization failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Organization failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	

	@Test (enabled= true, priority= 34)
	public void TestFilterCompanyProducts() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-49: Verify Allied Company and Product Name filter functionality");
			
			getDriver().get(url_companyProducts);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			
			SoftAssert softAssert = new SoftAssert();
			
			String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();
			
			click(By.id(productAlliedCompany+""+ShowFilter));
			waitElementInvisible(loading_cursor);
			click(By.cssSelector("#ul-"+productAlliedCompany+" li:nth-child(2) label"));
			click(By.id(productAlliedCompany+""+ApplyFilter));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getScreenshot();
			String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
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
			getScreenshot();
			String recordsCountAfter2 = getDriver().findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter2, "Product Name filter failed to apply");
			click(By.id(productName+""+ClearFilter));
			
			softAssert.assertAll();
			test.pass("Filters functionality verified successfully");
		
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("Filters functionality failed to verify successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Filters functionality failed to verify successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (priority = 35, enabled = true)
	public void WildcardCompanyProducts() throws InterruptedException, IOException {
		getDriver().get(url_companyProducts);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard(CompanyProductsTableName, "Company Products", 1);
	}


	@Test(priority= 36, enabled = true)
	public void SortingCompanyProducts() throws InterruptedException, IOException {
		Sorting(CompanyProductsTableName, "Company Products", 1);
	}
	
	
	@Test(priority= 37, enabled = true)
	public void PaginationCompanyProducts() throws InterruptedException, IOException {
		Pagination(CompanyProductsTableName, "Company Products");
	}
	
	
	@Test(priority= 38, enabled = true)
	public void RowsPerPageCompanyProducts() throws InterruptedException, IOException {
		RowsPerPage_();
	}
	
	
	@Test(priority= 39)
	public void ExportCSV() throws InterruptedException, IOException {
		getDriver().get(url_organization);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);	
		CSVExport("Organization Management", OrganizationManagementPage.orgCSVFileName, orgManagementTable, 1);
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
	}
	
}
