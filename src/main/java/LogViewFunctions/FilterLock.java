package LogViewFunctions;

import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.LockFilter;
import static PageObjects.BasePage.ResetFilters;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.UnlockFilter;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.gherkin.model.Scenario;
import Config.BaseTest;

import static MiscFunctions.Methods.*;

import PageObjects.BasePage;


public class FilterLock {

    @Test(enabled = true)
    public static void Lock(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
        BaseTest driver = new BaseTest();

//        if (size(By.cssSelector("#"+tablename+" #save-filters.d-none")) != 0) {    //if lock is already applied unlock it
//                click(By.id("#"+tablename+" #"+UnlockFilter));
//                click(By.id("#"+tablename+" #"+ResetFilters));
//                waitElementInvisible(loading_cursor);
//        }


        int totalNumberofColumns = size(By.cssSelector("#" + tablename + " th .log-header .mb-0")) + skipColumns;  //get number of columns in table and skip the audit,checkbox and action column if exists

        for (int i = 1; i <= totalNumberofColumns; i++) {
            try {
                SoftAssert softAssert = new SoftAssert();

                String recordBefore = getText(By.cssSelector("#" + tablename + " #" + ResultsCount)); //get total count of records in table

                if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header__filter-icon")) != 0) {  //check if filter icon is applied with the column

                    WebElement column = driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));
                    WebElement columnName = driver.getDriver().findElement(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") .log-header .mb-0"));

                    test = extent.createTest("AN-Lock-" + i + ": Verify user can apply filter on " + columnName.getText() + " column");
                    steps = test.createNode(Scenario.class, Steps);

                    if (size(By.cssSelector("#remove-filters.d-none")) == 0) {   //if lock is already applied on filter; unlock it
                        click(By.id(UnlockFilter));
                        click(By.id(ResetFilters));
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(2000);
                    }

                    WebElement filter_scroll = column;
                    ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); //scroll to filter
                    getScreenshot();

                    click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));  //click on filter icon to expand it
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);

                    if (size(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.footerCount)) != 0) {   //check if 'Showing x-x results' if displayed
                        if (getText(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.footerCount)).equals("Showing 1 - 1 Results")) {  //check if there is more than 1 checkbox to verify if filter functionality is working
                            test.skip("Values not enough to test lock filter functionality");
                            driver.saveResult(ITestResult.SKIP, null);
                            click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));
                        } else {
                            String getFilterItemName = getText(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") li:nth-child(3) label"));   //get name of filter item to be selected
                            click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") li:nth-child(3) label"));  //click on checkbox
                            Thread.sleep(500);
                            steps.createNode("1. Select any filter and click on apply filter button");

                            click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterApplyButton));  //click on apply button
                            waitElementInvisible(loading_cursor);
                            steps.createNode("2. Click on lock button");
                            click(By.cssSelector("#" + tablename + " #" + LockFilter));   //click on lock icon
                            waitElementInvisible(loading_cursor);
                            getScreenshot();
                            Thread.sleep(1000);

                            String recordsafterfilter = getText(By.cssSelector("#" + tablename + " #" + ResultsCount));  //get records after applying filter
                            softAssert.assertTrue(!recordsafterfilter.equals(0), "Result found count cannot be zero");
                            softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");

                            ///////////////////////


                            if (size(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle.ng-star-inserted")) == 1) {
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle.ng-star-inserted"));  //get list of items in table
                           //     System.out.println("1");
                                for (int x = 0; x < items.size(); x++) {
                                    softAssert.assertEquals(items.get(x).getText(), getFilterItemName.trim());
                                }
                            }

                            else if (size(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle")) == 1) {
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle"));  //get list of items in table
                           //     System.out.println("2");
                                for (int x = 0; x < items.size(); x++) {
                                    softAssert.assertEquals(items.get(x).getText(), getFilterItemName.trim());
                                }
                            }

                            else {
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle"));  //get list of items in table
                          //      System.out.println("3");
                                for (int x = 0; x < items.size(); x++) {
                                    if (!columnName.equals("W2 Cell Count")) {
                                        softAssert.assertEquals(items.get(x).getText(), getFilterItemName.trim());
                                    }
                                }
                            }


                            /*
                            if(size(By.id("Program Management")) != 0) {

                             //   if (getFilterItemName.equals("Vaccine Name") || getFilterItemName.equals("Vaccine Supplier") || getFilterItemName.equals("Feed Program Name"))
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label.vertical-align-middle"));  //get list of items in table

                                for (int x = 0; x < items.size(); x++) {
                                        softAssert.assertEquals(items.get(x).getText(), getFilterItemName.trim());
                                }
                            }

                            else {
                                List<WebElement> items = driver.getDriver().findElements(By.cssSelector("#" + tablename + " tr td:nth-child(" + i + ") label"));  //get list of items in table

                                for (int x = 0; x < items.size(); x++) {
                                    if (!getFilterItemName.equals("W2 Cell Count")) {
                                        softAssert.assertEquals(items.get(x).getText(), getFilterItemName.trim());
                                    }
                                }
                            }
                            */
                            ///////////////////////////

                            steps.createNode("3. Close " + name + " screen");
                            steps.createNode("4. Reopen " + name + " screen");

                            driver.getDriver().navigate().refresh();
                            //driver.getDriver().navigate().back();
                            //driver.getDriver().navigate().forward();
                            waitElementInvisible(loading_cursor);
                            waitElementVisible(By.cssSelector("#" + tablename + " #" + ResultsCount));
                            Thread.sleep(3000);
                            steps.createNode("5. Verify lock filter remains applied");
                            softAssert.assertEquals(getText(By.cssSelector("#" + tablename + " #" + ResultsCount)), recordsafterfilter, "Lock functionality failed");  //verify records remain save after refreshing the page
                            Thread.sleep(1000);
                            getScreenshot();
                            click(By.cssSelector("#" + tablename + " #" + UnlockFilter));  //unlock filter
                            waitElementInvisible(loading_cursor);
                            click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterClearButton)); //clear applied filter
                            waitElementInvisible(loading_cursor);
                        }
                    } else {
                        test.skip("Heirarchy filter cannot be tested with this method");    //there are few filter having heirarchy instead of checkbox; to skip them we used this condition
                        driver.saveResult(ITestResult.SKIP, null);
                        click(By.cssSelector("#" + tablename + " th:nth-child(" + i + ") " + BasePage.filterIcon));
                    }

                    softAssert.assertAll();
                    test.pass("Lock functionality verified successfully");
                    driver.saveResult(ITestResult.SUCCESS, null);
                }
            } catch (AssertionError er) {
                test.fail("Column failed to Lock");
                driver.saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Column failed to Lock");
                driver.saveResult(ITestResult.FAILURE, new Exception(ex));
            }
        }
    }
}
