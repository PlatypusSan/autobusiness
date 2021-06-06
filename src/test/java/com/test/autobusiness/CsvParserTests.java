package com.test.autobusiness;

import com.test.autobusiness.entities.Contact;
import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.entities.enums.ContactType;
import com.test.autobusiness.repositories.DealershipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CsvParserTests {

    @Autowired
    private DealershipRepository dealershipRepository;

    @Test
    void persistDealership() {

        Contact contact = new Contact(ContactType.EMAIL, "sdfgf");
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        Dealership dealership = new Dealership("name", "city", "time", "{}", contactList);
        dealership.getContacts().get(0).setDealership(dealership);
        dealershipRepository.save(dealership);

        Dealership dealershipResult = dealershipRepository.findAll().iterator().next();
        List<Contact> contactResult = dealershipResult.getContacts();
    }
}
