package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.services.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeclarationController {

    @Autowired
    DeclarationService declarationService;

    @GetMapping(path = "/declaration")
    public Declaration getDeclaration(@RequestParam long id) {
        return declarationService.getDeclaration(id);
    }

    @PostMapping(path = "/declaration")
    public void addDeclaration(@RequestBody Declaration declaration) {
        declarationService.addDeclaration(declaration);
    }
}
