package com.MyOVS.PersonalAdministration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.MyOVS.listener.ErrorUtil;
import com.MyOVS.util.TestUtil;

public class Login extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	static WebDriver driver;
	
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunable(PersonalAdministration, this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping the test case "+this.getClass().getSimpleName()+" as the Runmode is set to No");
			
			throw new SkipException("Skipping test case as the Runmode is set to No");
		}
		runmodes=TestUtil.getDataSetRunmodes(PersonalAdministration, this.getClass().getSimpleName());
	}
	
	@Test(dataProvider="getTestData")
	public void loginTest(String URL, String Scenario){
		// Test the runmode of the current data set
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Login");
		
		
		//Opening the browser
		driver=new FirefoxDriver();	
		
		// Maximizing the browser
		driver.manage().window().maximize();
		
		//Navigating to the URL by combining the User name and password as Selenium does not recognize the windows components
		driver.get(URL);	
		
		
		try{
		Thread.sleep(3000L);
		Alert alt = driver.switchTo().alert();	
		System.out.println(alt.getText());
		}catch(Throwable t){
			
		}
		
		
		// Verifying the Title to check Login
		String Title= driver.getTitle();
		
		driver.quit();
			
		try{
			if((!Title.equals("Home")&&!Scenario.equals("Negative"))){
				throw new Exception();
			}
		}catch(Throwable t){
			// Test fails if there is any exception
			ErrorUtil.addVerificationFailure(t);
			fail=true;
			isTestPass=false;
			}	
		
		}		
		
	
	
	
	@DataProvider	
	public Object[][] getTestData(){
		
		return TestUtil.getData(PersonalAdministration, this.getClass().getSimpleName());
	}
	
	
	@AfterTest
	public void reportTestResult(){
		
		if(isTestPass){
			TestUtil.reportDataSetResult(PersonalAdministration, "Test Cases", TestUtil.getRowNum(PersonalAdministration, this.getClass().getSimpleName()), "PASS");
		}else{
			TestUtil.reportDataSetResult(PersonalAdministration, "Test Cases", TestUtil.getRowNum(PersonalAdministration, this.getClass().getSimpleName()), "FAIL");
		}
	}
	
	
	@AfterMethod
	public void reporter(){
		if(skip)
			TestUtil.reportDataSetResult(PersonalAdministration, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail)
			TestUtil.reportDataSetResult(PersonalAdministration, this.getClass().getSimpleName(), count+2, "FAIL");
		else
			TestUtil.reportDataSetResult(PersonalAdministration, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	
	
}
