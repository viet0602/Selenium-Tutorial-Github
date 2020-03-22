package automationfc.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css{

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		System.out.println("Driver ID = " + driver.toString());
		
		driver.get("http://live.demoguru99.com/");	
	}

	@Test
	public void TC_01_LoginWithEmptyEmailandPw() {
	
		//driver.findElement(By.xpath("//div[@class='footer']//a[@title='My account']")).click();
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//email
		driver.findElement(By.id("email")).sendKeys("");
		//pw
		driver.findElement(By.id("pass")).sendKeys("");
		
		//login button
		driver.findElement(By.id("send2")).click();
		
		//Verify
		String emailErrorMsg= driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
		
		String passwordErrorMsg= driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
		sleepInSecond(3);
		
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//email
		driver.findElement(By.id("email")).sendKeys("123434234@12312");
		sleepInSecond(3);
		//login button
		driver.findElement(By.id("send2")).click();
		
		//Verify
		String emailErrorMsg= driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
		sleepInSecond(3);
	}

	@Test
	public void TC_03_LoginWithPasswordLessThan6Chars() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//email
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		sleepInSecond(3);
		//pw
		driver.findElement(By.id("pass")).sendKeys("123");
		sleepInSecond(3);
		//login button
		driver.findElement(By.id("send2")).click();
		
		//Verify
		
		String passwordErrorMsg= driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");
		sleepInSecond(3);
	}

	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//email
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		sleepInSecond(3);
		//pw
		driver.findElement(By.id("pass")).sendKeys("123123123");
		sleepInSecond(3);
		
		//login button
		driver.findElement(By.id("send2")).click();
		
		//Verify
		
		String passwordErrorMsg= driver.findElement(By.xpath("//ul[@class='messages']//span")).getText();
		Assert.assertEquals(passwordErrorMsg, "Invalid login or password.");
		sleepInSecond(3);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
