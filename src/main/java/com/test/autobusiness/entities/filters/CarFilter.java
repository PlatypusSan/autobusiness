package com.test.autobusiness.entities.filters;

import com.test.autobusiness.entities.columnenums.DriveUnit;
import com.test.autobusiness.entities.columnenums.EngineType;
import com.test.autobusiness.entities.columnenums.Transmission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarFilter {

    private String brand;
    private String model;
    private String generation;
    private String body;
    private DriveUnit driveUnit;
    private Transmission transmission;
    private EngineType engineType;
    private double minEngineVolume;
    private double maxEngineVolume;
    private int minAge;
    private int maxAge;
    private int maxMileAge;
    private int minPrice;
    private int maxPrice;
}
