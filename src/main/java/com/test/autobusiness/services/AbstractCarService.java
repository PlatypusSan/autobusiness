package com.test.autobusiness.services;

import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.dto.currency.CurrencyDTO;
import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.mappers.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public abstract class AbstractCarService {

    @Value("${sorting.default-field}")
    private String defaultSortingField;

    @Value("${sorting.default-direction}")
    private String defaultSortingDirection;
    private CurrencyDTO currency;
    private final CurrencyService currencyServiceImpl;
    private final CarMapper carMapper;

    abstract public List<Car> getFilteredCars(CarRepresentation carRep);

    protected Pageable configurePage(CarRepresentation carRep) {

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

    protected int calculatePrice(int price, String valute) {
        return (int) Math.round(price
                * currency.getValutes().getProperties().get("EUR").getValue()
                * currency.getValutes().getProperties().get("EUR").getNominal()
                / currency.getValutes().getProperties().get(valute).getValue()
                / currency.getValutes().getProperties().get(valute).getNominal());

    }

    public List<CarResponse> pickCurrency(CarRepresentation carRep, List<Car> cars) {

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
