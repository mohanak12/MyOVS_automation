package com.MyOVS.listener;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class MyListener extends AbstractWebDriverEventListener{
	
	public void beforeFindBy(By by,WebElement element,WebDriver driver){
		for (int i = 0; i < 2; i++) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: red; border: 3px solid red;");
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
	}

}
}
