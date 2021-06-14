package com.test.autobusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AutobusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutobusinessApplication.class, args);
    }

}
