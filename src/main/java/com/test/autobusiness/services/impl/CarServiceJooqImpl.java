package com.test.autobusiness.services.impl;

import com.test.autobusiness.dto.car.CarRequest;
import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.dto.car.CarUpdate;
import com.test.autobusiness.dto.directory.VendorDTO;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.jooq.Tables;
import com.test.autobusiness.jooq.tables.records.CarRecord;
import com.test.autobusiness.mappers.CarMapper;
import com.test.autobusiness.services.AbstractCarService;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.services.CurrencyService;
import org.jooq.DSLContext;
import org.jooq.SelectWhereStep;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.test.autobusiness.jooq.tables.Car.CAR;

@Service
public class CarServiceJooqImpl extends AbstractCarService implements CarService {

    private final DSLContext context;
    private final CarMapper carMapper;

    public CarServiceJooqImpl(CurrencyService currencyServiceImpl,
                              CarMapper carMapper,
                              DSLContext context) {
        super(currencyServiceImpl, carMapper);
        this.context = context;
        this.carMapper = carMapper;
    }

    @Override
    public List<Car> getFilteredCars(CarRepresentation carRep) {

        SelectWhereStep<CarRecord> record = context.selectFrom(Tables.CAR);

        if (carRep.getCarFilter() != null) setupSortingAndPagination(carRep.getCarFilter(), record);

        return record
                .orderBy(CAR.BRAND.desc())
                .limit(carRep.getPageSize())
                .offset(carRep.getPage() * carRep.getPageSize())
                .fetchInto(Car.class);
    }

    private void setupSortingAndPagination(CarFilter filter, SelectWhereStep<CarRecord> record) {

        if (filter.getBrand() != null) record.where(CAR.BRAND.contains(filter.getBrand()));
        if (filter.getModel() != null) record.where(CAR.MODEL.contains(filter.getModel()));
        if (filter.getGeneration() != null) record.where(CAR.GENERATION.contains(filter.getGeneration()));
        if (filter.getDriveUnit() != null) record.where(CAR.DRIVE_UNIT.contains(filter.getDriveUnit()));
        if (filter.getTransmission() != null) record.where(CAR.TRANSMISSION.contains(filter.getTransmission()));
        if (filter.getEngineType() != null) record.where(CAR.ENGINE_TYPE.contains(filter.getEngineType()));
        if (filter.getMinEngineVolume() != null) record.where(CAR.ENGINE_VOLUME.gt(filter.getMinEngineVolume()));
        if (filter.getMaxEngineVolume() != null) record.where(CAR.ENGINE_VOLUME.lt(filter.getMaxEngineVolume()));
        if (filter.getMinAge() != null) record.where(CAR.AGE.gt(filter.getMinAge()));
        if (filter.getMaxAge() != null) record.where(CAR.AGE.lt(filter.getMaxAge()));
        if (filter.getMaxMileAge() != null) record.where(CAR.MILE_AGE.lt(filter.getMaxMileAge()));
        if (filter.getMinPrice() != null) record.where(CAR.PRICE.gt(filter.getMinPrice()));
        if (filter.getMaxPrice() != null) record.where(CAR.PRICE.lt(filter.getMaxPrice()));
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
