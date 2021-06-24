package com.test.autobusiness.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarFilterDTO {

    String brand;
    String model;
    String generation;
    String body;
    String driveUnit;
    String transmission;
    String engineType;
    Double minEngineVolume;
    Double maxEngineVolume;
    Integer minAge;
    Integer maxAge;
    Integer maxMileAge;
    Integer minPrice;
    Integer maxPrice;
}
