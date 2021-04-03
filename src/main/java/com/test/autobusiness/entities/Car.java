package com.test.autobusiness.entities;

import com.test.autobusiness.entities.columnenums.DriveUnit;
import com.test.autobusiness.entities.columnenums.EngineType;
import com.test.autobusiness.entities.columnenums.Transmission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(name = "filterByBrand", parameters = {@ParamDef(name = "brand", type = "string")})
@FilterDef(name = "filterByModel", parameters = {@ParamDef(name = "model", type = "string")})
@FilterDef(name = "filterByGeneration", parameters = {@ParamDef(name = "generation", type = "string")})
@FilterDef(name = "filterByBody", parameters = {@ParamDef(name = "body", type = "string")})
@FilterDef(name = "filterByDriveUnit", parameters = {@ParamDef(name = "drive_unit", type = "string")})
@FilterDef(name = "filterByTransmission", parameters = {@ParamDef(name = "transmission ", type = "string")})
@FilterDef(name = "filterByEngineType", parameters = {@ParamDef(name = "engine_type", type = "string")})
@FilterDef(name = "filterByEngineVolume", parameters = {
        @ParamDef(name = "minEngineVolume", type = "double"),
        @ParamDef(name = "maxEngineVolume", type = "double"),
        @ParamDef(name = "engine_volume", type = "double")})
@FilterDef(name = "filterByMileAge", parameters = {
        @ParamDef(name = "maxMileAge", type = "integer"),
        @ParamDef(name = "mile_age", type = "integer")})

@Filters({
        @Filter(name = "filterByBrand", condition = ":brand = brand"),
        @Filter(name = "filterByModel", condition = ":model = model"),
        @Filter(name = "filterByGeneration", condition = ":generation = generation"),
        @Filter(name = "filterByBody", condition = ":body = body"),
        @Filter(name = "filterByDriveUnit", condition = ":drive_unit = drive_unit"),
        @Filter(name = "filterByTransmission", condition = ":transmission = transmission"),
        @Filter(name = "filterByEngineType", condition = ":engine_type = engine_type"),
        @Filter(name = "filterByEngineVolume", condition = ":maxEngineVolume >= engine_volume and :minEngineVolume <= engine_volume"),
        @Filter(name = "filterByMileAge", condition = ":maxMileAge >= mile_age")
})
public class Car extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dec_id")
    private Declaration declaration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_details",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "details_id"))
    private Set<Details> details;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String generation;

    @Column
    private String body;

    @Column
    @Enumerated(EnumType.STRING)
    private DriveUnit driveUnit;

    @Column
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column
    private double engineVolume;

    @Column
    private int age;

    @Column
    private int mileAge;

    @Column
    private int price;

    @Column
    boolean deleted;


    public Car(String brand, String model, String generation, String body, DriveUnit driveUnit,
               Transmission transmission, EngineType engineType, double engineVolume, int age, int mileAge, int price) {
        this.brand = brand;
        this.model = model;
        this.generation = generation;
        this.body = body;
        this.driveUnit = driveUnit;
        this.transmission = transmission;
        this.engineType = engineType;
        this.engineVolume = engineVolume;
        this.age = age;
        this.mileAge = mileAge;
        this.price = price;
    }
}
