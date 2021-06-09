package com.test.autobusiness.entities.dto.contact;

import com.test.autobusiness.entities.enums.ContactType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactResponse extends RepresentationModel<ContactResponse> {

    private ContactType contactType;
    private String value;
}
