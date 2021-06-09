package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.dto.declaration.DeclarationRequest;
import com.test.autobusiness.entities.dto.declaration.DeclarationResponse;
import com.test.autobusiness.entities.dto.declaration.DeclarationUpdate;
import com.test.autobusiness.entities.mappers.DeclarationMapper;
import com.test.autobusiness.services.DeclarationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/declaration")
public class DeclarationController {

    private final DeclarationService declarationService;
    private final DeclarationMapper declarationMapper;

    public DeclarationController(DeclarationService declarationService, DeclarationMapper declarationMapper) {
        this.declarationService = declarationService;
        this.declarationMapper = declarationMapper;
    }

    @GetMapping(path = "/{id}")
    public DeclarationResponse getDeclaration(@PathVariable long id) {

        return declarationMapper.declarationToDeclarationResponse(declarationService.getDeclaration(id));
    }

    @PostMapping()
    public DeclarationResponse addDeclaration(@Valid @RequestBody DeclarationRequest declarationRequest) {

        Declaration declaration = declarationMapper.declarationRequestToDeclaration(declarationRequest);
        declarationService.addDeclaration(declaration);
        return declarationMapper.declarationToDeclarationResponse(declaration);
    }

    @PutMapping()
    public DeclarationResponse updateDeclaration(@RequestBody DeclarationUpdate declarationUpdate) {

        Declaration declaration = declarationService.updateDeclaration(declarationUpdate);
        return declarationMapper.declarationToDeclarationResponse(declaration);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteDeclaration(@PathVariable long id) {

        declarationService.deleteDeclaration(id);
        return ResponseEntity.ok().build();
    }
}
