package com.test.autobusiness;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class CarServiceTests {

    @TestConfiguration
    static class CarServiceContextConfiguration {

        /*@Bean
        public CarService carService() {
            return new CarService() {

            }
        }*/
    }

    private final CarService carService;
    private final MockMvc mockMvc;

    @Mock
    private CarRepository carRepository;

    private static String jwt;
    private static String CAR_BRAND = "BMW";
    private static long CAR_ID = 4;

    @Autowired
    public CarServiceTests(CarService carService,
                           MockMvc mockMvc) {
        this.carService = carService;
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void setUp() {

        Car car = Car
                .builder()
                .brand(CAR_BRAND)
                .build();

        Mockito
                .when(carRepository.findById(CAR_ID))
                .thenReturn(Optional.of(car));
    }

    @Test
    void contextLoads() {

        assertThat(carService).isNotNull();
    }

    /*@Test
    void shouldAuthorize() throws Exception {

        MvcResult result = this.mockMvc.perform(post("/api/v1/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "  \"password\": \"test\",\n" +
                "  \"username\": \"goose\"\n" +
                "}"))
                .andExpect(status().isOk())
        .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        jwt = "Bearer_" + mapper.readTree(result
                .getResponse()
                .getContentAsString())
            .get("token").asText();
    }

    @Test
    void shouldReturnCarMock() throws Exception {

        this.mockMvc.perform(get("/api/v1/car/4").header(HttpHeaders.AUTHORIZATION, jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string((containsString("Jetta"))));
    }*/

    @Test
    void shouldReturnCar() {

        assertThat(carService.getCar(4)).isNotNull();
    }

    @Test
    void shouldReturnCarFromCarRepository() {

        assertThat(carRepository.findById(CAR_ID).get().getBrand())
                .isEqualTo(CAR_BRAND);
    }
}
