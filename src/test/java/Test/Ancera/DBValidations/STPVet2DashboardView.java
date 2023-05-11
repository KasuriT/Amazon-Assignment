package Test.Ancera.DBValidations;

import MiscFunctions.DB_Config_DW;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DBValidationMethods.viewsDataCompare;
import static MiscFunctions.DBValidationMethods.viewsDataCompareSP;
import static MiscFunctions.ExtentVariables.extent;
import static Test.Ancera.DBValidations.Queries.STPVet2DashBoardData_Queries.*;


public class STPVet2DashboardView extends DB_Config_DW {


    @BeforeSuite
    public static void setUp() {
        initReport("STPVet2DashboardView");
    }


    @Test(enabled = true, priority = 1)
    public static void DataCompareSTPVet2Dashboard() throws SQLException, InterruptedException, IOException {
        viewsDataCompareSP(getvet2DashboardDataQuery(oldViewName), getvet2DashboardDataQuery(newViewName));
    }


    @AfterTest
    public void endreport() throws Exception {
        DB_Config_DW.tearDown();
        DB_Config_DW.getStmt();
        DB_Config_DW.setStmt(getStmt());
        extent.flush();
    }
}
