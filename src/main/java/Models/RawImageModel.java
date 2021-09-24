package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Variables;

public class RawImageModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String pathogen;
	public String cartridgeID;
	public int lane;
	public String run_id;
	public String checksum;
	public String base64fileName;
	public String fileName;
	public String InstrumentID;
	public String fileType;
	public ArrayList<ReportFilters> lstFilters;
	public boolean runStartAssay;
	public boolean checkLog;
	public boolean isErrorCode;
	public boolean isPositive;
	public String runMode;
	public String runType;
	public String countOutcome;

	public static String fileAbsolutePath = "C:\\Users\\User\\Downloads\\RawImages\\";
	
	public RawImageModel() {

	}

	public static ArrayList<RawImageModel> FillData() {
		ArrayList<RawImageModel> lstRawImageModel = new ArrayList<RawImageModel>();	
		RawImageModel objTmp;
		ReportFilters objFilter;

		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 1;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L01S01.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = true;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_1";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 2;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L02S02.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_2";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);

		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 3;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L03S03.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_3";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		

		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 4;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L04S04.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_4";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 5;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L05S05.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_5";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 6;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L06S06.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_6";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 7;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L07S07.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_7";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 8;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L08S08.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_8";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 9;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L09S09.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_9";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 10;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L10S10.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_10";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 11;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L11S11.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = false;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_11";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 12;
		objTmp.checksum = "0113874a55889c461f7468d0900358e3da182072b7c250f16400982be26a22a5";
		objTmp.base64fileName = fileAbsolutePath+"base64_1.txt";
		objTmp.fileName = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_L12S12.bmp";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = true;
		objTmp.isErrorCode = false;
		objTmp.fileType = "bmp";
		objTmp.countOutcome = "";		
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0+"_12";
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-1"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
				
		//////////////////////////////Error Code Cases///////////////////////////////////////
			
		objTmp = new RawImageModel();
		objFilter = new ReportFilters();
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Coccidia";
		objTmp.cartridgeID = "TestCartridge-"+Test_Variables.date0;
		objTmp.lane = 1;
		objTmp.checksum = "";
		objTmp.base64fileName = "";
		objTmp.fileName = "";
		objTmp.InstrumentID = "PSN0001";
		objTmp.runType = "Normal";
		objTmp.runMode = "1";
		objTmp.runStartAssay = false;
		objTmp.checkLog = true;
		objTmp.isErrorCode = true;
		objTmp.fileType = "";
		objTmp.countOutcome = "E062";
		objTmp.run_id = Test_Variables.dateYYYYMMDD+"-TestAut-2"+Test_Variables.date0+"_1";		
		objFilter.LstSampleID = new ArrayList<>(Arrays.asList(Test_Variables.dateYYYYMMDD+"-TestAut-2"+Test_Variables.date0));
		objTmp.lstFilters.add(objFilter);
		lstRawImageModel.add(objTmp);
		
		return lstRawImageModel;
	}	
}

