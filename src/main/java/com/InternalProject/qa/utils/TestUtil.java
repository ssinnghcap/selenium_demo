package com.InternalProject.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.jetty.util.statistic.SampleStatistic;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import com.InternalProject.qa.base.BaseClass;
import com.google.common.math.Stats;
import org.openqa.selenium.Keys; 
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class TestUtil extends BaseClass{

	public static long PAGE_LOAD_TIMEOUT = 30;

	public static long IMPLICIT_WAIT = 30;

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "\\Test_Data\\TestData.xls";

	static Workbook book;
	static Sheet sheet;
	
	public void switchToFrame() {
		
		driver.switchTo().frame("mainpanel");
	}
	

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				if(sheet.getRow(i + 1)== null || sheet.getRow(i + 1).getCell(k) == null ) {
					break;
				}
									
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		
		return data;
	}
	
	public static void takeScreenshot() throws IOException {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "\\Screenshot\\test.png";
		
		FileUtils.copyFile(scrFile, new File(filePath));
	}
	
	
public static String getScreenshot(WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path); 
		
		try {
			FileUtils.copyFile(src, destination);
		}
		
		catch (Exception e) {
			System.out.println("Screenshot capture falied. " + e.getMessage());
		}
		
		
		return path;
		
		
	}
	
	
	
	
	
				
} 	
	
	