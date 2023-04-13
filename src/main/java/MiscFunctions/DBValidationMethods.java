package MiscFunctions;

import MiscFunctions.DB_Config_DW;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.test;

public class DBValidationMethods extends DB_Config_DW {

    public static void viewsDataCompare(String oldView, String newView) throws SQLException, InterruptedException, IOException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the data in each row for method "+methodName);
            SoftAssert softAssert = new SoftAssert();

            String query1 = oldView;

            long startTimeQuery1 = System.currentTimeMillis();

            ResultSet rs1 = getStmt().executeQuery(query1);

            long endTimeQuery1 = System.currentTimeMillis();
            long elapsedTimeQuery1 = endTimeQuery1 - startTimeQuery1;
            System.out.println("Response time Query 1: " + elapsedTimeQuery1 + " milliseconds");

            Thread.sleep(3000);
            List<String> data = new ArrayList<String>();

            ResultSetMetaData rsmd = rs1.getMetaData();
            int column_count = rsmd.getColumnCount();  //get column count

            while (rs1.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs1.getString(i);
                    data.add(columnValue);    //add all table data to List 'data'
                }
            }

            String query2 = newView;

            long startTimeQuery2 = System.currentTimeMillis();

            ResultSet rs2 = getStmt().executeQuery(query2);

            long endTimeQuery2 = System.currentTimeMillis();
            long elapsedTimeQuery2 = endTimeQuery2 - startTimeQuery2;
            System.out.println("Response time Query 2: " + elapsedTimeQuery2 + " milliseconds");

            Thread.sleep(3000);
            List<String> datanew = new ArrayList<String>();

            test.info("Old View Query execution time: "+elapsedTimeQuery1/1000 + " seconds ("+elapsedTimeQuery1+ " milliseconds)" );
            test.info("New View Query execution time: "+elapsedTimeQuery2/1000 + " seconds ("+elapsedTimeQuery2+" milliseconds)");
            long secondsDifference = (elapsedTimeQuery2/1000) - (elapsedTimeQuery1/1000);
            long millisecondsDifference = (elapsedTimeQuery2) - (elapsedTimeQuery1);
            test.info("New Query execution took: "+secondsDifference + " seconds more ("+millisecondsDifference+" milliseconds)");



            while (rs2.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2.getString(i);
                    datanew.add(columnValue);   //add all table data to List 'datanew'

                    if (methodName.equals("DataCompareNoCyclingConfig") ||
                            methodName.equals("OutOfIntervalRange") ||
                            methodName.equals("NoFlockAssociation")) {

                        test.info("T_Run_ID: "+rs2.getString("T_RUN_ID")+
                                " | CollectionDate: "+rs2.getString("COLLECTION_DATE")+
                                " | HouseID: "+rs2.getString("HOUSE_ID")+
                                " | CollectionSiteID: "+rs2.getString("COLLECTION_SITE_ID")+
                                " | ComplexID: "+rs2.getString("COMPLEX_ID")+
                                " | FARM_ID: "+rs2.getString("FARM_ID"));
                    }

                    if (methodName.equals("getCartridgeWithNo12Lanes")) {
                        test.info("CartridgeID: "+rs2.getString("CARTRIDGEID")+
                                " | SampleID: "+rs2.getString("SAMPLEID"));
                    }

                    if (methodName.equals("getNoCollectionDate") || methodName.equals("getNoProgramOnFlock") ) {
                        test.info("T_RUN_ID: "+rs2.getString("T_RUN_ID")+
                                " | SampleID: "+rs2.getString("SAMPLE_ID"));
                    }

                    if (methodName.equals("getNoOfSamplesPerCollection")) {
                        test.info("CollectionSiteID: "+rs2.getString("COLLECTION_SITE_ID")+
                                " | CollectionDate: "+rs2.getString("Collection Date")+
                                " | SamplesCollected: "+rs2.getString("Samples Collected"));
                    }
                }
            }

            int total_tabledata = column_count * datanew.size() / column_count;  //get total number of values in table

            for (int z = 1; z <= total_tabledata; z++) {
                softAssert.assertEquals(datanew.get(z - 1), data.get(z - 1), "Data not matching in row " + (z - 1) / column_count);
            }

            System.out.println("Total Rows Returned for method '" + methodName + "' are " + total_tabledata / column_count);

            test.log(Status.INFO, "Total Rows Returned for method " + methodName + "' are " + total_tabledata / column_count);
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
    }


    public static void viewsRowCompare(String oldView, String newView) throws InterruptedException, IOException, SQLException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the row count for method "+methodName);
            SoftAssert softAssert = new SoftAssert();


            String query1 = oldView;
            ResultSet rs1 = getStmt().executeQuery(query1);
            rs1.next();
            int countOldView = rs1.getInt(1);
            System.out.println("Total Number of Rows in Old View for method '" + methodName + "' are " + countOldView);
            test.info("Total Number of Rows in Old View for method '" + methodName + "' are " + countOldView);


            String query2 = newView;
            ResultSet rs2 = getStmt().executeQuery(query2);
            rs2.next();
            int countNewView = rs2.getInt(1);
            System.out.println("Total Number of Rows in New View for method '" + methodName + "' are " + countNewView);
            test.info("Total Number of Rows in Old View for method '" + methodName + "' are " + countNewView);

            softAssert.assertEquals(countNewView, countOldView);
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
    }


}
