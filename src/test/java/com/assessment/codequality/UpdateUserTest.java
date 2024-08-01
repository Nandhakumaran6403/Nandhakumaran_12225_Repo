package com.assessment.codequality;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class UpdateUserTest {

	private WebDriver driver;

	@BeforeEach
	void start() throws InterruptedException {
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS); // Implicit wait

		driver.get("http://localhost:3000/login");

		WebElement usernameInput = driver.findElement(By.name("name"));
		usernameInput.sendKeys("Nandhakumaran");

		WebElement passwordInput = driver.findElement(By.name("password"));
		passwordInput.sendKeys("123");

		WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		loginButton.submit();

		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.equals("http://localhost:3000/main")) {
			System.out.println("Positive login test passed for employee!");
		} else if (currentUrl.equals("http://localhost:3000/usermain")) {
			System.out.println("Positive login test passed for donator!");
		} else {
			System.out.println("Positive login test failed. Unexpected redirection.");
		}

		WebElement donatorDetailsButton = driver.findElement(By.xpath("//button[contains(text(),'Donator Details')]"));
		donatorDetailsButton.click();
	}

	@AfterEach
	void close() {
		driver.quit();
	}

	@Test
	void testUpdateUserValid() throws InterruptedException {

		WebElement donatorUpdateButton = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div/table/tbody/tr[2]/td[8]/a/button"));
		donatorUpdateButton.click();

		driver.findElement(By.name("name")).clear();
		WebElement nameField = driver.findElement(By.name("name"));
		nameField.sendKeys("Ponraj Marikannan");

		driver.findElement(By.name("emailid")).clear();
		WebElement emailField = driver.findElement(By.name("emailid"));
		emailField.sendKeys("vasi@example.com");

		driver.findElement(By.name("address")).clear();
		WebElement addressField = driver.findElement(By.name("address"));
		addressField.sendKeys("Mandelanagar");

		driver.findElement(By.name("phoneNumber")).clear();
		WebElement phoneField = driver.findElement(By.name("phoneNumber"));
		phoneField.sendKeys("0987654321");

		driver.findElement(By.name("donation")).clear();
		WebElement donationField = driver.findElement(By.name("donation"));
		donationField.sendKeys("100");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"edit2\"]/div/form/button"));
		submitButton.submit();

		waitForAlertAndCheckText("Data Updated Successfully", 10);

		assertEquals("http://localhost:3000/usermain", driver.getCurrentUrl());

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
