package com.loginpage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Datadriven {
	
	WebDriver driver;
	WebElement loginbutton;
	WebElement invalid;
	WebElement invalid2;
	WebElement credentials;
	WebElement button;
	WebElement  validationMessage;
	
	@Test (dataProvider= "credentails")
	public void launchbrowser ( String emailpicker, String pwdpicker) {
		 
		driver = new ChromeDriver ();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://stage.oreon.reontel.com/");
		
		credentials = driver.findElement(By.xpath("//input[@type='email']"));
		credentials.sendKeys(emailpicker);
		
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pwdpicker);
		
        button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
		
	}
	
	@DataProvider (name="credentails")
	public Object[][] testdata () throws InterruptedException {
		
		Object [][] input = new Object [11][2];
		input [0][0] = "test1@gmail.com";
		input [0][1] = "password1";
		
		
		input [1][0]= "test2@gmail.com";
		input [1][1] = "password2";
		
	
		input [2][0] = "test3@gmail.com";
		input [2][1] = "password3";
		
		
		input [3][0] = "test4@gmail.com";
		input [3][1] = "password4";
		
		
		input [4][0] = " test5@gmail.com";
		input [4][1] = " password5";
		
		
		input [5][0] = "test6@gmail.com";
		input [5][1] = "password1";
		
		
		input [6][0] = "test7@gmail.com";
		input [6][1] = "password666";
		
		
		input [7][0] = "1234gmail.com";
		input [7][1] = "pass10";
		
		
		input [8][0] = "test9@gmail.com";
		input [8][1] = "password8";
		
		
    	input [9][0] = "test10@gmail.com";
	    input [9][1] = "password9";
		
		
		input [10][0] = "test11@gmail.com";
		input [10][1] = " password10";
		
		return input;
		
		
	}

	
	

}
