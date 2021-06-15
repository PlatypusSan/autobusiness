package com.test.autobusiness.services.impl;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.dto.car.CarResponse;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.services.ExportService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class ExportServiceImpl implements ExportService {

    private final CarService carService;

    private static final String[] columns = new String[]{"Id", "Brand", "Model", "Generation", "Body",
            "Drive Unit", "Transmission", "Engine Type", "Currency", "Engine Volume", "Age", "Mileage", "Price"};


    public Resource getExportFile(CarRepresentation carRepresentation) throws IOException {

        List<Car> filteredCars = carService.getFilteredCars(carRepresentation);
        List<CarResponse> carResponseList = carService.pickCurrency(carRepresentation, filteredCars);

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Cars");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.GREY_80_PERCENT.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setBorderTop(BorderStyle.THICK);
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(BorderStyle.THICK);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;

        for (CarResponse carResponse : carResponseList) {
            Row row = sheet.createRow(rowNum++);
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
            sheet.autoSizeColumn(i);
        }

        try {

            File file = new File("car.xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();

            return new UrlResource(Paths.get(file.toURI()).toUri());
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
