package automationfc.com;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Upload_Files {

	WebDriver driver;
	WebElement element;
	JavascriptExecutor jsExecutor;
	private String rootFolderPath = System.getProperty("user.dir");
	String thailand1 = "1.jpg";
	String thailand2 = "2.jpg";
	String thailand3 = "3.jpg";
	String thailand1Path = rootFolderPath + "\\Upload_files\\" + thailand1;
	String thailand2Path = rootFolderPath + "\\Upload_files\\" + thailand2;
	String thailand3Path = rootFolderPath + "\\Upload_files\\" + thailand3;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", rootFolderPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_UploadFile_By_Sendkeys() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
		sleepInSecond(3);
		// Load file
		uploadFile.sendKeys(thailand1Path + "\n" + thailand2Path + "\n" + thailand3Path);
		sleepInSecond(3);

		// Verify 3 file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailand1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailand2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailand3 + "']")).isDisplayed());

		// CLick vào start button tại từng file
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(3);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thailand1 + "'and @href]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thailand2 + "'and @href]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thailand3 + "'and @href]")).isDisplayed());
	}

	@Test
	public void TC_04_Tagname() {
		driver.get("https://gofile.io/?t=uploadFiles");
		String parentWindowID = driver.getWindowHandle();
		// driver.findElement(By.xpath("//button[contains(.,'Click here')]")).click();
		WebElement uploadFile = driver.findElement(By.xpath("//input[@name='filesUploaded']"));
		sleepInSecond(3);
		// Load file
		uploadFile.sendKeys(thailand1Path + "\n" + thailand2Path + "\n" + thailand3Path);
		sleepInSecond(3);

		// Verify 3 file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='sorting_1' and text()='" + thailand1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='sorting_1' and text()='" + thailand2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='sorting_1' and text()='" + thailand3 + "']")).isDisplayed());
		// Upload
		// driver.findElement(By.xpath("//button[text()='Upload']")).click();
		clickToElementByJS("//button[text()='Upload']");
		sleepInSecond(15);

		// driver.findElement(By.xpath("//i[text()='Ok']")).click();
		clickToElementByJS("//button[contains(text(),'Ok')]");

		sleepInSecond(3);
		driver.findElement(By.xpath("//td[text()='Download link']//following-sibling::td/a")).click();
		sleepInSecond(3);

		// driver.findElement(By.xpath("//button[text()='Subscribe to a VPN']")).click();
		switchWindowByID(parentWindowID);
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + thailand1 + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + thailand1 + "')]/following-sibling::td//button[@data-original-title='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + thailand2 + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + thailand2 + "')]/following-sibling::td//button[@data-original-title='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + thailand3 + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + thailand3 + "')]/following-sibling::td//button[@data-original-title='Download']")).isDisplayed());

	}

	public void switchWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
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
