package org.leagueoftests.app.jbhsel;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GithubTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"C:/Users/Marta Siejka/Downloads/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe"
		);
		driver = new PhantomJSDriver(caps);
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}

	@Test
	public void findChampionsClass() throws IOException {
		driver.get("https://github.com/Zyavaranah");
		WebElement e;
		e = driver.findElement(By.partialLinkText("TAU-LeagueOfTests"));
		assertEquals(e.getAttribute("href"), "https://github.com/Zyavaranah/TAU-LeagueOfTests");
		File screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("01GitProfile.png"));
		e.click();
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("02GitRepo.png"));
		e = driver.findElement(By.partialLinkText("league-of-tests"));
		assertEquals(e.getAttribute("title"), "league-of-tests");
		e.click();
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("03GitLeague-of-tests.png"));
		e = driver.findElement(By.partialLinkText("src"));
		assertEquals(e.getAttribute("title"), "src");
		e.click();
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("04GitSrc.png"));
		e = driver.findElement(By.partialLinkText("main/java/org/leagueoftests"));
		assertEquals(e.getAttribute("href"), "https://github.com/Zyavaranah/TAU-LeagueOfTests/tree/master/league-of-tests/src/main/java/org/leagueoftests");
		e.click();
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("05Gitorgleagueoftests.png"));
		e = driver.findElement(By.partialLinkText("app"));
		assertEquals(e.getAttribute("title"), "app");
		e.click();
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("06Gitapp.png"));
		e = driver.findElement(By.partialLinkText("Champions.java"));
		assertEquals(e.getAttribute("title"), "Champions.java");






	}
}