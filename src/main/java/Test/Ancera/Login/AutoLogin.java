package Test.Ancera.Login;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import Models.AutoLoginModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Variables;

public class AutoLogin {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/AutoLogin"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Auto Login Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Auto Login Test",enabled= true, priority = 1) 
	public void AutoLoginTest() throws InterruptedException, IOException {
try {
		Test_Variables.lstAutoLoginCheck = AutoLoginModel.FillData();

		WebElement csvHover = Helper.driver.findElement(By.id("menu-administration"));
		Actions builder = new Actions(Helper.driver);
		builder.moveToElement(csvHover).build().perform();

		ClickElement.clickByCss(Helper.driver, "#menu-administration img");
		Thread.sleep(1000);
		ClickElement.clickByCss(Helper.driver, "#menu-piper img");
		Thread.sleep(1000);
		ClickElement.clickByCss(Helper.driver, "#menu-metadata label"); 
		Thread.sleep(1000);
		ClickElement.clickByCss(Helper.driver, "#menu-reports label");
		Thread.sleep(1000);
		
		
		for (AutoLoginModel objModel : Test_Variables.lstAutoLoginCheck) { 

			Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("1. "+objModel.precondition);		

			for (ReportFilters objFilter : objModel.lstFilters) {
				try {
					builder.moveToElement(csvHover).build().perform();
					Thread.sleep(1000);

					WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterXPath));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					
					String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
					Helper.driver.findElement(By.id(objFilter.FilterXPath)). sendKeys(clicklnk);
					Thread.sleep(1000);

					ArrayList<String> tabs2 = new ArrayList<String> (Helper.driver.getWindowHandles());
					Helper.driver.switchTo().window(tabs2.get(1));
					Thread.sleep(2000);

					Assert.assertTrue(Helper.driver.findElement(By.id(objFilter.FilterName)).isDisplayed());		
					Test_Variables.test.pass(objFilter.FilterName+ " screen successfully opens in new tab");
					Test_Variables.results.createNode(objFilter.FilterName+ " screen successfully opens in new tab");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("AutoLogin", Constants.AutoLoginReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.AutoLoginReportPath, null);

					Helper.driver.close();
					Helper.driver.switchTo().window(tabs2.get(0));
				}
				catch(AssertionError er) {
					Test_Variables.test.fail(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
					Test_Variables.results.createNode(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.AutoLoginReportPath, new Exception(er));
				}
				catch(Exception ex) {
					Test_Variables.test.fail(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
					Test_Variables.results.createNode(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.AutoLoginReportPath, ex);
				}	
			}
		}
	}
catch(AssertionError er) {
	}
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}


}
