package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.DataObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends CrudRepository<DataObject, Long> {

    Optional<DataObject> findFirstByDigest(byte[] digest);
}
