package com.test.autobusiness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"detail_name", "detail_type"}))
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

}
