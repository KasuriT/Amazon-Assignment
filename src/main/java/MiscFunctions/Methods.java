package MiscFunctions;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Config.BaseTest;
import PageObjects.BasePage;

import static MiscFunctions.ExtentVariables.test;

public class Methods {

    BaseTest drive = new BaseTest();
    public WebDriverWait wait = new WebDriverWait(drive.getDriver(), Duration.ofSeconds(120, 1));

    public static void click(By locator) {
            waitElementClickable(locator);
            BaseTest drive = new BaseTest();
            drive.getDriver().findElement(locator).click();
            waitElementInvisible(BasePage.loading_cursor);
    }


    public static void type(By locator, String text) {
        waitElementClickable(locator);
        BaseTest drive = new BaseTest();
        drive.getDriver().findElement(locator).sendKeys(text);
        waitElementInvisible(BasePage.loading_cursor);
    }

    public static String getText(By locator) {
        waitElementVisible(locator);
        BaseTest drive = new BaseTest();
        return drive.getDriver().findElement(locator).getText();
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
    public static void getScreenshot() throws IOException {
        BaseTest base = new BaseTest();
        //test.addScreenCaptureFromPath(base.get_Screenshot());
    }

}
