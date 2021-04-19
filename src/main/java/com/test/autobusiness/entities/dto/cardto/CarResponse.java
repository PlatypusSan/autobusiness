package com.test.autobusiness.entities.dto.cardto;

import com.test.autobusiness.entities.dto.declarationdto.DeclarationResponseForPage;
import com.test.autobusiness.entities.dto.detailsdto.DetailsResponse;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Data
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

    double engineVolume;

    int age;

    int mileAge;

    int price;
}