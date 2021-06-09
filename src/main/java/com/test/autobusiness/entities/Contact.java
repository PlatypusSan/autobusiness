package com.test.autobusiness.entities;

import com.test.autobusiness.entities.enums.ContactType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Contact extends AbstractEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ContactType contactType;

    @Column
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealership_id")
    private Dealership dealership;
}
