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
	public void test() {
		try{
			String UserName="vminnocci";
			String Password="Vico123!";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String DB_URL ="jdbc:sqlserver://sql-ie-qa-001.database.windows.net;databaseName=IE-DW;user=vminnocci;Password=Vico123!";
			Connection con = DriverManager.getConnection(DB_URL, UserName, Password);
		    stmt = con.createStatement();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	@Test
	public void test1() {
		try{
		//	String selectQuery = "select Count(*) from Alert_output";
			String selectQuery = "select status from salmonella_output where RUN_ID = '20210920-TestAut-PA-34218'";
			
			
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
			//	System.out.println("ID: "+rs.getInt(""));
				System.out.println("Status: "+rs.getString("status"));
			//	System.out.println("Sal: "+rs.getInt("EmpSal"));
			}
			stmt.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	@AfterTest
	public void tearDown() throws Exception {
		if (con != null) {
			con.close();
		}
	}












}
