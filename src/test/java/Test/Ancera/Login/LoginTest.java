package Test.Ancera.Login;

import java.io.IOException;
import java.net.MalformedURLException;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.DateUtil;

import static PageObjects.BasePage.*;
import static PageObjects.LoginPage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static Models.LoginModel.*;
import static MiscFunctions.Methods.*;

public class LoginTest extends BaseTest{

	public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

	@BeforeTest
	public void extent() throws MalformedURLException {
		spark = new ExtentSparkReporter("target/Reports/Login"+DateUtil.date+".html");
		spark.config().setReportName("Login Test Report");
	}


	@Test(enabled=true, priority= 1)
	public static void login() throws InterruptedException, IOException {
		BaseTest base = new BaseTest();
		try {
			System.out.println("Login: "+Thread.currentThread().getId());
			test = extent.createTest("Verify user can login into the IE portal");
			type(loginEmail, config.ie_username());
			type(loginPassword, config.ie_password());
			click(loginButton);	
			waitElementVisible(By.id("Home"));
			getScreenshot();
			BaseTest drive = new BaseTest();
			Assert.assertTrue(drive.getDriver().findElement(By.id("Home")).isDisplayed()); 
			test.pass("User logged in successfully");
			base.saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("User login failed");
			base.saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex){
			test.fail("User login failed");
			base.saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test(enabled= true, priority= 2, dependsOnMethods = {"login"})
	public void logout() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-LO-01: Verify user can Logout");

			Thread.sleep(1000);
			hover(sideBar);
			Thread.sleep(700);
			String version = getText(getVersion);
			Assert.assertTrue(version.startsWith("Version: 5."), "Version not in side menu bar");
			getScreenshot();
			click(logoutButton);
			waitElementVisible(loginEmail);
			Assert.assertTrue(getDriver().findElement(loginEmail).isDisplayed()); 

			test.pass("User logout successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("User logout failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex){
			test.fail("User logout failed");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test(enabled= true, priority= 3, dependsOnMethods = {"logout"})
	public void verifyLogout() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-LO-02: Verify clicking on back button does not take user back to application after logging out");

			getDriver().navigate().back();
			Assert.assertEquals(getDriver().getCurrentUrl(), url_login); 

			test.pass("User remainned logout successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("User remained logout failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex){
			test.fail("User remained logout failed");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}


	@Test(enabled=true, priority= 4, dependsOnMethods = {"verifyLogout"})
	public void loginNegativeCases() throws InterruptedException, IOException {

		for (int i = 0; i<lstLogin.size(); i++) {
			try {
				test = extent.createTest(lstLogin.get(i).testCaseName);

				clear(loginEmail);
				type(loginEmail, lstLogin.get(i).email);

				clear(loginPassword);
				type(loginPassword, lstLogin.get(i).password);

				click(loginButton);	

				waitElementVisible(alertMessage);
				Thread.sleep(700);
				getScreenshot();
				Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Sorry, we don’t recognize these credentials.", "Alert message did not appear");
				click(alertClose);
				test.pass("User receives an alert message 'Sorry, we don't recognize these credentials.'");
				saveResult(ITestResult.SUCCESS, null);
			}
			catch(AssertionError er) {
				test.fail("Login test failed");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Login test failed");
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}

	/*
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void TestLogin(Hashtable<String,String> data) throws InterruptedException, IOException {

	test = extent.createTest(data.get("testcasename"));

	if(data.get("runmode").equalsIgnoreCase("N")){
			throw new SkipException("Skipping the test as the Run mode is NO");	
		}

		try {
		clear(loginEmail);
		type(loginEmail, data.get("username"));

		clear(loginPassword);
		type(loginPassword, data.get("password"));
		}

		catch(IllegalArgumentException ex) {
			clear(loginEmail);
			type(loginEmail, data.get("username"));

			clear(loginPassword);
			type(loginPassword, data.get("password"));
		}



		if (data.get("validcase").equalsIgnoreCase("N")) {
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			System.out.println("12");
			getScreenshot();
			Assert.assertEquals(driver.findElement(alertMessage).getText(), "Sorry, we don’t recognize these credentials.", "Alert message did not appear");
			click(alertClose);
		}
		else {
			waitElementVisible(By.id("Home"));
			getScreenshot();
			Assert.assertTrue(driver.findElement(By.id("Home")).isDisplayed()); 
		}
	}

	 */

	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}
