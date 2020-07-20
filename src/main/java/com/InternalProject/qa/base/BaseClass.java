package com.InternalProject.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.InternalProject.qa.utils.TestUtil;


public class BaseClass {
	
	public static WebDriver driver;
	
	public static Properties prop;	
	
	public BaseClass() {
		
		try {
			prop = new Properties();
		
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\InternalProject\\qa\\utils\\config.properties");
			
			prop.load(ip);
						
		}
		
		catch(FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		catch(IOException e) {
			
			e.printStackTrace();
			
		}
				
	}
	
	
	public static void initialization() {
		
	 String browserName = prop.getProperty("browser");
	 
	 if(browserName.equals("chrome")) {
		 
		 String driverPath = System.getProperty("user.dir") + "\\DriverFolder\\chromedriver.exe"; 
		 
		 System.setProperty("webdriver.chrome.driver", driverPath);
	

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
	 
		 driver = new ChromeDriver(options);		 
	

		 		 
	 }
	 
	 else  {
		 
		 System.setProperty("webdriver.gecko.driver", "");
		 
		 driver = new FirefoxDriver();
		 
	 }
	 
	 driver.manage().window().maximize();
	 
	 driver.manage().deleteAllCookies();
	 	 
	 driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	 
	 driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	 	 
	 driver.get(prop.getProperty("url"));
	 
	 	 	 
	}
	
	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured: " + error);
		try {
			TestUtil.takeScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
