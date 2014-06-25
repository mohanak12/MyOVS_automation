package test;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.MyOVS.PersonalAdministration.TestSuiteBase;


public class DriverClass extends TestSuiteBase {

	public WebDriver driver;
	public boolean isInitialised=false;
	public static Properties or=null;
	public static WebElement menupath;
	public static WebElement submenupath;
	public static Actions builder;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		File file = new File("D:\\Selinium\\Java_Workspace\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());	
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer(); 
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		WebDriver driver= new InternetExplorerDriver(ieCapabilities);
		
		
		//WebDriver driver = new FirefoxDriver();
		driver.navigate().to("http://192.168.0.90");
		String main= driver.getWindowHandle();
		try{
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
	        
	    }catch(AWTException e) {
	        e.printStackTrace();
	    }
		System.out.println("test1");
		System.out.println(main);
		driver.get("http://192.168.0.90/Pages/Home.aspx");
		System.out.println(driver.getTitle());
		System.out.println("");
		or = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\MyOVS\\config\\or.properties");
		or.load(ip);		
		
		
		
		menupath = driver.findElement((By.linkText("Personal Administration")));
		submenupath = driver.findElement((By.linkText("Personal Details")));
		
		
		builder = new Actions(driver);
		builder.moveToElement(menupath).build().perform();
		
		submenupath.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println(driver.findElement(By.xpath("//*[@id='ctl00_m_g_20a3dc7a_1485_4f04_b303_51e2ac75b870_ctl00_widgetFamily_up_ct_widgetFamily']/header")).getText());
		
		driver.findElement(By.xpath("//*[@id='ctl00_m_g_20a3dc7a_1485_4f04_b303_51e2ac75b870_ctl00_widgetFamily_ctl10_addButton']")).click();
		
		driver.findElement(By.xpath("//*[@id='ctl00_m_g_20a3dc7a_1485_4f04_b303_51e2ac75b870_ctl00_widgetFamily_ctl08_dynamicFamilyForm_ctl01_Add_FamilyMemberType_dd_Add_FamilyMemberType']"));
		
		Select selectBox = new Select(driver.findElement(By.xpath("//*[@id='ctl00_m_g_20a3dc7a_1485_4f04_b303_51e2ac75b870_ctl00_widgetFamily_ctl08_dynamicFamilyForm_ctl05_Add_Gender_dd_Add_Gender']")));
        List<WebElement> selectOptions = selectBox.getOptions();
                
       
        for(WebElement temp:selectOptions){
        	System.out.println(temp.getText());
        	
        }
		//driver.findElement(By.xpath("//*[@id='ctl00_m_g_0a5c18e8_3756_4845_b38e_7cfcaedbae6d_ctl00_widgetPersonalDetails_ctl10_editButton']")).click();
													   
		//driver.findElement(By.xpath("//*[@id='ctl00_m_g_0a5c18e8_3756_4845_b38e_7cfcaedbae6d_ctl00_widgetPersonalDetails_ctl08_dynamicPersonalDetailsForm']/div[13]/div[1]/input")).click();
		//Thread.sleep(3000L);
		//System.out.println(driver.findElement(By.xpath("//*[@id='ctl00_m_g_0a5c18e8_3756_4845_b38e_7cfcaedbae6d_ctl00_widgetPersonalDetails_w-QuickMessagebox']")).getText());
		
		
		
				
				
			
	}

	
	public static void credentials(){
		try{
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
        
    }catch(AWTException e) {
        e.printStackTrace();
    }
	}
}


