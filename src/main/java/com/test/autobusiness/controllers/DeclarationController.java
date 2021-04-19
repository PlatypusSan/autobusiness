package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationRequest;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationResponse;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationUpdate;
import com.test.autobusiness.entities.mappers.DeclarationMapper;
import com.test.autobusiness.services.DeclarationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addDeclaration(@Valid @RequestBody DeclarationRequest declarationRequest) {

        declarationService.addDeclaration(declarationMapper.declarationRequestToDeclaration(declarationRequest));
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @PutMapping(path = "/declaration")
    public ResponseEntity<String> updateDeclaration(@RequestBody DeclarationUpdate declarationUpdate) {

        declarationService.updateDeclaration(declarationUpdate);
        return ResponseEntity.ok("Updated successfully: " + declarationUpdate.getId());
    }

    @DeleteMapping(path = "/admin/declaration/{id}")
    public ResponseEntity<String> deleteDeclaration(@PathVariable long id) {

        declarationService.deleteDeclaration(id);
        return ResponseEntity.ok("Deleted successfully: " + id);
    }
}
