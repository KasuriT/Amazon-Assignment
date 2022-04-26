package Test.Ancera.Login;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import io.github.sukgu.Shadow;


public class FlutterLogin{

	@BeforeTest
	public void extent() throws MalformedURLException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Login"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Login Test Report"); // Name of the report
		Helper.config();
	}
	


	@Test(enabled=true, priority= 1)
	public void login() throws InterruptedException, IOException {

		for (int i = 0; i<Test_Variables.lstLogin.size(); i++) {
			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstLogin.get(i).message);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
				Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login);
				Test_Variables.preconditions.createNode("2. User should land on login screen");

				Thread.sleep(10000);
				Actions action = new Actions(Helper.driver);
				Helper.driver.switchTo().frame(1);


		//		action.moveByOffset(300, 700).click().build().perform();
				
		//	Actions action = newfset Actions(Helper.driver);
				action.moveByOffset(700,420).perform();
				Thread.sleep(4000);
				action.click();
				
				
				
				
//				
//				Test_Variables.steps.createNode(Test_Variables.lstLogin.get(i).loginScenario);
//				Thread.sleep(6000);
//				WebElement iframe = Helper.driver.findElement(By.linkText("Email Address"));
//				Helper.driver.switchTo().frame(iframe);
//				
////			     Shadow shadow = new Shadow(Helper.driver);
////			        WebElement emailField = shadow.findElement("//input[@placeholder= 'Password')]");
//
//
//			        iframe.sendKeys("hello world");
			
				
				
			//	Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/flt-glass-pane//input")));
//				Helper.driver.findElement(By.xpath("/html/body/flt-glass-pane//input")).clear();
//				Helper.driver.findElement(By.xpath("/html/body/flt-glass-pane//input")).sendKeys(Test_Variables.lstLogin.get(i).email);

//				Test_Variables.steps.createNode(Test_Variables.lstLogin.get(i).passwordScenario);
//				Helper.driver.findElement(By.xpath("/html/body/flt-glass-pane//input")).clear();
//				Helper.driver.findElement(By.xpath("/html/body/flt-glass-pane//input")).sendKeys(Test_Variables.lstLogin.get(i).password);
//
//				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
//				Test_Variables.steps.createNode("3. Click on Sign in button");
//				Helper.driver.findElement(By.xpath("/html/body/flt-glass-pane")).click();
/*
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
				*/
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


	@Test(enabled= false, priority= 2)
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
			Test_Variables.steps.createNode("2. Verify the version in sidebar");
			
			WebElement hover = Helper.driver.findElement(By.id("menu-administration"));
			Actions action = new Actions(Helper.driver);
			action.moveToElement(hover).perform();
			Thread.sleep(1000);
			String version = Helper.driver.findElement(By.cssSelector(".version-text")).getText();
			Assert.assertTrue(version.startsWith("Version: 4."), "Version not in side menu bar");
			
			Test_Variables.steps.createNode("3. Click on Logout button");
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


	@Test(enabled= false, priority= 3)
	public void verifyLogout() throws InterruptedException, IOException {
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
	//	Helper.driver.close();
	}
}
