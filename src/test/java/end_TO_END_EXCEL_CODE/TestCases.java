package end_TO_END_EXCEL_CODE;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestCases {
	
	public WebDriver driver;
	
	@Test(priority = 1)
	public void createNewExcelFile() throws Throwable {
		   String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\end_TO_END_EXCEL_CODE\\Data.xlsx";

	        Workbook workbook = new XSSFWorkbook();

	        FileOutputStream outputStream = new FileOutputStream(filePath);
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }
	
	@Test(priority = 2, dependsOnMethods = "createNewExcelFile")
	public void createNewSheetAndPassInputData() throws Throwable {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\end_TO_END_EXCEL_CODE\\Data.xlsx";
	    String sheetName = "Register";
	    String[] firstname = { "Selenium", "Selenium", "Selenium", "Selenium", "Selenium"};
	    String[] lastname = { "Panda", "Panda", "Panda", "Panda", "Panda" };
	    String[] email = { "seleniumpanda13@gmail.com", "seleniumpanda14@gmail.com", "seleniumpanda15@gmail.com", "seleniumpanda16@gmail.com", "seleniumpanda17@gmail.com" };
	    String[] telephone = {"9876543210", "2345678901", "3456789012", "9876543210", "9876543210"  };
	    String[] password = {"Selenium@123", "Selenium@123", "Selenium@123", "Selenium@123", "Selenium@123" };
	    String[] confirmpassword = {"Selenium@123", "Selenium@123", "Selenium@123", "Selenium@123", "Selenium@123" };

	    FileInputStream inputStream = new FileInputStream(filePath);
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet sheet = workbook.createSheet(sheetName);

	    Row headerRow = sheet.createRow(0);
	    headerRow.createCell(0).setCellValue("firstname");
	    headerRow.createCell(1).setCellValue("lastname");
	    headerRow.createCell(2).setCellValue("email");
	    headerRow.createCell(3).setCellValue("telephone");
	    headerRow.createCell(4).setCellValue("password");
	    headerRow.createCell(5).setCellValue("confirmpassword");

	    for (int i = 0; i < firstname.length; i++) {
	        Row row = sheet.createRow(i+1);
	        row.createCell(0).setCellValue(firstname[i]);
	        row.createCell(1).setCellValue(lastname[i]);
	        row.createCell(2).setCellValue(email[i]);
	        row.createCell(3).setCellValue(telephone[i]);
	        row.createCell(4).setCellValue(password[i]);
	        row.createCell(5).setCellValue(confirmpassword[i]);
	    }

	    FileOutputStream outputStream = new FileOutputStream(filePath);
	    workbook.write(outputStream);
	    workbook.close();
	    outputStream.close();
	}
	
	@Test(priority = 3, dependsOnMethods = {"createNewExcelFile", "createNewSheetAndPassInputData"}, dataProvider = "TNRegister", dataProviderClass = Excel_With_DataProvider.class)
	public void tnRegister(String firstname, String lastname, String email, String telephone, String password, String confirmpassword) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys(firstname);
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys(telephone);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.id("input-confirm")).sendKeys(confirmpassword);
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	}
	
	@Test(priority = 4, dependsOnMethods = {"createNewExcelFile", "createNewSheetAndPassInputData", "tnRegister"})
	public void createNewSheetToPassCreatedDataInPreviousTestCase() throws Throwable {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\end_TO_END_EXCEL_CODE\\Data.xlsx";
	    String sheetName = "Login";
	    String[] email = { "seleniumpanda13@gmail.com", "seleniumpanda14@gmail.com", "seleniumpanda15@gmail.com", "seleniumpanda16@gmail.com", "seleniumpanda17@gmail.com" };
	    String[] password = {"Selenium@123", "Selenium@123", "Selenium@123", "Selenium@123", "Selenium@123" };

	    FileInputStream inputStream = new FileInputStream(filePath);
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet sheet = workbook.createSheet(sheetName);

	    int rowNum = 0;
	    Row row = sheet.createRow(rowNum++);
	    Cell usernameCell = row.createCell(0);
	    usernameCell.setCellValue("username");
	    Cell passwordCell = row.createCell(1);
	    passwordCell.setCellValue("password");

	    for (int i = 0; i < email.length; i++) {
	        row = sheet.createRow(rowNum++);
	        usernameCell = row.createCell(0);
	        usernameCell.setCellValue(email[i]);
	        passwordCell = row.createCell(1);
	        passwordCell.setCellValue(password[i]);
	    }

	    FileOutputStream outputStream = new FileOutputStream(filePath);
	    workbook.write(outputStream);
	    workbook.close();
	    outputStream.close();
		
	}
	
	@Test(priority = 5,dependsOnMethods = {"createNewExcelFile", "createNewSheetAndPassInputData", "tnRegister", "createNewSheetToPassCreatedDataInPreviousTestCase"}, dataProvider = "TNLogin", dataProviderClass = Excel_With_DataProvider.class)
	public void tnLogin(String username, String password) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(username);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
	}
	

}
	


