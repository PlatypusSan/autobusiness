package com.test.autobusiness.entities.dto.detailsdto;

import com.test.autobusiness.entities.Car;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Value
public class DetailsResponse {

    @NotBlank
    String detailType;

    @NotBlank
    String detailName;

    //Set<Car> cars;
}
