package com.test.autobusiness.services;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.repositories.CarRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class CarService {

    private final int pageSize = 3;
    @Autowired
    CarRepository carRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addCar(Car car) {
        carRepository.save(car);
    }

    public Car getCar(long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCars(int page) {
        return carRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public List<Car> getCars(int page, String field, String order) {

        Pageable sort;
        if (order.equals("descending")) {
            sort = PageRequest.of(page, pageSize, Sort.by(field).descending());
        } else {
            sort = PageRequest.of(page, pageSize, Sort.by(field));
        }
        return carRepository.findAll(sort).getContent();
    }

    @Transactional
    public List<Car> getFilteredCars(String brand, int page) {
        Filter filter = entityManager.unwrap(Session.class).enableFilter("filterByMileAge");
//        filter.setParameter("brand", brand);
        filter.setParameter("minMileAge", 3000);
        filter.setParameter("maxMileAge", 20000);
        filter.setParameter("mile_age", 0);

        Filter filter2 = entityManager.unwrap(Session.class).enableFilter("filterByBrand");
        filter2.setParameter("brand", brand);

        List<Car> result = carRepository.findAll(PageRequest.of(page, pageSize)).getContent();
        entityManager.unwrap(Session.class).disableFilter("filterByMileAge");
        entityManager.unwrap(Session.class).disableFilter("filterByBrand");



        return result;
    }
}
