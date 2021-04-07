package com.test.autobusiness.entities.filters;

import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class CarRepresentation {

    CarFilter carFilter;

    @Min(0)
    int page;

    String sortingField;

    String sortingOrder;
}
