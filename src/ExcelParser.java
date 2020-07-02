import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelParser {
    public static ArrayList<String> getInfo(String path){
        try (XSSFWorkbook excelBook = new XSSFWorkbook(path);){
            ArrayList<String> answer = new ArrayList<String>();
            XSSFSheet excelSheet = excelBook.getSheet("Лист1");

            Iterator<Row> rowIterator = excelSheet.rowIterator();
            rowIterator.next();
            while(rowIterator.hasNext()){
                XSSFRow tempRow = (XSSFRow) rowIterator.next();
                Iterator<Cell> cellIterator = tempRow.cellIterator();

                while (cellIterator.hasNext()){
                    XSSFCell tempCell = (XSSFCell) cellIterator.next();
                    answer.add(tempCell.toString().replaceAll("\\.0", ""));
                }
                System.out.println(" ");
            }
            return answer;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String >();
        }
    }
}