package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.dto.car.CarRequest;
import com.test.autobusiness.entities.dto.car.CarResponse;
import com.test.autobusiness.entities.dto.car.CarUpdate;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.entities.mappers.CarMapper;
import com.test.autobusiness.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(path = "/{id}")
    public CarResponse getCar(@PathVariable long id) {

        return carMapper.carToCarResponse(carService.getCar(id));
    }

    @PostMapping(path = "all")
    public List<CarResponse> getCars(@RequestBody CarRepresentation carRepresentation) {

        List<Car> filteredCars = carService.getFilteredCars(carRepresentation);
        return carService.pickCurrency(carRepresentation, filteredCars);
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

    @PostMapping(path = "/export")
    public ResponseEntity<Resource> exportFile(@RequestBody CarRepresentation carRepresentation) throws IOException {

        Resource resource = carService.getExportFile(carRepresentation);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
