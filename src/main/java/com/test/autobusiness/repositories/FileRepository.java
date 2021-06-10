package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.DataObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<DataObject, Long> {
}
