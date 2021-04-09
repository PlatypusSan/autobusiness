package com.test.autobusiness.services;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.Details;
import com.test.autobusiness.entities.dto.cardto.CarUpdate;
import com.test.autobusiness.entities.dto.directorydto.VendorDTO;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.entities.filters.CarRepresentation;
import com.test.autobusiness.entities.mappers.CarMapper;
import com.test.autobusiness.entities.mappers.DirectoryMapper;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.repositories.DetailsRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CarService {

    private final int pageSize = 3;

    private final CarRepository carRepository;

    private final DirectoryMapper directoryMapper;

    private final DetailsRepository detailsRepository;

    private final CarMapper carMapper;

    public CarService(CarRepository carRepository,
                      DirectoryMapper directoryMapper,
                      DetailsRepository detailsRepository,
                      CarMapper carMapper) {
        this.carRepository = carRepository;
        this.directoryMapper = directoryMapper;
        this.detailsRepository = detailsRepository;
        this.carMapper = carMapper;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Car> getCarsByVendorAndDriveUnit(String vendor, String driveUnit) {
        return carRepository.findCarByDriveUnitAndDeclarationVendorName(driveUnit, vendor);
    }

    public List<VendorDTO> getCarsByVendor() {
        return directoryMapper.dirElementToVendorDTOList(carRepository.getAllDirectories());
    }

    @Transactional
    public void addCar(Car car) {
        checkUniqueDetails(car);
        carRepository.save(car);
    }

    public void checkUniqueDetails(Car car) {
        if (car.getDetails() == null) return;
        List<Details> details = new ArrayList<>(car.getDetails());
        for (int i = 0; i < car.getDetails().size(); i++) {
            if (details.get(i).getId() == 0) {
                Details tempDetail = detailsRepository.findDetailsByDetailNameAndDetailType(details.get(i).getDetailName(),
                        details.get(i).getDetailType());

                if (tempDetail != null) {
                    details.set(i, tempDetail);
                }
            }
        }
        car.setDetails(new HashSet<Details>(details));
    }

    public Car getCar(long id) {

        return carRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no car with id: " + id));
    }

    public void updateCar(CarUpdate carUpdate) {

        Car car = carRepository.findById(carUpdate.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no car with id: " + carUpdate.getId())
        );
        carMapper.updateCarFromUpdate(carUpdate, car);
        carRepository.save(car);
    }

    @Transactional
    public List<Car> getFilteredCars(CarRepresentation carRep) {

        Pageable pageConfig;

        if (carRep.getSortingField() != null) {
            if (carRep.getSortingOrder().equals("descending")) {
                pageConfig = PageRequest.of(carRep.getPage(), pageSize, Sort.by(carRep.getSortingField()).descending());
            } else {
                pageConfig = PageRequest.of(carRep.getPage(), pageSize, Sort.by(carRep.getSortingField()));
            }
        } else {
            pageConfig = PageRequest.of(carRep.getPage(), pageSize);
        }

        List<Car> result;
        if (carRep.getCarFilter() != null) {
            List<Filter> filters = buildFilterList(carRep.getCarFilter());
            result = carRepository.findAll(pageConfig).getContent();
            disableCarFilters(filters);
        } else {
            result = carRepository.findAll(pageConfig).getContent();
        }

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

            filters.add(tempFilter);
        }

        if (carFilter.getMaxMileAge() != 0) {
            tempFilter = entityManager.unwrap(Session.class).enableFilter("filterByMileAge");
            tempFilter.setParameter("maxMileAge", carFilter.getMaxMileAge());
            tempFilter.setParameter("mile_age", 0);

            filters.add(tempFilter);
        }

        return filters;
    }

    private void disableCarFilters(List<Filter> filters) {
        for (Filter filter : filters) {
            entityManager.unwrap(Session.class).disableFilter(filter.getName());
        }
    }


    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }
}
