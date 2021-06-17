package com.test.autobusiness.services.impl;

import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.services.ExportService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class XlsxExportServiceImpl implements ExportService {

    @Value("${export.car.columns}")
    private String[] columns;
    private Font headerFont;
    private CellStyle headerCellStyle;
    private Workbook workbook;

    public Resource getExportFile(List<CarResponse> carResponseList) throws IOException {

        workbook = new XSSFWorkbook();
        Sheet carsSheet = workbook.createSheet("Cars");

        configureStyle();

        Row headerRow = carsSheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (CarResponse carResponse : carResponseList) {
            Row row = carsSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(carResponse.getId());
            row.createCell(1).setCellValue(carResponse.getBrand());
            row.createCell(2).setCellValue(carResponse.getModel());
            row.createCell(3).setCellValue(carResponse.getGeneration());
            row.createCell(4).setCellValue(carResponse.getBody());
            row.createCell(5).setCellValue(carResponse.getDriveUnit());
            row.createCell(6).setCellValue(carResponse.getTransmission());
            row.createCell(7).setCellValue(carResponse.getEngineType());
            row.createCell(8).setCellValue(carResponse.getCurrency());
            row.createCell(9).setCellValue(carResponse.getEngineVolume());
            row.createCell(10).setCellValue(carResponse.getAge());
            row.createCell(11).setCellValue(carResponse.getMileAge());
            row.createCell(12).setCellValue(carResponse.getPrice());
        }

        for (int i = 0; i < columns.length; i++) {
            carsSheet.autoSizeColumn(i);
        }

        File file = new File("cars.xlsx");
        FileOutputStream fileOut = new FileOutputStream(file);
        workbook.write(fileOut);
        fileOut.close();
        return new UrlResource(Paths.get(file.toURI()).toUri());
    }

    private void configureStyle() {

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.GREY_80_PERCENT.getIndex());

        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setBorderTop(BorderStyle.THICK);
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(BorderStyle.THICK);
    }
}
