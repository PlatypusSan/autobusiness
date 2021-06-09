package com.test.autobusiness.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.repositories.DealershipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealershipService {

    private final DealershipRepository dealershipRepository;

    public CompletableFuture<List<Dealership>> saveDealerships(final MultipartFile file) throws Exception {

        List<Dealership> dealershipList = parseCsvFileToBean(file);

        return CompletableFuture.completedFuture(dealershipList);
    }

    private List<Dealership> parseCsvFile(MultipartFile file) throws Exception {

        List<Dealership> dealershipList = new ArrayList<>();

        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Dealership dealership = new Dealership();
                    dealership.setName(data[0]);
                    dealership.setCity(data[1]);
                    dealership.setProperty(data[2]);
                    dealershipList.add(dealership);
                }
                return dealershipList;
            }
        } catch (final IOException e) {
            log.error("Failed to parse CSV file {}", e.toString());
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

    private List<Dealership> parseCsvFileToBean(MultipartFile file) throws IOException {

        /*Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("name", "name");
        mapping.put("city", "city");
        mapping.put("time", "time");
        mapping.put("contacts", "contacts");*/

        List<Dealership> dealershipList = new ArrayList<>();
        /*HeaderColumnNameTranslateMappingStrategy<Dealership> ms = new HeaderColumnNameTranslateMappingStrategy<>();
        ms.setType(Dealership.class);
        ms.setColumnMapping(mapping);*/

        Reader reader = new InputStreamReader(file.getInputStream());
        CsvToBean<Dealership> cb = new CsvToBeanBuilder<Dealership>(reader)
                .withType(Dealership.class)
                //.withMappingStrategy(ms)
                .build();

        dealershipList.addAll(cb.parse());
        reader.close();
        return dealershipList;
    }


}
