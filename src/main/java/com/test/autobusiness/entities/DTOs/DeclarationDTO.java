package com.test.autobusiness.entities.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.autobusiness.entities.Details;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;

@Data
public class DeclarationDTO {

    @Value
    public static class DeclarationRequest {

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
        CarDTO.CarRequest carRequest;
        HashSet<Details> details;
    }

    @Value
    public static class DeclarationResponse {

        @NotBlank
        String phoneNumber;
        String description;

        @NotBlank
        String vendorName;

        @NotBlank
        String place;

        @NotNull
        Date date;
        CarDTO.CarResponseForDeclaration carResponseForDeclaration;
        HashSet<Details> details;
    }

    @Value
    public static class DeclarationResponseForPage {
        long id;
    }
}
