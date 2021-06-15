package com.test.autobusiness.dto.details;

import com.test.autobusiness.entities.Car;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Value
public class DetailsRequest {

    @NotBlank
    String detailType;

    @NotBlank
    String detailName;

    Set<Car> cars;
}