package com.test.autobusiness.dto.declaration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.autobusiness.dto.car.CarRequest;
import com.test.autobusiness.entities.DateFormat;
import lombok.Value;

import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Value
public class DeclarationUpdate {

    @Min(0)
    long id;

    String phoneNumber;
    String description;
    String vendorName;
    String place;

    @JsonFormat(pattern = DateFormat.DATE)
    Date date;

    List<CarRequest> carRequestList;
}
