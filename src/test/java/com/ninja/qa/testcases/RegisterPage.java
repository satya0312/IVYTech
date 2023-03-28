package com.ninja.qa.testcases;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.base.Base;
import com.ninja.qa.pageobjects.HomePageObjects;
import com.ninja.qa.pageobjects.RegisterPageObjects;

public class RegisterPage extends Base{
	
	WebDriver driver ;
	
	public String GetDatastamp() {
		Date date =new Date();
		return date.toString().replace(" ","_").replace(":", "_");
	}
	
	@BeforeMethod
	public void Set_UP() throws IOException {
		
	
		driver=InitBrowserAndOpenAppli(LoadPropertiesFile("browsername")) ;
		HomePageObjects homepage=new HomePageObjects(driver);
		homepage.ClickOnMyAccount();
		homepage.ClickOnRegister();
				
	}
	
	@AfterMethod
	public void Tear_Down() {
		driver.quit();
	}
	
	
	@Test(priority =1)
	public void VerifyRegistrationByManditatoryFileds() {
		
		RegisterPageObjects RegisterPage = new RegisterPageObjects(driver);
		RegisterPage.EnterFirstName("satya");
		RegisterPage.EnterLastName("satyalast");
		RegisterPage.EnterEmail("abc"+GetDatastamp()+"@gmail.com");
		RegisterPage.EnterPhoneNumber("123456789");
		RegisterPage.EnterPassword("123456");
		RegisterPage.EnterConformPassword("123456");
		RegisterPage.AgreeConditionCheckbox();
		RegisterPage.ClickOnSubmit();	
		
		
		String ActualMessage =  RegisterPage.GetStatusOfAccountCreationSucasseMessage();
		
		Assert.assertEquals(ActualMessage, "Your Account Has Been Created!","Account is not created");
	}
	
	@Test(priority =2)
	public void VerifyRegistrationByAllFields() {
		
		RegisterPageObjects RegisterPage = new RegisterPageObjects(driver);
		RegisterPage.EnterFirstName("satya");
		RegisterPage.EnterLastName("satyalast");
		RegisterPage.EnterEmail("abc"+GetDatastamp()+"@gmail.com");
		RegisterPage.EnterPhoneNumber("123456789");
		RegisterPage.EnterPassword("123456");
		RegisterPage.EnterConformPassword("123456");
		
		RegisterPage.SelectNewsLetter();
		RegisterPage.ClickOnSubmit();
		
		
		
String ActualMessage =  driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(ActualMessage, "Your Account Has Been Created!","Account is not created");
	}
	
	@Test(priority =3)
	public void VerifyRegisitritionWithoutDetails() throws InterruptedException {
		RegisterPageObjects RegisterPage = new RegisterPageObjects(driver);
		RegisterPage.ClickOnSubmit();
		
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	
		
		String ActualFirstNameWarning = driver.findElement(By.xpath("//input[@id=\"input-firstname\"]/following-sibling::div")).getText();
		
		String ActualLastNameWarining = driver.findElement(By.xpath("//input[@id=\"input-lastname\"]/following-sibling::div")).getText(); 	
		
		String ActualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		
		 Thread.sleep(2000);
		Assert.assertTrue(ActualErrorMessage.contentEquals("Warning: You must agree to the Privacy Policy!"),"Not getting error message");
		Assert.assertTrue(ActualFirstNameWarning.contentEquals("First Name must be between 1 and 32 characters!"),"First nmae field Warning is not displaying properly");
		Assert.assertTrue(ActualLastNameWarining.contentEquals("Last Name must be between 1 and 32 characters!"),"Last nmae field Warning is not displaying properly");
		Assert.assertTrue(ActualEmailWarning.contentEquals("E-Mail Address does not appear to be valid!"),"Email field Warning is not displaying properly");
	}

}
