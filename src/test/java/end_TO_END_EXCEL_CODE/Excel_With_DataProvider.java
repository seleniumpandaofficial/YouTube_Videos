package end_TO_END_EXCEL_CODE;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Excel_With_DataProvider {
	
	public static FileInputStream ip;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	
	@DataProvider(name = "TNRegister")
	public static Object[][] creatingTNRegisterData() throws Exception {
		Object[][] data = Excel_With_DataProvider.readDataFromExcelForTN("Register");
		return data;
	}
	
	@DataProvider(name = "TNLogin")
	public static Object[][] LoggingInCreatedTNData() throws Exception {
		Object[][] data = Excel_With_DataProvider.readDataFromExcelForTN("Login");
		return data;
	}
	
	
public static Object[][] readDataFromExcelForTN(String sheetName) throws Exception {
	ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\end_TO_END_EXCEL_CODE\\Data.xlsx");
    workbook = new XSSFWorkbook(ip);
	sheet = workbook.getSheet(sheetName);
	
	int rows = sheet.getLastRowNum();
	int cols = sheet.getRow(0).getLastCellNum();
	
	Object[][] data = new Object[rows][cols];
	
	for(int i=0 ; i<rows ; i++) {
		XSSFRow row = sheet.getRow(i+1);
		
		 for(int j=0 ; j<cols ; j++) {
			 XSSFCell cell = row.getCell(j);
			 
			 CellType cellType = cell.getCellType();
			 
			 switch(cellType) {
			 
			 case STRING:
				 data[i][j] = cell.getStringCellValue();
				 break;
				 
			 case NUMERIC:
				 data[i][j] = Integer.toString((int)cell.getNumericCellValue());
				 break;
				 
			 case BOOLEAN:
				 data[i][j] = cell.getBooleanCellValue();
				 break;
			 }
		 }
	}
	
	return data;

}
}
