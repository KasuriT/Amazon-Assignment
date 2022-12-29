package Test.Ancera.Login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.RetryFailedCases;
import PageObjects.LoginPage;

import static PageObjects.BasePage.*;
import static PageObjects.ForgotPasswordPage.*;
import static MiscFunctions.ExtentVariables.*;
import static Models.ForgotPasswordModel.*;
import static MiscFunctions.Methods.*;


public class ForgotPassword extends BaseTest{

	@BeforeTest
	public void extent() throws MalformedURLException {
		spark = new ExtentSparkReporter("target/Reports/Forgot_Password"+DateUtil.date+".html");
		spark.config().setReportName("Forgot Password Test Report"); 
	//	config();
	}

	@Test(enabled=true, priority= 1)
	public void SendEmailLink() throws InterruptedException, IOException {
		waitElementClickable(By.id("forgot-password-1"));
		getDriver().findElement(By.id("forgot-password-1")).click();

		for (int i = 0; i<lstFpEmail.size(); i++) {
			try {
				test = extent.createTest(lstFpTestCase.get(i));
				waitElementVisible(LoginPage.loginEmail);
				Thread.sleep(1000);
				clear(LoginPage.loginEmail);
				type(LoginPage.loginEmail, lstFpEmail.get(i));
				Thread.sleep(1000);
				getScreenshot();
				click(forgotPasswordButton);
				
				waitElementVisible(alertMessage);
				String actual = getDriver().findElement(alertMessage).getText();
				String expected = lstFpAlertMessages.get(i) ;

				Assert.assertEquals(actual, expected); 
				test.pass(lstFpPassMessage.get(i));
				getScreenshot();
				saveResult(ITestResult.SUCCESS, null);
			}
			catch(AssertionError er) {
				test.fail(lstFpFailMessage.get(i));
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(lstFpFailMessage.get(i));
				saveResult(ITestResult.FAILURE, ex);
			}
			Thread.sleep(1000);
			getDriver().findElement(alertClose).click();
		}
	}

	@Test(enabled= true, priority= 2,  retryAnalyzer = RetryFailedCases.class)
	public void VerifyEmailReceived() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FP-03: Verify user receives reset password link");

			getDriver().get(Constants.url_GmailSignin);			
			type(By.xpath(gmailEmail), forgotPassword_email);
			enterKey(By.xpath(gmailEmail));
			
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
	
			waitElementVisible(By.xpath("//*[@class='yW']/span"));
			Thread.sleep(2000);
			List<WebElement> a = getDriver().findElements(By.xpath("//*[@class='yW']/span"));
			for(int i=0;i<a.size();i++){
				if(a.get(i).getText().equals("ancera.org.dev") || a.get(i).getText().equals("support")){  

					Thread.sleep(1000);
					a.get(i).click();
				}
			}

			Thread.sleep(2000);  
			Assert.assertTrue(getDriver().findElement(By.xpath(("//*[text()='Reset Password']"))).isDisplayed());
			getDriver().findElement(By.xpath(("//*[text()='Reset Password']"))).click();

			test.pass("Email with reset password link received successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Did not receive an email");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Did not receive an email");
			saveResult(ITestResult.FAILURE, ex);
		}
		
		Thread.sleep(1000);
		getDriver().findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();
			
		Thread.sleep(2000); 
		String currentTabHandle = getDriver().getWindowHandle();
		String newTabHandle = getDriver().getWindowHandles()
				.stream()
				.filter(handle -> !handle.equals(currentTabHandle ))
				.findFirst()
				.get();
		getDriver().switchTo().window(newTabHandle);
		Thread.sleep(2000);  

	}


	@Test(enabled=true, priority= 3)
	public void VerifyLogin() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FP-04: Verify user can reset password and login with new credentials");

			waitElementVisible(By.id("passwordId"));
			Thread.sleep(1000);
			getDriver().findElement(By.id(("passwordId"))).sendKeys(forgotPassword_resetPassword);
			getDriver().findElement(By.id(("rePassordId"))).sendKeys(forgotPassword_resetPassword);
			getDriver().findElement(By.xpath(("/html/body/app-root/div/app-reset-password/div/div[3]/form/button"))).click(); 
			Thread.sleep(1500);

			if (getDriver().findElements(alertMessage).size() != 0) {
				click(alertClose);
			}

			waitElementVisible(By.id("email"));
			Thread.sleep(2000);
			getDriver().findElement(By.id("email")).sendKeys(forgotPassword_email);
			getDriver().findElement(By.id("pwd")).clear();
			getDriver().findElement(By.id("pwd")).sendKeys(forgotPassword_resetPassword);
			getScreenshot();
			getDriver().findElement(By.id("btn-sign-in")).click();

			Thread.sleep(1500);
			if (getDriver().findElements(By.cssSelector("div button.footer__btn-main")).size() != 0) {
				getDriver().findElement(By.cssSelector("div button.footer__btn-main")).click();
			}
			
			waitElementVisible(By.id("Ancera Intelligence Engine"));
			Thread.sleep(2000);
			Assert.assertTrue(getDriver().findElement(By.id("open-profile")).isDisplayed());
			test.pass("User successfully logged into the account with new credentials");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}		catch(AssertionError er) {
			test.fail("User not able to log into the account with new credentials");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User not able to log into the account with new credentials");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

//	@AfterTest
//	public static void endreport() {
//		extent.flush();
//		getDriver().quit();
//	}
}

