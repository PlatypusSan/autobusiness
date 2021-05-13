package com.test.autobusiness.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}