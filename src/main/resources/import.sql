-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
SET search_path TO parkshark;
--Division
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Top Level', 'Original Top Level', 'Director', null);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 1a', 'Original Sub Level 1a', 'Director Lvl1a', 1);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 1b', 'Original Sub Level 1b', 'Director Lvl1b', 1);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 2', 'Original Sub Level 2', 'Director Lvl2', 2);
--Member
insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,
                   streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'Zineb', 'El tuti', '0123456789',
        'zineb.eltuti@mc.be', '1-ABC-111', 'BE', '2023-11-07', 'BRONZE', 'chien vert', '17', 1150, 'Woluwe St Pierre');
insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,
                   streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'Eli', 'kovic', '0987415266',
        'eli.kovic@mc.be', '1-BBB-111', 'BE', '2023-11-07', 'GOLD', 'chien rouge', '18', 1200, 'Woluwe St lambert');
insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,
                   streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'aaa', 'bbb', '0123456789',
        'aaa.bbb@mc.be', '1-CFR-123', 'FR', '2023-11-07', 'SILVER', 'chien orange', '58', 1000, 'BRUSSEL');
--User
insert into users(id, password, role, user_id, fk_member_id)
values (nextval('users_seq'), '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'MANAGER', 'admin',
        1);
insert into users(id, password, role, user_id, fk_member_id)
values (nextval('users_seq'), '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'MEMBER', 'goldmember',
        2);
insert into users(id, password, role, user_id, fk_member_id)
values (nextval('users_seq'), '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'MEMBER', 'silvermember',
        3);

insert into contact_person(id,
                           phone_number,
                           mobile_phone_number,
                           email,
                           streetname,
                           streetnumber,
                           postalcode,
                           postallabel)
values (nextval('contact_person_seq'), '024568736', '0465789632', 'aaa@aaa.aaa', 'Again street', '65', '1000',
        'Brussels');

insert into contact_person(id,
                           phone_number,
                           mobile_phone_number,
                           email,
                           streetname,
                           streetnumber,
                           postalcode,
                           postallabel)
values (nextval('contact_person_seq'), '024568737', null, 'bbb@bbb.bbb', 'Not again street', '66', '1001',
        'Ghent');

insert into contact_person(id,
                           phone_number,
                           mobile_phone_number,
                           email,
                           streetname,
                           streetnumber,
                           postalcode,
                           postallabel)
values (nextval('contact_person_seq'), null, '0465789634', 'ccc@ccc.ccc', 'No more street', '67', '1002',
        'Antwerp');



insert into parking_lot(id,
                        name,
                        capacity,
                        category,
                        number_of_places_available,
                        price_per_hour_in_euro,
                        streetname,
                        streetnumber,
                        postalcode,
                        postallabel,
                        fk_contact_person_id,
                        fk_division_id)
values (nextval('parking_lot_seq'), 'Parking Lot A', 500, 0,
        500, 2, 'Test street', '6', '1180', 'Uccle', 1, 1);

insert into parking_lot(id,
                        name,
                        capacity,
                        category,
                        number_of_places_available,
                        price_per_hour_in_euro,
                        streetname,
                        streetnumber,
                        postalcode,
                        postallabel,
                        fk_contact_person_id,
                        fk_division_id)
values (nextval('parking_lot_seq'), 'Parking Lot B', 1000, 1,
        1000, 3, 'Test2 street', '7', '1030', 'Schaerbeek', 2, 2);

insert into parking_lot(id,
                        name,
                        capacity,
                        category,
                        number_of_places_available,
                        price_per_hour_in_euro,
                        streetname,
                        streetnumber,
                        postalcode,
                        postallabel,
                        fk_contact_person_id,
                        fk_division_id)
values (nextval('parking_lot_seq'), 'Parking Lot C', 1500, 0,
        1500, 5, 'Test3 street', '8', '1020', 'Laeken', 3, 3);

