package com.test.autobusiness.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
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
    @JsonFormat(pattern = DateFormat.DATE)
    private Date date;

    @OneToMany(mappedBy = "declaration",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Car> cars;

    /*public Declaration(String phoneNumber, String description, String vendorName, String place, Date date) {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.vendorName = vendorName;
        this.place = place;
        this.date = date;
    }*/

}
