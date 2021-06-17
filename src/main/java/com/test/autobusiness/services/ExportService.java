package com.test.autobusiness.services;

import com.test.autobusiness.dto.car.CarResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExportService {

    Resource getExportFile(List<CarResponse> carResponses) throws Exception;
}
