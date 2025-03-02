package com.NetWorth.Transaction.Service;


import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionExtractor {

    public List<Map<String, Object>> extractDetails(String excel, String header1, String header2,String header3, String header4,String header5,int page, int size) throws IOException {
        List<Map<String, Object>> result = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(excel);
             Workbook workbook = WorkbookFactory.create(file)) {

            Sheet sheet = workbook.getSheetAt(0);

            int header1Index = -1;
            int header2Index = -1;
            int header3Index = -1;
            int header4Index = -1;
            int header5Index = -1;
            int headerRowIndex = -1;

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (Cell cell : row) {
                        if (cell.getCellType() == CellType.STRING) {
                            String cellValue = cell.getStringCellValue().trim();

                            // Check if this row contains both header1 and header2
                            if (cellValue.equalsIgnoreCase(header1)) {
                                header1Index = cell.getColumnIndex();
                            }
                            if (cellValue.equalsIgnoreCase(header2)) {
                                header2Index = cell.getColumnIndex();
                            }
                            if (cellValue.equalsIgnoreCase(header3)) {
                                header3Index = cell.getColumnIndex();
                            }
                            if (cellValue.equalsIgnoreCase(header4)) {
                                header4Index = cell.getColumnIndex();
                            }
                            if (cellValue.equalsIgnoreCase(header5)) {
                                header5Index = cell.getColumnIndex();
                            }
                        }
                    }

                    if (header1Index != -1 && header2Index != -1) {
                        headerRowIndex = i;
                        break;
                    }
                }
            }

            if (header1Index == -1 || header2Index == -1) {
                Map<String, Object> error = new HashMap<>();
                if (header1Index == -1) {
                    error.put("Error", "Column '" + header1 + "' not found.");
                }
                if (header2Index == -1) {
                    error.put("Error", "Column '" + header2 + "' not found.");
                }
                result.add(error);
                return result;
            }

            // Calculate pagination
            int startRow = headerRowIndex + 1 + (page - 1) * size;
            int endRow = Math.min(startRow + size, sheet.getLastRowNum() + 1);

            // Extract data from the specific columns under header1 and header2
            for (int i = startRow; i < endRow; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Map<String, Object> rowMap = new HashMap<>();

                    // Extract data from header1 column
                    Cell header1Cell = row.getCell(header1Index);
                    String header1Value = (header1Cell != null && header1Cell.getCellType() == CellType.STRING)
                            ? header1Cell.getStringCellValue()
                            : "N/A";  // Fallback value if the cell is null or not a string
                    if ("N/A".equals(header1Value)||header1.equals(header1Value)||header1Value.isEmpty()) {
                        continue;
                    }
                    rowMap.put(header1, header1Value);



                    // Extract data from header2 column (assuming numeric data for demonstration)
                    Cell header2Cell = row.getCell(header2Index);
                    String header2Value = (header2Cell != null && header2Cell.getCellType() == CellType.NUMERIC)
                            ? String.valueOf((int) header2Cell.getNumericCellValue())  // Convert to integer if numeric
                            : "N/A";  // Fallback value if the cell is not numeric
                    if ("N/A".equals(header2Value)||header2Value.isEmpty()) {
                        continue; // Skip the rest of the loop for this row and move to the next row
                    }
                    rowMap.put(header2, header2Value);

                    Cell header3Cell = row.getCell(header3Index);
                    String header3Value = (header3Cell != null && header1Cell.getCellType() == CellType.STRING)
                            ? header3Cell.getStringCellValue()
                            : "N/A";  // Fallback value if the cell is null or not a string
                    if ("N/A".equals(header3Value)||header3.equals(header3Value)||header3Value.isEmpty()) {
                        continue;
                    }
                    rowMap.put(header3, header3Value);

                    Cell header4Cell = row.getCell(header4Index);
                    String header4Value = (header4Cell != null && header4Cell.getCellType() == CellType.STRING)
                            ? header1Cell.getStringCellValue()
                            : "N/A";  // Fallback value if the cell is null or not a string
                    if ("N/A".equals(header4Value)||header4.equals(header4Value)||header4Value.isEmpty()) {
                        continue;
                    }
                    rowMap.put(header4, header4Value);

                    Cell header5Cell = row.getCell(header5Index);
                    String header5Value = (header5Cell != null && header5Cell.getCellType() == CellType.STRING)
                            ? header5Cell.getStringCellValue()
                            : "N/A";  // Fallback value if the cell is null or not a string
                    if ("N/A".equals(header5Value)||header5.equals(header5Value)||header5Value.isEmpty()) {
                        continue;
                    }
                    rowMap.put(header5, header5Value);


                    result.add(rowMap);  // Add the extracted data to the result list
                }
            }

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("Error", "Error reading the Excel file: " + e.getMessage());
            result.add(error);
        }

        return result;
    }

}