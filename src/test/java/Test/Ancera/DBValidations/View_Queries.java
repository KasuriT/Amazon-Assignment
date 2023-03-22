package Test.Ancera.DBValidations;

public class View_Queries {

    public static String oldViewName = "DS_COCCIDIA_OPG_FLOCK_METADATA";
    public static String newViewName = "DS_COCCIDIA_OPG_FLOCK_METADATA";


    public static String getAllRowsCountQuery(String viewName) {
        String allRowsCountQuery = "Select count(T_Run_ID) as count from " + viewName;
        return allRowsCountQuery;
    }


    public static String getAllDataQuery(String viewName) {
        String allDataQuery = "Select * from "+viewName;
        return allDataQuery;
    }


    public static String getNoCyclingConfigQuery(String viewName) {
        String NoCyclingConfigQuery = "WITH CTEFlock AS(SELECT\n" +
                "\t\tfm.ID\n" +
                "   ,fm.FARM_INTERNAL_ID\n" +
                "   ,fm.BIRD_SIZE\n" +
                "   ,fm.BIRD_SIZE_ID\n" +
                "   ,fhd.HOUSE_INTERNAL_ID\n" +
                "   ,House.siteUniqueNumber houseUniqueNumber\n" +
                "   ,Farm.siteUniqueNumber farmUniqueNumber\n" +
                "   ,FS.PROCESSING_DATE\n" +
                "   ,fhd.PLACEMENT_DATE\n" +
                "   ,fhd.HARVEST_DATE\n" +
                "\tFROM FLOCK_MGMT fm\n" +
                "\tINNER JOIN ET.[Site] Farm\n" +
                "\t\tON fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "\t\tAND Farm.isActive = 1\n" +
                "\t\tAND Farm.isDeleted = 0\n" +
                "\tINNER JOIN FLOCK_HOUSE_DETAILS fhd\n" +
                "\t\tON fm.ID = fhd.FLOCK_ID\n" +
                "\t\tAND fhd.isActive = 1\n" +
                "\t\tAND fhd.isDeleted = 0\n" +
                "\tINNER JOIN ET.[Site] House\n" +
                "\t\tON fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "\t\tAND House.isActive = 1\n" +
                "\t\tAND House.isDeleted = 0\n" +
                "\tLEFT JOIN FLOCK_SETTLEMENT\tFS ON FS.FLOCK_ID=fm.ID AND FS.IS_ACTIVE=1 AND FS.Is_DELETED=0\n" +
                "\tWHERE fm.BIRD_SIZE_ID IS NOT NULL\n" +
                "\t),\n" +
                "CTECycling\n" +
                "AS\n" +
                "(SELECT\n" +
                "\t\tCI.CyclingIntervalName\n" +
                "\t   ,CI.startDay\n" +
                "\t   ,CI.EndDay\n" +
                "\t   ,DSCC.BIRD_SIZE\n" +
                "\t   ,DSCC.BIRD_SIZE_ID\n" +
                "\t   ,DSCC.CYCLING_INTERVAL_ID\n" +
                "\t   ,Complex.siteuniquenumber INTERNAL_SITE_ID\n" +
                "\n" +
                "\t   ,PRG.FLOCK_ID\n" +
                "\tFROM DS_COMPLEX_CYCLING_CONFIG DSCC /*SELECT * FROM FLOCKCYCLINGINTERVAL*/\n" +
                "\tINNER JOIN ET.[Site] Complex\n" +
                "\t\tON DSCC.INTERNAL_SITE_ID = Complex.siteId\n" +
                "\t\tAND Complex.isActive = 1\n" +
                "\t\tAND Complex.isDeleted = 0\n" +
                "\tJOIN flockCyclingInterval CI\n" +
                "\t\tON CI.CyclingIntervalId = DSCC.CYCLING_INTERVAL_ID\n" +
                "\t\tAND CI.birdSizeName = DSCC.BIRD_SIZE\n" +
                "\tJOIN ET.ComplexCyclingProgramAss CCP\n" +
                "\t\tON CCP.configId = DSCC.CONFIG_ID\n" +
                "\t\tAND CCP.isActive = 1\n" +
                "\t\tAND CCP.isDeleted = 0\n" +
                "\tJOIN dbo.FLOCK_PROGRAM_DETAILS PRG\n" +
                "\t\tON PRG.PROGRAM_ID = CCP.programId\n" +
                "\t\tAND PRG.isActive = 1\n" +
                "\t\tAND PRG.isDeleted = 0\n" +
                "\tJOIN FLOCK_MGMT fm\n" +
                "\t\tON PRG.FLOCK_ID = fm.ID\n" +
                "\t\tAND fm.BIRD_SIZE_ID = DSCC.BIRD_SIZE_ID\n" +
                "\tWHERE DSCC.OPG_TYPE_ID IN (951 /*OPG_TOTAL*/, 952)\n" +
                "\t--AND CCP.programId NOT IN(50,51,52,53,54,55,56)\n" +
                "\n" +
                "\t/*OPG_LARGE*/\n" +
                "\tGROUP BY /*CONFIG_ID,*/\n" +
                "\tDSCC.BIRD_SIZE\n" +
                "   ,CI.startDay\n" +
                "   ,CI.EndDay\n" +
                "   ,DSCC.BIRD_SIZE_ID\n" +
                "   ,DSCC.CYCLING_INTERVAL_ID\n" +
                "   ,Complex.siteuniquenumber \n" +
                "   ,CI.CyclingIntervalName\n" +
                "   ,PRG.FLOCK_ID)\n" +
                "\tSELECT\n" +
                "\tcd.T_RUN_ID\n" +
                "   ,cd.COLLECTION_DATE\n" +
                "   ,cd.HOUSE_ID\n" +
                "   ,cd.COLLECTION_SITE_ID\n" +
                "   ,cd.COMPLEX_ID\n" +
                "   ,cd.FARM_ID\n" +
                "FROM "+viewName+" cd\n" +
                "WHERE (cd.COUNT_OUTCOME IS NULL\n" +
                "OR cd.COUNT_OUTCOME = 'Completed')\n" +
                "AND cd.FLOCK_DAY IS NULL\n" +
                "AND EXISTS (SELECT\n" +
                "\t\t1\n" +
                "\tFROM CTEFlock flk\n" +
                "\tWHERE cd.COLLECTION_DATE > flk.PLACEMENT_DATE\n" +
                "\tAND cd.COLLECTION_DATE <= COALESCE(flk.PROCESSING_DATE ,flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))\n" +
                "\tAND DATEDIFF(DAY, flk.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 <= DATEDIFF(DAY, flk.PLACEMENT_DATE, COALESCE(flk.PROCESSING_DATE ,flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))) + 1\n" +
                "\tAND cd.HOUSE_ID = houseUniqueNumber\n" +
                "\tAND cd.FARM_ID = FarmUniqueNumber)\n" +
                "AND NOT EXISTS (SELECT\n" +
                "\t\t*\n" +
                "\tFROM CTECycling DSCC\n" +
                "\tINNER JOIN CTEFlock F\n" +
                "\t\tON F.BIRD_SIZE = DSCC.BIRD_SIZE\n" +
                "\t\tAND DSCC.FLOCK_ID = F.ID --AND DSCC.INTERNAL_SITE_ID=F.FARM_INTERNAL_ID\n" +
                "\tWHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "\tAND cd.HOUSE_ID = F.houseUniqueNumber)";

