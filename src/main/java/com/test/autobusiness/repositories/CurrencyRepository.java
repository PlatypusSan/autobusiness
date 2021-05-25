package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.dto.currency.CurrencyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<CurrencyDTO, String> {

}
