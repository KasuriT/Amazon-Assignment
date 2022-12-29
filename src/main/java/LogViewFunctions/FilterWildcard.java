package LogViewFunctions;

import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.getScreenshot;
import static Config.BaseTest.saveResult;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.loading_cursor;
import static MiscFunctions.Methods.*;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Config.BaseTest;

public class FilterWildcard {

	@Test (enabled= true) 
	public static void Wildcard(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		int totalNumberofColumns = size(By.cssSelector("#"+tablename+" th .log-header .mb-0")) + skipColumns;   //get total columns and skip irrelevant columns
		BaseTest driver = new BaseTest();
		for (int i=1;i<=totalNumberofColumns; i++) {
			try {
				String recordBefore = getText(By.cssSelector("#"+tablename+" #"+ResultsCount));   //get result count
				if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")) != 0) {     //check column has filter icon
					WebElement columnName = driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
					
					test = extent.createTest("AN_Wildcard-"+i+": Verify user can apply wildcard on "+columnName.getText()+" filter");
					steps = test.createNode(Scenario.class, Steps);

					SoftAssert softAssert = new SoftAssert();
					
					
					WebElement filter_scroll = columnName;	//scroll to filter			
					((JavascriptExecutor)driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);   
					click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));   //open filter popup
					waitElementInvisible(loading_cursor);	
					Thread.sleep(500);

					if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")) != 0) {
						if (driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")).isDisplayed()) {  //check if filter has wildcard option
							if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") .data-log-radio")) == 0) {  //check if toggle is selected or not
								click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard"));  //click on toggle button to enable
								waitElementInvisible(loading_cursor);
							}

							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__search-input input"));
							type(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__search-input input"), "h");
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply"));
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();

							String getResultCount = getText(By.cssSelector("#"+tablename+" #"+ResultsCount)); 
							String recordAfter = getResultCount.replace(",", "");
							
							int count1 = size(By.id("col-0"));
							
							Thread.sleep(800);
							for (int j = 0; j<count1; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0) {
								String str = getText(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label"));

								softAssert.assertTrue(str.startsWith("h") || str.startsWith("H"), "WildCard Starts With failed");
								}
							}

							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
							waitElementInvisible(loading_cursor);	
							Thread.sleep(1500);

							click(By.xpath("//*[text() = ' Ends With ']"));
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply"));
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();

							int count2 = size(By.id("col-0"));
							for (int j = 0; j<count2; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0) {
								String str = getText(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label"));
								softAssert.assertTrue(str.endsWith("h") || str.endsWith("H"), "WildCard Ends With failed");
								}
							}
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+")  .log-header__filter-icon"));
							waitElementInvisible(loading_cursor);	
							Thread.sleep(800);
							click(By.xpath("//*[text() = ' Contains ']"));
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply"));
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();
							int count3 = size(By.id("col-0"));
							Thread.sleep(1000);
							for (int j = 0; j<count3; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0) {
								String str = getText(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label"));
								softAssert.assertTrue(str.contains("h") || str.contains("H"), "WildCard Contains failed");
								}
							}
							softAssert.assertNotEquals(recordAfter, recordBefore);		
							click(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__clear-filter span"));
							waitElementInvisible(loading_cursor);
						}	
					}
					else {
						test.skip("Filter does not have wildcard option");
						saveResult(ITestResult.SKIP, null);
					}

					Thread.sleep(700);
					softAssert.assertAll();
					test.pass("Wildcards tested successfully");
					saveResult(ITestResult.SUCCESS, null);
				}	
			}
			catch(AssertionError er) {
				test.fail("Wildcards failed to test successfully");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Wildcards failed to test successfully");
				saveResult(ITestResult.FAILURE, new Exception(ex));
			}
		}			
	}
	
}
