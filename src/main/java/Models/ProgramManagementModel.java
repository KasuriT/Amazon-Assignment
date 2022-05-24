package Models;

import java.util.ArrayList;

import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class ProgramManagementModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public ArrayList<ReportFilters> lstFilters;
	public boolean paginationExist;
	public boolean paginationLastPage;
	public boolean paginationNextPage;
	public boolean paginationFirstPage;
	public boolean paginationPreviousPage;
	public boolean sortLogic1;
	public boolean sortLogic2;
	public boolean startWith;
	public boolean endsWith;
	public boolean contains;
	public boolean viewAccess;
	public boolean unviewAccess;
	public boolean sortDescFirst;
	public boolean errorCase;
	public String steps;
	public String input;
	
	public static String VaccineProgramName = "Sino_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;
	public static String FeedProgramName = "Feed_"+Test_Variables.dateYYYYMMDD+"_"+Test_Variables.date0;

	
}
