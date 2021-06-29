package com.test.autobusiness.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Car extends AbstractEntity {

    @Column
    boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "declaration_id")
    private Declaration declaration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_details",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "details_id"))
    private Set<Details> details;

    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "generation")
    private String generation;
    @Column(name = "body")
    private String body;
    @Column(name = "drive_unit")
    private String driveUnit;
    @Column(name = "transmission")
    private String transmission;
    @Column(name = "engine_type")
    private String engineType;
    @Column(name = "engine_volume")
    private double engineVolume;
    @Column(name = "age")
    private int age;
    @Column(name = "mile_age")
    private int mileAge;
    @Column(name = "price")
    private int price;

    public void removeDetails() {
        this.details.clear();
    }
}
