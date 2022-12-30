package LogViewFunctions;

import static MiscFunctions.Constants.url_login;
import static MiscFunctions.ExtentVariables.PreConditions;
import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.preconditions;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.LockFilter;
import static PageObjects.BasePage.ResetFilters;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.UnlockFilter;
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
import MiscFunctions.Methods;

import static MiscFunctions.Methods.*;
import PageObjects.ProgramManagementPage;
import PageObjects.SalmonellaLogPage;

public class FilterLock {

	@Test (enabled= true) 
	public static void Lock(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		
		BaseTest base = new BaseTest();
		int totalNumberofColumns = size(By.cssSelector("#"+tablename+" th .log-header .mb-0")) + skipColumns;
		for (int i=1;i<=totalNumberofColumns; i++) {  //get size of columns
			try {
				SoftAssert softAssert = new SoftAssert();
				String recordBefore = getText(By.cssSelector("#"+tablename+" #"+ResultsCount)); 
				if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")) != 0) {  //check column with ehich filter icon is applied
					BaseTest driver = new BaseTest();
					WebElement column = driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
					WebElement columnName = driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));

					test = extent.createTest("AN-Lock-"+i+": Verify user can apply filter on "+columnName.getText()+" column", "This testcase will verify that user can apply filter on column");
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Go to url " +url_login);
					preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					preconditions.createNode("3. Hover to sidebar to expand the menu");
					preconditions.createNode("4. Click on "+name+"; "+name+" screen opens");

					if (size(By.cssSelector("#remove-filters.d-none")) == 0) {
						click(By.id(UnlockFilter));
						click(By.id(ResetFilters));
						waitElementInvisible(loading_cursor);	
						Thread.sleep(2000);
					}

					WebElement filter_scroll = column;
					((JavascriptExecutor)driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					getScreenshot();
					click(By.cssSelector("#"+tablename+" th:nth-child("+i+")  .log-header__filter-icon"));
					waitElementInvisible(loading_cursor);	
					Thread.sleep(1000);

					if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+SalmonellaLogPage.footerCount)) != 0) {
						if (getText(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+SalmonellaLogPage.footerCount)).equals("Showing 1 - 1 Results")) {
							test.skip("Values not enough to test lock filter functionality");
							results.createNode("Values not enough to test lock filter functionality");
							base.saveResult(ITestResult.SKIP, null);
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
						}
						else {
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") li:nth-child(3) label"));
							Thread.sleep(500);
							steps.createNode("1. Select any filter and click on apply filter button");
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply"));
							waitElementInvisible(loading_cursor);	
							steps.createNode("2. Click on lock button");	
							click(By.cssSelector("#"+tablename+" #"+LockFilter));
							waitElementInvisible(loading_cursor);	
							getScreenshot();
							Thread.sleep(1000);

							String recordsafterfilter = getText(By.cssSelector("#"+tablename+" #"+ResultsCount));
							softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");
							steps.createNode("3. Close "+name+" screen");
							steps.createNode("4. Reopen "+name+" screen");
							driver.getDriver().navigate().refresh();
							waitElementInvisible(loading_cursor);
							Methods.waitElementVisible(By.cssSelector("#"+tablename+" #"+ResultsCount));
							Thread.sleep(3000);

							if (tablename.equals(ProgramManagementPage.programFeedTable)) {
								click(ProgramManagementPage.programFeedProgramTab);
								waitElementInvisible(loading_cursor);
								Thread.sleep(2000);
							}

							steps.createNode("5. Verify lock filter remains applied");
							getScreenshot();
							softAssert.assertEquals(getText(By.cssSelector("#"+tablename+" #"+ResultsCount)), recordsafterfilter, "Lock functionality failed");
							Thread.sleep(1000);
							click(By.cssSelector("#"+tablename+" #"+UnlockFilter));
							waitElementInvisible(loading_cursor);
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__clear-filter span"));
							waitElementInvisible(loading_cursor);	
						}
					}
					else {
						test.skip("Heirarchy filter cannot be tested with this method");
						results.createNode("Heirarchy filter cannot be tested with this method");
						base.saveResult(ITestResult.SKIP, null);
						click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
					}
					softAssert.assertAll();
					test.pass("Lock functionality verified successfully");
					results.createNode("Lock functionality verified successfully");
					getScreenshot();
					base.saveResult(ITestResult.SUCCESS, null);
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to Lock");
				results.createNode("Column failed to Lock");
				base.saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Column failed to Lock");
				results.createNode("Column failed to Lock");
				base.saveResult(ITestResult.FAILURE, new Exception(ex));
			}
		}
	}
	
}
