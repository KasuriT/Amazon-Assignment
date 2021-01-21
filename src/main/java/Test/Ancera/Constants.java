package Test.Ancera;

public class Constants {

//	public static String url = "http://192.168.5.45:3300";
	public static String api = "http://192.168.5.45:3400/api/ancerainput/v1";
	public static String url = "https://ie-uat.ancera.com";    //uat url
//	public static String api = "https://ie-uat.ancera.com:5510/api/ancerainput/v1";   //uat api
	
	public static String url_login = url+"/auth/sign-in";
	public static String url_fp = url+"/auth/forgot-password";
	public static String url_home = url+"/home";
	public static String url_user = url+"/home/admin/users";
	public static String url_organization = url+"/home/admin/organizations";
	public static String url_access = url+"/home/admin/roles";
	public static String url_reportsManagement = url+"/home/admin/reportroles";
	public static String url_piperManagement = url+"/home/admin/piper";
	public static String url_alert = url+"/home/admin/alerts";
	public static String url_agreementManagement = url+"/home/admin/user-agreement";
	public static String url_dataTemplate = url+"/home/metadata/dataformat";
	public static String url_dataUpload = url+"/home/client/dataupload";
	public static String url_reports = url+"/home/reports/explore";
	public static String url_SalmonellaLog = url+"/home/reports/log/salmonella-log?report=Salmonella%20Log&id=1&code=0001";
	public static String url_CoccidiaLog = url+"/home/reports/log/coccidia-log?report=Coccidia%20Log&id=4&code=0002";
	public static String url_CoccidiaTimeline = url+"/home/reports/timeline/coccidia-timeline?report=Coccidia%20Timeline%20Internal&id=9&code=0004";
	public static String url_MPNSalmonellaLog = url+"/home/reports/log/mpn-log?report=MPN%20Salmonella%20Log&id=15&code=0006";
	public static String url_LocalDashboard = url+"/home/reports/wireframe/wireframe?report=Local%20Dashboard&id=16&code=0009";
	public static String url_ExternalCoccidiaLog = url+"/home/reports/log/external-coccidia-log?report=External%20Coccidia%20log&id=18&code=0007";
	public static String url_ExternalSalmonellaLog = url+"/home/reports/log/external-salmonella-log?report=External%20Salmonella%20log&id=17&code=0008";
	public static String url_ExternalMPNSalmonellaLog = url+"/home/reports/log/external-mpn-salmonella-log?report=External%20MPN%20Salmonella%20Log&id=19&code=0010";

	public static String url_GmailSignin = "https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false#identifier";
	
	public static String ReportFilePath = "./target/Reports";
	public static String LoginReportPath = "/Screenshots/Login/LoginScreenShot";
	public static String ForgotPasswordReportPath ="/Screenshots/ForgotPassword/ForgotPasswordScreenShot";
	public static String ProfileSettingReportPath = "/Screenshots/ProfileSetting/ProfileSettingScreenShot";
	public static String UserManagementReportPath = "/Screenshots/Administration/UserManagementScreenShot";
	public static String AccessManagementReportPath = "/Screenshots/Administration/AccessManagementScreenShot";
	public static String OrgManagementReportPath = "/Screenshots/Administration/OrganizationManagementScreenShot";
	public static String ReportManagementReportPath = "/Screenshots/Administration/ReportsManagementScreenShot";
	public static String AgreementManagementReportPath = "/Screenshots/Administration/AgreementManagementScreenShot";
	public static String PiperManagementReportPath = "/Screenshots/Administration/PiperManagementScreenShot";
	public static String DataTemplateManagementReportPath = "/Screenshots/MetaData/DTManagementScreenShot";
	public static String DataUploadReportPath = "/Screenshots/MetaData/DTUploadScreenShot";
	
	public static String SalmonellaReportPath = "/Screenshots/Reports/SalmonellaScreenShot";
	public static String ExternalSalmonellaReportPath = "/Screenshots/Reports/ExternalSalmonellaScreenShot";
	public static String CoccidiaReportPath = "/Screenshots/Reports/CoccidiaScreenShot";
	public static String ExternalCoccidiaReportPath = "/Screenshots/Reports/ExternalCoccidiaScreenShot";
	public static String CoccidiaTimelineReportPath = "/Screenshots/Reports/CoccidiaTimelineScreenShot";
	
	public static String api_login = api+"/login";
	public static String api_announcement = api+"/runfilelist";
	public static String api_FileUpload = api+"/fileupload";
	
	
}
