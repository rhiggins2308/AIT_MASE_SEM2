//package com.cucumber;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import io.cucumber.java.After;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import static org.junit.Assert.assertEquals;
//import org.openqa.selenium.By;
//
//public class RegisterUser {
//
//	WebDriver driver;
//
//	@Given("^user is logged in and navigates to registration page$")
//	public void user_login() throws Throwable {
//		// 1. Define the web driver
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\David\\eclipse-workspace\\mase-ericsson-project\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.get("http://localhost:8080/mase-group-project/");
//		WebElement registerPage=driver.
//				findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/div/div/div/a[3]"));
//		registerPage.click();
//
//	}
//
//	@When("^clicks on the different fields and fills in the details and clicks register user$")
//	public void user_goes_to_t_shirt_tab() throws Throwable {
//		WebElement nameFirst=driver.
//				findElement(By.xpath("/html/body/div/div/div/div/div/div/form/div[1]/div[1]/input"));
//		WebElement nameSecond=driver.
//				findElement(By.xpath("/html/body/div/div/div/div/div/div/form/div[1]/div[2]/input"));
//		WebElement password=driver.
//				findElement(By.xpath("/html/body/div/div/div/div/div/div/form/div[3]/div[1]/input"));
//		WebElement repeatPassword=driver.
//				findElement(By.xpath("/html/body/div/div/div/div/div/div/form/div[3]/div[2]/input"));
//		WebElement employeeTypeServiceRep=driver.
//				findElement(By.xpath("/html/body/div/div/div/div/div/div/form/select/option[2]"));
//		WebElement register=driver.
//				findElement(By.xpath("/html/body/div/div/div/div/div/div/form/div[4]/button"));
//		nameFirst.click();
//		nameFirst.sendKeys("David");
//		nameSecond.sendKeys("Finlay");
//		password.sendKeys("password");
//		repeatPassword.sendKeys("password");
//		employeeTypeServiceRep.click();
//		register.click();
//		
//	   
//	}
//
//
//	@Then("^the alert should show the success$")
//	public void tshirt_added_confirmation() throws Throwable {
//		WebElement successMessage=driver.
//				findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/p/text()"));
//		assertEquals("Employee Registration Successful!   ", successMessage.getText());
//		
//	}
//	
//	
//	@After //note import from cucumber api not junit
//	public void tearDown(){
//		driver.quit();
//	}
//
//}
