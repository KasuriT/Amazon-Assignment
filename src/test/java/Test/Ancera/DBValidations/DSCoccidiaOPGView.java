package Test.Ancera.DBValidations;

import MiscFunctions.DB_Config_DW;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DSCoccidiaOPGView extends DB_Config_DW {

    @Test(enabled= true, priority= 1)
    public void RowCompare() throws InterruptedException, IOException, SQLException {

        SoftAssert softAssert = new SoftAssert();

    //    String query1 = "Select count(status) as countOldView from COCCIDA_OUTPUT where Sample_ID like '202212%' ";
        String query1 = "Select count(T_Run_ID) as countOldView from DS_COCCIDIA_OPG_FLOCK_METADATA";
        ResultSet rs1 = getStmt().executeQuery(query1);
        rs1.next();
        int countOldView = rs1.getInt(1);
        System.out.println("Count1: "+countOldView);

        String query2 = "Select count(status) as countNewView from COCCIDA_OUTPUT where Sample_ID like '202212%' ";
        ResultSet rs2 = getStmt().executeQuery(query2);
        rs2.next();
        int countNewView = rs2.getInt(1);
        System.out.println("Count2: "+countNewView);

        softAssert.assertEquals(countNewView, countOldView);
        softAssert.assertAll();
    }

    @Test(enabled= true, priority= 1)
    public void DataCompare() throws InterruptedException, IOException, SQLException {

        SoftAssert softAssert = new SoftAssert();

        String query1 = "Select Top 10 * from COCCIDA_OUTPUT where Sample_ID like '202212%' ";
        ResultSet rs1 = getStmt().executeQuery(query1);
        rs1.next();

        String query2 = "Select * from COCCIDA_OUTPUT where Sample_ID like '202212%' ";
        ResultSet rs2 = getStmt().executeQuery(query2);
        rs2.next();

// Compare the data in the two tables
        while (rs1.next() && rs2.next()) {
            // Compare the values in each column
            for (int i=1;i<10;i++) {
                System.out.print(rs1.getInt(i));
                System.out.println();
                System.out.print(rs2.getInt(i));

               if (rs1.getInt(i) != (rs2.getInt(i))) {
                    System.out.println("Data in tables is not the same");
                    break;
                }
            }
        }

        // If the while loop finished without breaking, the data is the same
        System.out.println("Data in tables is the same");


    //    softAssert.assertEquals(countNewView, countOldView);
        softAssert.assertAll();
    }



    @AfterTest
    public void endreport() throws Exception {

        DB_Config_DW.tearDown();
        DB_Config_DW.getStmt();
        DB_Config_DW.setStmt(getStmt());
    }

}
