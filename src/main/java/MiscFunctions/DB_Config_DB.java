package MiscFunctions;

import Config.ReadPropertyFile;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_Config_DB {

	static Connection con = null;
	private static Statement stmt;	
    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

	@BeforeTest
	public static void test() {
		try{
			String UserName="akhan";
			String Password="Ancer@123!";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

            if (config.environment().equals("qa")) {
            	String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName=QA-IE-DB;user="+UserName+";Password="+Password;
				Connection con = DriverManager.getConnection(DB_URL, UserName, Password);
				setStmt(con.createStatement());
			}

            if (config.environment().equals("dev")) {
				String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName=PRH-IE-DB;user="+UserName+";Password="+Password;
				Connection con = DriverManager.getConnection(DB_URL, UserName, Password);
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
		DB_Config_DB.stmt = stmt;
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
