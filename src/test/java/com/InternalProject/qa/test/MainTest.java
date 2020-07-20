package com.InternalProject.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.xml.serializer.utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.InternalProject.qa.base.BaseClass;
import com.InternalProject.qa.pages.LoginPage;
import com.InternalProject.qa.pages.LandingPage;
import com.InternalProject.qa.utils.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.InternalProject.qa.utils.*;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;


@SuppressWarnings("unused")
public class MainTest extends BaseClass {
	
	LandingPage landingPage;
	LoginPage loginPage;
	
	public static WebDriver driver1;
	
	ExtentHtmlReporter htmlReports; 
	ExtentReports extent; 
	public static ExtentTest test; 
	
	String fileName = System.getProperty("user.dir")+"/ExtentReport/HtmlTestResults.html"; 
	
	public static List<String> screenshotPaths = new ArrayList<String>();
	
	
	String sheetName = "Sheet1";
	
	public MainTest() {		
		
		super(); 		
		
	}
			
	@BeforeTest
	public void setUp2() throws IOException, InterruptedException {

		htmlReports = new ExtentHtmlReporter(fileName);
		extent = new ExtentReports(); 
		extent.attachReporter(htmlReports);
		htmlReports.config().setReportName("Smoke Testing");
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReports.config().setDocumentTitle("Test Execution Results - Livemint Portal");
		extent.setSystemInfo("User:    ", "test user 1");
		extent.setSystemInfo("Platform:    ", "Web");
		extent.setSystemInfo("OS:    ", "Windows 10");
		
	}
	
	@BeforeTest
	public void setup() {
		initialization();
		landingPage = new LandingPage();
		loginPage = new LoginPage();
		
	}
		
		
	private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
	    Files.copy(source.toPath(), dest.toPath());
	}
	
	
	
	@DataProvider
	public Object[][] getTestData() {		
		
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}	
	
	
	@Test(priority = 2)
	
	public void LogoImageTest() throws InterruptedException {	
		
		test = extent.createTest("Verify Logo Image");
		
		driver1 = LandingPage.driver;
		
		Boolean flag = landingPage.validateLogoImage();
		Assert.assertTrue(flag);		
		
	}		
	
	
	@Test(priority = 3)
	
	public void signIn1() throws InterruptedException {
		
		test = extent.createTest("Sign in");
		
		landingPage.signIn();
	}
		
		
	
	@Test(priority = 4, dataProvider = "getTestData")
	
	public void loginWithOTP(String username, String password, String usernameFB, String passwordFB, String expTitle) throws InterruptedException {		
		
		test = extent.createTest("Login with Password/OTP");
		
		driver1 = LoginPage.driver;
		
		loginPage.login1(username, password, expTitle);
		Thread.sleep(3000);
				
	}		
	
	
	@Test(priority = 5)
	
	public void HelloLinkTest1() throws InterruptedException {	
	
		test = extent.createTest("Verify Hello Link");
		
		Boolean flag = loginPage.validateHelloLink1();
		Assert.assertTrue(flag);		
		
	}
	
	
	@Test(priority = 6)
	
	public void createBookmark() throws InterruptedException {		
		
		test = extent.createTest("Create Bookmark");
		
		loginPage.bookmarkPage();
				
	}
	
	@Test(priority = 7)
	
	public void openBookmark() throws InterruptedException {	
	
		test = extent.createTest("Open Bookmark");
		
		Boolean flag = loginPage.validateAndOpenBookmarkPage();
		Assert.assertTrue(flag);	
				
	}
			
	@Test(priority = 8)
	
	public void removeBookmark() throws InterruptedException {		
		
		test = extent.createTest("Remove bookmark");
		
		loginPage.unbookmarkPage();
				
	}
		
		 
	@Test(priority = 9)
	
	public void logOutRegular() throws InterruptedException {		
	
		test = extent.createTest("Logout - regular");
		
		loginPage.logOut();
				
	}	

	/*
	
	@Test(priority = 10)

	public void signIn2() throws InterruptedException {		
	
		test = extent.createTest("Sign in 2");
		
		driver1 = LandingPage.driver; 		
		
		landingPage.signIn();
					
	}
	
	
	@Test(priority = 11, dataProvider = "getTestData")
	
	public void loginWithFacebook(String username, String password, String usernameFB, String passwordFB, String expTitle) throws InterruptedException {		
		
		test = extent.createTest("Login with Facebook");
		
		driver1 = LoginPage.driver;
		
		loginPage.login2(usernameFB, passwordFB);
		Thread.sleep(5000);
				
	}
	
	@Test(priority = 12)
	
	public void HelloLinkTest2() throws InterruptedException {	
	
		test = extent.createTest("Verify Hello Link 2");
		
		Boolean flag = loginPage.validateHelloLink2();
		Assert.assertTrue(flag);		
		
	}
	
	
	@Test(priority = 13)
	
	public void logOutFacebook() throws InterruptedException {	
	
		test = extent.createTest("Logout - Facebook");
		
		loginPage.logOutFB();
				
	}
	
	*/	
		
	
	@AfterMethod(alwaysRun = true) //It will run after each test method execution
	public void checkResults(ITestResult testResults) throws InterruptedException, IOException {
		
		Thread.sleep(2000);
		
		if((testResults.getStatus()) == ITestResult.FAILURE) 
		{				
			
			int length = screenshotPaths.size();
			System.out.println("MainTest.screenshotPaths.length = " + length);
			MainTest.screenshotPaths.add((length), TestUtil.getScreenshot(MainTest.driver1)); 
			screenshotPaths.add((length), TestUtil.getScreenshot(driver1)); 
			System.out.println("\n\n\n\nMainTest.screenshotPaths.length =" + length);
			
				
			test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPaths.get(length)).build());
			test.log(Status.FAIL, testResults.getThrowable());
			test.log(Status.FAIL, "Test case is failed.");
			Thread.sleep(2000);
					
		} 
		
		else
		{	
			if((testResults.getStatus()) == ITestResult.SUCCESS) 
			{
				
				int length = screenshotPaths.size();
				System.out.println("MainTest.screenshotPaths.length =" + length);
						
				screenshotPaths.add((length), TestUtil.getScreenshot(driver1)); 
				
				System.out.println("\n\n\nMainTest.screenshotPaths.length =" + length);
								
				test.pass("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPaths.get(length)).build());
				test.log(Status.PASS, "Test case is passed.");
				
				Thread.sleep(2000);
				
			}
		
			else
			{
				if((testResults.getStatus()) == ITestResult.SKIP) 
				{
				test.log(Status.SKIP, testResults.getThrowable());
				test.skip("Execution is skipped for this test case.");
				
				}
			}
		}
				
	
		Thread.sleep(2000);
		extent.flush();
		
				
	}
	

	
	
	@AfterTest 		
	public void tearDown() throws IOException { 			
				
        driver1.quit();		
		
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void copyReport() throws IOException, InterruptedException
	{	
	
	Thread.sleep(5000);	
	File source = new File(fileName);
	String fileName2;	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH mm ss");  
    Date date = new Date();  
    System.out.println(formatter.format(date));  
    fileName2 = System.getProperty("user.dir")+"/ExtentReportsArchive/HtmlTestResults " +  formatter.format(date) + ".html"; 
   
    
	
    File dest = new File(fileName2);
        
    copyFileUsingJava7Files(source, dest);
	
	}
	
}

