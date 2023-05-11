package LogViewFunctions;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.getScreenshot;

public class VerifyColumnNames {

    public static void VerifyAllColumns(String tableName, String[] columnNamesExpected) throws IOException {
        try {
            BaseTest driver = new BaseTest();
            SoftAssert softAssert = new SoftAssert();

            List<WebElement> headerCells = driver.getDriver().findElements(By.cssSelector("#"+tableName+" th"));

            List<String> columnNames = new ArrayList<String>();

            for (WebElement headerCell : headerCells) {
                columnNames.add(headerCell.getText());
            }

            System.out.println(columnNames);

            for (int i = 0; i < columnNamesExpected.length; i++) {
                if (!columnNamesExpected[i].equals("")) {
                    test = extent.createTest("AN-VerifyColumns: Verify that " + columnNamesExpected[i] + " column is displayed in the table");
                    System.out.println(columnNames.get(i) + " ---> " + columnNamesExpected[i]);
                    softAssert.assertEquals(columnNames.get(i), columnNamesExpected[i]);
                }
                softAssert.assertAll();
                test.pass("Columns are displayed in the table successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            }


        } catch (AssertionError er) {
            test.fail("Columns are not displayed in the table");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Columns are not displayed in the table");
            saveResult(ITestResult.FAILURE, ex);
        }
    }
}
