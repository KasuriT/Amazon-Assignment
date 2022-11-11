package Test.Ancera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DB_Config {

	static Connection con = null;
	private static Statement stmt;	


	@BeforeTest
	public static void test() {
		try{
			//	String UserName="vminnocci";
			//	String Password="Vico123!";
			String UserName="akhan";
			String Password="Ancer@123!";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			//	String DB_URL ="jdbc:sqlserver://sql-ie-qa-001.database.windows.net;databaseName=IE-DW;user="+UserName+";Password="+Password;

			if (Constants.url.contains("qa")) {
				String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName=QA-IE-DW;user="+UserName+";Password="+Password;
				Connection con = DriverManager.getConnection(DB_URL, UserName, Password);
				setStmt(con.createStatement());
			}

			if (Constants.url.contains("dev")) {
				String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName=PRH-IE-DW;user="+UserName+";Password="+Password;
				Connection con = DriverManager.getConnection(DB_URL, UserName, Password);
				setStmt(con.createStatement());
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	/*
	@Test
	public static void test1() {
		try{
		//	String selectQuery = "select Count(*) from Alert_output";
		//	String selectQuery = "select status from salmonella_output where RUN_ID = '20210920-TestAut-PA-34218'";

		//	String selectQuery = "Select status, w2_cell_count from SALMONELLA_OUTPUT where RUN_ID between '20211121_LT_HIPIR_100' and '20211121_LT_HIPIR_104'";
		//	String selectQuery = "Select status, w2_cell_count from SALMONELLA_OUTPUT where RUN_ID  = '20220725-Salm-10533'";



			ResultSet rs = getStmt().executeQuery(selectQuery);
			while (rs.next()) {
			//	System.out.println("ID: "+rs.getInt(""));
				System.out.println("Status: "+rs.getString("status"));
				System.out.println("W2 Cell Count: "+rs.getString("w2_cell_count"));
			}
			getStmt().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	 */

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
		DB_Config.stmt = stmt;
	}
}
