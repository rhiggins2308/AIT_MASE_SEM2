package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

public class ViewCategoryStepsDef {

WebDriver driver;
	
	@Given("^user is successfully logged in$")
	public void user_is_successfully_logged_in() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "//Users//robbie//Documents//Chrome_Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		
		WebElement emailElement=driver.findElement(By.name("email"));
		emailElement.sendKeys("mase@gmail.com");
		
		WebElement passwordElement=driver.findElement(By.name("passwd"));
		passwordElement.sendKeys("12345");
		
		WebElement signInButton=driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
		signInButton.click();
		
		WebElement loggedInName=driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
		assertEquals("Joe Bloggs", loggedInName.getText());
	}

	@When("^user selects tshirts tab$")
	public void user_selects_tshirts_tab() throws Throwable {
		WebElement tabElement=driver.findElement(By.xpath("//*[@id=\\\"block_top_menu\\\"]/ul/li[3]/a"));
		tabElement.click();
	}

	@Then("^user gets tshirts displayed$")
	public void user_gets_tshirts_displayed() throws Throwable {
		WebElement tshirtLabel=driver.findElement(By.xpath("//*[@id=\\\"center_column\\\"]/h1/span"));
		assertEquals("T-shirts&nbsp;", tshirtLabel.getText());
	}

	@Then("^user gets there is one product displayed$")
	public void user_gets_there_is_product_displayed(int arg1) throws Throwable {
		WebElement prodCountLabel=driver.findElement(By.xpath("//*[@id=\\\"center_column\\\"]/h1/span2"));
		assertEquals("There is 1 product.", prodCountLabel.getText());	
	}	
	
	@After //note import from cucumber api not junit
	public void tearDown(){
		driver.quit();
	}
}
