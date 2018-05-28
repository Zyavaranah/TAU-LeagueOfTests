package org.leagueoftests.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class SomeSiteTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary("/usr/local/bin/google-chrome-stable");
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-gpu");

		final DesiredCapabilities dc = new DesiredCapabilities();
		dc.setJavascriptEnabled(true);
		dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		driver = new ChromeDriver(dc);
		baseUrl = "http://automationpractice.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void failTest() throws Exception {
		driver.get(baseUrl + "/index.php");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"email_create\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys("jestemsmutnymniemailem");
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]")).click();
		Thread.sleep(5000);
		assertEquals(true, driver.findElement(By.xpath("//*[@id=\"create_account_error\"]")).isDisplayed());
	}

	@Test
	public void registerTest() throws Exception {
		driver.get(baseUrl + "/index.php");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"email_create\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"email_create\"]"))
				.sendKeys("jestemmailem@zyavka" + new Random().nextInt(777777) + ".pl");
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]")).click();
		Thread.sleep(500);
		assertEquals(false, driver.findElement(By.xpath("//*[@id=\"create_account_error\"]")).isDisplayed());
		driver.findElement(By.xpath(("//*[@id=\"id_gender2\"]"))).click();
		assertEquals(true, driver.findElement(By.xpath(("//*[@id=\"id_gender2\"]"))).isSelected());
		driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys("mamimie");
		assertNotEquals(0,
				driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).getAttribute("value").length());
		driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys("inazwisko");
		assertNotEquals(0,
				driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).getAttribute("value").length());
		assertEquals(true, driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("value")
				.contains(Character.toString('@')));
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("maslo");
		assertNotEquals(0, driver.findElement(By.xpath("//*[@id=\"passwd\"]")).getAttribute("value").length());
		driver.findElement(By.xpath("//*[@id=\"firstname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("mamimie");
		assertNotEquals(0, driver.findElement(By.xpath("//*[@id=\"firstname\"]")).getAttribute("value").length());
		driver.findElement(By.xpath("//*[@id=\"lastname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("inazwisko");
		assertNotEquals(0, driver.findElement(By.xpath("//*[@id=\"lastname\"]")).getAttribute("value").length());
		driver.findElement(By.xpath("//*[@id=\"address1\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys("niejestembezdomna 1");
		assertNotEquals(0, driver.findElement(By.xpath("//*[@id=\"address1\"]")).getAttribute("value"));
		driver.findElement(By.xpath("//*[@id=\"city\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("samjesteswiesniak");
		assertNotEquals(0, driver.findElement(By.xpath("//*[@id=\"city\"]")).getAttribute("value"));
		driver.findElement(By.xpath("//*[@id=\"id_state\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"id_state\"]/option[17]")).click();
		assertNotEquals(null, driver.findElement(By.xpath("//*[@id=\"id_state\"]/option[17]")).getAttribute("value"));
		driver.findElement(By.xpath("//*[@id=\"postcode\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"postcode\"]")).sendKeys("69696");
		assertEquals(5, driver.findElement(By.xpath("//*[@id=\"postcode\"]")).getAttribute("value").length());
		driver.findElement(By.xpath("//*[@id=\"id_country\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"id_country\"]/option[2]")).click();
		assertNotEquals(null, driver.findElement(By.xpath("//*[@id=\"id_country\"]/option[2]")).getAttribute("value"));
		driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).sendKeys("696969696");
		assertNotEquals(0, driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).getAttribute("value").length());
		driver.findElement(By.xpath("//*[@id=\"alias\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"alias\"]")).sendKeys("domekzkartonow");
		assertNotEquals(0, driver.findElement(By.xpath("//*[@id=\"alias\"]")).getAttribute("value"));
		driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();
		Thread.sleep(500);
		assertEquals(true,
				driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span")).isDisplayed());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
