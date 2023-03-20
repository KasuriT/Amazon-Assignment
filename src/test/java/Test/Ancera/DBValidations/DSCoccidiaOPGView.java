package Test.Ancera.DBValidations;

import MiscFunctions.DB_Config_DW;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Config.BaseTest.saveResult;
import static Test.Ancera.DBValidations.View_Queries.*;

import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.ExtentVariables.extent;
import static ExtentReports.ExtentReport.initReport;


public class DSCoccidiaOPGView extends DB_Config_DW {


    @BeforeSuite
    public static void setUp() {
//        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/Reports/DSCoccidiaView"+date+".html");
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
        initReport("DSCoccidiaView");
    }


    public static void viewsDataCompare(String oldView, String newView) throws SQLException, InterruptedException, IOException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the data in each row for method "+methodName);
            SoftAssert softAssert = new SoftAssert();

            String query1 = oldView;
            ResultSet rs1 = getStmt().executeQuery(query1);
            Thread.sleep(3000);
            List<String> data = new ArrayList<String>();

            ResultSetMetaData rsmd = rs1.getMetaData();
            int column_count = rsmd.getColumnCount();  //get column count

            //////////////////////////////////
//            String[] columnNames = new String[column_count];
//            for (int i = 1; i <= column_count; i++) {
//                columnNames[i-1] = rsmd.getColumnName(i);
//            }
//
//            for (String columnName : columnNames) {
//                System.out.println(columnName);
//            }
            /////////////////////////////////

            while (rs1.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs1.getString(i);
                    data.add(columnValue);    //add all table data to List 'data'

                    ////////////////////////////////
//                    for (String columnName : columnNames) {
//                        String columnData = rs1.getString(columnName);
//                        test.log(Status.INFO, columnName + ": " + columnData);
//                    }
                    ////////////////////////////////
                }
            }

            String query2 = newView;
            ResultSet rs2 = getStmt().executeQuery(query2);
            Thread.sleep(3000);
            List<String> datanew = new ArrayList<String>();

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


    @Test(enabled = false, priority = 2)
    public static void AllRowsCompare() throws SQLException, InterruptedException, IOException {
        viewsRowCompare(getAllRowsCountQuery(oldViewName), getAllRowsCountQuery(newViewName));
    }

    @Test(enabled = false, priority = 3)
    public static void AllDataCompare() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getAllDataQuery(oldViewName), getAllDataQuery(newViewName));
    }

    @Test(enabled = false, priority = 4)
    public static void DataCompareNoCyclingConfig() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoCyclingConfigQuery(oldViewName), getNoCyclingConfigQuery(newViewName));
    }

    @Test(enabled = false, priority = 5)
    public static void OutOfIntervalRange() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getOutofIntervalRangeQuery(oldViewName), getOutofIntervalRangeQuery(newViewName));
    }

    @Test(enabled = false, priority = 6)
    public static void NoFlockAssociation() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoFlockAssociationQuery(oldViewName), getNoFlockAssociationQuery(newViewName));
    }

    @Test(enabled = false, priority = 7)
    public static void getNoCollectionDate() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoCollectionDateQuery(oldViewName), getNoCollectionDateQuery(newViewName));
    //    viewsRowCompare(getNoCollectionDateQuery(oldViewName), getNoCollectionDateQuery(newViewName));
    }

    @Test(enabled = false, priority = 8)
    public static void getNoOfSamplesPerCollection() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoSamplesPerCollectionQuery(oldViewName), getNoSamplesPerCollectionQuery(newViewName));
    }

    @Test(enabled = false, priority = 9)
    public static void getNoProgramOnFlock() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoProgramOnFlockQuery(oldViewName), getNoProgramOnFlockQuery(newViewName));
    }

    @Test(enabled = true, priority = 10)
    public static void getCartridgeWithNo12Lanes() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getCartridgeWithNo12LanesQuery(oldViewName), getCartridgeWithNo12LanesQuery(newViewName));
    }


    @AfterTest
    public void endreport() throws Exception {
        DB_Config_DW.tearDown();
        DB_Config_DW.getStmt();
        DB_Config_DW.setStmt(getStmt());
        extent.flush();
    }


}
