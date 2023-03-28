package com.ninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilis {
	
	public static final int IMPLECIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_Time=5;
	
	
	
	public static Object[][] GetTextDataFromExcel(String SheetName) throws IOException {
		XSSFWorkbook workbook = null;
		
		File excelfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\ninja\\qa\\testdata\\NinjaTestData.xlsx");
		try {
			FileInputStream fixExcel= new FileInputStream(excelfile);
			 workbook = new XSSFWorkbook(fixExcel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		XSSFSheet Sheet = workbook.getSheet(SheetName);
		 int rows = Sheet.getLastRowNum();
		 int col = Sheet.getRow(0).getLastCellNum();
		 
		 Object [][] data= new Object[rows][col];
		 
		 for(int i=0;i<rows;i++) {
			 XSSFRow row = Sheet.getRow(i+1);
			 
			 for(int j=0;j<col;j++) {
				 XSSFCell cell = row.getCell(j);
				 CellType cellType = cell.getCellType();
				 
				 switch(cellType) {
				 case STRING:
					 data[i][j] = cell.getStringCellValue();
					 break;
				 case NUMERIC:
					 data[i][j]= Integer.toString((int)cell.getNumericCellValue());
					 break;
				 case BOOLEAN:
					 data[i][j]= cell.getBooleanCellValue();
					 break;
				 }				 
			 }			 
		 }
		 return data;		
	}
	
	public static String CaptureScreenShot(WebDriver driver, String TestName) {
		
		File scrScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String DestinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + TestName + ".png";
		try {
			FileHandler.copy(scrScreenShot, new File(DestinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DestinationScreenshotPath;
	}

}
