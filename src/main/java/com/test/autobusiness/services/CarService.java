package com.test.autobusiness.services;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.Details;
import com.test.autobusiness.entities.dto.cardto.CarResponse;
import com.test.autobusiness.entities.dto.cardto.CarUpdate;
import com.test.autobusiness.entities.dto.directorydto.VendorDTO;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.entities.filters.CarFilterDTO;
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
import java.util.*;

@Service
public class CarService {

    private final CarRepository carRepository;

    private final DirectoryMapper directoryMapper;

    private final DetailsRepository detailsRepository;

    private final CarMapper carMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public CarService(CarRepository carRepository,
                      DirectoryMapper directoryMapper,
                      DetailsRepository detailsRepository,
                      CarMapper carMapper) {
        this.carRepository = carRepository;
        this.directoryMapper = directoryMapper;
        this.detailsRepository = detailsRepository;
        this.carMapper = carMapper;
    }



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

        return carRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There is no car with id: " + id));
    }

    @Transactional
    public void deleteCar(long id) {

        Car car = carRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There is no car with id: " + id)
                );
        car.removeDetails();
        carRepository.save(car);
        carRepository.deleteById(id);
    }

    @Transactional
    public CarResponse updateCar(CarUpdate carUpdate) {

        return carRepository.findById(carUpdate.getId())
                .map(car -> carMapper.updateCarFromUpdate(carUpdate, car))
                .map(carRepository::save)
                .map(carMapper::carToCarResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "There is no car with id: " + carUpdate.getId())
                );
    }


    public List<Car> getFilteredCars(CarRepresentation carRep) {

        Pageable pageConfig;

        int pageSize = 3;
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
        if (carRep.getCarFilterDTO() != null) {
            CarFilter carFilter = new CarFilter();
            carMapper.updateCarFilterFromDTO(carRep.getCarFilterDTO(), carFilter);
            result = carRepository.findAll(carRepository.getFilterSpecification(carFilter), pageConfig).getContent();
        } else {
            result = carRepository.findAll(pageConfig).getContent();
        }

        return result;
    }
}
