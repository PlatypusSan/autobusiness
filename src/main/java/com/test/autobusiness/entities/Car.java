package com.test.autobusiness.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
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
    private String driveUnit;

    @Column
    private String transmission;

    @Column
    private String engineType;

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

    public void removeDetails() {
        this.details.clear();
    }

    public Car(String brand, String model, String generation, String body, String driveUnit,
               String transmission, String engineType, double engineVolume, int age, int mileAge, int price) {
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
