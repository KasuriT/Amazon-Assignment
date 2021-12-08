package Test.Ancera;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;

public class Test_Functions {

	public static void userSearch() throws InterruptedException {
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userSearch)));
		Thread.sleep(500);
		WebElement search = Helper.driver.findElement(By.xpath(Test_Elements.userSearch));
		search.clear();
		search.sendKeys(Test_Variables.lstUserCreate.get(0));
		Helper.driver.findElement(By.id("tag-0")).click();
		Thread.sleep(1000);


	}

	public static void OrgSearch() throws InterruptedException {

		Thread.sleep(2000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgSearch)));
		Helper.driver.findElement(By.id("organSearchId")).clear();
		Helper.driver.findElement(By.id("organSearchId")).sendKeys(Test_Variables.lstOrganizationCreate.get(0));
		//	Helper.driver.findElement(By.id("organSearchId")).sendKeys("Test Organization0447");
		Helper.driver.findElement(By.id("organSearchId")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgExpandAnceraTab)));
		Helper.driver.findElement(By.xpath(Test_Elements.orgExpandAnceraTab)).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(3000);

	}



	public static void AccessFind() throws InterruptedException, IOException
	{

		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		for (int i=2; i<=80; i++) {

			int j = i-1;

			String actualXpath = Test_Elements.accessBeforeXpath+j+Test_Elements.accessAfterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

			Thread.sleep(1000);

			if (element.getText().equals(Test_Variables.lstAccessCreate.get(0))) {
				//		if (element.getText().equals("Administrator5949")) {
				Helper.driver.findElement(By.xpath(Test_Elements.accessBeforeXpath+j+Test_Elements.accessAfterXpath1)).click(); 
				break;
			}}}


	public static void fieldLevelReset() throws InterruptedException, IOException {
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("edit-field-access")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-reset")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-save")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
	}



	public static void login() throws InterruptedException, IOException {
		Helper.driver.findElement(By.id("logout")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("email")).clear();
		Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.login_email);
		Helper.driver.findElement(By.id("pwd")).clear();
		Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.login_password);
		Helper.driver.findElement(By.id("btn-sign-in")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	public static void getUserAccess() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		for(int i=1; i<=200; i++) {
			if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4)  label")).getText().equals(Test_Variables.login_email)) {
				Helper.driver.findElement(By.id("edit-user-"+i)).click();
				break;
			}
		}
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
	}



	public static boolean isFileDownload(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName)) {
				// File has been found, it can now be deleted:
				dirContents[i].delete();
				return true;
			}
		}
		return false;
	}


	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag=true;
		}
		return flag;
	}
	
	
	public void Navigate(String url, String id, String expectedid) throws InterruptedException, IOException {

		try{

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(url);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id(id)).getText();
			String expected = expectedid;

			Assert.assertEquals(actual, expected); 
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Flock Registration report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Flock Registration report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
