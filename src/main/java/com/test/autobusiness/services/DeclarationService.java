package com.test.autobusiness.services;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.dto.declarationdto.DeclarationUpdate;
import com.test.autobusiness.entities.mappers.DeclarationMapper;
import com.test.autobusiness.repositories.DeclarationRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
public class DeclarationService {

    private final DeclarationRepository declarationRepository;

    private final DetailsRepository detailsRepository;

    private final CarService carService;

    private final DeclarationMapper declarationMapper;

    public DeclarationService(DeclarationRepository declarationRepository,
                              DetailsRepository detailsRepository,
                              CarService carService,
                              DeclarationMapper declarationMapper) {
        this.declarationRepository = declarationRepository;
        this.detailsRepository = detailsRepository;
        this.carService = carService;
        this.declarationMapper = declarationMapper;
    }

    public Declaration getDeclaration(long id) {

        return declarationRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no declaration with id: " + id));
    }

    @Transactional
    public void addDeclaration(Declaration declaration) {
        declaration.getCars().forEach(carService::checkUniqueDetails);
        declarationRepository.save(declaration);
    }

    @Transactional
    public void updateDeclaration(DeclarationUpdate declarationUpdate) {

        Declaration declaration = declarationRepository.findById(declarationUpdate.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no declaration with id: " + declarationUpdate.getId()));

        declarationMapper.updateDeclarationFromUpdate(declarationUpdate, declaration);
        declarationRepository.save(declaration);
    }

    public void deleteDeclaration(long id) {
        declarationRepository.deleteById(id);
    }
}
