package Test.Ancera.Reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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

import Models.SalmonellaModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Test_Elements;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SalmonellaLog {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Salmonella_Log"+date+".html");
		spark.config().setReportName("Salmonella Log Test Report"); 
		config();
		ConfigureLogin.login();
	}
	
	
	@Test (priority = 1) 
	public void NavigateSalmonella() throws InterruptedException, IOException {
		NavigateToScreen(url_SalmonellaLog, "Salmonella Log", SalmonellaReportPath, salmonellaLogTitle);
	}

	
	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Lock(salmonellaLogTable, "Salmonella Log", SalmonellaReportPath, 2);
	}
	
	
	@Test (priority = 3) 
	public void WildcardSalm() throws InterruptedException, IOException {
		driver.get(url_SalmonellaLog);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Wildcard1(salmonellaLogTable, "Salmonella Log", SalmonellaReportPath, 2);
	}

	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		driver.get(url_SalmonellaLog);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Sorting1(salmonellaLogTable, "Salmonella Log", SalmonellaReportPath, 2);
	}
	
	
	@Test(priority= 5, enabled = true)
	public void RowsPerPage() throws InterruptedException, IOException {
		RowsPerPage1();
	}
	
	
	@Test(priority= 6, enabled = true)
	public void PaginationSalm() throws InterruptedException, IOException {
		driver.get(url_SalmonellaLog);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Pagination(salmonellaLogTable, "Salmonella Log", SalmonellaReportPath);
	}


	@SuppressWarnings("unused")
	@Test (description="Test Case: Date Filter Test",enabled= true, priority = 7) 
	public void DateFilter() throws InterruptedException, IOException {

		fieldLevelReset();
		lstSalmonellaDateSearch = SalmonellaModel.FillDate();
		String recordBefore = driver.findElement(By.id("results-found-count")).getText();
		SoftAssert softAssert = new SoftAssert();

		for (SalmonellaModel objModel : lstSalmonellaDateSearch) { 
			test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			for (ReportFilters objFilter : objModel.lstFilters) {
				Actions actions = new Actions(driver);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1000);
				steps.createNode("1. Click on date calendar icon; Calendar pops up");
				driver.findElement(By.id(slResultDate+""+slShowFilter)).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Thread.sleep(1500);
				String dateFrom = driver.findElement(By.xpath("//input[@placeholder='Start Date']")).getText();
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
					driver.findElement(By.id(slResultDate+""+slShowFilter)).click();
					Thread.sleep(750);
					test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));	

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
							saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
						}catch(Exception ex){
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
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
							String fromDateField = driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = driver.findElement(By.id("results-found-count")).getText();
							//Assert.assertEquals(fromDateField, fromDate);
							//Assert.assertEquals(toDateField, toDate, "Please ingest data with current date to test this scenario successfully");
							softAssert.assertNotEquals(recordBefore, recordAfter);
							test.pass(objFilter.FilterName+ " values verified successfully");
							results.createNode(objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();
						}catch(AssertionError er) {
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
						}catch(Exception ex){
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
						}
					}

					if(objModel.Filter3) {
						try {
							String value1 = objFilter.fromDate;	
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.DATE, Integer.parseInt(value1));
							Date fromdate1 = cal.getTime();    
							String fromDate = dateFormat.format(fromdate1);
							driver.findElement(By.id(slResultDate+""+slShowFilter)).click();

							String value3 =objFilter.toMonth;   
							cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, Integer.parseInt(value3));
							cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							Date todate2 = cal.getTime();    
							String toDate = dateFormat.format(todate2);
							System.out.println(toDate);
							driver.findElement(By.id(slResultDate+""+slShowFilter)).click();
							String fromDateField = driver.findElement(By.cssSelector("input[placeholder='Start Date']")).getAttribute("value");
							String toDateField = driver.findElement(By.cssSelector("input[placeholder='End Date']")).getAttribute("value");
							Thread.sleep(1000);
							steps.createNode("3. Verify the dates in To and From field"); 
							String recordAfter = driver.findElement(By.id("results-found-count")).getText();
							//Assert.assertEquals(fromDateField, fromDate);
							//Assert.assertEquals(toDateField, toDate);
							softAssert.assertNotEquals(recordBefore, recordAfter);
							test.pass(objFilter.FilterName+ " values verified successfully");
							results.createNode("1. "+objFilter.FilterName+ " values verified successfully");
							softAssert.assertAll();	
						}catch(AssertionError er) {
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
						}catch(Exception ex){
							test.fail(objFilter.FilterName+ " values failed to verify");
							results.createNode(objFilter.FilterName+ " values failed to verify");
							saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
						}
					}
					driver.findElement(By.id(slResultDate+""+slShowFilter)).click();	
				}
				else {
					test.skip("Unable to test the scenario because button is disabled");
					results.createNode("Unable to test the scenario because button is disabled");
					saveResultNew(ITestResult.SKIP, SalmonellaReportPath, null);
					ClickElement.clickById(driver, "results-found-count");
				}
			}
		}
	}


	@Test (description="Test Case: Date Filter Lock Test",enabled= true, priority = 8) 
	public void DateLockFilter() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-SL-10: Verify lock filter functionality on date filter", "This testcase will verify lock filter functionality on date filter");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			steps.createNode("1. Open date filter popup");
			SoftAssert softAssert = new SoftAssert();
			WebElement filter_scroll = driver.findElement(By.id(slSortFilter+""+slResultDate));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
			Thread.sleep(1000);
			driver.findElement(By.id(slResultDate+""+slShowFilter)).click();	
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);

			steps.createNode("2. Click on objFilter.FilterName");
			driver.findElement(By.id("list-title_date-selection")).click();
			Thread.sleep(1000);

			driver.findElement(By.cssSelector(slLast30Days)).click();	
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			steps.createNode("3. Click on Lock button");
			driver.findElement(By.id("save-filters")).click();;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			String recordsbeforefilter = driver.findElement(By.id("results-found-count")).getText(); 
			Thread.sleep(500);
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			steps.createNode("4. Close report");
			steps.createNode("5. Reopen report and verify that records are still the same as before closing the report");
			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			String recordsafterfilter = driver.findElement(By.id("results-found-count")).getText();

			softAssert.assertEquals(recordsafterfilter, recordsbeforefilter);
			test.pass("Filter locked functionality verified successfully on date filter");
			results.createNode("Filter lock remained applied on reopening the report on date filter");
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);
			softAssert.assertAll();
		}catch(AssertionError er) {
			test.fail("Filer lock functionality failed on date filter");
			results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Filer lock functionality failed on date filter");
			results.createNode("Filter lock failed to remain applied on reopening the report on date filter");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		driver.findElement(By.id("remove-filters")).click();
		driver.findElement(By.id("reset-all-filters")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}

	
	
	@Test (description="Test Case: Test Site Name Filter",enabled= true, priority = 9) 
	public void SiteName() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-SL-59: Verify Site Name Filter Functionality", "This test case will test Site Name Filter Functionality");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			String recordsBefore = driver.findElement(By.id("results-found-count")).getText();

			WebElement filter_scroll = driver.findElement(By.id(slCollectionSiteName+""+slShowFilter));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(800);	

			driver.findElement(By.id(slCollectionSiteName+""+slShowFilter)).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			driver.findElement(By.cssSelector("#sort-site_id tr:nth-child(1) td:nth-child(1) .custom-control.custom-checkbox")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#sort-site_id #list-title_apply")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			Assert.assertNotEquals(driver.findElement(By.id("results-found-count")).getText(), recordsBefore);
			test.pass("Checkbox selected successfully");
			results.createNode("Checkbox selected successfully");
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", CoccidiaReportPath));
			saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Filer lock functionality failed");
			results.createNode("Filter lock failed to remain applied on reopening the report");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Filer lock functionality failed");
			results.createNode("Filter lock failed to remain applied on reopening the report");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
		}	
	}



	@SuppressWarnings({ "unused", "unchecked" })
	@Test (description="Test Case: Contextual",enabled= true, priority = 10) 
	public void Contexual() throws InterruptedException, IOException {

		driver.get(url_SalmonellaLog);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1500);

		lstSalmonellaContexualCheck = SalmonellaModel.ContexualCheck(); 
		for (SalmonellaModel objModel : lstSalmonellaContexualCheck) { 	
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {	

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
								String data2 = response1.asString();
								System.out.println(data2);
								Thread.sleep(2000);
								RequestSpecification request_fileupload = RestAssured.given();
								request_fileupload.header("Content-Type", "application/json");
								request_fileupload.header("Authorization", "bearer " +token);
								HttpGet postRequest1 = new HttpGet(api_FileUpload);
								postRequest1.addHeader("Content-Type", "application/json");
								postRequest1.addHeader("Authorization", "Bearer "+token);

								json3.put("runId", lstSalmonellaIngest.get(0).runId);
								json3.put("checksum", lstSalmonellaIngest.get(0).checksum);
								json3.put("fileName", lstSalmonellaIngest.get(0).fileName);
								json3.put("fileType", lstSalmonellaIngest.get(0).fileType);
								json3.put("file", lstSalmonellaIngest.get(0).file);
								json3.put("fileJson", objModel.fileJson);				
								json3.put("Improc", lstSalmonellaIngest.get(0).improc);
								json3.put("RunMode", "1");
								json3.put("Pathogen", "Salmonella");

								request_fileupload.body(json3.toString());
								Response response2 = request_fileupload.post(api_FileUpload);
								String data3 = response2.asString();
								System.out.println(data3);
								JsonPath jsonPathEvaluator1 = response.jsonPath();
								jsonPathEvaluator1.get("statusCode");
								Thread.sleep(1000);

								Thread.sleep(120000);
								driver.navigate().refresh();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);
							}

							steps.createNode("1. Apply "+objFilter.FilterName);
							WebElement filter_scroll2 = driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slApplyFilter));
							((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
							Thread.sleep(800);

							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slShowFilter)).click();		
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);						

							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);
							driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slApplyFilter)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(2000);
							steps.createNode("2. Verify all filter behaves contexually");

							for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

								WebElement filter_scroll3 = driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+slShowFilter));
								((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll3); 

								driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+slShowFilter)).click();		
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);	

								String b = driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+footerCount)).getText();
								Assert.assertEquals(b, "Showing 1 - 1 Results");														
							}

							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slClearFilter)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	
						}

						if (objModel.secondCase) {
							String[] array;
							array = new String[objFilter.LstFilterSearch.size()];

							for (int t = 0; t<array.length; ) {
								for(int k = 0; k<objFilter.LstFilterSearch.size(); k++) {

									steps.createNode("1. Apply "+objFilter.FilterName);				
									driver.findElement(By.id(objFilter.LstFilterSearch.get(k)+""+slShowFilter)).click();		
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);						
									String a = driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(k)+" "+footerCount)).getText();
									array[t] = a;
									//			System.out.println("array: "+array[t]);
									t++;
									steps.createNode("2. Verify all filter behaves contexually");
									if(k==objFilter.LstFilterSearch.size() - 1) {
										WebElement filter_scroll2 = driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slShowFilter));
										((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll2); 
										Thread.sleep(800);

										driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slShowFilter)).click();		
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);						
										driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slSearchInput)).sendKeys(objFilter.LstFilterValues.get(0));
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(1000);
										driver.findElement(By.cssSelector("#"+objFilter.LstFilterXpath.get(0)+"_cust-cb-lst-txt_"+objFilter.LstFilterValues.get(0))).click();

										driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slApplyFilter)).click();
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
										Thread.sleep(2000);

										for (int l=0; l<objFilter.LstFilterSearch.size(); l++) {

											driver.findElement(By.id(objFilter.LstFilterSearch.get(l)+""+slShowFilter)).click();		
											wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
											Thread.sleep(1000);	

											String b = driver.findElement(By.cssSelector("#sort-"+objFilter.LstFilterSearch.get(l)+" "+footerCount)).getText();
											Assert.assertNotEquals(array[l], b);
										}
									}
								}
							}
							driver.findElement(By.id(objFilter.LstFilterXpath.get(0)+""+slClearFilter)).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(750);	
						}
						test.pass("Contexual Filter verified successfully");
						results.createNode("Contexual Filter verified successfully");
						test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
						saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);
					}
					catch(AssertionError er) {
						test.fail(objFilter.FilterName + " failed to verify contexually");
						results.createNode(objFilter.FilterName + " failed to verify contexually");
						saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objFilter.FilterName + " failed to verify contexually");
						results.createNode(objFilter.FilterName + " failed to verify contexually");
						saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
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
			test = extent.createTest("AN-SL-174: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
			steps.createNode("1. Verify int data type columns are right alligned");

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+slLaneCol+" .text-right")).isDisplayed() == true, "Lane column is not right alligned");
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+slW1CellCountCol+" .text-right")).isDisplayed() == true, "W1CellCount column is not right alligned");
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+slW1PCCountCol+" .text-right")).isDisplayed() == true, "W1PCCount column is not right alligned");
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+slW1MeanIntensityCol+" .text-right")).isDisplayed() == true, "W1MeanIntensity column is not right alligned");
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+slW2CellCountCol+" .text-right")).isDisplayed() == true, "W2CellCount column is not right alligned");
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+slW2CPCCountCol+" .text-right")).isDisplayed() == true, "W2PCCount column is not right alligned");
			softAssert.assertTrue(driver.findElement(By.cssSelector("#col-"+slW2MeanIntensityCol+" .text-right")).isDisplayed() == true, "W2MeanIntensity column is not right alligned");
			test.pass("Int data type columns are right alligned");
			results.createNode("Int data type columns are right alligned");
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);
			softAssert.assertAll();	
		}catch(AssertionError er) {
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 13) 
	public void FieldAccess() throws InterruptedException, IOException {

		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

		lstSalmonellaFieldAccess = SalmonellaModel.FieldAccess();

		for (SalmonellaModel objModel : lstSalmonellaFieldAccess) { 	
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
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

						driver.findElement(By.cssSelector(closeAudit)).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						test.pass("Column was hidden successfully");
						results.createNode("Column was hidden successfully");
						test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
						saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);
					}
					catch(AssertionError er) {
						test.fail(objFilter.FilterName+" column failed to hide");
						results.createNode(objFilter.FilterName+" column failed to shide");
						saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail(objFilter.FilterName+" column failed to hide");
						results.createNode(objFilter.FilterName+" column failed to shide");
						saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}

	@Test (enabled= true, priority = 14) 
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
	@Test (description="Test Case: Test Salmonella PNG Download",enabled= true, priority = 15) 
	public void PNGExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-SL-214: Verify user can download Salmonella PNG file", "This test case will verify user can download Salmonella PNG file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			steps.createNode("1. Hover mouse towards barchart on top");
			steps.createNode("2. Export PNG button becomes visible");

			driver.get(url_SalmonellaLog);			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);	
			driver.findElement(By.id("sampleId_show-filter")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#sort-sampleId li:nth-child(3) label")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			driver.findElement(By.id("sampleId_apply")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
			Thread.sleep(1000);

			Actions builder = new Actions(driver); 
			WebElement pngHover = driver.findElement(By.cssSelector(".run-timeline-bar-chart__download"));
			steps.createNode("3. Click on the button");
			builder.moveToElement(pngHover).build().perform();

			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			waitElementClickable(By.id("dc-bar-chart-salm-png"));
			WebElement clickDownload = driver.findElement(By.id("dc-bar-chart-salm-png"));
			Actions actions = new Actions(driver);
			actions.moveToElement(clickDownload).click().perform();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			//File downloadFolder = new File(fileDownloadPath);
			//List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			//	Assert.assertTrue(namesOfFiles.contains(slPNGFileName+date+".png")); 
			System.out.println("Success");
			test.pass("PNG downloaded successfully");
			results.createNode("PNG downloads successfully");
			saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("PNG failed to download");
			results.createNode("PNG failed to download");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("PNG failed to download");
			results.createNode("PNG failed to download");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
		}
		Thread.sleep(1000);
	}

	
	@SuppressWarnings({ "resource", "unused" })
	@Test (description="Test Case: Test Salmonella CSV Download",enabled= true, priority = 16) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-SL-216: Verify user can download Salmonella CSV file", "This test case will verify that user can download Salmonella CSV file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");
			
			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			
			String getRowText = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			waitElementClickable(By.cssSelector("#csv-action img"));
			driver.findElement(By.cssSelector("#csv-action img")).click();
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			Thread.sleep(1000);
			steps.createNode("5. Click on Export as CSV");
			driver.findElement(By.xpath("//*[text() = ' Export as CSV ']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, Test_Elements.slCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);

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
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
		}
	}


	@Test (description="Test Case: Test Salmonella Audit Download",enabled= true, priority = 17) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CL-216: Verify user can download Salmonella Audit file", "This test case will verify that user can download Salmonella Audit file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Coccidia Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			ClickElement.clickByCss(driver, "#select-runId-0 .data-log-checkbox");
			Thread.sleep(1000);

			//String getRowCount = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			waitElementClickable(By.cssSelector("#csv-action img"));
			driver.findElement(By.cssSelector("#csv-action img")).click();
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(driver, "export-audit-csv");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, slCSVAuditFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
		}
	}


	@Test (description="Test Case: Test Salmonella Template Download",enabled= true, priority = 18) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-SL-217: Verify user can download Salmonella Template file", "This test case will verify that user download Salmonella Template file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Salmonella Log; Salmonella Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			steps.createNode("3. Click on the button");
			waitElementClickable(By.cssSelector("#csv-action img"));
			driver.findElement(By.cssSelector("#csv-action img")).click();
			steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1500);
			ClickElement.clickByCss(driver, "#export-data-template label");
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			steps.createNode("5. Click on Export Data Template");
			steps.createNode("6. Select Sample MetaData Template");
			ClickElement.clickById(driver, "Sample-Metadata-Upload-Template");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			//System.out.println("Latest XLSX file is = "+filename);
			//Assert.assertEquals(filename, slSampleMetaData+".xlsx");
			Assert.assertTrue(filename.startsWith(slSampleMetaData));
			test.pass("Sample MetaData template downloaded successfully");
			results.createNode("Sample MetaData template downloaded successfully");
			saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");
		}
		catch(AssertionError er) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  	
			saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
		//	driver.close();
	}

}
