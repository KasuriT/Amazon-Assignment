package Test.Ancera;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
		Helper.driver.findElement(By.xpath(Test_Elements.orgSearch)).clear();
		Helper.driver.findElement(By.xpath(Test_Elements.orgSearch)).sendKeys(Test_Variables.lstOrganizationCreate.get(0));
	//	Helper.driver.findElement(By.xpath(Test_Elements.orgSearch)).sendKeys("Organization0447");
		Helper.driver.findElement(By.xpath(Test_Elements.orgSearch)).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.orgExpandAnceraTab)));
		Helper.driver.findElement(By.xpath(Test_Elements.orgExpandAnceraTab)).click();
		Thread.sleep(2000);
		
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

}
