package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.dto.cardto.CarRequest;
import com.test.autobusiness.entities.dto.cardto.CarResponse;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.entities.dto.directorydto.VendorDTO;
import com.test.autobusiness.entities.mappers.CarMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

        return carMapper.carToCarResponseAsList(carService.getFilteredCars(carFilter, page, field, order));
    }

    @PostMapping(path = "/")
    public CarResponse addCar(@Valid @RequestBody CarRequest carRequest) {
        Car car = carMapper.carRequestToCar(carRequest);
        carService.addCar(car);
        return carMapper.carToCarResponse(car);
    }

    @GetMapping(path = "/vendors")
    public List<VendorDTO> getCarsByVendor() {

        return carService.getCarsByVendor();
    }
}
