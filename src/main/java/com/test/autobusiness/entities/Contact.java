package com.test.autobusiness.entities;

import com.test.autobusiness.entities.enums.ContactType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Contact extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ContactType contactType;

    @Column
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealership_id")
    private Dealership dealership;

    public Contact(ContactType contactType, String value) {
        this.contactType = contactType;
        this.value = value;
    }
}
