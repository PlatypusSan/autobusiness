package com.test.autobusiness.util.converters;

import com.opencsv.bean.AbstractCsvConverter;
import com.test.autobusiness.entities.Contact;
import com.test.autobusiness.entities.enums.ContactType;

public class ContactConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String s) {

        Contact contact = new Contact();
        String[] split = s.split("\\.");
        contact.setContactType(ContactType.valueOf(split[0]));
        contact.setValue(split[1]);
        return contact;
    }
}
