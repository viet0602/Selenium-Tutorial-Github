package automationfc.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {

	WebDriver driver;
	@BeforeClass
	public void beforeClass(){
		driver = new FirefoxDriver();
		System.out.println("Driver ID = "+ driver.toString());
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
	}
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("firstname")).sendKeys("Viet");
		sleepInSecond(3);
	}
	@Test
	public void TC_02_Class() {
		//input id="password" class="input-text required-entry validate-password" type="password" title="Password" name="password
		driver.findElement(By.className("validate-password")).sendKeys("Viet0602");
		sleepInSecond(3);
	}
	@Test
	public void TC_03_Name() {
	//input id="middlename" class="input-text" type="text" title="Middle Name/Initial" value="" name="middlename"	
	driver.findElement(By.name("middlename")).sendKeys("Thi");
	sleepInSecond(3);
	}
	@Test
	public void TC_04_Tagname() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		int number = driver.findElements(By.tagName("input")).size();
		System.out.println("Input"+number);
		sleepInSecond(3);
	}
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		sleepInSecond(3);
	}
	@Test
	public void TC_06_Partial_LinkText() {
		driver.findElement(By.partialLinkText("POLICY")).click();
		sleepInSecond(3);
	}
	@Test
	public void TC_07_Css() {

	}
	@Test
	public void TC_08_Xpath() {

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
