package com.test.autobusiness;

import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.services.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PdfFileTests {

    @Autowired
    private CarService carService;

    @Autowired
    private ExportService exportService;

    @Test
    void documentCreation() throws Exception {

        CarRepresentation carRepresentation = CarRepresentation.builder()
                .page(0)
                .pageSize(3)
                .currency("USD")
                .build();

        carService.getExportFile(carRepresentation);
    }
}
