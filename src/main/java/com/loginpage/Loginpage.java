package com.loginpage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class Loginpage {
	
	
	WebDriver driver;
	WebElement loginbutton;
	WebElement invalid;
	WebElement invalid2;
	WebElement  button;
	WebElement button1;
	WebElement usernameField;
	WebElement loginButton1;
	WebElement validationMessage;
	WebElement button2;
	WebElement existemail;
	WebElement verification;
	
	
	@BeforeClass
	public void launchbrowser () {
		 
		driver = new ChromeDriver ();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://stage.oreon.reontel.com/");
		
		
	}
	
	//verify that click on login button without username and password
	
	@Test (priority=1)
	public void logincredentails () {
		
		 driver.navigate().refresh();
		 loginbutton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
		 loginbutton.click();
		 
	}
	
	//verify that invalid login username and password
	
	@Test (priority=0)
	public void invalidlogin (){
		
		 
		invalid = driver.findElement(By.xpath("//input[@type=\"email\"]"));
		
		invalid.sendKeys("test123@gmail.com");
		
		invalid2 = driver.findElement(By.xpath("//input[@type=\"password\"]"));
		invalid2.sendKeys("admin");
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		
		
			
	}
	
	//validate the error message of the email field
	@Test (priority=2)
  public void validation () throws InterruptedException {
	  
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		 
		button = driver.findElement(By.xpath("//button[@type='submit']"));
		  button.click();
		  
		 Thread.sleep(3000);
		  String actualmessage;
		  String excpexctedmessage="please eneter your email id ";
		  actualmessage = driver.findElement(By.xpath("//div[contains(text(),'Fill email')]")).getText();
		  Assert.assertEquals(actualmessage, excpexctedmessage);
		
		  
	
		  
  }
		//validate the error message of the password field
	  @Test (priority=3)
	    public void testBlankPassword() throws InterruptedException {
	        // Enter a username but leave password blank
		  Thread.sleep(4000);
	      
	        // Find the login button and click it
	          driver.findElement(By.xpath("//button[@type='submit']")).click();
	      

	        // Locate the validation message element
	       Thread.sleep(3000);
	         validationMessage = driver.findElement(By.xpath("//div[@id='user_password_help']"));
	        String actualMessage = validationMessage.getText();

	        // Expected message for password blank
	      String expectedMessage = "please enter password";
	       Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match when password is blank!");
	  }
      
	  //verify the login button whether is enabled or not
	  @Test (priority= 4)
	  public void testButtonIsEnabled() {
	        // Locate the button element
	        WebElement button2 = driver.findElement(By.xpath("//button[@type='submit']"));

	        // Verify if the button is enabled
	        boolean isEnabled = button2.isEnabled();
	        Assert.assertTrue(isEnabled, "The button should be enabled!");
	  }  
	  

}
