package com.test.autobusiness.services.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.QCar;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.mappers.CarMapper;
import com.test.autobusiness.mappers.DirectoryMapper;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import com.test.autobusiness.services.CurrencyService;
import com.test.autobusiness.services.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Primary
@Slf4j
public class CarServiceQueryDslImpl extends CarServiceImpl {

    private final CarMapper carMapper;
    private final CarRepository carRepository;

    public CarServiceQueryDslImpl(CarRepository carRepository,
                                  DirectoryMapper directoryMapper,
                                  DetailsRepository detailsRepository,
                                  CarMapper carMapper,
                                  CurrencyService currencyServiceImpl,
                                  ExportService exportService,
                                  EntityManager entityManager) {
        super(carRepository, directoryMapper, detailsRepository, carMapper, currencyServiceImpl, exportService);
        this.carMapper = carMapper;
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getFilteredCars(CarRepresentation carRep) {
        Pageable pageConfig = configurePage(carRep);
        List<Car> result;
        if (carRep.getCarFilterDTO() != null) {
            CarFilter carFilter = new CarFilter();
            carMapper.updateCarFilterFromDTO(carRep.getCarFilterDTO(), carFilter);
            BooleanExpression booleanExpression = getFilterExpression(carFilter);
            result = carRepository.findAll(booleanExpression, pageConfig).getContent();
        } else {
            result = carRepository.findAll(pageConfig).getContent();
        }
        return result;
    }

    public BooleanExpression getFilterExpression(CarFilter carFilter) {

        return QCar.car.brand.contains(carFilter.getBrand())
                .and(QCar.car.model.contains(carFilter.getModel()))
                .and(QCar.car.generation.contains(carFilter.getGeneration()))
                .and(QCar.car.driveUnit.contains(carFilter.getDriveUnit()))
                .and(QCar.car.transmission.contains(carFilter.getTransmission()))
                .and(QCar.car.engineType.contains(carFilter.getEngineType()))
                .and(QCar.car.engineVolume.gt(carFilter.getMinEngineVolume()))
                .and(QCar.car.engineVolume.lt(carFilter.getMaxEngineVolume()))
                .and(QCar.car.age.gt(carFilter.getMinAge()))
                .and(QCar.car.age.lt(carFilter.getMaxAge()))
                .and(QCar.car.mileAge.lt(carFilter.getMaxMileAge()))
                .and(QCar.car.price.gt(carFilter.getMinPrice()))
                .and(QCar.car.price.lt(carFilter.getMaxPrice()));
    }

}
