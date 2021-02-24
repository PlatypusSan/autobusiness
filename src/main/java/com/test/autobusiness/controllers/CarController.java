package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.columnEnums.DriveUnit;
import com.test.autobusiness.entities.columnEnums.EngineType;
import com.test.autobusiness.entities.columnEnums.Transmission;
import com.test.autobusiness.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping(path = "/cars/{id}")
    public Car getCar(@PathVariable int id) {
        Car car = carService.getCar(id);

        return car;
    }

    @GetMapping(path = "/cars")
    public List<Car> getCars(@RequestParam int page,
                             @RequestParam(required = false) String field,
                             @RequestParam(required = false) String order,
                             @RequestParam(required = false) String brand,
                             @RequestParam(required = false) String model,
                             @RequestParam(required = false) String generation,
                             @RequestParam(required = false) String body,
                             @RequestParam(required = false) DriveUnit driveUnit,
                             @RequestParam(required = false) Transmission transmission,
                             @RequestParam(required = false) EngineType engineType,
                             @RequestParam(required = false) double engineVolume,
                             @RequestParam(required = false) int age,
                             @RequestParam(required = false) int mileAge,
                             @RequestParam(required = false) int price
                             ) {

        if (field == null) {
            //return carService.getCars(page);
            return carService.getFilteredCars("vvvvv", page);
        } else {
            return carService.getCars(page, field, order);
        }
    }

    @PostMapping(path = "/cars")
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }
}
