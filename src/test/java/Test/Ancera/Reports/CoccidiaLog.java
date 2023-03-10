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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Config.BaseTest;
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
import static MiscFunctions.Methods.*;
import static Models.IngestionsModel.*;

public class CoccidiaLog extends BaseTest{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Coccidia_Log"+DateUtil.date+".html");
		spark.config().setReportName("Coccidia Log Test Report"); 
	}

	
	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}
	
	@Test (priority = 1) 
	public void NavigateCoccidia() throws InterruptedException, IOException {
		test = extent.createTest("CL-AN-01: Verify user can navigate to Salmonella Log Screen");
		CoccidiaLogPage.openCoccidiaLogPage();
	}


	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		CoccidiaLogPage.openCoccidiaLogPage();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Lock(coccidiaLogTable, "Coccidia Log", 2);
	}
	

	@Test (priority = 3) 
	public void WildcardCocci() throws InterruptedException, IOException {
		CoccidiaLogPage.openCoccidiaLogPage();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard(coccidiaLogTable, "Coccidia Log", 2);
	}
	
	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		getDriver().navigate().refresh();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Sorting(coccidiaLogTable, "Coccidia Log", 2);
	}
	
	@Test(priority= 5, enabled = true)
	public void RowsPerPage() throws InterruptedException, IOException {
		RowsPerPage();
	}
	
	
	@Test(priority= 6, enabled = true)
	public void PaginationCocci() throws InterruptedException, IOException {
		getDriver().navigate().refresh();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Pagination(coccidiaLogTable, "Coccidia Log", ReportFilePath);
	}

	

	@SuppressWarnings("unused")
	@Test (description="Test Case: Date Filter Test",enabled= true, priority = 7) 
	public void DateFilter() throws InterruptedException, IOException {

		FieldLevelAccess.fieldLevelReset();
		String recordBefore = getDriver().findElement(By.id("results-found-count")).getText();
		CoccidiaLogModel.lstCoccidiaDateSearch = CoccidiaLogModel.FillDate();
		SoftAssert softAssert = new SoftAssert();

		for (CoccidiaLogModel objModel : CoccidiaLogModel.lstCoccidiaDateSearch) { 
			test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			for (ReportFilters objFilter : objModel.lstFilters) {
				Actions actions = new Actions(getDriver());
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				steps.createNode("1. Click on date calendar icon; Calendar pops up");
				WebElement filter_scroll = getDriver().findElement(By.id(clResultDate+""+clShowFilter));
				((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
				getDriver().findElement(By.id("scanDateTime_show-filter")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(1500);

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				steps.createNode("2. Click on "+objFilter.FilterName);
				getDriver().findElement(By.id("list-title_date-selection")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				if (getDriver().findElement(By.cssSelector(objFilter.FilterListXPathSearch)).isEnabled()) {
					actions.moveToElement(getDriver().findElement(By.cssSelector(objFilter.FilterListXPathSearch))).click().perform();	
					waitElementInvisible(loading_cursor);
					Thread.sleep(750);
					getDriver().findElement(By.id(clResultDate+""+clShowFilter)).click();
					waitElementInvisible(loading_cursor);
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
							String fromDateField = getDriver().findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = getDriver().findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = getDriver().findElement(By.id("results-found-count")).getText();
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
							String fromDateField = getDriver().findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = getDriver().findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = getDriver().findElement(By.id("results-found-count")).getText();
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
							String fromDateField = getDriver().findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = getDriver().findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");

							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 

							String recordAfter = getDriver().findElement(By.id("results-found-count")).getText();
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
					getDriver().findElement(By.id(clResultDate+""+clShowFilter)).click();
				}
				else {
					test.skip("Unable to test the scenario because button is disabled");
					results.createNode("Unable to test the scenario because button is disabled");
					saveResult(ITestResult.SKIP, null);
					ClickElement.clickById(getDriver(), "results-found-count");
				}
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= true, priority = 8) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-CL-15: Verify lock filter functionality on date filter");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);	
			waitElementInvisible(loading_cursor);
			waitElementVisible(By.id("sort-scanDateTime"));
			steps.createNode("1. Open date filter popup");
			SoftAssert softAssert = new SoftAssert();
			WebElement filter_scroll = getDriver().findElement(By.id(clSortFilter+""+clResultDate));
			((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
			Thread.sleep(1000);
			getDriver().findElement(By.id(clResultDate+""+clShowFilter)).click();	
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			steps.createNode("2. Click on objFilter.FilterName");
			ClickElement.clickByCss(getDriver(), ".fa-chevron-down");
			//getDriver().findElement(By.id("list-title_date-selection")).click();
			Thread.sleep(1000);

			getDriver().findElement(By.cssSelector(clLast30Days)).click();	
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			steps.createNode("3. Click on Lock button");
			getDriver().findElement(By.id("save-filters")).click();;
			waitElementInvisible(loading_cursor);

			String recordsbeforefilter = getDriver().findElement(By.id("results-found-count")).getText(); 
			Thread.sleep(500);
			getScreenshot();
			steps.createNode("4. Close report");
			steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			String recordsafterfilter = getDriver().findElement(By.id("results-found-count")).getText();

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
		waitElementInvisible(loading_cursor);
		getDriver().findElement(By.id("remove-filters")).click();
		getDriver().findElement(By.id("reset-all-filters")).click();
		waitElementInvisible(loading_cursor);
	}


	@Test (description="Test Case: Test Site Name Filter",enabled= true, priority = 9) 
	public void SiteName() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-57: Verify Site Name Filter Functionality");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);	
			String recordsBefore = getDriver().findElement(By.id("results-found-count")).getText();

			WebElement filter_scroll = getDriver().findElement(By.id(clCollectionSiteName+""+clShowFilter));
			((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(800);		

			getDriver().findElement(By.id(clCollectionSiteName+""+clShowFilter)).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);	
			getDriver().findElement(By.cssSelector("#sort-site_id tr:nth-child(1) td:nth-child(1) .custom-control.custom-checkbox")).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(2000);
			getDriver().findElement(By.cssSelector("#sort-site_id #list-title_apply")).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			Assert.assertNotEquals(getDriver().findElement(By.id("results-found-count")).getText(), recordsBefore);
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
	@Test (description="Test Case: Contextual",enabled= false, priority = 10)
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
						waitElementInvisible(loading_cursor);	
						Thread.sleep(500);
						WebElement filter_scroll = getDriver().findElement(By.id("sort-"+objFilter.LstFilterSearch.get(0)));
						((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						waitElementInvisible(loading_cursor);
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
								getDriver().navigate().refresh();
								waitElementInvisible(loading_cursor);
								Thread.sleep(2000);
							}

							steps.createNode("1. Apply "+objFilter.FilterName);
							WebElement filter_scroll2 = getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clApplyFilter));
							((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
							Thread.sleep(800);

							getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clShowFilter)).click();	
							waitElementInvisible(loading_cursor);
							Thread.sleep(1000);						

							waitElementInvisible(loading_cursor);
							getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
							waitElementInvisible(loading_cursor);
							Thread.sleep(1000);
							getDriver().findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

							getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clApplyFilter)).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(2000);
							steps.createNode("2. Verify all filter behaves contexually");

							for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

								WebElement filter_scroll3 = getDriver().findElement(By.id(objFilter.LstFilterSearch.get(l)+""+clShowFilter));
								((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll3); 

								getDriver().findElement(By.id(objFilter.LstFilterSearch.get(l)+""+clShowFilter)).click();		
								waitElementInvisible(loading_cursor);
								Thread.sleep(1000);	

								String b = getDriver().findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+footerCount)).getText();
								Assert.assertEquals(b, "Showing 1 - 1 Results");														
							}

							getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clClearFilter)).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(750);	
						}

						if (objModel.secondCase) {
							String[] array;
							array = new String[objFilter.LstFilterSearch.size()];

							for (int t = 0; t<array.length; ) {
								for(int k = 0; k<objFilter.LstFilterSearch.size(); k++) {

									getDriver().findElement(By.id(objFilter.LstFilterSearch.get(k)+""+clShowFilter)).click();		
									waitElementInvisible(loading_cursor);
									Thread.sleep(1000);						
									String a = getDriver().findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(k)+" "+footerCount)).getText();
									array[t] = a;
									//System.out.println("array: "+array[t]);
									t++;

									if(k==objFilter.LstFilterSearch.size() - 1) {
										WebElement filter_scroll2 = getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clShowFilter));
										((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
										Thread.sleep(800);

										getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clShowFilter)).click();		
										waitElementInvisible(loading_cursor);
										Thread.sleep(1000);						
										getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
										waitElementInvisible(loading_cursor);
										Thread.sleep(1000);
										getDriver().findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

										getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clApplyFilter)).click();
										waitElementInvisible(loading_cursor);
										Thread.sleep(2000);

										for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

											getDriver().findElement(By.id(objFilter.LstFilterSearch.get(l)+""+clShowFilter)).click();		
											waitElementInvisible(loading_cursor);
											Thread.sleep(1000);	

											String b = getDriver().findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+footerCount)).getText();
											Assert.assertNotEquals(array[l], b);
										}
									}
								}
							}
							getDriver().findElement(By.id(objFilter.LstFilterXpath.get(0)+""+clClearFilter)).click();
							waitElementInvisible(loading_cursor);
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
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clLaneCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clTotalOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clSmallOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clMediumOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clLargeOPGCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clTotalCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clSmallCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clMediumCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clLargeCountCol+" .text-right")).isDisplayed() == true);
			softAssert.assertTrue(getDriver().findElement(By.cssSelector("#col-"+clMeanIntensityCol+" .text-right")).isDisplayed() == true);
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

		getDriver().navigate().refresh();
		waitElementInvisible(loading_cursor);

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
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);     
						getDriver().findElement(By.id("edit-field-access")).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(2000);
						steps.createNode("2. Unselect "+objFilter.FilterName+" column and Select previously unselected filter column and click on save button");

						if(objModel.viewAccess) {
							if (getDriver().findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) input")).isSelected() == false) {
								getDriver().findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}	

						if(objModel.unviewAccess) {
							int inc = Integer.parseInt(objFilter.FilterID) + 1;
							if (getDriver().findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) input")).isSelected() == true) {
								getDriver().findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}

						getDriver().findElement(By.id("btn-save")).click();
						waitElementInvisible(loading_cursor);
						waitElementVisible(alertMessage);
						Thread.sleep(1000);
						Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Report Settings Saved");

						if(objModel.viewAccess) {
							steps.createNode("3. Verify selected column is displayed in the table");
							Assert.assertEquals(getDriver().findElements(By.id(objModel.FilterUnHideID)).size(), 1);
						}	
						if(objModel.unviewAccess) {
							steps.createNode("4. Verify unselected column is hidden in the table");
							Assert.assertEquals(getDriver().findElements(By.id(objModel.FilterHideID)).size(), 0);
						}
						steps.createNode("5. Open audit log and verify that unselected column is hidden while selected column is displayed");
						getDriver().findElement(By.id("audit-trial-0")).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);

						if(objModel.viewAccess) {
							Assert.assertEquals(getDriver().findElements(By.cssSelector("#audit-"+objModel.FilterUnHideID+".table-header-report")).size(), 1);	
						}

						if(objModel.unviewAccess) {
							Assert.assertEquals(getDriver().findElements(By.cssSelector("#audit-"+objModel.FilterHideID+".table-header-report")).size(), 0);
						}

						getDriver().findElement(By.cssSelector(SalmonellaLogPage.closeAudit)).click();
						waitElementInvisible(loading_cursor);
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

		getDriver().findElement(By.id("edit-field-access")).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(By.id("btn-reset")).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(By.id("btn-save")).click();
		waitElementInvisible(loading_cursor);
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

			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(2000);	
			getDriver().findElement(By.id("sampleId_show-filter")).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#sort-sampleId li:nth-child(3) label")).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			getDriver().findElement(By.id("sampleId_apply")).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			
			getScreenshot();

			WebElement pngHover = getDriver().findElement(By.cssSelector(".run-timeline-bar-chart__download"));
			Actions builder = new Actions(getDriver());
			builder.moveToElement(pngHover).build().perform();
			waitElementClickable(By.id("dc-bar-chart-coci-png"));
			WebElement clickDownload = getDriver().findElement(By.id("dc-bar-chart-coci-png"));
			Actions actions = new Actions(getDriver());
			actions.moveToElement(clickDownload).click().perform();

			waitElementInvisible(loading_cursor);	

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
			test = extent.createTest("AN-CL-216: Verify user can download Coccidia CSV file");
			steps = test.createNode(Scenario.class, Steps);
			
			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			waitElementInvisible(loading_cursor);
			
			String getRowText = getDriver().findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			waitElementClickable(By.cssSelector("#csv-action img"));
			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			getScreenshot();
			Thread.sleep(1000);
			steps.createNode("5. Click on Export as CSV");
			getDriver().findElement(By.xpath("//*[text() = ' Export as CSV ']")).click();
			waitElementInvisible(loading_cursor);

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, clCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
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

					int rows = getDriver().findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = getDriver().findElements(By.cssSelector("tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+3;
						if (getDriver().findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), getDriver().findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
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
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Test Coccidia Audit Download",enabled= true, priority = 16) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-217: Verify user can download Coccidia Audit file");
			steps = test.createNode(Scenario.class, Steps);

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");			
		
			ClickElement.clickByCss(getDriver(), "#select-runId-0 .data-log-checkbox");
			Thread.sleep(1000);

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			waitElementClickable(By.cssSelector("#csv-action img"));
			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			getScreenshot();
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(getDriver(), "export-audit-csv");
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, clCSVAuditFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Test Coccidia Template Download",enabled= true, priority = 17) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-218: Verify user can download Coccidia Template file");
			steps = test.createNode(Scenario.class, Steps);

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			steps.createNode("3. Click on the button");
			waitElementClickable(By.cssSelector("#csv-action img"));
			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1000);
			ClickElement.clickByCss(getDriver(), "#export-data-template label");
			Thread.sleep(1000);
			getScreenshot();
			steps.createNode("5. Click on Export Data Template");
			steps.createNode("6. Select Sample MetaData Template");
			ClickElement.clickById(getDriver(), "Sample-Metadata-Upload-Template");
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

	
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			//System.out.println("Latest XLSX file is = "+filename);
			Assert.assertTrue(filename.startsWith(clSampleMetaData));
		//	Assert.assertEquals(filename, clSampleMetaData+".xlsx");
			test.pass("Sample MetaData template downloaded successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");


			test.pass("Sample MetaData downloaded successfully");
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Sample MetaData downoad failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sample MetaData downoad failed");	
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}
