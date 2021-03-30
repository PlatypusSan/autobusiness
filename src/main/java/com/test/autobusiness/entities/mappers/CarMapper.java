package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.DTOs.CarDTOs.CarRequest;
import com.test.autobusiness.entities.DTOs.CarDTOs.CarResponse;
import com.test.autobusiness.entities.DTOs.CarDTOs.CarResponseForDeclaration;
import org.dom4j.util.UserDataDocumentFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "declaration", target = "declarationResponseForPage")
    CarResponse carToCarResponse(Car car);

    CarResponseForDeclaration carToCarResponseToDeclaration(Car car);

    @Mapping(source = "declarationRequest", target = "declaration")
    Car carRequestToCar(CarRequest carRequest);

    @Mapping(source = "declaration", target = "declarationResponseForPage")
    List<CarResponse> carToCarResponseAsList(List<Car> carList);

}
