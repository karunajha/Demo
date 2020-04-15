package com.guru.base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.guru.utility.Utilty;

public class Test_script {
	static WebDriver driver;
	private static String baseUrl;

	public static void setup() {
		System.setProperty("webdriver.gecko.driver", "F:\\geckodriver-v0.25.0-win64\\geckodriver.exe");

		driver = new FirefoxDriver();
		baseUrl = Utilty.BASE_URL;
		driver.manage().timeouts().implicitlyWait(Utilty.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl);

	}

	/*
	 * Test Script 02 ************** This method will perform following Test Steps
	 * 
	 * 1) Go to http://www.demo.guru99.com/V4/ 2) Enter valid UserId 3) Enter valid
	 * Password 4) Click Login 5) Verify the Page Title after login
	 */
	public static void main(String[] args) throws Exception {
		String[][] testData = Utilty.getDataFromExcel(Utilty.FILE_PATH, Utilty.SHEET_NAME, Utilty.TABLE_NAME);
		String username, password, actualTitle, actualBoxTitle;

		// Testing for all parameters stored in the Excel File
		for (int i = 0; i < testData.length; i++) {
			username = testData[i][0];
			password = testData[i][1];
			setup();

		}
		// Enter username
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).clear(); // Good practice to clear
																								// a field before use
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys(Utilty.USER_NAME);

		// Enter Password
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Utilty.PASSWORD);
		;

//click login
		driver.findElement(By.name("btnLogin")).click();
		actualTitle = driver.getTitle();
		if (actualTitle.contains(Utilty.EXPECT_TITLE)) {
			System.out.println("Test case:Passed");

		} else {
			System.out.println("Test case:Failed");
		}
		driver.close();

	}

}
