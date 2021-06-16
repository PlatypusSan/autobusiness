package com.test.autobusiness;

import com.test.autobusiness.containers.AutobusinessPostgresqlContainer;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.repositories.CarRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@ContextConfiguration(initializers = {CarRepositoryDatabaseContainerTests.Initializer.class})
@SpringBootTest
@TestPropertySource("/application-test.yaml")
public class CarRepositoryDatabaseContainerTests {

    @ClassRule
    @Container
    private static final AutobusinessPostgresqlContainer postgreSQLContainer = AutobusinessPostgresqlContainer.postgresqlContainer();

    @Autowired
    private CarRepository carRepository;

    @Value("${username}")
    private String username;

    @Test
    void givenPostgresContainer_whenGetPostgresUsername_thenUsernameIsCorrect() {

        assertEquals(username, postgreSQLContainer.getUsername());
    }

    @Test
    void givenPostgresContainer_whenGetCars_thenCarsIsNotNull() {

        //when
        List<Car> carList = carRepository.findAll(PageRequest.of(0, 10)).getContent();

        //then
        assertNotNull(carList);
    }

    @Test
    @Rollback(false)
    @Transactional
    void givenCarAndCarRepository_whenPersistAndFindCar_thenCompareCars() {

        //given
        Car car = Car.builder()
                .brand("BMV")
                .age(2000)
                .body("SUV")
                .engineType("GAS")
                .build();

        //when
        carRepository.save(car);
        Car carToCompare = carRepository.findById(1L).get();
        carToCompare.setDetails(null);

        //then
        assertEquals(car, carToCompare);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
