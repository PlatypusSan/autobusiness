package com.test.autobusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class PostgresTestProfileJPAConfig {

    @Value("${test.containers.postgres.driver-class-name}")
    private String DRIVER_CLASS_NAME;

    @Value("${test.containers.postgres.url}")
    private String DB_URL;

    @Value("${test.containers.postgres.username}")
    private String DB_USERNAME;

    @Value("${test.containers.postgres.password}")
    private String DB_PASSWORD;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }
}
