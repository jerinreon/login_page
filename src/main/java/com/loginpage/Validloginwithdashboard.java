package com.loginpage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validloginwithdashboard {
	
	

	WebDriver driver;
	WebElement validusername;
	WebElement validpassword;
	
	
	@BeforeClass
	public void launchbrowser () {
		 
		driver = new ChromeDriver ();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://stage.oreon.reontel.com/");
		
		
	}
	
	  // verify that the valid login credentials with user name and password which the system can be login
	  //login
    @Test (priority=5)
	  public void validcrendentials () {
		 
  	  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		  
		  
		  validusername = driver.findElement(By.xpath("//input[@placeholder='Enter Your Email']"));
		  validusername.sendKeys("admin@reontel.com");
		  
		  validpassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		  validpassword.sendKeys("Reon@123");
		  
		  driver.findElement(By.xpath("//button[@type='submit']")).click();
		  
		  String  actualtitle;
		  String expectedtitle = "Dashboard";
		  
		  actualtitle = driver.findElement(By.xpath("//p[@class='crm_header']")).getText();
		  Assert.assertEquals(actualtitle, expectedtitle);
	  }
	  
    
    

}
