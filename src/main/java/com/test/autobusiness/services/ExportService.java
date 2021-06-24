package com.test.autobusiness.services;

import com.test.autobusiness.dto.car.CarResponse;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ExportService {

    Resource getExportFile(List<CarResponse> carResponses) throws Exception;
}
