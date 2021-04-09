package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.dto.cardto.CarRequest;
import com.test.autobusiness.entities.dto.cardto.CarResponse;
import com.test.autobusiness.entities.dto.cardto.CarUpdate;
import com.test.autobusiness.entities.dto.directorydto.VendorDTO;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.entities.mappers.CarMapper;
import com.test.autobusiness.services.CarService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping(path = "/car/{id}")
    public CarResponse getCar(@PathVariable int id) {
        return carMapper.carToCarResponse(carService.getCar(id));
    }


    @PostMapping(path = "/cars")
    public List<CarResponse> getCars(@RequestBody CarRepresentation carRepresentation) {

        return carMapper.carToCarResponseAsList(carService.getFilteredCars(carRepresentation));
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

    @GetMapping(path = "/vendor/cars")
    public List<CarResponse> getCarsByVendorAndDriveUnit(@RequestParam String vendor, @RequestParam String driveUnit) {
        return carMapper.carToCarResponseAsList(carService.getCarsByVendorAndDriveUnit(vendor, driveUnit));
    }

    @PutMapping("/car")
    public void updateCar(@RequestBody CarUpdate carUpdate) {
        carService.updateCar(carUpdate);
    }

    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }

}
