package Test.Ancera.Profile;

import java.io.IOException;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import MiscFunctions.Methods;
import PageObjects.BasePage;
import Test.Ancera.Login.LoginTest;

import static Models.ProfileModel.*;


public class ProfileSetting extends BaseTest{

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/Profile_Setting"+DateUtil.date+".html");	
		ExtentVariables.spark.config().setReportName("Profile Setting Test Report"); 
	//	Helper.config();
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

					getDriver().get(lstProfileNavigate.get(i).url);
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Methods.waitElementClickable(By.id("open-profile"));
					Methods.getScreenshot();
					getDriver().findElement(By.id("open-profile")).click();
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Methods.waitElementVisible(By.id("btn-save-2"));
					Thread.sleep(500);

					Assert.assertTrue(getDriver().findElement(By.id("firstNameId")).isDisplayed());
					getDriver().findElement(By.id("close-profile")).click();
					Methods.waitElementInvisible(BasePage.loading_cursor);
					Thread.sleep(500);
					Assert.assertEquals(getDriver().findElement(By.cssSelector("#screen-header p")).getText(), lstProfileNavigate.get(i).pageTitle);

					ExtentVariables.test.pass("User navigated successfully to Profile Setting page");
					ExtentVariables.results.createNode("User navigates to Profile Setting page");
					Methods.getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
				}
				catch(AssertionError er) {
					ExtentVariables.test.fail("User did not navigated to Profile Setting page");
					ExtentVariables.results.createNode("User did not navigated to Profile Setting page");
					saveResult(ITestResult.FAILURE, new Exception(er));
				}			
			}
		}
		catch(Exception ex){
			ExtentVariables.test.fail("User did not navigated to Profile Setting page");
			ExtentVariables.results.createNode("User did not navigated to Profile Setting page");
			saveResult(ITestResult.FAILURE, ex);
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
		getDriver().get(Constants.url_user);
		Methods.waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(By.id("open-profile")).click();
		ExtentVariables.test.createNode("Click on Profile Setting button");
		Methods.waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);
		Methods.getScreenshot();
		getDriver().findElement(By.id("close-profile")).click();
		Methods.waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);

		try{
			Assert.assertEquals(getDriver().findElement(By.id("User Management")).getText(), "User Management"); 
			ExtentVariables.test.pass("User successfully closed profile page");
			ExtentVariables.results.createNode("User successfully closed profile page");
			Methods.getScreenshot();
		}catch(AssertionError e){
			ExtentVariables.test.fail("User failed to close profile page");
			ExtentVariables.results.createNode("User failed to close profile page");
		}	

	}


//	@AfterTest
//	public static void endreport() {
//		ExtentVariables.extent.flush();
//		//getDriver().close();
//	}
}
