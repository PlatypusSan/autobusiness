package com.test.autobusiness.services.impl;

import com.test.autobusiness.entities.DataObject;
import com.test.autobusiness.repositories.FileRepository;
import com.test.autobusiness.services.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public DataObject getFile(long id) throws FileNotFoundException {

        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

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