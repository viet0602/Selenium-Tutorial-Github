package automationfc.com;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Window_Tab {

	WebDriver driver;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// driver.get("");
	}

	public void TC_01_Popup() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(3);

	}
	@Test
	public void TC_04_switchPage() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentWindowID = driver.getWindowHandle();
		String parentTitle = driver.getTitle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Google");
		switchToWindowByTitle(parentTitle);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		String facebookTitle = driver.getTitle();
		Assert.assertEquals(facebookTitle, "Facebook - Đăng nhập hoặc đăng ký");
		switchToWindowByTitle(parentTitle);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		String tikiTitle = driver.getTitle();
		Assert.assertEquals(tikiTitle, "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		closeWindoWithoutParentWindow(parentWindowID);
		sleepInSecond(3);
		String temp = driver.getTitle();
		Assert.assertEquals(temp, parentTitle);

	}

	@Test
	public void TC_05_WindowTab_02() {
		driver.get("https://kyna.vn/");
		String parentWindowID = driver.getWindowHandle();
		String parentWindowTitle = driver.getTitle();
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='facebook']")));
		sleepInSecond(3);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");

		switchToWindowByTitle(parentWindowTitle);
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='youtube']")));
		sleepInSecond(3);
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");

		switchToWindowByTitle(parentWindowTitle);
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='zalo']")));
		sleepInSecond(3);
		switchToWindowByTitle("Kyna.vn");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");

		switchToWindowByTitle(parentWindowTitle);
		clickElementByJS(driver.findElement(By.xpath("//a[text()='Giới thiệu về công ty']")));
		sleepInSecond(3);
		switchToWindowByTitle("Kyna.vn | Không tìm thấy trang");
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/p/kyna/gioi-thieu");

		//switchToWindowByTitle(parentWindowTitle);
		//System.out.println(driver.getCurrentUrl());
		sleepInSecond(3);
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='android-app-icon']")));
		switchToWindowByTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
		Assert.assertEquals(driver.getCurrentUrl(), "https://play.google.com/store/apps/details?id=com.kyna.app");
		switchToWindowByTitle(parentWindowTitle);
        System.out.println(driver.getCurrentUrl());
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
		clickElementByJS(driver.findElement(By.xpath("//a[@title='Kyna.vn']")));
		sleepInSecond(3);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn/");
		driver.switchTo().defaultContent();

		//switchToWindowByTitle(parentWindowTitle);
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='kynabiz.vn']")));
		sleepInSecond(3);
		switchToWindowByTitle("Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
		Assert.assertEquals(driver.getTitle(), "Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");

		closeWindoWithoutParentWindow(parentWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
	}
	@Test
	public void TC_06_WindowTab06() {
		driver.get("http://live.demoguru99.com/index.php/");
		String parentWindowID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeWindoWithoutParentWindow(parentWindowID);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}

	@Test
	public void TC_04_Tagname() {

	}


	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
	}

	public void closeWindoWithoutParentWindow(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}

	public boolean isElementDisplay(String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

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
