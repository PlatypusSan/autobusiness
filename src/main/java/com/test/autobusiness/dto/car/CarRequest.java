package com.test.autobusiness.dto.car;

import com.test.autobusiness.dto.declaration.DeclarationRequest;
import com.test.autobusiness.dto.details.DetailsRequest;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CarRequest {

    DeclarationRequest declarationRequest;
    Set<DetailsRequest> detailsRequests;

    @NotBlank(message = "brand is mandatory")
    String brand;

    @NotBlank
    String model;

    @NotBlank
    String generation;

    @NotBlank
    String body;

    @NotNull
    String driveUnit;

    @NotNull
    String transmission;

    @NotNull
    String engineType;

    @Min(0)
    @Max(9)
    double engineVolume;

    @Min(1900)
    @Max(2021)
    int age;

    @Min(0)
    @Max(1000000)
    int mileAge;

    @Min(0)
    int price;
}