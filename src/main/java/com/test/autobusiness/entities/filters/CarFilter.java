package com.test.autobusiness.entities.filters;

import com.test.autobusiness.entities.columnEnums.DriveUnit;
import com.test.autobusiness.entities.columnEnums.EngineType;
import com.test.autobusiness.entities.columnEnums.Transmission;
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
