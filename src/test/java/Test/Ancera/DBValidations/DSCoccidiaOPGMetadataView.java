package Test.Ancera.DBValidations;

import MiscFunctions.DB_Config_DW;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DBValidationMethods.viewsDataCompare;
import static MiscFunctions.DBValidationMethods.viewsRowCompare;
import static MiscFunctions.ExtentVariables.extent;
import static Test.Ancera.DBValidations.Queries.DSCoccidiaOPGMetadata_Queries.*;


public class DSCoccidiaOPGMetadataView extends DB_Config_DW {


    @BeforeSuite
    public static void setUp() {
        initReport("DSCoccidiaView");
    }


    @Test(enabled = true, priority = 1)
    public static void AllRowsCompare() throws SQLException, InterruptedException, IOException {
        viewsRowCompare(getAllRowsCountQuery(oldViewName), getAllRowsCountQuery(newViewName));
    }

    @Test(enabled = true, priority = 2, dependsOnMethods = {"AllRowsCompare"})
    public static void AllDataCompare() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getAllDataQuery(oldViewName), getAllDataQuery(newViewName));
    }


    @AfterTest
    public void endreport() throws Exception {
        DB_Config_DW.tearDown();
        DB_Config_DW.getStmt();
        DB_Config_DW.setStmt(getStmt());
        extent.flush();
    }
}
