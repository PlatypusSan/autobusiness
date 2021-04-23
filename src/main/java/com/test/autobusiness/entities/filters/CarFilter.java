package com.test.autobusiness.entities.filters;

import lombok.Data;

@Data
public class CarFilter {

    private String brand;
    private String model;
    private String generation;
    private String body;
    private String driveUnit;
    private String transmission;
    private String engineType;
    private double minEngineVolume;
    private double maxEngineVolume;
    private int minAge;
    private int maxAge;
    private int maxMileAge;
    private int minPrice;
    private int maxPrice;

    public CarFilter() {
        this.brand = "";
        this.model = "";
        this.generation = "";
        this.body = "";
        this.driveUnit = "";
        this.transmission = "";
        this.engineType = "";
        this.minEngineVolume = 0D;
        this.maxEngineVolume = 9D;
        this.maxMileAge = 1000000;
        this.minAge = 1900;
        this.maxAge = 2021;
        this.minPrice = 0;
        this.maxPrice = 10000000;
    }
}
