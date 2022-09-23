package Test.Ancera.Administration;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
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

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.RetryFailedCases;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;


public class UserManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_User_Management"+date+".html");
		spark.config().setReportName("User Management Test Report"); 
		config();
		ConfigureLogin.login();
	}


	@Test (priority = 1) 
	public void LockFilter() throws InterruptedException, IOException {
		driver.get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		Lock(userManagementTable, "User Management", Constants.UserManagementReportPath, 0);
	}

	@Test (priority = 2) 
	public void WildcardUser() throws InterruptedException, IOException {
		driver.get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		Wildcard1(userManagementTable, "User Management", UserManagementReportPath, 0);
	}


	@Test(priority= 3)
	public void sorting() throws InterruptedException, IOException {
		driver.get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		Sorting1(userManagementTable, "User Management", UserManagementReportPath, 0);
	}
	
	@Test(priority= 4)
	public void pagination() throws InterruptedException, IOException {
		Pagination(userManagementTable, "User Management", UserManagementReportPath);
	}
	
	@Test(priority= 5)
	public void rowsperPage() throws InterruptedException, IOException {
		driver.get(Constants.url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		RowsPerPage1();
	}

	@Test(priority= 6)
	public void ExportCSV() throws InterruptedException, IOException {
		CSVExport("User Management", UserManagementReportPath, Test_Elements.userCSVFileName, userManagementTable, 1);
	}
	

	@Test (enabled= true, priority= 7) 
	public void OpenClosePopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-02: Verify user can open and close Create New User Popup", "This test case will verify that user is able to open and close create new user popup");
			steps.createNode("1. Click on Create New button");

			driver.get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);
			
			click(usercreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(By.cssSelector(".pop-head")).getText(), "Create User", "Popup failed to open"); 

			click(popupCloseButton);
			waitElementInvisible(loading_cursor);
			Assert.assertEquals(Helper.driver.findElements(popupNextButton).size(), 0); 
			test.pass("User popup window opened and closed successfully");
			results.createNode("User popup window opened and closed successfully");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);

		}catch(AssertionError er){
			test.fail("User popup window did not open or closed successfully");
			results.createNode("User popup window did not open or closed successfully");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}	
	}


	@Test (enabled= true, priority= 8) 
	public void CreateUser() throws InterruptedException, IOException {
		try{
			test = Test_Variables.extent.createTest("AN-UM-03: Verify user can create a user", "This test case will verify create new ancera user");
			steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			steps.createNode("1. Enter valid data in all fields and click on Save button");

			SoftAssert softAssert = new SoftAssert();

			driver.get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			waitElementClickable(usercreateButton);
			Thread.sleep(3000);

			click(By.id(userEmail+""+ShowFilter));
			waitElementInvisible(loading_cursor);
			type(By.id(userEmail+""+SearchInput), createUserEmail);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			
			if (driver.findElement(By.id(userEmail+""+SelectAll)).isDisplayed()) {
				click(By.id(userEmail+""+SelectAll));
				click(By.id(userEmail+""+ApplyFilter ));
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				scroll(deleteSearchedUser);
				waitElementClickable(deleteSearchedUser);
				Thread.sleep(1000);
				click(deleteSearchedUser);
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
			Thread.sleep(3000);

			WebElement firstName = driver.findElement(userFirstNameInput);
			firstName.sendKeys("Ancera Test");  
			driver.findElement(popupNextButton).click();
			softAssert.assertTrue(firstName.isDisplayed());

			WebElement lastName = driver.findElement(userLastNameInput);
			lastName.sendKeys("User");  
			driver.findElement(popupNextButton).click();
			softAssert.assertTrue(lastName.isDisplayed());

			WebElement email = driver.findElement(userEmailInput);
			email.sendKeys(createUserEmail);  
			driver.findElement(popupNextButton).click();
			waitElementInvisible(loading_cursor);

			WebElement orgType = driver.findElement(userOrgTypeInput);
			softAssert.assertTrue(orgType.isDisplayed());
			orgType.sendKeys("Client");
			orgType.sendKeys(Keys.ENTER);
			driver.findElement(popupNextButton).click();
			softAssert.assertTrue(orgType.isDisplayed());
			Thread.sleep(1000);

			WebElement org = driver.findElement(userOrgInput);
			org.click();
		//	org.sendKeys(Keys.DOWN);
		//	org.sendKeys(Keys.DOWN);
			org.sendKeys(Keys.ENTER);

			softAssert.assertEquals(driver.findElements(siteAdministratorToggle).size(), 1, "Site Administrator button is not displayed");
			driver.findElement(siteAdministratorToggle).click();

			driver.findElement(popupNextButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			
			click(reportRoleExpand);
			driver.findElement(reportRoleSelect).sendKeys(Keys.ENTER);

			click(AgreeementExpand);
			driver.findElement(AgreementSelect).sendKeys(Keys.ENTER);
		
			driver.findElement(systemRolesExpand).click();
			Thread.sleep(1000);
			driver.findElement(systemRolesSelect).sendKeys("Admin");
			driver.findElement(systemRolesSelect).sendKeys(Keys.ENTER);
			
			
			driver.findElement(popupSaveButton).click();    
			waitElementVisible(alertMessage);
			Thread.sleep(1000);

			softAssert.assertEquals(Helper.driver.findElement(alertMessage).getText(), "New user created."); 
			softAssert.assertAll();
			test.pass("New User created successfully");
			results.createNode("New User created successfully");
			test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}catch(AssertionError er) {
			test.fail("Failed to create a new user");
			results.createNode("Failed to create a new user");
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Failed to create a new user");
			results.createNode("Failed to create a new user");
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 9, retryAnalyzer = RetryFailedCases.class, dependsOnMethods = {"CreateUser"}) 
	public void VerifyEmail() throws InterruptedException, IOException {
		test = extent.createTest("AN-UM-04: Verify user receives an email to reset password");
		steps = test.createNode(Scenario.class, Steps);
		results = test.createNode(Scenario.class, Results);

		steps.createNode("1. Go to email account against which the user is created");
		steps.createNode("2. Check that mail to reset password is received or not");

		driver.get(Constants.url_GmailSignin);
		waitElementClickable(By.xpath(gmailEmail));
		Thread.sleep(5000);
		
		type(By.xpath(gmailEmail), createUserEmail);
		driver.findElement(By.xpath(gmailEmail)).sendKeys(Keys.ENTER);	
		
		waitElementClickable(By.xpath(gmailPassword));
		Thread.sleep(5000);
		type(By.xpath(gmailPassword), createUserPassword);
		driver.findElement(By.xpath(gmailPassword)).sendKeys(Keys.ENTER);

		if (driver.findElements(By.xpath(gmailSecurityCheck)).size() != 0) { 

			driver.findElement(By.xpath(gmailSecurityCheck)).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(gmailSecurityEmail)).sendKeys(createUserSecurityEmail);
			driver.findElement(By.xpath(gmailSecurityEmail)).sendKeys(Keys.ENTER);	

		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
		test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));

		List<WebElement> a = Helper.driver.findElements(By.xpath("//*[@class='yW']/span"));
		for(int i=0;i<a.size();i++){
			if(a.get(i).getText().equals("ancera.org.dev") || a.get(i).getText().equals("support")){  
				a.get(i).click();
			}
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create Password")));
		Thread.sleep(1000);
		if (driver.findElement(By.linkText("Create Password")).getText().equals("Create Password")) {

			test.pass("Reset password email received successfully");
			results.createNode("Email to reset password received successfully");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));	
		}

		else {
			test.fail("Did not receive an email");
			results.createNode("Email to reset password did not received");
		}

		driver.findElement(By.linkText("Create Password")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();

		String currentTabHandle = driver.getWindowHandle();
		String newTabHandle = driver.getWindowHandles()
				.stream()
				.filter(handle -> !handle.equals(currentTabHandle ))
				.findFirst()
				.get();
		driver.switchTo().window(newTabHandle);
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
			driver.findElement(enterNewPassword).sendKeys(createUserPassword);
			driver.findElement(enterConfirmPassword).sendKeys(createUserPassword);
			driver.findElement(clickPasswordButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			driver.findElement(logoutButton).click();
			waitElementVisible(loginEmail);
			driver.findElement(loginEmail).sendKeys(createUserEmail);
			driver.findElement(loginPassword).sendKeys(createUserPassword);
			driver.findElement(loginButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);	
			
			if (size(AcceptAgreementonLogin) !=0) {
			click(AcceptAgreementonLogin);
			}
			
			Thread.sleep(1500);	
			
			test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", UserManagementReportPath));
			Assert.assertTrue(Helper.driver.findElement(By.id("Ancera Intelligence Engine")).isDisplayed(), "New user was not able to login into application");
			test.pass("Password was reset; user logged in successfully");
			results.createNode("Password was reset; user logged in successfully");
			test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("User failed to login");
			results.createNode("User failed to login");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User failed to login");
			results.createNode("User failed to login");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 11) 
	public void VerifyReportRole() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-06: Verify user can view assigned reports and agreement");
			steps = Test_Variables.test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Assign report role and agreement to new user");

			driver.get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			openEditUserPopup(createUserEmail);
			click(popupCloseButton);
	
			click(agreeementSearchedUser);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElements(agreementList).size(), 1);
			click(popupCloseButton);

			driver.get(Constants.url_reports);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			Assert.assertNotEquals(Helper.driver.findElements(By.cssSelector(".report-img")).size(), 0);  //verify reports are visible in reports screen
			test.pass("Assigned reports were visible to the user successfully");
			results.createNode("Assigned reports were visible to the user successfully");
			test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Assigned reports were not visible to the user");
			results.createNode("Assigned reports were not visible to the user");  
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Assigned reports were not visible to the user");
			results.createNode("Assigned reports were not visible to the user");  	
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
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

			driver.get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			openEditUserPopup(createUserEmail);
			click(popupNextButton);
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
			test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "User details updated.");
		//	waitElementVisible(By.xpath("//*[text()= ' Active ']"));
			Thread.sleep(10000);
			softAssert.assertEquals(driver.findElement(By.cssSelector("#col-"+userSiteAccessCol+" label")).getText(), "Active");
			softAssert.assertAll();
			test.pass("Testing sites assigned successfully");
			results.createNode("Testing sites assigned successfully");
			test.addScreenCaptureFromPath(getScreenshot("User Management", Constants.UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Testing sites failed to assigned successfully");
			results.createNode("Testing sites failed to assigned successfully");  
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Testing sites failed to assigned successfully");
			results.createNode("Testing sites failed to assigned successfully");  	
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 13, dependsOnMethods = {"VerifyTestingSitesAccess"}) 
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
			test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "User details updated.");
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			test.fail("Sites column failed to display Active after assigning Sites to the user");
			results.createNode("Sites column failed to display Active after assigning Sites to the user");  
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sites column failed to display Active after assigning Sites to the user");
			results.createNode("Sites column failed to display Active after assigning Sites to the user");  	
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Update User", enabled = true, priority= 14) 
	public void UpdateUser() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-17: Verify user can update a user and convert user to piper user");
			steps = test.createNode(Scenario.class, Test_Variables.Steps);
			results = test.createNode(Scenario.class, Test_Variables.Results);
			steps.createNode("1. Click on update button next to created user; Update user popup appears");
			steps.createNode("2. Make any change and click on Save button");

			driver.get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			openEditUserPopup(createUserEmail);
			Thread.sleep(3000);
			clear(userLastNameInput);
			type(userLastNameInput, "User Updated");
			click(popupNextButton);
			click(popupNextButton);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);

			Assert.assertEquals(driver.findElement(alertMessage).getText(), "User details updated."); 
			Assert.assertEquals(driver.findElement(By.cssSelector("#col-"+Test_Elements.userLastNameCol+" label")).getText(), "User Updated", "Last name in popup not same as in table"); 

			test.pass("User updated successfully");
			results.createNode("User updated successfully; an alert message appears 'User details updated.'");
			test.addScreenCaptureFromPath(getScreenshot("User Management", Constants.UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, Constants.UserManagementReportPath, null);
		}catch(AssertionError er) {
			test.fail("Failed to update the user");
			results.createNode("Failed to update the user");
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("Failed to update the user");
			results.createNode("Failed to update the user");
			saveResultNew(ITestResult.FAILURE, Constants.UserManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 15) 
	public void EditAssignRolePopup() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-18: Verify user can be edited from assign roles and right popup", "This test case will verify that user can be edited from roles and right popup");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Click on assign roles and report button next to user");
			steps.createNode("2. Verify user is able to edit user from there");

			driver.get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			click(By.id(userEmail+""+ShowFilter ));
			waitElementInvisible(loading_cursor);
			type(By.id(userEmail+""+SearchInput ), createUserEmail);
			Thread.sleep(1500);
			driver.findElement(By.id(userEmail+""+SelectAll)).click();
			driver.findElement(By.id(userEmail+""+ApplyFilter )).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			click(By.cssSelector("tr:nth-child(1) #col-"+userRoleCol+" img"));
			waitElementInvisible(loading_cursor);
			Thread.sleep(2500);
			click(reportRoleExpand);
			driver.findElement(reportRoleSelect).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(reportRoleSelect).sendKeys(Keys.ENTER);
			Thread.sleep(700);
			
			String Reporting = Helper.driver.findElement(reportRoleGetValue).getText();
			click(popupSaveButton);
			Thread.sleep(750);
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(700);
			Assert.assertEquals(driver.findElement(alertMessage).getText(), "Roles and Rights has been updated successfully");
			Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+userReportingCol+" label")).getText(), Reporting);

			test.pass("User was able to edit from assign roles and reports successfully");
			results.createNode("User was able to edit from assign roles and reports successfully");
			test.addScreenCaptureFromPath(getScreenshot("User Management", Constants.UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("User was not able to edit from assign roles and reports");
			results.createNode("User was not able to edit from assign roles and reports");  
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User was not able to edit from assign roles and reports");
			results.createNode("User was not able to edit from assign roles and reports");  	
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
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

			driver.get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			click(usercreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(800);
			type(userFirstNameInput, "Ancera Test");
			type(userLastNameInput, "User");
			type(userEmailInput, "siteadminuser@anc.com");
			click(popupNextButton);
			Thread.sleep(700);
			SoftAssert softAssert = new SoftAssert();
			click(userOrgTypeInput);
			softAssert.assertEquals(driver.findElements(By.cssSelector(".ng-option")).size(), 1);
			driver.findElement(By.cssSelector("#orgTypeId input")).sendKeys(Keys.ENTER);
			Thread.sleep(700);
			
			click(userOrgInput);
			softAssert.assertEquals(driver.findElements(By.cssSelector(".ng-option")).size(), 1);
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
		
			softAssert.assertEquals(driver.findElements(siteAdministratorToggle).size(), 1, "Site Administrator button is not displayed");		
			click(popupNextButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(700);
			click(systemRolesExpand);
			Thread.sleep(1000);
			click(systemRoleSelect1);
			Thread.sleep(500);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2500);
			
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New user created."); 
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));

			click(By.id(userEmail+""+ShowFilter));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			type(By.id(userEmail+""+SearchInput), "siteadminuser@anc.com");
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			click(By.id(userEmail+""+SelectAll));
			click(By.id(userEmail+""+ApplyFilter ));
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);
			scroll(deleteSearchedUser);
			Thread.sleep(1000);
			click(deleteSearchedUser);
			Thread.sleep(1500);
			click(popupYesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "User details deleted."); 
			softAssert.assertAll();		
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
		}
		catch(AssertionError er) {
			test.fail("User not created");
			results.createNode("User not created");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("User not created");
			results.createNode("User not created");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
		}	
	}


	@Test (description="Test Case: Site Admin Sites Assigned", enabled= true, priority= 17) 
	public void SiteAdminSitesAssigned() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-21: Verify user can only see sites that are assigned to him", "This test case will verify that user can only see sites that are assigned to him");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			SoftAssert softAssert = new SoftAssert();
			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			click(editSearchedOrg);
			waitElementInvisible(loading_cursor);

			int siteSizeOrg = driver.findElements(By.cssSelector(".tree-list-toggle")).size();  //42

			driver.get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);
			driver.findElement(editSearchedUser).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(popupNextButton).click();
			Thread.sleep(700);
			driver.findElement(popupNextButton).click();
			Thread.sleep(800);
			driver.findElement(openUserSites).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			int siteSizeUser = driver.findElements(By.cssSelector(".form-check-label")).size();  //22

			int sites = (siteSizeOrg/2)+1;    //(42/2=21)+1 = 22
			softAssert.assertEquals(sites, siteSizeUser, "Site Admin is not able to see only the sites that are assigned to him");

			driver.get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			driver.findElement(By.id(orgOrganzationType+""+ShowFilter)).click();		
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(By.cssSelector("#sort-orgnTypeName .filter-popup__footer--count")).getText(), "Showing 1 - 1 Results", "Site Admin is not able to see only his organization in org type filter");
			driver.findElement(By.id(orgName+""+ShowFilter)).click();
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(By.cssSelector("#sort-orgnName .filter-popup__footer--count")).getText(), "Showing 1 - 1 Results", "Site Admin is not able to see only his organization in org filter");

			softAssert.assertAll();
			test.pass("Sites showed only those that are assigned to user successfully");
			results.createNode("Sites showed only those that are assigned to user successfully");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Sites not showed only those that are assigned to user");
			results.createNode("Sites not showed only those that are assigned to user");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("Sites not showed only those that are assigned to user");
			results.createNode("Sites not showed only those that are assigned to user");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
		}	
	}


	@Test (enabled= true, priority= 18) 
	public void OrgTypeColumn() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-22: Verify user can only see organization that is assigned to him", "This test case will verify that user can only see organization that is assigned to him");
			steps = test.createNode(Scenario.class, Test_Variables.Steps);
			results = test.createNode(Scenario.class, Test_Variables.Results);

			SoftAssert softAssert = new SoftAssert();
			driver.get(Constants.url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			softAssert.assertEquals(driver.findElement(By.id(ResultsCount)).getText(), "1", "Only assigned org is not displayed");

			String orgName = driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(1) label")).getText();

			Helper.driver.get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			String totalRows = driver.findElement(By.id(ResultsCount)).getText();

			for (int i=1; i<=Integer.parseInt(totalRows);i++) {
				WebElement a = driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+userOrgCol+" label"));
				softAssert.assertEquals(a.getText(), orgName);
			}

			softAssert.assertAll();
			test.pass("Created user deleted successfully");
			results.createNode("User deleted successfully; an alert message appears 'User details deleted.'");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
		}	
	}


	@Test (enabled= true, priority= 19) 
	public void ClientMappingSiteAdmin() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-23: Verify user can only see organization that is assigned to him in client mapping", "This test case will verify that user can only see organization that is assigned to him in client mapping");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Open client mapping popup and verify only 1 org dislays assigned to Site Admin");

			driver.get(url_dataTemplate);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			driver.findElement(By.id("create-client-mapping")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(By.id("ClientId")).click();
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option")).size(), 1);					
			test.pass("Client mapping only showed site admin org");
			results.createNode("Client mapping only showed site admin org");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
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

			Helper.driver.get(url_dataUpload);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(By.id("ClientId")).click();
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option")).size(), 1);					
			test.pass("Client mapping only showed site admin org");
			results.createNode("Client mapping only showed site admin org");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
		}	
	}


	@Test (enabled= false, priority= 21) 
	public void SiteAdminEditSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-25: Verify user can edit sites of his organization", "This test case will verify that user can only see organization that is assigned to him in client mapping");
			steps = Test_Variables.test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			steps.createNode("1. Open client mapping popup and verify only 1 org displays assigned to Site Admin");

			driver.get(Constants.url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			SoftAssert softAssert = new SoftAssert();

			click(editSearchedOrg);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);	

			driver.findElement(orgAddSite1).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			driver.findElement(orgSiteTypeInputChild).click();   
			Thread.sleep(500);	
			driver.findElement(orgSiteTypeDropDownValue).click();  

			driver.findElement(orgSiteNameInput).sendKeys("Test Region");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(3000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			Set<String> deleteIcons = new HashSet<String>();
			driver.findElements(By.cssSelector("li .text-ellipsis"))
			.stream()
			.forEach(product -> deleteIcons.add(product.getText()));
			System.out.println("Total delete icon : "+deleteIcons.size());

			List<WebElement> a = driver.findElements(By.cssSelector(".delete")) ;

			int b = deleteIcons.size() - 2;
			a.get(b).click();
			Thread.sleep(2000);

			driver.findElement(popupYesButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(2000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Site details deleted successfully.");
			softAssert.assertAll();
			test.pass("Client mapping only showed site admin org");
			results.createNode("Client mapping only showed site admin org");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("Client mapping not nly showed site admin org");
			results.createNode("Client mapping not nly showed site admin org");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
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

			driver.findElement(logoutButton).click();
			waitElementVisible(loginEmail);

			type(loginEmail, login_email);
			type(loginPassword, login_password);
			click(loginButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			
			driver.get(url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);

			String preRecords = driver.findElement(By.id(ResultsCount)).getText();

			driver.findElement(By.id(userEmail+""+ShowFilter )).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(By.id(userEmail+""+SearchInput )).sendKeys(createUserEmail);
			Thread.sleep(1200);
			driver.findElement(By.id(userEmail+""+SelectAll)).click();
			driver.findElement(By.id(userEmail+""+ApplyFilter )).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			scroll(deleteSearchedUser);
			Thread.sleep(700);
			driver.findElement(deleteSearchedUser).click();
			Thread.sleep(1500);
			driver.findElement(popupYesButton).click();
			waitElementVisible(alertMessage);
			Thread.sleep(5000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "User details deleted."); 
			String postRecords = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(preRecords, postRecords);
			softAssert.assertAll();
			test.pass("Created user deleted successfully");
			results.createNode("User deleted successfully; an alert message appears 'User details deleted.'");
			test.addScreenCaptureFromPath(getScreenshot("User Management", UserManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("Created user failed to delete");
			results.createNode("Created user failed to delete");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
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

			driver.get(Constants.url_user);
			waitElementInvisible(loading_cursor);
			waitElementVisible(usercreateButton);
			Thread.sleep(3000);
			
			driver.findElement(By.cssSelector("tr:nth-child(3) #col-"+userRoleCol+" img")).click();
			waitElementInvisible(loading_cursor);

			driver.findElement(systemRolesExpand).click();
			int roles = driver.findElements(systemRolesSelected).size();
			driver.findElement(popupCloseButton).click();
			Thread.sleep(1000);

			String s = driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(7) label")).getText();
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
			saveResultNew(ITestResult.SUCCESS, UserManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Assigned roles were not same in popup and table");
			results.createNode("Assigned roles were not same in popup and table");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Assigned roles were not same in popup and table");
			results.createNode("Assigned roles were not same in popup and table");
			saveResultNew(ITestResult.FAILURE, UserManagementReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//Helper.driver.close();
	}
}