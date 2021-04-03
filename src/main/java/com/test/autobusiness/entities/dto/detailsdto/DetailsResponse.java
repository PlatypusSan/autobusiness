package com.test.autobusiness.entities.dto.detailsdto;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class DetailsResponse {

    @NotBlank
    String detailType;

    @NotBlank
    String detailName;

    //Set<Car> cars;
}
