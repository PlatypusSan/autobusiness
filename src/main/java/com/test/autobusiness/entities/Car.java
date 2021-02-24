package com.test.autobusiness.entities;

import com.test.autobusiness.entities.columnEnums.DriveUnit;
import com.test.autobusiness.entities.columnEnums.EngineType;
import com.test.autobusiness.entities.columnEnums.Transmission;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Calendar;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FilterDef(name = "filterByBrand", parameters = {@ParamDef(name = "brand", type = "string")})
@FilterDef(name = "filterByMileAge", parameters = {
        @ParamDef(name = "minMileAge", type = "integer"),
        @ParamDef(name = "maxMileAge", type = "integer"),
        @ParamDef(name = "mile_age", type = "integer")})
@Filters({
        @Filter(name = "filterByBrand", condition = ":brand = brand"),
        @Filter(name = "filterByMileAge", condition=":minMileAge <= mile_age and :maxMileAge >= mile_age")
})
public class Car extends AbstractEntity{

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

}
