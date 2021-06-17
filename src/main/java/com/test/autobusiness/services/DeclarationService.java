package com.test.autobusiness.services;

import com.test.autobusiness.dto.declaration.DeclarationRequest;
import com.test.autobusiness.dto.declaration.DeclarationResponse;
import com.test.autobusiness.dto.declaration.DeclarationUpdate;
import com.test.autobusiness.entities.Declaration;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public interface DeclarationService {

    Declaration getDeclaration(long id);

    DeclarationResponse getDeclarationResponse(long id);

    @Transactional
    Declaration saveDeclaration(Declaration declaration);

    DeclarationResponse addDeclaration(DeclarationRequest declarationRequest);

    @Transactional
    DeclarationResponse updateDeclaration(DeclarationUpdate declarationUpdate);

    void deleteDeclaration(long id);
}
