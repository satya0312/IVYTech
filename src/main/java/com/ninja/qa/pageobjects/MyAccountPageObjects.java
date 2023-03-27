package com.ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPageObjects {
	
	WebDriver driver;
	
	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccountInformatioOption;
	
	
	public MyAccountPageObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean getDisplayedStatusOfEditYourAccountInformation() {
		
		boolean displaystatus= editYourAccountInformatioOption.isDisplayed();
		return displaystatus;
		
	}
	
	

}
