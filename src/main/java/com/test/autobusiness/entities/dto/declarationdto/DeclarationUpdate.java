package com.test.autobusiness.entities.dto.declarationdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.autobusiness.entities.dto.cardto.CarRequest;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date date;

    List<CarRequest> carRequestList;
}
