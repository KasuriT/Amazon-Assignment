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
import Test.Ancera.DateUtil;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;
import static Test.Ancera.Test_Functions.NavigateToScreen;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProgramManagement {


	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Program_Management"+Test_Variables.date+".html");
		spark.config().setReportName("Program Management Test Report"); 
		config();
		ConfigureLogin.login();
	}


	@Test(priority= 1)
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen(Constants.url_programManagement, "Program Management", Constants.ProgramManagementReportPath, Test_Elements.programManagementTitle);
	}	
	
	
	@Test (enabled= true, priority= 2) 
	public void CreatePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();
		
		driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		
		for (ProgramManagementModel objModel : Test_Variables.lstProgramManagementSearch) { 	
			try {
				test = Test_Variables.extent.createTest(objModel.TestCaseNameCreate);
				preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				preconditions.createNode("1. Go to url " +Constants.url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
				steps.createNode("1. Click on Create New Program button");
				steps.createNode("2. Add valid data in all fields");
				steps.createNode("3. Click on save button");

				SoftAssert softAssert = new SoftAssert();

				Helper.driver.findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Helper.driver.findElement(By.xpath("//*[text()=' Register New Program']")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

				//Program Name
				softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
				driver.findElement(Test_Elements.programName).sendKeys(objModel.ProgramName);
				softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

				//Target Pathogen
				driver.findElement(Test_Elements.programTargetPathogen).click();
				Thread.sleep(500);
				softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(".ng-option-label")).getText(), "Coccidia");
				driver.findElement(Test_Elements.programTargetPathogen).sendKeys(Keys.ENTER);
				Thread.sleep(500);
				softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
				
				//Program Type
				driver.findElement(Test_Elements.programProgramType).sendKeys(objModel.ProgramType);
				Thread.sleep(500);	
				softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
				driver.findElement(Test_Elements.programProgramType).sendKeys(Keys.ENTER);
				
				//Supplier
				driver.findElement(Test_Elements.programSupplier).sendKeys(ProgramManagementModel.SupplierName);
				Thread.sleep(500);
				if (driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
					driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
				}
				else {
					Helper.driver.findElement(By.cssSelector(".list-item")).click();		
				}
				Thread.sleep(500);
				
				//Complex
				driver.findElement(By.cssSelector("#compleSiteId .toggle-list")).click();
				if (Constants.url.contains("qa")) {
					driver.findElement(By.id("compleSiteId_search")).sendKeys(ProgramManagementModel.ComplexNameQA);
				}
				if (Constants.url.contains("uat")) {
					driver.findElement(By.id("compleSiteId_search")).sendKeys(ProgramManagementModel.ComplexNameUAT);
				}
				if (Constants.url.contains("dev")) {
					driver.findElement(By.id("compleSiteId_search")).sendKeys(ProgramManagementModel.ComplexNameDEV);
				}
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("label b")).click();
				
				//Start Date
				driver.findElement(By.cssSelector("#startDate img")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(500);
				WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
				List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
				DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
				Thread.sleep(500);

				//End Date
				driver.findElement(By.cssSelector("#endDate img")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(500);
				WebElement dateWidgetToEnd = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
				List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
				DateUtil.clickGivenDay(columns2, DateUtil.getDay("07"));
				Thread.sleep(700);
				
				//Program Description
				driver.findElement(Test_Elements.programDescription).sendKeys(ProgramManagementModel.DescriptionName);

				//Vaccine Number of Applications on Flock
				if (objModel.ProgramType.startsWith("Vaccine")) {
					driver.findElement(Test_Elements.programNoApplicationFlock).sendKeys("64");
					softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#numOfApplicationId-error-container svg")).size(), 1, "Mandatory check failed on No of Application Flock");
					Helper.driver.findElement(Test_Elements.programNoApplicationFlock).clear();

					String NoApplicationFlock = "2";
					driver.findElement(Test_Elements.programNoApplicationFlock).sendKeys(NoApplicationFlock);
					Thread.sleep(500);

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						driver.findElement(By.id(Test_Elements.programDaysApplicationFlock+"-"+i)).sendKeys(""+i);
					}
				}
				
				//Feed Details
				if (objModel.ProgramType.equals("Feed")) {
					driver.findElement(Test_Elements.programFeedTypeDropdown).click();
					Thread.sleep(500);	
					driver.findElement(Test_Elements.programFeedTypeDropdown).sendKeys(Keys.ENTER);

					driver.findElement(Test_Elements.programFlockDayStart).sendKeys("1");

					WebElement EndDay = Helper.driver.findElement(Test_Elements.programFlockDayStart);
					driver.findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

					WebElement ingredient = Helper.driver.findElement(Test_Elements.programFeedTypeDropdown);
					driver.findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

					WebElement ingredientCategory = Helper.driver.findElement(Test_Elements.programFlockDayStart);
					driver.findElement(with(By.tagName("input")).below(ingredientCategory)).click();
					List<WebElement> ingredientCategories = Helper.driver.findElements(By.cssSelector(".ng-option-label"));
					softAssert.assertEquals(ingredientCategories.get(0).getText(), "Antibiotic");
					softAssert.assertEquals(ingredientCategories.get(1).getText(), "Coccidia Stat");
					softAssert.assertEquals(ingredientCategories.get(2).getText(), "Natural");
					ingredientCategories.get(0).click();
					Helper.driver.findElement(By.xpath(("//*[text()='Add Ingredient']"))).click();
				}
				
				//Treatment Details
				if(objModel.ProgramType.equals("Treatment")) {
					driver.findElement(By.xpath("//div[2]/div[1]/div[1]/app-anc-input-box/div/input[1]")).sendKeys("Treatment Program");
				//	Helper.driver.findElement(By.xpath("//label[text() = ' Treatment Name ']")).sendKeys("Treatment Program");
				}
				
				//Bioshuttle Details
				if(objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
				//  driver.findElement(By.cssSelector("[formcontrolname = 'fcBioshuttleName'] input.ng-touched")).sendKeys("BioShuttle Vaccine");
					driver.findElement(By.xpath("//div[2]/div[1]/app-anc-input-box/div/input[1]")).sendKeys("BioShuttle Vaccine");	
				//	Helper.driver.findElement(By.xpath("//*[text() = ' Bioshuttle Name ']")).sendKeys("BioShuttle Vaccine");
				}
								
				test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				driver.findElement(By.xpath(("//button[text() = ' Submit ']"))).click();
				
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				
				softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 
								
				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programFeedSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programFeedStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programFeedEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);			
				}
				
				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}
							
				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programVaccineNumberOfApplicationFlockCol)).getText(), "4");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programVaccineStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programVaccineEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}
				
				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleNumberOfApplicationFlockCol)).getText(), "4");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleFlockDayStartCol)).getText(), "");	
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+Test_Elements.programBioshuttleFlockDayEndCol)).getText(), "");	
				}
				
				softAssert.assertAll();
				Test_Variables.test.pass("New Program created successfully");
				Test_Variables.results.createNode("New Program created successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("New Program failed to create");
				Test_Variables.results.createNode("New Program failed to create");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				Test_Variables.test.fail("New Program failed to create");
				Test_Variables.results.createNode("New Program failed to create");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}


	
	@Test (enabled= true, priority= 2, dependsOnMethods = {"CreatePrograms"}) 
	public void UpdatePrograms() throws InterruptedException, IOException {
		Test_Variables.lstProgramManagementSearch = ProgramManagementModel.FillData();
		
		Helper.driver.get(Constants.url_programManagement);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		
		for (ProgramManagementModel objModel : Test_Variables.lstProgramManagementSearch) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseNameUpdate);
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

				Helper.driver.findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				
				
				List<WebElement> programsName = Helper.driver.findElements(By.cssSelector(objModel.ProgramName_CSS+" label"));
				
				for (int i = 0; i <= programsName.size(); i++) {
					if (programsName.get(i).equals(objModel.ProgramName)) {
						Helper.driver.findElement(By.id(objModel.EditButtonPre+""+i+"-"+objModel.ButtonPost)).click();
					}
				}
				
				//Program Description
				Helper.driver.findElement(Test_Elements.programDescription).sendKeys(ProgramManagementModel.DescriptionName+" Updated");
								
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
				
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				
				softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Program details deleted"); 
								
				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programFeedSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programFeedStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programFeedEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);			
				}
				
				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}
							
				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programVaccineNumberOfApplicationFlockCol)).getText(), "4");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programVaccineStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programVaccineEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}
				
				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleNumberOfApplicationFlockCol)).getText(), "4");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleStartDateCol)).getText(), Test_Variables.dateMM+"/01/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleEndDateCol)).getText(), Test_Variables.dateMM+"/07/"+Test_Variables.dateYYYY);
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleFlockDayStartCol)).getText(), "");	
					softAssert.assertEquals(Helper.driver.findElement(By.cssSelector(Test_Elements.programBioshuttleFlockDayEndCol)).getText(), "");	
				}
				
				softAssert.assertAll();
				Test_Variables.test.pass("New Program created successfully");
				Test_Variables.results.createNode("New Program created successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("New Program failed to create");
				Test_Variables.results.createNode("New Program failed to create");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				Test_Variables.test.fail("New Program failed to create");
				Test_Variables.results.createNode("New Program failed to create");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}
	
	
	@Test (enabled= true, priority= 2, dependsOnMethods = {"CreatePrograms"}) 
	public void DeletePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();
		
		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		
		for (ProgramManagementModel objModel : Test_Variables.lstProgramManagementSearch) { 	
			try {
				test = Test_Variables.extent.createTest(objModel.TestCaseNameDelete);
				steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				steps.createNode("1. Delete the created program");

				SoftAssert softAssert = new SoftAssert();
				driver.findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);
				
				List<WebElement> programsName = Helper.driver.findElements(By.cssSelector(objModel.ProgramName_CSS+" label"));
				
				for (int i = 0; i <= programsName.size(); i++) {
					if (programsName.get(i).equals(objModel.ProgramName)) {
						driver.findElement(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost)).click();
					}
				}
				
				waitElementInvisible(loading_cursor);				
				Thread.sleep(1000);
				
				softAssert.assertEquals(Helper.driver.findElement(alertMessage).getText(), "Program details deleted"); 			
				softAssert.assertAll();
				test.pass("New Program created successfully");
				results.createNode("New Program created successfully");
				test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}
	

	@Test (enabled= true, priority= 2, dependsOnMethods = {"CreatePrograms"}) 
	public void ProgramsUtilization() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();
		
		driver.get(Constants.url_programManagement);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		
		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = Test_Variables.extent.createTest(objModel.TestCaseNameDelete);
				steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				steps.createNode("1. Click on Create New Program button");
				steps.createNode("2. Add valid data in all fields");
				steps.createNode("3. Click on save button");

				SoftAssert softAssert = new SoftAssert();
			
				driver.findElement(By.cssSelector("programName_show-filter-program")).click();
				driver.findElement(By.cssSelector("program_utilization_programName_search-input")).sendKeys(objModel.ProgramName);
				driver.findElement(By.cssSelector("program_utilization_programName_apply")).click();
				
				softAssert.assertEquals(Helper.driver.findElement(alertMessage).getText(), "Program details deleted"); 			
				softAssert.assertAll();
				test.pass("New Program created successfully");
				results.createNode("New Program created successfully");
				test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}
	

	@Test (enabled= true, priority= 8) 
	public void VerifyColumns() throws InterruptedException, IOException {
		try {		
			test = Test_Variables.extent.createTest("AN-Program-09: Verify that all columns appear", "This testcase will verify that all columns appear in table");
			preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			preconditions.createNode("1. Go to url " +Constants.url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			preconditions.createNode("5. Create Feed Program");
			steps.createNode("1. Verify all columns appear and columns have filter icon with them");

			SoftAssert softAssert = new SoftAssert();

			driver.get(Constants.url_programManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String[] feedColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Feed Types", "Start Date", "End Date", "Complex", "Action"};
			String[] treatmentColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Start Date", "End Date", "Treatment Name", "Flock Day Start", "Flock Day End", "Route", "Treatment Description", "Complex", "Action"};
			String[] vaccineColumnNamesExpected = {"Program Name", "Supplier Name", "No. Of Applications On Flock", "Description", "Start Date", "End Date", "Flock Day Applications", "Complex", "Action"};
			String[] vaccineBioshuttleColumnNamesExpected = {"Program Name", "Supplier Name", "No. Of Applications On Flock", "Description", "Start Date", "End Date", "Flock Day Applications", "Bioshuttle Name", "Bioshuttle Flock Day Start", "Bioshuttle Flock Day End", "Complex", "Action"};
	
		//	click(programFeedProgramTab_XPATH);
			waitElementInvisible(loading_cursor);

			List<WebElement> feedColumnNamesActual = Helper.driver.findElements(By.cssSelector(Test_Elements.programFeedTable+" th"));
			int sizeFeedTable = feedColumnNamesActual.size();
			for(int i =0; i<sizeFeedTable ; i++){
				softAssert.assertEquals(feedColumnNamesActual.get(i).getText(), feedColumnNamesExpected[i]);
			}
			
			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programFeedTable+" #programName_show-filter")).size(), 1, "Program Name filter not displaying");
			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programFeedTable+" #supplierName_show-filter")).size(), 1, "Supplier Name filter not displaying");
		
			System.out.println("32");
		//	click(programVaccineProgramTab_XPATH);
			System.out.println("322");
			waitElementInvisible(loading_cursor);
			Thread.sleep(4000);
			
			
			List<WebElement> vaccineColumnNamesActual = Helper.driver.findElements(By.cssSelector(programVaccineTable+" th"));
			int size = vaccineColumnNamesActual.size();
			for(int i =0; i<size ; i++){
				softAssert.assertEquals(vaccineColumnNamesActual.get(i).getText(), vaccineColumnNamesExpected[i]);
			}

			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programVaccineTable+" #programName_show-filter")).size(), 1, "Program Name filter not displaying");
			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programVaccineTable+" #supplierName_show-filter")).size(), 1, "Supplier Name filter not displaying");
			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programVaccineTable+" #noOfApplications_show-filter")).size(), 1, "No of Application on Flock filter not displaying");
			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programVaccineTable+" #startDate_show-filter")).size(), 1, "Start Date filter not displaying");
			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programVaccineTable+" #endDate_show-filter")).size(), 1, "End Date filter not displaying");
			softAssert.assertEquals(driver.findElements(By.cssSelector("#"+programVaccineTable+" #complex_show-filter")).size(), 1, "Complex filter not displaying");
			softAssert.assertEquals(driver.findElements(By.id("description_show-filter")).size(), 0, "Description showing filter");
			softAssert.assertEquals(driver.findElements(By.id("noOfApplications_show-filter")).size(), 0, "No of Application showing filter");
			softAssert.assertEquals(driver.findElements(By.id("feedName_show-filter")).size(), 0, "Feed Type column showing filter");
			softAssert.assertAll();
			test.pass("All columns displayed successfully");
			results.createNode("All columns displayed successfully");
			test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			test.fail("All columns did not displayed");
			results.createNode("All columns did not displayed");
			saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("All columns did not displayed");
			results.createNode("All columns did not displayed");
			saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}
	
	
	@Test (priority = 10) 
	public void LockFeed() throws InterruptedException, IOException {
		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(Test_Elements.programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(Test_Elements.programFeedTable, "Program Management", Constants.ProgramManagementReportPath, 0);
	}

	
	@Test (priority = 11) 
	public void LockTreatment() throws InterruptedException, IOException {
		driver.findElement(Test_Elements.programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(Test_Elements.programTreatmentTable, "Program Management", Constants.ProgramManagementReportPath, 0);
	}
	
	
	@Test (priority = 12) 
	public void LockVaccine() throws InterruptedException, IOException {
		driver.findElement(Test_Elements.programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(Test_Elements.programVaccineTable, "Program Management", Constants.ProgramManagementReportPath, 0);
	}
	
	
	@Test (priority = 13) 
	public void LockBioshuttle() throws InterruptedException, IOException {
		Helper.driver.findElement(Test_Elements.programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(Test_Elements.programBioshuttleTable, "Program Management", Constants.ProgramManagementReportPath, 0);
	}
	
	
	@Test (priority = 14) 
	public void LockUtilization() throws InterruptedException, IOException {
		Helper.driver.findElement(Test_Elements.programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(Test_Elements.programUtilizationTable, "Program Management", Constants.ProgramManagementReportPath, 0);
	}

	
	@Test (priority = 15) 
	public void PaginationFeed() throws InterruptedException, IOException {
		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(Test_Elements.programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(Test_Elements.programFeedTable, "Program Management", Constants.ProgramManagementReportPath);
	}
	
	@Test (priority = 16) 
	public void PaginationTreatment() throws InterruptedException, IOException {
		driver.findElement(Test_Elements.programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(Test_Elements.programTreatmentTable, "Program Management", Constants.ProgramManagementReportPath);
	}

	@Test (priority = 16) 
	public void PaginationVaccine() throws InterruptedException, IOException {
		driver.findElement(Test_Elements.programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(Test_Elements.programVaccineTable, "Program Management", Constants.ProgramManagementReportPath);
	}
	
	@Test (priority = 16) 
	public void PaginationBioshuttle() throws InterruptedException, IOException {
		driver.findElement(Test_Elements.programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(Test_Elements.programUtilizationTable, "Program Management", Constants.ProgramManagementReportPath);
	}

/*

	@Test (priority = 9) 
	public void LockVaccine() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
		waitElementInvisible(loading_cursor);
		Test_Functions.Lock(Test_Elements.programVaccineTable, "Program Management", Constants.ProgramManagementReportPath, 0);
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
		Test_Functions.Wildcard(Test_Elements.programFeedTable, "Program Management", Constants.ProgramManagementReportPath, 0);
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
*/

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}
}
