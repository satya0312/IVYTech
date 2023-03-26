package com.ninja.qa.testcases;

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

public class LoginPage {
	
	WebDriver driver;
	
	public String GetDatastamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
	
	@BeforeMethod
	public void Set_Up(){
		
		String browsername ="Chrome";
		
		if(browsername.contentEquals("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		}
		else if(browsername.contentEquals("Firefox")) {
			
			driver = new FirefoxDriver();
			
		}
		else if(browsername.contentEquals("Edge")) {
			driver = new EdgeDriver();
			
		}

		driver.manage().window().maximize();
		driver.get("http://www.tutorialsninja.com/demo/");
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"caret\"]")).click();

		driver.findElement(By.linkText("Login")).click();
		
	}
	
	@AfterMethod
	public void Tear_Down() {
		driver.quit();
	}

	

	@Test(priority = 1)
	public void LoginWithValidDetails() throws InterruptedException {
		

		driver.findElement(By.id("input-email")).sendKeys("tsatya0312@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satya@0312");
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"The Edit your account information is not displyes in page ");
		

	}

	@Test(priority = 2)
	public void LoginWithInvalidDetail() {

		

		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satyda@0312");
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");


	}

	@Test(priority = 3)
	public void LoginWithInvaldiEmailAndValidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satyda@0312");
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");


	}

	@Test(priority = 4)
	public void LoginWithInvalidEmailAndPassword() {
		
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
