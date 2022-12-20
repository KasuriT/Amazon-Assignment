package LogViewFunctions;

import static MiscFunctions.Constants.wait;
import static MiscFunctions.Helper.driver;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FieldLevelAccess {

	public static void fieldLevelReset() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.id("edit-field-access")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.id("btn-reset")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("btn-save")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
	}
	
}
