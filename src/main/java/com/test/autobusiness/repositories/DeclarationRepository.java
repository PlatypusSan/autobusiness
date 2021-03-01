package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Declaration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationRepository extends CrudRepository<Declaration, Long> {
    //Declaration findById(long id);
}
