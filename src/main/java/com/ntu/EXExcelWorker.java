package com.ntu;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class EXExcelWorker {


    private static XSSFSheet sheet;
    private static List<String> rowNames;
    private static StringBuilder stringBuilder = new StringBuilder();
    private String fileName;

    public EXExcelWorker(String fileName){
        this.fileName = fileName;
    }

    public List<String> chooseGroupToDisplay(String kurs) throws IOException {
        Integer kursi = Integer.valueOf(kurs);
        Integer actKurs = 0;
        stringBuilder = new StringBuilder();
        if (kursi>=1 && kursi<=5) {

            switch (kurs) {
                case "1":
                    actKurs = 0;
                    break;
                case "2":
                    actKurs = 1;
                    break;
                case "3":
                    actKurs = 2;
                    break;
                case "4":
                    actKurs = 0;
                    break;
                case "5":
                    actKurs = 1;
                    break;
            }

            File myFile = new File(fileName);
            FileInputStream fis = new FileInputStream(myFile);
            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            // Return first sheet from the XLSX workbook
            sheet = myWorkBook.getSheetAt(actKurs);


            rowNames = getGropNames(sheet);

            if (rowNames!=null){
                return rowNames.stream()
                        .map(x-> x.replace('-','_'))
                        .map(String::trim)
                        .collect(Collectors.toList());
            }

        }   else return Collections.singletonList("Error while getting curse, sorry go to Menu");
//        }   else return Collections.singletonList("Виберіть курс 3-5");

        return Collections.singletonList(stringBuilder.toString());
    }

    public List<String> getGropNames(XSSFSheet sheet){
        int startRow = 4;
        if (fileName.contains("1-2")){
            startRow = 3;
        }
        List<String> response = new ArrayList<String>();
        XSSFRow row = sheet.getRow(startRow);
        Iterator<Cell> iterator = row.iterator();
        while (iterator.hasNext()){
            Cell cell = iterator.next();
            if (!cell.getStringCellValue().isEmpty()){
                response.add(cell.getStringCellValue());
            }
        }
        return response;
    }


    public void getRowValues() throws IOException {
        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

        }


    }

    public String getRowsByGroupName(String group) {
        group = group.replaceAll("І", "I"); //replase UA I on ENGLISH I;
        rowNames = rowNames.stream().map(x->x.replaceAll("І", "I"))
                .map(String::trim).collect(Collectors.toList());
        int colIndex = rowNames.indexOf(group);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == colIndex+1 || cell.getColumnIndex() == 0) {


                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            if (!cell.getStringCellValue().isEmpty())
                                stringBuilder.append(cell.getStringCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                                stringBuilder.append(cell.getNumericCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                                stringBuilder.append(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                    }

                }

            }
                stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
