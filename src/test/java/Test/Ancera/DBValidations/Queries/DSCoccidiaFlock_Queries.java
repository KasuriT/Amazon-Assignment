package Test.Ancera.DBValidations.Queries;

public class DSCoccidiaFlock_Queries {

    public static String oldViewName = "DS_COCCIDIA_OPG_FLOCK_METADATA";
    public static String newViewName = "DS_COCCIDIA_OPG_FLOCK_METADATA_NEW_TEMP";


    public static String getAllRowsCountQuery(String viewName) {
        String allRowsCountQuery = "Select count(T_Run_ID) as count from " + viewName;
        return allRowsCountQuery;
    }


    public static String getAllDataQuery(String viewName) {
        String allDataQuery = "Select T_RUN_ID,SAMPLE_ID,SAMPLE_MATRIX,TOTAL_OPG,TOTAL_SMALL_OPG,TOTAL_MEDIUM_OPG,TOTAL_LARGE_OPG,SCANDATETIME,INSTRUMENT_ID,CARTRIDGEID,COLLECTION_SITE_ID,HOUSE_ID,HOUSE,FARM_ID,FARM,COMPLEX_ID,COMPLEX,SUBREGION_ID,SUBREGION,REGION_ID,REGION,INTEGRATOR_ID,INTEGRATOR,COLLECTION_DATE,COUNT_OUTCOME,PLACEMENT_DATE,FLOCK_DAY,REAL_PLACEMENT_DATE,TEST_SITE_NAME,TEST_SITE_ID,METADATA_DATE_RECEIVED,RUN_DATE,METADATA_COMPLETE_DATE,METADATA_UPDATE_DATE,SAMPLE_INTERVAL,TOTAL_OPG_IN_RANGE,TOTAL_LARGE_OPG_IN_RANGE,TOTAL_BIRDS_PLACED,UNIQUE_FLOCK_ID,HARVEST_DATE,SITE_ID,TOTAL_WEIGHT_CONDEMNED_LB,TOTAL_WEIGHT_CONDEMNED_KG,NUM_BIRDS_CONDEMNED_WHOLE,PARTS_WEIGHT_CONDEMNED_LB,PARTS_WEIGHT_CONDEMNED_KG,kCAL_PER_POUND,A_GRADE_PAWS_PERC,AIRSAC_PERCENTAGE,IP_PERCENTAGE,LEUKOSIS_PERCENTAGE,SEPTOX_PERCENTAGE,TUMOR_PERCENTAGE,FLOCK_ID,NUM_BIRDS_DOA_PLANT,WEEK1_MORTALITY,WEEK2_MORTALITY,WEEK3_MORTALITY,WEEK4_MORTALITY,WEEK5_MORTALITY,WEEK6_MORTALITY,WEEK7_MORTALITY,WEEK8_MORTALITY,WEEK9_MORTALITY,WEEKLY_FARM_RANK,HISTORICAL_FARM_COST_VARIANCE,WEEKLY_FARM_COST_VARIANCE,DAYS_OUT,AGE_OF_LITTER,AVG_SOLD_AGE,NUM_BIRDS_SOLD,PLACEMENT_DENSITY,PROCESSING_DATE,PROCESSING_SITE_ID,USDA_PLANT_ID,PLANT_LOCATION,NUM_BIRDS_PROCESSED,AVG_BIRD_WEIGHT_LBS,AVG_BIRD_WEIGHT_KGS,AVG_DAILY_WEIGHT_GAIN_LB,AVG_DAILY_WEIGHT_GAIN_KG,TOTAL_WEIGHT_PROCESSED_LB,TOTAL_WEIGHT_PROCESSED_KG,TOTAL_FEED_WEIGHT_LB,TOTAL_FEED_WEIGHT_KG,FCR,FCR_ADJ,FEED_COST_PER_LIVE_POUND,MEDICATION_COST_PER_LIVE_POUND,GROWER_COST_PER_LIVE_POUND,LIVABILITY_PERCENTAGE,OVERALL_MORTALITY_PERCENTAGE,HATCH_DATE,VACCINE,VACCINE_START_DATE,VACCINE_END_DATE,FEED_PROGRAM,FEED_START_DATE,FEED_END_DATE,BIO_SHUTTLE,BIO_SHUTTLE_START_DATE,BIO_SHUTTLE_END_DATE,INTERVENTION_BIO_SHUTTLE_ID,INTERVENTION_VACCINE_ID,INTERVENTION_FEED_PROGRAM_ID from "+viewName;
        return allDataQuery;
    }


