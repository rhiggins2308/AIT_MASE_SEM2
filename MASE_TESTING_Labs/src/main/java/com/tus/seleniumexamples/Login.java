package com.tus.seleniumexamples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args) {
		//1. Define the web driver
		System.setProperty("webdriver.chrome.driver", "//Users//robbie//Documents//Chrome_Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//2. Open a web page
		driver.get("https://www.unitedrugby.com");

	}

}