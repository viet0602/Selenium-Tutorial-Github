package automationfc.com;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_Selenium_WebDriver_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String rootFolderPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", rootFolderPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;

	}

	public void TC_02_Static_Wait() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		Thread.sleep(6000);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());

	}

	@Test
	public void TC_03_Impicit_Wait() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
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

	public void TC_05_ExplicitWait_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		// Hello world appear after loading icon disappear
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
	}

	public void TC_06_AjaxLoading_Implicit_Wait() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.findElement(By.xpath("//a[contains(text(),'27')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Wednesday, May 27, 2020']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Wednesday, May 27, 2020')]")).isDisplayed());

	}

	public void TC_06_AjaxLoading_ExplicitWait() throws InterruptedException {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		scrollToElement("//div[@id='example']");
		driver.findElement(By.xpath("//a[contains(text(),'27')]")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		Thread.sleep(1000); // Giữa 2 step này để 1 đoạn sleep chênh lệch thì chạy ổn định chị nhé
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected' and @title='Wednesday, May 27, 2020']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Wednesday, May 27, 2020']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim(), "Wednesday, May 27, 2020");
	}

	public void TC_07_Fluent_Wait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countdownt = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='javascript_countdown_time']")));
		fluentElement = new FluentWait<WebElement>(countdownt);
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
				// Tần số mỗi giây check 1 lần
				.pollingEvery(1, TimeUnit.SECONDS)
				// Nếu gặp exception là find không thấy Element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
				// Kiểm tra điều kiện
				.until(new Function<WebElement, Boolean>() {

					public Boolean apply(WebElement element) {
						// Kiểm tra điều kiện countdount = 00
						Boolean flag = element.getText().endsWith("02");
						System.out.println("Time=" + element.getText());
						return flag;
					}

				});
	}

	@Test
	public void TC_08_Fluent_Wait_Only() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		WebElement element = driver.findElement(By.xpath("//div[@id='finish']//h4"));
		fluentElement = new FluentWait<WebElement>(element);
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
				// Tần số mỗi giây check 1 lần
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				// Nếu gặp exception là find không thấy Element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
				// Kiểm tra điều kiện
				.until(new Function<WebElement, String>() {

					public String apply(WebElement element) {
						// Kiểm tra điều kiện countdount = 00
						String flag = element.getText();
						return flag;
					}

				});
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath(locator)));
	}

}
