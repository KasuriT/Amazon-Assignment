package Test.Ancera.DBValidations;

import MiscFunctions.DB_Config_DW;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
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


public class DSCoccidiaOPGView extends DB_Config_DW {

    public static void viewsDataCompare(String oldView, String newView) throws SQLException, InterruptedException, IOException {
        try {
            SoftAssert softAssert = new SoftAssert();

            String query1 = oldView;
            ResultSet rs1 = getStmt().executeQuery(query1);
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
            ResultSet rs2 = getStmt().executeQuery(query2);
            Thread.sleep(3000);
            List<String> datanew = new ArrayList<String>();

            while (rs2.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2.getString(i);
                    datanew.add(columnValue);   //add all table data to List 'datanew'
                }
            }

            int total_tabledata = column_count * datanew.size() / column_count;  //get total number of values in table

            for (int z = 1; z <= total_tabledata; z++) {
                softAssert.assertEquals(datanew.get(z - 1), data.get(z - 1), "Data not matching in row " + (z - 1) / column_count);
            }
            softAssert.assertAll();
        }
        catch (AssertionError er) {
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
    }


    @Test(enabled = true, priority = 1)
    public void RowCompare() throws InterruptedException, IOException, SQLException {
        try {
            SoftAssert softAssert = new SoftAssert();

           // String query1 = countRowsView + oldViewName;
            String query1 = getAllRowsCountQuery(oldViewName);
            ResultSet rs1 = getStmt().executeQuery(query1);
            rs1.next();
            int countOldView = rs1.getInt(1);
            System.out.println("Count1: " + countOldView);

          //  String query2 = countRowsView + newViewName;
            String query2 = getAllRowsCountQuery(newViewName);
            ResultSet rs2 = getStmt().executeQuery(query2);
            rs2.next();
            int countNewView = rs2.getInt(1);
            System.out.println("Count2: " + countNewView);

            softAssert.assertEquals(countNewView, countOldView);
            softAssert.assertAll();
        } catch (AssertionError er) {
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
    }


    @Test(enabled = true, priority = 3)
    public static void AllDataCompare() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getAllDataQuery(oldViewName), getAllDataQuery(newViewName));
    }

    @Test(enabled = true, priority = 4)
    public static void DataCompareNoCyclingConfig() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoCyclingConfigQuery(oldViewName), getNoCyclingConfigQuery(newViewName));
    }

    @Test(enabled = true, priority = 5)
    public static void OutOfIntervalRange() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getOutofIntervalRangeQuery(oldViewName), getOutofIntervalRangeQuery(newViewName));
    }

    @Test(enabled = true, priority = 6)
    public static void NoFlockAssociation() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoFlockAssociationQuery(oldViewName), getNoFlockAssociationQuery(newViewName));
    }


    @AfterTest
    public void endreport() throws Exception {

        DB_Config_DW.tearDown();
        DB_Config_DW.getStmt();
        DB_Config_DW.setStmt(getStmt());
    }

}
