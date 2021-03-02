package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Long> {
    boolean existsDetailsByDetailNameAndDetailType(String detailName, String detailType);
    Details findDetailsByDetailNameAndDetailType(String detailName, String detailType);
    HashSet<Details> findAll();
}
