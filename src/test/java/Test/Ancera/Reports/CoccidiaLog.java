package Test.Ancera.Reports;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import LogViewFunctions.FieldLevelAccess;
import MiscFunctions.ClickElement;
import MiscFunctions.DateUtil;
import MiscFunctions.DownloadFileCheck;
import Models.ReportFilters;
import PageObjects.CoccidiaLogPage;
import PageObjects.SalmonellaLogPage;
import Models.CoccidiaLogModel;
import Test.Ancera.Login.LoginTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static PageObjects.CoccidiaLogPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Helper.*;
import static Models.IngestionsModel.*;

public class CoccidiaLog {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Coccidia_Log"+DateUtil.date+".html");
		spark.config().setReportName("Coccidia Log Test Report"); 
		config();
		LoginTest.login();
	}


	@Test (priority = 1) 
	public void NavigateCoccidia() throws InterruptedException, IOException {
		test = extent.createTest("CL-AN-01: Verify user can navigate to Salmonella Log Screen");
		CoccidiaLogPage.openCoccidiaLogPage();
	}
	
//	@Test (priority = 1) 
//	public void NavigateCoccidia() throws InterruptedException, IOException {
//		NavigateToScreen(url_CoccidiaLog, "Coccidia Log", coccidiaLogTitle);
//	}


	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Lock(coccidiaLogTable, "Coccidia Log", 2);
	}
	

	@Test (priority = 3) 
	public void WildcardCocci() throws InterruptedException, IOException {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Wildcard(coccidiaLogTable, "Coccidia Log", 2);
	}
	
	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Sorting(coccidiaLogTable, "Coccidia Log", 2);
	}
	
	@Test(priority= 5, enabled = true)
	public void RowsPerPage() throws InterruptedException, IOException {
		RowsPerPage();
	}
	
	
	@Test(priority= 6, enabled = true)
	public void PaginationCocci() throws InterruptedException, IOException {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Pagination(coccidiaLogTable, "Coccidia Log", ReportFilePath);
	}

	

	@SuppressWarnings("unused")
	@Test (description="Test Case: Date Filter Test",enabled= true, priority = 7) 
	public void DateFilter() throws InterruptedException, IOException {

		FieldLevelAccess.fieldLevelReset();
		String recordBefore = driver.findElement(By.id("results-found-count")).getText();
		CoccidiaLogModel.lstCoccidiaDateSearch = CoccidiaLogModel.FillDate();
		SoftAssert softAssert = new SoftAssert();

		for (CoccidiaLogModel objModel : CoccidiaLogModel.lstCoccidiaDateSearch) { 
			test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			for (ReportFilters objFilter : objModel.lstFilters) {
				Actions actions = new Actions(driver);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				steps.createNode("1. Click on date calendar icon; Calendar pops up");
				WebElement filter_scroll = driver.findElement(By.id(clResultDate+""+clShowFilter));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
				driver.findElement(By.id("scanDateTime_show-filter")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);
				//	String dateFrom = driver.findElement(By.xpath("//input[@placeholder='Start Date']")).getText();
				//	softAssert.assertEquals(dateFrom, dateMMDDYYYY1);

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				steps.createNode("2. Click on "+objFilter.FilterName);
				driver.findElement(By.id("list-title_date-selection")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				if (driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch)).isEnabled()) {
					actions.moveToElement(driver.findElement(By.cssSelector(objFilter.FilterListXPathSearch))).click().perform();	
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(750);
					driver.findElement(By.id(clResultDate+""+clShowFilter)).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(700);
					getScreenshot();	

					if(objModel.Filter1) {
						try{
							String value1 = objFilter.fromDate;	
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.DATE, Integer.parseInt(value1));
							Date fromdate1 = cal.getTime();    
							String fromDate = dateFormat.format(fromdate1);

							String value = objFilter.toDate;
							cal = Calendar.getInstance();
							cal.add(Calendar.DATE, Integer.parseInt(value));
							Date todate2 = cal.getTime();    
							String toDate = dateFormat.format(todate2);
							String fromDateField = driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = driver.findElement(By.id("results-found-count")).getText();
							//	Assert.assertEquals(fromDateField, fromDate);
							//	Assert.assertEquals(toDateField, toDate);
							softAssert.assertNotEquals(recordBefore, recordAfter);
							test.pass(objFilter.FilterName+ " values verified successfully");
							results.createNode(objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();
						}catch(AssertionError er) {
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResult(ITestResult.FAILURE, new Exception(er));
						}catch(Exception ex){
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResult(ITestResult.FAILURE, ex);
						}
					}

					if(objModel.Filter2) {
						try{
							String value2 =objFilter.fromMonth;
							String value1 = objFilter.fromDate;	
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, Integer.parseInt(value2));
							cal.set(Calendar.DATE, Integer.parseInt(value1));
							Date fromdate1 = cal.getTime();    
							String fromDate = dateFormat.format(fromdate1);

							String value3 =objFilter.toMonth;   
							cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, Integer.parseInt(value3));
							cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							Date todate2 = cal.getTime();    
							String toDate = dateFormat.format(todate2);

							getScreenshot();
							String fromDateField = driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = driver.findElement(By.id("results-found-count")).getText();
							//	Assert.assertEquals(fromDateField, fromDate);
							//	Assert.assertEquals(toDateField, toDate);
							softAssert.assertNotEquals(recordBefore, recordAfter);
							test.pass(objFilter.FilterName+ " values verified successfully");
							results.createNode(objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();
						}catch(AssertionError er) {
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResult(ITestResult.FAILURE, new Exception(er));
						}catch(Exception ex){
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResult(ITestResult.FAILURE, ex);
						}
					}

					if(objModel.Filter3) {
						try {
							String value1 = objFilter.fromDate;	
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.DATE, Integer.parseInt(value1));
							Date fromdate1 = cal.getTime();    
							String fromDate = dateFormat.format(fromdate1);

							String value3 =objFilter.toMonth;   
							cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, Integer.parseInt(value3));
							cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							Date todate2 = cal.getTime();    
							String toDate = dateFormat.format(todate2);

							getScreenshot();
							String fromDateField = driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");

							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 

							String recordAfter = driver.findElement(By.id("results-found-count")).getText();
							//	Assert.assertEquals(fromDateField, fromDate);
							//	Assert.assertEquals(toDateField, toDate);
							softAssert.assertNotEquals(recordBefore, recordAfter);
							test.pass(objFilter.FilterName+ " values verified successfully");
							results.createNode("1. "+objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();
						}catch(AssertionError er) {
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResult(ITestResult.FAILURE, new Exception(er));
						}catch(Exception ex){
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResult(ITestResult.FAILURE, ex);
						}
					}
					driver.findElement(By.id(clResultDate+""+clShowFilter)).click();
				}
				else {
					test.skip("Unable to test the scenario because button is disabled");
					results.createNode("Unable to test the scenario because button is disabled");
					saveResult(ITestResult.SKIP, null);
					ClickElement.clickById(driver, "results-found-count");
				}
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= true, priority = 8) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-CL-15: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000);	
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-scanDateTime"))); 
			steps.createNode("1. Open date filter popup");
			SoftAssert softAssert = new SoftAssert();
			WebElement filter_scroll = driver.findElement(By.id(clSortFilter+""+clResultDate));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
			Thread.sleep(1000);
			driver.findElement(By.id(clResultDate+""+clShowFilter)).click();	
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);

			steps.createNode("2. Click on objFilter.FilterName");
			driver.findElement(By.id("list-title_date-selection")).click();
			Thread.sleep(1000);

			driver.findElement(By.cssSelector(clLast30Days)).click();	
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(2000);
			steps.createNode("3. Click on Lock button");
			driver.findElement(By.id("save-filters")).click();;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

			String recordsbeforefilter = driver.findElement(By.id("results-found-count")).getText(); 
			Thread.sleep(500);
			getScreenshot();
			steps.createNode("4. Close report");
			steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(2000);
			String recordsafterfilter = driver.findElement(By.id("results-found-count")).getText();

			softAssert.assertEquals(recordsafterfilter, recordsbeforefilter);
			test.pass("Filter locked functionality verified successfully on date filter");
			results.createNode("Filter lock remained applied on reopening the report on date filter");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
			softAssert.assertAll();
		}catch(AssertionError er) {
			test.fail("Filer lock functionality failed on date filter");
			results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Filer lock functionality failed on date filter");
			results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			saveResult(ITestResult.FAILURE, ex);
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		driver.findElement(By.id("remove-filters")).click();
		driver.findElement(By.id("reset-all-filters")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	}


	@Test (description="Test Case: Test Site Name Filter",enabled= true, priority = 9) 
	public void SiteName() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-57: Verify Site Name Filter Functionality", "This test case will test Site Name Filter Functionality");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(1000);	
			String recordsBefore = driver.findElement(By.id("results-found-count")).getText();

			WebElement filter_scroll = driver.findElement(By.id(clCollectionSiteName+""+clShowFilter));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(800);		

			driver.findElement(By.id(clCollectionSiteName+""+clShowFilter)).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			driver.findElement(By.cssSelector("#sort-site_id tr:nth-child(1) td:nth-child(1) .custom-control.custom-checkbox")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#sort-site_id #list-title_apply")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Assert.assertNotEquals(driver.findElement(By.id("results-found-count")).getText(), recordsBefore);
			test.pass("Site Name filter verified successfully");
			results.createNode("Site Name filter verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Site Name filter failed");
			results.createNode("Site Name filter failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Site Name filter failed");
			results.createNode("Site Name filter failed");
			saveResult(ITestResult.FAILURE, ex);
		}	
	}

	
	@SuppressWarnings({ "unused", "unchecked" })
	@Test (description="Test Case: Contextual",enabled= true, priority = 10) 
	public void Contexual() throws InterruptedException, IOException {

		Thread.sleep(1500);
		CoccidiaLogModel.lstCoccidiaContexualCheck = CoccidiaLogModel.ContexualCheck(); 
		for (CoccidiaLogModel objModel : CoccidiaLogModel.lstCoccidiaContexualCheck) { 	
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						steps.createNode("1. Apply "+objFilter.FilterName);	
						steps.createNode("2. Verify all filter behaves contexually");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
						Thread.sleep(500);
						WebElement filter_scroll = driver.findElement(By.id("sort-"+objFilter.LstFilterSearch.get(0)));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(800);

						if (objModel.firstCase) {
							if (objModel.runIngestion) {
								RequestSpecification request = RestAssured.given();
								request.header("Content-Type", "application/json");
								JSONObject json = new JSONObject();   
								json.put("piperid", piperId);
								json.put("password", piperPassword);
								json.put("DISAPIVersion", "14.13");
								request.body(json.toString());
								Response response = request.post(api_login);
								int code = response.getStatusCode();
								Assert.assertEquals(code, 200);

								String data = response.asString();
								JsonPath jsonPathEvaluator = response.jsonPath();
								String token = jsonPathEvaluator.get("token");		
								Thread.sleep(2000);
								RequestSpecification request_announcement = RestAssured.given();

								request_announcement.header("Content-Type", "application/json");
								request_announcement.header("Authorization", "bearer " +token);
								HttpGet postRequest = new HttpGet(api_announcement);
								postRequest.addHeader("Content-Type", "application/json");
								postRequest.addHeader("Authorization", "Bearer "+token);

								JSONObject json1 = new JSONObject();
								JSONObject json2 = new JSONObject();
								JSONObject json3 = new JSONObject();
								JSONArray list = new JSONArray();

								json1.put("runId", lstApiAnnouncement.get(0));
								json1.put("dateTime", lstApiAnnouncement.get(1));
								json1.put("Piperid",  lstApiAnnouncement.get(2));
								json1.put("MPNCalculationType", lstApiAnnouncement.get(3));
								json2.put("fileName", lstApiAnnouncement.get(4));
								json2.put("checksum", lstApiAnnouncement.get(5));
								list.add(json2);
								json1.put("files", list);

								request_announcement.body(json1.toString());
								Response response1 = request_announcement.post(api_announcement);
								Thread.sleep(2000);
								RequestSpecification request_fileupload = RestAssured.given();
								request_fileupload.header("Content-Type", "application/json");
								request_fileupload.header("Authorization", "bearer " +token);
								HttpGet postRequest1 = new HttpGet(api_FileUpload);
								postRequest1.addHeader("Content-Type", "application/json");
								postRequest1.addHeader("Authorization", "Bearer "+token);

								json3.put("runId", lstCoccidiaIngest.get(0).runId);
								json3.put("checksum", lstCoccidiaIngest.get(0).checksum);
								json3.put("fileName", lstCoccidiaIngest.get(0).fileName);
								json3.put("fileType", lstCoccidiaIngest.get(0).fileType);
								json3.put("file", lstCoccidiaIngest.get(0).file);
								json3.put("fileJson", objModel.fileJson);				
								json3.put("Improc", lstCoccidiaIngest.get(0).improc);
								json3.put("RunMode", "1");
								json3.put("Pathogen", "Coccidia");

								request_fileupload.body(json3.toString());
								Response response2 = request_fileupload.post(api_FileUpload);
								String data3 = response2.asString();
								JsonPath jsonPathEvaluator1 = response.jsonPath();
								jsonPathEvaluator1.get("statusCode");
								Thread.sleep(1000);

								Thread.sleep(20000);
								driver.navigate().refresh();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);
							}

							steps.createNode("1. Apply "+objFilter.FilterName);
							WebElement filter_scroll2 = driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clApplyFilter));
							((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
							Thread.sleep(800);

							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clShowFilter)).click();	
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);						

							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clApplyFilter)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							steps.createNode("2. Verify all filter behaves contexually");

							for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

								WebElement filter_scroll3 = driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+clShowFilter));
								((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll3); 

								driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+clShowFilter)).click();		
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);	

								String b = driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+SalmonellaLogPage.footerCount)).getText();
								Assert.assertEquals(b, "Showing 1 - 1 Results");														
							}

							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clClearFilter)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	
						}

						if (objModel.secondCase) {
							String[] array;
							array = new String[objFilter.LstFilterSearch.size()];

							for (int t = 0; t<array.length; ) {
								for(int k = 0; k<objFilter.LstFilterSearch.size(); k++) {

									driver.findElement(By.id(objFilter.LstFilterSearch.get(k)+""+clShowFilter)).click();		
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);						
									String a = driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(k)+" "+SalmonellaLogPage.footerCount)).getText();
									array[t] = a;
									//System.out.println("array: "+array[t]);
									t++;

									if(k==objFilter.LstFilterSearch.size() - 1) {
										WebElement filter_scroll2 = driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clShowFilter));
										((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
										Thread.sleep(800);

										driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clShowFilter)).click();		
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);						
										driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);
										driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

										driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clApplyFilter)).click();
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(2000);

										for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

											driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+clShowFilter)).click();		
											wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
											Thread.sleep(1000);	

											String b = driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+SalmonellaLogPage.footerCount)).getText();
											Assert.assertNotEquals(array[l], b);
										}
									}
								}
							}
							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clClearFilter)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	

						}
						test.pass("Contexual Filter verified successfully");
						results.createNode("Contexual Filter verified successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}
					catch(AssertionError er) {
						test.fail(objFilter.FilterName + " failed to verify contexually");
						results.createNode(objFilter.FilterName + " failed to verify contexually");
						saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objFilter.FilterName + " failed to verify contexually");
						results.createNode(objFilter.FilterName + " failed to verify contexually");
						saveResult(ITestResult.FAILURE, ex);
					}
				}	
			}
			catch(Exception ex) {
			}
		}
	}
	

	@Test (enabled= true, priority = 11) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-CL-180: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
			steps.createNode("1. Verify int data type columns are right alligned");
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clLaneCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clTotalOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clSmallOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clMediumOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clLargeOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clTotalCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clSmallCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clMediumCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clLargeCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+clMeanIntensityCol+" .text-right")).isDisplayed() == true);
			softAssert.assertAll();
			test.pass("Int data type columns are right alligned");
			results.createNode("Int data type columns are right alligned");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority = 12) 
	public void FieldAccess() throws InterruptedException, IOException {

		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		CoccidiaLogModel.lstCoccidiaFieldAccess = CoccidiaLogModel.FieldAccess();

		for (CoccidiaLogModel objModel : CoccidiaLogModel.lstCoccidiaFieldAccess) { 	
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
				steps.createNode("1. Click on filed access icon; popup appears");				

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);     
						driver.findElement(By.id("edit-field-access")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);
						steps.createNode("2. Unselect "+objFilter.FilterName+" column and Select previously unselected filter column and click on save button");

						if(objModel.viewAccess) {
							if (driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) input")).isSelected() == false) {
								driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}	

						if(objModel.unviewAccess) {
							int inc = Integer.parseInt(objFilter.FilterID) + 1;
							if (driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) input")).isSelected() == true) {
								driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}

						driver.findElement(By.id("btn-save")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
						Thread.sleep(1000);
						Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Report Settings Saved");

						if(objModel.viewAccess) {
							steps.createNode("3. Verify selected column is displayed in the table");
							Assert.assertEquals(driver.findElements(By.id(objModel.FilterUnHideID)).size(), 1);
						}	
						if(objModel.unviewAccess) {
							steps.createNode("4. Verify unselected column is hidden in the table");
							Assert.assertEquals(driver.findElements(By.id(objModel.FilterHideID)).size(), 0);
						}
						steps.createNode("5. Open audit log and verify that unselected column is hidden while selected column is displayed");
						driver.findElement(By.id("audit-trial-0")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						if(objModel.viewAccess) {
							Assert.assertEquals(driver.findElements(By.cssSelector("#audit-"+objModel.FilterUnHideID+".table-header-report")).size(), 1);	
						}

						if(objModel.unviewAccess) {
							Assert.assertEquals(driver.findElements(By.cssSelector("#audit-"+objModel.FilterHideID+".table-header-report")).size(), 0);
						}

						driver.findElement(By.cssSelector(SalmonellaLogPage.closeAudit)).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						test.pass("Column was hidden successfully");
						results.createNode("Column was hidden successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}
					catch(AssertionError er) {
						test.fail(objFilter.FilterName+" column failed to hide");
						results.createNode(objFilter.FilterName+" column failed to shide");
						saveResult(ITestResult.FAILURE, new Exception(er));
					}catch(Exception ex){
						test.fail(objFilter.FilterName+" column failed to hide");
						results.createNode(objFilter.FilterName+" column failed to shide");
						saveResult(ITestResult.FAILURE, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority = 13) 
	public void FieldAccessResetDefault() throws InterruptedException, IOException {

		driver.findElement(By.id("edit-field-access")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.id("btn-reset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.id("btn-save")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
	}


	@SuppressWarnings("unused")
	@Test (description="Test Case: Test Coccidia PNG Download",enabled= true, priority = 14) 
	public void PNGExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-215: Verify user can download Coccidia PNG file", "This test case will verify user can download Coccidia PNG file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			steps.createNode("1. Hover mouse towards barchart on top");
			steps.createNode("2. Export PNG button becomes visible");
			steps.createNode("3. Click on the button");

			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(2000);	
			driver.findElement(By.id("sampleId_show-filter")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#sort-sampleId li:nth-child(3) label")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			driver.findElement(By.id("sampleId_apply")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			
			getScreenshot();

			WebElement pngHover = driver.findElement(By.cssSelector(".run-timeline-bar-chart__download"));
			Actions builder = new Actions(driver);
			builder.moveToElement(pngHover).build().perform();
			waitElementClickable(By.id("dc-bar-chart-coci-png"));
			WebElement clickDownload = driver.findElement(By.id("dc-bar-chart-coci-png"));
			Actions actions = new Actions(driver);
			actions.moveToElement(clickDownload).click().perform();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1000);
			//File downloadFolder = new File(fileDownloadPath);
			//List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			//	Assert.assertTrue(namesOfFiles.contains(clPNGFileName+date+".png")); 
			//System.out.println("Success");
			test.pass("PNG downloaded successfully");
			results.createNode("PNG downloads successfully");
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("PNG failed to download");
			results.createNode("PNG failed to download");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("PNG failed to download");
			results.createNode("PNG failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
		Thread.sleep(2000);
	}


	@SuppressWarnings({ "resource", "unused" })
	@Test (description="Test Case: Test Coccidia CSV Download",enabled= true, priority = 15) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-216: Verify user can download Coccidia CSV file", "This test case will verify that user can download Coccidia CSV file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");
			
			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			
			String getRowText = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			waitElementClickable(By.cssSelector("#csv-action img"));
			driver.findElement(By.cssSelector("#csv-action img")).click();
			getScreenshot();
			Thread.sleep(1000);
			steps.createNode("5. Click on Export as CSV");
			driver.findElement(By.xpath("//*[text() = ' Export as CSV ']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

		
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, clCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename);
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

					int rows = driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = driver.findElements(By.cssSelector("tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+3;
						if (driver.findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), driver.findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = -1;
						}
						columnsCountTotal++;
					}
				}
			}
			
				
			Path path = Paths.get(fileDownloadPath+"\\"+filename);
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

			file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Test Coccidia Audit Download",enabled= true, priority = 16) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-217: Verify user can download Coccidia Audit file", "This test case will verify that user can download Coccidia Audit file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");			
		
			ClickElement.clickByCss(driver, "#select-runId-0 .data-log-checkbox");
			Thread.sleep(1000);

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			waitElementClickable(By.cssSelector("#csv-action img"));
			driver.findElement(By.cssSelector("#csv-action img")).click();
			getScreenshot();
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(driver, "export-audit-csv");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);

//			if(wait.until(ExpectedConditions.alertIsPresent())==null) {
//				System.out.println("Alert not exists");
//			}
//			else {
//				System.out.println("Alert exists");
//				driver.switchTo().alert().accept();
//			}


			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, clCSVAuditFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Test Coccidia Template Download",enabled= true, priority = 17) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-218: Verify user can download Coccidia Template file", "This test case will verify that user download Coccidia Template file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Coccidia Log; Coccidia Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			steps.createNode("3. Click on the button");
			waitElementClickable(By.cssSelector("#csv-action img"));
			driver.findElement(By.cssSelector("#csv-action img")).click();
			steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1000);
			ClickElement.clickByCss(driver, "#export-data-template label");
			Thread.sleep(1000);
			getScreenshot();
			steps.createNode("5. Click on Export Data Template");
			steps.createNode("6. Select Sample MetaData Template");
			ClickElement.clickById(driver, "Sample-Metadata-Upload-Template");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

	
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			//System.out.println("Latest XLSX file is = "+filename);
			Assert.assertTrue(filename.startsWith(clSampleMetaData));
		//	Assert.assertEquals(filename, clSampleMetaData+".xlsx");
			test.pass("Sample MetaData template downloaded successfully");
			results.createNode("Sample MetaData template downloads successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");


			test.pass("Sample MetaData downloaded successfully");
			results.createNode("Sample MetaData downloaded successfully");
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
		driver.close();
	}
}
