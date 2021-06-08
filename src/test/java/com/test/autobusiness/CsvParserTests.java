package com.test.autobusiness;

import com.test.autobusiness.entities.Contact;
import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.entities.enums.ContactType;
import com.test.autobusiness.repositories.DealershipRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootTest
public class CsvParserTests {

    @Autowired
    private DealershipRepository dealershipRepository;

   /* @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;*/

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

    @Test
    void saveToCsv() throws Exception {

        /*JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());*/
    }

    @Test
    void threadStart() throws InterruptedException, ExecutionException {

        
    }

    @Async
    public CompletableFuture<String> asyncTest() throws InterruptedException {

        System.out.println("IN asyncTest - after invocation");
        Thread.sleep(5000);
        System.out.println("IN asyncTest - after sleep");
        return CompletableFuture.completedFuture("result");
    }
}
