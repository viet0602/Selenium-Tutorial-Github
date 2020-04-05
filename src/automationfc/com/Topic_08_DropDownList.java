package automationfc.com;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_DropDownList {

	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_HTMLDropDownList01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Mobile Testing");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

		select.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		select.selectByIndex(9);
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		int optionCount = select.getOptions().size();
		Assert.assertEquals(optionCount, 10);

		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());

		select.selectByVisibleText("Automation");
		Thread.sleep(3000);
		select.selectByVisibleText("Mobile");
		Thread.sleep(3000);
		select.selectByVisibleText("Desktop");
		Thread.sleep(3000);

		Assert.assertEquals(select.getAllSelectedOptions().size(), 3);
		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);

	}

	@Test
	public void TC_02_HTMLDropDownList02() throws InterruptedException {
		String email = "viettesting" + randomNumber() + "@gmail.com";
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[text()='Register']"));

		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Viet");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("1");
		Thread.sleep(3000);
		int optionCountDay = select.getOptions().size();
		Assert.assertEquals(optionCountDay, 32);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");
		Thread.sleep(3000);
		int optionCountMonth = select.getOptions().size();
		Assert.assertEquals(optionCountMonth, 13);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1980");
		Thread.sleep(3000);
		int optionCountYear = select.getOptions().size();
		Assert.assertEquals(optionCountYear, 112);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Sam sung");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random ran = new Random();
		return ran.nextInt(999999);
	}

}
