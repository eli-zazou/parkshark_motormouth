-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
SET search_path TO parkshark;

insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Top Level', 'Original Top Level', 'Director', null);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 1a', 'Original Sub Level 1a', 'Director Lvl1a', 1);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 1b', 'Original Sub Level 1b', 'Director Lvl1b', 1);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 2', 'Original Sub Level 2', 'Director Lvl2', 2);



insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'Zineb', 'El tuti', '0123456789',
        'zineb.eltuti@mc.be', '1-ABC-111', 'BE', '2023-11-07', 'BRONZE', 'chien vert', '17', 1150, 'Woluwe St Pierre');
insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'Eli', 'kovic', '0987415266',
        'eli.kovic@mc.be', '1-BBB-111', 'BE', '2023-11-07', 'COLD', 'chien rouge', '18', 1200, 'Woluwe St lambert');
insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'aaa', 'bbb', '0123456789',
        'aaa.bbb@mc.be', '1-CFR-123', 'FR', '2023-11-07', 'SILVER', 'chien orange', '58', 1000, 'BRUSSEL');