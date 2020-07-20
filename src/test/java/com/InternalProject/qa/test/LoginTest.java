package com.InternalProject.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.InternalProject.qa.base.BaseClass;
import com.InternalProject.qa.pages.LoginPage;
import com.InternalProject.qa.utils.TestUtil;

@SuppressWarnings("unused")
public class LoginTest extends BaseClass {
	
	LoginPage loginPage;
	
	String sheetName = "Sheet1";
	
	public LoginTest() {		
		
		super(); 		
		
	}	
	
	@BeforeTest
		
	public void setup() {
		
		initialization();
		loginPage = new LoginPage();
		
	}
	
	/*	
	@Test(priority = 3, dataProvider = "getTestData")
	
	public void loginWithOTP(String username, String password, String usernameFB, String passwordFB) throws InterruptedException {		
		
		loginPage.login1(username, password);
		Thread.sleep(5000);
				
	}
	*/ 
		
	@Test(priority = 4)
	
	public void logOutRegular() throws InterruptedException {		
		
		loginPage.logOut();
				
	}
	
	@Test(priority = 5, dataProvider = "getTestData")
	
	public void loginWithFacebook(String username, String password, String usernameFB, String passwordFB) throws InterruptedException {		
		
		loginPage.login2(usernameFB, passwordFB);
		Thread.sleep(15000);
				
	}
	
	@Test(priority = 6)
	
	public void logOutFacebook() throws InterruptedException {		
		
		loginPage.logOutFB();
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
