package Test.Ancera.Administration;

import java.io.IOException;
import java.util.*;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import MiscFunctions.FrameworkConstants;
import MiscFunctions.RetryFailedCases;
import PageObjects.OrganizationManagementPage;
import PageObjects.UserManagementPage;
import Test.Ancera.Login.LoginTest;
import Utilities.DataUtil;
import Utilities.ExcelReader;

import static Models.ForgotPasswordModel.*;
import static PageObjects.UserManagementPage.*;
import static PageObjects.BasePage.*;
import static PageObjects.ForgotPasswordPage.*;
import static PageObjects.LoginPage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static LogViewFunctions.RowsPerPage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static LogViewFunctions.LogCSVExport.*;
import static Models.UserManagementModel.*;


public class UserManagement extends BaseTest{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_User_Management"+DateUtil.date+".html");
		spark.config().setReportName("User Management Test Report"); 
	}


	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}


	@Test (priority = 1) 
	public void LockFilter() throws InterruptedException, IOException {
		getDriver().get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		Lock(userManagementTable, "User Management", 0);
	}

	
	@Test (priority = 2) 
	public void WildcardUser() throws InterruptedException, IOException {
		getDriver().get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		Wildcard(userManagementTable, "User Management", 0);
	}


	@Test(priority= 3)
	public void sorting() throws InterruptedException, IOException {
		getDriver().get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		Sorting(userManagementTable, "User Management", 0);
	}
	
	@Test(priority= 4)
	public void pagination() throws InterruptedException, IOException {
		Pagination(userManagementTable, "User Management", ReportFilePath);
	}
	
	@Test(priority= 5)
	public void rowsperPage() throws InterruptedException, IOException {
		getDriver().get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		RowsPerPage();
	}


	@Test(priority= 6)
	public void ExportCSV() throws InterruptedException, IOException {
		CSVExport("User Management", userCSVFileName, userManagementTable, 1);
	}


	@Test (enabled= false, priority= 7) 
	public void OpenClosePopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-02: Verify user can open and close Create New User Popup", "This test case will verify that user is able to open and close create new user popup");
			steps = test.createNode(Scenario.class, Steps);
			steps.createNode("1. Click on Create New button");

			getDriver().get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(2000);
			
			click(usercreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElement(By.cssSelector(".pop-head")).getText(), "Create User", "Popup failed to open"); 

			click(popupCloseButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(getDriver().findElements(popupNextButton).size(), 0); 
			test.pass("User popup window opened and closed successfully");
			results.createNode("User popup window opened and closed successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);

		}catch(AssertionError er){
			test.fail("User popup window did not open or closed successfully");
			results.createNode("User popup window did not open or closed successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
	}


	@Test(enabled= true, priority= 7)
	public void DeleteEmail() throws InterruptedException, IOException {
		test = extent.createTest("AN-FP-00: Delete all emails from gmail");

		((JavascriptExecutor)getDriver()).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));

		getDriver().get(Constants.url_GmailSignin);
		type(By.xpath(gmailEmail), createUserEmail);
		Thread.sleep(1500);
		enterKey(By.xpath(gmailEmail));

		type(By.xpath(gmailPassword), createUserPassword);
		Thread.sleep(1500);
		enterKey(By.xpath(gmailPassword));
		Thread.sleep(3000);

		if (size(gmailSecurityCheck) != 0) {
			click(gmailSecurityCheck);
			Thread.sleep(3000);
			type(gmailSecurityEmail, createUserSecurityEmail);
			enterKey(gmailSecurityEmail);

			if (size(gmailNotNow) != 0) {
				click(gmailNotNow);
			}
		}

		getDriver().findElement(By.xpath("//*[@id=\":1y\"]/div[1]/span")).click();
		Thread.sleep(1500);
		if (size(By.cssSelector("div[data-tooltip='Delete']")) != 0) {
			getDriver().findElement(By.cssSelector("div[data-tooltip='Delete']")).click();
		}
		Thread.sleep(1500);

		ArrayList<String> tabs2 = new ArrayList<String> (getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs2.get(1));
		getDriver().close();
		getDriver().switchTo().window(tabs2.get(0));

	}




	@Test (enabled= true, priority= 8) 
	public void CreateUser() throws InterruptedException, IOException {
		try{
			test = ExtentVariables.extent.createTest("AN-UM-03: Verify user can create a user");
			steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
			results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

			steps.createNode("1. Enter valid data in all fields and click on Save button");

			getDriver().get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(2000);
			
			SoftAssert softAssert = new SoftAssert();

			click(By.id(userEmail+""+ShowFilter));
			waitElementInvisible(loading_cursor);
			type(By.id(userEmail+""+SearchInput), createUserEmail);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			
			if (size(By.xpath("//b[text() = '"+createUserEmail+"']")) != 0) {
				click(By.id(userEmail+""+SelectAll));
				click(By.id(userEmail+""+ApplyFilter ));
				waitElementInvisible(loading_cursor);
				Thread.sleep(1500);
				scroll(agreeementSearchedUser);
				waitElementClickable(deleteSearchedUser);
				Thread.sleep(1000);
				try {
				click(deleteSearchedUser);
				}
				catch(StaleElementReferenceException ex){
					click(deleteSearchedUser);
				}
				Thread.sleep(1500);
				click(popupYesButton);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
			}	
			else {
				click(By.id(userEmail+""+ShowFilter));
			}

			click(usercreateButton);
			waitElementInvisible(loading_cursor);
			waitElementClickable(userOrgTypeInput);			
			Thread.sleep(3000);
			type(userOrgTypeInput, "Client");
			enterKey(userOrgTypeInput);
			click(popupNextButton);
			softAssert.assertTrue(getDriver().findElement(userOrgTypeInput).isDisplayed());
			Thread.sleep(1000);

			click(userOrgInput);
			enterKey(userOrgInput);
			Thread.sleep(500);
			String OrganizationName = getText(getOrgName);
			
			softAssert.assertEquals(getDriver().findElements(siteAdministratorToggle).size(), 1, "Site Administrator button is not displayed");
			try {
				click(siteAdministratorToggle);
			}
			catch (ElementClickInterceptedException ex) {
				click(siteAdministratorToggle);
			}
			
			getDriver().findElement(popupNextButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			
			type(userFirstNameInput, "Ancera Test");
			type(userLastNameInput, "User");
			type(userEmailInput, createUserEmail); 
			getDriver().findElement(popupNextButton).click();

			if(getDriver().findElement(By.cssSelector("#emailId-error-container path:nth-child(1)")).isDisplayed())
			{
				getDriver().get(url_organization);
				waitElementInvisible(loading_cursor);
				waitElementVisible(OrganizationManagementPage.orgCreateButton);
				Thread.sleep(1000);
				OrganizationManagementPage.openEditOrgPopup(OrganizationName);
				waitElementInvisible(loading_cursor);
				click(orgDomains);
				Thread.sleep(1000);
				type(enterAllowDomain,"gmail.com");
				Thread.sleep(1000);
				click(OrganizationManagementPage.selectAllCheckBox);
				Thread.sleep(1000);
				click(OrganizationManagementPage.orgAllowDomainsExpand);
				Thread.sleep(1000);
				click(popupNextButton);
				waitElementVisible(popupSaveButton);
				click(popupSaveButton);
				waitElementVisible(alertMessage);
				
				getDriver().get(url_user);
				waitElementInvisible(loading_cursor);
				waitElementVisible(usercreateButton);
				Thread.sleep(3000);
				
				click(usercreateButton);
				waitElementInvisible(loading_cursor);
				waitElementClickable(userOrgTypeInput);			
				Thread.sleep(4000);
				type(userOrgTypeInput, "Client");
				enterKey(userOrgTypeInput);
				Thread.sleep(1000);

				click(userOrgInput);
				enterKey(userOrgInput);
				Thread.sleep(500);
				
				try {
					click(siteAdministratorToggle);
				}
				catch (ElementClickInterceptedException ex) {
					click(siteAdministratorToggle);
				}
				
				getDriver().findElement(popupNextButton).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				
				type(userFirstNameInput, "Ancera Test");
				type(userLastNameInput, "User");
				type(userEmailInput, createUserEmail); 
				getDriver().findElement(popupNextButton).click();
			}
	
			click(reportRoleExpand);
			enterKey(reportRoleSelect);
		
			click(systemRolesExpand);
			Thread.sleep(1000);
			type(systemRolesSelect, "Admin");
			enterKey(systemRolesSelect);
			click(systemRolesExpand);
			
			waitElementClickable(popupSaveButton);
			click(popupSaveButton); 
			waitElementVisible(alertMessage);
			Thread.sleep(1000);

			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New user created."); 
			softAssert.assertAll();
			test.pass("New User created successfully");
			results.createNode("New User created successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Failed to create a new user");
			results.createNode("Failed to create a new user");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Failed to create a new user");
			results.createNode("Failed to create a new user");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 9, retryAnalyzer = RetryFailedCases.class)
	public void VerifyEmail() throws InterruptedException, IOException {
		test = extent.createTest("AN-UM-04: Verify user receives an email to reset password");
		steps = test.createNode(Scenario.class, Steps);
		results = test.createNode(Scenario.class, Results);

		steps.createNode("1. Go to email account against which the user is created");
		steps.createNode("2. Check that mail to reset password is received or not");

		getDriver().get(Constants.url_GmailSignin);
		waitElementClickable(By.xpath(gmailEmail));
		Thread.sleep(5000);
		
		type(By.xpath(gmailEmail), createUserEmail);
		enterKey(By.xpath(gmailEmail));
		
		waitElementClickable(By.xpath(gmailPassword));
		Thread.sleep(4000);
		type(By.xpath(gmailPassword), createUserPassword);
		enterKey(By.xpath(gmailPassword));
		
		Thread.sleep(4000);
		if (size(gmailSecurityCheck) != 0) { 
			click(gmailSecurityCheck);
			Thread.sleep(3000);
			type(gmailSecurityEmail, createUserSecurityEmail);
			enterKey(gmailSecurityEmail);
			
			if (size(gmailNotNow) != 0) {
				click(gmailNotNow);
			}	
		}
		
		waitElementVisible(By.xpath("//*[@class='yW']/span"));
		getScreenshot();
		Thread.sleep(5000);
		List<WebElement> a = getDriver().findElements(By.xpath("//*[@class='yW']/span"));
		for(int i=0;i<a.size();i++){
			if(a.get(i).getText().equals("ancera.org.dev") || a.get(i).getText().equals("support")){  
				a.get(i).click();
			}
		}

		waitElementVisible(By.linkText("Create Password"));
		Thread.sleep(2000);
		if (getDriver().findElement(By.linkText("Create Password")).getText().equals("Create Password")) {
			test.pass("Reset password email received successfully");
			results.createNode("Email to reset password received successfully");
			getScreenshot();	
		}

		else {
			test.fail("Did not receive an email");
			results.createNode("Email to reset password did not received");
		}

		getDriver().findElement(By.linkText("Create Password")).click();
		Thread.sleep(1000);
		getDriver().findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();

		String currentTabHandle = getDriver().getWindowHandle();
		String newTabHandle = getDriver().getWindowHandles()
				.stream()
				.filter(handle -> !handle.equals(currentTabHandle ))
				.findFirst()
				.get();
		getDriver().switchTo().window(newTabHandle);
	}


	@Test (enabled= true, priority= 10) 
	public void ResetPassword() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-05: Verify user can set password and log in");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Click on the reset password link;  user redirects to application to set new password");
			steps.createNode("2. Enter email and newly set password; user should be logged in");

			waitElementVisible(enterNewPassword);
			getDriver().findElement(enterNewPassword).sendKeys(createUserPassword);
			getDriver().findElement(enterConfirmPassword).sendKeys(createUserPassword);
			getDriver().findElement(clickPasswordButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			getDriver().findElement(logoutButton).click();
			waitElementVisible(loginEmail);
			getDriver().findElement(loginEmail).sendKeys(createUserEmail);
			getDriver().findElement(loginPassword).sendKeys(createUserPassword);
			getDriver().findElement(loginButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);	
			
			if (size(AcceptAgreementonLogin) !=0) {
			click(AcceptAgreementonLogin);
			}
			
			Thread.sleep(1500);	
			
			getScreenshot();
			Assert.assertTrue(getDriver().findElement(By.id("Ancera Intelligence Engine")).isDisplayed(), "New user was not able to login into application");
			test.pass("Password was reset; user logged in successfully");
			results.createNode("Password was reset; user logged in successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("User failed to login");
			results.createNode("User failed to login");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User failed to login");
			results.createNode("User failed to login");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 11) 
	public void VerifyReportRole() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-06: Verify user can view assigned reports and agreement");
			steps = ExtentVariables.test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Assign report role and agreement to new user");

			getDriver().get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			UserManagementPage.openEditUserPopup(createUserEmail);
			click(popupCloseButton);
	
			click(agreeementSearchedUser);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElements(agreementList).size(), 1);
			click(popupCloseButton);

			getDriver().get(Constants.url_reports);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			Assert.assertNotEquals(getDriver().findElements(By.cssSelector(".report-img")).size(), 0);  //verify reports are visible in reports screen
			test.pass("Assigned reports were visible to the user successfully");
			results.createNode("Assigned reports were visible to the user successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Assigned reports were not visible to the user");
			results.createNode("Assigned reports were not visible to the user");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Assigned reports were not visible to the user");
			results.createNode("Assigned reports were not visible to the user");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 12) 
	public void VerifyTestingSitesAccess() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-07: Verify Sites column displays Active after assigning All Testing Sites to the user");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Assign All Collection Sites to the new user");
			steps.createNode("2. Verify user was able to assign All Testing Sites to the user");
			SoftAssert softAssert = new SoftAssert();

			getDriver().get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			UserManagementPage.openEditUserPopup(createUserEmail);
			waitElementVisible(userRoleCategory);
			waitElementClickable(popupNextButton);
			Thread.sleep(1000);
			click(popupNextButton);
			Thread.sleep(1000);
			click(popupNextButton);
			Thread.sleep(750);

			click(openUserSites);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(selectTestingSites);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(saveUserSites);
			Thread.sleep(1000);
			getScreenshot();
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "User details updated.");
		//	waitElementVisible(By.xpath("//*[text()= ' Active ']"));
			Thread.sleep(10000);
			softAssert.assertEquals(getDriver().findElement(By.cssSelector("#col-"+userSiteAccessCol+" label")).getText(), "Active");
			softAssert.assertAll();
			test.pass("Testing sites assigned successfully");
			results.createNode("Testing sites assigned successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Testing sites failed to assigned successfully");
			results.createNode("Testing sites failed to assigned successfully");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Testing sites failed to assigned successfully");
			results.createNode("Testing sites failed to assigned successfully");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority= 13)
	public void VerifyCollectionSitesAccess() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-08: Verify Sites column displays Active after assigning All Collection Sites to the user");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Assign All Collection Sites to the new user");
			steps.createNode("2. Verify user was able to assign All Collection Sites to the user");
			SoftAssert softAssert = new SoftAssert();
			
			click(editSearchedUser);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			click(popupNextButton);
			click(popupNextButton);
			Thread.sleep(750);

			click(openUserSites);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(selectColletionSites);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(saveUserSites);
			Thread.sleep(1000);
			getScreenshot();
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "User details updated.");
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			test.fail("Sites column failed to display Active after assigning Sites to the user");
			results.createNode("Sites column failed to display Active after assigning Sites to the user");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sites column failed to display Active after assigning Sites to the user");
			results.createNode("Sites column failed to display Active after assigning Sites to the user");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Update User", enabled = true, priority= 14) 
	public void UpdateUser() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-17: Verify user can update a user and convert user to piper user");
			steps = test.createNode(Scenario.class, ExtentVariables.Steps);
			results = test.createNode(Scenario.class, ExtentVariables.Results);
			steps.createNode("1. Click on update button next to created user; Update user popup appears");
			steps.createNode("2. Make any change and click on Save button");
			
			getDriver().get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			
			UserManagementPage.openEditUserPopup(createUserEmail);
			waitElementVisible(userRoleCategory);
			waitElementClickable(popupNextButton);
			Thread.sleep(1000);
			click(popupNextButton);
			waitElementVisible(userLastNameInput);
			clear(userLastNameInput);
			type(userLastNameInput, "User Updated");
			click(popupNextButton);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);

			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "User details updated."); 
			Assert.assertEquals(getDriver().findElement(By.cssSelector("#col-"+userLastNameCol+" label")).getText(), "User Updated", "Last name in popup not same as in table"); 

			test.pass("User updated successfully");
			results.createNode("User updated successfully; an alert message appears 'User details updated.'");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Failed to update the user");
			results.createNode("Failed to update the user");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("Failed to update the user");
			results.createNode("Failed to update the user");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= false, priority= 15) 
	public void EditAssignRolePopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-18: Verify user can be edited from assign roles and right popup", "This test case will verify that user can be edited from roles and right popup");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on assign roles and report button next to user");
			steps.createNode("2. Verify user is able to edit user from there");

			getDriver().get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			click(By.id(userEmail+""+ShowFilter ));
			waitElementInvisible(loading_cursor);
			type(By.id(userEmail+""+SearchInput ), createUserEmail);
			Thread.sleep(1500);
			getDriver().findElement(By.id(userEmail+""+SelectAll)).click();
			getDriver().findElement(By.id(userEmail+""+ApplyFilter )).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			click(By.cssSelector("tr:nth-child(1) #col-"+userRoleCol+" img"));
			waitElementInvisible(loading_cursor);
			Thread.sleep(2500);
			click(reportRoleExpand);
			getDriver().findElement(reportRoleSelect).sendKeys(Keys.ARROW_DOWN);
			getDriver().findElement(reportRoleSelect).sendKeys(Keys.ENTER);
			Thread.sleep(700);
			
			String Reporting = getDriver().findElement(reportRoleGetValue).getText();
			click(popupSaveButton);
			waitElementClickable(popupYesButton);
			Thread.sleep(750);
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(700);
			Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Roles and Rights has been updated successfully");
			Assert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-"+userReportingCol+" label")).getText(), Reporting);

			test.pass("User was able to edit from assign roles and reports successfully");
			results.createNode("User was able to edit from assign roles and reports successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("User was not able to edit from assign roles and reports");
			results.createNode("User was not able to edit from assign roles and reports");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User was not able to edit from assign roles and reports");
			results.createNode("User was not able to edit from assign roles and reports");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Site Admin New User Create", enabled= true, priority= 16) 
	public void SiteAdminNewUserCreate() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-19: Verify site admin is able to create new user", "This test case will verify that is able to create new user");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +Constants.url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on Administration and select User Management");
			steps.createNode("1. From Site Admin user create a new user");
			
			SoftAssert softAssert = new SoftAssert();
			
			getDriver().get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			click(usercreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(800);
			
			click(userOrgTypeInput);
			softAssert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option")).size(), 1, "Only 1 org type ws not shown");
			getScreenshot();
			getDriver().findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
			Thread.sleep(700);
			
			click(userOrgInput);
			softAssert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option")).size(), 1, "Only 1 organization was not shown");
			Thread.sleep(1000);
			getScreenshot();
			getDriver().findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
		
			softAssert.assertEquals(getDriver().findElements(siteAdministratorToggle).size(), 1, "Site Administrator button is not displayed");		
			click(popupNextButton);
			
			type(userFirstNameInput, "Ancera Test");
			type(userLastNameInput, "User");
			type(userEmailInput, "siteadminuser"+DateUtil.date0+"@gmail.com");
			click(popupNextButton);
			Thread.sleep(700);
			
			waitElementInvisible(loading_cursor);
			Thread.sleep(700);
			click(systemRolesExpand);
			Thread.sleep(1000);
			click(systemRoleSelect1);
			Thread.sleep(500);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2500);
			
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New user created."); 
			getScreenshot();

			click(By.id(userEmail+""+ShowFilter));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			type(By.id(userEmail+""+SearchInput), "siteadminuser"+DateUtil.date0+"@gmail.com");
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			click(By.id(userEmail+""+SelectAll));
			click(By.id(userEmail+""+ApplyFilter ));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			scroll(deleteSearchedUser);
			Thread.sleep(1000);

			try {
				click(deleteSearchedUser);
			}
			catch(StaleElementReferenceException ex){
				click(deleteSearchedUser);
			}

			waitElementClickable(popupYesButton);
			Thread.sleep(1000);
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "User details deleted."); 
			softAssert.assertAll();		
			getScreenshot();
		}
		catch(AssertionError er) {
			test.fail("User not created");
			results.createNode("User not created");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("User not created");
			results.createNode("User not created");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test (description="Test Case: Site Admin Sites Assigned", enabled= true, priority= 17) 
	public void SiteAdminSitesAssigned() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-21: Verify user can only see sites that are assigned to him", "This test case will verify that user can only see sites that are assigned to him");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			SoftAssert softAssert = new SoftAssert();
			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			click(OrganizationManagementPage.editSearchedOrg);
			waitElementInvisible(loading_cursor);

			int siteSizeOrg = getDriver().findElements(By.cssSelector(".tree-list-toggle")).size();  //42
			getScreenshot();
			getDriver().get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);
			waitElementVisible(editSearchedUser);
			getDriver().findElement(editSearchedUser).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(userRoleCategory);
			Thread.sleep(1000);
			getDriver().findElement(popupNextButton).click();
			Thread.sleep(700);
			getDriver().findElement(popupNextButton).click();
			Thread.sleep(800);
			getDriver().findElement(openUserSites).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getScreenshot();
			int siteSizeUser = getDriver().findElements(By.cssSelector(".form-check-label")).size();  //22

			int sites = (siteSizeOrg/2)+1;    //(42/2=21)+1 = 22
			softAssert.assertEquals(sites, siteSizeUser, "Site Admin is not able to see only the sites that are assigned to him");

			getDriver().get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			getDriver().findElement(By.id(OrganizationManagementPage.orgOrganzationType+""+ShowFilter)).click();		
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(By.cssSelector("#sort-orgnTypeName .filter-popup__footer--count")).getText(), "Showing 1 - 1 Results", "Site Admin is not able to see only his organization in org type filter");
			getDriver().findElement(By.id(OrganizationManagementPage.orgName+""+ShowFilter)).click();
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(By.cssSelector("#sort-orgnName .filter-popup__footer--count")).getText(), "Showing 1 - 1 Results", "Site Admin is not able to see only his organization in org filter");

			softAssert.assertAll();
			test.pass("Sites showed only those that are assigned to user successfully");
			results.createNode("Sites showed only those that are assigned to user successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Sites not showed only those that are assigned to user");
			results.createNode("Sites not showed only those that are assigned to user");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("Sites not showed only those that are assigned to user");
			results.createNode("Sites not showed only those that are assigned to user");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test (enabled= true, priority= 18) 
	public void OrgTypeColumn() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-22: Verify user can only see organization that is assigned to him", "This test case will verify that user can only see organization that is assigned to him");
			steps = test.createNode(Scenario.class, ExtentVariables.Steps);
			results = test.createNode(Scenario.class, ExtentVariables.Results);

			SoftAssert softAssert = new SoftAssert();
			getDriver().get(Constants.url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			softAssert.assertEquals(getDriver().findElement(By.id(ResultsCount)).getText(), "1", "Only assigned org is not displayed");

			String orgName = getDriver().findElement(By.cssSelector("tr:nth-child(1) td:nth-child(1) label")).getText();

			getDriver().get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			String totalRows = getDriver().findElement(By.id(ResultsCount)).getText();

			for (int i=1; i<=Integer.parseInt(totalRows);i++) {
				WebElement a = getDriver().findElement(By.cssSelector("tr:nth-child("+i+") #col-"+userOrgCol+" label"));
				softAssert.assertEquals(a.getText(), orgName);
			}

			softAssert.assertAll();
			test.pass("Created user deleted successfully");
			results.createNode("User deleted successfully; an alert message appears 'User details deleted.'");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test (enabled= true, priority= 19) 
	public void ClientMappingSiteAdmin() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-23: Verify user can only see organization that is assigned to him in client mapping", "This test case will verify that user can only see organization that is assigned to him in client mapping");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Open client mapping popup and verify only 1 org dislays assigned to Site Admin");

			getDriver().get(url_dataTemplate);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			getDriver().findElement(By.id("create-client-mapping")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(By.id("ClientId")).click();
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option")).size(), 1);					
			test.pass("Client mapping only showed site admin org");
			results.createNode("Client mapping only showed site admin org");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test (enabled= true, priority= 20) 
	public void DataUploadSiteAdmin() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-24: Verify user can only see organization that is assigned to him in data upload screen");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with Site Admin; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu; Click on MetaData and select Data Template Managemnt");
			steps.createNode("1. Open client mapping popup and verify only 1 org dislays assigned to Site Admin");

			getDriver().get(url_dataUpload);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(By.id("ClientId")).click();
			Thread.sleep(1000);
			Assert.assertEquals(getDriver().findElements(By.cssSelector(".ng-option")).size(), 1);					
			test.pass("Client mapping only showed site admin org");
			results.createNode("Client mapping only showed site admin org");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test (enabled= true, priority= 21)
	public void SiteAdminEditSites(Hashtable<String,String> data) throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-25: Verify user can edit sites of his organization", "This test case will verify that user can only see organization that is assigned to him in client mapping");
			steps = ExtentVariables.test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Open client mapping popup and verify only 1 org displays assigned to Site Admin");

		//	ExcelReader excel = new ExcelReader(FrameworkConstants.SUITE1_XL_PATH);
		//	DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);
			
			getDriver().get(Constants.url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			SoftAssert softAssert = new SoftAssert();

			click(OrganizationManagementPage.editSearchedOrg);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);	

			getDriver().findElement(OrganizationManagementPage.orgAddSite1).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			getDriver().findElement(OrganizationManagementPage.orgSiteTypeInputChild).click();   
			Thread.sleep(500);	
			getDriver().findElement(OrganizationManagementPage.orgSiteTypeDropDownValue).click();  

			getDriver().findElement(OrganizationManagementPage.orgSiteNameInput).sendKeys("Test Region");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(3000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			Set<String> deleteIcons = new HashSet<String>();
			getDriver().findElements(By.cssSelector("li .text-ellipsis"))
			.stream()
			.forEach(product -> deleteIcons.add(product.getText()));
			System.out.println("Total delete icon : "+deleteIcons.size());

			List<WebElement> a = getDriver().findElements(By.cssSelector(".delete")) ;

			int b = deleteIcons.size() - 2;
			a.get(b).click();
			Thread.sleep(2000);

			getDriver().findElement(popupYesButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(2000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Site details deleted successfully.");
			softAssert.assertAll();
			test.pass("Client mapping only showed site admin org");
			results.createNode("Client mapping only showed site admin org");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test (description="Test Case: Delete User", enabled= true, priority= 22) 
	public void DeleteUser() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-26: Verify user can be deleted");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Click on delete butotn next to created user; confirmation box appears");
			steps.createNode("2. Click on yes button");

			getDriver().findElement(logoutButton).click();
			waitElementVisible(loginEmail);
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			type(loginEmail, config.ie_username());
			type(loginPassword, config.ie_password());
			click(loginButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			
			getDriver().get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			String preRecords = getDriver().findElement(By.id(ResultsCount)).getText();

			getDriver().findElement(By.id(userEmail+""+ShowFilter )).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(By.id(userEmail+""+SearchInput )).sendKeys(createUserEmail);
			Thread.sleep(1200);
			getDriver().findElement(By.id(userEmail+""+SelectAll)).click();
			getDriver().findElement(By.id(userEmail+""+ApplyFilter )).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			
			scroll(agreeementSearchedUser);
			waitElementClickable(deleteSearchedUser);
			Thread.sleep(1000);
		
			try {
				click(deleteSearchedUser);
				}
				catch(StaleElementReferenceException ex){
					click(deleteSearchedUser);
				}
	
			waitElementClickable(popupYesButton);
			Thread.sleep(700);
			click(popupYesButton);

			waitElementVisible(alertMessage);
			Thread.sleep(5000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "User details deleted."); 
			String postRecords = getDriver().findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(preRecords, postRecords);
			softAssert.assertAll();
			test.pass("Created user deleted successfully");
			results.createNode("User deleted successfully; an alert message appears 'User details deleted.'");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test (enabled= false, priority =23) 
	public void AccessRoleCount() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-UM-65: Verify count of assign role in popup is same as that in table");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on assign roles and rights popup next to user and check the assign roles");
			steps.createNode("2. Verify the assign roles in table next to that user");

			getDriver().get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);
			
			getDriver().findElement(By.cssSelector("tr:nth-child(3) #col-"+userRoleCol+" img")).click();
			waitElementInvisible(loading_cursor);

			getDriver().findElement(systemRolesExpand).click();
			int roles = getDriver().findElements(systemRolesSelected).size();
			getDriver().findElement(popupCloseButton).click();
			Thread.sleep(1000);

			String s = getDriver().findElement(By.cssSelector("tr:nth-child(3) td:nth-child(7) label")).getText();
			int commas = 0;
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)==',') 
				{
					commas++;
				}
			}

			Assert.assertEquals(roles-1, commas);
			test.pass("Assigned roles verified successfully");
			results.createNode("Assigned roles verified successfully");
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Assigned roles were not same in popup and table");
			results.createNode("Assigned roles were not same in popup and table");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Assigned roles were not same in popup and table");
			results.createNode("Assigned roles were not same in popup and table");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
	}
	
}