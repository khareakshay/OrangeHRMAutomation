package com.MyTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class OrangeHRMTest {
	static WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	int count = 1;
	AdminUserManagement adminUserManagement;

	@Test(priority = 1)
	public void getSideMenu() throws InterruptedException {
		adminUserManagement.getSideMenu();
		test.pass("Test passed - side menu");
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void clickAdmin() throws InterruptedException {
		adminUserManagement.clickAdmin();
		test.pass("Test passed - click admin option");
		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void byUserName() throws InterruptedException {
		adminUserManagement.byUserName();
		test.pass("Test passed - search byUserName");
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void byUserRole() throws InterruptedException {
		adminUserManagement.byUserRole();
		test.pass("Test passed - search byUserRole");
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void byUserStatus() throws InterruptedException {
		adminUserManagement.byUserStatus();
		test.pass("Test passed - search byUserStatus");
		Thread.sleep(2000);
	}

	@BeforeTest
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		// Login with credential
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();

		adminUserManagement = new AdminUserManagement(driver);

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport/orangehrmAdminReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		test = extent.createTest("OrangeHRM Admin Test");
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenShot, new File("Admin_ScreentShot_" + count + ".jpeg"));
		System.out.println("Screenshot captured!!!");
		count++;
	}

	@AfterTest
	public void afterTest() {
		if (driver.getCurrentUrl().contains("viewSystemUsers")) {
			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
			driver.findElement(By.linkText("Logout")).click();
		}

		driver.close();
		extent.flush();
		
	}

}
