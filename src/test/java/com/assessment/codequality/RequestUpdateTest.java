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
import org.openqa.selenium.support.ui.Select;

class RequestUpdateTest {

	private WebDriver driver;

	@BeforeEach
	void start() throws InterruptedException {
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS); // Implicit wait

		driver.get("http://localhost:3000/login");

		WebElement usernameInput = driver.findElement(By.name("name"));
		usernameInput.sendKeys("nandha");

		WebElement passwordInput = driver.findElement(By.name("password"));
		passwordInput.sendKeys("nandha123");

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

		WebElement donatorDetailsButton = driver.findElement(By.xpath("//button[contains(text(),'Handle Requests')]"));
		donatorDetailsButton.click();
	}

	@AfterEach
	void close() {
		driver.quit();
	}

	@Test
	void testUpdateRequestValid() throws InterruptedException {

		driver.get("http://localhost:3000/edit/9");

		WebElement selectElement = driver.findElement(By.name("status"));

		Select dropdown = new Select(selectElement);

		dropdown.selectByVisibleText("Approved");

		WebElement selectedOption = dropdown.getFirstSelectedOption();
		assertEquals("Approved", selectedOption.getText());

		WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"edit\"]/div/form/button"));
		submitButton.submit();

		waitForAlertAndCheckText("Data Updated Successfully", 10);

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
