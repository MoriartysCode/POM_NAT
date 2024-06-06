package com.qa.opencart.util;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static final String Test_Data_Sheet_Path="./src/test/resources/TestData/openCartTestData.xlsx";
	private static XSSFWorkbook book;
	private static XSSFSheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		System.out.println("Reading data from sheet"+sheetName);
		Object data[][]=null;
		try {
			FileInputStream ip = new FileInputStream(Test_Data_Sheet_Path);//creates the connection with file
			book = new XSSFWorkbook(ip);
			sheet = book.getSheet(sheetName);
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0 ; i<sheet.getLastRowNum();i++) {
				for(int j=0 ; j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
