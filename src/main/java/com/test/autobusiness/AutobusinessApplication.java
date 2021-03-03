package com.test.autobusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
public class AutobusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutobusinessApplication.class, args);
	}

}
