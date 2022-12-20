package Test.Ancera.Profile;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import MiscFunctions.Helper;
import Test.Ancera.Login.LoginTest;

import static Models.ProfileModel.*;


public class ProfileSetting{

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/Profile_Setting"+DateUtil.date+".html");	
		ExtentVariables.spark.config().setReportName("Profile Setting Test Report"); 
		Helper.config();
		LoginTest.login();
	}

	@Test (enabled= true, priority = 2) 
	public void NavigateProfile() throws InterruptedException, IOException {
		try{
			for(int i=0; i<lstProfileNavigate.size();i++) {
				try {
					ExtentVariables.test = ExtentVariables.extent.createTest(lstProfileNavigate.get(i).testCaseNavigate);

					ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
					ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
					ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

					ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
					ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					ExtentVariables.steps.createNode(lstProfileNavigate.get(i).stepPage);
					ExtentVariables.steps.createNode("2. Click on Profile Settings icon on top right of screen");

					Helper.driver.get(lstProfileNavigate.get(i).url);
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Constants.wait.until(ExpectedConditions.elementToBeClickable(By.id("open-profile")));
					Helper.getScreenshot();
					Helper.driver.findElement(By.id("open-profile")).click();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Constants.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-save-2")));
					Thread.sleep(500);

					Assert.assertTrue(Helper.driver.findElement(By.id("firstNameId")).isDisplayed());
					Helper.driver.findElement(By.id("close-profile")).click();
					Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(500);
					Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#screen-header p")).getText(), lstProfileNavigate.get(i).pageTitle);

					ExtentVariables.test.pass("User navigated successfully to Profile Setting page");
					ExtentVariables.results.createNode("User navigates to Profile Setting page");
					Helper.getScreenshot();
					Helper.saveResult(ITestResult.SUCCESS, null);
				}
				catch(AssertionError er) {
					ExtentVariables.test.fail("User did not navigated to Profile Setting page");
					ExtentVariables.results.createNode("User did not navigated to Profile Setting page");
					Helper.saveResult(ITestResult.FAILURE, new Exception(er));
				}			
			}
		}
		catch(Exception ex){
			ExtentVariables.test.fail("User did not navigated to Profile Setting page");
			ExtentVariables.results.createNode("User did not navigated to Profile Setting page");
			Helper.saveResult(ITestResult.FAILURE, ex);
		}	
	}


	

	@Test (enabled= true, priority = 4) 
	public void ExitProfile() throws InterruptedException, IOException {

		ExtentVariables.test = ExtentVariables.extent.createTest("AN-PS-24: Exit Profile Screen", "This test case will verify user can exit profile screen" );
		ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
		ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
		ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

		ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
		ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		ExtentVariables.steps.createNode("1. Click on Profile Setting icon on top right of screen; Profile setting page opens");
		ExtentVariables.steps.createNode("2. Again click on it");
		Helper.driver.get(Constants.url_user);
		Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("open-profile")).click();
		ExtentVariables.test.createNode("Click on Profile Setting button");
		Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.getScreenshot();
		Helper.driver.findElement(By.id("close-profile")).click();
		Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);

		try{
			Assert.assertEquals(Helper.driver.findElement(By.id("User Management")).getText(), "User Management"); 
			ExtentVariables.test.pass("User successfully closed profile page");
			ExtentVariables.results.createNode("User successfully closed profile page");
			Helper.getScreenshot();
		}catch(AssertionError e){
			ExtentVariables.test.fail("User failed to close profile page");
			ExtentVariables.results.createNode("User failed to close profile page");
		}	

	}


	@AfterTest
	public static void endreport() {
		ExtentVariables.extent.flush();
		//Helper.driver.close();
	}
}
