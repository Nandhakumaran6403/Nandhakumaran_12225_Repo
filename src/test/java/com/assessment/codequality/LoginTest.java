package com.assessment.codequality;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTest {

	private WebDriver driver;

	@BeforeEach
	void start() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:3000/login");
	}

	@AfterEach
	void close() {

		driver.quit();
	}

	@Test
	void testLoginWithValidCredentialsAsDonator() throws InterruptedException {
		WebElement usernameInput = driver.findElement(By.name("name"));
		usernameInput.sendKeys("Nandhakumaran");

		WebElement passwordInput = driver.findElement(By.name("password"));
		passwordInput.sendKeys("123");

		WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		loginButton.submit();

		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.equals("http://localhost:3000/usermain")) {
			System.out.println("Positive login test passed for Donator!");
		} else {
			System.out.println("Positive login test failed. Unexpected redirection.");
		}

		assertEquals("http://localhost:3000/usermain", driver.getCurrentUrl());

	}

	@Test
	void testLoginWithValidCredentialsAsEmployee() throws InterruptedException {
		WebElement usernameInput = driver.findElement(By.name("name"));
		usernameInput.sendKeys("nandha");

		WebElement passwordInput = driver.findElement(By.name("password"));
		passwordInput.sendKeys("nandha123");

		WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		loginButton.submit();

		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.equals("http://localhost:3000/main")) {
			System.out.println("Positive login test passed for employee!");
		} else {
			System.out.println("Positive login test failed. Unexpected redirection.");
		}

		assertEquals("http://localhost:3000/main", driver.getCurrentUrl());

	}

	@Test
	void testLoginWithInvalidCredentials() throws InterruptedException {

		WebElement usernameInput = driver.findElement(By.name("name"));
		usernameInput.sendKeys("InvalidUser");

		WebElement passwordInput = driver.findElement(By.name("password"));
		passwordInput.sendKeys("InvalidPassword");

		WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		loginButton.submit();

		WebElement errorMessage = null;
		try {
			errorMessage = driver.findElement(By.className("text-red-500"));
		} catch (Exception e) {

		}
		if (errorMessage != null && errorMessage.isDisplayed()) {
			System.out.println("Negative login test passed for invalid credentials!");
		} else {
			System.out.println("Negative login test failed. Expected error message not found.");
		}
		
		assertEquals("http://localhost:3000/login", driver.getCurrentUrl());


	}

}
