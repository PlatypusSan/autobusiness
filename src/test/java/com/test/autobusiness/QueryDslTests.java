package com.test.autobusiness;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.autobusiness.entities.QCar;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@Slf4j
@SpringBootTest
public class QueryDslTests {

    @Autowired
    EntityManager entityManager;

    @Test
    void selectCar() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCar qCar = QCar.car;

        Predicate[] predicates = new Predicate[]{
                qCar.id.eq(4L),
                qCar.brand.eq("Volkswagen")
        };

        /*Car car = queryFactory
                .selectFrom(qCar)
                .where(QCar.car.brand.contains(""))
                .fetchOne();*/

    }
}
