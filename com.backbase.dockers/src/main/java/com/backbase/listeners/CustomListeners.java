package com.backbase.listeners;


import java.net.InetAddress;

import org.apache.log4j.PropertyConfigurator;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;

import com.backbase.baseSetUp.BaseSetUp_Dockers;
import com.backbase.utilLibrary.ActionAfterTest;
import com.backbase.utilLibrary.ActionBeforeTest;

import com.backbase.utilLibrary.MonitoringMail;
import com.backbase.utilLibrary.TestConfig;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;



public class CustomListeners extends BaseSetUp_Dockers implements ITestListener, ISuiteListener, IInvokedMethodListener  {
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		{
			//String mthdName= method.getTestMethod().getMethodName();
			// System.out.println("Starting " + mthdName + "test case" );
			// ExtentTestManager.startTest(mthdName);
			/* try {
				actionBeforeTest.beforeTestAction3(mthdName,mthdName);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}*/
		}
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		
	        try
	        {
	        	System.out.println("Inside After Method of test case");
	            //actionAfterTest.testCasesSheetUpd3(testResult);
	        }

	        catch (Exception e)
	        {
	            System.out.println("Excpetion is " + e.getMessage());
	        }
		}


	@Override
	public void onStart(ISuite suite) {
		System.out.println("At suite start");
		 /*String log4jConfigPath=System.getProperty("user.dir")+"\\"+ "log4j.properties";
	     PropertyConfigurator.configure(log4jConfigPath);          
	        try
	        {	            
	                System.out.println("Inside Before Test class of BASE CLASS: FIREFOX");
	                String filePath=System.getProperty("user.dir")+"\\"+ "TestReportsAllURIsValidation.html";
	                report=new ExtentReports(filePath,true, DisplayOrder.OLDEST_FIRST);       		
	        }
	        catch(Exception e)
	        {
	            System.out.println(e.getMessage());
	       
	        }*/
	}

	@Override
	public void onFinish(ISuite suite){
		
		try
		{
			MonitoringMail mail = new MonitoringMail();
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			String messageBody="http://" + InetAddress.getLocalHost().getHostAddress()
				+":8080/job/SeleniumDockerProject1/Extent_20Report/";
			System.out.println(messageBody);
		
			mail.sendMail(TestConfig.server, TestConfig.from,TestConfig.to, TestConfig.subject, messageBody);
			
			System.out.println("On finish of test suite");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
