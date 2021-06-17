package com.test.autobusiness.controllers;

import com.test.autobusiness.dto.car.CarRequest;
import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.dto.car.CarUpdate;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping(path = "/{id}")
    public CarResponse getCar(@PathVariable long id) {

        return carService.getCarResponse(id);
    }

    @PostMapping(path = "all")
    public List<CarResponse> getCars(@RequestBody CarRepresentation carRepresentation) {

        return carService.getCarResponses(carRepresentation);
    }

    @PostMapping()
    public CarResponse addCar(@Valid @RequestBody CarRequest carRequest) {

        return carService.addCar(carRequest);
    }

    @PutMapping()
    public CarResponse updateCar(@RequestBody CarUpdate carUpdate) {

        return carService.updateCar(carUpdate);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable long id) {

        carService.deleteCar(id);
    }

    @PostMapping(path = "/export")
    public ResponseEntity<Resource> exportFile(@RequestBody CarRepresentation carRepresentation) throws Exception {

        Resource resource = carService.getExportFile(carRepresentation);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
