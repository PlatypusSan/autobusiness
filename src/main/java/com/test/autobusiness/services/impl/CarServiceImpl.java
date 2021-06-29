package com.test.autobusiness.services.impl;

import com.test.autobusiness.dto.car.CarRequest;
import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.dto.car.CarUpdate;
import com.test.autobusiness.dto.directory.VendorDTO;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.Details;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.mappers.CarMapper;
import com.test.autobusiness.mappers.DirectoryMapper;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import com.test.autobusiness.services.AbstractCarService;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.services.CurrencyService;
import com.test.autobusiness.services.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@Primary
public class CarServiceImpl extends AbstractCarService implements CarService {

    private final CarRepository carRepository;
    private final DirectoryMapper directoryMapper;
    private final DetailsRepository detailsRepository;
    private final CarMapper carMapper;
    private final ExportService exportService;

    public CarServiceImpl(CurrencyService currencyServiceImpl,
                          CarMapper carMapper,
                          CarRepository carRepository,
                          DirectoryMapper directoryMapper,
                          DetailsRepository detailsRepository,
                          ExportService exportService) {
        super(currencyServiceImpl, carMapper);
        this.carRepository = carRepository;
        this.directoryMapper = directoryMapper;
        this.detailsRepository = detailsRepository;
        this.carMapper = carMapper;
        this.exportService = exportService;
    }

    public List<CarResponse> getCarsByVendorAndDriveUnit(String vendor, String driveUnit) {

        List<Car> carList = carRepository.findCarByDriveUnitAndDeclarationVendorName(driveUnit, vendor);
        return carMapper.carToCarResponseAsList(carList);
    }

    public List<VendorDTO> getCarsByVendor() {

        return directoryMapper.dirElementToVendorDTOList(carRepository.getAllDirectories());
    }

    @Transactional
    public Car saveCar(Car car) {

        checkUniqueDetails(car);
        return carRepository.save(car);
    }

    @Override
    public CarResponse addCar(CarRequest carRequest) {

        Car car = carMapper.carRequestToCar(carRequest);
        return carMapper.carToCarResponse(saveCar(car));
    }

    public void checkUniqueDetails(Car car) {
        if (car.getDetails() == null) return;
        List<Details> details = new ArrayList<>(car.getDetails());
        for (int i = 0; i < car.getDetails().size(); i++) {
            if (details.get(i).getId() == 0) {
                Details tempDetail = detailsRepository.findDetailsByDetailNameAndDetailType(details.get(i).getDetailName(),
                        details.get(i).getDetailType());
                if (tempDetail != null) {
                    details.set(i, tempDetail);
                }
            }
        }
        car.setDetails(new HashSet<Details>(details));
    }

    @Transactional
    public Car getCar(long id) {

        return carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "There is no car with id: " + id));
    }

    @Override
    public CarResponse getCarResponse(long id) {
        return carMapper.carToCarResponse(getCar(id));
    }

    @Override
    public List<CarResponse> getCarResponses(CarRepresentation carRepresentation) {

        List<Car> filteredCars = getFilteredCars(carRepresentation);
        return pickCurrency(carRepresentation, filteredCars);
    }

    @Transactional
    public void deleteCar(long id) {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "There is no car with id: " + id));
        car.removeDetails();
        carRepository.save(car);
        carRepository.deleteById(id);
        log.info("IN deleteCar - car with id: {} successfully deleted", id);
    }

    @Transactional
    public CarResponse updateCar(CarUpdate carUpdate) {

        return carRepository.findById(carUpdate.getId())
                .map(car -> carMapper.updateCarFromUpdate(carUpdate, car))
                .map(carRepository::save)
                .map(carMapper::carToCarResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "There is no car with id: " + carUpdate.getId()));
    }

    public List<Car> getFilteredCars(CarRepresentation carRep) {

        Pageable pageConfig = configurePage(carRep);
        List<Car> result;
        if (carRep.getCarFilter() != null) {
            result = carRepository.findAll(
                    carRepository.getFilterSpecification(carRep.getCarFilter()), pageConfig).getContent();
        } else {
            result = carRepository.findAll(pageConfig).getContent();
        }
        return result;
    }

    public Resource getExportFile(CarRepresentation carRepresentation) throws Exception {

        List<CarResponse> carResponseList = pickCurrency(carRepresentation, getFilteredCars(carRepresentation));
        return exportService.getExportFile(carResponseList);
    }
}