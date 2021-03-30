package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    List<Car> findByBrandLike(String brand);

    List<Car> findByPrice(String brand);

}
