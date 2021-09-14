package Test.Ancera.Login;

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

import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;


public class TestLogin{

	@BeforeTest
	public void extent() {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Login"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Login Test Report"); // Name of the report
		Helper.config();

	}

	@Test(enabled=true, priority= 1)
	public void login() throws InterruptedException, IOException {

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		for (int i = 0; i<Test_Variables.lstLogin.size(); i++) {
			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstLogin.get(i).message);

				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login);
				Test_Variables.preconditions.createNode("2. User should land on login screen");

				Test_Variables.steps.createNode(Test_Variables.lstLogin.get(i).loginScenario);
				Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
				Helper.driver.findElement(By.id("email")).clear();
				Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.lstLogin.get(i).email);

				Test_Variables.steps.createNode(Test_Variables.lstLogin.get(i).passwordScenario);
				Helper.driver.findElement(By.id("pwd")).clear();
				Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.lstLogin.get(i).password);

				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
				Test_Variables.steps.createNode("3. Click on Sign in button");
				Helper.driver.findElement(By.id("btn-sign-in")).click();

				if (Test_Variables.lstLogin.get(i).invalidCredentials == true) {
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
					Thread.sleep(1500);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
					Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Sorry, we don’t recognize these credentials.", "Alert message did not appear");
					Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
					Test_Variables.test.pass(Test_Variables.lstLogin.get(i).message);
					Test_Variables.results.createNode(Test_Variables.lstLogin.get(i).passScenario);
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.LoginReportPath, null);
				}

				if (Test_Variables.lstLogin.get(i).validCredentials == true) {
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Home")));
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
					Assert.assertTrue(Helper.driver.findElement(By.id("Home")).isDisplayed()); 
					Test_Variables.test.pass(Test_Variables.lstLogin.get(i).message);
					Test_Variables.results.createNode(Test_Variables.lstLogin.get(i).passScenario);
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.LoginReportPath, null);
				}
			}
			catch(AssertionError er) {
				Test_Variables.test.fail(Test_Variables.lstLogin.get(i).message);
				Test_Variables.results.createNode(Test_Variables.lstLogin.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(Test_Variables.lstLogin.get(i).message);
				Test_Variables.results.createNode(Test_Variables.lstLogin.get(i).failScenario);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, ex);
			}
		}
	}


	@Test(enabled= true, priority= 2)
	public void logout() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-LO-01: Verify user can Logout", "This testcase will verify user can logout of the application");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Home")));
			Thread.sleep(2000);
			Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials");
			Test_Variables.preconditions.createNode("3. User is redirected to home page");
			Test_Variables.steps.createNode("1. Hover to expand sidebar");
			Test_Variables.steps.createNode("2. Click on Logout button");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
			Helper.driver.findElement(By.id("logout")).click();
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Assert.assertTrue(Helper.driver.findElement(By.id("email")).isDisplayed()); 

			Test_Variables.test.pass("User logout successfully");
			Test_Variables.results.createNode("User is logged out of the application and navigated to login screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));

			Helper.saveResultNew(ITestResult.SUCCESS, Constants.LoginReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User logout failed");
			Test_Variables.results.createNode("User is not logged out of the application");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, new Exception(er));
		}
		catch(Exception ex){
			Test_Variables.test.fail("User logout failed");
			Test_Variables.results.createNode("User is not logged out of the application");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, ex);
		}	
	}


	@Test(enabled= true, priority= 3)
	public void VerifyLogout() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-LO-02: Verify clicking on back button does not take user back to application after logging out", "This testcase will verify user stays on login screen on clicking on browser back button after logging out");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Helper.driver.navigate().back();
			Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials");
			Test_Variables.preconditions.createNode("3. User is redirected to home page");
			Test_Variables.preconditions.createNode("4. Hover to expand sidebar");
			Test_Variables.preconditions.createNode("5. Click on Logout button; User logs out of the application");
			Test_Variables.steps.createNode("1. Click on Browser back button after logging out");
			Thread.sleep(2000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
			String actual = Helper.driver.getCurrentUrl();
			String expected = Constants.url_login;

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User remainned logout successfully");
			Test_Variables.results.createNode("User stayed on login page");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));

			Helper.saveResultNew(ITestResult.SUCCESS, Constants.LoginReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User remained logout failed");
			Test_Variables.results.createNode("User is again redirected into the application");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, new Exception(er));
		}
		catch(Exception ex){
			Test_Variables.test.fail("User remained logout failed");
			Test_Variables.results.createNode("User is again redirected into the application");

			Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, ex);
		}	
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();

	}

}
