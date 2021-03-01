package com.test.autobusiness.services;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarFilter;
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
import java.util.ArrayList;
import java.util.List;

@Service
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
        return carRepository.findById(id).get();
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
    public List<Car> getFilteredCars(CarFilter carFilter, int page, String field, String order) {

        Pageable pageConfig;

        if (field != null) {
            if (order.equals("descending")) {
                pageConfig = PageRequest.of(page, pageSize, Sort.by(field).descending());
            } else {
                pageConfig = PageRequest.of(page, pageSize, Sort.by(field));
            }
        } else {
            pageConfig = PageRequest.of(page, pageSize);
        }

        List<Filter> filters = buildFilterList(carFilter);
        List<Car> result = carRepository.findAll(pageConfig).getContent();
        disableCarFilters(filters);

        return result;
    }

    private List<Filter> buildFilterList(CarFilter carFilter) {
        List<Filter> filters = new ArrayList<>();
        Filter tempFilter;

        if (carFilter.getBrand() != null) {
            tempFilter = entityManager.unwrap(Session.class).enableFilter("filterByBrand");
            tempFilter.setParameter("brand", carFilter.getBrand());
            filters.add(tempFilter);
        }

        if (carFilter.getModel() != null) {
            tempFilter = entityManager.unwrap(Session.class).enableFilter("filterByModel");
            tempFilter.setParameter("model", carFilter.getModel());
            filters.add(tempFilter);
        }

        if (carFilter.getDriveUnit() != null) {
            tempFilter = entityManager.unwrap(Session.class).enableFilter("filterByDriveUnit");
            tempFilter.setParameter("drive_unit", carFilter.getDriveUnit().toString());
            filters.add(tempFilter);
        }

        if (carFilter.getMinEngineVolume() != 0.0 || carFilter.getMaxEngineVolume() != 0.0) {
            tempFilter = entityManager.unwrap(Session.class).enableFilter("filterByEngineVolume");
            tempFilter.setParameter("minEngineVolume", carFilter.getMinEngineVolume());
            if (carFilter.getMaxEngineVolume() != 0.0) {
                tempFilter.setParameter("maxEngineVolume", carFilter.getMaxEngineVolume());
            } else {
                tempFilter.setParameter("maxEngineVolume", 8.5);
            }
                tempFilter.setParameter("engine_volume", 0.0);
            ;
            filters.add(tempFilter);
        }

        if (carFilter.getMaxMileAge() != 0) {
            tempFilter = entityManager.unwrap(Session.class).enableFilter("filterByMileAge");
            tempFilter.setParameter("maxMileAge", carFilter.getMaxMileAge());
            tempFilter.setParameter("mile_age", 0);
            ;
            filters.add(tempFilter);
        }

        return filters;
    }

    private void disableCarFilters(List<Filter> filters) {
        for (Filter filter : filters) {
            entityManager.unwrap(Session.class).disableFilter(filter.getName());
        }
    }

    /*@Transactional
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
    }*/
}
