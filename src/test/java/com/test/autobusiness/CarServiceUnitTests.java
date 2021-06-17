package com.test.autobusiness;

import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.mappers.CarMapper;
import com.test.autobusiness.mappers.DirectoryMapper;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.services.CurrencyService;
import com.test.autobusiness.services.ExportService;
import com.test.autobusiness.services.impl.CarServiceImpl;
import com.test.autobusiness.utils.JacksonCarMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;

@SpringBootTest
@TestPropertySource("/application-test.yaml")
public class CarServiceUnitTests {

    private final JacksonCarMapper jacksonCarMapper;
    private final CurrencyService currencyServiceImpl;
    private CarService carService;

    @Value("${brand}")
    private String brand;

    @Value("${currency}")
    private String currency;

    @Value("${car-id}")
    private long carId;

    @Mock
    private CarRepository carRepository;

    @Mock
    private DetailsRepository detailsRepository;

    @Spy
    private DirectoryMapper directoryMapper;

    @Spy
    private CarMapper carMapper;

    @Spy
    private ExportService exportService;

    @Autowired
    public CarServiceUnitTests(JacksonCarMapper jacksonCarMapper, CurrencyService currencyServiceImpl) {
        this.jacksonCarMapper = jacksonCarMapper;
        this.currencyServiceImpl = currencyServiceImpl;
    }

    @BeforeEach
    public void setupTestsEnvironment() {

        carService = new CarServiceImpl(
                carRepository,
                directoryMapper,
                detailsRepository,
                carMapper,
                currencyServiceImpl,
                exportService
        );
    }

    @Test
    void givenCarService_whenGetCarServiceValue_then_CarServiceIsNotNull() {

        assertThat(carService).isNotNull();
    }

    @Test
    void givenCarAndMockedRepo_whenCarServiceReturnsCar_thenBrandIsEqualToGiven() {

        //given
        Car car = Car
                .builder()
                .brand(brand)
                .build();
        Mockito
                .when(carRepository.findById(carId))
                .thenReturn(Optional.of(car));
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);

        //when
        Car carResult = carService.getCar(carId);

        //then
        Mockito
                .verify(carRepository, atMostOnce()).findById(arg.capture());
        assertThat(carResult.getBrand()).isEqualTo(brand);
        assertEquals(carId, arg.getValue());
    }

    @Test
    void givenCarRepresentationAndCars_whenCarServiceReturnsCars_thenPricesAndCurrenciesAreCorrect() throws Exception {

        //given
        List<Car> carList = jacksonCarMapper.getCars();
        CarRepresentation carRepresentation = CarRepresentation
                .builder()
                .currency(currency)
                .build();

        //when
        List<CarResponse> carResponseList = carService.pickCurrency(carRepresentation, carList);

        //then
        carResponseList
                .forEach(carResponse -> {
                    assertEquals(carRepresentation.getCurrency(), carResponse.getCurrency());
                });
    }
}
