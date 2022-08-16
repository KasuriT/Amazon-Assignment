package Test.Ancera;

public class Constants {

//	public static String url = "https://ie-qa.ancera.com"; 
//	public static String api = "https://disapi-qa.ancera.com/api/ancerainput/v1";
//	public static String api = "https://ie-qa.ancera.com:5500/api/ancerainput/v1";
//	public static String api = "http://hipir-1.anceralabs.com/api/ancerainput/v1";
	
	public static String url = "https://ie-dev.ancera.com";
	public static String api = "https://ie-dev.ancera.com:5510/api/ancerainput/v1";

//	public static String url = "https://ie-uat.ancera.com";   
//	public static String api = "https://disapi-uat.ancera.com/api/ancerainput/v1"; 
	
	public static String url_login = url+"/auth/sign-in";
	public static String url_fp = url+"/auth/forgot-password";
	public static String url_home = url+"/home";
	public static String url_user = url+"/home/admin/users";
	public static String url_organization = url+"/home/admin/organizations";
	public static String url_access = url+"/home/admin/roles";
	public static String url_reportsManagement = url+"/home/admin/reportroles";
	public static String url_alert = url+"/home/admin/alerts";
	public static String url_agreementManagement = url+"/home/admin/user-agreement";
	public static String url_barcodeManagement = url+"/home/admin/barcode-generation";
	public static String url_complexConfig = url+"/home/admin/complex-cycling-config";
	public static String url_flockRegistration = url+"/home/admin/flock-management"; 
	public static String url_programManagement = url+"/home/admin/program";
	public static String url_piperManagement = url+"/home/admin/piper";
	public static String url_piperSoftware = url+"/home/admin/piper-software-management";
	public static String url_piperConfiguration = url+"/home/admin/piper-configuration";
	public static String url_dataTemplate = url+"/home/metadata/dataformat";
	public static String url_dataUpload = url+"/home/client/dataupload";
	public static String url_poultryManagement = url+"/home/metadata/poultry";
	public static String url_reports = url+"/home/reports/explore";

//	public static String url_SalmonellaLog = url+"/home/reports/log/salmonella-log?report=Salmonella%20Log&id=16&code=0001&type=1";  //qa
//	public static String url_CoccidiaLog = url+"/home/reports/log/coccidia-log?report=Coccidia%20Log&id=17&code=0002&type=2";   //qa
//	public static String url_SitesLog = url+"/home/reports/log/sites-log?report=Sites%20Log&id=244&code=0012&type=12";   //qa
	
//	public static String url_SalmonellaLog = url+"/home/reports/log/salmonella-log?report=Salmonella%20Log&id=1&code=0001&type=1";   //uat
//	public static String url_CoccidiaLog = url+"/home/reports/log/coccidia-log?report=Coccidia%20Log&id=4&code=0002&type=2";   //uat
//	public static String url_SitesLog = url+"/home/reports/log/sites-log?report=Sites%20Log&id=112&code=0012&type=12";   //uat
	
	public static String url_SalmonellaLog = url+"/home/reports/log/salmonella-log?report=Salmonella%20Log&id=13&code=0001&type=1";  //dev
	public static String url_CoccidiaLog = url+"/home/reports/log/coccidia-log?report=Coccidia%20Log&id=9&code=0002&type=2";   //dev
	public static String url_SitesLog = url+"/home/reports/log/sites-log?report=Sites%20Log&id=18&code=0012&type=12";   //dev
	
	public static String url_CoccidiaTimeline = url+"/home/reports/timeline/coccidia-timeline?report=Coccidia%20Timeline&id=18&code=0004&type=4";
	public static String url_MPNSalmonellaLog = url+"/home/reports/log/mpn-log?report=MPN%20Salmonella%20Log&id=15&code=0006";
	public static String url_LocalDashboard = url+"/home/reports/wireframe/wireframe?report=Local%20Dashboard&id=23&code=0009&type=9";
	public static String url_ExternalCoccidiaLog = url+"/home/reports/log/external-coccidia-log?report=External%20Coccidia%20Log&id=18&code=0007&type=7"; 
	public static String url_ExternalSalmonellaLog = url+"/home/reports/log/external-salmonella-log?report=External%20Salmonella%20log&id=22&code=0008&type=8";
	
	public static String url_ExternalMPNSalmonellaLog = url+"/home/reports/log/external-mpn-salmonella-log?report=External%20MPN%20Salmonella%20Log&id=19&code=0010";

