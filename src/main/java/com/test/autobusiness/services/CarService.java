package com.test.autobusiness.services;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.Details;
import com.test.autobusiness.entities.dto.car.CarResponse;
import com.test.autobusiness.entities.dto.car.CarUpdate;
import com.test.autobusiness.entities.dto.currency.CurrencyDTO;
import com.test.autobusiness.entities.dto.directory.VendorDTO;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.entities.mappers.CarMapper;
import com.test.autobusiness.entities.mappers.DirectoryMapper;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final DirectoryMapper directoryMapper;
    private final DetailsRepository detailsRepository;
    private final CarMapper carMapper;
    private final CurrencyService currencyServiceImpl;

    private Cache<Long, Car> cache = Caffeine.newBuilder()
            .expireAfterAccess(Duration.ofMinutes(4))
            .weigher((k, v) -> 5)
            .maximumWeight(10)
            .recordStats()
            .build();

    @Value("${sorting.default-field}")
    private String defaultSortingField;

    @Value("${sorting.default-direction}")
    private String defaultSortingDirection;

    private CurrencyDTO currency;


    public List<Car> getCarsByVendorAndDriveUnit(String vendor, String driveUnit) {
        return carRepository.findCarByDriveUnitAndDeclarationVendorName(driveUnit, vendor);
    }

    public List<VendorDTO> getCarsByVendor() {

        return directoryMapper.dirElementToVendorDTOList(carRepository.getAllDirectories());
    }

    @Transactional
    public void addCar(Car car) {
        checkUniqueDetails(car);
        carRepository.save(car);
    }

    public void checkUniqueDetails(Car car) {
        if (car.getDetails() == null) return;
        List<Details> details = new ArrayList<>(car.getDetails());
        for (int i = 0; i < car.getDetails().size(); i++) {
            if (details.get(i).getId() == 0) {
                Details tempDetail = detailsRepository.findDetailsByDetailNameAndDetailType(details.get(i).getDetailName(),
                        details.get(i).getDetailType());

                if (tempDetail != null) {
                    details.set(i, tempDetail);
                }
            }
        }
        car.setDetails(new HashSet<Details>(details));
    }

    @Transactional
    public Car getCar(long id) {

        Car car = cache.get(id, k -> {
            log.info("IN getCars - car with id: {} found", id);
            return carRepository.findById(id)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "There is no car with id: " + id));
        });
        return car;
    }

    @Transactional
    public void deleteCar(long id) {

        Car car = carRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There is no car with id: " + id)
                );
        cache.invalidate(car.getId());
        car.removeDetails();
        carRepository.save(car);
        carRepository.deleteById(id);
    }

    @Transactional
    public CarResponse updateCar(CarUpdate carUpdate) {

        return carRepository.findById(carUpdate.getId())
                .map(car -> {
                    cache.invalidate(car.getId());
                    return carMapper.updateCarFromUpdate(carUpdate, car);
                })
                .map(carRepository::save)
                .map(carMapper::carToCarResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "There is no car with id: " + carUpdate.getId())
                );
    }


    public List<CarResponse> getFilteredCars(CarRepresentation carRep) {

        /*try {
            JacksonMapper jacksonMapper = new JacksonMapper(carRepository);
            jacksonMapper.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return pickCurrency(carRep, filterAndSortCars(carRep));
    }

    private Pageable configurePage(CarRepresentation carRep) {

        return PageRequest.of(
                carRep.getPage(),
                carRep.getPageSize(),
                Sort.by(carRep.getSortingOrder() == null
                                ? Sort.Direction.valueOf(defaultSortingDirection)
                                : carRep.getSortingOrder(),
                        carRep.getSortingField() == null
                                ? defaultSortingField
                                : carRep.getSortingField().getSortingFieldName()));
    }

    private List<Car> filterAndSortCars(CarRepresentation carRep) {

        Pageable pageConfig = configurePage(carRep);

        List<Car> result;
        if (carRep.getCarFilterDTO() != null) {
            CarFilter carFilter = new CarFilter();
            carMapper.updateCarFilterFromDTO(carRep.getCarFilterDTO(), carFilter);
            result = carRepository.findAll(carRepository.getFilterSpecification(carFilter), pageConfig).getContent();
        } else {
            result = carRepository.findAll(pageConfig).getContent();
        }
        return result;
    }

    private int calculatePrice(int price, String valute) {
        return (int) Math.round(price
                * currency.getValutes().getProperties().get("EUR").getValue()
                * currency.getValutes().getProperties().get("EUR").getNominal()
                / currency.getValutes().getProperties().get(valute).getValue()
                / currency.getValutes().getProperties().get(valute).getNominal());

    }

    private List<CarResponse> pickCurrency(CarRepresentation carRep, List<Car> cars) {

        List<CarResponse> resultResponse = carMapper.carToCarResponseAsList(cars);

        if (carRep.getCurrency() != null) {

            currency = currencyServiceImpl.getExchangeRates();

            if (!currency.getValutes().getProperties().containsKey(carRep.getCurrency())) {
                throw new NoSuchElementException("No currency found with value: " + carRep.getCurrency());
            }

            resultResponse
                    .forEach(carResponse -> {
                        carResponse.setCurrency(carRep.getCurrency());
                        carResponse.setPrice(calculatePrice(carResponse.getPrice(), carRep.getCurrency()));
                    });
        }

        return resultResponse;
    }
}
