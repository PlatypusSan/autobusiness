package com.test.autobusiness.initializer;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.columnEnums.DriveUnit;
import com.test.autobusiness.entities.columnEnums.EngineType;
import com.test.autobusiness.entities.columnEnums.Transmission;
import com.test.autobusiness.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Initializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    CarRepository carRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        carRepository.save(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        carRepository.save(new Car("aaaaa", "nnnn", "3", "Хэтчбек 5 дв.",
                DriveUnit.BACK_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.9, 2014, 1233, 2342));
        carRepository.save(new Car("vvvvv", "ffffff", "3", "Хэтчбек 5 дв.",
                DriveUnit.FOUR_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.4, 2014, 453454, 34234));
        carRepository.save(new Car("ddddd", "eeeee", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.6, 2014, 5455454, 7566));
        carRepository.save(new Car("ggggg", "aaaaaa", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.6, 2014, 435, 4534));
        carRepository.save(new Car("wwwww", "ccccc", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.6, 2014, 453545, 3244));
        carRepository.save(new Car("vvvvv", "bbbbb", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.6, 2014, 4534, 11111));
    }
}