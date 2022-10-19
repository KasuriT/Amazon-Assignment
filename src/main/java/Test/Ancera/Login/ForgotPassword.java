package Test.Ancera.Login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.Constants;
import Test.Ancera.RetryFailedCases;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Constants.*;



public class ForgotPassword {

//	ConfigureLogin log = new ConfigureLogin();
	@BeforeTest
	public void extent() throws MalformedURLException {
		spark = new ExtentSparkReporter("target/Reports/Forgot_Password"+date+".html");
		spark.config().setReportName("Forgot Password Test Report"); 
		config();
	}

	@Test(enabled=true, priority= 1)
	public void SendEmailLink() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("forgot-password-1")));
		driver.findElement(By.id("forgot-password-1")).click();

		for (int i = 0; i<lstFpEmail.size(); i++) {
			try {
				test = extent.createTest(lstFpTestCase.get(i), lstFpTestCaseDescription.get(i));
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url "+url_login);
				preconditions.createNode("2. User should land on login screen");

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
				Thread.sleep(1000);
				clear(loginEmail);
				type(loginEmail, lstFpEmail.get(i));
				steps.createNode(lstFpStep1.get(i));
				Thread.sleep(1000);

				steps.createNode("3. Click on submit button");
				test.addScreenCaptureFromPath(getScreenshot("Forgot Password", ForgotPasswordReportPath));
				click(forgotPasswordButton);
				
				waitElementVisible(alertMessage);
				String actual = driver.findElement(alertMessage).getText();
				String expected = lstFpAlertMessages.get(i) ;

				Assert.assertEquals(actual, expected); 
				test.pass(lstFpPassMessage.get(i));
				results.createNode(lstFpPassMessage.get(i));
				test.addScreenCaptureFromPath(getScreenshot("Forgot Password", ForgotPasswordReportPath));
				saveResultNew(ITestResult.SUCCESS, ForgotPasswordReportPath, null);
			}
			catch(AssertionError er) {
				test.fail(lstFpFailMessage.get(i));
				results.createNode(lstFpFailMessage.get(i));
				saveResultNew(ITestResult.FAILURE, ForgotPasswordReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(lstFpFailMessage.get(i));
				results.createNode(lstFpFailMessage.get(i));
				saveResultNew(ITestResult.FAILURE, ForgotPasswordReportPath, ex);
			}
			Thread.sleep(1000);
			driver.findElement(alertClose).click();
		}
	}

	@Test(enabled= true, priority= 2,  retryAnalyzer = RetryFailedCases.class)
	public void VerifyEmailReceived() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FP-03: Verify user receives reset password link", "This test case will check whether user reveives an email to reset password");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url "+url_login+"; user should land on login screen");
			preconditions.createNode("2. Click on Forgot Password button from login screen; User navigates to forgot password screen");
			preconditions.createNode("3. Enter valid email address for which account already exist in Email Address field");
			preconditions.createNode("4. Click on Submit button; user receives an alert message to check email for instructons");
			steps.createNode("1. Sign in to registered email account to verify that the email is recieved or not");
			
			driver.get(Constants.url_GmailSignin);
			waitElementClickable(By.xpath(gmailEmail));
			Thread.sleep(5000);
			
			type(By.xpath(gmailEmail), forgotPassword_email);
			enterKey(By.xpath(gmailEmail));
			
			waitElementClickable(By.xpath(gmailPassword));
			Thread.sleep(2000);
			type(By.xpath(gmailPassword), forgotPassword_password);
			enterKey(By.xpath(gmailPassword));
			Thread.sleep(3000);
			
			if (size(gmailSecurityCheck) != 0) { 
				click(gmailSecurityCheck);
				Thread.sleep(3000);
				type(gmailSecurityEmail, forgotPasswordSecurityEmail);
				enterKey(gmailSecurityEmail);
				
				if (size(gmailNotNow) != 0) {
					click(gmailNotNow);
				}	
			}
	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
			Thread.sleep(2000);
			List<WebElement> a = driver.findElements(By.xpath("//*[@class='yW']/span"));
			for(int i=0;i<a.size();i++){
				if(a.get(i).getText().equals("ancera.org.dev") || a.get(i).getText().equals("support")){  

					Thread.sleep(1000);
					a.get(i).click();
				}
			}

			Thread.sleep(2000);  
			Assert.assertTrue(driver.findElement(By.xpath(("//*[text()='Reset Password']"))).isDisplayed());
			driver.findElement(By.xpath(("//*[text()='Reset Password']"))).click();

			test.pass("Email with reset password link received successfully");
			results.createNode("User receives an email from Support team with reset password link");
			test.addScreenCaptureFromPath(getScreenshot("Forgot Password", ForgotPasswordReportPath));
			saveResultNew(ITestResult.SUCCESS, ForgotPasswordReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Did not receive an email");
			results.createNode("User did not received an email from Support team with reset password link");
			saveResultNew(ITestResult.FAILURE, ForgotPasswordReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Did not receive an email");
			results.createNode("User did not received an email from Support team with reset password link");
			saveResultNew(ITestResult.FAILURE, ForgotPasswordReportPath, ex);
		}
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();
			
		Thread.sleep(2000); 
		String currentTabHandle = driver.getWindowHandle();
		String newTabHandle = driver.getWindowHandles()
				.stream()
				.filter(handle -> !handle.equals(currentTabHandle ))
				.findFirst()
				.get();
		driver.switchTo().window(newTabHandle);
		Thread.sleep(2000);  

	}


	@Test(enabled=true, priority= 3)
	public void VerifyLogin() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FP-04: Verify user can reset password and login with new credentials", "This test case will check whether user can reset his password and login with new credentials or not");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url "+url_login+"; user should land on login screen");
			preconditions.createNode("2. Click on Forgot Password button from login screen; User navigates to forgot password screen");
			preconditions.createNode("3. Enter valid email address for which account already exist in Email Address field");
			preconditions.createNode("4. Click on Submit button; user receives an alert message to check email for instructons");
			steps.createNode("1. Sign in to registered email account and click on reset password link from received email; user navigates to screen to reset password");
			steps.createNode("2. Enter password in password field");
			steps.createNode("3. Enter same password in confirm password field");
			steps.createNode("4. Click on submit button; User recieves an alert message that password reset successfully");
			steps.createNode("5. Login with new credentials");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordId")));
			Thread.sleep(1000);
			driver.findElement(By.id(("passwordId"))).sendKeys(forgotPassword_resetPassword);
			driver.findElement(By.id(("rePassordId"))).sendKeys(forgotPassword_resetPassword);
			driver.findElement(By.xpath(("/html/body/app-root/div/app-reset-password/div/div[3]/form/button"))).click(); 
			Thread.sleep(1500);

			if (driver.findElements(alertMessage).size() != 0) {
				click(alertClose);
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			Thread.sleep(2000);
			driver.findElement(By.id("email")).sendKeys(forgotPassword_email);
			driver.findElement(By.id("pwd")).clear();
			driver.findElement(By.id("pwd")).sendKeys(forgotPassword_resetPassword);
			test.addScreenCaptureFromPath(getScreenshot("Forgot Password", ForgotPasswordReportPath));
			driver.findElement(By.id("btn-sign-in")).click();

			Thread.sleep(1500);
			if (driver.findElements(By.cssSelector("div button.footer__btn-main")).size() != 0) {
				driver.findElement(By.cssSelector("div button.footer__btn-main")).click();
			}
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ancera Intelligence Engine")));
			Thread.sleep(2000);

		//	Assert.assertEquals(driver.findElement(By.id("Ancera Intelligence Engine")).getText(), "Ancera Intelligence Engine (Testing Environment)"); 
			Assert.assertTrue(driver.findElement(By.id("open-profile")).isDisplayed());
			test.pass("User successfully logged into the account with new credentials");
			results.createNode("User successfully logged into the account with new credentials");
			test.addScreenCaptureFromPath(getScreenshot("Forgot Password", ForgotPasswordReportPath));
			saveResultNew(ITestResult.SUCCESS, ForgotPasswordReportPath, null);
		}		catch(AssertionError er) {
			test.fail("User not able to log into the account with new credentials");
			results.createNode("User not able to log into the account with new credentials");
			saveResultNew(ITestResult.FAILURE, ForgotPasswordReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User not able to log into the account with new credentials");
			results.createNode("User not able to log into the account with new credentials");
			saveResultNew(ITestResult.FAILURE, ForgotPasswordReportPath, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}

