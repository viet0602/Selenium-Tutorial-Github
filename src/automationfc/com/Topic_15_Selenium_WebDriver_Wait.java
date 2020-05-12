package automationfc.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Selenium_WebDriver_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String rootFolderPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", rootFolderPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

	}

	public void TC_02_Static_Wait() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		Thread.sleep(6000);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());

	}
	public void TC_03_Impicit_Wait() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		// Waiting loading icon disappear after 5s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		// Hello world appear after loading icon disappear
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		
	}

	public void TC_04_ExplicitWait_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		// Waiting loading icon disappear after 5s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		// Hello world appear after loading icon disappear
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
	}

	public void TC_04_ExplicitWait_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		// Hello world appear after loading icon disappear
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
	}

	public void TC_05_AjaxLoading_ImplicitWait() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.findElement(By.xpath("//a[contains(text(),'27')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Wednesday, May 27, 2020']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Wednesday, May 27, 2020')]")).isDisplayed());

	}

	@Test
	public void TC_05_AjaxLoading_ExplicitWait() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.findElement(By.xpath("//a[contains(text(),'27')]")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected' and @title='Wednesday, May 27, 2020']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Wednesday, May 27, 2020']")).isDisplayed());
		//Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_RadCalendar1_Top']")).getText().trim(), "Wednesday, May 27, 2020");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Wednesday, May 27, 2020')]")).isDisplayed());

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
