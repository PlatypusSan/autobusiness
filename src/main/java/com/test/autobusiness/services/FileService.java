package com.test.autobusiness.services;

import com.test.autobusiness.entities.DataObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Service
public interface FileService {

    DataObject getFile(long id) throws FileNotFoundException;

    long saveImportFile(MultipartFile file);
}
