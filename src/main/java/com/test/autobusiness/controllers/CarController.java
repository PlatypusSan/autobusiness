package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping(path = "/cars")
    public List<Car> getCars(@RequestParam int page,
                             @RequestParam(required = false) String field,
                             @RequestParam(required = false) String order,
                             @RequestBody CarFilter carFilter) {
        return carService.getFilteredCars(carFilter, page, field, order);
    }

    @PostMapping(path = "/")
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }
}
