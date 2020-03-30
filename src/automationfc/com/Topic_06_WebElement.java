
package automationfc.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement {

	WebDriver driver;

	By email = By.id("mail");
	By age = By.id("under_18");
	By education = By.id("edu");
	By jobrole1 = By.id("job1");
	By jobrole2 = By.id("job2");
	By jobrole3 = By.id("job3");
	By interest = By.id("development");
	By interestdisable = By.id("check-disbaled");
	By slider1 = By.id("slider-1");
	By slider2 = By.id("slider-2");
	By password = By.id("password");
	By agedisable = By.id("radio-disabled");
	By biography = By.id("bio");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_isDisplayed() {
		// access page: https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Check elements displayed, If displayed, input data
		if (isElementDisplayed(email)) {
			driver.findElement(email).sendKeys("Automation testing");
		}
		if (isElementDisplayed(age)) {
			driver.findElement(age).click();
		}
		if (isElementDisplayed(education)) {
			driver.findElement(education).sendKeys("Automation testing");
		}

	}

	@Test
	public void TC_02_isEnabled() {
		// access page: https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Assert.assertTrue(isElementEnabled(email));
		Assert.assertTrue(isElementEnabled(age));
		Assert.assertTrue(isElementEnabled(education));
		Assert.assertTrue(isElementEnabled(jobrole1));
		Assert.assertTrue(isElementEnabled(jobrole2));
		Assert.assertTrue(isElementEnabled(interest));
		Assert.assertTrue(isElementEnabled(slider1));

		Assert.assertFalse(isElementEnabled(password));
		Assert.assertFalse(isElementEnabled(agedisable));
		Assert.assertFalse(isElementEnabled(biography));
		Assert.assertFalse(isElementEnabled(jobrole3));
		Assert.assertFalse(isElementEnabled(interestdisable));
		Assert.assertFalse(isElementEnabled(slider2));

	}

	@Test
	public void TC_03_isSelected() {
		// access page: https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(age).click();
		driver.findElement(interest).click();

		Assert.assertTrue(isElementSelected(age));
		Assert.assertTrue(isElementSelected(interest));
		
		driver.findElement(interest).click();

		Assert.assertFalse(isElementSelected(interest));
	}

	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			return true;
		} else
			return false;

	}

	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element with [ " + by + " ]is DISPLAY");
			return true;
		} else {
			System.out.println("Element with [" + by + " ]is UNDISPLAY");
			return false;
		}

	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		} else
			return false;

	}

}