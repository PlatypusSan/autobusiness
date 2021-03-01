package com.test.autobusiness.services;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.repositories.DeclarationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeclarationService {

    @Autowired
    DeclarationRepository declarationRepository;

    public Declaration getDeclaration(long id) {
        return declarationRepository.findById(id).get();
    }

    public void addDeclaration(Declaration declaration) {
        declarationRepository.save(declaration);
    }
}
