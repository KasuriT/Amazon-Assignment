package MiscFunctions;

import MiscFunctions.DB_Config_DW;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static Config.BaseTest.saveResult;
import static Config.BaseTest.saveResultNoScreenshot;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.test;

public class DBValidationMethods extends DB_Config_DW {


    public static void viewsDataCompare(String oldView, String newView) throws SQLException, InterruptedException, IOException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the data in each row for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            String query1 = oldView;

            long startTimeQuery1 = System.currentTimeMillis();

            ResultSet rs1 = getStmt().executeQuery(query1);

            long endTimeQuery1 = System.currentTimeMillis();
            long elapsedTimeQuery1 = endTimeQuery1 - startTimeQuery1;
            System.out.println("Response time Query 1: " + elapsedTimeQuery1 + " milliseconds");

            Thread.sleep(3000);
            //    Thread.sleep(85000);
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

            int total_tabledata_old = column_count * data.size() / column_count;  //get total number of values in table
            System.out.println("Total Rows Returned for method 1 '" + methodName + "' are " + total_tabledata_old / column_count);

            Thread.sleep(3000);
            // Thread.sleep(85000);
            List<String> datanew = new ArrayList<String>();

            test.info("Old View Query execution time: " + elapsedTimeQuery1 / 1000 + " seconds (" + elapsedTimeQuery1 + " milliseconds)");
            test.info("New View Query execution time: " + elapsedTimeQuery2 / 1000 + " seconds (" + elapsedTimeQuery2 + " milliseconds)");
            long secondsDifference = (elapsedTimeQuery2 / 1000) - (elapsedTimeQuery1 / 1000);
            long millisecondsDifference = (elapsedTimeQuery2) - (elapsedTimeQuery1);
            test.info("New Query execution took: " + secondsDifference + " seconds more (" + millisecondsDifference + " milliseconds)");


