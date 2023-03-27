package com.ninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.base.Base;

public class SearchPage extends Base {

	WebDriver driver;

	@BeforeMethod
	public void Set_Up() throws IOException {
		driver = InitBrowserAndOpenAppli(LoadPropertiesFile("browsername"));
	}

	@AfterMethod
	public void Tear_Down() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifySearchWithValidProduct() {

		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}

	@Test(priority = 2)
	public void VerifySearchwithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

		String ActualMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();

		Assert.assertTrue(ActualMessage.contains("There is no product that matches the search criteria."));
	}

	@Test(priority = 3)
	public void VerifySearchwithoutdetails() {

		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

		String ActualMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();

		Assert.assertTrue(ActualMessage.contains("There is no product that matches the search criteria."));
	}
}
