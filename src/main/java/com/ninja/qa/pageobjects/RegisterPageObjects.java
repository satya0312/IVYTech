package com.ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObjects {
	
	WebDriver driver;
	@FindBy(id="input-firstname")
	private WebElement EnterFirstName;
	
	@FindBy(id="input-lastname")
	private WebElement EnterLastName;
	
	@FindBy(id="input-email")
	private WebElement EnterEmail;
	
	@FindBy(id="input-telephone")
	private WebElement EnterPhoneNumber;
	
	@FindBy(id="input-password")
	private WebElement EnterPassword;
	
	@FindBy(id="input-confirm")
	private WebElement EnterConformpassword;
	
	@FindBy(name="agree")
	private WebElement AgreeConditionCheckbox;
	
	@FindBy(xpath="//input[@type=\\\"submit\\\"]")
	private WebElement ClickOnSubmit;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement AccountCreationSucasseMessage;
	
	

	
	public RegisterPageObjects(WebDriver driver) {
		this.driver=driver;		
		PageFactory.initElements(driver, this);		
	}
	
	public void EnterFirstName(String FirstName) {		
		EnterFirstName.sendKeys(FirstName);		
	}
	public void EnterLastName(String LastName) {
		EnterLastName.sendKeys(LastName);
	}
	public void EnterEmail(String enterEmail) {
		EnterEmail.sendKeys(enterEmail);
	}
	
	public void EnterPhoneNumber(String PhoneNumber) {
		EnterPhoneNumber.sendKeys(PhoneNumber);
	}
	
	public void EnterPassword(String Password) {
		EnterPassword.sendKeys(Password);
	}
	
	public void EnterConformPassword(String Conformpassword) {
		EnterConformpassword.sendKeys(Conformpassword);
	}
	
	public void AgreeConditionCheckbox() {
		AgreeConditionCheckbox.click();
	}
	
	public void ClickOnSubmit() {
		ClickOnSubmit.clear();
	}
	
	public boolean GetStatusOfAccountCreationSucasseMessage() {
		return AccountCreationSucasseMessage.isDisplayed();
	}
	
	
	

}
