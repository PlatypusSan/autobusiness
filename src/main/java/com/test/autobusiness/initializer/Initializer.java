package com.test.autobusiness.initializer;

import com.test.autobusiness.entities.*;
import com.test.autobusiness.entities.columnEnums.DriveUnit;
import com.test.autobusiness.entities.columnEnums.EngineType;
import com.test.autobusiness.entities.columnEnums.Transmission;
import com.test.autobusiness.repositories.CarRepository;
import com.test.autobusiness.repositories.RoleRepository;
import com.test.autobusiness.services.DeclarationService;
import com.test.autobusiness.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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


        //roles
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleRepository.saveAndFlush(role);
        role = new Role();
        role.setName("ROLE_USER");
        roleRepository.saveAndFlush(role);

        //users
        User user = new User();
        user.setUsername("admin");
        user.setPassword("1234");
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        userService.save(user);
        user = new User();
        user.setUsername("user");
        user.setPassword("1234");
        userService.save(user);

        Car car1 = new Car("Renault", "Megane", "3", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.8, 2014, 14650, 9500);
        Details detail1 = new Details("Safety", "ABS");
        Details detail2 = new Details("Safety", "ESP");
        Details detail3 = new Details("Comfort", "Acsel");
        Set<Details> details = new HashSet<>();
        details.add(detail1);
        details.add(detail2);
        details.add(detail3);


        try {
            Declaration dec1 = new Declaration("+375 (33) 333-55-66", "Nice car, btw",
                    "Goose", "Minsk", new SimpleDateFormat("yyyy-MM-dd").parse("2005-5-5"));
            dec1.setCar(car1);
            dec1.setDetails(details);
            declarationService.addDeclaration(dec1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //carRepository.save(car1);
        carRepository.save(new Car("Ford", "Focus", "3", "Хэтчбек 4 дв.",
                DriveUnit.BACK_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.9, 2014, 1233, 2342));
        carRepository.save(new Car("Chevrolet", "Captiva", "1", "Хэтчбек 5 дв.",
                DriveUnit.FOUR_WHEEL, Transmission.AUTO, EngineType.DIESEL, 1.4, 2004, 45354, 34234));
        carRepository.save(new Car("Ford", "Fusion", "2", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 1.6, 2011, 54554, 7566));
        carRepository.save(new Car("Skoda", "Octavia", "1", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.AUTO, EngineType.DIESEL, 1.6, 2015, 435, 4534));
        carRepository.save(new Car("Nissan", "Navara", "3", "Хэтчбек 5 дв.",
                DriveUnit.BACK_WHEEL, Transmission.MANUAL, EngineType.GAS, 1.6, 2014, 45545, 3244));
        carRepository.save(new Car("Volkswagen", "Touran", "1", "Хэтчбек 5 дв.",
                DriveUnit.FRONT_WHEEL, Transmission.MANUAL, EngineType.PETROL, 2.0, 2020, 4534, 11111));
        System.out.println("__________INIT__________");
    }
}