package Test.Ancera.Administration;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.ProgramManagementModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProgramManagement {


	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Program_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Program Management Test Report"); 
		Helper.config();
		ConfigureLogin.login();
	}


	@Test(priority= 1)
	public void Navigate() throws InterruptedException, IOException {
		Test_Functions.NavigateToScreen(Constants.url_programManagement, "Program Management", Constants.ProgramManagementReportPath, Test_Elements.programManagementTitle);
	}


	@Test (enabled= true, priority= 2) 
	public void CreateProgramVaccine() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-02: Verify that user is able to create new Vaccine program", "This testcase will verify that user is able to create new program");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			Test_Variables.steps.createNode("1. Click on Create New Program button");
			Test_Variables.steps.createNode("2. Add valid data in all fields");
			Test_Variables.steps.createNode("3. Click on save button");

			SoftAssert softAssert = new SoftAssert();

			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.programCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);

			Helper.driver.findElement(Test_Elements.popupCloseButton).click();
			softAssert.assertEquals(Helper.driver.findElements(Test_Elements.programName).size(), 0);

			Helper.driver.findElement(Test_Elements.programCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);

			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
			Helper.driver.findElement(Test_Elements.programName).sendKeys(ProgramManagementModel.VaccineProgramName);
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");


			Helper.driver.findElement(Test_Elements.programSupplier).sendKeys("China");
			Thread.sleep(1000);
			if (Helper.driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
				Helper.driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
			}

			else {
				//	Helper.driver.findElement(Test_Elements.programSupplier).sendKeys(Keys.ENTER);
				Helper.driver.findElement(By.cssSelector(".list-item")).click();		
			}

			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.programDescription).sendKeys("Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur");
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#descriptionId.has-error")).size(), 1);
			Helper.driver.findElement(Test_Elements.programDescription).clear();
			Helper.driver.findElement(Test_Elements.programDescription).sendKeys("Vaccine Testing Program");

			Helper.driver.findElement(Test_Elements.programTargetPathogen).click();
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(".ng-option-label")).getText(), "Coccidia");
			Helper.driver.findElement(Test_Elements.programTargetPathogen).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

			Helper.driver.findElement(Test_Elements.programProgramType).sendKeys("Vaccine");
			Thread.sleep(1000);	
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
			Helper.driver.findElement(Test_Elements.programProgramType).sendKeys(Keys.ENTER);

			Helper.driver.findElement(Test_Elements.programNoApplicationFlock).sendKeys("64");
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#numOfApplicationId-error-container svg")).size(), 1, "Mandatory check failed on No of Application Flock");
			Helper.driver.findElement(Test_Elements.programNoApplicationFlock).clear();

			String NoApplicationFlock = "4";
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.programNoApplicationFlock).sendKeys(NoApplicationFlock);
			Thread.sleep(1000);

			for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
				Helper.driver.findElement(By.id(Test_Elements.programDaysApplicationFlock+"-"+i)).sendKeys(""+i);
			}
			Thread.sleep(1500);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("#table-vaccine-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.VaccineProgramName)) {
					for (int j=1; j<Helper.driver.findElements(By.cssSelector("#table-vaccine-log tr:nth-child(1) td")).size(); j++) {
						softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child("+j+")")).getText().isEmpty(), false);
					}
					break;
				}
			}

			softAssert.assertAll();
			Test_Variables.test.pass("New Program created successfully");
			Test_Variables.results.createNode("New Program created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 3) 
	public void UpdateProgramVaccine() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-03: Verify that user is able to update Vaccine program", "This testcase will verify that user is able to update vaccine program");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			Test_Variables.preconditions.createNode("5. Create Vaccine Program");
			Test_Variables.steps.createNode("1. Click on edit button next to created vaccine");
			Test_Variables.steps.createNode("2. Make changes and click on save button");

			SoftAssert softAssert = new SoftAssert();

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("#table-vaccine-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.VaccineProgramName)) {				
					Helper.driver.findElement(By.id(Test_Elements.programEditVaccineButton+""+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.programDescription).sendKeys(" Updated");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Program details updated"); 

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("#manage-feed-program-log tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.VaccineProgramName)) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child(4) label")).getText(), "Vaccine Testing Program Updated");						
					break;	
				}
			}

			softAssert.assertAll();
			Test_Variables.test.pass("Vaccine Program updated successfully");
			Test_Variables.results.createNode("New Program updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("New Program failed to update");
			Test_Variables.results.createNode("New Program failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Program failed to update");
			Test_Variables.results.createNode("New Program failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 4) 
	public void DeleteProgramVaccine() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-04: Verify that user is able to delete Vaccine program", "This testcase will verify that user is able to delete vaccine program");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			Test_Variables.preconditions.createNode("5. Create Vaccine Program");
			Test_Variables.steps.createNode("1. Click on delete button next to created vaccine");

			SoftAssert softAssert = new SoftAssert();

			String CountBeforeDelete = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("#table-vaccine-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.VaccineProgramName)) {				
					Helper.driver.findElement(By.id(Test_Elements.programDeleteVaccineButton+""+i)).click();
					break;
				}
			}

			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.popupYesButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Program details deleted"); 

			String CountAfterDelete = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
			softAssert.assertEquals(Integer.parseInt(CountAfterDelete),Integer.parseInt(CountBeforeDelete)-1); 

			softAssert.assertAll();
			Test_Variables.test.pass("Vaccine Program deleted successfully");
			Test_Variables.results.createNode("New Program deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("New Program deleted to update");
			Test_Variables.results.createNode("New Program deleted to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Program deleted to update");
			Test_Variables.results.createNode("New Program deleted to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 5) 
	public void CreateProgramFeed() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-05: Verify that user is able to create new Feed program", "This testcase will verify that user is able to create new program");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			Test_Variables.steps.createNode("1. Click on Create New Program button");
			Test_Variables.steps.createNode("2. Add valid data in all fields");
			Test_Variables.steps.createNode("3. Click on save button");

			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.programCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);

			Helper.driver.findElement(Test_Elements.programName).sendKeys(ProgramManagementModel.FeedProgramName);

			Helper.driver.findElement(Test_Elements.programSupplier).sendKeys("China");
			Thread.sleep(1000);
			if (Helper.driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
				Helper.driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
			}

			else {
				Helper.driver.findElement(By.cssSelector(".list-item")).click();		
			}

			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.programDescription).sendKeys("Feed Testing Program");

			Helper.driver.findElement(Test_Elements.programTargetPathogen).click();
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(".ng-option-label")).getText(), "Coccidia");
			Helper.driver.findElement(Test_Elements.programTargetPathogen).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.programProgramType).sendKeys("Feed");
			Thread.sleep(1000);	
			Helper.driver.findElement(Test_Elements.programProgramType).sendKeys(Keys.ENTER);
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");


			Helper.driver.findElement(Test_Elements.programFeedTypeDropdown).click();
			Thread.sleep(1000);	
			Helper.driver.findElement(Test_Elements.programFeedTypeDropdown).sendKeys(Keys.ENTER);

			Helper.driver.findElement(Test_Elements.programFlockDayStart).sendKeys("1");

			WebElement EndDay = Helper.driver.findElement(Test_Elements.programFlockDayStart);
			Helper.driver.findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

			WebElement ingredient = Helper.driver.findElement(Test_Elements.programFeedTypeDropdown);
			Helper.driver.findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

			WebElement ingredientCategory = Helper.driver.findElement(Test_Elements.programFlockDayStart);
			Helper.driver.findElement(with(By.tagName("input")).below(ingredientCategory)).click();
			List<WebElement> ingredientCategories = Helper.driver.findElements(By.cssSelector(".ng-option-label"));
			softAssert.assertEquals(ingredientCategories.get(0).getText(), "Antibiotic");
			softAssert.assertEquals(ingredientCategories.get(1).getText(), "Coccidia Stat");
			softAssert.assertEquals(ingredientCategories.get(2).getText(), "Natural");
			ingredientCategories.get(0).click();

			//			Helper.driver.findElement(Test_Elements.programFeedCategory).click();
			//			String[] feedCategoriesExpected = {"Pre-stater", "Starter I", "Starter II", "Grower I", "Gower II", "Finisher", "Withdrawal", "Other"};
			//			List<WebElement> feedCategoriesActual = Helper.driver.findElements(By.cssSelector(".ng-option-label"));
			//			for (int i=0;i<feedCategoriesExpected.length;i++) {
			//				softAssert.assertEquals(feedCategoriesActual.get(i).getText(), feedCategoriesExpected[i]);
			//			}		
			//			feedCategoriesActual.get(0).click();

			Helper.driver.findElement(By.xpath(("//*[text()='Add Ingredient']"))).click();

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(5000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("#manage-feed-program-log tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.FeedProgramName)) {
					for (int j=1; j<Helper.driver.findElements(By.cssSelector("#manage-feed-program-log th")).size(); j++) {
						System.out.println(Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child("+j+") label")).getText());
						softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child("+j+") label")).getText().isEmpty(), false);						
					}
					break;
				}
			}
			softAssert.assertAll();
			Test_Variables.test.pass("New Program created successfully");
			Test_Variables.results.createNode("New Program created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 6) 
	public void UpdateProgramFeed() throws InterruptedException, IOException {
		try {					
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-06: Verify that user is able to update Feed program", "This testcase will verify that user is able to update feed program");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			Test_Variables.preconditions.createNode("5. Create feed Program");
			Test_Variables.steps.createNode("1. Click on edit button next to created vaccine");
			Test_Variables.steps.createNode("2. Make changes and click on save button");

			SoftAssert softAssert = new SoftAssert();

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.FeedProgramName)) {				
					Helper.driver.findElement(By.id(Test_Elements.programEditFeedButton+""+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.programDescription).sendKeys(" Updated");

			Helper.driver.findElement(Test_Elements.programAddFeedType).click();
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 1, "Mandatory check failed");
			Helper.driver.findElement(Test_Elements.programFeedType2Dropdown).click();
			Thread.sleep(1000);	
			List<WebElement> feedTypes = Helper.driver.findElements(By.cssSelector(".ng-option-label"));
			feedTypes.get(2).click();

			//Helper.driver.findElement(Test_Elements.programFlockDayStart).sendKeys("10");

			WebElement EndDay = Helper.driver.findElement(Test_Elements.programFlockDayStart2);
			Helper.driver.findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("15");

			WebElement ingredient = Helper.driver.findElement(Test_Elements.programFeedType2Dropdown);
			Helper.driver.findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

			WebElement ingredientCategory = Helper.driver.findElement(Test_Elements.programFlockDayStart2);
			Helper.driver.findElement(with(By.tagName("input")).below(ingredientCategory)).click();

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Program details updated"); 

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("#manage-feed-program-log tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.VaccineProgramName)) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child(4) label")).getText(), "Vaccine Testing Program Updated");						
					break;	
				}
			}
			softAssert.assertAll();
			Test_Variables.test.pass("Feed Program updated successfully");
			Test_Variables.results.createNode("Feed updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Feed failed to update");
			Test_Variables.results.createNode("Feed failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("Feed failed to update");
			Test_Variables.results.createNode("Feed failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 7) 
	public void DeleteProgramFeed() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-08: Verify that user is able to delete Feed program", "This testcase will verify that user is able to delete Feed program");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			Test_Variables.preconditions.createNode("5. Create Feed Program");
			Test_Variables.steps.createNode("1. Click on delete button next to created vaccine");

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			for (int i=1; i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {

				if (Helper.driver.findElement(By.cssSelector("#manage-feed-program-log tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(ProgramManagementModel.FeedProgramName)) {				
					Helper.driver.findElement(By.id(Test_Elements.programDeleteFeedButton+""+i)).click();
					break;
				}
			}

			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupYesButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Program details deleted", "Alert message did not displayed or matched"); 
			Test_Variables.test.pass("Feed Program deleted successfully");
			Test_Variables.results.createNode("New Program deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("New Program deleted to update");
			Test_Variables.results.createNode("New Program deleted to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Program deleted to update");
			Test_Variables.results.createNode("New Program deleted to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 8) 
	public void VerifyColumns() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-09: Verify that all columns appear", "This testcase will verify that all columns appear in table");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			Test_Variables.preconditions.createNode("5. Create Feed Program");
			Test_Variables.steps.createNode("1. Verify all columns appear and columns have filter icon with them");

			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			String[] vaccineColumnNamesExpected = {"Program Name", "Supplier Name", "No. Of Applications On Flock", "Description", "Action"};
			List<WebElement> vaccineColumnNamesActual = Helper.driver.findElements(By.cssSelector(Test_Elements.programVaccineTable+" th"));
			int size = vaccineColumnNamesActual.size();
			for(int i =0; i<size ; i++){
				softAssert.assertEquals(vaccineColumnNamesActual.get(i).getText(), vaccineColumnNamesExpected[i]);
			}

			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.programVaccineTable+" #programName_show-filter")).size(), 1, "Program Name filter not displaying");
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.programVaccineTable+" #supplierName_show-filter")).size(), 1, "Supplier Name filter not displaying");

			softAssert.assertEquals(Helper.driver.findElements(By.id("description_show-filter")).size(), 0, "Description showing filter");
			softAssert.assertEquals(Helper.driver.findElements(By.id("noOfApplications_show-filter")).size(), 0, "No of Application showing filter");
			softAssert.assertEquals(Helper.driver.findElements(By.id("feedName_show-filter")).size(), 0, "Feed Type column showing filter");

			Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);

			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.programFeedTable+" #programName_show-filter")).size(), 1, "Program Name filter not displaying");
			softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+Test_Elements.programFeedTable+" #supplierName_show-filter")).size(), 1, "Supplier Name filter not displaying");

			String[] feedColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Feed Types", "Action"};
			List<WebElement> feedColumnNamesActual = Helper.driver.findElements(By.cssSelector(Test_Elements.programFeedTable+" th"));
			int sizeFeedTable = vaccineColumnNamesActual.size();
			for(int i =0; i<sizeFeedTable ; i++){
				softAssert.assertEquals(feedColumnNamesActual.get(i).getText(), feedColumnNamesExpected[i]);
			}
			softAssert.assertAll();
			Test_Variables.test.pass("All columns displayed successfully");
			Test_Variables.results.createNode("All columns displayed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("All columns did not displayed");
			Test_Variables.results.createNode("All columns did not displayed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("All columns did not displayed");
			Test_Variables.results.createNode("All columns did not displayed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (priority = 9) 
	public void LockVaccine() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Test_Functions.Lock(Test_Elements.programVaccineTable, "Program Management", Constants.ProgramManagementReportPath);
	}


	@Test (priority = 10) 
	public void LockFeed() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		Test_Functions.Lock(Test_Elements.programFeedTable, "Program Management", Constants.ProgramManagementReportPath);
	}


	@Test (priority = 11) 
	public void WildcardVaccine() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);

		Test_Functions.Wildcard(Test_Elements.programVaccineTable, "Program Management", Constants.ProgramManagementReportPath);
	}


	@Test (priority = 12) 
	public void WildcardFeed() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		Test_Functions.Wildcard(Test_Elements.programFeedTable, "Program Management", Constants.ProgramManagementReportPath);
	}


	@Test(priority= 13)
	public void sortingVaccine() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Test_Functions.Sorting(Test_Elements.programVaccineTable, "Program Management", Constants.ProgramManagementReportPath);
	}


	@Test(priority= 14)
	public void sortingFeed() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		Test_Functions.Sorting(Test_Elements.programFeedTable, "Program Management", Constants.ProgramManagementReportPath);
	}


	@Test(priority= 15)
	public void ExportVaccineCSV() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);

		Test_Functions.CSVExport("Program Management", Constants.ProgramManagementReportPath, Test_Elements.programVaccineCSVFileName, Test_Elements.programVaccineTable);
	}


	@Test(priority= 16)
	public void ExportFeedCSV() throws InterruptedException, IOException {	
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		Test_Functions.CSVExport("Program Management", Constants.ProgramManagementReportPath, Test_Elements.programFeedCSVFileName, Test_Elements.programFeedTable);
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}
}
