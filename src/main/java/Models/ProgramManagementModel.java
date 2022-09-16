package Models;

import java.util.ArrayList;

import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class ProgramManagementModel {

	public String TestCaseNameCreate;
	public String TestCaseNameUpdate;
	public String TestCaseNameDelete;
	public String Program;
	public String ProgramName;
	public String ProgramName_CSS;
	public String ProgramType;
	public String EditButtonPre;
	public String ButtonPost;
	public String DeleteButtonPre;

	public String steps;
	public String input;
	
	public static String VaccineProgramName = "Sino_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	public static String FeedProgramName = "Feed_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	public static String TreatmentProgramName = "Treatment_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	public static String BioshuttleProgramName = "Bioshuttle_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	
	public static String SupplierName = "China";
	public static String DescriptionName = "Vaccine Testing Program";
	
	public static String ComplexNameQA = "sprint 40";
	public static String ComplexNameUAT = "Pilgrim's Canton";
	public static String ComplexNameDEV = "Pilgrim's Canton";
	
	public static String createProgramTestCaseTitle = "Verify user can create";
	public static String updateProgramTestCaseTitle = "Verify user can update";
	public static String deleteProgramTestCaseTitle = "Verify user can delete";
	
	public static ArrayList<ProgramManagementModel> FillData() {
		ArrayList<ProgramManagementModel> lstProgramManagementModel = new ArrayList<ProgramManagementModel>();
		ProgramManagementModel objTmp;
		
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Vaccine Programs";
		objTmp.TestCaseNameCreate = "AN-Program-11: "+createProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameUpdate = "AN-Program-15: "+updateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDelete = "AN-Program-19: "+deleteProgramTestCaseTitle+objTmp.Program;
		objTmp.ProgramName = VaccineProgramName;
		objTmp.ProgramName_CSS =Test_Elements.programVaccineProgramNameCol;
		objTmp.ProgramType = "Vaccine";
		objTmp.EditButtonPre = Test_Elements.programVaccineEdit;
		objTmp.DeleteButtonPre = Test_Elements.programVaccineDelete;
		objTmp.ButtonPost = Test_Elements.programVaccine_ID;
		lstProgramManagementModel.add(objTmp);	
				
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Feed Programs";
		objTmp.TestCaseNameCreate = "AN-Program-12: "+createProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameUpdate = "AN-Program-16: "+updateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDelete = "AN-Program-20: "+deleteProgramTestCaseTitle+objTmp.Program;
		objTmp.ProgramName = FeedProgramName;
		objTmp.ProgramName_CSS =Test_Elements.programVaccineProgramNameCol;
		objTmp.EditButtonPre = Test_Elements.programFeedEdit;
		objTmp.DeleteButtonPre = Test_Elements.programFeedDelete;
		objTmp.ButtonPost = Test_Elements.programFeed_ID;
		objTmp.ProgramType = "Feed";
		lstProgramManagementModel.add(objTmp);	
	
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Treatment";
		objTmp.TestCaseNameCreate = "AN-Program-13: "+createProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameUpdate = "AN-Program-17: "+updateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDelete = "AN-Program-21: "+deleteProgramTestCaseTitle+objTmp.Program;
		objTmp.ProgramName = TreatmentProgramName;
		objTmp.ProgramName_CSS =Test_Elements.programVaccineProgramNameCol;
		objTmp.EditButtonPre = Test_Elements.programTreatmentEdit;
		objTmp.DeleteButtonPre = Test_Elements.programTreatmentDelete;
		objTmp.ButtonPost = Test_Elements.programTreatment_ID;
		objTmp.ProgramType = "Treatment";
		lstProgramManagementModel.add(objTmp);	
			
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Vaccine with Bioshuttle";
		objTmp.TestCaseNameCreate = "AN-Program-14: "+createProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameUpdate = "AN-Program-18: "+updateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDelete = "AN-Program-22: "+deleteProgramTestCaseTitle+objTmp.Program;
		objTmp.ProgramName = BioshuttleProgramName;
		objTmp.ProgramName_CSS =Test_Elements.programVaccineProgramNameCol;
		objTmp.ProgramType = "Vaccine with Bioshuttle";
		objTmp.EditButtonPre = Test_Elements.programBioshuttleEdit;
		objTmp.DeleteButtonPre = Test_Elements.programBioshuttleDelete;
		objTmp.ButtonPost = Test_Elements.programBioshuttle_ID;
		lstProgramManagementModel.add(objTmp);

		return lstProgramManagementModel;
	}
	
	
}
