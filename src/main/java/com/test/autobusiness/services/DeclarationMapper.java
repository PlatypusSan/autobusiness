package com.test.autobusiness.services;

import com.test.autobusiness.entities.DTOs.DeclarationDTO;
import com.test.autobusiness.entities.Declaration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeclarationMapper {

    DeclarationMapper INSTANCE = Mappers.getMapper(DeclarationMapper.class);

    @Mapping(source = "car", target = "carResponseForDeclaration")
    DeclarationDTO.DeclarationResponse declarationToDeclarationResponse(Declaration declaration);

    @Mapping(source = "carRequest", target = "car")
    Declaration declarationRequestToDeclaration(DeclarationDTO.DeclarationRequest declarationRequest);
}
