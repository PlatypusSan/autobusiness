package com.test.autobusiness.entities.dto.declarationdto;

import com.test.autobusiness.entities.dto.cardto.CarResponseForDeclaration;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Value
public class DeclarationResponse {

    String phoneNumber;

    String description;

    String vendorName;

    String place;

    Date date;

    List<CarResponseForDeclaration> carResponseForDeclarationList;

}