package Test.Ancera;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;


public class ConfigureLogin {


	@Test(description="Test Case: Enter valid username and password",enabled=true, priority= 1)
	public static void login() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Login-07: Login with valid credentials", "This test case will verify that user can login with valid credentials");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, "Pre_Conditions");
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, "Steps");
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, "Result");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Helper.driver.findElement(By.id("email")).clear();
			Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.login_email);
			Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login);
			Test_Variables.preconditions.createNode("2. User should land on login screen");
			Test_Variables.steps.createNode("1. Enter valid email address in Email Address field (" +Test_Variables.login_email+")");
			Helper.driver.findElement(By.id("pwd")).clear();
			Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.login_password);
			Test_Variables.steps.createNode("2. Enter valid password in Password field (**********)");

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-sign-in")).click();
			Test_Variables.steps.createNode("3. Click on Sign In button");
			Thread.sleep(1000);
			
			if (Helper.driver.findElements(By.xpath(Test_Elements.amLicensePopup)).size() != 0) {
				Helper.driver.findElement(By.xpath(Test_Elements.amLicensePopup)).click();
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Home")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("Ancera Intelligence Engine")).getText();
			String expected = "Ancera Intelligence Engine (Testing Environment)";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User logged in successfully");
			Test_Variables.results.createNode("User logged in successfully to homepage");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.LoginReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User log in failed");
			Test_Variables.results.createNode("User log in failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User log in failed");
			Test_Variables.results.createNode("User log in failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.LoginReportPath, ex);
		}
	}
}