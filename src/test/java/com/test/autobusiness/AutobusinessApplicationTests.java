package com.test.autobusiness;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AutobusinessApplicationTests {

    @ClassRule
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void givenApplicationContext_thenContextIsNotNull() {
        assertNotNull(applicationContext);
    }

}
