package com.test.autobusiness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
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
    private Set<Declaration> declarations = new HashSet<>();

    @JsonIgnore
    public Set<Declaration> getDeclarations() {
        return declarations;
    }

    public Details(String detailType, String detailName) {
        this.detailType = detailType;
        this.detailName = detailName;
    }
}
