package com.ninja.qa.testcases;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.base.Base;

public class LoginPage extends Base{
	
	WebDriver driver;
	
	public String GetDatastamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
	
	@BeforeMethod
	public void Set_Up() throws IOException{
		driver=InitBrowserAndOpenAppli(LoadPropertiesFile("browsername")) ;
		driver.findElement(By.xpath("//span[@class=\"caret\"]")).click();

		driver.findElement(By.linkText("Login")).click();
		
	}
	
	@AfterMethod
	public void Tear_Down() {
		driver.quit();
	}

	

	@Test(priority = 1)
	public void LoginWithValidDetails() throws InterruptedException, IOException {
		

		driver.findElement(By.id("input-email")).sendKeys(LoadPropertiesFile("ValidEmail"));
		driver.findElement(By.id("input-password")).sendKeys(LoadPropertiesFile("ValidPassword"));
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"The Edit your account information is not displyes in page ");
		

	}

	@Test(priority = 2)
	public void LoginWithInvalidDetail() throws IOException {

		

		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys(LoadTestdata("InvalidPassword"));
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedErrorMessage = LoadTestdata("emailPasswrodNoMatchWarning");
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");


	}

	@Test(priority = 3)
	public void LoginWithInvaldiEmailAndValidPassword() throws IOException {
		
		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys(LoadPropertiesFile("ValidPassword"));
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");


	}

	@Test(priority = 4)
	public void LoginWithInvalidEmailAndInvalidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satyda@0312" + GetDatastamp());
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");


	}

	@Test(priority = 5)
	public void LoginwithoutCredentials() {
		
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");


	}

}
