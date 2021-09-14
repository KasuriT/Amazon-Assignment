package Test.Ancera.Login;

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

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.Helper;
import Test.Ancera.RetryFailedCases;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;


public class ForgotPassword {

	ConfigureLogin log = new ConfigureLogin();

	@BeforeTest
	public void extent() {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Forgot_Password"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Forgot Password Test Report"); 
		Helper.config();
	}

	@Test(enabled=true, priority= 1)
	public void SendEmailLink() throws InterruptedException, IOException {


		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.id("forgot-password-1")));
		Helper.driver.findElement(By.id("forgot-password-1")).click();

		for (int i = 0; i<Test_Variables.lstFpEmail.size(); i++) {
			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstFpTestCase.get(i), Test_Variables.lstFpTestCaseDescription.get(i));
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login);
				Test_Variables.preconditions.createNode("2. User should land on login screen");

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("email")).clear();
				Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.lstFpEmail.get(i));  
				Test_Variables.steps.createNode(Test_Variables.lstFpStep1.get(i));
				Thread.sleep(1000);

				Test_Variables.steps.createNode("3. Click on submit button");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Forgot Password", Constants.ForgotPasswordReportPath));
				Helper.driver.findElement(By.id("forgot-pass")).click();  

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
				String actual = Helper.driver.findElement(By.id("message")).getText();
				String expected = Test_Variables.lstFpAlertMessages.get(i) ;

				Assert.assertEquals(actual, expected); 
				Test_Variables.test.pass(Test_Variables.lstFpPassMessage.get(i));
				Test_Variables.results.createNode(Test_Variables.lstFpPassMessage.get(i));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Forgot Password", Constants.ForgotPasswordReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.ForgotPasswordReportPath, null);
			}
			catch(AssertionError er) {
				Test_Variables.test.fail(Test_Variables.lstFpFailMessage.get(i));
				Test_Variables.results.createNode(Test_Variables.lstFpFailMessage.get(i));
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ForgotPasswordReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(Test_Variables.lstFpFailMessage.get(i));
				Test_Variables.results.createNode(Test_Variables.lstFpFailMessage.get(i));
				Helper.saveResultNew(ITestResult.FAILURE, Constants.ForgotPasswordReportPath, ex);
			}
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}
	}

	@Test(enabled= true, priority= 2,  retryAnalyzer = RetryFailedCases.class)
	public void VerifyEmailReceived() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FP-03: Verify user receives reset password link", "This test case will check whether user reveives an email to reset password");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Thread.sleep(2000);
			Helper.driver.get(Constants.url_GmailSignin);   
			Thread.sleep(1500);

			Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login+"; user should land on login screen");
			Test_Variables.preconditions.createNode("2. Click on Forgot Password button from login screen; User navigates to forgot password screen");
			Test_Variables.preconditions.createNode("3. Enter valid email address for which account already exist in Email Address field");
			Test_Variables.preconditions.createNode("4. Click on Submit button; user receives an alert message to check email for instructons");
			Test_Variables.steps.createNode("1. Sign in to registered email account to verify that the email is recieved or not");

			if (Helper.driver.findElements(By.id("identifierId")).size() != 0) {
				Helper.driver.findElement(By.id("identifierId")).sendKeys(Test_Variables.forgotPassword_email);    
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);	
			}

			else if(Helper.driver.findElements(By.id("Email")).size() != 0) {

				Helper.driver.findElement(By.id("Email")).sendKeys(Test_Variables.forgotPassword_email);    
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("Email")).sendKeys(Keys.ENTER);			
			}
			
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.name("password")).sendKeys(Test_Variables.forgotPassword_password);
			Helper.driver.findElement(By.name("password")).sendKeys(Keys.ENTER);	
			
			if (Helper.driver.findElements(By.cssSelector(".vxx8jf")).size() != 0) { 
				Helper.driver.findElement(By.cssSelector(".vxx8jf")).click();
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("knowledge-preregistered-email-response")).sendKeys(Test_Variables.forgotPasswordSecurityEmail);
				Helper.driver.findElement(By.id("knowledge-preregistered-email-response")).sendKeys(Keys.ENTER);
			}
			
			
			if (Helper.driver.findElements(By.xpath(Test_Elements.gmailSecurityCheck)).size() != 0) { 
				Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityCheck)).click();
				Thread.sleep(1000);
				Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityEmail)).sendKeys(Test_Variables.forgotPasswordSecurityEmail);
				Helper.driver.findElement(By.xpath(Test_Elements.gmailSecurityEmail)).sendKeys(Keys.ENTER);
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
			Thread.sleep(2000);
			List<WebElement> a = Helper.driver.findElements(By.xpath("//*[@class='yW']/span"));
			for(int i=0;i<a.size();i++){
				if(a.get(i).getText().equals("ancera.org") || a.get(i).getText().equals("support")){  

					Thread.sleep(1000);
					a.get(i).click();
				}
			}

			Thread.sleep(2000);  
			Assert.assertTrue(Helper.driver.findElement(By.xpath(("//*[text()='Reset Password']"))).isDisplayed());
			Helper.driver.findElement(By.xpath(("//*[text()='Reset Password']"))).click();

			Test_Variables.test.pass("Email with reset password link received successfully");
			Test_Variables.results.createNode("User receives an email from Support team with reset password link");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Forgot Password", Constants.ForgotPasswordReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ForgotPasswordReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Did not receive an email");
			Test_Variables.results.createNode("User did not received an email from Support team with reset password link");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ForgotPasswordReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Did not receive an email");
			Test_Variables.results.createNode("User did not received an email from Support team with reset password link");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ForgotPasswordReportPath, ex);
		}
		
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();
			
		Thread.sleep(2000); 
		String currentTabHandle = Helper.driver.getWindowHandle();
		String newTabHandle = Helper.driver.getWindowHandles()
				.stream()
				.filter(handle -> !handle.equals(currentTabHandle ))
				.findFirst()
				.get();
		Helper.driver.switchTo().window(newTabHandle);
		Thread.sleep(2000);  

	}


	@Test(enabled=true, priority= 3)
	public void VerifyLogin() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FP-04: Verify user can reset password and login with new credentials", "This test case will check whether user can reset his password and login with new credentials or not");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url "+Constants.url_login+"; user should land on login screen");
			Test_Variables.preconditions.createNode("2. Click on Forgot Password button from login screen; User navigates to forgot password screen");
			Test_Variables.preconditions.createNode("3. Enter valid email address for which account already exist in Email Address field");
			Test_Variables.preconditions.createNode("4. Click on Submit button; user receives an alert message to check email for instructons");
			Test_Variables.steps.createNode("1. Sign in to registered email account and click on reset password link from received email; user navigates to screen to reset password");
			Test_Variables.steps.createNode("2. Enter password in password field");
			Test_Variables.steps.createNode("3. Enter same password in confirm password field");
			Test_Variables.steps.createNode("4. Click on submit button; User recieves an alert message that password reset successfully");
			Test_Variables.steps.createNode("5. Login with new credentials");

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordId")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(("passwordId"))).sendKeys(Test_Variables.forgotPassword_resetPassword);
			Helper.driver.findElement(By.id(("rePassordId"))).sendKeys(Test_Variables.forgotPassword_resetPassword);
			Helper.driver.findElement(By.xpath(("/html/body/app-root/div/app-reset-password/div/div[3]/form/button"))).click(); 
			Thread.sleep(1500);

			if (Helper.driver.findElements(By.xpath(Test_Elements.alertbox)).size() != 0) {
				Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
			}

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Thread.sleep(2000);
			Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.forgotPassword_email);
			Helper.driver.findElement(By.id("pwd")).clear();
			Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.forgotPassword_resetPassword);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Forgot Password", Constants.ForgotPasswordReportPath));
			Helper.driver.findElement(By.id("btn-sign-in")).click();

			Thread.sleep(1500);
			if (Helper.driver.findElements(By.cssSelector("div button.footer__btn-main")).size() != 0) {
				Helper.driver.findElement(By.cssSelector("div button.footer__btn-main")).click();
			}
			
			
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ancera Intelligence Engine")));
			Thread.sleep(2000);

		//	Assert.assertEquals(Helper.driver.findElement(By.id("Ancera Intelligence Engine")).getText(), "Ancera Intelligence Engine (Testing Environment)"); 
			Assert.assertTrue(Helper.driver.findElement(By.id("open-profile")).isDisplayed());
			Test_Variables.test.pass("User successfully logged into the account with new credentials");
			Test_Variables.results.createNode("User successfully logged into the account with new credentials");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Forgot Password", Constants.ForgotPasswordReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ForgotPasswordReportPath, null);
		}		catch(AssertionError er) {
			Test_Variables.test.fail("User not able to log into the account with new credentials");
			Test_Variables.results.createNode("User not able to log into the account with new credentials");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ForgotPasswordReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User not able to log into the account with new credentials");
			Test_Variables.results.createNode("User not able to log into the account with new credentials");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ForgotPasswordReportPath, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}