            while (rs2.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2.getString(i);
                    datanew.add(columnValue);   //add all table data to List 'datanew'

                    if (methodName.equals("DataCompareNoCyclingConfig") ||
                            methodName.equals("OutOfIntervalRange") ||
                            methodName.equals("NoFlockAssociation")) {

                        test.info("T_Run_ID: " + rs2.getString("T_RUN_ID") +
                                " | CollectionDate: " + rs2.getString("COLLECTION_DATE") +
                                " | HouseID: " + rs2.getString("HOUSE_ID") +
                                " | CollectionSiteID: " + rs2.getString("COLLECTION_SITE_ID") +
                                " | ComplexID: " + rs2.getString("COMPLEX_ID") +
                                " | FARM_ID: " + rs2.getString("FARM_ID"));
                    }

                    if (methodName.equals("getCartridgeWithNo12Lanes")) {
                        test.info("CartridgeID: " + rs2.getString("CARTRIDGEID") +
                                " | SampleID: " + rs2.getString("SAMPLEID"));
                    }

                    if (methodName.equals("getNoCollectionDate") || methodName.equals("getNoProgramOnFlock")) {
                        test.info("T_RUN_ID: " + rs2.getString("T_RUN_ID") +
                                " | SampleID: " + rs2.getString("SAMPLE_ID"));
                    }

                    if (methodName.equals("getNoOfSamplesPerCollection")) {
                        test.info("CollectionSiteID: " + rs2.getString("COLLECTION_SITE_ID") +
                                " | CollectionDate: " + rs2.getString("Collection Date") +
                                " | SamplesCollected: " + rs2.getString("Samples Collected"));
                    }


//                    if (methodName.equals("AllDataCompare")) {
//                        test.info("T_RUN_ID: "+rs2.getString("T_RUN_ID"));
//                    }


//                    if (methodName.equals("DataCompareSTPVet2Dashboard")) {
//                        test.info("T_RUN_ID: "+rs2.getString("T_RUN_ID")+
//                                " | RUN_ID: "+rs2.getString("RUN_ID")+
//                                " | Intervention Name: "+rs2.getString("interventionName"));
//                    }
                }
            }

            int total_tabledata = column_count * datanew.size() / column_count;  //get total number of values in table

            for (int z = 1; z <= total_tabledata; z++) {
                softAssert.assertEquals(datanew.get(z - 1), data.get(z - 1), "Data not matching in row " + (z - 1) / column_count);
            }

            System.out.println("Total Rows Returned for method 2 '" + methodName + "' are " + total_tabledata / column_count);

            test.log(Status.INFO, "Total Rows Returned for method " + methodName + "' are " + total_tabledata / column_count);
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }


    public static void viewsDataCompareSP(String oldView, String newView) throws SQLException, InterruptedException, IOException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the data in each row for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            String query1 = oldView;

            long startTimeQuery1 = System.currentTimeMillis();


            String DB_URL = "jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName="+PRH_DB_Name+";user=" + DB_UserName + ";Password=" + DB_Password;
            Connection conn = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
            setStmt(conn.createStatement());


            CallableStatement cstmt = conn.prepareCall(query1);
            ResultSet rs1 = cstmt.executeQuery();

            long endTimeQuery1 = System.currentTimeMillis();
            long elapsedTimeQuery1 = endTimeQuery1 - startTimeQuery1;
            System.out.println("Response time Query 1: " + elapsedTimeQuery1 + " milliseconds");

            Thread.sleep(3000);

            List<String> dataTable1 = new ArrayList<String>();
            List<String> dataTable2 = new ArrayList<String>();
            List<String> dataTable3 = new ArrayList<String>();
            List<String> dataTable4 = new ArrayList<String>();

            ResultSetMetaData rsmd1 = rs1.getMetaData();
            int column_count1 = rsmd1.getColumnCount();  //get column count
            while (rs1.next()) {
                //   System.out.print("First Name: "+rs1.getString("interventionTypeName")+", ");
                int column_count = rsmd1.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs1.getString(i);
                    dataTable1.add(columnValue);    //add all table data to List 'dataTable1'
                }
            }


            cstmt.getMoreResults();
            ResultSet rs2 = cstmt.getResultSet();
            ResultSetMetaData rsmd2 = rs2.getMetaData();
            int column_count2 = rsmd2.getColumnCount();  //get column count
            while (rs2.next()) {
                int column_count = rsmd2.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2.getString(i);
                    dataTable2.add(columnValue);    //add all table data to List 'dataTable2'
                }
            }


            cstmt.getMoreResults();
            ResultSet rs3 = cstmt.getResultSet();
            ResultSetMetaData rsmd3 = rs3.getMetaData();
            int column_count3 = rsmd3.getColumnCount();  //get column count
            while (rs3.next()) {
                int column_count = rsmd3.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs3.getString(i);
                    dataTable3.add(columnValue);    //add all table data to List 'dataTable3'
                }
            }


            cstmt.getMoreResults();
            ResultSet rs4 = cstmt.getResultSet();
            ResultSetMetaData rsmd4 = rs4.getMetaData();
            int column_count4 = rsmd4.getColumnCount();  //get column count
            while (rs4.next()) {
                int column_count = rsmd4.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs4.getString(i);
                    dataTable4.add(columnValue);    //add all table data to List 'dataTable3'
                }
            }


            String query2 = newView;

            long startTimeQuery2 = System.currentTimeMillis();

            CallableStatement cstmtNew = con.prepareCall(query2);
            ResultSet rs1New = cstmtNew.executeQuery();

            long endTimeQuery2 = System.currentTimeMillis();
            long elapsedTimeQuery2 = endTimeQuery2 - startTimeQuery2;
            System.out.println("Response time Query 2: " + elapsedTimeQuery2 + " milliseconds");

            Thread.sleep(3000);
            List<String> dataTableNew1 = new ArrayList<String>();
            List<String> dataTableNew2 = new ArrayList<String>();
            List<String> dataTableNew3 = new ArrayList<String>();
            List<String> dataTableNew4 = new ArrayList<String>();

            test.info("Old View Query execution time: " + elapsedTimeQuery1 / 1000 + " seconds (" + elapsedTimeQuery1 + " milliseconds)");
            test.info("New View Query execution time: " + elapsedTimeQuery2 / 1000 + " seconds (" + elapsedTimeQuery2 + " milliseconds)");
            long secondsDifference = (elapsedTimeQuery2 / 1000) - (elapsedTimeQuery1 / 1000);
            long millisecondsDifference = (elapsedTimeQuery2) - (elapsedTimeQuery1);
            test.info("New Query execution took: " + secondsDifference + " seconds more (" + millisecondsDifference + " milliseconds)");


            ResultSetMetaData rsmd1New = rs1New.getMetaData();
            int column_count1New = rsmd1New.getColumnCount();  //get column count
            System.out.println("Columns Table 1: "+column_count1New);
            while (rs1New.next()) {
                int column_count = rsmd1New.getColumnCount();  //get column count
          //      test.info("T_RUN_ID Table 1: "+rs1New.getString("T_RUN_ID"));
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs1New.getString(i);
                    dataTableNew1.add(columnValue);    //add all table data to List 'dataTable1'
                }
            }


            cstmtNew.getMoreResults();
            ResultSet rs2New = cstmtNew.getResultSet();
            ResultSetMetaData rsmd2New = rs2New.getMetaData();
            int column_count2New = rsmd2New.getColumnCount();  //get column count
            System.out.println("Columns Table 2: "+column_count2New);
            while (rs2New.next()) {
                int column_count = rsmd2New.getColumnCount();  //get column count
            //    test.info("T_RUN_ID Table 2: "+rs2New.getString("T_RUN_ID"));
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2New.getString(i);
                    dataTableNew2.add(columnValue);    //add all table data to List 'dataTable2'
                }
            }


            cstmtNew.getMoreResults();
            ResultSet rs3New = cstmtNew.getResultSet();
            ResultSetMetaData rsmd3New = rs3New.getMetaData();
            int column_count3New = rsmd3New.getColumnCount();  //get column count
            System.out.println("Columns Table 3: "+column_count3New);
            while (rs3New.next()) {
                int column_count = rsmd3New.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs3New.getString(i);
                    dataTableNew3.add(columnValue);    //add all table data to List 'dataTable3'
                }
            }


            cstmtNew.getMoreResults();
            ResultSet rs4New = cstmtNew.getResultSet();
            ResultSetMetaData rsmd4New = rs4New.getMetaData();
            int column_count4New = rsmd4New.getColumnCount();  //get column count
            System.out.println("Columns Table 4: "+column_count4New);
            while (rs4New.next()) {
                int column_count = rsmd4New.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs4New.getString(i);
                    dataTableNew4.add(columnValue);    //add all table data to List 'dataTable4'
                }
            }


            int total_tabledata1 = column_count1 * dataTableNew1.size() / column_count1;  //get total number of values in table

          //  System.out.println(dataTable1);
         //   System.out.println(dataTable1.get(1));
        //    List<String> sortedList = dataTable1.stream().sorted().collect(Collectors.toList());
         //   List<String> sortedListNew = dataTableNew1.stream().sorted().collect(Collectors.toList());
         //   Collections.sort(dataTableNew1);
            //   Collections.sort(dataTable1);

            for (int z = 1; z <= total_tabledata1; z++) {
             //   System.out.println(sortedListNew.get(z - 1)+" >>>>>> "+sortedList.get(z - 1));
             //   softAssert.assertEquals(sortedListNew.get(z - 1), sortedList.get(z - 1), "Data not matching in Table 1 row " + (z - 1) / column_count1);
               softAssert.assertEquals(dataTableNew1.get(z - 1), dataTable1.get(z - 1), "Data not matching in Table 1 row " + (z - 1) / column_count1);
            }
            System.out.println("Total Rows Returned for SP '" + methodName + "' are " + total_tabledata1 / column_count1);
            test.log(Status.INFO, "Total Rows Returned for SP " + methodName + "' are " + total_tabledata1 / column_count1);


            int total_tabledata2 = column_count2 * dataTableNew2.size() / column_count2;  //get total number of values in table
            for (int z = 1; z <= total_tabledata2; z++) {
                softAssert.assertEquals(dataTableNew2.get(z - 1), dataTable2.get(z - 1), "Data not matching in Table 2 row " + (z - 1) / column_count2);
            }
            System.out.println("Total Rows Returned for method 2 '" + methodName + "' are " + total_tabledata2 / column_count2);
            test.log(Status.INFO, "Total Rows Returned for method " + methodName + "' are " + total_tabledata2 / column_count2);


            int total_tabledata3 = column_count3 * dataTableNew3.size() / column_count3;  //get total number of values in table
            for (int z = 1; z <= total_tabledata3; z++) {
                softAssert.assertEquals(dataTableNew3.get(z - 1), dataTable3.get(z - 1), "Data not matching in Table 3 row " + (z - 1) / column_count3);
            }
            System.out.println("Total Rows Returned for method 2 '" + methodName + "' are " + total_tabledata3 / column_count3);
            test.log(Status.INFO, "Total Rows Returned for method " + methodName + "' are " + total_tabledata3 / column_count3);


            int total_tabledata4 = column_count4 * dataTableNew4.size() / column_count4;  //get total number of values in table
            for (int z = 1; z <= total_tabledata4; z++) {
                softAssert.assertEquals(dataTableNew4.get(z - 1), dataTable4.get(z - 1), "Data not matching in Table 4 row " + (z - 1) / column_count4);
            }
            System.out.println("Total Rows Returned for method 2 '" + methodName + "' are " + total_tabledata4 / column_count4);
            test.log(Status.INFO, "Total Rows Returned for method " + methodName + "' are " + total_tabledata4 / column_count4);


            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
        catch (Exception ex) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public static void viewsRowCompare(String oldView, String newView) throws InterruptedException, IOException, SQLException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the row count for method " + methodName);
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
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }


}
