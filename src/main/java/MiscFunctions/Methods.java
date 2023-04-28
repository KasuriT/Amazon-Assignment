package MiscFunctions;

import static MiscFunctions.ExtentVariables.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Config.BaseTest;
import PageObjects.BasePage;

public class Methods {

	BaseTest drive = new BaseTest();
	public WebDriverWait wait = new WebDriverWait(drive.getDriver(), Duration.ofSeconds(75, 1));
	
	public static void click(By locator) {
		waitElementClickable(locator);
		BaseTest drive = new BaseTest();
		drive.getDriver().findElement(locator).click();
		waitElementInvisible(BasePage.loading_cursor);
		ExtentVariables.test.log(Status.INFO, "Clicking on "+locator);
	}

	public static void type(By locator, String text) {
		waitElementClickable(locator);
		BaseTest drive = new BaseTest();
		drive.getDriver().findElement(locator).sendKeys(text);
		waitElementInvisible(BasePage.loading_cursor);
		ExtentVariables.test.log(Status.INFO, "Typing "+text+" in "+locator);
	}

	public static void enterKey(By locator) {
		waitElementClickable(locator);
		BaseTest drive = new BaseTest();
		drive.getDriver().findElement(locator).sendKeys(Keys.ENTER);
		ExtentVariables.test.log(Status.INFO, "Press enter key in "+locator);
	}

	public static String getText(By locator) {
		waitElementVisible(locator);
		BaseTest drive = new BaseTest();
		return drive.getDriver().findElement(locator).getText();
	}

	public static String getAttribute(By locator) {
		BaseTest drive = new BaseTest();
		return drive.getDriver().findElement(locator).getAttribute("value");
	}

	public static void clear(By locator) {
		waitElementClickable(locator);
		BaseTest drive = new BaseTest();
		drive.getDriver().findElement(locator).clear();
	}

	public static int size(By locator) {
		BaseTest drive = new BaseTest();
		int elementSize = drive.getDriver().findElements(locator).size();
		return elementSize;
	}

	public static void waitElementVisible(By locator) {
		Methods methods = new Methods();
		methods.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitElementInvisible(String locator) {
		Methods methods = new Methods();
		methods.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
	}

	public static void waitElementClickable(By locator) {
		Methods methods = new Methods();
		methods.wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
		

	public static void scroll(By locator) {
		BaseTest drive = new BaseTest();
		WebElement scroll = drive.getDriver().findElement(locator);
		((JavascriptExecutor)drive.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll);
	}
	
	public static void hover(By locator) {
		BaseTest drive = new BaseTest();
		WebElement hover = drive.getDriver().findElement(locator);
		Actions action = new Actions(drive.getDriver());
		action.moveToElement(hover).perform();
	}
	
	public static void getScreenshot() throws IOException {
		BaseTest base = new BaseTest();
		test.addScreenCaptureFromPath(base.get_Screenshot());
	}
	
	
}
