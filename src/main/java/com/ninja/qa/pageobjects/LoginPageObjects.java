package com.ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement EmailField;
	
	@FindBy(id="input-password")
	private WebElement PasswordField;
	
	@FindBy(xpath="//input[@value =\"Login\"]")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement NomatchforEMailandPassword;
	
	
	public LoginPageObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//Actions
	
	public void ProvideEmailId(String Email) {
		EmailField.sendKeys(Email);
	}
	
	public void ProvidePassword(String Password) {
		PasswordField.sendKeys(Password);
		
	}
	
	public void ClickOnLoginButn() {
		LoginButton.click();
	}
	
	public String NomatchforEMailandPassword() {
		return NomatchforEMailandPassword.getText();
	}
	
	

}
