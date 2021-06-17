package com.test.autobusiness.services.impl;

import com.test.autobusiness.dto.declaration.DeclarationRequest;
import com.test.autobusiness.dto.declaration.DeclarationResponse;
import com.test.autobusiness.dto.declaration.DeclarationUpdate;
import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.mappers.DeclarationMapper;
import com.test.autobusiness.repositories.DeclarationRepository;
import com.test.autobusiness.services.CarService;
import com.test.autobusiness.services.DeclarationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeclarationServiceImpl implements DeclarationService {

    private final DeclarationRepository declarationRepository;
    private final CarService carService;
    private final DeclarationMapper declarationMapper;

    public Declaration getDeclaration(long id) {

        return declarationRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There is no declaration with id: " + id));
    }

    @Override
    public DeclarationResponse getDeclarationResponse(long id) {
        return declarationMapper.declarationToDeclarationResponse(getDeclaration(id));
    }

    @Transactional
    public Declaration saveDeclaration(Declaration declaration) {
        declaration.getCars()
                .forEach(carService::checkUniqueDetails);
        return declarationRepository.save(declaration);
    }

    @Override
    public DeclarationResponse addDeclaration(DeclarationRequest declarationRequest) {

        Declaration declaration = declarationMapper.declarationRequestToDeclaration(declarationRequest);
        return declarationMapper.declarationToDeclarationResponse(saveDeclaration(declaration));
    }

    @Transactional
    public DeclarationResponse updateDeclaration(DeclarationUpdate declarationUpdate) {

        Declaration declaration = declarationRepository.findById(declarationUpdate.getId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There is no declaration with id: " + declarationUpdate.getId()));

        declarationMapper.updateDeclarationFromUpdate(declarationUpdate, declaration);
        declarationRepository.save(declaration);
        return declarationMapper.declarationToDeclarationResponse(declaration);
    }

    public void deleteDeclaration(long id) {
        declarationRepository.deleteById(id);
    }
}