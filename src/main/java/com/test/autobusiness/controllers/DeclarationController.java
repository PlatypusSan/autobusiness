package com.test.autobusiness.controllers;

import com.test.autobusiness.dto.declaration.DeclarationRequest;
import com.test.autobusiness.dto.declaration.DeclarationResponse;
import com.test.autobusiness.dto.declaration.DeclarationUpdate;
import com.test.autobusiness.mappers.DeclarationMapper;
import com.test.autobusiness.services.DeclarationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/declaration")
@RequiredArgsConstructor
public class DeclarationController {

    private final DeclarationService declarationService;
    private final DeclarationMapper declarationMapper;

    @GetMapping(path = "/{id}")
    public DeclarationResponse getDeclaration(@PathVariable long id) {

        return declarationService.getDeclarationResponse(id);
    }

    @PostMapping()
    public DeclarationResponse addDeclaration(@Valid @RequestBody DeclarationRequest declarationRequest) {

        return declarationService.addDeclaration(declarationRequest);
    }

    @PutMapping()
    public DeclarationResponse updateDeclaration(@RequestBody DeclarationUpdate declarationUpdate) {

        return declarationService.updateDeclaration(declarationUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDeclaration(@PathVariable long id) {

        declarationService.deleteDeclaration(id);
    }
}
