package com.test.autobusiness.services;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.repositories.DeclarationRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class DeclarationService {

    private final DeclarationRepository declarationRepository;

    private final DetailsRepository detailsRepository;

    public DeclarationService(DeclarationRepository declarationRepository, DetailsRepository detailsRepository) {
        this.declarationRepository = declarationRepository;
        this.detailsRepository = detailsRepository;
    }

    public Declaration getDeclaration(long id) {
        return declarationRepository.findById(id).get();
    }

    public void addDeclaration(Declaration declaration) {
        declarationRepository.save(declaration);
    }
}
