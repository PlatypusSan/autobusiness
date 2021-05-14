package com.test.autobusiness;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.repositories.CarRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class JacksonMapper {

    private final CarRepository carRepository;

    public JacksonMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void writeFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Car car = carRepository.findById(5L).get();
        car.setDeclaration(null);
        car.setDetails(null);
        objectMapper.writeValue(new File("target/cars.json"), car);

        car = objectMapper.readValue(new URL("file:target/cars.json"), Car.class);
        JsonNode jsonNode = objectMapper.readTree(new URL("file:target/cars.json"));

    }
}
