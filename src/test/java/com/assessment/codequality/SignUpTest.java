package com.assessment.codequality;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpTest {

	private WebDriver driver;

	@BeforeEach
	void start() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS); // Implicit wait
		driver.get("http://localhost:3000/signup");
	}

	@AfterEach
	void close() {
		driver.quit();
	}

	@Test
	void testSignupWithValidData() throws InterruptedException {

		WebElement nameInput = driver.findElement(By.name("name"));
		nameInput.sendKeys("nandha");

		WebElement emailInput = driver.findElement(By.name("email"));
		emailInput.sendKeys("nandha@example.com");

		WebElement passwordInput = driver.findElement(By.name("password"));
		passwordInput.sendKeys("nandha64");

		WebElement signupButton = driver.findElement(By.xpath("//button[contains(text(),'Sign Up')]"));
		signupButton.submit();

		waitForAlertAndCheckText("Data Added Successfully", 10);

		assertEquals("http://localhost:3000/login", driver.getCurrentUrl());

	}

	@Test
	void testSignupWithEmptyFields() throws InterruptedException {

		WebElement nameInput = driver.findElement(By.name("name"));
		nameInput.sendKeys("");

		WebElement emailInput = driver.findElement(By.name("email"));
		emailInput.sendKeys("sena@example.com");

		WebElement passwordInput = driver.findElement(By.name("password"));
		passwordInput.sendKeys("sena123");

		WebElement signupButton = driver.findElement(By.xpath("//button[contains(text(),'Sign Up')]"));
		signupButton.submit();

		waitForAlertAndCheckText("Please enter Valid Name!!!", 10);

		waitForAlertAndCheckText("Please Enter the Valid Inputs!!!", 10);

		assertEquals("http://localhost:3000/signup", driver.getCurrentUrl());

	}

	private void waitForAlertAndCheckText(String expectedText, int timeoutInSeconds) {
		long endTime = System.currentTimeMillis() + timeoutInSeconds * 1000;
		while (System.currentTimeMillis() < endTime) {
			try {
				String alertText = driver.switchTo().alert().getText();
				if (alertText.equals(expectedText)) {
					driver.switchTo().alert().accept();
					return;
				}
			} catch (NoAlertPresentException e) {

			}

		}
		throw new AssertionError("Alert text did not match expected text: " + expectedText);
	}

}
