package test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SampleZip {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Logger APP_LOGS = Logger.getLogger("devpinoyLogger");
		APP_LOGS.debug("Test message");
		
		Properties config = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//qtpselenium//config//Config.properties");
		config.load(ip);
		System.out.println(config.get("screenshotPath"));
		
		
		//Zip zip = new Zip();
		//zip.folder("C:\\Files\\E-books\\Stuff\\Security testing", "C:\\Files\\E-books\\Stuff\\Security testing.zip");

	}

}
