package com.MyOVS.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.MyOVS.util.Xls_Reader;

public class TestBase {
	public static Logger APP_LOGS=null;	
	public static Properties config=null;
	public static Properties or=null;
	public static Xls_Reader suiteXls=null;
	public static Xls_Reader PersonalAdministration=null;
	public static Xls_Reader suiteBxls=null;
	public static Xls_Reader suiteCxls=null;
	public static boolean isInitalized= false;
	
	
	public void initialize() throws Exception{
		
		if(!isInitalized){
			
		//Logs
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		APP_LOGS = Logger.getLogger("devpinoyLogger");	
		
		
		// Property files
		APP_LOGS.debug("Loading property files");
		
		config = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\MyOVS\\config\\Config.properties");
		config.load(ip);	
		
		or = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\MyOVS\\config\\OR.properties");
		or.load(ip);	
		
		APP_LOGS.debug("Loaded property files successfully");
		
		
		
		// XLS files
		APP_LOGS.debug("Loading XLS files");
		
		PersonalAdministration = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\MyOVS\\xls\\PersonalAdministration.xlsx");		
		suiteXls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\MyOVS\\xls\\Suite.xlsx");
		
		APP_LOGS.debug("Loaded XLS (Excel) files successfully");
		isInitalized= true;
		}
		
	}
	
	

}
