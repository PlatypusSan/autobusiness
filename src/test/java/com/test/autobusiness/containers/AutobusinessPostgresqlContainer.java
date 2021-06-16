package com.test.autobusiness.containers;

import com.test.autobusiness.util.PropertiesLoader;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Properties;

public class AutobusinessPostgresqlContainer extends PostgreSQLContainer<AutobusinessPostgresqlContainer> {

    private static AutobusinessPostgresqlContainer container;
    private static final Properties PROPERTIES = PropertiesLoader
            .loadProperties("application-test.yaml");
    private static final String IMAGE_VERSION = PROPERTIES.getProperty("version");
    private static final String DB_NAME = PROPERTIES.getProperty("name");
    private static final String DB_USERNAME = PROPERTIES.getProperty("username");
    private static final String DB_PASSWORD = PROPERTIES.getProperty("password");

    private AutobusinessPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static AutobusinessPostgresqlContainer postgresqlContainer() {

        container = new AutobusinessPostgresqlContainer()
                .withUsername(DB_USERNAME)
                .withPassword(DB_PASSWORD)
                .withDatabaseName(DB_NAME);
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}

