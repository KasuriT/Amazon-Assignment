package MiscFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Config.ReadPropertyFile;

public class DB_Config_DW {

	static Connection con = null;
	private static Statement stmt;	
    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

	public static String DB_UserName="akhan";
	public static String DB_Password="Ancer@123!";
	public static String PRH_DB_Name="PRH-IE-DW-15-3-2023";

	@BeforeTest
	public static void test() {
		try{

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

            if (config.environment().equals("qa")) {
            	String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName=QA-IE-DW;user="+DB_UserName+";Password="+DB_Password;
				Connection con = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
				setStmt(con.createStatement());
			}

            if (config.environment().equals("dev")) {
				//String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName=PRH-IE-DW;user="+UserName+";Password="+Password;
				String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName="+PRH_DB_Name+";user="+DB_UserName+";Password="+DB_Password;
				Connection con = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
				setStmt(con.createStatement());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	@AfterTest
	public static void tearDown() throws Exception {
		if (con != null) {
			con.close();
		}
	}


	public static Statement getStmt() {
		return stmt;
	}


	public static void setStmt(Statement stmt) {
		DB_Config_DW.stmt = stmt;
	}
	
	
	
//	@Test
//	public static void test1() {
//		try{
//			String selectQuery = "select status from salmonella_output where RUN_ID = '20221223-Salm-2710'";
//			ResultSet rs = getStmt().executeQuery(selectQuery);
//			while (rs.next()) {
//				System.out.println("Status: "+rs.getString("status"));
//			}
//			getStmt().close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	 
	
}
