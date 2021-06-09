package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.dto.car.CarRequest;
import com.test.autobusiness.entities.dto.car.CarResponse;
import com.test.autobusiness.entities.dto.car.CarUpdate;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.entities.mappers.CarMapper;
import com.test.autobusiness.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping(path = "/{id}")
    public CarResponse getCar(@PathVariable long id) {

        return carMapper.carToCarResponse(carService.getCar(id));
    }

    @PostMapping(path = "all")
    public List<CarResponse> getCars(@RequestBody CarRepresentation carRepresentation) {

        return carService.getFilteredCars(carRepresentation);
    }

    @PostMapping()
    public CarResponse addCar(@Valid @RequestBody CarRequest carRequest) {

        Car car = carMapper.carRequestToCar(carRequest);
        carService.addCar(car);
        return carMapper.carToCarResponse(car);
    }

    @PutMapping()
    public CarResponse updateCar(@RequestBody CarUpdate carUpdate) {

        return carService.updateCar(carUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id) {

        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}
