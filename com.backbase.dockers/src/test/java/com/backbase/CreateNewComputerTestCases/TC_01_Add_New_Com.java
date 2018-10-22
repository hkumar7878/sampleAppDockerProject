package com.backbase.CreateNewComputerTestCases;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.backbase.appPages.Page_Home;
import com.backbase.appPages.Page_NewComputerAddition;
import com.backbase.baseSetUp.BaseSetUp_Dockers;
import com.backbase.utilLibrary.ActionAfterTest;
import com.backbase.utilLibrary.ActionBeforeTest;
import com.backbase.utilLibrary.ApplicationUtilityLib;
import com.backbase.utilLibrary.DataProviderRep_HashMap;
import com.backbase.utilLibrary.DataProviderRepository;
import com.backbase.utilLibrary.ExtentTestManager;

public class TC_01_Add_New_Com extends BaseSetUp_Dockers {
	String methodName=null;
	String SheetName=null;
	String testCasesSheetName=null;
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();
	String testCaseName;
	static int i=0;
	Page_Home homePgObj;
	Page_NewComputerAddition newCompAddObj;
	String compFoundBeforeAddition;
	String compFoundAfterAddition;
	String successMsg;
	String introFormattedDate=null;
	String dCFormatteddate=null;
	
	

	
	@BeforeMethod()
	@Parameters({"browserType"})
	public void getMethodName(Method method,String browserType) throws InterruptedException
	{
		 String mthdName=method.getName();
		 System.out.println("Starting " + mthdName + "test case" );
		 ExtentTestManager.startTest(mthdName);
		 actionBeforeTest.beforeTestAction3(mthdName,mthdName,browserType);
	}
	
	
	@Test(dataProviderClass=DataProviderRep_HashMap.class,dataProvider="newComputerData10",priority=1)
	//public void TC_01_Verify_New_Computer_Addition_With_Valid_Data(String newcompName,String introDate,String dcDate,String companyName) throws ParseException
	public void TC_01_Verify_New_Computer_Addition_With_Valid_Data(Map <String,String> data) throws ParseException
	{
		
		homePgObj= new Page_Home(driver);
				
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Get the total computer count found header text from the home page
		
		compFoundBeforeAddition=homePgObj.getComputerFoundData();
		
		// To get number of computers from header text
		
		int computerFoundBeforeAddition=ApplicationUtilityLib.getTotalComputerValue(compFoundBeforeAddition);
				
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		//newCompAddObj.enterNewCompData("Firefox", "CompName", newcompName, testCaseName);
		String str1=data.get("Computer Name");
		System.out.println(data.get("Computer Name"));
		newCompAddObj.enterNewCompData("Firefox", "CompName", data.get("Computer Name"), testCaseName);
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", data.get("Introducation Date"), testCaseName);
		
		//Step 6: Enter New Computer Discontinue date
				
		newCompAddObj.enterNewCompData("Firefox", "disContDate", data.get("Discontinued Date"), testCaseName);
				
				// Step 7: Select company 'Apple Inc.' from company drop down
				
		newCompAddObj.selectCompanyName("Firefox", data.get("Company Name"), testCaseName);
				
				// Step 8: Click on 'Create this computer' button
				
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
				
		/*newCompAddObj.enterNewCompData("Firefox", "CompName", data.get("Introducation Date"), testCaseName);
		newCompAddObj.enterNewCompData("Firefox", "CompName", data.get("Discontinued Date"), testCaseName);
		newCompAddObj.enterNewCompData("Firefox", "CompName", data.get("Company Name"), testCaseName);*/
		
		
	}
	
	@AfterMethod
	   public void afterEachTest(ITestResult result) throws InterruptedException {
	        try
	        {

	            System.out.println("Inside After Method of test case");
	            actionAfterTest.testCaseReportUp(result, "Addition");
	        }

	        catch (Exception e)
	        {
	            System.out.println("Excpetion is " + e.getMessage());
	        }
		}

}
