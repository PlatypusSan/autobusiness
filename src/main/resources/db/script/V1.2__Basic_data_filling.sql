--liquibase formatted sql
--changeset platyPuss:3
insert
into declaration (id, date, description, phone_number, place, vendor_name)
values (1, '2005-05-05 00:00:00.000000', 'dec description 1', '123-63-61', 'Minsk', 'Goose'),
       (2, '2015-11-01 00:00:00.000000', 'dec description 2', '846-73-52', 'LA', 'Ivan'),
       (3, '2018-7-23 00:00:00.000000', 'dec description 3', '318-23-37', 'Berlin', 'Peter');

insert
into car (id, age, body, brand, deleted, drive_unit, engine_type, engine_volume, generation, mile_age, model, price,
          transmission, declaration_id)
values (4, 2005, 'Hatchback', 'Volkswagen', false, 'BACK_WHEEL', 'PETROL', 1.8, 4, 145000, 'Jetta', 9350, 'MANUAL', 1),
       (5, 2016, 'Sedan', 'Skoda', false, 'FRONT_WHEEL', 'DIESEL', 2, 3, 198000, 'Superb', 20400, 'MANUAL', 1),
       (6, 2012, 'Van', 'Opel', false, 'FRONT_WHEEL', 'PETROL', 1.4, 2, 93000, 'Meriva', 7350, 'MANUAL', 1),
       (7, 2015, 'Hatchback', 'Peugeot', false, 'FRONT_WHEEL', 'DIESEL', 1.6, 2, 180000, '308', 11650, 'MANUAL', 1),
       (8, 2008, 'Sedan', 'Honda', false, 'BACK_WHEEL', 'GAS', 2.4, 7, 220000, 'Accord', 7150, 'AUTO', 1),
       (9, 2016, 'Sedan', 'Kia', false, 'FOUR_WHEEL', 'PETROL', 1.6, 3, 87000, 'Rio', 9350, 'MANUAL', 1),
       (10, 2009, 'Hatchback', 'Volkswagen', false, 'FRONT_WHEEL', 'PETROL', 1.8, 6, 380000, 'Golf', 6000, 'MANUAL', 2),
       (11, 2016, 'Hatchback', 'Volvo ', false, 'FRONT_WHEEL', 'DIESEL', 2.0, 2, 175000, 'V40', 12900, 'AUTO', 2),
       (12, 2010, 'MUV', 'BMW', false, 'FOUR_WHEEL', 'PETROL', 3.0, 1, 170000, 'X6', 17000, 'AUTO', 2);

insert
into details (id, detail_name, detail_type)
values (13, 'Bluetooth', 'Multimedia'),
       (14, 'USB', 'Multimedia'),
       (15, 'ABS', 'Safety'),
       (16, 'ESP', 'Safety'),
       (17, 'Acsel', 'Comfort');

insert into car_details (details_id, car_id)
values (15, 4),
       (14, 4),
       (16, 4),
       (14, 5),
       (13, 5),
       (16, 8),
       (15, 8),
       (14, 8);


