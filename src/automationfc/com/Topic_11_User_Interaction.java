package automationfc.com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction {

	WebDriver driver;
	Actions action;
	WebElement element; // Khong khoi tao, chi co luc minh dung no

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01_Hover() {
		driver.get("http://www.myntra.com/");
		element = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Discover']"));
		action.moveToElement(element).perform();
		driver.findElement(By.xpath("//a[text()='American Eagle']")).click();
		Assert.assertTrue((driver.findElement(By.xpath("//h1[text()='American Eagle']")).isDisplayed()));
		Assert.assertTrue((driver.findElement(By.xpath("//ul[@class='breadcrumbs-list']//span[text()='American Eagle']")).isDisplayed()));
		turnOffNotification();
	}
	@Test
	public void TC_02_Click_And_Hold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(3)).release().perform();
		//release: nha chuot
		List<WebElement> allItemSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(allItemSelected.size(), 4);	
		
	}
	@Test
	public void TC_03_Click_And_Select_Element() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(allItems.get(0));
		action.click(allItems.get(2));
		action.click(allItems.get(5));
		action.click(allItems.get(6));
		action.click(allItems.get(7));
		action.keyUp(Keys.CONTROL).perform();
		List<WebElement> allItemSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(allItemSelected.size(), 5);	
	}
	@Test
	public void TC_04_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	}
	
	@Test
	public void TC_05_Right_Click_To_Element() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover')]")).isDisplayed());
		//driver.switchTo().alert().accept();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void turnOffNotification() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enable", false);
	//	driver = new FirefoxDriver(profile);
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
