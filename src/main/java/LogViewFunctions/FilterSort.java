package LogViewFunctions;

import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.alertMessage;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Config.BaseTest;

public class FilterSort {

	@Test (enabled= true) 
	public static void Sorting(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		BaseTest base = new BaseTest();
		int totalNumberofColumns = size(By.cssSelector("#"+tablename+" th .log-header .mb-0")) + skipColumns;   //get total columns and skip irrelevant columns
		for (int i=1;i<=totalNumberofColumns; i++) {
			try {
				SoftAssert softAssert = new SoftAssert();
				BaseTest driver = new BaseTest();
				
				if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0")) !=0) {
					WebElement column = driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
					test = extent.createTest("AN-Sorting-"+i+": Apply Sorting on "+column.getText()+" column", "This test case will verify that user can apply sorting on "+column.getText()+ " column");
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					if (column.getText().contains("Time") || column.getText().contains("Date") || column.getText().contains("DATE") || column.getText().contains("CREATED")) {
						test.skip("Column sorted successfully");
						results.createNode("Column sorted successfully");
						base.saveResult(ITestResult.SKIP, null);
					}

					else {
						WebElement filter_scroll = column;
						((JavascriptExecutor)driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						steps.createNode("1. Click on "+column.getText()+" column header");

						column.click();
						waitElementInvisible(loading_cursor);					
						Thread.sleep(1000);
						if (size(By.cssSelector("#"+tablename+" .fa-sort-amount-down")) != 0) {
							softAssert.assertEquals(size(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_desc .log-header .mb-0")), 1, "Did not sorted in descending order");
							softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
							getScreenshot();			
						}

						column.click();
						waitElementInvisible(loading_cursor);					
						Thread.sleep(1000);
						if (size(By.cssSelector("#"+tablename+" .fa-sort-amount-down")) != 0) {
							softAssert.assertEquals(size(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_asc .log-header .mb-0")), 1, "Did not sorted in descending order");
							softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
							getScreenshot();
						}
						softAssert.assertAll();
						test.pass("Column sorted successfully");
						results.createNode("Column sorted successfully");
						base.saveResult(ITestResult.SUCCESS, null);
					}
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to sort");
				results.createNode("Column failed to sort");
				base.saveResult(ITestResult.FAILURE, new Exception(er));
			}
		}
	}
	
}
