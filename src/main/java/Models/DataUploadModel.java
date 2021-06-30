package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.MetaData.DataUpload;

public class DataUploadModel {

	
	public String TestCaseName;
	public String TestCaseDescription;
	public String templateName;
	public String fileName;
	public String steps;
	public String passStep;
	public String failStep;
	public String AlertMessage;
	public ArrayList<ReportFilters> lstFilters;
	
	public static String OrganizationSiteID = "1001001";
	public static String RegionSiteID = "1001043";
	public static String SubRegionSiteID = "1001052";
	public static String ComplexSiteID = "1001159";
	public static String HouseSiteID = "1001066";
	public static String ProcessingPlantSiteID = "1001078";
	public static String FarmSiteID = "1033011";
	
	public DataUploadModel() {

	}

	
	public static ArrayList<DataUploadModel> FillData() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp = new DataUploadModel();
		
		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-36: Verify that user cannot upload Flock Metadata without Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata without Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(""));
		objTmp.steps = "Leave Site ID field empty and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata without Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata without Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-37: Verify that user cannot upload Flock Metadata without Placement Date";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata without Placement Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(FarmSiteID, ""));
		objTmp.steps = "Leave Placement Date field empty and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata without Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata without Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-40: Verify user cannot upload Flock Metadata with Organization Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Organization Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(OrganizationSiteID));
		objTmp.steps = "Enter Organization Site ID and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Organization Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Organization Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-41: Verify user cannot upload Flock Metadata with Region Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Region Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(RegionSiteID));
		objTmp.steps = "Enter Region Site ID and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Region Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Region Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-42: Verify user cannot upload Flock Metadata with Sub-Region Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Sub-Region ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SubRegionSiteID));
		objTmp.steps = "Enter Sub-Region Site ID and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Sub-Region Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Sub_Region Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-43: Verify user cannot upload Flock Metadata with Complex Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Complex Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ComplexSiteID));
		objTmp.steps = "Enter Complex Site ID and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Complex Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Complex Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-44: Verify user cannot upload Flock Metadata with House Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with House Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(HouseSiteID));
		objTmp.steps = "Enter House Site ID and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with House Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with House Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-45: Verify user cannot upload Flock Metadata with Processing Plant Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Processing Plant Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ProcessingPlantSiteID));
		objTmp.steps = "Enter Processing Plant Site ID and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Processing Plant Site ID successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Processing Plant Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-46: Verify user can upload Flock Metadata with Farm Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Farm Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(FarmSiteID, "6/09/2021"));
		objTmp.steps = "Enter Farm Site ID and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Farm Site ID successfully";
		objTmp.failStep = "User failed to upload Flock Metadata with Farm Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-47: Verify that user cannot upload Flock Metadata with Hatch Date greater than Placement Date";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Hatch Date greater than Placement Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(2, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-10", "2021-06-01"));
		objTmp.steps = "Enter Hatch Date greater than Placement Date and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Hatch Date greater than Placement Date successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Hatch Date greater than Placement Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-48: Verify that user can upload Flock Metadata with Hatch Date less than Placement Date";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Hatch Date less than Placement Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(2, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-09", "2021-06-10"));
		objTmp.steps = "Enter Hatch Date less than Placement Date and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Flock Metadata with Hatch Date less than Placement Date successfully";
		objTmp.failStep = "User was not able to upload Flock Metadata with Hatch Date less than Placement Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-49: Verify that user cannot upload Flock Metadata with Placement Date greater than Processing Date";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Placement Date greater than Processing Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(8, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-01", "2021-06-10"));
		objTmp.steps = "Enter Placement Date greater than Processing Date and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Placement Date greater than Processing Date successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Placement Date greater than Processing Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-50: Verify that user can upload Flock Metadata with Placement Date less than Processing Date";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Placement Date less than Processing Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(8, 3, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-10", "2021-06-01", "2021-05-25"));
		objTmp.steps = "Enter Placement Date less than Processing Date and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Flock Metadata with Placement Date less than Processing Date successfully";
		objTmp.failStep = "User was not able to upload Flock Metadata with Placement Date less than Processing Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-51: Verify that user cannot upload Flock Metadata with Organization Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Organization Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(OrganizationSiteID));
		objTmp.steps = "Enter Organization Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Organization Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Organization Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-52: Verify that user cannot upload Flock Metadata with Region Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Region Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(RegionSiteID));
		objTmp.steps = "Enter Region Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Region Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Region Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-53: Verify that user cannot upload Flock Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SubRegionSiteID));
		objTmp.steps = "Enter Sub-Region Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Sub-Region Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-54: Verify that user cannot upload Flock Metadata with Complex Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Complex Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ComplexSiteID));
		objTmp.steps = "Enter Complex Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Complex Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Complex Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-55: Verify that user cannot upload Flock Metadata with House Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with House Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(HouseSiteID));
		objTmp.steps = "Enter House Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with House Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with House Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-56: Verify that user cannot upload Flock Metadata with Farm Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Farm Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(FarmSiteID));
		objTmp.steps = "Enter Farm Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Farm Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Farm Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-57: Verify that user can upload Flock Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ProcessingPlantSiteID));
		objTmp.steps = "Enter Processing Plant Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Flock Metadata with Processing Plant Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-58: Verify that user cannot upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80"));
		objTmp.steps = "Enter Number of Birds DOA at Plant greater than Number of Birds Placed and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-59: Verify that user can upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("80", "100"));
		objTmp.steps = "Enter Number of Birds DOA at Plant less than Number of Birds Placed and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-60: Verify that user cannot upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(11, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80"));
		objTmp.steps = "Enter Number of Birds DOA at Plant greater than Number of Birds Processed and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-61: Verify that user can upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(11, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("80", "100"));
		objTmp.steps = "Enter Number of Birds Processed less than Number of Birds Placed and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-62: Verify that user cannot upload Flock Metadata with Total Weight_Condemned_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 19, 20));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "100", "100"));
		objTmp.steps = "Enter Total Weight_Condemned_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-63: Verify that user can upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 19, 20));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "80", "40"));
		objTmp.steps = "Enter Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-64: Verify that user cannot upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 20, 21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "100", "100"));
		objTmp.steps = "Enter Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-65: Verify that user can upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 20, 21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "80", "40"));
		objTmp.steps = "Enter Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		return lstDataUploadModel;
	}
	
	
	public static ArrayList<DataUploadModel> FillDataSitePerformance() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp = new DataUploadModel();
		
		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-66: Verify that user cannot upload Site Performance with Number of Birds Sold greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with Number of Birds Sold greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(3, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80"));
		objTmp.steps = "Enter Number of Birds Sold greater than Number of Birds Placed";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with Number of Birds Sold greater than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with Number of Birds Sold greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-67: Verify that user can upload Site Performance with NUM_BIRDS_SOLD less than NUM_BIRDS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with NUM_BIRDS_SOLD less than NUM_BIRDS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(3, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("80", "100"));
		objTmp.steps = "Enter with NUM_BIRDS_SOLD less than NUM_BIRDS_PLACED";
		objTmp.AlertMessage = DataUpload.sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with NUM_BIRDS_SOLD less than NUM_BIRDS_PLACED successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with NUM_BIRDS_SOLD less than NUM_BIRDS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-68: Verify that user cannot upload Site Performance with DOA_PLANT_PERC greater than NUM_BIRDS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with DOA_PLANT_PERC greater than NUM_BIRDS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(12, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "80"));
		objTmp.steps = "Enter Number DOA_PLANT_PERC greater than NUM_BIRDS_PLACED";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with DOA_PLANT_PERC greater than NUM_BIRDS_PLACED successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with DOA_PLANT_PERC greater than NUM_BIRDS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-69: Verify that user can upload Site Performance with Percentage DOA less than NUM_BIRDS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with Percentage DOA less than NUM_BIRDS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(12, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "100"));
		objTmp.steps = "Enter Percentage DOA less than NUM_BIRDS_PLACED";
		objTmp.AlertMessage = DataUpload.sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with Percentage DOA less than NUM_BIRDS_PLACED successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with Percentage DOA less than NUM_BIRDS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-70: Verify that user cannot upload Site Performance with MORTALITY_NUM_BIRDS greater than NUM_BIRDS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with MORTALITY_NUM_BIRDS greater than NUM_BIRDS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(15, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "80"));
		objTmp.steps = "Enter MORTALITY_NUM_BIRDS greater than NUM_BIRDS_PLACED";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with MORTALITY_NUM_BIRDS greater than NUM_BIRDS_PLACED successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with MORTALITY_NUM_BIRDS greater than NUM_BIRDS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-71: Verify that user can upload Site Performance with MORTALITY_NUM_BIRDS less than NUM_BIRDS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with MORTALITY_NUM_BIRDS less than NUM_BIRDS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(15, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "120"));
		objTmp.steps = "Enter MORTALITY_NUM_BIRDS less than NUM_BIRDS_PLACED";
		objTmp.AlertMessage = DataUpload.sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with MORTALITY_NUM_BIRDS less than NUM_BIRDS_PLACED successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with MORTALITY_NUM_BIRDS less than NUM_BIRDS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-72: Verify that user cannot upload Site Performance with Feed Conversion Ratio <1";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with Feed Conversion Ratio <1";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("0.5"));
		objTmp.steps = "Enter Feed Conversion Ratio <1";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with Feed Conversion Ratio <1 successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with Feed Conversion Ratio <1";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-73: Verify that user can upload Site Performance with Feed Conversion Ratio >1";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with Feed Conversion Ratio >1";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("1.5"));
		objTmp.steps = "Enter Feed Conversion Ratio >1";
		objTmp.AlertMessage = DataUpload.sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with Feed Conversion Ratio >1 successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with Feed Conversion Ratio >1";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
			
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-74: Verify that user cannot upload Site Performance with NUM_BIRDS_CONDEMNED_WHOLE greater than NUM_BIRDS_SOLD";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with NUM_BIRDS_CONDEMNED_WHOLE greater than NUM_BIRDS_SOLD";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "50"));
		objTmp.steps = "Enter NUM_BIRDS_CONDEMNED_WHOLE greater than NUM_BIRDS_SOLD";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with NUM_BIRDS_CONDEMNED_WHOLE greater than NUM_BIRDS_SOLD successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with NUM_BIRDS_CONDEMNED_WHOLE greater than NUM_BIRDS_SOLD";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-75: Verify that user can upload Site Performance with NUM_BIRDS_CONDEMNED_WHOLE less than NUM_BIRDS_SOLD";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with NUM_BIRDS_CONDEMNED_WHOLE less than NUM_BIRDS_SOLD";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "100"));
		objTmp.steps = "Enter NUM_BIRDS_CONDEMNED_WHOLE less than NUM_BIRDS_SOLD";
		objTmp.AlertMessage = DataUpload.sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with NUM_BIRDS_CONDEMNED_WHOLE less than NUM_BIRDS_SOLD successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with NUM_BIRDS_CONDEMNED_WHOLE less than NUM_BIRDS_SOLD";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-76: Verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(27, 25, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-77: Verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(27, 25, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.AlertMessage = DataUpload.sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-78: Verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(28, 26, 24));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-79: Verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(28, 26, 24));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.AlertMessage = DataUpload.sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		return lstDataUploadModel;
	}
	
	
	public static ArrayList<DataUploadModel> FillDataSampleMetaData() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp = new DataUploadModel();
		
		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-04: Verify that user cannot upload file leaving Key field/s empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file leaving Key field/s empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "A"));
		objTmp.steps = "Leave Key field empty";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file leaving Key field empty successfully";
		objTmp.failStep = "User was able to upload file leaving Key field empty";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-05: Verify that user cannot upload file with case sensitive duplicate values";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with case sensitive duplicate values";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "A", "a"));
		objTmp.steps = "Enter duplicate values as case sensitive";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with case sensitive duplicate values successfully";
		objTmp.failStep = "User was able to upload file with case sensitive duplicate values";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-06: Verify that user can upload file with entering valid data in Key field/s";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with entering valid data in Key field/s";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A0732302", "", ""));
		objTmp.steps = "Enter valid data in Key field/s";
		objTmp.AlertMessage = DataUpload.sampleMetadataFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-07: Verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A0732302", "A0732302"));
		objTmp.steps = "Enter same value in Key field/s for 2 rows";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with values in Key field/s not being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s not being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-08: Verify that user can upload file with values in Key field/s being unique";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with values in Key field/s being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A0732302", "A0732303"));
		objTmp.steps = "Enter unique values for Key field";
		objTmp.AlertMessage = DataUpload.sampleMetadataFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with values in Key field/s being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-09: Verify that user cannot upload file with String datatype in Decimal datatype";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with String datatype in Decimal datatype";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A"));
		objTmp.steps = "Enter string value in decimal field";
		objTmp.AlertMessage = "Errors found in "+DataUpload.sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with String datatype in Decimal datatype successfully";
		objTmp.failStep = "User was able to upload file with String datatype in Decimal datatype";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-10: Verify that user can upload file with decimal value in Decimal datatype";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with decimal value in Decimal datatype";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("1.5"));
		objTmp.steps = "Enter decimal value in Decimal datatype";
		objTmp.AlertMessage = DataUpload.sampleMetadataFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with decimal value in Decimal datatype successfully";
		objTmp.failStep = "User was able to upload file with decimal value in Decimal datatype";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		return lstDataUploadModel;
	}
	
	
	public static ArrayList<DataUploadModel> FillDataSaveTemplate() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp = new DataUploadModel();
		
		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-22: Verify that user can save "+objTmp.templateName;
		objTmp.TestCaseDescription = "This test case will verify that user can save "+objTmp.templateName;
		objTmp.lstFilters = new ArrayList<>();
		objTmp.fileName = "Flock Metadata.xlsx";
		objTmp.templateName = "Flock Metadata";
		objTmp.steps = "Upload Flock Metadata";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-23: Verify that user can save "+objTmp.templateName;
		objTmp.TestCaseDescription = "This test case will verify that user can save "+objTmp.templateName;
		objTmp.lstFilters = new ArrayList<>();
		objTmp.fileName = "Sample Metadata Upload Template.xlsx";
		objTmp.templateName = "Sample Metadata";
		objTmp.steps = "Upload Sample Metadata";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-24: Verify that user can save "+objTmp.templateName;
		objTmp.TestCaseDescription = "This test case will verify that user can save "+objTmp.templateName;
		objTmp.lstFilters = new ArrayList<>();
		objTmp.fileName = "Weekly Site Performance.xlsx";
		objTmp.templateName = "Weekly Site Performance";
		objTmp.steps = "Upload Weekly Site Performance";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
		
		objTmp = new DataUploadModel();	
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-25: Verify that user can save "+objTmp.templateName;
		objTmp.TestCaseDescription = "This test case will verify that user can save "+objTmp.templateName;
		objTmp.lstFilters = new ArrayList<>();
		objTmp.fileName = "Estimated Assay Input Template.xlsx";
		objTmp.templateName = "Estimated Assay Input";
		objTmp.steps = "Upload Estimated Assay Input";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
	
		return lstDataUploadModel;
	}
	
	
}

