package com.test.autobusiness.entities.filters;

import lombok.Value;

@Value
public class CarFilter {

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
