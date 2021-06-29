package com.test.autobusiness.repositories.impl;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarFilter;
import com.test.autobusiness.repositories.CarFilterRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class CarFilterRepositoryImpl implements CarFilterRepository {

    @Override
    public Specification<Car> brandContains(String brand) {
        return (car, cq, cb) -> cb.like(car.get("brand"), "%" + brand + "%");
    }

    @Override
    public Specification<Car> modelContains(String model) {
        return (car, cq, cb) -> cb.like(car.get("model"), "%" + model + "%");
    }

    @Override
    public Specification<Car> generationContains(String generation) {
        return (car, cq, cb) -> cb.like(car.get("generation"), "%" + generation + "%");
    }

    @Override
    public Specification<Car> bodyContains(String body) {
        return (car, cq, cb) -> cb.like(car.get("body"), "%" + body + "%");
    }

    @Override
    public Specification<Car> driveUnitContains(String driveUnit) {
        return (car, cq, cb) -> cb.like(car.get("driveUnit"), "%" + driveUnit + "%");
    }

    @Override
    public Specification<Car> transmissionContains(String transmission) {
        return (car, cq, cb) -> cb.like(car.get("transmission"), "%" + transmission + "%");
    }

    @Override
    public Specification<Car> engineTypeContains(String engineType) {
        return (car, cq, cb) -> cb.like(car.get("engineType"), "%" + engineType + "%");
    }

    @Override
    public Specification<Car> engineVolumeGreaterThen(double engineVolume) {
        return (car, cq, cb) -> cb.gt(car.get("engineVolume"), engineVolume);
    }

    @Override
    public Specification<Car> engineVolumeLessThen(double engineVolume) {
        return (car, cq, cb) -> cb.lt(car.get("engineVolume"), engineVolume);

    }

    @Override
    public Specification<Car> ageGreaterThen(int age) {
        return (car, cq, cb) -> cb.gt(car.get("age"), age);
    }

    @Override
    public Specification<Car> ageLessThen(int age) {
        return (car, cq, cb) -> cb.lt(car.get("age"), age);
    }

    @Override
    public Specification<Car> mileAgeLessThen(int mileAge) {
        return (car, cq, cb) -> cb.lt(car.get("mileAge"), mileAge);
    }

    @Override
    public Specification<Car> priceGreaterThen(int price) {
        return (car, cq, cb) -> cb.gt(car.get("price"), price);
    }

    @Override
    public Specification<Car> priceLessThen(int price) {
        return (car, cq, cb) -> cb.lt(car.get("price"), price);
    }

    @Override
    public Specification<Car> getFilterSpecification(CarFilter filter) {


        Specification<Car> spec = Specification.where(null);
        if (filter.getBrand() != null) spec = spec.and(brandContains(filter.getBrand()));
        if (filter.getModel() != null) spec = spec.and(modelContains(filter.getModel()));
        if (filter.getGeneration() != null) spec = spec.and(generationContains(filter.getGeneration()));
        if (filter.getBody() != null) spec = spec.and(bodyContains(filter.getBody()));
        if (filter.getDriveUnit() != null) spec = spec.and(driveUnitContains(filter.getDriveUnit()));
        if (filter.getTransmission() != null) spec = spec.and(transmissionContains(filter.getTransmission()));
        if (filter.getEngineType() != null) spec = spec.and(engineTypeContains(filter.getEngineType()));
        if (filter.getMinEngineVolume() != null) spec = spec.and(engineVolumeGreaterThen(filter.getMinEngineVolume()));
        if (filter.getMaxEngineVolume() != null) spec = spec.and(engineVolumeLessThen(filter.getMaxEngineVolume()));
        if (filter.getMinAge() != null) spec = spec.and(ageGreaterThen(filter.getMinAge()));
        if (filter.getMaxAge() != null) spec = spec.and(ageLessThen(filter.getMaxAge()));
        if (filter.getMaxMileAge() != null) spec = spec.and(mileAgeLessThen(filter.getMaxMileAge()));
        if (filter.getMinPrice() != null) spec = spec.and(priceGreaterThen(filter.getMinPrice()));
        if (filter.getMaxPrice() != null) spec = spec.and(priceLessThen(filter.getMaxPrice()));
        return spec;
    }
}
