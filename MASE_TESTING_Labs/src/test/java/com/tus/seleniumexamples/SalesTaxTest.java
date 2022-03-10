package com.tus.seleniumexamples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SalesTaxTest {
	
	WebDriver driver;
	
	@BeforeEach
	void setup() {
		//1. Define the web driver
		System.setProperty("webdriver.chrome.driver", "//Users//robbie//Documents//Chrome_Drivers/chromedriver");
		driver = new ChromeDriver();
		
		//2. Open a web page
		driver.get("file:///Users//robbie//OneDrive//Education//MSc-AppliedSoftwareEngineering//MASE//Semester_1//Agile_Design_Testing//4_BDD_Selenium_Cucumber//Selenium_Exercises//Ex_2//sales_tax//sales_tax.html");
	}

	@Test
	public void testTitle() {
		assertEquals("Sales Tax Calculator", driver.getTitle());
	}
	
	@Test
	public void testCalculateTotal() {
		
		//Get the subtotal element and set the value
		WebElement subtotalField = driver.findElement(By.id("subtotal"));
		subtotalField.clear();
		subtotalField.sendKeys("100");
		
		//Get the TaxRate element and set the value
		WebElement taxRateField = driver.findElement(By.id("taxRate"));
		taxRateField.clear();
		taxRateField.sendKeys("10");
		
		//Click on Calculate Button
		WebElement calculateButton = driver.findElement(By.id("calculate"));
		calculateButton.click();
		
			//Get the sales tax and verify its value
			WebElement salesTaxField = driver.findElement(By.id("salesTax"));
			//System.out.println(salesTaxFueld.getText());
			assertEquals("10", salesTaxField.getAttribute("value"));
			
			//Get the total element and verify its value
			WebElement totalField = driver.findElement(By.id("total"));
			assertEquals("110.00", totalField.getAttribute("value"));			
	}
	
	@AfterEach
	void tearDown() {
		driver.quit();
	}
}
