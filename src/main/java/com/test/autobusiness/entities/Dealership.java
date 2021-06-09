package com.test.autobusiness.entities;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.test.autobusiness.util.converters.ContactConverter;
import com.test.autobusiness.util.converters.PropertyConverter;
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

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private String city;

    @CsvBindByPosition(position = 2)
    private String time;

    @Type(type = "jsonb")
    @CsvCustomBindByPosition(position = 4, converter = PropertyConverter.class)
    @CsvBindByName(column = "property")
    private String property;

    @CsvBindAndSplitByPosition(position = 3, elementType = Contact.class, splitOn = "\\|", converter = ContactConverter.class)
    @OneToMany(mappedBy = "dealership",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Contact> contacts;
}
