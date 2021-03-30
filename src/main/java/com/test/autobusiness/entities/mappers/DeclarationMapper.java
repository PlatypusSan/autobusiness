package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationRequest;
import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationResponse;
import com.test.autobusiness.entities.Declaration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeclarationMapper {

    @Mapping(source = "car", target = "carResponseForDeclaration")
    DeclarationResponse declarationToDeclarationResponse(Declaration declaration);

    @Mapping(source = "carRequest", target = "car")
    Declaration declarationRequestToDeclaration(DeclarationRequest declarationRequest);
}
