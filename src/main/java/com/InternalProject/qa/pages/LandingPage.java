package com.InternalProject.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.InternalProject.qa.base.BaseClass;

public class LandingPage extends BaseClass {	
	
	//Page Factory - Object Repository
		
	@FindBy (xpath = "//*[@id='header']/div[1]/div/aside/div[1]/a[3]")
		                  	                  	
	WebElement sitelogo;
	
	
	@FindBy (xpath = "//a[contains(text(),'Sign in')]")
	WebElement signin;
	
	
	public LandingPage()
	{
		PageFactory.initElements(driver, this);
	}
		
	
	//Actions
	
	public String fetchLandingPageTitle() {
		
		return driver.getTitle();
	}
	
	
	public Boolean validateLogoImage() throws InterruptedException {		
		
		Thread.sleep(3000);
		return sitelogo.isDisplayed();
		
	}
	
	public LoginPage signIn() throws InterruptedException {		
		
		signin.click();
		Thread.sleep(3000);	
		return new LoginPage();
		
	}

}
