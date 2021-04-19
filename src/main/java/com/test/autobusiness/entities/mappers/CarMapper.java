package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.controllers.CarController;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.dto.cardto.CarRequest;
import com.test.autobusiness.entities.dto.cardto.CarResponse;
import com.test.autobusiness.entities.dto.cardto.CarResponseForDeclaration;
import com.test.autobusiness.entities.dto.cardto.CarUpdate;
import org.mapstruct.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "declarationRequest", target = "declaration")
    @Mapping(source = "detailsRequests", target = "details")
    void updateCarFromUpdate(CarUpdate carUpdate, @MappingTarget Car car);

    @AfterMapping
    default void addLinks(@MappingTarget CarResponse carResponse) {

        addLinksToCar(carResponse, carResponse.getId());
    }

    @AfterMapping
    default void addLinks(@MappingTarget CarResponseForDeclaration carResponse) {

        addLinksToCar(carResponse, carResponse.getId());
    }

    default <T extends RepresentationModel> void addLinksToCar(T carToLink, long id) {
        carToLink.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getCar(id))
                .withSelfRel());
        carToLink.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CarController.class).deleteCar(id))
                .withRel("delete"));
        carToLink.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CarController.class).updateCar(null))
                .slash(id)
                .withRel("update"));
    }


}
