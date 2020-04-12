package automationfc.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox_Alert {

	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExcutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_ButtonJS() {
		driver.get("http://live.demoguru99.com/");

		clickElementByJS(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")));

		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "http://live.demoguru99.com/index.php/customer/account/login/");

		clickElementByJS(driver.findElement(By.xpath("//div[@class='col-1 new-users']//span[text()='Create an Account']")));
		currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "http://live.demoguru99.com/index.php/customer/account/create/");

	}
	@Test
	public void TC_02_DefaultCheckBoxOrRadioButton() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		By checkbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		driver.findElement(checkbox).click();
		Assert.assertTrue(driver.findElement(checkbox).isSelected());
		driver.findElement(checkbox).click();
		Assert.assertFalse(driver.findElement(checkbox).isSelected());
		Thread.sleep(3000);
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		By radio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		driver.findElement(radio).click();
		if (!(driver.findElement(radio).isSelected())) {
			driver.findElement(radio).click();
		}
	}
	@Test
	public void TC_03_CustomCheckBoxOrRadioButton() throws InterruptedException {
		driver.get("https://material.angular.io/components/radio/examples");

		By summerRadio = By.xpath("//input[@value='Summer']");
		// driver.findElement(summerRadio).click();
		clickElementByJS(driver.findElement(summerRadio));
		if (!(driver.findElement(summerRadio).isSelected())) {
			clickElementByJS(driver.findElement(summerRadio));
		}
		Assert.assertTrue(driver.findElement(summerRadio).isSelected());
		Thread.sleep(3000);

		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkBox1 = By.xpath("//span[contains(text(),'Checked')]//preceding-sibling::div/input");
		clickElementByJS(driver.findElement(checkBox1));
		By checkBox2 = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::div/input");
		clickElementByJS(driver.findElement(checkBox2));
		Assert.assertTrue(driver.findElement(checkBox1).isSelected());
		Assert.assertTrue(driver.findElement(checkBox2).isSelected());
		clickElementByJS(driver.findElement(checkBox1));
		clickElementByJS(driver.findElement(checkBox2));
		Assert.assertFalse(driver.findElement(checkBox1).isSelected());
		Assert.assertFalse(driver.findElement(checkBox2).isSelected());
	}
	@Test
	public void TC_04_AcceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert=driver.switchTo().alert();
		String message=alert.getText();
		Assert.assertEquals(message, "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
	}
	@Test
	public void TC_05_ConfirmAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Thread.sleep(3000);
		alert=driver.switchTo().alert();
		String message=alert.getText();
		Assert.assertEquals(message, "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}
	@Test
	public void TC_06_PromptAlert() throws InterruptedException {
		String text = "This is Testing";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Thread.sleep(3000);
		alert=driver.switchTo().alert();
		String message=alert.getText();
		Assert.assertEquals(message, "I am a JS prompt");
		alert.sendKeys(text);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: "+text);
	}
	@Test
	public void TC_07_AuthenAlert() throws InterruptedException {
		String username = "admin";
		String password = "admin";
		driver.get("http://"+ username + ":"+ password+ "@"+ "the-internet.herokuapp.com/basic_auth");

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
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
