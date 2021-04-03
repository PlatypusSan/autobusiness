package com.test.autobusiness.initializer;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.Declaration;
import com.test.autobusiness.entities.columnenums.DriveUnit;
import com.test.autobusiness.entities.columnenums.EngineType;
import com.test.autobusiness.entities.columnenums.Transmission;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.repositories.RoleRepository;
import com.test.autobusiness.services.DeclarationService;
import com.test.autobusiness.services.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Initializer implements ApplicationListener<ApplicationReadyEvent> {

    private final CarRepository carRepository;

    private final RoleRepository roleRepository;

    private final DeclarationService declarationService;

    private final UserService userService;

    public Initializer(CarRepository carRepository, RoleRepository roleRepository,
                       DeclarationService declarationService, UserService userService) {
        this.carRepository = carRepository;
        this.roleRepository = roleRepository;
        this.declarationService = declarationService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        System.out.println("__________INIT__________");

        List<Car> cars1 = new ArrayList<>();
        cars1.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        cars1.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        cars1.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        cars1.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FOUR_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        cars1.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.BACK_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        cars1.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.BACK_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));

        List<Car> cars2 = new ArrayList<>();
        cars2.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        cars2.add(new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500));
        try {
            Declaration dec1 = new Declaration("+375 (33) 333-55-66", "Nice cars, btw",
                    "Goose", "Minsk", new SimpleDateFormat("yyyy-MM-dd").parse("2005-5-5"));
            dec1.setCars(cars1);
            declarationService.addDeclaration(dec1);

            Declaration dec2 = new Declaration("+375 (33) 333-55-66", "Nice cars, btw",
                    "Nick", "Minsk", new SimpleDateFormat("yyyy-MM-dd").parse("2005-5-5"));
            dec2.setCars(cars2);
            declarationService.addDeclaration(dec2);


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("________INIT_END________");
    }
}