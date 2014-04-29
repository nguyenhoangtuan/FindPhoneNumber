/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findphonenumber;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Tuan
 */
public class FindPhoneNumber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try {
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File("output.xls"));
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            Label label = new Label(0, 2, "A label record");
            wsheet.addCell(label);
            Number number = new Number(3, 4, 3.1459);
            wsheet.addCell(number);
            wworkbook.write();
            wworkbook.close();

            Workbook workbook = Workbook.getWorkbook(new File("output.xls"));
            Sheet sheet = workbook.getSheet(0);
            Cell cell1 = sheet.getCell(0, 2);
            System.out.println(cell1.getContents());
            Cell cell2 = sheet.getCell(3, 4);
            System.out.println(cell2.getContents());
            workbook.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
