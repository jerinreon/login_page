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
	  
    //verify the crm module when click on the link
    @Test (priority=2)
     public void crm () {
    	 
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	
    	
    	WebElement crm = driver.findElement(By.linkText("CRM"));
    	 crm.click();
    	 
    	 String actualvalue;
    	 String expectedvalue = "Lead";
    	 
    	 actualvalue = driver.findElement(By.xpath("//span[@class='layoutStyle_spanStyle']")).getText();
    	 Assert.assertEquals(actualvalue, expectedvalue);
      
     }
    
    //verify that add the lead when clicked on the plus button
    @Test (priority=3)
    public void lead () {
    	
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    
    	WebElement lead = driver.findElement(By.xpath("//p[@class='crm_header']//*[name()='svg']"));
    	lead.click();
    	
    	String actualvalue;
    	String expectedvalue = "Add Lead";
    	
    	actualvalue = driver.findElement(By.xpath("//p[@class='crm_header pb-0 m-0']")).getText();
    	Assert.assertEquals(actualvalue, expectedvalue,"button is not working");
	    	
 
    }
    
    
    //verify that if button is disabled then clicked on it that will provide by a error message
    @Test (priority=4)
    public void validationmessageofcontactinfo () throws InterruptedException  {
    	
    
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	WebElement validation = driver.findElement(By.xpath("//div[@id='rc-tabs-0-tab-contact_info']//div[1]"));
    	validation.click();
    	 
    	WebElement validationMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Please Submit The Basic Info']")));
    	String actualMessage = validationMessageElement.getText();
    	
    	String expectedmessage = "Please Submit The Basic Info";	
    	Assert.assertEquals(actualMessage, expectedmessage,"validation message not found");
    	
    	
    	
    }
    
    //verify that contactinfo whether it is enabled or not
    @Test(priority=5)
  public void contactinfo () {
	  
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	 List<WebElement> tabButtons = driver.findElements(By.xpath("//div[@id='rc-tabs-9-tab-contact_info']//div[1]"));

        
         Assert.assertTrue(tabButtons.isEmpty(), "tab button should be disabled!");
    
         }
          
    
  //verify that if button is disabled then clicked on it that will provide by an error message
    @Test (priority=6)
     public void validationmessageofsalesfollowup () {
    	 
    	 
    	 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
     	WebElement validation = driver.findElement(By.xpath("//div[contains(text(),'Sales Follow Up')]"));
     	validation.click();
     	 
     	WebElement validationMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Please Submit The Basic Info']")));
     	String actualMessage = validationMessageElement.getText();
     	
     	String expectedmessage = "Please Submit The Basic Info";	
     	Assert.assertEquals(actualMessage, expectedmessage,"validation message not found");
    	 
     }
    
   
    
    
    
    
    
    
    
    

    //verify the search bar working when give a name which should be consider by the values into the field
    @Test (priority =4,enabled=false)
    public void searchbar () {
    	
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
    	search.sendKeys("Jerin");
    	
    	String actualvalue;
    	String expectedvalue = "Jerin";
    	
    	 actualvalue= driver.findElement(By.xpath("//span[normalize-space()='Jerin']")).getText();
       	Assert.assertEquals(actualvalue,expectedvalue, "searchbar is not working");
    			
    
    }
 
    
    
 
    
    @Test (enabled= false)
    public void hrms () {
    	
    	
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	
    	 hrms =	driver.findElement(By.linkText("HRMS"));
    	 hrms.click();
    	 
    	 driver.findElement(By.xpath("//div[@class=' d-flex justify-content-start align-items-end gap-2'][normalize-space()='Masters']")).click();
    	 driver.findElement(By.xpath("//div[contains(text(),'Departments')]")).click();
    	 
    	 
    	 element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='crm_header']//*[name()='svg']")));
		 element.click();
    	 
		 
		 List<String> departments = Arrays.asList("testdm", "cv", "bbc", "mca", "bca");
		 int randomIndex = random.nextInt(departments.size());
		 String randomDepartment = departments.get(randomIndex);
		 
		   departmentField = driver.findElement(By.xpath("//input[@class='ant-input css-nllxry input undefined']"));
	        departmentField.sendKeys(randomDepartment);
	        
	        
	        WebElement submitButton = driver.findElement(By.xpath("//button[@class='ant-btn css-nllxry ant-btn-default purchaseButton']"));
	        submitButton.click();
		 
    	 
    }
    
    

}
