package com.test.autobusiness.services;

import com.test.autobusiness.entities.filters.CarRepresentation;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ExportService {

    Resource getExportFile(CarRepresentation carRepresentation) throws IOException;
}
