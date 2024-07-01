package com.excelData.helper;

import com.excelData.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    //Check file type
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            return true;
        else
            return false;
    }

    //Convert excel to list of product
    public static List<Product> convertExcelToListOfProduct(InputStream is){
        List<Product> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);

            // Iterate over all sheets in the workbook
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                // Process each sheet
                Iterator<Row> iterator = sheet.iterator();
                int rowNumber = 0;
                while (iterator.hasNext()) {
                    Row row = iterator.next();

                    // Skip empty rows
                    if (rowNumber == 0 || isEmptyRow(row)) {
                        rowNumber++;
                        continue;
                    }

                    Iterator<Cell> cells = row.iterator();
                    int cid = 0;
                    Product p = new Product();
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        switch (cid) {
                            case 0:
                                p.setProductId((int)cell.getNumericCellValue());
                                break;
                            case 1:
                                p.setProductName(cell.getStringCellValue());
                                break;
                            case 2:
                                p.setProductDesc(cell.getStringCellValue());
                                break;
                            case 3:
                                p.setProductPrice(cell.getNumericCellValue());
                                break;
                            default:
                                break;
                        }
                        cid++;
                    }
                    list.add(p);
                    rowNumber++;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private static boolean isEmptyRow(Row row) {
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}

