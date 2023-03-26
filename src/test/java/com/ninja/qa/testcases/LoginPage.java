package com.ninja.qa.testcases;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage {

	public String GetDatastamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	@Test(priority = 1)
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
		driver.quit();

	}

	@Test(priority = 2)
	public void LoginWithInvalidDetail() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("http://www.tutorialsninja.com/demo/");
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"caret\"]")).click();

		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satyda@0312");

		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();

		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");

		driver.quit();

	}

	@Test(priority = 3)
	public void LoginWithInvaldiEmailAndValidPassword() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("http://www.tutorialsninja.com/demo/");
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"caret\"]")).click();

		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satyda@0312");

		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();

		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");

		driver.quit();

	}

	@Test(priority = 4)
	public void LoginWithInvalidEmailAndPassword() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("http://www.tutorialsninja.com/demo/");
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"caret\"]")).click();

		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("input-email")).sendKeys("tsatya0312" + GetDatastamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Satyda@0312" + GetDatastamp());

		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();

		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");

		driver.quit();

	}

	@Test(priority = 5)
	public void LoginwithoutCredentials() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("http://www.tutorialsninja.com/demo/");
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"caret\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("//input[@value =\"Login\"]")).click();
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");

		driver.quit();

	}

}
