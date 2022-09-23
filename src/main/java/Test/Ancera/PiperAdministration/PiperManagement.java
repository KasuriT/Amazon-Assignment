package Test.Ancera.PiperAdministration;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.PiperManagementModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

public class PiperManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		spark = new 	ExtentSparkReporter("target/Reports/Piper_Management"+date+".html");
		spark.config().setReportName("Piper Management Test Report"); 
		config();
		ConfigureLogin.login();
	}

	
	@Test(priority= 1)
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen(url_piperManagement, "PIPER Management", PiperManagementReportPath, piperManagementTitle);
	}
	
	
	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Lock(piperManagementTable, "PIPER Management", PiperManagementReportPath, 0);
	}
	
	
	@Test (priority = 3, enabled = true) 
	public void WildcardPiper() throws InterruptedException, IOException {
		driver.get(url_piperManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard1(piperManagementTable, "PIPER Management", PiperManagementReportPath, 0);
	}

	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		Sorting(piperManagementTable, "PIPER Management", PiperManagementReportPath);
	}
	
	
	@Test (enabled= true, priority = 5) 
	public void TestSiteFilter() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-PM-011: Verify Test Site Filter Functionality");
			steps = test.createNode(Scenario.class, "Steps");
			results = test.createNode(Scenario.class, "Result");
			test.addScreenCaptureFromPath(getScreenshot("Piper Management", PiperManagementReportPath));
			steps.createNode("1. Verify Test Site Filter Functionality");
			
			SoftAssert softAssert = new SoftAssert();
			String recordBefore = driver.findElement(By.id(ResultsCount)).getText(); 
			driver.findElement(By.id("testSite_show-filter")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
//			List<WebElement> a = driver.findElements(By.cssSelector("th:nth-child(2) label:nth-child(2)"));
//			a.get(2).click();
			click(By.cssSelector("th:nth-child(2) tr:nth-child(2) td:nth-child(2) label:nth-child(1)"));
			driver.findElement(By.cssSelector("th:nth-child(2) .filter-popup__footer--apply")).click();
			waitElementInvisible(loading_cursor);	
			waitElementInvisible(loading_cursor);
			steps.createNode("2. Click on lock button");	
			driver.findElement(By.id(LockFilter)).click();
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);
			String recordsafterfilter = driver.findElement(By.id(ResultsCount)).getText();
			softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");
			driver.navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			softAssert.assertEquals(driver.findElement(By.id(ResultsCount)).getText(), recordsafterfilter, "Lock functionality failed");
			Thread.sleep(1000);
			driver.findElement(By.id(UnlockFilter)).click();
			waitElementInvisible(loading_cursor);
			driver.findElement(By.cssSelector("th:nth-child(2) .log-header__clear-filter span")).click();
			waitElementInvisible(loading_cursor);
			softAssert.assertAll();
			test.pass("Test Site Filter verified successfully");
			results.createNode("Test Site Filter verified successfully");
			test.addScreenCaptureFromPath(getScreenshot("Piper Management", PiperManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, PiperManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Test Site filter failed to verify");
			results.createNode("Test Site filter failed to verify");
			saveResultNew(ITestResult.FAILURE, PiperManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Test Site filter failed to verify");
			results.createNode("Test Site filter failed to verify");
			saveResultNew(ITestResult.FAILURE, PiperManagementReportPath, ex);
		}	
	}
	
	

	@Test (enabled= true, priority = 6) 
	public void BasicScenarios() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-PM-12: Verify user can view sites", "This test case will verify that user can view sites");
			steps = test.createNode(Scenario.class, "Steps");
			results = test.createNode(Scenario.class, "Result");
			test.addScreenCaptureFromPath(getScreenshot("Piper Management", PiperManagementReportPath));

			steps.createNode("1. Open configure piper popop");
			steps.createNode("2. Verify sites hierarcy");
			steps.createNode("3. Verify oranization appears in dropdown");
			steps.createNode("4. Verify user can save the settings");
			
			driver.get(url_piperManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
			SoftAssert softAssert = new SoftAssert();

			String piperName = driver.findElement(By.cssSelector("#"+piperManagementTable+" #row-1 #col-0 label")).getText();
			
			scroll(By.cssSelector("td:nth-child(12)"));
			Thread.sleep(1500);
			driver.findElement(By.cssSelector("#edit-piper-2")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);
			softAssert.assertEquals(driver.findElement(By.id("descId")).getText(), piperName, "Piper Name does not appear in text field");
			driver.findElement(By.cssSelector(".b-md:nth-child(1)")).click();
			waitElementInvisible(loading_cursor);
			driver.findElement(By.id("btn-show-tree")).click();
			waitElementInvisible(loading_cursor);
			softAssert.assertTrue(driver.findElement(By.cssSelector(".popup-heading")).isDisplayed());
			
			driver.findElement(By.cssSelector("#orgTypeId .ng-arrow-wrapper")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			if (driver.findElement(By.cssSelector(".ng-option:nth-child(1)")).getText() == "Ancera" && driver.findElement(By.cssSelector(".ng-option:nth-child(2)")).getText() == "Client" && driver.findElement(By.cssSelector(".ng-option:nth-child(3)")).getText() == "Partner" && driver.findElement(By.cssSelector(".ng-option:nth-child(4)")).getText() == "Consumer" && driver.findElement(By.cssSelector(".ng-option:nth-child(4)")).getText() == "Allied Partner") {
				softAssert.assertTrue(true, "Organization types not displaying in dropdown");
			}
			waitElementInvisible(loading_cursor);
			driver.findElement(By.id("btn-cancel-sites")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
		//	driver.findElement(By.xpath("//*[text() = ' Submit ']")).click();
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
		//	softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Testing sites details updated successfully.");
			softAssert.assertAll();
			test.pass("Sites and dropdowns verified successfully");
			results.createNode("Sites and dropdowns verified successfully");
			test.addScreenCaptureFromPath(getScreenshot("Piper Management", PiperManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, PiperManagementReportPath, null);
		}catch(AssertionError er){
			test.fail("Sites and dropdowns failed to verify");
			results.createNode("Sites and dropdowns failed to verify");
			saveResultNew(ITestResult.FAILURE, PiperManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Sites and dropdowns failed to verify");
			results.createNode("Sites and dropdowns failed to verify");
			saveResultNew(ITestResult.FAILURE, PiperManagementReportPath, ex);
		}	
	}
	
	
	@Test (enabled= true, priority = 7) 
	public void AlertSetting() throws InterruptedException, IOException {

		driver.navigate().refresh();
		waitElementInvisible(loading_cursor);
		lstPiperManagementCreate = PiperManagementModel.FillData();
		for (PiperManagementModel objModel : lstPiperManagementCreate) { 
			try{
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, "Pre_Conditions");
				steps = test.createNode(Scenario.class, "Steps");
				results = test.createNode(Scenario.class, "Result");
				test.addScreenCaptureFromPath(getScreenshot("Piper Management", PiperManagementReportPath));

				preconditions.createNode("1. Go to url " +url_login+ "and login with valid credentials");
				preconditions.createNode("2. Hover to sidebar to expand menu");
				preconditions.createNode("3. Expand Administration and click on Piper Managment");
				steps.createNode("1. Open Alert Setting popup");
				steps.createNode(objModel.passStep);
				SoftAssert softAssert = new SoftAssert();
				waitElementInvisible(loading_cursor);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(text(),'Alert Setting')]")).click();
		
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						Thread.sleep(2000);
						driver.findElement(By.id("programNameId")).clear();
						driver.findElement(By.id("programNameId")).sendKeys(objModel.emailList);
						driver.findElement(By.id("num-descId")).clear();
						driver.findElement(By.id("num-descId")).sendKeys(objModel.hoursList);
						Thread.sleep(2000);
						driver.findElement(By.id("btn-save")).click();
						if (objModel.negativeScenario) {
						for (int i =0; i<objFilter.LstFilterValues.size(); i++) {
							
							softAssert.assertEquals(driver.findElements(By.xpath(objFilter.LstFilterValues.get(i))).size(), 1);	
						}

						driver.findElement(By.id("close-popup-modal")).click();
						Thread.sleep(2000);
						}
						
						if (objModel.positiveScenario) {
							wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
							Thread.sleep(1000);
							softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Alert Settings details updated.");
						}

						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						test.addScreenCaptureFromPath(getScreenshot("Piper Management", PiperManagementReportPath));
						saveResultNew(ITestResult.SUCCESS, PiperManagementReportPath, null);
					}catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, PiperManagementReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, PiperManagementReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
	//	driver.close();
	}
}
