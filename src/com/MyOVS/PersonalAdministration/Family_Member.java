package com.MyOVS.PersonalAdministration;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.MyOVS.listener.ErrorUtil;
import com.MyOVS.util.TestUtil;

public class Family_Member extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;		
	
	
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunable(PersonalAdministration, this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping the test case "+this.getClass().getSimpleName()+" as the Runmode is set to No");			
			throw new SkipException("Skipping test case as the Runmode is set to No");
		}
		runmodes=TestUtil.getDataSetRunmodes(PersonalAdministration, this.getClass().getSimpleName());
	}
	
	@Test(description="Adding a Family member")
	public void familyMember(){
		// Test the run mode of the current data set
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case - Family Member");				
		
		try{
			
			// Accessing the same browser
			openBrowser();	
			
			// Login - Continue if login is successful		
			Assert.assertTrue(login("Home"), "Login Failed");
					
			// Click on the sub menu Personal Details
			clickMenu("PersonalAdministration", "Family");
			
			verifyWidget("FamilyHeader", "FAMILY MEMBER");	
			
			// Getting the count of members
			member_count = driver.findElements(By.xpath("//div[contains(@id, '_header')]")).size();
			
			
			// Click on add button
			clickButton("AddBtn_Family");
			
			// Verify all the drop down values and select a drop down value
			String MemberType[]=config.getProperty("MemberTypeDropdown").split(",");
			verifyDropdownOptions(MemberType,"MemberType");			
			selectDropDownValue("MemberType",config.getProperty("MemberType_Value"));
			
			// Enter Last Name
			editTextField("LastName_Family",config.getProperty("LastName_Family_Value"));
			
			
			// Enter First Name
			editTextField("FirstName_Family",config.getProperty("FirstName_Family_Value"));	
			
			
			// Enter Date of birth
			clickButton("BirthDateBtn_Family");
			clickButton("BirthDateBtn_Family_05Dec2012");			
			
			//Verify all gender drop down and select a value			
			verifyDropdownOptions(config.getProperty("GenderDropdown_Family").split(","), "Gender_Family");
			selectDropDownValue("Gender_Family",config.getProperty("Gender_Family_value"));
			
			// Enter SSN Number
			editTextField("SSN_Family",config.getProperty("SSN_Family_value"));
			
			
			
			// Verify all the drop down values and select a drop down value
			String Nationality_Family[]=config.getProperty("NationalityDropdown").split(",");
						
			// The below code is to replace the country name with ","
			for(int i=0;i<Nationality_Family.length;i++){
				if(Nationality_Family[i].equals("Wallis Futuna (WF)")){
					Nationality_Family[i]="Wallis,Futuna (WF)";
				}else{
					Nationality_Family[i]=Nationality_Family[i];
					}
				}
						
			verifyDropdownOptions(Nationality_Family,"Nationality_Family");			
			selectDropDownValue("Nationality_Family", config.getProperty("Nationality_Family_value"));
			
			// Click on save button
			driver.findElement(By.className("FooterUpdateButton")).click();
			
			// Verify all the saved values
			
			Thread.sleep(10000L); // Waiting for the page to refresh
			assertEquals(getObjectText("MessageBox_Family"), "The information has been created successfully.");
			
			// Verify the member is created and displayed in the view mode
			Firstname=config.getProperty("FirstName_Family_Value");
			Lastname=config.getProperty("LastName_Family_Value");	
			driver.findElement(By.xpath("//div[contains(@id, '"+Firstname+" "+Lastname+member_count+"')]")).click();
			
			// Verify all the values
			Thread.sleep(3000L);
			// Asserting Member type drop down value
			assertEquals(getObjectText_Family("MemberType_Family_View"),config.getProperty("MemberType_Value"));
			
			// Asserting Last name value in display mode
			APP_LOGS.debug("Asserting Last name value in display mode");
			assertEquals(getObjectText_Family("LastName_Family_View"),config.getProperty("LastName_Family_Value"));
			
			// Asserting First name value in display mode
			APP_LOGS.debug("Asserting First name value in display mode");			
			assertEquals(getObjectText_Family("Firstname_Family_View"),config.getProperty("FirstName_Family_Value"));
						
			// Asserting Birth date value in display mode
			APP_LOGS.debug("Asserting First name value in display mode");
			assertEquals(getObjectText_Family("BirthDate_Family_View"),config.getProperty("BirthDate_Family_Value"));
			
			// Asserting Gender drop down value in display mode
			APP_LOGS.debug("Asserting Gender drop down value in display mode");
			assertEquals(getObjectText_Family("Gender_Family_View"),config.getProperty("Gender_Family_value"));
			
			// Asserting SSN value in display mode
			APP_LOGS.debug("Asserting SSN value in display mode");
			assertEquals(getObjectText_Family("SSN_Family_View"),config.getProperty("SSN_Family_value"));
			
			// Asserting Nationality drop down value in display mode
			APP_LOGS.debug("Asserting Nationality drop down value in display mode");
			assertEquals(getObjectText_Family("Nationality_Family_View"),config.getProperty("Nationality_Family_value"));			
						
			// Clicking on the delete button			
			driver.findElement(By.xpath("//*[@id='ctl00_m_g_20a3dc7a_1485_4f04_b303_51e2ac75b870_ctl00_widgetFamily_ctl08_dynamicFamilyForm_ctl00_accorPane"+Firstname+" "+Lastname+member_count+"_content_ctl00_Delete_View_FamilyMemberType']/input")).click();
			Thread.sleep(5000L);
			alertwindow("ok");	
			
			//Page refresh
			Actions actionObject = new Actions(driver);
			actionObject.sendKeys(Keys.F5).perform();
			
			// Verifying whether the Family member is deleted			
			int Element=driver.findElements(By.xpath("//div[contains(@id, '"+Firstname+" "+Lastname+member_count+"_header')]")).size();	
			
			if(Element!=0){
				APP_LOGS.debug("Member "+Firstname+" "+Lastname+" is not deleted");
				throw new SkipException("Member "+Firstname+" "+Lastname+" is not deleted");
			}else{
				APP_LOGS.debug(" "+Firstname+" "+Lastname+" is deleted");
			}				
			closeBrowser();
			
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


