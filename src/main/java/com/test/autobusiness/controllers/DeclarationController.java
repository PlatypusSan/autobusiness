package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationRequest;
import com.test.autobusiness.entities.DTOs.DeclarationDTOs.DeclarationResponse;
import com.test.autobusiness.entities.mappers.DeclarationMapper;
import com.test.autobusiness.services.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DeclarationController {

    @Autowired
    DeclarationService declarationService;

    @GetMapping(path = "/declaration")
    public DeclarationResponse getDeclaration(@RequestParam long id) {

        return DeclarationMapper.INSTANCE.declarationToDeclarationResponse(declarationService.getDeclaration(id));
    }

    @PostMapping(path = "/declaration")
    public void addDeclaration(@Valid @RequestBody DeclarationRequest declarationRequest) {
        declarationService.addDeclaration(DeclarationMapper.INSTANCE.declarationRequestToDeclaration(declarationRequest));
    }
}
