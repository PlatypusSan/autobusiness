package com.test.autobusiness.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Declaration extends AbstractEntity{

    @Column
    @NotBlank
    private String phoneNumber;

    @Column
    private String description;

    @Column
    @NotBlank
    private String vendorName;

    @Column
    private String place;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
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
