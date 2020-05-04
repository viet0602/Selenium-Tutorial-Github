
package automationfc.com;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JavascriptExcutor {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	String email, password;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email= "viettesting" + randomNumber() + "@gmail.com";
	    password = "123123";
	}

	public void TC_01_ID() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		sleepInSecond(3);
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");

		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");

		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		highlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/button");

		Assert.assertTrue(isTextInInnerHTML("Samsung Galaxy was added to your shopping cart."));

		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		String liveGuruTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(liveGuruTitle, "Customer Service");

		highlightElement("//input[@name='email']");
		scrollToElement("//input[@name='email']");

		Assert.assertTrue(isTextInInnerHTML("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		/*
		 * navigateToUrlByJS("http://demo.guru99.com/v4/"); String liveDemoGuruDomain = (String) executeForBrowser("return document.domain;"); Assert.assertEquals(liveDemoGuruDomain, "demo.guru99.com");
		 */

	}


	public void TC_02_Class() {

		String name = "Nguyen Van A";
		String gender = "male";
		String dob = "2020-04-01";
		String add = "Luc Nam";
		String city = "Bac giang";
		String state = "Viet nam";
		String pin = "989898";
		String phone = "0987131567";
		

		By nameTextBox = By.xpath("//input[@name='name']");
		By dateOfBirthTextBox = By.xpath("//input[@name='dob']");
		By addressTextBox = By.xpath("//textarea[@name='addr']");
		By cityTextBox = By.xpath("//input[@name='city']");
		By stateTextBox = By.xpath("//input[@name='state']");
		By pinTextBox = By.xpath("//input[@name='pinno']");
		By phoneTextBox = By.xpath("//input[@name='telephoneno']");
		By emailTextBox = By.xpath("//input[@name='emailid']");
		By passwordTextBox = By.xpath("//input[@name='password']");

		navigateToUrlByJS("http://demo.guru99.com/v4");
		clickToElementByJS("//a[text()='here']");
		sendkeyToElementByJS("//input[@name = 'emailid']", email);
		clickToElementByJS("//input[@name = 'btnLogin']");

		String userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		String password1 = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		navigateToUrlByJS("http://demo.guru99.com/v4");
		sendkeyToElementByJS("//input[@name='uid']", userID);
		sendkeyToElementByJS("//input[@name='password']", password1);
		clickToElementByJS("//input[@name='btnLogin']");
		sleepInSecond(3);

		clickToElementByJS("//a[text()='New Customer']");
		driver.findElement(nameTextBox).sendKeys(name);
		driver.findElement(dateOfBirthTextBox).sendKeys(dob);
		driver.findElement(addressTextBox).sendKeys(add);
		driver.findElement(cityTextBox).sendKeys(city);
		driver.findElement(stateTextBox).sendKeys(state);
		driver.findElement(pinTextBox).sendKeys(pin);
		driver.findElement(phoneTextBox).sendKeys(phone);
		driver.findElement(emailTextBox).sendKeys(email);
		driver.findElement(passwordTextBox).sendKeys(password);
		clickToElementByJS("//input[@name='sub']");

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

	}
	@Test
	public void TC_03_Name() {
		String firstname, lastname, confirmpw;
		firstname="viet";
		lastname="testing";
		confirmpw="123123";
		
		navigateToUrlByJS("http://live.demoguru99.com/");
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		clickToElementByJS("//div[@class='col-1 new-users']//a[@title='Create an Account']");
		
		sendkeyToElementByJS("//input[@id='firstname']", firstname);
		sendkeyToElementByJS("//input[@id='lastname']", lastname);
		sendkeyToElementByJS("//input[@id='email_address']", email);
		sendkeyToElementByJS("//input[@id='password']", password);
		sendkeyToElementByJS("//input[@id='confirmation']", confirmpw);
		clickToElementByJS("//span[text()='Register']");
		//isTextInInnerHTML("Thank you for registering with Main Website Store.");
	//	Assert.assertTrue(isTextInInnerHTML("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		Assert.assertTrue(isTextInInnerHTML("Thank you for registering with Main Website Store."));
		sleepInSecond(3);
		clickToElementByJS("//a[text()='Log Out']");
		
		// Cho nay chi phai cho sleep 5-10s no moi pass duoc
		// Vi sau khi click xong no sẽ vào check ngay
		// Mà check ngay thì ko có
		sleepInSecond(3);
		Assert.assertTrue(isTextInInnerHTML("YOU ARE NOW LOGGED OUT"));
		//Assert.assertTrue(isTextInInnerHTML("You are now logged out"));
		
	}

	public void TC_04_Tagname() {

	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Browser
	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean isTextInInnerHTML(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public int randomNumber() {
		Random ran = new Random();
		return ran.nextInt(999999);
	}

}
