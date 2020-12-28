package sample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Demo {

	String spreadsheetId = "1d6wXLqS8AgtBFP8aXiCRPRLU1IjSH29pgGPjfaa4iBg";
	@Test
	public void register() {
		try {
			
			String url = System.getProperty("AppUrlValue");
			System.out.println(System.getProperty("Credentials"));
			
			ReadWriteGoogleSheet sample = new ReadWriteGoogleSheet();
			String str = sample.getData(spreadsheetId, "Username");
			System.out.println(str);
			
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/BrowserDriversEXE/chromedriver");
			WebDriver driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.findElement(By.id("firstname")).sendKeys("Arunava");
			driver.findElement(By.id("lastname")).sendKeys("Chatterjee");

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			driver.findElement(By.id("userName")).sendKeys(dtf.format(now));
			Thread.sleep(5000);
			driver.findElement(By.id("password")).sendKeys("Pasword1$");

			
			sample.updateData(spreadsheetId, str, dtf.format(now));
			
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
