package Test.Ancera.Ingestions;

import static MiscFunctions.DateUtil.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import MiscFunctions.DB_Config_DW;
import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import MiscFunctions.Constants;
import Models.IngestionsModel;
import Models.RawImageCompareCountModel;
import Models.ReportFilters;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RawImageCountCompareSalmonella extends DB_Config_DW {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		DB_Config_DW.test();
	}


	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Raw Image Ingestion", enabled= true, priority= 1) 
	public void CountCompare() throws InterruptedException, IOException, SQLException	{

		RawImageCompareCountModel.lstRawImageComparison = RawImageCompareCountModel.FillData20();   //Change number to 20 to run for 20 images and 49 to run for 49 images
		String totalImages = "20";   //change number based on total number of images to test; 20 or 49
		for (RawImageCompareCountModel objModel : RawImageCompareCountModel.lstRawImageComparison) { 
			try{
				for (ReportFilters objFilter : objModel.lstFilters) {

					DateFormat dateFormat = new SimpleDateFormat("mm.ss");
					Date date1 = new Date();
					dateFormat.format(date1);

					RequestSpecification request = RestAssured.given();
					request.header("Content-Type", "application/json");
					JSONObject json = new JSONObject();
					json.put("piperid", IngestionsModel.piperId);
					json.put("password", IngestionsModel.piperPassword);
					json.put("DISAPIVersion", "15.2.0");
					request.body(json.toString());
					Response response = request.post(Constants.api_login);
					int code = response.getStatusCode();
					Assert.assertEquals(code, 200);

					String data = response.asString();
					System.out.println(data);

					JsonPath jsonPathEvaluator = response.jsonPath();
					String token = jsonPathEvaluator.get("token");		
					String statusCode = jsonPathEvaluator.get("statusCode");

					Thread.sleep(2000);
					RequestSpecification request_announcement = RestAssured.given();

					request_announcement.header("Content-Type", "application/json");
					request_announcement.header("Authorization", "bearer " +token);

					HttpGet postRequest = new HttpGet(Constants.api_announcement);
					postRequest.addHeader("Content-Type", "application/json");
					postRequest.addHeader("Authorization", "Bearer "+token);

					JSONObject json1 = new JSONObject();
					JSONObject json2 = new JSONObject();
					JSONObject json3 = new JSONObject();
					JSONObject json4 = new JSONObject();
					JSONArray list = new JSONArray();

					DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
					Date dateR = new Date();
					String date = dateFormat1.format(dateR);

					DateFormat dateFormat3 = new SimpleDateFormat("hh:mm a");
					Date dateRI = new Date();
					String dateRIT = dateFormat3.format(dateRI);

					///////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////

					if (objModel.runStartAssay ) {
						try {
							RequestSpecification request_startAssay = RestAssured.given();

							request_startAssay.header("Content-Type", "application/json");
							request_startAssay.header("Authorization", "bearer " +token);

							HttpGet postRequest3 = new HttpGet(Constants.api_StartAssay);
							postRequest3.addHeader("Content-Type", "application/json");
							postRequest3.addHeader("Authorization", "Bearer "+token);

							System.out.println("Run-ID: "+IngestionsModel.RunID_Salm);
							json4.put("DateTime", dateRIY+"T"+date+".000Z");
							json4.put("InstrumentId", objModel.InstrumentID);
							json4.put("UserId", IngestionsModel.lstStartAssaySalmonella.get(0).UserID);
							json4.put("CartridgeId", objModel.cartridgeID);
							json4.put("RunId", IngestionsModel.RunID_Salm);
							json4.put("PathogenName", objModel.pathogen);				

							request_startAssay.body(json4.toString());
							Response response3 = request_startAssay.post(Constants.api_StartAssay);

							String data4 = response3.asString();
							System.out.println(data4);
							Thread.sleep(150000);
						}
						catch(Exception ex){
						}
					}
					
					
					/////////////////////////////////////////////////////////End Start Assay////////////////////////////////////////////////////////////////////////////////	


					/////////////////////////////////////////////////////////Raw Image API////////////////////////////////////////////////////////////////////////////////

					try{

						Thread.sleep(1000);
						RequestSpecification request_fileupload = RestAssured.given();
						request_fileupload.header("Content-Type", "application/json");
						request_fileupload.header("Authorization", "bearer " +token);

						HttpGet postRequest1 = new HttpGet(Constants.api_RawImage);
						postRequest1.addHeader("Content-Type", "application/json");
						postRequest1.addHeader("Authorization", "Bearer "+token);

						System.out.println("Run-ID Raw Image: "+objModel.run_id);
						json3.put("cartridgeId", objModel.cartridgeID);
						json3.put("lane", objModel.lane);
						json3.put("dateTime", dateRIY+"T"+date+".000Z");
						json3.put("piperId", objModel.InstrumentID);
						json3.put("runType", objModel.runMode);
						json3.put("runId", objModel.run_id);
						json3.put("Pathogen", objModel.pathogen);
						json3.put("sampleid", objFilter.LstSampleID.get(0));
						json3.put("instrumentid", objModel.InstrumentID);
						json3.put("userid", IngestionsModel.UserID);

						String TestFile1 = objModel.checksum;
						FileReader FR1 = new FileReader(TestFile1);
						BufferedReader BR1 = new BufferedReader(FR1);
						String Content1 = "";

						while((Content1 = BR1.readLine())!= null) {
							json3.put("checksum", Content1);					
						}

						json3.put("fileName", objModel.fileName);
						json3.put("fileType", objModel.fileType);

						if (!objModel.isErrorCode) {
							String TestFile = objModel.base64fileName;
							FileReader FR = new FileReader(TestFile);
							BufferedReader BR = new BufferedReader(FR);
							String Content = "";

							while((Content = BR.readLine())!= null) {
								json3.put("file", Content);					
							}
						}

						if (objModel.isErrorCode) {
							json3.put("file", "");		
						}

						json3.put("Improc", "ImprocSalm01");
						json3.put("countOutCome", objModel.countOutcome);
						json3.put("statusCode", "");
						json3.put("IE_COLLECTION_SITE_ID", "1");
						json3.put("runMode", objModel.runMode);		
						json3.put("ieSampleMatrixID", "196");
						json3.put("dilutionFactor", "0.1");

						request_fileupload.body(json3.toString());
						Response response2 = request_fileupload.post(Constants.api_RawImage);
						String data3 = response2.asString();
						System.out.println(data3);

						Thread.sleep(3000);
						String ty = objModel.totalImages;
						
					}
					catch(Exception ex){
					}	
				}
			}	
			catch(Exception ex){
			}
		}


		try{
			SoftAssert softAssert = new SoftAssert();
			for (int x = 0;x<=100;x++) {

				String query1 = "Select count(status) as count from SALMONELLA_OUTPUT where Sample_ID like '%"+date0+"' and Sample_ID like '"+dateYYYYMMDD+"%'";
			//	String query1 = "Select count(status) as count from SALMONELLA_OUTPUT where Sample_ID like '20211201%' and Sample_ID like '%1503'";
				ResultSet rs1 = getStmt().executeQuery(query1);

				while (rs1.next()) {
					System.out.println("Count: "+rs1.getString("count"));
					if (rs1.getString("count").equals(totalImages)) {

						int i = 1;
						while (i<=Integer.parseInt(totalImages)) {
							System.out.println("'"+dateYYYYMMDD+"_Salm_"+i+"_"+date0+"'");
							String query2 = "Select w2_cell_count, lane_noise_ratio_percent, count_outcome from SALMONELLA_OUTPUT where Sample_ID = '"+dateYYYYMMDD+"_Salm_"+i+"_"+date0+"'";
						//	String query2 = "Select w2_cell_count, lane_noise_ratio_percent, count_outcome from SALMONELLA_OUTPUT where Sample_ID = '"+Test_Variables.dateYYYYMMDD+"_Salm_"+i+"_1503'";

							ResultSet rs = getStmt().executeQuery(query2);
							while (rs.next()) {
								System.out.println("W2 Cell Count: "+rs.getString("w2_cell_count"));
								String a = rs.getString("w2_cell_count");
								String b = rs.getString("lane_noise_ratio_percent");
								String c = rs.getString("count_outcome");
												
								String path = System.getProperty("user.dir")+"\\CountComparison\\CountComparison"+totalImages+".xlsx";
								FileInputStream fs = new FileInputStream(path);
								XSSFWorkbook workbook = new XSSFWorkbook(fs);
								XSSFSheet sheet = workbook.getSheetAt(0);
								Row row = sheet.getRow(i);
								Cell cell = row.getCell(1);

								DataFormatter formatter = new DataFormatter();
								String val = formatter.formatCellValue(sheet.getRow(i).getCell(1));
								System.out.println("Value: "+val);
								
								String noiseCount = formatter.formatCellValue(sheet.getRow(i).getCell(4));
												
								FileInputStream fs1 = new FileInputStream(path);
								Workbook wb = new XSSFWorkbook(fs1);
								Sheet sheet1 = wb.getSheetAt(0);
								int lastRow = sheet1.getLastRowNum();
								Row row1 = sheet1.getRow(i);
								Cell cell1 = row1.createCell(2);
								cell1.setCellValue("");
								cell1.setCellValue(a);
								
								DecimalFormat df = new DecimalFormat("#.##");
								
								if (b != null) {
								float convert = Float.parseFloat(b);
								Cell cell3 = row1.createCell(5);
								cell3.setCellValue("");
								cell3.setCellValue(df.format(convert));
								}
								
								Cell cell4 = row1.createCell(8);
								cell4.setCellValue("");
								cell4.setCellValue(c);
								
								if (val != null && a != null) {
									int difference = Integer.parseInt(val) - Integer.parseInt(a);
									Row row2 = sheet1.getRow(i);
									Cell cell2 = row2.createCell(3);
									cell2.setCellValue("");
									cell2.setCellValue(difference);
									softAssert.assertEquals(val, difference);
								}
								else {
									System.out.println("Column was Null; cannot find difference in w2 cell count");
								}
								
								if (noiseCount != null && b != null) {
									double difference = Float.parseFloat(noiseCount) - Float.parseFloat(b);
									Row row2 = sheet1.getRow(i);
									Cell cell2 = row2.createCell(6);

									cell2.setCellValue("");
									cell2.setCellValue(df.format(difference));
									softAssert.assertEquals(val, difference);
								}
								else {
									System.out.println("Column was Null; cannot find difference in noise count");
								}
								
								FileOutputStream fos = new FileOutputStream(path);
								wb.write(fos);
								fos.close();
								i++;
							}
						}			
					}
					else {
						Thread.sleep(120000);
					}
				}
			}
			softAssert.assertAll();
			getStmt().close();		
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}



	@AfterTest
	public void endreport() throws Exception {

		DB_Config_DW.tearDown();
		DB_Config_DW.getStmt();
		DB_Config_DW.setStmt(getStmt());
	}

}