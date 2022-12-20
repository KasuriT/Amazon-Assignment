package Models;

import static MiscFunctions.Constants.url_user;
import static MiscFunctions.Helper.driver;
import static MiscFunctions.Helper.scroll;
import static MiscFunctions.Helper.waitElementInvisible;
import static MiscFunctions.Helper.waitElementVisible;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import PageObjects.UserManagementPage;

public class UserManagementModel {	
	
	public static String createUserEmail = "ancera.test.user101@gmail.com";
	public static String createUserPassword = "ancera123";
	public static String createUserSecurityEmail = "ancera.test.user100@gmail.com";

	
	public static void openEditUserPopup(String emailAddress) throws InterruptedException, IOException {
		driver.get(url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(UserManagementPage.usercreateButton);
		Thread.sleep(3000);
		driver.findElement(By.id("userEmail_show-filter")).click();
		waitElementInvisible(loading_cursor);
		driver.findElement(By.id("userEmail_search-input")).sendKeys(emailAddress);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("th:nth-child(4) li:nth-child(1) label")).click();					  
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		driver.findElement(By.id("userEmail_apply")).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		scroll(By.id("edit-user-1"));
		Thread.sleep(1000); 
		try {
		driver.findElement(By.cssSelector("#edit-user-1 img")).click();
		}
		catch (StaleElementReferenceException ex) {
			driver.findElement(By.cssSelector("#edit-user-1 img")).click();
		}
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);

	}
}
