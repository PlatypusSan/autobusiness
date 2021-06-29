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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.test.autobusiness.jooq.tables.Car.CAR;

@Primary
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
        CarFilter carFilter = new CarFilter();
        carMapper.updateCarFilterFromDTO(carRep.getCarFilterDTO(), carFilter);

        setupSortingAndPagination(carFilter, record);

        return record
                .orderBy(CAR.BRAND.desc())
                .limit(carRep.getPageSize())
                .offset(carRep.getPage() * carRep.getPageSize())
                .fetchInto(Car.class);
    }

    private void setupSortingAndPagination(CarFilter filter, SelectWhereStep<CarRecord> record) {

        record.where(CAR.BRAND.contains(filter.getBrand())
                .and(CAR.MODEL.contains(filter.getModel()))
                .and(CAR.GENERATION.contains(filter.getGeneration()))
                .and(CAR.DRIVE_UNIT.contains(filter.getDriveUnit()))
                .and(CAR.TRANSMISSION.contains(filter.getTransmission()))
                .and(CAR.ENGINE_TYPE.contains(filter.getEngineType()))
                .and(CAR.ENGINE_VOLUME.gt(filter.getMinEngineVolume()))
                .and(CAR.ENGINE_VOLUME.lt(filter.getMaxEngineVolume()))
                .and(CAR.AGE.gt(filter.getMinAge()))
                .and(CAR.AGE.lt(filter.getMaxAge()))
                .and(CAR.MILE_AGE.lt(filter.getMaxMileAge()))
                .and(CAR.PRICE.gt(filter.getMinPrice()))
                .and(CAR.PRICE.lt(filter.getMaxPrice())));
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
