package com.test.autobusiness.entities.filters;

import com.test.autobusiness.entities.columnenums.DriveUnit;
import com.test.autobusiness.entities.columnenums.EngineType;
import com.test.autobusiness.entities.columnenums.Transmission;
import lombok.Value;

@Value
public class CarFilter {

    String brand;
    String model;
    String generation;
    String body;
    DriveUnit driveUnit;
    Transmission transmission;
    EngineType engineType;
    double minEngineVolume;
    double maxEngineVolume;
    int minAge;
    int maxAge;
    int maxMileAge;
    int minPrice;
    int maxPrice;
}
