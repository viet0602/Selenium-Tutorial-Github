
package automationfc.com;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic4_xpath_II {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		System.out.println("Driver ID = " + driver.toString());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_05_LoginWithValidEmailAndPW() {
		// Access to http://live.demoguru99.com/index.php/
		driver.get("http://live.demoguru99.com/index.php/");
		// Click My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// input email pw correctly
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");

		// login button
		driver.findElement(By.id("send2")).click();

		// Verify information
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, Automation Testing!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'box-content' ]/p[contains(text(),'Automation Testing')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'automation@gmail.com')]")).isDisplayed());
		// Click Account link in header to log out
		driver.findElement(By.cssSelector(".skip-account")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]")).click();
		
		//Verify homepage
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}

	@Test
	public void TC_06_CreateNewAccount() {
		String lastname = "Music";
		String firstname = "Samsung";
		String email = "samsungmusic"+randomNumber()+"@gmail.com";
		// access page
		driver.get("http://live.demoguru99.com/");

		// Click My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Click Create An Account button to go register acc page
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();

		// Input full infor: first name, last name, email address, Pw, confirm pw
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		// driver.findElement(By.id("middlename")).sendKeys("123434234@12312");
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.id("confirmation")).sendKeys("123123");
		// Click Register button
		driver.findElement(By.xpath("//form[@id='form-validate']//button[@title='Register']")).click();
		// Verify message when register successful: thank you for registering with Main
		// Website Store
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, "+ firstname +" "+ lastname +"!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'box-content' ]/p[contains(text(),'"+ firstname +" "+ lastname +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'" + email + "')]")).isDisplayed());

		// Log out system
		driver.findElement(By.cssSelector(".skip-account")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]")).click();
		// Check system after log out successful
		//Verify homepage
				Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}

	public int randomNumber() {
		Random ran = new Random();
		return ran.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
