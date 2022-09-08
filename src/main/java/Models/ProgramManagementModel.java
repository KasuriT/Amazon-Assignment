package Models;

import java.util.ArrayList;
import Test.Ancera.Test_Variables;

public class ProgramManagementModel {

	public String TestCaseName;
	public String Program;
	public String ProgramName;
	public String ProgramType;
	//public ArrayList<ReportFilters> lstFilters;

	public String steps;
	public String input;
	
	public static String VaccineProgramName = "Sino_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	public static String FeedProgramName = "Feed_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	public static String TreatmentProgramName = "Treatment_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	public static String BioshuttleProgramName = "Bioshuttle_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	
	public static String SupplierName = "China";
	
	public static String ComplexNameQA = "sprint 40";
	public static String ComplexNameUAT = "Pilgrim's Canton";
	public static String ComplexNameDEV = "Pilgrim's Canton";
	
	public static String applyFilterTitle = "Verify user can create";
	
	
	public static ArrayList<ProgramManagementModel> FillData() {
		ArrayList<ProgramManagementModel> lstProgramManagementModel = new ArrayList<ProgramManagementModel>();
		ProgramManagementModel objTmp;;
		
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Vaccine Programs";
		objTmp.TestCaseName = "AN-Program-11: "+applyFilterTitle+objTmp.Program;
		objTmp.ProgramName = VaccineProgramName;
		objTmp.ProgramType = "Vaccine";
		lstProgramManagementModel.add(objTmp);	
				
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Feed Programs";
		objTmp.TestCaseName = "AN-Program-12: "+applyFilterTitle+objTmp.Program;
		objTmp.ProgramName = FeedProgramName;
		objTmp.ProgramType = "Feed";
		lstProgramManagementModel.add(objTmp);	
	
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Treatment";
		objTmp.TestCaseName = "AN-Program-13: "+applyFilterTitle+objTmp.Program;
		objTmp.ProgramName = TreatmentProgramName;
		objTmp.ProgramType = "Treatment";
		lstProgramManagementModel.add(objTmp);	
			
		objTmp = new ProgramManagementModel();
		objTmp.Program = "Vaccine with Bioshuttle";
		objTmp.TestCaseName = "AN-Program-14: "+applyFilterTitle+objTmp.Program;
		objTmp.ProgramName = BioshuttleProgramName;
		objTmp.ProgramType = "Vaccine with Bioshuttle";
		lstProgramManagementModel.add(objTmp);

		return lstProgramManagementModel;
	}
	
	
}
