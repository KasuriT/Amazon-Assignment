package Test.Ancera;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries extends DB_Config{

	public static String site = "none";
	
	public static void getSiteID() throws InterruptedException, IOException, SQLException	{
		
		String query1 = "Select siteUniqueNumber from dbo.Site where siteName = 'TestHouse1_20220622' and isDeleted = 0";
		ResultSet rs1 = getStmt().executeQuery(query1);
		
		while (rs1.next()) {
				System.out.println("Site Unique Number: "+rs1.getString("siteUniqueNumber"));
				site = rs1.getString("siteUniqueNumber");
			}
			getStmt().close();
			
		
		
		
	}
	
}
