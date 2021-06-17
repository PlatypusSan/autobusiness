package com.test.autobusiness.controllers;

import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.dto.directory.VendorDTO;
import com.test.autobusiness.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/vendor")
@RequiredArgsConstructor
public class VendorController {

    private final CarService carService;

    @GetMapping()
    public List<VendorDTO> getCarsByVendor() {
        return carService.getCarsByVendor();
    }

    @GetMapping(path = "cars")
    public List<CarResponse> getCarsByVendorAndDriveUnit(@RequestParam String vendor, @RequestParam String driveUnit) {
        return carService.getCarsByVendorAndDriveUnit(vendor, driveUnit);
    }
}
