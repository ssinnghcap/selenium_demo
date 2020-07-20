package com.InternalProject.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.InternalProject.qa.base.BaseClass;
import com.InternalProject.qa.pages.LandingPage;
import com.InternalProject.qa.pages.LoginPage;
import com.InternalProject.qa.utils.TestUtil;

@SuppressWarnings("unused")
public class LandingTest extends BaseClass {
	
	LoginPage loginPage;
	
	LandingPage landingPage;
	
	//Page2 page2;
	
	String sheetName = "Sheet1";
	
	public LandingTest() {		
		
		super(); 		
		
	}
	
	
	@BeforeTest
		
	public void setup() {
		
		initialization();
		loginPage = new LoginPage();
		
	}
		
	
	@Test(priority = 1)
	public void LoginPageTitleTest() {
		
		String expectedTitle = ""; 
		String title = landingPage.fetchLandingPageTitle();
		Assert.assertEquals(title, expectedTitle);
	}
		
	
	@Test(priority = 2)
	
	public void LogoImageTest() throws InterruptedException {		
		
		Boolean flag = landingPage.validateLogoImage();
		Assert.assertTrue(flag);		
	}
		
			
	@DataProvider
	public Object[][] getTestData() {
			
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	
}	
	
	@AfterTest 		
	public void tearDown() { 			
		driver.quit();		
	}
	
}
