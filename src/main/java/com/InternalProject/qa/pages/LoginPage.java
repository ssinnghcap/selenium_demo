package com.InternalProject.qa.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.InternalProject.qa.base.BaseClass;

@SuppressWarnings("unused")
public class LoginPage extends BaseClass {	
	
	
	//Page Factory - Object Repository
	
	@FindBy(xpath = "//*[@id='search']") 
	WebElement body; 	
	
		
	@FindBy(xpath = "//*[@id='uemail']") 
	WebElement username; 	
	
	
	@FindBy(xpath = "//*[@id='upass']") 
	WebElement password;
	
	
	@FindBy(xpath = "//*[@id='login']") 
	WebElement login ;
	
	
	@FindBy (xpath = "//*[@id='header']/div[1]/div/aside/div[1]/a[3]")
	public
	WebElement sitelogo;
	
	
	@FindBy (xpath = "//a[contains(text(),'Hello')]")
	static
	WebElement helloLink;
	
	
	@FindBy (xpath = "//*[@id='myAccount']/a[3]")
  	WebElement logOut; 	
		
	
	@FindBy (xpath = "//a[contains(text(),'Sign in')]")
	WebElement signin;
	
	
	@FindBy (xpath = "//*[@id='fbmBtn']")
	WebElement continueWithFacebook;
	
	
	@FindBy (xpath = "//*[@id='email']")
	WebElement facebookUsr;
	
	
	@FindBy (css = "#pass")
	WebElement facebookPwd;
	
	
	@FindBy (xpath = "//*[@id='u_0_0']")
	WebElement facebookLogin;	
	
	@FindBy (xpath = "//*[@id='u_0_4']/div[2]/div[1]/div[1]/button")
	WebElement continueAsScript;
		
	
	@FindBy (xpath = "//*[@id='fixedNav_popular']")
	WebElement trendingLink;
		
	@FindBy (xpath = "//*[@id='bookmark_11595210906103']")
	WebElement bookmarkIcon;
	
	@FindBy (xpath = "//*[@id='bookmark_11595210906103']")
	WebElement bookmarkIconInsideReads;
	
	@FindBy (xpath = "//*[@id='fixedNav_myreads']")
	
	WebElement myReadsLink;
	
	@FindBy (xpath = "//*[@id='bm_11595210906103']/div/div/h2/a")
	WebElement bookmarkedLinkUnderMyReads;
	
		
	public String bookmark; 
	public LoginPage()
	{
				
		PageFactory.initElements(driver, this);
	}		
	
		
	public LandingPage login1 (String un, String pwd, String title) throws InterruptedException {		
		
		
		username.sendKeys(un);
		Thread.sleep(1000);
				
		password.sendKeys(pwd);
		Thread.sleep(1000);
		
		login.click();
		Thread.sleep(5000);
				
		return new LandingPage();		
			
	}	
	
	public Boolean validateHelloLink1() throws InterruptedException {		
		
		Thread.sleep(3000);
		return 	helloLink.isDisplayed();
		
	}	
	
	
	public Boolean validateHelloLink2() throws InterruptedException {	
		
		for (String handle1 : driver.getWindowHandles()) {

	        System.out.println(handle1);
	        driver.switchTo().window(handle1);
	    
	        }
		
		Thread.sleep(2000);
		return 	helloLink.isDisplayed();
		
	}
	
	
	public void bookmarkPage() throws InterruptedException {	
		
		trendingLink.click();
		Thread.sleep(2000);
		
		bookmarkIcon.click();		
		Thread.sleep(2000);
	}
	
	
	public Boolean validateAndOpenBookmarkPage() throws InterruptedException {	
		
		myReadsLink.click();
		Thread.sleep(4000);
		
		Boolean flag = bookmarkedLinkUnderMyReads.isDisplayed(); 	
		bookmarkedLinkUnderMyReads.click();		
		Thread.sleep(5000);
		return flag; 
		
	}
	
	public void unbookmarkPage() throws InterruptedException {	
		
		
		myReadsLink.click(); 
		Thread.sleep(3000); 
		
		//Bring the ribbon icon in page focus 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(2000);
		
		bookmarkIcon.click();	
		Thread.sleep(2000);
		
	}
	
	public LandingPage login2 (String un, String pwd) throws InterruptedException {		
		
		continueWithFacebook.click();
		Thread.sleep(2000);
		
		for (String handle1 : driver.getWindowHandles()) {

            System.out.println(handle1);
            driver.switchTo().window(handle1);

         }
					
		facebookUsr.clear();
		Thread.sleep(1000);
		
		facebookUsr.sendKeys(un);
		Thread.sleep(1000);
				
		facebookPwd.sendKeys(pwd);
		Thread.sleep(1000);
		
		facebookLogin.click();
		Thread.sleep(5000);
		
		return new LandingPage();		
		
	}	


	public LandingPage logOut () throws InterruptedException {		
	
		sitelogo.click();
		Thread.sleep(2000);
			
		logOut.click();
		return new LandingPage();				
}	


public LandingPage logOutFB () throws InterruptedException {		
	
		sitelogo.click();
		
		Thread.sleep(2000);
			
		logOut.click();
		Thread.sleep(2000);
		
		return new LandingPage();		
		
	}

}
