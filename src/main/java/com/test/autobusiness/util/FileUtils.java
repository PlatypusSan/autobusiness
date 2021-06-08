package com.test.autobusiness.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.test.autobusiness.entities.Dealership;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class FileUtils {


    private String fileName;
    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private File file;

    public FileUtils(String fileName) {
        this.fileName = fileName;
    }

    public Dealership readLine() {
        /*try {
            if (csvReader == null) initReader();
            String[] line = csvReader.readNext();
            if (line == null) return null;
            return new Dealership(line[0], LocalDate.parse(line[1], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } catch (Exception e) {
            log.error("Error while reading line in file: " + this.fileName);
            return null;
        }*/
        return new Dealership();
    }

    public void writeLine(Dealership line) {
        try {
            if (csvWriter == null) initWriter();
            String[] lineStr = new String[2];
            lineStr[0] = line.getName();
            lineStr[1] = line.getTime();
            csvWriter.writeNext(lineStr);
        } catch (Exception e) {
            log.error("Error while writing line in file: " + this.fileName);
        }
    }

    private void initReader() throws Exception {
        ClassLoader classLoader = this
                .getClass()
                .getClassLoader();
        if (file == null) file = new File(classLoader
                .getResource(fileName)
                .getFile());
        if (fileReader == null) fileReader = new FileReader(file);
        if (csvReader == null) csvReader = new CSVReader(fileReader);
    }

    private void initWriter() throws Exception {
        if (file == null) {
            file = new File(fileName);
            file.createNewFile();
        }
        if (fileWriter == null) fileWriter = new FileWriter(file, true);
        if (csvWriter == null) csvWriter = new CSVWriter(fileWriter);
    }

    public void closeWriter() {
        try {
            csvWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            log.error("Error while closing writer.");
        }
    }

    public void closeReader() {
        try {
            csvReader.close();
            fileReader.close();
        } catch (IOException e) {
            log.error("Error while closing reader.");
        }
    }

}
