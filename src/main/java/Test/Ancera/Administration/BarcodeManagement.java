package Test.Ancera.Administration;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class BarcodeManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Barcode_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Barcode Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	
	@Test (description="Test Case: Navigate to Barcode Management Screen",enabled= true, priority = 1) 
	public void NavigateBarcodeManagement() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Barcode-01: Verify user can navigate to Barcode Management screen", "This test case will verify that user can navigate to Barcode Management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Barcode Management", Constants.BarcodeManagementReportPath));
			Helper.driver.get(Constants.url_barcodeManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Barcode Management")));
			Thread.sleep(1500);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Administration and select Barcode Management");

			Assert.assertEquals(Helper.driver.findElement(By.id("Barcode Management")).getText(), "Barcode Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Data Upload Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Barcode Management", Constants.BarcodeManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.BarcodeManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Barcode Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Barcode Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, ex);
		}
	}

	
	@Test (description="Test Case: Error Opening Print Screen",enabled= true, priority = 2 ) 
	public void ErrorOpeningPrintScreen() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Barcode-02: Verify user cannot open print screen without selecting a site", "This test case will verify that user cannot open print screen without selecting a site");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Barcode Management");
			Test_Variables.steps.createNode("1. Click on Print button without selecting a site");
			
			Actions builder = new Actions(Helper.driver); 
			WebElement pngHover = Helper.driver.findElement(By.cssSelector(".fa-print"));
			Test_Variables.steps.createNode("3. Click on the button");
			builder.moveToElement(pngHover).build().perform();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".fa-print")).click();	
			Test_Variables.steps.createNode("2. Verify error message appears");
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Please select a collection site");
					
			Test_Variables.test.pass("Error message appeared successfully on clicking print icon");
			Test_Variables.results.createNode("Error message appeared successfully on clicking print icon");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Barcode Management", Constants.BarcodeManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.BarcodeManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Error message failed to appear on clicking print icon");
			Test_Variables.results.createNode("Error message failed to appear on clicking print icon");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Error message failed to appear on clicking print icon");
			Test_Variables.results.createNode("Error message failed to appear on clicking print icon");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, ex);
		}
	}
	
	
	@Test (description="Test Case: Open print screen",enabled= false, priority = 3) 
	public void OpenPrintScreen() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Barcode-03: Verify user can open print screen on clicking print icon after selecting a site", "This test case will verify that user can open print screen on clicking print icon after selecting a site");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Barcode Management");
			Test_Variables.steps.createNode("1. Select a site and then click on Print button");
			
			Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(1) .custom-control-label")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".fa-print")).click();	
			Thread.sleep(3000);

			String parent=Helper.driver.getWindowHandle();
			Set<String>s=Helper.driver.getWindowHandles();
			Iterator<String> I1= s.iterator();

			Test_Variables.steps.createNode("2. Verify print window opens with selected site barcodes");
			
			while(I1.hasNext())
			{
				String child_window=I1.next();

				if(!parent.equals(child_window))
				{
					Helper.driver.switchTo().window(child_window);

					System.out.println(Helper.driver.switchTo().window(child_window).getTitle());
					Thread.sleep(2000);

					Robot r = new Robot();
					r.keyPress(KeyEvent.VK_ENTER);
					
				}
			}

			Helper.driver.switchTo().window(parent);

			Test_Variables.test.pass("Print screen opened successfully");
			Test_Variables.results.createNode("Print screen opened successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Barcode Management", Constants.BarcodeManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.BarcodeManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Print screen failed to open");
			Test_Variables.results.createNode("Print screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Print screen failed to open");
			Test_Variables.results.createNode("Print screen failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, ex);
		}
	}
	

	@Test (description="Test Case: Assign Sites Appearance",enabled= true, priority = 4) 
	public void AssignedSiteAppearance() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Barcode-05: Verify only those sites appear which are assigned to user", "This test case will verify that only those sites appear which are assigned to user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Barcode Management");
			Test_Variables.steps.createNode("1. Verify only those sites appear which are assigned to user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			for (int j=1;j<Helper.driver.findElements(By.cssSelector("tr")).size(); j++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					Helper.driver.findElement(By.id("edit-user-"+j)).click();
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
						Helper.driver.get(Constants.url_barcodeManagement);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);
						int sitesCountBarcode = Helper.driver.findElements(By.cssSelector(".custom-control-label")).size();

						Assert.assertEquals(sitesCountBarcode, collectionSitesSize);
						Test_Variables.test.pass("Only those sites appeared  which are assigned to user successfully");
						Test_Variables.results.createNode("Only those sites appeared which are assigned to user successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Barcode Management", Constants.BarcodeManagementReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.BarcodeManagementReportPath, null);	
					}
				}
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Those sites did not appeared which are assigned to user");
			Test_Variables.results.createNode("Those sites did not appeared which are assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Those sites did not appeared which are assigned to user");
			Test_Variables.results.createNode("Those sites did not appeared which are assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, ex);
		}
	}
	
	
	@Test (description="Test Case: Search Functionality",enabled= true, priority = 5) 
	public void SearchFunctionality() throws InterruptedException, IOException {
		try {
			
			Helper.driver.get(Constants.url_barcodeManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(2000);
			
			Test_Variables.test = Test_Variables.extent.createTest("AN-Barcode-07/08/09/10/11: Verify search bar functionality", "This test case will verify that search bar is fully functional");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Barcode Management");
			Test_Variables.steps.createNode("1. Enter valid site in search bar");

			if(Helper.driver.findElements(By.cssSelector("tr:nth-child(2) td:nth-child(3) label")).size() != 0) {
				System.out.println("a");
				String siteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(3) label")).getText();

				Test_Variables.steps.createNode("2. Click on backspace and verify backspace is working");
				Helper.driver.findElement(By.id("search-bar")).sendKeys(siteName);
				Helper.driver.findElement(By.id("search-bar")).sendKeys(Keys.BACK_SPACE);
				Assert.assertNotEquals(Helper.driver.findElement(By.id("search-bar")).getText().length(), siteName.length());	

				Test_Variables.steps.createNode("3. Click on ESC and verify search string is cleared");
				Helper.driver.findElement(By.id("search-bar")).sendKeys(siteName);
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("search-bar")).sendKeys(Keys.ESCAPE);
				Assert.assertEquals(Helper.driver.findElement(By.id("search-bar")).getText(), "");

				Test_Variables.steps.createNode("4. Click on Enter and verify site is search");
				Helper.driver.findElement(By.id("search-bar")).sendKeys(siteName);
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("search-bar")).sendKeys(Keys.ARROW_DOWN);
				Helper.driver.findElement(By.id("search-bar")).sendKeys(Keys.ENTER);
				Assert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) label")).getText(), siteName);
				Test_Variables.test.pass("Search bar is functional");
				Test_Variables.results.createNode("Search bar is functional");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Barcode Management", Constants.BarcodeManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.BarcodeManagementReportPath, null);	
			}

			else {
				Test_Variables.test.skip("Search functionality cannot be tested because there is only 1 site");
				Test_Variables.results.createNode("Search functionality cannot be tested because there is only 1 site");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Barcode Management", Constants.BarcodeManagementReportPath));
				Helper.saveResultNew(ITestResult.SKIP, Constants.BarcodeManagementReportPath, null);	
			}
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Search bar is not functional");
			Test_Variables.results.createNode("Search bar is not functional");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Search bar is not functional");
			Test_Variables.results.createNode("Search bar is not functional");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.BarcodeManagementReportPath, ex);
		}
	}
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();
	}
	
}
