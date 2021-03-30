package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.DTOs.CarDTOs.CarRequest;
import com.test.autobusiness.entities.DTOs.CarDTOs.CarResponse;
import com.test.autobusiness.entities.DTOs.CarDTOs.CarResponseForDeclaration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "declaration", target = "declarationResponseForPage")
    CarResponse carToCarResponse(Car car);

    CarResponseForDeclaration carToCarResponseToDeclaration(Car car);

    @Mapping(source = "declarationRequest", target = "declaration")
    Car carRequestToCar(CarRequest carRequest);
}
