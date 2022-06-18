package Test.Ancera.Administration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import Models.ComplexConfigModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.DateUtil;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class PreFlutterMobile{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Pre_Flutter_Mobile"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Pre Flutter Mobile Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (priority = 1) 
	public void CreateOrg() throws InterruptedException, IOException {
			try{
				Test_Variables.test = Test_Variables.extent.createTest("AN-OM-20: Verify user can create New Organizationn", "This test case will verify that user can create new organization");
		
				Helper.driver.get(Constants.url_organization);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				
				Helper.driver.findElement(Test_Elements.orgCreateButton).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				Helper.driver.findElement(Test_Elements.orgTypeDropDownExpand).click();
				Thread.sleep(750);
				Helper.driver.findElement(Test_Elements.orgTypeInput).sendKeys(Keys.ENTER);
				
				Helper.driver.findElement(Test_Elements.orgNameInput).sendKeys(ComplexConfigModel.organizationName);
				
				Helper.driver.findElement(Test_Elements.popupNextButton).click();
				Thread.sleep(1000);
				Helper.driver.findElement(Test_Elements.orgMaxUsersInput).sendKeys("10");
				
				Helper.driver.findElement(Test_Elements.orgSystemRolesExpand).click();
				Thread.sleep(1000);
				List<WebElement> systemroleSelect = Helper.driver.findElements(By.cssSelector(".ng-dropdown-panel-items"));
				systemroleSelect.get(0).click();			
				Helper.driver.findElement(Test_Elements.orgSystemRolesExpand).click();
				
				Helper.driver.findElement(Test_Elements.orgReportRolesExpand).click();
				Thread.sleep(1000);
				List<WebElement> reportroleSelect = Helper.driver.findElements(By.cssSelector(".ng-dropdown-panel-items"));
				reportroleSelect.get(0).click();
				
				Helper.driver.findElement(Test_Elements.orgReportRolesExpand).click();
				Helper.driver.findElement(Test_Elements.popupSaveButton).click();;
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(0)); 
				Test_Variables.test.pass("New Organization created successfully");
				Test_Variables.results.createNode("New Organization created successfully; displayed alert message 'New organization created.'");	
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
			}catch(AssertionError er){
				Test_Variables.test.fail("New Organization creation failed");
				Test_Variables.results.createNode("New Organization creation failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
			}	
			catch(Exception ex){
				Test_Variables.test.fail("New Organization creation failed");
				Test_Variables.results.createNode("New Organization creation failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
			}	
		}
	

	@Test (description="Test Case: Organization Site Check",enabled= true, priority= 10) 
	public void OrganizationSitesHierarchyCheck() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");

			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(ComplexConfigModel.organizationName)) {
					Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}

			Thread.sleep(1000);	
		//	Helper.driver.findElement(Test_Elements.orgParentSiteClick).click(); 
		//	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		//	Thread.sleep(2000);



			Helper.driver.findElement(Test_Elements.orgAddSite1).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInput).click();
			String regionType = Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).getText();	
			softAssert.assertEquals(regionType, "Region");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Region");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			Helper.driver.findElement(Test_Elements.orgAddSite2).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInput).click();
			String subregionType = Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Sub Region");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			Helper.driver.findElement(Test_Elements.orgAddSite3).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Complex Site");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			Helper.driver.findElement(Test_Elements.orgAddSite4).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String farmType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Farm");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("11. Click on + icon to create new site and verify Site Type as House");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String HouseType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test House");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");
			softAssert.assertAll();
			Test_Variables.test.pass("Site heirarchy verified successfully");
			Test_Variables.results.createNode("Site heirarchy verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);	
		}catch(AssertionError er){
			Test_Variables.test.fail("Site heirarchy failed to verify successfully");
			Test_Variables.results.createNode("Site heirarchy failed to verify successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Site heirarchy failed to verify successfully");
			Test_Variables.results.createNode("Site heirarchy failed to verify successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}	

	

	@Test (enabled= true, priority= 10) 
	public void VerifyTestingSitesAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning All Testing Sites to the user", "This test case will verify Sites column displays Active after assigning sites to the user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);
		
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					
					
					WebElement filter_scroll = Helper.driver.findElement(By.id("edit-user-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
					Thread.sleep(2000);
					
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(4000);
					
					Helper.driver.findElement(By.id("btn-next")).click();
					Helper.driver.findElement(By.id("btn-next")).click();
					Thread.sleep(750);
					
					Helper.driver.findElement(By.cssSelector(".btn-sites")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);
					
					Helper.driver.findElement(Test_Elements.userSitesSearch).sendKeys(ComplexConfigModel.organizationName);
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath("//*[text()=' "+ComplexConfigModel.organizationName+" ']")).click();
					
					Helper.driver.findElement(By.id("btn-ok-sites")).click();
					Thread.sleep(1000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.driver.findElement(By.id("btn-save")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);
					Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");	
					Test_Variables.test.pass("Site Assigned to user successfully");
					Test_Variables.results.createNode("Site Assigned to user successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.PreFlutterMobileReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
					break;
					
				}
			}	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Site failed to assigned to user successfully");
			Test_Variables.results.createNode("Site failed to assigned to user successfully");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Site failed to assigned to user successfully");
			Test_Variables.results.createNode("Site failed to assigned to user successfully");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, ex);
		}
	}
	
	
	
	


	@Test (enabled= true, priority= 2) 
	public void CreateProgramVaccine() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-02: Verify that user is able to create new Vaccine program", "This testcase will verify that user is able to create new program");

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);



			//			List<WebElement> allProducts = Helper.driver.findElements(Test_Elements.programColumn1);
			//			for (WebElement product: allProducts) {
			//				System.out.println(product.getText());
			//			    if(!product.getText().equals(ComplexConfigModel.vaccineName)) 
			//			        return; // or break or whatever
			//			}



			for (int j=1;j<Helper.driver.findElements(Test_Elements.programColumn1).size();j++) {
				if (Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-0 label")).getText().equals(ComplexConfigModel.vaccineName)) {
					break;
				}

				else {
					Helper.driver.findElement(Test_Elements.programCreateButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);

					Helper.driver.findElement(Test_Elements.programName).sendKeys(ComplexConfigModel.vaccineName);

					Helper.driver.findElement(Test_Elements.programTargetPathogen).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.programTargetPathogen).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys("Vaccine");
					Thread.sleep(1000);	
					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programSupplier).sendKeys("China");
					Thread.sleep(1000);
					if (Helper.driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						Helper.driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
					}

					else {
						Helper.driver.findElement(By.cssSelector(".list-item")).click();		
					}

					Helper.driver.findElement(Test_Elements.programDescription).sendKeys("Vaccine Testing Program");

					Helper.driver.findElement(By.cssSelector("#startDate img")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);
					WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(2000);

					String NoApplicationFlock = "2";
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
					Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 
					Test_Variables.test.pass("New Program created successfully");
					Test_Variables.results.createNode("New Program created successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
					break;
				}
			}

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


	@Test (enabled= true, priority= 5) 
	public void CreateProgramFeed() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-05: Verify that user is able to create new Feed program", "This testcase will verify that user is able to create new program");

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);

			Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();

			String rowCount = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

			for (int j=1;j<Integer.parseInt(rowCount);j++) {
				if (Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-0 label")).getText().equals(ComplexConfigModel.feedName)) {
					break;
				}

				else {

					Helper.driver.findElement(Test_Elements.programCreateButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);

					Helper.driver.findElement(Test_Elements.programName).sendKeys(ComplexConfigModel.feedName);

					Helper.driver.findElement(Test_Elements.programTargetPathogen).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.programTargetPathogen).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys("Feed");
					Thread.sleep(1000);	
					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys(Keys.ENTER);

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
					ingredientCategories.get(0).click();

					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
					Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(5000);
					Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 
					Test_Variables.test.pass("New Program created successfully");
					Test_Variables.results.createNode("New Program created successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
					break;
				}
			}
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



	@Test (description="Test Case: Create complex Configurations",enabled= true, priority = 4) 
	public void CreateComplexConfig() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Complex: Create Complex Configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Complex OPG Range Config; Screen opens");
			Test_Variables.steps.createNode("1. Create Configuration");

			Helper.driver.get(Constants.url_complexConfig);;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			Test_Variables.lstCreateComplexConfig = ComplexConfigModel.CreateConfig();

			Helper.driver.findElement(Test_Elements.complexCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectComplexDropdown).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSearchComplex).sendKeys("TestComplex");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectComplexSite).click();

			Helper.driver.findElement(Test_Elements.complexSelectVaccine).sendKeys("TestCMP");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectVaccine).sendKeys(Keys.ENTER);

			Helper.driver.findElement(Test_Elements.complexSelectFeed).sendKeys("TestCMF");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectFeed).sendKeys(Keys.ENTER);

			for (ComplexConfigModel objModel : Test_Variables.lstCreateComplexConfig) { 	

				Helper.driver.findElement(Test_Elements.complexOPGType).click();
				Thread.sleep(1000);
				List<WebElement> OPGTypeSelect = Helper.driver.findElements(Test_Elements.complexSelectValueFromDropdown);
				OPGTypeSelect.get(0).click();

				Helper.driver.findElement(Test_Elements.complexBirdSize).click();
				Thread.sleep(1000);
				List<WebElement> BirdSizeSelect = Helper.driver.findElements(Test_Elements.complexSelectValueFromDropdown);
				BirdSizeSelect.get(0).click();

				Helper.driver.findElement(Test_Elements.complexSamplingInterval).click();
				Thread.sleep(1000);
				List<WebElement> SamplingIntervalSelect = Helper.driver.findElements(Test_Elements.complexSelectValueFromDropdown);
				SamplingIntervalSelect.get(0).click();

				Helper.driver.findElement(Test_Elements.complexComplexThreshold).sendKeys(objModel.ComplexThreshold);
				Helper.driver.findElement(Test_Elements.complexHouseThreshold).sendKeys(objModel.HouseThreshold);
				Helper.driver.findElement(Test_Elements.complexLowerLimit).sendKeys(objModel.LowerLimit);
				Helper.driver.findElement(Test_Elements.complexUpperLimit).sendKeys(objModel.UpperLimit);
				Thread.sleep(1000);
				Helper.driver.findElement(Test_Elements.complexAddButton).click();
			}

			Helper.driver.findElement(Test_Elements.complexSubmitButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertNotEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Complex cycling configuration details saved");
			Test_Variables.test.pass("Complex cycling configuration details saved successfully");
			Test_Variables.results.createNode("Complex cycling configuration details saved successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PreFlutterMobileReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Complex cycling configuration details failed to save");
			Test_Variables.results.createNode("Complex cycling configuration details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Complex cycling configuration details failed to save");
			Test_Variables.results.createNode("Complex cycling configuration details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, ex);
		}
	}


	
	@Test (enabled= true, priority =11) 
	public void CreateFlock() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-98: Verify user can create Flock", "This test case will verify that user can crate flock");
			Helper.driver.get(Constants.url_flockRegistration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			Helper.driver.findElement(Test_Elements.flockCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	

			Helper.driver.findElement(Test_Elements.flockIntegratorFlockID).sendKeys("IntegratorID_"+Test_Variables.date0);
			if (Helper.driver.findElements(By.cssSelector("#integratorFlockId .list-item")).size() != 0) {
				Helper.driver.findElement(By.cssSelector("#integratorFlockId .list-item")).click();
			}
	
			Helper.driver.findElement(Test_Elements.flockBirdSizeExpandDropDown).click();
			List<WebElement> birdSizeList = Helper.driver.findElements(By.cssSelector(".ng-option"));
			birdSizeList.get(5).click();
		
			Helper.driver.findElement(Test_Elements.flockFarmExpandDropdown).click();

			Helper.driver.findElement(Test_Elements.flockFarmSeach).sendKeys(ComplexConfigModel.organizationFarm1Name);
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//b[text() = '"+ComplexConfigModel.organizationFarm1Name+"']")).click();
			
			Helper.driver.findElement(Test_Elements.flockHousePlacementDots).click();

			Helper.driver.findElement(By.cssSelector("#placementDate img")).click();
			Thread.sleep(1000);		
			
			WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#placementDate .dp-popup"))).get(0);
			List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
			DateUtil.clickGivenDay(columns1, DateUtil.getDay("02"));

			Helper.driver.findElement(Test_Elements.flockSelectHouses).click();
			Helper.driver.findElement(By.xpath("//*[text() = '"+ComplexConfigModel.organizationHouse1Name+"']")).click();
			Helper.driver.findElement(Test_Elements.flockHouseSaveButton).click();
			
			Helper.driver.findElement(Test_Elements.flockProgramDetailsDots).click();
			
			Helper.driver.findElement(Test_Elements.flockProgramExpandDropDown).sendKeys(ComplexConfigModel.vaccineName);			
			Helper.driver.findElement(Test_Elements.flockProgramStartDate).click();
			
			WebElement dateWidgetTo1 = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
			List<WebElement> columns2 = dateWidgetTo1.findElements(By.tagName("button"));
			DateUtil.clickGivenDay(columns2, DateUtil.getDay("01"));

			Helper.driver.findElement(Test_Elements.flockAdministrationMethod).sendKeys("TestCMSMethod");
			
			Assert.assertTrue(!Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-39 label")).getText().isEmpty(), "Farm Site ID is empty");
			
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
	
	


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}
