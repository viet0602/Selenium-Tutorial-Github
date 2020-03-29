package automationfc.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrower {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_VerifyUrl_GetcurrentUrl() {
		// Access page http://live.demoguru99.com
		driver.get("http://live.demoguru99.com");

		// Click My account link at footer
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();

		// Verify url of page
		String urlLogin = driver.getCurrentUrl();
		Assert.assertEquals(urlLogin, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Click create an account button
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
		
		// Verify url register page
		String urlCreateAcc = driver.getCurrentUrl();
		Assert.assertEquals(urlCreateAcc, "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_VerifyTitle_Gettitle() {
		// Access page http://live.demoguru99.com
		driver.get("http://live.demoguru99.com");

		// Click My account link at footer
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();

		// Verify title of page
		String titleLoginPage = driver.getTitle();
		Assert.assertEquals(titleLoginPage, "Customer Login");

		// Click create an account button
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
		String titleCreateAnAccoount = driver.getTitle();
		// Verify title
		Assert.assertEquals(titleCreateAnAccoount, "Create New Customer Account");
	}

	@Test
	public void TC_03_NavigateFunction_BackFoward() {
		// Access page http://live.demoguru99.com
		driver.get("http://live.demoguru99.com");

		// Click My account link at footer
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
		// Click create an account button
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
		// Verify url of register page
		String urlCreateAcc = driver.getCurrentUrl();
		Assert.assertEquals(urlCreateAcc, "http://live.demoguru99.com/index.php/customer/account/create/");
		//Back to login page
		driver.navigate().back();
		// Verify url of login page
		String urlLogin = driver.getCurrentUrl();
		Assert.assertEquals(urlLogin, "http://live.demoguru99.com/index.php/customer/account/login/");
		//forward to Register page
		driver.navigate().forward();
		//Verify title of Register page
		String titleCreateAnAccoount = driver.getTitle();
		Assert.assertEquals(titleCreateAnAccoount, "Create New Customer Account");
	}

	@Test
	public void TC_04_GetPageSourceCode_GetpageSource() {
		// Access page http://live.demoguru99.com
		driver.get("http://live.demoguru99.com");

		// Click My account link at footer
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();

		// Verify url of page
		String loginSource = driver.getPageSource();
		Assert.assertTrue(loginSource.contains("Login or Create an Account"));

		// Click create an account button
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
		
		// Verify url register page
		String registerSource = driver.getPageSource();
		Assert.assertTrue(registerSource.contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
