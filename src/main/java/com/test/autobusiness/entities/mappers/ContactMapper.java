package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.Contact;
import com.test.autobusiness.entities.dto.contact.ContactResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactResponse contactToContactResponse(Contact contact);
}
