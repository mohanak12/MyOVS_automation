package com.MyOVS.PersonalAdministration;





import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import com.MyOVS.listener.ErrorUtil;
import com.MyOVS.util.TestUtil;

public class Personal_Details extends TestSuiteBase{
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
	
	
	@Test
	public void modifyPersonalDetails(){	
				
		try{
		
			// Opening browser
			openBrowser();		
		
			// Login - Continue if login is successful		
			Assert.assertTrue(login("Home"), "Login Failed");
		
			// Click on the sub menu Personal Details
			clickMenu("PersonalAdministration", "PersonalDetails");
			verifyWidget("PersonalDetailsHeader", "PERSONAL DETAILS");		
			
			// Click on the edit button
			clickButton("editButtonPA");			
			
			// Verify all the drop down values and select a drop down value 
			String Title_PA[]={"Miss","Mr","Mrs","Ms"};			
			verifyDropdownOptions(Title_PA,"Title_PA");			
			selectDropDownValue("Title_PA", config.getProperty("Title_PA_Value"));		
			
			// Enter First name
			editTextField("FirstName",config.getProperty("FirstName_PA_Value"));		
			
			// Enter Last name
			editTextField("LastName",config.getProperty("LastName_PA_Value"));			
			
			// Enter Middle name
			editTextField("MiddleName",config.getProperty("MiddleName_PA_Value"));		
			
			// Enter KnownAs
			editTextField("KnownAs",config.getProperty("KnownAs_PA_Value"));			
			
			// Enter NameAtBirth
			editTextField("NameAtBirth",config.getProperty("NameAtBirth_PA_Value")); 						
			
			// Enter Initials
			editTextField("Initials",config.getProperty("Initials_PA_Value"));			
			
			
			// Verify Read-only field, DateOfBirth value 
			APP_LOGS.debug("Verifying Date of Birth is a read-only field or not");
			Assert.assertTrue(isReadOnly("DateOfBirth"));
				
			APP_LOGS.debug("Verifying text in the Date of Birth field");
			Assert.assertEquals(config.getProperty("DateOfBirth"), getTextValue("DateOfBirth"));						
			
			
			
			// Verify all the drop down values and select a drop down value
			String Language_PA[]=config.getProperty("LanguageDropdown").split(",");
			verifyDropdownOptions(Language_PA,"Language_PA");			
			selectDropDownValue("Language_PA",config.getProperty("Language_PA_Value"));
			//Assert.assertEquals(selectedDropdownValue("Language_PA"),config.getProperty("Language_PA_Value"));
						
			
			// Verify all the drop down values and select a drop down value
			String Nationality_PA[]=config.getProperty("NationalityDropdown").split(",");
			
			// The below code is to replace the country name with ","
			for(int i=0;i<Nationality_PA.length;i++){
				if(Nationality_PA[i].equals("Wallis Futuna (WF)")){
					Nationality_PA[i]="Wallis,Futuna (WF)";
				}else{
					Nationality_PA[i]=Nationality_PA[i];
				}
			}
			
			verifyDropdownOptions(Nationality_PA,"Nationality_PA");			
			selectDropDownValue("Nationality_PA", config.getProperty("Nationality_PA_Value"));
			//Assert.assertEquals(selectedDropdownValue("Nationality_PA"),config.getProperty("Nationality_PA_Value"));
			
			
			// Verify SSN Number
			Assert.assertTrue(isReadOnly("SSN"), "SSN Number field is not read-only");
			//Assert.assertEquals(selectedTextValue("SSN"),config.getProperty("SSN"));
			
			
			// Verify all the drop down values and select a drop down value
			String Gender_PA[]=config.getProperty("GenderDropdown").split(",");
			verifyDropdownOptions(Gender_PA,"Gender_PA");			
			selectDropDownValue("Gender_PA", config.getProperty("Gender_PA_Value"));
			//Assert.assertEquals(selectedDropdownValue("Gender_PA"),config.getProperty("Gender_PA_Value"));
			
			
			
			// Click Save
			driver.findElement(By.className("FooterUpdateButton")).click();			
			
			Thread.sleep(5000L);			
			
			// Verify Success message
			APP_LOGS.debug("Verifying success message after saving Personal details");
			Assert.assertEquals(getObjectText("MessageBox_PA"),"The information has been updated successfully.");
			
			
			// Verifying the saved values in View mode
			APP_LOGS.debug("Verifying Title in view mode");
			Assert.assertEquals(getObjectText("Title_PA_View"), config.getProperty("Title_PA_Value"));
						
			APP_LOGS.debug("Verifying First Name in view mode");
			Assert.assertEquals(getObjectText("FirstName_PA_View"), config.getProperty("FirstName_PA_Value"));
			
			APP_LOGS.debug("Verifying Last Name in view mode");
			Assert.assertEquals(getObjectText("LastName_PA_View"), config.getProperty("LastName_PA_Value"));
			
			APP_LOGS.debug("Verifying Middle Name in view mode");
			Assert.assertEquals(getObjectText("MiddleName_PA_View"), config.getProperty("MiddleName_PA_Value"));
			
			APP_LOGS.debug("Verifying \"Known as\" in view mode");
			Assert.assertEquals(getObjectText("KnownAs_PA_View"), config.getProperty("KnownAs_PA_Value"));
			
			APP_LOGS.debug("Verifying Name at Birth in view mode");
			Assert.assertEquals(getObjectText("NameAtBirth_PA_View"), config.getProperty("NameAtBirth_PA_Value"));
			
			APP_LOGS.debug("Verifying Initials in view mode");
			Assert.assertEquals(getObjectText("Initials_PA_View"), config.getProperty("Initials_PA_Value"));
			
			APP_LOGS.debug("Verifying Date of Birth in view mode");
			Assert.assertEquals(config.getProperty("DateOfBirth"),getObjectText("DateOfBirth_PA_View"));
			
			APP_LOGS.debug("Verifying Language in view mode");
			Assert.assertEquals(getObjectText("Language_PA_View"), config.getProperty("Language_PA_Value"));
			
			APP_LOGS.debug("Verifying Nationality in view mode");
			Assert.assertEquals(getObjectText("Nationality_PA_View"), config.getProperty("Nationality_PA_Value"));
			
			APP_LOGS.debug("Verifying SSN in view mode");
			Assert.assertEquals(config.getProperty("SSN"),getObjectText("SSN_PA_View"));
			
			APP_LOGS.debug("Verifying Gender in view mode");
			Assert.assertEquals(getObjectText("Gender_PA_View"), config.getProperty("Gender_PA_Value"));
			
			closeBrowser();
			
		
		}catch(Throwable t){
			// Test fails if there is any exception
			ErrorUtil.addVerificationFailure(t);
			fail=true;
			isTestPass=false;
		}		
		
			
	}
	

		
	@AfterTest
	public void reportTestResult(){
		
		isTestPass=isMethodPass;
		if(isTestPass){
			TestUtil.reportDataSetResult(PersonalAdministration, "Test Cases", TestUtil.getRowNum(PersonalAdministration, this.getClass().getSimpleName()), "PASS");
		}else{
			TestUtil.reportDataSetResult(PersonalAdministration, "Test Cases", TestUtil.getRowNum(PersonalAdministration, this.getClass().getSimpleName()), "FAIL");			
		}
		isTestPass=true;
		
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
	
	
	
	
	
	@DataProvider	
	public Object[][] getTestData(){
		return TestUtil.getData(PersonalAdministration, this.getClass().getSimpleName());
	}

}
