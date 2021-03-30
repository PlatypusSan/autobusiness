package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationRequest;
import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationResponse;
import com.test.autobusiness.entities.mappers.DeclarationMapper;
import com.test.autobusiness.services.DeclarationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DeclarationController {

    private final DeclarationService declarationService;

    private final DeclarationMapper declarationMapper;

    public DeclarationController(DeclarationService declarationService, DeclarationMapper declarationMapper) {
        this.declarationService = declarationService;
        this.declarationMapper = declarationMapper;
    }

    @GetMapping(path = "/declaration")
    public DeclarationResponse getDeclaration(@RequestParam long id) {

        return declarationMapper.declarationToDeclarationResponse(declarationService.getDeclaration(id));
    }

    @PostMapping(path = "/declaration")
    public void addDeclaration(@Valid @RequestBody DeclarationRequest declarationRequest) {
        declarationService.addDeclaration(declarationMapper.declarationRequestToDeclaration(declarationRequest));
    }
}
