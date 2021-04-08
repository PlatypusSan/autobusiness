package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationRequest;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationResponse;
import com.test.autobusiness.entities.mappers.DeclarationMapper;
import com.test.autobusiness.services.DeclarationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class DeclarationController {

    private final DeclarationService declarationService;

    private final DeclarationMapper declarationMapper;

    public DeclarationController(DeclarationService declarationService, DeclarationMapper declarationMapper) {
        this.declarationService = declarationService;
        this.declarationMapper = declarationMapper;
    }

    @GetMapping(path = "/declaration/{id}")
    public DeclarationResponse getDeclaration(@PathVariable long id) {

        return declarationMapper.declarationToDeclarationResponse(declarationService.getDeclaration(id));
    }

    @PostMapping(path = "/declaration")
    public DeclarationResponse addDeclaration(@Valid @RequestBody DeclarationRequest declarationRequest) {
        Declaration declaration = declarationMapper.declarationRequestToDeclaration(declarationRequest);
        declarationService.addDeclaration(declaration);
        return declarationMapper.declarationToDeclarationResponse(declaration);
    }
}
