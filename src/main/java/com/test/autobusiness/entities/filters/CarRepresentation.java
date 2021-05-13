package com.test.autobusiness.entities.filters;

import com.test.autobusiness.entities.enums.SortingField;
import lombok.Value;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Value
public class CarRepresentation {

    CarFilterDTO carFilterDTO;

    @Min(0)
    int page;

    @Min(0)
    @Max(50)
    int pageSize;

    String currency;

    SortingField sortingField;

    Sort.Direction sortingOrder;
}
