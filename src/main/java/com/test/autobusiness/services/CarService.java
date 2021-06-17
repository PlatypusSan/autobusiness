package com.test.autobusiness.services;

import com.test.autobusiness.dto.car.CarRequest;
import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.dto.car.CarUpdate;
import com.test.autobusiness.dto.directory.VendorDTO;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarRepresentation;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public interface CarService {

    List<CarResponse> getCarsByVendorAndDriveUnit(String vendor, String driveUnit);

    List<VendorDTO> getCarsByVendor();

    @Transactional
    Car saveCar(Car car);

    CarResponse addCar(CarRequest carRequest);

    void checkUniqueDetails(Car car);

    @Transactional
    Car getCar(long id);

    CarResponse getCarResponse(long id);

    List<CarResponse> getCarResponses(CarRepresentation carRepresentation);

    @Transactional
    void deleteCar(long id);

    @Transactional
    CarResponse updateCar(CarUpdate carUpdate);

    List<Car> getFilteredCars(CarRepresentation carRep);

    List<CarResponse> pickCurrency(CarRepresentation carRep, List<Car> cars);

    Resource getExportFile(CarRepresentation carRepresentation) throws IOException, Exception;
}
