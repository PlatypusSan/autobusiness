package com.test.autobusiness.entities.dto.cardto;

import com.test.autobusiness.entities.dto.declarationdto.DeclarationRequest;
import com.test.autobusiness.entities.dto.detailsdto.DetailsRequest;
import lombok.Value;

import javax.validation.constraints.Min;
import java.util.Set;

@Value
public class CarUpdate {

    @Min(0)
    long id;

    DeclarationRequest declarationRequest;

    Set<DetailsRequest> detailsRequests;

    String brand;

    String model;

    String generation;

    String body;

    String driveUnit;

    String transmission;

    String engineType;

    Double engineVolume;

    Integer age;

    Integer mileAge;

    Integer price;
}
