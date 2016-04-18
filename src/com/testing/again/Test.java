package com.testing.again;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Function;
import com.thoughtworks.selenium.Wait;

import bsh.Capabilities;

public class Test
{
	WebDriver driver; 
	@BeforeSuite
	public void setUp()
	{
		driver = new FirefoxDriver();
		driver.get("https://hmswebcu01.cu.lan/hms/");
	}
	
	@org.testng.annotations.Test
	public void test() throws IOException
	{
		/*Cookie cookie = new Cookie("testname", "testvalue");
		driver.manage().addCookie(cookie);
		driver.manage().deleteCookie(cookie);
		DesiredCapabilities cap = new DesiredCapabilities();
		FirefoxProfile profile = new FirefoxProfile();
		profile.addExtension(new File("D:\\Webdriver\\firebug-2.0.13-fx.xpi"));
		driver = new FirefoxDriver(profile);*/
		
		WebElement element = driver.findElement(By.id("asm_login_name"));
		System.out.println("element text:"+element.getAttribute("value"));
		
		//Actions action = new Actions(driver);
		//action.moveToElement(element);
		//action.
		//action.build().perform();
		
		new Actions(driver).moveToElement(element).click().keyDown(Keys.SHIFT).sendKeys("test").perform();
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement jsElement = (WebElement)executor.executeScript("return document.getElementById('asm_login_name')");
		jsElement.clear();
		jsElement.sendKeys("again");
		
		List<WebElement> webElements = (List<WebElement>)executor.executeScript("return document.getElementsByName('asm_login_name')");
		for(WebElement webElement: webElements)
		{
			webElement.clear();
			webElement.sendKeys("againagain");
			System.out.println("element text:"+webElement.getAttribute("value"));
		}
		driver.get("D:\\Webdriver notes\\Alert.html");
		driver.findElement(By.tagName("button")).click();
		driver.switchTo().alert().dismiss();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>()
		{

			@Override
			public Boolean apply(WebDriver driver)
			{
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath("//div[@class='uploadElement']")).isEnabled();
				//return null;
			}
			
		});
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.withTimeout(2, TimeUnit.MINUTES);

		WebElement element1 = wait.until(new Function<WebDriver,WebElement>(){
			public  WebElement apply(WebDriver driver)
			{
				return driver.findElement(By.id("id"));
			}
		});
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("www.us.oracle.com:8080");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
		//driver.quit();
	}
	

}
