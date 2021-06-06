package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Dealership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepository extends CrudRepository<Dealership, Long> {
}
