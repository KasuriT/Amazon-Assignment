package LogViewFunctions;

import java.io.IOException;

import org.openqa.selenium.By;
import PageObjects.BasePage;
import static MiscFunctions.Methods.*;

public class FieldLevelAccess {

	public static void fieldLevelReset() throws InterruptedException, IOException {
		waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);
		click(By.id(BasePage.FieldAccess));
		waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);
		click(BasePage.popupResetButton);
		Thread.sleep(1000);
		click(BasePage.popupSaveButton);
		waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(1000);
	}


}
