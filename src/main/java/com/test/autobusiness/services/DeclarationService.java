package com.test.autobusiness.services;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.repositories.DeclarationRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

        return declarationRepository.findById(id).orElseThrow(
                () ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no declaration with id: " + id));
    }

    public void addDeclaration(Declaration declaration) {
        declaration.getCars().forEach(carService::checkUniqueDetails);
        declarationRepository.save(declaration);
    }
}
