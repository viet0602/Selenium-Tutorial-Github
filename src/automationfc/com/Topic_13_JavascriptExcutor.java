
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

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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

	@Test
	public void TC_02_Class() {
		By addressTextBox = By.xpath("//textarea[@name='addr']");
		String email = "viettesting" + randomNumber() + "@gmail.com";
		String name = "Nguyen Van A";
		String gender = "male";
		String dob = "2020-04-01";
		String add = "Luc Nam";
		String city = "Bac giang";
		String state = "Viet nam";
		String pin = "989898";
		String phone = "0987131567";
		String password = "123123";

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
		sendkeyToElementByJS("//input[@name='name']", name);
		sendkeyToElementByJS("//input[@name='dob']", dob);
		sendkeyToElementByJS("//textarea[@name='addr']", add);
		//driver.findElement(addressTextBox).sendKeys(add);
		sendkeyToElementByJS("//input[@name='city']", city);
		sendkeyToElementByJS("//input[@name='state']", state);
		sendkeyToElementByJS("//input[@name='pinno']", pin);
		sendkeyToElementByJS("//input[@name='telephoneno']", phone);
		sendkeyToElementByJS("//input[@name='emailid']", email);
		sendkeyToElementByJS("//input[@name='password']", password);
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

	public void TC_03_Name() {

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
