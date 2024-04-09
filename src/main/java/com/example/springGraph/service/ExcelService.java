package com.example.springGraph.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Service
public class ExcelService {

    public GridCoordinates getGridCoordinates(MultipartFile file, String level1, String level2, String level3) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Cell cell1 = currentRow.getCell(0);
                Cell cell2 = currentRow.getCell(1);
                Cell cell3 = currentRow.getCell(2);

                String cellValue1 = getCellValue(cell1);
                String cellValue2 = getCellValue(cell2);
                String cellValue3 = getCellValue(cell3);

                // 입력 값과 엑셀의 값이 일치하는지 검사합니다.
                if (level1.equals(cellValue1) &&
                        (level2 == null || level2.equals(cellValue2)) &&
                        (level3 == null || level3.equals(cellValue3))) {
                    // 격자 X와 Y 값을 가져옵니다.
                    int gridX = (int) currentRow.getCell(3).getNumericCellValue();
                    int gridY = (int) currentRow.getCell(4).getNumericCellValue();
                    return new GridCoordinates(gridX, gridY);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 적절한 예외 처리를 수행합니다.
        }
        return null; // 조건에 맞는 데이터가 없는 경우
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    public class GridCoordinates {
        private int x;
        private int y;

        public GridCoordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
