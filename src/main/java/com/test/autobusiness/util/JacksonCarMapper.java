package com.test.autobusiness.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.repositories.CarRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class JacksonCarMapper {

    private final CarRepository carRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public JacksonCarMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void writeFile() throws IOException {

        objectMapper = new ObjectMapper();
        List<Car> cars = carRepository.findAll();
        cars.forEach(c -> {
            c.setDeclaration(null);
            c.setDetails(null);
        });

        objectMapper.writeValue(new File("target/cars.json"), cars);

        cars = objectMapper.readValue(new URL("file:target/cars.json"), List.class);
        JsonNode jsonNode = objectMapper.readTree(new URL("file:target/cars.json"));

    }

    public List<Car> getCars() throws Exception {

        return objectMapper.readValue(new URL("file:target/cars.json"), List.class);
    }
}
