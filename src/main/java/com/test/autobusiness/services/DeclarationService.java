package com.test.autobusiness.services;

import com.test.autobusiness.dto.declaration.DeclarationUpdate;
import com.test.autobusiness.entities.Declaration;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public interface DeclarationService {

    Declaration getDeclaration(long id);

    @Transactional
    void addDeclaration(Declaration declaration);

    @Transactional
    Declaration updateDeclaration(DeclarationUpdate declarationUpdate);

    void deleteDeclaration(long id);
}
