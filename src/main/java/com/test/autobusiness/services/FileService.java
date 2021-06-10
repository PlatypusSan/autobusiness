package com.test.autobusiness.services;

import com.test.autobusiness.entities.DataObject;
import com.test.autobusiness.repositories.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public void saveImportFile(MultipartFile file) {

        DataObject dataObject = new DataObject();
        try {
            dataObject.setFile(file.getBytes());
            dataObject.setName(file.getName());
            fileRepository.save(dataObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
