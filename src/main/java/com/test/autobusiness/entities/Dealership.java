package com.test.autobusiness.entities;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.test.autobusiness.util.converters.ContactConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Dealership extends AbstractEntity implements Serializable {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "city")
    private String city;

    @CsvBindByName(column = "time")
    private String time;

    @Type(type = "jsonb")
    //@CsvCustomBindByPosition(position = 4, converter = PropertyConverter.class)
    @CsvBindByName(column = "property")
    private String property;

    @CsvBindAndSplitByName(elementType = Contact.class, splitOn = "\\|", converter = ContactConverter.class)
    @OneToMany(mappedBy = "dealership",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Contact> contacts;
}
