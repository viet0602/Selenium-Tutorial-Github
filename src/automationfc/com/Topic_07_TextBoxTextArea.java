package automationfc.com;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBoxTextArea {

	WebDriver driver;
	String email, userID, password, loginPageUrl;
	String name, dob, add, city, state, pin, phone, gender, customerID;
	String addEdit, cityEdit, stateEdit, pinEdit, phoneEdit, emailEdit;
	By nameTextBox = By.xpath("//input[@name='name']");
	By genderTextBox = By.xpath("//input[@name='gender']");
	By dateOfBirthTextBox = By.xpath("//input[@name='dob']");
	By addressTextBox = By.xpath("//textarea[@name='addr']");
	By cityTextBox = By.xpath("//input[@name='city']");
	By stateTextBox = By.xpath("//input[@name='state']");
	By pinTextBox = By.xpath("//input[@name='pinno']");
	By phoneTextBox = By.xpath("//input[@name='telephoneno']");
	By emailTextBox = By.xpath("//input[@name='emailid']");
	By passwordTextBox = By.xpath("//input[@name='password']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");

		email = "viettesting" + randomNumber() + "@gmail.com";
		name = "Nguyen Van A";
		gender = "male";
		dob = "2020-04-01";
		add = "Luc Nam";
		city = "Bac giang";
		state = "Viet nam";
		pin = "989898";
		phone = "0987131567";
		password = "123123";

		addEdit = "Luc Ngan";
		cityEdit = "Bac Giang";
		stateEdit = "USA";
		pinEdit = "090909";
		phoneEdit = "0987123234";

	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name = 'emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name = 'btnLogin']")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

//mngr253302 - ypUhube 
	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + userID + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	}

	@Test
	public void TC_03_CreateNewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextBox).sendKeys(name);
		driver.findElement(dateOfBirthTextBox).sendKeys(dob);
		driver.findElement(addressTextBox).sendKeys(add);
		driver.findElement(cityTextBox).sendKeys(city);
		driver.findElement(stateTextBox).sendKeys(state);
		driver.findElement(pinTextBox).sendKeys(pin);
		driver.findElement(phoneTextBox).sendKeys(phone);
		driver.findElement(emailTextBox).sendKeys(email);
		driver.findElement(passwordTextBox).sendKeys(password);

		driver.findElement(By.xpath("//input[@name='sub']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), add);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		//System.out.println(customerID);
	}

	@Test
	public void TC_04_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Assert.assertFalse(driver.findElement(nameTextBox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextBox).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirthTextBox).isEnabled());
		driver.findElement(addressTextBox).clear();
		driver.findElement(addressTextBox).sendKeys(addEdit);
		driver.findElement(cityTextBox).clear();
		driver.findElement(cityTextBox).sendKeys(cityEdit);

		driver.findElement(stateTextBox).clear();
		driver.findElement(stateTextBox).sendKeys(stateEdit);
		driver.findElement(pinTextBox).clear();
		driver.findElement(pinTextBox).sendKeys(pinEdit);
		driver.findElement(phoneTextBox).clear();
		driver.findElement(phoneTextBox).sendKeys(phoneEdit);
		driver.findElement(emailTextBox).clear();
		driver.findElement(emailTextBox).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		Assert.assertTrue((driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed()));
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
