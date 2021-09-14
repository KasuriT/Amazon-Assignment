package Models;

import java.util.ArrayList;

import Test.Ancera.Test_Variables;

public class AlertManagementModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String steps;
	public String passStep;
	public String failStep;

	public String AlertInfo;
	public String AlertDesc;
	public String DataSource;
	public String Report;
	public String AlertVariable;
	public String AlertMode;
	public String Condition;
	public String Text;
	public String minValue;
	public String maxValue;
	public String AggregateMode;
	public String Deviation;
	public String Notify;
	public String Days;
	public String customDays;
	
	public boolean isAlertMode;
	public boolean isBetween;
	
	public ArrayList<ReportFilters> lstFilters;
	
	public AlertManagementModel() {

	}

	public static String applyFilterTitle = "Verify user can apply ";
	public static String applyFilterDesc = "This test case will verify that filtered records are displayed in table on applying ";

	
	public static ArrayList<AlertManagementModel> FillData() {
		ArrayList<AlertManagementModel> lstAlertManagementModel = new ArrayList<AlertManagementModel>();
		AlertManagementModel objTmp;
		ReportFilters objFilter;
	/*	
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-02: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert1"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Threshold Between (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10";
		objTmp.maxValue = "2000";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-04: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert2"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Threshold Less Than (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-06: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert3"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Threshold Greater Than (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-08: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert4"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average >= (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-10: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert5"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average < (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-12: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert6"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation > (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-14: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert7"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation < (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Less than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-16: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert8"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation >= (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-18: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert9"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Daily (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-20: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert10"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-22: Create alert of data source 'Piper Log', Alert Variable 'Salmonella' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Salmonella' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert11"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Salmonella)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Salmonella";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Salmonella' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-24: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert12"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Threshold Between (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10";
		objTmp.maxValue = "2000";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-26: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert13"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Threshold Less Than (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-28: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert14"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Threshold Greater Than (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-30: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert15"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average >= (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-32: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert16"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average < (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-34: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert17"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation > (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-36: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert18"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation < (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-38: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert19"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation >= (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-40: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert20"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Daily (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-42: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert21"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-44: Create alert of data source 'Piper Log', Alert Variable 'Coccidia' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Coccidia' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert22"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Coccidia)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Coccidia";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Coccidia' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-46: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert23"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Threshold 'Between'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10"; 
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-48: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert24"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Threshold 'Less Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
	
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-50: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert25"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Threshold 'Greater Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
//		objTmp = new AlertManagementModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-AlertM-52: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average >='";
//		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average >='";
//		objTmp.lstFilters = new ArrayList<>();
//		objTmp.AlertInfo = "Alert26"+Test_Variables.date0;
//		objTmp.AlertDesc = "Farm Performance Feed Conversion Aggregate '>='";
//		objTmp.DataSource = "Farm Performance";
//		objTmp.AlertVariable = "Feed Conversion";
//		objTmp.AlertMode = "Aggregate";
//		objTmp.AggregateMode = "Moving Average";
//		objTmp.Condition = "Greater than or equal to";
//		objTmp.Text = "10";
//		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average >='";
//		objTmp.lstFilters.add(objFilter);
//		lstAlertManagementModel.add(objTmp);
//		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-54: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert27"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Aggregate '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	
		
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-56: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert28"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Standard Deviation '>'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
			
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-58: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert29"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Standard Deviation '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-60: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert30"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Standard Deviation '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-62: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert31"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Absence 'Daily'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-64: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert32"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Farm Perfromance)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-66: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert33"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Farm Performance)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		/////////////////////
		
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-68: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert34"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Threshold 'Between'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight/Bird";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10"; 
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-70: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert35"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Threshold 'Less Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
	
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-72: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert36"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Threshold 'Greater Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-74: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert37"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Aggregate '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-76: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert38"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Aggregate '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	
		
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-78: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert39"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Standard Deviation '>'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
			
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-80: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert40"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Standard Deviation '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-82: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert41"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Standard Deviation '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		*/
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-84: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert31"+Test_Variables.date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Absence 'Daily'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "42";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-86: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert43"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Average Weight)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-88: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert44"+Test_Variables.date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Average Weight)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		return lstAlertManagementModel;
	}
	
	
	
}