	public static String url_GmailSignin = "https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false#identifier";
	
	public static String ReportFilePath = "./target/Reports";
	public static String LoginReportPath = "/Screenshots/Login/LoginScreenShot";
	public static String AutoLoginReportPath = "/Screenshots/Login/AutoLoginScreenShot";
	public static String ForgotPasswordReportPath ="/Screenshots/ForgotPassword/ForgotPasswordScreenShot";
	public static String ProfileSettingReportPath = "/Screenshots/ProfileSetting/ProfileSettingScreenShot";
	public static String UserManagementReportPath = "/Screenshots/Administration/UserManagementScreenShot";
	public static String AccessManagementReportPath = "/Screenshots/Administration/AccessManagementScreenShot";
	public static String OrgManagementReportPath = "/Screenshots/Administration/OrganizationManagementScreenShot";
	public static String ReportManagementReportPath = "/Screenshots/Administration/ReportsManagementScreenShot";
	public static String AgreementManagementReportPath = "/Screenshots/Administration/AgreementManagementScreenShot";
	public static String AlertManagementReportPath = "/Screenshots/Administration/AlertManagementScreenShot";
	public static String BarcodeManagementReportPath = "/Screenshots/Administration/BarcodeManagementScreenShot";
	public static String CyclingConfigReportPath = "/Screenshots/Administration/ComplexCyclingConfigScreenShot";
	public static String FlockRegistrationReportPath = "/Screenshots/Administration/FlockRegistrationScreenShot";
	public static String ProgramManagementReportPath = "/Screenshots/Administration/ProgramManagementScreenShot";
	public static String PiperManagementReportPath = "/Screenshots/Piper_Administration/PiperManagementScreenShot";
	public static String PiperSoftwareReportPath = "/Screenshots/Piper_Administration/PiperSoftwareScreenShot";
	public static String PiperConfigurationReportPath = "/Screenshots/Piper_Administration/PiperConfigurationScreenShot";
	public static String DataTemplateManagementReportPath = "/Screenshots/MetaData/DTManagementScreenShot";
	public static String DataUploadReportPath = "/Screenshots/MetaData/DTUploadScreenShot";
	public static String PoultryManagementReportPath = "/Screenshots/MetaData/PoultryManagementScreenShot";
	public static String PreFlutterMobileReportPath = "/Screenshots/Flutter/PreFlutterMobileScreenShot";
	
	public static String SalmonellaReportPath = "/Screenshots/Reports/SalmonellaScreenShot";
	public static String ExternalSalmonellaReportPath = "/Screenshots/Reports/ExternalSalmonellaScreenShot";
	public static String CoccidiaReportPath = "/Screenshots/Reports/CoccidiaScreenShot";
	public static String ExternalCoccidiaReportPath = "/Screenshots/Reports/ExternalCoccidiaScreenShot";
	public static String CoccidiaTimelineReportPath = "/Screenshots/Reports/CoccidiaTimelineScreenShot";
	public static String StartAssayReportPath = "/Screenshots/Reports/StartAssayScreenShot";
	public static String InstallationRunReportPath = "/Screenshots/Reports/InstallationRunScreenShot";
	public static String NormalIngestionReportPath = "/Screenshots/Reports/NormalIngestionScreenShot";
	public static String PAConfigReportPath = "/Screenshots/Reports/PAConfigScreenShot";
	public static String RawImageReportPath = "/Screenshots/Reports/RawImageScreenShot";
	public static String SitesLogReportPath = "/Screenshots/Reports/SitesLogScreenShot";
	public static String DataSecurityReportPath = "/Screenshots/Reports/DataSecurityScreenShot";
	
	public static String api_login = api+"/login";
	public static String api_announcement = api+"/runfilelist";
	public static String api_FileUpload = api+"/fileupload";
	public static String api_StartAssay = api+"/startAssay?apiVersion=18.0";
	public static String api_RawImage = api+"/rawimgupload?apiVersion=18.0";
	public static String api_FileRequest = api+"/filerequest?apiVersion=17.0";
	public static String api_MPNSettingRequest = api+"/mpnsettingsrequest?apiVersion=17.0";
	public static String api_UserListRequest = api+"/userlistrequest?apiversion=18.0";
	public static String api_HeartBeat = api+"/heartbeat?apiversion=17.0";
	public static String api_SystemLogs = api+"/systemlogs";
}
