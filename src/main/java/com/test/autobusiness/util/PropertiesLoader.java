package com.test.autobusiness.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties(String resourceFileName) {

        Properties configuration = new Properties();
        File file = new File(resourceFileName);
        try {
            InputStream inputStream = PropertiesLoader.class
                    .getClassLoader()
                    .getResourceAsStream(resourceFileName);
            configuration.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }
}
