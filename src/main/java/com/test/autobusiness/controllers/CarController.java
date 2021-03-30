package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.DTOs.CarDTOs.CarRequest;
import com.test.autobusiness.entities.DTOs.CarDTOs.CarResponse;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.entities.mappers.CarMapper;
import com.test.autobusiness.services.CarService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping(path = "/cars/{id}")
    public CarResponse getCar(@PathVariable int id) {
        return carMapper.carToCarResponse(carService.getCar(id));
    }


    @PostMapping(path = "/cars")
    public List<CarResponse> getCars(@RequestParam int page,
                                            @RequestParam(required = false) String field,
                                            @RequestParam(required = false) String order,
                                            @RequestBody CarFilter carFilter) {
        return carService.getFilteredCars(carFilter, page, field, order)
                .stream().map(carMapper::carToCarResponse).collect(Collectors.toList());
    }

    @PostMapping(path = "/")
    public void addCar(@Valid @RequestBody CarRequest carDTO) {
        carService.addCar(carMapper.carRequestToCar(carDTO));
    }
}
