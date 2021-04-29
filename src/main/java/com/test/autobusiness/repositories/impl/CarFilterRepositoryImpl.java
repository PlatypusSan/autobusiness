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
    public Specification<Car> getFilterSpecification(CarFilter carFilter) {
        Specification<Car> specification =
                priceLessThen(carFilter.getMaxPrice())
                        .and(priceGreaterThen(carFilter.getMinPrice()))
                        .and(mileAgeLessThen(carFilter.getMaxMileAge()))
                        .and(ageLessThen(carFilter.getMaxAge()))
                        .and(ageGreaterThen(carFilter.getMinAge()))
                        .and(engineVolumeLessThen(carFilter.getMaxEngineVolume()))
                        .and(engineVolumeGreaterThen(carFilter.getMinEngineVolume()))
                        .and(engineTypeContains(carFilter.getEngineType()))
                        .and(driveUnitContains(carFilter.getDriveUnit()))
                        .and(transmissionContains(carFilter.getTransmission()))
                        .and(bodyContains(carFilter.getBody()))
                        .and(generationContains(carFilter.getGeneration()))
                        .and(modelContains(carFilter.getModel()))
                        .and(brandContains(carFilter.getBrand()));

        return specification;
    }
}
