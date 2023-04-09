package testng_dataProvider_parallel;
//controlling parallel tests thread count invoked by DataProvider using data-provider-thread-count

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Control_ParallelTests_DataProvider {

	public WebDriver driver;

	@DataProvider(name = "TN", parallel = true)
	public Object[][] getData() {
		Object[][] data = {{"seleniumpanda@gmail.com", "Selenium@123" },
				           {"seleniumpanda1@gmail.com", "Selenium@123"},
				           {"seleniumpanda2@gmail.com", "Selenium@123"},
				           {"seleniumpanda3@gmail.com", "Selenium@123"},
				           {"seleniumpanda4@gmail.com", "Selenium@123"},
				           {"seleniumpanda5@gmail.com", "Selenium@123"},
				           {"seleniumpanda6@gmail.com", "Selenium@123"},
				           {"seleniumpanda7@gmail.com", "Selenium@123"},
				           {"seleniumpanda8@gmail.com", "Selenium@123"},
				           {"seleniumpanda9@gmail.com", "Selenium@123"},
				           {"seleniumpanda10@gmail.com", "Selenium@123"},
		                   {"seleniumpanda11@gmail.com", "Selenium@123"}};

		return data;
	}

	@Test(dataProvider = "TN")
	public void TNLogin(String username, String password) throws Throwable {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(username);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		driver.quit();

	}
}