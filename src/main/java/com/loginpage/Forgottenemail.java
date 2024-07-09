package com.loginpage;

import static org.testng.Assert.assertEquals;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Forgottenemail {
	
	WebDriver driver;
	WebElement existemail;
	WebElement verification;
	WebElement textElement;

	
	@BeforeClass
	public void launchbrowser () {
		 
		driver = new ChromeDriver ();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://stage.oreon.reontel.com/");
	}
	
	
	  //verify the forgotpassword button
	  @Test (priority=1)
	  public void forgotpassword () {
		  
		  
		  driver.findElement(By.xpath("//span[normalize-space()='Forgot password?']")).click();
		  
		  
		  
		  
	  }
	  
	  
	  //verify the validation message whether it is existemail or not in forgotarea
	  @Test (priority=2)
	  public void existemailid () throws InterruptedException {
		  
		  
		 Thread.sleep(2000);
		  existemail = driver.findElement(By.xpath("//input[@placeholder='Enter Your Email']"));
		  existemail.sendKeys("test@gmail.com");
		  
		  verification = driver.findElement(By.xpath("//button[@type='submit']"));
		  verification.click();
		  
		  Thread.sleep(2000);
		  String actualvalidation;
		  String expectedvalidation = "Username not recognized. Please try again.";
		  
		  actualvalidation = driver.findElement(By.xpath("//span[normalize-space()='User does not exists']")).getText();
		  Assert.assertEquals(actualvalidation,expectedvalidation);
		  
	  }
	  
	//verify by the successfull message after eneter the valid email data
	  
	  @Test(priority=3)
	  
	  public void validdata () throws InterruptedException {
		  
		  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 
		   driver.navigate().refresh();
		  driver.findElement(By.xpath("//input[@placeholder='Enter Your Email']")).sendKeys("admin@reontel.com");
		  driver.findElement(By.xpath("//button[@type='submit']")).click();
		  
		 
		  String actualmessage;
		  String expectedmessage = "A password reset link has been sent to your email address.";                      
		  
		  actualmessage= driver.findElement(By.xpath("//div[@class='bg-success-subtle p-4 rounded-3']")).getText();
		  Assert.assertEquals(actualmessage, expectedmessage);
		  
		  
		  
	  }
	  //verify that the back navigation from forgotpassowrd
	  @Test (priority=4)
	  public void navigation ( ) throws InterruptedException {
		  
		  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		  
		  
          try {
             
		  driver.navigate().back();
		  
        	  textElement = driver.findElement(By.xpath("//p[normalize-space()='Welcome, Login to your account.']"));
              String text = textElement.getText();

              // Check if the text is blank
              if (text == null || text.trim().isEmpty()) {
                  System.out.println("The text element is blank.");
                  // You can also use an assertion if required
                  Assert.fail("The text element is blank.");
              } else {
                  System.out.println("The text element contains: " + text);
                  // Further assertions or actions
              }
          } catch (NoSuchElementException e) {
              System.out.println("The text element was not found on the page.");
              // You can also use an assertion if required
              Assert.fail("The text element was not found on the page.",e);
          }
          
          catch (Exception e) {
              System.out.println("An unexpected error occurred: " + e.getMessage());
              Assert.fail("An unexpected error occurred and page is blank", e);
          }
          
      }
	  
	
	  
	  

}

