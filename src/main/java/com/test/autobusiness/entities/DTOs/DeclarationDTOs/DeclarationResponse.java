package com.test.autobusiness.entities.DTOs.DeclarationDTOs;

import com.test.autobusiness.entities.DTOs.CarDTOs.CarResponseForDeclaration;
import com.test.autobusiness.entities.DTOs.DetailsDTOs.DetailsResponse;
import com.test.autobusiness.entities.Details;
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