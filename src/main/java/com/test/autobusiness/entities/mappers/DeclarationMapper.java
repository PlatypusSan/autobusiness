package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.controllers.CarController;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.dto.cardto.CarRequest;
import com.test.autobusiness.entities.dto.cardto.CarResponse;
import com.test.autobusiness.entities.dto.cardto.CarResponseForDeclaration;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationRequest;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Mapper(componentModel = "spring")
public interface DeclarationMapper {

    @Mapping(source = "cars", target = "carResponseForDeclarationList")
    DeclarationResponse declarationToDeclarationResponse(Declaration declaration);

    @Mapping(source = "carRequestList", target = "cars")
    Declaration declarationRequestToDeclaration(DeclarationRequest declarationRequest);

    @Mapping(source = "declarationRequest", target = "declaration")
    @Mapping(source = "detailsRequests", target = "details")
    Car carRequestToCar(CarRequest carRequest);

    @Mapping(source = "details", target = "detailsResponses")
    CarResponseForDeclaration carToCarResponseToDeclaration(Car car);

    @AfterMapping
    default void addLinks(@MappingTarget CarResponseForDeclaration carResponse) {
        carResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getCars(null))
                .slash(carResponse.getId())
                .withSelfRel());
    }


}
