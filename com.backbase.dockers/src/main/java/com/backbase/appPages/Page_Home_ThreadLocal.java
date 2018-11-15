package com.backbase.appPages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.backbase.baseSetUp.BaseSetUp_Dockers;
import com.backbase.errorCollectors.ErrorCollector;
import com.backbase.utilLibrary.ApplicationUtilityLib;
import com.backbase.utilLibrary.ExtentTestManager;
import com.backbase.utilLibrary.ExtentTestManager1;
import com.backbase.utilLibrary.ExtentTestManager2;
import com.backbase.utilLibrary.ExtentTestManager3;
import com.backbase.utilLibrary.GenericUtilLib;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;



public class Page_Home_ThreadLocal extends BaseSetUp_Dockers{
	
	static boolean isEventSuccessful=false;
	String totalCompFoundData;
	String successMsg;
	ArrayList<String> rowsData=new ArrayList<String>();
	
	public Page_Home_ThreadLocal(WebDriver driver_td)
    {
    	this.driver_td = driver_td;
    	PageFactory.initElements(driver_td,this);
    }

	@FindBy(xpath = "//a[contains(text(),'Play sample application')]")
	public static WebElement lnk_sampleApp;

	@FindBy(xpath = "//a[@id='add']")
	public static WebElement btn_addNewComp;

	@FindBy(xpath = "//section[@id='main']/h1")
	public static WebElement hd_computerFound;
	
	@FindBy(xpath = "//div[@class='alert-message warning']")
	public static WebElement altMsg_success;
	
	@FindBy(xpath = "//input[@id='searchbox']")
	public static WebElement txt_searchBox;
	
	@FindBy(xpath = "//input[@id='searchsubmit']")
	public static WebElement btn_Filter;
	
	@FindBy(xpath = "//table[@class='computers zebra-striped']")
	public static WebElement table_CompDts;
	
	@FindBy(xpath = "//div[@class='well']//em")
	public static WebElement msg_NthToDisplay;

	
	/*
	 * 
	 * This method will verify the existence of sample 
	 * application link display on home page
	 * 
	 * 
	 * 
	 * 
	 */
	
	public void verifyHomePage(String moduleName)
    {

        try
        {
           
        	isEventSuccessful=GenericUtilLib.verifyLinkDisplay(lnk_sampleApp, "Play sample");
            Assert.assertTrue(isEventSuccessful);
            logger.info("--------Home Page is displayed successfully-----------------");
            System.out.println("Home Page is displayed successfully");  
            if(moduleName.equalsIgnoreCase("Addition"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            
            else if (moduleName.equalsIgnoreCase("Reading"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager1.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            else if (moduleName.equalsIgnoreCase("Updation"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager2.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            else if (moduleName.equalsIgnoreCase("Deletion"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager3.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            	

           
        }

        catch(Exception e)
        {
            ErrorCollector.addVerificationFailure(e);
            logger.info("--------Home Page is not displayed-----------------");
            logger.info(e.getMessage());
            if(moduleName.equalsIgnoreCase("Addition"))
            	ExtentTestManager.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );          
            else if (moduleName.equalsIgnoreCase("Reading"))         
            	ExtentTestManager1.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );
            else if (moduleName.equalsIgnoreCase("Updation"))
            	ExtentTestManager2.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );
            else if (moduleName.equalsIgnoreCase("Deletion"))
            	ExtentTestManager3.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );
        }
    }
	
	
			
	}
	
	


