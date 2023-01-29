package reusable;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class Reusable {


    public String read_data_from_XL(String columnName) {

        String field_value = null;

        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/pizzahut_testNG_datasheet.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("pizzahut");
            XSSFRow row = sheet.getRow(0);

            int col_num = -1;

            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(columnName))
                    col_num = i;
            }

            row = sheet.getRow(1);
            XSSFCell cell = row.getCell(col_num);

            field_value = cell.getStringCellValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return field_value;
    }


    public boolean isElementPresent(WebDriver driver , By by){
        boolean FLAG = false;
        try{
            driver.findElement(by).isDisplayed();
            FLAG = true;
        }
        catch(Exception e){}
        return FLAG;
    }


}
