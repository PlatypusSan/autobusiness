package com.test.autobusiness;

import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.entities.enums.SortingField;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.jooq.Tables;
import com.test.autobusiness.jooq.tables.Car;
import com.test.autobusiness.jooq.tables.records.CarRecord;
import com.test.autobusiness.repositories.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.SelectWhereStep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
@SpringBootTest
public class JooqTests {

    @Autowired
    DSLContext context;

    @Autowired
    CarRepository carRepository;

    @Test
    void selectCar() {

        //given
        CarRepresentation carRepresentation = CarRepresentation.builder()
                .pageSize(3)
                .page(0)
                .sortingField(SortingField.BRAND)
                .sortingOrder(Sort.Direction.ASC)
                .build();
        //when
        List<CarResponse> cars;
        SelectWhereStep<CarRecord> record = context.selectFrom(Tables.CAR);
        setupSortingAndPagination(carRepresentation, record);

        //then
        cars = record.fetchInto(CarResponse.class);
    }

    void setupSortingAndPagination(CarRepresentation carRepresentation, SelectWhereStep<CarRecord> record) {

        record.where(Car.CAR.BRAND.contains("Opel")
                .and(Car.CAR.MODEL.contains(""))
                .and(Car.CAR.GENERATION.contains(""))
                .and(Car.CAR.DRIVE_UNIT.contains(""))
                .and(Car.CAR.TRANSMISSION.contains(""))
                .and(Car.CAR.ENGINE_TYPE.contains(""))
                .and(Car.CAR.ENGINE_VOLUME.gt(0D))
                .and(Car.CAR.ENGINE_VOLUME.lt(10D))
                .and(Car.CAR.AGE.gt(0))
                .and(Car.CAR.AGE.lt(2021))
                .and(Car.CAR.MILE_AGE.lt(1000000000))
                .and(Car.CAR.PRICE.gt(0))
                .and(Car.CAR.PRICE.lt(1000000000)))
                .orderBy(Car.CAR.BRAND.desc())
                .limit(carRepresentation.getPageSize())
                .offset(carRepresentation.getPage() * carRepresentation.getPageSize());
    }

}
