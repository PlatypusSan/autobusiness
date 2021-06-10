package com.test.autobusiness.services;

import com.test.autobusiness.entities.DataObject;
import com.test.autobusiness.repositories.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public long saveImportFile(MultipartFile file) {

        DataObject dataObject = new DataObject();
        try {
            dataObject = checkForSimilarFiles(file);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dataObject.getId();
    }

    private DataObject checkForSimilarFiles(MultipartFile file) throws NoSuchAlgorithmException, IOException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(file.getBytes());
        byte[] digest = md.digest();
        Optional<DataObject> dataObjectOptional = fileRepository.findFirstByDigest(digest);
        if (dataObjectOptional.isPresent()) {
            return dataObjectOptional.get();
        } else {
            return fileRepository.save(new DataObject(file.getName(), file.getBytes(), digest));
        }
    }
}
