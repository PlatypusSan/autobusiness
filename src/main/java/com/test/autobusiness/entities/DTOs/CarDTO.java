package com.test.autobusiness.entities.DTOs;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarDTO {
    @Value public static class CarRequest{
        DeclarationDTO.DeclarationRequest declarationRequest;

        @NotBlank(message = "brand is mandatory")
        String brand;

        @NotBlank
        String model;

        @NotBlank
        String generation;

        @NotBlank
        String body;

        @NotNull
        String driveUnit;

        @NotNull
        String transmission;

        @NotNull
        String engineType;

        @Min(0)
        @Max(9)
        double engineVolume;

        @Min(1900)
        @Max(2021)
        int age;

        @Min(0)
        @Max(1000000)
        int mileAge;

        @Min(0)
        int price;
    }

    @Value public static class CarResponse{
        DeclarationDTO.DeclarationResponseForPage declarationResponseForPage;

        @NotBlank(message = "brand is mandatory")
        String brand;

        @NotBlank
        String model;

        @NotBlank
        String generation;

        @NotBlank
        String body;

        @NotNull
        String driveUnit;

        @NotNull
        String transmission;

        @NotNull
        String engineType;

        @Min(0)
        @Max(9)
        double engineVolume;

        @Min(1900)
        @Max(2021)
        int age;

        @Min(0)
        @Max(1000000)
        int mileAge;

        @Min(0)
        int price;
    }

    @Value public static class CarResponseForDeclaration{

        @NotBlank(message = "brand is mandatory")
        String brand;

        @NotBlank
        String model;

        @NotBlank
        String generation;

        @NotBlank
        String body;

        @NotNull
        String driveUnit;

        @NotNull
        String transmission;

        @NotNull
        String engineType;

        @Min(0)
        @Max(9)
        double engineVolume;

        @Min(1900)
        @Max(2021)
        int age;

        @Min(0)
        @Max(1000000)
        int mileAge;

        @Min(0)
        int price;
    }

}
