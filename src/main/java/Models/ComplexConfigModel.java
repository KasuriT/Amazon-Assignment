package Models;

import java.util.ArrayList;

import Test.Ancera.Test_Variables;

public class ComplexConfigModel {

	public String ComplexThreshold;
	public String HouseThreshold;
	public String LowerLimit;
	public String UpperLimit;

	
	public ComplexConfigModel() {

	}
	
	public static String organizationName = "TestCMSOrg_"+Test_Variables.dateYYYYMMDD;
	public static String organizationFarm1Name = "TestFarm1_"+Test_Variables.dateYYYYMMDD;
	public static String organizationHouse1Name = "TestHouse1_"+Test_Variables.dateYYYYMMDD;
	public static String vaccineName = "SinoVac_"+Test_Variables.dateYYYYMMDD;
	public static String feedName = "SinoFeed_"+Test_Variables.dateYYYYMMDD;
	
	

	
	public static ArrayList<ComplexConfigModel> CreateConfig() {
		ArrayList<ComplexConfigModel> lstComplexConfigModel = new ArrayList<ComplexConfigModel>();
		ComplexConfigModel objTmp = new ComplexConfigModel();

		////////Total OPG Small////////
		objTmp.ComplexThreshold = "50";
		objTmp.HouseThreshold = "50";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		////////Total OPG Medium////////
		objTmp.ComplexThreshold = "50";
		objTmp.HouseThreshold = "50";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		////////Total OPG Large////////
		objTmp.ComplexThreshold = "50";
		objTmp.HouseThreshold = "50";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "40000";
		objTmp.UpperLimit = "50000";
		lstComplexConfigModel.add(objTmp);	
			
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		
		////////Total OPG Pullets////////
		objTmp.ComplexThreshold = "50";
		objTmp.HouseThreshold = "50";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "60000";
		objTmp.UpperLimit = "70000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "40000";
		objTmp.UpperLimit = "50000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		////////Total OPG Broilers////////
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "60000";
		objTmp.UpperLimit = "70000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "40000";
		objTmp.UpperLimit = "50000";
		lstComplexConfigModel.add(objTmp);	
		
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		////////large OPG Small////////
		objTmp.ComplexThreshold = "50";
		objTmp.HouseThreshold = "50";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
		
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	

		
		////////Large OPG Medium////////
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
		
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "40000";
		objTmp.UpperLimit = "50000";
		lstComplexConfigModel.add(objTmp);	
		
		
		////////Large OPG Large////////
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
				
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "40000";
		objTmp.UpperLimit = "50000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		
		////////Large OPG Pullets////////
		objTmp.ComplexThreshold = "50";
		objTmp.HouseThreshold = "50";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
			
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "40000";
		objTmp.UpperLimit = "50000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "60000";
		objTmp.UpperLimit = "70000";
		lstComplexConfigModel.add(objTmp);	
		
		////////Large OPG Broiler////////
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "10000";
		objTmp.UpperLimit = "20000";
		lstComplexConfigModel.add(objTmp);	
			
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "60000";
		objTmp.UpperLimit = "70000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "20000";
		objTmp.UpperLimit = "30000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "30000";
		objTmp.UpperLimit = "40000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "40000";
		objTmp.UpperLimit = "50000";
		lstComplexConfigModel.add(objTmp);	
		
		objTmp.ComplexThreshold = "67";
		objTmp.HouseThreshold = "60";
		objTmp.LowerLimit = "50000";
		objTmp.UpperLimit = "60000";
		lstComplexConfigModel.add(objTmp);	
		
		return lstComplexConfigModel;
	}
	
}