    public static String getNoCyclingConfigQuery(String viewName) {
        String NoCyclingConfigQuery = "WITH CTEFlock\n" +
                "AS\n" +
                "(SELECT\n" +
                "\t\tfm.ID\n" +
                "\t   ,fm.FARM_INTERNAL_ID\n" +
                "\t   ,fhd.HOUSE_INTERNAL_ID\n" +
                "\t   ,House.siteUniqueNumber AS HOUSE_UNIQUE_NUMBER\n" +
                "\t   ,Farm.siteUniqueNumber AS FARM_UNIQUE_NUMBER\n" +
                "\t   ,FS.PROCESSING_DATE\n" +
                "\t   ,fhd.PLACEMENT_DATE\n" +
                "\t   ,fhd.HARVEST_DATE\n" +
                "\t   ,fhd.ID AS FLOCK_HOUSE_DETAILS_ID\n" +
                "\tFROM FLOCK_MGMT fm\n" +
                "\tINNER JOIN ET.[SITE] Farm\n" +
                "\t\tON fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "\t\tAND Farm.isActive = 1\n" +
                "\t\tAND Farm.isDeleted = 0\n" +
                "\tINNER JOIN FLOCK_HOUSE_DETAILS fhd\n" +
                "\t\tON fm.ID = fhd.FLOCK_ID\n" +
                "\t\tAND fhd.isActive = 1\n" +
                "\t\tAND fhd.isDeleted = 0\n" +
                "\tINNER JOIN ET.[SITE] House\n" +
                "\t\tON fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "\t\tAND House.isActive = 1\n" +
                "\t\tAND House.isDeleted = 0\n" +
                "\tLEFT JOIN FLOCK_SETTLEMENT FS\n" +
                "\t\tON FS.FLOCK_ID = fm.ID\n" +
                "\t\tAND FS.IS_ACTIVE = 1\n" +
                "\t\tAND FS.Is_DELETED = 0\n" +
                "\tWHERE fm.BIRD_SIZE_ID IS NOT NULL),\n" +
                "CTESamplingPlan\n" +
                "AS\n" +
                "(SELECT\n" +
                "\t\tDSCC.TARGET_AGE_RANGE_MIN  AS startDay\n" +
                "\t\t,DSCC.TARGET_AGE_RANGE_MAX AS EndDay\n" +
                "\t\t,fhd.FLOCK_ID\n" +
                "\t\t,Complex.siteUniqueNumber INTERNAL_SITE_ID\n" +
                "\t\t,DSCC.FLOCK_HOUSE_DETAILS_ID\n" +
                "\tFROM DS_COMPLEX_CYCLING_CONFIG_NEW_TEMP DSCC\n" +
                "\tINNER JOIN ET.[SITE] Complex\n" +
                "\t\tON DSCC.INTERNAL_SITE_ID = Complex.siteId\n" +
                "\t\tAND Complex.isActive = 1\n" +
                "\t\tAND Complex.isDeleted = 0\n" +
                "\tINNER JOIN FLOCK_HOUSE_DETAILS fhd\n" +
                "\t\tON DSCC.FLOCK_HOUSE_DETAILS_ID = fhd.ID\n" +
                "\t\tAND fhd.isActive = 1\n" +
                "\t\tAND fhd.isDeleted = 0\n" +
                "\tGROUP BY\n" +
                "\t   DSCC.TARGET_AGE_RANGE_MIN\n" +
                "\t   ,DSCC.TARGET_AGE_RANGE_MAX\n" +
                "\t   ,fhd.FLOCK_ID\n" +
                "\t   ,Complex.siteUniqueNumber\n" +
                "\t   ,DSCC.FLOCK_HOUSE_DETAILS_ID\n" +
                ")\n" +
                "SELECT\n" +
                "\t\tcd.T_RUN_ID\n" +
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
                "\tAND cd.COLLECTION_DATE <= COALESCE(flk.PROCESSING_DATE, flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))\n" +
                "\tAND DATEDIFF(DAY, flk.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 \n" +
                "\t\t<= DATEDIFF(DAY, flk.PLACEMENT_DATE, COALESCE(flk.PROCESSING_DATE, flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))) + 1\n" +
                "\tAND cd.HOUSE_ID = HOUSE_UNIQUE_NUMBER\n" +
                "\tAND cd.FARM_ID = FARM_UNIQUE_NUMBER)\n" +
                "AND NOT EXISTS (SELECT\n" +
                "\t\t1\n" +
                "\tFROM CTESamplingPlan DSCC\n" +
                "\tINNER JOIN CTEFlock F\n" +
                "\t\tON DSCC.FLOCK_ID = F.ID\n" +
                "\t\tAND DSCC.FLOCK_HOUSE_DETAILS_ID = f.FLOCK_HOUSE_DETAILS_ID\n" +
                "\tWHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "\tAND cd.HOUSE_ID = F.HOUSE_UNIQUE_NUMBER)";

