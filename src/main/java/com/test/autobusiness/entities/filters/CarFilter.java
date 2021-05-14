package com.test.autobusiness.entities.filters;

import lombok.Data;

@Data
public class CarFilter {

    private String brand = "";
    private String model = "";
    private String generation = "";
    private String body = "";
    private String driveUnit = "";
    private String transmission = "";
    private String engineType = "";
    private double minEngineVolume = 0D;
    private double maxEngineVolume = 9D;
    private int minAge = 1900;
    private int maxAge = 2021;
    private int maxMileAge = 1000000;
    private int minPrice = 0;
    private int maxPrice = 10000000;
}
