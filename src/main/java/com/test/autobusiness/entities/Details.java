package com.test.autobusiness.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Details extends AbstractEntity {

    @Column
    private String detailType;

    @Column
    private String detailName;

    @ManyToMany(mappedBy = "details")
    private Set<Car> cars;

    public Details(String detailType, String detailName) {
        this.detailType = detailType;
        this.detailName = detailName;
    }
}
