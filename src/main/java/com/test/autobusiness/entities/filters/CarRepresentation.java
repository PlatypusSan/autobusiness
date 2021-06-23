package com.test.autobusiness.entities.filters;

import com.test.autobusiness.dto.CarFilterDTO;
import com.test.autobusiness.entities.enums.SortingField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
public class CarRepresentation {

    @Min(0)
    int page;

    @Min(0)
    @Max(50)
    int pageSize;
    String currency;
    SortingField sortingField;
    Sort.Direction sortingOrder;
    CarFilterDTO carFilterDTO;
}
