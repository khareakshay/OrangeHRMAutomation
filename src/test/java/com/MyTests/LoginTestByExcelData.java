package com.MyTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LoginTestByExcelData {
	String fPath = "D:\\Eclips_Workspace\\LoginData.xlsx";
	File file;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	int rows, i, j, index = 1;
	static WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	int count = 1;
	
	@Test(dataProvider = "getLoginData")
	public void loginToOHROM(String un, String ps) {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(ps);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	@AfterMethod
	public void afterMethod() throws IOException, InterruptedException {
		System.out.println("After Method");
		String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String actUrl = driver.getCurrentUrl();
		Thread.sleep(3000);
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenShot, new File("Login_ScreentShot_" + count + ".jpeg"));
		System.out.println("Screenshot captured!!!");
		
		if (driver.getCurrentUrl().contains("dashboard")) {
			System.out.println("actUrl "+actUrl);
			Assert.assertEquals(actUrl, expUrl );
			System.out.println("Test case passed");
			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
			driver.findElement(By.linkText("Logout")).click();

		} else {
			Assert.assertFalse(driver.getCurrentUrl().contains("dashboard"));
			System.out.println("Test case failed");

		}
		index++;
		count++;
	}

	@DataProvider
	public Object[][] getLoginData() {
		rows = sheet.getPhysicalNumberOfRows();
		String[][] loginData = new String[rows - 1][2];

		for (i = 0; i < rows - 1; i++) {
			row = sheet.getRow(i + 1);
			for (j = 0; j < 2; j++) {
				cell = row.getCell(j);
				loginData[i][j] = cell.getStringCellValue();
			}
		}
		return loginData;
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		file = new File(fPath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport/orangehrmLoginReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		test = extent.createTest("OrangeHRM Login Test");
		
	}

	@AfterTest
	public void afterTest() throws IOException {
		System.out.println("After Test");
		wb.close();
		fis.close();
		driver.close();
		extent.flush();
	}

}