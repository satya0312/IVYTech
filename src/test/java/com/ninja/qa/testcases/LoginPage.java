package com.ninja.qa.testcases;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninja.qa.base.Base;
import com.ninja.qa.pageobjects.HomePageObjects;
import com.ninja.qa.pageobjects.LoginPageObjects;
import com.ninja.qa.pageobjects.MyAccountPageObjects;
import com.ninja.qa.utilities.Utilis;

public class LoginPage extends Base {

	WebDriver driver;

	public String GetDatastamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	@BeforeMethod
	public void Set_Up() throws IOException, InterruptedException {
		driver = InitBrowserAndOpenAppli(LoadPropertiesFile("browsername"));

		HomePageObjects homepage = new HomePageObjects(driver);
		homepage.ClickOnMyAccount();
		homepage.ClickOnLogin();

	}

	@AfterMethod
	public void Tear_Down() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "ValidCredentialsSupplier")
	public void LoginWithValidDetails(String email, String Password) throws InterruptedException, IOException {
		LoginPageObjects LoginPage = new LoginPageObjects(driver);
		LoginPage.ProvideEmailId(email);
		LoginPage.ProvidePassword(Password);
		LoginPage.ClickOnLoginButn();
		MyAccountPageObjects AcountPage = new MyAccountPageObjects(driver);
		Assert.assertTrue(AcountPage.getDisplayedStatusOfEditYourAccountInformation(),
				"The Edit your account information is not displyes in page ");
	}

	@DataProvider(name = "ValidCredentialsSupplier")
	public Object[][] SupplyTestData() throws IOException {
		Object[][] data = Utilis.GetTextDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void LoginWithInvalidDetail() throws IOException {
		LoginPageObjects LoginPage = new LoginPageObjects(driver);
		LoginPage.ProvideEmailId("tsatya0312" + GetDatastamp() + "@gmail.com");
		LoginPage.ProvidePassword(LoadTestdata("InvalidPassword"));
		LoginPage.ClickOnLoginButn();
		
		String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String ExpectedErrorMessage = LoadTestdata("emailPasswrodNoMatchWarning");
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");
	}

	@Test(priority = 3)
	public void LoginWithInvaldiEmailAndValidPassword() throws IOException {
		
		LoginPageObjects LoginPage = new LoginPageObjects(driver);
		LoginPage.ProvideEmailId("tsatya0312" + GetDatastamp() + "@gmail.com");
		LoginPage.ProvidePassword(LoadPropertiesFile("ValidPassword"));
		LoginPage.ClickOnLoginButn();
		
		String ActualErrorMessage = LoginPage.NomatchforEMailandPassword();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");
	}

	@Test(priority = 4)
	public void LoginWithInvalidEmailAndInvalidPassword() {
		
		LoginPageObjects LoginPage = new LoginPageObjects(driver);
		LoginPage.ProvideEmailId("tsatya0312" + GetDatastamp() + "@gmail.com");
		LoginPage.ProvidePassword("Satyda@0312" + GetDatastamp());
		LoginPage.ClickOnLoginButn();

		
		String ActualErrorMessage = LoginPage.NomatchforEMailandPassword();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");

	}

	@Test(priority = 5)
	public void LoginwithoutCredentials() {
		LoginPageObjects LoginPage = new LoginPageObjects(driver);

		LoginPage.ClickOnLoginButn();
		String ActualErrorMessage = LoginPage.NomatchforEMailandPassword();
		String ExpectedErrorMessage = " Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage), "Expected Error message is not displayed");

	}

}
