package com.ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {

	WebDriver driver;
	// Objects
	@FindBy(xpath = "//span[@class=\"caret\"]")
	private WebElement MyAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement LoginOption;
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;

	public HomePageObjects(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	// Actions
	public void ClickOnMyAccount() {
		MyAccountDropMenu.click();
	}

	public void ClickOnLogin() {
		LoginOption.click();
	}
	
	public void ClickOnRegister() {
		RegisterOption.click();
	}

}
