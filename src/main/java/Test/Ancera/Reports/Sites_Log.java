package Test.Ancera.Reports;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Models.SitesLogModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sites_Log {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Sites_Log"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Sites Log Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Sites Log Screen",enabled= true, priority = 1) 
	public void NavigateSites() throws InterruptedException, IOException {

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-Sites-01: Verify user can navigate to Sites Log Screen", "This test case will verify user can navigate to Sites Log Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.steps.createNode("1. Click on Sites Log");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(Constants.url_SitesLog);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("Sites Log")).getText();
			String expected = "Sites Log";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully to Sites Log screen");
			Test_Variables.results.createNode("Sites Log report opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Sites Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Sites Log report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
		}
	}


	@Test (description="Test Case: Filter Test",enabled= true, priority = 2) 
	public void TestFilter() throws InterruptedException, IOException {

		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstSitesLogSearch = SitesLogModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
		for (SitesLogModel objModel : Test_Variables.lstSitesLogSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							WebElement filter_scroll = Helper.driver.findElement(By.id(Test_Elements.slSortFilter+""+objFilter.LstFilterXpath.get(i)));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
							Thread.sleep(800);	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");				
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.sitesShowFilter)).click();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);						
							if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.clSortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
								Assert.assertTrue(true, "No records available to test filter");
								Test_Variables.test.skip("No records available to test filter");
								Helper.saveResultNew(ITestResult.SKIP, Constants.CoccidiaReportPath, null);
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.clShowFilter)).click();	
							}

							else {
								for (int j = 0; j < objFilter.LstFilterValues.size(); j++) {
									Test_Variables.steps.createNode("2. Select the checkbox");
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector("#"+Test_Elements.sitesSortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 		
									Thread.sleep(1000);
									ClickElement.clickByCss(Helper.driver, "#"+Test_Elements.clSortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label");
								}

								Test_Variables.steps.createNode("3. Click on apply filter button");	
								ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+""+Test_Elements.sitesApplyFilter);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(800);
								String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();		

								Assert.assertNotEquals(recordBefore, recordAfter);
								Test_Variables.test.pass("Filter applied successfully");
								Test_Variables.results.createNode("Filter applied successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch, objModel.TestCaseDescriptionSearch);
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify filter is applied and relevant results are displayed in table");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							if (Helper.driver.findElements(By.cssSelector("#"+Test_Elements.sitesSortFilter+""+objFilter.LstFilterXpath.get(i)+" .active-filter")).size() != 0) {			
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.sitesShowFilter)).click();	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Thread.sleep(500);
								if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.clSortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
									Test_Variables.test.skip("No records available to test filter");
									Helper.saveResultNew(ITestResult.SKIP, Constants.CoccidiaReportPath, null);
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.clShowFilter)).click();
								}
								else {	
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.slSortFilter+""+objFilter.LstFilterXpath.get(i)+" .divider")).size(), 1);
									Test_Variables.test.pass("Applied checkbox bubbled to top successfully");
									Test_Variables.results.createNode("Applied checkbox bubbled to top successfully");
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
									Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.sitesClearFilter)).click();
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.clShowFilter)).click();
								}
							}
							else {
								Test_Variables.results.createNode("Test case skipped because filter was not applied");
								Test_Variables.test.skip("Test case skipped because filter was not applied");
								Helper.saveResultNew(ITestResult.SKIP, Constants.CoccidiaReportPath, null);
							}			
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}



	@Test (description="Test Case: Wildcard",enabled= true, priority = 3) 
	public void wildcard() throws InterruptedException, IOException {
		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);

		Test_Variables.lstSiteLogWildcardSearch = SitesLogModel.Wildcard(); 
		for (SitesLogModel objModel : Test_Variables.lstSiteLogWildcardSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	

						Test_Variables.steps.createNode("1. Click on Sites Log");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.slShowFilter));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 	
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.slShowFilter)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						if (Helper.driver.findElements(By.cssSelector("#sort-"+objFilter.FilterID+" .data-log-radio")).size() == 0) {
							Helper.driver.findElement(By.cssSelector("#sort-"+objFilter.FilterID+" .filter-popup__action--wildcard")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						}

						if(objModel.startWith) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterXPath+"_wildcard-option1")).click();
						}

						if(objModel.endsWith) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterXPath+"_wildcard-option3")).click();
						}

						if(objModel.contains) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterXPath+"_wildcard-option2")).click();
						}

						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.slSearchInput)).clear();
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.slSearchInput)).sendKeys(objModel.input);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.slApplyFilter)).click();

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] td:nth-child(4) label"));
						int count = rows.size();
						Thread.sleep(1000);
						for (int i = 0; i<count; i++) {
							if(objModel.startWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								Assert.assertTrue(str.startsWith(objFilter.LstFilterValues.get(0)) || str.startsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.endsWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();				
								Assert.assertTrue(str.endsWith(objFilter.LstFilterValues.get(0)) || str.endsWith(objFilter.LstFilterValues.get(1)));
							}

							if(objModel.contains) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								Assert.assertTrue(str.contains(objFilter.LstFilterValues.get(0)) || str.contains(objFilter.LstFilterValues.get(1)));
							}
						}

						Thread.sleep(1000);
						Test_Variables.test.pass("Wildcards tested successfully");
						Test_Variables.results.createNode("Wildcards tested successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
					}
					Helper.driver.findElement(By.id(objFilter.FilterXPath+""+Test_Elements.slClearFilter)).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Sites Lock Filter Functionality",enabled= true, priority = 4) 
	public void SitesLock() throws InterruptedException, IOException {
		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);
		Test_Variables.lstSitesLogLock = SitesLogModel.Lock(); 
		for (SitesLogModel objModel : Test_Variables.lstSitesLogLock) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
				SoftAssert softAssert = new SoftAssert();

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);	
						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
						Helper.driver.findElement(By.id(objFilter.FilterID)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);
						if (Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterSort+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
							Test_Variables.test.skip("Values not enough to test lock filter functionality");
							Test_Variables.results.createNode("Values not enough to test lock filter functionality");
							Helper.saveResultNew(ITestResult.SKIP, Constants.SitesLogReportPath, null);
							Helper.driver.findElement(By.id(objFilter.FilterID)).click();
						}
						else {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterSort+" li:nth-child(3) label")).click();
							Thread.sleep(500);
							Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
							Helper.driver.findElement(By.id(objFilter.FilterApply)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Test_Variables.steps.createNode("2. Click on lock button");	
							Helper.driver.findElement(By.id("save-filters")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Thread.sleep(1000);
							String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();
							Test_Variables.steps.createNode("3. Close Sites Log Report");
							Test_Variables.steps.createNode("4. Reopen Sites Log Report");
							Helper.driver.navigate().refresh();

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.steps.createNode("5. Verify lock filter remains applied");
							softAssert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.id("results-found-count")).getText());
							Test_Variables.test.pass(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("remove-filters")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Helper.driver.findElement(By.id(objFilter.FilterClear)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							softAssert.assertAll();
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Pagination",enabled= true, priority = 5) 
	public void Pagination() throws InterruptedException, IOException {
		Test_Variables.lstSitesLogPagination = SitesLogModel.pagination();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 
		Thread.sleep(500);

		for (SitesLogModel objModel : Test_Variables.lstSitesLogPagination) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						String recordNumber = Helper.driver.findElement(By.id("results-found-count")).getText();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));

						String removeComma = recordNumber.replace(",", "");
						double x = Double.parseDouble(removeComma);
						double y = 100;
						double divide = Math.ceil(Math.abs(x/y));
						final int totalPages = (int)divide;
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
							Helper.driver.findElement(By.id(objFilter.paginationPage)).click();

							if (objModel.paginationExist) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Verify pagination exists");
								Assert.assertTrue(Helper.driver.findElements(By.id("activePageNumber")).size() != 0);
								Test_Variables.test.pass("Pagination displayed successfully");
								Test_Variables.results.createNode("Pagination displayed successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);		
							}


							if (objModel.paginationLastPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '>>' icon in pagination");

								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));	
									Assert.fail("An error alert message displayed");
								}	
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, totalPages+"/"+totalPages);
								Test_Variables.test.pass("Navigated to last page successfully");
								Test_Variables.results.createNode("Navigated to last page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}

							if (objModel.paginationPreviousPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
								Test_Variables.test.pass("Navigated to previous page successfully");
								Test_Variables.results.createNode("Navigated to previous page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}


							if (objModel.paginationFirstPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '<<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 1+"/"+totalPages);
								Test_Variables.test.pass("Navigated to first page successfully");
								Test_Variables.results.createNode("Navigated to first page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}


							if (objModel.paginationNextPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '>' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 2+"/"+totalPages);
								Test_Variables.test.pass("Navigated to next page successfully");
								Test_Variables.results.createNode("Navigated to next page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}
						}

						else {
							Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
							Test_Variables.test.pass("Records are less then 100; pagination cannot be tested");
							Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Helper.driver.findElement(By.id("first-page")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	@Test (description="Test Case: Test Table Rows",enabled= true, priority = 6) 
	public void RowsPerPage() throws InterruptedException, IOException {
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 
		Thread.sleep(500);

		Test_Variables.lstSitesLogRowCount = SitesLogModel.searchRows();

		for (SitesLogModel objModel : Test_Variables.lstSitesLogRowCount) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

				Actions actions = new Actions(Helper.driver);
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("rows")));
						Thread.sleep(500);
						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > Integer.parseInt(objFilter.count)) {
							WebElement expandFilter = Helper.driver.findElement(By.id("rows"));
							actions.moveToElement(expandFilter).click().perform();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Helper.driver.findElement(By.id(objFilter.FilterListXPathSearch)).click();

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(1000);
							List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] tr"));

							int count = rows.size();
							int new_count = count - 1;
							System.out.println("ROW COUNT : "+new_count);
							Assert.assertEquals(new_count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
						}

						else {
							Assert.assertTrue(true, "Records are less then "+objFilter.count);
							Test_Variables.test.pass("Records are less then "+objFilter.count);
							Test_Variables.results.createNode("Rcords are less then "+objFilter.count);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch, objModel.TestCaseDescriptionSearch);
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));

						Test_Variables.steps.createNode("1. Select "+objFilter.FilterName+" from dropdown below");
						Test_Variables.steps.createNode("2. Go to next page from pagination");
						Test_Variables.steps.createNode("3. Verify that still "+objFilter.FilterName+" is selected");

						String results = Helper.driver.findElement(By.id("results-found-count")).getText();
						int sum = Integer.parseInt(objFilter.count) + Integer.parseInt(objFilter.count);

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > sum) {

							ClickElement.clickById(Helper.driver, "next-page");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] tr"));
							int count = rows.size();
							int new_count = count - 1;
							System.out.println("ROW COUNT : "+new_count);
							Assert.assertEquals(new_count, Integer.parseInt(objFilter.count));
							Test_Variables.test.pass(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.results.createNode(objFilter.FilterName+" displayed succcessfully on next page");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);	
						}
						else {
							Assert.assertTrue(true, "Records are less then "+sum);
							Test_Variables.test.pass("Records are less then "+sum);
							Test_Variables.results.createNode("Rcords are less then "+sum);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority =7) 
	public void Sorting() throws InterruptedException, IOException {

		Test_Variables.lstSitesLogSorting = SitesLogModel.sorting();

		for (SitesLogModel objModel : Test_Variables.lstSitesLogSorting) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.ColumnID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" column header");
						Helper.driver.findElement(By.id("objFilter.ColumnID")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(500);
						if (objModel.sortDescFirst) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).size(), 1, "Did not sorted in descending order");
						}

						if (!objModel.sortDescFirst) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_asc")).size(), 1, "Did not sorted in ascending order");
						}
						Assert.assertEquals(Helper.driver.findElements(By.id("message")).size(), 0, "Exception message occured");

						Test_Variables.test.pass(objFilter.FilterName+" column sorted successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(1000);
						if (objModel.sortDescFirst) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_asc")).size(), 1, "Did not sorted in ascending order");
						}
						if (!objModel.sortDescFirst) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).size(), 1, "Did not sorted in desccending order");	
						}
						Assert.assertEquals(Helper.driver.findElements(By.id("message")).size(), 0, "Exception message occured");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));		
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to sort");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to sort");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(StaleElementReferenceException s) {
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					}
				}

			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority =8) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-Sites-72: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
			Test_Variables.steps.createNode("1. Verify int data type columns are right alligned");

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.sitesLatitudeCol+" .text-right")).size(), 0, "Latitude column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.sitesLongitudeCol+" .text-right")).size(), 0, "Longitude column is not right alligned");

			Test_Variables.test.pass("Int data type columns are right alligned");
			Test_Variables.results.createNode("Int data type columns are right alligned");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
			softAssert.assertAll();	
		}catch(AssertionError er) {
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 9) 
	public void FieldAccess() throws InterruptedException, IOException {

		Helper.driver.navigate().refresh();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstSitesLogFieldAccess = SitesLogModel.FieldAccess();

		for (SitesLogModel objModel : Test_Variables.lstSitesLogFieldAccess) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
				Test_Variables.steps.createNode("1. Click on filed access icon; popup appears");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);     
						Helper.driver.findElement(By.id("edit-field-access")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);
						Test_Variables.steps.createNode("2. Unselect "+objFilter.FilterName+" column and Select previously unselected filter column and click on save button");

						if(objModel.viewAccess) {
							if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) input")).isSelected() == false) {
								Helper.driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}	

						if(objModel.unviewAccess) {
							int inc = Integer.parseInt(objFilter.FilterID) + 1;
							if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) input")).isSelected() == true) {
								Helper.driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}

						Helper.driver.findElement(By.id("btn-save")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
						Thread.sleep(1000);
						Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Report Settings Saved");

						if(objModel.viewAccess) {
							Test_Variables.steps.createNode("3. Verify selected column is displayed in the table");
							Assert.assertEquals(Helper.driver.findElements(By.id(objModel.FilterUnHideID)).size(), 1);
						}	
						if(objModel.unviewAccess) {
							Test_Variables.steps.createNode("4. Verify unselected column is hidden in the table");
							Assert.assertEquals(Helper.driver.findElements(By.id(objModel.FilterHideID)).size(), 0);
						}
						Test_Variables.steps.createNode("5. Open audit log and verify that unselected column is hidden while selected column is displayed");
						Helper.driver.findElement(By.id("audit-trial-0")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						if(objModel.viewAccess) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#audit-"+objModel.FilterUnHideID+".table-header-report")).size(), 1);	
						}

						if(objModel.unviewAccess) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#audit-"+objModel.FilterHideID+".table-header-report")).size(), 0);
						}

						Helper.driver.findElement(By.cssSelector(Test_Elements.closeAudit)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						Test_Variables.test.pass("Column was hidden successfully");
						Test_Variables.results.createNode("Column was hidden successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to hide");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to shide");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail(objFilter.FilterName+" column failed to hide");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to shide");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}


	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}

		return theNewestFile;
	}

	@SuppressWarnings("unused")
	@Test (description="Test Case: Test Sites CSV Download",enabled= true, priority =10) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Sites-83: Verify user can download Sites CSV file and verify the records", "This test case will verify that user can download Sites CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Helper.driver.findElement(By.id(Test_Elements.sitesSiteID+""+Test_Elements.sitesShowFilter)).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);						
			ClickElement.clickByCss(Helper.driver, "#"+Test_Elements.clSortFilter+""+Test_Elements.sitesSiteID+" li:nth-child(3) label");

			ClickElement.clickById(Helper.driver, Test_Elements.sitesSiteID+""+Test_Elements.sitesApplyFilter);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);

			String getRowText = Helper.driver.findElement(By.id("results-found-count")).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Test_Variables.steps.createNode("5. Click on Export as CSV");	
			Test_Variables.steps.createNode("6. Verify Site ID is same in table and CSV");
			Test_Variables.steps.createNode("7. Verify Site Name is same in table and CSV");
			Test_Variables.steps.createNode("8. Verify Site Type is same in table and CSV");
			Test_Variables.steps.createNode("9. Verify Address is same in table and CSV");
			Test_Variables.steps.createNode("10. Verify Date Created is same in table and CSV");
			Test_Variables.steps.createNode("11. Verify Created By is same in table and CSV");

			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, Test_Variables.sitesCSVFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
			FileReader filereader = new FileReader(file);
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			StringBuffer buffer = new StringBuffer();
			String data[];		    
			int j = 0;
			int k = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {

					int a = Helper.driver.findElements(By.cssSelector("tr")).size();
					if (k < a) {
						System.out.print(data[i] + " ");

						int l = j+3;
						if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).size() != 0 && l<=14) {
							//	System.out.print(Helper.driver.findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText()+" ");
							softAssert.assertEquals(data[i], Helper.driver.findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText());
						}
						else {
							k = k+1;
							l =0;
							j = -1;
						}
						j++;
					}
				}
				System.out.println(" ");

			}

			Path path = Paths.get(Test_Variables.fileDownloadPath+"\\"+filename);
			long lines = 0;
			try {
				lines = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
			}

			long excludeHeader = lines - 1;
			String s = String.valueOf(excludeHeader);

			String str = getRowText;
			str = str.replace(",", "");
			Assert.assertEquals(s, str);

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
		}
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Test Sites Audit Download",enabled= true, priority = 11) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Sites-84: Verify user can download Sites Audit file", "This test case will verify that user can download Sites Audit file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Sites Log; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			ClickElement.clickByCss(Helper.driver, "#select-site-0 .custom-checkbox");
			Thread.sleep(1000);

			//String getRowCount = Helper.driver.findElement(By.id("results-found-count")).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
			Thread.sleep(1500);
			Test_Variables.steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(Helper.driver, "export-audit-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, Test_Variables.sitesCSVAuditFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
		}
	}



	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}

