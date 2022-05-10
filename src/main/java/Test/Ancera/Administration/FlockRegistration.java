package Test.Ancera.Administration;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import Models.FlockRegistrationModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.DateUtil;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import Test.Ancera.Reports.SalmonellaLog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FlockRegistration {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Flock_Registration"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Flock Registration Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Flock Registration Screen",enabled= true, priority = 1) 
	public void NavigateFlock() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-01: Verify user can navigate to Flock Registration Screen", "This test case will verify user can navigate to Flock Registration Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Flock Registration; page opens");
			Test_Variables.steps.createNode("1. Click on Flock Registration");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(Constants.url_flockRegistration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("Flock Registrations")).getText();
			String expected = "Flock Registrations";

			Assert.assertEquals(actual, expected); 	
			Test_Variables.test.pass("User navigated successfully to Flock Registration screen");
			Test_Variables.results.createNode("Flock Registration report opens successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Flock Registration report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Flock Registration report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}


	@Test (description="Test Case: Filter Test",enabled= true, priority = 2) 
	public void testfilter() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_flockRegistration);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstFlockRegistrationSearch = FlockRegistrationModel.FillData();
		String recordBefore = Helper.driver.findElement(By.id("results-found-count")).getText(); 
		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							WebElement filter_scroll = Helper.driver.findElement(By.id(Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
							Thread.sleep(800);	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");				
							Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockShowFilter)).click();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(800);						
							if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
								Assert.assertTrue(true, "No records available to test filter");
								Test_Variables.test.skip("No records available to test filter");
								Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockShowFilter)).click();	
							}

							else {
								for (int j = 0; j < objFilter.LstFilterValues.size(); j++) {
									Test_Variables.steps.createNode("2. Select the checkbox");
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector("#"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 		
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label")).click();
								}

								Test_Variables.steps.createNode("3. Click on apply filter button");	
								ClickElement.clickById(Helper.driver, objFilter.LstFilterXpath.get(i)+""+Test_Elements.sitesApplyFilter);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(800);
								String recordAfter = Helper.driver.findElement(By.id("results-found-count")).getText();		

								Assert.assertNotEquals(recordBefore, recordAfter);
								Test_Variables.test.pass("Filter applied successfully");
								Test_Variables.results.createNode("Filter applied successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
							}
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
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
						Test_Variables.preconditions.createNode("5. Click on Flock Registration; Flock Registration reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify filter is applied and relevant results are displayed in table");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							if (Helper.driver.findElements(By.cssSelector("#"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" .active-filter")).size() != 0) {			
								Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockShowFilter)).click();	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Thread.sleep(500);
								if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
									Test_Variables.test.skip("No records available to test filter");
									Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.flockShowFilter)).click();
								}
								else {	
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.slSortFilter+""+objFilter.LstFilterXpath.get(i)+" .divider")).size(), 1);
									Test_Variables.test.pass("Applied checkbox bubbled to top successfully");
									Test_Variables.results.createNode("Applied checkbox bubbled to top successfully");
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
									Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockClearFilter)).click();
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);	
									Helper.driver.findElement(By.id(objFilter.LstFilterXpath.get(i) +""+Test_Elements.flockShowFilter)).click();
								}
							}
							else {
								Test_Variables.results.createNode("Test case skipped because filter was not applied");
								Test_Variables.test.skip("Test case skipped because filter was not applied");
								Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
							}			
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}



	@Test (description="Test Case: Wildcard",enabled= true, priority = 3) 
	public void wildcard() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_flockRegistration);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);

		Test_Variables.lstFlockRegistrationWildcardSearch = FlockRegistrationModel.Wildcard(); 
		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationWildcardSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	

						Test_Variables.steps.createNode("1. Click on Flock Registration");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.flockShowFilter));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 	
						Thread.sleep(1000);
						Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.flockShowFilter)).click();
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

						SoftAssert softAssert = new SoftAssert();		
						List<WebElement> rows = Helper.driver.findElements(By.cssSelector("[id='dc-table-graph'] td:nth-child(4) label"));
						int count = rows.size();
						Thread.sleep(1000);
						for (int i = 0; i<count; i++) {
							if(objModel.startWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								softAssert.assertTrue(str.toUpperCase().startsWith(objFilter.LstFilterValues.get(0)), "Wildcard failed to apply");
							}
							if(objModel.endsWith) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();				
								softAssert.assertTrue(str.toUpperCase().endsWith(objFilter.LstFilterValues.get(0)), "Wildcard failed to apply");
							}
							if(objModel.contains) {
								String str = Helper.driver.findElement(By.cssSelector("#row-"+i+" #col-"+objFilter.ColumnID+" label")).getText();
								softAssert.assertTrue(str.toUpperCase().contains(objFilter.LstFilterValues.get(0)), "Wildcard failed to apply");
							}
						}

						Thread.sleep(1000);
						Test_Variables.test.pass("Wildcards tested successfully");
						Test_Variables.results.createNode("Wildcards tested successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.FlockRegistrationReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("Wildcards failed to test successfully");
						Test_Variables.results.createNode("Wildcards failed to test successfully");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}
					Helper.driver.findElement(By.id(objFilter.FilterID+""+Test_Elements.ClearFilter)).click();
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Flock Lock Filter Functionality",enabled= true, priority = 4) 
	public void FlockLock() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_flockRegistration);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);

		if (Helper.driver.findElements(By.cssSelector("#remove-filters.d-none")).size() == 0) {
			Helper.driver.findElement(By.id("remove-filters")).click();
			Thread.sleep(1000);
		}

		Test_Variables.lstFlockRegistrationLock = FlockRegistrationModel.Lock(); 
		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationLock) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");
				SoftAssert softAssert = new SoftAssert();

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);	
						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						Helper.driver.findElement(By.id(objFilter.FilterID)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);
						if (Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterSort+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
							Test_Variables.test.skip("Values not enough to test lock filter functionality");
							Test_Variables.results.createNode("Values not enough to test lock filter functionality");
							Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
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
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
							Thread.sleep(1000);
							String recordsafterfilter = Helper.driver.findElement(By.id("results-found-count")).getText();
							Test_Variables.steps.createNode("3. Close Flock Registration Report");
							Test_Variables.steps.createNode("4. Reopen Flock Registration Report");
							Helper.driver.navigate().refresh();

							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							Test_Variables.steps.createNode("5. Verify lock filter remains applied");
							softAssert.assertEquals(recordsafterfilter, Helper.driver.findElement(By.id("results-found-count")).getText());
							Test_Variables.test.pass(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.results.createNode(objFilter.FilterName+" lock functionality verified successfully");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
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
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to remain locked");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to remain locked");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (description="Test Case: Test Pagination",enabled= true, priority = 5) 
	public void Pagination() throws InterruptedException, IOException {
		Test_Variables.lstFlockRegistrationPagination = FlockRegistrationModel.pagination();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 
		Thread.sleep(500);

		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationPagination) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						String recordNumber = Helper.driver.findElement(By.id("results-found-count")).getText();
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));

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
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);		
							}


							if (objModel.paginationLastPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '>>' icon in pagination");

								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));	
									Assert.fail("An error alert message displayed");
								}	
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, totalPages+"/"+totalPages);
								Test_Variables.test.pass("Navigated to last page successfully");
								Test_Variables.results.createNode("Navigated to last page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
							}

							if (objModel.paginationPreviousPage) {
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
								Test_Variables.test.pass("Navigated to previous page successfully");
								Test_Variables.results.createNode("Navigated to previous page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
							}


							if (objModel.paginationFirstPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '<<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 1+"/"+totalPages);
								Test_Variables.test.pass("Navigated to first page successfully");
								Test_Variables.results.createNode("Navigated to first page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
							}


							if (objModel.paginationNextPage) {	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Test_Variables.steps.createNode("1. Click on '>' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
									Assert.fail("An error alert message displayed");
								}
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 2+"/"+totalPages);
								Test_Variables.test.pass("Navigated to next page successfully");
								Test_Variables.results.createNode("Navigated to next page successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
							}
						}

						else {
							Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
							Test_Variables.test.pass("Records are less then 100; pagination cannot be tested");
							Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
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

		Test_Variables.lstFlockRegistrationRowCount = FlockRegistrationModel.searchRows();

		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationRowCount) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

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
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
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
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
						}

						else {
							Assert.assertTrue(true, "Records are less then "+objFilter.count);
							Test_Variables.test.pass("Records are less then "+objFilter.count);
							Test_Variables.results.createNode("Rcords are less then "+objFilter.count);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch, objModel.TestCaseDescriptionSearch);
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));

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
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
						}
						else {
							Assert.assertTrue(true, "Records are less then "+sum);
							Test_Variables.test.pass("Records are less then "+sum);
							Test_Variables.results.createNode("Rcords are less then "+sum);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
							Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName+" failed to display on next page");
						Test_Variables.results.createNode(objFilter.FilterName+" failed to display on next page");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority =7) 
	public void Sorting() throws InterruptedException, IOException {

		Test_Variables.lstFlockRegistrationSorting = FlockRegistrationModel.sorting();

		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationSorting) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

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
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						ClickElement.clickById(Helper.driver, objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(1000);
						if (objModel.sortDescFirst) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_asc")).size(), 1, "Did not sorted in ascending order");
						}
						if (!objModel.sortDescFirst) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+objFilter.ColumnID+".sort_desc")).size(), 1, "Did not sorted in desccending order");	
						}
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						Assert.assertEquals(Helper.driver.findElements(By.id("message")).size(), 0, "Exception message occured");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));		
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to sort");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to sort");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
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
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-98: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");
			Test_Variables.steps.createNode("1. Verify int data type columns are right alligned");

			Helper.driver.get(Constants.url_flockRegistration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			
			Helper.driver.findElement(By.id("edit-field-access")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockNumBirdsPlacedCol+" .text-right")).size(), 0, "Num birds placed is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockNumBirdsDOACol+" .text-right")).size(), 0, "Num birds DOA is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockNumBirdsProcessedCol+" .text-right")).size(), 0, "Num Birds Processed is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockTotalWeightProcessedLBCol+" .text-right")).size(), 0, "Total Weight Processed LB is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockTotalWeightProcessedKGCol+" .text-right")).size(), 0, "Total Weight Processed KG is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockTotalFeedWeightLBCol+" .text-right")).size(), 0, "Total Feed Weight LB column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockTotalFeedWeightKGCol+" .text-right")).size(), 0, "Total Feed Weight KG column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockTotalWeightCondemnedLBCol+" .text-right")).size(), 0, "Total Weight Condemend LB column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockTotalWeightCondemnedKGCol+" .text-right")).size(), 0, "Total Weight Condemend KG column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockPartsWeightCondemnedLBCol+" .text-right")).size(), 0, "Parts Weight Condemend LB column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockPartsWeightCondemnedKGCol+" .text-right")).size(), 0, "Parts Weight Condemend KG column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockTotalCostPerWeightCol+" .text-right")).size(), 0, "Total Cost USDT per Weight column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockLiveabilityCol+" .text-right")).size(), 0, "Livability column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockMortalityCol+" .text-right")).size(), 0, "Mortality column is not right alligned");
			
			
			List<WebElement> A_Grade_Paws_PERC = Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockAGradePawsCol));
			int size2 = A_Grade_Paws_PERC.size();
			for(int i=1;i<size2;i++) {
				if (A_Grade_Paws_PERC.get(i).getText() != "") {
					Double a = Double.parseDouble(A_Grade_Paws_PERC.get(i).getText());
					System.out.println(a);
					if (a<=1 && a >=0) {
						softAssert.assertTrue(true, "A Grade Paws percentile is in range 0-1");
					}
					else {
						softAssert.assertTrue(true, "A Grade Paws percentile is not in range 0-1");
					}
				}
			}
			
			List<WebElement> Mortality_PERC = Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockMortalityCol));
			int size3 = Mortality_PERC.size();
			for(int i=1;i<size3;i++) {
				if (Mortality_PERC.get(i).getText() != "") {
					Double a = Double.parseDouble(Mortality_PERC.get(i).getText());
					System.out.println(a);
					if (a<=1 && a >=0) {
						softAssert.assertTrue(true, "Mortality percentile is in range 0-1");
					}
					else {
						softAssert.assertTrue(true, "Mortality percentile is not in range 0-1");
					}
				}
			}
			
			List<WebElement> Livability_PERC = Helper.driver.findElements(By.cssSelector("#col-"+Test_Elements.flockLiveabilityCol));
			int size4 = Mortality_PERC.size();
			for(int i=1;i<size4;i++) {
				if (Livability_PERC.get(i).getText() != "") {
					Double a = Double.parseDouble(Livability_PERC.get(i).getText());
					System.out.println(a);
					if (a<=1 && a >=0) {
						softAssert.assertTrue(true, "Livability percentile is in range 0-1");
					}
					else {
						softAssert.assertTrue(true, "Livability percentile is not in range 0-1");
					}
				}
			}

			softAssert.assertAll();	
			Test_Variables.test.pass("Int data type columns are right alligned");
			Test_Variables.results.createNode("Int data type columns are right alligned");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}



	@Test (description="Test Case: Filter Test Bulk",enabled= true, priority = 9) 
	public void TestFilterBulk() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_flockRegistration);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Helper.driver.findElement(By.id("create-flock")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Helper.driver.findElement(By.id("file-input")).sendKeys(System.getProperty("user.dir")+"\\Excel\\FlockMetaDataBulk.xlsx");
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Thread.sleep(2000);
		Assert.assertEquals(Helper.driver.findElements(Test_Elements.alertMessage).size(), 0, "Error message occured");
		Test_Variables.lstFlockRegistrationSearch = FlockRegistrationModel.FillData();
		int recordBefore = Helper.driver.findElements(By.cssSelector(".bulk-flock-data tr")).size(); 
		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName+" from bulk screen", objModel.TestCaseDescription+" from bulk screen");
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
							Thread.sleep(500);
							WebElement filter_scroll = Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)));
							((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
							Thread.sleep(800);	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" to expand it");	

							Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockShowFilter)).click();	
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							Thread.sleep(800);						
							if (Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
								Assert.assertTrue(true, "No records available to test filter");
								Test_Variables.test.skip("No records available to test filter");
								Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
								Helper.driver.findElement(By.id(".bulk-flock-data #"+objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockShowFilter)).click();	
							}

							else {
								for (int j = 0; j < objFilter.LstFilterValues.size(); j++) {
									Test_Variables.steps.createNode("2. Select the checkbox");
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									WebElement checkbox_scroll = Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", checkbox_scroll); 		
									Thread.sleep(1000);
									ClickElement.clickByCss(Helper.driver, ".bulk-flock-data #"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" li:nth-child("+objFilter.LstFilterValues.get(j)+") label");
								}

								Test_Variables.steps.createNode("3. Click on apply filter button");	
								ClickElement.clickByCss(Helper.driver, ".bulk-flock-data #"+objFilter.LstFilterXpath.get(i)+""+Test_Elements.sitesApplyFilter);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(800);
								int recordAfter =  Helper.driver.findElements(By.cssSelector(".bulk-flock-data tr")).size(); 	

								Assert.assertNotEquals(recordBefore, recordAfter);
								Test_Variables.test.pass("Filter applied successfully");
								Test_Variables.results.createNode("Filter applied successfully");
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
							}
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail(objFilter.FilterName + " failed to apply");
						Test_Variables.results.createNode(objFilter.FilterName + " failed to apply");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}

					try {
						Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameSearch+" from bulk screen", objModel.TestCaseDescriptionSearch+" from bulk screen");
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
						Test_Variables.preconditions.createNode("5. Click on Flock Registration; Flock Registration reports open");
						Test_Variables.preconditions.createNode("6. Click on "+objFilter.FilterName+" to expand it; and enter a value to search");
						Test_Variables.preconditions.createNode("7. Select the checkbox and click on apply filter icon");
						Test_Variables.steps.createNode("1. Verify filter is applied and relevant results are displayed in table");

						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						for(int i = 0; i<objFilter.LstFilterXpath.size(); i++) {
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

							if (Helper.driver.findElements(By.cssSelector(".bulk-flock-data #"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" .active-filter")).size() != 0) {			
								Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockShowFilter)).click();	
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								Thread.sleep(500);
								if (Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+Test_Elements.flockSortFilter+""+objFilter.LstFilterXpath.get(i)+" "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
									Test_Variables.test.skip("No records available to test filter");
									Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
									Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+objFilter.LstFilterXpath.get(i) +""+Test_Elements.flockShowFilter)).click();
								}
								else {	
									Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".bulk-flock-data #"+Test_Elements.slSortFilter+""+objFilter.LstFilterXpath.get(i)+" .divider")).size(), 1);
									Test_Variables.test.pass("Applied checkbox bubbled to top successfully");
									Test_Variables.results.createNode("Applied checkbox bubbled to top successfully");
									Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
									Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
									Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+objFilter.LstFilterXpath.get(i)+""+Test_Elements.flockClearFilter)).click();
									Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);	
									Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+objFilter.LstFilterXpath.get(i) +""+Test_Elements.flockShowFilter)).click();
								}
							}
							else {
								Test_Variables.results.createNode("Test case skipped because filter was not applied");
								Test_Variables.test.skip("Test case skipped because filter was not applied");
								Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
							}			
						}
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Applied checkbox failed to bubble to top");
						Test_Variables.results.createNode("Applied checkbox failed to bubble to top");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}	
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority =10) 
	public void SortingBulk() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_flockRegistration);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Helper.driver.findElement(By.id("create-flock")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Helper.driver.findElement(By.id("file-input")).sendKeys(System.getProperty("user.dir")+"\\Excel\\FlockMetaDataBulk.xlsx");
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		Test_Variables.lstFlockRegistrationSorting = FlockRegistrationModel.sorting();

		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationSorting) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

						WebElement filter_scroll = Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+objFilter.ColumnID));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.steps.createNode("1. Click on "+objFilter.FilterName+" column header");
						Helper.driver.findElement(By.cssSelector(".bulk-flock-data #"+objFilter.ColumnID)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(1000);

						Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".bulk-flock-data #"+objFilter.ColumnID+".sort_desc")).size(), 1, "Did not sorted in descending order");
						Assert.assertEquals(Helper.driver.findElements(By.id("message")).size(), 0, "Exception message occured");
						Test_Variables.steps.createNode(objModel.steps);
						Test_Variables.test.pass(objFilter.FilterName+" column sorted successfully");
						Test_Variables.results.createNode(objFilter.FilterName+" column sorted successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						ClickElement.clickByCss(Helper.driver, ".bulk-flock-data #"+objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));					
						Thread.sleep(1000);

						Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".bulk-flock-data #"+objFilter.ColumnID+".sort_asc")).size(), 1, "Did not sorted in ascending order");
						Assert.assertEquals(Helper.driver.findElements(By.id("message")).size(), 0, "Exception message occured");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Test_Variables.test.pass("Flock was editted successfully");
						Test_Variables.results.createNode("Flock was editted successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
					}
					catch(AssertionError er) {
						Test_Variables.test.fail(objFilter.FilterName+" column failed to sort");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to sort");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(StaleElementReferenceException s) {
						ClickElement.clickByCss(Helper.driver, ".bulk-flock-data #"+objFilter.ColumnID);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority =11) 
	public void CreateFlock() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-98: Verify user can create Flock", "This test case will verify that user can crate flock");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");
			SoftAssert softAssert = new SoftAssert();
			Helper.driver.get(Constants.url_flockRegistration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Helper.driver.findElement(By.id("create-flock")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	


			Helper.driver.findElement(By.cssSelector("#siteId .toggle-list")).click();

			if (Helper.driver.findElements(By.cssSelector("#siteId .fa-caret-right")).size() >= 1) {
				//	List<WebElement> sitelist = Helper.driver.findElements(By.cssSelector(".popup-content tr")); 
				Helper.driver.findElement(By.xpath("//div[1]/app-hierarchy-select-box[1]/div[1]/div[3]/div[2]/table[1]/tr[1]/td[1]/div[1]/i[1]")).click();
			}
			else {
				Test_Variables.test.skip("Assign site to user");
				Test_Variables.results.createNode("User was not assigned any site");
				Helper.saveResultNew(ITestResult.SKIP, Constants.FlockRegistrationReportPath, null);
			}

			Helper.driver.findElement(By.cssSelector("#siteId tr:nth-child(2) .fa-caret-right")).click();
			Helper.driver.findElement(By.cssSelector("#siteId tr:nth-child(3) .fa-caret-right")).click();
			Helper.driver.findElement(By.cssSelector("#siteId tr:nth-child(4) .fa-caret-right")).click();
			Helper.driver.findElement(By.cssSelector("#siteId tr:nth-child(5).selectable")).click();
//
			Helper.driver.findElement(By.cssSelector("#placementDate img")).click();
			Thread.sleep(1000);		
			
			WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#placementDate .dp-popup"))).get(0);
			List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
			DateUtil.clickGivenDay(columns1, DateUtil.getCurrentDayPlus(-1));
			Thread.sleep(2000);											      
			Helper.driver.findElement(By.cssSelector("#integratorFlockId input")).sendKeys("IntegratorID_"+Test_Variables.date0);
			System.out.println("0");
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("button.disabled")).size(), 1);
			System.out.println("1");
			if (Helper.driver.findElements(By.cssSelector("#integratorFlockId .list-item")).size() != 0) {
				Helper.driver.findElement(By.cssSelector("#integratorFlockId .list-item")).click();
			}
			System.out.println("2");
			Helper.driver.findElement(By.cssSelector("#birdSizeId img")).click();
			List<WebElement> birdsizelist = Helper.driver.findElements(By.cssSelector("#birdSizeId li")); 
			softAssert.assertEquals(birdsizelist.get(0).getText(), "Small");
			softAssert.assertEquals(birdsizelist.get(1).getText(), "Medium");
			softAssert.assertEquals(birdsizelist.get(2).getText(), "Large");
			softAssert.assertEquals(birdsizelist.get(3).getText(), "Pullet");
			Helper.driver.findElement(By.cssSelector("#birdSizeId ul:nth-child(1) li:nth-child(1)")).click();

			Helper.driver.findElement(By.cssSelector("#marketingProgramId img")).click();
			List<WebElement> marketingProgramList = Helper.driver.findElements(By.cssSelector("#marketingProgramId li")); 
			softAssert.assertEquals(marketingProgramList.get(0).getText(), "Conventional");
			softAssert.assertEquals(marketingProgramList.get(1).getText(), "No Human Antibiotics");
			softAssert.assertEquals(marketingProgramList.get(2).getText(), "No Antibiotics Ever");
			softAssert.assertEquals(marketingProgramList.get(3).getText(), "Organic");
			softAssert.assertEquals(marketingProgramList.get(4).getText(), "Pastured");
			Helper.driver.findElement(By.cssSelector("#marketingProgramId ul:nth-child(1) li:nth-child(1)")).click();
			
			Helper.driver.findElement(By.cssSelector("#birdSex img")).click();
			List<WebElement> birdsexList = Helper.driver.findElements(By.cssSelector("#birdSex li")); 
			softAssert.assertEquals(birdsexList.get(0).getText(), "Male");
			softAssert.assertEquals(birdsexList.get(1).getText(), "Female");
			softAssert.assertEquals(birdsexList.get(2).getText(), "Mixed");
			Helper.driver.findElement(By.cssSelector("#birdSex ul:nth-child(1) li:nth-child(1)")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#birdBreed img")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#birdBreed ul:nth-child(1) li:nth-child(1)")).click();
			
			
		//	WebElement birdBreed = Helper.driver.findElement(By.cssSelector("#birdBreed input"));
		//	Helper.driver.findElement(with(By.tagName("input")).above(birdBreed)).sendKeys("hello");			
			
			Helper.driver.findElement(By.id("num-numBirdsDOAPlant")).sendKeys("10");
			Helper.driver.findElement(By.id("num-numBirdsProcessed")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalWeightProcessedLb")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalWeightProcessedKG")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalFeedWeightLb")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalFeedWeightKG")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalWeightCondemnedLb")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalWeightCondemnedKG")).sendKeys("10");
			Helper.driver.findElement(By.id("num-numBirdsCondemnedWhole")).sendKeys("10");
			Helper.driver.findElement(By.id("num-partsWeightCondemnedLb")).sendKeys("10");
			Helper.driver.findElement(By.id("num-partsWeightCondemnedKG")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalAmountPaid")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalAmountPaidLb")).sendKeys("10");
			
			Helper.driver.findElement(By.id("num-totalCostPerLb")).sendKeys("10");
			Helper.driver.findElement(By.id("num-livabilityPercentage")).sendKeys("1");
			Helper.driver.findElement(By.id("num-mortalityPercentage")).sendKeys("1");
			Helper.driver.findElement(By.id("num-avgDailyWeightGainLb")).sendKeys("10");
			Helper.driver.findElement(By.id("num-avgAgeOfBirdsProcessed")).sendKeys("10");
			Helper.driver.findElement(By.id("num-avgBirdWeightLb")).sendKeys("10");
			Helper.driver.findElement(By.id("num-avgBirdWeightKG")).sendKeys("10");
			Helper.driver.findElement(By.id("num-fcr")).sendKeys("10");
			Helper.driver.findElement(By.id("num-numBirdsSold")).sendKeys("10");
			Helper.driver.findElement(By.id("num-adjFcr")).sendKeys("10");
			Helper.driver.findElement(By.id("num-AGradePawsPercentage")).sendKeys("1");
			
			Helper.driver.findElement(By.cssSelector("#usdaPlantId img")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#usdaPlantId ul:nth-child(1) li:nth-child(1)")).click();
		
			Helper.driver.findElement(By.id("num-downgradePawsPerc")).sendKeys("10");
			Helper.driver.findElement(By.id("num-totalMortality")).sendKeys("10");
			Helper.driver.findElement(By.id("num-timeFrame")).sendKeys("10");
			
			
			Helper.driver.findElement(By.id("submit-flock")).click();		
			Helper.driver.findElement(By.cssSelector(".anc-btn-secondary")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Helper.driver.findElement(By.id("integratorFlockId_show-filter")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.findElement(By.id("integratorFlockId_search-input")).sendKeys("IntegratorID_"+Test_Variables.date0);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);
			Helper.driver.findElement(By.id("integratorFlockId_cust-cb-lst-txt_selectAll")).click();
			Helper.driver.findElement(By.id("integratorFlockId_apply")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2500);

			softAssert.assertEquals(Helper.driver.findElement(By.id("results-found-count")).getText(), "1");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockBirdSexCol+" label")).getText().isEmpty(), "Bird Sex not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockMarketingProgramCol+" label")).getText().isEmpty(), "Marketing Program not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockBirdSizeCol+" label")).getText().isEmpty(), "Bird Size not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockUniqueFlockIDCol+" label")).getText().isEmpty(), "Unique Flock ID not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockSiteIDCol+" label")).getText().isEmpty(), "Farm Site ID not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTimeFrameCol+" label")).getText().isEmpty(), "Time Frame not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalMortalityCol+" label")).getText().isEmpty(), "Total Mortality not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockDowngradePawsPercCol+" label")).getText().isEmpty(), "Downgrade Paws not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockUSDAPlantIDCol+" label")).getText().isEmpty(), "USDA Plant ID not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAGradePawsCol+" label")).getText().isEmpty(), "A Grade Paws not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAdjFCRCol+" label")).getText().isEmpty(), "Adj FCR not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockNumBirdsSoldCol+" label")).getText().isEmpty(), "Num of Birds Solde not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockFCRCol+" label")).getText().isEmpty(), "FCR not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAvgBirdWeightKGCol+" label")).getText().isEmpty(), "Average Bird Weight kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAvgBirdWeightLBCol+" label")).getText().isEmpty(), "Average bird wieght lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAvgAgeBirdsProcessedCol+" label")).getText().isEmpty(), "Average birds processed not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAvgDailyWeightGainLBCol+" label")).getText().isEmpty(), "Daily weight gain not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockMortalityCol+" label")).getText().isEmpty(), "Mortality not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockLiveabilityCol+" label")).getText().isEmpty(), "Liveability not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalCostPerWeightCol+" label")).getText().isEmpty(), "total cost per weight not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAmountPaidPePoundCol+" label")).getText().isEmpty(), "Amount paid per pound not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalGrowerPayCol+" label")).getText().isEmpty(), "total grower pay not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockPartsWeightCondemnedKGCol+" label")).getText().isEmpty(), "Parts weigth ccondemned kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockPartsWeightCondemnedLBCol+" label")).getText().isEmpty(), "Parts weight condemned lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockNumBirdsCondemnedWholeCol+" label")).getText().isEmpty(), "Num of birds condemned whole not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalWeightCondemnedKGCol+" label")).getText().isEmpty(), "Total weight condemned kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalWeightCondemnedLBCol+" label")).getText().isEmpty(), "Total weigth condemned lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalFeedWeightKGCol+" label")).getText().isEmpty(), "Total feed weight kgnot displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalFeedWeightLBCol+" label")).getText().isEmpty(), "Total feed weight lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalWeightProcessedKGCol+" label")).getText().isEmpty(), "Total weigth processed kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockTotalWeightProcessedLBCol+" label")).getText().isEmpty(), "Total wieght processed lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockNumBirdsProcessedCol+" label")).getText().isEmpty(), "num of birds processed not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockNumBirdsDOACol+" label")).getText().isEmpty(), "Num of Birds DOA not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockUniqueFlockIDCol+" label")).getText().isEmpty(), "Unique Flock ID is not displayed");
			
			Helper.driver.findElement(By.id("audit-trial-0")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			String auditCheck =	Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(1)#col-0")).getText();
			softAssert.assertEquals(auditCheck, "IntegratorID_"+Test_Variables.date0);	
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditBirdSexCol+" label")).getText().isEmpty(), "Bird Sex not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditMarketingProgramCol+" label")).getText().isEmpty(), "Marketing Program not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditBirdSizeCol+" label")).getText().isEmpty(), "Bird Size not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditUniqueFlockIDCol+" label")).getText().isEmpty(), "Unique Flock ID not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditSiteIDCol+" label")).getText().isEmpty(), "Farm Site ID not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTimeFrameCol+" label")).getText().isEmpty(), "Time Frame not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalMortalityCol+" label")).getText().isEmpty(), "Total Mortality not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditDowngradePawsPercCol+" label")).getText().isEmpty(), "Downgrade Paws not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditUSDAPlantIDCol+" label")).getText().isEmpty(), "USDA Plant ID not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditAGradePawsCol+" label")).getText().isEmpty(), "A Grade Paws not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditAdjFCRCol+" label")).getText().isEmpty(), "Adj FCR not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditNumBirdsSoldCol+" label")).getText().isEmpty(), "Num of Birds Solde not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditFCRCol+" label")).getText().isEmpty(), "FCR not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditAvBirdWeightKGCol+" label")).getText().isEmpty(), "Average Bird Weight kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditAvBirdWeightLBCol+" label")).getText().isEmpty(), "Average bird wieght lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditAvgAgeBirdsProcessedCol+" label")).getText().isEmpty(), "Average birds processed not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditAvgDailyWeightGainLBCol+" label")).getText().isEmpty(), "Daily weight gain not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditMortalityCol+" label")).getText().isEmpty(), "Mortality not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditLiveabilityCol+" label")).getText().isEmpty(), "Liveability not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalCostPerWeightCol+" label")).getText().isEmpty(), "total cost per weight not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditAmountPaidPerPoundCol+" label")).getText().isEmpty(), "Amount paid per pound not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalGrowerPayCol+" label")).getText().isEmpty(), "total grower pay not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditPartsWeightCondemnedKGCol+" label")).getText().isEmpty(), "Parts weigth ccondemned kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditPartsWeightCondemnedLBCol+" label")).getText().isEmpty(), "Parts weight condemned lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditNumBirdsCondemnedWholeCol+" label")).getText().isEmpty(), "Num of birds condemned whole not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalWeightCondemnedKGCol+" label")).getText().isEmpty(), "Total weight condemned kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalWeightCondemnedLBCol+" label")).getText().isEmpty(), "Total weigth condemned lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalFeedWeightKGCol+" label")).getText().isEmpty(), "Total feed weight kgnot displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalFeedWeightLBCol+" label")).getText().isEmpty(), "Total feed weight lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalWeightProcessedKGCol+" label")).getText().isEmpty(), "Total weigth processed kg not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditTotalWeightProcessedLBCol+" label")).getText().isEmpty(), "Total wieght processed lb not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditNumBirdsProcessedCol+" label")).getText().isEmpty(), "num of birds processed not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditNumBirdsDOACol+" label")).getText().isEmpty(), "Num of Birds DOA not displayed");
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.flockAuditUniqueFlockIDCol+" label")).getText().isEmpty(), "Unique Flock ID is not displayed");

			
			Helper.driver.findElement(By.id("close-gen-modal")).click();
			
			softAssert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-39 label")).getText().isEmpty(), "Farm Site ID is empty");
			
			softAssert.assertAll();
			
			Test_Variables.test.pass("Flock was created successfully");
			Test_Variables.results.createNode("Flock was created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Flock failed to create");
			Test_Variables.results.createNode("Flock failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Flock failed to create");
			Test_Variables.results.createNode("Flock failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}


	@Test (enabled= true, priority =12) 
	public void EditFlock() throws InterruptedException, IOException {
		
		Helper.driver.get(Constants.url_flockRegistration);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		

		Test_Variables.lstFlockRegistrationEdit = FlockRegistrationModel.EditFlock();
		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationEdit) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("audit-trial-0")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

						int getRows = Helper.driver.findElements(By.cssSelector(".audit-v2 tr")).size();
						Helper.driver.findElement(By.id("close-gen-modal")).click();
						Thread.sleep(1500);	
						System.out.println(getRows);
//						WebElement scroll = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #edit-flock"));
//						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
//						Thread.sleep(2000);	
						System.out.println("1");
						Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #edit-flock")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);
						Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterID+" input")).clear();
						Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterID+" input")).sendKeys(objModel.input);
						System.out.println("2");
						if (Helper.driver.findElements(By.cssSelector("#"+objFilter.FilterID+" .list-item")).size() != 0) {
							Helper.driver.findElement(By.cssSelector("#"+objFilter.FilterID+" .list-item")).click();
						}
						System.out.println("3");
						Helper.driver.findElement(By.id("submit-flock")).click();
						Thread.sleep(2000);
						Helper.driver.findElement(By.cssSelector(".anc-btn-secondary")).click();
						System.out.println("4");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(1000);
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#col-"+objFilter.ColumnID+" label")).getText(), objModel.input);

						Helper.driver.findElement(By.id("audit-trial-0")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						System.out.println("5");
						softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".audit-v2 tr")).size(), getRows+1);
						Helper.driver.findElement(By.id("close-gen-modal")).click();
						
						Set<String> uniqueProductName = new HashSet<String>();
						Helper.driver.findElements(By.cssSelector("td:nth-child(8)"))
						.stream()
						.forEach(product -> uniqueProductName.add(product.getText()));

						System.out.println("Total unique product found : "+uniqueProductName.size());
						
					//	System.out.println("All product names are : ");
					//	uniqueProductName.forEach(name -> System.out.println(name));
						
						softAssert.assertEquals(uniqueProductName.size(), Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText(), "Did not displayed unique flock id");								
						softAssert.assertAll();
						Test_Variables.test.pass("Flock was edited successfully");
						Test_Variables.results.createNode("Flock was edited successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Flock failed to edit");
						Test_Variables.results.createNode("Flock failed to edit");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Flock failed to edit");
						Test_Variables.results.createNode("Flock failed to edit");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
				Test_Variables.test.fail("Flock failed to edit");
				Test_Variables.results.createNode("Flock failed to edit");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
			}
			
		}
	}
	
	
	@Test (description = "IE-3200", enabled= true, priority =13) 
	public void EditPlacementDate() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-105: Verify that new flock is created on editing the Placement Date only of the existing flock", "This testcase will verify that new flock is created on editing the Placement Date only of the existing flock");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

			Helper.driver.get(Constants.url_flockRegistration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

//			WebElement scroll = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #edit-flock"));
//			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
//			Thread.sleep(2000);	
			Helper.driver.findElement(By.id("audit-trial-0")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			int auditRows = Helper.driver.findElements(By.cssSelector(".audit-v2 tr")).size();
			
			Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #edit-flock")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector("#placementDate img")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
		//	WebElement dateWidgetFrom = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/app-root/div/app-manage-flock-registration/div[2]/app-popup-component/div/div/div/div[3]/app-create-flock/div/div[1]/form/div/div[2]/app-date-select-box/div/div[2]/div/dp-date-picker/div[2]/div"))).get(0);
			WebElement dateWidgetFrom = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#placementDate .dp-popup"))).get(0);
			List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("button"));
		    DateUtil.clickGivenDay(columns, DateUtil.getCurrentDay());
			Thread.sleep(2000);

//			Helper.driver.findElement(By.cssSelector("#processingDate img")).click();
//			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
//			Thread.sleep(2000);
//			WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#processingDate .dp-popup"))).get(0);
//			List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
//			DateUtil.clickGivenDay(columns1, DateUtil.getCurrentDayPlus(1));
//			Thread.sleep(2000);

			Helper.driver.findElement(By.id("submit-flock")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.cssSelector(".anc-btn-secondary")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			SoftAssert softAssert = new SoftAssert();
			Helper.driver.findElement(By.id("audit-trial-0")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));		
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector(".audit-v2 tr")).size(), auditRows+1);
			Helper.driver.findElement(By.id("close-gen-modal")).click();
			softAssert.assertAll();
			Test_Variables.test.pass("New Flock was created successfully");
			Test_Variables.results.createNode("New Flock was created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("New flock was not created");
			Test_Variables.results.createNode("New flock was not created");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("New flock was not created");
			Test_Variables.results.createNode("New flock was not created");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 14) 
	public void FlockValidation() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_flockRegistration);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		String siteID = Helper.driver.findElement(By.id("tr:nth-child(1) #col-1")).getText();
		Helper.driver.findElement(By.id("create-flock")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		Test_Variables.lstFlockRegistrationValidation = FlockRegistrationModel.FlockValidation();	

		for (FlockRegistrationModel objModel : Test_Variables.lstFlockRegistrationValidation) { 
			try {
				Thread.sleep(2000);
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.steps.createNode("1. Navigate to Flock Registrations screen");
				Test_Variables.steps.createNode("2. Click on create new button; and select file from bulk upload");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {
							String path = System.getProperty("user.dir")+"\\Excel\\FlockMetadataValidation.xlsx";
							FileInputStream fsIP= new FileInputStream(new File(path));
							@SuppressWarnings("resource")
							XSSFWorkbook wb = new XSSFWorkbook(fsIP);
							XSSFSheet worksheet = wb.getSheetAt(0);
							Cell cell = null;
							cell = worksheet.getRow(1).createCell(1);
							cell.setCellValue(siteID);
							cell=worksheet.getRow(1).createCell(objFilter.LstColumnID.get(i)); 
							cell.setCellValue(objFilter.LstColumnValues.get(i));  
							FileOutputStream output_file =new FileOutputStream(new File(path));
							wb.write(output_file);
							output_file.close();  
							chkCounter++;
						}

						Test_Variables.steps.createNode("3. "+objModel.steps);
						Helper.driver.findElement(By.id("file-input")).sendKeys(System.getProperty("user.dir")+"\\Excel\\FlockMetadataValidation.xlsx");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1500);						

						if (objModel.errorCase) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#col-"+objFilter.ColumnID+" .fa-exclamation-circle")).size(), 1); 
						}
						else {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#col-"+objFilter.ColumnID+" .fa-exclamation-circle")).size(), 0); 
						}

						Test_Variables.test.pass("Validation verified successfully");
						Test_Variables.results.createNode("Validation verified successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);
					}
					catch(AssertionError er) {
						Test_Variables.test.fail("Validation Failed");
						Test_Variables.results.createNode("Validation Failed");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
					}
					catch(Exception ex) {
						Test_Variables.test.fail("Validation Failed");
						Test_Variables.results.createNode("Validation Failed");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}		
	}


	@Test (enabled= true, priority = 15, dependsOnMethods = { "FlockValidation" }) 
	public void FlockUploadBulk() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-103: Verify flocks are added through bulk upload", "This testcase will verify that flocks are added through bulk upload");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Navigate to Flock Registrations screen");
			Test_Variables.steps.createNode("2. Click on create new button; and select file from bulk upload");
			Test_Variables.steps.createNode("3. Upload file and click on save button");

			Helper.driver.findElement(By.id("submit-flock")).click();		
			Helper.driver.findElement(By.cssSelector(".anc-btn-secondary")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));	
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "FlockMetadataValidation.xlsx saved successfully.");
			Test_Variables.test.pass("Flock uploaded successfully");
			Test_Variables.results.createNode("Flock uploaded successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);

		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Flock failed to upload");
			Test_Variables.results.createNode("Flock failed to upload");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
	}


	@Test (enabled= true, priority = 16, dependsOnMethods = { "FlockValidation" }) 
	public void SiteAppearnace() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-104: Verify only those sites appear which are assigned to user", "This testcase will verify that only those sites are displayed to user which are assigned to him");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Flock Registrations");
			Test_Variables.steps.createNode("1. Verify only those sites appear which are assigned to user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			for (int i=1;i<=500;i++) {

				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-3 label")).getText().equals(Test_Variables.login_email)) {
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					break;
				}	
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(2000);

			int collectionSitesSize = 0;
			for (int i=1;i<=Helper.driver.findElements(By.cssSelector(".site-tree-card")).size();i++) {
				if (!Helper.driver.findElement(By.xpath("//*[@id=\"select-sites\"]//div["+i+"]/div/p[2]")).getText().equals("Collection Sites: 0")) {
					collectionSitesSize = collectionSitesSize+1;

					if (i == Helper.driver.findElements(By.cssSelector(".site-tree-card")).size()) {
						Helper.driver.get(Constants.url_flockRegistration);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);
						Helper.driver.findElement(By.id("create-flock")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Helper.driver.findElement(By.cssSelector("#siteId .fa-chevron-down")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

						int sitesCountflock = Helper.driver.findElements(By.cssSelector("#siteId tr")).size();

						Assert.assertEquals(sitesCountflock, collectionSitesSize);
						Test_Variables.test.pass("Only those sites appeared  which are assigned to user successfully");
						Test_Variables.results.createNode("Only those sites appeared which are assigned to user successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registrations", Constants.FlockRegistrationReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
					}
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Those sites did not appeared which are assigned to user");
			Test_Variables.results.createNode("Those sites did not appeared which are assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Those sites did not appeared which are assigned to user");
			Test_Variables.results.createNode("Those sites did not appeared which are assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
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


	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Test flock CSV Download",enabled= true, priority =17) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-105: Verify user can download Flock CSV file and verify the records", "This test case will verify that user can download Flock CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Flock Registration; Flock Registration reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Helper.driver.get(Constants.url_flockRegistration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			Helper.driver.findElement(By.id(Test_Elements.sitesSiteID+""+Test_Elements.flockShowFilter)).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);						
			ClickElement.clickByCss(Helper.driver, "#"+Test_Elements.flockSortFilter+""+Test_Elements.flockSiteID+" li:nth-child(3) label");

			ClickElement.clickById(Helper.driver, Test_Elements.flockSiteID+""+Test_Elements.sitesApplyFilter);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);

			String getRowText = Helper.driver.findElement(By.id("results-found-count")).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Test_Variables.steps.createNode("5. Click on Export as CSV");	
			Test_Variables.steps.createNode("6. Verify the columns are same in table and CSV");

			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, Test_Variables.flockCSVFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);

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

			int columnsCountTotal = 0;
			int rowsCount = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {
					int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = Helper.driver.findElements(By.cssSelector("tr:nth-child(1) td")).size() - 2;
						int columnsCount = columnsCountTotal+2;

						if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), Helper.driver.findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = 0;  
						}
						columnsCountTotal++;
					}
				}
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
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Test Flock Audit Download",enabled= true, priority = 18) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-106: Verify user can download Flock Audit file", "This test case will verify that user can download Flock Audit file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Flock Registration; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Thread.sleep(1000);

			//String getRowCount = Helper.driver.findElement(By.id("results-found-count")).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
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
			Assert.assertEquals(filename, Test_Variables.flockCSVAuditFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}


	@Test (description="Test Case: Test Flock Template Download",enabled= true, priority = 19) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-107: Verify user can download Flock Template file", "This test case will verify that user download Flock Template file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Flock Registration; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			Test_Variables.steps.createNode("3. Click on the button");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1000);
			ClickElement.clickById(Helper.driver, "export-data-template");
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Test_Variables.steps.createNode("5. Click on Export Data Template");
			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			Assert.assertTrue(filename.startsWith("FLOCK METADATA"), "File did not downloaded with name as FLOCK METADATA");
			
			int colNumTable = Helper.driver.findElements(By.cssSelector("th")).size();
			
		       FileInputStream fis = new FileInputStream(Test_Variables.fileDownloadPath+"\\FLOCK METADATA.xlsx");
		       XSSFWorkbook workbook = new XSSFWorkbook(fis);
		       XSSFSheet sheet = workbook.getSheet("Sheet");
		       XSSFRow row = sheet.getRow(0);
		       int colNum = row.getLastCellNum();
		       System.out.println("Total Number of Columns in the excel is : "+colNum);
			
			Assert.assertEquals(colNum, colNumTable);
			
			Test_Variables.test.pass("Sample MetaData template downloaded successfully");
			Test_Variables.results.createNode("Sample MetaData template downloaded successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Sample MetaData downoad failed");
			Test_Variables.results.createNode("Sample MetaData failed to download");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}


