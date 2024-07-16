package com.loginpage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
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
    // verify that check box whether is checked or unchecked against to "Name field"
      @Test(priority=7)
      public void verifycheckbox () {
    	  
    	  // Locate the checkbox element
          WebElement checkBox = driver.findElement(By.xpath("//div[contains(@class,'col-9 d-flex justify-content-end')]//input[@type='checkbox']")); // Replace with the actual ID or locator of your checkbox

          // Check if the checkbox is selected
          if (!checkBox.isSelected()) {
              // If not selected, click to select it
              checkBox.click();
              System.out.println("Checkbox checked.");
          } else {
              System.out.println("Checkbox was already checked.");
          }

          // Assert that the checkbox is now selected
          assert checkBox.isSelected();

          // Now, uncheck the checkbox if it is selected
         if (checkBox.isSelected()) {
              // Click to unselect it
              checkBox.click();
              System.out.println("Checkbox unchecked.");
         } else {
             System.out.println("Checkbox was already unchecked.");
          }

          //Assert that the checkbox is now unselected
          assert !checkBox.isSelected();
      }
    	  
    	  
      @Test (priority=9)
   public void verifythedropdown () throws InterruptedException {
	   
    	  
    	  
    	  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	  // Locate the checkbox element
          WebElement checkBox = driver.findElement(By.xpath("//div[contains(@class,'col-9 d-flex justify-content-end')]//input[@type='checkbox']")); 
          
          // Replace with the actual ID or locator of your checkbox
          checkBox.click();
        
          // Locate the dropdown and text field elements
          WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='search'])[1]"))); // Replace with the actual ID or locator of your dropdown
          dropdownElement.click();
           Assert.assertTrue(dropdownElement.isDisplayed(), " it is not showing");
         
      }
      //verify the name input field working when give enter the value
      @Test(priority=8)
      public void verifyname () {
    	  
    	
    	 // wait = new WebDriverWait(driver, Duration.ofSeconds(20));
          //WebElement uncheckedbox = driver.findElement(By.xpath("//div[contains(@class,'col-9 d-flex justify-content-end')]//input[@type='checkbox']"));
          //uncheckedbox.click();
         
            driver.navigate().refresh();
	        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[1]")));
	        String expectedValue = "Test Value";
	        
	        inputField.sendKeys(expectedValue);
	        String actualValue = inputField.getAttribute("value");
	        Assert.assertEquals(actualValue, expectedValue, "The input field is not working");
          
             
   }
          @Test (priority=10)
         public void verifytheinvalidname () {
        	 
        		 driver.navigate().refresh();
        	 // Locate the name input field
             WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[1]")));

             // Define the invalid name containing numbers
             String invalidName = "John123&(";

             // Clear any existing text in the name field
           

             // Attempt to enter the invalid name into the name field
             nameField.sendKeys(invalidName);

             // Retrieve the current value from the name field
             String currentValue = nameField.getAttribute("value");

             // Check if the field removed numbers or displayed an error message
             boolean containsNumbers = currentValue.matches(".*\\d.*");

             // Assert that the name field does not contain numbers
             Assert.assertFalse(containsNumbers, "The name field should not accept numbers.");
         }
    
          
          //verify that mandatory symbol of name fields
     @Test (priority=11)
      public void mandatorysymbolofname () {
    	  
    	  
    	  WebElement nameFieldLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='col-3 add_lead_fiels_name']"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText = nameFieldLabel.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol = labelText.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol, "The mandatory symbol is not present for the name field.");
    	  
     }
     
     
          //verify that mandatory symbol of name fields
          @Test(priority=12)
          public void mandotrysymboloftype () {
        	  
        	  
          
          
          WebElement typeFieldLabel2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mb-3 mx-5 add_lead_fiels_name']"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = typeFieldLabel2.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	  
          
          
          
      }
        //verify that mandatory symbol of name phonenum
          
      @Test(priority=13)
      public void mandatorysymbolofphonenumberfield () {
    	  
    	  
    	  
          WebElement phonenumFieldLabel2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[contains(@class,'pt-2')]/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[contains(@class,'col-12 mx-3')]/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[@class='card lead-card p-4']/div[@class='row d-flex justify-content-start']/div[3]/div[1]"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = phonenumFieldLabel2.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	  
    	  
    	  
      }
       
      @Test (priority=14)
       public void mandatorysymbolofemail () {
    	   
    	   
    	  WebElement emailFieldLabel2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[contains(@class,'pt-2')]/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[contains(@class,'col-12 mx-3')]/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[@class='card lead-card p-4']/div[@class='row d-flex justify-content-start']/div[4]/div[1]"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = emailFieldLabel2.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	  
    	  
    	  
    	   
       }
      
      
      @Test (priority=15)
      public void mandatorysymbolofbranch () {
    	  
    	  
    	  WebElement branchFieldLabel2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[contains(@class,'pt-2')]/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[contains(@class,'col-12 mx-3')]/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[@class='card lead-card p-4']/div[@class='row d-flex justify-content-start']/div[5]/div[1]"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = branchFieldLabel2.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	  
    	  
    	  
      }
      
      
      @Test (priority=16)
      public void mandotrysymbolofleadsource () {
    	  
    	WebElement scrollableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']")));
    	 
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript ("arguments[0].scrollTop = 500", scrollableElement);
        
        
        
        
  	  WebElement leadsource = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[@class=' pt-2']/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[@class=' col-12 mx-3']/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[2]/div[1]/div[1]/div[1]"))); // Replace with your label's xpath

        // Get the text of the label
        String labelText2 = leadsource.getText();

        // Check if the label contains the mandatory symbol (*)
        boolean containsMandatorySymbol2 = labelText2.contains("*");

        // Assert that the mandatory symbol is present
        Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
  	    	  
          
      }
      
      @Test (priority=17)
       public void leadpurpose () {
    	   
    	  
    	  
    	  WebElement leadpurpose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-4 ps-5 pe-5']//div[@class='mb-2 add_lead_fiels_name']"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = leadpurpose.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	    	  
    	   
    	  
       }
      
      
      @Test (priority=18)
      public void productname () {
    	  
    	  
    	  
    	  WebElement productname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[contains(@class,'pt-2')]/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[contains(@class,'col-12 mx-3')]/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[2]/div[1]/div[3]/div[1]"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = productname.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	    	  
    	   	  
    	  
      }
      
      @Test (priority=19)
      public void salesperson () {
    	  
    	  WebElement scrollableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']")));
     	 
      	JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript ("arguments[0].scrollTop = 800", scrollableElement);
          
          
    	  
          
          
          WebElement salesperson = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[contains(@class,'pt-2')]/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[contains(@class,'col-12 mx-3')]/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[3]/div[1]/div[1]/div[1]"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = salesperson.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	    	  
    	  
    	  
      }
      
      
      @Test (priority=20)
      public void status ( ) {
    	  
    	  WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[contains(@class,'pt-2')]/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[contains(@class,'col-12 mx-3')]/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[3]/div[1]/div[2]/div[1]"))); // Replace with your label's xpath

          // Get the text of the label
          String labelText2 = status.getText();

          // Check if the label contains the mandatory symbol (*)
          boolean containsMandatorySymbol2 = labelText2.contains("*");

          // Assert that the mandatory symbol is present
          Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
    	    	 	  
    	  
      }
    
     @Test (priority=21)
      public void country () {
    	  
    	 WebElement scrollableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']")));
     	 
       	JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript ("arguments[0].scrollTop = 1200", scrollableElement);
    	 
    
           WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[@class=' pt-2']/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[@class=' col-12 mx-3']/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[4]/div[1]/div[1]/div[1]"))); // Replace with your label's xpath

           // Get the text of the label
           String labelText2 = country.getText();

           // Check if the label contains the mandatory symbol (*)
           boolean containsMandatorySymbol2 = labelText2.contains("*");

           // Assert that the mandatory symbol is present
           Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
     	    	 	  
           
           
           
    	  
      }
      
    
     @Test (priority=22)
     public void location () {
    	 
    	 
    	 

         WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //body/div[@id='root']/div/div[@class='m-0 p-0']/div[@class='m-0 p-0 layoutStyle']/div[@class='d-flex p-0 m-0']/div[contains(@class,'pt-2')]/div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']/div[@class='mt-4 mx-3']/div[@class='row']/div[contains(@class,'col-12 mx-3')]/div/div/form[@class='ant-form ant-form-horizontal css-nllxry']/div[4]/div[1]/div[3]/div[1]"))); // Replace with your label's xpath

         // Get the text of the label
         String labelText2 = country.getText();

         // Check if the label contains the mandatory symbol (*)
         boolean containsMandatorySymbol2 = labelText2.contains("*");

         // Assert that the mandatory symbol is present
         Assert.assertTrue(containsMandatorySymbol2, "The mandatory symbol is not present for the name field.");
   	    	 	  
    	 
    	 
     }
      
      @Test (priority=23)
      public void pageupload () throws InterruptedException, AWTException {
    	  
    	  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	  
     	 WebElement scrollableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pt-2 pl-0 ps-3 pe-2 layoutStyle_childrenStyle']")));
      	 
    	
    	  JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript ("arguments[0].scrollTop = 1700", scrollableElement);
          
          WebElement fileInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ant-btn css-nllxry ant-btn-default editProductButton']")));
  
          fileInput.click();
          
          Thread.sleep(2000);

          Robot robot= new Robot();
          String filepath = "C:\\Users\\Jerin\\Downloads\\test.pdf\n"+ "C:\\Users\\Jerin\\Downloads\\SamplePNGImage_10mbmb.png\n";
          
          
          
          StringSelection selection = new StringSelection(filepath);

          Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
          
      		 
      		 robot.keyPress(KeyEvent.VK_CONTROL);
      		 
      		 robot.keyPress(KeyEvent.VK_V);
      		 
      	


      		 robot.keyRelease(KeyEvent.VK_V);
      		 robot.keyRelease(KeyEvent.VK_CONTROL);
      		 robot.keyPress(KeyEvent.VK_ENTER);
      		 robot.keyRelease(KeyEvent.VK_ENTER);
     
      	Thread.sleep(5000);
 
      		 
         
      		 
             

      		 

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
	        
	        
	        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='ant-input ant-input-status-error css-nllxry input undefined']")));
	        String expectedValue = "Test Value";
	        
	        inputField.sendKeys(expectedValue);
	        String actualValue = inputField.getAttribute("value");
	        Assert.assertEquals(actualValue, expectedValue, "The value in the input field does not match the expected value.");
    }

	        
	        
		 
    	 
    }
    
    


