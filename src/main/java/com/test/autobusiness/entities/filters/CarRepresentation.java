package com.test.autobusiness.entities.filters;

import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class CarRepresentation {

    CarFilterDTO carFilterDTO;

    @Min(0)
    int page;

    String currency;

    String sortingField;

    String sortingOrder;
}
