package com.test.autobusiness.dto.car;

import com.test.autobusiness.dto.details.DetailsResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
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