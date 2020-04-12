
package automationfc.com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_CustomDropDownList {

	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String text;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Jquery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		SelectItemInDropDownList("//span[@id='number-button']", " //ul[@id='number-menu']//div", "19");
		Assert.assertTrue(isDisplayItems("//span[@id='number-button']//span[@class='ui-selectmenu-text'and text()='19']"));
		// Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text'and text()='19']")).isDisplayed());

	}

	@Test
	public void TC_02_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		SelectItemInDropDownList("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-search-icon')]", "//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(getTextByJS("select[name='games'] option"), "Football");

	}

	@Test
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		SelectItemInDropDownList("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span", "Jenny Hess");
		Assert.assertTrue(isDisplayItems("//div[@class='ui fluid selection dropdown']/div[@class='text'and text()='Jenny Hess']"));

	}

	public void SelectItemInDropDownList(String parentXpath, String childXpath, String expectedItem) {
		// Click vào 1 thẻ cha để nó xổ ra tất cả các item con bên trong
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(10);
		// Lấy hết tất cả các item gán vào 1 cái list <WebElement> (findElements>

		List<WebElement> allitems = driver.findElements(By.xpath(childXpath));
		// Wait cho tất cả các item được load lên
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		// Dùng vòng lặp duyệt qua các item
		for (int i = 0; i < allitems.size(); i++) {
			// Get text của item ra
			String itemText = allitems.get(i).getText();
			// Kiểm tra xem có bằng item mình cần chọn ko
			if (itemText.equals(expectedItem)) {
				// Nếu bằng thì click vào item đó
				allitems.get(i).click();
				// Thoát khỏi vòng lặp
				break;
			}

		}

	}

	public boolean isDisplayItems(String locator) {
		if (driver.findElement(By.xpath(locator)).isDisplayed())
			return true;
		else
			return false;
	}

	public String getTextByJS(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
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
