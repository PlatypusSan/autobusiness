package com.test.autobusiness;

import com.test.autobusiness.repositories.CarRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContainerTests {

    @Autowired
    private CarRepository carRepository;

    @ClassRule
    @Autowired
    public static AutobusinessPostgresqlContainer postgreSQLContainer;

    @Test
    @Transactional
    void getCar() {

        assertEquals(4L, carRepository.findById(4L).get().getId());
    }
}
