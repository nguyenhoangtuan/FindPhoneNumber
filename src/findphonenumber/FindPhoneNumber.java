/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findphonenumber;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Tuan
 */
public class FindPhoneNumber {

    Set<String> listNumber;

    public static void main(String[] args) throws IOException {
//        FindPhoneNumber ff = new FindPhoneNumber();
//        ff.read();
//        ff.write();
        FileChooser fc = new FileChooser();
        fc.setVisible(true);
    }

    public void read(String inputFileName) throws IOException {
        File inputWorkbook = new File(inputFileName);
        Workbook w;

        listNumber = new TreeSet<String>();

        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);

            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);

                    String str = cell.getContents();
                    String[] parts = str.split(" ");
                    for (int k = 0; k < parts.length; k++) {
                        String regex = "[0-9]+";
                        int leng = parts[k].length();
                        
                        if (leng >= 9 && leng <= 14) {
                             String tmp =parts[k].replaceAll("[-+.^:,]","");
                            if (parts[k].matches(regex)) {
                                listNumber.add(parts[k]);
                            } else if ((parts[k].substring(0, leng - 1)).matches(regex)) {
                                listNumber.add((parts[k].substring(0, leng - 1)));
                            } else if (tmp.matches(regex)) {
                                 listNumber.add(tmp);
                            } else if ((tmp.substring(0, tmp.length() - 1)).matches(regex)) {
                                listNumber.add((tmp.substring(0, tmp.length() - 1)));
                            
                            }
                        }

                    }

                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }

//        for (String temp : listNumber) {
//            System.out.println(temp);
//        }
        System.out.println(listNumber.size());
    }

    public void write() {
        try {
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File("OutputNew.xls"));
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            int i = 0;
            for (String temp : listNumber) {
                Label label = new Label(0, i, temp);
                wsheet.addCell(label);
                i++;
            }
            wworkbook.write();
            wworkbook.close();
            
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Error");
        }
    }

}
