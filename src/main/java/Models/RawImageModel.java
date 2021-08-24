package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Variables;

public class RawImageModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String pathogen;
	public String cartridgeID;
	public String lane;
	public String checksum;
	public String fileName;
	public ArrayList<ReportFilters> lstFilters;
	public boolean runStartAssay;

	public RawImageModel() {

	}

	public static ArrayList<RawImageModel> FillData() {
		ArrayList<RawImageModel> lstRawImageModel = new ArrayList<RawImageModel>();	
		RawImageModel objTmp;
		ReportFilters objFilter;

		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-Coccidia-01: Ingest a run for Salmonella and upload valid sample matrix and verify the result are updated accordingly";
		objTmp.TestCaseDescription = "This test case will verify that uploading valid sample matrix updates results accordingly";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridgeCocci"+Test_Variables.date0;
		objTmp.lane = "1";
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.runStartAssay = true;
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"TestAut-Raw-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);

		return lstRawImageModel;
	}	
}

