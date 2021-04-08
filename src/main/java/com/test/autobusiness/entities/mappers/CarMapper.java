package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.dto.cardto.CarRequest;
import com.test.autobusiness.entities.dto.cardto.CarResponse;
import com.test.autobusiness.entities.dto.cardto.CarResponseForDeclaration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "details", target = "detailsResponses")
    @Mapping(source = "declaration", target = "declarationResponseForPage")
    CarResponse carToCarResponse(Car car);

    @Mapping(source = "details", target = "detailsResponses")
    CarResponseForDeclaration carToCarResponseToDeclaration(Car car);

    @Mapping(source = "declarationRequest", target = "declaration")
    @Mapping(source = "detailsRequests", target = "details")
    Car carRequestToCar(CarRequest carRequest);

    @Mapping(source = "details", target = "detailsResponses")
    @Mapping(source = "declaration", target = "declarationResponseForPage")
    List<CarResponse> carToCarResponseAsList(List<Car> carList);

    @Mapping(source = "declarationRequest", target = "declaration")
    @Mapping(source = "detailsRequests", target = "details")
    List<Car> carRequestToCarList(List<CarRequest> carRequestList);

    @Mapping(source = "details", target = "detailsResponses")
        //@Mapping(source = "declaration", target = "declarationResponseForPage")
    List<CarResponseForDeclaration> carToCarResponseForDeclarationAsList(List<Car> carList);


}
