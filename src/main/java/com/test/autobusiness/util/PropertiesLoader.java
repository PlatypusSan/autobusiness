package com.test.autobusiness.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties(String resourceFileName) {

        Properties configuration = new Properties();
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