        return NoCyclingConfigQuery;
    }


    public static String getOutofIntervalRangeQuery(String viewName) {
        String outOfIntervalRangeQuery = "WITH CTEFlock\n" +
                "AS\n" +
                "(SELECT\n" +
                "\t\tfm.ID\n" +
                "\t   ,fm.FARM_INTERNAL_ID\n" +
                "\t   ,fm.BIRD_SIZE\n" +
                "\t   ,fm.BIRD_SIZE_ID\n" +
                "\t   ,fhd.HOUSE_INTERNAL_ID\n" +
                "\t   ,House.siteUniqueNumber houseUniqueNumber\n" +
                "\t   ,Farm.siteUniqueNumber farmUniqueNumber\n" +
                "\t   ,FS.PROCESSING_DATE\n" +
                "\t   ,fhd.PLACEMENT_DATE\n" +
                "\t   ,fhd.HARVEST_DATE\n" +
                "\tFROM FLOCK_MGMT fm\n" +
                "\tINNER JOIN ET.[Site] Farm\n" +
                "\t\tON fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "\t\tAND Farm.isActive = 1\n" +
                "\t\tAND Farm.isDeleted = 0\n" +
                "\tINNER JOIN FLOCK_HOUSE_DETAILS fhd\n" +
                "\t\tON fm.ID = fhd.FLOCK_ID\n" +
                "\t\tAND fhd.isActive = 1\n" +
                "\t\tAND fhd.isDeleted = 0\n" +
                "\tINNER JOIN ET.[Site] House\n" +
                "\t\tON fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "\t\tAND House.isActive = 1\n" +
                "\t\tAND House.isDeleted = 0\n" +
                "\tLEFT JOIN FLOCK_SETTLEMENT\tFS ON FS.FLOCK_ID=fm.ID\n" +
                "\tWHERE fm.BIRD_SIZE_ID IS NOT NULL),\n" +
                "CTECycling\n" +
                "AS\n" +
                "(SELECT\n" +
                "\t\tCI.CyclingIntervalName\n" +
                "\t   ,CI.startDay\n" +
                "\t   ,CI.EndDay\n" +
                "\t   ,DSCC.BIRD_SIZE\n" +
                "\t   ,DSCC.BIRD_SIZE_ID\n" +
                "\t   ,DSCC.CYCLING_INTERVAL_ID\n" +
                "\t   ,Complex.siteUniqueNumber INTERNAL_SITE_ID\n" +
                "\n" +
                "\t   ,PRG.FLOCK_ID\n" +
                "\tFROM DS_COMPLEX_CYCLING_CONFIG DSCC /*SELECT * FROM FLOCKCYCLINGINTERVAL*/\n" +
                "\tINNER JOIN ET.[Site] Complex\n" +
                "\t\tON DSCC.INTERNAL_SITE_ID = Complex.siteId\n" +
                "\t\tAND Complex.isActive = 1\n" +
                "\t\tAND Complex.isDeleted = 0\n" +
                "\tJOIN flockCyclingInterval CI\n" +
                "\t\tON CI.CyclingIntervalId = DSCC.CYCLING_INTERVAL_ID\n" +
                "\t\tAND CI.birdSizeName = DSCC.BIRD_SIZE\n" +
                "\tJOIN ET.ComplexCyclingProgramAss CCP\n" +
                "\t\tON CCP.configId = DSCC.CONFIG_ID\n" +
                "\t\tAND CCP.isActive = 1\n" +
                "\t\tAND CCP.isDeleted = 0\n" +
                "\tJOIN dbo.FLOCK_PROGRAM_DETAILS PRG\n" +
                "\t\tON PRG.PROGRAM_ID = CCP.programId\n" +
                "\t\tAND PRG.isActive = 1\n" +
                "\t\tAND PRG.isDeleted = 0\n" +
                "\tJOIN FLOCK_MGMT fm\n" +
                "\t\tON PRG.FLOCK_ID = fm.ID\n" +
                "\t\tAND fm.BIRD_SIZE_ID = DSCC.BIRD_SIZE_ID\n" +
                "\tWHERE DSCC.OPG_TYPE_ID IN (951 /*OPG_TOTAL*/, 952)\n" +
                "\t--AND CCP.programId NOT IN(50,51,52,53,54,55,56)\n" +
                "\n" +
                "\t/*OPG_LARGE*/\n" +
                "\tGROUP BY /*CONFIG_ID,*/\n" +
                "\tDSCC.BIRD_SIZE\n" +
                "   ,CI.startDay\n" +
                "   ,CI.EndDay\n" +
                "   ,DSCC.BIRD_SIZE_ID\n" +
                "   ,DSCC.CYCLING_INTERVAL_ID\n" +
                "   ,Complex.siteUniqueNumber\n" +
                "   ,CI.CyclingIntervalName\n" +
                "   ,PRG.FLOCK_ID)\n" +
                "SELECT\n" +
                "\tcd.T_RUN_ID\n" +
                "   ,cd.COLLECTION_DATE\n" +
                "   ,cd.HOUSE_ID\n" +
                "   ,cd.COLLECTION_SITE_ID\n" +
                "   ,cd.COMPLEX_ID\n" +
                "   ,cd.FARM_ID\n" +
                "FROM "+viewName+" cd\n" +
                "WHERE (cd.COUNT_OUTCOME IS NULL\n" +
                "OR cd.COUNT_OUTCOME = 'Completed')\n" +
                "AND cd.FLOCK_DAY IS NULL\n" +
                "AND EXISTS (SELECT\n" +
                "\t\t1\n" +
                "\tFROM CTEFlock flk\n" +
                "\tWHERE cd.COLLECTION_DATE > flk.PLACEMENT_DATE\n" +
                "\tAND cd.COLLECTION_DATE <= COALESCE(flk.PROCESSING_DATE,flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))\n" +
                "\tAND DATEDIFF(DAY, flk.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 <= DATEDIFF(DAY, flk.PLACEMENT_DATE, COALESCE(flk.PROCESSING_DATE,flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))) + 1\n" +
                "\tAND cd.HOUSE_ID = houseUniqueNumber\n" +
                "\tAND cd.FARM_ID = FarmUniqueNumber)\n" +
                "AND EXISTS (SELECT\n" +
                "\t\t1\n" +
                "\tFROM CTECycling DSCC\n" +
                "\tINNER JOIN CTEFlock F\n" +
                "\t\tON F.BIRD_SIZE = DSCC.BIRD_SIZE\n" +
                "\t\tAND DSCC.FLOCK_ID = F.ID --AND DSCC.INTERNAL_SITE_ID=F.FARM_INTERNAL_ID\n" +
                "\tWHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "\tAND cd.HOUSE_ID = F.houseUniqueNumber)\n" +
                "AND NOT EXISTS (SELECT\n" +
                "\t\t*\n" +
                "\tFROM CTECycling DSCC\n" +
                "\tINNER JOIN CTEFlock F\n" +
                "\t\tON F.BIRD_SIZE = DSCC.BIRD_SIZE\n" +
                "\t\tAND DSCC.FLOCK_ID = F.ID\n" +
                "\tWHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "\tAND cd.HOUSE_ID = F.houseUniqueNumber\n" +
                "\tAND DATEDIFF(DAY, F.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 <= DATEDIFF(DAY, F.PLACEMENT_DATE, COALESCE(F.PROCESSING_DATE ,F.HARVEST_DATE, DATEADD(DAY, 63, F.PLACEMENT_DATE))) + 1\n" +
                "\tAND DATEDIFF(DAY, F.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 BETWEEN DSCC.startDay AND ISNULL(DSCC.EndDay, DATEDIFF(DAY, F.PLACEMENT_DATE, COALESCE(F.PROCESSING_DATE ,F.HARVEST_DATE, DATEADD(DAY, 63, F.PLACEMENT_DATE))) + 1))";
        return outOfIntervalRangeQuery;
    }


    public static String getNoFlockAssociationQuery(String viewName) {
        String noflockAssociationQuery = "SELECT\n" +
                "\tcd.T_RUN_ID\n" +
                "   ,cd.COLLECTION_DATE\n" +
                "   ,cd.HOUSE_ID\n" +
                "   ,cd.COLLECTION_SITE_ID\n" +
                "   ,cd.COMPLEX_ID\n" +
                "   ,cd.FARM_ID\n" +
                "FROM "+viewName+" cd\n" +
                "WHERE (cd.COUNT_OUTCOME IS NULL\n" +
                "OR cd.COUNT_OUTCOME = 'Completed')\n" +
                "AND cd.FLOCK_DAY IS NULL\n" +
                "AND not EXISTS (SELECT\n" +
                "\t\t1\n" +
                "\tFROM FLOCK_MGMT fm\n" +
                "\tINNER JOIN ET.[Site] Farm\n" +
                "\t\tON fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "\t\tAND Farm.isActive = 1\n" +
                "\t\tAND Farm.isDeleted = 0\n" +
                "\tINNER JOIN FLOCK_HOUSE_DETAILS fhd\n" +
                "\t\tON fm.ID = fhd.FLOCK_ID\n" +
                "\t\tAND fhd.isActive = 1\n" +
                "\t\tAND fhd.isDeleted = 0\n" +
                "\tINNER JOIN ET.[Site] House\n" +
                "\t\tON fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "\t\tAND House.isActive = 1\n" +
                "\t\tAND House.isDeleted = 0\n" +
                "\tLEFT JOIN FLOCK_SETTLEMENT\tFS ON FS.FLOCK_ID=fm.ID AND FS.IS_ACTIVE=1 AND FS.Is_DELETED=0\n" +
                "\tWHERE fm.BIRD_SIZE_ID IS NOT NULL\n" +
                "\t\n" +
                "\tAND cd.COLLECTION_DATE > fhd.PLACEMENT_DATE\n" +
                "\tAND cd.COLLECTION_DATE <= COALESCE(FS.PROCESSING_DATE ,fhd.HARVEST_DATE, DATEADD(DAY, 63, fhd.PLACEMENT_DATE))\n" +
                "\tAND DATEDIFF(DAY, fhd.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 <= DATEDIFF(DAY, fhd.PLACEMENT_DATE, COALESCE(FS.PROCESSING_DATE ,fhd.HARVEST_DATE, DATEADD(DAY, 63, fhd.PLACEMENT_DATE))) + 1\n" +
                "\tAND cd.HOUSE_ID = house.siteUniqueNumber\n" +
                "\tAND cd.FARM_ID = Farm.siteUniqueNumber)";
        return noflockAssociationQuery;
    }

    public static String getNoCollectionDateRowCountQuery(String viewName) {
        String noCollectionDateQuery = "SELECT count(T_Run_ID) as count FROM "+viewName+"\n" +
                "where COLLECTION_DATE IS NULL";
        return noCollectionDateQuery;
    }

    public static String getNoCollectionDateQuery(String viewName) {
        String noCollectionDateQuery = "SELECT T_RUN_ID, SAMPLE_ID FROM "+viewName+"\n" +
                "where COLLECTION_DATE IS NULL";
        return noCollectionDateQuery;
    }


    public static String getNoSamplesPerCollectionQuery(String viewName) {
        String noSamplesPerCollectionQuery = "SELECT \n" +
                "    COLLECTION_SITE_ID, \n" +
                "    CONVERT(DATE, COLLECTION_DATE) as \"Collection Date\", \n" +
                "    COUNT(SAMPLE_ID) as \"Samples Collected\"\n" +
                "FROM "+viewName+"\n" +
                "GROUP BY \n" +
                "    COLLECTION_SITE_ID, \n" +
                "    CONVERT(DATE, COLLECTION_DATE)\n" +
                "HAVING COUNT(SAMPLE_ID) % 12 <> 0";
        return noSamplesPerCollectionQuery;
    }



    public static String getNoProgramOnFlockQuery(String viewName) {
        String noProgramOnFlockQuery = "SELECT T_RUN_ID, SAMPLE_ID \n" +
                "FROM \n" +
                "    "+viewName+"\n" +
                "WHERE\n" +
                "    FEED_PROGRAM IS NULL \n" +
                "    AND BIO_SHUTTLE IS NULL \n" +
                "    AND VACCINE IS NULL";
        return noProgramOnFlockQuery;
    }

    public static String getCartridgeWithNo12LanesQuery(String viewName) {
        String no12LaneReportedQuery = "SELECT CARTRIDGEID, COUNT(SAMPLE_ID) as SAMPLEID FROM "+viewName+"\n" +
                "GROUP BY CARTRIDGEID\n" +
                "HAVING COUNT(SAMPLE_ID) <> 12";
        return no12LaneReportedQuery;
    }







}
