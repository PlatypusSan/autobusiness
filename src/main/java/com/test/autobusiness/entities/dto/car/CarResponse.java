package com.test.autobusiness.entities.dto.car;

import com.test.autobusiness.entities.dto.declaration.DeclarationResponseForPage;
import com.test.autobusiness.entities.dto.details.DetailsResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarResponse extends RepresentationModel<CarResponse> {

    long id;
    DeclarationResponseForPage declarationResponseForPage;
    Set<DetailsResponse> detailsResponses;
    String brand;
    String model;
    String generation;
    String body;
    String driveUnit;
    String transmission;
    String engineType;
    String currency;
    double engineVolume;
    int age;
    int mileAge;
    int price;
}