        return NoCyclingConfigQuery;
    }


    public static String getOutofIntervalRangeQuery(String viewName) {
        String outOfIntervalRangeQuery = "WITH CTEFlock\n" +
                "AS\n" +
                "(SELECT\n" +
                "\t\tfm.ID\n" +
                "\t   ,fm.FARM_INTERNAL_ID\n" +
                "\t   ,fhd.HOUSE_INTERNAL_ID\n" +
                "\t   ,House.siteUniqueNumber AS HOUSE_UNIQUE_NUMBER\n" +
                "\t   ,Farm.siteUniqueNumber AS FARM_UNIQUE_NUMBER\n" +
                "\t   ,FS.PROCESSING_DATE\n" +
                "\t   ,fhd.PLACEMENT_DATE\n" +
                "\t   ,fhd.HARVEST_DATE\n" +
                "\t   ,fhd.ID AS FLOCK_HOUSE_DETAILS_ID\n" +
                "\tFROM FLOCK_MGMT fm\n" +
                "\tINNER JOIN ET.[SITE] Farm\n" +
                "\t\tON fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "\t\tAND Farm.isActive = 1\n" +
                "\t\tAND Farm.isDeleted = 0\n" +
                "\tINNER JOIN FLOCK_HOUSE_DETAILS fhd\n" +
                "\t\tON fm.ID = fhd.FLOCK_ID\n" +
                "\t\tAND fhd.isActive = 1\n" +
                "\t\tAND fhd.isDeleted = 0\n" +
                "\tINNER JOIN ET.[SITE] House\n" +
                "\t\tON fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "\t\tAND House.isActive = 1\n" +
                "\t\tAND House.isDeleted = 0\n" +
                "\tLEFT JOIN FLOCK_SETTLEMENT FS\n" +
                "\t\tON FS.FLOCK_ID = fm.ID\n" +
                "\t\tAND FS.IS_ACTIVE = 1\n" +
                "\t\tAND FS.Is_DELETED = 0\n" +
                "\tWHERE fm.BIRD_SIZE_ID IS NOT NULL),\n" +
                "CTESamplingPlan\n" +
                "AS\n" +
                "(SELECT\n" +
                "\t\tDSCC.TARGET_AGE_RANGE_MIN  AS startDay\n" +
                "\t\t,DSCC.TARGET_AGE_RANGE_MAX AS EndDay\n" +
                "\t\t,fhd.FLOCK_ID\n" +
                "\t\t,Complex.siteUniqueNumber AS INTERNAL_SITE_ID\n" +
                "\t\t,DSCC.FLOCK_HOUSE_DETAILS_ID\n" +
                "\tFROM DS_COMPLEX_CYCLING_CONFIG_NEW_TEMP DSCC\n" +
                "\tINNER JOIN ET.[SITE] Complex\n" +
                "\t\tON DSCC.INTERNAL_SITE_ID = Complex.siteId\n" +
                "\t\tAND Complex.isActive = 1\n" +
                "\t\tAND Complex.isDeleted = 0\n" +
                "\tINNER JOIN FLOCK_HOUSE_DETAILS fhd\n" +
                "\t\tON DSCC.FLOCK_HOUSE_DETAILS_ID = fhd.ID\n" +
                "\t\tAND fhd.isActive = 1\n" +
                "\t\tAND fhd.isDeleted = 0\n" +
                "\tGROUP BY\n" +
                "\t   DSCC.TARGET_AGE_RANGE_MIN\n" +
                "\t   ,DSCC.TARGET_AGE_RANGE_MAX\n" +
                "\t   ,fhd.FLOCK_ID\n" +
                "\t   ,Complex.siteUniqueNumber\n" +
                "\t   ,DSCC.FLOCK_HOUSE_DETAILS_ID\n" +
                ")\n" +
                "SELECT\n" +
                "\t\tcd.T_RUN_ID\n" +
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
                "\tAND cd.COLLECTION_DATE <= COALESCE(flk.PROCESSING_DATE, flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))\n" +
                "\tAND DATEDIFF(DAY, flk.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 \n" +
                "\t\t<= DATEDIFF(DAY, flk.PLACEMENT_DATE, COALESCE(flk.PROCESSING_DATE, flk.HARVEST_DATE, DATEADD(DAY, 63, flk.PLACEMENT_DATE))) + 1\n" +
                "\tAND cd.HOUSE_ID = HOUSE_UNIQUE_NUMBER\n" +
                "\tAND cd.FARM_ID = FARM_UNIQUE_NUMBER)\n" +
                "AND EXISTS (SELECT\n" +
                "\t\t1\n" +
                "\tFROM CTESamplingPlan DSCC\n" +
                "\tINNER JOIN CTEFlock F\n" +
                "\t\tON DSCC.FLOCK_ID = F.ID\n" +
                "\t\tAND DSCC.FLOCK_HOUSE_DETAILS_ID = f.FLOCK_HOUSE_DETAILS_ID\n" +
                "\tWHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "\tAND cd.HOUSE_ID = F.HOUSE_UNIQUE_NUMBER\n" +
                "\t)\n" +
                "AND NOT EXISTS (SELECT\n" +
                "\t\t1\n" +
                "\tFROM CTESamplingPlan DSCC\n" +
                "\tINNER JOIN CTEFlock F\n" +
                "\t\tON DSCC.FLOCK_ID = F.ID\n" +
                "\t\tAND DSCC.FLOCK_HOUSE_DETAILS_ID = f.FLOCK_HOUSE_DETAILS_ID\n" +
                "\tWHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "\tAND cd.HOUSE_ID = F.HOUSE_UNIQUE_NUMBER\n" +
                "\tAND DATEDIFF(DAY, F.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 <= DATEDIFF(DAY, F.PLACEMENT_DATE, COALESCE(F.PROCESSING_DATE, F.HARVEST_DATE, DATEADD(DAY, 63, F.PLACEMENT_DATE))) + 1\n" +
                "\tAND DATEDIFF(DAY, F.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 BETWEEN DSCC.startDay AND ISNULL(DSCC.EndDay, DATEDIFF(DAY, F.PLACEMENT_DATE, COALESCE(F.PROCESSING_DATE, F.HARVEST_DATE, DATEADD(DAY, 63, F.PLACEMENT_DATE))) + 1))\n";
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
                "AND NOT EXISTS (SELECT\n" +
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
                "\tLEFT JOIN FLOCK_SETTLEMENT FS\n" +
                "\t\tON FS.FLOCK_ID = fm.ID\n" +
                "\t\tAND FS.IS_ACTIVE = 1\n" +
                "\t\tAND FS.Is_DELETED = 0\n" +
                "\tWHERE fm.BIRD_SIZE_ID IS NOT NULL\n" +
                "\tAND cd.COLLECTION_DATE > fhd.PLACEMENT_DATE\n" +
                "\tAND cd.COLLECTION_DATE <= COALESCE(FS.PROCESSING_DATE, fhd.HARVEST_DATE, DATEADD(DAY, 63, fhd.PLACEMENT_DATE))\n" +
                "\tAND DATEDIFF(DAY, fhd.PLACEMENT_DATE, cd.COLLECTION_DATE) + 1 <= DATEDIFF(DAY, fhd.PLACEMENT_DATE, COALESCE(FS.PROCESSING_DATE, fhd.HARVEST_DATE, DATEADD(DAY, 63, fhd.PLACEMENT_DATE))) + 1\n" +
                "\tAND cd.HOUSE_ID = house.siteUniqueNumber\n" +
                "\tAND cd.FARM_ID = Farm.siteUniqueNumber)";
        return noflockAssociationQuery;
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
