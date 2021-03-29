package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.DTOs.CarDTO;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.services.CarMapper;
import com.test.autobusiness.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    @Autowired
    CarService carService;


    @GetMapping(path = "/cars/{id}")
    public CarDTO.CarResponse getCar(@PathVariable int id) {
        return CarMapper.INSTANCE.carToCarResponse(carService.getCar(id));
    }


    @PostMapping(path = "/cars")
    public List<CarDTO.CarResponse> getCars(@RequestParam int page,
                                @RequestParam(required = false) String field,
                                @RequestParam(required = false) String order,
                                @RequestBody CarFilter carFilter) {
        return carService.getFilteredCars(carFilter, page, field, order)
                .stream().map(CarMapper.INSTANCE::carToCarResponse).collect(Collectors.toList());
    }

    @PostMapping(path = "/")
    public void addCar(@Valid @RequestBody CarDTO.CarRequest carDTO) {
        carService.addCar(CarMapper.INSTANCE.carRequestToCar(carDTO));
    }
}
