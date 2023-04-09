package reading_data_from_Excel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class RediffAndTNoginTest {

	public WebDriver driver;

	@Test(priority = 0, dataProvider = "RediffDataExcelSheet", dataProviderClass = ExcelCode.class)
	public void rediffLoginTest(String username, String password) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.findElement(By.id("login1")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("signinbtn")).click();

	}

	@Test(priority = 1, dataProvider = "TutorialsNinjaDataExcelSheet", dataProviderClass = ExcelCode.class)
	public void tutorialsNinjaLoginTest(String username, String password) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(username);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
