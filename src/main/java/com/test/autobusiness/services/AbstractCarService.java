package com.test.autobusiness.services;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.filters.CarRepresentation;

import java.util.List;

public abstract class AbstractCarService {

    abstract public List<Car> getFilteredCars(CarRepresentation carRep);
}
