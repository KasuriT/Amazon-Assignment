package MiscFunctions;

import static MiscFunctions.Constants.url_login;
import static MiscFunctions.Constants.wait;
import static MiscFunctions.ExtentVariables.PreConditions;
import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.preconditions;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Helper.driver;
import static MiscFunctions.Helper.getScreenshot;
import static MiscFunctions.Helper.saveResult;
import static MiscFunctions.Helper.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;

public class NavigateToScreen {

	@Test (enabled=true) 
	public static void navigate(String url,  String name, By id) throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-01: Navigate to "+name+" Screen", "This test case will navigate to "+name+" screen");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			steps.createNode("1. Hover to sidebar to expand the menu");
			steps.createNode("2. Select "+name+" from side bar menu");					

			driver.get(url);
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(id));
			Thread.sleep(3000);	
			Assert.assertEquals(driver.findElement(id).getText(), ""+name); 			
			test.pass("User navigated to "+name+" screen successfully");
			results.createNode("User navigated to "+name+" screen successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("User failed to navigate to "+name+" screen");
			results.createNode("User failed to navigate to "+name+" screen");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("User failed to navigate to "+name+" screen");
			results.createNode("User failed to navigate to "+name+" screen");
			saveResult(ITestResult.FAILURE, ex);
		}		
	}
}
