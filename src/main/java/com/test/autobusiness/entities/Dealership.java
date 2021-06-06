package com.test.autobusiness.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dealership extends AbstractEntity {

    private String name;
    private String city;
    private String time;

    @Type(type = "jsonb")
    private String property;

    @OneToMany(mappedBy = "dealership",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Contact> contacts;
}
