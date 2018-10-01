package com.leedOnline.driver;
import static com.jayway.restassured.RestAssured.given;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.jayway.restassured.response.Response;
import com.leedOnline.driver.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class CommonMethod extends BaseClass {
	
	static Format formatter = new SimpleDateFormat("YYYY-MM-dd");
	static Date date = new Date();
	public static String Sheetname = "ContactForm";
	public static String fetchedID;
	public static ExtentReports extent;
	public static String apiRequestId;
    public static Float responsetime;
	public static Response res;
	public static List creditIdList = new ArrayList();
	public static HashMap<String,String> shortIdAndCreditIdhm=new HashMap<String,String>();
	public static ExtentTest test;
	public static String ProgramID;
	public static String signupID;
	public static String screenshotfile = System.getProperty("user.dir") +"/target/Screenshots/";
    public static File extentconfigfile = new File(System.getProperty("user.dir") +"/src/main/resources/extent-config.xml");
    static Random rand = new Random();
    public static String contentType = "application/x-www-form-urlencoded";
    static int  n = rand.nextInt(50);
    public static String Reportfile = System.getProperty("user.dir") +"/Report/ARC-AutomationReport" + "_" + formatter.format(date)+n + ".html";
	public static String downloadPath = System.getProperty("user.dir") +"/Downloads/";
	public static WebDriverWait wait;
	static WebElement element;
	
	
	public static WebElement findElement(final String objectLocater) throws IOException{
		wait = new WebDriverWait(driver(), 60);
		//System.out.println(downloadPath);
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		
		FileInputStream fp1 = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/DashbordLocator.properties");
		OR.load(fp1);
		
		
		String objecttypeandvalues = OR.getProperty(objectLocater);
		System.out.println(objecttypeandvalues);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		System.out.println("obj val: " + objectvalue);
			
		switch(objecttype){
		  
						case "id":
							return driver().findElement(By.id(objectvalue));
							
						case "xpath":
			  
							return driver().findElement(By.xpath(objectvalue));
			  			                
			            case "name":
			  
			            	return driver().findElement(By.name(objectvalue));
			  			               		  
			            case "class":
			  
			            	return driver().findElement(By.className(objectvalue));
			  
			            case "tagname":
			  
			        	    return driver().findElement(By.tagName(objectvalue));
			     
			            case "css":
			  			  
				        	return driver().findElement(By.cssSelector(objectvalue));
				        
			            case "linkText":
				  			  
				        	return driver().findElement(By.linkText(objectvalue));
			            default:
			            	
			            	return null;
		}
		
		}
	
	
	
	
	public static List<WebElement> findElements(String objectLocater) throws IOException{
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		String objecttypeandvalues = OR.getProperty(objectLocater);
		System.out.println(objecttypeandvalues);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		System.out.println("obj val: " + objectvalue);
			
		switch(objecttype){
		  
						case "id":
							return driver().findElements(By.id(objectvalue));
							
						case "xpath":
			  
							return driver().findElements(By.xpath(objectvalue));
			  			                
			            case "name":
			  
			            	return driver().findElements(By.name(objectvalue));
			  			               		  
			            case "class":
			  
			            	return driver().findElements(By.className(objectvalue));
			  
			            case "tagname":
			  
			        	    return driver().findElements(By.tagName(objectvalue));
			     
			            case "css":
			  			  
				        	return driver().findElements(By.cssSelector(objectvalue));
				        
			            case "linkText":
				  			  
				        	return driver().findElements(By.linkText(objectvalue));
			            default:
			            	
			            	return null;
		}
		
		}
	//user defined click Method
	public static void click(String objectLocater) throws IOException{
		
		
		findElement(objectLocater).click();
		
	}
	
	
	//user defined submit Method
		public static void submit(String objectLocater) throws IOException{
			
			
			findElement(objectLocater).submit();
			
		}
	
	//user defined sendkeys Method
	public static void sendKeys(String objectLocater,String value) throws IOException{
		//System.out.println("objectLocator is :"+objectLocater);
		//System.out.println("value is:"+value);
		findElement(objectLocater).sendKeys(value);
	}
	//user defined gettext Method
	public static String getText( String objectLocater) throws IOException {
	
		return findElement( objectLocater).getText();
		
	}
	
	public static String getattributeValue(String objectLocater) throws IOException {
		
		return findElement(objectLocater).getAttribute("value");
		
	}
	//user defined clear Method
	public static void clear(String objectLocater) throws IOException{
		findElement( objectLocater).clear();
	}
	
	public static void assertTruegetAttributeComparision(String objectLocater,String name, String message) throws IOException{
		Assert.assertTrue(findElement( objectLocater).getAttribute("value").equalsIgnoreCase(name),message);
	}
	
	public static void assertEqualsMessage(String actual,String expected, String message) throws IOException{
		  
		  Assert.assertEquals(actual, expected, message+ " found " + actual + " but expected "+ expected);
		 }
	
	 public static void assertcontainsMessage(String actual,String expected, String message) throws IOException{
	        
	     //System.out.println(CommonMethod.getText( objectLocater));
	     
	  Assert.assertTrue(actual.contains(expected), message + " found " + actual + " but expected "+ expected);
	 }
	
	public static void moveToElement(String objectLocator) throws IOException, InterruptedException{
		
		Actions action = new Actions(driver());
		action.moveToElement(findElement( objectLocator)).build().perform();
		Thread.sleep(2000);
	}
	
	public static Float responsetime() {
		 return Float.valueOf(CommonMethod.res.header("X-Api-Process-Time"));		 
	 }
	
	
	public static void pageloadwait(){
		
	
	wait.until( new Predicate<WebDriver>()
			{ public boolean apply(WebDriver driver) 
	{ return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	} } );}
	
	static Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

        @Override
        public boolean apply(WebDriver input) {
            return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
        }

};


	
	public static void assertTruebooleanCondition(boolean boo,String message){
		
		Assert.assertTrue(boo, message);
	}
	
	public static void assertEqualsmessage(String objectLocator,String expected, String message) throws IOException{
		
		
		Assert.assertEquals(getText(objectLocator), expected, message);
	}
	
    public static void assertEqualsmessageAttributevalue(String objectLocator,String expected, String message) throws IOException{
		
		
		Assert.assertEquals(getattributeValue(objectLocator), expected, message);
	}
	
   public static void assertNotSame(String objectLocator,String expected, String message) throws IOException{
		/*System.out.println("hello");
	    System.out.println(CommonMethod.getText("ErrorMessage"));*/
		Assert.assertNotSame((findElement( objectLocator)).getText(), expected, message);
	}
	
    public static void assertcontainsmessage(String objectLocater,String expected, String message) throws IOException{
   
    	//System.out.println(CommonMethod.getText( objectLocater));
		Assert.assertTrue(getText(objectLocater).contains(expected), message);
	}
    
    public static void assertcontains(String objectLocater,String expected , String message) throws IOException{ 
    	//System.out.println(CommonMethod.getText( objectLocater)); 
    	String Actual = CommonMethod.getText( objectLocater); 
    	Assert.assertTrue(expected.contains(getText(objectLocater)), message + " found " + Actual + " but expected "+ expected);
    	}
    
    public static void assertcontainsmessage(WebElement objectLocater,String expected, String message) throws IOException{
        
        //System.out.println(CommonMethod.getText( objectLocater));
        String Actual =objectLocater.getText();
     Assert.assertTrue(expected.contains(Actual), message + " found " + Actual + " but expected "+ expected);
    }
    
    public static void assertcontainsattributevalue(String objectLocator,String expected, String message) throws IOException{
    	
    	System.out.println((findElement(objectLocator)).getAttribute("value"));
		Assert.assertTrue((findElement( objectLocator)).getAttribute("value").contains(expected), message);
	}
	
    public static void assertisElementPresentTrue(String objectLocator,String message) throws IOException{
	    
    	boolean boo =  findElements(objectLocator).size()>0;
    	System.out.println(boo);
    	Assert.assertTrue(boo, message);
    	
	}
    
    public static void assertisElementPresentFalse(String objectLocator,String message) throws IOException{
	    
    	boolean boo =  findElements( objectLocator).size()>0;
    	System.out.println(boo);
    	Assert.assertFalse(boo, message);
    	
	}
    
    public static void selectdropdownrandom(String objectLocator) throws IOException, InterruptedException{
    	
    	Select se = new Select( findElement(objectLocator));
    	List<WebElement> ele = se.getOptions();
    	se.selectByIndex(new Random().nextInt(ele.size()));
    	Thread.sleep(2000);
    	WebElement option = se.getFirstSelectedOption();
    	System.out.println(option.getText());
    }
    
    public static void selectdropdown(String objectLocator,String value) throws IOException{
    	
    	Select se = new Select(findElement(objectLocator));
    	se.selectByVisibleText(value);
    	
    }
    
    //Is displayed Method (Assertion)
    public static void Isdisplayed(String objectLocater,String message) throws IOException{
    	
    	Assert.assertTrue(findElement(objectLocater).isDisplayed(),message);
    	
    }
    
    public static void IsEnabled( String objectLocater,String message) throws IOException{
    	
    	Assert.assertTrue(findElement( objectLocater).isEnabled(),message);
    	
    }
    
    public static void assertcontentPageSource( String expectedtext, String message){
    	
    	Assert.assertTrue(driver().getPageSource().contains(expectedtext),message);
    }
    
   public static void assertcurrentUrl( String expectedUrl,String message){
    	System.out.println(driver().getCurrentUrl());
    	System.out.println(expectedUrl);
    	Assert.assertTrue(driver().getCurrentUrl().equals(expectedUrl),message);
    }
  public static void assertcurrentUrlTest(String expectedUrl){
	   String current =driver().getCurrentUrl();
	   System.out.println(current);
	   System.out.println(expectedUrl);
	   Assert.assertEquals(current, expectedUrl);
    	//Assert.assertEquals(expectedUrl, expected, message);
    	//Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(expectedUrl));
    	//assertTrue(driver.getCurrentUrl().equals(expectedUrl));
   	
   }
   
    //---new Addition
    
    public static void scrolldowntoLast(){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    //
    
    public static void scrolldowntoElement( String objectLocater) throws IOException{
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver());
        js.executeScript("arguments[0].scrollIntoView(true);",findElement(objectLocater));
    }
    
    public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
    public static boolean isFileDownloadedExtension(String downloadPath, String fileName) {
  		boolean flag = false;
  	    File dir = new File(downloadPath);
  	    File[] dir_contents = dir.listFiles();
  	  	    
  	    for (int i = 0; i < dir_contents.length; i++) {
  	        if (dir_contents[i].getName().endsWith(fileName))
  	            return flag=true;
  	            }

  	    return flag;
  	}
    public static void VerifyDownloadWithFileName(String filename)  {
		
    
	    Assert.assertTrue(isFileDownloaded(downloadPath, filename), "Failed to download Expected document");
	}
    
    
 public static void  VerifyDownloadVerifyExtension(String filename)  {
		
	    Assert.assertTrue(isFileDownloadedExtension(downloadPath, filename), "Failed to download Expected document");
	} 
   
   public static void productcount(String objectLocator, int Pno, String message) throws IOException{
		
    	List<WebElement> list = (findElements(objectLocator));
    	System.out.println(list.size());
    	int act = list.size();
		for(WebElement opt : list){
	    System.out.println(opt.getText());
		}
		Assert.assertEquals(act, Pno, message);
		}

	
   /*****************Driver wait*******************************/
   public static void driverwait(int timeToWaitInSec) throws InterruptedException{
		Reporter.log("waiting for "+timeToWaitInSec+" seconds...");
		Thread.sleep(timeToWaitInSec*1000);
	}
   
   
	
    public static String takeScreenshot(String methodname) throws IOException{
		try {
		TakesScreenshot ts = (TakesScreenshot)driver();
		File Source = ts.getScreenshotAs(OutputType.FILE);
		String Dest = screenshotfile + methodname + ".png";
		File Destination = new File(Dest);
		FileUtils.copyFile(Source, Destination);
		return Dest;
		}
		catch(Exception e){	
			System.out.println("Exception Taking screenshot" + e.getMessage());
			return e.getMessage();
		}	
    }
			
			
    public static void ExtentReportConfig() {
		extent = new ExtentReports(Reportfile, true);
		extent.loadConfig(extentconfigfile);
        Map<String, String> sysInfo = new HashMap<String, String>();
		sysInfo.put("Selenium Version", "2.53");
		sysInfo.put("Environment", "Staging");
        extent.addSystemInfo(sysInfo);
		
	}
    
    public static void testlog(String log, String message){
    	
    	switch(log){
    	
    	case "Pass":
    		test.log(LogStatus.PASS, message);
    		break;
    		
    	case "Info":
    		test.log(LogStatus.INFO, message);
    		break;
    		
    	 default:
         	
         	System.out.println("Not Valid Input");
    	}
    }
    
    public static void testlogError(String error){
        test.log(LogStatus.FAIL,"<pre>" + error.toString() + "</pre>");
    }
    
    
    
    public static String randomNumber() throws IOException, InterruptedException{
    	
    	 int random_num = 1;
		    Random t = new Random();
		    // random integers in [1000, 800000]
		    random_num=	(t.nextInt(800000));
		    ProgramID = String.valueOf(random_num);		    
		    System.out.println(ProgramID);
			Thread.sleep(1000);
			return ProgramID;
			
    }
       
	
    public static String filereadID(String url) throws IOException {

		FileReader inputFile = new FileReader(url);
		// Instantiate the BufferedReader Class
		BufferedReader bufferReader = new BufferedReader(inputFile);

		// Variable to hold the one line data
		String text;
		// Read file line by line and print on the console
		while ((text = bufferReader.readLine()) != null) {

			fetchedID = text;
			// System.out.println(CommonMethod.ProgramID);

		}

		// Close the buffer reader
		bufferReader.close();
		return fetchedID;
	}
	
	
	
	
	public static void FluentWait(final String objectLocater){
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver())							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(2, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);
		
			wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement  apply(WebDriver t) {
				//return t.findElement(By.xpath(".//*[contains(text(),'+ Add')]"));
				try {
					
					element= findElement(objectLocater);
				
				} catch (IOException e) {
					e.printStackTrace();
				}
				return element;
			}
		});
		
	}
	
	public static WebElement WaitUntilPresence(String objectlocator) throws IOException{
		
		
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		
		FileInputStream fp1 = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/DashbordLocator.properties");
		OR.load(fp1);
		
		
		
		String objecttypeandvalues = OR.getProperty(objectlocator);
		
		System.out.println(objecttypeandvalues);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		System.out.println("obj val: " + objectvalue);
		switch(objecttype){
		
		
		  
		case "id":
			
			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectvalue))));
			
		case "xpath":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectvalue))));
			                
        case "name":

        	return (wait.until(ExpectedConditions.presenceOfElementLocated(By.name(objectvalue))));
			               		  
        case "class":

        	return (wait.until(ExpectedConditions.presenceOfElementLocated(By.className(objectvalue))));

        case "tagname":

        	return (wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(objectvalue))));
 
        case "css":
			  
        	return (wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(objectvalue))));
        
        case "linkText":
  			  
        	return (wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(objectvalue))));
        default:
        	
        	return null;
}
		//By css = findElement(objectlocator);
		
	}
	
     public static WebElement WaitUntilVisibility(String objectlocator) throws IOException{
		
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		
		FileInputStream fp1 = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/DashbordLocator.properties");
		OR.load(fp1);
		
		String objecttypeandvalues = OR.getProperty(objectlocator);
		
		System.out.println(objecttypeandvalues);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		System.out.println("obj val: " + objectvalue);
		switch(objecttype){
		  
		case "id":
			
			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectvalue))));
			
		case "xpath":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectvalue))));
			                
        case "name":

        	return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectvalue))));
			               		  
        case "class":

        	return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(objectvalue))));

        case "tagname":

        	return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(objectvalue))));
 
        case "css":
			  
        	return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectvalue))));
        
        case "linkText":
  			  
        	return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(objectvalue))));
        default:
        	
        	return null;
}
		//By css = findElement(objectlocator);
		
	}
	
	public static Boolean WaitUntilInVisibility(String objectlocator) throws IOException{
   
		   Properties OR = new Properties();
		   FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		   OR.load(fp);
		   
		   FileInputStream fp1 = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/DashbordLocator.properties");
		   OR.load(fp1);
		   
		   
		   
		   String objecttypeandvalues = OR.getProperty(objectlocator);
		   
		   System.out.println(objecttypeandvalues);
		   String[] splits = objecttypeandvalues.split("~");
		   String objecttype = splits[0]; 
		   System.out.println("obj type: " + objecttype);
		   String objectvalue = splits[1];
		   System.out.println("obj val: " + objectvalue);
		   switch(objecttype){
		   
		   
		     
		   case "id":
		    
		    return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(objectvalue))));
		    
		   case "xpath":
		
		    return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(objectvalue))));
		                    
		   case "name":
		
		    return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(objectvalue))));
		                       
		   case "class":
		
		    return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(objectvalue))));
		
		   case "tagname":
		
		    return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(objectvalue))));
		  
		   case "css":
		      
		    return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(objectvalue))));
		         
		   case "linkText":
		        
		    return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(objectvalue))));
		        
		   default:
		          
		    return null;
		 } 
  }
	
	public static void displayhiddenElement(String objectLocator) throws IOException{
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver();
	    js.executeScript("arguments[0].setAttribute('style', 'display:block !important;')",findElement(objectLocator));
	   }
	
	
	
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	public static String getLabel(float responseTime) {			
		if (responseTime <= 4000){
			
	    return "<span class='label outline info'>" + responseTimeInMS() + " Milliseconds" + "</span>";
		}
		
		else
		{
		return "<span class='label outline fatal'>" + responseTimeInMS() + " Milliseconds" + "</span>";
		}	    
	}
	
	public static String getStatus(int statusCode) {
		if(statusCode == 200) {
			return "Pass";
		}else {
			return "Need Discussion";
		}
	}
	
	public static String getLabel(String responsetime) {	
		
	    return "<span class='label outline info'>" + responseTimeInMS() + " Milliseconds" + "</span>";    
	}
	 
	 public static String responseTimeInMS(){
		 DecimalFormat df = new DecimalFormat("#.###");
		 Float time = responsetime*1000;
		 String value = df.format(time);
		 return  value;
	 }
	 public static void getCreditIdList(String keyName,String key1, String key2) {
		 Map<String, List<HashMap<String,List<HashMap<String, String>>>>> jsonMap = new HashMap<String, List<HashMap<String,List<HashMap<String, String>>>>>();
			jsonMap = (HashMap) CommonMethod.res.jsonPath().getMap("$");
		    for (String key : jsonMap.keySet()) {
		    	//System.out.println("key" +key);
		    	if (key.equals(keyName)) {
		    		List<HashMap<String,List<HashMap<String, String>>>> list = new ArrayList<HashMap<String,List<HashMap<String, String>>>>();
		    		list = jsonMap.get(keyName);
		    		for(int i = 0; i < list.size(); i++)
				    {
		    			 Map<String, List<HashMap<String, String>>> jsonMap2 = new HashMap<String, List<HashMap<String, String>>>();
		    			 jsonMap2 = list.get(i);
		    			 //System.out.println("value are: " + jsonMap2);
		    			 Set<Map.Entry<String, List<HashMap<String, String>>>> entryMap = jsonMap2.entrySet();
		    			 for (Entry entry : entryMap) {
		    					Object key3 = entry.getKey();
		    					if (key3.toString().equals(key1)) {
		    						if(entry.getValue()!=null) {
		    			    			//System.out.println("key is: "+ key3 +" value is:"+ entry.getValue().toString());
		    			    			List<HashMap<String, String>> list2 = new ArrayList<HashMap<String, String>>();
		    				    		list2 = jsonMap2.get(key3);
		    				    		//System.out.println("list2 is: "+list2);
		    				    		Map  jsonMap3 = new HashMap();
		    				    		int list2Size = list2.size();
		    				    		//System.out.println("size is :" +list2Size);
		    				    		for(int j=0;j<list2Size ; j++) {
		    				    			jsonMap3 = list2.get(j);
		    				    			Set<Map.Entry> entryMap2 = jsonMap3.entrySet();
			    			    			 for (Entry entry2 : entryMap2) {
			    			    					Object key4 = entry2.getKey();
			    			    					if (key4.toString().equals(key2)) {
			    			    						if(entry2.getValue()!=null && entry2.getValue().toString().contains("1000143658") ) {
			    			    							//System.out.println("short id is: "+ jsonMap3.get("shortId").toString() +" value is:"+ entry2.getValue().toString());
			    			    							shortIdAndCreditIdhm.put(jsonMap3.get("shortId").toString(), entry2.getValue().toString());
			    			    							//Assert.assertFalse(key1 + "value is empty.", entry.getValue().toString().isEmpty());		
			    			    						}else {
			    			    							//Assert.assertFalse(key1 + " value is null.", true);
			    			    						}
			    			    									
			    			    			    	}
			    			    			  }
		    				    		}
		    			    			
		    			    			//System.out.println("value are: " + jsonMap3);
		    			    			
		    						}else {
		    							//Assert.assertFalse(key3 + " value is null.", true);
		    						}
		    						 
		    			    	}
		    			  }
				    
				    }
		    				    			
		    	}		
		    	else if(key.toLowerCase().contains("error")) {
		    		//Assert.assertTrue("Error is present in the response.", false);
		    	}		    	
		    }
	 }
	 
	 public static String getResponseValue(String key) {
		 String keyV = "";
		 Map<String,String> jsonMap = new HashMap<String,String>();
		 jsonMap = (HashMap) CommonMethod.res.jsonPath().getMap("$");
	     for (String key1 : jsonMap.keySet()) {
	    	if (key1.equals(key)) {
	    		keyV = jsonMap.get(key);
	    		 Assert.assertFalse( jsonMap.get(key).isEmpty(), "Token value is empty.");
	    		
	    	}else {
	    		 Assert.assertTrue(false, key+" is not present in the response");
	    	}
	    	
	    }
	    return keyV;
	 }
	 
	 public static String getCreditUrl(String SheetName, int rowNum, String creditShortId) {
		 String url = "";
		 Set<Map.Entry<String, String>> entryMap = CommonMethod.shortIdAndCreditIdhm.entrySet();
		 for (Entry entry : entryMap) {
			 Object shortId = entry.getKey();
			 if(shortId.toString().equals(creditShortId)) {
				 String creditIdValue = entry.getValue().toString();
				 System.out.println("short Id is: "+shortId.toString()+" and credit id is : "+creditIdValue);
			    Object creditId = CommonMethod.shortIdAndCreditIdhm.get(shortId);
				CommonMethod.res = given().log().ifValidationFails()
						.header("Authorization", header)
						.spec(reqSpec)
						.when()
						.get("Credits/credit/"+data.getCellData(SheetName, "projectType", rowNum)+"/"+data.getCellData(SheetName, "projectId", rowNum)+"?"+
						"creditId="+creditId.toString()+"&return=form&forceSync=form")
						.then()
						.extract()
						.response();
		
			   
			    url = CommonMethod.getResponseValue("form");
				System.out.println("Form url is: "+url);				 

			 }else {
				 	
				 }
		 }
		 if(url.equals("")) {
			 System.out.println(" Status of credit short id "+creditShortId+" is NOT ATTEMPTED. ");
		 }else {
			 
		 }
		 return url;	    
	 }
	 
	
	public static void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	
	
	
	public static void openUrl(String url) throws InterruptedException, IOException{
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		driver.get(url);
		Thread.sleep(1000);
	}
		
}