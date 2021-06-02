package com.test.autobusiness.containers;

import org.testcontainers.containers.PostgreSQLContainer;

public class AutobusinessPostgresqlContainer extends PostgreSQLContainer<AutobusinessPostgresqlContainer> {

    private static AutobusinessPostgresqlContainer container;
    private static final String IMAGE_VERSION = "postgres:11.1";
    private static final String DB_NAME = "autobusiness";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";

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

