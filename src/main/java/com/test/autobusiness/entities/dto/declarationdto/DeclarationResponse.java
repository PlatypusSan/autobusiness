package com.test.autobusiness.entities.dto.declarationdto;

import com.test.autobusiness.entities.dto.cardto.CarResponseForDeclaration;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class DeclarationResponse {

    long id;

    String phoneNumber;

    String description;

    String vendorName;

    String place;

    Date date;

    List<CarResponseForDeclaration> carResponseForDeclarationList;

}