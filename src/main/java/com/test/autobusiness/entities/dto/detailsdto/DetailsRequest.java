package com.test.autobusiness.entities.dto.detailsdto;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class DetailsRequest {

    @NotBlank
    String detailType;

    @NotBlank
    String detailName;
}