package com.test.autobusiness.services;

import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.Details;
import com.test.autobusiness.repositories.DeclarationRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class DeclarationService {

    @Autowired
    DeclarationRepository declarationRepository;

    @Autowired
    DetailsRepository detailsRepository;

    public Declaration getDeclaration(long id) {
        return declarationRepository.findById(id).get();
    }

    public void addDeclaration(Declaration declaration) {
        checkUniqueDetails(declaration);
        declarationRepository.save(declaration);
    }

    private void checkUniqueDetails(Declaration declaration) {
        List<Details> details = new ArrayList<>(declaration.getDetails());
        for (int i = 0; i < declaration.getDetails().size(); i++) {
            if (details.get(i).getId() == 0) {
                Details tempDetail = detailsRepository.findDetailsByDetailNameAndDetailType(details.get(i).getDetailName(),
                        details.get(i).getDetailType());

                if (tempDetail != null) {
                    details.set(i, tempDetail);
                }
            }
        }
        declaration.setDetails(new HashSet<Details>(details));
    }
}
