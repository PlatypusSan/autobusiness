package com.test.autobusiness;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.PostgreSQLContainer;

@Component
public class AutobusinessPostgresqlContainer extends PostgreSQLContainer<AutobusinessPostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:11.1";
    private static AutobusinessPostgresqlContainer container;


    private AutobusinessPostgresqlContainer() {

        super(IMAGE_VERSION);
    }

    @Bean
    public static AutobusinessPostgresqlContainer getInstance() {

        if (container == null) {
            container = new AutobusinessPostgresqlContainer();
        }
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

