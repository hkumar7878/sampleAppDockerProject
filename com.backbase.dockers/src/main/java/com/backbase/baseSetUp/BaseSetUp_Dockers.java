package com.backbase.baseSetUp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.backbase.utilLibrary.ExcelReader;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseSetUp_Dockers {

	//public static WebDriver driver;
	public static RemoteWebDriver driver;
	//public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public static final Logger logger = Logger.getLogger(Test.class.getName());
	public static ExtentReports report;
	public static ExtentReports report1;
	public static ExtentTest ext_logger;
	public String app_Url;
	public String browserType;
	public String browserName;
	DesiredCapabilities cap=null;
	
	public static ExtentTest Extnt_loggerAllCities;
	public static ExcelReader excelReader = null;
	public static Properties prop;
	public static ExcelReader ExcelRd_Obj_Test_Suite = null;
	public static int suiteRow_AllURIs = 0;
	public String XMLtestCaseName = null;
	public String XMLtestCaseID = null;
	String browser=null;

	public BaseSetUp_Dockers() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//backbase//configuration//configuration.properties");
			prop.load(ip);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static String [][] getExcelData1(String ExcelName,String SheetName)
	 {
	    	String path= System.getProperty("user.dir")+"\\Test Excel Files\\" + ExcelName;
	    	excelReader=new ExcelReader(path);
	    	String[][] excelData = excelReader.getDatafromExcel(SheetName, ExcelName);
	    	return excelData;
	 }	  
	
	public static String [][] getExcelHashData(String ExcelName,String SheetName)
	 {
	    	String path= System.getProperty("user.dir")+"\\Test Excel Files\\" + ExcelName;
	    	excelReader=new ExcelReader(path);
	    	String[][] excelData = excelReader.getDatafromExcel(SheetName, ExcelName);
	    	return excelData;
	 }

	
	public void initializeTestBaseSetup(String browserType) {
		app_Url = prop.getProperty("App_URL");
		//browserType = prop.getProperty("Browser_Type");
		try {

		/*	if (browserType.contains("Firefox")) {

				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\" + "Browser Exes/geckodriver.exe");
				System.out.println("Launching firefox browser");
				logger.info("Creating a object of Firefox Browser");
				logger.info("Navigating to " + app_Url + "for Firefox browser");
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				//driver = new FirefoxDriver();
				//driver=new RemoteWebDriver(new URL ("http://172.17.0.2:4444/wd/hub"), cap);
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
				//driver=new RemoteWebDriver(new URL("http://172.17.0.3:5555/wd/hub"),cap);
				driver.get(app_Url);
				driver.manage().window().maximize();
			}*/
			
			if (browserType.contains("Firefox")) {

				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\" + "Browser Exes/geckodriver.exe");
				System.out.println("Launching firefox browser");
				logger.info("Creating a object of Firefox Browser");
				logger.info("Navigating to " + app_Url + "for Firefox browser");
				//FirefoxOptions ffoptions= new FirefoxOptions();
				//ffoptions.addArguments("disable-gpu");
				//cap = DesiredCapabilities.firefox();
		        //cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, ffoptions);	
				driver = new FirefoxDriver();
				//driver=new RemoteWebDriver(new URL ("http://172.17.0.2:4444/wd/hub"), cap);
				//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		        //driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap));
				//driver=new RemoteWebDriver(new URL("http://172.17.0.3:5555/wd/hub"),cap);
				driver.get(app_Url);
				
				driver.manage().window().maximize();
			}
			
			else if (browserType.contains("Chrome")) {

				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\" + "Browser Exes/chromedriver.exe");
				System.out.println("Launching chrome browser");
				logger.info("Creating a object of chrome Browser");
				logger.info("Navigating to " + app_Url + "for chrome browser");
				//FirefoxOptions ffoptions= new FirefoxOptions();
				//ffoptions.addArguments("disable-gpu");
				//cap = DesiredCapabilities.firefox();
		        //cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, ffoptions);	
				driver = new ChromeDriver();
				//driver=new RemoteWebDriver(new URL ("http://172.17.0.2:4444/wd/hub"), cap);
				//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		        //driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap));
				//driver=new RemoteWebDriver(new URL("http://172.17.0.3:5555/wd/hub"),cap);
				driver.get(app_Url);
				
				driver.manage().window().maximize();
			}
			/*else if (browserType.contains("Chrome")) {
				String currentDir = System.getProperty("user.dir");
				String chromeDriverLocation = currentDir + "/Browser Exes/chromedriver.exe";
				
				// **** Important note: There is no need to set the path for chrome driver to invoke chrome driver
				// since scripts are going to be executed on Chrome docker container. So' there is no need to set the path, in fact
				// we don't need to use chrome.exe. It has been verified and working fine.
				
				//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\" + "Browser Exes/chromedriver.exe");
				//System.setProperty("webdriver.chrome.driver",chromeDriverLocation);
				logger.info("Creating a object of Chrome Browser");
				System.out.println("Launching Chrome browser......");			
				ChromeOptions chromeOptions = new ChromeOptions();
		        chromeOptions.addArguments("disable-gpu");
		        cap = DesiredCapabilities.chrome();
		        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);		
				driver=new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"), cap);
				//driver = new ChromeDriver();
				driver.get(app_Url);
				driver.manage().window().maximize();

			}*/

		} catch (Exception e) {
			System.out.println("Error....." + e.getMessage());
		}

	}
	
	public void initializeTestBaseSetup1(String browserType) {
		if(System.getenv("Browser")!=null && !System.getenv("browser").isEmpty())
			browser = System.getenv("browser");
		else
			browser = prop.getProperty("browser");
		
		prop.setProperty("Browser_Type", browser);
		app_Url = prop.getProperty("App_URL");
		browserName=prop.getProperty("Browser_Type");
		
		
		try {		
			//if (browserName.contains("Firefox")) {
			if (prop.getProperty("Browser_Type").equals("Firefox")) {
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\" + "Browser Exes/geckodriver.exe");
				System.out.println("Launching firefox browser");
				logger.info("Creating a object of Firefox Browser");
				logger.info("Navigating to " + app_Url + "for Firefox browser");	
				driver = new FirefoxDriver();		
				driver.get(app_Url);			
				driver.manage().window().maximize();
			}
			
			//else if (browserName.contains("Chrome")) {
			else if (prop.getProperty("Browser_Type").equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\" + "Browser Exes/chromedriver.exe");
				System.out.println("Launching chrome browser");
				logger.info("Creating a object of chrome Browser");
				logger.info("Navigating to " + app_Url + "for chrome browser");		
				driver = new ChromeDriver();			
				driver.get(app_Url);			
				driver.manage().window().maximize();
			}
			
		} catch (Exception e) {
			System.out.println("Error....." + e.getMessage());
		}

	}
	
	
	//@BeforeTest	   
	 public void onBeforeTest(ITestContext testContext)
	    {
	
		 String log4jConfigPath=System.getProperty("user.dir")+"\\"+ "log4j.properties";
	     PropertyConfigurator.configure(log4jConfigPath);          
	        try
	        {	            
	                System.out.println("Inside Before Test class of BASE CLASS: FIREFOX");
	                String filePath=System.getProperty("user.dir")+"\\"+ "TestReportsAllURIsValidation.html";
	                String filePath1=System.getProperty("user.dir")+"\\"+ "TestReportsAllURIsValidation1.html";
	                report=new ExtentReports(filePath,true, DisplayOrder.OLDEST_FIRST);  
	                report1=new ExtentReports(filePath1,true, DisplayOrder.OLDEST_FIRST);
	        }
	        catch(Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	    }
	
}
