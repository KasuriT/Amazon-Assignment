package Test.Ancera.DBValidations;

import MiscFunctions.DB_Config_DW;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static MiscFunctions.DBValidationMethods.viewsDataCompare;
import static MiscFunctions.DBValidationMethods.viewsRowCompare;
import static Test.Ancera.DBValidations.Queries.DSCoccidiaOPGFlockMetadata_Queries.*;

import static MiscFunctions.ExtentVariables.extent;
import static ExtentReports.ExtentReport.initReport;


public class DSCoccidiaOPGFlockMetadataView extends DB_Config_DW {


    @BeforeSuite
    public static void setUp() {
        initReport("DSCoccidiaView");
    }


    @Test(enabled = true, priority = 2)
    public static void AllRowsCompare() throws SQLException, InterruptedException, IOException {
        viewsRowCompare(getAllRowsCountQuery(oldViewName), getAllRowsCountQuery(newViewName));
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

    @Test(enabled = true, priority = 7)
    public static void getNoCollectionDate() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoCollectionDateQuery(oldViewName), getNoCollectionDateQuery(newViewName));
    }

    @Test(enabled = true, priority = 8)
    public static void getNoOfSamplesPerCollection() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoSamplesPerCollectionQuery(oldViewName), getNoSamplesPerCollectionQuery(newViewName));
    }

    @Test(enabled = true, priority = 9)
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
