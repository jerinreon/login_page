package com.loginpage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class Validloginwithdashboard {
	


	 WebDriver driver;
	 WebDriverWait wait;
	 WebElement validusername;
	 WebElement validpassword;
	 Random random;
	 WebElement hrms;
	 WebElement element;
	 WebElement departmentField;
	
	@BeforeClass
	public void launchbrowser () {
		 
		driver = new ChromeDriver ();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://stage.oreon.reontel.com/");
		random = new Random();
		
		
	}
	
	  // verify that the valid login credentials with user name and password which the system can be login
	  //login
    @Test (priority=1)
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
	  
    @Test (priority=2)
    public void hrms () {
    	
    	
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	
    	 hrms =	driver.findElement(By.linkText("HRMS"));
    	 hrms.click();
    	 
    	 driver.findElement(By.xpath("//div[@class=' d-flex justify-content-start align-items-end gap-2'][normalize-space()='Masters']")).click();
    	 driver.findElement(By.xpath("//div[contains(text(),'Departments')]")).click();
    	 
    	 
    	 element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='crm_header']//*[name()='svg']")));
		 element.click();
    	 
		 
		 List<String> departments = Arrays.asList("HR", "manager", "Testing", "Engineering", "Finance");
		 int randomIndex = random.nextInt(departments.size());
		 String randomDepartment = departments.get(randomIndex);
		 
		   departmentField = driver.findElement(By.xpath("//input[@class='ant-input css-nllxry input undefined']"));
	        departmentField.sendKeys(randomDepartment);
	        
	        
	        WebElement submitButton = driver.findElement(By.xpath("//button[@class='ant-btn css-nllxry ant-btn-default purchaseButton']"));
	        submitButton.click();
		 
    	 
    }
    
    

}
