package com.ninja.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class NinjaListners implements ITestListener {
	
	String TestName;
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Testing is Statrted");
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		TestName = result.getName();
		
		System.out.println(TestName+" Test Case started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		TestName = result.getName();
		
		System.out.println(TestName+" Test Case Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		TestName = result.getName();
		
		System.out.println(TestName+" Test Case Failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		TestName = result.getName();
		
		System.out.println(TestName+" Test Case Skipped");
		
	}


	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Testing is Completed");
	}

}
