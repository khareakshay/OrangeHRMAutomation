package com.MyTests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminUserManagement {

	WebDriver driver;

	public AdminUserManagement(WebDriver driver) {
		this.driver = driver;
	}

	public void getSideMenu() throws InterruptedException {
		// Side Menus
		List<WebElement> menus = driver
				.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li/a/span"));
		System.out.println("Total menus: " + menus.size());
		for (WebElement i : menus) {
			System.out.println(i.getText());
		}
		Thread.sleep(1000);	
	}

	public void clickAdmin() {
		// Click on admin Button
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")).click();
	}

	public void byUserName() throws InterruptedException {
		System.out.println("============ Search ByUserName() ================");
		// Search by username
		driver.findElement(By.xpath(
				"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input"))
				.sendKeys("Admin");

		// Click Search
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]"))
				.click();
		Thread.sleep(1000);

		WebElement noOfEmp = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div"));
		System.out.println("Total Number of Emp: " + noOfEmp.getText());

		// Page Refresh
		driver.navigate().refresh();
		System.out.println("Page refreshed");
		Thread.sleep(2000);
	}

	public void byUserRole() throws InterruptedException {
		System.out.println("============ Search ByUserRole() ================");
		// Search by User roll
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'][1]")).click();

		driver.findElement(By.xpath("//*[contains(text(),'Admin')][1]")).click();
		// click on Search button
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]"))
				.click();
		Thread.sleep(1000);

		WebElement noOfEmployee = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div"));
		System.out.println("Total Number of Emp: " + noOfEmployee.getText());

		// Page Refresh
		driver.navigate().refresh();
		System.out.println("Page refreshed");
		Thread.sleep(2000);
	}

	public void byUserStatus() throws InterruptedException {
		System.out.println("============ Search ByUserStatus() ================");
		// Search by User Status
		driver.findElement(By.xpath(
				"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i"))
				.click();

		driver.findElement(By.xpath("//*[contains(text(),'Enabled')][1]")).click();
		// click on Search button
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]"))
				.click();
		Thread.sleep(1000);

		WebElement noOfEmpstatus = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div"));
		System.out.println("Total Number Emp: " + noOfEmpstatus.getText());
	}
}
