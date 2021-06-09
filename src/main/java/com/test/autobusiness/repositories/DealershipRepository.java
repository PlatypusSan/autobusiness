package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Dealership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealershipRepository extends CrudRepository<Dealership, Long> {

    List<Dealership> findAll();
}
