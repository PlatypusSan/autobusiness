package com.test.autobusiness.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declaration extends AbstractEntity {

    @Column
    private String phoneNumber;

    @Column
    private String description;

    @Column
    private String vendorName;

    @Column
    private String place;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "declaration_details",
            joinColumns = {@JoinColumn(name = "declaration_id")},
            inverseJoinColumns = {@JoinColumn(name = "details_id")}
    )
    Set<Details> details = new HashSet<>();

    public Declaration(String phoneNumber, String description, String vendorName, String place, Date date) {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.vendorName = vendorName;
        this.place = place;
        this.date = date;
    }

}
