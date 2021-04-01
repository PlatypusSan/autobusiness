package com.test.autobusiness.entities.dto.declarationdto;

import com.test.autobusiness.entities.dto.cardto.CarResponseForDeclaration;
import com.test.autobusiness.entities.dto.detailsdto.DetailsResponse;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;

@Value
public class DeclarationResponse {

    @NotBlank
    String phoneNumber;
    String description;

    @NotBlank
    String vendorName;

    @NotBlank
    String place;

    @NotNull
    Date date;
    CarResponseForDeclaration carResponseForDeclaration;
    HashSet<DetailsResponse> details;
}