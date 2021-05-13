package com.test.autobusiness.entities.dto.declaration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.autobusiness.entities.DateFormat;
import com.test.autobusiness.entities.dto.car.CarRequest;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Value
public class DeclarationRequest {

    @NotBlank
    String phoneNumber;
    String description;

    @NotBlank
    String vendorName;

    @NotBlank
    String place;

    @JsonFormat(pattern = DateFormat.DATE)
    @NotNull
    Date date;

    List<CarRequest> carRequestList;

}