package com.test.autobusiness.services.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.test.autobusiness.dto.car.CarRequest;
import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.dto.car.CarUpdate;
import com.test.autobusiness.dto.directory.VendorDTO;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.QCar;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.mappers.CarMapper;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.services.AbstractCarService;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.services.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class CarServiceQueryDslImpl extends AbstractCarService implements CarService {

    private final CarRepository carRepository;

    public CarServiceQueryDslImpl(CurrencyService currencyServiceImpl,
                                  CarRepository carRepository,
                                  CarMapper carMapper) {
        super(currencyServiceImpl, carMapper);
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getFilteredCars(CarRepresentation carRep) {
        Pageable pageConfig = configurePage(carRep);
        List<Car> result;
        if (carRep.getCarFilter() != null) {
            Predicate predicate = getFilterExpression(carRep.getCarFilter());
            result = carRepository.findAll(predicate, pageConfig).getContent();
        } else {
            result = carRepository.findAll(pageConfig).getContent();
        }
        return result;
    }

    private Predicate getFilterExpression(CarFilter filter) {

        BooleanBuilder builder = new BooleanBuilder();
        if (filter.getBrand() != null) builder.and(QCar.car.brand.contains(filter.getBrand()));
        if (filter.getModel() != null) builder.and(QCar.car.model.contains(filter.getModel()));
        if (filter.getGeneration() != null) builder.and(QCar.car.generation.contains(filter.getGeneration()));
        if (filter.getBody() != null) builder.and(QCar.car.generation.contains(filter.getBody()));
        if (filter.getDriveUnit() != null) builder.and(QCar.car.driveUnit.contains(filter.getDriveUnit()));
        if (filter.getTransmission() != null) builder.and(QCar.car.transmission.contains(filter.getTransmission()));
        if (filter.getEngineType() != null) builder.and(QCar.car.engineType.contains(filter.getEngineType()));
        if (filter.getMinEngineVolume() != null) builder.and(QCar.car.engineVolume.gt(filter.getMinEngineVolume()));
        if (filter.getMaxEngineVolume() != null) builder.and(QCar.car.engineVolume.lt(filter.getMaxEngineVolume()));
        if (filter.getMinAge() != null) builder.and(QCar.car.age.gt(filter.getMinAge()));
        if (filter.getMaxAge() != null) builder.and(QCar.car.age.lt(filter.getMaxAge()));
        if (filter.getMaxMileAge() != null) builder.and(QCar.car.mileAge.lt(filter.getMaxMileAge()));
        if (filter.getMinPrice() != null) builder.and(QCar.car.price.gt(filter.getMinPrice()));
        if (filter.getMaxPrice() != null) builder.and(QCar.car.price.lt(filter.getMaxPrice()));
        return builder.getValue();
    }

    @Override
    public List<CarResponse> getCarsByVendorAndDriveUnit(String vendor, String driveUnit) {
        return null;
    }

    @Override
    public List<VendorDTO> getCarsByVendor() {
        return null;
    }

    @Override
    public Car saveCar(Car car) {
        return null;
    }

    @Override
    public CarResponse addCar(CarRequest carRequest) {
        return null;
    }

    @Override
    public void checkUniqueDetails(Car car) {

    }

    @Override
    public Car getCar(long id) {
        return null;
    }

    @Override
    public CarResponse getCarResponse(long id) {
        return null;
    }

    @Override
    public List<CarResponse> getCarResponses(CarRepresentation carRepresentation) {

        List<Car> filteredCars = getFilteredCars(carRepresentation);
        return pickCurrency(carRepresentation, filteredCars);
    }

    @Override
    public void deleteCar(long id) {

    }

    @Override
    public CarResponse updateCar(CarUpdate carUpdate) {
        return null;
    }

    @Override
    public Resource getExportFile(CarRepresentation carRepresentation) throws IOException, Exception {
        return null;
    }
}
