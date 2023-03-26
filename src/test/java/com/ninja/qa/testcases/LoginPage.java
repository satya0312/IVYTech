package com.ninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage {
	
	@Test
	public void LoginWithValidDetails() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);		
				
		driver.manage().window().maximize();
		driver.get("http://www.tutorialsninja.com/demo/");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"caret\"]")).click();
		
		driver.findElement(By.linkText("Login")).click();
		 
		driver.findElement(By.id("input-email")).sendKeys("tsatya0312@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satya@0312");
		
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), 
				"The Edit your account information is not displyes in page ");
		
		
		
	}

}
