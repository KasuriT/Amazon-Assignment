package Test.Ancera.Profile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.ProfileModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;


public class ProfileSetting {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Profile_Setting"+Test_Variables.date+".html");	
		Test_Variables.spark.config().setReportName("Profile Setting Test Report"); 

		
		Helper.config();
		ConfigureLogin.login();
	}
	
	@Test (enabled= true, priority = 2) 
	public void NavigateProfile() throws InterruptedException, IOException {

			for(int i=0; i<Test_Variables.lstProfileNavigate.size();i++) {

			Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstProfileNavigate.get(i).testCaseNavigate);
	
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			
	        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode(Test_Variables.lstProfileNavigate.get(i).stepPage);
			Test_Variables.steps.createNode("2. Click on Profile Settings icon on top right of screen");
			
			Helper.driver.get(Test_Variables.lstProfileNavigate.get(i).url);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("open-profile")));
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Profile Setting", Constants.ProfileSettingReportPath));
			Helper.driver.findElement(By.id("open-profile")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-save-2")));
			Thread.sleep(500);
		//	String actual = Helper.driver.findElement(By.xpath(Test_Elements.getTitle)).getText();
		//	String expected = Test_Variables.profileTitle;

			try{
			//	Assert.assertEquals(actual, expected); 
				Assert.assertTrue(Helper.driver.findElement(By.id("firstNameId")).isDisplayed());
				Test_Variables.test.pass("User navigated successfully to Profile Setting page");
				Test_Variables.results.createNode("User navigates to Profile Setting page");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Profile Setting", Constants.ProfileSettingReportPath));
			}catch(AssertionError e){
				Test_Variables.test.fail("User did not navigated to Profile Setting page");
				Test_Variables.results.createNode("User does not navigates to Profile Setting page");
			}	
		}
	}
	
	
	@Test (enabled=false, priority = 3) 
	public void ValidationCheck() throws InterruptedException, IOException {
		String FirstNameError;
		String LastNameError;
		String PhoneNoError;

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);
		
		
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.profileButton)));
		Helper.driver.findElement(By.xpath(Test_Elements.profileButton)).click();
		

		for (ProfileModel objModel : Test_Variables.lstProfileMandatoryCheck) {
			FirstNameError = ""; 
			LastNameError = "";

			PhoneNoError = "";
			
			
			if (objModel.isOpenPage)
			{
				Helper.driver.get(Constants.url_user);
				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.profileButton)));
				Helper.driver.findElement(By.xpath(Test_Elements.profileButton)).click();
				Test_Variables.test.createNode("Click on Profile Setting button");

					}			
		
				Test_Variables.test = Test_Variables.extent.createTest(objModel.testCaseMandatoryCheck);

					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profileFirstName)).clear();
					Helper.driver.findElement(By.xpath(Test_Elements.profileFirstName)).sendKeys(objModel.firstName);
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profileLastName)).clear();
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profileLastName)).sendKeys(objModel.lastName);
					Thread.sleep(1000);
					if (objModel.cellCode)
					{
					Helper.driver.findElement(By.xpath(Test_Elements.profileCellCode)).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profileCellCodeSelect)).click();
					}
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profileCellNo)).clear();
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profileCellNo)).sendKeys(objModel.cellNo);
					if (objModel.phoneCode)
					{
					Helper.driver.findElement(By.xpath(Test_Elements.profilePhoneCode)).click();
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profilePhoneCodeSelect)).click();
					}
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profilePhoneNo)).clear();
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath(Test_Elements.profilePhoneNo)).sendKeys(objModel.phoneNo);

					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.profileSaveButton)));
					Helper.driver.findElement(By.xpath(Test_Elements.profileSaveButton)).click(); 

					if (objModel.CheckS1) 
					{
					Thread.sleep(2000);

					if (objModel.firstName.isEmpty() ) {
						if(Helper.driver.findElement(By.xpath(Test_Elements.profileFirstNameValidation)).isDisplayed()) {
							FirstNameError = Helper.driver.findElement(By.xpath(Test_Elements.profileFirstNameValidation)).getText();
						}
						Assert.assertEquals(FirstNameError, Test_Elements.profileFirstNameExpected); 
					}

					if (objModel.lastName.isEmpty() ) {
						if(Helper.driver.findElement(By.xpath(Test_Elements.profileLastNameValidation)).isDisplayed()) {
							LastNameError = Helper.driver.findElement(By.xpath(Test_Elements.profileLastNameValidation)).getText();
						}
						Assert.assertEquals(LastNameError, Test_Elements.profileLastNameExpected); 
					}

					if (objModel.phoneNo.isEmpty() ) {
						if(Helper.driver.findElement(By.xpath(Test_Elements.profilePhoneNoValidation)).isDisplayed()) {
							PhoneNoError = Helper.driver.findElement(By.xpath(Test_Elements.profilePhoneNoValidation)).getText();
						}
						Assert.assertEquals(PhoneNoError, Test_Elements.profilePhoneNoExpected); 
					}
					continue;
					}

					else
					{
						Assert.assertFalse(true);
					}
		}

	}

	
	@Test (enabled= true, priority = 4) 
	public void ExitProfile() throws InterruptedException, IOException {
		
		Test_Variables.test = Test_Variables.extent.createTest("AN-PS-24: Exit Profile Screen", "This test case will verify user can exit profile screen" );
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
		
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.steps.createNode("1. Click on Profile Setting icon on top right of screen; Profile setting page opens");
		Test_Variables.steps.createNode("2. Again click on it");
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.profileButton)));
		//Thread.sleep(2000);
		Helper.driver.findElement(By.xpath(Test_Elements.profileButton)).click();
		Test_Variables.test.createNode("Click on Profile Setting button");
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Profile Setting", Constants.ProfileSettingReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.profileBackButton)).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);

		try{
			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText(), "User Management"); 
			Test_Variables.test.pass("User successfully closed profile page");
			Test_Variables.results.createNode("User successfully closed profile page");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Profile Setting", Constants.ProfileSettingReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("User failed to close profile page");
			Test_Variables.results.createNode("User failed to close profile page");
		}	
		
	}
	
	

	@AfterMethod
	public void saveResult(ITestResult result) throws IOException {
		Helper.saveResult(result, Constants.ProfileSettingReportPath);
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}
