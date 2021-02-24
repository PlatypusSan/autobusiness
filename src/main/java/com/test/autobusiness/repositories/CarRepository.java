package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    Car findById(long id);
    List<Car> findByBrandLike(String brand);
    List<Car> findByPrice(String brand);


}
