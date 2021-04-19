package com.test.autobusiness.entities.dto.declarationdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.autobusiness.entities.dto.cardto.CarRequest;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    Date date;

    List<CarRequest> carRequestList;

}