package Test.Ancera.Login;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Variables;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Constants.*;

public class TestLogin{

	@BeforeTest
	public void extent() throws MalformedURLException {
		spark = new ExtentSparkReporter("target/Reports/Login"+date+".html");
		spark.config().setReportName("Login Test Report"); // Name of the report
		config();
	}

	@Test(enabled=true, priority= 1)
	public void login() throws InterruptedException, IOException {

		waitElementVisible(loginEmail);
		for (int i = 0; i<lstLogin.size(); i++) {
			try {
				test = extent.createTest(lstLogin.get(i).testCaseName);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url "+url_login);
				preconditions.createNode("2. User should land on login screen");

				waitElementClickable(loginEmail);
				steps.createNode(lstLogin.get(i).loginStep);
				clear(loginEmail);
				type(loginEmail, lstLogin.get(i).email);

				steps.createNode(lstLogin.get(i).passwordStep);
				clear(loginPassword);
				type(loginPassword, lstLogin.get(i).password);

				test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
				steps.createNode("3. Click on Sign in button");
				click(loginButton);	

				if (lstLogin.get(i).invalidCredentials == true) {
					waitElementVisible(alertMessage);
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
					Assert.assertEquals(driver.findElement(alertMessage).getText(), "Sorry, we don’t recognize these credentials.", "Alert message did not appear");
					click(alertClose);
					test.pass("User receives an alert message 'Sorry, we don't recognize these credentials.'");
					results.createNode("User receives an alert message 'Sorry, we don't recognize these credentials.'");
					Helper.saveResultNew(ITestResult.SUCCESS, LoginReportPath, null);
				}

				if (lstLogin.get(i).invalidCredentials == false) {
					waitElementVisible(By.id("Home"));
					test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
					Assert.assertTrue(driver.findElement(By.id("Home")).isDisplayed()); 
					test.pass("User logged in successfully");
					results.createNode("User logged in successfully");
					saveResultNew(ITestResult.SUCCESS, LoginReportPath, null);
				}
			}
			catch(AssertionError er) {
				test.fail("Login test failed");
				results.createNode("Login test failed");
				saveResultNew(ITestResult.FAILURE, LoginReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Login test failed");
				results.createNode("Login test failed");
				saveResultNew(ITestResult.FAILURE, LoginReportPath, ex);
			}
		}
	}


	@Test(enabled= true, priority= 2)
	public void logout() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-LO-01: Verify user can Logout", "This testcase will verify user can logout of the application");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			waitElementVisible(By.id("Home"));
			Thread.sleep(2000);
			preconditions.createNode("1. Go to url "+url_login);
			preconditions.createNode("2. Login with valid credentials");
			preconditions.createNode("3. User is redirected to home page");
			steps.createNode("1. Hover to expand sidebar");
			steps.createNode("2. Verify the version in sidebar");

			WebElement hover = driver.findElement(By.id("menu-administration"));
			Actions action = new Actions(driver);
			action.moveToElement(hover).perform();
			Thread.sleep(1000);
			String version = driver.findElement(By.cssSelector(".version-text")).getText();
			Assert.assertTrue(version.startsWith("Version: 5."), "Version not in side menu bar");

			steps.createNode("3. Click on Logout button");
			test.addScreenCaptureFromPath(getScreenshot("Login", Constants.LoginReportPath));
			click(logoutButton);
			waitElementVisible(loginEmail);
			Assert.assertTrue(driver.findElement(loginEmail).isDisplayed()); 

			test.pass("User logout successfully");
			results.createNode("User is logged out of the application and navigated to login screen");
			test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
			saveResultNew(ITestResult.SUCCESS, LoginReportPath, null);
		}catch(AssertionError er) {
			test.fail("User logout failed");
			results.createNode("User is not logged out of the application");
			saveResultNew(ITestResult.FAILURE, LoginReportPath, new Exception(er));
		}
		catch(Exception ex){
			test.fail("User logout failed");
			results.createNode("User is not logged out of the application");
			saveResultNew(ITestResult.FAILURE, LoginReportPath, ex);
		}	
	}


	@Test(enabled= true, priority= 3)
	public void verifyLogout() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-LO-02: Verify clicking on back button does not take user back to application after logging out", "This testcase will verify user stays on login screen on clicking on browser back button after logging out");

			preconditions = Test_Variables.test.createNode(Scenario.class, PreConditions);
			steps = Test_Variables.test.createNode(Scenario.class, Steps);
			results = Test_Variables.test.createNode(Scenario.class, Results);

			driver.navigate().back();
			preconditions.createNode("1. Go to url "+url_login);
			preconditions.createNode("2. Login with valid credentials");
			preconditions.createNode("3. User is redirected to home page");
			preconditions.createNode("4. Hover to expand sidebar");
			preconditions.createNode("5. Click on Logout button; User logs out of the application");
			steps.createNode("1. Click on Browser back button after logging out");
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
			String actual = driver.getCurrentUrl();
			String expected = url_login;

			Assert.assertEquals(actual, expected); 
			test.pass("User remainned logout successfully");
			results.createNode("User stayed on login page");
			test.addScreenCaptureFromPath(Helper.getScreenshot("Login", LoginReportPath));

			saveResultNew(ITestResult.SUCCESS, LoginReportPath, null);
		}catch(AssertionError er) {
			test.fail("User remained logout failed");
			results.createNode("User is again redirected into the application");
			saveResultNew(ITestResult.FAILURE, LoginReportPath, new Exception(er));
		}
		catch(Exception ex){
			test.fail("User remained logout failed");
			results.createNode("User is again redirected into the application");
			saveResultNew(ITestResult.FAILURE, LoginReportPath, ex);
		}	
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
		driver.close();
	}
}
