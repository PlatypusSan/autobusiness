package com.test.autobusiness.services;

import com.test.autobusiness.dto.declaration.DeclarationUpdate;
import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.mappers.DeclarationMapper;
import com.test.autobusiness.repositories.DeclarationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeclarationService {

    private final DeclarationRepository declarationRepository;
    private final CarService carService;
    private final DeclarationMapper declarationMapper;

    public Declaration getDeclaration(long id) {

        return declarationRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There is no declaration with id: " + id));
    }

    @Transactional
    public void addDeclaration(Declaration declaration) {
        declaration.getCars().forEach(carService::checkUniqueDetails);
        declarationRepository.save(declaration);
    }

    @Transactional
    public Declaration updateDeclaration(DeclarationUpdate declarationUpdate) {

        Declaration declaration = declarationRepository.findById(declarationUpdate.getId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There is no declaration with id: " + declarationUpdate.getId()));

        declarationMapper.updateDeclarationFromUpdate(declarationUpdate, declaration);
        declarationRepository.save(declaration);
        return declaration;
    }

    public void deleteDeclaration(long id) {
        declarationRepository.deleteById(id);
    }
}
