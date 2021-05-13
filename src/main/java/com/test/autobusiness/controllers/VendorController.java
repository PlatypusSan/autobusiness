package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.dto.cardto.CarResponse;
import com.test.autobusiness.entities.dto.directorydto.VendorDTO;
import com.test.autobusiness.entities.mappers.CarMapper;
import com.test.autobusiness.services.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {

    private final CarMapper carMapper;
    private final CarService carService;

    public VendorController(CarMapper carMapper, CarService carService) {
        this.carMapper = carMapper;
        this.carService = carService;
    }

    @GetMapping()
    public List<VendorDTO> getCarsByVendor() {
        return carService.getCarsByVendor();
    }

    @GetMapping(path = "cars")
    public List<CarResponse> getCarsByVendorAndDriveUnit(@RequestParam String vendor, @RequestParam String driveUnit) {
        return carMapper.carToCarResponseAsList(carService.getCarsByVendorAndDriveUnit(vendor, driveUnit));
    }
}
