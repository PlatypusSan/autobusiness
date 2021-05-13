package com.test.autobusiness.entities.dto.car;

import com.test.autobusiness.entities.dto.details.DetailsResponse;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Data
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