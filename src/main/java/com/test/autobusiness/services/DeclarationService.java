package com.test.autobusiness.services;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.repositories.DeclarationRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class DeclarationService {

    private final DeclarationRepository declarationRepository;

    private final DetailsRepository detailsRepository;

    private final CarService carService;

    public DeclarationService(DeclarationRepository declarationRepository,
                              DetailsRepository detailsRepository,
                              CarService carService) {
        this.declarationRepository = declarationRepository;
        this.detailsRepository = detailsRepository;
        this.carService = carService;
    }

    public Declaration getDeclaration(long id) {
        return declarationRepository.findById(id).get();
    }

    public void addDeclaration(Declaration declaration) {
        declaration.getCars().forEach(carService::checkUniqueDetails);
        declarationRepository.save(declaration);
    }
}
