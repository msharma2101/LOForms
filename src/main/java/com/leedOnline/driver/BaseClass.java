package com.leedOnline.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	private static final ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<>();
	//public static WebDriver driver;
	
	public static XlsReader data;
	public Properties prop;
	public static ResponseSpecBuilder builder;
	public static RequestSpecification reqSpec;
	public static ResponseSpecification respSpec;
	public static String Token;
    public static String header;
    public static RemoteWebDriver driver; 
    public String apiUrl = "https://leedonline-api-dev.usgbc.org/v1/json";
    
	
	
	@BeforeMethod(alwaysRun=true)
	@Parameters({"browserName","environment"})
	public void setup( String browserName,String environment) throws InterruptedException, IOException{
		//Excel path configuration
		data= new XlsReader(System.getProperty("user.dir")+"/LOTest.xlsx"); 
		     
		//selecting browser based on parameter from TestNG.xml
		if(browserName.equalsIgnoreCase("firefox")){
			
			FirefoxProfile profile = new FirefoxProfile();
			   profile.setPreference("browser.download.folderList", 2);
			   profile.setPreference("browser.download.dir", System.getProperty("user.dir") +"/Downloads/");
			   profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			   profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
			   profile.setPreference("browser.download.manager.showWhenStarting", false);
			   profile.setPreference("browser.download.manager.focusWhenStarting", false);  
			   profile.setPreference("browser.download.useDownloadDir", true);
			   profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			   profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			   profile.setPreference("browser.download.manager.closeWhenDone", true);
			   profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			   profile.setPreference("browser.download.manager.useWindow", false);
			   profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
			   profile.setPreference("pdfjs.disabled", true);
			   
			         
			      Proxy proxy = new Proxy();
				  proxy.setHttpProxy("localhost:8055");
				  proxy.setFtpProxy("localhost:8055");
				  proxy.setSslProxy("localhost:8055");
				  DesiredCapabilities capabilities = new DesiredCapabilities();
				  capabilities.setCapability(CapabilityType.PROXY, proxy);
				  driver = new FirefoxDriver(capabilities); 
				  
				  driver = new FirefoxDriver(profile);
				  drivers.set(driver);
				  Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				  String browser = caps.getBrowserName();
				  String browserVersion = caps.getVersion();
				  System.out.println(browser+" "+browserVersion);
		
		}
		else if(browserName.equalsIgnoreCase("chrome")){

			//work with chrome
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/DriverFiles/chromedriver.exe");
			 HashMap<String, Object> images = new HashMap<String, Object>(); 
		        images.put("images", 2); 
		        HashMap<String, Object> prefs = new HashMap<String, Object>(); 
		        prefs.put("profile.default_content_setting_values", images);
		        ChromeOptions options =new ChromeOptions(); 
		        options.setExperimentalOption("prefs", prefs); 
		        DesiredCapabilities chromeCaps = DesiredCapabilities.chrome(); 
		        chromeCaps.setCapability(ChromeOptions.CAPABILITY, options); 

		       
			driver = new ChromeDriver();
			 drivers.set(driver);
		}
		
		else if(browserName.equalsIgnoreCase("opera")){
			//opera
			System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"/DriverFiles/operadriver.exe");
			driver = new OperaDriver();
			 drivers.set(driver);
		}
		
		else if(browserName.equalsIgnoreCase("ie")){

			//work with Internet explorer
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/DriverFiles/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			 drivers.set(driver);
			
		}
		
		else if(browserName.equalsIgnoreCase("safari")){

			//work with Internet explorer
			System.setProperty("webdriver.safari.noinstall", "true"); //To stop uninstall each time
			driver = new SafariDriver();
			 drivers.set(driver);
			
		}
		
		else if(browserName.equalsIgnoreCase("phantom")){

			File src = new File ("C:\\AutomationGroup10\\Drivers\\phantomjs.exe");
			System.setProperty("phantomjs.binary.path", src.getAbsolutePath());
		    driver = new PhantomJSDriver();
		    drivers.set(driver);
			
		}
		
		else if(browserName.equalsIgnoreCase("htmlunit")){

			
		    //driver = new HtmlUnitDriver();
		    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
		    Logger logger = Logger.getLogger("");
		    logger.setLevel(Level.OFF); 
			
		}
				
		driver().manage().window().maximize();
		driver().manage().window().setSize(new Dimension(1280,1024));
		driver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		 reqSpec  = new RequestSpecBuilder()
		         .setBaseUri(apiUrl)
		         .build();     
	      respSpec = new ResponseSpecBuilder()
	         .expectStatusCode(200)
	         .build();

		prop= new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Environment.properties");

		prop.load(file);
		String devurl=prop.getProperty("ENV_DEV");
		String qaurl=prop.getProperty("ENV_QA");
		String stageurl=prop.getProperty("ENV_STAGING");
		String productionurl=prop.getProperty("ENV_PRODUCTION");
		
		if(environment.equalsIgnoreCase("qa")){
			
			driver().get(qaurl);
			
		}
		else if(environment.equalsIgnoreCase("staging")){
			driver().get(stageurl);

			
		}else if(environment.equalsIgnoreCase("dev")){
			driver().get(devurl);

			
		}
		else if(environment.equalsIgnoreCase("production")){
			driver().get(productionurl);

			
		}
		Thread.sleep(5000);
		
		//wait = new WebDriverWait(driver, 60);
	
	}	
	
	@AfterMethod(alwaysRun = true)
	public static void teardown(ITestResult result) {
		RemoteWebDriver driver = driver();	       
		 if (result.getStatus() == ITestResult.FAILURE) {
			 CommonMethod.test.log(LogStatus.FAIL, result.getThrowable());
	        } else if (result.getStatus() == ITestResult.SKIP) {
	        CommonMethod.test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
	        } else {
	        CommonMethod.test.log(LogStatus.PASS, "Test passed");
	        }
		
		 CommonMethod.extent.endTest(CommonMethod.test);
		 CommonMethod.extent.flush();
		driver.quit();
		
	}
	
	@AfterTest(alwaysRun = true)
	public void end(){	
		//wait=null;
		
	}
	
	 public static RemoteWebDriver driver() {
	        RemoteWebDriver driver = drivers.get();
	        if (driver == null) {
	            throw new IllegalStateException("Driver should have not been null.");
	        }
	        return driver;
	    }
	
}