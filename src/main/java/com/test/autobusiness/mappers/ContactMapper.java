package com.test.autobusiness.mappers;

import com.test.autobusiness.dto.contact.ContactResponse;
import com.test.autobusiness.entities.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactResponse contactToContactResponse(Contact contact);
}
