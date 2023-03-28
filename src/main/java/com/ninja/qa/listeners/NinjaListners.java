package com.ninja.qa.listeners;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ninja.qa.utilities.ExtentReporter;
import com.ninja.qa.utilities.Utilis;

public class NinjaListners implements ITestListener {

	String TestName;
	ExtentReports extentreport;
	ExtentTest extentest;

	@Override
	public void onStart(ITestContext context) {
		try {
			extentreport = ExtentReporter.GenerateExtentReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {

		TestName = result.getName();
		extentest = extentreport.createTest(TestName);
		extentest.log(Status.INFO, TestName + " Test Case started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentest = extentreport.createTest(TestName);
		extentest.log(Status.PASS, TestName + " Test Case Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		// screenshot code
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalAccessException | NoSuchFieldException | SecurityException | IllegalArgumentException e) {

			e.printStackTrace();
		}

		String DestinationScreenshotPath= Utilis.CaptureScreenShot(driver, TestName);
		extentest.addScreenCaptureFromPath(DestinationScreenshotPath);
		extentest = extentreport.createTest(TestName);
		extentest.log(Status.FAIL, TestName + " got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		TestName = result.getName();
		extentest = extentreport.createTest(TestName);
		extentest.log(Status.SKIP, TestName + " Test Case Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		// to create the Report
		extentreport.flush();
		
		String PathOfExtenetReport= System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html";
		File extentreport = new File(PathOfExtenetReport);
		
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
