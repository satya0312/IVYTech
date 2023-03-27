package com.ninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	WebDriver driver;

	public String LoadPropertiesFile(String Key) throws IOException {
		Properties prop = new Properties();
		File Propfile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\ninja\\qa\\config\\config.properties");

		FileInputStream fis = new FileInputStream(Propfile);
		prop.load(fis);

		return prop.getProperty(Key);
	}
	
	public String LoadTestdata(String Key) throws IOException {
		Properties Testdata = new Properties();
		File Propfile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\ninja\\qa\\config\\testdata.properties");

		FileInputStream fis1 = new FileInputStream(Propfile);
		Testdata.load(fis1);

		return Testdata.getProperty(Key);
	}
	

	public WebDriver InitBrowserAndOpenAppli(String browsername) throws IOException {
		if (browsername.equalsIgnoreCase("chromeDriver")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		} else if (browsername.equalsIgnoreCase("Firefox")) {

			driver = new FirefoxDriver();

		} else if (browsername.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get(LoadPropertiesFile("URL"));
		return driver;

	}

}
