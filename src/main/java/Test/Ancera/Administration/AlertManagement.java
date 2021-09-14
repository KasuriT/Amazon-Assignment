package Test.Ancera.Administration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.AlertManagementModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class AlertManagement {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Administration_Alert_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Agreement Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Alert Management Screen",enabled= true, priority = 1) 
	public void NavigateAlertManagement() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-License-01: Verify user can navigate to Alert Management screen", "This test case will verify that user can navigate to Alert Management screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Alert Management", Constants.AlertManagementReportPath));
			Helper.driver.get(Constants.url_alert);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Alert Management")));
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on Administration and select Alert Management");

			Assert.assertEquals(Helper.driver.findElement(By.id("Alert Management")).getText(), "Alert Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Alert Management Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.AlertManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.AlertManagementReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Alert Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Alert Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, ex);
		}
	}




	@Test (description="Test Case: Navigate to Alert Management Screen",enabled= false, priority = 1) 
	public void NavigateAlertsdManagement() throws InterruptedException, IOException {
		try {

			DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date12 = new Date();
			String date102 = dateFormat2.format(date12);
			String myTime = date102;
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date d = df.parse(myTime); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.MINUTE, 10);
			String newTime = df.format(cal.getTime());
			System.out.println(newTime);


		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Alert Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to Alert Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, ex);
		}
	}





	@SuppressWarnings("unused")
	@Test (enabled= true, priority = 2) 
	public void CreateAlert() throws InterruptedException, IOException {

		Test_Variables.lstAlertCreate = AlertManagementModel.FillData();
		for (AlertManagementModel objModel : Test_Variables.lstAlertCreate) { 
			try{
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Navigate to Alert Management screen");
				Test_Variables.steps.createNode("1. Click on create new button");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {

						for (int i=2; i <=Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
							if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).size() != 0) {
								//	System.out.println(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).getText());
								if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).getText().equals(objModel.AlertDesc)) {
									Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).click();
									Thread.sleep(1500);
									WebElement filter_scroll = Helper.driver.findElement(By.id("duplicate-alert"));
									((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
									Thread.sleep(1000);
									if (Helper.driver.findElements(By.id("activate-alert")).size() == 1) {
										Helper.driver.findElement(By.id("activate-alert")).click();
									}
									else {
										Helper.driver.findElement(By.id("close-duplicate-modal")).click();
									}
								}
							}
						}




						List<WebElement> rows = Helper.driver.findElements(By.cssSelector("td:nth-child(6)"));
						//    List<WebElement> column = Helper.driver.findElements(By.tagName("td"));
						List<String> value = new ArrayList<String>();

						//    System.out.println(rows.size());

						for (int j=0; j<rows.size(); j++){
							System.out.println(rows.get(j).getText());
							value.add(rows.get(j).getText());
						}
						if (value.contains(objModel.AlertDesc)){
							System.out.println("Value found");
						}
						else{
							System.out.println("Not Found");
							Helper.driver.findElement(By.id("create-alert")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys("Ancera");
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("div[class='form-control form-control-inv']")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector(".d-inline-block ")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("list-title_apply")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("alert-info-input")).sendKeys(objModel.AlertInfo);
							Helper.driver.findElement(By.id("alert-desc-input")).sendKeys(objModel.AlertDesc);
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("btn-next")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(objModel.DataSource);
							Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(Keys.ENTER);

							if (Helper.driver.findElements(By.cssSelector("#reportId input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(objModel.Report);
								Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(Keys.ENTER);
							}

							if (Helper.driver.findElements(By.cssSelector("#fieldId input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(objModel.AlertVariable);
								Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(Keys.ENTER);
							}

							if (Helper.driver.findElements(By.cssSelector("#alertModesId input")).size() != 0 ) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(objModel.AlertMode);
								Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(Keys.ENTER);	  //threshold
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.cssSelector("#thresholdConditionId input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(objModel.Condition);
								Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("minId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("minId")).sendKeys(objModel.minValue);
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("maxId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("maxId")).sendKeys(objModel.maxValue);
							}

							Thread.sleep(1000);
							System.out.println("adasd");
							if (Helper.driver.findElements(By.id("aggregateModeId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#aggregateModeId input")).sendKeys(objModel.AggregateMode);
								Helper.driver.findElement(By.cssSelector("#aggregateModeId input")).sendKeys(Keys.ENTER);
							}

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("aggregeateConditionId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(objModel.Condition);
								Helper.driver.findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(Keys.ENTER);
							}
							
							
							

							Thread.sleep(1000);
							if (Helper.driver.findElements(By.id("alert-aggregate-days-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-aggregate-days-input")).sendKeys(objModel.Text);
							}

							if (Helper.driver.findElements(By.id("absenceDelayFactorId-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("absenceDelayFactorId-input")).sendKeys(objModel.Deviation);
							}
							
	
	
							if (Helper.driver.findElements(By.id("alert-deviation-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-deviation-input")).sendKeys(objModel.Deviation);
								
							}

							if (Helper.driver.findElements(By.id("notifyEveryId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#notifyEveryId input")).sendKeys(objModel.Notify);
								Helper.driver.findElement(By.cssSelector("#notifyEveryId input")).sendKeys(Keys.ENTER);
							}

							if (Helper.driver.findElements(By.id("alert-custom-days-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-custom-days-input")).sendKeys(objModel.customDays);
							}

							if (Helper.driver.findElements(By.id("alert-duration-input")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-duration-input")).sendKeys(objModel.Text);
							}					
							
				
							if (Helper.driver.findElements(By.id("absenceDelayFactorId")).size() != 0) {
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#absenceDelayFactorId input")).sendKeys(objModel.Days);
								Helper.driver.findElement(By.cssSelector("#absenceDelayFactorId input")).sendKeys(Keys.ENTER);

							}

			Thread.sleep(1000);
							Helper.driver.findElement(By.id("btn-next")).click();
							Thread.sleep(1000);
							Helper.driver.findElement(By.id("btn-finish")).click();
							Thread.sleep(1000);
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
							Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "New alert configuration created.");
							Thread.sleep(1000);
						}















						/*			


					    List<WebElement> allOptions = Helper.driver.findElements(By.cssSelector("td:nth-child(6)"));

					//    System.out.println(allOptions);

					    for ( WebElement we: allOptions) { 
					    	//System.out.println(we.getText());
					        if ( we.getText().contains( objModel.AlertDesc ) ) {
					        	System.out.println(we.getText());
					        	break;
					       }
					        else {
					        	Helper.driver.findElement(By.id("create-alert")).click();
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys("Ancera");
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("div[class='form-control form-control-inv']")).click();
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector(".d-inline-block ")).click();
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("list-title_apply")).click();
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("alert-info-input")).sendKeys(objModel.AlertInfo);
								Helper.driver.findElement(By.id("alert-desc-input")).sendKeys(objModel.AlertDesc);
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("btn-next")).click();
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(objModel.DataSource);
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(objModel.Report);
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(Keys.ENTER);

								Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(objModel.AlertVariable);
								Thread.sleep(1000);
								Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								/////////////////////////////////////////////////////////////////////////////////////////////////
								Thread.sleep(1000);
								if (Helper.driver.findElements(By.cssSelector("#alertModesId input")).size() != 0 ) {
									Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(objModel.AlertMode);
									Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(Keys.ENTER);	  //threshold
								}

								Thread.sleep(1000);
								if (Helper.driver.findElements(By.cssSelector("#thresholdConditionId input")).size() != 0) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(objModel.Condition);
									Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(Keys.ENTER);
								}

								Thread.sleep(1000);
								if (Helper.driver.findElements(By.id("minId")).size() != 0) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.id("minId")).sendKeys(objModel.minValue);
								}

								Thread.sleep(1000);
								if (Helper.driver.findElements(By.id("maxId")).size() != 0) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.id("maxId")).sendKeys(objModel.maxValue);
								}

								Thread.sleep(1000);
								System.out.println("adasd");
								if (Helper.driver.findElements(By.id("aggregateModeId")).size() != 0) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#aggregateModeId input")).sendKeys(objModel.AggregateMode);
								}

								Thread.sleep(1000);
								if (Helper.driver.findElements(By.id("aggregeateConditionId")).size() != 0) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(objModel.Condition);
								}

								Thread.sleep(1000);
								if (Helper.driver.findElements(By.id("alert-aggregate-days-input")).size() != 0) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.id("alert-aggregate-days-input")).sendKeys(objModel.Text);
								}

								Thread.sleep(1000);
								if (Helper.driver.findElements(By.id("alert-deviation-input")).size() != 0) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.id("alert-deviation-input")).sendKeys(objModel.Deviation);
								}

								Thread.sleep(1000);
								Helper.driver.findElement(By.id("btn-next")).click();
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("btn-finish")).click();
								Thread.sleep(1000);
								Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
								Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "New alert configuration created.");
								Thread.sleep(1000);
					        }

					    }








			/*			


						for (int i=2; i <=Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
							if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).size() != 0) {
								//System.out.println(Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).getText());
								if (!Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(6)")).getText().equals(objModel.AlertDesc)) {
									Helper.driver.findElement(By.id("create-alert")).click();
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys("Ancera");
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#organizationId input")).sendKeys(Keys.ENTER);
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("div[class='form-control form-control-inv']")).click();
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector(".d-inline-block ")).click();
									Thread.sleep(1000);
									Helper.driver.findElement(By.id("list-title_apply")).click();
									Thread.sleep(1000);
									Helper.driver.findElement(By.id("alert-info-input")).sendKeys(objModel.AlertInfo);
									Helper.driver.findElement(By.id("alert-desc-input")).sendKeys(objModel.AlertDesc);
									Thread.sleep(1000);
									Helper.driver.findElement(By.id("btn-next")).click();
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(objModel.DataSource);
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#dataSourcesid input")).sendKeys(Keys.ENTER);
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(objModel.Report);
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#reportId input")).sendKeys(Keys.ENTER);

									Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(objModel.AlertVariable);
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#fieldId input")).sendKeys(Keys.ENTER);
									Thread.sleep(1000);
									/////////////////////////////////////////////////////////////////////////////////////////////////
									Thread.sleep(1000);
									if (Helper.driver.findElements(By.cssSelector("#alertModesId input")).size() != 0 ) {
										Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(objModel.AlertMode);
										Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(Keys.ENTER);	  //threshold
									}

									Thread.sleep(1000);
									if (Helper.driver.findElements(By.cssSelector("#thresholdConditionId input")).size() != 0) {
										Thread.sleep(1000);
										Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(objModel.Condition);
										Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(Keys.ENTER);
									}

									if (Helper.driver.findElements(By.id("minId")).size() != 0) {
										Thread.sleep(1000);
										Helper.driver.findElement(By.id("minId")).sendKeys(objModel.minValue);
									}

									if (Helper.driver.findElements(By.id("maxId")).size() != 0) {
										Thread.sleep(1000);
										Helper.driver.findElement(By.id("maxId")).sendKeys(objModel.maxValue);
									}

									if (Helper.driver.findElements(By.id("aggregateModeId")).size() != 0) {
										Thread.sleep(1000);
										Helper.driver.findElement(By.cssSelector("#aggregateModeId input")).sendKeys(objModel.AggregateMode);
									}

									if (Helper.driver.findElements(By.id("aggregeateConditionId")).size() != 0) {
										Thread.sleep(1000);
										Helper.driver.findElement(By.cssSelector("#aggregeateConditionId input")).sendKeys(objModel.Condition);
									}

									if (Helper.driver.findElements(By.id("alert-aggregate-days-input")).size() != 0) {
										Thread.sleep(1000);
										Helper.driver.findElement(By.id("alert-aggregate-days-input")).sendKeys(objModel.Text);
									}


									if (Helper.driver.findElements(By.id("alert-deviation-input")).size() != 0) {
										Thread.sleep(1000);
										Helper.driver.findElement(By.id("alert-deviation-input")).sendKeys(objModel.Deviation);
									}




//									if (objModel.isAlertMode) {
//										Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(objModel.AlertMode);
//										Helper.driver.findElement(By.cssSelector("#alertModesId input")).sendKeys(Keys.ENTER);
//
//										Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(objModel.Condition);
//										Helper.driver.findElement(By.cssSelector("#thresholdConditionId input")).sendKeys(Keys.ENTER);
//
//										if (objModel.isBetween) {
//											Helper.driver.findElement(By.id("minId")).sendKeys(objModel.minValue);
//											Helper.driver.findElement(By.id("maxId")).sendKeys(objModel.maxValue);
//										}	
//
//										if (!objModel.isBetween) {
//											Helper.driver.findElement(By.id("minId")).sendKeys(objModel.Text);
//										}	
//			
//									}		

//									if (!objModel.isAlertMode) {
//										Helper.driver.findElement(By.cssSelector("#stringBasedConditionId input")).sendKeys(objModel.Condition);
//										Helper.driver.findElement(By.cssSelector("#stringBasedConditionId input")).sendKeys(Keys.ENTER);
//										Thread.sleep(1000);
//										Helper.driver.findElement(By.id("alert-Text-input")).sendKeys(objModel.Text);
//									}

									Helper.driver.findElement(By.id("btn-next")).click();
									Thread.sleep(1000);
								//	Helper.driver.findElement(By.id("btn-finish")).click();
									Thread.sleep(1000);
									Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
									Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "New alert configuration created.");
									Thread.sleep(1000);


								}   
							}
						}
						 */

						Test_Variables.test.pass("User was able to create alert successfully");
						Test_Variables.results.createNode("User was able to create alert successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Configuration", Constants.AlertManagementReportPath));
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.AlertManagementReportPath, null);
					}catch(AssertionError er) {
						Test_Variables.test.fail("User failed to create alert");
						Test_Variables.results.createNode("User failed to create alert");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, new Exception(er));
					}catch(Exception ex){
						Test_Variables.test.fail("User failed to create alert");
						Test_Variables.results.createNode("User failed to create alert");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.AlertManagementReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}





	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}
