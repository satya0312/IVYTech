package com.ninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports GenerateExtentReport() throws IOException {

		ExtentReports extentreport = new ExtentReports();

		File ExtentReportFile = new File(
				System.getProperty("user.dir") + "//test-output//ExtentReports//ExtentReport.html");

		ExtentSparkReporter spartReporter = new ExtentSparkReporter(ExtentReportFile);
		spartReporter.config().setTheme(Theme.DARK);
		spartReporter.config().setReportName("Ninja Test Automation Report");
		spartReporter.config().setDocumentTitle("Ninja Reports");
		spartReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		
		extentreport.attachReporter(spartReporter);
		
		
		Properties ConfigProp = new Properties();
		File ConfigPropFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\ninja\\qa\\config\\config.properties");

		FileInputStream fis = new FileInputStream(ConfigPropFile);
		ConfigProp.load(fis);
		
		extentreport.setSystemInfo("Application URL", ConfigProp.getProperty("URL"));
		extentreport.setSystemInfo("Browser Name", ConfigProp.getProperty("browsername"));
		extentreport.setSystemInfo("Email",ConfigProp.getProperty("ValidEmail"));
		extentreport.setSystemInfo("Password",ConfigProp.getProperty("ValidPassword"));
		
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("User Name ", System.getProperty("user.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentreport;

	}
}
 