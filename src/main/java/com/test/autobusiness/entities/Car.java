package com.test.autobusiness.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "declaration_id")
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
}
