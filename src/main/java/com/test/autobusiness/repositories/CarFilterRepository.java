package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFilterRepository {

    Specification<Car> brandContains(String brand);

    Specification<Car> modelContains(String model);

    Specification<Car> generationContains(String generation);

    Specification<Car> bodyContains(String body);

    Specification<Car> driveUnitContains(String driveUnit);

    Specification<Car> transmissionContains(String transmission);

    Specification<Car> engineTypeContains(String engineType);

    Specification<Car> engineVolumeGreaterThen(double engineVolume);

    Specification<Car> engineVolumeLessThen(double engineVolume);

    Specification<Car> ageGreaterThen(int age);

    Specification<Car> ageLessThen(int age);

    Specification<Car> mileAgeLessThen(int mileAge);

    Specification<Car> priceGreaterThen(int price);

    Specification<Car> priceLessThen(int price);

    Specification<Car> getFilterSpecification(CarFilter carFilter);
}
