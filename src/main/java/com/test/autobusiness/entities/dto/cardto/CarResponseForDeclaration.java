package com.test.autobusiness.entities.dto.cardto;

import com.test.autobusiness.entities.dto.detailsdto.DetailsResponse;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Value
public class CarResponseForDeclaration extends RepresentationModel<CarResponseForDeclaration> {

    long id;

    String brand;

    Set<DetailsResponse> detailsResponses;

    String model;

    String generation;

    String body;

    String driveUnit;

    String transmission;

    String engineType;

    double engineVolume;

    int age;

    int mileAge;

    int price;
}