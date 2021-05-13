package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.controllers.DeclarationController;
import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.dto.declaration.DeclarationRequest;
import com.test.autobusiness.entities.dto.declaration.DeclarationResponse;
import com.test.autobusiness.entities.dto.declaration.DeclarationUpdate;
import org.mapstruct.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface DeclarationMapper {

    @Mapping(source = "cars", target = "carResponseForDeclarationList")
    DeclarationResponse declarationToDeclarationResponse(Declaration declaration);

    @Mapping(source = "carRequestList", target = "cars")
    Declaration declarationRequestToDeclaration(DeclarationRequest declarationRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "carRequestList", target = "cars")
    void updateDeclarationFromUpdate(DeclarationUpdate declarationUpdate,
                                     @MappingTarget Declaration declaration);

    @AfterMapping
    default void addLinks(@MappingTarget DeclarationResponse declarationResponse) {

        addLinksToDeclaration(declarationResponse, declarationResponse.getId());
    }

    default <T extends RepresentationModel> void addLinksToDeclaration(T declarationResponse, long id) {
        declarationResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DeclarationController.class).getDeclaration(id))
                .withSelfRel());
        declarationResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DeclarationController.class).deleteDeclaration(id))
                .withRel("delete"));
        declarationResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DeclarationController.class).updateDeclaration(null))
                .slash(id)
                .withRel("update"));
    }
}
