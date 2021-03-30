package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationRequest;
import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationResponse;
import com.test.autobusiness.entities.Declaration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeclarationMapper {

    DeclarationMapper INSTANCE = Mappers.getMapper(DeclarationMapper.class);

    @Mapping(source = "car", target = "carResponseForDeclaration")
    DeclarationResponse declarationToDeclarationResponse(Declaration declaration);

    @Mapping(source = "carRequest", target = "car")
    Declaration declarationRequestToDeclaration(DeclarationRequest declarationRequest);
}
