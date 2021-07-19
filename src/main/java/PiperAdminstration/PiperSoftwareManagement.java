package PiperAdminstration;

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

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class PiperSoftwareManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new 	ExtentSparkReporter("target/Reports/Administration_Piper_Software_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Piper Software Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	
	
	@Test (description="Test Case: Navigate to Piper Software Management Screen",enabled= true, priority = 1) 
	public void NavigatePSM() throws InterruptedException, IOException {

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PSM-01: Verify user can navigate to Piper Software Management Screen", "This test case will verify user can navigate to Piper Software Management Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("1. Click on Piper Administration and select Piper Software Management");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(Constants.url_piperSoftware);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("PIPER Software Management")).getText();
			String expected = "PIPER Software Management";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully to PIPER Software Management screen");
			Test_Variables.results.createNode("User navigated successfully to PIPER Software Management screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Software Management", Constants.PiperSoftwareReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperSoftwareReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("PIPER Software Management failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("PIPER Software Management failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, ex);
		}
	}
	
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
	
}
