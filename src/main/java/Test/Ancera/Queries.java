package Test.Ancera;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries extends DB_Config{

	public static String site = "none";
	
	public static String getFarmName = "SELECT TOP 1  frm.siteName FROM et.site frm\r\n"
			+ "JOIN et.site hs ON frm.siteId  = hs.parentSiteId\r\n"
			+ "AND hs.isActive = 1 AND hs.isDeleted = 0 AND frm.isActive = 1 AND frm.isDeleted = 0\r\n"
			+ "WHERE frm.siteTypeId = 6";
	
	
	public static String getComplexName = "SELECT TOP 1  frm.siteName FROM et.site frm\r\n"
			+ "JOIN et.site hs ON frm.siteId  = hs.parentSiteId\r\n"
			+ "AND hs.isActive = 1 AND hs.isDeleted = 0 AND frm.isActive = 1 AND frm.isDeleted = 0\r\n"
			+ "WHERE frm.siteTypeId = 5";
	
	
	public static String getFarmHousesCount = "	SELECT Count(hs.siteName) as Count FROM et.site frm\r\n"
			+ "			JOIN et.site hs ON frm.siteId  = hs.parentSiteId AND hs.isActive = 1 AND hs.isDeleted = 0 AND frm.isActive = 1 AND frm.isDeleted = 0\r\n"
			+ "			WHERE frm.siteTypeId = 6 \r\n"
			+ "			And frm.siteName = ("+getFarmName+") ";
	

	
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
