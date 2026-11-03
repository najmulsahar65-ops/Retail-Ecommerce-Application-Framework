package com.qa.retail.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	private int count=0;
	private static int maxTry=3;
	

	@Override
	public boolean retry(ITestResult result) {
		return false;
	}
	
	

}
