package com.test.autobusiness.entities.dto.declaration;

import com.test.autobusiness.entities.dto.car.CarResponseForDeclaration;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Data
public class DeclarationResponse extends RepresentationModel<DeclarationResponse> {

    long id;

    String phoneNumber;

    String description;

    String vendorName;

    String place;

    Date date;

    List<CarResponseForDeclaration> carResponseForDeclarationList;

}