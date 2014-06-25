package com.MyOVS.PersonalAdministration;




import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import com.MyOVS.base.TestBase;
import com.MyOVS.listener.ErrorUtil;
import com.MyOVS.util.TestUtil;



public class TestSuiteBase extends TestBase{

	 public static WebDriver driver;
	 public WebElement menupath;
	 public WebElement submenupath;
	 public Actions builder;
	 public boolean isMethodPass=true;
	 //public static 
	 boolean initialized=false;
	 static int member_count;
	 static String Firstname;
	 static String Lastname;
	 public static String browser;
	
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		
		initialize();
		
		if(!TestUtil.isSuiteRunable(suiteXls,"PersonalAdministration")){
			APP_LOGS.debug("Skipping the test case as the Runmode is set to No");
			
			throw new SkipException("Skipping test case as the Runmode is set to No");
			
		}		
		
	} 
	
	/** Open Browser as specified in property file*/
	public WebDriver openBrowser(){		
		
		
		browser=config.getProperty("browserType");
		if(browser.equalsIgnoreCase("mozilla")){
			
			driver= new FirefoxDriver();			
			APP_LOGS.debug("Initialising Firefox driver");
			
		}else if(browser.equalsIgnoreCase("IE")) {
			File file = new File("D:\\Selinium\\Java_Workspace\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());	
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer(); 
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver= new InternetExplorerDriver(ieCapabilities);
			APP_LOGS.debug("Initialising Internet explorer driver");
			
		}else if(browser.equalsIgnoreCase("chrome")){
			
			driver= new ChromeDriver();
			APP_LOGS.debug("Initialising Chrome driver");
		}		
			
			APP_LOGS.debug("Maximising driver");			
			driver.manage().window().maximize();			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			initialized=true;
			
					
			return driver;
		}
	
	
	/** Navigate to  URL and verify Title*/
	public boolean login(String title){
		
		if(browser.equalsIgnoreCase("Mozilla")){
			
			driver.get(config.getProperty("testSiteName_mozilla")); 
			APP_LOGS.debug("Navigating to "+config.getProperty("testSiteName_mozilla"));
			
		}else if(browser.equalsIgnoreCase("IE")){
			
			driver.get(config.getProperty("testSiteName_IE"));			
			credentials();			
			APP_LOGS.debug("Navigating to "+config.getProperty("testSiteName_IE"));
			
		}else if(browser.equalsIgnoreCase("Chrome")){
			
		}		
		
		//Verifying login
		try{
			Thread.sleep(10000L);			
			Assert.assertEquals(driver.getTitle(), title);
			APP_LOGS.debug("Login Passed");
			return true;
			
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			isMethodPass=false;
			APP_LOGS.debug("Error --- Login Failed");
			return false;
			
			}		
		
	}
	
	private void credentials() {
		
		try {
            
            Robot robot = new Robot();
            // Creates the delay of 5 sec so that you can open notepad before
            // Robot start writting
            robot.delay(5000);             
            robot.keyPress(KeyEvent.VK_S);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyPress(KeyEvent.VK_K);            
            robot.keyPress(KeyEvent.VK_BACK_SLASH);
            robot.keyPress(KeyEvent.VK_M);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyPress(KeyEvent.VK_R);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyPress(KeyEvent.VK_TAB);
            
            
            robot.keyPress(KeyEvent.VK_M);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyPress(KeyEvent.VK_R);
            robot.keyPress(KeyEvent.VK_1);            
            robot.keyPress(KeyEvent.VK_ENTER);
            
        } catch (AWTException e) {
            e.printStackTrace();
        }
		
	}

	/** Hover over Menu and click on the Sub menu */
	public void clickMenu(String menu, String submenu){
		
		try{
		menupath = driver.findElement((By.xpath(or.getProperty(menu))));
		submenupath = driver.findElement((By.xpath(or.getProperty(submenu))));	
						
		builder = new Actions(driver);
		builder.moveToElement(menupath).build().perform();
		//highlightElement(driver.findElement((By.xpath(or.getProperty(menu)))));
		//highlightElement(driver.findElement((By.xpath(or.getProperty(submenu)))));
		submenupath.click();
		
		APP_LOGS.debug("Clicking on submenu "+submenu);
		
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			isMethodPass=false;
			APP_LOGS.debug(" Error --- Unable to select menu");	
			throw new SkipException(" Error --- Unable to click on the submenu "+submenu);
			
		}
		
	}
	
	/** Verify Widget header name */
	public void verifyWidget( String xpath, String header){
		try{
			//Verify Widget header name			
			Assert.assertEquals(header, driver.findElement(By.xpath(or.getProperty(xpath))).getText());
			APP_LOGS.debug(header+" Widget is displayed");
			}catch(Throwable t){
				ErrorUtil.addVerificationFailure(t);
				isMethodPass=false;
				APP_LOGS.debug(" Error --- "+header+" Widget is not displayed");
				throw new SkipException(" Error --- "+header+" Widget is not displayed");
			
		}
	}
	
	/** Clicks on the button if button is displayed and is enabled */
	public void clickButton(String buttonXpath){
		try{
			APP_LOGS.debug("Click Button "+buttonXpath);
			if(driver.findElement(By.xpath(or.getProperty(buttonXpath))).isDisplayed()&&driver.findElement(By.xpath(or.getProperty(buttonXpath))).isEnabled()){
				highlightElement(driver.findElement(By.xpath(or.getProperty(buttonXpath))));
				driver.findElement(By.xpath(or.getProperty(buttonXpath))).click();	
			}else{
				throw new SkipException(" Error --- "+buttonXpath+" button not displayed or button is disabled");
			}
			}catch(Throwable t){
				isMethodPass=false;
				APP_LOGS.debug(" Error --- "+buttonXpath+" button is not locatable");
				throw new SkipException(" Error --- "+buttonXpath+" button is not locatable");
			}
		}
	
	/** Verifies the available drop down values */
	public void verifyDropdownOptions(String options[], String xpath) {
		try{
		APP_LOGS.debug("Dropdown values "+xpath);
        Select selectBox = new Select(driver.findElement(By.xpath(or.getProperty(xpath))));
        List<WebElement> selectOptions = selectBox.getOptions();
        
        
        List<String> strlist = new ArrayList<String>();
        for(WebElement temp:selectOptions){        	
        	strlist.add(temp.getText());
        	
        }
        
        for(int i=0;i<options.length;i++){        	
        	if(strlist.contains(options[i])){
        		APP_LOGS.debug("Dropdown value "+options[i]+" is present");        		
        	}else{
        		APP_LOGS.debug("Error ---- Dropdown value "+options[i]+" is not present");        		
        	}
        }
		}catch(Throwable t){
			isMethodPass=false;
			APP_LOGS.debug("Error ---- Dropdown "+xpath+" is not present");
			throw new SkipException("Error --- "+xpath+" is not present");
		}
        	
	}
	
	/** Select drop down value */
	public void selectDropDownValue(String dropdownPath, String dropdownValue){
		try{
			APP_LOGS.debug("Select drop down value "+dropdownValue+" in "+dropdownPath);
			Select selectBox = new Select(driver.findElement(By.xpath(or.getProperty(dropdownPath))));
			highlightElement(driver.findElement(By.xpath(or.getProperty(dropdownPath))));
	        selectBox.selectByVisibleText(dropdownValue);			
		}catch(Throwable t){
			isMethodPass=false;
			APP_LOGS.debug("Error ---- Unable to select drop down value "+dropdownValue);
			throw new SkipException("Error ---- Unable to select drop down value "+dropdownValue);
		}
		
	}
	
	/** Returns WebElement if XPath is passed */
	public WebElement getObject(String objectPath){
		try{
			APP_LOGS.debug("Get Object "+objectPath);
			return driver.findElement(By.xpath(or.getProperty(objectPath)));
		}catch(Throwable t){
			isMethodPass=false;
			APP_LOGS.debug("Error ---- "+objectPath+" not found");
			throw new SkipException("Error ---- "+objectPath+" not found");
		}
	}
	
	/** Returns Text from WebElement if XPath is passed based on getText() function*/
	public String getObjectText(String objectPath){
		try{
			APP_LOGS.debug("Get Text from "+objectPath);
			return driver.findElement(By.xpath(or.getProperty(objectPath))).getText();
		}catch(Throwable t){
			isMethodPass=false;
			APP_LOGS.debug("Error ---- "+objectPath+" not found");
			throw new SkipException("Error ---- "+objectPath+" not found");
		}
	}
	
	/**
	 * Enters Text in Text field after clearing the Text field
	 */
	 
	public void editTextField(String xPath, String value){
		try{
			Assert.assertTrue(isDisplayed(xPath), xPath+" is not displayed");
			Assert.assertFalse(isReadOnly(xPath), xPath+" is not a read-only field");
			APP_LOGS.debug("Entering value in the Text field"+xPath);
			highlightElement(driver.findElement(By.xpath(or.getProperty(xPath))));
			driver.findElement(By.xpath(or.getProperty(xPath))).clear();
			driver.findElement(By.xpath(or.getProperty(xPath))).sendKeys(value);
					
		}catch(Throwable t){
			isMethodPass=false;			
			APP_LOGS.debug("Error ---- "+xPath+" not found/Not displayed/is a read-only field");
			throw new SkipException("Error ---- "+xPath+" not found/Not displayed/is a read-only field");
		}
	}
    
	/** Verifies and returns true if the object is a read-only */
	public boolean isReadOnly(String XPath){
		try{
			APP_LOGS.debug("Verifying "+XPath+" is a read-only field");
			driver.findElement(By.xpath(or.getProperty(XPath))).isDisplayed();
			if(driver.findElement(By.xpath(or.getProperty(XPath))).isEnabled())
				return false;
			else
				return true;
			
			}catch(Throwable t){
						
				APP_LOGS.debug("Error ---- "+XPath+" not found");
				throw new SkipException("Error ---- "+XPath+" not found");
			}
	}
	
	/** Verifies and returns true if the object is displayed */
	public boolean isDisplayed(String XPath){
		try{
			APP_LOGS.debug("Verifying "+XPath+" is displayed");
			if(driver.findElement(By.xpath(or.getProperty(XPath))).isDisplayed())
				return true;
			else
				return false;
			
			}catch(Throwable t){
						
				APP_LOGS.debug("Error ---- "+XPath+" not found");
				throw new SkipException("Error ---- "+XPath+" not found");
			}
	}
	
	/** Returns the selected drop down value in the drop down field */
	public String selectedDropdownValue(String xpath){
		try{
			APP_LOGS.debug("Get Selected drop down value"+xpath);
			Select selectBox = new Select(driver.findElement(By.xpath(or.getProperty(xpath))));	
			return selectBox.getFirstSelectedOption().getText();
		}catch(Throwable t){
			APP_LOGS.debug("Error ---- "+xpath+" not found");
			throw new SkipException("Error ---- "+xpath+" not found");
		}
        
	}
	
	/** Returns the available value in the WebElement using attribute value*/
	public String getTextValue(String xpath){
		try{
			APP_LOGS.debug("Get Selected Text value"+xpath);
			return driver.findElement(By.xpath(or.getProperty(xpath))).getAttribute("Value");
		}catch(Throwable t){					
			APP_LOGS.debug("Error ---- "+xpath+" not found");
			throw new SkipException("Error ---- "+xpath+" not found");
	}
        
	}
	
	/** Closes the browser if the browser is open*/
	public void closeBrowser(){
		try{
			APP_LOGS.debug("Closing the Browser");
			driver.quit();
		}catch(Throwable t){
			APP_LOGS.debug("Error ---- driver not found");
			throw new SkipException("Error ---- driver not found");
		}
	}
	
	/** Highlights the webElement*/
	public void highlightElement(WebElement element) {		
	    for (int i = 0; i < 2; i++) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: red; border: 3px solid red;");
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
	    
	}
}	
	/** Handles Alert window when command (Ok/Cancel) is passed to it */
	public void alertwindow(String command){
		try{
		APP_LOGS.debug("Identifying alert window");
		Alert alt = driver.switchTo().alert();		
		
		if(command.equalsIgnoreCase("Ok")){
			APP_LOGS.debug("Clicking Ok button in alert window");
			alt.accept();			
		}else if(command.equalsIgnoreCase("Cancel")){
			APP_LOGS.debug("Clicking Cancel button in alert window");
			alt.dismiss();
		}else{
			APP_LOGS.debug("Command for alert window is not recognised");
			throw new SkipException("Error --- Unable to recognise the command");
		}
		}catch(Throwable t){
			APP_LOGS.debug("Alert window is not found");
			throw new SkipException("Error --- No Alert window found");
		}	
}
		
	public void assertEquals(String actual, String expected){
		APP_LOGS.debug("Asserting  "+actual+" and "+expected+" ");
		try{
		Assert.assertEquals(actual,expected);
		}catch(Throwable t){
			APP_LOGS.debug("Actual: "+actual+" and Expected: "+expected+" are not same ");
			throw new SkipException("Error --- Actual: "+actual+" and Expected: "+expected+" are not same ");
		}
	}
	
	// Family specific methods
	
	public String getObjectText_Family(String objectPath){
		try{
			APP_LOGS.debug("Get Text from "+objectPath);			
			return driver.findElement(By.xpath(or.getProperty(objectPath+"_Start")+Firstname+" "+Lastname+member_count+or.getProperty(objectPath+"_End"))).getText();
		}catch(Throwable t){
			isMethodPass=false;
			APP_LOGS.debug("Error ---- "+objectPath+" not found");
			throw new SkipException("Error ---- "+objectPath+" not found");
		}
	}
	
	
}	
		


