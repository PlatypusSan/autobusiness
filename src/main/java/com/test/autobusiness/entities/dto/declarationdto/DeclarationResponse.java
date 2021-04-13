package com.test.autobusiness.entities.dto.declarationdto;

import com.test.autobusiness.entities.dto.cardto.CarResponseForDeclaration;
import lombok.Data;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;
import java.util.Set;

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