package com.lo.Testcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.jayway.restassured.http.ContentType;
import com.leedOnline.driver.BaseClass;
import com.leedOnline.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class EB_SS109_RainwaterManagementTest extends BaseClass{
	@Test
	@Parameters({"rowNum", "ApiSheet" })
	public void Get_LEED_creditsCredit(int rowNum, String SheetName) throws IOException {
		try {	
			String shortId = "SS109";
			CommonMethod.ExtentReportConfig();
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			String creditURL = CommonMethod.getCreditUrl(SheetName, rowNum, shortId);
			if(creditURL.equals("")) {
				System.out.println("Credit URL not found.");
			}else {
				CommonMethod.openUrl(creditURL);
			}
			
			
			CommonMethod.apiRequestId = CommonMethod.res.header("X-Api-Request-Id");
			CommonMethod.responsetime = CommonMethod.responsetime();
			CommonMethod.test =  CommonMethod.extent
					.startTest("Get_LEED_creditsCredit Api "+ CommonMethod.getLabel(CommonMethod.responseTimeInMS()),
							"Get credit details.")
					.assignCategory("api test");
			
			System.out.println("Api Request Id " + CommonMethod.apiRequestId);
			System.out.println("Authorization Token Generated " + header);
			System.out.println("Response received from API " + CommonMethod.res.asString());
			System.out.println("Responsetime of API " + CommonMethod.responseTimeInMS());
			
			CommonMethod.res.then().assertThat().statusCode(200);		  
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
			
			CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
			CommonMethod.testlog("Pass", "Api Request Id is : " + "<br>" + CommonMethod.apiRequestId);
			CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
			CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
			CommonMethod.testlog("Pass", "verifies response from API" + "<br>" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "API responded in : " + CommonMethod.responseTimeInMS() + " Milliseconds");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
