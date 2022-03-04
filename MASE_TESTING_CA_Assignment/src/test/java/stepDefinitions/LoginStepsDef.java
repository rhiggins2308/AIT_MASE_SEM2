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

public class LoginStepsDef {
	static WebDriver driver;

	@Given("^user is on the login page$")
	public void user_is_on_the_login_page() throws Throwable {
		// 1. Define the web driver
		System.setProperty("webdriver.chrome.driver", "//Users//robbie//Documents//Chrome_Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		WebElement signInButton=driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
		signInButton.click();

	}

	@When("^user enters correct username$")
	public void user_enters_correct_username() throws Throwable {
		WebElement emailElement=driver.findElement(By.name("email"));
		emailElement.sendKeys("mase@gmail.com");
	   
	}

	@When("^user enters correct password$")
	public void user_enters_correct_password() throws Throwable {
		WebElement passwordElement=driver.findElement(By.name("passwd"));
		passwordElement.sendKeys("12345");
	}

	@When("^user clicks the login button$")
	public void user_clicks_the_login_button() throws Throwable {
		WebElement loginButton=driver.findElement(By.id("SubmitLogin"));
		loginButton.click();
	}

	@Then("^user get login confirmation$")
	public void user_get_login_confirmation() throws Throwable {
		WebElement loggedInName=driver.
				findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
		assertEquals("Joe Bloggs", loggedInName.getText());
	}
	
	@When("^user enters incorrect password$")
	public void user_enters_incorrect_password() throws Throwable {
		driver.findElement(By.name("passwd")).sendKeys("1234");
	}
	
	@Then("^user gets invalid credentials warning$")
	public void user_gets_invalid_credentials_warning() throws Throwable {
		WebElement errorMessage=driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/ol/li"));
		//String message=driver.findElement(By.xpath("/html/body/div[1]/form/div[5]/span")).getText();
		assertEquals("Invalid password.", errorMessage.getText());
	}
	
	//Steps for the DataTable
	@When("^user enters username \"([^\"]*)\"$")
	public void user_enters_username(String userName) throws Throwable {
		WebElement emailElement=driver.findElement(By.name("email"));
		emailElement.sendKeys(userName);
	}

	@When("^user enters  password \"([^\"]*)\"$")
	public void user_enters_password(String password) throws Throwable {
			driver.findElement(By.name("passwd")).sendKeys(password);
	}

	@Then("^user get \"([^\"]*)\" message$")
	public void user_get_message(String message) throws Throwable {
		WebElement loggedInName=driver.
				findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
		assertEquals(message, loggedInName.getText());
	}
	
	@After //note import from cucumber api not junit
	public void tearDown(){
		driver.quit();
	}
}
